package com.delhivery.TMS_WMS.applicationApi;

import com.delhivery.TMS_WMS.api.TmsAuthApi;
import com.delhivery.TMS_WMS.pojo.tms.response.TmsGetOrdersResponse;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TmsApiRequests {
    
    private static final ConfigLoader config = ConfigLoader.getInstance();
    
    /**
     * Get Orders from TMS
     * Uses TMS token for x-coreos-access and x-coreos-auth headers
     */
    public static TmsGetOrdersResponse getOrders(String status, String originFacility) {
        String tmsToken = TmsAuthApi.getAccessToken();
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Chromium\";v=\"142\", \"Google Chrome\";v=\"142\", \"Not_A Brand\";v=\"99\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36")
            // Dynamic Headers using TMS Token
            .header("x-coreos-access", tmsToken)
            .header("x-coreos-auth", tmsToken)
            // Headers from properties
            .header("x-coreos-request-id", config.getProperty("tms.orders.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.orders.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.orders.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.orders.header.x-tms-data-access"))
            // Query Params
            .queryParam("status", status)
            .queryParam("originFacility", originFacility)
            .log().all()
        .when()
            .get(config.getProperty("tms.orders.endpoint"))
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch TMS orders. Status: " + response.getStatusCode());
        }
        
        return response.as(TmsGetOrdersResponse.class);
    }

    /**
     * Create Demand in TMS
     * Uses static tokens from properties file for x-coreos-access and x-coreos-auth headers
     * 
     * NOTE: Tokens expire and need refresh. Update qa.properties when tokens expire.
     * Properties: tms.demand.header.x-coreos-access and tms.demand.header.x-coreos-auth
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse createDemand(com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest requestBody) {
        // Get tokens from properties (user-specific tokens required for FTL demand creation)
        String accessToken = config.getProperty("tms.demand.header.x-coreos-access");
        String authToken = config.getProperty("tms.demand.header.x-coreos-auth");
        
        // Fallback to client credentials token if properties tokens not set
        if (accessToken == null || accessToken.isEmpty() || authToken == null || authToken.isEmpty()) {
            System.out.println("⚠ Warning: Static tokens not found in properties. Attempting to use client credentials token...");
            System.out.println("⚠ Note: Client credentials token may not have FTL demand creation permissions.");
            String tmsToken = TmsAuthApi.getAccessToken();
            accessToken = tmsToken;
            authToken = tmsToken;
        }
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("content-type", "application/json")
            // Headers from properties (user-specific tokens)
            .header("x-coreos-access", accessToken)
            .header("x-coreos-auth", authToken)
            // Headers from properties
            .header("callback", config.getProperty("tms.demand.header.callback"))
            .header("origin", config.getProperty("tms.demand.header.origin"))
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.demand.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(requestBody)
            .log().all()
        .when()
            .post(config.getProperty("tms.demand.endpoint"))
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() == 403) {
            System.err.println("❌ ERROR: 403 Forbidden - Token expired or insufficient permissions");
            System.err.println("📝 Action Required: Please update tokens in qa.properties");
            System.err.println("📖 See TOKEN_MANAGEMENT.md for instructions on getting fresh tokens");
            throw new RuntimeException("Failed to create TMS demand. Status: 403 - Token expired or insufficient permissions. " +
                "Please update tms.demand.header.x-coreos-access and x-coreos-auth in qa.properties. See TOKEN_MANAGEMENT.md");
        }
        
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201 && response.getStatusCode() != 202) {
            throw new RuntimeException("Failed to create TMS demand. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse.class);
    }
    
    /**
     * Get Demands (Load Listing) from TMS Plan Tab
     * Uses static tokens from properties file for x-coreos-access and x-coreos-auth headers
     * 
     * @param planOrigin Origin facility (e.g., ROUTECHFC)
     * @param state State filter (e.g., unallocated:unallocated)
     * @param previouslyIndented Previously indented filter (true/false)
     * @return GetDemandsResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.GetDemandsResponse getDemands(
            String planOrigin, String state, Boolean previouslyIndented) {
        // Get tokens from properties (user-specific tokens required)
        String accessToken = config.getProperty("tms.demand.header.x-coreos-access");
        String authToken = config.getProperty("tms.demand.header.x-coreos-auth");
        
        // Fallback to client credentials token if properties tokens not set
        if (accessToken == null || accessToken.isEmpty() || authToken == null || authToken.isEmpty()) {
            System.out.println("⚠ Warning: Static tokens not found in properties. Attempting to use client credentials token...");
            String tmsToken = TmsAuthApi.getAccessToken();
            accessToken = tmsToken;
            authToken = tmsToken;
        }
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            // Headers from properties (user-specific tokens)
            .header("x-coreos-access", accessToken)
            .header("x-coreos-auth", authToken)
            // Headers from properties
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.demand.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            // Query Params
            .queryParam("is_route_name_required", "true")
            .queryParam("planOrigin", planOrigin)
            .queryParam("state", state)
            .queryParam("previouslyIndented", previouslyIndented != null ? previouslyIndented.toString() : "false")
            .queryParam("enrichChildLoadsCount", "true")
            .queryParam("childTransAssigned", "true")
            .log().all()
        .when()
            .get(config.getProperty("tms.demand.endpoint"))
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() == 403) {
            System.err.println("❌ ERROR: 403 Forbidden - Token expired or insufficient permissions");
            System.err.println("📝 Action Required: Please update tokens in qa.properties");
            throw new RuntimeException("Failed to fetch TMS demands. Status: 403 - Token expired or insufficient permissions. " +
                "Please update tms.demand.header.x-coreos-access and x-coreos-auth in qa.properties");
        }
        
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch TMS demands. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.GetDemandsResponse.class);
    }
    
    /**
     * Assign Spot Contract to Demand
     * Uses static tokens from properties file for x-coreos-access and x-coreos-auth headers
     * 
     * @param requestBody AssignSpotContractRequest payload
     * @return AssignSpotContractResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.AssignSpotContractResponse assignSpotContract(
            com.delhivery.TMS_WMS.pojo.tms.request.AssignSpotContractRequest requestBody) {
        // Get tokens from properties (user-specific tokens required)
        String accessToken = config.getProperty("tms.demand.header.x-coreos-access");
        String authToken = config.getProperty("tms.demand.header.x-coreos-auth");
        
        // Fallback to client credentials token if properties tokens not set
        if (accessToken == null || accessToken.isEmpty() || authToken == null || authToken.isEmpty()) {
            System.out.println("⚠ Warning: Static tokens not found in properties. Attempting to use client credentials token...");
            String tmsToken = TmsAuthApi.getAccessToken();
            accessToken = tmsToken;
            authToken = tmsToken;
        }
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("content-type", "application/json")
            // Headers from properties (user-specific tokens)
            .header("x-coreos-access", accessToken)
            .header("x-coreos-auth", authToken)
            // Headers from properties
            .header("callback", config.getProperty("tms.demand.header.callback"))
            .header("origin", config.getProperty("tms.demand.header.origin"))
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.demand.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(requestBody)
            .log().all()
        .when()
            .post("/tms/api/v1/demands/assign-spot-contract")
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() == 403) {
            System.err.println("❌ ERROR: 403 Forbidden - Token expired or insufficient permissions");
            System.err.println("📝 Action Required: Please update tokens in qa.properties");
            throw new RuntimeException("Failed to assign spot contract. Status: 403 - Token expired or insufficient permissions. " +
                "Please update tms.demand.header.x-coreos-access and x-coreos-auth in qa.properties");
        }
        
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201 && response.getStatusCode() != 202) {
            throw new RuntimeException("Failed to assign spot contract. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.AssignSpotContractResponse.class);
    }
    
    /**
     * Create Indent (Bulk Indent) for Demands
     * Uses static tokens from properties file for x-coreos-access and x-coreos-auth headers
     * 
     * @param requestBody CreateIndentRequest payload with loadIds
     * @return CreateIndentResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateIndentResponse createIndent(
            com.delhivery.TMS_WMS.pojo.tms.request.CreateIndentRequest requestBody) {
        // Get tokens from properties (user-specific tokens required)
        String accessToken = config.getProperty("tms.demand.header.x-coreos-access");
        String authToken = config.getProperty("tms.demand.header.x-coreos-auth");
        
        // Fallback to client credentials token if properties tokens not set
        if (accessToken == null || accessToken.isEmpty() || authToken == null || authToken.isEmpty()) {
            System.out.println("⚠ Warning: Static tokens not found in properties. Attempting to use client credentials token...");
            String tmsToken = TmsAuthApi.getAccessToken();
            accessToken = tmsToken;
            authToken = tmsToken;
        }
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("content-type", "application/json")
            // Headers from properties (user-specific tokens)
            .header("x-coreos-access", accessToken)
            .header("x-coreos-auth", authToken)
            // Headers from properties
            .header("callback", config.getProperty("tms.demand.header.callback"))
            .header("origin", config.getProperty("tms.demand.header.origin"))
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.demand.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(requestBody)
            .log().all()
        .when()
            .post("/tms/api/v2/demands/bulk/indent")
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() == 403) {
            System.err.println("❌ ERROR: 403 Forbidden - Token expired or insufficient permissions");
            System.err.println("📝 Action Required: Please update tokens in qa.properties");
            throw new RuntimeException("Failed to create indent. Status: 403 - Token expired or insufficient permissions. " +
                "Please update tms.demand.header.x-coreos-access and x-coreos-auth in qa.properties");
        }
        
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201 && response.getStatusCode() != 202) {
            throw new RuntimeException("Failed to create indent. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.CreateIndentResponse.class);
    }
    
    /**
     * Mark Reported for Indent
     * Uses static tokens from properties file for x-coreos-access and x-coreos-auth headers
     * 
     * @param requestBody MarkReportedRequest payload with indent ID and vehicle/driver details
     * @return MarkReportedResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.MarkReportedResponse markReported(
            com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest requestBody) {
        // Get tokens from properties (user-specific tokens required)
        String accessToken = config.getProperty("tms.demand.header.x-coreos-access");
        String authToken = config.getProperty("tms.demand.header.x-coreos-auth");
        
        // Fallback to client credentials token if properties tokens not set
        if (accessToken == null || accessToken.isEmpty() || authToken == null || authToken.isEmpty()) {
            System.out.println("⚠ Warning: Static tokens not found in properties. Attempting to use client credentials token...");
            String tmsToken = TmsAuthApi.getAccessToken();
            accessToken = tmsToken;
            authToken = tmsToken;
        }
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("content-type", "application/json")
            // Headers from properties (user-specific tokens)
            .header("x-coreos-access", accessToken)
            .header("x-coreos-auth", authToken)
            // Headers from properties
            .header("callback", config.getProperty("tms.demand.header.callback"))
            .header("origin", config.getProperty("tms.demand.header.origin"))
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.demand.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(requestBody)
            .log().all()
        .when()
            .post("/tms/api/v1/indents/action/actionMarkReported")
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() == 403) {
            System.err.println("❌ ERROR: 403 Forbidden - Token expired or insufficient permissions");
            System.err.println("📝 Action Required: Please update tokens in qa.properties");
            throw new RuntimeException("Failed to mark reported. Status: 403 - Token expired or insufficient permissions. " +
                "Please update tms.demand.header.x-coreos-access and x-coreos-auth in qa.properties");
        }
        
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201 && response.getStatusCode() != 202) {
            throw new RuntimeException("Failed to mark reported. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.MarkReportedResponse.class);
    }
}
