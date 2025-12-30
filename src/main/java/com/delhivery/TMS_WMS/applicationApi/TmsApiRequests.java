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
     * Uses demand app token for x-coreos-access and x-coreos-auth headers
     * 
     * @param demandAppToken The demand app token for authentication
     * @param requestBody The demand request body
     * @return CreateDemandResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse createDemand(String demandAppToken, com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest requestBody) {
        // Validate that demandAppToken is provided
        if (demandAppToken == null || demandAppToken.isEmpty()) {
            throw new IllegalArgumentException("Demand App Token is required for creating demand");
        }
        
        System.out.println("=== Using Demand App Token for createDemand ===");
        
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
            // Dynamic Headers using Demand App Token
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
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
            
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201 && response.getStatusCode() != 202) {
            throw new RuntimeException("Failed to create TMS demand. Status: " + response.getStatusCode());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse.class);
    }
    
    /**
     * Create LTL Order in TMS
     * Uses order app token for x-coreos-access header
     * 
     * @param orderAppToken The order app token (from LtlOrderAppAuthApi)
     * @param requestBody The LTL order request body
     * @return Response from the API
     */
    public static io.restassured.response.Response createLtlOrder(String orderAppToken, com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderRequest requestBody) {
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("X-COREOS-TID", config.getProperty("tms.auth.tid"))
            .header("X-COREOS-REQUEST-ID", "123")
            .header("X-COREOS-ACCESS", orderAppToken)
            .header("X-COREOS-APP-ID", "gps")
            .header("Content-Type", "application/json")
            .header("X-COREOS-USERINFO", "{\"id\":\"1\",\"name\":\"abc\"}")
            .body(requestBody)
            .log().all()
        .when()
            .post("/tms/api/v1/orders/")
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Create LTL Load (Demand) in TMS
     * Uses token from properties file (tms.demand.header.x-coreos-access) for x-coreos-access and x-coreos-auth headers
     * 
     * @param requestBody The LTL demand request body
     * @return Response from the API
     */
    public static io.restassured.response.Response createLtlLoad(com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest requestBody) {
        // Use token from properties file (same approach as createDemand)
        String accessToken = config.getProperty("tms.demand.header.x-coreos-access");
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("tms.demand.header.x-coreos-access is not configured in properties file. " +
                    "Please update tms.demand.header.x-coreos-access and x-coreos-auth in qa.properties");
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
            // Use token from properties file
            .header("x-coreos-access", accessToken)
            .header("x-coreos-auth", accessToken)
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
            .post("/tms/api/v1/demands/")
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Update LTL Order in TMS
     * Uses order app token for x-coreos-access header
     * 
     * @param orderAppToken The order app token (from LtlOrderAppAuthApi)
     * @param orderNumber The order number to update
     * @param requestBody The LTL order update request body
     * @return Response from the API
     */
    public static io.restassured.response.Response updateLtlOrder(String orderAppToken, String orderNumber, com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest requestBody) {
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("X-COREOS-TID", config.getProperty("tms.auth.tid"))
            .header("X-COREOS-REQUEST-ID", "123")
            .header("X-COREOS-ACCESS", orderAppToken)
            .header("X-COREOS-APP-ID", "gps")
            .header("Content-Type", "application/json")
            .header("X-COREOS-USERINFO", "{\"id\":\"1\",\"name\":\"abc\"}")
            .body(requestBody)
            .log().all()
        .when()
            .put("/tms/api/v1/orders/" + orderNumber)
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Get Indents by Load ID
     * Uses demand app token for x-coreos-access and x-coreos-auth headers
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @param loadId The load ID to get indents for (e.g., DLV-SD283H)
     * @return Response from the API
     */
    public static io.restassured.response.Response getIndentsByLoadId(String demandAppToken, String loadId) {
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
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id", "OS1-RequestId:-9b41a345-4a2f-44ec-86f1-4bba2730ad51"))
            .header("x-coreos-tid", config.getProperty("tms.auth.tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .log().all()
        .when()
            .get("/tms/api/v1/indents/get_by_loadid/" + loadId)
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Get Indent Detail by Unique Code
     * Uses demand app token for authentication
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @param uniqueCode The indent unique code (indent ID)
     * @return Response from the API
     */
    public static io.restassured.response.Response getIndentDetail(String demandAppToken, String uniqueCode) {
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
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id", "OS1-RequestId:-9b41a345-4a2f-44ec-86f1-4bba2730ad51"))
            .header("x-coreos-tid", config.getProperty("tms.auth.tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .log().all()
        .when()
            .get("/tms/api/v1/indents/" + uniqueCode)
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Retry Failed Manifestation
     * Uses demand app token for authentication
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @return Response from the API
     */
    public static io.restassured.response.Response retryFailedManifestation(String demandAppToken) {
        // Build request body
        com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest request = 
            new com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest();
        
        com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Meta meta = 
            new com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Meta(
                config.getProperty("tms.auth.tid"),
                config.getProperty("tms.demand.app.appId")
            );
        
        com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Payload payload = 
            new com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Payload("indent_manifest_retry");
        
        request.setMeta(meta);
        request.setPayload(payload);
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
            .header("Content-Type", "application/json")
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id", "OS1-RequestId:-7f364a56-4587-4ed5-a2e0-0232b88d3331"))
            .header("x-coreos-tid", config.getProperty("tms.auth.tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(request)
            .log().all()
        .when()
            .post("/tms/api/v1/indents/cron/retry_failed_manifestation")
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
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
