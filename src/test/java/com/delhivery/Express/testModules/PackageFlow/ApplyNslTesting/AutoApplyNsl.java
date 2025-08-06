package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;


import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.SMS.SmsResponse;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.Test;

import java.util.*;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

public class AutoApplyNsl extends BaseTest {
    private String dispatchId;
    private ArrayList<String> waybills, bagIds, tripIds;
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    public HashMap<String,String> reqData;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin, package_count, UPL;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String,String> manifestData = new HashMap<String,String>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    ApiController apiCtrl = new ApiController();

    public AutoApplyNsl() {
        this.scenario = "Package Flow B2C Prepaid type shipment";
        client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();

    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void Auto_ApplyX_SC_Nsl(String scenario, String product_type, String payment_mode) {
        clData.put("client", "regression_client");
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("client", "regression_client");
        data.put("product_type",product_type);
        data.put("payment_mode", payment_mode);
        data.put("hold_location", "true");
        waybills = apiCtrl.cmuManifestApi(data);
        String waybill = waybills.get(0);
//		waybill = apiCtrl.cmuManifestApi(data);
        logInfo("Waybill generated " + waybill);
        if(product_type=="B2B" || product_type=="Heavy" ){
            Utilities.hardWait(30);
        }

        if(scenario.contains("prepaid")  || scenario.contains("cod") || scenario.contains("repl") ) {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            //GI of pkgs
            String cn_loc = apiCtrl.fetchPackageInfo(waybill, clData).cn;
            apiCtrl.giApi(waybill, cn_loc, clData);
        }
        Utilities.hardWait(2);


        //pickup schedule
        if(scenario.contains("QC") || scenario.contains("pickup")) {
            apiCtrl.ApplyNsl(waybills, "PP", "X-ASP",data);
        }
        Utilities.hardWait(20);

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
        if(payment_mode=="cod" || payment_mode=="prepaid"){
            assertKeyValue("cs.nsl", "X-SC", pkgs.cs.nsl.toString());
        }else if(payment_mode=="pickup"){
            assertKeyValue("cs.nsl", "X-ASP", pkgs.cs.nsl.toString());
        } else if(payment_mode=="repl"){
            assertKeyValue("cs.nsl", "X-PIOM", pkgs.cs.nsl.toString());
        }



    }

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void Auto_ApplyX_SC_Nsl_diff_pkg_type(String scenario, String product_type, String payment_mode) {
        clData.put("client", "regression_client");
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("client", "regression_client");
        data.put("product_type",product_type);
        data.put("payment_mode", payment_mode);
        data.put("hold_location", "true");
        waybills = apiCtrl.cmuManifestApi(data);
        String waybill = waybills.get(0);
//		waybill = apiCtrl.cmuManifestApi(data);
        logInfo("Waybill generated " + waybill);
        Utilities.hardWait(2);

        if(scenario.contains("prepaid")  || scenario.contains("cod") || scenario.contains("repl") ) {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            //GI of pkgs
            String cn_loc = apiCtrl.fetchPackageInfo(waybill, clData).cn;
            apiCtrl.giApi(waybill, cn_loc, clData);
        }
        Utilities.hardWait(2);


        //pickup schedule
        if(scenario.contains("QC") || scenario.contains("pickup")) {
            apiCtrl.ApplyNsl(waybills, "PP", "X-ASP",data);
        }
        Utilities.hardWait(10);

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
        if(payment_mode=="cod" || payment_mode=="prepaid"){
            assertKeyValue("cs.nsl", "X-SC", pkgs.cs.nsl.toString());
        }else if(payment_mode=="pickup"){
            assertKeyValue("cs.nsl", "X-ASP", pkgs.cs.nsl.toString());
        } else if(payment_mode=="repl"){
            assertKeyValue("cs.nsl", "X-PIOM", pkgs.cs.nsl.toString());
        }
    }
}
