package com.delhivery.TMS.api;

import com.delhivery.core.api.SpecBuilder;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.delhivery.core.utils.Utilities.logCodeBlock;
import static com.delhivery.core.utils.Utilities.logInfo;
import static io.restassured.RestAssured.given;

/**
 * TMS-specific RestResource for HTTP calls
 * Handles TMS API requests with proper logging and validation
 */
public class TmsRestResource {
    
    private static final String TMS_BASE_PATH = "/tms/api/v2";
    
    /**
     * POST request for TMS APIs
     * @param endpoint The API endpoint (without base path)
     * @param requestBody The request body object
     * @param isSubmit Whether to submit the contract (for contracts API)
     * @return Response from the API
     */
    public static Response post(String endpoint, Object requestBody, boolean isSubmit) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        // Log request details
        System.out.println("=== TMS API Request ===");
        System.out.println("Endpoint: " + TMS_BASE_PATH + endpoint);
        System.out.println("Method: POST");
        System.out.println("is_submit: " + isSubmit);
        
        try {
            System.out.println("Request Body: " + Utilities.jsonObjectToString(requestBody));
        } catch (JsonProcessingException e) {
            System.out.println("Request Body: " + requestBody.toString());
        }
        
        // Make the API call
        Response response = given()
                .spec(requestSpec)
                .queryParam("is_submit", isSubmit)
                .body(requestBody)
                .when()
                .post(TMS_BASE_PATH + endpoint)
                .then()
                .extract()
                .response();
        
        // Log response details
        System.out.println("=== TMS API Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
    
    /**
     * POST request for TMS APIs (default isSubmit=false)
     * @param endpoint The API endpoint (without base path)
     * @param requestBody The request body object
     * @return Response from the API
     */
    public static Response post(String endpoint, Object requestBody) {
        return post(endpoint, requestBody, false);
    }
    
    /**
     * GET request for TMS APIs
     * @param endpoint The API endpoint (without base path)
     * @return Response from the API
     */
    public static Response get(String endpoint) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        // Log request details
        System.out.println("=== TMS API Request ===");
        System.out.println("Endpoint: " + TMS_BASE_PATH + endpoint);
        System.out.println("Method: GET");
        
        // Make the API call
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(TMS_BASE_PATH + endpoint)
                .then()
                .extract()
                .response();
        
        // Log response details
        System.out.println("=== TMS API Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
    
    /**
     * PUT request for TMS APIs
     * @param endpoint The API endpoint (without base path)
     * @param requestBody The request body object
     * @return Response from the API
     */
    public static Response put(String endpoint, Object requestBody) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        // Log request details
        System.out.println("=== TMS API Request ===");
        System.out.println("Endpoint: " + TMS_BASE_PATH + endpoint);
        System.out.println("Method: PUT");
        
        try {
            System.out.println("Request Body: " + Utilities.jsonObjectToString(requestBody));
        } catch (JsonProcessingException e) {
            System.out.println("Request Body: " + requestBody.toString());
        }
        
        // Make the API call
        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put(TMS_BASE_PATH + endpoint)
                .then()
                .extract()
                .response();
        
        // Log response details
        System.out.println("=== TMS API Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
    
    /**
     * DELETE request for TMS APIs
     * @param endpoint The API endpoint (without base path)
     * @return Response from the API
     */
    public static Response delete(String endpoint) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        // Log request details
        System.out.println("=== TMS API Request ===");
        System.out.println("Endpoint: " + TMS_BASE_PATH + endpoint);
        System.out.println("Method: DELETE");
        
        // Make the API call
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete(TMS_BASE_PATH + endpoint)
                .then()
                .extract()
                .response();
        
        // Log response details
        System.out.println("=== TMS API Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
} 