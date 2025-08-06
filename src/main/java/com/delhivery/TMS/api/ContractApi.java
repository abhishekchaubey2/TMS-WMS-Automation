package com.delhivery.TMS.api;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * TMS Contracts API class
 * Uses unified ContractRequestPayload with dynamic is_submit handling
 */
public class ContractApi {
    
    private static final String CONTRACTS_ENDPOINT = "/contracts/";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Create a new contract with dynamic is_submit parameter
     * @param requestPayload The contract request payload
     * @param isSubmit Whether to submit the contract
     * @return Response from the API
     */
    public static Response createContract(ContractRequestPayload requestPayload, boolean isSubmit) {
        return TmsRestResource.post(CONTRACTS_ENDPOINT, requestPayload, isSubmit);
    }
    
    /**
     * Get a contract by ID
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response getContract(String contractId) {
        return TmsRestResource.get(CONTRACTS_ENDPOINT + contractId);
    }
    
    /**
     * Get all contracts
     * @return Response from the API
     */
    public static Response getAllContracts() {
        return TmsRestResource.get(CONTRACTS_ENDPOINT);
    }
    
    /**
     * Update a contract
     * @param contractId The contract ID
     * @param requestPayload The updated contract request payload
     * @return Response from the API
     */
    public static Response updateContract(String contractId, ContractRequestPayload requestPayload) {
        return TmsRestResource.put(CONTRACTS_ENDPOINT + contractId, requestPayload);
    }
    
    /**
     * Delete a contract
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response deleteContract(String contractId) {
        return TmsRestResource.delete(CONTRACTS_ENDPOINT + contractId);
    }
} 