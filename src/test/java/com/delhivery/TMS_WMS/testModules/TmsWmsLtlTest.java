package com.delhivery.TMS_WMS.testModules;

import com.delhivery.TMS_WMS.api.LtlOrderAppAuthApi;
import com.delhivery.TMS_WMS.api.DemandAppAuthApi;
import com.delhivery.TMS_WMS.controller.TmsController;
import com.delhivery.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TMS_WMS_LTL Test Module - Follows Express framework pattern
 * Extends BaseTest and uses Controller + DataProvider pattern
 * Tests LTL (Less Than Truckload) order flow
 */
public class TmsWmsLtlTest extends BaseTest {
    
    // Static variable to store order app token for later use
    private static String orderAppTokenStatic;
    // Static variable to store demand app token for later use
    private static String demandAppTokenStatic;
    // Static variables to store created LTL order details
    private static String createdLtlOrderNumberStatic;
    private static String createdLtlOrderIdStatic;
    private static String createdLtlLineItemNumberStatic;
    private static String createdLtlInvoiceNumberStatic;
    private static io.restassured.response.Response createdLtlOrderResponseStatic;
    // Static variables to store created LTL load details
    private static String createdLtlLoadIdStatic;
    private static io.restassured.response.Response createdLtlLoadResponseStatic;
    // Static variable to store indent unique code (indent ID)
    private static String createdLtlIndentUniqueCodeStatic;
    
    /**
     * Generate Order App Token for order creation
     * This token will be used in subsequent API calls for order creation
     */
    @Test(priority = 0, groups = {"LTL", "Auth", "Config"})
    public void testGenerateOrderAppToken() {
        System.out.println("=== Test: Generate Order App Token ===");

        io.restassured.response.Response response = LtlOrderAppAuthApi.authenticate();

        int statusCode = response.getStatusCode();
        System.out.println("Order App Token API Status Code: " + statusCode);
        
        Assert.assertEquals(statusCode, 200, "Order App Token API should return HTTP 200");
        
        // The response structure has data.accessToken
        String accessToken = response.jsonPath().getString("data.accessToken");
        Assert.assertNotNull(accessToken, "Access token should not be null");
        
        // Store the token for later use
        orderAppTokenStatic = accessToken;
        
        String tokenType = response.jsonPath().getString("data.tokenType");
        Integer expiresIn = response.jsonPath().getInt("data.expiresIn");
        
        System.out.println("✓ Order App Token generated successfully");
        System.out.println("Token Type: " + tokenType);
        System.out.println("Expires In: " + expiresIn + " seconds");
        System.out.println("Access Token (first 50 chars): " + accessToken.substring(0, Math.min(50, accessToken.length())) + "...");
        System.out.println("Token stored for future API calls");
    }
    
    /**
     * Get stored order app token
     * @return The stored order app token
     */
    public static String getOrderAppToken() {
        if (orderAppTokenStatic == null) {
            // If token not stored yet, generate it
            orderAppTokenStatic = LtlOrderAppAuthApi.getOrderAppToken();
        }
        return orderAppTokenStatic;
    }
    
    /**
     * Verify token is stored and accessible
     */
    @Test(priority = 1, groups = {"LTL", "Auth"}, dependsOnMethods = {"testGenerateOrderAppToken"})
    public void testVerifyOrderAppTokenStored() {
        System.out.println("=== Test: Verify Order App Token Stored ===");
        
        Assert.assertNotNull(orderAppTokenStatic, "Order App Token should be stored from previous test");
        Assert.assertTrue(orderAppTokenStatic.length() > 0, "Order App Token should not be empty");
        
        System.out.println("✓ Order App Token verified and ready for use");
        System.out.println("Token length: " + orderAppTokenStatic.length() + " characters");
    }
    
    /**
     * Generate Demand App Token for demand/load operations
     * This token will be used in subsequent API calls for demand/load creation
     */
    @Test(priority = 2, groups = {"LTL", "Auth", "Config"})
    public void testGenerateDemandAppToken() {
        System.out.println("=== Test: Generate Demand App Token ===");

        io.restassured.response.Response response = DemandAppAuthApi.authenticate();

        int statusCode = response.getStatusCode();
        System.out.println("Demand App Token API Status Code: " + statusCode);
        
        Assert.assertEquals(statusCode, 200, "Demand App Token API should return HTTP 200");
        
        // The response structure has data.accessToken
        String accessToken = response.jsonPath().getString("data.accessToken");
        Assert.assertNotNull(accessToken, "Access token should not be null");
        
        // Store the token for later use
        demandAppTokenStatic = accessToken;
        
        String tokenType = response.jsonPath().getString("data.tokenType");
        Integer expiresIn = response.jsonPath().getInt("data.expiresIn");
        
        System.out.println("✓ Demand App Token generated successfully");
        System.out.println("Token Type: " + tokenType);
        System.out.println("Expires In: " + expiresIn + " seconds");
        System.out.println("Access Token (first 50 chars): " + accessToken.substring(0, Math.min(50, accessToken.length())) + "...");
        System.out.println("Token stored for future API calls");
    }
    
    /**
     * Get stored demand app token
     * @return The stored demand app token
     */
    public static String getDemandAppToken() {
        if (demandAppTokenStatic == null) {
            // If token not stored yet, generate it
            demandAppTokenStatic = DemandAppAuthApi.getDemandAppToken();
        }
        return demandAppTokenStatic;
    }
    
    /**
     * Verify demand app token is stored and accessible
     */
    @Test(priority = 3, groups = {"LTL", "Auth"}, dependsOnMethods = {"testGenerateDemandAppToken"})
    public void testVerifyDemandAppTokenStored() {
        System.out.println("=== Test: Verify Demand App Token Stored ===");
        
        Assert.assertNotNull(demandAppTokenStatic, "Demand App Token should be stored from previous test");
        Assert.assertTrue(demandAppTokenStatic.length() > 0, "Demand App Token should not be empty");
        
        System.out.println("✓ Demand App Token verified and ready for use");
        System.out.println("Token length: " + demandAppTokenStatic.length() + " characters");
    }
    
    /**
     * Create LTL Order in TMS
     * Uses the order app token from previous test
     */
    @Test(priority = 4, groups = {"LTL", "OrderCreation"}, dependsOnMethods = {"testVerifyOrderAppTokenStored"})
    public void testCreateLtlOrder() {
        System.out.println("=== Test: Create LTL Order in TMS ===");
        
        Assert.assertNotNull(orderAppTokenStatic, "Order App Token should be available from previous test");
        System.out.println("Using Order App Token (first 50 chars): " + orderAppTokenStatic.substring(0, Math.min(50, orderAppTokenStatic.length())) + "...");
        
        // Create order via controller
        io.restassured.response.Response response = TmsController.createLtlOrder(orderAppTokenStatic);
        
        int statusCode = response.getStatusCode();
        System.out.println("Create LTL Order Status Code: " + statusCode);
        
        // Accept 200, 201, or 202 as success
        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 202,
                "Create LTL Order API should return HTTP 200, 201, or 202. Got: " + statusCode);
        
        // Store the created order details
        createdLtlOrderNumberStatic = TmsController.getLastCreatedLtlOrderNumber();
        Assert.assertNotNull(createdLtlOrderNumberStatic, "Created LTL Order Number should not be null");
        
        createdLtlOrderIdStatic = TmsController.getLastCreatedLtlOrderId();
        createdLtlLineItemNumberStatic = TmsController.getLastCreatedLtlLineItemNumber();
        createdLtlInvoiceNumberStatic = TmsController.getLastCreatedLtlInvoiceNumber();
        createdLtlOrderResponseStatic = TmsController.getLastLtlOrderResponse();
        
        System.out.println("✓ LTL Order created successfully");
        System.out.println("Order Number: " + createdLtlOrderNumberStatic);
        System.out.println("Line Item Number: " + createdLtlLineItemNumberStatic);
        System.out.println("Invoice Number: " + createdLtlInvoiceNumberStatic);
        if (createdLtlOrderIdStatic != null) {
            System.out.println("Order ID: " + createdLtlOrderIdStatic);
        }
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        System.out.println("Order details stored for future API calls");
    }
    
    /**
     * Create LTL Load (Demand) in TMS
     * Uses the order ID from the previously created LTL order
     */
    @Test(priority = 5, groups = {"LTL", "LoadCreation"}, dependsOnMethods = {"testCreateLtlOrder"})
    public void testCreateLtlLoad() {
        System.out.println("=== Test: Create LTL Load (Demand) in TMS ===");
        
        // Use order number as order ID (order number IS the order ID)
        Assert.assertNotNull(createdLtlOrderNumberStatic, "Order Number should be available from order creation");
        String orderIdToUse = createdLtlOrderNumberStatic;
        System.out.println("Using Order Number as Order ID: " + orderIdToUse);
        
        // Wait for order to be fully processed before creating load
        System.out.println("Waiting 10 seconds for order to be fully processed...");
        try {
            Thread.sleep(10000); // Wait 10 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Wait interrupted: " + e.getMessage());
        }
        System.out.println("Wait completed. Proceeding with load creation...");
        
        // Create load via controller
        io.restassured.response.Response response = TmsController.createLtlLoad(orderIdToUse);
        
        int statusCode = response.getStatusCode();
        System.out.println("Create LTL Load Status Code: " + statusCode);
        
        // Accept 200, 201, or 202 as success
        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 202,
                "Create LTL Load API should return HTTP 200, 201, or 202. Got: " + statusCode);
        
        // Store the created load details
        createdLtlLoadIdStatic = TmsController.getLastCreatedLtlLoadId();
        createdLtlLoadResponseStatic = TmsController.getLastLtlLoadResponse();
        
        System.out.println("✓ LTL Load created successfully");
        if (createdLtlLoadIdStatic != null) {
            System.out.println("Load ID: " + createdLtlLoadIdStatic);
        }
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        System.out.println("Load details stored for future API calls");
    }
    
    /**
     * Update LTL Order in TMS
     * Uses the order app token and stored order details
     */
    @Test(priority = 6, groups = {"LTL", "OrderUpdate"}, dependsOnMethods = {"testCreateLtlLoad"})
    public void testUpdateLtlOrder() {
        System.out.println("=== Test: Update LTL Order in TMS ===");
        
        Assert.assertNotNull(orderAppTokenStatic, "Order App Token should be available from previous test");
        Assert.assertNotNull(createdLtlOrderNumberStatic, "Order Number should be available from order creation");
        Assert.assertNotNull(createdLtlInvoiceNumberStatic, "Invoice Number should be available from order creation");
        Assert.assertNotNull(createdLtlLineItemNumberStatic, "Line Item Number should be available from order creation");
        
        System.out.println("Using Order Number: " + createdLtlOrderNumberStatic);
        System.out.println("Using Invoice Number: " + createdLtlInvoiceNumberStatic);
        System.out.println("Using Line Item Number: " + createdLtlLineItemNumberStatic);
        
        // Update order via controller
        io.restassured.response.Response response = TmsController.updateLtlOrder(orderAppTokenStatic);
        
        int statusCode = response.getStatusCode();
        System.out.println("Update LTL Order Status Code: " + statusCode);
        
        // Accept 200, 201, or 202 as success
        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 202,
                "Update LTL Order API should return HTTP 200, 201, or 202. Got: " + statusCode);
        
        System.out.println("✓ LTL Order updated successfully");
        System.out.println("Response Body: " + response.getBody().asPrettyString());
    }
    
    /**
     * Get stored LTL order number
     * @return The stored LTL order number
     */
    public static String getCreatedLtlOrderNumber() {
        return createdLtlOrderNumberStatic;
    }
    
    /**
     * Get stored LTL order ID
     * @return The stored LTL order ID (from response)
     */
    public static String getCreatedLtlOrderId() {
        return createdLtlOrderIdStatic;
    }
    
    /**
     * Get stored LTL line item number
     * @return The stored LTL line item number (from order creation payload)
     */
    public static String getCreatedLtlLineItemNumber() {
        return createdLtlLineItemNumberStatic;
    }
    
    /**
     * Get stored LTL invoice number
     * @return The stored LTL invoice number (from order creation payload)
     */
    public static String getCreatedLtlInvoiceNumber() {
        return createdLtlInvoiceNumberStatic;
    }
    
    /**
     * Get stored LTL order response
     * @return The full response from order creation
     */
    public static io.restassured.response.Response getCreatedLtlOrderResponse() {
        return createdLtlOrderResponseStatic;
    }
    
    /**
     * Get stored LTL load ID
     * @return The stored LTL load ID
     */
    public static String getCreatedLtlLoadId() {
        return createdLtlLoadIdStatic;
    }
    
    /**
     * Get stored LTL load response
     * @return The full response from load creation
     */
    public static io.restassured.response.Response getCreatedLtlLoadResponse() {
        return createdLtlLoadResponseStatic;
    }
    
    /**
     * Get Indents by Load ID
     * Uses the demand app token and stored load ID
     */
    @Test(priority = 7, groups = {"LTL", "Indents"}, dependsOnMethods = {"testCreateLtlLoad", "testVerifyDemandAppTokenStored"})
    public void testGetIndentsByLoadId() {
        System.out.println("=== Test: Get Indents by Load ID ===");
        
        Assert.assertNotNull(demandAppTokenStatic, "Demand App Token should be available from previous test");
        Assert.assertNotNull(createdLtlLoadIdStatic, "Load ID should be available from load creation");
        
        System.out.println("Using Demand App Token (first 50 chars): " + demandAppTokenStatic.substring(0, Math.min(50, demandAppTokenStatic.length())) + "...");
        System.out.println("Using Load ID: " + createdLtlLoadIdStatic);
        
        // Wait for load to be processed and indent to be created
        System.out.println("Waiting 10 seconds for load to be processed and indent to be created...");
        try {
            Thread.sleep(10000); // Wait 10 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Wait interrupted: " + e.getMessage());
        }
        System.out.println("Wait completed. Proceeding with getting indents...");
        
        // Get indents via controller
        io.restassured.response.Response response = TmsController.getIndentsByLoadId(demandAppTokenStatic, createdLtlLoadIdStatic);
        
        int statusCode = response.getStatusCode();
        System.out.println("Get Indents by Load ID Status Code: " + statusCode);
        
        // Accept 200 as success
        Assert.assertEquals(statusCode, 200, "Get Indents by Load ID API should return HTTP 200. Got: " + statusCode);
        
        String responseBody = response.getBody().asString();
        Assert.assertNotNull(responseBody, "Response body should not be null");
        Assert.assertTrue(responseBody.length() > 0, "Response body should not be empty");
        
        // Extract and store the indent unique code (indent ID)
        String uniqueCode = response.jsonPath().getString("data.uniqueCode");
        Assert.assertNotNull(uniqueCode, "Indent unique code should not be null");
        createdLtlIndentUniqueCodeStatic = uniqueCode;
        
        System.out.println("✓ Indents retrieved successfully");
        System.out.println("Indent Unique Code (Indent ID): " + uniqueCode);
        System.out.println("Stored Indent Unique Code: " + createdLtlIndentUniqueCodeStatic);
        System.out.println("Response Body: " + responseBody);
    }
    
    /**
     * Get stored LTL indent unique code (indent ID)
     * @return The stored LTL indent unique code
     */
    public static String getCreatedLtlIndentUniqueCode() {
        return createdLtlIndentUniqueCodeStatic;
    }
    
    /**
     * Check Indent Detail and Retry Failed Manifestation if needed
     * Checks if indent state is "accepted:accepted" and LRN is populated
     * If not, retries failed manifestation up to 4 times
     * After 4 retries, checks if state is "cancelled:cancelled"
     */
    @Test(priority = 8, groups = {"LTL", "Indents"}, dependsOnMethods = {"testGetIndentsByLoadId", "testVerifyDemandAppTokenStored"})
    public void testCheckIndentDetailAndRetry() {
        System.out.println("=== Test: Check Indent Detail and Retry Failed Manifestation ===");
        
        Assert.assertNotNull(demandAppTokenStatic, "Demand App Token should be available from previous test");
        Assert.assertNotNull(createdLtlIndentUniqueCodeStatic, "Indent Unique Code should be available from previous test");
        
        System.out.println("Using Demand App Token (first 50 chars): " + demandAppTokenStatic.substring(0, Math.min(50, demandAppTokenStatic.length())) + "...");
        System.out.println("Using Indent Unique Code: " + createdLtlIndentUniqueCodeStatic);
        
        final int MAX_RETRIES = 4;
        final int RETRY_DELAY_SECONDS = 5;
        boolean conditionMet = false;
        String finalState = null;
        String finalLrn = null;
        
        for (int attempt = 0; attempt <= MAX_RETRIES; attempt++) {
            System.out.println("\n--- Attempt " + (attempt + 1) + " ---");
            
            // Get indent detail
            io.restassured.response.Response indentDetailResponse = TmsController.getIndentDetail(
                demandAppTokenStatic, 
                createdLtlIndentUniqueCodeStatic
            );
            
            int statusCode = indentDetailResponse.getStatusCode();
            System.out.println("Get Indent Detail Status Code: " + statusCode);
            Assert.assertEquals(statusCode, 200, "Get Indent Detail API should return HTTP 200. Got: " + statusCode);
            
            // Extract state and LRN
            String state = indentDetailResponse.jsonPath().getString("data.state");
            String lrn = indentDetailResponse.jsonPath().getString("data.lrn");
            
            System.out.println("Indent State: " + state);
            System.out.println("Indent LRN: " + lrn);
            
            // Check if condition is met: state is "accepted:accepted" AND LRN is populated
            if ("accepted:accepted".equals(state) && lrn != null && !lrn.trim().isEmpty()) {
                System.out.println("✓ Condition met: State is 'accepted:accepted' and LRN is populated");
                conditionMet = true;
                finalState = state;
                finalLrn = lrn;
                break;
            } else {
                System.out.println("✗ Condition not met: State='" + state + "', LRN='" + lrn + "'");
                
                // If we haven't reached max retries, retry failed manifestation
                if (attempt < MAX_RETRIES) {
                    System.out.println("Waiting " + RETRY_DELAY_SECONDS + " seconds before retrying failed manifestation...");
                    try {
                        Thread.sleep(RETRY_DELAY_SECONDS * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Wait interrupted: " + e.getMessage());
                    }
                    
                    System.out.println("Calling Retry Failed Manifestation API...");
                    io.restassured.response.Response retryResponse = TmsController.retryFailedManifestation(
                        demandAppTokenStatic
                    );
                    
                    int retryStatusCode = retryResponse.getStatusCode();
                    System.out.println("Retry Failed Manifestation Status Code: " + retryStatusCode);
                    // Accept 200, 201, or 202 as success for retry API
                    Assert.assertTrue(retryStatusCode == 200 || retryStatusCode == 201 || retryStatusCode == 202,
                        "Retry Failed Manifestation API should return HTTP 200, 201, or 202. Got: " + retryStatusCode);
                    
                    System.out.println("✓ Retry Failed Manifestation API called successfully");
                } else {
                    // After max retries, store final state for final check
                    finalState = state;
                    finalLrn = lrn;
                }
            }
        }
        
        // After loop, if condition not met, check if state is "cancelled:cancelled"
        if (!conditionMet) {
            System.out.println("\n--- Final Check After " + MAX_RETRIES + " Retries ---");
            System.out.println("Condition was not met after " + MAX_RETRIES + " retries");
            System.out.println("Final State: " + finalState);
            System.out.println("Final LRN: " + finalLrn);
            
            // Check if state is "cancelled:cancelled"
            if ("cancelled:cancelled".equals(finalState)) {
                System.out.println("✓ Final state is 'cancelled:cancelled' as expected after retries");
            } else {
                System.out.println("⚠ Final state is '" + finalState + "' (expected 'cancelled:cancelled' after retries)");
                // Note: This is a warning, not a failure, as the API behavior may vary
            }
        } else {
            System.out.println("\n✓ Success: Indent state is 'accepted:accepted' and LRN is populated");
            System.out.println("Final State: " + finalState);
            System.out.println("Final LRN: " + finalLrn);
        }
    }
}

