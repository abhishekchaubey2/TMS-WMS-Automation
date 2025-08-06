package com.delhivery.TMS.controller;

import com.delhivery.TMS.api.ContractApiV2;
import com.delhivery.TMS.api.ContractApi;
import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayloadV2;
import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayloadV2;
import com.delhivery.TMS.RequestBuilder.ContractRequestBuilderV2;
import com.delhivery.TMS.RequestBuilder.ContractRequestBuilder;
import com.delhivery.core.utils.Assertions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * TMS Contract Controller - Helper class for contract API operations
 * Provides reusable methods for contract creation, retrieval, and management
 * Implements robust production-ready pattern with proper validation and logging
 */
public class ContractController {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Create a contract with custom parameters
     * Implements robust production-ready pattern with hardcoded values
     * @return Response from the API
     */
    public static Response createContract(String vendorId, String contractName, Long startDate, Long endDate,
                                       String serviceType, String contractType, String requestType,
                                       String origin, String destination, Double rate, String rateType,
                                       Integer tat, String tatDisplayUnit, String vendorName,
                                       Integer volumetricCoefficient, Integer minChargableWt, Integer minCharge,
                                       boolean isSubmit) {
        try {
            System.out.println("=== ContractController.createContract Debug ===");
            System.out.println("Step 1: Creating Java object with parameters...");
            
            // Step 1: Create Java object using custom parameters
            ContractRequestPayloadV2 requestPayload = ContractRequestBuilderV2.buildCustomContractRequest(
                    vendorId, contractName, startDate, endDate, serviceType, contractType, requestType,
                    origin, destination, rate, rateType, tat, tatDisplayUnit, vendorName,
                    volumetricCoefficient, minChargableWt, minCharge
            );
            
            System.out.println("Step 2: Making API call...");
            
            // Step 2: Make API call with proper logging
            Response response = ContractApiV2.createContract(requestPayload, isSubmit);
            
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
                ContractResponsePayloadV2 responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    ContractResponsePayloadV2.class
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
     * Create a contract with vehicleName and remarks using the new data structure
     * @return Response from the API
     */
    public static Response createContractWithVehicleNameAndRemarks(String vendorId, String contractName, Long startDate, Long endDate,
                                       String serviceType, String contractType, String requestType,
                                       String origin, String destination, String vehicleName,
                                       Double rate, String rateType, Integer tat, String tatDisplayUnit,
                                       String remarks, String vendorName, boolean isSubmit) {
        try {
            System.out.println("=== ContractController.createContractWithVehicleNameAndRemarks Debug ===");
            System.out.println("Step 1: Creating Java object with parameters...");
            
            // Step 1: Create Java object using custom parameters with vehicleName and remarks
            ContractRequestPayload requestPayload = ContractRequestBuilder.buildCustomContractRequest(
                    vendorId, contractName, startDate, endDate, serviceType, contractType, requestType,
                    origin, destination, vehicleName, rate, rateType, tat, tatDisplayUnit, remarks, vendorName
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
            System.err.println("=== Error in ContractController.createContractWithVehicleNameAndRemarks ===");
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
        Response response = ContractApiV2.getAllContracts();
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Get All Contracts Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        // Step 5: Deserialize JSON response to Java object
        try {
            ContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(), 
                ContractResponsePayload.class
            );
            
            // Step 6: Assert response message
            if (responsePayload.getSuccess() != null) {
                Assertions.assertKeyValue("Get all contracts success", true, responsePayload.getSuccess());
            }
            
            if (responsePayload.getMessage() != null) {
                System.out.println("Response Message: " + responsePayload.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error deserializing response: " + e.getMessage());
        }
        
        return response;
    }

    /**
     * Get contract by ID with robust validation and logging
     * @param contractId Contract ID
     * @return Response from the API
     */
    public static Response getContract(String contractId) {
        // Step 2: Make API call with proper logging
        Response response = ContractApiV2.getContract(contractId);
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Get Contract by ID Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        // Step 5: Deserialize JSON response to Java object
        try {
            ContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(), 
                ContractResponsePayload.class
            );
            
            // Step 6: Assert response message
            if (responsePayload.getSuccess() != null) {
                Assertions.assertKeyValue("Get contract success", true, responsePayload.getSuccess());
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
    }

    /**
     * Update contract with robust validation and logging
     * @param contractId Contract ID
     * @param requestPayload Updated contract request payload
     * @return Response from the API
     */
    public static Response updateContract(String contractId, ContractRequestPayloadV2 requestPayload) {
        // Step 2: Make API call with proper logging
        Response response = ContractApiV2.updateContract(contractId, requestPayload);
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Update Contract Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        // Step 5: Deserialize JSON response to Java object
        try {
            ContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(), 
                ContractResponsePayload.class
            );
            
            // Step 6: Assert response message
            if (responsePayload.getSuccess() != null) {
                Assertions.assertKeyValue("Update contract success", true, responsePayload.getSuccess());
            }
            
            if (responsePayload.getMessage() != null) {
                System.out.println("Response Message: " + responsePayload.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error deserializing response: " + e.getMessage());
        }
        
        return response;
    }

    /**
     * Delete contract with robust validation and logging
     * @param contractId Contract ID
     * @return Response from the API
     */
    public static Response deleteContract(String contractId) {
        // Step 2: Make API call with proper logging
        Response response = ContractApiV2.deleteContract(contractId);
        
        // Step 3: Assert status code (first validation)
        Assertions.assertStatusCode(200, response);
        
        // Step 4: Log full JSON response
        System.out.println("=== Delete Contract Response ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        // Step 5: Deserialize JSON response to Java object
        try {
            ContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(), 
                ContractResponsePayload.class
            );
            
            // Step 6: Assert response message
            if (responsePayload.getSuccess() != null) {
                Assertions.assertKeyValue("Delete contract success", true, responsePayload.getSuccess());
            }
            
            if (responsePayload.getMessage() != null) {
                System.out.println("Response Message: " + responsePayload.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error deserializing response: " + e.getMessage());
        }
        
        return response;
    }


} 