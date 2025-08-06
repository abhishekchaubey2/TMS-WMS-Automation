package com.delhivery.project1.applicationApi;

import com.delhivery.core.api.RestResource;
import com.delhivery.project1.pojo.module.request.ContainerCreateRequest;
import io.restassured.response.Response;

import static com.delhivery.core.api.Routes.*;

public class module1 {

    public static Response getRequestStatus(String paramsKey, String paramsValue) {
        return RestResource.get(REQUEST_TRACKER, paramsKey, paramsValue);
    }

    public static Response createContainer(ContainerCreateRequest containerCreateRequest) {
        return RestResource.post(CONTAINER_CREATE, containerCreateRequest);
    }


}

