package com.delhivery.TMS_WMS.api;

import com.delhivery.TMS_WMS.pojo.tmsauth.request.TmsAuthRequest;
import com.delhivery.TMS_WMS.pojo.tmsauth.response.TmsAuthResponse;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static io.restassured.RestAssured.given;

/**
 * Thread-safe TMS Client Credentials Authentication API with token caching
 */
public class TmsAuthApi {
    
    private static String cachedToken = null;
    private static final Lock tokenLock = new ReentrantLock();
    
    /**
     * Authenticate with TMS using client credentials
     * @return Response containing access_token
     */
    public static Response authenticate() {
        ConfigLoader config = ConfigLoader.getInstance();
        
        TmsAuthRequest authRequest = new TmsAuthRequest(
            config.getTmsAuthClientId(),
            config.getTmsAuthClientSecret(),
            config.getTmsAuthAudience()
        );
        
        return given()
                .baseUri(config.getTmsAuthBaseUrl())
                .contentType(ContentType.JSON)
                .header("X-COREOS-REQUEST-ID", "123")
                .header("X-COREOS-TID", config.getTmsAuthTid())
                .body(authRequest)
                .when()
                .post(config.getTmsAuthEndpoint())
                .then()
                .extract()
                .response();
    }
    
    /**
     * Get TMS access token with thread-safe caching
     * @return Access token string
     */
    public static String getAccessToken() {
        // Check if token exists without lock (fast path)
        if (cachedToken != null) {
            return cachedToken;
        }
        
        // Acquire lock for token generation
        tokenLock.lock();
        try {
            // Double-check after acquiring lock
            if (cachedToken != null) {
                return cachedToken;
            }
            
            System.out.println("=== Generating TMS Access Token ===");
            Response response = authenticate();
            
            if (response.getStatusCode() == 200) {
                TmsAuthResponse authResponse = response.as(TmsAuthResponse.class);
                cachedToken = authResponse.getData().getAccessToken();
                System.out.println("TMS Token cached: " + cachedToken.substring(0, Math.min(20, cachedToken.length())) + "...");
                return cachedToken;
            } else {
                throw new RuntimeException("TMS Authentication failed with status: " + response.getStatusCode() 
                    + ", Response: " + response.getBody().asString());
            }
        } finally {
            tokenLock.unlock();
        }
    }
    
    /**
     * Clear cached token (for testing or token expiry)
     */
    public static void clearToken() {
        tokenLock.lock();
        try {
            cachedToken = null;
            System.out.println("TMS token cache cleared");
        } finally {
            tokenLock.unlock();
        }
    }
}
