package com.delhivery.Express.testModules.ServiceabilityService;

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

public class DomesticTemplateForwardServiceability extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public DomesticTemplateForwardServiceability() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

	}
	
	public void UpdateWeightDiffEnv(String Env,String Current_scenario, String waybill, HashMap<String,String> Data) {
		if (Current_scenario.contains("weight less than 10 kg")) {
			apiCtrl.QcWtApiDiffEnv(Data.get("enviorment"), waybill, 9999.12, "Predicted_CTLG",Data);
			
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(Data.get("enviorment"),waybill,Data);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 9999.12, iwt);
			Utilities.hardWait(40);
		} else if (Current_scenario.contains("weight more than 10 kg")) {
			apiCtrl.QcWtApiDiffEnv(Data.get("enviorment"), waybill, 15000.12, "Predicted_CTLG",Data);
			
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(Data.get("enviorment"),waybill,Data);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 15000.12, iwt);
			Utilities.hardWait(40);
//			assertKeyValue("cpdt", "Heavy", pkgdetails.packages.get(0).cpdt);
		}
	}

	public void FillExpectedValues(String cn, String cnid, String rgn, String sc, String srv, String ocid, 
			String cnc,String dpc, String wvcid, String cpdt,String cn1, String dpcid,String cs_nsl,String cs_sr,String cs_st,String cs_ss,String pin,String rpin) {
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
		Expected_values.put("cs.ss", cs_ss);
		Expected_values.put("pin", pin);
		Expected_values.put("rpin", rpin);

	}
	
	
	@DataProvider(name = "Weight_Passed_While_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Weight_Passed_While_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C package", "B2C" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "null", "IND110092AAB", "Mumbai", "null", "", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-231","UD" ,"Center changed by system","","",""},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "null", "IND110092AAB", "Mumbai", "null", "", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}
			};
	}
	
	@Test(dataProvider = "Weight_Passed_While_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityWithWeightPassed(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("pin","122001"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(55);
		}
		
		// Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}

		Utilities.hardWait(20);

		// Perform GI
		String fm_loc = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
		apiCtrl.giApi(waybill, fm_loc, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, fm_loc, clData);
		}

		Utilities.hardWait(5);
		
		//update weight -> on master shipment only
		if(Scenario.contains("B2C")) {
			apiCtrl.QcWtApi(waybill, 15000.12, "sorter", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 9999.12, "sorter", clData);
		}
		
		
		Utilities.hardWait(40);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Add_Updated_While_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Add_Updated_While_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2C package", "B2C" , "KN_RPC (DELHI)", "IND110064AAB", "E", "null", "null", "IND110092AAB", "ro", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-219", "UD", "Pincode updated by Addfix","","",""}
			};
	}
	
	@Test(dataProvider = "Add_Updated_While_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityWithAddUpdation(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("state", "Haryana");
		Payload.put("city", "Gurgaon");
		Payload.put("add", "Godoli");
		Payload.put("pin","122001"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(50);
		}
		
		// Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}

		Utilities.hardWait(10);
		
		//update add
		Payload.put("add", "Baliawas");
		apiCtrl.EditApi(waybill, Payload);
		
		
		Utilities.hardWait(130);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}

	@DataProvider(name = "Add_Updated_After_Weight_Updation_While_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Add_Updated_After_Weight_Updation_While_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2C package", "B2C" , "KN_RPC (DELHI)", "IND110064AAB", "W", "1028", "null", "IND110092AAB", "ro", "null", "", "Heavy", "KN_RPC (DELHI)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-219", "UD", "Pincode updated by Addfix","","",""}
			};
	}
	
	@Test(dataProvider = "Add_Updated_After_Weight_Updation_While_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityWithAddAndWeightUpdation(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("state", "Haryana");
		Payload.put("city", "Gurgaon");
		Payload.put("add", "Godoli");
		Payload.put("pin","122001"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(50);
		}
		
		// Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}

		Utilities.hardWait(10);
		
		if(Scenario.contains("B2C")) {
			apiCtrl.QcWtApi(waybill, 15000.12, "sorter", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 9999.12, "sorter", clData);
		}
		
		Utilities.hardWait(15);
		
		//update add
		Payload.put("add", "Baliawas");
		apiCtrl.EditApi(waybill, Payload);
		
		
		Utilities.hardWait(130);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Custom_Data_Set_In_Client_Config_but_pin_has_no_data", parallel = false)
	public Object[][] Custom_Data_Set_In_Client_Config_but_pin_has_no_data() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "null", "Delhi", "null", "null", "B2B", "Delhi_Govindpura (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "Delhi_AshokNgr_DC (Delhi)", "IND110096AAB", "", "1242", "null", "null", "Delhi", "null", "null", "", "Delhi_AshokNgr_DC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "null", "Delhi", "null", "null", "B2B", "Delhi_Govindpura (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Set_In_Client_Config_but_pin_has_no_data" , enabled = true)
	public void VerifyCustomServiceabilityWithNODataForPin(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("pin","110032"); 
		
		if(type.equalsIgnoreCase("B2C")){
			Payload.put("pin","110098"); 
		}
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		Utilities.hardWait(100);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Custom_Data_Set_In_Client_Config_but_new_pin_has_no_data", parallel = false)
	public Object[][] Custom_Data_Set_In_Client_Config_but_new_pin_has_no_data() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "null", "Delhi", "null", "null", "B2B", "Delhi_Govindpura (Delhi)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "null", "Delhi", "null", "null", "B2B", "Delhi_Govindpura (Delhi)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Set_In_Client_Config_but_new_pin_has_no_data" , enabled = true)
	public void VerifyCustomServiceabilityWithNODataForNewPin(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("add", "Pushta Rd, near Sai Bhawan, opposite CNG pump, Geeta Colony, Delhi, 110031");
		Payload.put("client", "custom_srv_client");
		Payload.put("city", "Delhi");
		Payload.put("state", "Delhi");
		Payload.put("add", "Chabi Ganj");
		Payload.put("pin","110031"); 
		
//		if(type.equalsIgnoreCase("B2C")){
//			Payload.put("pin","110098"); 
//		}
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		Utilities.hardWait(30);
		HashMap<String,String> edit_payload = new HashMap<>();
		edit_payload.put("client", "custom_srv_client");
		edit_payload.put("add", "CBD, Maharaja Surajmal Marg, near YAMUNA SPORTS COMPLEX, Vishwas Nagar Extension, Vishwas Nagar, Shahdara, Delhi");
		if(type.equalsIgnoreCase("B2C")){
			Payload.put("add","Teliwara"); 
		}
		apiCtrl.EditApi(waybill, edit_payload);
		
		Utilities.hardWait(200);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		if(Scenario.contains("B2C")) {
			assertKeyValue("pin", "110031", pkgDetails.pin.toString());
		}else {
			assertKeyValue("pin", "110032", pkgDetails.pin.toString());
		}
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Custom_Data_Set_In_Client_Config_but_old_pin_has_no_data", parallel = false)
	public Object[][] Custom_Data_Set_In_Client_Config_but_old_pin_has_no_data() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Set_In_Client_Config_but_old_pin_has_no_data" , enabled = true)
	public void VerifyCustomServiceabilityWithNODataForOldPin(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("add", "CBD, Maharaja Surajmal Marg, near YAMUNA SPORTS COMPLEX, Vishwas Nagar Extension, Vishwas Nagar, Shahdara, Delhi");
		Payload.put("client", "custom_srv_client");
		Payload.put("pin","110032"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		Utilities.hardWait(30);
		HashMap<String,String> edit_payload = new HashMap<>();
		edit_payload.put("client", "custom_srv_client");
		edit_payload.put("add", "Pushta Rd, near Sai Bhawan, opposite CNG pump, Geeta Colony, Delhi, 110031");
		apiCtrl.EditApi(waybill, edit_payload);
		
		Utilities.hardWait(200);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		assertKeyValue("pin", "110031", pkgDetails.pin.toString());
		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Custom_Data_Not_Set_In_Client_Config", parallel = false)
	public Object[][] Custom_Data_Not_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" ,  "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded","","",""}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Not_Set_In_Client_Config" , enabled = true)
	public void VerifyNoCustomServiceability(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		Utilities.hardWait(100);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
}
