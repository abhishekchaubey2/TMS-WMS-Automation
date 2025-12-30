package com.delhivery.TMS_WMS.applicationApi;

import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Orion Application API
 * Contains all Orion API endpoint methods
 */
public class OrionApiRequests {
    
    private static final ConfigLoader config = ConfigLoader.getInstance();
    
    /**
     * Get Trip by External Mapping ID (Load ID)
     * Fetches trip information from Orion using the load unique code
     * 
     * @param externalMappingId The load unique code (e.g., DLV-S7SYD1)
     * @return GetTripResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse getTripByExternalMappingId(String externalMappingId) {
        // Get Orion token from properties
        String orionToken = config.getProperty("orion.bearer.token");
        
        if (orionToken == null || orionToken.isEmpty()) {
            throw new RuntimeException("Orion bearer token not found in properties. Please set orion.bearer.token in qa.properties");
        }
        
        String baseUrl = config.getProperty("orion.base.url", "https://orion-load-fullcycle-api-uat.delhivery.com");
        
        System.out.println("=== Orion Get Trip API Call ===");
        System.out.println("Endpoint: /trips/");
        System.out.println("External Mapping ID: " + externalMappingId);
        
        Response response = given()
            .baseUri(baseUrl)
            .header("Authorization", "Bearer " + orionToken)
            .header("Content-Type", "application/json")
            .queryParam("external_mapping_id", externalMappingId)
            .log().all()
        .when()
            .get("/trips/")
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to get trip from Orion. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse tripResponse = 
            response.as(com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse.class);
        
        // Extract and log transaction ID
        String transactionId = tripResponse.getTransactionId();
        if (transactionId != null) {
            System.out.println("✓ Transaction ID extracted: " + transactionId);
        } else {
            System.out.println("⚠ Warning: Transaction ID not found in response");
        }
        
        return tripResponse;
    }
    
    /**
     * Place Bid for a Trip
     * Places a bid on a trip using the transaction ID
     * 
     * @param requestBody PlaceBidRequest payload
     * @return PlaceBidResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse placeBid(
            com.delhivery.TMS_WMS.pojo.orion.request.PlaceBidRequest requestBody) {
        // Get Orion Bid API token from properties
        String orionBidToken = config.getProperty("orion.bid.bearer.token");
        
        if (orionBidToken == null || orionBidToken.isEmpty()) {
            throw new RuntimeException("Orion Bid bearer token not found in properties. Please set orion.bid.bearer.token in qa.properties");
        }
        
        String baseUrl = config.getProperty("orion.bid.base.url", "https://orion-bid-api-uat.delhivery.com");
        
        System.out.println("=== Orion Place Bid API Call ===");
        System.out.println("Endpoint: /bids/");
        System.out.println("Transaction ID: " + requestBody.getTransactionId());
        System.out.println("Supplier ID: " + requestBody.getSupplierId());
        System.out.println("Bid Price: " + requestBody.getBidPrice());
        
        Response response = given()
            .baseUri(baseUrl)
            .header("Authorization", "Bearer " + orionBidToken)
            .header("Content-Type", "application/json")
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("origin", "https://orion-uat.delhivery.com")
            .header("priority", "u=1, i")
            .header("referer", "https://orion-uat.delhivery.com/")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .body(requestBody)
            .log().all()
        .when()
            .post("/bids/")
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201) {
            throw new RuntimeException("Failed to place bid in Orion. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse bidResponse = 
            response.as(com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse.class);
        
        if (bidResponse.getSuccess() != null && bidResponse.getSuccess()) {
            System.out.println("✓ Bid placed successfully");
            if (bidResponse.getData() != null) {
                if (bidResponse.getData().getId() != null) {
                    System.out.println("  Bid ID: " + bidResponse.getData().getId());
                }
                if (bidResponse.getData().getMessage() != null) {
                    System.out.println("  Message: " + bidResponse.getData().getMessage());
                }
            }
        }
        
        return bidResponse;
    }
    
    /**
     * Confirm Bid for a Trip
     * Confirms a bid on a trip using the transaction ID
     * 
     * @param transactionId The transaction ID (trip ID)
     * @param requestBody ConfirmBidRequest payload
     * @return ConfirmBidResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse confirmBid(
            String transactionId,
            com.delhivery.TMS_WMS.pojo.orion.request.ConfirmBidRequest requestBody) {
        // Get Orion Trip API token from properties
        String orionTripToken = config.getProperty("orion.trip.bearer.token");
        
        if (orionTripToken == null || orionTripToken.isEmpty()) {
            throw new RuntimeException("Orion Trip bearer token not found in properties. Please set orion.trip.bearer.token in qa.properties");
        }
        
        String baseUrl = config.getProperty("orion.trip.base.url", "https://orion-trip-api-uat.delhivery.com");
        
        System.out.println("=== Orion Confirm Bid API Call ===");
        System.out.println("Endpoint: /trips/actions/" + transactionId + "/");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Action Code: " + requestBody.getActionCode());
        System.out.println("Action Sub Code: " + requestBody.getActionSubCode());
        
        Response response = given()
            .baseUri(baseUrl)
            .header("Authorization", "Bearer " + orionTripToken)
            .header("Content-Type", "application/json")
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("origin", "https://orion-uat.delhivery.com")
            .header("priority", "u=1, i")
            .header("referer", "https://orion-uat.delhivery.com/")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .body(requestBody)
            .log().all()
        .when()
            .patch("/trips/actions/" + transactionId + "/")
        .then()
            .log().all()
            .extract()
            .response();
            
        // Accept 200, 201, 202 as success status codes
        int statusCode = response.getStatusCode();
        if (statusCode != 200 && statusCode != 201 && statusCode != 202) {
            String errorResponse = response.getBody().asString();
            throw new RuntimeException("Failed to confirm bid in Orion. Status: " + statusCode + 
                ", Response: " + errorResponse);
        }
        
        com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse confirmResponse = 
            response.as(com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse.class);
        
        if (confirmResponse.getSuccess() != null && confirmResponse.getSuccess()) {
            System.out.println("✓ Bid confirmed successfully");
            if (confirmResponse.getMessage() != null) {
                System.out.println("  Message: " + confirmResponse.getMessage());
            }
        }
        
        return confirmResponse;
    }
}

