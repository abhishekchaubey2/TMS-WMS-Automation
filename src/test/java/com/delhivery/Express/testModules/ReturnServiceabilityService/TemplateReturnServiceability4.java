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
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class TemplateReturnServiceability4 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public TemplateReturnServiceability4() {
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
		apiCtrl.fmOMSApi(Waybill, "FMPICK", data);
		//Performing FM of child package if present
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			apiCtrl.fmOMSApi(CWaybill, "FMPICK", data);
		}
		
		if(data.containsKey("B2B")|| data.containsKey("Heavy")) {
			Utilities.hardWait(120);
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
	
	//Diff state nsz
	@DataProvider(name = "aseg_updates_nsz_cn_diff_state_shipments", parallel = false)
	public Object[][] aseg_updayes_nsz_cn_diff_state_shipments() {
		return new Object[][] {
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110044AAB", "Delhi", "Del_Okhla_PC (DELHI)", "IND110044AAB", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-203", "RT", "Package details changed by shipper", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-DDD3FD", "UD", "Out for delivery","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-38", "DL", "Delivered to consignee","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-203", "RT", "Package details changed by shipper", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null" },
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST" ,"Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-203", "PP", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-203", "PU", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "RT-111", "DL", "DTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-108", "CN", "QC failed at pickup. Product count mismatch","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Pending", "KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "IND110064AAB", "Delhi", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Delhi_East_RPC (Delhi)", "Delhi_East_RPC (Delhi)", "IND110096AAA", "null", "null", "Delhi", "null"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Dispatched", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-DDD3FD", "UD", "Out for delivery","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Delivered", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "EOD-38", "DL", "Delivered to consignee","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Returned" , "KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "IND110092AAB", "Delhi", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Delhi_East_RPC (Delhi)", "Delhi_East_RPC (Delhi)", "IND110096AAA", "null", "null", "Delhi", "null"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C","RTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-110", "DL", "RTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" , "LOST", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "LT-100", "LT", "Shipment LOST","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"PickupPending", "KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "Delhi", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-203", "PP", "Package details changed by shipper", "Delhi_East_RPC (Delhi)","Delhi_East_RPC (Delhi)",  "IND110096AAA", "null", "null", "Delhi", "null"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "PickedUp", "KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "Delhi", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-203", "PU", "Package details changed by shipper","Delhi_East_RPC (Delhi)", "Delhi_East_RPC (Delhi)",  "IND110096AAA", "null", "null", "Delhi", "null"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "DTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "RT-111", "DL", "DTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Cancelled" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"}
		};
	}
	
	@Test(dataProvider = "aseg_updates_nsz_cn_diff_state_shipments", enabled = true)
	public void verifyCnaddUpdatedtoNSZForDiffStateShipment(String Scenario, String type,String state,
			String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
		clData = new HashMap<String,String>();
		clData.put("client", "custom_srv_client_2");
		clData.put("product_type", type);
		clData.put("return_add", "Ghondli");
		clData.put("return_city", "Delhi");
		clData.put("return_state", "Delhi");
		clData.put("return_pin", "110031");
		waybill = diffStShpt.DifferentStateShipments(state,clData);
		logInfo("Waybill " + waybill);
		Utilities.hardWait(20);
		
		//Updating weight
		clData.put("wt_rule", "Predicted_CTLG");
		if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
			clData.put("SecondPojo", "true");
		}
		
		Utilities.hardWait(10);
		
		//Returning the package
		if(state.equalsIgnoreCase("Returned") || state.equalsIgnoreCase("PickupPending") || state.equalsIgnoreCase("PickedUp")){
			//Do nothing
		}else if(state.equalsIgnoreCase("Pending")){
			List<String> wbns11 = new ArrayList<>();
			wbns11.add(waybill);
			apiCtrl.ApplyNsl(wbns11, "RT", "RT-101",clData);
			Utilities.hardWait(10);
			//Mark package pending
			PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			String Pending_rcn = pkgDetails.rcn;
			apiCtrl.giApi(waybill, Pending_rcn, clData);
		}else {
			MarkReturnAfterGI(waybill, waybill, clData);
		}
		
		//Editing radd	
		clData.put("return_add", "Theka road");
		apiCtrl.EditApi(waybill, clData);
		
		Utilities.hardWait(60);
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
			PackageDetail2 pkgDetails = apiCtrl.fetchPackageInfo2(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys2(pkgDetails, Expected_values);
		}else {
			PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		}

	}
	
	//cn = NSZ, invalid,No data exist
	
	//SRV TRIGGER FOR IN-TRANSIT SHIPMENTS , WT RULE -> "Predicted_CTLG"--> DONE
	@DataProvider(name = "Wrong_cn_cases", parallel = false)
	public Object[][] Wrong_cn_cases() {
		return new Object[][] { 
			{ "Scenario:: B2B package with no data exist", "B2B" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "RT-101", "RT", "Returned as per Client Instructions","Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B package with invalid cn", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "RT-101", "RT", "Returned as per Client Instructions","Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B package with NSZ cn", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "RT-101", "RT", "Returned as per Client Instructions","Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2C package with no data exist", "B2C" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "KN_RPC (DELHI)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2C package with invalid cn", "B2C" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "KN_RPC (DELHI)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2C package with NSZ cn", "B2C" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "KN_RPC (DELHI)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			};
	}
	
	@Test(dataProvider = "Wrong_cn_cases" , enabled = true)
	public void VerifyServiceabilityTriggerWrongCN(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "custom_srv_client_2");
		
		if(Scenario.contains("NSZ cn")) {
			clData.put("return_pin", "110033");
			clData.put("return_add", "Burari");
		}else if(Scenario.contains("no data exist")) {
			clData.put("return_pin", "312456");
			clData.put("return_add", "test");
		}else {
			//invalid cn
			clData.put("return_pin", "133101");
			clData.put("return_add", "Saha");
		}
		
		Payload = clData;
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("HEAVY")) {
			Utilities.hardWait(80);
		}
		
		clData.put("cwh_uuid","");
		MarkReturnAfterGI(waybill, waybill, clData);
		
		
		Utilities.hardWait(20);
		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
	}
	
	//Amazon case with rpin and radd both
	@DataProvider(name = "Custom_Data_Set_In_Client_Config_For_Amazon_Client", parallel = false)
	public Object[][] Custom_Data_Set_In_Client_Config_For_Amazon_Client() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not received from Client", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "null", "null"},
			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Set_In_Client_Config_For_Amazon_Client" , enabled = false)
	public void VerifyCustomServiceabilityAmazonClient(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_amazon_client");
		Payload.put("client", "custom_amazon_client");
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
		
		Utilities.hardWait(60);
		
		//Returning the package
		List<String> wbns = new ArrayList<>();
		wbns.add(waybill);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			wbns.add(child_waybill);
		}
		apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
		
		Utilities.hardWait(10);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid,	 rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	//Amazon case with rpin only
	@DataProvider(name = "Only_Rpin_Passed_While_Custom_Data_Set_In_Client_Config_For_Amazon_Client", parallel = false)
	public Object[][] Only_Rpin_Passed_While_Custom_Data_Set_In_Client_Config_For_Amazon_Client() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not received from Client", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "null", "null"},
			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Only_Rpin_Passed_While_Custom_Data_Set_In_Client_Config_For_Amazon_Client" , enabled = false)
	public void VerifyCustomServiceabilityAmazonCLientRpinOnlyAmazon(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_amazon_client");
		Payload.put("client", "custom_amazon_client");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "");
		Payload.put("return_city", "");
		Payload.put("return_state", "");
		
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
		
		Utilities.hardWait(60);
		
		//Returning the package
		List<String> wbns = new ArrayList<>();
		wbns.add(waybill);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			wbns.add(child_waybill);
		}
		apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
		
		Utilities.hardWait(10);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid,	 rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
}
