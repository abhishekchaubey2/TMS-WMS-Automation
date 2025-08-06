package com.delhivery.core.api;

// import io.restassured.response.Response;

// public class Auth {
//     private static String auth_token;

//     private Response getAuthToken(){

//         return null;
//     }

// }

import com.delhivery.core.utils.ConfigLoader;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

/**
 * Authentication Manager for handling JWT tokens and authentication headers
 */
public class Auth {
    private static Auth instance;
    private ConfigLoader configLoader;
    private TokenGenerator tokenGenerator;
    
    // Store tokens in memory for the duration of the test run
    private String xCoreosAccess;
    private String xCoreosAuth;
    private String xTmsDataAccess;
    
    private Auth() {
        this.configLoader = ConfigLoader.getInstance();
        this.tokenGenerator = new TokenGenerator();
    }
    
    public static Auth getInstance() {
        if (instance == null) {
            synchronized (Auth.class) {
                if (instance == null) {
                    instance = new Auth();
                }
            }
        }
        return instance;
    }
    
    /**
     * Add authentication headers to the request specification
     */
    public RequestSpecification addAuthHeaders(RequestSpecification requestSpec) {
        // Ensure tokens are generated before adding headers
        generateAndSetAllTokens();
        
        return requestSpec
                .header("x-coreos-access", this.xCoreosAccess)
                .header("x-coreos-auth", this.xCoreosAuth)
                .header("x-tms-data-access", this.xTmsDataAccess)
                .header("x-coreos-tid", configLoader.getXCoreosTid())
                .header("x-coreos-userinfo", configLoader.getXCoreosUserinfo());
    }
    
    /**
     * Get all authentication headers as a map
     */
    public Map<String, String> getAuthHeaders() {
        // Ensure tokens are generated before returning headers
        generateAndSetAllTokens();
        
        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("x-coreos-access", this.xCoreosAccess);
        authHeaders.put("x-coreos-auth", this.xCoreosAuth);
        authHeaders.put("x-tms-data-access", this.xTmsDataAccess);
        authHeaders.put("x-coreos-tid", configLoader.getXCoreosTid());
        authHeaders.put("x-coreos-userinfo", configLoader.getXCoreosUserinfo());
        return authHeaders;
    }
    
    /**
     * Generates and sets all required tokens.
     * This method should be called before any request that requires authentication.
     */
    private void generateAndSetAllTokens() {
        System.out.println("Generating new tokens...");
        Map<String, String> newTokenDetails = tokenGenerator.generateAccessToken(
                configLoader.getProperty("client.id"),
                configLoader.getProperty("client.secret"),
                configLoader.getProperty("client.audience"),
                configLoader.getFixedRequestId(), // Use fixed request ID
                configLoader.getXCoreosTid()
        );
        
        if (newTokenDetails != null && newTokenDetails.containsKey("accessToken")) {
            this.xCoreosAccess = newTokenDetails.get("accessToken");
            this.xCoreosAuth = newTokenDetails.get("accessToken"); // Assuming x-coreos-auth is the same as access token
            this.xTmsDataAccess = configLoader.getXTmsDataAccess(); // This token is still from config
            System.out.println("New tokens generated and set.");
        } else {
            throw new RuntimeException("Failed to generate new tokens.");
        }
    }
    
    /**
     * Validate if all required authentication tokens are present
     */
    public boolean areTokensValid() {
        // Since tokens are generated on demand, this method will trigger generation
        // and check if they are non-null.
        try {
            generateAndSetAllTokens();
            return this.xCoreosAccess != null && !this.xCoreosAccess.isEmpty() &&
                   this.xCoreosAuth != null && !this.xCoreosAuth.isEmpty();
        } catch (RuntimeException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Generate a fixed request ID for x-coreos-request-id header
     */
    public String generateRequestId() {
        return configLoader.getFixedRequestId();
    }
}

