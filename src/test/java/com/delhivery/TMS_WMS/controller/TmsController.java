package com.delhivery.TMS_WMS.controller;

import com.delhivery.TMS_WMS.applicationApi.TmsApiRequests;
import com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest;
import com.delhivery.TMS_WMS.pojo.tms.request.RouteDetails;
import com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderRequest;
import com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TMS Controller - Orchestrates TMS API calls
 */
public class TmsController {
    
    private static String lastUniqueCode = null;
    
    /**
     * Create Demand in TMS for a specific Order ID
     * @param demandAppToken The demand app token for authentication
     * @param orderId The WMS Order ID (e.g., wmstmsintltl371$706629)
     * @return CreateDemandResponse
     */
    public static CreateDemandResponse createDemand(String demandAppToken, String orderId) {
        System.out.println("=== TMS Controller: Creating Demand ===");
        System.out.println("Order ID: " + orderId);
        
        // Build Request
        CreateDemandRequest request = new CreateDemandRequest();
        request.setServiceType("LTL");
        request.setVehicleName(null);
        request.setExpDispatchAt(1766223937105L); // Using value from latest payload, ideally should be dynamic
        request.setOrderIds(Collections.singletonList(orderId));
        
        RouteDetails routeDetails = new RouteDetails();
        routeDetails.setOriginFacility("AUTOFC1");
        routeDetails.setDestinationFacility("DLV_ec0ebee9");
        routeDetails.setStops(Collections.emptyList());
        
        request.setRouteDetails(routeDetails);
        
        // Call API
        CreateDemandResponse response = TmsApiRequests.createDemand(demandAppToken, request);
        
        // Store Unique Code
        if (response.getData() != null && 
            response.getData().getRequest() != null && 
            response.getData().getRequest().getBody() != null &&
            response.getData().getRequest().getBody().getUniqueCode() != null) {
            
            lastUniqueCode = response.getData().getRequest().getBody().getUniqueCode();
            System.out.println("Stored Unique Code: " + lastUniqueCode);
        } else {
            System.err.println("Warning: Unique Code not found in response");
        }
        
        return response;
    }
    
    public static String getLastUniqueCode() {
        return lastUniqueCode;
    }
    
    // Store last created LTL order details
    private static String lastCreatedLtlOrderNumber = null;
    private static String lastCreatedLtlOrderId = null;
    private static String lastCreatedLtlLineItemNumber = null;
    private static String lastCreatedLtlInvoiceNumber = null;
    private static io.restassured.response.Response lastLtlOrderResponse = null;
    
    /**
     * Create LTL Order in TMS
     * @param orderAppToken The order app token
     * @return Response from the API
     */
    public static Response createLtlOrder(String orderAppToken) {
        System.out.println("=== TMS Controller: Creating LTL Order ===");
        
        ConfigLoader config = ConfigLoader.getInstance();
        
        // Generate dynamic order number and invoice number using epoch time
        long epochTime = System.currentTimeMillis();
        String orderNumber = "order-" + epochTime;
        String invoiceNumber = "INV" + epochTime;
        String customerReferenceNumber = "REF-" + epochTime;
        
        System.out.println("Generated Order Number: " + orderNumber);
        System.out.println("Generated Invoice Number: " + invoiceNumber);
        
        // Build Request
        TmsLtlOrderRequest request = new TmsLtlOrderRequest();
        request.setOrigin("AUTOFC1");
        request.setDestination("LTL1");
        request.setReadyToManifest(false);
        request.setIsPacked(false);
        request.setBoxCount(1);
        request.setOrderNumber(orderNumber);
        request.setSoldToFacility("DEL");
        request.setCustomerReferenceNumber(customerReferenceNumber);
        request.setOrderPriority("High");
        request.setClientId(config.getProperty("wms.order.default.clientId", "652b252bb7044c2e85310b654b1ef4cf"));
        
        // Invoice Details
        List<TmsLtlOrderRequest.InvoiceDetails> invoiceDetails = new ArrayList<>();
        TmsLtlOrderRequest.InvoiceDetails invoice = new TmsLtlOrderRequest.InvoiceDetails();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDt(epochTime);
        invoice.setInvoiceAmount(123.88);
        invoice.setIsDeleted(false);
        invoiceDetails.add(invoice);
        request.setInvoiceDetails(invoiceDetails);
        
        // Line Items
        List<TmsLtlOrderRequest.LineItem> lineItems = new ArrayList<>();
        TmsLtlOrderRequest.LineItem lineItem = new TmsLtlOrderRequest.LineItem();
        lineItem.setQuantity(1);
        String lineItemNumber = orderNumber; // lineItemNumber is same as orderNumber
        lineItem.setLineItemNumber(lineItemNumber);
        lineItem.setMeasurementUnit("box");
        
        // Volume
        TmsLtlOrderRequest.Volume volume = new TmsLtlOrderRequest.Volume();
        volume.setValue(5.080928);
        volume.setUnit("cft");
        lineItem.setVolume(volume);
        
        // Weight
        TmsLtlOrderRequest.Weight weight = new TmsLtlOrderRequest.Weight();
        weight.setValue(16.4);
        weight.setUnit("kgs");
        lineItem.setWeight(weight);
        
        // Product
        TmsLtlOrderRequest.Product product = new TmsLtlOrderRequest.Product();
        product.setCategory("wms");
        product.setCode("108562");
        product.setMrp(null);
        lineItem.setProduct(product);
        lineItem.setIsDeleted(false);
        lineItems.add(lineItem);
        request.setLineItems(lineItems);
        
        // Callback URL (can be from config or default)
        request.setCallbackUrl("https://webhook.site/52a7c883-cba0-45d4-a16a-46ca52127763");
        
        // Call API
        Response response = TmsApiRequests.createLtlOrder(orderAppToken, request);
        
        // Store order number (from request)
        lastCreatedLtlOrderNumber = orderNumber;
        System.out.println("Stored LTL Order Number: " + lastCreatedLtlOrderNumber);
        
        // Store line item number (from request payload)
        lastCreatedLtlLineItemNumber = lineItemNumber;
        System.out.println("Stored LTL Line Item Number: " + lastCreatedLtlLineItemNumber);
        
        // Store invoice number (from request payload)
        lastCreatedLtlInvoiceNumber = invoiceNumber;
        System.out.println("Stored LTL Invoice Number: " + lastCreatedLtlInvoiceNumber);
        
        // Store full response for later use
        lastLtlOrderResponse = response;
        
        // Extract and store order ID from response if available
        try {
            String orderId = response.jsonPath().getString("data.id");
            if (orderId != null && !orderId.isEmpty()) {
                lastCreatedLtlOrderId = orderId;
                System.out.println("Stored LTL Order ID: " + lastCreatedLtlOrderId);
            } else {
                // Try alternative paths
                orderId = response.jsonPath().getString("id");
                if (orderId != null && !orderId.isEmpty()) {
                    lastCreatedLtlOrderId = orderId;
                    System.out.println("Stored LTL Order ID (from root): " + lastCreatedLtlOrderId);
                } else {
                    // Try data.orderId
                    orderId = response.jsonPath().getString("data.orderId");
                    if (orderId != null && !orderId.isEmpty()) {
                        lastCreatedLtlOrderId = orderId;
                        System.out.println("Stored LTL Order ID (from data.orderId): " + lastCreatedLtlOrderId);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Could not extract order ID from response: " + e.getMessage());
            System.out.println("Response body: " + response.getBody().asPrettyString());
        }
        
        return response;
    }
    
    public static String getLastCreatedLtlOrderNumber() {
        return lastCreatedLtlOrderNumber;
    }
    
    public static String getLastCreatedLtlOrderId() {
        return lastCreatedLtlOrderId;
    }
    
    public static String getLastCreatedLtlLineItemNumber() {
        return lastCreatedLtlLineItemNumber;
    }
    
    public static String getLastCreatedLtlInvoiceNumber() {
        return lastCreatedLtlInvoiceNumber;
    }
    
    public static io.restassured.response.Response getLastLtlOrderResponse() {
        return lastLtlOrderResponse;
    }
    
    // Store last created LTL load/demand details
    private static String lastCreatedLtlLoadId = null;
    private static io.restassured.response.Response lastLtlLoadResponse = null;
    
    /**
     * Create LTL Load (Demand) in TMS
     * Uses the order ID from the previously created LTL order
     * @param orderId The order ID from the created LTL order
     * @return Response from the API
     */
    public static io.restassured.response.Response createLtlLoad(String orderId) {
        System.out.println("=== TMS Controller: Creating LTL Load (Demand) ===");
        System.out.println("Using Order ID: " + orderId);
        
        ConfigLoader config = ConfigLoader.getInstance();
        
        // Build Request
        CreateDemandRequest request = new CreateDemandRequest();
        request.setServiceType("LTL");
        request.setVehicleName(null);
        
        // Set expDispatchAt to future time (24 hours from now)
        long expDispatchAt = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
        request.setExpDispatchAt(expDispatchAt);
        
        // Use the order ID from the created order
        request.setOrderIds(Collections.singletonList(orderId));
        
        RouteDetails routeDetails = new RouteDetails();
        routeDetails.setOriginFacility("AUTOFC1");
        routeDetails.setDestinationFacility("LTL1");
        routeDetails.setStops(Collections.emptyList());
        
        request.setRouteDetails(routeDetails);
        
        // Call API (uses token from properties file)
        io.restassured.response.Response response = TmsApiRequests.createLtlLoad(request);
        
        // Store response
        lastLtlLoadResponse = response;
        
        // Extract and store unique code (load ID) from response
        // The unique code is at data.request.body.uniqueCode (e.g., "DLV-SGT68R")
        try {
            String uniqueCode = response.jsonPath().getString("data.request.body.uniqueCode");
            if (uniqueCode != null && !uniqueCode.isEmpty()) {
                lastCreatedLtlLoadId = uniqueCode;
                System.out.println("Stored LTL Load Unique Code (Load ID): " + lastCreatedLtlLoadId);
            } else {
                System.out.println("Warning: uniqueCode not found at data.request.body.uniqueCode");
                // Try alternative paths as fallback
                String loadId = response.jsonPath().getString("data.data.id");
                if (loadId != null && !loadId.isEmpty()) {
                    lastCreatedLtlLoadId = loadId;
                    System.out.println("Stored LTL Load ID (from data.data.id): " + lastCreatedLtlLoadId);
                } else {
                    loadId = response.jsonPath().getString("data.id");
                    if (loadId != null && !loadId.isEmpty()) {
                        lastCreatedLtlLoadId = loadId;
                        System.out.println("Stored LTL Load ID (from data.id): " + lastCreatedLtlLoadId);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Could not extract load unique code from response: " + e.getMessage());
            System.out.println("Response body: " + response.getBody().asPrettyString());
        }
        
        return response;
    }
    
    public static String getLastCreatedLtlLoadId() {
        return lastCreatedLtlLoadId;
    }
    
    public static io.restassured.response.Response getLastLtlLoadResponse() {
        return lastLtlLoadResponse;
    }
    
    /**
     * Update LTL Order in TMS
     * Uses stored order number, invoice number, and line item number
     * @param orderAppToken The order app token
     * @return Response from the API
     */
    public static io.restassured.response.Response updateLtlOrder(String orderAppToken) {
        System.out.println("=== TMS Controller: Updating LTL Order ===");
        
        if (lastCreatedLtlOrderNumber == null) {
            throw new IllegalStateException("No LTL order number stored. Create order first.");
        }
        if (lastCreatedLtlInvoiceNumber == null) {
            throw new IllegalStateException("No LTL invoice number stored. Create order first.");
        }
        if (lastCreatedLtlLineItemNumber == null) {
            throw new IllegalStateException("No LTL line item number stored. Create order first.");
        }
        
        System.out.println("Using Order Number: " + lastCreatedLtlOrderNumber);
        System.out.println("Using Invoice Number: " + lastCreatedLtlInvoiceNumber);
        System.out.println("Using Line Item Number: " + lastCreatedLtlLineItemNumber);
        
        ConfigLoader config = ConfigLoader.getInstance();
        
        // Build Update Request
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest request = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest();
        
        request.setReadyToManifest(true);
        request.setIsPacked(true);
        request.setBoxCount(1);
        
        // Box Dimension
        List<com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.BoxDimension> boxDimensions = new ArrayList<>();
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.BoxDimension boxDim = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.BoxDimension();
        boxDim.setLength(19);
        boxDim.setBreadth(10);
        boxDim.setHeight(1);
        boxDim.setWeight(43);
        boxDim.setQuantity(1);
        boxDim.setWeightUnit("24");
        boxDim.setBoxId(String.valueOf(System.currentTimeMillis())); // Generate unique box ID
        boxDimensions.add(boxDim);
        request.setBoxDimension(boxDimensions);
        
        // Use stored order number
        request.setOrderNumber(lastCreatedLtlOrderNumber);
        request.setOrderPriority("High");
        request.setSoldToFacility("DEL");
        request.setCustomerReferenceNumber("REF-" + System.currentTimeMillis());
        request.setStatus("assigned");
        request.setIsDeleted(false);
        request.setClientId(config.getProperty("wms.order.default.clientId", "652b252bb7044c2e85310b654b1ef4cf"));
        
        // Invoice Details - use stored invoice number
        List<com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.InvoiceDetails> invoiceDetails = new ArrayList<>();
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.InvoiceDetails invoice = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.InvoiceDetails();
        invoice.setInvoiceNumber(lastCreatedLtlInvoiceNumber);
        invoice.setInvoiceDt(System.currentTimeMillis());
        invoice.setInvoiceAmount(123.88);
        invoice.setIsDeleted(false);
        invoiceDetails.add(invoice);
        request.setInvoiceDetails(invoiceDetails);
        
        // Line Items - use stored line item number
        List<com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.LineItem> lineItems = new ArrayList<>();
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.LineItem lineItem = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.LineItem();
        lineItem.setQuantity(1);
        lineItem.setLineItemNumber(lastCreatedLtlLineItemNumber); // Use stored line item number
        lineItem.setMeasurementUnit("box");
        
        // Volume
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.Volume volume = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.Volume();
        volume.setValue(5.080928);
        volume.setUnit("cft");
        lineItem.setVolume(volume);
        
        // Weight
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.Weight weight = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.Weight();
        weight.setValue(16.4);
        weight.setUnit("kgs");
        lineItem.setWeight(weight);
        
        // Product
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.Product product = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.Product();
        product.setCategory("wms");
        product.setCode("108562");
        product.setMrp(null);
        lineItem.setProduct(product);
        
        // Additional fields for update
        lineItem.setStatus("assigned");
        lineItem.setIsDeleted(false);
        lineItem.setIsPacked(true);
        lineItem.setReadyToManifest(true);
        lineItem.setBoxCount(1);
        
        // Invoice Details in line item - use stored invoice number
        List<com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.InvoiceDetails> lineItemInvoiceDetails = new ArrayList<>();
        com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.InvoiceDetails lineItemInvoice = 
            new com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest.InvoiceDetails();
        lineItemInvoice.setInvoiceNumber(lastCreatedLtlInvoiceNumber); // Use stored invoice number
        lineItemInvoice.setInvoiceDt(System.currentTimeMillis());
        lineItemInvoice.setInvoiceAmount(123.88);
        lineItemInvoice.setIsDeleted(false);
        lineItemInvoiceDetails.add(lineItemInvoice);
        lineItem.setInvoiceDetails(lineItemInvoiceDetails);
        
        lineItems.add(lineItem);
        request.setLineItems(lineItems);
        
        // Callback URL
        request.setCallbackUrl("https://webhook.site/dd3bca91-2a32-4ca5-a32b-bcdded784ba5");
        
        // Call API
        io.restassured.response.Response response = TmsApiRequests.updateLtlOrder(orderAppToken, lastCreatedLtlOrderNumber, request);
        
        return response;
    }
    
    /**
     * Get Indents by Load ID
     * Uses demand app token for authentication
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @param loadId The load ID to get indents for (e.g., DLV-SD283H)
     * @return Response from the API
     */
    public static io.restassured.response.Response getIndentsByLoadId(String demandAppToken, String loadId) {
        System.out.println("=== TMS Controller: Getting Indents by Load ID ===");
        System.out.println("Load ID: " + loadId);
        System.out.println("Using Demand App Token (first 50 chars): " + demandAppToken.substring(0, Math.min(50, demandAppToken.length())) + "...");
        
        // Call API
        io.restassured.response.Response response = TmsApiRequests.getIndentsByLoadId(demandAppToken, loadId);
        
        return response;
    }
    
    /**
     * Get Indent Detail by Unique Code
     * Uses demand app token for authentication
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @param uniqueCode The indent unique code (indent ID)
     * @return Response from the API
     */
    public static io.restassured.response.Response getIndentDetail(String demandAppToken, String uniqueCode) {
        System.out.println("=== TMS Controller: Getting Indent Detail ===");
        System.out.println("Indent Unique Code: " + uniqueCode);
        System.out.println("Using Demand App Token (first 50 chars): " + demandAppToken.substring(0, Math.min(50, demandAppToken.length())) + "...");
        
        // Call API
        io.restassured.response.Response response = TmsApiRequests.getIndentDetail(demandAppToken, uniqueCode);
        
        return response;
    }
    
    /**
     * Retry Failed Manifestation
     * Uses demand app token for authentication
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @return Response from the API
     */
    public static io.restassured.response.Response retryFailedManifestation(String demandAppToken) {
        System.out.println("=== TMS Controller: Retrying Failed Manifestation ===");
        System.out.println("Using Demand App Token (first 50 chars): " + demandAppToken.substring(0, Math.min(50, demandAppToken.length())) + "...");
        
        // Call API
        io.restassured.response.Response response = TmsApiRequests.retryFailedManifestation(demandAppToken);
        
        return response;
    }
}
