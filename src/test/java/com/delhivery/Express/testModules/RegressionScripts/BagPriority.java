package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Utilities.logInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BagPriority extends BaseTest {
    ApiController apiCtrl = new ApiController();
    public HashMap<String, String> clData = new HashMap<>();
    private final String product_type, payment_mode;

    public BagPriority() {
        DataProviderClass.fileName = "CmuRegressionData";
        DataProviderClass.sheetName = "Pkg_flows";
        product_type = "B2C";
        payment_mode = "COD";
    }

    @Test(dataProvider = "Different_Bagging_endpoints", dataProviderClass = manifestationData.class, enabled = true)
    public void fetch_bag_score(String Scenario) throws IOException {
        String scoreBagId = "";
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, YamlReader.getYamlValues("Centers.East_Delhi").get("Name").toString(), clData);
        if (Scenario.contains("v2")) {
            scoreBagId = apiCtrl.bagv2Api(wbn);
            Utilities.hardWait(30);
            logInfo("Bag created " + scoreBagId);
        } else if (Scenario.contains("v3")) {
            scoreBagId = apiCtrl.bagv3Api(wbn);
            logInfo("Bag created " + scoreBagId);
            Utilities.hardWait(60);
        } else if (Scenario.contains("v4")) {
            scoreBagId = apiCtrl.bagv4Api(wbn);
            logInfo("Bag created " + scoreBagId);
        } else if (Scenario.contains("instabagging")) {
            scoreBagId = "BAG" + RandomStringUtils.randomNumeric(10);
            data.put("bi", "dgw");
            data.put("bt", "Surface");
            data.put("st", "Ankush");
            data.put("wbn", wbn);
            data.put("bs", scoreBagId);
            data.put("bd", "Mumbai MIDC (Maharashtra)");
            apiCtrl.instaBaggingCreateApi(data);
            apiCtrl.instaBaggingSealApi(data);
        }
        Utilities.hardWait(30);
        apiCtrl.FetchBagScore(scoreBagId);
    }

    @Test(dataProvider = "Different_Bagging_endpoints", dataProviderClass = manifestationData.class, enabled = true)
    public void update_bag_status_hold(String Scenario) throws IOException {
        String scoreBagId = "";
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, YamlReader.getYamlValues("Centers.East_Delhi").get("Name").toString(), clData);
        if (Scenario.contains("v2")) {
            scoreBagId = apiCtrl.bagv2Api(wbn);
            Utilities.hardWait(30);
            logInfo("Bag created " + scoreBagId);
        } else if (Scenario.contains("v3")) {
            scoreBagId = apiCtrl.bagv3Api(wbn);
            logInfo("Bag created " + scoreBagId);
            Utilities.hardWait(60);
        } else if (Scenario.contains("v4")) {
            scoreBagId = apiCtrl.bagv4Api(wbn);
            logInfo("Bag created " + scoreBagId);
        } else if (Scenario.contains("instabagging")) {
            scoreBagId = "BAG" + RandomStringUtils.randomNumeric(10);
            data.put("bi", "dgw");
            data.put("bt", "Surface");
            data.put("st", "Ankush");
            data.put("wbn", wbn);
            data.put("bs", scoreBagId);
            data.put("bd", "Mumbai MIDC (Maharashtra)");
            apiCtrl.instaBaggingCreateApi(data);
            apiCtrl.instaBaggingSealApi(data);
        }
        Utilities.hardWait(30);
        apiCtrl.markBagHold(scoreBagId);
    }

    @Test(dataProvider = "Different_Bagging_endpoints", dataProviderClass = manifestationData.class, enabled = true)
    public void update_bag_status_unhold(String Scenario) throws IOException {
        String scoreBagId = "";
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, YamlReader.getYamlValues("Centers.East_Delhi").get("Name").toString(), clData);
        if (Scenario.contains("v2")) {
            scoreBagId = apiCtrl.bagv2Api(wbn);
            Utilities.hardWait(30);
            logInfo("Bag created " + scoreBagId);
        } else if (Scenario.contains("v3")) {
            scoreBagId = apiCtrl.bagv3Api(wbn);
            logInfo("Bag created " + scoreBagId);
            Utilities.hardWait(60);
        } else if (Scenario.contains("v4")) {
            scoreBagId = apiCtrl.bagv4Api(wbn);
            logInfo("Bag created " + scoreBagId);
        } else if (Scenario.contains("instabagging")) {
            scoreBagId = "BAG" + RandomStringUtils.randomNumeric(10);
            data.put("bi", "dgw");
            data.put("bt", "Surface");
            data.put("st", "Ankush");
            data.put("wbn", wbn);
            data.put("bs", scoreBagId);
            data.put("bd", "Mumbai MIDC (Maharashtra)");
            apiCtrl.instaBaggingCreateApi(data);
            apiCtrl.instaBaggingSealApi(data);
        }
        Utilities.hardWait(30);
        apiCtrl.markBagUnhold(scoreBagId);
    }
}
