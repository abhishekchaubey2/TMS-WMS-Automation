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
     * 
     * TODO: TEMPORARY - Using hardcoded token for createDemand only. Remove this and use TmsAuthApi.getAccessToken()
     * after investigating the issue with generated token permissions.
     */
    public static com.delhivery.TMS_WMS.pojo.tms.response.CreateDemandResponse createDemand(com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest requestBody) {
        // TEMPORARY: Use hardcoded token only for createDemand API
        String tmsToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhSC1FZjZXMU1FVU9LVmllczZIamx4c0dKQlVsanVENXdQajVtNldfVndRIn0.eyJleHAiOjE3NjY4MzgxMDEsImlhdCI6MTc2Njc1MTcwMSwiYXV0aF90aW1lIjoxNzY2NzUxNzAxLCJqdGkiOiJkZmVjOGZlNi1mN2M3LTQ1NzktODk0MS0wZDJkNmE3OTE1OTkiLCJpc3MiOiJodHRwczovL2F1dGgtc2IxLnNhbmRib3guZ2V0b3MxLmNvbS9hdXRoL3JlYWxtcy9kZWwwZ2F4Z2oxIiwiYXVkIjpbInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmU3YzBmNmU1LWU2NzQtNTEyZS04YjhhLTM4OWNiMTVkOTRkMSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmVlY2ZiNzY3LWIzNWQtNTExZS05ZmNhLTA1MDU0OTkyYTZkZCIsInBsYXRmb3JtOmFwcDpjb3Jlb3MiLCJwbGF0Zm9ybTphcHA6c2VydmljZTpkMDU1YjlkOC00NjZmLTVhNzMtOGRhZC01YTFlYTRhZmJjNDAiLCJwbGF0Zm9ybTphcHA6c2VydmljZTplOTYzY2QyYS1kZDc1LTUzYzMtOGExMy1jOGIzMDVmZjU1MmUiLCJtdGZaclBjeENLNnM0QTN6eFAzeHhEVnN0Vk5LdjhtVyIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlmOGNlNGZmLWU4M2QtNTk0Yi1iOTRiLWM2M2FmZGY4NmZjNiIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjc0YWE5NzhjLTEzMWQtNTRmYS04ODczLWNkNTcwZjcyMWI0OSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjE0OTdhZDg4LTk4NGUtNTg3My05MzAxLThjNzg4ZmFkODI1NiIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjMxM2UyN2JiLTQyZDYtNTFkZi04ZGMwLWIzYWI2MWJjNDY4OSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjg3ZDVkYjUxLTVlODUtNTAyYy04MDMwLWY3ZjJlMzU0MWI5MSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjk0OTc1NjNkLWRkYjktNTMyYS1iYWYyLTQxZWExZDU4N2FmZSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmEyOTdlOWUyLTA0YzYtNWI1NS1iZDQwLTI3OTNiYWI4ZjljMCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjNmNGE4NGRkLWRiN2MtNTc4OS05YzE0LTA0YzUyOGVjMDE4MyIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmVlMmYyOWJiLTgwZWUtNTYzYi04MTkzLTI2ZDZjNDgwMzI0OSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmM5YzgyNTBhLTUzNDItNTJkMC1iNTUxLWNkZWY5OGVmMDJiMSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjZmYzA1MTM2LThhZmQtNTBiZi04ODBlLTYxZTkyNDg2MTNiMSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmQ0NWE0YzRmLTZjODUtNTMwMy1iYTBhLTIxMTM4MDg3OWE0NyIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjM4NmVlZWRiLWU5YTktNTIxYi04MmY2LTZjMTZkNTUzNTQ1NSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjViNGU4NTg2LWM3MmEtNWQyMi04N2I1LWE0NzgxZjAxZWIwNSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlkODNlNDA5LTg2OWUtNTM1My04ZTIzLTljMzgxODQ5NGU3OCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjQ3NzdjY2Y4LTZhMGItNWEwYi1iNzFmLWFkYmFmYTIxYjczOCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjZhYTBhNjI1LWYwMTgtNWY4ZS04YTI5LWY3OTgxNmUzMjQ1ZiIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjUwN2U2NDUxLTJiMTYtNWFiOS1hZGZlLWQ5OTM2MDI3YzVhOSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjBkODFiMDhhLWM4ZTUtNTdkOS1iZjBkLWMxMDg0OWFmYzQ2ZCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjNiMzNmMmE5LTczZGYtNWM5NC1hMzU0LWZlNDkwOTNiNDcwMSJdLCJzdWIiOiIzMDIyZmZmMy1kY2Y5LTRhYjgtYjA5Mi00ZGNiOWUwNjU5NWYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwbGF0Zm9ybTphcHA6Y2xpZW50OjIwZTFhYWE5LTQzNTUtNTdlNy1hNjZlLTMyYjlhM2JjZmJkYSIsInNlc3Npb25fc3RhdGUiOiIzOGFkYzY5Yy0yZmI0LTQzMWMtOTFmMS03YjIxMjU4Mjg5ZTEiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vZGVsMGdheGdqMS5zYW5kYm94LmdldG9zMS5jb20iXSwicmVzb3VyY2VfYWNjZXNzIjp7InBsYXRmb3JtOmFwcDpzZXJ2aWNlOmU3YzBmNmU1LWU2NzQtNTEyZS04YjhhLTM4OWNiMTVkOTRkMSI6eyJyb2xlcyI6WyJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLXVwZGF0ZS1yYXRlLW1hc3RlciIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtcHJvY3VyZSIsIlJvbGU6dG1zLWRlbWFuZHM6dGVuYW50LWJ1bGstY3JlYXRlLWNvbnRyYWN0IiwiUm9sZTp0bXMtZGVtYW5kczp3cml0ZSIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtYnVsay10YXNrIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtZmFjaWxpdGllcyIsIlJvbGU6dG1zLWRlbWFuZHM6dGVuYW50LWJ1bGstY3JlYXRlLXJhdGUtbWFzdGVyIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay11cGxvYWQtcGF5bWVudHMiLCJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLWNyZWF0ZS1sb2FkIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtc3VwcGxpZXIiLCJSb2xlOnRtcy1kZW1hbmRzOnJlYWQiLCJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLWNyZWF0ZS1pbnZvaWNlcyIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtY3JlYXRlLWNvbnRyYWN0IiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYWRtaW4tdmlld2VyIiwiUm9sZTp0bXMtZGVtYW5kczpkZW1hbmQtYWRtaW4iLCJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLWRlbGV0ZS1wYXltZW50cyIsIlJvbGU6dG1zLWRlbWFuZHM6bWFuYWdlLWNsdXN0ZXJzIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtcHJvZHVjdHMiLCJSb2xlOnRtcy1kZW1hbmRzOndyaXRlLWNhbmNlbC1sb2FkIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtb3JkZXJzIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYWRtaW4tbWFuYWdlciIsIlJvbGU6dG1zLWRlbWFuZHM6ZGVtYW5kLXVwZGF0ZSIsIlJvbGU6dG1zLWRlbWFuZHM6dGVuYW50LWJ1bGstdXBsb2FkLVBPRCIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtYWxsb2NhdGUtY29udHJhY3QiLCJSb2xlOnRtcy1kZW1hbmRzOndyaXRlLWxvYWQtaW52b2ljZSJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6ZWVjZmI3NjctYjM1ZC01MTFlLTlmY2EtMDUwNTQ5OTJhNmRkIjp7InJvbGVzIjpbIlJvbGU6dG1zLXZlaGljbGVzOndyaXRlIiwiUm9sZTp0bXMtdmVoaWNsZXM6cmVhZCJdfSwicGxhdGZvcm06YXBwOmNvcmVvcyI6eyJyb2xlcyI6WyJSb2xlOmNvcmVvczp1bXMtZnBhLWFkbWluIiwiUm9sZTpjb3Jlb3M6cGFydGljaXBhbnQtZnBhLWFkbWluIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTpkMDU1YjlkOC00NjZmLTVhNzMtOGRhZC01YTFlYTRhZmJjNDAiOnsicm9sZXMiOlsiUm9sZTp0bXMtYWN0aXZpdHktbG9nczpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTplOTYzY2QyYS1kZDc1LTUzYzMtOGExMy1jOGIzMDVmZjU1MmUiOnsicm9sZXMiOlsiUm9sZTp0bXMtcGxhbm5pbmc6cmVhZCIsIlJvbGU6dG1zLXBsYW5uaW5nOndyaXRlIl19LCJtdGZaclBjeENLNnM0QTN6eFAzeHhEVnN0Vk5LdjhtVyI6eyJyb2xlcyI6WyJSb2xlOnBhcnRpY2lwYW50LWZwYTpwYXJ0aWNpcGFudC1mcGEtYWRtaW4iXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlmOGNlNGZmLWU4M2QtNTk0Yi1iOTRiLWM2M2FmZGY4NmZjNiI6eyJyb2xlcyI6WyJSb2xlOm1hc3RlcnM6dGVuYW50LWJsYW5rLXZpZXciXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjc0YWE5NzhjLTEzMWQtNTRmYS04ODczLWNkNTcwZjcyMWI0OSI6eyJyb2xlcyI6WyJSb2xlOnRtcy10cmFuc3BvcnRlcnM6cmVhZC1zdXBwbGllci11c2VyIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOnRlbmFudC1hZG1pbi1tYW5hZ2VyIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOnJlYWQtdmVuZG9yLXRlbmFudC1jb25maWciLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6dGVuYW50LWFkbWluLXZpZXdlciIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp3cml0ZS11c2VyIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOndyaXRlLXZlbmRvci10ZW5hbnQtY29uZmlnIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOnJlYWQiLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6c3VwcGxpZXItcG9jLXVzZXIiLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6ZW5hYmxlRGlzYWJsZS1zcCIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczpwbGF0Zm9ybS1hZG1pbiIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczpzdXBwbGllci1hZG1pbiIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp3cml0ZSIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp2ZXJpZnktc3AiLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6cmVhZC1kYXRhLWFjY2Vzcy10b2tlbiIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp3cml0ZS1zdXBwbGllci11c2VyIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZToxNDk3YWQ4OC05ODRlLTU4NzMtOTMwMS04Yzc4OGZhZDgyNTYiOnsicm9sZXMiOlsiUm9sZTpjb250cm9sdG93ZXI6dGVuYW50LWJsYW5rLXZpZXciXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjMxM2UyN2JiLTQyZDYtNTFkZi04ZGMwLWIzYWI2MWJjNDY4OSI6eyJyb2xlcyI6WyJSb2xlOkluZGVudFVJOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTo4N2Q1ZGI1MS01ZTg1LTUwMmMtODAzMC1mN2YyZTM1NDFiOTEiOnsicm9sZXMiOlsiUm9sZTpDTV9JbmJvdW5kX091dGJvdW5kOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTo5NDk3NTYzZC1kZGI5LTUzMmEtYmFmMi00MWVhMWQ1ODdhZmUiOnsicm9sZXMiOlsiUm9sZTp0bXMtd2ViLWF1Y3Rpb25zOndyaXRlIiwiUm9sZTp0bXMtd2ViLWF1Y3Rpb25zOnJlYWQiXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmEyOTdlOWUyLTA0YzYtNWI1NS1iZDQwLTI3OTNiYWI4ZjljMCI6eyJyb2xlcyI6WyJSb2xlOkJ1bGs6dGVuYW50LWJsYW5rLXZpZXciXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjNmNGE4NGRkLWRiN2MtNTc4OS05YzE0LTA0YzUyOGVjMDE4MyI6eyJyb2xlcyI6WyJSb2xlOnRtcy1jb21tb246c3BvdC1wcmljZSIsIlJvbGU6dG1zLWNvbW1vbjpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTplZTJmMjliYi04MGVlLTU2M2ItODE5My0yNmQ2YzQ4MDMyNDkiOnsicm9sZXMiOlsiUm9sZTp0bXMtZmFjaWxpdGllczp3cml0ZSIsIlJvbGU6dG1zLWZhY2lsaXRpZXM6cmVhZCJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6YzljODI1MGEtNTM0Mi01MmQwLWI1NTEtY2RlZjk4ZWYwMmIxIjp7InJvbGVzIjpbIlJvbGU6QW5hbHl0aWNzOnJlYWQtcmVwb3J0cyIsIlJvbGU6QW5hbHl0aWNzOndyaXRlLXJlcG9ydHMtZ2VuZXJhdGUiXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjZmYzA1MTM2LThhZmQtNTBiZi04ODBlLTYxZTkyNDg2MTNiMSI6eyJyb2xlcyI6WyJSb2xlOnRtcy1yYXRlLW1hc3RlcnM6cmVhZCIsIlJvbGU6dG1zLXJhdGUtbWFzdGVyczp3cml0ZSJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6ZDQ1YTRjNGYtNmM4NS01MzAzLWJhMGEtMjExMzgwODc5YTQ3Ijp7InJvbGVzIjpbIlJvbGU6dG1zLW9yZGVyczpvcmRlcnMtY3J1ZCIsIlJvbGU6dG1zLW9yZGVyczpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTozODZlZWVkYi1lOWE5LTUyMWItODJmNi02YzE2ZDU1MzU0NTUiOnsicm9sZXMiOlsiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tdmVoaWNsZS1kZXRhaWxzIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tc2xhIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tYWRkaXRpb25hbC1kZXRhaWxzIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tcmVwb3J0ZWQiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWNhbmNlbCIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWRlbGl2ZXJlZCIsIlJvbGU6dG1zLWluZGVudHM6ZWRpdC1kaXN0YW5jZSIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWxvYWRpbmctdW5sb2FkaW5nIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZSIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWxyIiwiUm9sZTp0bXMtaW5kZW50czp0ZW5hbnQtYWRtaW4tdmlld2VyIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tYWNjZXB0IiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS11cGxvYWQtcG9kIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tdmVoaWNsZS1kb2NrZWQiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWFjdGlvbi12ZWhpY2xlLXNlYWxlZCIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLW5zbCIsIlJvbGU6dG1zLWluZGVudHM6dGVuYW50LWFkbWluLW1hbmFnZXIiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWFjdGlvbi1hc3NpZ24iLCJSb2xlOnRtcy1pbmRlbnRzOnJlYWQiLCJSb2xlOnRtcy1pbmRlbnRzOmRlbWFuZC1hZG1pbiIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWRpc3BhdGNoIiwiUm9sZTp0bXMtaW5kZW50czpyZWFkLWN0LXN1bW1hcnkiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWFjdGlvbi12ZWhpY2xlLWRldGFpbHMtaW50cmFuc2l0IiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24taW5zcGVjdC12ZWhpY2xlIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1pbml0aWF0ZS1jb25zZW50IiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24taW52b2ljZSJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6NWI0ZTg1ODYtYzcyYS01ZDIyLTg3YjUtYTQ3ODFmMDFlYjA1Ijp7InJvbGVzIjpbIlJvbGU6dG1zLWNvbmZpZ3VyYXRpb25zOnJlYWQiXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlkODNlNDA5LTg2OWUtNTM1My04ZTIzLTljMzgxODQ5NGU3OCI6eyJyb2xlcyI6WyJSb2xlOnRtcy13ZWItc3BvdDp3cml0ZSIsIlJvbGU6dG1zLXdlYi1zcG90OnJlYWQiLCJSb2xlOnRtcy13ZWItc3BvdDptYW5hZ2UtYXVjdGlvbiJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6NDc3N2NjZjgtNmEwYi01YTBiLWI3MWYtYWRiYWZhMjFiNzM4Ijp7InJvbGVzIjpbIlJvbGU6dG1zLXdlYi1wbGFuOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTo2YWEwYTYyNS1mMDE4LTVmOGUtOGEyOS1mNzk4MTZlMzI0NWYiOnsicm9sZXMiOlsiUm9sZTp0bXMtYmlsbGluZzphcHByb3ZlLXJlamVjdC1jcmVkaXRub3RlLW1lbW9zIiwiUm9sZTp0bXMtYmlsbGluZzpyZWFkLWNoYXJnZXMiLCJSb2xlOnRtcy1iaWxsaW5nOmRvd25sb2FkLWludm9pY2UiLCJSb2xlOnRtcy1iaWxsaW5nOnJlYWQtc2hpcHBlci1iaWxsaW5nLWNvbmZpZyIsIlJvbGU6dG1zLWJpbGxpbmc6YXBwcm92ZS1yZWplY3QtaW52b2ljZSIsIlJvbGU6dG1zLWJpbGxpbmc6cmVhZC1zaGlwcGVyLWludm9pY2UiLCJSb2xlOnRtcy1iaWxsaW5nOndyaXRlLWRlYml0bm90ZS1tZW1vcyIsIlJvbGU6dG1zLWJpbGxpbmc6d3JpdGUtY2hhcmdlcyIsIlJvbGU6dG1zLWJpbGxpbmc6d3JpdGUtc2hpcHBlci1iaWxsaW5nLWNvbmZpZyJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6NTA3ZTY0NTEtMmIxNi01YWI5LWFkZmUtZDk5MzYwMjdjNWE5Ijp7InJvbGVzIjpbIlJvbGU6dG1zLXdlYi1iaWxsaW5nOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTowZDgxYjA4YS1jOGU1LTU3ZDktYmYwZC1jMTA4NDlhZmM0NmQiOnsicm9sZXMiOlsiUm9sZTp0bXMtY29udHJhY3RzOndyaXRlLWFwcHJvdmUiLCJSb2xlOnRtcy1jb250cmFjdHM6d3JpdGUtYWN0aXZhdGUtaW5hY3RpdmF0ZSIsIlJvbGU6dG1zLWNvbnRyYWN0czp0ZW5hbnQtYWRtaW4tbWFuYWdlciIsIlJvbGU6dG1zLWNvbnRyYWN0czp3cml0ZSIsIlJvbGU6dG1zLWNvbnRyYWN0czpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTozYjMzZjJhOS03M2RmLTVjOTQtYTM1NC1mZTQ5MDkzYjQ3MDEiOnsicm9sZXMiOlsiUm9sZTpMb2FkVUk6dGVuYW50LWJsYW5rLXZpZXciXX19LCJzY29wZSI6Im9wZW5pZCBjb3Jlb3MgcHJvZmlsZSBlbWFpbCIsInNpZCI6IjM4YWRjNjljLTJmYjQtNDMxYy05MWYxLTdiMjEyNTgyODllMSIsInRlbmFudFV1aWQiOiJ0ZW5hbnRzOjBjOThkNGMwLWJjMzYtNTdiMC05ZjRmLWRiMzNjM2E4NzExMSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJ0eiI6IkFzaWEvS29sa2F0YSIsImh0dHBzOi8vZGVsaGl2ZXJ5LmNvbS90ZW5hbnRJZCI6ImRlbDBnYXhnajEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIrOTE4ODc3NjU3ODk5IiwiZ2l2ZW5fbmFtZSI6IlNoaXZhbSIsInVzZXJJZCI6ImQyNDlkNzU3LWNkOTctNGZlOC1hN2RhLWZjMTVmNTNmMjY0MiIsInNlY3VyaXR5TGV2ZWwiOiJPUEVOIiwiaG9zdG5hbWUiOiJzYW5kYm94LmdldG9zMS5jb20iLCJhcHBJZCI6InBsYXRmb3JtOmFwcDoyMGUxYWFhOS00MzU1LTU3ZTctYTY2ZS0zMmI5YTNiY2ZiZGEtY2xpZW50IiwidGVuYW50SWQiOiJkZWwwZ2F4Z2oxIiwibmFtZSI6IlNoaXZhbSBTaW5naCIsImh0dHBzOi8vZGVsaGl2ZXJ5LmNvbS91c2VySWQiOiJkMjQ5ZDc1Ny1jZDk3LTRmZTgtYTdkYS1mYzE1ZjUzZjI2NDIiLCJmYW1pbHlfbmFtZSI6IlNpbmdoIiwiZW1haWwiOiJzaGl2YW0uc2luZ2gxM0BkZWxoaXZlcnkuY29tIn0.ZSr667e2UvJQeqP3jSVnDs-gZFDgD0IWbzR5eSG9SHP2rcAtiQB0dX_ev1fRLhWQ4eOpt4QQUNhkh6NiiqEYzNnJZHNv613WNXZYKBYiREJIsmcXY-ohR7F0ueqsrwQSHjPnsoxhhyVRKH2Oxw_RWE0bYimZEmT6kjdGtzKTZhs7ACijIKWh072f7m1LK-Ku2xLS4y7seDy_bd4fovJ2Hgmk63dtTFwrVv2rvmLsnypVsRQ0XLBwwRAPRXvW0LAPeqMXjCyWZ1ZkOFgHsx3vmDizx2C0NZFig3H1dO2Mf8Nu-7rMr9HOHG_VqlRWenompMo8oZmE3rRZ7MjgHvAg9A";
            System.out.println("=== Using Hardcoded TMS Token for createDemand (TEMPORARY) ===");
        // String tmsToken = TmsAuthApi.getAccessToken(); // Original code - use this after fixing token issue
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
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
    
    /**
     * Create LTL Order in TMS
     * Uses order app token for x-coreos-access header
     * 
     * @param orderAppToken The order app token (from LtlOrderAppAuthApi)
     * @param requestBody The LTL order request body
     * @return Response from the API
     */
    public static io.restassured.response.Response createLtlOrder(String orderAppToken, com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderRequest requestBody) {
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("X-COREOS-TID", config.getProperty("tms.auth.tid"))
            .header("X-COREOS-REQUEST-ID", "123")
            .header("X-COREOS-ACCESS", orderAppToken)
            .header("X-COREOS-APP-ID", "gps")
            .header("Content-Type", "application/json")
            .header("X-COREOS-USERINFO", "{\"id\":\"1\",\"name\":\"abc\"}")
            .body(requestBody)
            .log().all()
        .when()
            .post("/tms/api/v1/orders/")
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Create LTL Load (Demand) in TMS
     * Uses hardcoded token for x-coreos-access and x-coreos-auth headers
     * 
     * @param requestBody The LTL demand request body
     * @return Response from the API
     */
    public static io.restassured.response.Response createLtlLoad(com.delhivery.TMS_WMS.pojo.tms.request.CreateDemandRequest requestBody) {
        // Hardcoded token provided by user
        String hardcodedToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhSC1FZjZXMU1FVU9LVmllczZIamx4c0dKQlVsanVENXdQajVtNldfVndRIn0.eyJleHAiOjE3NjY5MjY0MzMsImlhdCI6MTc2Njg0MDAzNCwiYXV0aF90aW1lIjoxNzY2ODQwMDMzLCJqdGkiOiJkYmRjYzU1NS0zYmZiLTQzNjItYjk1OS05ZmNjODE0YmY5YzIiLCJpc3MiOiJodHRwczovL2F1dGgtc2IxLnNhbmRib3guZ2V0b3MxLmNvbS9hdXRoL3JlYWxtcy9kZWwwZ2F4Z2oxIiwiYXVkIjpbInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmU3YzBmNmU1LWU2NzQtNTEyZS04YjhhLTM4OWNiMTVkOTRkMSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmVlY2ZiNzY3LWIzNWQtNTExZS05ZmNhLTA1MDU0OTkyYTZkZCIsInBsYXRmb3JtOmFwcDpjb3Jlb3MiLCJwbGF0Zm9ybTphcHA6c2VydmljZTpkMDU1YjlkOC00NjZmLTVhNzMtOGRhZC01YTFlYTRhZmJjNDAiLCJwbGF0Zm9ybTphcHA6c2VydmljZTplOTYzY2QyYS1kZDc1LTUzYzMtOGExMy1jOGIzMDVmZjU1MmUiLCJtdGZaclBjeENLNnM0QTN6eFAzeHhEVnN0Vk5LdjhtVyIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlmOGNlNGZmLWU4M2QtNTk0Yi1iOTRiLWM2M2FmZGY4NmZjNiIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjc0YWE5NzhjLTEzMWQtNTRmYS04ODczLWNkNTcwZjcyMWI0OSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjE0OTdhZDg4LTk4NGUtNTg3My05MzAxLThjNzg4ZmFkODI1NiIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjMxM2UyN2JiLTQyZDYtNTFkZi04ZGMwLWIzYWI2MWJjNDY4OSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjg3ZDVkYjUxLTVlODUtNTAyYy04MDMwLWY3ZjJlMzU0MWI5MSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjk0OTc1NjNkLWRkYjktNTMyYS1iYWYyLTQxZWExZDU4N2FmZSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmEyOTdlOWUyLTA0YzYtNWI1NS1iZDQwLTI3OTNiYWI4ZjljMCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjNmNGE4NGRkLWRiN2MtNTc4OS05YzE0LTA0YzUyOGVjMDE4MyIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmVlMmYyOWJiLTgwZWUtNTYzYi04MTkzLTI2ZDZjNDgwMzI0OSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmM5YzgyNTBhLTUzNDItNTJkMC1iNTUxLWNkZWY5OGVmMDJiMSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjZmYzA1MTM2LThhZmQtNTBiZi04ODBlLTYxZTkyNDg2MTNiMSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmQ0NWE0YzRmLTZjODUtNTMwMy1iYTBhLTIxMTM4MDg3OWE0NyIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjM4NmVlZWRiLWU5YTktNTIxYi04MmY2LTZjMTZkNTUzNTQ1NSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjViNGU4NTg2LWM3MmEtNWQyMi04N2I1LWE0NzgxZjAxZWIwNSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlkODNlNDA5LTg2OWUtNTM1My04ZTIzLTljMzgxODQ5NGU3OCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjQ3NzdjY2Y4LTZhMGItNWEwYi1iNzFmLWFkYmFmYTIxYjczOCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjZhYTBhNjI1LWYwMTgtNWY4ZS04YTI5LWY3OTgxNmUzMjQ1ZiIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjUwN2U2NDUxLTJiMTYtNWFiOS1hZGZlLWQ5OTM2MDI3YzVhOSIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjBkODFiMDhhLWM4ZTUtNTdkOS1iZjBkLWMxMDg0OWFmYzQ2ZCIsInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjNiMzNmMmE5LTczZGYtNWM5NC1hMzU0LWZlNDkwOTNiNDcwMSJdLCJzdWIiOiIzMDIyZmZmMy1kY2Y5LTRhYjgtYjA5Mi00ZGNiOWUwNjU5NWYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwbGF0Zm9ybTphcHA6Y2xpZW50OjIwZTFhYWE5LTQzNTUtNTdlNy1hNjZlLTMyYjlhM2JjZmJkYSIsInNlc3Npb25fc3RhdGUiOiJmY2FhNDljOS1jYWY0LTRlOTMtOTFmMi04NWVmNTA4ZGU2ZTciLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vZGVsMGdheGdqMS5zYW5kYm94LmdldG9zMS5jb20iXSwicmVzb3VyY2VfYWNjZXNzIjp7InBsYXRmb3JtOmFwcDpzZXJ2aWNlOmU3YzBmNmU1LWU2NzQtNTEyZS04YjhhLTM4OWNiMTVkOTRkMSI6eyJyb2xlcyI6WyJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLXVwZGF0ZS1yYXRlLW1hc3RlciIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtcHJvY3VyZSIsIlJvbGU6dG1zLWRlbWFuZHM6dGVuYW50LWJ1bGstY3JlYXRlLWNvbnRyYWN0IiwiUm9sZTp0bXMtZGVtYW5kczp3cml0ZSIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtYnVsay10YXNrIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtZmFjaWxpdGllcyIsIlJvbGU6dG1zLWRlbWFuZHM6dGVuYW50LWJ1bGstY3JlYXRlLXJhdGUtbWFzdGVyIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay11cGxvYWQtcGF5bWVudHMiLCJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLWNyZWF0ZS1sb2FkIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtc3VwcGxpZXIiLCJSb2xlOnRtcy1kZW1hbmRzOnJlYWQiLCJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLWNyZWF0ZS1pbnZvaWNlcyIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtY3JlYXRlLWNvbnRyYWN0IiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYWRtaW4tdmlld2VyIiwiUm9sZTp0bXMtZGVtYW5kczpkZW1hbmQtYWRtaW4iLCJSb2xlOnRtcy1kZW1hbmRzOnRlbmFudC1idWxrLWRlbGV0ZS1wYXltZW50cyIsIlJvbGU6dG1zLWRlbWFuZHM6bWFuYWdlLWNsdXN0ZXJzIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtcHJvZHVjdHMiLCJSb2xlOnRtcy1kZW1hbmRzOndyaXRlLWNhbmNlbC1sb2FkIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYnVsay1jcmVhdGUtb3JkZXJzIiwiUm9sZTp0bXMtZGVtYW5kczp0ZW5hbnQtYWRtaW4tbWFuYWdlciIsIlJvbGU6dG1zLWRlbWFuZHM6ZGVtYW5kLXVwZGF0ZSIsIlJvbGU6dG1zLWRlbWFuZHM6dGVuYW50LWJ1bGstdXBsb2FkLVBPRCIsIlJvbGU6dG1zLWRlbWFuZHM6d3JpdGUtYWxsb2NhdGUtY29udHJhY3QiLCJSb2xlOnRtcy1kZW1hbmRzOndyaXRlLWxvYWQtaW52b2ljZSJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6ZWVjZmI3NjctYjM1ZC01MTFlLTlmY2EtMDUwNTQ5OTJhNmRkIjp7InJvbGVzIjpbIlJvbGU6dG1zLXZlaGljbGVzOndyaXRlIiwiUm9sZTp0bXMtdmVoaWNsZXM6cmVhZCJdfSwicGxhdGZvcm06YXBwOmNvcmVvcyI6eyJyb2xlcyI6WyJSb2xlOmNvcmVvczp1bXMtZnBhLWFkbWluIiwiUm9sZTpjb3Jlb3M6cGFydGljaXBhbnQtZnBhLWFkbWluIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTpkMDU1YjlkOC00NjZmLTVhNzMtOGRhZC01YTFlYTRhZmJjNDAiOnsicm9sZXMiOlsiUm9sZTp0bXMtYWN0aXZpdHktbG9nczpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTplOTYzY2QyYS1kZDc1LTUzYzMtOGExMy1jOGIzMDVmZjU1MmUiOnsicm9sZXMiOlsiUm9sZTp0bXMtcGxhbm5pbmc6cmVhZCIsIlJvbGU6dG1zLXBsYW5uaW5nOndyaXRlIl19LCJtdGZaclBjeENLNnM0QTN6eFAzeHhEVnN0Vk5LdjhtVyI6eyJyb2xlcyI6WyJSb2xlOnBhcnRpY2lwYW50LWZwYTpwYXJ0aWNpcGFudC1mcGEtYWRtaW4iXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlmOGNlNGZmLWU4M2QtNTk0Yi1iOTRiLWM2M2FmZGY4NmZjNiI6eyJyb2xlcyI6WyJSb2xlOm1hc3RlcnM6dGVuYW50LWJsYW5rLXZpZXciXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjc0YWE5NzhjLTEzMWQtNTRmYS04ODczLWNkNTcwZjcyMWI0OSI6eyJyb2xlcyI6WyJSb2xlOnRtcy10cmFuc3BvcnRlcnM6cmVhZC1zdXBwbGllci11c2VyIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOnRlbmFudC1hZG1pbi1tYW5hZ2VyIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOnJlYWQtdmVuZG9yLXRlbmFudC1jb25maWciLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6dGVuYW50LWFkbWluLXZpZXdlciIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp3cml0ZS11c2VyIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOndyaXRlLXZlbmRvci10ZW5hbnQtY29uZmlnIiwiUm9sZTp0bXMtdHJhbnNwb3J0ZXJzOnJlYWQiLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6c3VwcGxpZXItcG9jLXVzZXIiLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6ZW5hYmxlRGlzYWJsZS1zcCIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczpwbGF0Zm9ybS1hZG1pbiIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczpzdXBwbGllci1hZG1pbiIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp3cml0ZSIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp2ZXJpZnktc3AiLCJSb2xlOnRtcy10cmFuc3BvcnRlcnM6cmVhZC1kYXRhLWFjY2Vzcy10b2tlbiIsIlJvbGU6dG1zLXRyYW5zcG9ydGVyczp3cml0ZS1zdXBwbGllci11c2VyIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZToxNDk3YWQ4OC05ODRlLTU4NzMtOTMwMS04Yzc4OGZhZDgyNTYiOnsicm9sZXMiOlsiUm9sZTpjb250cm9sdG93ZXI6dGVuYW50LWJsYW5rLXZpZXciXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjMxM2UyN2JiLTQyZDYtNTFkZi04ZGMwLWIzYWI2MWJjNDY4OSI6eyJyb2xlcyI6WyJSb2xlOkluZGVudFVJOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTo4N2Q1ZGI1MS01ZTg1LTUwMmMtODAzMC1mN2YyZTM1NDFiOTEiOnsicm9sZXMiOlsiUm9sZTpDTV9JbmJvdW5kX091dGJvdW5kOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTo5NDk3NTYzZC1kZGI5LTUzMmEtYmFmMi00MWVhMWQ1ODdhZmUiOnsicm9sZXMiOlsiUm9sZTp0bXMtd2ViLWF1Y3Rpb25zOndyaXRlIiwiUm9sZTp0bXMtd2ViLWF1Y3Rpb25zOnJlYWQiXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOmEyOTdlOWUyLTA0YzYtNWI1NS1iZDQwLTI3OTNiYWI4ZjljMCI6eyJyb2xlcyI6WyJSb2xlOkJ1bGs6dGVuYW50LWJsYW5rLXZpZXciXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjNmNGE4NGRkLWRiN2MtNTc4OS05YzE0LTA0YzUyOGVjMDE4MyI6eyJyb2xlcyI6WyJSb2xlOnRtcy1jb21tb246c3BvdC1wcmljZSIsIlJvbGU6dG1zLWNvbW1vbjpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTplZTJmMjliYi04MGVlLTU2M2ItODE5My0yNmQ2YzQ4MDMyNDkiOnsicm9sZXMiOlsiUm9sZTp0bXMtZmFjaWxpdGllczp3cml0ZSIsIlJvbGU6dG1zLWZhY2lsaXRpZXM6cmVhZCJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6YzljODI1MGEtNTM0Mi01MmQwLWI1NTEtY2RlZjk4ZWYwMmIxIjp7InJvbGVzIjpbIlJvbGU6QW5hbHl0aWNzOnJlYWQtcmVwb3J0cyIsIlJvbGU6QW5hbHl0aWNzOndyaXRlLXJlcG9ydHMtZ2VuZXJhdGUiXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjZmYzA1MTM2LThhZmQtNTBiZi04ODBlLTYxZTkyNDg2MTNiMSI6eyJyb2xlcyI6WyJSb2xlOnRtcy1yYXRlLW1hc3RlcnM6cmVhZCIsIlJvbGU6dG1zLXJhdGUtbWFzdGVyczp3cml0ZSJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6ZDQ1YTRjNGYtNmM4NS01MzAzLWJhMGEtMjExMzgwODc5YTQ3Ijp7InJvbGVzIjpbIlJvbGU6dG1zLW9yZGVyczpvcmRlcnMtY3J1ZCIsIlJvbGU6dG1zLW9yZGVyczpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTozODZlZWVkYi1lOWE5LTUyMWItODJmNi02YzE2ZDU1MzU0NTUiOnsicm9sZXMiOlsiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tdmVoaWNsZS1kZXRhaWxzIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tc2xhIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tYWRkaXRpb25hbC1kZXRhaWxzIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tcmVwb3J0ZWQiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWNhbmNlbCIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWRlbGl2ZXJlZCIsIlJvbGU6dG1zLWluZGVudHM6ZWRpdC1kaXN0YW5jZSIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWxvYWRpbmctdW5sb2FkaW5nIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZSIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWxyIiwiUm9sZTp0bXMtaW5kZW50czp0ZW5hbnQtYWRtaW4tdmlld2VyIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tYWNjZXB0IiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS11cGxvYWQtcG9kIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24tdmVoaWNsZS1kb2NrZWQiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWFjdGlvbi12ZWhpY2xlLXNlYWxlZCIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLW5zbCIsIlJvbGU6dG1zLWluZGVudHM6dGVuYW50LWFkbWluLW1hbmFnZXIiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWFjdGlvbi1hc3NpZ24iLCJSb2xlOnRtcy1pbmRlbnRzOnJlYWQiLCJSb2xlOnRtcy1pbmRlbnRzOmRlbWFuZC1hZG1pbiIsIlJvbGU6dG1zLWluZGVudHM6d3JpdGUtYWN0aW9uLWRpc3BhdGNoIiwiUm9sZTp0bXMtaW5kZW50czpyZWFkLWN0LXN1bW1hcnkiLCJSb2xlOnRtcy1pbmRlbnRzOndyaXRlLWFjdGlvbi12ZWhpY2xlLWRldGFpbHMtaW50cmFuc2l0IiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24taW5zcGVjdC12ZWhpY2xlIiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1pbml0aWF0ZS1jb25zZW50IiwiUm9sZTp0bXMtaW5kZW50czp3cml0ZS1hY3Rpb24taW52b2ljZSJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6NWI0ZTg1ODYtYzcyYS01ZDIyLTg3YjUtYTQ3ODFmMDFlYjA1Ijp7InJvbGVzIjpbIlJvbGU6dG1zLWNvbmZpZ3VyYXRpb25zOnJlYWQiXX0sInBsYXRmb3JtOmFwcDpzZXJ2aWNlOjlkODNlNDA5LTg2OWUtNTM1My04ZTIzLTljMzgxODQ5NGU3OCI6eyJyb2xlcyI6WyJSb2xlOnRtcy13ZWItc3BvdDp3cml0ZSIsIlJvbGU6dG1zLXdlYi1zcG90OnJlYWQiLCJSb2xlOnRtcy13ZWItc3BvdDptYW5hZ2UtYXVjdGlvbiJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6NDc3N2NjZjgtNmEwYi01YTBiLWI3MWYtYWRiYWZhMjFiNzM4Ijp7InJvbGVzIjpbIlJvbGU6dG1zLXdlYi1wbGFuOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTo2YWEwYTYyNS1mMDE4LTVmOGUtOGEyOS1mNzk4MTZlMzI0NWYiOnsicm9sZXMiOlsiUm9sZTp0bXMtYmlsbGluZzphcHByb3ZlLXJlamVjdC1jcmVkaXRub3RlLW1lbW9zIiwiUm9sZTp0bXMtYmlsbGluZzpyZWFkLWNoYXJnZXMiLCJSb2xlOnRtcy1iaWxsaW5nOmRvd25sb2FkLWludm9pY2UiLCJSb2xlOnRtcy1iaWxsaW5nOnJlYWQtc2hpcHBlci1iaWxsaW5nLWNvbmZpZyIsIlJvbGU6dG1zLWJpbGxpbmc6YXBwcm92ZS1yZWplY3QtaW52b2ljZSIsIlJvbGU6dG1zLWJpbGxpbmc6cmVhZC1zaGlwcGVyLWludm9pY2UiLCJSb2xlOnRtcy1iaWxsaW5nOndyaXRlLWRlYml0bm90ZS1tZW1vcyIsIlJvbGU6dG1zLWJpbGxpbmc6d3JpdGUtY2hhcmdlcyIsIlJvbGU6dG1zLWJpbGxpbmc6d3JpdGUtc2hpcHBlci1iaWxsaW5nLWNvbmZpZyJdfSwicGxhdGZvcm06YXBwOnNlcnZpY2U6NTA3ZTY0NTEtMmIxNi01YWI5LWFkZmUtZDk5MzYwMjdjNWE5Ijp7InJvbGVzIjpbIlJvbGU6dG1zLXdlYi1iaWxsaW5nOnRlbmFudC1ibGFuay12aWV3Il19LCJwbGF0Zm9ybTphcHA6c2VydmljZTowZDgxYjA4YS1jOGU1LTU3ZDktYmYwZC1jMTA4NDlhZmM0NmQiOnsicm9sZXMiOlsiUm9sZTp0bXMtY29udHJhY3RzOndyaXRlLWFwcHJvdmUiLCJSb2xlOnRtcy1jb250cmFjdHM6d3JpdGUtYWN0aXZhdGUtaW5hY3RpdmF0ZSIsIlJvbGU6dG1zLWNvbnRyYWN0czp0ZW5hbnQtYWRtaW4tbWFuYWdlciIsIlJvbGU6dG1zLWNvbnRyYWN0czp3cml0ZSIsIlJvbGU6dG1zLWNvbnRyYWN0czpyZWFkIl19LCJwbGF0Zm9ybTphcHA6c2VydmljZTozYjMzZjJhOS03M2RmLTVjOTQtYTM1NC1mZTQ5MDkzYjQ3MDEiOnsicm9sZXMiOlsiUm9sZTpMb2FkVUk6dGVuYW50LWJsYW5rLXZpZXciXX19LCJzY29wZSI6Im9wZW5pZCBjb3Jlb3MgcHJvZmlsZSBlbWFpbCIsInNpZCI6ImZjYWE0OWM5LWNhZjQtNGU5My05MWYyLTg1ZWY1MDhkZTZlNyIsInRlbmFudFV1aWQiOiJ0ZW5hbnRzOjBjOThkNGMwLWJjMzYtNTdiMC05ZjRmLWRiMzNjM2E4NzExMSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJ0eiI6IkFzaWEvS29sa2F0YSIsImh0dHBzOi8vZGVsaGl2ZXJ5LmNvbS90ZW5hbnRJZCI6ImRlbDBnYXhnajEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiIrOTE4ODc3NjU3ODk5IiwiZ2l2ZW5fbmFtZSI6IlNoaXZhbSIsInVzZXJJZCI6ImQyNDlkNzU3LWNkOTctNGZlOC1hN2RhLWZjMTVmNTNmMjY0MiIsInNlY3VyaXR5TGV2ZWwiOiJPUEVOIiwiaG9zdG5hbWUiOiJzYW5kYm94LmdldG9zMS5jb20iLCJhcHBJZCI6InBsYXRmb3JtOmFwcDoyMGUxYWFhOS00MzU1LTU3ZTctYTY2ZS0zMmI5YTNiY2ZiZGEtY2xpZW50IiwidGVuYW50SWQiOiJkZWwwZ2F4Z2oxIiwibmFtZSI6IlNoaXZhbSBTaW5naCIsImh0dHBzOi8vZGVsaGl2ZXJ5LmNvbS91c2VySWQiOiJkMjQ5ZDc1Ny1jZDk3LTRmZTgtYTdkYS1mYzE1ZjUzZjI2NDIiLCJmYW1pbHlfbmFtZSI6IlNpbmdoIiwiZW1haWwiOiJzaGl2YW0uc2luZ2gxM0BkZWxoaXZlcnkuY29tIn0.W38NfZOQCAH9t4AwOQOlWAWoZ9XsVHgYxT90qwddaU6dQ7aETxJCVsbEP8MdeuGDjEyT5toO3EzAo6v5svi9ow4FwveZD9VxserVVQH45cOkbDm0i2f85C75ynH8o_loq4hlieD2t5h_sHwC2GUbePsnW3ZA4gN_LGuFR2yTl2NeRbggMW2E1z1mF_oKFOU0JyU6WTl5t04vObDxEn0ZNXoi17J1vS8qZYrkPRuFBKicXBBPIHkGs8Sn2UIv0c4nhbWfK-KCR9wmrf9HnIWwdP3I-Vd0NBU5JLIecz-28806R0gyYb46tlNbi97H65KfQu0tZv2Wceayigd8RKXJnA";
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("content-type", "application/json")
            // Use hardcoded token
            .header("x-coreos-access", hardcodedToken)
            .header("x-coreos-auth", hardcodedToken)
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
            .post("/tms/api/v1/demands/")
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Update LTL Order in TMS
     * Uses order app token for x-coreos-access header
     * 
     * @param orderAppToken The order app token (from LtlOrderAppAuthApi)
     * @param orderNumber The order number to update
     * @param requestBody The LTL order update request body
     * @return Response from the API
     */
    public static io.restassured.response.Response updateLtlOrder(String orderAppToken, String orderNumber, com.delhivery.TMS_WMS.pojo.tms.request.TmsLtlOrderUpdateRequest requestBody) {
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("X-COREOS-TID", config.getProperty("tms.auth.tid"))
            .header("X-COREOS-REQUEST-ID", "123")
            .header("X-COREOS-ACCESS", orderAppToken)
            .header("X-COREOS-APP-ID", "gps")
            .header("Content-Type", "application/json")
            .header("X-COREOS-USERINFO", "{\"id\":\"1\",\"name\":\"abc\"}")
            .body(requestBody)
            .log().all()
        .when()
            .put("/tms/api/v1/orders/" + orderNumber)
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
    
    /**
     * Get Indents by Load ID
     * Uses demand app token for x-coreos-access and x-coreos-auth headers
     * 
     * @param demandAppToken The demand app token (from DemandAppAuthApi)
     * @param loadId The load ID to get indents for (e.g., DLV-SD283H)
     * @return Response from the API
     */
    public static io.restassured.response.Response getIndentsByLoadId(String demandAppToken, String loadId) {
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id", "OS1-RequestId:-9b41a345-4a2f-44ec-86f1-4bba2730ad51"))
            .header("x-coreos-tid", config.getProperty("tms.auth.tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .log().all()
        .when()
            .get("/tms/api/v1/indents/get_by_loadid/" + loadId)
        .then()
            .log().all()
            .extract()
            .response();
            
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
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"143\", \"Chromium\";v=\"143\", \"Not A(Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36")
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id", "OS1-RequestId:-9b41a345-4a2f-44ec-86f1-4bba2730ad51"))
            .header("x-coreos-tid", config.getProperty("tms.auth.tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .log().all()
        .when()
            .get("/tms/api/v1/indents/" + uniqueCode)
        .then()
            .log().all()
            .extract()
            .response();
            
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
        // Build request body
        com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest request = 
            new com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest();
        
        com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Meta meta = 
            new com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Meta(
                config.getProperty("tms.auth.tid"),
                config.getProperty("tms.demand.app.appId")
            );
        
        com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Payload payload = 
            new com.delhivery.TMS_WMS.pojo.tms.request.RetryFailedManifestationRequest.Payload("indent_manifest_retry");
        
        request.setMeta(meta);
        request.setPayload(payload);
        
        Response response = given()
            .baseUri(config.getProperty("tms.auth.baseUrl"))
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
            .header("priority", "u=1, i")
            .header("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Linux\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-origin")
            .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36")
            .header("Content-Type", "application/json")
            .header("x-coreos-access", demandAppToken)
            .header("x-coreos-auth", demandAppToken)
            .header("x-coreos-request-id", config.getProperty("tms.demand.header.x-coreos-request-id", "OS1-RequestId:-7f364a56-4587-4ed5-a2e0-0232b88d3331"))
            .header("x-coreos-tid", config.getProperty("tms.auth.tid"))
            .header("x-coreos-userinfo", config.getProperty("tms.demand.header.x-coreos-userinfo"))
            .header("x-tms-data-access", config.getProperty("tms.demand.header.x-tms-data-access"))
            .body(request)
            .log().all()
        .when()
            .post("/tms/api/v1/indents/cron/retry_failed_manifestation")
        .then()
            .log().all()
            .extract()
            .response();
            
        return response;
    }
}
