package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.Arrays;
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

public class ApplyNslGeneric2 extends BaseTest{
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

    public ApplyNslGeneric2() {
        this.scenario = "Package Flow B2C Prepaid type shipment";
        this.product_type = "B2C";
        this.payment_mode = "Prepaid";
        this.pin = "400059";
        this.package_count = "3";
        client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
        ocid = YamlReader.getYamlValues("Centers.Gurgaon").get("SortCode").toString();
        cnid = YamlReader.getYamlValues("Centers.Mumbai_MIDC").get("SortCode").toString();

    }
	
	@Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void WhenNslAppliedOnDifferentPkgStateFalseValue(String scenario, String product_type, String state) {
    	clData.put("client", "regression_client");
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("product_type",product_type);
		data.put("pin", "400059");
		data.put("einv_qr", "false");
		String waybill = diffStShpt.DifferentStateShipments(state,data);
		logInfo("Waybill " + waybill);
		
		//Fetch Pkg Info
		PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
		assertKeyValue("einv_qr", false, pkgs.einvQr);
		
		List<String> list = Arrays.asList(waybill);
		String last_scan=pkgs.cs.nsl;
		ApplynslgenericResponse apiResponse;
        //Apply NSL
		Utilities.hardWait(7);
		if(scenario.contains("Returned")) {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "RT", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
			
		}else if(scenario.contains("manifested") || scenario.contains("In transit") || scenario.contains("Pending") ) {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
			
		}else if(scenario.contains("Cancelled")) {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "CN", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
			
		}else if(scenario.contains("Delivered")) {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "DL", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
			
		}else if(scenario.contains("PickupPending")) {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "PP", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
			
		}else if(scenario.contains("PickedUp")) {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "PU", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
			
		}else {
			apiResponse = apiCtrl.ApplyNslGeneric(list, "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
			
		}
		assertKeyValue("success", true, apiResponse.success);
		assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
		
		if (product_type.equals("B2B")) {
			Utilities.hardWait(10);
		}
		PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill, clData);
		
		if(pkgs.einvQr == true || scenario.contains("Delivered") ||scenario.contains("PickedUp") ||  scenario.contains("Cancelled") || scenario.contains("PickupPending")) {
			assertKeyValue("nsl", last_scan, pkg.cs.nsl);
		}else {
			assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);
	}
		
	
	}
	

	
	@Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void WhenNslAppliedOnDiffPkgsTypeTrueValue(String scenario,  String type) {
    	clData.put("client", "regression_client");
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("product_type","B2C");
		data.put("pin", "400059");
		data.put("einv_qr", "true");
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, data);
		String waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
	
        
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
        String last_scan=pkgs.cs.nsl;
        Utilities.hardWait(4);
        
        //Apply NSL
		
		if (product_type.equals("B2B")) {
			Utilities.hardWait(10);
		}
		apiCtrl.ApplyNsl(waybills, "UD", "DLYLH-145", clData);
		Utilities.hardWait(7);
		ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(waybills, "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
		assertKeyValue("success", true, apiResponse.success);
		assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
		assertKeyValue("nsl", last_scan, pkgs.cs.nsl);
		//Fetch Pkg Info
		/*if(scenario.contains("HEAVY")) {
			assertKeyValue("nsl", last_scan, pkgs.cs.nsl);
		}else {
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill, clData);
			assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);
		}
		*/
		
		
		
	
	}
	@Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void WhenNslAppliedOnDiffPkgsTypeFalseValue(String scenario,  String type) {
    	clData.put("client", "regression_client");
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("product_type","B2C");
		data.put("pin", "400059");
		data.put("einv_qr", "false");
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, data);
		String waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
	
        
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
        String last_scan=pkgs.cs.nsl;
        Utilities.hardWait(4);
        
        //Apply NSL
		
		if (product_type.equals("B2B")) {
			Utilities.hardWait(10);
		}
		apiCtrl.ApplyNsl(waybills, "UD", "DLYLH-145", clData);
		Utilities.hardWait(7);
		ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(waybills, "UD", "DLYLH-145", "Held for invoices/ Missing or Incomplete Documents", clData);
		assertKeyValue("success", true, apiResponse.success);
		assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
		//Fetch Pkg Info
		if(scenario.contains("HEAVY")) {
			assertKeyValue("nsl", last_scan, pkgs.cs.nsl);
		}else {
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill, clData);
			assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);
		}
	}
}
