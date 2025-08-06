package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;

import static com.delhivery.core.utils.Assertions.assertKeyValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.ApplyNslApi.response.ApplyNslResponsePayload;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.applynslgeneric.response.ApplynslgenericResponse;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;

public class applynslDTUP extends BaseTest {
    private final ThreadLocal<List<String>> waybillList = new ThreadLocal<>();
    ThreadLocal<String> waybillToBeProcess = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgs = new ThreadLocal<>();
    private final ThreadLocal<ApplyNslResponsePayload> apiResponse = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
    private final ThreadLocal<Map<String, Object>> pkgFlowData = new ThreadLocal<>();
    ThreadLocal<String> last_scan = new ThreadLocal<>();
    private final String product_type, pin;
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    ApiController apiCtrl = new ApiController();

    public applynslDTUP() {
        this.product_type = "B2C";
        this.pin = "400059";
    }

    private ThreadLocal<HashMap<String, String>> getManifestData(String product_type) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> clData = new HashMap<>();
            clData.put("client", "regression_client");
            clData.put("return_pin", "400059");
            clData.put("return_add", "MIDC, Mumbai");
            clData.put("product_type", product_type);
            return clData;
        });
    }

    @Test(dataProvider = "Different_state_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void NSL_DTUP_ZL_Cases_Apply_NSL(String scenario, String state, String product_type) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        clData.set(getManifestData(product_type).get());

        Utilities.logInfo("Generating waybill for product type " + product_type + " and state " + state);


        waybillToBeProcess.set(diffStShpt.DifferentStateShipments(state, clData.get()));
        Utilities.hardWait(30);


        //Apply NSL
        if (state == "DISPATCHED") {
            ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "UD", "DTUP-ZL", clData.get());
            assertKeyValue("Success", false, applyNslResponsePayload.isSuccess());
            assertKeyValue("data", "NSL cannot apply on these packages ", applyNslResponsePayload.getMessage());
        } else if (state == "LOST") {
            ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "LT", "DTUP-ZL", clData.get());
            assertKeyValue("Success", false, applyNslResponsePayload.isSuccess());
            assertKeyValue("data", "NSL cannot apply on these packages ", applyNslResponsePayload.getMessage());
        } else {
            ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "DL", "DTUP-ZL", clData.get());
            assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
            assertKeyValue("data", "NSL successfully applied on packages " + waybillToBeProcess.get(), applyNslResponsePayload.getData());

            PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
            assertKeyValue("nsl", "DTUP-ZL", pkg2.cs.nsl);
        }
		postProcess();
    }

    @Test(dataProvider = "Different_state_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void NSL_DTUP_ZL_Cases_Apply_NSL_Generic(String scenario, String state, String product_type) {
        HashMap<String, String> clData = new HashMap<>();
        clData.put("client", "regression_client");
        clData.put("product_type", product_type);
        Utilities.logInfo("Generating waybill for product type " + product_type + " and state " + state);

        waybillToBeProcess.set(diffStShpt.DifferentStateShipments(state, clData));
        List<String> list = Arrays.asList(waybillToBeProcess.get());
        Utilities.hardWait(30);

        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData));

        //unset dd.id
        if (pkgs.get().dd.id != null && state != "DISPATCHED") {
            apiCtrl.unsetShipmentDispatchIdApi(waybillToBeProcess.get(), pkgs.get().dd.id.toString(), clData);

        }

        //Apply NSL
        if (state == "DISPATCHED") {
            ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "UD", "DTUP-ZL", "Zero liability shipment", clData);
            Utilities.hardWait(30);
            assertKeyValue("success", true, apiResponse.success);
            assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
            assertKeyValue("nsl", "X-DDD3FD", pkg.cs.nsl);
        } else if (state == "LOST") {
            ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "LT", "DTUP-ZL", "Zero liability shipment", clData);
            Utilities.hardWait(30);
            assertKeyValue("success", false, apiResponse.success);
            assertKeyValue("msg", "Status passed is invalid/restricted.Kindly pass one of these statuses :- UD,DL,RT,PU,PP,CN", apiResponse.msg);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
            assertKeyValue("nsl", "LT-100", pkg.cs.nsl);
        } else {
            ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "DL", "DTUP-ZL", "Zero liability shipment", clData);
            Utilities.hardWait(30);
            assertKeyValue("success", true, apiResponse.success);
            assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
            assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);
        }
		postProcess();
    }

    @Test(dataProvider = "Different_state_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void NSL_DTUP_ZL_Cases_Apply_NSL_LM_Sync_V1(String scenario, String state, String product_type) {
        HashMap<String, String> clData = new HashMap<>();
        clData.put("client", "regression_client");
        clData.put("product_type", product_type);
        Utilities.logInfo("Generating waybill for product type " + product_type + " and state " + state);

        waybillToBeProcess.set(diffStShpt.DifferentStateShipments(state, clData));

        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData));

        ArrayList<String> list = new ArrayList<String>();
        list.add(waybillToBeProcess.get());

        //Apply NSL
        if (state == "DISPATCHED") {
            clData.put("ss", "Undelivered");
            clData.put("st", "UD");
            clData.put("nsl_code", "DTUP-ZL");
            apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
            Utilities.hardWait(20);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
            assertKeyValue("nsl", "X-DDD3FD", pkg.cs.nsl);
        } else if (state == "LOST") {
            clData.put("ss", "LOST");
            clData.put("st", "LT");
            clData.put("nsl_code", "DTUP-ZL");
            apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
            Utilities.hardWait(20);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
            assertKeyValue("nsl", "LT-100", pkg.cs.nsl);
        } else {
            apiCtrl.lmUpdateHQShipmentApi(list, "zero_liability_DL", clData);
            Utilities.hardWait(20);
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
            assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);
        }
		postProcess();
    }

    private void postProcess() {
        ThreadLocal<?>[] variables = {data, clData, waybillList, waybillToBeProcess, pkgFlowData, apiResponse, last_scan, pkgs};
        for (ThreadLocal<?> variable : variables) {
            if (variable != null) {
                variable.remove();
            }
        }
    }
}
