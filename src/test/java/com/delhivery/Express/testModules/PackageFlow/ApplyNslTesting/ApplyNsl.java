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
import com.delhivery.Express.pojo.ApplyNslApi.response.ApplyNslResponsePayload;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

public class ApplyNsl extends BaseTest {
    private final ThreadLocal<List<String>> waybillList = new ThreadLocal<>();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();
    private final String pin = "400059";
    private final ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
    private final ThreadLocal<Map<String, Object>> pkgFlowData = new ThreadLocal<>();
    ThreadLocal<ApplyNslResponsePayload> apiResponse = new ThreadLocal<>();
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

//    private ThreadLocal<HashMap<String, String>> getManifestDataWithOrWithoutEINVQR(String product_type, String payment_mode, String pin, String einvqr) {
//        return ThreadLocal.withInitial(() -> {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("client", "regression_client");
//            map.put("product_type", product_type);
//            if (payment_mode != null) {
//                map.put("payment_mode", payment_mode);
//            }
//
//            if (einvqr != null) {
//                map.put("einv_qr", einvqr);
//            }
//            if (pin != null) {
//                map.put("pin", pin);
//            }
//            return map;
//        });
//    }
//
//    private ThreadLocal<HashMap<String, String>> getClData(String client) {
//        return ThreadLocal.withInitial(() -> {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("client", client);
//            return map;
//        });
//    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslWhenEinvQrKeyIsTrue(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());

        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, null, "true").get());

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
        ThreadLocal<PackageDetail> pkgs = ThreadLocal.withInitial(() -> apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("einv_qr", true, pkgs.get().einvQr);
        logInfo("Scan REMARK : " + pkgs.get().einvQr);

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
        apiResponse.set(apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get()));

        assertKeyValue("success", false, apiResponse.get().isSuccess());
        assertKeyValue("message : NSL cannot apply on these packages", true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages "));
        postProcess();
    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslWhenEinvQrKeyisFalse(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, null, "false").get());

        waybillList.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybillList.get().get(0));

        logInfo("Waybill generated " + waybill.get());
        if (scenario.contains("cash") || scenario.contains("pickup")) {
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), "PP", "Open", "X-UCI", clData.get());
        } else {
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), "UD", "Manifested", "X-UCI", clData.get());
        }
        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }
        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
        assertKeyValue("einv_qr", false, pkgs.einvQr);
        logInfo("Scan REMARK : " + pkgs.einvQr);

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
        apiResponse.set(apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get()));
        if (pkgs.einvQr || scenario.contains("cash") || scenario.contains("pickup") || scenario.contains("Heavy")) {
            assertKeyValue("success", false, apiResponse.get().isSuccess());
            assertKeyValue("message :: NSL cannot apply on these packages "+ waybill.get(), true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages "));
        } else {
            assertKeyValue("success", true, apiResponse.get().isSuccess());
            assertKeyValue("data::NSL successfully applied on packages "+ waybill.get(), true, apiResponse.get().getData().contains("NSL successfully applied on packages "));
            pkgFlowData.set(YamlReader.getYamlValues("packageFlowScans.DocumentMissing"));
            apiCtrl.verifyPackageFetchInfoApi(waybill.get(), pkgFlowData.get().get("statusType").toString(), pkgFlowData.get().get("scanStatus").toString(), pkgFlowData.get().get("scanNsl").toString(), clData.get());
        }
        postProcess();

    }

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslWhenNslAppliedOnDifferentPkgStateTrueValue(String scenario, String product_type, String state) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "true").get());

        waybill.set(diffStShpt.DifferentStateShipments(state, data.get()));
        logInfo("Waybill " + waybill.get());

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
        assertKeyValue("einv_qr", true, pkgs.einvQr);
        waybillList.set(Collections.singletonList(waybill.get()));

        //Apply NSL
        Utilities.hardWait(10);
        apiResponse.set(apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get()));
        if (pkgs.einvQr || scenario.contains("Delivered") || scenario.contains("PickupPending") || scenario.contains("Cancelled") || scenario.contains("PickedUp")) {
            assertKeyValue("success", false, apiResponse.get().isSuccess());
            if (scenario.contains("PickedUp") && !pkgs.einvQr) {
                assertKeyValue("message:: NSL cannot apply on these packages " + waybill.get(), true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages"));
            } else {
                assertKeyValue("message :: NSL cannot apply on these packages "+waybill.get(), true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages"));
            }
        } else {
            assertKeyValue("success", true, apiResponse.get().isSuccess());
            assertKeyValue("data :: NSL successfully applied on packages "+ waybill.get(), true , apiResponse.get().getData().contains("NSL successfully applied on packages"));
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
            assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);

        }
        postProcess();
    }

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslWhenNslAppliedOnDifferentPkgStateFalseValue(String scenario, String product_type, String state) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "false").get());

        waybill.set(diffStShpt.DifferentStateShipments(state, data.get()));
        logInfo("Waybill " + waybill.get());

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
        assertKeyValue("einv_qr", false, pkgs.einvQr);
        List<String> list = Collections.singletonList(waybill.get());

        //Apply NSL
        Utilities.hardWait(10);
        if(pkgs.einvQr || scenario.contains("Delivered") || scenario.contains("PICKUPPENDING") || scenario.contains("Cancelled") || scenario.contains("PickedUp")){
            clData.get().put("can_apply_nsl","false");
        }

        ApplyNslResponsePayload apiResponse = apiCtrl.ApplyNsl(list, "UD", "DLYLH-145", clData.get());
        if (pkgs.einvQr || scenario.contains("Delivered") || scenario.contains("PICKUPPENDING") || scenario.contains("Cancelled") || scenario.contains("PickedUp")) {
            assertKeyValue("success", false, apiResponse.isSuccess());
            if ((scenario.contains("PickedUp") || scenario.contains(" PickupPending")) && !pkgs.einvQr) {
                assertKeyValue("message : NSL cannot apply on these packages " + waybill.get(), true, apiResponse.getMessage().contains("NSL cannot apply on these packages"));
            } else {
                assertKeyValue("message : NSL cannot apply on these packages "+waybill.get(), true, apiResponse.getMessage().contains("NSL cannot apply on these packages"));
            }
        } else {
            assertKeyValue("success", true, apiResponse.isSuccess());
            assertKeyValue("data : NSL successfully applied on packages " + waybill.get(), true, apiResponse.getData().contains("NSL successfully applied on packages"));
            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
            assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);

        }
        postProcess();
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslWhenNslAppliedOnDiffPkgsTypeTrueValue(String scenario, String type) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR("B2C", null, pin, "true").get());

        waybillList.set(diffTypeShipment.DifferentTypeShipments(type, data.get()));
        waybill.set(waybillList.get().get(0));

        logInfo("Waybill generated " + waybill.get());

        //Getting child waybill
        if (waybillList.get().size() > 1 && scenario.contains("MPS")) {
            logInfo("Child Waybill generated " + waybillList.get().get(1));
        }

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());

        //Apply NSL
        if (scenario.contains("NO DATA") || scenario.contains("PARTIALLY MANIFESTED")) {
            Utilities.hardWait(10);
        }

        apiResponse.set(apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get()));
        if (scenario.contains("HEAVY") || scenario.contains("B2C") || scenario.contains("B2B package") || scenario.contains("B2B MPS package") || scenario.contains("B2C MPS")) {
            assertKeyValue("success", false, apiResponse.get().isSuccess());
            assertKeyValue("message: NSL cannot apply on these packages", true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages"));
        } else {
            assertKeyValue("success", true, apiResponse.get().isSuccess());
            if (scenario.contains("B2B MPS package") || scenario.contains("NO DATA") || scenario.contains("PARTIALLY MANIFESTED")) {
                assertKeyValue("data : NSL successfully applied on packages " + waybill.get() + ", " + waybillList.get().get(1), true, apiResponse.get().getData().contains("NSL successfully applied on packages"));
            } else {
                assertKeyValue("data : NSL successfully applied on packages " + waybill.get(), true, apiResponse.get().getData().contains("NSL successfully applied on packages"));
            }

            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
            assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);
        }
        postProcess();
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslWhenNslAppliedOnDiffPkgsTypeFalseValue(String scenario, String type) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR("B2C", null, pin, "false").get());

        waybillList.set(diffTypeShipment.DifferentTypeShipments(type, data.get()));
        waybill.set(waybillList.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        //Getting child waybill
        if (waybillList.get().size() > 1 && scenario.contains("MPS")) {
            logInfo("Child Waybill generated " + waybillList.get().get(1));
        }

        if (scenario.contains("NO DATA") || scenario.contains("PARTIALLY MANIFESTED")) {
            Utilities.hardWait(10);
        }

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());

        //Apply NS
        apiResponse.set(apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYLH-145", clData.get()));
        if (scenario.contains("HEAVY")) {
            assertKeyValue("success", false, apiResponse.get().isSuccess());
            assertKeyValue("message : NSL cannot apply on these packages", true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages"));
        } else {
            assertKeyValue("success", true, apiResponse.get().isSuccess());
            if (scenario.contains("B2C MPS") || scenario.contains("B2B MPS") || scenario.contains("NO DATA") || scenario.contains("PARTIALLY MANIFESTED")) {
                assertKeyValue("data : NSL successfully applied on packages " + waybill.get() + ", " + waybillList.get().get(1), true, apiResponse.get().getData().contains("NSL successfully applied on packages"));
            } else {
                assertKeyValue("data : NSL successfully applied on packages " + waybill.get(), true, apiResponse.get().getData().contains("NSL successfully applied on packages"));
            }

            PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
            assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);
        }
        postProcess();
    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void applyNslCCFNslCases(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());

        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, pin, null).get());
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
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill.get(), clData.get());
        assertKeyValue("einv_qr", true, pkgs.einvQr);

        logInfo("Scan REMARK : " + pkgs.einvQr);
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
        clData.get().put("can_apply_nsl","false");
        apiResponse.set(apiCtrl.ApplyNsl(waybillList.get(), "UD", "DLYB2B-101", clData.get()));
        assertKeyValue("success", false, apiResponse.get().isSuccess());
        //As This error message toggled with wbn so migrate this assertion with error msg
        assertKeyValue("Message : NSL cannot apply on these packages", true, apiResponse.get().getMessage().contains("NSL cannot apply on these packages"));
        postProcess();
    }

    private void postProcess() {
        ThreadLocal<?>[] variables = {data, clData, waybillList, waybill, pkgFlowData, apiResponse};
        for (ThreadLocal<?> variable : variables) {
            if (variable != null) {
                variable.remove();
            }
        }
    }
}
