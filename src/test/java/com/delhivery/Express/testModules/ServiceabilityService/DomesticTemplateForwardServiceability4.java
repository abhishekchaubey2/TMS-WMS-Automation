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

public class DomesticTemplateForwardServiceability4 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public DomesticTemplateForwardServiceability4() {
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

	
	//Pincode updated
	@DataProvider(name = "Pincode_Updated_While_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Pincode_Updated_While_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""}
			};
	}
	
	@Test(dataProvider = "Pincode_Updated_While_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityWithPincodeUpdation(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("add", "Test");
		Payload.put("state", "");
		Payload.put("city", "");
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
		
		//update pincode
		Payload.put("pincode", "122003");
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
	
	@DataProvider(name = "PDA_Switch_To_Secondary_Address", parallel = false)
	public Object[][] PDA_Switch_To_Secondary_Address() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: B2C package", "B2C" ,  "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "Heavy", "Barauli_SiwanRd_D (Bihar)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""}
			};
	}
	
	@Test(dataProvider = "PDA_Switch_To_Secondary_Address" , enabled = true)
	public void PDA_Switch_To_Secondary_Address(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
		Payload.put("pin", "122003");
		Payload.put("secondary_name", "Test");
		Payload.put("secondary_add", "Godoli");
		Payload.put("secondary_city", "Gurgaon");
		Payload.put("secondary_state", "Haryana");
		Payload.put("secondary_pin", "122001");
		
		
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
		
		// Perform GI
		String gi_cn = apiCtrl.fetchPackageInfo(waybill, clData).cn;
		apiCtrl.giApi(waybill, gi_cn, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, gi_cn, clData);
		}
		
		Utilities.hardWait(5);
		//Mark out for delivery
		clData.put("shipment_destination_center", gi_cn);
		clData.put("MPS", "true");
		String dispatchId = apiCtrl.markShipmentDispatchApi(waybill,clData);
		String dispatchId2 = "";
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			 dispatchId2 = apiCtrl.markShipmentDispatchApi(child_waybill,clData);
		}
		Utilities.hardWait(5);
		//Applying pda switching NSL
		apiCtrl.lmUpdateHQShipmentApi(waybill, "PDA",clData);
		apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId,clData);
		
		if(!dispatchId2.equalsIgnoreCase("")) {
			apiCtrl.lmUpdateHQShipmentApi(child_waybill, "PDA",clData);
			apiCtrl.unsetShipmentDispatchIdApi(child_waybill, dispatchId,clData);
		}
		
		Utilities.hardWait(60);
		
		
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
	
	
	//Passing add such that aseg updates pin after PDA switching
	@DataProvider(name = "Address_Update_After_PDA_Switch_To_Secondary_Address", parallel = false)
	public Object[][] PDA_Switch_ToAddress_Update_After_PDA_Switch_To_Secondary_Address_Secondary_Address() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Del_Okhla_PC (Delhi)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: B2C package", "B2C" , "KN_RPC (DELHI)", "IND110064AAB", "E", "null", "null", "IND110092AAB", "ro", "null", "", "Heavy", "KN_RPC (DELHI)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "null", "", "Heavy", "Del_Okhla_PC (Delhi)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" ,  "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Del_Okhla_PC (Delhi)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Del_Okhla_PC (Delhi)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"KN_RPC (DELHI)", "IND110064AAB", "E", "null", "null", "IND110092AAB", "ro", "null", "", "", "KN_RPC (DELHI)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" ,  "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Del_Okhla_PC (Delhi)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"KN_RPC (DELHI)", "IND110064AAB", "E", "null", "null", "IND110092AAB", "ro", "null", "", "Heavy", "KN_RPC (DELHI)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Del_Okhla_PC (Delhi)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""}
			};
	}
	
	@Test(dataProvider = "Address_Update_After_PDA_Switch_To_Secondary_Address" , enabled = true)
	public void Address_Update_After_PDA_Switch_To_Secondary_Address(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
		Payload.put("pin", "122003");
		Payload.put("secondary_name", "Test");
		Payload.put("secondary_add", "Baliawas");
		Payload.put("secondary_city", "Gurgaon");
		Payload.put("secondary_state", "Haryana");
		Payload.put("secondary_pin", "122001");
		
		
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

		Utilities.hardWait(30);

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
		
		// Perform GI
		String gi_cn = apiCtrl.fetchPackageInfo(waybill, clData).cn;
		apiCtrl.giApi(waybill, gi_cn, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, gi_cn, clData);
		}
		
		//Mark out for delivery
		clData.put("shipment_destination_center", gi_cn);
		clData.put("MPS", "true");
		String dispatchId = apiCtrl.markShipmentDispatchApi(waybill,clData);
		String dispatchId2 = "";
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			 dispatchId2 = apiCtrl.markShipmentDispatchApi(child_waybill,clData);
		}
		
		//Applying pda switching NSL
		apiCtrl.lmUpdateHQShipmentApi(waybill, "PDA",clData);
		apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId,clData);
		
		if(!dispatchId2.equalsIgnoreCase("")) {
			apiCtrl.lmUpdateHQShipmentApi(child_waybill, "PDA",clData);
			apiCtrl.unsetShipmentDispatchIdApi(child_waybill, dispatchId,clData);
		}
		
		Utilities.hardWait(60);
		
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
