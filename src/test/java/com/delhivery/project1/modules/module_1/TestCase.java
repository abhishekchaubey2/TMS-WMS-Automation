/**
 * Sample test case file
 **/

package com.delhivery.project1.modules.module_1;

import com.delhivery.core.BaseTest;
import com.delhivery.project1.applicationApi.module1;
import com.delhivery.project1.controllers.Controller;
import com.delhivery.core.utils.RetryAnalyzer;
import com.delhivery.project1.pojo.module.request.ContainerCreateRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Assertions.assertStatusCode;
import static com.delhivery.core.utils.Utilities.*;
import static com.delhivery.core.utils.Utilities.logInfo;
import static com.delhivery.project1.modules.module_1.RequestBuilder.containerCreateRequestBuilder;


public class TestCase extends BaseTest {

    Controller C1 = new Controller();

    @Test(priority = 1, groups = {"module_1"}, retryAnalyzer = RetryAnalyzer.class)
    public void createContainer() {
        C1.createContainer(null, true);
    }

    @Test(priority = 2, groups = {"module_1"}, retryAnalyzer = RetryAnalyzer.class)
    public void createContainerNegativeCase() {
        String containerId = generateUniqueEntity("container");
        logInfo("Creating new picked container: " + containerId);
        ContainerCreateRequest containerCreateRequest = containerCreateRequestBuilder(containerId, "fixed", false);
        Response response = module1.createContainer(containerCreateRequest);
        assertStatusCode(400, response);
        logCodeBlock("Response: " + response.asPrettyString());
    }
}