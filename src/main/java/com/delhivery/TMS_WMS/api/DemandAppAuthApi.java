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
 * Demand App Token Authentication API
 * Generates access token for demand/load operations in TMS
 */
public class DemandAppAuthApi {
    
    private static String cachedDemandAppToken = null;
    private static final Lock tokenLock = new ReentrantLock();
    
    /**
     * Authenticate to get demand app token using client credentials
     * @return Response containing accessToken
     */
    public static Response authenticate() {
        ConfigLoader config = ConfigLoader.getInstance();
        
        // Use demand app specific credentials
        TmsAuthRequest authRequest = new TmsAuthRequest(
            config.getTmsDemandAppClientId(),
            config.getTmsDemandAppClientSecret(),
            config.getTmsDemandAppAudience()
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
     * Get demand app access token with thread-safe caching
     * @return Access token string
     */
    public static String getDemandAppToken() {
        // Check if token exists without lock (fast path)
        if (cachedDemandAppToken != null) {
            return cachedDemandAppToken;
        }
        
        // Acquire lock for token generation
        tokenLock.lock();
        try {
            // Double-check after acquiring lock
            if (cachedDemandAppToken != null) {
                return cachedDemandAppToken;
            }
            
            System.out.println("=== Generating Demand App Token ===");
            Response response = authenticate();
            
            if (response.getStatusCode() == 200) {
                TmsAuthResponse authResponse = response.as(TmsAuthResponse.class);
                cachedDemandAppToken = authResponse.getData().getAccessToken();
                System.out.println("Demand App Token cached: " + cachedDemandAppToken.substring(0, Math.min(20, cachedDemandAppToken.length())) + "...");
                return cachedDemandAppToken;
            } else {
                throw new RuntimeException("Demand App Authentication failed with status: " + response.getStatusCode() 
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
            cachedDemandAppToken = null;
            System.out.println("Demand App token cache cleared");
        } finally {
            tokenLock.unlock();
        }
    }
}

