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
 * LTL Order App Token Authentication API
 * Generates access token for order creation in LTL flow
 */
public class LtlOrderAppAuthApi {
    
    private static String cachedOrderAppToken = null;
    private static final Lock tokenLock = new ReentrantLock();
    
    /**
     * Authenticate to get order app token using client credentials
     * @return Response containing accessToken
     */
    public static Response authenticate() {
        ConfigLoader config = ConfigLoader.getInstance();
        
        // Use the same credentials as TMS auth (from curl provided)
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
     * Get order app access token with thread-safe caching
     * @return Access token string
     */
    public static String getOrderAppToken() {
        // Check if token exists without lock (fast path)
        if (cachedOrderAppToken != null) {
            return cachedOrderAppToken;
        }
        
        // Acquire lock for token generation
        tokenLock.lock();
        try {
            // Double-check after acquiring lock
            if (cachedOrderAppToken != null) {
                return cachedOrderAppToken;
            }
            
            System.out.println("=== Generating LTL Order App Token ===");
            Response response = authenticate();
            
            if (response.getStatusCode() == 200) {
                TmsAuthResponse authResponse = response.as(TmsAuthResponse.class);
                cachedOrderAppToken = authResponse.getData().getAccessToken();
                System.out.println("Order App Token cached: " + cachedOrderAppToken.substring(0, Math.min(20, cachedOrderAppToken.length())) + "...");
                return cachedOrderAppToken;
            } else {
                throw new RuntimeException("Order App Authentication failed with status: " + response.getStatusCode() 
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
            cachedOrderAppToken = null;
            System.out.println("Order App token cache cleared");
        } finally {
            tokenLock.unlock();
        }
    }
}

