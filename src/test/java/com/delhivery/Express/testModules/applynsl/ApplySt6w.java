package com.delhivery.Express.testModules.applynsl;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ApplySt6w extends BaseTest {
    ThreadLocal<ArrayList<String>> waybillList = new ThreadLocal<>();
    ThreadLocal<HashMap<String, String>> manifestData = new ThreadLocal<>();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    ApiController apiCtrl = new ApiController();

    @DataProvider(name = "Different_pdt_payment_types", parallel = true)
    public static Object[][] Different_pdt_payment_types() {
        return new Object[][]{
                {"Scenario:: When pdt = B2C and payment type = prepaid ", "B2C", "prepaid"},
                {"Scenario:: When pdt = B2C and payment type = postpaid ", "B2C", "postpaid"},
                {"Scenario:: When pdt = B2C and payment type = cod ", "B2C", "cod"},
                {"Scenario:: When pdt = B2C and payment type = pickup ", "B2C", "pickup"},
                {"Scenario:: When pdt = B2C and payment type = repl ", "B2C", "repl"},
                {"Scenario:: When pdt = B2B and payment type = prepaid ", "B2B", "prepaid"},
                {"Scenario:: When pdt = B2B and payment type = postpaid ", "B2B", "postpaid"},
                {"Scenario:: When pdt = B2B and payment type = cod ", "B2B", "cod"},
                {"Scenario:: When pdt = B2B and payment type = pickup ", "B2B", "pickup"},
                {"Scenario:: When pdt = B2B and payment type = repl ", "B2B", "repl"},
                {"Scenario:: When pdt = Heavy and payment type = prepaid ", "Heavy", "prepaid"},
                {"Scenario:: When pdt = Heavy and payment type = postpaid ", "Heavy", "postpaid"},
                {"Scenario:: When pdt = Heavy and payment type = cod ", "Heavy", "cod"},
                {"Scenario:: When pdt = Heavy and payment type = pickup ", "Heavy", "pickup"},
                {"Scenario:: When pdt = Heavy and payment type = repl ", "Heavy", "repl"},
                {"Scenario:: When pdt = DOC and payment type = prepaid ", "DOC", "prepaid"},
                {"Scenario:: When pdt = DOC and payment type = postpaid ", "DOC", "postpaid"},
                {"Scenario:: When pdt = DOC and payment type = cod ", "DOC", "cod"},
                {"Scenario:: When pdt = DOC and payment type = pickup ", "DOC", "pickup"},
                {"Scenario:: When pdt = DOC and payment type = repl ", "DOC", "repl"}
        };
    }

    @Test(dataProvider = "Different_pdt_payment_types", enabled = true)
    public void testApplySt6W(String Scenario, String product_type, String payment_mode) {
        HashMap<String, String> clData = new HashMap<>();
        clData.put("client", "regression_client");
        clData.put("product_type", product_type);
        clData.put("payment_mode", payment_mode);
        manifestData.set(clData);
        if (payment_mode == "pickup") {
            manifestData.get().put("status", "Canceled");
            String waybill = diffStShpt.DifferentStateShipments("PICKUPPENDING", manifestData.get());
            waybillList.set(new ArrayList<>(Collections.singletonList(waybill)));
            apiCtrl.ApplyNsl(waybillList.get(), "PP", "X-ASP", manifestData.get());
            apiCtrl.verifyPackageFetchInfoApi(waybillList.get().get(0), "PP", "Scheduled", "X-ASP", manifestData.get());
            apiCtrl.ApplyNsl(waybillList.get(), "CN", "ST-6W", manifestData.get());
            apiCtrl.verifyPackageFetchInfoApi(waybillList.get().get(0), "CN", "Canceled", "ST-6W", manifestData.get());
        } else {

            ArrayList<String> waybills = apiCtrl.cmuManifestApi(manifestData.get());
            waybillList.set(waybills);
            manifestData.get().put("status", "");
            apiCtrl.fmOMSApi(waybillList.get(), "FMPICK", manifestData.get());
            apiCtrl.ApplyNsl(waybillList.get(), "UD", "ST-6W", manifestData.get());
            apiCtrl.verifyPackageFetchInfoApi(waybillList.get().get(0), "UD", "In Transit", "ST-6W", manifestData.get());
        }
    }
}



