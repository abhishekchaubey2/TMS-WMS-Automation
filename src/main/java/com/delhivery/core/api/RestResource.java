// package com.delhivery.core.api;

// import com.delhivery.core.utils.ConfigLoader;
// import com.delhivery.core.utils.CsvFileUtils;
// import com.delhivery.core.utils.HttpTemplateObject;
// import com.delhivery.core.utils.Utilities;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import io.restassured.http.ContentType;
// import io.restassured.response.Response;
// import io.restassured.specification.QueryableRequestSpecification;
// import io.restassured.specification.RequestSpecification;
// import io.restassured.specification.SpecificationQuerier;

// import java.io.File;
// import java.util.HashMap;
// import java.util.Map;

// import static com.delhivery.core.api.Routes.CMU_PUSH_API_MANIFEST;
// import static com.delhivery.core.api.SpecBuilder.*;
// import static com.delhivery.core.api.SpecBuilder.getEdtVsStageRequestSpec;
// import static com.delhivery.core.api.SpecBuilder.getEdtVsStageRequestSpec2;
// import static com.delhivery.core.utils.Utilities.logCodeBlock;
// import static com.delhivery.core.utils.Utilities.logInfo;
// import static io.restassured.RestAssured.given;

// public class RestResource {
// //	private static Response response;
// //    private static QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec());
// //    private static RequestSpecification getRequestSpec;
// //    private static RequestSpecification getTrackingSpec,getHqTrakingSpec;

//     public static Response postAccount(HashMap<String, String> requestBody) {
//         RequestSpecification accountRequestSpec = getAccountRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(accountRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + Routes.FETCH_USER_JWT_TOKEN);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(accountRequestSpec)
//                 .body(requestBody)
//                 .when().post(Routes.FETCH_USER_JWT_TOKEN)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();
//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, Object requestBody) {
// //      QueryableRequestSpecification queryRequest = null;
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec())
//                 .body(requestBody)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, String enviorment, Object requestBody) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getEdtVsStageRequestSpec(enviorment, "regression_client");
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
// //        QueryableRequestSpecification queryRequest = null;
//         if (enviorment.contentEquals("edt")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path);
//         } else {
//             logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path);
//         }
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getEdtVsStageRequestSpec(enviorment, "regression_client"))
//                 .body(requestBody)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, String paramKey, String paramValue) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());


//         Response response = given(getRequestSpec())
//                 .queryParams(paramKey, paramValue)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, Map<String, String> paramsMap) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         String params = "";
//         int i = 1;
//         for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
//             params = params + entry.getKey() + "=" + entry.getValue();
//             if (i != paramsMap.size()) {
//                 params = params + "&amp";
//             }
//             i++;
//         }
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec())
//                 .params(paramsMap)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, Map<String, String> paramsMap, String token) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpecBearer(token));
//         String params = "";
//         int i = 1;
//         for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
//             params = params + entry.getKey() + "=" + entry.getValue();
//             if (i != paramsMap.size()) {
//                 params = params + "&amp";
//             }
//             i++;
//         }

//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpecBearer(token))
//                 .params(paramsMap)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();
//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, String paramKey, String paramValue) {
// //      QueryableRequestSpecification queryRequest = null;
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getRequestSpec())
//                 .param(paramKey, paramValue)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }


//     public static Response getBearer(String path, String paramKey, String paramValue, String token) {
// //      QueryableRequestSpecification queryRequest = null;
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getRequestSpecBearer(token))
//                 .param(paramKey, paramValue)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, String enviorment, String paramKey, String paramValue) {
// //      QueryableRequestSpecification queryRequest = null;
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getEdtVsStageRequestSpec(enviorment, "regression_client");
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         if (enviorment.contentEquals("edt")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path + "?" + paramKey + "=" + paramValue);
//         } else {
//             logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path + "?" + paramKey + "=" + paramValue);
//         }
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getEdtVsStageRequestSpec(enviorment, "regression_client"))
//                 .param(paramKey, paramValue)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }


//     public static Response get(String path) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec())
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, Object requestBody) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec())
//                 .body(requestBody)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response put(String path, Object requestBody) {
// //      QueryableRequestSpecification queryRequest = null;
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec())
//                 .body(requestBody)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response put(String path, Object requestBody, String token) {
//         RequestSpecification getRequestSpec = getRequestSpecUID(token);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec)
//                 .body(requestBody)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response put(String path, Object requestBody, String paramKey, String paramValue) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec())
//                 .param(paramKey, paramValue)
//                 .body(requestBody)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response postFormData(String path, Map<String, String> data) {
//         RequestSpecification getRequestSpec = getRequestSpecFormData(data);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         System.out.println(data);
//         Response response = given(getRequestSpec)
//                 .multiPart(data.get("file_param_name"), new File(data.get("file_path")))
//                 .multiPart(data.get("param_name"), data.get("param_value"), "text/plain")
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response putFormData(String path, Map<String, String> data, String paramKey, String paramValue, String filePath) {
//         RequestSpecification getRequestSpec = getRequestSpecFormData(data);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec)
//                 .multiPart("file", new File(filePath))
//                 .multiPart(paramKey, paramValue)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response putFormData(String path, Map<String, String> data, String paramKey, String paramValue) {
//         Response response = given(getRequestSpecFormData(data))
//                 .multiPart(paramKey, paramValue)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response put(String path, Object requestBody, String paramKey, String paramValue, String application) {
//         RequestSpecification getRequestSpec = getRequestSpecOdx();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpecOdx())
//                 .param(paramKey, paramValue)
//                 .body(requestBody)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }


//     public static Response post(String path, Map<String, String> paramsMap) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         String params = "";
//         int i = 1;
//         for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
//             params = params + entry.getKey() + "=" + entry.getValue();
//             if (i != paramsMap.size()) {
//                 params = params + "&amp";
//             }
//             i++;
//         }
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec())
//                 .queryParams(paramsMap)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, Object requestBody, String requestData) {
//         RequestSpecification getRequestSpec = getRequestSpec(requestData);
//         if (path.contentEquals(CMU_PUSH_API_MANIFEST)) {
//             getRequestSpec = getRequestSpecCmu(requestData);
//         } else {
//             getRequestSpec = getRequestSpec(requestData);
//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());
// //        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
// //        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec)
//                 .body(requestBody)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();


// //        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
// //        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());
//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;

//         if (requestData.containsKey("upl")) {
//             path = path.replace("uplId", requestData.get("upl"));

//         } else if (requestData.containsKey("clientUuid")) {
//             path = path + requestData.get("clientUuid");

//         }

//         if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
//             getRequestSpec = getRequestSpec(requestData);

//         } else if (requestData.containsKey("auth")) {
//             getRequestSpec = getRequestSpec(requestData.get("auth"));

//         } else if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));

//         } else {
//             getRequestSpec = getRequestSpec();

//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));

//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getMultipleParams(String path, Map<String, String> paramsMap) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         String params = "";
//         int i = 1;
//         for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
//             params = params + entry.getKey() + "=" + entry.getValue();
//             if (i != paramsMap.size()) {
//                 params = params + "&amp";
//             }
//             i++;
//         }
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec())
//                 .params(paramsMap)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, String paramKey, String paramValue, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();
//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getRequestSpec)
//                 .param(paramKey, paramValue)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, String paramKey, Object paramValue, HashMap<String, String> requestData) throws JsonProcessingException {
//         RequestSpecification getRequestSpec = null;

//         if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
//             getRequestSpec = getRequestSpec(requestData);

//         } else {
//             getRequestSpec = getRequestSpec();

//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);

//         String paramVal = Utilities.jsonObjectToString(paramValue);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramVal);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getRequestSpec)
//                 .param(paramKey, paramVal)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getTrack(String path, Map<String, String> data) {
//         RequestSpecification getTrackingSpec = getTrakingSpec();
//         if (data.containsKey("client")) {
//             getTrackingSpec = getTrackingSpec(data.get("client"));
//         }
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getTrackingSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + "wbns" + "=" + data.getOrDefault("wbns", null));
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getTrackingSpec)
//                 .params(data)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getHqTrack(String path, Map<String, String> data) {
//         RequestSpecification getHqTrakingSpec = getHqTrakingSpec();
//         if (data.containsKey("client")) {
//             getHqTrakingSpec = getTrackingSpec(data.get("client"));
//         }
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getHqTrakingSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + "waybill" + "=" + data.getOrDefault("waybill", null));
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getHqTrakingSpec)
//                 .params(data)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getdiffenv(String path, String enviorment, Map<String, String> data) {
//         if (enviorment.contentEquals("edt")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path + "?" + data);
//         } else if (enviorment.contentEquals("Trackin")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getTrackingUrl() + path + "?" + data);
//         } else {
//             logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path + "?" + data);
//         }
//         // logInfo("Request headers : "+queryRequest.getHeaders());
//         Response response = given(getEdtVsStageRequestSpec(enviorment))
//                 .params(data)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getdiffenv(String path, String env) {
//         if (env.contentEquals("edt")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path);
//         } else if (env.contentEquals("Trackin")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getTrackingUrl() + path);
//         } else {
//             logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path);
//         }

//         Response response = given(getEdtVsStageRequestSpec(env))
//                 .when()
//                 .get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, Object requestBody, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         if (requestData.containsKey("env")) {
//             getRequestSpec = getEdtVsStageRequestSpec(requestData.get("env"));
//         } else {
//             getRequestSpec = getRequestSpec();
//         }

//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
//             getRequestSpec = getRequestSpec(requestData);

//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec)
//                 .body(requestBody)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response put(String path, Object requestBody, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();
//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec)
//                 .body(requestBody)
//                 .when().put(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, HashMap<String, String> paramsMap, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();
//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         String uriParams = "";
//         String paramType = "?";
//         for (Map.Entry<String, String> set : paramsMap.entrySet()) {
//             uriParams = uriParams + paramType + set.getKey() + "=" + set.getValue();
//             if (paramType.equals("?")) {
//                 paramType = "&";
//             }
//         }

//         logInfo("Request url : " + queryRequest.getBaseUri() + path + uriParams);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec)
//                 .params(paramsMap)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get(String path, String paramValue, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         } else {
//             getRequestSpec = getRequestSpec();

//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + paramValue + "/.json");
//         logInfo("Request headers : " + queryRequest.getHeaders());
// //        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
// //        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());

//         Response response = given(getRequestSpec)
//                 .when().get(path + paramValue + "/.json")
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

// //        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
// //        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());
//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response Get(String path, String enviorment, String paramValue, String Client) {
//         if (enviorment.contentEquals("edt")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path + paramValue + "/.json");
//         } else {
//             logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path + paramValue + "/.json");
//         }
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getEdtVsStageRequestSpec(enviorment, Client);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getEdtVsStageRequestSpec(enviorment, Client))
//                 .when().get(path + paramValue + "/.json")
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, String enviorment, Object requestBody, String Client) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getEdtVsStageRequestSpec("staging", Client);
//         if (enviorment.contentEquals("edt")) {
//             logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path);
//             getRequestSpec = getEdtVsStageRequestSpec("edt", Client);
//         } else {
//             logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path);
//         }
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         //resetting queryrequest value to default
//         queryRequest = SpecificationQuerier.query(getRequestSpec());
//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getEdtVsStageRequestSpec(enviorment, Client))
//                 .body(requestBody)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getQCAnswers(String path, String paramKey, String paramValue) {
// //      QueryableRequestSpecification queryRequest = null;
//         RequestSpecification getRequestSpec = getRequestQCSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
//         logInfo("Request headers : " + queryRequest.getHeaders());
//         Response response = given(getRequestQCSpec())
//                 .param(paramKey, paramValue)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response patch(String path, Object requestBody, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();
//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
//             getRequestSpec = getRequestSpec(requestData);

//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec)
//                 .body(requestBody)
//                 .when().patch(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post(String path, String csvFilePath, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();

//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
//             getRequestSpec = getRequestSpec(requestData);

//         }

//         getRequestSpec.contentType(ContentType.MULTIPART);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         File csvFile = new File(csvFilePath);
//         CsvFileUtils.printCSVFileData(csvFile);

//         Response response = given(getRequestSpec)
//                 .multiPart("csv", csvFile, "text/csv")
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response patch(String path, String csvFilePath, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();

//         if (requestData.containsKey("client")) {
//             getRequestSpec = getRequestSpec(requestData.get("client"));
//         }

//         if (requestData.containsKey("jobType")) {
//             getRequestSpec = getRequestSpec(requestData.get("jobType"));
//         }

//         if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
//             getRequestSpec = getRequestSpec(requestData);

//         }

//         getRequestSpec.contentType(ContentType.MULTIPART);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         File csvFile = new File(csvFilePath);
//         CsvFileUtils.printCSVFileData(csvFile);

//         Response response = given(getRequestSpec)
//                 .multiPart("csv", csvFile, "text/csv")
//                 .when().patch(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response post2(String environment, String path, Object requestBody, HashMap<String, Object> requestData, String Client) {
//         RequestSpecification getRequestSpec;
//         getRequestSpec = getRequestSpec();
//         getRequestSpec = getEdtVsStageRequestSpec(environment, Client);

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (!requestBody.getClass().getSimpleName().equals("String")) {
//             try {
//                 logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
//             } catch (JsonProcessingException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             logCodeBlock("Request body : " + requestBody);
//         }

//         Response response = given(getRequestSpec)
//                 .body(requestBody)
//                 .when().post(path)
//                 .then().spec(getResponseSpec())
//                 .extract().response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response get2(String environment, String path, HashMap<String, String> requestData) {
//         RequestSpecification getRequestSpec;

//         if (requestData.containsKey("upl")) {
//             path = path.replace("uplId", requestData.get("upl"));
//         }

//         if (requestData.containsKey("auth")) {
//             getRequestSpec = getRequestSpec(requestData.get("auth"));

//         } else if (environment.equalsIgnoreCase("manifest_regression_client")) {
//             getRequestSpec = getEdtVsStageRequestSpec2(environment);

//         } else {
//             getRequestSpec = getEdtVsStageRequestSpec(environment);

//         }

//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }

//     public static Response getMultipleParams(String path, HashMap<String, Object> paramsMap) {
//         RequestSpecification getRequestSpec = getRequestSpec();
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//         String params = "";
//         int i = 1;
//         for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
//             params = params + entry.getKey() + "=" + entry.getValue();
//             if (i != paramsMap.size()) {
//                 params = params + "&amp";
//             }
//             i++;
//         }
//         logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         Response response = given(getRequestSpec())
//                 .params(paramsMap)
//                 .when().get(path)
//                 .then().spec(getResponseSpec())
//                 .extract()
//                 .response();

//         logCodeBlock("API Response :: " + response.getBody().asPrettyString());
//         return response;
//     }


//     //TODO From here we will use all method call with HttpObject Template
//     public static Response get(String path, HttpTemplateObject expressHttpReqObject) {
//         RequestSpecification requestSpec = SpecBuilder.getRequestSpec(expressHttpReqObject);
//         QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);

//         logInfo("Request url : " + queryRequest.getBaseUri() + path);
//         logInfo("Request headers : " + queryRequest.getHeaders());

//         if (expressHttpReqObject.getQueryParam() != null) {
//             return given(requestSpec)
//                     .params(expressHttpReqObject.getQueryParam())
//                     .when().get(path)
//                     .then().spec(SpecBuilder.getResponseSpec())
//                     .extract()
//                     .response();
//         }

//         return given(requestSpec)
//                 .when().get(path)
//                 .then().spec(SpecBuilder.getResponseSpec())
//                 .extract()
//                 .response();
//     }

//     public static Response put(String path, HttpTemplateObject expressHttpReqObject) {
//         RequestSpecification requestSpec = SpecBuilder.getRequestSpec(expressHttpReqObject);

//         return given(requestSpec)
//                 .body(expressHttpReqObject.getRequestBody())
//                 .when().put(path)
//                 .then().spec(SpecBuilder.getResponseSpec())
//                 .extract().response();
//     }

//     public static Response post(String path, HttpTemplateObject expressHttpReqObject) {
//         RequestSpecification requestSpec = SpecBuilder.getRequestSpec(expressHttpReqObject);

//         return given(requestSpec)
//                 .body(expressHttpReqObject.getRequestBody())
//                 .when().post(path)
//                 .then().spec(SpecBuilder.getResponseSpec())
//                 .extract().response();
//     }

//     //TODO before impl new rest method please check once, can we use exiting http object base template or not
//     //todo if not then impl new method above these comments, impl with HttpObject template

// }

package com.delhivery.core.api;

import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.CsvFileUtils;
import com.delhivery.core.utils.HttpTemplateObject;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.delhivery.core.api.Routes.CMU_PUSH_API_MANIFEST;
import static com.delhivery.core.api.SpecBuilder.*;
//import static com.delhivery.core.api.SpecBuilder.getEdtVsStageRequestSpec;
//import static com.delhivery.core.api.SpecBuilder.getEdtVsStageRequestSpec2;
import static com.delhivery.core.utils.Utilities.logCodeBlock;
import static com.delhivery.core.utils.Utilities.logInfo;
import static io.restassured.RestAssured.given;

public class RestResource {
//	private static Response response;
//    private static QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec());
//    private static RequestSpecification getRequestSpec;
//    private static RequestSpecification getTrackingSpec,getHqTrakingSpec;

    public static Response postAccount(HashMap<String, String> requestBody) {
        RequestSpecification accountRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(accountRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + Routes.FETCH_USER_JWT_TOKEN);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(accountRequestSpec)
                .body(requestBody)
                .when().post(Routes.FETCH_USER_JWT_TOKEN)
                .then().spec(getResponseSpec())
                .extract()
                .response();
        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, Object requestBody) {
//      QueryableRequestSpecification queryRequest = null;
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec())
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, String enviorment, Object requestBody) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getEdtVsStageRequestSpec(enviorment, "regression_client");
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
//        QueryableRequestSpecification queryRequest = null;
        if (enviorment.contentEquals("edt")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path);
        } else {
            logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path);
        }
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getEdtVsStageRequestSpec(enviorment, "regression_client"))
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, String paramKey, String paramValue) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());


        Response response = given(getRequestSpec())
                .queryParams(paramKey, paramValue)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, Map<String, String> paramsMap) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        String params = "";
        int i = 1;
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params = params + entry.getKey() + "=" + entry.getValue();
            if (i != paramsMap.size()) {
                params = params + "&amp";
            }
            i++;
        }
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec())
                .params(paramsMap)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, Map<String, String> paramsMap, String token) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpecBearer(token));
        String params = "";
        int i = 1;
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params = params + entry.getKey() + "=" + entry.getValue();
            if (i != paramsMap.size()) {
                params = params + "&amp";
            }
            i++;
        }

        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpecBearer(token))
                .params(paramsMap)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, String paramKey, String paramValue) {
//      QueryableRequestSpecification queryRequest = null;
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getRequestSpec())
                .param(paramKey, paramValue)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }


    public static Response getBearer(String path, String paramKey, String paramValue, String token) {
//      QueryableRequestSpecification queryRequest = null;
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getRequestSpecBearer(token))
                .param(paramKey, paramValue)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, String enviorment, String paramKey, String paramValue) {
//      QueryableRequestSpecification queryRequest = null;
        RequestSpecification getRequestSpec;
        getRequestSpec = getEdtVsStageRequestSpec(enviorment, "regression_client");
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        if (enviorment.contentEquals("edt")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path + "?" + paramKey + "=" + paramValue);
        } else {
            logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path + "?" + paramKey + "=" + paramValue);
        }
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getEdtVsStageRequestSpec(enviorment, "regression_client"))
                .param(paramKey, paramValue)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }


    public static Response get(String path) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec())
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, Object requestBody) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec())
                .body(requestBody)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response put(String path, Object requestBody) {
//      QueryableRequestSpecification queryRequest = null;
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec())
                .body(requestBody)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response put(String path, Object requestBody, String token) {
        RequestSpecification getRequestSpec = getRequestSpecUID(token);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec)
                .body(requestBody)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response put(String path, Object requestBody, String paramKey, String paramValue) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec())
                .param(paramKey, paramValue)
                .body(requestBody)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response postFormData(String path, Map<String, String> data) {
        RequestSpecification getRequestSpec = getRequestSpecFormData(data);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());
        System.out.println(data);
        Response response = given(getRequestSpec)
                .multiPart(data.get("file_param_name"), new File(data.get("file_path")))
                .multiPart(data.get("param_name"), data.get("param_value"), "text/plain")
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response putFormData(String path, Map<String, String> data, String paramKey, String paramValue, String filePath) {
        RequestSpecification getRequestSpec = getRequestSpecFormData(data);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec)
                .multiPart("file", new File(filePath))
                .multiPart(paramKey, paramValue)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response putFormData(String path, Map<String, String> data, String paramKey, String paramValue) {
        Response response = given(getRequestSpecFormData(data))
                .multiPart(paramKey, paramValue)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response put(String path, Object requestBody, String paramKey, String paramValue, String application) {
        RequestSpecification getRequestSpec = getRequestSpecOdx();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpecOdx())
                .param(paramKey, paramValue)
                .body(requestBody)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }


    public static Response post(String path, Map<String, String> paramsMap) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        String params = "";
        int i = 1;
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params = params + entry.getKey() + "=" + entry.getValue();
            if (i != paramsMap.size()) {
                params = params + "&amp";
            }
            i++;
        }
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec())
                .queryParams(paramsMap)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, Object requestBody, String requestData) {
        RequestSpecification getRequestSpec = getRequestSpec(requestData);
        if (path.contentEquals(CMU_PUSH_API_MANIFEST)) {
            getRequestSpec = getRequestSpecCmu(requestData);
        } else {
            getRequestSpec = getRequestSpec(requestData);
        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());
//        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
//        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec)
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();


//        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
//        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());
        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;

        if (requestData.containsKey("upl")) {
            path = path.replace("uplId", requestData.get("upl"));

        } else if (requestData.containsKey("clientUuid")) {
            path = path + requestData.get("clientUuid");

        }

        if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
            getRequestSpec = getRequestSpec(requestData);

        } else if (requestData.containsKey("auth")) {
            getRequestSpec = getRequestSpec(requestData.get("auth"));

        } else if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));

        } else {
            getRequestSpec = getRequestSpec();

        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));

        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getMultipleParams(String path, Map<String, String> paramsMap) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        String params = "";
        int i = 1;
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            params = params + entry.getKey() + "=" + entry.getValue();
            if (i != paramsMap.size()) {
                params = params + "&amp";
            }
            i++;
        }
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec())
                .params(paramsMap)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, String paramKey, String paramValue, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();
        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getRequestSpec)
                .param(paramKey, paramValue)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, String paramKey, Object paramValue, HashMap<String, String> requestData) throws JsonProcessingException {
        RequestSpecification getRequestSpec = null;

        if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
            getRequestSpec = getRequestSpec(requestData);

        } else {
            getRequestSpec = getRequestSpec();

        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);

        String paramVal = Utilities.jsonObjectToString(paramValue);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramVal);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getRequestSpec)
                .param(paramKey, paramVal)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getTrack(String path, Map<String, String> data) {
        RequestSpecification getTrackingSpec = getTrakingSpec();
        if (data.containsKey("client")) {
            getTrackingSpec = getTrackingSpec(data.get("client"));
        }
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getTrackingSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + "wbns" + "=" + data.getOrDefault("wbns", null));
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getTrackingSpec)
                .params(data)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getHqTrack(String path, Map<String, String> data) {
        RequestSpecification getHqTrakingSpec = getHqTrakingSpec();
        if (data.containsKey("client")) {
            getHqTrakingSpec = getTrackingSpec(data.get("client"));
        }
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getHqTrakingSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + "waybill" + "=" + data.getOrDefault("waybill", null));
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getHqTrakingSpec)
                .params(data)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getdiffenv(String path, String enviorment, Map<String, String> data) {
        if (enviorment.contentEquals("edt")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path + "?" + data);
        } else if (enviorment.contentEquals("Trackin")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getTrackingUrl() + path + "?" + data);
        } else {
            logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path + "?" + data);
        }
        // logInfo("Request headers : "+queryRequest.getHeaders());
        Response response = given(getEdtVsStageRequestSpec(enviorment))
                .params(data)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getdiffenv(String path, String env) {
        if (env.contentEquals("edt")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path);
        } else if (env.contentEquals("Trackin")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getTrackingUrl() + path);
        } else {
            logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path);
        }

        Response response = given(getEdtVsStageRequestSpec(env))
                .when()
                .get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, Object requestBody, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        if (requestData.containsKey("env")) {
            getRequestSpec = getEdtVsStageRequestSpec(requestData.get("env"));
        } else {
            getRequestSpec = getRequestSpec();
        }

        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
            getRequestSpec = getRequestSpec(requestData);

        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec)
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response put(String path, Object requestBody, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();
        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec)
                .body(requestBody)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, HashMap<String, String> paramsMap, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();
        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        String uriParams = "";
        String paramType = "?";
        for (Map.Entry<String, String> set : paramsMap.entrySet()) {
            uriParams = uriParams + paramType + set.getKey() + "=" + set.getValue();
            if (paramType.equals("?")) {
                paramType = "&";
            }
        }

        logInfo("Request url : " + queryRequest.getBaseUri() + path + uriParams);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec)
                .params(paramsMap)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get(String path, String paramValue, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        } else {
            getRequestSpec = getRequestSpec();

        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + paramValue + "/.json");
        logInfo("Request headers : " + queryRequest.getHeaders());
//        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
//        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());

        Response response = given(getRequestSpec)
                .when().get(path + paramValue + "/.json")
                .then().spec(getResponseSpec())
                .extract()
                .response();

//        logInfo("queryRequest - headers authorization : "+queryRequest.getHeaders().get("Authorization").toString());
//        logInfo("getRequestSpec - headers authorization : "+((RequestSpecificationImpl) getRequestSpec).getHeaders().get("Authorization").toString());
        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response Get(String path, String enviorment, String paramValue, String Client) {
        if (enviorment.contentEquals("edt")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path + paramValue + "/.json");
        } else {
            logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path + paramValue + "/.json");
        }
        RequestSpecification getRequestSpec;
        getRequestSpec = getEdtVsStageRequestSpec(enviorment, Client);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getEdtVsStageRequestSpec(enviorment, Client))
                .when().get(path + paramValue + "/.json")
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, String enviorment, Object requestBody, String Client) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getEdtVsStageRequestSpec("staging", Client);
        if (enviorment.contentEquals("edt")) {
            logInfo("Request url : " + ConfigLoader.getInstance().getEdtBaseUrl() + path);
            getRequestSpec = getEdtVsStageRequestSpec("edt", Client);
        } else {
            logInfo("Request url : " + ConfigLoader.getInstance().getStagingBaseUrl() + path);
        }
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request headers : " + queryRequest.getHeaders());

        //resetting queryrequest value to default
        queryRequest = SpecificationQuerier.query(getRequestSpec());
        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getEdtVsStageRequestSpec(enviorment, Client))
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getQCAnswers(String path, String paramKey, String paramValue) {
//      QueryableRequestSpecification queryRequest = null;
        RequestSpecification getRequestSpec = getRequestQCSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + paramKey + "=" + paramValue);
        logInfo("Request headers : " + queryRequest.getHeaders());
        Response response = given(getRequestQCSpec())
                .param(paramKey, paramValue)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response patch(String path, Object requestBody, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();
        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
            getRequestSpec = getRequestSpec(requestData);

        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec)
                .body(requestBody)
                .when().patch(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post(String path, String csvFilePath, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();

        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
            getRequestSpec = getRequestSpec(requestData);

        }

        getRequestSpec.contentType(ContentType.MULTIPART);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        File csvFile = new File(csvFilePath);
        CsvFileUtils.printCSVFileData(csvFile);

        Response response = given(getRequestSpec)
                .multiPart("csv", csvFile, "text/csv")
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response patch(String path, String csvFilePath, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();

        if (requestData.containsKey("client")) {
            getRequestSpec = getRequestSpec(requestData.get("client"));
        }

        if (requestData.containsKey("jobType")) {
            getRequestSpec = getRequestSpec(requestData.get("jobType"));
        }

        if (requestData.containsKey("auth") && requestData.containsKey("domain")) {
            getRequestSpec = getRequestSpec(requestData);

        }

        getRequestSpec.contentType(ContentType.MULTIPART);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        File csvFile = new File(csvFilePath);
        CsvFileUtils.printCSVFileData(csvFile);

        Response response = given(getRequestSpec)
                .multiPart("csv", csvFile, "text/csv")
                .when().patch(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response post2(String environment, String path, Object requestBody, HashMap<String, Object> requestData, String Client) {
        RequestSpecification getRequestSpec;
        getRequestSpec = getRequestSpec();
        getRequestSpec = getEdtVsStageRequestSpec(environment, Client);

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (!requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(getRequestSpec)
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response get2(String environment, String path, HashMap<String, String> requestData) {
        RequestSpecification getRequestSpec;

        if (requestData.containsKey("upl")) {
            path = path.replace("uplId", requestData.get("upl"));
        }

        if (requestData.containsKey("auth")) {
            getRequestSpec = getRequestSpec(requestData.get("auth"));

        } else if (environment.equalsIgnoreCase("manifest_regression_client")) {
            getRequestSpec = getEdtVsStageRequestSpec2(environment);

        } else {
            getRequestSpec = getEdtVsStageRequestSpec(environment);

        }

        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getMultipleParams(String path, HashMap<String, Object> paramsMap) {
        RequestSpecification getRequestSpec = getRequestSpec();
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(getRequestSpec);
        String params = "";
        int i = 1;
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            params = params + entry.getKey() + "=" + entry.getValue();
            if (i != paramsMap.size()) {
                params = params + "&amp";
            }
            i++;
        }
        logInfo("Request url : " + queryRequest.getBaseUri() + path + "?" + params);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(getRequestSpec())
                .params(paramsMap)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }


    //TODO From here we will use all method call with HttpObject Template
    public static Response get(String path, HttpTemplateObject expressHttpReqObject) {
        RequestSpecification requestSpec = SpecBuilder.getRequestSpec(expressHttpReqObject);
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);

        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (expressHttpReqObject.getQueryParam() != null) {
            return given(requestSpec)
                    .params(expressHttpReqObject.getQueryParam())
                    .when().get(path)
                    .then().spec(SpecBuilder.getResponseSpec())
                    .extract()
                    .response();
        }

        return given(requestSpec)
                .when().get(path)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, HttpTemplateObject expressHttpReqObject) {
        RequestSpecification requestSpec = SpecBuilder.getRequestSpec(expressHttpReqObject);

        return given(requestSpec)
                .body(expressHttpReqObject.getRequestBody())
                .when().put(path)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public static Response post(String path, HttpTemplateObject expressHttpReqObject) {
        RequestSpecification requestSpec = SpecBuilder.getRequestSpec(expressHttpReqObject);

        return given(requestSpec)
                .body(expressHttpReqObject.getRequestBody())
                .when().post(path)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    //TODO before impl new rest method please check once, can we use exiting http object base template or not
    //todo if not then impl new method above these comments, impl with HttpObject template

    // TMS Methods with Authentication
    public static Response postWithAuth(String path, Object requestBody, RequestSpecification requestSpec) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (requestBody != null && !requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (requestBody != null) {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(requestSpec)
                .body(requestBody)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response getWithAuth(String path, RequestSpecification requestSpec) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(requestSpec)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response putWithAuth(String path, Object requestBody, RequestSpecification requestSpec) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (requestBody != null && !requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (requestBody != null) {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(requestSpec)
                .body(requestBody)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response deleteWithAuth(String path, RequestSpecification requestSpec) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        Response response = given(requestSpec)
                .when().delete(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }

    public static Response patchWithAuth(String path, Object requestBody, RequestSpecification requestSpec) {
        QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
        logInfo("Request url : " + queryRequest.getBaseUri() + path);
        logInfo("Request headers : " + queryRequest.getHeaders());

        if (requestBody != null && !requestBody.getClass().getSimpleName().equals("String")) {
            try {
                logCodeBlock("Request body : " + Utilities.jsonObjectToString(requestBody));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (requestBody != null) {
            logCodeBlock("Request body : " + requestBody);
        }

        Response response = given(requestSpec)
                .body(requestBody)
                .when().patch(path)
                .then().spec(getResponseSpec())
                .extract().response();

        logCodeBlock("API Response :: " + response.getBody().asPrettyString());
        return response;
    }
}