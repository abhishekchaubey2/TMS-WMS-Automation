package com.delhivery.Express.testModules.es;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentTypeShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.MPSAssociateDetails.response.MPSAssociateDetailsResPayload;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MPSAssociate extends BaseTest {
    DifferentTypeShipments differentTypeShipments = new DifferentTypeShipments();
    ApiController apiController = new ApiController();


    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class)
    private void testMPSAssociateDetails(String scenario, String type) {
        HashMap<String, String> payload = new HashMap<>();
        ArrayList<String> wbn = differentTypeShipments.DifferentTypeShipments(type, payload);
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        param.put("associates", "True");
        param.put("wbn", wbn.get(0));
        MPSAssociateDetailsResPayload apiResponse = apiController.getMPSAssociateDetails("edt", param);
        assertResponse(type, apiResponse);
        MPSAssociateDetailsResPayload stageApiResponse = apiController.getMPSAssociateDetails("staging", param);
        assertResponse(type, stageApiResponse);
    }

    private void assertResponse(String type, MPSAssociateDetailsResPayload apiResponse) {
        if (!type.contains("MPS")) {
            Assertions.assertKeyValue("Remark", "Package is not multipiece shipment", apiResponse.getRemark());
        } else {
            Assertions.assertKeyValue("Remark", "", apiResponse.getRemark());
        }
    }
}
