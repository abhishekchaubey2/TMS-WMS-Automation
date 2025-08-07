package com.delhivery.TMS.api;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.delhivery.TMS.pojo.put_api_contracts.request.PutContractRequestPayload;
import com.delhivery.TMS.pojo.put_api_contracts.response.PutContractResponsePayload;
import com.delhivery.TMS.pojo.approve_react_deact_contracts.response.ContractActionResponsePayload;
import com.delhivery.TMS.pojo.approve_react_deact_contracts.request.ApproveContractRequestPayload;
import com.delhivery.TMS.pojo.approve_react_deact_contracts.request.ReactivateContractRequestPayload;
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
        // Try using path parameter instead of query parameter
        return TmsRestResource.get(CONTRACTS_ENDPOINT + "{contractId}", contractId);
    }
    
    /**
     * Get all contracts
     * @return Response from the API
     */
    public static Response getAllContracts() {
        return TmsRestResource.get(CONTRACTS_ENDPOINT);
    }
    
    /**
     * Update a contract (using original ContractRequestPayload)
     * @param contractId The contract ID
     * @param requestPayload The updated contract request payload
     * @return Response from the API
     */
    public static Response updateContract(String contractId, ContractRequestPayload requestPayload) {
        return TmsRestResource.put(CONTRACTS_ENDPOINT + contractId, requestPayload);
    }
    
    /**
     * Update a contract using PUT API specific payload
     * @param contractId The contract ID
     * @param requestPayload The PUT contract request payload
     * @return Response from the API
     */
    public static Response updateContractWithPutPayload(String contractId, PutContractRequestPayload requestPayload) {
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
    
    /**
     * Deactivate a contract
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response deactivateContract(String contractId) {
        // Send empty JSON object instead of null
        return TmsRestResource.put(CONTRACTS_ENDPOINT + "deactivate/" + contractId, "{}");
    }
    
    /**
     * Reactivate a contract
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response reactivateContract(String contractId) {
        // Create reactivate request payload with default dates
        ReactivateContractRequestPayload reactivatePayload = new ReactivateContractRequestPayload(1753189500195L, 1785503102778L);
        return TmsRestResource.put(CONTRACTS_ENDPOINT + "reactivate/" + contractId, reactivatePayload);
    }
    
    /**
     * Approval flow for a contract
     * @param contractId The contract ID
     * @return Response from the API
     */
    public static Response approvalFlowContract(String contractId) {
        // Create approval request payload
        ApproveContractRequestPayload approvalPayload = new ApproveContractRequestPayload(true);
        return TmsRestResource.put(CONTRACTS_ENDPOINT + "approval_flow/" + contractId, approvalPayload);
    }
} 