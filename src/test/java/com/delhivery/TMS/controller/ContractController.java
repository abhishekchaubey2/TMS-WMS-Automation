package com.delhivery.TMS.controller;

import com.delhivery.TMS.api.ContractApi;
import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.delhivery.TMS.RequestBuilder.ContractRequestBuilder;
import com.delhivery.core.utils.Assertions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * TMS Contract Controller
 * Uses unified ContractRequestPayload with dynamic is_submit handling
 */
public class ContractController {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Create a contract with dynamic is_submit handling
     * @param vendorId Vendor ID
     * @param contractName Contract name
     * @param startDate Start date
     * @param endDate End date
     * @param serviceType Service type
     * @param contractType Contract type
     * @param requestType Request type
     * @param origin Origin
     * @param destination Destination
     * @param vehicleName Vehicle name
     * @param rate Rate
     * @param rateType Rate type
     * @param tat TAT
     * @param tatDisplayUnit TAT display unit
     * @param remarks Remarks
     * @param vendorName Vendor name
     * @param volumetricCoefficient Volumetric coefficient
     * @param minChargableWt Minimum chargable weight
     * @param minCharge Minimum charge
     * @param isSubmit Whether to submit the contract (affects field nullification)
     * @return Response from the API
     */
    public static Response createContract(String vendorId, String contractName, Long startDate, Long endDate,
                                       String serviceType, String contractType, String requestType,
                                       String origin, String destination, String vehicleName,
                                       Double rate, String rateType, Integer tat, String tatDisplayUnit,
                                       String remarks, String vendorName,
                                       Integer volumetricCoefficient, Integer minChargableWt, Integer minCharge,
                                       boolean isSubmit) {
        try {
            System.out.println("=== ContractController.createContract Debug ===");
            System.out.println("Step 1: Creating Java object with parameters...");
            
            // Step 1: Create Java object using unified builder with all parameters
            ContractRequestPayload requestPayload = ContractRequestBuilder.buildCustomContractRequest(
                    vendorId, contractName, startDate, endDate, serviceType, contractType, requestType,
                    origin, destination, vehicleName, rate, rateType, tat, tatDisplayUnit, remarks, vendorName,
                    volumetricCoefficient, minChargableWt, minCharge, isSubmit
            );
            
            System.out.println("Step 2: Making API call...");
            
            // Step 2: Make API call with proper logging
            Response response = ContractApi.createContract(requestPayload, isSubmit);
            
            System.out.println("Step 3: Asserting status code...");
            
            // Step 3: Assert status code (first validation)
            Assertions.assertStatusCode(202, response);
            
            System.out.println("Step 4: Logging response...");
            
            // Step 4: Log full JSON response
            System.out.println("=== Contract Creation Response ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            System.out.println("Step 5: Deserializing response...");
            
            // Step 5: Deserialize JSON response to Java object
            try {
                ContractResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    ContractResponsePayload.class
                );
                
                System.out.println("Step 6: Asserting response message...");
                
                // Step 6: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("Contract creation success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getMessage() != null) {
                    System.out.println("Response Message: " + responsePayload.getMessage());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("Contract ID: " + responsePayload.getData().getContractId());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing response: " + e.getMessage());
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.createContract ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Get all contracts with robust validation and logging
     * @return Response from the API
     */
    public static Response getAllContracts() {
        // Step 2: Make API call with proper logging
        Response response = ContractApi.getAllContracts();
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Get All Contracts Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
    
    /**
     * Get a contract by ID with robust validation and logging
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response getContract(String contractId) {
        // Step 2: Make API call with proper logging
        Response response = ContractApi.getContract(contractId);
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Get Contract Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
    
    /**
     * Update a contract with robust validation and logging
     * @param contractId The contract ID
     * @param requestPayload The updated contract request payload
     * @return Response from the API
     */
    public static Response updateContract(String contractId, ContractRequestPayload requestPayload) {
        // Step 2: Make API call with proper logging
        Response response = ContractApi.updateContract(contractId, requestPayload);
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Update Contract Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
    
    /**
     * Delete a contract with robust validation and logging
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response deleteContract(String contractId) {
        // Step 2: Make API call with proper logging
        Response response = ContractApi.deleteContract(contractId);
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Delete Contract Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response;
    }
} 