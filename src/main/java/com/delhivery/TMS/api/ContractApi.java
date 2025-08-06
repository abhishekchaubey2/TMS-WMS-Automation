package com.delhivery.TMS.api;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * TMS Contracts API class
 * Uses TmsRestResource for HTTP calls with proper logging and validation
 */
public class ContractApi {
    
    private static final String CONTRACTS_ENDPOINT = "/contracts/";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Create a new contract with is_submit parameter
     * @param requestPayload The contract request payload
     * @param isSubmit Whether to submit the contract
     * @return Response from the API
     */
    public static Response createContract(ContractRequestPayload requestPayload, boolean isSubmit) {
        return TmsRestResource.post(CONTRACTS_ENDPOINT, requestPayload, isSubmit);
    }
    
    /**
     * Create a new contract (default submit=true as per curl command)
     * @param requestPayload The contract request payload
     * @return Response from the API
     */
    public static Response createContract(ContractRequestPayload requestPayload) {
        return createContract(requestPayload, true);
    }
    
    /**
     * Create contract with custom parameters
     * @param vendorId Vendor ID
     * @param contractName Contract name
     * @param startDate Start date in milliseconds
     * @param endDate End date in milliseconds
     * @param serviceType Service type (LTL, FTL, etc.)
     * @param contractType Contract type (PER_TRIP, PER_KM, etc.)
     * @param requestType Request type (LONG_TERM, SHORT_TERM, etc.)
     * @param origin Origin location
     * @param destination Destination location
     * @param vehicleName Vehicle name
     * @param rate Rate amount
     * @param rateType Rate type (Flat, Per KM, etc.)
     * @param tat Transit time
     * @param tatDisplayUnit Transit time unit (hours, days, etc.)
     * @param remarks Remarks
     * @param vendorName Vendor name
     * @param isSubmit Whether to submit the contract
     * @return Response from the API
     */
    public static Response createContractWithParams(
            String vendorId,
            String contractName,
            Long startDate,
            Long endDate,
            String serviceType,
            String contractType,
            String requestType,
            String origin,
            String destination,
            String vehicleName,
            Double rate,
            String rateType,
            Integer tat,
            String tatDisplayUnit,
            String remarks,
            String vendorName,
            boolean isSubmit) {
        
        ContractRequestPayload requestPayload = com.delhivery.TMS.RequestBuilder.ContractRequestBuilder
                .buildCustomContractRequest(
                        vendorId, contractName, startDate, endDate, serviceType, contractType, requestType,
                        origin, destination, vehicleName, rate, rateType, tat, tatDisplayUnit, remarks, vendorName
                );
        
        return createContract(requestPayload, isSubmit);
    }
    
    /**
     * Create contract with custom parameters (default submit=true)
     */
    public static Response createContractWithParams(
            String vendorId,
            String contractName,
            Long startDate,
            Long endDate,
            String serviceType,
            String contractType,
            String requestType,
            String origin,
            String destination,
            String vehicleName,
            Double rate,
            String rateType,
            Integer tat,
            String tatDisplayUnit,
            String remarks,
            String vendorName) {
        
        return createContractWithParams(vendorId, contractName, startDate, endDate, serviceType, contractType,
                requestType, origin, destination, vehicleName, rate, rateType, tat, tatDisplayUnit, remarks, vendorName, true);
    }
    
    /**
     * Get contract by ID
     * @param contractId Contract ID
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
     * Update contract
     * @param contractId Contract ID
     * @param requestPayload Updated contract request payload
     * @return Response from the API
     */
    public static Response updateContract(String contractId, ContractRequestPayload requestPayload) {
        return TmsRestResource.put(CONTRACTS_ENDPOINT + contractId, requestPayload);
    }
    
    /**
     * Delete contract
     * @param contractId Contract ID
     * @return Response from the API
     */
    public static Response deleteContract(String contractId) {
        return TmsRestResource.delete(CONTRACTS_ENDPOINT + contractId);
    }
} 