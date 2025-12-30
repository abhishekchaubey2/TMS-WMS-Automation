package com.delhivery.TMS_WMS.controller;

import com.delhivery.TMS_WMS.RequestBuilder.WmsRequestBuilder;
import com.delhivery.TMS_WMS.applicationApi.WmsApiRequests;
import com.delhivery.TMS_WMS.pojo.wmsorder.request.CreateOrderRequest;
import com.delhivery.TMS_WMS.pojo.wmsorder.response.CreateOrderResponse;
import com.delhivery.TMS_WMS.pojo.wmsoutbound.request.*;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;

import java.util.*;

/**
 * WMS Controller - Follows Express ApiController pattern
 * Uses RequestBuilder and delegates to ApplicationApi layer
 */
public class WmsController {

    // Store last created order number for future use
    private static String lastCreatedOrderNumber = null;
    // Store last order create request id for request-tracker (if needed)
    private static String lastOrderCreateRequestId = null;
    // Store last created pickwave request id for future use
    private static String lastPickwaveRequestId = null;
    // Store last pickwave id and pick ids from filters API
    private static Integer lastPickwaveId = null;
    private static List<Integer> lastPickIds = null;
    // Store last created container id for future use (if needed)
    private static String lastCreatedContainerId = null;
    // Store picklist IDs (alias for lastPickIds for consistency)
    private static List<Integer> lastPicklistIds = null;
    // Store waybill and dispatch information
    private static List<String> lastWaybills = new ArrayList<>();
    private static String lastDispatchNumber = null;

    /**
     * Create WMS Order using RequestBuilder pattern
     * All defaults come from qa.properties, can be overridden via HashMap
     *
     * @param baseOrderNumber Base order number (last 4 digits auto-generated)
     * @param data            HashMap with optional overrides for any field
     * @return CreateOrderResponse
     */
    public static CreateOrderResponse createOrder(String baseOrderNumber, HashMap<String, String> data) {
        System.out.println("=== WMS Controller: Creating Order ===");

        // Build request using RequestBuilder (like Express pattern)
        CreateOrderRequest request = WmsRequestBuilder.buildCreateOrderRequest(baseOrderNumber, data);

        // Extract and store order number
        lastCreatedOrderNumber = request.getData().getOrders().get(0).getOrderNumber();

        // Debug: Print all three values (they should all be the same)
        String orderNumber = request.getData().getOrders().get(0).getOrderNumber();
        String shipmentNumber = request.getData().getOrders().get(0).getShipments().get(0).getNumber();
        String invoiceNumber = request.getData().getOrders().get(0).getShipments().get(0).getInvoice().getInvoiceNumber();

        System.out.println("=== Generated Numbers (All 3 should be same) ===");
        System.out.println("Order Number:    " + orderNumber);
        System.out.println("Shipment Number: " + shipmentNumber);
        System.out.println("Invoice Number:  " + invoiceNumber);
        System.out.println("================================================");

        // Call Application API layer
        CreateOrderResponse response = WmsApiRequests.createOrderAndGetResponse(request);

        // Store request id for potential request-tracker checks
        lastOrderCreateRequestId = response.getRequestId();

        System.out.println("Order created: " + (response.getSuccess() ? "SUCCESS" : "FAILED"));

        return response;
    }

    /**
     * Create Pickwave in WMS for a given shipment id string (part after '$' in orderID).
     * Stores and returns the request_id from the create pickwave response.
     */
    public static String createPickwaveForShipment(String shipmentIdString) {
        System.out.println("=== WMS Controller: Creating Pickwave ===");
        System.out.println("Shipment ID (string): " + shipmentIdString);

        int shipmentId = Integer.parseInt(shipmentIdString);
        Response response = WmsApiRequests.createPickwave(shipmentId);

        System.out.println("Create Pickwave Response Status: " + response.getStatusCode());
        System.out.println("Create Pickwave Response Body: " + response.getBody().asPrettyString());

        lastPickwaveRequestId = response.jsonPath().getString("request_id");
        return lastPickwaveRequestId;
    }

    /**
     * Get last created order number
     *
     * @return Last created order number
     */
    public static String getLastCreatedOrderNumber() {
        return lastCreatedOrderNumber;
    }

    public static String getLastOrderCreateRequestId() {
        return lastOrderCreateRequestId;
    }

    public static String getLastPickwaveRequestId() {
        return lastPickwaveRequestId;
    }

    public static Integer getLastPickwaveId() {
        return lastPickwaveId;
    }

    public static List<Integer> getLastPickIds() {
        return lastPickIds;
    }

    public static String getLastCreatedContainerId() {
        return lastCreatedContainerId;
    }

    /**
     * Update outbound FC configuration in WMS.
     */
    public static Response updateOutboundFcConfig() {
        System.out.println("=== WMS Controller: Updating Outbound FC Config ===");
        Response response = WmsApiRequests.updateOutboundFcConfig();
        System.out.println("Update FC Config Response Status: " + response.getStatusCode());
        System.out.println("Update FC Config Response Body: " + response.getBody().asPrettyString());
        return response;
    }

    /**
     * Fetch WMS request tracker logs for the last created pickwave
     *
     * @return raw Response containing logs
     */
    public static Response getRequestTrackerLogsForLastPickwave() {
        if (lastPickwaveRequestId == null) {
            throw new IllegalStateException("No pickwave request id stored yet");
        }
        System.out.println("=== WMS Controller: Fetching Request Tracker Logs ===");
        System.out.println("Using Request ID: " + lastPickwaveRequestId);
        return WmsApiRequests.getRequestTrackerLogs(lastPickwaveRequestId);
    }

    /**
     * Fetch WMS request tracker logs for the last order create request.
     *
     * @return raw Response containing logs
     */
    public static Response getOrderCreateRequestStatus() {
        if (lastOrderCreateRequestId == null) {
            throw new IllegalStateException("No order create request id stored yet");
        }
        System.out.println("=== WMS Controller: Fetching Order Create Request Status ===");
        System.out.println("Using Request ID: " + lastOrderCreateRequestId);
        return WmsApiRequests.getRequestTrackerLogs(lastOrderCreateRequestId);
    }

    /**
     * Call Pickwave filters v2 API for the last created shipment/order.
     * Uses stored shipment/order number and saves pickwave id + pick ids.
     *
     * @return raw Response from filters API
     */
    public static Response getPickwaveFiltersForLastShipment() {
        if (lastCreatedOrderNumber == null) {
            throw new IllegalStateException("No WMS order/shipment number stored yet");
        }

        ConfigLoader config = ConfigLoader.getInstance();
        String shipmentNumber = lastCreatedOrderNumber;
        String fc = config.getWmsFcUuid();

        System.out.println("=== WMS Controller: Fetching Pickwave Filters v2 ===");
        System.out.println("Using Shipment Number: " + shipmentNumber);

        Response response = WmsApiRequests.getPickwaveFilters(
                "CREATED",
                shipmentNumber,
                0,
                fc,
                false
        );

        try {
            // Best-effort extraction and storage of pickwave id and pick ids
            Integer pwId = response.jsonPath().getInt("data[0].id");
            List<Integer> pIds = response.jsonPath().getList("data[0].pick_lists.id");
            lastPickwaveId = pwId;
            lastPickIds = pIds;
            lastPicklistIds = pIds; // Also store as lastPicklistIds for consistency
            System.out.println("Stored Pickwave ID: " + lastPickwaveId + ", Pick IDs: " + lastPickIds);
        } catch (Exception e) {
            System.out.println("Unable to parse pickwave id / pick ids from filters response: " + e.getMessage());
        }

        return response;
    }

    /**
     * Assign a movable container to a picklist, using shipment id to build container name
     *
     * @param pickListId        pick_list_id value
     * @param shipmentIdString  shipment id string (e.g. 706818)
     * @return raw Response from assign container API
     */
    public static Response assignContainerToPicklist(int pickListId, String shipmentIdString) {
        String movableContainer = "container_" + shipmentIdString + "_auto";
        System.out.println("=== WMS Controller: Assigning Container to Picklist ===");
        System.out.println("pick_list_id: " + pickListId + ", movable_container: " + movableContainer);

        Response response = WmsApiRequests.assignPicklistContainer(pickListId, movableContainer, false);

        System.out.println("Assign Container Response Status: " + response.getStatusCode());
        System.out.println("Assign Container Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Assign a fixed container id to a picklist (e.g. CONT_DEMO_002)
     */
    public static Response assignFixedContainerToPicklist(int pickListId, String containerId) {
        System.out.println("=== WMS Controller: Assigning Fixed Container to Picklist ===");
        System.out.println("pick_list_id: " + pickListId + ", movable_container: " + containerId);
        Response response = WmsApiRequests.assignPicklistContainer(pickListId, containerId, false);
        System.out.println("Assign Fixed Container Response Status: " + response.getStatusCode());
        System.out.println("Assign Fixed Container Response Body: " + response.getBody().asPrettyString());
        return response;
    }

    /**
     * Internal helper to build and send a container create request for a given container id.
     * Mirrors the provided container/create curl while using framework standards.
     */
    private static Response createContainerInternal(String containerId) {
        ConfigLoader config = ConfigLoader.getInstance();

        System.out.println("=== WMS Controller: Creating Container ===");
        System.out.println("Container ID: " + containerId);

        java.util.Map<String, Object> body = new java.util.HashMap<>();
        body.put("container_id", containerId);
        body.put("status", null);
        body.put("length", 1);
        body.put("width", 2);
        body.put("height", 3);
        body.put("volume", 10);
        body.put("fulfillment_center_name", config.getWmsFcUuid());
        body.put("velocity", "1");
        body.put("aisle", "1");
        body.put("level", "1");
        body.put("column", "1");
        body.put("zone", "autoZone1");
        body.put("zone_type", "STOCKEDAREA");
        body.put("container_type", "shelf");
        body.put("is_movable", true);
        body.put("is_temp_container", false);
        body.put("audit", false);
        body.put("max_wt", 5);
        body.put("floor", "First");
        body.put("location", "AUTOFC1.autoZone1.1.30.1.2");
        body.put("fc_name", "AUTOFC1");
        java.util.Map<String, Object> coords = new java.util.HashMap<>();
        coords.put("X", 1);
        coords.put("Y", 2);
        coords.put("Z", 3);
        body.put("coordinates", coords);

        Response response = WmsApiRequests.createContainer(body);

        System.out.println("Create Container Response Status: " + response.getStatusCode());
        System.out.println("Create Container Response Body: " + response.getBody().asPrettyString());

        lastCreatedContainerId = containerId;
        return response;
    }

    /**
     * Create WMS Container for a specific shipment id (container name derived from shipment id).
     */
    public static String createContainerForShipment(String shipmentIdString) {
        String containerId = "container_" + shipmentIdString + "_auto";
        createContainerInternal(containerId);
        return containerId;
    }

    /**
     * Create WMS Container with an explicit container id (e.g. CONT_DEMO_002).
     */
    public static String createContainerWithId(String containerId) {
        createContainerInternal(containerId);
        return containerId;
    }

    /**
     * Get Picklist details for a given pick id.
     * Mirrors: GET /wms/qa2/pick/get/picklist?pick_id=...&multi_container=...
     */
    public static Response getPicklistDetails(int pickId, boolean multiContainer) {
        System.out.println("=== WMS Controller: Get Picklist Details ===");
        System.out.println("pick_id: " + pickId + ", multi_container: " + multiContainer);

        Response response = WmsApiRequests.getPicklist(pickId, multiContainer);

        System.out.println("Get Picklist Response Status: " + response.getStatusCode());
        System.out.println("Get Picklist Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Assign picklist to current WMS user.
     */
    public static Response assignPicklistToUser(int pickWaveId, int pickListId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String user = config.getWmsUserUuid();
        String fc = config.getWmsFcUuid();

        System.out.println("=== WMS Controller: Assign Picklist To User ===");
        System.out.println("Pickwave ID: " + pickWaveId + ", Picklist ID: " + pickListId + ", User: " + user);

        Response response = WmsApiRequests.assignPicklistToUser(pickListId, user, fc);

        System.out.println("Assign Picklist To User Response Status: " + response.getStatusCode());
        System.out.println("Assign Picklist To User Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Update picklist container item using provided payload.
     */
    public static Response updatePicklistContainerItem(int itemId, java.util.Map<String, Object> body) {
        System.out.println("=== WMS Controller: Update Picklist Container Item ===");
        System.out.println("Item ID: " + itemId);

        Response response = WmsApiRequests.updatePicklistContainerItem(itemId, body);

        System.out.println("Update Container Item Response Status: " + response.getStatusCode());
        System.out.println("Update Container Item Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Complete picklist.
     */
    public static Response completePicklist(int pickListId) {
        System.out.println("=== WMS Controller: Complete Picklist ===");
        System.out.println("Picklist ID: " + pickListId);

        Response response = WmsApiRequests.completePicklist(pickListId, false, false);

        System.out.println("Complete Picklist Response Status: " + response.getStatusCode());
        System.out.println("Complete Picklist Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Fetch pickwave filters for last shipment with custom status.
     */
    public static Response getPickwaveFiltersForLastShipmentWithStatus(String status) {
        if (lastCreatedOrderNumber == null) {
            throw new IllegalStateException("No WMS order/shipment number stored yet");
        }

        ConfigLoader config = ConfigLoader.getInstance();
        String shipmentNumber = lastCreatedOrderNumber;
        String fc = config.getWmsFcUuid();

        System.out.println("=== WMS Controller: Fetching Pickwave Filters v2 (custom status) ===");
        System.out.println("Using Shipment Number: " + shipmentNumber + ", Status: " + status);

        return WmsApiRequests.getPickwaveFilters(
                status,
                shipmentNumber,
                0,
                fc,
                false
        );
    }

    /**
     * Initiate pack for the given container id.
     */
    public static Response initiatePack(String containerId) {
        System.out.println("=== WMS Controller: Initiate Pack ===");
        System.out.println("Container ID: " + containerId);

        Response response = WmsApiRequests.initiatePack(containerId);

        System.out.println("Pack Initiate Response Status: " + response.getStatusCode());
        System.out.println("Pack Initiate Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Get FIM container detail for the given container id.
     */
    public static Response getFimContainerDetail(String containerId) {
        System.out.println("=== WMS Controller: Get FIM Container Detail ===");
        System.out.println("Container ID: " + containerId);

        Response response = WmsApiRequests.getFimContainerDetail(containerId);

        System.out.println("FIM Container Detail Response Status: " + response.getStatusCode());
        System.out.println("FIM Container Detail Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Complete box for shipment using given container id and product details.
     */
    public static Response completeBox(long shipmentId, String containerId) {
        ConfigLoader config = ConfigLoader.getInstance();

        System.out.println("=== WMS Controller: Complete Box ===");
        System.out.println("Shipment ID: " + shipmentId + ", Container ID: " + containerId);

        java.util.Map<String, Object> product = new java.util.HashMap<>();
        product.put("product_id", config.getWmsPackDefaultProductId());
        product.put("quantity", 1);
        product.put("siob", false);
        product.put("client_uuid", config.getWmsClientUuid());

        java.util.Map<String, Object> container = new java.util.HashMap<>();
        container.put("container_id", containerId);
        container.put("products", java.util.Collections.singletonList(product));

        java.util.Map<String, Object> body = new java.util.HashMap<>();
        body.put("fulfillment_center_id", config.getWmsFcUuid());
        body.put("shipment_id", String.valueOf(shipmentId));
        body.put("containers", java.util.Collections.singletonList(container));

        Response response = WmsApiRequests.completeBox(body);

        System.out.println("Complete Box Response Status: " + response.getStatusCode());
        System.out.println("Complete Box Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Pack shipment for given shipment id.
     */
    public static Response packShipment(long shipmentId) {
        System.out.println("=== WMS Controller: Pack Shipment ===");
        System.out.println("Shipment ID: " + shipmentId);

        Response response = WmsApiRequests.packShipment(shipmentId);

        System.out.println("Pack Shipment Response Status: " + response.getStatusCode());
        System.out.println("Pack Shipment Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Fetch auto dimensions for a given waybill / barcode identifier.
     */
    public static Response fetchAutoDimensions(String barcodeIdentifier) {
        System.out.println("=== WMS Controller: Fetch Auto Dimensions ===");
        System.out.println("Barcode Identifier: " + barcodeIdentifier);

        Response response = WmsApiRequests.fetchAutoDimensions(barcodeIdentifier);

        System.out.println("Fetch Auto Dimensions Response Status: " + response.getStatusCode());
        System.out.println("Fetch Auto Dimensions Response Body: " + response.getBody().asPrettyString());

        return response;
    }

    /**
     * Clear stored order number (for testing)
     */
    public static void clearLastOrderNumber() {
        lastCreatedOrderNumber = null;
    }

    /**
     * Get Pickwave Create Request Status (with polling)
     */
    public static Response getPickwaveCreateRequestStatus(String requestId, int maxRetries, long delayMs) {
        System.out.println("=== WMS Controller: Getting Pickwave Create Request Status ===");
        System.out.println("Request ID: " + requestId);
        
        for (int i = 0; i < maxRetries; i++) {
            try {
                Response response = WmsApiRequests.getRequestTrackerLogs(requestId);
                
                if (response.getStatusCode() == 200) {
                    // Check if pickwave is created
                    List<Map<String, Object>> data = response.jsonPath().getList("data");
                    if (data != null && !data.isEmpty()) {
                        for (Map<String, Object> item : data) {
                            if ("PICKWAVE".equals(item.get("entity")) && "CREATED".equals(item.get("rec_state"))) {
                                System.out.println("✓ Pickwave created successfully");
                                return response;
                            }
                        }
                    }
                }
                
                if (i < maxRetries - 1) {
                    System.out.println("Waiting for pickwave creation... Attempt " + (i + 1) + "/" + maxRetries);
                    Thread.sleep(delayMs);
                }
            } catch (Exception e) {
                System.out.println("Error checking pickwave status: " + e.getMessage());
                if (i < maxRetries - 1) {
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        throw new RuntimeException("Pickwave creation status check failed after " + maxRetries + " attempts");
    }

    /**
     * Get Pickwave Filters and extract pickwave ID and picklist IDs
     */
    public static Response getPickwaveFilters(String shipmentNumber) {
        System.out.println("=== WMS Controller: Getting Pickwave Filters ===");
        System.out.println("Shipment Number: " + shipmentNumber);
        
        ConfigLoader config = ConfigLoader.getInstance();
        Response response = WmsApiRequests.getPickwaveFilters(
            "CREATED",
            shipmentNumber,
            0,
            config.getWmsFcUuid(),
            false
        );
        
        if (response.getStatusCode() == 200) {
            try {
                // Check if data array exists and is not empty
                List<Object> dataArray = response.jsonPath().getList("data");
                if (dataArray != null && !dataArray.isEmpty()) {
                    Integer pwId = response.jsonPath().getInt("data[0].id");
                    List<Integer> pIds = response.jsonPath().getList("data[0].pick_lists.id");
                    if (pwId != null && pIds != null) {
                        lastPickwaveId = pwId;
                        lastPickIds = pIds;
                        lastPicklistIds = pIds; // Also store as lastPicklistIds for consistency
                        System.out.println("Stored Pickwave ID: " + lastPickwaveId + ", Picklist IDs: " + lastPicklistIds);
                    } else {
                        System.out.println("Pickwave data found but IDs are null - pickwave may still be processing");
                    }
                } else {
                    System.out.println("No pickwave data found in response - pickwave may not be created yet");
                    // Log response for debugging
                    try {
                        String responseBody = response.getBody().asPrettyString();
                        System.out.println("Response preview: " + responseBody.substring(0, Math.min(500, responseBody.length())));
                    } catch (Exception e) {
                        // Ignore
                    }
                }
            } catch (Exception e) {
                System.out.println("Unable to parse pickwave/picklist IDs: " + e.getMessage());
                System.out.println("Response body: " + response.getBody().asPrettyString());
            }
        } else {
            System.out.println("Pickwave filters API returned status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody().asPrettyString());
        }
        
        return response;
    }

    /**
     * Assign Picklist to User
     */
    public static Response assignPicklistToUser(Integer pickwaveId, List<Integer> picklistIds, String username) {
        System.out.println("=== WMS Controller: Assigning Picklist to User ===");
        System.out.println("Pickwave ID: " + pickwaveId + ", Picklist IDs: " + picklistIds + ", User: " + username);
        
        AssignPicklistRequest request = AssignPicklistRequest.builder()
            .pickWaveId(pickwaveId)
            .pickIds(picklistIds)
            .assign(true)
            .user(username)
            .build();
        
        Response response = WmsApiRequests.assignPicklist(request);
        System.out.println("Assign Picklist Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Assign Pick Container to Picklist
     */
    public static Response assignPickContainer(int pickListId, String containerId) {
        System.out.println("=== WMS Controller: Assigning Pick Container ===");
        System.out.println("Picklist ID: " + pickListId + ", Container ID: " + containerId);
        
        Response response = WmsApiRequests.assignPicklistContainer(pickListId, containerId, false);
        System.out.println("Assign Container Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Update Container Item (Picking operation)
     */
    public static Response updateContainerItem(String clientUuid, String dlvSku, String scannableId, 
                                               String pickLocation, String pickContainer, int itemId, 
                                               int pickCount, String lotId, String bucket, String picklistId) {
        System.out.println("=== WMS Controller: Updating Container Item (Picking) ===");
        System.out.println("DlvSku: " + dlvSku + ", ScannableId: " + scannableId + ", PickContainer: " + pickContainer);
        
        ConfigLoader config = ConfigLoader.getInstance();
        UpdateContainerRequest request = UpdateContainerRequest.builder()
            .clientId(clientUuid)
            .dlvSku(dlvSku)
            .fulfillmentCenter(config.getWmsFcUuid())
            .itemContainer(pickLocation)
            .itemId(itemId)
            .itemPicked(pickCount)
            .movableContainer(pickContainer)
            .scannableId(scannableId)
            .lotId(lotId)
            .bucket(bucket != null ? bucket : "PRIME")
            .multiContainer(false)
            .build();
        
        Response response = WmsApiRequests.updateContainerItem(request, "item_id", picklistId);
        System.out.println("Update Container Item Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Complete Picklist
     */
    public static Response completePicklist(int picklistId, boolean multiContainer) {
        System.out.println("=== WMS Controller: Completing Picklist ===");
        System.out.println("Picklist ID: " + picklistId);
        
        CompletePicklistRequest request = CompletePicklistRequest.builder()
            .lost(false)
            .multiContainer(multiContainer)
            .build();
        
        Response response = WmsApiRequests.completePicklist(request, "pick_list_id", String.valueOf(picklistId));
        System.out.println("Complete Picklist Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Pack Initiate
     */
    public static Response packInitiate(String pickContainer) {
        System.out.println("=== WMS Controller: Pack Initiate ===");
        System.out.println("Pick Container: " + pickContainer);
        
        ConfigLoader config = ConfigLoader.getInstance();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("fulfillment_center_id", config.getWmsFcUuid());
        paramsMap.put("scan_type", "PACK_SCAN");
        
        Response response = WmsApiRequests.packInitiate(pickContainer, paramsMap);
        System.out.println("Pack Initiate Response Status: " + response.getStatusCode());
        return response;
    }


    /**
     * Complete Box
     */
    public static Response completeBox(String shipmentId, String pickContainer, String dlvSku, int qty, boolean siobFlag, String clientUuid) {
        System.out.println("=== WMS Controller: Completing Box ===");
        System.out.println("Shipment ID: " + shipmentId + ", Container: " + pickContainer + ", DlvSku: " + dlvSku + ", Qty: " + qty);
        
        ConfigLoader config = ConfigLoader.getInstance();
        CompleteBoxRequest.Container container = CompleteBoxRequest.Container.builder()
            .containerId(pickContainer)
            .product(CompleteBoxRequest.Product.builder()
                .productId(dlvSku)
                .quantity(qty)
                .siob(siobFlag)
                .sidList(null)
                .clientUuuid(clientUuid)
                .build())
            .build();
        
        CompleteBoxRequest request = CompleteBoxRequest.builder()
            .fulfillmentCenterId(config.getWmsFcUuid())
            .shipmentId(shipmentId)
            .containers(Collections.singletonList(container))
            .build();
        
        Response response = WmsApiRequests.completeBox(request);
        System.out.println("Complete Box Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Pack Shipment
     */
    public static Response packShipment(int shipmentId) {
        System.out.println("=== WMS Controller: Packing Shipment ===");
        System.out.println("Shipment ID: " + shipmentId);
        
        ConfigLoader config = ConfigLoader.getInstance();
        ShipmentPackRequest request = ShipmentPackRequest.builder()
            .shipmentId(shipmentId)
            .fulfillmentCenterId(config.getWmsFcUuid())
            .retry(false)
            .build();
        
        Response response = WmsApiRequests.packShipment(request);
        System.out.println("Pack Shipment Response Status: " + response.getStatusCode());
        
        if (response.getStatusCode() == 200) {
            // Extract waybills from response
            List<String> waybills = response.jsonPath().getList("data.boxes.wayBillId");
            if (waybills != null) {
                lastWaybills.addAll(waybills);
                System.out.println("Extracted Waybills: " + waybills);
            }
        }
        
        return response;
    }


    /**
     * Save Auto Dimensions (RTS)
     */
    public static Response saveAutoDimensions(String waybill, double length, double width, double height, double weight) {
        System.out.println("=== WMS Controller: Saving Auto Dimensions (RTS) ===");
        System.out.println("Waybill: " + waybill + ", Dimensions: " + length + "x" + width + "x" + height + ", Weight: " + weight);
        
        ConfigLoader config = ConfigLoader.getInstance();
        RtsRequest request = RtsRequest.builder()
            .length(length)
            .width(width)
            .height(height)
            .weight(weight)
            .waybillNumber(waybill)
            .fulfillmentCenterUuid(config.getWmsFcUuid())
            .courier("DELHIVERY")
            .barcodeIdentifier(waybill)
            .deviceId("AUTO_DEVICE")
            .vendorName("AUTO_VENDOR")
            .build();
        
        Response response = WmsApiRequests.saveAutoDimensions(request);
        System.out.println("Save Auto Dimensions Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Create Dispatch
     */
    public static String createDispatch(String clientUuid, String courier) {
        System.out.println("=== WMS Controller: Creating Dispatch ===");
        System.out.println("Client UUID: " + clientUuid + ", Courier: " + courier);
        
        ConfigLoader config = ConfigLoader.getInstance();
        CreateDispatchRequest request = CreateDispatchRequest.builder()
            .clientUuid(clientUuid)
            .courier(courier)
            .dispatchType("STANDARD")
            .courierType("STANDARD")
            .waybillCount(lastWaybills.size())
            .fulfillmentCenterUuid(config.getWmsFcUuid())
            .build();
        
        Response response = WmsApiRequests.createDispatch(request);
        System.out.println("Create Dispatch Response Status: " + response.getStatusCode());
        
        if (response.getStatusCode() == 200) {
            String dispatchNumber = response.jsonPath().getString("data[0].dispatch_number");
            lastDispatchNumber = dispatchNumber;
            System.out.println("Dispatch Number: " + dispatchNumber);
            return dispatchNumber;
        }
        
        throw new RuntimeException("Failed to create dispatch. Status: " + response.getStatusCode());
    }

    /**
     * Add Waybill to Dispatch
     */
    public static Response addWaybillToDispatch(String waybill, String dispatchNumber) {
        System.out.println("=== WMS Controller: Adding Waybill to Dispatch ===");
        System.out.println("Waybill: " + waybill + ", Dispatch: " + dispatchNumber);
        
        ConfigLoader config = ConfigLoader.getInstance();
        AddWaybillRequest request = AddWaybillRequest.builder()
            .waybillNumber(waybill)
            .fulfillmentCenterUuid(config.getWmsFcUuid())
            .dispatchNumber(dispatchNumber)
            .build();
        
        Response response = WmsApiRequests.addWaybill(request);
        System.out.println("Add Waybill Response Status: " + response.getStatusCode());
        return response;
    }

    /**
     * Complete Dispatch
     */
    public static Response completeDispatch(String dispatchNumber) {
        System.out.println("=== WMS Controller: Completing Dispatch ===");
        System.out.println("Dispatch Number: " + dispatchNumber);
        
        ConfigLoader config = ConfigLoader.getInstance();
        CompleteDispatchRequest request = CompleteDispatchRequest.builder()
            .dispatchNumber(dispatchNumber)
            .fulfillmentCenterUuid(config.getWmsFcUuid())
            .build();
        
        Response response = WmsApiRequests.completeDispatch(request);
        System.out.println("Complete Dispatch Response Status: " + response.getStatusCode());
        return response;
    }

    // Getters for stored values
    public static List<Integer> getLastPicklistIds() {
        return lastPicklistIds != null ? lastPicklistIds : lastPickIds;
    }

    public static List<String> getLastWaybills() {
        return lastWaybills;
    }

    public static String getLastDispatchNumber() {
        return lastDispatchNumber;
    }

    public static void setLastPickwaveRequestId(String requestId) {
        lastPickwaveRequestId = requestId;
    }
}
