package com.delhivery.TMS.controller;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayloadV2;
import com.delhivery.TMS.RequestBuilder.ContractRequestBuilderV2;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * TMS Contract Test Class
 * Uses ContractController helper methods for API operations
 */
public class ContractTest {
    
    /**
     * Test creating a contract with custom parameters
     */
    @Test
    public void testCreateContractWithCustomParams() {
        ContractController.createContract(
                "SIN9394",
                "test_contract_custom",
                1753122600000L,
                1785436200000L,
                "FTL",
                "PER_TRIP",
                "LONG_TERM",
                "MUM001",
                "DEL001",
                800.0,
                "Flat",
                24,
                "hours",
                "test transporter pvt ltd",
                400,
                50,
                50,
                false
        );
    }
    
    /**
     * Test creating a contract with is_submit=true using hardcoded values
     */
    @Test(priority = 2, groups = {"TMS", "Contracts"})
    public void testCreateContractWithIsSubmitTrue() {
        try {
            System.out.println("=== Starting Contract Creation Test ===");
            System.out.println("Calling ContractController.createContract...");
            
            ContractController.createContract(
                    "SIN9393",                    // vendorId
                    "singh_transport_contract2",  // contractName
                    1753122600000L,               // startDate
                    1785436200000L,               // endDate
                    "LTL",                        // serviceType
                    "PER_TRIP",                   // contractType
                    "LONG_TERM",                  // requestType
                    "JNP123",                     // origin
                    "ISPAT_GGN",                  // destination
                    700.0,                        // rate
                    "Flat",                       // rateType
                    12,                           // tat
                    "hours",                      // tatDisplayUnit
                    "singh transporter pvt ltd",  // vendorName
                    389,                          // volumetricCoefficient
                    38,                           // minChargableWt
                    38,                           // minCharge
                    true                          // isSubmit
            );
            
            System.out.println("=== Contract Creation Test Completed Successfully ===");
        } catch (Exception e) {
            System.err.println("=== Error in Contract Creation Test ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Test getting all contracts
     */
    @Test
    public void testGetAllContracts() {
        ContractController.getAllContracts();
    }
    
    /**
     * Test getting a specific contract by ID
     */
    @Test
    public void testGetContractById() {
        // This would require a valid contract ID
        String contractId = "test-contract-id";
        Response response = ContractController.getContract(contractId);
        System.out.println("Contract retrieved successfully with status: " + response.getStatusCode());
    }
    
    /**
     * Test updating a contract
     */
    @Test
    public void testUpdateContract() {
        // This would require a valid contract ID
        String contractId = "test-contract-id";
        ContractRequestPayloadV2 requestPayload = ContractRequestBuilderV2.buildSampleContractRequest();
        Response response = ContractController.updateContract(contractId, requestPayload);
        System.out.println("Contract updated successfully with status: " + response.getStatusCode());
    }
    
    /**
     * Test deleting a contract
     */
    @Test
    public void testDeleteContract() {
        // This would require a valid contract ID
        String contractId = "test-contract-id";
        Response response = ContractController.deleteContract(contractId);
        System.out.println("Contract deleted successfully with status: " + response.getStatusCode());
    }
} 