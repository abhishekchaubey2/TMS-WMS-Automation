package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

public class LMSyncV1 extends BaseTest {
    ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
    ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
    ThreadLocal<ArrayList<String>> waybillList = new ThreadLocal<>();
    ThreadLocal<Map<String, Object>> pkgFlowData = new ThreadLocal<>();
    ThreadLocal<PackageDetail> pkgs = new ThreadLocal<>();
    ThreadLocal<String> waybill = new ThreadLocal<>();
    ThreadLocal<String> last_scan = new ThreadLocal<>();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    ApiController apiCtrl = new ApiController();
    private final String pin = "400059";

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void testLMSyncV1WhenEinvQrKeyIsTrue(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, pin, "true").get());
        waybillList.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybillList.get().get(0));

        logInfo("Waybill generated " + waybill.get());

        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }

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
        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("einv_qr", true, pkgs.get().einvQr);
        //Fetch Pkg Info

        last_scan.set(pkgs.get().cs.nsl);
        //Apply NSL

        apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_inTransit", clData.get());
        Utilities.hardWait(2);
        if (pkgs.get().einvQr) {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl);
        } else {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
        postProcess();
    }


    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void testLMSyncV1WhenEinvQrKeyisFalse(String scenario, String product_type, String payment_mode) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, payment_mode, pin, "false").get());
        waybillList.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybillList.get().get(0));

        logInfo("Waybill generated " + waybill.get());

        if (product_type.equals("B2B")) {
            Utilities.hardWait(10);
        }

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

        //fetch pkg info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        last_scan.set(pkgs.get().cs.nsl);
        assertKeyValue("einv_qr", false, pkgs.get().einvQr);

        //Apply NSL
        if(!product_type.equals("Heavy") && payment_mode.equalsIgnoreCase("pickup")){
            Utilities.hardWait(5);
            apiCtrl.ApplyNsl(waybillList.get(), "PP", "X-ASP", clData.get());
            Utilities.hardWait(5);
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_pickupPending_Scheduled", clData.get());
        }else if(!product_type.equals("Heavy")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_inTransit", clData.get());
        }
        Utilities.hardWait(8);
        if(scenario.contains("cash") || product_type.equals("Heavy")){
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl);
        } else if (pkgs.get().einvQr  || scenario.contains("pickup")) {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        } else {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
        postProcess();
    }

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void testLMSyncV1WhenNslAppliedOnDifferentPkgStateTrueValue(String scenario, String product_type, String state) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "true").get());
        waybill.set(diffStShpt.DifferentStateShipments(state, data.get()));
        waybillList.set(new ArrayList<>(Collections.singletonList(waybill.get())));
        logInfo("Waybill generated " + waybill.get());

        //fetch pkg info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        last_scan.set(pkgs.get().cs.nsl);


        //Apply NSL
        Utilities.hardWait(2);
        if (scenario.contains("manifested")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_manifested", clData.get());

        } else if (scenario.contains("In transit")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_inTransit", clData.get());

        } else if (scenario.contains("Pending")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_pending", clData.get());

        } else if (scenario.contains("Delivered")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_delivered", clData.get());

        } else if (scenario.contains("Returned")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_returned", clData.get());

        } else if (scenario.contains("PickupPending")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_pickupPending", clData.get());

        } else if (scenario.contains("PickedUp")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_PickedUp", clData.get());

        } else if (scenario.contains("Cancelled")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_cancelled", clData.get());
        }

        if (pkgs.get().einvQr || scenario.contains("Delivered") || scenario.contains("Cancelled")) {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl);
        } else {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
        postProcess();
    }

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void testLMSyncV1WhenNslAppliedOnDifferentPkgStateFalseValue(String scenario, String product_type, String state) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR(product_type, null, pin, "false").get());
        waybill.set(diffStShpt.DifferentStateShipments(state, data.get()));
        waybillList.set(new ArrayList<>(Collections.singletonList(waybill.get())));
        logInfo("Waybill generated " + waybill.get());


        //fetch pkg info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        last_scan.set(pkgs.get().cs.nsl);


        //Apply NSL
        Utilities.hardWait(2);
        if (scenario.contains("manifested")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_manifested", clData.get());

        } else if (scenario.contains("In transit")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_inTransit", clData.get());

        } else if (scenario.contains("Pending")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_pending", clData.get());

        } else if (scenario.contains("Delivered")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_delivered", clData.get());

        } else if (scenario.contains("Returned")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_returned", clData.get());

        } else if (scenario.contains("PICKUPPENDING")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_pickupPending", clData.get());

        } else if (scenario.contains("PickedUp")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_PickedUp", clData.get());

        } else if (scenario.contains("Cancelled")) {
            apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_cancelled", clData.get());
        }

        if (pkgs.get().einvQr || scenario.contains("Delivered") || scenario.contains("Cancelled")) {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl.toString());
        } else {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void testLMSyncV1WhenNslAppliedOnDiffPkgsTypeTrueValue(String scenario, String type) {
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
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        last_scan.set(pkgs.get().cs.nsl);

        //Apply NSL
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_inTransit", clData.get());
        if (scenario.contains("B2C") || scenario.contains("HEAVY") || scenario.contains("B2B package") || scenario.contains("B2B MPS package")||scenario.contains("NO DATA WITHOUT MANIFESTATION")) {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl);
        } else {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
        postProcess();
    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void testLMSyncV1WhenNslAppliedOnDiffPkgsTypeFalseValue(String scenario, String type) {
        clData.set(ApplyNSLHelper.getClData("regression_client").get());
        data.set(ApplyNSLHelper.getManifestDataWithOrWithoutEINVQR("B2C", null, pin, "false").get());
        waybillList.set(diffTypeShipment.DifferentTypeShipments(type, data.get()));
        waybill.set(waybillList.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        //Getting child waybill
        if (waybillList.get().size() > 1 && scenario.contains("MPS")) {
            logInfo("Child Waybill generated " + waybillList.get().get(1));
        }

        //Fetch Pkg Info
        pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        last_scan.set(pkgs.get().cs.nsl);

        //Apply NSL
        Utilities.hardWait(30);
        apiCtrl.lmUpdateHQShipmentApi(waybillList.get(), "docu_inTransit", clData.get());
      //In Lm Sync V1 api there is a check DLYH 145 can not be applied on Heavy and No data without manifestation package
        if (scenario.contains("HEAVY")||scenario.contains("NO DATA WITHOUT MANIFESTATION")) {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", last_scan.get(), pkgs.get().cs.nsl);
        } else {
            //Fetch Pkg Info
            pkgs.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
            assertKeyValue("nsl", "DLYLH-145", pkgs.get().cs.nsl);
        }
        postProcess();
    }

    private void postProcess() {
        ThreadLocal<?>[] variables = {data, clData, waybillList, waybill, pkgFlowData, last_scan, pkgs};
        for (ThreadLocal<?> variable : variables) {
            if (variable != null) {
                variable.remove();
            }
        }
    }
}
