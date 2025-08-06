package com.delhivery.Express.testModules.applynsl;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.ApplyNslApi.response.ApplyNslResponsePayload;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.DataProvider;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import org.testng.annotations.Test;

public class ApplyNsl107 extends BaseTest {
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    ThreadLocal<String> waybillToBeProcess = new ThreadLocal<>();
    ThreadLocal<List<String>> waybillListToBeProcess = new ThreadLocal<>();
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

    private ThreadLocal<HashMap<String, String>> getManifestData(String product_type, String payment_mode) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> clData = new HashMap<>();
            clData.put("client", "regression_client");
            clData.put("return_pin", "400059");
            clData.put("return_add", "MIDC, Mumbai");
            clData.put("product_type", product_type);
            clData.put("payment_mode", payment_mode);
            return clData;
        });
    }

    @Test(dataProvider = "Different_pdt_payment_types", enabled = true)
    public void applyNsl(String Scenario, String product_type, String payment_mode) {

        ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
        clData.set(getManifestData(product_type, payment_mode).get());

        Utilities.logInfo("Generating waybill for product type " + product_type + " and payment mode " + payment_mode);

        if (payment_mode == "pickup") {
            waybillToBeProcess.set(diffStShpt.DifferentStateShipments("PICKEDUP", clData.get()));
            Utilities.hardWait(30);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
            apiCtrl.giApi(waybillToBeProcess.get(), pkg.rcn, clData.get());
            Utilities.hardWait(10);
            ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "PU", "ST-107", clData.get());
            assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), "PU", "Pending", "ST-107", clData.get());
            applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "PU", "ST-107", clData.get());
            assertKeyValue("Success", false, applyNslResponsePayload.isSuccess());
        } else {
            waybillListToBeProcess.set(apiCtrl.cmuManifestApi(clData.get()));
            waybillToBeProcess.set(waybillListToBeProcess.get().get(0));

            if (product_type.equals("B2B") || product_type.equals("Heavy")) {
                Utilities.hardWait(70);
            }

            logInfo("Waybill generated " + waybillToBeProcess.get());

            apiCtrl.fmOMSApi(waybillToBeProcess.get(), "FMPICK", clData.get());
            ThreadLocal<PackageDetail> pkg = new ThreadLocal<>();
            pkg.set(apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get()));
            if (product_type.equals("B2B") || product_type.equals("Heavy")) {
                apiCtrl.giApi(waybillToBeProcess.get(), pkg.get().cn, clData.get());
            } else {
                apiCtrl.giApi(waybillToBeProcess.get(), pkg.get().cn, clData.get());
            }

            ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "UD", "ST-107", clData.get());
            assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), "UD", "Pending", "ST-107", clData.get());
            applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "UD", "ST-107", clData.get());
            assertKeyValue("Success", false, applyNslResponsePayload.isSuccess());
        }
    }
}