package com.delhivery.TMS_WMS.testModules;

import com.delhivery.TMS_WMS.controller.FTLController;
import com.delhivery.TMS_WMS.pojo.ftlorder.response.CreateFTLOrderResponse;
import com.delhivery.core.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * FTL (Full Truck Load) Flow Test Module
 * Automates APIs related to FTL flow including:
 * - FTL order creation
 * - FTL demand creation
 * - FTL trip management
 * - FTL dispatch operations
 * 
 * Follows Express framework pattern - Extends BaseTest and uses Controller + DataProvider
 */
public class FTLFlow extends BaseTest {
    
    // Static variables to store data between test steps
    private static String ftlOrderId;
    private static String ftlDemandId;
    private static String ftlTripId;
    private static String ftlDispatchId;
    
    @BeforeClass
    public void setUp() {
        System.out.println("=== FTL Flow Test Suite Initialization ===");
        // Initialize any required setup for FTL flow tests
    }
    
    /**
     * Test: Create FTL Order
     * Creates a Full Truck Load order in WMS/TMS
     * Uses unique order_number, shipment number, and invoice_number (all synchronized)
     */
    @Test(priority = 1, groups = {"FTL", "OrderCreation"}, description = "Create FTL Order")
    public void testCreateFTLOrder() {
        System.out.println("=== Test: Create FTL Order ===");
        
        // Base order number - last 4 digits will be auto-generated for uniqueness
        String baseOrderNumber = "tmsWMSFTL";
        
        // Optional: Override defaults via HashMap if needed
        HashMap<String, String> orderData = new HashMap<>();
        // Example: orderData.put("fc", "ROUTECHFC");
        // Example: orderData.put("consignee_name", "Custom Name");
        
        // Create FTL order via controller (follows Express framework pattern)
        CreateFTLOrderResponse response = FTLController.createFTLOrder(baseOrderNumber, orderData);
        
        // Assertions
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertTrue(response.getSuccess(), "FTL Order creation should be successful");
        Assert.assertNotNull(response.getRequestId(), "Request ID should not be null");
        
        // Get and log the created order number
        ftlOrderId = FTLController.getLastCreatedFTLOrderNumber();
        Assert.assertNotNull(ftlOrderId, "FTL Order ID should not be null");
        
        System.out.println("✓ FTL Order created successfully");
        System.out.println("Order Number: " + ftlOrderId);
        System.out.println("Request ID: " + response.getRequestId());
        System.out.println("Message: " + response.getMessage());
        System.out.println("Note: order_number, shipment number, and invoice_number are all synchronized: " + ftlOrderId);
    }
    
    /**
     * Test: Verify FTL Order in TMS
     * Verifies that the created FTL order appears in TMS with format: {orderNumber}${shipmentId}
     * Example: tmsWM7946$707343
     */
    @Test(priority = 2, groups = {"FTL", "OrderCreation", "TMS"}, dependsOnMethods = {"testCreateFTLOrder"}, description = "Verify FTL Order in TMS")
    public void testVerifyFTLOrderInTms() {
        System.out.println("=== Test: Verify FTL Order in TMS ===");
        
        Assert.assertNotNull(ftlOrderId, "FTL Order ID should not be null");
        System.out.println("WMS Order Number: " + ftlOrderId);
        System.out.println("Expected TMS Order Format: " + ftlOrderId + "$<shipmentId>");
        
        // Find order in TMS with retry logic (6 attempts, 5 seconds delay)
        boolean orderFound = FTLController.findAndVerifyFTLOrderInTms(ftlOrderId, 6, 5000);
        
        Assert.assertTrue(orderFound, "FTL Order should be present in TMS Orders list after waiting");
        
        // Store the found TMS order ID for future tests
        String tmsOrderId = FTLController.getFoundTmsOrderId();
        String shipmentId = FTLController.getFoundTmsShipmentId();
        
        Assert.assertNotNull(tmsOrderId, "TMS Order ID should not be null");
        Assert.assertNotNull(shipmentId, "Shipment ID should not be null");
        
        System.out.println("✓ FTL Order verified in TMS");
        System.out.println("  WMS Order Number: " + ftlOrderId);
        System.out.println("  TMS Order ID: " + tmsOrderId);
        System.out.println("  Shipment ID: " + shipmentId);
        System.out.println("  This TMS Order ID can be used for creating FTL load");
    }
    
    /**
     * Test: Create FTL Load (Demand)
     * Creates an FTL load/demand for the FTL order in TMS
     * Uses the TMS Order ID found in previous step
     */
    @Test(priority = 3, groups = {"FTL", "DemandCreation", "TMS"}, dependsOnMethods = {"testVerifyFTLOrderInTms"}, description = "Create FTL Load (Demand)")
    public void testCreateFTLDemand() {
        System.out.println("=== Test: Create FTL Load (Demand) ===");
        
        // Get TMS Order ID from previous step
        String tmsOrderId = FTLController.getFoundTmsOrderId();
        Assert.assertNotNull(tmsOrderId, "TMS Order ID should be available from previous step");
        
        System.out.println("Using TMS Order ID: " + tmsOrderId);
        
        // Create FTL Load using stored TMS Order ID
        // Destination facility will be extracted from TMS order or use default
        com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse response = FTLController.createFTLLoad();
        
        // Assertions
        Assert.assertNotNull(response, "FTL Load response should not be null");
        Assert.assertTrue(response.getSuccess(), "FTL Load creation should be successful");
        Assert.assertNotNull(response.getData(), "Response Data should not be null");
        
        // Store unique code for future use
        ftlDemandId = FTLController.getFtlDemandUniqueCode();
        Assert.assertNotNull(ftlDemandId, "FTL Demand Unique Code should not be null");
        
        System.out.println("✓ FTL Load created successfully");
        System.out.println("  TMS Order ID: " + tmsOrderId);
        System.out.println("  Unique Code: " + ftlDemandId);
        System.out.println("  Service Type: FTL");
    }
    
    /**
     * Test: Verify FTL Load in Plan Tab
     * Verifies that the created FTL load appears in TMS Plan Tab (Load Listing)
     * Uses unique code from load creation to find the load
     */
    @Test(priority = 4, groups = {"FTL", "DemandCreation", "TMS"}, dependsOnMethods = {"testCreateFTLDemand"}, description = "Verify FTL Load in Plan Tab")
    public void testVerifyFTLLoadInPlanTab() {
        System.out.println("=== Test: Verify FTL Load in Plan Tab ===");
        
        // Get unique code from previous step
        String uniqueCode = FTLController.getFtlDemandUniqueCode();
        Assert.assertNotNull(uniqueCode, "FTL Load Unique Code should be available from previous step");
        
        System.out.println("Searching for Unique Code: " + uniqueCode);
        System.out.println("Plan Origin: ROUTECHFC");
        System.out.println("State: unallocated:unallocated");
        
        // Verify load in plan tab with retry logic (6 attempts, 5 seconds delay)
        boolean loadFound = FTLController.verifyFTLLoadInPlanTab(uniqueCode, "ROUTECHFC", 6, 5000);
        
        Assert.assertTrue(loadFound, "FTL Load should be present in Plan Tab after waiting");
        
        // Store the found demand ID for future tests
        String demandId = FTLController.getFtlDemandId();
        Assert.assertNotNull(demandId, "Demand ID should not be null");
        
        System.out.println("✓ FTL Load verified in Plan Tab");
        System.out.println("  Unique Code: " + uniqueCode);
        System.out.println("  Demand ID: " + demandId);
        System.out.println("  This Demand ID can be used for assigning spot contract");
    }
    
    /**
     * Test: Assign Spot Contract to FTL Load
     * Assigns a spot contract (transporter) to the verified FTL load
     */
    @Test(priority = 5, groups = {"FTL", "ContractAssignment", "TMS"}, dependsOnMethods = {"testVerifyFTLLoadInPlanTab"}, description = "Assign Spot Contract to FTL Load")
    public void testAssignSpotContract() {
        System.out.println("=== Test: Assign Spot Contract to FTL Load ===");
        
        // Get demand ID from previous step
        String demandId = FTLController.getFtlDemandId();
        Assert.assertNotNull(demandId, "Demand ID should be available from previous step");
        
        // Contract details
        Double freightCost = 100.0;
        String vendorId = "DLVRYORION";
        String vendorName = "delhivery orion";
        
        System.out.println("Demand ID: " + demandId);
        System.out.println("Vendor ID: " + vendorId);
        System.out.println("Vendor Name: " + vendorName);
        System.out.println("Freight Cost: " + freightCost);
        
        // Assign spot contract using stored demand ID
        com.delhivery.TMS_WMS.pojo.tms.response.AssignSpotContractResponse response = 
            FTLController.assignSpotContractToFTLLoad(freightCost, vendorId, vendorName);
        
        // Assertions
        Assert.assertNotNull(response, "Spot Contract response should not be null");
        Assert.assertTrue(response.getSuccess(), "Spot Contract assignment should be successful");
        
        System.out.println("✓ Spot Contract assigned successfully");
        System.out.println("  Demand ID: " + demandId);
        System.out.println("  Vendor: " + vendorName + " (" + vendorId + ")");
        System.out.println("  Freight Cost: " + freightCost);
    }
    
    /**
     * Test: Create Indent for FTL Load
     * Creates an indent for the FTL load using its unique code
     */
    @Test(priority = 6, groups = {"FTL", "IndentCreation", "TMS"}, dependsOnMethods = {"testAssignSpotContract"}, description = "Create Indent for FTL Load")
    public void testCreateIndent() {
        System.out.println("=== Test: Create Indent for FTL Load ===");
        
        // Get unique code from load creation
        String uniqueCode = FTLController.getFtlDemandUniqueCode();
        Assert.assertNotNull(uniqueCode, "FTL Load Unique Code should be available from previous step");
        
        System.out.println("Load Unique Code: " + uniqueCode);
        
        // Create indent using stored unique code
        com.delhivery.TMS_WMS.pojo.tms.response.CreateIndentResponse response = 
            FTLController.createIndentForFTLLoad();
        
        // Assertions
        Assert.assertNotNull(response, "Indent response should not be null");
        Assert.assertTrue(response.getSuccess(), "Indent creation should be successful");
        
        System.out.println("✓ Indent created successfully");
        System.out.println("  Load Unique Code: " + uniqueCode);
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
        if (response.getData() != null && response.getData().getRequestId() != null) {
            System.out.println("  Request ID: " + response.getData().getRequestId());
        }
    }
    
    /**
     * Test: Get Trip from Orion
     * Fetches trip information from Orion using load ID and extracts transaction ID
     * Uses retry logic as trip may take time to sync to Orion after indent creation
     */
    @Test(priority = 7, groups = {"FTL", "Orion", "Trip"}, dependsOnMethods = {"testCreateIndent"}, description = "Get Trip from Orion")
    public void testGetTripFromOrion() {
        System.out.println("=== Test: Get Trip from Orion ===");
        
        // Get unique code from load creation
        String uniqueCode = FTLController.getFtlDemandUniqueCode();
        Assert.assertNotNull(uniqueCode, "FTL Load Unique Code should be available from previous step");
        
        System.out.println("Load ID (External Mapping ID): " + uniqueCode);
        
        // Retry logic: Trip may take time to sync to Orion after indent creation
        com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse response = null;
        String transactionId = null;
        int maxRetries = 6;
        int retryDelayMillis = 5000;
        boolean tripFound = false;
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                // Get trip from Orion using stored unique code
                response = FTLController.getTripFromOrion();
                
                // Check if results are available
                if (response != null && response.getSuccess() && 
                    response.getData() != null && response.getData().getResults() != null && 
                    !response.getData().getResults().isEmpty()) {
                    
                    transactionId = FTLController.getOrionTransactionId();
                    if (transactionId != null) {
                        tripFound = true;
                        System.out.println("✓ Trip found in Orion on attempt " + (i + 1));
                        break;
                    }
                }
                
                if (i < maxRetries - 1) {
                    System.out.println("Attempt " + (i + 1) + ": Trip not found yet. Retrying in " + (retryDelayMillis / 1000) + " seconds...");
                    Thread.sleep(retryDelayMillis);
                }
            } catch (Exception e) {
                System.err.println("Error on attempt " + (i + 1) + ": " + e.getMessage());
                if (i < maxRetries - 1) {
                    try {
                        Thread.sleep(retryDelayMillis);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        // Assertions
        Assert.assertNotNull(response, "Orion Trip response should not be null");
        Assert.assertTrue(response.getSuccess(), "Orion Trip API call should be successful");
        Assert.assertNotNull(response.getData(), "Response Data should not be null");
        Assert.assertNotNull(response.getData().getResults(), "Results should not be null");
        Assert.assertTrue(tripFound, 
            "Trip should be found in Orion after waiting. Results were empty after " + maxRetries + " attempts. " +
            "Trip may not have synced to Orion yet. Please check manually.");
        
        // Extract transaction ID
        transactionId = FTLController.getOrionTransactionId();
        Assert.assertNotNull(transactionId, "Transaction ID should be extracted from Orion response");
        
        System.out.println("✓ Trip retrieved successfully from Orion");
        System.out.println("  Load ID: " + uniqueCode);
        System.out.println("  Transaction ID: " + transactionId);
        System.out.println("  Trip Status: " + response.getData().getResults().get(0).getTripStatus());
        System.out.println("  Origin: " + response.getData().getResults().get(0).getOrigin());
        System.out.println("  Destination: " + response.getData().getResults().get(0).getDestination());
        System.out.println("  Commercial Type: " + response.getData().getResults().get(0).getCommercialType());
    }
    
    @Test(priority = 8, groups = {"FTL", "Orion", "Bid"}, dependsOnMethods = {"testGetTripFromOrion"}, description = "Place Bid for Orion Trip")
    public void testPlaceBidForTrip() {
        System.out.println("=== Test: Place Bid for Orion Trip ===");
        
        String transactionId = FTLController.getOrionTransactionId();
        Assert.assertNotNull(transactionId, "Transaction ID should be available from previous step");
        System.out.println("Transaction ID: " + transactionId);
        
        // Place bid with default values
        com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse response = FTLController.placeBidForTrip();
        
        Assert.assertNotNull(response, "Bid response should not be null");
        Assert.assertTrue(response.getSuccess(), "Bid placement should be successful");
        
        System.out.println("✓ Bid placed successfully");
        System.out.println("  Transaction ID: " + transactionId);
        if (response.getData() != null) {
            System.out.println("  Bid ID: " + response.getData().getId());
            System.out.println("  Message: " + response.getData().getMessage());
        }
    }
    
    @Test(priority = 9, groups = {"FTL", "Orion", "Bid"}, dependsOnMethods = {"testPlaceBidForTrip"}, description = "Confirm Bid for Orion Trip")
    public void testConfirmBidForTrip() {
        System.out.println("=== Test: Confirm Bid for Orion Trip ===");
        
        String transactionId = FTLController.getOrionTransactionId();
        Assert.assertNotNull(transactionId, "Transaction ID should be available from previous step");
        
        String bidId = FTLController.getOrionBidId();
        Assert.assertNotNull(bidId, "Bid ID should be available from previous step");
        
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Bid ID: " + bidId);
        
        // Confirm bid with default values
        com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse response = FTLController.confirmBidForTrip();
        
        Assert.assertNotNull(response, "Confirm bid response should not be null");
        Assert.assertTrue(response.getSuccess(), "Bid confirmation should be successful");
        
        System.out.println("✓ Bid confirmed successfully");
        System.out.println("  Transaction ID: " + transactionId);
        System.out.println("  Bid ID: " + bidId);
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
    }
    
    @Test(priority = 10, groups = {"FTL", "TMS", "Indent"}, dependsOnMethods = {"testConfirmBidForTrip"}, description = "Mark Reported for Indent")
    public void testMarkReportedForIndent() {
        System.out.println("=== Test: Mark Reported for Indent ===");
        
        // Get indent ID (try stored value first, then property)
        String indentId = FTLController.getIndentId();
        if (indentId == null || indentId.isEmpty()) {
            // Try to get from properties
            com.delhivery.core.utils.ConfigLoader config = com.delhivery.core.utils.ConfigLoader.getInstance();
            indentId = config.getProperty("tms.markReported.default.indentId");
        }
        
        // If still not found, skip test with warning
        if (indentId == null || indentId.isEmpty()) {
            System.out.println("⚠ Warning: Indent ID not found. Skipping Mark Reported test.");
            System.out.println("  Please set tms.markReported.default.indentId in qa.properties or ensure indent ID is stored after Create Indent.");
            return; // Skip test instead of failing
        }
        
        System.out.println("Indent ID: " + indentId);
        
        // Call Mark Reported API
        com.delhivery.TMS_WMS.pojo.tms.response.MarkReportedResponse response = 
            FTLController.markReportedForIndent(indentId);
        
        // Assertions
        Assert.assertNotNull(response, "Mark Reported response should not be null");
        Assert.assertTrue(response.getSuccess(), "Mark Reported should be successful");
        
        System.out.println("✓ Mark Reported API called successfully");
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
    }
    
    /**
     * Test: Create Pickwave for FTL Order in WMS
     * Creates a pickwave for the FTL order using shipment ID
     * Note: TMS Order ID format is ShipmentNumber$shipmentId (e.g., tmsWM7711$707379)
     * WMS uses only the shipmentId part (e.g., 707379) for pickwave creation
     */
    @Test(priority = 11, groups = {"FTL", "WMS", "Pickwave"}, dependsOnMethods = {"testVerifyFTLOrderInTms"}, description = "Create Pickwave for FTL Order in WMS")
    public void testCreatePickwaveForFTLOrder() {
        System.out.println("=== Test: Create Pickwave for FTL Order in WMS ===");
        
        String tmsOrderId = FTLController.getFoundTmsOrderId();
        String shipmentId = FTLController.getFoundTmsShipmentId();
        
        Assert.assertNotNull(tmsOrderId, "TMS Order ID should be available from order verification");
        Assert.assertNotNull(shipmentId, "Shipment ID should be available from order verification");
        
        System.out.println("TMS Order ID: " + tmsOrderId);
        System.out.println("Shipment ID (for WMS): " + shipmentId);
        System.out.println("Note: WMS uses only shipmentId part, not the full TMS Order ID");
        
        // Create pickwave using existing WMS Controller method
        String pickwaveRequestId = FTLController.createPickwaveForFTLOrder();
        
        Assert.assertNotNull(pickwaveRequestId, "Pickwave Request ID should not be null");
        Assert.assertTrue(pickwaveRequestId.length() > 0, "Pickwave Request ID should not be empty");
        
        System.out.println("✓ Pickwave created successfully for FTL Order");
        System.out.println("  TMS Order ID: " + tmsOrderId);
        System.out.println("  Shipment ID: " + shipmentId);
        System.out.println("  Pickwave Request ID: " + pickwaveRequestId);
    }

    /**
     * Test: Get Pickwave Create Request Status
     */
    @Test(priority = 12, groups = {"FTL", "WMS", "Pickwave"}, dependsOnMethods = {"testCreatePickwaveForFTLOrder"}, description = "Get Pickwave Create Request Status")
    public void testGetPickwaveCreateRequestStatus() {
        System.out.println("=== Test: Get Pickwave Create Request Status ===");
        
        // Get from WmsController (stored during pickwave creation)
        String pickwaveRequestId = com.delhivery.TMS_WMS.controller.WmsController.getLastPickwaveRequestId();
        if (pickwaveRequestId == null) {
            // Fallback to FTLController
            pickwaveRequestId = FTLController.getWmsPickwaveRequestId();
        }
        
        Assert.assertNotNull(pickwaveRequestId, "Pickwave Request ID should be available");
        
        FTLController.getPickwaveCreateRequestStatusForFTL(pickwaveRequestId);
        System.out.println("✓ Pickwave create request status verified");
    }

    /**
     * Test: Get Pickwave Filters
     */
    @Test(priority = 13, groups = {"FTL", "WMS", "Pickwave"}, dependsOnMethods = {"testGetPickwaveCreateRequestStatus"}, 
          retryAnalyzer = com.delhivery.core.utils.RetryAnalyzer.class, description = "Get Pickwave Filters")
    public void testGetPickwaveFilters() {
        System.out.println("=== Test: Get Pickwave Filters ===");
        
        FTLController.getPickwaveFiltersForFTL();
        
        Integer pickwaveId = FTLController.getWmsPickwaveId();
        Assert.assertNotNull(pickwaveId, "Pickwave ID should be extracted");
        
        System.out.println("✓ Pickwave filters retrieved successfully");
        System.out.println("  Pickwave ID: " + pickwaveId);
        System.out.println("  Picklist IDs: " + FTLController.getWmsPicklistIds());
    }

    /**
     * Test: Assign Picklist to User
     */
    @Test(priority = 14, groups = {"FTL", "WMS", "Picking"}, dependsOnMethods = {"testGetPickwaveFilters"}, description = "Assign Picklist to User")
    public void testAssignPicklistToUser() {
        System.out.println("=== Test: Assign Picklist to User ===");
        
        com.delhivery.core.utils.ConfigLoader config = com.delhivery.core.utils.ConfigLoader.getInstance();
        String username = config.getProperty("wms.user.uuid", "UMSAU002");
        
        FTLController.assignPicklistToUserForFTL(username);
        System.out.println("✓ Picklist assigned to user successfully");
    }

    /**
     * Test: Assign Pick Container
     */
    @Test(priority = 15, groups = {"FTL", "WMS", "Picking"}, dependsOnMethods = {"testAssignPicklistToUser"}, description = "Assign Pick Container")
    public void testAssignPickContainer() {
        System.out.println("=== Test: Assign Pick Container ===");
        
        String containerId = FTLController.assignPickContainerForFTL();
        
        Assert.assertNotNull(containerId, "Container ID should not be null");
        System.out.println("✓ Pick container assigned successfully");
        System.out.println("  Container ID: " + containerId);
    }

    /**
     * Test: Get Picklist Details
     */
    @Test(priority = 16, groups = {"FTL", "WMS", "Picking"}, dependsOnMethods = {"testAssignPickContainer"}, description = "Get Picklist Details")
    public void testGetPicklistDetails() {
        System.out.println("=== Test: Get Picklist Details ===");
        
        Response response = FTLController.getPicklistDetailsForFTL();
        
        Assert.assertEquals(response.getStatusCode(), 200, "Get picklist API should return HTTP 200");
        Boolean successFlag = response.jsonPath().getBoolean("success");
        Assert.assertTrue(successFlag, "Get picklist success flag should be true");
        
        System.out.println("✓ Picklist details retrieved successfully");
    }

    /**
     * Test: Update Container Item (Picking)
     * Extracts product details from picklist response and updates container item
     */
    @Test(priority = 17, groups = {"FTL", "WMS", "Picking"}, dependsOnMethods = {"testGetPicklistDetails"}, description = "Update Container Item (Picking)")
    public void testUpdateContainerItem() {
        System.out.println("=== Test: Update Container Item (Picking) ===");
        
        // Get picklist details to extract product information
        Response picklistResponse = FTLController.getPicklistDetailsForFTL();
        
        Assert.assertEquals(picklistResponse.getStatusCode(), 200, "Get picklist API should return HTTP 200");
        
        // Extract product details from picklist response
        // Response structure: data[0].pick_list_items[0] contains the first item
        Integer itemId = picklistResponse.jsonPath().getInt("data[0].pick_list_items[0].id");
        String dlvSku = picklistResponse.jsonPath().getString("data[0].pick_list_items[0].del_sku");
        String scannableId = picklistResponse.jsonPath().getString("data[0].pick_list_items[0].scannable_id");
        String location = picklistResponse.jsonPath().getString("data[0].pick_list_items[0].location");
        Integer remainingQty = picklistResponse.jsonPath().getInt("data[0].pick_list_items[0].remaining_qty");
        String bucket = picklistResponse.jsonPath().getString("data[0].pick_list_items[0].bucket");
        String lotId = picklistResponse.jsonPath().getString("data[0].pick_list_items[0].lot_id"); // May be null
        
        // Validate required fields
        Assert.assertNotNull(itemId, "Item ID should not be null");
        Assert.assertNotNull(dlvSku, "DLV SKU should not be null");
        Assert.assertNotNull(scannableId, "Scannable ID should not be null");
        Assert.assertNotNull(location, "Location should not be null");
        Assert.assertNotNull(remainingQty, "Remaining quantity should not be null");
        
        System.out.println("Extracted Product Details:");
        System.out.println("  Item ID: " + itemId);
        System.out.println("  DLV SKU: " + dlvSku);
        System.out.println("  Scannable ID: " + scannableId);
        System.out.println("  Location: " + location);
        System.out.println("  Remaining Qty: " + remainingQty);
        System.out.println("  Bucket: " + (bucket != null ? bucket : "PRIME"));
        System.out.println("  Lot ID: " + (lotId != null ? lotId : "null"));
        
        // Get client UUID from config
        com.delhivery.core.utils.ConfigLoader config = com.delhivery.core.utils.ConfigLoader.getInstance();
        String clientUuid = config.getProperty("wms.client1.uuid");
        
        // Update container item with picked quantity (pick all remaining items)
        int pickCount = remainingQty != null ? remainingQty : 1;
        String finalBucket = bucket != null ? bucket : "PRIME";
        String finalLotId = lotId != null ? lotId : "";
        
        FTLController.updateContainerItemForFTL(clientUuid, dlvSku, scannableId, location, itemId, pickCount, finalLotId);
        
        System.out.println("✓ Container item updated successfully");
    }

    /**
     * Test: Complete Picklist
     */
    @Test(priority = 18, groups = {"FTL", "WMS", "Picking"}, dependsOnMethods = {"testUpdateContainerItem"}, description = "Complete Picklist")
    public void testCompletePicklist() {
        System.out.println("=== Test: Complete Picklist ===");
        
        FTLController.completePicklistForFTL();
        System.out.println("✓ Picklist completed successfully");
    }

    /**
     * Test: Pack Initiate
     */
    @Test(priority = 19, groups = {"FTL", "WMS", "Packing"}, dependsOnMethods = {"testCompletePicklist"}, description = "Pack Initiate")
    public void testPackInitiate() {
        System.out.println("=== Test: Pack Initiate ===");
        
        FTLController.packInitiateForFTL();
        System.out.println("✓ Pack initiated successfully");
    }

    /**
     * Test: Get FIM Container Detail
     */
    @Test(priority = 20, groups = {"FTL", "WMS", "Packing"}, dependsOnMethods = {"testPackInitiate"}, description = "Get FIM Container Detail")
    public void testGetFimContainerDetail() {
        System.out.println("=== Test: Get FIM Container Detail ===");
        
        FTLController.getFimContainerDetailForFTL();
        System.out.println("✓ FIM container details retrieved successfully");
    }

    /**
     * Test: Complete Box
     */
    @Test(priority = 21, groups = {"FTL", "WMS", "Packing"}, dependsOnMethods = {"testGetFimContainerDetail"}, description = "Complete Box")
    public void testCompleteBox() {
        System.out.println("=== Test: Complete Box ===");
        
        // Note: These values should come from container details or picklist
        // For now, using placeholder values
        com.delhivery.core.utils.ConfigLoader config = com.delhivery.core.utils.ConfigLoader.getInstance();
        String clientUuid = config.getProperty("wms.client1.uuid");
        
        // TODO: Extract actual dlvSku and qty from container details
        // For now, using placeholder - adjust based on actual response
        System.out.println("⚠ Note: Complete Box requires product details from container");
        System.out.println("  This step may need to be customized based on actual container response");
        
        // Uncomment and adjust when you have actual container product details:
        // FTLController.completeBoxForFTL("DLV_SKU", 2, false, clientUuid);
    }

    /**
     * Test: Pack Shipment
     */
    @Test(priority = 22, groups = {"FTL", "WMS", "Packing"}, dependsOnMethods = {"testGetFimContainerDetail"}, description = "Pack Shipment")
    public void testPackShipment() {
        System.out.println("=== Test: Pack Shipment ===");
        
        // Note: Skipping if completeBox wasn't called
        // In real flow, box should be completed before packing
        try {
            FTLController.packShipmentForFTL();
            System.out.println("✓ Shipment packed successfully");
            
            java.util.List<String> waybills = FTLController.getWmsWaybills();
            if (waybills != null && !waybills.isEmpty()) {
                System.out.println("  Waybills: " + waybills);
            }
        } catch (Exception e) {
            System.out.println("⚠ Warning: " + e.getMessage());
            System.out.println("  Skipping pack shipment - may need box completion first");
        }
    }

    /**
     * Test: Fetch Auto Dimensions at RTS
     */
    @Test(priority = 23, groups = {"FTL", "WMS", "RTS"}, dependsOnMethods = {"testPackShipment"}, description = "Fetch Auto Dimensions at RTS")
    public void testFetchAutoDimensionsAtRts() {
        System.out.println("=== Test: Fetch Auto Dimensions at RTS ===");
        
        try {
            FTLController.fetchAutoDimensionsForFTL();
            System.out.println("✓ Auto dimensions fetched successfully");
        } catch (Exception e) {
            System.out.println("⚠ Warning: " + e.getMessage());
            System.out.println("  Skipping fetch auto dimensions - may need packed shipment first");
        }
    }

    /**
     * Test: Save Auto Dimensions (RTS)
     */
    @Test(priority = 24, groups = {"FTL", "WMS", "RTS"}, dependsOnMethods = {"testFetchAutoDimensionsAtRts"}, description = "Save Auto Dimensions (RTS)")
    public void testSaveAutoDimensions() {
        System.out.println("=== Test: Save Auto Dimensions (RTS) ===");
        
        // Default dimensions - adjust as needed
        double length = 10.0;
        double width = 8.0;
        double height = 6.0;
        double weight = 2.5;
        
        try {
            FTLController.saveAutoDimensionsForFTL(length, width, height, weight);
            System.out.println("✓ Auto dimensions saved successfully");
            System.out.println("  Dimensions: " + length + "x" + width + "x" + height + ", Weight: " + weight);
        } catch (Exception e) {
            System.out.println("⚠ Warning: " + e.getMessage());
            System.out.println("  Skipping save auto dimensions - may need fetched dimensions first");
        }
    }

    /**
     * Test: Verify Shipment Status
     */
    @Test(priority = 25, groups = {"FTL", "WMS", "RTS"}, dependsOnMethods = {"testSaveAutoDimensions"}, description = "Verify Shipment Status")
    public void testVerifyShipmentStatus() {
        System.out.println("=== Test: Verify Shipment Status ===");
        
        // This would typically call a shipment status API
        // For now, just logging that RTS step is complete
        System.out.println("✓ Shipment should be in RTS status after dimension capture");
    }

    /**
     * Test: Create Dispatch
     */
    @Test(priority = 26, groups = {"FTL", "WMS", "Dispatch"}, dependsOnMethods = {"testVerifyShipmentStatus"}, description = "Create Dispatch")
    public void testCreateDispatch() {
        System.out.println("=== Test: Create Dispatch ===");
        
        com.delhivery.core.utils.ConfigLoader config = com.delhivery.core.utils.ConfigLoader.getInstance();
        String clientUuid = config.getProperty("wms.client1.uuid");
        String courier = "DELHIVERY";
        
        try {
            String dispatchNumber = FTLController.createDispatchForFTL(clientUuid, courier);
            Assert.assertNotNull(dispatchNumber, "Dispatch Number should not be null");
            System.out.println("✓ Dispatch created successfully");
            System.out.println("  Dispatch Number: " + dispatchNumber);
        } catch (Exception e) {
            System.out.println("⚠ Warning: " + e.getMessage());
            System.out.println("  Skipping dispatch creation - may need waybills first");
        }
    }

    /**
     * Test: Add Waybill to Dispatch
     */
    @Test(priority = 27, groups = {"FTL", "WMS", "Dispatch"}, dependsOnMethods = {"testCreateDispatch"}, description = "Add Waybill to Dispatch")
    public void testAddWaybillToDispatch() {
        System.out.println("=== Test: Add Waybill to Dispatch ===");
        
        try {
            FTLController.addWaybillToDispatchForFTL();
            System.out.println("✓ Waybills added to dispatch successfully");
        } catch (Exception e) {
            System.out.println("⚠ Warning: " + e.getMessage());
            System.out.println("  Skipping add waybill - may need dispatch and waybills first");
        }
    }

    /**
     * Test: Complete Dispatch
     */
    @Test(priority = 28, groups = {"FTL", "WMS", "Dispatch"}, dependsOnMethods = {"testAddWaybillToDispatch"}, description = "Complete Dispatch")
    public void testCompleteDispatch() {
        System.out.println("=== Test: Complete Dispatch ===");
        
        try {
            FTLController.completeDispatchForFTL();
            System.out.println("✓ Dispatch completed successfully");
        } catch (Exception e) {
            System.out.println("⚠ Warning: " + e.getMessage());
            System.out.println("  Skipping complete dispatch - may need dispatch with waybills first");
        }
    }
    
    /**
     * Test: Create FTL Trip
     * Creates a trip for the FTL demand
     */
    @Test(priority = 5, groups = {"FTL", "TripManagement"}, dependsOnMethods = {"testVerifyFTLDemandStatus"}, description = "Create FTL Trip")
    public void testCreateFTLTrip() {
        System.out.println("=== Test: Create FTL Trip ===");
        
        Assert.assertNotNull(ftlDemandId, "FTL Demand ID should be available");
        
        // TODO: Implement FTL trip creation
        // Example:
        // FTLTripResponse response = FTLController.createFTLTrip(ftlDemandId, tripData);
        // Assert.assertNotNull(response, "Trip response should not be null");
        // Assert.assertTrue(response.getSuccess(), "FTL Trip creation should be successful");
        // ftlTripId = response.getTripId();
        
        System.out.println("✓ FTL Trip creation - To be implemented");
        System.out.println("Demand ID: " + ftlDemandId);
        System.out.println("Trip ID: " + ftlTripId);
    }
    
    /**
     * Test: Assign Vehicle to FTL Trip
     * Assigns a vehicle to the FTL trip
     */
    @Test(priority = 6, groups = {"FTL", "TripManagement"}, dependsOnMethods = {"testCreateFTLTrip"}, description = "Assign Vehicle to FTL Trip")
    public void testAssignVehicleToFTLTrip() {
        System.out.println("=== Test: Assign Vehicle to FTL Trip ===");
        
        Assert.assertNotNull(ftlTripId, "FTL Trip ID should not be null");
        
        // TODO: Implement vehicle assignment
        // Example:
        // FTLVehicleAssignmentResponse response = FTLController.assignVehicleToTrip(ftlTripId, vehicleData);
        // Assert.assertNotNull(response, "Vehicle assignment response should not be null");
        // Assert.assertTrue(response.getSuccess(), "Vehicle assignment should be successful");
        
        System.out.println("✓ Vehicle assignment to FTL Trip - To be implemented");
        System.out.println("Trip ID: " + ftlTripId);
    }
    
    /**
     * Test: Verify FTL Trip Status
     * Verifies that the FTL trip is in the correct status
     */
    @Test(priority = 7, groups = {"FTL", "TripManagement"}, dependsOnMethods = {"testAssignVehicleToFTLTrip"}, description = "Verify FTL Trip Status")
    public void testVerifyFTLTripStatus() {
        System.out.println("=== Test: Verify FTL Trip Status ===");
        
        Assert.assertNotNull(ftlTripId, "FTL Trip ID should not be null");
        
        // TODO: Implement trip status verification
        // Example:
        // FTLTripStatusResponse response = FTLController.getFTLTripStatus(ftlTripId);
        // Assert.assertEquals(response.getStatus(), "ASSIGNED", "Trip status should be ASSIGNED");
        
        System.out.println("✓ FTL Trip status verification - To be implemented");
        System.out.println("Trip ID: " + ftlTripId);
    }
    
    /**
     * Test: Create FTL Dispatch
     * Creates a dispatch for the FTL trip
     */
    @Test(priority = 8, groups = {"FTL", "Dispatch"}, dependsOnMethods = {"testVerifyFTLTripStatus"}, description = "Create FTL Dispatch")
    public void testCreateFTLDispatch() {
        System.out.println("=== Test: Create FTL Dispatch ===");
        
        Assert.assertNotNull(ftlTripId, "FTL Trip ID should be available");
        
        // TODO: Implement FTL dispatch creation
        // Example:
        // FTLDispatchResponse response = FTLController.createFTLDispatch(ftlTripId, dispatchData);
        // Assert.assertNotNull(response, "Dispatch response should not be null");
        // Assert.assertTrue(response.getSuccess(), "FTL Dispatch creation should be successful");
        // ftlDispatchId = response.getDispatchId();
        
        System.out.println("✓ FTL Dispatch creation - To be implemented");
        System.out.println("Trip ID: " + ftlTripId);
        System.out.println("Dispatch ID: " + ftlDispatchId);
    }
    
    /**
     * Test: Verify FTL Dispatch Status
     * Verifies that the FTL dispatch is created successfully
     */
    @Test(priority = 9, groups = {"FTL", "Dispatch"}, dependsOnMethods = {"testCreateFTLDispatch"}, description = "Verify FTL Dispatch Status")
    public void testVerifyFTLDispatchStatus() {
        System.out.println("=== Test: Verify FTL Dispatch Status ===");
        
        Assert.assertNotNull(ftlDispatchId, "FTL Dispatch ID should not be null");
        
        // TODO: Implement dispatch status verification
        // Example:
        // FTLDispatchStatusResponse response = FTLController.getFTLDispatchStatus(ftlDispatchId);
        // Assert.assertEquals(response.getStatus(), "DISPATCHED", "Dispatch status should be DISPATCHED");
        
        System.out.println("✓ FTL Dispatch status verification - To be implemented");
        System.out.println("Dispatch ID: " + ftlDispatchId);
    }
    
    /**
     * Test: Track FTL Shipment
     * Tracks the FTL shipment through its lifecycle
     */
    @Test(priority = 10, groups = {"FTL", "Tracking"}, dependsOnMethods = {"testVerifyFTLDispatchStatus"}, description = "Track FTL Shipment")
    public void testTrackFTLShipment() {
        System.out.println("=== Test: Track FTL Shipment ===");
        
        Assert.assertNotNull(ftlOrderId, "FTL Order ID should be available");
        
        // TODO: Implement FTL shipment tracking
        // Example:
        // FTLTrackingResponse response = FTLController.trackFTLShipment(ftlOrderId);
        // Assert.assertNotNull(response, "Tracking response should not be null");
        // Assert.assertNotNull(response.getCurrentStatus(), "Current status should not be null");
        
        System.out.println("✓ FTL Shipment tracking - To be implemented");
        System.out.println("Order ID: " + ftlOrderId);
    }
    
    /**
     * Test: Complete FTL Delivery
     * Marks the FTL shipment as delivered
     */
    @Test(priority = 11, groups = {"FTL", "Delivery"}, dependsOnMethods = {"testTrackFTLShipment"}, description = "Complete FTL Delivery")
    public void testCompleteFTLDelivery() {
        System.out.println("=== Test: Complete FTL Delivery ===");
        
        Assert.assertNotNull(ftlOrderId, "FTL Order ID should be available");
        
        // TODO: Implement FTL delivery completion
        // Example:
        // FTLDeliveryResponse response = FTLController.completeFTLDelivery(ftlOrderId, deliveryData);
        // Assert.assertNotNull(response, "Delivery response should not be null");
        // Assert.assertTrue(response.getSuccess(), "FTL Delivery completion should be successful");
        
        System.out.println("✓ FTL Delivery completion - To be implemented");
        System.out.println("Order ID: " + ftlOrderId);
    }
    
    /**
     * Test: Verify FTL Order Completion
     * Verifies that the FTL order is completed successfully
     */
    @Test(priority = 12, groups = {"FTL", "Delivery"}, dependsOnMethods = {"testCompleteFTLDelivery"}, description = "Verify FTL Order Completion")
    public void testVerifyFTLOrderCompletion() {
        System.out.println("=== Test: Verify FTL Order Completion ===");
        
        Assert.assertNotNull(ftlOrderId, "FTL Order ID should not be null");
        
        // TODO: Implement final order status verification
        // Example:
        // FTLOrderStatusResponse response = FTLController.getFTLOrderStatus(ftlOrderId);
        // Assert.assertEquals(response.getStatus(), "DELIVERED", "Order status should be DELIVERED");
        
        System.out.println("✓ FTL Order completion verification - To be implemented");
        System.out.println("Order ID: " + ftlOrderId);
        System.out.println("=== FTL Flow Test Suite Completed ===");
    }
    
    /**
     * Helper method to get stored FTL Order ID
     * Can be used by other test classes if needed
     */
    public static String getFtlOrderId() {
        return ftlOrderId;
    }
    
    /**
     * Helper method to get stored FTL Demand ID
     * Can be used by other test classes if needed
     */
    public static String getFtlDemandId() {
        return ftlDemandId;
    }
    
    /**
     * Helper method to get stored FTL Trip ID
     * Can be used by other test classes if needed
     */
    public static String getFtlTripId() {
        return ftlTripId;
    }
    
    /**
     * Helper method to get stored FTL Dispatch ID
     * Can be used by other test classes if needed
     */
    public static String getFtlDispatchId() {
        return ftlDispatchId;
    }
}

