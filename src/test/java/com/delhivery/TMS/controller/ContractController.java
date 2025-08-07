package com.delhivery.TMS.controller;

import com.delhivery.TMS.api.ContractApi;
import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.delhivery.TMS.pojo.Get_Api_contracts.response.GetContractResponsePayload;
import com.delhivery.TMS.pojo.put_api_contracts.request.PutContractRequestPayload;
import com.delhivery.TMS.pojo.put_api_contracts.response.PutContractResponsePayload;
import com.delhivery.TMS.pojo.approve_react_deact_contracts.response.ContractActionResponsePayload;
import com.delhivery.TMS.RequestBuilder.ContractRequestBuilder;
import com.delhivery.TMS.utils.ContractStateTracker;
import com.delhivery.TMS.RequestBuilder.PutContractRequestBuilder;
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
     * Extract contract ID from POST response and get contract details
     * @param postResponse Response from POST API call
     * @return Response from GET API call
     */
    public static Response getContractByIdFromPostResponse(Response postResponse) {
        try {
            System.out.println("=== ContractController.getContractByIdFromPostResponse Debug ===");
            
            // Step 1: Extract contract ID from POST response
            String contractId = extractContractIdFromResponse(postResponse);
            System.out.println("Extracted Contract ID: " + contractId);
            
            if (contractId == null || contractId.isEmpty()) {
                throw new RuntimeException("Failed to extract contract ID from POST response");
            }
            
            // Step 2: Get contract by ID
            return getContractById(contractId);
            
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.getContractByIdFromPostResponse ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Get contract by ID with robust validation and logging (without status check)
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response getContractById(String contractId) {
        return getContractById(contractId, null);
    }
    
    /**
     * Get contract by ID with robust validation and logging
     * @param contractId The contract ID
     * @param status Expected status (optional)
     * @return Response from the API
     */
    public static Response getContractById(String contractId, String status) {
        try {
            System.out.println("=== ContractController.getContractById Debug ===");
            System.out.println("Contract ID: " + contractId);
            
            // Step 1: Make API call with proper logging
            Response response = ContractApi.getContract(contractId);
            
            System.out.println("Step 2: Asserting status code...");
            
            // Step 2: Assert status code (first validation)
            Assertions.assertStatusCode(200, response);
            
            System.out.println("Step 3: Logging response...");
            
            // Step 3: Log full JSON response
            System.out.println("=== Get Contract Response ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("=== COMPLETE GET RESPONSE BODY ===");
            System.out.println(response.getBody().asPrettyString());
            System.out.println("=== END COMPLETE GET RESPONSE BODY ===");
            
            System.out.println("Step 4: Deserializing response...");
            
            // Step 4: Deserialize JSON response to Java object
            try {
                GetContractResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    GetContractResponsePayload.class
                );
                
                System.out.println("Step 5: Asserting response message...");
                
                // Step 5: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("Get contract success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("=== GET Response Details ===");
                    System.out.println("Contract ID: " + responsePayload.getData().getContractId());
                    System.out.println("Contract Name: " + responsePayload.getData().getContractName());
                    System.out.println("Status: " + responsePayload.getData().getStatus());
                    System.out.println("State: " + responsePayload.getData().getState());
                    System.out.println("Version: " + responsePayload.getData().getVersion());
                    System.out.println("Is Edited: " + responsePayload.getData().getIsEdited());
                    System.out.println("Approved By: " + responsePayload.getData().getApprovedBy());
                    System.out.println("Contract Type: " + responsePayload.getData().getContractType());
                    System.out.println("Service Type: " + responsePayload.getData().getServiceType());
                    System.out.println("Request Type: " + responsePayload.getData().getRequestType());
                    System.out.println("Vendor ID: " + responsePayload.getData().getVendorId());
                    System.out.println("Vendor Name: " + responsePayload.getData().getVendorName());
                    System.out.println("Start Date: " + responsePayload.getData().getStartDate());
                    System.out.println("End Date: " + responsePayload.getData().getEndDate());
                    System.out.println("Remarks: " + responsePayload.getData().getRemarks());
                    System.out.println("Sub Status: " + responsePayload.getData().getSubStatus());
                    System.out.println("Contract And Request Type: " + responsePayload.getData().getContractAndRequestType());
                    System.out.println("Reference ID: " + responsePayload.getData().getReferenceId());
                    
                    // Log charges information
                    if (responsePayload.getData().getDelayCharges() != null) {
                        System.out.println("Delay Charges: " + responsePayload.getData().getDelayCharges());
                    }
                    if (responsePayload.getData().getMaxDamageCharges() != null) {
                        System.out.println("Max Damage Charges: " + responsePayload.getData().getMaxDamageCharges());
                    }
                    if (responsePayload.getData().getLoadingCharges() != null) {
                        System.out.println("Loading Charges: " + responsePayload.getData().getLoadingCharges());
                    }
                    if (responsePayload.getData().getUnloadingCharges() != null) {
                        System.out.println("Unloading Charges: " + responsePayload.getData().getUnloadingCharges());
                    }
                    if (responsePayload.getData().getDetentionLoadingCharges() != null) {
                        System.out.println("Detention Loading Charges: " + responsePayload.getData().getDetentionLoadingCharges());
                    }
                    if (responsePayload.getData().getDetentionUnloadingCharges() != null) {
                        System.out.println("Detention Unloading Charges: " + responsePayload.getData().getDetentionUnloadingCharges());
                    }
                    if (responsePayload.getData().getMultiPointCharges() != null) {
                        System.out.println("Multi Point Charges: " + responsePayload.getData().getMultiPointCharges());
                    }
                    if (responsePayload.getData().getMinimumLRCharges() != null) {
                        System.out.println("Minimum LR Charges: " + responsePayload.getData().getMinimumLRCharges());
                    }
                    
                    if (responsePayload.getData().getUpdatedVersionFields() != null) {
                        System.out.println("Updated Version Fields: " + responsePayload.getData().getUpdatedVersionFields());
                    }
                    
                    System.out.println("=== End GET Response Details ===");
                } else {
                    System.out.println("❌ ERROR: GET response data is NULL!");
                    System.out.println("Full GET Response: " + response.getBody().asString());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing response: " + e.getMessage());
                System.err.println("=== COMPLETE RAW RESPONSE BODY ===");
                System.err.println(response.getBody().asPrettyString());
                System.err.println("=== END COMPLETE RAW RESPONSE BODY ===");
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.getContractById ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Extract contract ID from POST response
     * @param response Response from POST API
     * @return Contract ID
     */
    public static String extractContractIdFromResponse(Response response) {
        try {
            ContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(), 
                ContractResponsePayload.class
            );
            
            if (responsePayload.getData() != null) {
                // Extract the LONG ID field (first field) - this is the correct ID for GET API
                return responsePayload.getData().getId();
            }
            
            return null;
        } catch (Exception e) {
            System.err.println("Error extracting contract ID: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Extract contract state (id, status, version, success) from GET response
     * @param response The API response
     * @return ContractStateTracker with extracted data
     */
    public static ContractStateTracker extractContractStateFromResponse(Response response) {
        try {
            GetContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(),
                GetContractResponsePayload.class
            );
            
            if (responsePayload.getData() != null) {
                GetContractResponsePayload.GetContractData data = responsePayload.getData();
                return ContractStateTracker.builder()
                    .contractId(data.getId())
                    .status(data.getStatus())
                    .version(data.getVersion())
                    .success(responsePayload.getSuccess())
                    .build();
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error extracting contract state: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Extract contract ID from PUT response (for state updates)
     * @param response The API response
     * @return Contract ID
     */
    public static String extractContractIdFromPutResponse(Response response) {
        try {
            PutContractResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(),
                PutContractResponsePayload.class
            );
            if (responsePayload.getData() != null) {
                return responsePayload.getData().getId();
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error extracting contract ID from PUT response: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Extract contract ID from action response (approve/reactivate/deactivate)
     * @param response The API response
     * @return Contract ID
     */
    public static String extractContractIdFromActionResponse(Response response) {
        try {
            ContractActionResponsePayload responsePayload = objectMapper.readValue(
                response.getBody().asString(),
                ContractActionResponsePayload.class
            );
            if (responsePayload.getData() != null) {
                return responsePayload.getData().getId();
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error extracting contract ID from action response: " + e.getMessage());
            return null;
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
    
    /**
     * Update a contract using PUT API specific payload with robust validation and logging
     * @param contractId The contract ID
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
     * @return Response from the API
     */
    public static Response updateContractWithPutPayload(String contractId, String vendorId, String contractName, 
                                                     Long startDate, Long endDate, String serviceType, String contractType, 
                                                     String requestType, String origin, String destination, String vehicleName,
                                                     Double rate, String rateType, Integer tat, String tatDisplayUnit,
                                                     String remarks, String vendorName) {
        try {
            System.out.println("=== ContractController.updateContractWithPutPayload Debug ===");
            System.out.println("Step 1: Creating PUT contract request payload...");
            
            // Step 1: Create PUT contract request payload
            PutContractRequestPayload requestPayload = PutContractRequestBuilder.buildCustomPutContractRequest(
                    vendorId, contractName, startDate, endDate, serviceType, contractType, requestType,
                    origin, destination, vehicleName, rate, rateType, tat, tatDisplayUnit, remarks, vendorName
            );
            
            System.out.println("Step 2: Making PUT API call...");
            
            // Step 2: Make PUT API call with proper logging
            Response response = ContractApi.updateContractWithPutPayload(contractId, requestPayload);
            
            System.out.println("Step 3: Asserting status code...");
            
            // Step 3: Assert status code (first validation)
            Assertions.assertStatusCode(202, response);
            
            System.out.println("Step 4: Logging response...");
            
            // Step 4: Log full JSON response
            System.out.println("=== PUT Contract Update Response ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            System.out.println("Step 5: Deserializing response...");
            
            // Step 5: Deserialize JSON response to Java object
            try {
                PutContractResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    PutContractResponsePayload.class
                );
                
                System.out.println("Step 6: Asserting response message...");
                
                // Step 6: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("PUT contract update success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("Updated Contract ID: " + responsePayload.getData().getContractId());
                    System.out.println("Message: " + responsePayload.getData().getMessage());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing PUT response: " + e.getMessage());
                System.err.println("=== COMPLETE RAW PUT RESPONSE BODY ===");
                System.err.println(response.getBody().asPrettyString());
                System.err.println("=== END COMPLETE RAW PUT RESPONSE BODY ===");
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.updateContractWithPutPayload ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Update a contract using sample PUT payload
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response updateContractWithSamplePutPayload(String contractId) {
        try {
            System.out.println("=== ContractController.updateContractWithSamplePutPayload Debug ===");
            System.out.println("Step 1: Creating sample PUT contract request payload...");
            
            // Step 1: Create sample PUT contract request payload
            PutContractRequestPayload requestPayload = PutContractRequestBuilder.buildSamplePutContractRequest();
            
            System.out.println("Step 2: Making PUT API call...");
            
            // Step 2: Make PUT API call with proper logging
            Response response = ContractApi.updateContractWithPutPayload(contractId, requestPayload);
            
            System.out.println("Step 3: Asserting status code...");
            
            // Step 3: Assert status code (first validation)
            Assertions.assertStatusCode(202, response);
            
            System.out.println("Step 4: Logging response...");
            
            // Step 4: Log full JSON response
            System.out.println("=== PUT Contract Update Response (Sample) ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            System.out.println("Step 5: Deserializing response...");
            
            // Step 5: Deserialize JSON response to Java object
            try {
                PutContractResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    PutContractResponsePayload.class
                );
                
                System.out.println("Step 6: Asserting response message...");
                
                // Step 6: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("PUT contract update success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("Updated Contract ID: " + responsePayload.getData().getContractId());
                    System.out.println("Message: " + responsePayload.getData().getMessage());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing PUT response: " + e.getMessage());
                System.err.println("=== COMPLETE RAW PUT RESPONSE BODY ===");
                System.err.println(response.getBody().asPrettyString());
                System.err.println("=== END COMPLETE RAW PUT RESPONSE BODY ===");
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.updateContractWithSamplePutPayload ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Deactivate a contract with robust validation and logging
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response deactivateContract(String contractId) {
        try {
            System.out.println("=== ContractController.deactivateContract Debug ===");
            System.out.println("Contract ID: " + contractId);
            
            // Step 1: Make API call with proper logging
            Response response = ContractApi.deactivateContract(contractId);
            
            System.out.println("Step 2: Asserting status code...");
            
            // Step 2: Assert status code (first validation)
            Assertions.assertStatusCode(202, response);
            
            System.out.println("Step 3: Logging response...");
            
            // Step 3: Log full JSON response
            System.out.println("=== Deactivate Contract Response ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            System.out.println("Step 4: Deserializing response...");
            
            // Step 4: Deserialize JSON response to Java object
            try {
                ContractActionResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    ContractActionResponsePayload.class
                );
                
                System.out.println("Step 5: Asserting response message...");
                
                // Step 5: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("Deactivate contract success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("Deactivated Contract ID: " + responsePayload.getData().getContractId());
                    System.out.println("Message: " + responsePayload.getData().getMessage());
                    System.out.println("Request ID: " + responsePayload.getData().getRequestId());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing deactivate response: " + e.getMessage());
                System.err.println("=== COMPLETE RAW DEACTIVATE RESPONSE BODY ===");
                System.err.println(response.getBody().asPrettyString());
                System.err.println("=== END COMPLETE RAW DEACTIVATE RESPONSE BODY ===");
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.deactivateContract ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Reactivate a contract with robust validation and logging
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response reactivateContract(String contractId) {
        try {
            System.out.println("=== ContractController.reactivateContract Debug ===");
            System.out.println("Contract ID: " + contractId);
            
            // Step 1: Make API call with proper logging
            Response response = ContractApi.reactivateContract(contractId);
            
            System.out.println("Step 2: Asserting status code...");
            
            // Step 2: Assert status code (first validation)
            Assertions.assertStatusCode(202, response);
            
            System.out.println("Step 3: Logging response...");
            
            // Step 3: Log full JSON response
            System.out.println("=== Reactivate Contract Response ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            System.out.println("Step 4: Deserializing response...");
            
            // Step 4: Deserialize JSON response to Java object
            try {
                ContractActionResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    ContractActionResponsePayload.class
                );
                
                System.out.println("Step 5: Asserting response message...");
                
                // Step 5: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("Reactivate contract success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("Reactivated Contract ID: " + responsePayload.getData().getContractId());
                    System.out.println("Message: " + responsePayload.getData().getMessage());
                    System.out.println("Request ID: " + responsePayload.getData().getRequestId());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing reactivate response: " + e.getMessage());
                System.err.println("=== COMPLETE RAW REACTIVATE RESPONSE BODY ===");
                System.err.println(response.getBody().asPrettyString());
                System.err.println("=== END COMPLETE RAW REACTIVATE RESPONSE BODY ===");
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.reactivateContract ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Approval flow for a contract with robust validation and logging
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response approvalFlowContract(String contractId) {
        try {
            System.out.println("=== ContractController.approvalFlowContract Debug ===");
            System.out.println("Contract ID: " + contractId);
            
            // Step 1: Make API call with proper logging
            Response response = ContractApi.approvalFlowContract(contractId);
            
            System.out.println("Step 2: Asserting status code...");
            
            // Step 2: Assert status code (first validation)
            Assertions.assertStatusCode(202, response);
            
            System.out.println("Step 3: Logging response...");
            
            // Step 3: Log full JSON response
            System.out.println("=== Approval Flow Contract Response ===");
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asPrettyString());
            
            System.out.println("Step 4: Deserializing response...");
            
            // Step 4: Deserialize JSON response to Java object
            try {
                ContractActionResponsePayload responsePayload = objectMapper.readValue(
                    response.getBody().asString(), 
                    ContractActionResponsePayload.class
                );
                
                System.out.println("Step 5: Asserting response message...");
                
                // Step 5: Assert response message
                if (responsePayload.getSuccess() != null) {
                    Assertions.assertKeyValue("Approval flow contract success", true, responsePayload.getSuccess());
                }
                
                if (responsePayload.getData() != null) {
                    System.out.println("Approval Flow Contract ID: " + responsePayload.getData().getContractId());
                    System.out.println("Message: " + responsePayload.getData().getMessage());
                    System.out.println("Request ID: " + responsePayload.getData().getRequestId());
                }
                
            } catch (Exception e) {
                System.err.println("Error deserializing approval flow response: " + e.getMessage());
                System.err.println("=== COMPLETE RAW APPROVAL FLOW RESPONSE BODY ===");
                System.err.println(response.getBody().asPrettyString());
                System.err.println("=== END COMPLETE RAW APPROVAL FLOW RESPONSE BODY ===");
            }
            
            return response;
        } catch (Exception e) {
            System.err.println("=== Error in ContractController.approvalFlowContract ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
} 