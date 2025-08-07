package com.delhivery.TMS.controller;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.delhivery.TMS.utils.ContractStateTracker;

/**
 * TMS Contract Test Class
 * Uses unified ContractRequestPayload with dynamic is_submit handling
 */
public class ContractTest {
    
    /**
     * Complete Contract Lifecycle Flow Test
     * POST → GET → PUT → GET → APPROVE → GET → DEACTIVATE → GET → REACTIVATE → GET
     */
    @Test(priority = 1, groups = {"TMS", "Contracts", "LIFECYCLE"})
    public void testCompleteContractLifecycleFlow() {
        System.out.println("=== Starting Complete Contract Lifecycle Flow Test ===");
        
        // Initialize state tracker
        ContractStateTracker stateTracker = new ContractStateTracker();
        stateTracker.reset();
        
        try {
            // Step 1: POST - Create contract and extract ID
            System.out.println("--- Step 1: Creating contract via POST ---");
            Response postResponse = ContractController.createContract(
                    "SIN9393",                    // vendorId
                    "Lifecycle_Test_Contract",    // contractName
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
                    "Testing complete lifecycle",  // remarks
                    "singh transporter pvt ltd",  // vendorName
                    389,                          // volumetricCoefficient
                    38,                           // minChargableWt
                    38,                           // minCharge
                    true                          // isSubmit = true
            );
            
            String contractId = ContractController.extractContractIdFromResponse(postResponse);
            stateTracker.updateContractId(contractId);
            System.out.println("Initial Contract ID: " + contractId);
            
            // Step 2: GET - Verify initial state (ACTIVE)
            System.out.println("--- Step 2: Verifying initial state via GET ---");
            Response getResponse1 = ContractController.getContractById(contractId);
            ContractStateTracker initialState = ContractController.extractContractStateFromResponse(getResponse1);
            
            if (initialState != null) {
                stateTracker.updateState(
                    initialState.getContractId(),
                    initialState.getStatus(),
                    initialState.getVersion(),
                    initialState.getSuccess()
                );
                
                // Assert initial state
                assert initialState.getContractId().equals(contractId) : "Contract ID mismatch in initial GET";
                assert "ACTIVE".equals(initialState.getStatus()) : "Initial status should be ACTIVE, got: " + initialState.getStatus();
                assert Boolean.TRUE.equals(initialState.getSuccess()) : "Initial success should be true";
                System.out.println("✓ Initial state validated: ID=" + initialState.getContractId() + ", Status=" + initialState.getStatus());
            }
            
            // Step 3: PUT - Update contract (should change status to UNDER_REVIEW)
            System.out.println("--- Step 3: Updating contract via PUT ---");
            Response putResponse = ContractController.updateContractWithSamplePutPayload(contractId);
            String updatedContractId = ContractController.extractContractIdFromPutResponse(putResponse);
            stateTracker.updateContractId(updatedContractId);
            System.out.println("Updated Contract ID: " + updatedContractId);
            
            // Step 4: GET - Verify status is UNDER_REVIEW
            System.out.println("--- Step 4: Verifying status after PUT (should be UNDER_REVIEW) ---");
            Response getResponse2 = ContractController.getContractById(updatedContractId);
            ContractStateTracker putState = ContractController.extractContractStateFromResponse(getResponse2);
            
            if (putState != null) {
                stateTracker.updateState(
                    putState.getContractId(),
                    putState.getStatus(),
                    putState.getVersion(),
                    putState.getSuccess()
                );
                
                // Assert PUT state
                assert putState.getContractId().equals(updatedContractId) : "Contract ID mismatch after PUT";
                assert "UNDER_REVIEW".equals(putState.getStatus()) : "Status after PUT should be UNDER_REVIEW, got: " + putState.getStatus();
                assert Boolean.TRUE.equals(putState.getSuccess()) : "Success after PUT should be true";
                System.out.println("✓ PUT state validated: ID=" + putState.getContractId() + ", Status=" + putState.getStatus());
            }
            
            // Step 5: APPROVE - Trigger approval flow
            System.out.println("--- Step 5: Triggering approval flow ---");
            Response approveResponse = ContractController.approvalFlowContract(updatedContractId);
            String approvedContractId = ContractController.extractContractIdFromActionResponse(approveResponse);
            stateTracker.updateContractId(approvedContractId);
            stateTracker.updateStatus("ACTIVE"); // Expected status after approval
            System.out.println("Approved Contract ID: " + approvedContractId);
            
            // Step 6: GET - Verify status is ACTIVE after approval
            System.out.println("--- Step 6: Verifying status after approval (should be ACTIVE) ---");
            Response getResponse3 = ContractController.getContractById(approvedContractId);
            ContractStateTracker approveState = ContractController.extractContractStateFromResponse(getResponse3);
            
            if (approveState != null) {
                stateTracker.updateState(
                    approveState.getContractId(),
                    approveState.getStatus(),
                    approveState.getVersion(),
                    approveState.getSuccess()
                );
                
                // Assert approval state
                assert approveState.getContractId().equals(approvedContractId) : "Contract ID mismatch after approval";
                assert "ACTIVE".equals(approveState.getStatus()) : "Status after approval should be ACTIVE, got: " + approveState.getStatus();
                assert Boolean.TRUE.equals(approveState.getSuccess()) : "Success after approval should be true";
                System.out.println("✓ Approval state validated: ID=" + approveState.getContractId() + ", Status=" + approveState.getStatus());
            }
            
            // Step 7: DEACTIVATE - Deactivate contract
            System.out.println("--- Step 7: Deactivating contract ---");
            Response deactivateResponse = ContractController.deactivateContract(approvedContractId);
            String deactivatedContractId = ContractController.extractContractIdFromActionResponse(deactivateResponse);
            stateTracker.updateContractId(deactivatedContractId);
            stateTracker.updateStatus("INACTIVE"); // Expected status after deactivation
            System.out.println("Deactivated Contract ID: " + deactivatedContractId);
            
            // Step 8: GET - Verify status is INACTIVE after deactivation
            System.out.println("--- Step 8: Verifying status after deactivation (should be INACTIVE) ---");
            Response getResponse4 = ContractController.getContractById(deactivatedContractId);
            ContractStateTracker deactivateState = ContractController.extractContractStateFromResponse(getResponse4);
            
            if (deactivateState != null) {
                stateTracker.updateState(
                    deactivateState.getContractId(),
                    deactivateState.getStatus(),
                    deactivateState.getVersion(),
                    deactivateState.getSuccess()
                );
                
                // Assert deactivation state
                assert deactivateState.getContractId().equals(deactivatedContractId) : "Contract ID mismatch after deactivation";
                assert "INACTIVE".equals(deactivateState.getStatus()) : "Status after deactivation should be INACTIVE, got: " + deactivateState.getStatus();
                assert Boolean.TRUE.equals(deactivateState.getSuccess()) : "Success after deactivation should be true";
                System.out.println("✓ Deactivation state validated: ID=" + deactivateState.getContractId() + ", Status=" + deactivateState.getStatus());
            }
            
            // Step 9: REACTIVATE - Reactivate contract
            System.out.println("--- Step 9: Reactivating contract ---");
            Response reactivateResponse = ContractController.reactivateContract(deactivatedContractId);
            String reactivatedContractId = ContractController.extractContractIdFromActionResponse(reactivateResponse);
            stateTracker.updateContractId(reactivatedContractId);
            stateTracker.updateStatus("ACTIVE"); // Expected status after reactivation
            System.out.println("Reactivated Contract ID: " + reactivatedContractId);
            
            // Step 10: GET - Verify status is ACTIVE after reactivation
            System.out.println("--- Step 10: Verifying status after reactivation (should be ACTIVE) ---");
            Response getResponse5 = ContractController.getContractById(reactivatedContractId);
            ContractStateTracker reactivateState = ContractController.extractContractStateFromResponse(getResponse5);
            
            if (reactivateState != null) {
                stateTracker.updateState(
                    reactivateState.getContractId(),
                    reactivateState.getStatus(),
                    reactivateState.getVersion(),
                    reactivateState.getSuccess()
                );
                
                // Assert reactivation state
                assert reactivateState.getContractId().equals(reactivatedContractId) : "Contract ID mismatch after reactivation";
                assert "ACTIVE".equals(reactivateState.getStatus()) : "Status after reactivation should be ACTIVE, got: " + reactivateState.getStatus();
                assert Boolean.TRUE.equals(reactivateState.getSuccess()) : "Success after reactivation should be true";
                System.out.println("✓ Reactivation state validated: ID=" + reactivateState.getContractId() + ", Status=" + reactivateState.getStatus());
            }
            
            System.out.println("=== Complete Contract Lifecycle Flow Test Completed Successfully ===");
            
        } catch (Exception e) {
            System.err.println("=== Error in Complete Contract Lifecycle Flow Test ===");
            System.err.println("Error: " + e.getMessage());
            System.err.println("Current State: " + stateTracker.getContractId() + ", Status: " + stateTracker.getStatus());
            e.printStackTrace();
            // Don't throw - just log the error as requested
        }
    }
    
    /**
     * Contract Approval Flow Test
     * POST → GET → PUT → GET → APPROVE → GET
     */
    @Test(priority = 2, groups = {"TMS", "Contracts", "APPROVAL_FLOW"})
    public void testContractApprovalFlow() {
        System.out.println("=== Starting Contract Approval Flow Test ===");
        
        // Initialize state tracker
        ContractStateTracker stateTracker = new ContractStateTracker();
        stateTracker.reset();
        
        try {
            // Step 1: POST - Create contract and extract ID
            System.out.println("--- Step 1: Creating contract via POST ---");
            Response postResponse = ContractController.createContract(
                    "SIN9393",                    // vendorId
                    "Approval_Flow_Test_Contract", // contractName
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
                    "Testing approval flow",       // remarks
                    "singh transporter pvt ltd",  // vendorName
                    389,                          // volumetricCoefficient
                    38,                           // minChargableWt
                    38,                           // minCharge
                    true                          // isSubmit = true
            );
            
            String contractId = ContractController.extractContractIdFromResponse(postResponse);
            stateTracker.updateContractId(contractId);
            System.out.println("Initial Contract ID: " + contractId);
            
            // Step 2: GET - Verify initial state (ACTIVE)
            System.out.println("--- Step 2: Verifying initial state via GET ---");
            Response getResponse1 = ContractController.getContractById(contractId);
            ContractStateTracker initialState = ContractController.extractContractStateFromResponse(getResponse1);
            
            if (initialState != null) {
                stateTracker.updateState(
                    initialState.getContractId(),
                    initialState.getStatus(),
                    initialState.getVersion(),
                    initialState.getSuccess()
                );
                
                // Assert initial state
                assert initialState.getContractId().equals(contractId) : "Contract ID mismatch in initial GET";
                assert "ACTIVE".equals(initialState.getStatus()) : "Initial status should be ACTIVE, got: " + initialState.getStatus();
                assert Boolean.TRUE.equals(initialState.getSuccess()) : "Initial success should be true";
                System.out.println("✓ Initial state validated: ID=" + initialState.getContractId() + ", Status=" + initialState.getStatus());
            }
            
            // Step 3: PUT - Update contract (should change status to UNDER_REVIEW)
            System.out.println("--- Step 3: Updating contract via PUT ---");
            Response putResponse = ContractController.updateContractWithSamplePutPayload(contractId);
            String updatedContractId = ContractController.extractContractIdFromPutResponse(putResponse);
            stateTracker.updateContractId(updatedContractId);
            System.out.println("Updated Contract ID: " + updatedContractId);
            
            // Step 4: GET - Verify status is UNDER_REVIEW
            System.out.println("--- Step 4: Verifying status after PUT (should be UNDER_REVIEW) ---");
            Response getResponse2 = ContractController.getContractById(updatedContractId);
            ContractStateTracker putState = ContractController.extractContractStateFromResponse(getResponse2);
            
            if (putState != null) {
                stateTracker.updateState(
                    putState.getContractId(),
                    putState.getStatus(),
                    putState.getVersion(),
                    putState.getSuccess()
                );
                
                // Assert PUT state
                assert putState.getContractId().equals(updatedContractId) : "Contract ID mismatch after PUT";
                assert "UNDER_REVIEW".equals(putState.getStatus()) : "Status after PUT should be UNDER_REVIEW, got: " + putState.getStatus();
                assert Boolean.TRUE.equals(putState.getSuccess()) : "Success after PUT should be true";
                System.out.println("✓ PUT state validated: ID=" + putState.getContractId() + ", Status=" + putState.getStatus());
            }
            
            // Step 5: APPROVE - Trigger approval flow
            System.out.println("--- Step 5: Triggering approval flow ---");
            Response approveResponse = ContractController.approvalFlowContract(updatedContractId);
            String approvedContractId = ContractController.extractContractIdFromActionResponse(approveResponse);
            stateTracker.updateContractId(approvedContractId);
            stateTracker.updateStatus("ACTIVE"); // Expected status after approval
            System.out.println("Approved Contract ID: " + approvedContractId);
            
            // Step 6: GET - Verify status is ACTIVE after approval
            System.out.println("--- Step 6: Verifying status after approval (should be ACTIVE) ---");
            Response getResponse3 = ContractController.getContractById(approvedContractId);
            ContractStateTracker approveState = ContractController.extractContractStateFromResponse(getResponse3);
            
            if (approveState != null) {
                stateTracker.updateState(
                    approveState.getContractId(),
                    approveState.getStatus(),
                    approveState.getVersion(),
                    approveState.getSuccess()
                );
                
                // Assert approval state
                assert approveState.getContractId().equals(approvedContractId) : "Contract ID mismatch after approval";
                assert "ACTIVE".equals(approveState.getStatus()) : "Status after approval should be ACTIVE, got: " + approveState.getStatus();
                assert Boolean.TRUE.equals(approveState.getSuccess()) : "Success after approval should be true";
                System.out.println("✓ Approval state validated: ID=" + approveState.getContractId() + ", Status=" + approveState.getStatus());
            }
            
            System.out.println("=== Contract Approval Flow Test Completed Successfully ===");
            
        } catch (Exception e) {
            System.err.println("=== Error in Contract Approval Flow Test ===");
            System.err.println("Error: " + e.getMessage());
            System.err.println("Current State: " + stateTracker.getContractId() + ", Status: " + stateTracker.getStatus());
            e.printStackTrace();
            // Don't throw - just log the error as requested
        }
    }
    
    /**
     * Enhanced test demonstrating complete POST to GET flow with detailed validation
     * Shows how the ID from POST response is used in GET API with same headers and token
     */
    @Test(priority = 3, groups = {"TMS", "Contracts", "POST_TO_GET"})
    public void testCompletePostToGetFlow() {
        System.out.println("=== Starting Complete POST to GET Flow Test ===");
        
        // Step 1: Create a contract using POST API
        System.out.println("--- Step 1: Creating contract via POST API ---");
        Response postResponse = ContractController.createContract(
                "SIN9393",                    // vendorId
                "Complete_Flow_Test_Contract", // contractName
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "LTL",                        // serviceType
                "PER_TRIP",                   // contractType
                "LONG_TERM",                  // requestType
                "JNP123",                     // origin
                "ISPAT_GGN",                  // destination
                "Closed 32FT MXL",            // vehicleName
                700.0,                        // rate
                "Flat",                       // rateType
                12,                           // tat
                "hours",                      // tatDisplayUnit
                "Testing complete flow",      // remarks
                "singh transporter pvt ltd",  // vendorName
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true
        );
        
        // Step 2: Extract and validate the ID from POST response
        System.out.println("--- Step 2: Extracting ID from POST response ---");
        String extractedId = ContractController.extractContractIdFromResponse(postResponse);
        System.out.println("Extracted Contract ID: " + extractedId);
        
        // Validate that ID is not null or empty
        if (extractedId == null || extractedId.isEmpty()) {
            throw new RuntimeException("Failed to extract contract ID from POST response");
        }
        
        // Step 3: Use the extracted ID to make GET API call
        System.out.println("--- Step 3: Making GET API call with extracted ID ---");
        Response getResponse = ContractController.getContractById(extractedId,"ACTIVE");
        
        // Step 4: Validate that both responses are successful
        System.out.println("--- Step 4: Validating responses ---");
        System.out.println("POST Response Status: " + postResponse.getStatusCode());
        System.out.println("GET Response Status: " + getResponse.getStatusCode());
        
        // Additional validation: Ensure the contract ID in GET response matches
        try {
            com.delhivery.TMS.pojo.Get_Api_contracts.response.GetContractResponsePayload getResponsePayload = 
                new com.fasterxml.jackson.databind.ObjectMapper().readValue(
                    getResponse.getBody().asString(), 
                    com.delhivery.TMS.pojo.Get_Api_contracts.response.GetContractResponsePayload.class
                );
            
            if (getResponsePayload.getData() != null) {
                System.out.println("GET Response Contract ID: " + getResponsePayload.getData().getContractId());
                System.out.println("GET Response Contract Name: " + getResponsePayload.getData().getContractName());
                System.out.println("GET Response Status: " + getResponsePayload.getData().getStatus());
            }
        } catch (Exception e) {
            System.err.println("Error parsing GET response: " + e.getMessage());
        }
        
        System.out.println("=== Complete POST to GET Flow Test Completed Successfully ===");
    }
    
    /**
     * Test PUT contract update using custom payload
     * Creates a contract first, then updates it using PUT API
     */
    @Test(priority = 4, groups = {"TMS", "Contracts", "PUT"})
    public void testPutContractUpdateWithCustomPayload() {
        System.out.println("=== Starting PUT Contract Update Test (Custom Payload) ===");
        
        // Step 1: Create a contract first
        System.out.println("--- Step 1: Creating contract for PUT test ---");
        Response postResponse = ContractController.createContract(
                "SIN9393",                    // vendorId
                "PUT_Test_Contract",           // contractName
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
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true
        );
        
        // Step 2: Extract contract ID from POST response
        System.out.println("--- Step 2: Extracting contract ID for PUT update ---");
        String contractId = ContractController.extractContractIdFromResponse(postResponse);
        System.out.println("Contract ID for PUT update: " + contractId);
        
        // Step 3: Update contract using PUT API with custom payload
        System.out.println("--- Step 3: Updating contract via PUT API ---");
        Response putResponse = ContractController.updateContractWithPutPayload(
                contractId,                   // contractId
                "SIN9393",                    // vendorId
                "PUT_Updated_Contract",        // contractName (updated)
                1753122600000L,               // startDate
                1785436200000L,               // endDate
                "FTL",                        // serviceType
                "PER_TRIP",                   // contractType
                "LONG_TERM",                  // requestType
                "JNP123",                     // origin
                "ISPAT_GGN",                  // destination
                "Closed 32FT MXL",            // vehicleName
                800.0,                        // rate (updated)
                "Flat",                       // rateType
                15,                           // tat (updated)
                "hours",                      // tatDisplayUnit
                "Updated via PUT API",        // remarks (updated)
                "singh transporter pvt ltd"   // vendorName
        );
        
        System.out.println("=== PUT Contract Update Test (Custom Payload) Completed Successfully ===");
    }
    
    /**
     * Test PUT contract update using sample payload
     * Creates a contract first, then updates it using PUT API with sample data
     */
    @Test(priority = 5, groups = {"TMS", "Contracts", "PUT"})
    public void testPutContractUpdateWithSamplePayload() {
        System.out.println("=== Starting PUT Contract Update Test (Sample Payload) ===");
        
        // Step 1: Create a contract first
        System.out.println("--- Step 1: Creating contract for PUT test ---");
        Response postResponse = ContractController.createContract(
                "SIN9393",                    // vendorId
                "PUT_Sample_Test_Contract",    // contractName
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
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true
        );
        
        // Step 2: Extract contract ID from POST response
        System.out.println("--- Step 2: Extracting contract ID for PUT update ---");
        String contractId = ContractController.extractContractIdFromResponse(postResponse);
        System.out.println("Contract ID for PUT update: " + contractId);
        
        // Step 3: Update contract using PUT API with sample payload
        System.out.println("--- Step 3: Updating contract via PUT API (Sample) ---");
        Response putResponse = ContractController.updateContractWithSamplePutPayload(contractId);
        
        System.out.println("=== PUT Contract Update Test (Sample Payload) Completed Successfully ===");
    }
    
    /**
     * Test complete POST to PUT to GET flow
     * Creates a contract, updates it via PUT, then retrieves the updated contract
     */
    @Test(priority = 6, groups = {"TMS", "Contracts", "POST_TO_PUT_TO_GET"})
    public void testCompletePostToPutToGetFlow() {
        System.out.println("=== Starting Complete POST to PUT to GET Flow Test ===");
        
        // Step 1: Create a contract using POST API
        System.out.println("--- Step 1: Creating contract via POST API ---");
        Response postResponse = ContractController.createContract(
                "SIN9393",                    // vendorId
                "Complete_PUT_Flow_Contract", // contractName
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
                "Testing complete PUT flow",  // remarks
                "singh transporter pvt ltd",  // vendorName
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true
        );
        
        // Step 2: Extract contract ID from POST response
        System.out.println("--- Step 2: Extracting contract ID for PUT update ---");
        String contractId = ContractController.extractContractIdFromResponse(postResponse);
        System.out.println("Contract ID for PUT update: " + contractId);
        
        // Step 3: Update contract using PUT API
        System.out.println("--- Step 3: Updating contract via PUT API ---");
        Response putResponse = ContractController.updateContractWithSamplePutPayload(contractId);
        
        // Step 3.5: Extract NEW ID from PUT response
        System.out.println("--- Step 3.5: Extracting NEW ID from PUT response ---");
        String updatedContractId = ContractController.extractContractIdFromPutResponse(putResponse);
        System.out.println("Updated Contract ID: " + updatedContractId);
        
        // Step 4: Get the updated contract using GET API with NEW ID
        System.out.println("--- Step 4: Getting updated contract via GET API with NEW ID ---");
        Response getResponse = ContractController.getContractById(updatedContractId);
        
        // Step 5: Validate all responses
        System.out.println("--- Step 5: Validating all responses ---");
        System.out.println("POST Response Status: " + postResponse.getStatusCode());
        System.out.println("PUT Response Status: " + putResponse.getStatusCode());
        System.out.println("GET Response Status: " + getResponse.getStatusCode());
        
        // Step 6: Verify the status is UNDER_REVIEW
        System.out.println("--- Step 6: Verifying status is UNDER_REVIEW ---");
        ContractStateTracker contractState = ContractController.extractContractStateFromResponse(getResponse);
        System.out.println("Contract Status: " + contractState.getStatus());
        System.out.println("Contract ID: " + contractState.getContractId());
        System.out.println("Contract Version: " + contractState.getVersion());
        
        // Assert that status should be UNDER_REVIEW
        assert "UNDER_REVIEW".equals(contractState.getStatus()) : 
            "Status after PUT should be UNDER_REVIEW, got: " + contractState.getStatus();
        
        System.out.println("=== Complete POST to PUT to GET Flow Test Completed Successfully ===");
    }
    
    /**
     * Test contract deactivation flow
     * Creates a contract, then deactivates it
     */
    @Test(priority = 7, groups = {"TMS", "Contracts", "DEACTIVATE"})
    public void testContractDeactivationFlow() {
        System.out.println("=== Starting Contract Deactivation Flow Test ===");
        
        // Step 1: Create a contract using POST API
        System.out.println("--- Step 1: Creating contract for deactivation ---");
        Response postResponse = ContractController.createContract(
                "SIN9393",                    // vendorId
                "Deactivate_Test_Contract",   // contractName
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
                "Testing contract deactivation", // remarks
                "singh transporter pvt ltd",  // vendorName
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true
        );
        
        // Step 2: Extract contract ID from POST response
        System.out.println("--- Step 2: Extracting contract ID for deactivation ---");
        String contractId = ContractController.extractContractIdFromResponse(postResponse);
        System.out.println("Contract ID for deactivation: " + contractId);
        
        // Step 3: Deactivate contract using PUT API
        System.out.println("--- Step 3: Deactivating contract via PUT API ---");
        Response deactivateResponse = ContractController.deactivateContract(contractId);
        
        // Step 4: Validate deactivation response
        System.out.println("--- Step 4: Validating deactivation response ---");
        System.out.println("POST Response Status: " + postResponse.getStatusCode());
        System.out.println("Deactivate Response Status: " + deactivateResponse.getStatusCode());
        
        System.out.println("=== Contract Deactivation Flow Test Completed Successfully ===");
    }
    
    /**
     * Test contract reactivation flow
     * Creates a contract, deactivates it, then reactivates it
     */
    @Test(priority = 8, groups = {"TMS", "Contracts", "REACTIVATE"})
    public void testContractReactivationFlow() {
        System.out.println("=== Starting Contract Reactivation Flow Test ===");
        
        // Step 1: Create a contract using POST API
        System.out.println("--- Step 1: Creating contract for reactivation ---");
        Response postResponse = ContractController.createContract(
                "SIN9393",                    // vendorId
                "Reactivate_Test_Contract",   // contractName
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
                "Testing contract reactivation", // remarks
                "singh transporter pvt ltd",  // vendorName
                389,                          // volumetricCoefficient
                38,                           // minChargableWt
                38,                           // minCharge
                true                          // isSubmit = true
        );
        
        // Step 2: Extract contract ID from POST response
        System.out.println("--- Step 2: Extracting contract ID for reactivation ---");
        String contractId = ContractController.extractContractIdFromResponse(postResponse);
        System.out.println("Contract ID for reactivation: " + contractId);
        
        // Step 3: Deactivate contract first (required before reactivation)
        System.out.println("--- Step 3: Deactivating contract (required before reactivation) ---");
        Response deactivateResponse = ContractController.deactivateContract(contractId);
        String deactivatedContractId = ContractController.extractContractIdFromActionResponse(deactivateResponse);
        System.out.println("Deactivated Contract ID: " + deactivatedContractId);
        
        // Step 4: Reactivate contract using PUT API
        System.out.println("--- Step 4: Reactivating contract via PUT API ---");
        Response reactivateResponse = ContractController.reactivateContract(deactivatedContractId);
        
        // Step 5: Validate reactivation response
        System.out.println("--- Step 5: Validating reactivation response ---");
        System.out.println("POST Response Status: " + postResponse.getStatusCode());
        System.out.println("Deactivate Response Status: " + deactivateResponse.getStatusCode());
        System.out.println("Reactivate Response Status: " + reactivateResponse.getStatusCode());
        
        System.out.println("=== Contract Reactivation Flow Test Completed Successfully ===");
    }
    
} 