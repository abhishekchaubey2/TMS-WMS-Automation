package com.delhivery.TMS_WMS.applicationApi;

import com.delhivery.TMS_WMS.api.TmsAuthApi;
import com.delhivery.TMS_WMS.pojo.tms.response.TmsGetOrdersResponse;
import com.delhivery.core.utils.ConfigLoader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TmsApiRequests {
    
    private static final ConfigLoader config = ConfigLoader.getInstance();
    
    /**
     * Get Orders from TMS
     * Uses TMS token for x-coreos-access and x-coreos-auth headers
     */
    public static TmsGetOrdersResponse getOrders(String status, String originFacility) {
        String tmsToken = TmsAuthApi.getAccessToken();
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Chromium\";v=\"142\", \"Google Chrome\";v=\"142\", \"Not_A Brand\";v=\"99\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36")
            // Dynamic Headers using TMS Token
            .header("x-coreos-access", tmsToken)
            .header("x-coreos-auth", tmsToken)
            // Headers from properties
            .header("x-coreos-request-id", config.getProperty("tms.orders.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.orders.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.orders.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.orders.header.x-tms-data-access"))
            // Query Params
            .queryParam("status", status)
            .queryParam("originFacility", originFacility)
            .log().all()
        .when()
            .get(config.getProperty("tms.orders.endpoint"))
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch TMS orders. Status: " + response.getStatusCode());
        }
        
        return response.as(TmsGetOrdersResponse.class);
    }

    /**
     * Create Demand in TMS
     * Uses TMS token for x-coreos-access and x-coreos-auth headers
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse createDemand(com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest requestBody) {
        String tmsToken = TmsAuthApi.getAccessToken();
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-US,en;q=0.9")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Chromium\";v=\"142\", \"Google Chrome\";v=\"142\", \"Not_A Brand\";v=\"99\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36")
            .header("content-type", "application/json")
            // Dynamic Headers using TMS Token
            .header("x-coreos-access", tmsToken)
            .header("x-coreos-auth", tmsToken)
            // Headers from properties
            .header("callback", config.getProperty("tms.demand.header.callback"))
            .header("origin", config.getProperty("tms.demand.header.origin"))
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id"))
            .header("x-coreos-tid", config.getProperty("tms.demand.header.x-coreos-tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(requestBody)
            .log().all()
        .when()
            .post(config.getProperty("tms.demand.endpoint"))
        .then()
            .log().all()
            .extract()
            .response();
            
        if (response.getStatusCode() != 200 && response.getStatusCode() != 201 && response.getStatusCode() != 202) {
            throw new RuntimeException("Failed to create TMS demand. Status: " + response.getStatusCode());
        }
        
        return response.as(com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse.class);
    }
}
