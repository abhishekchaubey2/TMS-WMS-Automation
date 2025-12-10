package com.delhivery.TMS_WMS.api;

import com.delhivery.TMS_WMS.pojo.wmsauth.request.WmsAuthRequest;
import com.delhivery.TMS_WMS.pojo.wmsauth.response.WmsAuthResponse;
import com.delhivery.core.api.SpecBuilder;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static io.restassured.RestAssured.given;

/**
 * Thread-safe WMS Authentication API with token caching
 */
public class WmsAuthApi {
    
    private static String cachedToken = null;
    private static final Lock tokenLock = new ReentrantLock();
    
    /**
     * Authenticate with WMS and get access token (thread-safe with caching)
     * @return Response containing access_token
     */
    public static Response authenticate() {
        ConfigLoader config = ConfigLoader.getInstance();
        
        WmsAuthRequest authRequest = new WmsAuthRequest(
            config.getWmsUsername(),
            config.getWmsPassword()
        );
        
        return given()
                .spec(SpecBuilder.getWmsRequestSpec())
                .body(authRequest)
                .when()
                .post(config.getWmsAuthEndpoint())
                .then()
                .extract()
                .response();
    }
    
    /**
     * Get WMS access token with thread-safe caching
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
            
            System.out.println("=== Generating WMS Access Token ===");
            Response response = authenticate();
            
            if (response.getStatusCode() == 200) {
                WmsAuthResponse authResponse = response.as(WmsAuthResponse.class);
                cachedToken = authResponse.getData().getAccessToken();
                System.out.println("WMS Token cached: " + cachedToken.substring(0, Math.min(20, cachedToken.length())) + "...");
                return cachedToken;
            } else {
                throw new RuntimeException("WMS Authentication failed with status: " + response.getStatusCode());
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
            System.out.println("WMS token cache cleared");
        } finally {
            tokenLock.unlock();
        }
    }
}
