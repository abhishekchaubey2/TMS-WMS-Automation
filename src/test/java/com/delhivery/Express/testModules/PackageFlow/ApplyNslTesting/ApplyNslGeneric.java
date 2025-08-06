package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.applynslgeneric.response.ApplynslgenericResponse;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

public class ApplyNslGeneric extends BaseTest {
    private final ThreadLocal<List<String>> waybillList = new ThreadLocal<>();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgs = new ThreadLocal<>();
    private final ThreadLocal<ApplynslgenericResponse> apiResponse = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
    private final ThreadLocal<Map<String, Object>> pkgFlowData = new ThreadLocal<>();
    ThreadLocal<String> last_scan = new ThreadLocal<>();
    private final String product_type, pin;
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    ApiController apiCtrl = new ApiController();

    public ApplyNslGeneric() {
        this.product_type = "B2C";
        this.pin = "400059";
    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslGenericWhenEinvQrKeyIsTrue(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, pin, "true").get());

        waybillList.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybillList.get().get(0));

        logInfo("Waybill generated " + waybill.get());
        if (payment_mode.equalsIgnoreCase("pickup") || payment_mode.equalsIgnoreCase("cash")) {
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), "PP", "Open", "X-UCI", clData.get());

        } else {
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), "UD", "Manifested", "X-UCI", clData.get());
        }
        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("einv_qr", true, pkgs.get().einvQr);
        if (scenario.contains("prepaid") || scenario.contains("postpaid") || scenario.contains("cod") || scenario.contains("repl")) {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData.get());
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.fmPick"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData.get());
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.fmDepart"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());

        }
        //Apply NSL
        apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));
        Utilities.hardWait(2);
        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        assertKeyValue("success", true, apiResponse.get().success);
        assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.get().msg);
        if (!pkgs.get().einvQr) {
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.DocumentMissing"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());
        }
        postProcess();
    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslGenericWhenEinvQrKeyisFalse(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, pin, "false").get());

        waybillList.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybillList.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        if (payment_mode.equalsIgnoreCase("pickup") || payment_mode.equalsIgnoreCase("cash")) {
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), "PP", "Open", "X-UCI", clData.get());

        } else {
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), "UD", "Manifested", "X-UCI", clData.get());
        }
        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }

        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("einv_qr", false, pkgs.get().einvQr);

        if (scenario.contains("prepaid") || scenario.contains("postpaid") || scenario.contains("cod") || scenario.contains("repl")) {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData.get());
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.fmPick"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData.get());
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.fmDepart"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());
        }

        //Apply NSL
        apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));
        Utilities.hardWait(7);
        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        assertKeyValue("success", true, apiResponse.get().success);
        assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.get().msg);
        if (scenario.contains("cash") || scenario.contains("pickup") || scenario.contains("Heavy")) {
            assertKeyValue("nsl", pkgs.get().cs.nsl, pkgs.get().cs.nsl);
        } else {
            Utilities.hardWait(10);
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.DocumentMissing"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());
        }
        postProcess();
    }

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslGenericWhenNslAppliedOnDifferentPkgStateTrueValue(String scenario, String product_type, String state) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "true").get());

        waybill.set(diffStShpt.DifferentStateShipments(state, data.get()));
        logInfo("Waybill generated " + waybill.get());

        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("einv_qr", true, pkgs.get().einvQr);
        waybillList.set(Collections.singletonList(waybill.get()));

        //Apply NSL
        Utilities.hardWait(7);
        if (scenario.contains("Returned")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "RT", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("manifested") || scenario.contains("In transit") || scenario.contains("Pending")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("Cancelled")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "CN", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("Delivered")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "DL", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("PickupPending")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "PP", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("PickedUp")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "PU", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));

        }
        assertKeyValue("success", true, apiResponse.get().success);
        assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.get().msg);

        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }

        if (pkgs.get().einvQr || scenario.contains("Delivered") || scenario.contains("PickedUp") || scenario.contains("Cancelled") || scenario.contains("PickupPending")) {
            assertKeyValue("nsl", pkgs.get().cs.nsl, pkgs.get().cs.nsl);
        } else {
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
    }

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslGenericWhenNslAppliedOnDifferentPkgStateFalseValue(String scenario, String product_type, String state) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "false").get());

        waybill.set(diffStShpt.DifferentStateShipments(state, data.get()));
        logInfo("Waybill generated " + waybill.get());

        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("einv_qr", false, pkgs.get().einvQr);

        waybillList.set(Collections.singletonList(waybill.get()));

        last_scan.set(pkgs.get().cs.nsl);


        //Apply NSL
        Utilities.hardWait(5);
        if (scenario.contains("Returned")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "RT", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("manifested") || scenario.contains("In transit") || scenario.contains("Pending")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("Cancelled")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "CN", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("Delivered")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "DL", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("PickupPending")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "PP", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else if (scenario.contains("PickedUp")) {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "PU", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));


        } else {
            apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));

        }
        assertKeyValue("success", true, apiResponse.get().success);
        assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.get().msg);

        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        Utilities.hardWait(25);
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));

        if (pkgs.get().einvQr || scenario.contains("Delivered") || scenario.contains("Cancelled")) {
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl);
        } else {
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslGenericWhenNslAppliedOnDiffPkgsTypeTrueValue(String scenario, String type) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "true").get());

        waybillList.set(diffTypeShipment.DifferentTypeShipments(type, data.get()));
        waybill.set(waybillList.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        //Getting child waybill
        if (waybillList.get().size() > 1 && scenario.contains("MPS")) {
            logInfo("Child Waybill generated " + waybillList.get().get(1));
        }

        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        Utilities.hardWait(4);

        //Apply NSL

        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get());
        Utilities.hardWait(7);
        apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));
        assertKeyValue("success", true, apiResponse.get().success);
        assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.get().msg);
        assertKeyValue("nsl", pkgs.get().cs.nsl, pkgs.get().cs.nsl);
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslGenericWhenNslAppliedOnDiffPkgsTypeFalseValue(String scenario, String type) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "false").get());

        waybillList.set(diffTypeShipment.DifferentTypeShipments(type, data.get()));
        waybill.set(waybillList.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        //Getting child waybill
        if (waybillList.get().size() > 1 && scenario.contains("MPS")) {
            logInfo("Child Waybill generated " + waybillList.get().get(1));
        }

        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        Utilities.hardWait(4);

        //Apply NSL
        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get());
        Utilities.hardWait(7);
        apiResponse.set(apiCtrl.ApplyNslGeneric(waybillList.get(), "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData.get()));
        assertKeyValue("success", true, apiResponse.get().success);
        assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.get().msg);

        //Fetch Pkg Info
        if (scenario.contains("HEAVY")) {
            assertKeyValue("nsl", pkgs.get().cs.nsl, pkgs.get().cs.nsl);
        } else {
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
        postProcess();
    }

    private void postProcess() {
        ThreadLocal<?>[] variables = {data, clData, waybillList, waybill, pkgFlowData, apiResponse, last_scan, pkgs};
        for (ThreadLocal<?> variable : variables) {
            if (variable != null) {
                variable.remove();
            }
        }
    }

}
