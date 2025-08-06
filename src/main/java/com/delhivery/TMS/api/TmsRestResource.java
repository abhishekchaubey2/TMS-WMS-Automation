package com.delhivery.TMS.api;

import com.delhivery.core.api.SpecBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * TMS REST Resource for making HTTP calls
 * Handles dynamic is_submit parameter and proper logging
 */
public class TmsRestResource {
    
    private static final String TMS_BASE_PATH = "/tms/api/v2";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Make a POST request with dynamic is_submit parameter
     * @param endpoint The API endpoint
     * @param requestBody The request body object
     * @param isSubmit Whether to submit the contract
     * @return Response from the API
     */
    public static Response post(String endpoint, Object requestBody, boolean isSubmit) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        // Log request details
        System.out.println("=== TMS API Request ===");
        System.out.println("Endpoint: " + TMS_BASE_PATH + endpoint);
        System.out.println("Method: POST");
        System.out.println("is_submit: " + isSubmit);
        System.out.println("Request Body: " + (requestBody != null ? convertObjectToJson(requestBody) : "null"));
        
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
     * Make a GET request
     * @param endpoint The API endpoint
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
     * Make a PUT request
     * @param endpoint The API endpoint
     * @param requestBody The request body object
     * @return Response from the API
     */
    public static Response put(String endpoint, Object requestBody) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        // Log request details
        System.out.println("=== TMS API Request ===");
        System.out.println("Endpoint: " + TMS_BASE_PATH + endpoint);
        System.out.println("Method: PUT");
        System.out.println("Request Body: " + (requestBody != null ? convertObjectToJson(requestBody) : "null"));
        
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
     * Make a DELETE request
     * @param endpoint The API endpoint
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
    
    /**
     * Convert object to JSON string for logging
     * @param obj The object to convert
     * @return JSON string
     */
    private static String convertObjectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "Error converting object to JSON: " + e.getMessage();
        }
    }
} 