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
import com.delhivery.Express.pojo.applynsl.response.applynslresponse;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
@Deprecated
public class applynsl extends BaseTest {
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

    public applynsl() {
        this.scenario = "Package Flow B2C Prepaid type shipment";
        this.product_type = "B2C";
        this.payment_mode = "Prepaid";
        this.pin = "400059";
        this.package_count = "3";
        client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
        ocid = YamlReader.getYamlValues("Centers.Gurgaon").get("SortCode").toString();
        cnid = YamlReader.getYamlValues("Centers.Mumbai_MIDC").get("SortCode").toString();

    }
    
   
	 @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
	    public void WhenEinvQrKeyIsTrue(String scenario, String product_type, String payment_mode) {
	    	clData.put("client", "regression_client");
			HashMap<String,String> data = new HashMap<String,String>();
			data.put("product_type",product_type);
			data.put("payment_mode", payment_mode);
			data.put("pin", "400059");
			//data.put("einv_qr", "true");
			
			waybills = apiCtrl.cmuManifestApi(data);
			String waybill = waybills.get(0);
//			waybill = apiCtrl.cmuManifestApi(data);
			logInfo("Waybill generated " + waybill);
			if(payment_mode == "pickup" || payment_mode =="cash") {
				apiCtrl.verifyPackageFetchInfoApi(waybill, "PP", "Open", "X-UCI", clData);
				
			}else {
				apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI", clData);
			}
			if (product_type.equals("B2B")) {
				Utilities.hardWait(15);
			}
			//Fetch Pkg Info
			PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
			assertKeyValue("einv_qr", true, pkgs.einvQr);
			
			logInfo("Scan REMARK : " + pkgs.einvQr);
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
	        //Apply NSL
			Utilities.hardWait(2);
			
			applynslresponse apiResponse = apiCtrl.Applynsl(waybills, "DLYLH-145", clData);
			
			if(scenario.contains("cash")||scenario.contains("pickup")) {
				apiCtrl.verifyPackageFetchInfoApi(waybill, "PP", "Open", "X-UCI", clData);
			}else {
				assertKeyValue("success", true, apiResponse.success);
				apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
			}
			
		
		}
	 @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
	    public void WhenEinvQrKeyisFalse(String scenario, String product_type, String payment_mode) {
	    	clData.put("client", "regression_client");
			HashMap<String,String> data = new HashMap<String,String>();
			data.put("product_type",product_type);
			data.put("payment_mode", payment_mode);
			data.put("pin", "400059");
			data.put("einv_qr", "false");
			
			waybills = apiCtrl.cmuManifestApi(data);
			String waybill = waybills.get(0);
//			waybill = apiCtrl.cmuManifestApi(data);
			logInfo("Waybill generated " + waybill);
			
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
	        
			//Fetch Pkg Info
			PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
			assertKeyValue("einv_qr", false, pkgs.einvQr);
			String last_scan=pkgs.cs.nsl;
			//apply nsl
			applynslresponse apiResponse = apiCtrl.Applynsl(waybills, "DLYLH-145", clData);
			assertKeyValue("success", true, apiResponse.success);
		
			Utilities.hardWait(2);
			pkgFlowData = YamlReader.getYamlValues("packageFlowScans.DocumentMissing");
			if(scenario.contains("cash") || scenario.contains("pickup")) {
				apiCtrl.verifyPackageFetchInfoApi(waybill, "PP", "Open", "X-UCI", clData);
			}else if(scenario.contains("Heavy")){
				PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybill, clData);
				assertKeyValue("nsl", last_scan, pkg2.cs.nsl);
			}
			else {
				apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
			}
	        
			
		}
	 
	
		
		@Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
	    public void WhenNslAppliedOnDifferentPkgStateTrueValue(String scenario, String product_type, String state) {
	    	clData.put("client", "regression_client");
			HashMap<String,String> data = new HashMap<String,String>();
			data.put("product_type",product_type);
			data.put("pin", "400059");
			data.put("einv_qr", "true");
			String waybill = diffStShpt.DifferentStateShipments(state,data);
			logInfo("Waybill " + waybill);
			
			//Fetch Pkg Info
			PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
			assertKeyValue("einv_qr", true, pkgs.einvQr);
			List<String> list = Arrays.asList(waybill);
			String last_scan=pkgs.cs.nsl;
	        //Apply NSL
			Utilities.hardWait(2);
			applynslresponse apiResponse = apiCtrl.Applynsl(list, "DLYLH-145", clData);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("nsl", last_scan, pkgs.cs.nsl);
			
			
			
		
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
			
	        //Apply NSL
			Utilities.hardWait(2);
			applynslresponse apiResponse = apiCtrl.Applynsl(list, "DLYLH-145", clData);
			
				assertKeyValue("success", true, apiResponse.success);
				if(scenario.contains("manifested") || scenario.contains("In transit") || scenario.contains("Pending") || scenario.contains("Returned")) {
					PackageDetail pkg = apiCtrl.fetchPackageInfo(waybill, clData);
					assertKeyValue("nsl", "DLYLH-145", pkg.cs.nsl);
				}else if(scenario.contains("cancelled")) {
					apiCtrl.verifyPackageFetchInfoApi(waybill, "CN", "Cancelled", "X-UCI", clData);
				}else if(scenario.contains("Delivered")) {
					apiCtrl.verifyPackageFetchInfoApi(waybill, "DL", "Delivered", "EOD-38", clData);
				}else if(scenario.contains("PickupPending")) {
					apiCtrl.verifyPackageFetchInfoApi(waybill, "PP", "Open", "X-UCI", clData);
				}else if(scenario.contains("PickedUp")) {
					apiCtrl.verifyPackageFetchInfoApi(waybill, "PU", "In transit", "DLYLH-145", clData);
				}
		
		}
	 
	
		
		@Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
	    public void WhenNslAppliedOnDiffPkgsTypeTrueValue(String scenario, String type) {
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
		
			//Fetch Pkg Info
			PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
			
	        System.out.println(waybills);
	        //Apply NSL
			Utilities.hardWait(2);
			applynslresponse apiResponse = apiCtrl.Applynsl(waybills, "DLYLH-145", clData);
			assertKeyValue("success", true, apiResponse.success);
			String last_scan=pkgs.cs.nsl;
			
		}
		
		@Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
	    public void WhenNslAppliedOnDiffPkgsTypeFalseValue(String scenario, String type) {
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
		
			//Fetch Pkg Info
			PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
			
	        System.out.println(waybills);
	        //Apply NSL
			Utilities.hardWait(2);
			applynslresponse apiResponse = apiCtrl.Applynsl(waybills, "DLYLH-145", clData);
			assertKeyValue("success", true, apiResponse.success);
			String last_scan=pkgs.cs.nsl;
			
		}


}
