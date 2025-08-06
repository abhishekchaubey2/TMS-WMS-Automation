/**
 * Sample request builder file
 **/

package com.delhivery.project1.modules.module_1;

import com.delhivery.core.BaseTest;
import com.delhivery.project1.pojo.module.request.ContainerCreateRequest;
import com.delhivery.core.utils.ConfigLoader;

public class RequestBuilder extends BaseTest {

    private static final String FC_UUID = ConfigLoader.getInstance().getFcUuid();

    public static ContainerCreateRequest containerCreateRequestBuilder(String containerId, String status, boolean movable) {
        return ContainerCreateRequest.builder()
                .containerId(containerId)
                .status(status)
                .length(1)
                .width(2)
                .height(3)
                .volume(10)
                .fulfillmentCenterName(FC_UUID)
                .velocity("1")
                .aisle("1")
                .level("1")
                .column("1")
                .zone("ZONE1")
                .zoneType(null)
                .containerType("shelf")
                .isMovable(movable)
                .audit(false)
                .maxWt(5)
                .floor("First")
                .build();
    }
}

