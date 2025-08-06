package com.delhivery.Express.testModules.ReturnServiceabilityService;
import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class ReturnInternalWeightServiceability extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ReturnInternalWeightServiceability() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

	}
	
	public void FillExpectedValues(String cn, String cnid, String rgn, String sc, String srv, String ocid, 
			String cnc,String dpc, String wvcid, String cpdt,String cn1, String dpcid,String cs_nsl,String cs_sr,String cs_st,String rcn ,String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
		Expected_values = new LinkedHashMap<String, String>();
		Expected_values.put("cn", cn);
		Expected_values.put("cnid", cnid);
		Expected_values.put("rgn", rgn);
		Expected_values.put("sc", sc);
		Expected_values.put("srv", srv);
		Expected_values.put("ocid", ocid);
		Expected_values.put("cnc", cnc);
		Expected_values.put("dpc", dpc);
		Expected_values.put("wvcid", wvcid);
		Expected_values.put("cpdt", cpdt);
		Expected_values.put("cn1", cn1);
		Expected_values.put("dpcid", dpcid);
		Expected_values.put("cs.nsl", cs_nsl);
		Expected_values.put("cs.st", cs_st);
		Expected_values.put("cs.sr", cs_sr);
		//For return shipments
		Expected_values.put("rcn", rcn);
		Expected_values.put("rcn1", rcn1);
		Expected_values.put("rcnid", rcnid);
		Expected_values.put("rdpc", rdpc);
		Expected_values.put("rdpcid", rdpcid);
		Expected_values.put("rcty", rcty);
		Expected_values.put("rcns", rcns);
		

	}
	
	
	public void MarkReturnAfterGI(String Waybill , String CWaybill , HashMap<String,String> data) {
		//Perform FM
		data.put("cwh_uuid", "");
		apiCtrl.fmOMSApi(Waybill, "FMPICK", data);
		//Performing FM of child package if present
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			apiCtrl.fmOMSApi(CWaybill, "FMPICK", data);
		}
		
		if(data.containsKey("B2B")|| data.containsKey("Heavy")) {
			Utilities.hardWait(60);
		}
		
		
		//Perform GI
		String fm_loc  = apiCtrl.fetchPackageInfo(Waybill, data).cs.sl;
		apiCtrl.giApi(Waybill, fm_loc, data);
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			apiCtrl.giApi(CWaybill, fm_loc, data);
		}
		
		Utilities.hardWait(20);
		
		//Mark return
		HashMap<String,String> edit_payload = new HashMap<>();
		List<String> wbns = new ArrayList<>();
		wbns.add(Waybill);
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			wbns.add(CWaybill);
		}
		apiCtrl.ApplyNsl(wbns, "RT", "RT-101",data);
	}
	

	@DataProvider(name = "Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Manifested_state", parallel = false)
	public Object[][] Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Manifested_state() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "null", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with internal child", "MPS WITH MCOUNT 1","GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "null", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "null", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "null", "", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110092AAB", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PPOM", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Manifested_state" , enabled = true)
	public void VerifyInternalWeightReturnServiceabilityManifestedShipment(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");
		
		if(Scenario.contains("B2C") || Scenario.contains("Partially Manifested")){
			Payload.put("weight", "15000.12");
		}else {
			Payload.put("weight", "14000.12");
		}
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		Utilities.hardWait(5);
		
		//Updating weight manually via qc_wt API
		if(Scenario.contains("B2C") || Scenario.contains("Partially Manifested")){
			apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", Payload);
		}else {
			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", Payload);
		}
		
		Utilities.hardWait(60);
		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Transit", parallel = false)
	public Object[][] Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Transit() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with internal child", "MPS WITH MCOUNT 1","GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110092AAB", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Transit" , enabled = true)
	public void VerifyInternalWeightReturnServiceabilityInTransitShipment(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");
		
//		if(Scenario.contains("B2C") || Scenario.contains("Partially Manifested")){
//			Payload.put("weight", "15000.12");
//		}else {
//			Payload.put("weight", "14000.12");
//		}
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(60);
		}
		
		Utilities.hardWait(10);
		
		//Perform FM
		clData.put("cwh_uuid", "");
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		Utilities.hardWait(5);
		
		//Updating weight manually via qc_wt API
		if(Scenario.contains("B2C") || Scenario.contains("Partially Manifested")){
			apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", Payload);
		}else {
			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", Payload);
		}
		
		Utilities.hardWait(30);
		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Transit_RT", parallel = false)
	public Object[][] Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Transit_RT() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2C package", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110092AAB", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PIBM", "UD", "Shipment Received at Origin Center - Manifestation Pending", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Weight_Verified_Key_is_Set_as_true_In_Client_Config_In_Transit_RT" , enabled = true)
	public void VerifyInternalWeightReturnServiceabilityInReturnTransitShipment(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("weight_verification", "true");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");
		
		if(Scenario.contains("B2C") || Scenario.contains("Partially Manifested")){
			Payload.put("weight", "15000.12");
		}else {
			Payload.put("weight", "14000.12");
		}
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		//Marking the package as return
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
		Utilities.hardWait(50);

		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	//Restrict_Srv_Flag_Enabled_client
	//Weight_verification_false_client
	
	@DataProvider(name = "Weight_Verified_Key_is_Set_as_False_In_Client_Config_In_Transit_RT", parallel = false)
	public Object[][] Weight_Verified_Key_is_Set_as_False_In_Client_Config_In_Transit_RT() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "null", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
//			{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Weight_Verified_Key_is_Set_as_False_In_Client_Config_In_Transit_RT" , enabled = true)
	public void Weight_Verified_Key_is_Set_as_False_In_Client_Config_In_Transit_RT(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "Weight_verification_false_client");
		Payload.put("client", "Weight_verification_false_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");
		
		if(Scenario.contains("B2C") || Scenario.contains("Partially Manifested")){
			Payload.put("weight", "15000.12");
		}else {
			Payload.put("weight", "14000.12");
		}
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		//Marking the package as return
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
		Utilities.hardWait(50);

		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Restrict_Srv_Flag_Enabled_client_In_Client_Config_In_Transit_RT", parallel = false)
	public Object[][] Restrict_Srv_Flag_Enabled_client_In_Client_Config_In_Transit_RT() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
//			{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Restrict_Srv_Flag_Enabled_client_In_Client_Config_In_Transit_RT" , enabled = true)
	public void Restrict_Srv_Flag_Enabled_client_In_Client_Config_In_Transit_RT(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "Restrict_Srv_Flag_Enabled_client");
		Payload.put("client", "Restrict_Srv_Flag_Enabled_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");

		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		//Marking the package as return
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
		Utilities.hardWait(10);
		if(Scenario.contains("B2C")){
			apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
		}
		
		Utilities.hardWait(40);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Weight_Updated__In_RT_Pending_State", parallel = false)
	public Object[][] Weight_Updated__In_RT_Pending_State() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UNEX", "RT", "Unexpected scan", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "X-UNEX", "RT", "Unexpected scan", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UNEX", "RT", "Unexpected scan", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UNEX", "RT", "Unexpected scan", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "X-UNEX", "RT", "Unexpected scan", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,   "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UNEX", "RT", "Unexpected scan", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Weight_Updated__In_RT_Pending_State" , enabled = true)
	public void Weight_Updated__In_RT_Pending_State(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		//Marking the package as return
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
		Utilities.hardWait(10);
		
		//Performing GI at rcn 
		String gi_loc  = apiCtrl.fetchPackageInfo(waybill, clData).rcn;
		apiCtrl.giApi(waybill, gi_loc, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, gi_loc, clData);
		}
		
		if(Scenario.contains("B2C")){
			apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
		}
		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
}