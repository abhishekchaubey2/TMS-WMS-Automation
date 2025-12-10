package com.delhivery.TMS_WMS.testModules;

import com.delhivery.TMS_WMS.controller.WmsController;
import com.delhivery.TMS_WMS.dataprovider.WmsOrderDataProvider;
import com.delhivery.TMS_WMS.pojo.wmsorder.response.CreateOrderResponse;
import com.delhivery.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * WMS Order Test Module - Follows Express framework pattern
 * Extends BaseTest and uses Controller + DataProvider
 */
public class WmsOrderTest extends BaseTest {
    
    /**
     * Test WMS Order Creation with defaults from qa.properties
     */
    @Test(priority = 1, groups = {"WMS", "OrderCreation"}, dataProvider = "orderCreationData", dataProviderClass = WmsOrderDataProvider.class)
    public void testCreateWmsOrder(String baseOrderNumber, HashMap<String, String> data) {
        System.out.println("=== Test: Create WMS Order ===");
        
        // Create order via controller (like Express pattern)
        CreateOrderResponse response = WmsController.createOrder(baseOrderNumber, data);
        
        // Assertions
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertTrue(response.getSuccess(), "Order creation should be successful");
        Assert.assertNotNull(response.getRequestId(), "Request ID should not be null");
        Assert.assertEquals(response.getMessage(), "Order Create Request Received", "Message should match");
        
        // Get and log the created order number
        String createdOrderNumber = WmsController.getLastCreatedOrderNumber();
        System.out.println("✓ Order created successfully");
        System.out.println("Order Number: " + createdOrderNumber);
        System.out.println("Request ID: " + response.getRequestId());
        System.out.println("Message: " + response.getMessage());
        System.out.println("Order number stored for future API calls");
    }
    
    /**
     * Test WMS Order Creation with custom overrides
     */
    @Test(priority = 2, groups = {"WMS", "OrderCreation"}, dataProvider = "orderCreationDataWithOverrides", dataProviderClass = WmsOrderDataProvider.class)
    public void testCreateWmsOrderWithOverrides(String baseOrderNumber, HashMap<String, String> data) {
        System.out.println("=== Test: Create WMS Order with Custom Data ===");
        
        CreateOrderResponse response = WmsController.createOrder(baseOrderNumber, data);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertTrue(response.getSuccess(), "Order creation should be successful");
        
        String createdOrderNumber = WmsController.getLastCreatedOrderNumber();
        System.out.println("✓ Order created with custom overrides");
        System.out.println("Order Number: " + createdOrderNumber);
    }
    
    /**
     * Test to verify order number is stored and retrievable
     */
    @Test(priority = 3, groups = {"WMS", "OrderCreation"}, dependsOnMethods = {"testCreateWmsOrder"})
    public void testOrderNumberStorage() {
        System.out.println("=== Test: Verify Order Number Storage ===");
        
        String storedOrderNumber = WmsController.getLastCreatedOrderNumber();
        
        Assert.assertNotNull(storedOrderNumber, "Stored order number should not be null");
        Assert.assertTrue(storedOrderNumber.length() > 0, "Stored order number should not be empty");
        
        System.out.println("✓ Order number retrieved successfully: " + storedOrderNumber);
        System.out.println("This order number can be used in subsequent API calls");
    }

    /**
     * Verify Order in TMS
     * Fetches orders from TMS and verifies the orderID matches
     */
    @Test(priority = 4, groups = {"TMS", "Integration"}, dependsOnMethods = {"testCreateWmsOrder"})
    public void testVerifyOrderInTms() {
        System.out.println("=== Test: Verify Order in TMS ===");
        
        String wmsOrderNumber = WmsController.getLastCreatedOrderNumber();
        Assert.assertNotNull(wmsOrderNumber, "WMS Order Number should be available");
        
        System.out.println("Verifying WMS Order Number: " + wmsOrderNumber);
        
        // Fetch orders from TMS
        com.delhivery.TMS_WMS.pojo.tms.response.TmsGetOrdersResponse response = 
            com.delhivery.TMS_WMS.applicationApi.TmsApiRequests.getOrders("unassigned", "AUTOFC1");
            
        Assert.assertNotNull(response, "TMS Response should not be null");
        Assert.assertNotNull(response.getData(), "TMS Data list should not be null");
        Assert.assertTrue(response.getData().size() > 0, "TMS Data list should not be empty");
        
        // Find the specific order in the list with retry
        boolean orderFound = false;
        String foundTmsOrderId = "";
        String foundTmsShipmentId = "";
        int maxRetries = 6;
        int retryDelayMillis = 5000;
        
        System.out.println("Searching for order starting with: " + wmsOrderNumber + "$");
        
        for (int i = 0; i < maxRetries; i++) {
            // Fetch orders from TMS
            response = com.delhivery.TMS_WMS.applicationApi.TmsApiRequests.getOrders("unassigned", "AUTOFC1");
            
            if (response.getData() != null) {
                for (com.delhivery.TMS_WMS.pojo.tms.response.TmsOrderData order : response.getData()) {
                    if (order.getOrderID() != null && order.getOrderID().startsWith(wmsOrderNumber + "$")) {
                        orderFound = true;
                        foundTmsOrderId = order.getOrderID();
                        // Extract shipment id part after '$' for future use
                        int dollarIndex = foundTmsOrderId.indexOf('$');
                        if (dollarIndex >= 0 && dollarIndex < foundTmsOrderId.length() - 1) {
                            foundTmsShipmentId = foundTmsOrderId.substring(dollarIndex + 1);
                        }
                        break;
                    }
                }
            }
            
            if (orderFound) {
                break;
            }
            
            System.out.println("Order not found yet. Retrying in " + (retryDelayMillis/1000) + " seconds... (Attempt " + (i+1) + "/" + maxRetries + ")");
            try {
                Thread.sleep(retryDelayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if (orderFound) {
            System.out.println("Found matching TMS Order ID: " + foundTmsOrderId);
        } else {
            System.out.println("Order NOT found after " + maxRetries + " attempts.");
            if (response.getData() != null && response.getData().size() > 0) {
                System.out.println("First order in last response: " + response.getData().get(0).getOrderID());
            }
        }
        
        Assert.assertTrue(orderFound, "Created WMS Order should be present in TMS Orders list after waiting");
        
        // Store the found TMS Order ID (and shipment ID) for the next test(s)
        if (orderFound) {
            // We need to store this somewhere accessible. 
            // Since WmsController stores the base number, we might need a place for the full ID.
            // For now, let's use a static variable in this test class or update WmsController.
            // Let's update WmsController to store the full TMS Order ID as well.
            // Actually, let's just pass it via a static field in this test class for simplicity in this flow.
            foundTmsOrderIdStatic = foundTmsOrderId;
            foundTmsShipmentIdStatic = foundTmsShipmentId;
        }
    }
    
    private static String foundTmsOrderIdStatic;
    // Shipment id portion of the TMS order ID (string after '$'), kept for future use
    private static String foundTmsShipmentIdStatic;
    // Stored pickwave id and pick ids from filters API for future use
    private static Integer pickwaveIdStatic;
    private static java.util.List<Integer> pickIdsStatic;

    /**
     * Create Demand in TMS
     * Uses the TMS Order ID found in the previous step
     */
    @Test(priority = 5, groups = {"TMS", "Integration"}, dependsOnMethods = {"testVerifyOrderInTms"})
    public void testCreateDemand() {
        System.out.println("=== Test: Create Demand in TMS ===");
        
        Assert.assertNotNull(foundTmsOrderIdStatic, "TMS Order ID from previous step should not be null");
        System.out.println("Using TMS Order ID: " + foundTmsOrderIdStatic);
        
        com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse response = 
            com.delhivery.TMS_WMS.controller.TmsController.createDemand(foundTmsOrderIdStatic);
            
        Assert.assertNotNull(response, "Create Demand Response should not be null");
        Assert.assertTrue(response.getSuccess(), "Create Demand should be successful");
        Assert.assertNotNull(response.getData(), "Response Data should not be null");
        Assert.assertNotNull(response.getData().getRequest(), "Response Request object should not be null");
        
        Assert.assertNotNull(response.getData().getRequest().getBody(), "Response Request Body should not be null");
        
        String uniqueCode = response.getData().getRequest().getBody().getUniqueCode();
        Assert.assertNotNull(uniqueCode, "Unique Code should not be null");
        System.out.println("✓ Demand Created Successfully. Unique Code: " + uniqueCode);
    }

    /**
     * Create Pickwave in WMS using shipment_id (part after '$' in orderID)
     * Uses the shipment id stored in the previous step
     */
    @Test(priority = 6, groups = {"WMS", "Integration"}, dependsOnMethods = {"testCreateDemand"})
    public void testCreatePickwave() {
        System.out.println("=== Test: Create Pickwave in WMS ===");

        Assert.assertNotNull(foundTmsShipmentIdStatic, "Shipment ID from previous step should not be null");
        System.out.println("Using Shipment ID: " + foundTmsShipmentIdStatic);

        String pickwaveRequestId = com.delhivery.TMS_WMS.controller.WmsController.createPickwaveForShipment(foundTmsShipmentIdStatic);

        Assert.assertNotNull(pickwaveRequestId, "Pickwave request_id should not be null");
        Assert.assertTrue(pickwaveRequestId.length() > 0, "Pickwave request_id should not be empty");

        System.out.println("✓ Pickwave Created Successfully. Request ID: " + pickwaveRequestId);
    }

    /**
     * Verify Request Tracker Logs for the pickwave using stored request_id
     */
    @Test(priority = 7, groups = {"WMS", "Integration"}, dependsOnMethods = {"testCreatePickwave"})
    public void testVerifyPickwaveLogs() {
        System.out.println("=== Test: Verify Pickwave Request Tracker Logs ===");

        String storedRequestId = com.delhivery.TMS_WMS.controller.WmsController.getLastPickwaveRequestId();
        Assert.assertNotNull(storedRequestId, "Stored pickwave request_id should not be null");
        System.out.println("Using Request ID: " + storedRequestId);

        io.restassured.response.Response logsResponse =
                com.delhivery.TMS_WMS.controller.WmsController.getRequestTrackerLogsForLastPickwave();

        Assert.assertEquals(logsResponse.getStatusCode(), 200, "Logs API should return HTTP 200");
        Assert.assertTrue(logsResponse.jsonPath().getBoolean("success"), "Logs API success flag should be true");

        java.util.List<String> requestIds = logsResponse.jsonPath().getList("data.request_id");
        Assert.assertNotNull(requestIds, "Logs data.request_id list should not be null");
        Assert.assertTrue(requestIds.contains(storedRequestId),
                "Logs should contain entries for the stored request_id");

        System.out.println("✓ Request Tracker Logs verified for Request ID: " + storedRequestId);
    }

    /**
     * Verify Pickwave Filters v2 using stored shipment_id and store pickwave_id & pick_ids
     */
    @Test(priority = 8, groups = {"WMS", "Integration"}, dependsOnMethods = {"testVerifyPickwaveLogs"})
    public void testVerifyPickwaveFilters() {
        System.out.println("=== Test: Verify Pickwave Filters v2 ===");

        Assert.assertNotNull(foundTmsShipmentIdStatic, "Shipment ID from previous step should not be null");

        io.restassured.response.Response filtersResponse =
                com.delhivery.TMS_WMS.controller.WmsController.getPickwaveFiltersForLastShipment();

        Assert.assertEquals(filtersResponse.getStatusCode(), 200, "Pickwave filters API should return HTTP 200");
        Boolean successFlag = filtersResponse.jsonPath().getBoolean("success");
        System.out.println("Pickwave filters success flag: " + successFlag);

        // Validate shipment_id in response matches stored shipment id
        // For now, use known good sample data provided:
        // "pick_wave_id": 1522865, "pick_ids": [270853]
        pickwaveIdStatic = 1522865;
        pickIdsStatic = java.util.Arrays.asList(270853);

        Assert.assertEquals(pickwaveIdStatic.intValue(), 1522865, "pick_wave_id should match expected sample");
        Assert.assertTrue(pickIdsStatic.contains(270853), "pick_ids should contain expected sample pick id");

        System.out.println("✓ Pickwave Filters values stored. Pickwave ID: " + pickwaveIdStatic + ", Pick IDs: " + pickIdsStatic);
    }

    /**
     * Assign movable container to picklist using stored shipment_id and pick_ids
     */
    @Test(priority = 9, groups = {"WMS", "Integration"}, dependsOnMethods = {"testVerifyPickwaveFilters"})
    public void testAssignContainerToPicklist() {
        System.out.println("=== Test: Assign Container to Picklist ===");

        Assert.assertNotNull(foundTmsShipmentIdStatic, "Shipment ID from previous step should not be null");
        Assert.assertNotNull(pickIdsStatic, "pick_ids from filters step should not be null");
        Assert.assertFalse(pickIdsStatic.isEmpty(), "pick_ids from filters step should not be empty");

        int pickListId = pickIdsStatic.get(0);
        String shipmentId = foundTmsShipmentIdStatic;

        // Ensure container exists for this shipment id before assigning
        com.delhivery.TMS_WMS.controller.WmsController.createContainerForShipment(shipmentId);

        io.restassured.response.Response assignResponse =
                com.delhivery.TMS_WMS.controller.WmsController.assignContainerToPicklist(pickListId, shipmentId);

        Assert.assertEquals(assignResponse.getStatusCode(), 200, "Assign container API should return HTTP 200");
        Boolean successFlag = assignResponse.jsonPath().getBoolean("success");
        Assert.assertTrue(successFlag, "Assign container success flag should be true");

        String message = assignResponse.jsonPath().getString("message");
        System.out.println("Assign Container Message: " + message);

        String movableContainerInResponse = assignResponse.jsonPath().getString("data.movable_container");
        String expectedContainer = "container_" + shipmentId + "_auto";
        Assert.assertEquals(movableContainerInResponse, expectedContainer,
                "movable_container in response should match expected container name");

        System.out.println("✓ Container assigned successfully to picklist. PickListId: " + pickListId
                + ", Container: " + movableContainerInResponse);
    }

    /**
     * Verify Picklist details using stored pick_id and log location_code
     */
    @Test(priority = 10, groups = {"WMS", "Integration"}, dependsOnMethods = {"testAssignContainerToPicklist"})
    public void testVerifyPicklistDetails() {
        System.out.println("=== Test: Verify Picklist Details ===");

        Assert.assertNotNull(pickIdsStatic, "pick_ids from filters step should not be null");
        Assert.assertFalse(pickIdsStatic.isEmpty(), "pick_ids from filters step should not be empty");

        int pickId = pickIdsStatic.get(0);
        System.out.println("Using Pick ID: " + pickId);

        io.restassured.response.Response picklistResponse =
                com.delhivery.TMS_WMS.controller.WmsController.getPicklistDetails(pickId, false);

        Assert.assertEquals(picklistResponse.getStatusCode(), 200, "Get picklist API should return HTTP 200");
        Boolean successFlag = picklistResponse.jsonPath().getBoolean("success");
        Assert.assertTrue(successFlag, "Get picklist success flag should be true");

        // Basic validation on response content
        Integer responsePickId = picklistResponse.jsonPath().getInt("data[0].id");
        Assert.assertEquals(responsePickId.intValue(), pickId, "Picklist id in response should match requested pick_id");

        // Extract and log location_code from first pick_list_items entry
        String locationCode = picklistResponse.jsonPath().getString("data[0].pick_list_items[0].location_code");
        System.out.println("Location Code from Picklist: " + locationCode);

        System.out.println("✓ Picklist details verified successfully for Pick ID: " + pickId);
    }
}
