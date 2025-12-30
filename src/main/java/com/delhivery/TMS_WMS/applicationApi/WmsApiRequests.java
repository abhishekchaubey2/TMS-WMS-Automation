package com.delhivery.TMS_WMS.applicationApi;

import com.delhivery.TMS_WMS.api.WmsAuthApi;
import com.delhivery.TMS_WMS.pojo.picklist.response.GetPicklistResponse;
import com.delhivery.TMS_WMS.pojo.wmsorder.request.CreateOrderRequest;
import com.delhivery.TMS_WMS.pojo.wmsorder.response.CreateOrderResponse;
import com.delhivery.TMS_WMS.pojo.wmsoutbound.request.*;
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
    private static final String UPDATE_FC_CONFIG = "/wms/qa2/fc/update/config";
    private static final String CREATE_PICKWAVE = "/wms/qa2/pick/pickwave/create";
    private static final String CREATE_PICKWAVE_PCA = "/wms/qa2/pick/pickwave/create-pickwave-pca-algorithm";
    private static final String REQUEST_TRACKER_LOGS = "/wms/qa2/request-tracker/logs";
    private static final String PICKWAVE_FILTERS_V2 = "/wms/qa2/pick/pickwave/filters/v2";
    private static final String GET_PICKLIST = "/wms/qa2/pick/get/picklist";
    private static final String ASSIGN_PICKLIST_CONTAINER = "/wms/qa2/pick/picklist/assign/container";
    private static final String CREATE_CONTAINER = "/wms/qa2/fulfillment-inventory-management/container/create";
    private static final String ASSIGN_PICKLIST_USER = "/wms/qa2/pick/picklist/assign/user/";
    private static final String UPDATE_CONTAINER_ITEM = "/wms/qa2/pick/picklist/update/container/item";
    private static final String PICKLIST_COMPLETE = "/wms/qa2/pick/picklist/complete";
    private static final String PACK_INITIATE = "/wms/qa2/pack/initiate";
    private static final String FIM_CONTAINER_DETAIL = "/wms/qa2/fulfillment-inventory-management/container/detail";
    private static final String PACK_COMPLETE_BOX = "/wms/qa2/pack/complete-box";
    private static final String PACK_SHIPMENT_PACK = "/wms/qa2/pack/shipment-pack";
    private static final String RTS_FETCH_AUTO_DIMENSIONS = "/wms/qa2/rts-dispatch/fetch-auto-dimensions";
    private static final String SAVE_AUTO_DIMENSIONS = "/wms/qa2/rts-dispatch/save-auto-dimensions";
    private static final String CREATE_DISPATCH = "/wms/qa2/rts-dispatch/create-dispatch";
    private static final String ADD_WAYBILL = "/wms/qa2/rts-dispatch/add-waybill";
    private static final String COMPLETE_DISPATCH = "/wms/qa2/rts-dispatch/complete-dispatch";
    
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
     * Update outbound configuration for the WMS FC.
     * Mirrors the fc/update/config curl used in Outbound_Normal_Flows_QA2.
     */
    public static Response updateOutboundFcConfig() {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Update FC Outbound Config API Call ===");
        System.out.println("Endpoint: " + UPDATE_FC_CONFIG);

        Map<String, Object> pickImprovements = new HashMap<>();
        pickImprovements.put("enabled", true);

        Map<String, Object> pickImprovementsV2 = new HashMap<>();
        pickImprovementsV2.put("enabled", true);

        Map<String, Object> configData = new HashMap<>();
        configData.put("pickImprovements", pickImprovements);
        configData.put("pickImprovementsV2", pickImprovementsV2);
        configData.put("autoAddDispatch", false);
        configData.put("oneShipmentOneDispatch", false);
        configData.put("oneCourierOneDispatch", false);
        configData.put("autoCompleteDispatch", false);
        configData.put("dropzoneEnabled", false);
        configData.put("multiContainer", true);
        configData.put("exclusiveCaseEnabledClients",
                java.util.Collections.singletonList(config.getWmsClientUuid()));

        Map<String, Object> body = new HashMap<>();
        body.put("configType", "outbound");
        body.put("fcUuid", config.getWmsFcUuid());
        body.put("configData", configData);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("Accept", "*/*")
                .body(body)
                .when()
                .put(UPDATE_FC_CONFIG)
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
     * Create Pickwave using PCA Algorithm in WMS for a given shipment id
     * Uses the new PCA algorithm endpoint
     *
     * curl -X POST "https://qa2-api-wms.delhivery.com/wms/qa2/pick/pickwave/create-pickwave-pca-algorithm"
     *   -H "Content-Type: application/json"
     *   -H "fc-uuid: db313a0b5c6942a2b7bf81cbf0639f52"
     *   -H "User-Uuid: UMSAU002"
     *   -H "Authorization: Bearer <TOKEN>"
     *   -d '{"create_by":"shipments","fc":"db313a0b5c6942a2b7bf81cbf0639f52","shipment_count":1,
     *        "workflow":"31","created_by":"autouser","shipment_id":[707381]}'
     *
     * @param shipmentId numeric shipment id (part after '$' in orderID)
     * @return raw Response from WMS API
     */
    public static Response createPickwavePCA(int shipmentId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Create Pickwave PCA Algorithm API Call ===");
        System.out.println("Endpoint: " + CREATE_PICKWAVE_PCA);
        System.out.println("Shipment ID: " + shipmentId);

        // Build request body as per curl - shipment_id is directly in body, not in filters
        Map<String, Object> body = new HashMap<>();
        body.put("create_by", "shipments");
        body.put("fc", config.getWmsFcUuid());
        body.put("shipment_count", 1);
        body.put("workflow", "31");
        body.put("created_by", "autouser");
        body.put("shipment_id", Collections.singletonList(shipmentId));

        return given()
                .baseUri(config.getWmsBaseUrl())
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
                .header("Authorization", "Bearer " + wmsToken)
                .header("Content-Type", "application/json")
                .header("Origin", "https://qa2-wms.delhivery.com")
                .header("Referer", "https://qa2-wms.delhivery.com/")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
                .header("User-Uuid", config.getWmsUserUuid())
                .header("fc-uuid", config.getWmsFcUuid())
                .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Linux\"")
                .body(body)
                .log().all()
                .when()
                .post(CREATE_PICKWAVE_PCA)
                .then()
                .log().all()
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

    /**
     * Assign picklist to a user.
     *
     * Mirrors:
     * PUT /wms/qa2/pick/picklist/assign/user/
     * Payload:
     * {
     *   "picklist_ids": ["<picklistId>"],
     *   "user_uuid": "<userUuid>",
     *   "fulfillment_center_uuid": "<fcUuid>"
     * }
     */
    public static Response assignPicklistToUser(int pickListId, String userUuid, String fcUuid) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Assign Picklist To User API Call ===");
        System.out.println("Endpoint: " + ASSIGN_PICKLIST_USER);
        System.out.println("picklist_id: " + pickListId + ", user_uuid: " + userUuid);

        Map<String, Object> body = new HashMap<>();
        body.put("picklist_ids", java.util.Collections.singletonList(String.valueOf(pickListId)));
        body.put("user_uuid", userUuid);
        body.put("fulfillment_center_uuid", fcUuid);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(body)
                .when()
                .put(ASSIGN_PICKLIST_USER)
                .then()
                .extract()
                .response();
    }

    /**
     * Assign Picklist to User (POJO-based version)
     */
    public static Response assignPicklist(AssignPicklistRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Assign Picklist to User API Call ===");
        System.out.println("Endpoint: " + ASSIGN_PICKLIST_USER);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .put(ASSIGN_PICKLIST_USER)
                .then()
                .extract()
                .response();
    }

    /**
     * Update picklist container item (perform pick into movable container).
     *
     * Mirrors:
     * PUT /wms/qa2/pick/picklist/update/container/item?item_id=...
     */
    public static Response updatePicklistContainerItem(int itemId, Map<String, Object> body) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Update Picklist Container Item API Call ===");
        System.out.println("Endpoint: " + UPDATE_CONTAINER_ITEM);
        System.out.println("item_id: " + itemId);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("item_id", itemId)
                .body(body)
                .when()
                .put(UPDATE_CONTAINER_ITEM)
                .then()
                .extract()
                .response();
    }

    /**
     * Update Container Item (Picking) - POJO-based version
     */
    public static Response updateContainerItem(UpdateContainerRequest request, String paramKey, String paramValue) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Update Container Item API Call ===");
        System.out.println("Endpoint: " + UPDATE_CONTAINER_ITEM);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam(paramKey, paramValue)
                .body(request)
                .when()
                .put(UPDATE_CONTAINER_ITEM)
                .then()
                .extract()
                .response();
    }

    /**
     * Complete a picklist.
     *
     * Mirrors:
     * PUT /wms/qa2/pick/picklist/complete?pick_list_id=...
     */
    public static Response completePicklist(int pickListId, boolean lost, boolean multiContainer) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Complete Picklist API Call ===");
        System.out.println("Endpoint: " + PICKLIST_COMPLETE);
        System.out.println("pick_list_id: " + pickListId);

        Map<String, Object> body = new HashMap<>();
        body.put("lost", lost);
        body.put("multiContainer", multiContainer);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("pick_list_id", pickListId)
                .body(body)
                .when()
                .put(PICKLIST_COMPLETE)
                .then()
                .extract()
                .response();
    }

    /**
     * Complete Picklist - POJO-based version
     */
    public static Response completePicklist(CompletePicklistRequest request, String paramKey, String paramValue) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Complete Picklist API Call ===");
        System.out.println("Endpoint: " + PICKLIST_COMPLETE);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam(paramKey, paramValue)
                .body(request)
                .when()
                .put(PICKLIST_COMPLETE)
                .then()
                .extract()
                .response();
    }

    /**
     * Initiate pack for a given container id.
     *
     * Mirrors:
     * GET /wms/qa2/pack/initiate/{containerId}
     */
    public static Response initiatePack(String containerId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Pack Initiate API Call ===");
        System.out.println("Endpoint: " + PACK_INITIATE + "/" + containerId);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("scan_type", "PACK_SCAN")
                .queryParam("fulfillment_center_id", config.getWmsFcUuid())
                .when()
                .get(PACK_INITIATE + "/" + containerId)
                .then()
                .extract()
                .response();
    }

    /**
     * Pack Initiate - POJO-based version
     */
    public static Response packInitiate(String pickContainer, Map<String, String> paramsMap) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Pack Initiate API Call ===");
        System.out.println("Endpoint: " + PACK_INITIATE + pickContainer);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .params(paramsMap)
                .when()
                .get(PACK_INITIATE + pickContainer)
                .then()
                .extract()
                .response();
    }

    /**
     * Get FIM container detail for a given container name.
     *
     * Mirrors:
     * GET /wms/qa2/fulfillment-inventory-management/container/detail?name=...
     */
    public static Response getFimContainerDetail(String containerId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS FIM Container Detail API Call ===");
        System.out.println("Endpoint: " + FIM_CONTAINER_DETAIL);
        System.out.println("name: " + containerId);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("name", containerId)
                .when()
                .get(FIM_CONTAINER_DETAIL)
                .then()
                .extract()
                .response();
    }

    /**
     * Get FIM Container Detail - POJO-based version
     */
    public static Response getFimContainerDetail(String containerId, Map<String, String> paramsMap) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Get FIM Container Detail API Call ===");
        System.out.println("Endpoint: " + FIM_CONTAINER_DETAIL + containerId);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .params(paramsMap)
                .when()
                .get(FIM_CONTAINER_DETAIL + containerId)
                .then()
                .extract()
                .response();
    }

    /**
     * Complete box for pack.
     *
     * Mirrors:
     * POST /wms/qa2/pack/complete-box
     */
    public static Response completeBox(Object body) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Pack Complete Box API Call ===");
        System.out.println("Endpoint: " + PACK_COMPLETE_BOX);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(body)
                .when()
                .post(PACK_COMPLETE_BOX)
                .then()
                .extract()
                .response();
    }

    /**
     * Complete Box - POJO-based version
     */
    public static Response completeBox(CompleteBoxRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Complete Box API Call ===");
        System.out.println("Endpoint: " + PACK_COMPLETE_BOX);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .post(PACK_COMPLETE_BOX)
                .then()
                .extract()
                .response();
    }

    /**
     * Pack shipment API.
     *
     * Mirrors:
     * POST /wms/qa2/pack/shipment-pack
     */
    public static Response packShipment(long shipmentId) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Pack Shipment API Call ===");
        System.out.println("Endpoint: " + PACK_SHIPMENT_PACK);
        System.out.println("shipment_id: " + shipmentId);

        Map<String, Object> body = new HashMap<>();
        body.put("shipment_id", shipmentId);
        body.put("fulfillment_center_id", config.getWmsFcUuid());
        body.put("retry", false);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(body)
                .when()
                .post(PACK_SHIPMENT_PACK)
                .then()
                .extract()
                .response();
    }

    /**
     * Pack Shipment - POJO-based version
     */
    public static Response packShipment(ShipmentPackRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Pack Shipment API Call ===");
        System.out.println("Endpoint: " + PACK_SHIPMENT_PACK);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .post(PACK_SHIPMENT_PACK)
                .then()
                .extract()
                .response();
    }

    /**
     * Fetch auto dimensions at RTS for a given barcode/waybill.
     *
     * Mirrors:
     * GET /wms/qa2/rts-dispatch/fetch-auto-dimensions?barcode_identifier=...
     */
    public static Response fetchAutoDimensions(String barcodeIdentifier) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Fetch Auto Dimensions API Call ===");
        System.out.println("Endpoint: " + RTS_FETCH_AUTO_DIMENSIONS);
        System.out.println("barcode_identifier: " + barcodeIdentifier);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .queryParam("barcode_identifier", barcodeIdentifier)
                .when()
                .get(RTS_FETCH_AUTO_DIMENSIONS)
                .then()
                .extract()
                .response();
    }

    /**
     * Fetch Auto Dimensions at RTS - POJO-based version
     */
    public static Response fetchAutoDimensions(Map<String, String> paramsMap) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Fetch Auto Dimensions API Call ===");
        System.out.println("Endpoint: " + RTS_FETCH_AUTO_DIMENSIONS);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .params(paramsMap)
                .when()
                .get(RTS_FETCH_AUTO_DIMENSIONS)
                .then()
                .extract()
                .response();
    }

    /**
     * Save Auto Dimensions (RTS)
     */
    public static Response saveAutoDimensions(RtsRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Save Auto Dimensions API Call ===");
        System.out.println("Endpoint: " + SAVE_AUTO_DIMENSIONS);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .post(SAVE_AUTO_DIMENSIONS)
                .then()
                .extract()
                .response();
    }

    /**
     * Create Dispatch
     */
    public static Response createDispatch(CreateDispatchRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Create Dispatch API Call ===");
        System.out.println("Endpoint: " + CREATE_DISPATCH);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .post(CREATE_DISPATCH)
                .then()
                .extract()
                .response();
    }

    /**
     * Add Waybill to Dispatch
     */
    public static Response addWaybill(AddWaybillRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Add Waybill to Dispatch API Call ===");
        System.out.println("Endpoint: " + ADD_WAYBILL);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .post(ADD_WAYBILL)
                .then()
                .extract()
                .response();
    }

    /**
     * Complete Dispatch
     */
    public static Response completeDispatch(CompleteDispatchRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();

        System.out.println("=== WMS Complete Dispatch API Call ===");
        System.out.println("Endpoint: " + COMPLETE_DISPATCH);

        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + wmsToken)
                .header("fc-uuid", config.getWmsFcUuid())
                .header("User-Uuid", config.getWmsUserUuid())
                .body(request)
                .when()
                .post(COMPLETE_DISPATCH)
                .then()
                .extract()
                .response();
    }
}
