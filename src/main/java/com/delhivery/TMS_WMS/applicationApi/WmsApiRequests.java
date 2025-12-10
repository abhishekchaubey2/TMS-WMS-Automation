package com.delhivery.TMS_WMS.applicationApi;

import com.delhivery.TMS_WMS.api.WmsAuthApi;
import com.delhivery.TMS_WMS.pojo.picklist.response.GetPicklistResponse;
import com.delhivery.TMS_WMS.pojo.wmsorder.request.CreateOrderRequest;
import com.delhivery.TMS_WMS.pojo.wmsorder.response.CreateOrderResponse;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * WMS Application API - Similar to Express PackageFlowRequests
 * Contains all WMS API endpoint methods
 */
public class WmsApiRequests {
    
    // Route constants
    private static final String CREATE_ORDER = "/wms/qa2/order-management/order/create";
    private static final String CREATE_PICKWAVE = "/wms/qa2/pick/pickwave/create";
    private static final String REQUEST_TRACKER_LOGS = "/wms/qa2/request-tracker/logs";
    private static final String PICKWAVE_FILTERS_V2 = "/wms/qa2/pick/pickwave/filters/v2";
    private static final String GET_PICKLIST = "/wms/qa2/pick/get/picklist";
    private static final String ASSIGN_PICKLIST_CONTAINER = "/wms/qa2/pick/picklist/assign/container";
    private static final String CREATE_CONTAINER = "/wms/qa2/fulfillment-inventory-management/container/create";
    
    /**
     * Create WMS Order
     * @param request CreateOrderRequest payload
     * @return Response from WMS API
     */
    public static Response createOrder(CreateOrderRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();
        
        System.out.println("=== WMS Create Order API Call ===");
        System.out.println("Endpoint: " + CREATE_ORDER);
        System.out.println("Token: " + wmsToken.substring(0, Math.min(20, wmsToken.length())) + "...");
        
        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .header("Accept", "*/*")
                .body(request)
                .when()
                .post(CREATE_ORDER)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Create WMS Order and return response object
     * @param request CreateOrderRequest payload
     * @return CreateOrderResponse object
     */
    public static CreateOrderResponse createOrderAndGetResponse(CreateOrderRequest request) {
        Response response = createOrder(request);
        
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response.as(CreateOrderResponse.class);
    }

    /**
     * Create Pickwave in WMS for a given shipment id
     * Mirrors the provided curl while using framework standards (WmsAuthApi + ConfigLoader)
     *
     * curl -X POST "https://qa2-api-wms.delhivery.com/wms/qa2/pick/pickwave/create"
     *   -H "Content-Type: application/json"
     *   -H "fc-uuid: 28d521fc52bf47e08f59c162dfc883e5"
     *   -H "User-Uuid: UMSAU002"
     *   -H "Authorization: Bearer <TOKEN>"
     *   -d '{"create_by":"shipments","fc":"28d521fc52bf47e08f59c162dfc883e5","shipment_count":1,
     *        "filters":{"shipment_id":[706818]},"workflow":"31","created_by":"autouser"}'
     *
     * @param shipmentId numeric shipment id (part after '$' in orderID)
     * @return raw Response from WMS API
     */
    public static Response createPickwave(int shipmentId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Create Pickwave API Call ===");
        System.out.println("Endpoint: " + CREATE_PICKWAVE);
        System.out.println("Shipment ID: " + shipmentId);

        // Build request body as per curl
        Map<String, Object> body = new HashMap<>();
        body.put("create_by", "shipments");
        body.put("fc", config.getWmsFcUuid());
        body.put("shipment_count", 1);

        Map<String, Object> filters = new HashMap<>();
        filters.put("shipment_id", Collections.singletonList(shipmentId));
        body.put("filters", filters);

        body.put("workflow", "31");
        body.put("created_by", "autouser");

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(body)
                .when()
                .post(CREATE_PICKWAVE)
                .then()
                .extract()
                .response();
    }

    /**
     * Get WMS request tracker logs for a given request_id
     *
     * Mirrors:
     * curl -G "https://qa2-api-wms.delhivery.com/wms/qa2/request-tracker/logs"
     *   -H "Content-Type: application/json"
     *   -H "fc-uuid: <fc>"
     *   -H "User-Uuid: <user>"
     *   -H "Authorization: Bearer <TOKEN>"
     *   --data-urlencode "request_id=<REQUEST_ID>"
     *
     * @param requestId the pickwave request_id
     * @return raw Response from WMS API
     */
    public static Response getRequestTrackerLogs(String requestId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Request Tracker Logs API Call ===");
        System.out.println("Endpoint: " + REQUEST_TRACKER_LOGS);
        System.out.println("Request ID: " + requestId);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("request_id", requestId)
                .when()
                .get(REQUEST_TRACKER_LOGS)
                .then()
                .extract()
                .response();
    }

    /**
     * Get Pickwave filters (v2) for a given shipment/order number
     *
     * Mirrors:
     * curl -G "https://qa2-api-wms.delhivery.com/wms/qa2/pick/pickwave/filters/v2"
     *   -H "Content-Type: application/json"
     *   -H "fc-uuid: <fc>"
     *   -H "User-Uuid: <user>"
     *   -H "Authorization: Bearer <TOKEN>"
     *   --data-urlencode "status=CREATED"
     *   --data-urlencode "shipmentNumber=<shipmentNumber>"
     *   --data-urlencode "page=0"
     *   --data-urlencode "fulfillmentCenter=<fc>"
     *   --data-urlencode "multi_container=false"
     *
     * @param status            pickwave status filter (e.g. CREATED)
     * @param shipmentNumber    WMS shipment/order number (e.g. wmstmsintltl575)
     * @param page              page number
     * @param fulfillmentCenter fc uuid
     * @param multiContainer    multi_container flag
     * @return raw Response from WMS API
     */
    public static Response getPickwaveFilters(String status,
                                              String shipmentNumber,
                                              int page,
                                              String fulfillmentCenter,
                                              boolean multiContainer) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Pickwave Filters v2 API Call ===");
        System.out.println("Endpoint: " + PICKWAVE_FILTERS_V2);
        System.out.println("Status: " + status + ", ShipmentNumber: " + shipmentNumber);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("status", status)
                .queryParam("shipmentNumber", shipmentNumber)
                .queryParam("page", page)
                .queryParam("fulfillmentCenter", fulfillmentCenter)
                .queryParam("multi_container", multiContainer)
                .when()
                .get(PICKWAVE_FILTERS_V2)
                .then()
                .extract()
                .response();
    }

    /**
     * Assign a movable container to a picklist
     *
     * Mirrors:
     * curl -X PUT "https://qa2-api-wms.delhivery.com/wms/qa2/pick/picklist/assign/container?pick_list_id=270853"
     *   -H "Content-Type: application/json"
     *   -H "fc-uuid: <fc>"
     *   -H "User-Uuid: <user>"
     *   -H "Authorization: Bearer <TOKEN>"
     *   -d '{"movable_container":"container_706818_auto","multi_container":false}'
     *
     * @param pickListId        pick_list_id query param
     * @param movableContainer  container name (e.g. container_706818_auto)
     * @param multiContainer    multi_container flag
     * @return raw Response from WMS API
     */
    public static Response assignPicklistContainer(int pickListId,
                                                   String movableContainer,
                                                   boolean multiContainer) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Assign Picklist Container API Call ===");
        System.out.println("Endpoint: " + ASSIGN_PICKLIST_CONTAINER);
        System.out.println("pick_list_id: " + pickListId + ", movable_container: " + movableContainer);

        Map<String, Object> body = new HashMap<>();
        body.put("movable_container", movableContainer);
        body.put("multi_container", multiContainer);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("pick_list_id", pickListId)
                .body(body)
                .when()
                .put(ASSIGN_PICKLIST_CONTAINER)
                .then()
                .extract()
                .response();
    }

    /**
     * Create a WMS container
     *
     * Mirrors:
     * curl -X POST "https://qa2-api-wms.delhivery.com/wms/qa2/fulfillment-inventory-management/container/create"
     *   -H "Content-Type: application/json"
     *   -H "fc-uuid: <fc>"
     *   -H "User-Uuid: <user>"
     *   -H "Authorization: Bearer <TOKEN>"
     *   -d '{...container payload...}'
     *
     * @param body fully built container create payload (as Map or POJO)
     * @return raw Response from WMS API
     */
    public static Response createContainer(Object body) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Create Container API Call ===");
        System.out.println("Endpoint: " + CREATE_CONTAINER);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(body)
                .when()
                .post(CREATE_CONTAINER)
                .then()
                .extract()
                .response();
    }

    /**
     * Get Picklist details for a given pick id.
     *
     * Mirrors:
     * GET /wms/qa2/pick/get/picklist?pick_id=270853&multi_container=false
     *
     * @param pickId         pick_id query param
     * @param multiContainer multi_container flag
     * @return raw Response from WMS API
     */
    public static Response getPicklist(int pickId, boolean multiContainer) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Get Picklist API Call ===");
        System.out.println("Endpoint: " + GET_PICKLIST);
        System.out.println("pick_id: " + pickId + ", multi_container: " + multiContainer);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("pick_id", pickId)
                .queryParam("multi_container", multiContainer)
                .when()
                .get(GET_PICKLIST)
                .then()
                .extract()
                .response();
    }
}
