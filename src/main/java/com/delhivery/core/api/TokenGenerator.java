package com.delhivery.core.api;

import com.delhivery.core.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility to generate new access tokens using client credentials flow.
 */
public class TokenGenerator {
    private ConfigLoader configLoader;
    
    public TokenGenerator() {
        this.configLoader = ConfigLoader.getInstance();
    }
    
    /**
     * Generates a new access token using client ID and client secret.
     * @param clientId The client ID.
     * @param clientSecret The client secret.
     * @param audience The audience for the token.
     * @param xCoreosRequestId A unique request ID.
     * @param xCoreosTid The tenant ID.
     * @return A map containing the access token and its expiry details, or null if generation fails.
     */
    public Map<String, String> generateAccessToken(String clientId, String clientSecret, String audience, String xCoreosRequestId, String xCoreosTid) {
        String tokenEndpoint = configLoader.getBaseUrl() + "/core/api/v1/aaa/auth/client-credentials";
        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("clientId", clientId);
        requestBody.put("clientSecret", clientSecret);
        requestBody.put("audience", audience);
        
        Response response = RestAssured.given()
                .baseUri(configLoader.getBaseUrl())
                .contentType(ContentType.JSON)
                .header("X-COREOS-REQUEST-ID", xCoreosRequestId)
                .header("X-COREOS-TID", xCoreosTid)
                .body(requestBody)
                .when()
                .post(tokenEndpoint)
                .then()
                .log().all()
                .extract()
                .response();
        
        if (response.getStatusCode() == 200) {
            Map<String, String> tokenDetails = new HashMap<>();
            tokenDetails.put("accessToken", response.jsonPath().getString("data.accessToken"));
            tokenDetails.put("expiresIn", String.valueOf(response.jsonPath().getLong("data.expiresIn")));
            return tokenDetails;
        } else {
            System.err.println("Failed to generate access token. Status code: " + response.getStatusCode());
            System.err.println("Response body: " + response.getBody().asString());
            return null;
        }
    }
}

