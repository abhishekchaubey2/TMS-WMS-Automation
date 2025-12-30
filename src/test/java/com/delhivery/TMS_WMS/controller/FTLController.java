package com.delhivery.TMS_WMS.controller;

import com.delhivery.TMS_WMS.RequestBuilder.FTLRequestBuilder;
import com.delhivery.TMS_WMS.applicationApi.FTLApiRequests;
import com.delhivery.TMS_WMS.applicationApi.OrionApiRequests;
import com.delhivery.TMS_WMS.applicationApi.TmsApiRequests;
import com.delhivery.TMS_WMS.pojo.ftlorder.request.CreateFTLOrderRequest;
import com.delhivery.TMS_WMS.pojo.ftlorder.response.CreateFTLOrderResponse;
import com.delhivery.TMS_WMS.pojo.tms.request.AssignSpotContractRequest;
import com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest;
import com.delhivery.TMS_WMS.pojo.tms.request.DemandReference;
import com.delhivery.TMS_WMS.pojo.tms.request.RouteDetails;
import com.delhivery.TMS_WMS.pojo.tms.request.SpotContract;
import com.delhivery.TMS_WMS.pojo.tms.response.AssignSpotContractResponse;
import com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse;
import com.delhivery.TMS_WMS.pojo.tms.response.DemandListItem;
import com.delhivery.TMS_WMS.pojo.tms.response.GetDemandsResponse;
import com.delhivery.TMS_WMS.pojo.tms.response.TmsGetOrdersResponse;
import com.delhivery.TMS_WMS.pojo.tms.response.TmsOrderData;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * FTL Controller - Follows Express ApiController pattern
 * Uses RequestBuilder and delegates to ApplicationApi layer
 */
public class FTLController {

    // Store last created FTL order number for future use
    private static String lastCreatedFTLOrderNumber = null;
    private static String lastCreatedFTLRequestId = null;
    // Store TMS order details after verification
    private static String foundTmsOrderId = null;
    private static String foundTmsShipmentId = null;
    // Store FTL demand details
    private static String ftlDemandUniqueCode = null;
    private static String ftlDemandId = null;
    // Store Orion transaction ID
    private static String orionTransactionId = null;
    // Store Orion bid ID
    private static String orionBidId = null;
    // Store Indent ID (from Create Indent response)
    private static String indentId = null;
    // Store vehicle and driver details (from Confirm Bid)
    private static String vehicleNumber = null;
    private static String driverPhone = null;
    private static String driverName = null;

    /**
     * Create FTL Order using RequestBuilder pattern
     * All defaults come from qa.properties, can be overridden via HashMap
     *
     * @param baseOrderNumber Base order number (last 4 digits auto-generated)
     * @param data            HashMap with optional overrides for any field
     * @return CreateFTLOrderResponse
     */
    public static CreateFTLOrderResponse createFTLOrder(String baseOrderNumber, HashMap<String, String> data) {
        System.out.println("=== FTL Controller: Creating FTL Order ===");

        // Build request using RequestBuilder (like Express pattern)
        CreateFTLOrderRequest request = FTLRequestBuilder.buildCreateFTLOrderRequest(baseOrderNumber, data);

        // Extract and store order number
        lastCreatedFTLOrderNumber = request.getData().getOrders().get(0).getOrderNumber();

        // Debug: Print all three values (they should all be the same)
        String orderNumber = request.getData().getOrders().get(0).getOrderNumber();
        String shipmentNumber = request.getData().getOrders().get(0).getShipments().get(0).getNumber();
        String invoiceNumber = request.getData().getOrders().get(0).getShipments().get(0).getInvoice().getInvoiceNumber();

        System.out.println("=== Generated FTL Numbers (All 3 should be same) ===");
        System.out.println("Order Number:    " + orderNumber);
        System.out.println("Shipment Number: " + shipmentNumber);
        System.out.println("Invoice Number:  " + invoiceNumber);
        System.out.println("====================================================");

        // Call Application API layer
        CreateFTLOrderResponse response = FTLApiRequests.createFTLOrderAndGetResponse(request);

        System.out.println("FTL Order created: " + (response.getSuccess() ? "SUCCESS" : "FAILED"));
        
        // Store request ID
        if (response.getRequestId() != null) {
            lastCreatedFTLRequestId = response.getRequestId();
        }

        return response;
    }

    /**
     * Get last created FTL order number
     *
     * @return Last created FTL order number
     */
    public static String getLastCreatedFTLOrderNumber() {
        return lastCreatedFTLOrderNumber;
    }
    
    /**
     * Get last created FTL request ID
     *
     * @return Last created FTL request ID
     */
    public static String getLastCreatedFTLRequestId() {
        return lastCreatedFTLRequestId;
    }

    /**
     * Find and verify FTL Order in TMS
     * Searches for order with pattern: {wmsOrderNumber}${shipmentId}
     * Uses ROUTECHFC as originFacility for FTL orders
     * 
     * @param wmsOrderNumber The WMS order number (e.g., tmsWM7946)
     * @param maxRetries Maximum number of retry attempts
     * @param retryDelayMillis Delay between retries in milliseconds
     * @return true if order found, false otherwise
     */
    public static boolean findAndVerifyFTLOrderInTms(String wmsOrderNumber, int maxRetries, int retryDelayMillis) {
        System.out.println("=== FTL Controller: Finding FTL Order in TMS ===");
        System.out.println("WMS Order Number: " + wmsOrderNumber);
        System.out.println("Searching for pattern: " + wmsOrderNumber + "$*");
        System.out.println("Origin Facility: ROUTECHFC");
        
        boolean orderFound = false;
        String searchPattern = wmsOrderNumber + "$";
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                // Fetch orders from TMS with status=unassigned and originFacility=ROUTECHFC
                TmsGetOrdersResponse response = TmsApiRequests.getOrders("unassigned", "ROUTECHFC");
                
                if (response != null && response.getData() != null) {
                    System.out.println("Fetched " + response.getData().size() + " orders from TMS");
                    
                    // Search for order matching the pattern
                    for (TmsOrderData order : response.getData()) {
                        if (order.getOrderID() != null && order.getOrderID().startsWith(searchPattern)) {
                            orderFound = true;
                            foundTmsOrderId = order.getOrderID();
                            
                            // Extract shipment ID (part after '$')
                            int dollarIndex = foundTmsOrderId.indexOf('$');
                            if (dollarIndex >= 0 && dollarIndex < foundTmsOrderId.length() - 1) {
                                foundTmsShipmentId = foundTmsOrderId.substring(dollarIndex + 1);
                            }
                            
                            // Store destination facility for FTL load creation
                            if (order.getDestination() != null) {
                                foundTmsDestinationFacility = order.getDestination();
                            }
                            
                            System.out.println("✓ Found matching TMS Order ID: " + foundTmsOrderId);
                            System.out.println("  Shipment ID: " + foundTmsShipmentId);
                            System.out.println("  Destination Facility: " + foundTmsDestinationFacility);
                            break;
                        }
                    }
                }
                
                if (orderFound) {
                    break;
                }
                
                if (i < maxRetries - 1) {
                    System.out.println("Order not found yet. Retrying in " + (retryDelayMillis/1000) + " seconds... (Attempt " + (i+1) + "/" + maxRetries + ")");
                    Thread.sleep(retryDelayMillis);
                }
                
            } catch (Exception e) {
                System.err.println("Error while searching for FTL order in TMS: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        if (!orderFound) {
            System.out.println("⚠ Order NOT found after " + maxRetries + " attempts.");
            System.out.println("  Searched for pattern: " + searchPattern);
            System.out.println("  Origin Facility: ROUTECHFC");
            System.out.println("  Status: unassigned");
        }
        
        return orderFound;
    }
    
    /**
     * Get found TMS Order ID (full ID with shipment ID)
     * @return TMS Order ID (e.g., tmsWM7946$707343)
     */
    public static String getFoundTmsOrderId() {
        return foundTmsOrderId;
    }
    
    /**
     * Get found TMS Shipment ID (part after '$')
     * @return Shipment ID (e.g., 707343)
     */
    public static String getFoundTmsShipmentId() {
        return foundTmsShipmentId;
    }
    
    /**
     * Create FTL Load (Demand) in TMS
     * Reuses existing TmsApiRequests.createDemand() with FTL-specific parameters
     * 
     * @param tmsOrderId The TMS Order ID (e.g., tmsWM9350$707345)
     * @param destinationFacility Destination facility code (e.g., DLV_4f4ad67c)
     * @return CreateDemandResponse
     */
    public static CreateDemandResponse createFTLLoad(String tmsOrderId, String destinationFacility) {
        System.out.println("=== FTL Controller: Creating FTL Load (Demand) ===");
        System.out.println("TMS Order ID: " + tmsOrderId);
        System.out.println("Destination Facility: " + destinationFacility);
        
        ConfigLoader config = ConfigLoader.getInstance();
        
        // Build FTL Demand Request
        CreateDemandRequest request = new CreateDemandRequest();
        request.setServiceType("FTL"); // FTL-specific
        request.setVehicleName(config.getProperty("ftl.demand.default.vehicleName", "Truck Open 10 Tyre"));
        
        // Calculate expDispatchAt (current time + some hours, or use from config)
        long expDispatchAt = System.currentTimeMillis() + (24 * 60 * 60 * 1000L); // 24 hours from now
        String expDispatchAtConfig = config.getProperty("ftl.demand.default.expDispatchAt");
        if (expDispatchAtConfig != null && !expDispatchAtConfig.isEmpty()) {
            try {
                expDispatchAt = Long.parseLong(expDispatchAtConfig);
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid expDispatchAt in config, using calculated value");
            }
        }
        request.setExpDispatchAt(expDispatchAt);
        
        request.setOrderIds(Collections.singletonList(tmsOrderId));
        
        // Build Route Details for FTL
        RouteDetails routeDetails = new RouteDetails();
        routeDetails.setOriginFacility("ROUTECHFC"); // FTL-specific origin
        routeDetails.setDestinationFacility(destinationFacility);
        routeDetails.setStops(Collections.emptyList()); // Empty stops for FTL
        
        request.setRouteDetails(routeDetails);
        
        // Call existing TMS API (reusing existing implementation)
        CreateDemandResponse response = TmsApiRequests.createDemand(request);
        
        // Store Unique Code and Demand ID from response
        if (response.getData() != null && 
            response.getData().getRequest() != null && 
            response.getData().getRequest().getBody() != null) {
            
            if (response.getData().getRequest().getBody().getUniqueCode() != null) {
                ftlDemandUniqueCode = response.getData().getRequest().getBody().getUniqueCode();
                System.out.println("✓ FTL Load created successfully");
                System.out.println("  Unique Code: " + ftlDemandUniqueCode);
            }
            
            // Store request ID as demand ID (if available)
            if (response.getData().getRequestId() != null) {
                ftlDemandId = response.getData().getRequestId();
            }
        } else {
            System.err.println("Warning: Unique Code not found in FTL Load response");
        }
        
        return response;
    }
    
    /**
     * Verify FTL Load in TMS Plan Tab (Load Listing)
     * Searches for load by unique code in the plan tab
     * 
     * @param uniqueCode The unique code from load creation (e.g., DLV-SUUGKH)
     * @param planOrigin Origin facility (e.g., ROUTECHFC)
     * @param maxRetries Maximum number of retry attempts
     * @param retryDelayMillis Delay between retries in milliseconds
     * @return true if load found, false otherwise
     */
    public static boolean verifyFTLLoadInPlanTab(String uniqueCode, String planOrigin, int maxRetries, int retryDelayMillis) {
        System.out.println("=== FTL Controller: Verifying FTL Load in Plan Tab ===");
        System.out.println("Unique Code: " + uniqueCode);
        System.out.println("Plan Origin: " + planOrigin);
        System.out.println("State: unallocated:unallocated");
        
        boolean loadFound = false;
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                // Fetch demands from plan tab
                GetDemandsResponse response = TmsApiRequests.getDemands(planOrigin, "unallocated:unallocated", false);
                
                if (response != null && response.getDemandsList() != null) {
                    System.out.println("Fetched " + response.getDemandsList().size() + " demands from plan tab");
                    
                    // Search for load matching the unique code
                    for (DemandListItem demand : response.getDemandsList()) {
                        // Debug: Print first few demands to see structure
                        if (i == 0 && demand == response.getDemandsList().get(0)) {
                            System.out.println("Sample demand from response:");
                            System.out.println("  ID: " + demand.getId());
                            System.out.println("  Unique Code: " + demand.getUniqueCode());
                            System.out.println("  Service Type: " + demand.getServiceType());
                            System.out.println("  State: " + demand.getState());
                        }
                        
                        if (demand.getUniqueCode() != null && demand.getUniqueCode().equals(uniqueCode)) {
                            loadFound = true;
                            ftlDemandId = demand.getId();
                            
                            System.out.println("✓ Found matching FTL Load in Plan Tab");
                            System.out.println("  Unique Code: " + demand.getUniqueCode());
                            System.out.println("  Demand ID: " + demand.getId());
                            System.out.println("  Service Type: " + demand.getServiceType());
                            System.out.println("  State: " + demand.getState());
                            break;
                        }
                    }
                    
                    // If not found, print all unique codes for debugging
                    if (!loadFound && i == 0) {
                        System.out.println("Available unique codes in response:");
                        for (DemandListItem demand : response.getDemandsList()) {
                            System.out.println("  - " + demand.getUniqueCode() + " (ID: " + demand.getId() + ")");
                        }
                    }
                }
                
                if (loadFound) {
                    break;
                }
                
                if (i < maxRetries - 1) {
                    System.out.println("Load not found yet. Retrying in " + (retryDelayMillis/1000) + " seconds... (Attempt " + (i+1) + "/" + maxRetries + ")");
                    Thread.sleep(retryDelayMillis);
                }
                
            } catch (Exception e) {
                System.err.println("Error while searching for FTL load in plan tab: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        if (!loadFound) {
            System.out.println("⚠ Load NOT found after " + maxRetries + " attempts.");
            System.out.println("  Searched for unique code: " + uniqueCode);
            System.out.println("  Plan Origin: " + planOrigin);
            System.out.println("  State: unallocated:unallocated");
        }
        
        return loadFound;
    }
    
    /**
     * Assign Spot Contract to FTL Load
     * 
     * @param demandId The demand ID (from plan tab verification)
     * @param freightCost Freight cost
     * @param vendorId Vendor ID (e.g., DLVRYORION)
     * @param vendorName Vendor Name (e.g., delhivery orion)
     * @return AssignSpotContractResponse
     */
    public static AssignSpotContractResponse assignSpotContractToFTLLoad(
            String demandId, Double freightCost, String vendorId, String vendorName) {
        System.out.println("=== FTL Controller: Assigning Spot Contract ===");
        System.out.println("Demand ID: " + demandId);
        System.out.println("Vendor ID: " + vendorId);
        System.out.println("Vendor Name: " + vendorName);
        System.out.println("Freight Cost: " + freightCost);
        
        // Build Spot Contract Request
        AssignSpotContractRequest request = new AssignSpotContractRequest();
        
        // Set Contract details
        SpotContract contract = new SpotContract(freightCost, vendorId, vendorName);
        request.setContract(contract);
        
        // Set Demand reference
        DemandReference demand = new DemandReference(demandId);
        request.setDemand(demand);
        
        // Call API
        AssignSpotContractResponse response = TmsApiRequests.assignSpotContract(request);
        
        System.out.println("✓ Spot Contract assigned: " + (response.getSuccess() ? "SUCCESS" : "FAILED"));
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
        
        return response;
    }
    
    /**
     * Assign Spot Contract to FTL Load using stored demand ID
     * 
     * @param freightCost Freight cost
     * @param vendorId Vendor ID (e.g., DLVRYORION)
     * @param vendorName Vendor Name (e.g., delhivery orion)
     * @return AssignSpotContractResponse
     */
    public static AssignSpotContractResponse assignSpotContractToFTLLoad(
            Double freightCost, String vendorId, String vendorName) {
        if (ftlDemandId == null) {
            throw new IllegalStateException("Demand ID not found. Please verify FTL load in plan tab first.");
        }
        return assignSpotContractToFTLLoad(ftlDemandId, freightCost, vendorId, vendorName);
    }
    
    /**
     * Get FTL Demand ID (from plan tab verification)
     * @return Demand ID
     */
    public static String getFtlDemandId() {
        return ftlDemandId;
    }
    
    // Store destination facility from TMS order
    private static String foundTmsDestinationFacility = null;
    
    /**
     * Create FTL Load (Demand) in TMS using stored TMS Order ID
     * Extracts destination facility from TMS order or uses default
     * 
     * @return CreateDemandResponse
     */
    public static CreateDemandResponse createFTLLoad() {
        if (foundTmsOrderId == null) {
            throw new IllegalStateException("TMS Order ID not found. Please verify FTL order in TMS first.");
        }
        
        ConfigLoader config = ConfigLoader.getInstance();
        // Use destination facility from TMS order if available, otherwise use default
        String destinationFacility = foundTmsDestinationFacility != null 
            ? foundTmsDestinationFacility 
            : config.getProperty("ftl.demand.default.destinationFacility", "DLV_4f4ad67c");
        
        return createFTLLoad(foundTmsOrderId, destinationFacility);
    }
    
    /**
     * Set destination facility from TMS order (called during order verification)
     * @param destinationFacility Destination facility code
     */
    public static void setFoundTmsDestinationFacility(String destinationFacility) {
        foundTmsDestinationFacility = destinationFacility;
    }
    
    /**
     * Get FTL Demand Unique Code
     * @return Unique Code from FTL Load creation
     */
    public static String getFtlDemandUniqueCode() {
        return ftlDemandUniqueCode;
    }
    
    /**
     * Create Indent for FTL Load
     * Uses the unique code from load creation
     * 
     * @param uniqueCode The unique code (load ID) from load creation (e.g., DLV-SW5G46)
     * @return CreateIndentResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateIndentResponse createIndentForFTLLoad(String uniqueCode) {
        System.out.println("=== FTL Controller: Creating Indent ===");
        System.out.println("Load Unique Code: " + uniqueCode);
        
        // Build Indent Request
        com.delhivery.TMS_WMS.pojo.tms.request.CreateIndentRequest request = 
            new com.delhivery.TMS_WMS.pojo.tms.request.CreateIndentRequest();
        
        // Set load IDs (using unique code)
        java.util.List<String> loadIds = new java.util.ArrayList<>();
        loadIds.add(uniqueCode);
        request.setLoadIds(loadIds);
        
        // Call API
        com.delhivery.TMS_WMS.pojo.tms.response.CreateIndentResponse response = 
            TmsApiRequests.createIndent(request);
        
        System.out.println("✓ Indent created: " + (response.getSuccess() ? "SUCCESS" : "FAILED"));
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
        if (response.getData() != null && response.getData().getRequestId() != null) {
            System.out.println("  Request ID: " + response.getData().getRequestId());
            // Store request ID as indent ID (may need to be updated if actual indent ID is different)
            indentId = response.getData().getRequestId();
        }
        
        return response;
    }
    
    /**
     * Create Indent for FTL Load (using stored unique code)
     * 
     * @return CreateIndentResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateIndentResponse createIndentForFTLLoad() {
        if (ftlDemandUniqueCode == null) {
            throw new IllegalStateException("FTL Load Unique Code not found. Please create FTL load first.");
        }
        return createIndentForFTLLoad(ftlDemandUniqueCode);
    }
    
    /**
     * Get Trip from Orion by Load ID (External Mapping ID)
     * Extracts transaction ID from response and stores it for further use
     * 
     * @param loadId The load unique code (e.g., DLV-S7SYD1)
     * @return GetTripResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse getTripFromOrion(String loadId) {
        System.out.println("=== FTL Controller: Getting Trip from Orion ===");
        System.out.println("Load ID (External Mapping ID): " + loadId);
        
        // Call Orion API
        com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse response = 
            OrionApiRequests.getTripByExternalMappingId(loadId);
        
        // Extract and store transaction ID
        String transactionId = response.getTransactionId();
        if (transactionId != null) {
            orionTransactionId = transactionId;
            System.out.println("✓ Transaction ID extracted and stored: " + transactionId);
        } else {
            System.out.println("⚠ Warning: Transaction ID not found in Orion response");
        }
        
        return response;
    }
    
    /**
     * Get Trip from Orion (using stored unique code)
     * 
     * @return GetTripResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.GetTripResponse getTripFromOrion() {
        if (ftlDemandUniqueCode == null) {
            throw new IllegalStateException("FTL Load Unique Code not found. Please create FTL load first.");
        }
        return getTripFromOrion(ftlDemandUniqueCode);
    }
    
    /**
     * Get Orion Transaction ID
     * @return Transaction ID from Orion trip
     */
    public static String getOrionTransactionId() {
        return orionTransactionId;
    }
    
    /**
     * Place Bid for Orion Trip
     * Places a bid on the trip using the stored transaction ID
     * 
     * @param supplierId Supplier ID (e.g., "ums::user::4b4e91ea-aca1-11ef-9dbc-02c2dde5cc41")
     * @param supplierName Supplier name (e.g., "ANSH VARMA")
     * @param bidPrice Bid price (e.g., 1)
     * @return PlaceBidResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse placeBidForTrip(
            String supplierId, String supplierName, Integer bidPrice) {
        if (orionTransactionId == null || orionTransactionId.isEmpty()) {
            throw new IllegalStateException("Orion Transaction ID not found. Please get trip from Orion first.");
        }
        
        System.out.println("=== FTL Controller: Placing Bid for Orion Trip ===");
        System.out.println("Transaction ID: " + orionTransactionId);
        System.out.println("Supplier ID: " + supplierId);
        System.out.println("Supplier Name: " + supplierName);
        System.out.println("Bid Price: " + bidPrice);
        
        // Create bid request
        com.delhivery.TMS_WMS.pojo.orion.request.PlaceBidRequest bidRequest = 
            new com.delhivery.TMS_WMS.pojo.orion.request.PlaceBidRequest(
                orionTransactionId,
                supplierId,
                supplierName,
                bidPrice,
                "FTL",
                "orion-web"
            );
        
        // Call Orion Bid API
        com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse response = 
            OrionApiRequests.placeBid(bidRequest);
        
        System.out.println("✓ Bid placed successfully");
        if (response.getData() != null) {
            String bidId = response.getData().getId();
            if (bidId != null) {
                orionBidId = bidId;
                System.out.println("  Bid ID: " + bidId);
            }
            System.out.println("  Message: " + response.getData().getMessage());
        }
        
        return response;
    }
    
    /**
     * Place Bid for Orion Trip (with default values)
     * Uses default supplier ID and name from properties or hardcoded values
     * 
     * @return PlaceBidResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.PlaceBidResponse placeBidForTrip() {
        // Default values from curl example
        String defaultSupplierId = "ums::user::4b4e91ea-aca1-11ef-9dbc-02c2dde5cc41";
        String defaultSupplierName = "ANSH VARMA";
        Integer defaultBidPrice = 1;
        
        return placeBidForTrip(defaultSupplierId, defaultSupplierName, defaultBidPrice);
    }
    
    /**
     * Get Orion Bid ID
     * @return Bid ID from placed bid
     */
    public static String getOrionBidId() {
        return orionBidId;
    }
    
    /**
     * Confirm Bid for Orion Trip
     * Confirms the placed bid using transaction ID and bid ID
     * 
     * @param selectedBidId The bid ID to confirm (from place bid response)
     * @param bidAmount The bid amount
     * @param supplierId Supplier ID
     * @param supplierName Supplier name
     * @return ConfirmBidResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse confirmBidForTrip(
            String selectedBidId, Integer bidAmount, String supplierId, String supplierName) {
        if (orionTransactionId == null || orionTransactionId.isEmpty()) {
            throw new IllegalStateException("Orion Transaction ID not found. Please get trip from Orion first.");
        }
        
        if (selectedBidId == null || selectedBidId.isEmpty()) {
            throw new IllegalStateException("Selected Bid ID not found. Please place bid first.");
        }
        
        System.out.println("=== FTL Controller: Confirming Bid for Orion Trip ===");
        System.out.println("Transaction ID: " + orionTransactionId);
        System.out.println("Selected Bid ID: " + selectedBidId);
        System.out.println("Bid Amount: " + bidAmount);
        
        // Create confirm bid data
        com.delhivery.TMS_WMS.pojo.orion.request.ConfirmBidRequest.ConfirmBidData bidData = 
            new com.delhivery.TMS_WMS.pojo.orion.request.ConfirmBidRequest.ConfirmBidData();
        
        bidData.setUuid(orionTransactionId);
        bidData.setSpId(supplierId);
        bidData.setSpName(supplierName);
        bidData.setAdvancePayout(0);
        bidData.setBidAmount(bidAmount);
        bidData.setSelectedBid(selectedBidId);
        bidData.setTruckType("closed");
        bidData.setTruckDisplayName("32FTMXLHQ");
        bidData.setStatus("truck_confirmed");
        bidData.setPlacedTruckPassing(15);
        // Generate unique driver phone number (10 digits) to avoid conflicts
        String uniquePhoneSuffix = Utilities.getUniqueInt(4);
        String uniqueDriverPhone = "990000" + uniquePhoneSuffix; // Ensures 10 digits total
        bidData.setDriverPhone(uniqueDriverPhone);
        System.out.println("  Generated Driver Phone: " + uniqueDriverPhone);
        // Store driver phone for later use (Mark Reported API)
        driverPhone = uniqueDriverPhone;
        // Generate unique vehicle number to avoid conflicts
        String uniqueSuffix = Utilities.getUniqueInt(4);
        String uniqueVehicleNo = "UP12ED" + uniqueSuffix;
        bidData.setVehicleNo(uniqueVehicleNo);
        System.out.println("  Generated Vehicle No: " + uniqueVehicleNo);
        // Store vehicle number for later use (Mark Reported API)
        vehicleNumber = uniqueVehicleNo;
        // Store driver name for later use (Mark Reported API)
        driverName = "rfqdriver";
        // Set expected arrival time to at least 2 hours from now (must be in the future)
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.LocalDateTime arrivalTime = now.plusHours(2).plusMinutes(1); // Add 1 minute buffer to ensure it's in the future
        // Format as ISO_LOCAL_DATE_TIME without microseconds (e.g., "2025-12-29T21:21:21")
        // Remove microseconds/nanoseconds to match API expected format
        arrivalTime = arrivalTime.withNano(0); // Remove nanoseconds
        String formattedArrivalTime = arrivalTime.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        bidData.setExpectedArrivalTimePickup(formattedArrivalTime);
        System.out.println("  Expected Arrival Time: " + formattedArrivalTime + " (Current: " + 
            now.withNano(0).format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")");
        bidData.setUserConfirmation(true);
        bidData.setVendorAdvancePercent(80);
        bidData.setAllocatedVendorClassification("NA");
        bidData.setAllocatedRecommendedVendor(false);
        bidData.setInventoryList(java.util.Collections.emptyList());
        bidData.setDriverName("rfqdriver");
        bidData.setAllocatedByName("qathanos");
        bidData.setAllocatedByPhone("+918347660990");
        
        // Create confirm bid request
        com.delhivery.TMS_WMS.pojo.orion.request.ConfirmBidRequest confirmRequest = 
            new com.delhivery.TMS_WMS.pojo.orion.request.ConfirmBidRequest(
                "SUP",
                "CNF",
                orionTransactionId,
                bidData
            );
        
        // Call Orion Confirm Bid API
        com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse response = 
            OrionApiRequests.confirmBid(orionTransactionId, confirmRequest);
        
        System.out.println("✓ Bid confirmed successfully");
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
        
        return response;
    }
    
    /**
     * Confirm Bid for Orion Trip (with default values)
     * Uses stored bid ID and default values
     * 
     * @return ConfirmBidResponse
     */
    public static com.delhivery.TMS_WMS.pojo.orion.response.ConfirmBidResponse confirmBidForTrip() {
        if (orionBidId == null || orionBidId.isEmpty()) {
            throw new IllegalStateException("Orion Bid ID not found. Please place bid first.");
        }
        
        // Default values from curl example
        String defaultSupplierId = "ums::user::4b4e91ea-aca1-11ef-9dbc-02c2dde5cc41";
        String defaultSupplierName = "ANSH VARMA";
        Integer defaultBidAmount = 1;
        
        return confirmBidForTrip(orionBidId, defaultBidAmount, defaultSupplierId, defaultSupplierName);
    }
    
    /**
     * Mark Reported for Indent
     * Uses stored vehicle/driver details from Confirm Bid and indent ID
     * 
     * @param indentId The indent ID (e.g., "S975XQAR")
     * @return MarkReportedResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.MarkReportedResponse markReportedForIndent(String indentId) {
        if (vehicleNumber == null || driverPhone == null || driverName == null) {
            throw new IllegalStateException("Vehicle/Driver details not found. Please confirm bid first.");
        }
        
        System.out.println("=== FTL Controller: Marking Indent as Reported ===");
        System.out.println("Indent ID: " + indentId);
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Driver Phone: " + driverPhone);
        System.out.println("Driver Name: " + driverName);
        
        // Build Mark Reported Request
        com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest request = 
            new com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest();
        
        // Set dataIds
        com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest.DataIds dataIds = 
            new com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest.DataIds();
        dataIds.setIndent(indentId);
        request.setDataIds(dataIds);
        
        // Set data
        com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest.MarkReportedData data = 
            new com.delhivery.TMS_WMS.pojo.tms.request.MarkReportedRequest.MarkReportedData();
        data.setVehicleNumber(vehicleNumber);
        data.setDriverName(driverName);
        data.setCountryCode("+91");
        data.setDriverNumber(driverPhone);
        // Get reporting center from properties or use default
        ConfigLoader config = ConfigLoader.getInstance();
        String reportingCenter = config.getProperty("tms.markReported.default.reportingCenter", "ROUTECHFC");
        data.setReportingCenter(reportingCenter);
        // Set reportedOn as current timestamp in milliseconds
        data.setReportedOn(System.currentTimeMillis());
        request.setData(data);
        
        // Call API
        com.delhivery.TMS_WMS.pojo.tms.response.MarkReportedResponse response = 
            TmsApiRequests.markReported(request);
        
        System.out.println("✓ Mark Reported API called");
        if (response.getSuccess() != null) {
            System.out.println("  Success: " + response.getSuccess());
        }
        if (response.getMessage() != null) {
            System.out.println("  Message: " + response.getMessage());
        }
        
        return response;
    }
    
    /**
     * Mark Reported for Indent (using stored indent ID)
     * 
     * @return MarkReportedResponse
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.MarkReportedResponse markReportedForIndent() {
        // Try to use stored indent ID first
        if (indentId != null && !indentId.isEmpty()) {
            return markReportedForIndent(indentId);
        }
        
        // Fallback to property if available
        ConfigLoader config = ConfigLoader.getInstance();
        String indentIdFromProperty = config.getProperty("tms.markReported.default.indentId");
        if (indentIdFromProperty != null && !indentIdFromProperty.isEmpty()) {
            return markReportedForIndent(indentIdFromProperty);
        }
        
        throw new IllegalStateException("Indent ID not found. Please provide indent ID or set tms.markReported.default.indentId in properties.");
    }
    
    /**
     * Get stored Indent ID
     * @return Indent ID
     */
    public static String getIndentId() {
        return indentId;
    }
    
    /**
     * Set Indent ID (for manual override)
     * @param indentId The indent ID
     */
    public static void setIndentId(String indentId) {
        FTLController.indentId = indentId;
    }
    
    /**
     * Create Pickwave for FTL Order in WMS using PCA Algorithm
     * Uses the shipment ID from TMS order verification
     * Note: TMS Order ID format is ShipmentNumber$shipmentId (e.g., tmsWM7711$707379)
     * WMS uses only the shipmentId part (e.g., 707379) for pickwave creation
     * 
     * @return Pickwave Request ID
     */
    public static String createPickwaveForFTLOrder() {
        if (foundTmsShipmentId == null) {
            throw new IllegalStateException("Shipment ID not found. Please verify FTL order in TMS first.");
        }
        
        System.out.println("=== FTL Controller: Creating Pickwave for FTL Order (PCA Algorithm) ===");
        System.out.println("TMS Order ID: " + foundTmsOrderId);
        System.out.println("Shipment ID (for WMS): " + foundTmsShipmentId);
        
        // Use new PCA Algorithm API
        int shipmentIdInt = Integer.parseInt(foundTmsShipmentId);
        Response response = com.delhivery.TMS_WMS.applicationApi.WmsApiRequests.createPickwavePCA(shipmentIdInt);
        
        System.out.println("Create Pickwave PCA Response Status: " + response.getStatusCode());
        System.out.println("Create Pickwave PCA Response Body: " + response.getBody().asPrettyString());
        
        if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
            String pickwaveRequestId = response.jsonPath().getString("request_id");
            System.out.println("✓ Pickwave created successfully for FTL Order");
            System.out.println("  Pickwave Request ID: " + pickwaveRequestId);
            // Store in WmsController for consistency
            com.delhivery.TMS_WMS.controller.WmsController.setLastPickwaveRequestId(pickwaveRequestId);
            wmsPickwaveRequestId = pickwaveRequestId;
            return pickwaveRequestId;
        } else {
            throw new RuntimeException("Failed to create pickwave using PCA algorithm. Status: " + response.getStatusCode() + 
                ", Response: " + response.getBody().asString());
        }
    }
    
    // Store WMS pickwave and picklist information
    private static String wmsPickwaveRequestId = null;
    private static Integer wmsPickwaveId = null;
    private static List<Integer> wmsPicklistIds = null;
    private static String wmsPickContainer = null;
    private static List<String> wmsWaybills = new ArrayList<>();
    private static String wmsDispatchNumber = null;

    /**
     * Get Pickwave Create Request Status for FTL Order
     */
    public static void getPickwaveCreateRequestStatusForFTL(String pickwaveRequestId) {
        System.out.println("=== FTL Controller: Getting Pickwave Create Request Status ===");
        com.delhivery.TMS_WMS.controller.WmsController.getPickwaveCreateRequestStatus(pickwaveRequestId, 10, 5000);
        wmsPickwaveRequestId = pickwaveRequestId;
    }

    /**
     * Get Pickwave Filters for FTL Order
     * Uses the WMS order number (lastCreatedFTLOrderNumber) which is the shipment number
     * Includes retry logic as pickwave creation may take time
     */
    public static void getPickwaveFiltersForFTL() {
        if (lastCreatedFTLOrderNumber == null) {
            throw new IllegalStateException("FTL Order Number not found. Please create FTL order first.");
        }
        
        System.out.println("=== FTL Controller: Getting Pickwave Filters ===");
        System.out.println("WMS Order/Shipment Number: " + lastCreatedFTLOrderNumber);
        
        // Retry logic - pickwave may take time to be created (up to 2 minutes)
        int maxRetries = 24; // 2 minutes with 5-second intervals
        long delayMs = 5000;
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                com.delhivery.TMS_WMS.controller.WmsController.getPickwaveFilters(lastCreatedFTLOrderNumber);
                wmsPickwaveId = com.delhivery.TMS_WMS.controller.WmsController.getLastPickwaveId();
                wmsPicklistIds = com.delhivery.TMS_WMS.controller.WmsController.getLastPicklistIds();
                
                if (wmsPickwaveId != null && wmsPicklistIds != null && !wmsPicklistIds.isEmpty()) {
                    System.out.println("✓ Pickwave filters retrieved successfully");
                    System.out.println("Stored Pickwave ID: " + wmsPickwaveId);
                    System.out.println("Stored Picklist IDs: " + wmsPicklistIds);
                    return;
                }
                
                if (i < maxRetries - 1) {
                    System.out.println("Pickwave not found yet. Waiting... Attempt " + (i + 1) + "/" + maxRetries);
                    Thread.sleep(delayMs);
                }
            } catch (Exception e) {
                System.out.println("Error getting pickwave filters: " + e.getMessage());
                if (i < maxRetries - 1) {
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        throw new RuntimeException("Pickwave filters not found after " + maxRetries + " attempts. Pickwave may not be created yet.");
    }

    /**
     * Assign Picklist to User for FTL Order
     */
    public static void assignPicklistToUserForFTL(String username) {
        if (wmsPickwaveId == null || wmsPicklistIds == null || wmsPicklistIds.isEmpty()) {
            throw new IllegalStateException("Pickwave ID or Picklist IDs not found. Please get pickwave filters first.");
        }
        
        System.out.println("=== FTL Controller: Assigning Picklist to User ===");
        com.delhivery.TMS_WMS.controller.WmsController.assignPicklistToUser(wmsPickwaveId, wmsPicklistIds, username);
    }

    /**
     * Assign Pick Container for FTL Order
     */
    public static String assignPickContainerForFTL() {
        if (wmsPicklistIds == null || wmsPicklistIds.isEmpty()) {
            throw new IllegalStateException("Picklist IDs not found. Please get pickwave filters first.");
        }
        
        System.out.println("=== FTL Controller: Assigning Pick Container ===");
        
        // Create container for first picklist
        String containerId = com.delhivery.TMS_WMS.controller.WmsController.createContainerForShipment(foundTmsShipmentId);
        wmsPickContainer = containerId;
        
        // Assign container to first picklist
        com.delhivery.TMS_WMS.controller.WmsController.assignPickContainer(wmsPicklistIds.get(0), containerId);
        
        return containerId;
    }

    /**
     * Get Picklist Details for FTL Order
     * Returns the response so product details can be extracted
     */
    public static Response getPicklistDetailsForFTL() {
        if (wmsPicklistIds == null || wmsPicklistIds.isEmpty()) {
            throw new IllegalStateException("Picklist IDs not found. Please get pickwave filters first.");
        }
        
        System.out.println("=== FTL Controller: Getting Picklist Details ===");
        return com.delhivery.TMS_WMS.controller.WmsController.getPicklistDetails(wmsPicklistIds.get(0), false);
    }

    /**
     * Update Container Item (Picking) for FTL Order
     * This is a simplified version - in real scenarios, you'd need product details from picklist
     */
    public static void updateContainerItemForFTL(String clientUuid, String dlvSku, String scannableId, 
                                                  String pickLocation, int itemId, int pickCount, String lotId) {
        if (wmsPickContainer == null || wmsPicklistIds == null || wmsPicklistIds.isEmpty()) {
            throw new IllegalStateException("Pick container or picklist IDs not found. Please assign container first.");
        }
        
        System.out.println("=== FTL Controller: Updating Container Item (Picking) ===");
        com.delhivery.TMS_WMS.controller.WmsController.updateContainerItem(
            clientUuid, dlvSku, scannableId, pickLocation, wmsPickContainer, 
            itemId, pickCount, lotId, "PRIME", String.valueOf(wmsPicklistIds.get(0))
        );
    }

    /**
     * Complete Picklist for FTL Order
     */
    public static void completePicklistForFTL() {
        if (wmsPicklistIds == null || wmsPicklistIds.isEmpty()) {
            throw new IllegalStateException("Picklist IDs not found. Please get pickwave filters first.");
        }
        
        System.out.println("=== FTL Controller: Completing Picklist ===");
        com.delhivery.TMS_WMS.controller.WmsController.completePicklist(wmsPicklistIds.get(0), false);
    }

    /**
     * Pack Initiate for FTL Order
     */
    public static void packInitiateForFTL() {
        if (wmsPickContainer == null) {
            throw new IllegalStateException("Pick container not found. Please assign container first.");
        }
        
        System.out.println("=== FTL Controller: Pack Initiate ===");
        com.delhivery.TMS_WMS.controller.WmsController.packInitiate(wmsPickContainer);
    }

    /**
     * Get FIM Container Detail for FTL Order
     */
    public static void getFimContainerDetailForFTL() {
        if (wmsPickContainer == null) {
            throw new IllegalStateException("Pick container not found. Please assign container first.");
        }
        
        System.out.println("=== FTL Controller: Get FIM Container Detail ===");
        com.delhivery.TMS_WMS.controller.WmsController.getFimContainerDetail(wmsPickContainer);
    }

    /**
     * Complete Box for FTL Order
     */
    public static void completeBoxForFTL(String dlvSku, int qty, boolean siobFlag, String clientUuid) {
        if (foundTmsShipmentId == null || wmsPickContainer == null) {
            throw new IllegalStateException("Shipment ID or pick container not found.");
        }
        
        System.out.println("=== FTL Controller: Completing Box ===");
        com.delhivery.TMS_WMS.controller.WmsController.completeBox(
            foundTmsShipmentId, wmsPickContainer, dlvSku, qty, siobFlag, clientUuid
        );
    }

    /**
     * Pack Shipment for FTL Order
     */
    public static void packShipmentForFTL() {
        if (foundTmsShipmentId == null) {
            throw new IllegalStateException("Shipment ID not found.");
        }
        
        System.out.println("=== FTL Controller: Packing Shipment ===");
        int shipmentIdInt = Integer.parseInt(foundTmsShipmentId);
        com.delhivery.TMS_WMS.controller.WmsController.packShipment(shipmentIdInt);
        wmsWaybills = com.delhivery.TMS_WMS.controller.WmsController.getLastWaybills();
        System.out.println("Extracted Waybills: " + wmsWaybills);
    }

    /**
     * Fetch Auto Dimensions at RTS for FTL Order
     */
    public static void fetchAutoDimensionsForFTL() {
        if (wmsWaybills == null || wmsWaybills.isEmpty()) {
            throw new IllegalStateException("Waybills not found. Please pack shipment first.");
        }
        
        System.out.println("=== FTL Controller: Fetching Auto Dimensions ===");
        for (String waybill : wmsWaybills) {
            com.delhivery.TMS_WMS.controller.WmsController.fetchAutoDimensions(waybill);
        }
    }

    /**
     * Save Auto Dimensions (RTS) for FTL Order
     */
    public static void saveAutoDimensionsForFTL(double length, double width, double height, double weight) {
        if (wmsWaybills == null || wmsWaybills.isEmpty()) {
            throw new IllegalStateException("Waybills not found. Please pack shipment first.");
        }
        
        System.out.println("=== FTL Controller: Saving Auto Dimensions (RTS) ===");
        for (String waybill : wmsWaybills) {
            com.delhivery.TMS_WMS.controller.WmsController.saveAutoDimensions(waybill, length, width, height, weight);
        }
    }

    /**
     * Create Dispatch for FTL Order
     */
    public static String createDispatchForFTL(String clientUuid, String courier) {
        System.out.println("=== FTL Controller: Creating Dispatch ===");
        String dispatchNumber = com.delhivery.TMS_WMS.controller.WmsController.createDispatch(clientUuid, courier);
        wmsDispatchNumber = dispatchNumber;
        return dispatchNumber;
    }

    /**
     * Add Waybill to Dispatch for FTL Order
     */
    public static void addWaybillToDispatchForFTL() {
        if (wmsWaybills == null || wmsWaybills.isEmpty() || wmsDispatchNumber == null) {
            throw new IllegalStateException("Waybills or dispatch number not found.");
        }
        
        System.out.println("=== FTL Controller: Adding Waybill to Dispatch ===");
        for (String waybill : wmsWaybills) {
            com.delhivery.TMS_WMS.controller.WmsController.addWaybillToDispatch(waybill, wmsDispatchNumber);
        }
    }

    /**
     * Complete Dispatch for FTL Order
     */
    public static void completeDispatchForFTL() {
        if (wmsDispatchNumber == null) {
            throw new IllegalStateException("Dispatch number not found. Please create dispatch first.");
        }
        
        System.out.println("=== FTL Controller: Completing Dispatch ===");
        com.delhivery.TMS_WMS.controller.WmsController.completeDispatch(wmsDispatchNumber);
    }

    // Getters for WMS-related stored values
    public static String getWmsPickwaveRequestId() {
        return wmsPickwaveRequestId;
    }

    public static Integer getWmsPickwaveId() {
        return wmsPickwaveId;
    }

    public static List<Integer> getWmsPicklistIds() {
        return wmsPicklistIds;
    }

    public static String getWmsPickContainer() {
        return wmsPickContainer;
    }

    public static List<String> getWmsWaybills() {
        return wmsWaybills;
    }

    public static String getWmsDispatchNumber() {
        return wmsDispatchNumber;
    }

    /**
     * Clear stored FTL order number (for testing)
     */
    public static void clearLastFTLOrderNumber() {
        lastCreatedFTLOrderNumber = null;
        lastCreatedFTLRequestId = null;
        foundTmsOrderId = null;
        foundTmsShipmentId = null;
        foundTmsDestinationFacility = null;
        ftlDemandUniqueCode = null;
        ftlDemandId = null;
        orionTransactionId = null;
        orionBidId = null;
        indentId = null;
        vehicleNumber = null;
        driverPhone = null;
        driverName = null;
        // Clear WMS-related stored values
        wmsPickwaveRequestId = null;
        wmsPickwaveId = null;
        wmsPicklistIds = null;
        wmsPickContainer = null;
        wmsWaybills.clear();
        wmsDispatchNumber = null;
    }
}

