package com.delhivery.Express.testModules.RegressionScripts;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.HashMap;

import com.delhivery.Express.testModules.util.TestModuleHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;

public class Mapper2 extends BaseTest {
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    ApiController apiCtrl = new ApiController();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestPayload = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgdetails = new ThreadLocal<>();

    @DataProvider(name = "Return_b2c_diff_state_shipments_raseg_update_rpin", parallel = true)
    public Object[][] Pincode_Return_b2c_diff_state_shipments_raseg_update_rpin() {
        return new Object[][]{
                {"Scenario:: Manifest state shipment", "Manifest", true},
                {"Scenario:: In Transit state shipment", "In Transit", true},
                {"Scenario:: Pending state shipment", "Pending", true},
                {"Scenario:: Returned state shipment", "Returned", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", true},
                {"Scenario:: PickedUp state shipment", "PickedUp", true},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", true},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", true},

                //NR edit api won't update address
                {"Scenario:: Dispatched state shipment", "Dispatched", false},
                {"Scenario:: Delivered state shipment", "Delivered", false},
                {"Scenario:: RTO state shipment", "RTO", false},
                {"Scenario:: LOST state shipment", "LOST", false},
                {"Scenario:: DTO state shipment", "DTO", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", true}

        };
    }

    //FT 224074 cases
    @Test(dataProvider = "Return_b2c_diff_state_shipments_raseg_update_rpin", enabled = true)
    public void UpdateRcnForDifferentStatesPkg(String Scenario, String state, Boolean enable) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinAddReturnAddAndReturnPin(null, "B2C", null, "122003", "testingDummyData", "test", "122005").get());
        waybill.set(diffStShpt.DifferentStateShipments(state, requestPayload.get()));
        logInfo("Waybill " + waybill.get());
        Utilities.hardWait(10);

        if (enable) {
            apiCtrl.centerUpdateRTApi(waybill.get(), "Del_Okhla_PC (DELHI)", enable);

            pkgdetails.set(apiCtrl.fetchPackageInfo(waybill.get(), requestPayload.get()));
            assertKeyValue("rcn", "Del_Okhla_PC (DELHI)", pkgdetails.get().rcn);

        } else {
            apiCtrl.centerUpdateRTApi(waybill.get(), "Del_Okhla_PC (DELHI)", enable);
        }
        postProcess();
    }

    @DataProvider(name = "b2c_pdt_diff_state_shipments", parallel = true)
    public Object[][] Pdt_Forward_b2c_pdt_diff_state_shipments_pin_update() {
        return new Object[][]{
                {"Scenario:: Manifest state shipment", "Manifest", true},
                {"Scenario:: In Transit state shipment", "In Transit", true},
                {"Scenario:: Pending state shipment", "Pending", true},
                {"Scenario:: Returned state shipment", "Returned", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", true},
                {"Scenario:: PickedUp state shipment", "PickedUp", true},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", true},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", true},

                //NR edit api won't update address
                {"Scenario:: Dispatched state shipment", "Dispatched", false},
                {"Scenario:: Delivered state shipment", "Delivered", false},
                {"Scenario:: RTO state shipment", "RTO", false},
                {"Scenario:: LOST state shipment", "LOST", false},
                {"Scenario:: DTO state shipment", "DTO", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", false}

        };
    }

    //FT 224074 cases
    @Test(dataProvider = "b2c_pdt_diff_state_shipments", enabled = true)
    public void UpdateCnForDifferentStatesPkg(String Scenario, String state, Boolean enable) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinAndAdd(null, "B2C", null, "110001", "test").get());

        waybill.set(diffStShpt.DifferentStateShipments(state, requestPayload.get()));
        logInfo("Waybill " + waybill.get());
        Utilities.hardWait(30);

        if (enable) {
            apiCtrl.centerUpdateApi(waybill.get(), "Barauli_SiwanRd_D (Bihar)", enable);
            pkgdetails.set(apiCtrl.fetchPackageInfo(waybill.get(), requestPayload.get()));
            assertKeyValue("cn", "Barauli_SiwanRd_D (Bihar)", pkgdetails.get().cn);
        } else {
            apiCtrl.centerUpdateApi(waybill.get(), "Barauli_SiwanRd_D (Bihar)", enable);
        }
        postProcess();
    }

    //Remove thread local data associated with caller thread
    private void postProcess() {
        requestPayload.remove();
        waybill.remove();
        pkgdetails.get();
    }
}
