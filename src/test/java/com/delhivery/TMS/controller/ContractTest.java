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
     * Test creating a contract with custom parameters using ContractApiV2
     */
    @Test(priority = 1, groups = {"TMS", "Contracts"})
    public void testCreateContractWithCustomParams() {
        ContractController.createContract(
                "SIN9393",                    // vendorId
                "test_contract_custom",       // contractName
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "LTL",                        // serviceType (changed from FTL to LTL)
                "PER_TRIP",                   // contractType
                "LONG_TERM",                  // requestType
                "JNP123",                     // origin (changed to match working test)
                "ISPAT_GGN",                  // destination (changed to match working test)
                700.0,                        // rate
                "Flat",                       // rateType
                12,                           // tat
                "hours",                      // tatDisplayUnit
                "singh transporter pvt ltd",  // vendorName
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                false                         // isSubmit
        );
    }
    
    /**
     * Test creating a contract with is_submit=true using ContractApiV2
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
     * Test creating a contract with vehicleName and remarks using ContractApi
     */
    @Test(priority = 3, groups = {"TMS", "Contracts"})
    public void testCreateContractWithVehicleNameAndRemarks() {
        ContractController.createContractWithVehicleNameAndRemarks(
                "SIN9393",                    // vendorId
                "Singh_transport_contract1",  // contractName
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "FTL",                        // serviceType
                "PER_TRIP",                   // contractType
                "LONG_TERM",                  // requestType
                "JNP123",                     // origin
                "ISPAT_GGN",                  // destination
                "Closed 32FT MXL",            // vehicleName
                700.0,                        // rate
                "Flat",                       // rateType
                12,                           // tat
                "hours",                      // tatDisplayUnit
                "NA",                         // remarks
                "singh transporter pvt ltd",  // vendorName
                true                          // isSubmit
        );
    }
    
    /**
     * Test creating a contract with original ContractApi structure
     */
    @Test(priority = 4, groups = {"TMS", "Contracts"})
    public void testCreateContractWithOriginalApi() {
        ContractController.createContractWithVehicleNameAndRemarks(
                "SIN9393",                    // vendorId
                "Singh_transport_contract1",  // contractName
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "FTL",                        // serviceType
                "PER_TRIP",                   // contractType
                "LONG_TERM",                  // requestType
                "JNP123",                     // origin
                "ISPAT_GGN",                  // destination
                "Closed 32FT MXL",            // vehicleName
                700.0,                        // rate
                "Flat",                       // rateType
                12,                           // tat
                "hours",                      // tatDisplayUnit
                "NA",                         // remarks
                "singh transporter pvt ltd",  // vendorName
                true                          // isSubmit
        );
    }
} 