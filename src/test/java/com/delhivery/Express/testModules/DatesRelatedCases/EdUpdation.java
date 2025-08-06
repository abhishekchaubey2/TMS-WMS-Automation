package com.delhivery.Express.testModules.DatesRelatedCases;

import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

public class EdUpdation extends BaseTest {
    private final String ocid, cnid, client;
    public Map<String, Object> bagFlowData;
    ApiController apiCtrl = new ApiController();
    private final String add_for_addfix_callback = "Godoli";
    private final String product_type2;
    private final ThreadLocal<HashMap<String, String>> manifestData = new ThreadLocal<>();
    private final ThreadLocal<List<String>> waybillListToBeProcess = new ThreadLocal<>();
    private final ThreadLocal<String> waybillToBeProcess = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> clDataMap = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> editDataMap = new ThreadLocal<>();

    
    /*
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void When_Pkg_Is_ReRouted_ED_Should_not_update(String scenario, String product_type, String payment_mode) {
    	clData.put("client", "regression_client");
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("product_type",product_type);
		data.put("payment_mode", payment_mode);
		data.put("pin", pin_for_6_days_ED_Update);
		//data.put("einv_qr", "true");

		waybills = apiCtrl.cmuManifestApi(data);
		String waybill = waybills.get(0);
//		waybill = apiCtrl.cmuManifestApi(data);
		logInfo("Waybill generated " + waybill);
		if(payment_mode == "pickup" || payment_mode =="cash") {
			apiCtrl.verifyPackageFetchInfoApi(waybill, "PP", "Open", "X-UCI", clData);

		}else {
			apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI", clData);
		}
		if (product_type.equals("B2B")) {
			Utilities.hardWait(15);
		}


		if(scenario.contains("prepaid") || scenario.contains("postpaid") || scenario.contains("cod") || scenario.contains("repl")) {
	    	//FM OMS Pick
			apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
			pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
	        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

	        //FM OMS Depart
	        apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
	        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
	        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

	    }

		//GI on pickup or cash pkgs
		if(scenario.contains("cash") || scenario.contains("pickup")) {
			String fm_loc = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
			apiCtrl.giApi(waybill, fm_loc, clData);
		}

		//Fetch Pkg Info
				PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
				String current_ed = pkgs.date.ed ;
				logInfo("Current ED" + current_ed);

        //Edit pkg CN
		//editing add
        HashMap<String, String> editAdd = new HashMap<>();
        editAdd.put("add", add_for_addfix_callback); // aseg update pin to 122003
        apiCtrl.EditApi(waybill, editAdd);

        //waiting period for pincode update
        Utilities.hardWait(addfixCallbackDelayTime);
        PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
        Assertions.assertKeyValue("ed",pkgdetails.date.ed, current_ed);
        Utilities.hardWait(70);


	}
    **/


    @Factory(dataProvider = "Different_pdt_types", dataProviderClass = manifestationData.class)
    public Object[] createInstances(String pdt) {
        return new Object[]{new EdUpdation(pdt)};
    }


    public EdUpdation(String pdt) {
        this.product_type2 = pdt;
        client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
        ocid = YamlReader.getYamlValues("Centers.Gurgaon").get("SortCode").toString();
        cnid = YamlReader.getYamlValues("Centers.Mumbai_MIDC").get("SortCode").toString();
    }


    @Test(dataProvider = "Different_pt_types", dataProviderClass = manifestationData.class)
    public void When_Pkg_Is_ReRouted_ED_Should_not_update(String payment_mode) {
        HashMap<String, String> clData = new HashMap<>();
        clData.put("client", "regression_client");
        clDataMap.set(clData);
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type2);
        data.put("payment_mode", payment_mode);
        String pin_for_6_days_ED_Update = "400059";
        data.put("pin", pin_for_6_days_ED_Update);
        manifestData.set(data);
        //data.put("einv_qr", "true");

        ArrayList<String> waybills = apiCtrl.cmuManifestApi(data);
        waybillListToBeProcess.set(waybills);
        waybillToBeProcess.set(waybillListToBeProcess.get().get(0));

//    		waybill = apiCtrl.cmuManifestApi(data);
        logInfo("Waybill generated " + waybillToBeProcess.get());
        if (payment_mode.equalsIgnoreCase("pickup") || payment_mode.equalsIgnoreCase("cash")) {
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), "PP", "Open", "X-UCI", clDataMap.get());

        } else {
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), "UD", "Manifested", "X-UCI", clDataMap.get());
        }
        if (product_type2.equals("B2B")) {
            Utilities.hardWait(15);
        }


        if (payment_mode == "prepaid" || payment_mode == "postpaid" || payment_mode == "cod" || payment_mode == "repl") {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybillToBeProcess.get(), "FMPICK", clDataMap.get());
            Map<String, Object> pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clDataMap.get());

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybillToBeProcess.get(), "FMDEPART", clDataMap.get());
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clDataMap.get());

        }

        //GI on pickup or cash pkgs
        if (payment_mode == "cash" || payment_mode == "pickup") {
            waybillListToBeProcess.set(Collections.singletonList(waybillToBeProcess.get()));
            apiCtrl.ApplyNsl(waybillListToBeProcess.get(), "PP", "X-ASP", manifestData.get());
            apiCtrl.markRerveseShipmentDispatchApi(waybillToBeProcess.get(), manifestData.get());
            apiCtrl.lmUpdateHQShipmentApi(waybillToBeProcess.get(), "PickedUp", manifestData.get());
            //apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId,data);
            String fm_loc = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clDataMap.get()).cs.sl;
            //apiCtrl.giApi(waybill, fm_loc, clData);
        }

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clDataMap.get());
        String current_ed = pkgs.date.ed;
        logInfo("Current ED" + current_ed);

        //Edit pkg CN
        //editing add
        if (payment_mode == "prepaid" || payment_mode.equalsIgnoreCase("postpaid") || payment_mode == "cod" || payment_mode == "repl") {
            HashMap<String, String> editAdd = new HashMap<>();
            editAdd.put("add", add_for_addfix_callback); // aseg update pin to 122003
            editDataMap.set(editAdd);
            apiCtrl.EditApi(waybillToBeProcess.get(), editDataMap.get());


            //waiting period for pincode update
            Utilities.hardWait(10);
            PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clDataMap.get());
            Assertions.assertKeyValue("ed", pkgdetails.date.ed, current_ed);
            Utilities.hardWait(70);
        }
        postProcess();
    }

    @Test(dataProvider = "Different_pt_types", dataProviderClass = manifestationData.class)
    public void When_Pkg_Is_ReRouted_ED_Should_update(String pt) {
        String payment_mode = pt;
        HashMap<String, String> clData = new HashMap<>();
        clData.put("client", "regression_client");
        clDataMap.set(clData);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("product_type", product_type2);
        data.put("payment_mode", payment_mode);
        String pin_for_today_ED = "302001";
        data.put("pin", pin_for_today_ED);
        //data.put("einv_qr", "true");
        manifestData.set(data);

        ArrayList<String> waybills = apiCtrl.cmuManifestApi(manifestData.get());
        waybillListToBeProcess.set(waybills);
        waybillToBeProcess.set(waybillListToBeProcess.get().get(0));
        logInfo("Waybill generated " + waybillToBeProcess.get());
        if (payment_mode == "pickup" || payment_mode == "cash") {
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), "PP", "Open", "X-UCI", clDataMap.get());

        } else {
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), "UD", "Manifested", "X-UCI", clDataMap.get());
        }
        if (product_type2.equals("B2B")) {
            Utilities.hardWait(15);
        }


        if (payment_mode == "prepaid" || payment_mode == "postpaid" || payment_mode == "cod" || payment_mode == "repl") {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybillToBeProcess.get(), "FMPICK", clDataMap.get());
            Map<String, Object> pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clDataMap.get());

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybillToBeProcess.get(), "FMDEPART", clDataMap.get());
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
            apiCtrl.verifyPackageFetchInfoApi(waybillToBeProcess.get(), pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clDataMap.get());

        }

        //GI on pickup or cash pkgs
        if (payment_mode == "cash" || payment_mode == "pickup") {
            waybills.add(waybillToBeProcess.get());
            apiCtrl.ApplyNsl(waybills, "PP", "X-ASP", manifestData.get());
            apiCtrl.markRerveseShipmentDispatchApi(waybillToBeProcess.get(), manifestData.get());
            apiCtrl.lmUpdateHQShipmentApi(waybillToBeProcess.get(), "PickedUp", manifestData.get());
            //apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId,data);
            String fm_loc = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clDataMap.get()).cs.sl;
            //apiCtrl.giApi(waybill, fm_loc, clDataMap.get());
        }

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clDataMap.get());
        String current_ed = pkgs.date.ed;
        logInfo("Current ED" + current_ed);

        //Edit pkg CN
        //editing add
        if (payment_mode == "prepaid" || payment_mode == "postpaid" || payment_mode == "cod" || payment_mode == "repl") {
            HashMap<String, String> editAdd = new HashMap<>();
            editAdd.put("add", add_for_addfix_callback); // aseg update pin to 122003
            editDataMap.set(editAdd);
            apiCtrl.EditApi(waybillToBeProcess.get(), editDataMap.get());

            //waiting period for pincode update
            Utilities.hardWait(10);
            PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clDataMap.get());
            Assertions.assertKeyValue("ed", pkgdetails.date.ed, current_ed);
            Utilities.hardWait(70);
        }
        postProcess();
    }

    //Remove thread local data associated with caller thread
    private void postProcess() {
        clDataMap.remove();
        editDataMap.remove();
        manifestData.remove();
    }
}
