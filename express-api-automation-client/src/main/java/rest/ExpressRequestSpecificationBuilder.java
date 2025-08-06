package rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import common.template.ExpressHttpReqObject;
import common.utils.ExpressConstant;

import java.util.HashMap;
import java.util.Map;

public class ExpressRequestSpecificationBuilder {
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpec(ExpressHttpReqObject expressHttpReqObject) {
        return new RequestSpecBuilder()
                .setBaseUri(expressHttpReqObject.getUrl())
                .addHeaders(defaultHeaders(expressHttpReqObject))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    private static Map<String, String> defaultHeaders(ExpressHttpReqObject expressHttpReqObject) {
        Map<String, String> headers = new HashMap<>();

        if(expressHttpReqObject.getIsHeaderDecorationRequired()!=null && !expressHttpReqObject.getIsHeaderDecorationRequired()){
            return expressHttpReqObject.getHeaders();
        }

        if (expressHttpReqObject.getToken() == null) {
            return headers;
        }

        if (expressHttpReqObject.getToken().length() > 50 || expressHttpReqObject.getToken().
                contains(ExpressConstant.AUTH_USER_TOKEN_PREFIX)) {
            headers.put("Authorization", addTokenPrefix(expressHttpReqObject.getToken(),
                    ExpressConstant.AUTH_USER_TOKEN_PREFIX));
            return headers;
        }
        headers.put("Authorization", addTokenPrefix(expressHttpReqObject.getToken(), ExpressConstant.
                CLIENT_TOKEN_PREFIX));

        return headers;
    }

    public static String addTokenPrefix(String input, String prefix) {
        if (input == null) {
            throw new RuntimeException("Authorization Token is null while preparing request");
        }
        if (input.trim().startsWith(prefix)) {
            return input;
        }
        return prefix + " " + input.trim();
    }
}
