package com.delhivery.TMS.controller;

import org.testng.annotations.Test;

/**
 * TMS Contract Test Class
 * Uses unified ContractRequestPayload with dynamic is_submit handling
 */
public class ContractTest {
    
    /**
     * Test creating a contract with dynamic is_submit handling
     * Tests both scenarios: is_submit=true and is_submit=false
     */
    @Test(priority = 1, groups = {"TMS", "Contracts"})
    public void testCreateContractWithDynamicIsSubmit() {
        System.out.println("=== Starting Dynamic Contract Creation Test ===");
        
        // Test with is_submit = true (should nullify vehicleName)
        System.out.println("--- Testing with is_submit = true ---");
        ContractController.createContract(
                "SIN9393",                    // vendorId
                "Singh_transport_contract1",  // contractName
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "LTL",                        // serviceType
                "PER_TRIP",                   // contractType
                "LONG_TERM",                  // requestType
                "JNP123",                     // origin
                "ISPAT_GGN",                  // destination
                "Closed 32FT MXL",            // vehicleName (will be nullified when is_submit=true)
                700.0,                        // rate
                "Flat",                       // rateType
                12,                           // tat
                "hours",                      // tatDisplayUnit
                "NA",                         // remarks
                "singh transporter pvt ltd",  // vendorName
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true (vehicleName will be null)
        );
        
        System.out.println("--- Testing with is_submit = false ---");
        // Test with is_submit = false (should nullify V2 fields)
        ContractController.createContract(
                "SIN9393",                    // vendorId
                "Singh_transport_contract2",  // contractName
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "FTL",                        // serviceType (changed from LTL to FTL)
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
                null,                         // volumetricCoefficient (null for FTL)
                null,                         // minChargableWt (null for FTL)
                null,                         // minCharge (null for FTL)
                false                         // isSubmit = false (V2 fields will be null)
        );
        
        System.out.println("=== Dynamic Contract Creation Test Completed Successfully ===");
    }
} 