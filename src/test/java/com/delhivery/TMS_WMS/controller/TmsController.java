package com.delhivery.TMS_WMS.controller;

import com.delhivery.TMS_WMS.applicationApi.TmsApiRequests;
import com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest;
import com.delhivery.TMS_WMS.pojo.tms.request.RouteDetails;
import com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse;

import java.util.Collections;

/**
 * TMS Controller - Orchestrates TMS API calls
 */
public class TmsController {
    
    private static String lastUniqueCode = null;
    
    /**
     * Create Demand in TMS for a specific Order ID
     * @param orderId The WMS Order ID (e.g., wmstmsintltl371$706629)
     * @return CreateDemandResponse
     */
    public static CreateDemandResponse createDemand(String orderId) {
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
        CreateDemandResponse response = TmsApiRequests.createDemand(request);
        
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
}
