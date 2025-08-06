package com.delhivery.core.api;

import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/**
 * Thread-safe Token Manager for JWT token operations
 * 
 * Principles followed:
 * 1. Thread Safety - Uses ConcurrentHashMap and ReentrantLock
 * 2. Multiple Users Support - User-specific token storage
 * 3. Lock Management - Fine-grained locking per user
 * 4. Single Responsibility - Only handles token operations
 */
public class TokenManager {
    
    // Thread-safe storage for user tokens
    private static final ConcurrentHashMap<String, TokenInfo> userTokens = new ConcurrentHashMap<>();
    
    // Thread-safe lock management per user
    private static final ConcurrentHashMap<String, Lock> userLocks = new ConcurrentHashMap<>();
    
    // Singleton instance
    private static volatile TokenManager instance;
    private static final Lock instanceLock = new ReentrantLock();
    
    private TokenManager() {
        // Private constructor for singleton
    }
    
    /**
     * Thread-safe singleton getter
     */
    public static TokenManager getInstance() {
        if (instance == null) {
            instanceLock.lock();
        try {
                if (instance == null) {
                    instance = new TokenManager();
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
            }
            
    /**
     * Generates a fresh X-CoreOS auth token for the specified user
     * Thread-safe and supports multiple users simultaneously
     * 
     * @param userId The user identifier (can be null for default user)
     * @return The generated auth token
     */
    public String generateXCoreosAuthToken(String userId) {
        String userKey = (userId != null) ? userId : "default";
        Lock userLock = getUserLock(userKey);
        
        userLock.lock();
        try {
            // Always generate fresh token (no caching as per requirements)
            return generateFreshToken();
        } finally {
            userLock.unlock();
        }
    }
    
    /**
     * Generates a fresh X-CoreOS auth token for default user
     * Thread-safe method for backward compatibility
     * 
     * @return The generated auth token
     */
    public static String generateXCoreosAuthToken() {
        return getInstance().generateXCoreosAuthToken(null);
    }
    
    /**
     * Gets token for backward compatibility
     * @param tokenType The type of token (unused but kept for compatibility)
     * @return The generated auth token
     */
    public static String getToken(String tokenType) {
        return generateXCoreosAuthToken();
            }
            
    /**
     * Gets or creates a lock for a specific user
     * Thread-safe lock management
     */
    private Lock getUserLock(String userId) {
        return userLocks.computeIfAbsent(userId, k -> new ReentrantLock());
    }
    
    /**
     * Generates a fresh token by making API call
     * Single responsibility: Only handles token generation
     */
    private String generateFreshToken() {
        try {
            String clientId = ConfigLoader.getInstance().getProperty("tms_client_id");
            String clientSecret = ConfigLoader.getInstance().getProperty("tms_client_secret");
            String audience = "platform:app:coreos";
            
            System.out.println("=== Token Generation Debug ===");
            System.out.println("Client ID: " + clientId);
            System.out.println("Client Secret: " + (clientSecret != null ? clientSecret.substring(0, Math.min(10, clientSecret.length())) + "..." : "null"));
            System.out.println("Audience: " + audience);
            
            // Prepare request body for client credentials
            String requestBody = String.format("{\"clientId\":\"%s\",\"clientSecret\":\"%s\",\"audience\":\"%s\"}", 
                clientId, clientSecret, audience);
            
            System.out.println("Request Body: " + requestBody);
            
            Response response = given()
                    .baseUri(ConfigLoader.getInstance().getBaseUrl())
                    .header("X-COREOS-REQUEST-ID", ConfigLoader.getInstance().getFixedRequestId())
                    .header("Content-Type", "application/json")
                    .header("X-COREOS-TID", ConfigLoader.getInstance().getXCoreosTid())
                    .body(requestBody)
                    .when()
                    .post("/core/api/v1/aaa/auth/client-credentials")
                    .then()
                    .extract().response();
            
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            if (response.getStatusCode() == 200) {
                String responseBody = response.getBody().asString();
                String token = response.jsonPath().getString("data.accessToken");
                
                if (token != null && !token.isEmpty()) {
                    System.out.println("Token extracted successfully: " + token.substring(0, Math.min(50, token.length())) + "...");
                    return token;
                } else {
                    throw new RuntimeException("Token not found in response: " + responseBody);
                }
            } else {
                throw new RuntimeException("Token generation failed with status: " + response.getStatusCode() + 
                    ", Response: " + response.getBody().asString());
            }
        } catch (Exception e) {
            System.err.println("Error generating X-CoreOS auth token: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to generate X-CoreOS auth token", e);
        }
    }
    
    /**
     * Clears all cached tokens and locks (for cleanup)
     * Thread-safe cleanup method
     */
    public void clearAllTokens() {
        userTokens.clear();
        userLocks.clear();
    }
    
    /**
     * Clears tokens for a specific user
     * Thread-safe per-user cleanup
     */
    public void clearUserTokens(String userId) {
        String userKey = (userId != null) ? userId : "default";
        userTokens.remove(userKey);
        userLocks.remove(userKey);
    }
    
    /**
     * Inner class to store token information
     * Single responsibility: Token data structure
     */
    private static class TokenInfo {
        private final String token;
        private final long generationTime;
        
        public TokenInfo(String token, long generationTime) {
            this.token = token;
            this.generationTime = generationTime;
        }
        
        public String getToken() {
            return token;
        }
        
        public long getGenerationTime() {
            return generationTime;
        }
    }
}
