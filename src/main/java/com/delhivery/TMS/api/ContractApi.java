package com.delhivery.TMS.api;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.response.ContractResponsePayload;
import com.delhivery.core.api.SpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * TMS Contracts API class
 */
public class ContractApi {
    
    private static final String CONTRACTS_ENDPOINT = "/tms/api/v2/contracts/";
    
    /**
     * Create a new contract
     * @param requestPayload The contract request payload
     * @return Response from the API
     */
    public static Response createContract(ContractRequestPayload requestPayload) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        return given()
                .spec(requestSpec)
                .queryParam("is_submit", "true")
                .body(requestPayload)
                .when()
                .post(CONTRACTS_ENDPOINT)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Create a new contract with custom parameters
     * @param vendorId Vendor ID
     * @param contractName Contract name
     * @param startDate Start date in milliseconds
     * @param endDate End date in milliseconds
     * @param serviceType Service type (FTL, PTL, etc.)
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
            String vendorName) {
        
        ContractRequestPayload requestPayload = com.delhivery.TMS.RequestBuilder.ContractRequestBuilder
                .buildCustomContractRequest(
                        vendorId, contractName, startDate, endDate, serviceType, contractType, requestType,
                        origin, destination, vehicleName, rate, rateType, tat, tatDisplayUnit, remarks, vendorName
                );
        
        return createContract(requestPayload);
    }
    
    /**
     * Get contract by ID
     * @param contractId Contract ID
     * @return Response from the API
     */
    public static Response getContract(String contractId) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        return given()
                .spec(requestSpec)
                .when()
                .get(CONTRACTS_ENDPOINT + contractId)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Get all contracts
     * @return Response from the API
     */
    public static Response getAllContracts() {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        return given()
                .spec(requestSpec)
                .when()
                .get(CONTRACTS_ENDPOINT)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Update contract
     * @param contractId Contract ID
     * @param requestPayload Updated contract request payload
     * @return Response from the API
     */
    public static Response updateContract(String contractId, ContractRequestPayload requestPayload) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        return given()
                .spec(requestSpec)
                .body(requestPayload)
                .when()
                .put(CONTRACTS_ENDPOINT + contractId)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Delete contract
     * @param contractId Contract ID
     * @return Response from the API
     */
    public static Response deleteContract(String contractId) {
        RequestSpecification requestSpec = SpecBuilder.getTmsRequestSpecWithAuth("service-account", "TMS Service");
        
        return given()
                .spec(requestSpec)
                .when()
                .delete(CONTRACTS_ENDPOINT + contractId)
                .then()
                .extract()
                .response();
    }
} 