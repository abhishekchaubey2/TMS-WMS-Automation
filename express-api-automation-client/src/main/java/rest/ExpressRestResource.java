package rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import common.template.ExpressHttpReqObject;

import static io.restassured.RestAssured.given;

public class ExpressRestResource {
    public static Response get(String path, ExpressHttpReqObject expressHttpReqObject) {
        RequestSpecification requestSpec = ExpressRequestSpecificationBuilder.getRequestSpec(expressHttpReqObject);
        if(expressHttpReqObject.getQueryParam()!=null){
            return given(requestSpec)
                    .params(expressHttpReqObject.getQueryParam())
                    .when().get(path)
                    .then().spec(ExpressRequestSpecificationBuilder.getResponseSpec())
                    .extract()
                    .response();
        }

        return given(requestSpec)
                .when().get(path)
                .then().spec(ExpressRequestSpecificationBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, ExpressHttpReqObject expressHttpReqObject) {
        RequestSpecification requestSpec = ExpressRequestSpecificationBuilder.getRequestSpec(expressHttpReqObject);

        return given(requestSpec)
                .body(expressHttpReqObject.getRequestBody())
                .when().put(path)
                .then().spec(ExpressRequestSpecificationBuilder.getResponseSpec())
                .extract().response();
    }

    public static Response post(String path, ExpressHttpReqObject expressHttpReqObject) {
        RequestSpecification requestSpec = ExpressRequestSpecificationBuilder.getRequestSpec(expressHttpReqObject);
        return given(requestSpec)
                .body(expressHttpReqObject.getRequestBody())
                .when().post(path)
                .then().spec(ExpressRequestSpecificationBuilder.getResponseSpec())
                .extract().response();
    }
}
