/**
 * Commonly used Test methods are written here
 **/

package com.delhivery.project1.controllers;

import com.delhivery.core.BaseTest;
import com.delhivery.project1.pojo.module.request.ContainerCreateRequest;
import com.delhivery.project1.pojo.module.response.ApiResponse;
import com.delhivery.project1.applicationApi.module1;
import io.restassured.response.Response;

import static com.delhivery.project1.modules.module_1.RequestBuilder.containerCreateRequestBuilder;
import static com.delhivery.core.utils.Assertions.assertStatusCode;
import static com.delhivery.core.utils.Utilities.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Controller extends BaseTest {


    public String createContainer(String status, boolean movable) {
        String containerId = generateUniqueEntity("container");
        logInfo("Creating new picked container: " + containerId);
        ContainerCreateRequest containerCreateRequest = containerCreateRequestBuilder(containerId, status, movable);
        Response response = module1.createContainer(containerCreateRequest);
        assertStatusCode(200, response);
        logCodeBlock("Response: " + response.asPrettyString());
        ApiResponse apiResponse = response.as(ApiResponse.class);
        logInfo("Asserting success = true");
        assertThat(apiResponse.getSuccess(), equalTo(true));
        logInfo("Asserting success message = container " + containerId + " created successfully!");
        assertThat(apiResponse.getMessage(), equalTo("container "+containerId+" created successfully!"));
        return containerId;
    }
}