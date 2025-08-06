package com.delhivery.Express.testModules.es;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentTypeAndStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.InstaBaggingCreateApi.response.InstaBaggingCreateResponsePayload;
import com.delhivery.Express.pojo.InstaBaggingSealApi.response.bar.InstaBaggingSealWithBarResponsePayload;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class InstaBagging extends BaseTest {
    DifferentTypeAndStateShipments differentTypeAndStateShipments = new DifferentTypeAndStateShipments();
    ApiController apiController = new ApiController();
    ThreadLocal<ArrayList<String>> wbnList = new ThreadLocal<>();
    ThreadLocal<String> dgw = new ThreadLocal<>();
    private final String env;

    InstaBagging(String env) {
        this.env = env;
    }

    @Factory(dataProvider = "different_env", dataProviderClass = manifestationData.class)
    private static Object[] getDiffEnv(String scenario, String env) {
        return new Object[]{new InstaBagging(env)};
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class)
    private void testInstaBaggingFlowWithDiffPackageType(String scenario, String type) {
        HashMap<String, String> manifestData = new HashMap<>();
        wbnList.set(differentTypeAndStateShipments.getDifferentTypeAndStateShipments(type, "IN TRANSIT", manifestData));

        manifestData.clear();
        String bagId = "BAG" + wbnList.get().get(0);
        manifestData.put("bs", bagId);
        manifestData.put("bd", "Mumbai MIDC (Maharashtra)");
        manifestData.put("bt", "Surface");
        manifestData.put("st", "Gurgaon_Station");
        dgw.set(Utilities.getUniqueString());
        wbnList.get().forEach(wbn -> {
            manifestData.put("bi", dgw.get());
            manifestData.put("env", env);
            manifestData.put("wbn", wbn);
            InstaBaggingCreateResponsePayload apiResponse = apiController.instaBaggingCreateApi(manifestData);
            if (type.equalsIgnoreCase("NO DATA WITHOUT MANIFESTATION")) {
                Assertions.assertKeyValue("Error : ", wbn + " - Shipment not manifested", apiResponse.getError());
            }
        });
        if (!type.equalsIgnoreCase("NO DATA WITHOUT MANIFESTATION")) {
            manifestData.put("bar", "DV" + wbnList.get().get(0).substring(1, 3) + "L" + wbnList.get().get(0).substring(1));
            InstaBaggingSealWithBarResponsePayload instaBaggingSealWithBarResponsePayload = (InstaBaggingSealWithBarResponsePayload) apiController.instaBaggingSealApi(manifestData);
        }
    }
}
