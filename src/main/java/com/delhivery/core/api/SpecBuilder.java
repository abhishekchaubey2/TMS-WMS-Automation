package com.delhivery.core.api;

import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.CoreConstants;
import com.delhivery.core.utils.HttpTemplateObject;
import com.delhivery.core.utils.ProdConfigLoader;
import com.delhivery.core.utils.YamlReader;
import com.delhivery.core.api.TokenManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SpecBuilder {

    // static String access_token = getToken("wms");
    // static String phuzeAccess_token = getToken("phuze");
    private static Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
        headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        return headers;
    }

    private static Map<String, String> defaultHeadersBearer(String bearer) {
        Map<String, String> headers = new HashMap<>();
        //Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
        headers.put("Authorization", "Bearer " + bearer);

        return headers;
    }

    private static Map<String, String> defaultHeadersOdx() {
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
//        headers.put("fc-uuid", ConfigLoader.getInstance().getFcUuid());
//        headers.put("User-Uuid", ConfigLoader.getInstance().getUserUuid());
//        headers.put("Authorization", "Bearer " + access_token);
        headers.put("Authorization", "Token " + clientDetails.get("token").toString());
        headers.put("Application", "RT-ODX");
        return headers;
    }

    private static Map<String, String> prodHeaders() {
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> clientDetails = YamlReader.getYamlValues("productionData.client_" + ProdConfigLoader.getInstance().getClient());
        headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        return headers;
    }

    private static Map<String, String> QCHeadersBearer() {
        Map<String, String> headers = new HashMap<>();
        //Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
        headers.put("Authorization", "Bearer " + ConfigLoader.getInstance().getQCBearer());

        return headers;
    }

    private static Map<String, String> ManifestationServiceHeaders() {
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> ManifestclientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getManifestRegressionClient());
        headers.put("Authorization", "Token " + ManifestclientDetails.get("token").toString());

        return headers;
    }

    // Default getRequestSpec method for backward compatibility
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    // Default getRequestSpecBearer method for backward compatibility
    public static RequestSpecification getRequestSpecBearer(String bearer) {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .addHeader("Authorization", "Bearer " + bearer)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    // Default getRequestSpecOdx method for backward compatibility
    public static RequestSpecification getRequestSpecOdx() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }



    private static String generateUserInfo(String userId, String userName) {
        return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", userId, userName);
    }
    
    public static RequestSpecification getTmsRequestSpecWithAuth(String userId, String userName) {
        // Generate new token every time
            System.out.println("Generating new TMS token...");
        String tmsToken = TokenManager.generateXCoreosAuthToken();
        
        // Use the same token for both x-coreos-auth and x-coreos-access

        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getTmsBaseUrl())
                .addHeaders(tmsHeadersWithAuth(tmsToken, tmsToken))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    // Updated to include only x-coreos-auth and x-coreos-access headers
    private static Map<String, String> tmsHeadersWithAuth(String xCoreosAuth, String xCoreosAccess) {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", ConfigLoader.getInstance().getTmsAccept());
        headers.put("accept-language", ConfigLoader.getInstance().getTmsAcceptLanguage());
        headers.put("callback", ConfigLoader.getInstance().getTmsCallback());
        headers.put("content-type", ConfigLoader.getInstance().getTmsContentType());
        //headers.put("origin", ConfigLoader.getInstance().getTmsOrigin());
        //headers.put("priority", ConfigLoader.getInstance().getTmsPriority());
        //headers.put("sec-ch-ua", ConfigLoader.getInstance().getTmsSecChUa());
        //headers.put("sec-ch-ua-mobile", ConfigLoader.getInstance().getTmsSecChUaMobile());
        //headers.put("sec-ch-ua-platform", ConfigLoader.getInstance().getTmsSecChUaPlatform());
        //headers.put("sec-fetch-dest", ConfigLoader.getInstance().getTmsSecFetchDest());
        //headers.put("sec-fetch-mode", ConfigLoader.getInstance().getTmsSecFetchMode());
        //headers.put("sec-fetch-site", ConfigLoader.getInstance().getTmsSecFetchSite());
        //headers.put("user-agent", ConfigLoader.getInstance().getTmsUserAgent());
        headers.put("x-coreos-tid", ConfigLoader.getInstance().getTmsXCoreosTid());
        // Use tokens without Bearer prefix
        headers.put("x-coreos-auth", xCoreosAuth);
        headers.put("x-coreos-access", xCoreosAccess);
        
        System.out.println("=== TMS Headers Debug ===");
        System.out.println("x-coreos-auth token: " + (xCoreosAuth != null ? xCoreosAuth.substring(0, Math.min(50, xCoreosAuth.length())) + "..." : "null"));
        System.out.println("x-coreos-access token: " + (xCoreosAccess != null ? xCoreosAccess.substring(0, Math.min(50, xCoreosAccess.length())) + "..." : "null"));
        System.out.println("callback: " + ConfigLoader.getInstance().getTmsCallback());
        
        return headers;
    }

    // TMS FINISHED
    public static RequestSpecification getRequestSpecFormData(Map<String, String> data) {
        String tokenType = data.get("token_type");
        String bearer;

        if (data.containsKey("jwt_token")) {
            bearer = data.get("jwt_token");
        } else {
            throw new RuntimeException("Token is not set");
        }

        Map<String, String> headers = new HashMap<>();
        switch (tokenType) {
            case "cookie_token":
                headers.put("cookie", bearer);
                break;

            case "client_token":
                headers = defaultHeaders();
                break;

            case "bearer":
                headers = defaultHeadersBearer(bearer);
                break;

            default:
                throw new RuntimeException("Key " + tokenType + " is not defined");
        }


        if (data.containsKey("content_tye")) {
            return new RequestSpecBuilder()
                    .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                    .addHeaders(headers)
                    .setContentType(ContentType.MULTIPART)
                    .setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                    .log(LogDetail.ALL)
                    .build();
        }
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .addHeaders(headers)
                .log(LogDetail.ALL)
                .build();
    }

    //tms change_1
    public static RequestSpecification getRequestSpec(HttpTemplateObject expressHttpReqObject) {
        return new RequestSpecBuilder()
                .setBaseUri(expressHttpReqObject.getUrl())
                .addHeaders(defaultHeaders(expressHttpReqObject))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
    //tms change_2
    private static Map<String, String> defaultHeaders(HttpTemplateObject expressHttpReqObject) {
        //if header decoration is not required then return the headers as it is, header decoration flag will be set through consumer
        if(expressHttpReqObject.getIsHeaderDecorationRequired()!=null && !expressHttpReqObject.getIsHeaderDecorationRequired()){
            return expressHttpReqObject.getHeaders();
        }

        Map<String, String> headers = new HashMap<>();

        //if token is not present in request template then return the headers as it is
        if (expressHttpReqObject.getToken() == null) {
            return headers;
        }

        //if token is present in request template then add the token to headers
        //if length of token is greater than 50 then it is a user token
        //if length of token is less than 50 then it is a client token
        if (expressHttpReqObject.getToken().length() > 50 || expressHttpReqObject.getToken().
                contains(CoreConstants.AUTH_USER_TOKEN_PREFIX)) {
            headers.put("Authorization", addTokenPrefix(expressHttpReqObject.getToken(),
                    CoreConstants.AUTH_USER_TOKEN_PREFIX));
            return headers;
        }
        headers.put("Authorization", addTokenPrefix(expressHttpReqObject.getToken(), CoreConstants.
                CLIENT_TOKEN_PREFIX));

        return headers;
    }

    //helper to add token prefix
    public static String addTokenPrefix(String input, String prefix) {
        if (input == null) {
            throw new RuntimeException("Authorization Token is null while preparing request");
        }
        if (input.trim().startsWith(prefix)) {
            return input;
        }
        return prefix + " " + input.trim();
    }
    //tms_change_3_finish

    public static RequestSpecification getTrakingSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getTrackingUrl())
                .addHeaders(defaultHeaders())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getHqTrakingSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .addHeaders(defaultHeaders())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
    
    public static RequestSpecification getEdtVsStageRequestSpec(String enviorment,String Client) {
    	String base_uri = ConfigLoader.getInstance().getBaseUrl();
    	
    	if(enviorment.equalsIgnoreCase("edt") || enviorment.equalsIgnoreCase("NewManifest")) {
    		base_uri = ConfigLoader.getInstance().getEdtBaseUrl();
    	}else if(enviorment.equalsIgnoreCase("staging")) {
    		base_uri = ConfigLoader.getInstance().getStagingBaseUrl();
    	}
    	else if(enviorment.equalsIgnoreCase("Trackin")){
    		base_uri = ConfigLoader.getInstance().getTrackingUrl();
    	}else if(enviorment.equalsIgnoreCase("SMS")){
    		base_uri = ConfigLoader.getInstance().getSMSUrl();
    	}else if(enviorment.equalsIgnoreCase("ewbnCreate")){
            base_uri = ConfigLoader.getInstance().getEwbnCreateUrl();
        }
        //adding condition for ewbnFetch url
        else if(enviorment.equalsIgnoreCase("ewbnFetch")){
            base_uri = ConfigLoader.getInstance().getEwbnFetchUrl();
        }

    	
    	Map<String, String> headers = new HashMap<>();
    	headers = defaultHeaders();
        //if a client name is sent in request data then getting it's token
        if (Client.length() < 40 && !Client.equalsIgnoreCase("regression_client") && !Client.equalsIgnoreCase("UMS")) {
            Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" +
                    ConfigLoader.getInstance().getClient(Client));
            headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        }
        return new RequestSpecBuilder()
                .setBaseUri(base_uri)
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getEdtVsStageRequestSpec(String enviorment) {
        String base_uri = ConfigLoader.getInstance().getBaseUrl();

        if (enviorment.equalsIgnoreCase("edt")) {
            base_uri = ConfigLoader.getInstance().getEdtBaseUrl();
        } else if (enviorment.equalsIgnoreCase("staging")) {
            base_uri = ConfigLoader.getInstance().getStagingBaseUrl();
        } else if (enviorment.equalsIgnoreCase("Trackin")) {
            base_uri = ConfigLoader.getInstance().getTrackingUrl();
        }


        Map<String, String> headers = new HashMap<>();
        headers = defaultHeaders();
        //if a client name is sent in request data then getting it's token
//		if (Client.length() < 40 && !Client.equalsIgnoreCase("regression_client") && !Client.equalsIgnoreCase("UMS")) {
//			Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" + 
//		ConfigLoader.getInstance().getClient(Client));
//			headers.put("Authorization", "Token " + clientDetails.get("token").toString());
//			
//		}
        return new RequestSpecBuilder()
                .setBaseUri(base_uri)
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    // public static RequestSpecification getAccountRequestSpec() {
    //     return new RequestSpecBuilder()
    //             .setBaseUri(ConfigLoader.getInstance().getUmsUrl())
    //             .setContentType(ContentType.JSON)
    //             .log(LogDetail.ALL)
    //             .build();
    // }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getTrackingSpec(String requestData) {
        String uri = "";
        Map<String, String> headers = new HashMap<>();
        headers = defaultHeaders();
        //if a client name is sent in request data then getting it's token
        if (requestData.length() < 40 && !requestData.equalsIgnoreCase("regression_client") && !requestData.equalsIgnoreCase("UMS")) {
            Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" +
                    ConfigLoader.getInstance().getClient(requestData));
            headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        }

        switch (requestData.toUpperCase()) {
            case "TRACKING":
                uri = ConfigLoader.getInstance().getTrackingUrl();
                break;

            default:
                uri = ConfigLoader.getInstance().getBaseUrl();
                break;

        }

        if (requestData.length() > 100) {
            headers.put("Authorization", "Bearer " + requestData);
        }

        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpecCmu(String requestData) {
        String uri = "";
        Map<String, String> headers = new HashMap<>();
        headers = defaultHeaders();
//        logInfo("getRequestSpec -> client: "+requestData+" - token:"+headers.get("Authorization"));
        //if a client name is sent in request data then getting it's token
        if (requestData.length() < 40 && !requestData.equalsIgnoreCase("regression_client") && !requestData.equalsIgnoreCase("UMS")) {
            Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" +
                    ConfigLoader.getInstance().getClient(requestData));
            headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        }

        switch (requestData.toUpperCase()) {
            case "UMS":
                uri = ConfigLoader.getInstance().getUmsUrl();
                break;

            default:
                uri = ConfigLoader.getInstance().getBaseUrl();
                break;

        }

        if (requestData.length() > 100) {
            headers.put("Authorization", "Bearer " + requestData);
        }

//        logInfo("getRequestSpec -> client: "+requestData+" - token:"+headers.get("Authorization"));

        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addHeaders(headers)
                .setContentType(ContentType.URLENC)
                .setAccept(ContentType.URLENC)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpec(String requestData) {
        String uri = "";
        Map<String, String> headers = new HashMap<>();
        headers = defaultHeaders();
//        logInfo("getRequestSpec -> client: "+requestData+" - token:"+headers.get("Authorization"));
        //if a client name is sent in request data then getting it's token
        if (requestData.length() < 40 && !requestData.equalsIgnoreCase("regression_client")
                && !requestData.equalsIgnoreCase("UMS")
                && !requestData.equalsIgnoreCase("ApiSanity")
                && !requestData.equalsIgnoreCase("ProdSanity")) {
            Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" +
                    ConfigLoader.getInstance().getClient(requestData));
            headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        }

        switch (requestData.toUpperCase()) {
            case "UMS":
                uri = ConfigLoader.getInstance().getUmsUrl();
                break;

            case "APISANITY":
                uri = ConfigLoader.getInstance().getApiSanityUrl();
                break;

            case "PRODSANITY":
                uri = ProdConfigLoader.getInstance().getBaseUrl();
                headers = prodHeaders();
                break;

            case "CMS":
                uri = ConfigLoader.getInstance().getClaimPanelUrl();
                break;

            default:
                uri = ConfigLoader.getInstance().getBaseUrl();
                break;

        }

        if (requestData.length() > 100) {
            headers.put("Authorization", "Bearer " + requestData);
        }

//        logInfo("getRequestSpec -> client: "+requestData+" - token:"+headers.get("Authorization"));

        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpec(HashMap requestData) {
        String uri = "";
        Map<String, String> headers = new HashMap<>();
        headers = defaultHeaders();

        //if a client name is sent in request data then getting it's token
        if (requestData.containsKey("client")) {
            Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" +
                    ConfigLoader.getInstance().getClient(requestData.get("client").toString()));
            headers.put("Authorization", "Token " + clientDetails.get("token").toString());

        }

        switch (requestData.get("domain").toString().toUpperCase()) {
            case "UMS":
                uri = ConfigLoader.getInstance().getUmsUrl();
                break;

            case "APISANITY":
                uri = ConfigLoader.getInstance().getApiSanityUrl();
                break;

            case "PRODSANITY":
                uri = ProdConfigLoader.getInstance().getBaseUrl();
                headers = prodHeaders();

            case "CMS":
                uri = ConfigLoader.getInstance().getClaimPanelUrl();
                break;

            default:
                uri = ConfigLoader.getInstance().getBaseUrl();
                break;

        }

        if (requestData.containsKey("auth")) {
            headers.put("Authorization", "Bearer " + requestData.get("auth"));
        }

        return new RequestSpecBuilder()
                .setBaseUri(uri)
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpecUID(String requestData) {
        Map<String, String> headers = defaultHeadersBearer(requestData);
        headers.put("clientuuid", YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient()).get("client_uuid").toString());
        headers.put("username", YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient()).get("name").toString());
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestQCSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getInstance().getQCAnswerUrl())
                .addHeaders(QCHeadersBearer())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getEdtVsStageRequestSpec2(String enviorment) {
        String base_uri = ConfigLoader.getInstance().getBaseUrl();

        if(enviorment.equalsIgnoreCase("edt")) {
            base_uri = ConfigLoader.getInstance().getEdtBaseUrl();
        }else if(enviorment.equalsIgnoreCase("staging")) {
            base_uri = ConfigLoader.getInstance().getStagingBaseUrl();
        }
        else if(enviorment.equalsIgnoreCase("Trackin")){
            base_uri = ConfigLoader.getInstance().getTrackingUrl();
        }
        else if(enviorment.equalsIgnoreCase("manifest_regression_client")){
            base_uri = ConfigLoader.getInstance().getNewManifestUrl();
        }


        Map<String, String> headers = new HashMap<>();
        headers = ManifestationServiceHeaders();
        //if a client name is sent in request data then getting it's token
		/*if (Client.length() < 40 && !Client.equalsIgnoreCase("ManifestationClient") && !Client.equalsIgnoreCase("UMS")) {
			Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_" +
		    ConfigLoader.getInstance().getClient(Client));
			headers.put("Authorization", "Token " + clientDetails.get("token").toString());
        }else{
            headers = ManifestationServiceHeaders();
        }
        */

        return new RequestSpecBuilder()
                .setBaseUri(base_uri)
                .addHeaders(headers)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    //Todo before impl new spec builder please check can we use the above http template spec builder for the same
}