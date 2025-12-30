package com.delhivery.TMS_WMS.applicationApi;

import com.delhivery.TMS_WMS.api.WmsAuthApi;
import com.delhivery.TMS_WMS.pojo.ftlorder.request.CreateFTLOrderRequest;
import com.delhivery.TMS_WMS.pojo.ftlorder.response.CreateFTLOrderResponse;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * FTL Application API - Similar to Express PackageFlowRequests and WmsApiRequests
 * Contains all FTL API endpoint methods
 */
public class FTLApiRequests {
    
    // Route constants
    private static final String CREATE_FTL_ORDER = "/wms/qa2/order-management/order/create";
    
    /**
     * Create FTL Order
     * @param request CreateFTLOrderRequest payload
     * @return Response from WMS API
     */
    public static Response createFTLOrder(CreateFTLOrderRequest request) {
        ConfigLoader config = ConfigLoader.getInstance();
        String wmsToken = WmsAuthApi.getAccessToken();
        
        System.out.println("=== FTL Create Order API Call ===");
        System.out.println("Endpoint: " + CREATE_FTL_ORDER);
        System.out.println("Token: " + wmsToken.substring(0, Math.min(20, wmsToken.length())) + "...");
        
        return given()
                .baseUri(config.getWmsBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", wmsToken)
                .header("fc-uuid", config.getProperty("ftl.order.default.fcUuid", "db313a0b5c6942a2b7bf81cbf0639f52"))
                .header("User-Uuid", config.getWmsUserUuid())
                .header("Accept", "*/*")
                .body(request)
                .when()
                .post(CREATE_FTL_ORDER)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Create FTL Order and return response object
     * @param request CreateFTLOrderRequest payload
     * @return CreateFTLOrderResponse object
     */
    public static CreateFTLOrderResponse createFTLOrderAndGetResponse(CreateFTLOrderRequest request) {
        Response response = createFTLOrder(request);
        
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        
        return response.as(CreateFTLOrderResponse.class);
    }
}

