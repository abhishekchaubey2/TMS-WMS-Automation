package com.delhivery.Express.testModules.ServiceabilityService;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.Utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
public class InternalWeightServiceability10 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability10() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

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
	
	public void UpdateWeight(String Current_scenario, String waybill, HashMap<String,String> clData) {
		if (Current_scenario.contains("weight less than 10 kg")) {
			if (clData.containsKey("wt_rule")) {
				apiCtrl.QcWtApi(waybill, 9999.12, clData.get("wt_rule"), clData);
			} else {
				apiCtrl.QcWtApi(waybill, 9999.12, "sorter", clData);
			}
			
			if(clData.containsKey("SecondPojo")) {
				PackageDetail2 pkgdetails = apiCtrl.fetchPackageInfo2(waybill, clData);
				double iwt = pkgdetails.intWt.wt;
				assertKeyValue("int_wt", 9999.12, iwt);
			}else {
				PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				double iwt = pkgdetails.intWt.wt;
				assertKeyValue("int_wt", 9999.12, iwt);
			}
			
			if(clData.containsKey("Consecutively")) {
				Utilities.hardWait(5);
			}else {
				Utilities.hardWait(40);
			}
		} else if (Current_scenario.contains("weight more than 10 kg")) {
			if (clData.containsKey("wt_rule")) {
				apiCtrl.QcWtApi(waybill, 15000.12, clData.get("wt_rule"), clData);
			} else {
				apiCtrl.QcWtApi(waybill, 15000.12, "sorter", clData);
			}
			
			if(clData.containsKey("SecondPojo")) {
				PackageDetail2 pkgdetails = apiCtrl.fetchPackageInfo2(waybill, clData);
				double iwt = pkgdetails.intWt.wt;
				assertKeyValue("int_wt", 15000.12, iwt);
			}else {
				PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				double iwt = pkgdetails.intWt.wt;
				assertKeyValue("int_wt", 15000.12, iwt);
			}
			
			if(clData.containsKey("Consecutively")) {
				Utilities.hardWait(5);
			}else {
				Utilities.hardWait(40);
			}
//			assertKeyValue("cpdt", "Heavy", pkgdetails.packages.get(0).cpdt);
		}
	}
	
	public void UpdateWeightInverse(String Current_scenario, String waybill, HashMap<String,String> clData) {
		if (Current_scenario.contains("weight less than 10 kg")) {
			if (clData.containsKey("wt_rule")) {
				apiCtrl.QcWtApi(waybill, 15000.12, clData.get("wt_rule"), clData);
			} else {
				apiCtrl.QcWtApi(waybill, 15000.12, "sorter", clData);
			}
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 15000.12, iwt);
			if(clData.containsKey("Consecutively")) {
				Utilities.hardWait(5);
			}else {
				Utilities.hardWait(40);
			}
		} else if (Current_scenario.contains("weight more than 10 kg")) {
			if (clData.containsKey("wt_rule")) {
				apiCtrl.QcWtApi(waybill, 9999.12, clData.get("wt_rule"), clData);
			} else {
				apiCtrl.QcWtApi(waybill, 9999.12, "sorter", clData);
			}
			
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill,clData);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 9999.12, iwt);
			if(clData.containsKey("Consecutively")) {
				Utilities.hardWait(5);
			}else {
				Utilities.hardWait(40);
			}
//			assertKeyValue("cpdt", "Heavy", pkgdetails.packages.get(0).cpdt);
		}
	}
	
	public void UpdateWeightDiffEnv(String Env,String Current_scenario, String waybill, HashMap<String,String> Data) {
		if (Current_scenario.contains("weight less than 10 kg")) {
			apiCtrl.QcWtApiDiffEnv(Data.get("enviorment"), waybill, 9999.2, "Predicted_CTLG",Data);
			
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(Data.get("enviorment"),waybill,Data);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 9999.12, iwt);
			Utilities.hardWait(40);
		} else if (Current_scenario.contains("weight more than 10 kg")) {
			apiCtrl.QcWtApiDiffEnv(Data.get("enviorment"), waybill, 10000.2, "Predicted_CTLG",Data);
			
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(Data.get("enviorment"),waybill,Data);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 15000.12, iwt);
			Utilities.hardWait(40);
//			assertKeyValue("cpdt", "Heavy", pkgdetails.packages.get(0).cpdt);
		}
	}
	
	
	public void setDataForNoData(HashMap<String,String> payloadData) {
		payloadData.put("product_type", "B2B");
		payloadData.put("pin", "122001");
		payloadData.put("add", "Palika bazar");
		payloadData.put("city", "Gurgaon");
	}
		
	//Toggling SRV 2 Times
	@DataProvider(name = "Triggering_SRV_Twice", parallel = false)
	public Object[][] Triggering_SRV_Twice() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}};
	}
	
	//108-114 Triggering_SRV_Twice
	@Test(dataProvider = "Triggering_SRV_Twice" , enabled = true)
	public void VerifyServiceabilityTriggerTWICE(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "regression_client");
		
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
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		Utilities.hardWait(15);
		
		
		//Perform GI
		String fm_loc  = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
		apiCtrl.giApi(waybill, fm_loc, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, fm_loc, clData);
		}
		
		Utilities.hardWait(60);
		
		//update weight -> on master shipment only
		clData.put("wt_rule", "Predicted_CTLG");
		UpdateWeight(Scenario, waybill, clData);
		
		
		Utilities.hardWait(60);
		
		
		//update weight again -> on master shipment only
		clData.put("wt_rule", "Predicted_CTLG");
		UpdateWeightInverse(Scenario, waybill, clData);
		
		
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
	
	
	//115-121 Toggling SRV 2 Times without gap 
	@DataProvider(name = "Triggering_SRV_Twice_Consecutively_Without_Gap", parallel = false)
	public Object[][] Triggering_SRV_Twice_Consecutively_Without_Gap() {
		return new Object[][] {
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2C package with weight more than 10 kg", "B2C", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2B MPS with internal child with weight less than 10 kg","B2B MPS WITH INTERNAL CHILD", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1","Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS", "Gurgaon_Kadipur (Haryana)","IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B","Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
				{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS", "Del_B_RPC (Delhi)", "IND110037AAB", "N", "249", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""} };
	}
			
	// 115-121 Triggering_SRV_Twice
	@Test(dataProvider = "Triggering_SRV_Twice_Consecutively_Without_Gap", enabled = false)
	public void VerifyServiceabilityTriggerConsecutively(String Scenario, String type, String cn, String cnid,
			String rgn, String sc, String srv, String ocid, String cnc, String dpc, String wvcid, String cpdt,
			String cn1, String dpcid, String cs_nsl, String cs_st, String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "regression_client");

		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);

		// Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}

		if (Scenario.contains("B2B") || Scenario.contains("Heavy")) {
			Utilities.hardWait(50);
		}

		// Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}

		if (Scenario.contains("B2B") || Scenario.contains("Heavy")) {
			Utilities.hardWait(40);
		}
		Utilities.hardWait(15);

		// Perform GI
		String fm_loc = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
		apiCtrl.giApi(waybill, fm_loc, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, fm_loc, clData);
		}

		Utilities.hardWait(60);

		// update weight -> on master shipment only
		clData.put("wt_rule", "Predicted_CTLG");
		clData.put("Consecutively", "YES");
		UpdateWeight(Scenario, waybill, clData);

		// update weight again -> on master shipment only
		clData.put("wt_rule", "Predicted_CTLG");
		UpdateWeightInverse(Scenario, waybill, clData);
		
		Utilities.hardWait(20);
		
		// Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt, cn1, dpcid, cs_nsl, cs_sr, cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);

		// Asserting values on child waybill if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
	}
	
	
	//98-102
	@DataProvider(name = "PT_Is_Missing_FOR_NEW_CPDT", parallel = false)
	public Object[][] PT_Is_Missing_FOR_NEW_CPDT() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Samalkha_DC (Delhi)", "IND000000AEY", "", "591", "null", "IND110092AAB", "Delhi", "null", "", "", "Samalkha_DC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "null", "Heavy", "Delhi_Paschim_DC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS WITH MCOUNT 1","Samalkha_DC (Delhi)", "IND000000AEY", "", "591", "null", "IND110092AAB", "Delhi", "null", "", "", "Samalkha_DC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}};
	}
	
	@Test(dataProvider = "PT_Is_Missing_FOR_NEW_CPDT" , enabled = true)
	public void VerifyServiceabilityTriggerPTMissing(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "regression_client");
		if(Scenario.contains("B2C")) {
			Payload.put("pin","110074");
			Payload.put("payment_mode","COD");
		}else {
			Payload.put("pin","110088");
			Payload.put("payment_mode","COD");
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
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(50);
		}
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		
		
		//Perform GI
		String fm_loc = "East_Delhi (DELHI)";
		if(apiCtrl.fetchPackageInfo(waybill, clData) != null) {
			fm_loc  = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
		}
		apiCtrl.giApi(waybill, fm_loc, clData);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.giApi(child_waybill, fm_loc, clData);
		}
		
		Utilities.hardWait(10);
		
		//update weight -> on master shipment only
		clData.put("wt_rule", "Predicted_CTLG");
		UpdateWeight(Scenario, waybill, clData);
		
		
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
	
	
	//Mapper not restricted
		//Restricted Mapper Case
		@DataProvider(name = "No_mapper_cn", parallel = false)
		public Object[][] No_mapper_cn() {
			return new Object[][] { 
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", "IND110092AAB", "Delhi", "null", "","B2C","", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "null", "IND110092AAB", "Gurgaon", "null", "","Heavy","Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", "IND110092AAB", "Delhi", "null", "","B2C", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "","B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}
				};
		}
		
		@Test(dataProvider = "No_mapper_cn" , enabled = true)
		public void No_mapper_cn(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			Payload.put("client", "regression_client");
			Payload.put("pin","122003");
			Payload.put("add", "Baliawas");
			Payload.put("city", "Gurgaon");
			Payload.put("state", "Haryana");
			clData.put("client", "regression_client");
			
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

			if (Scenario.contains("B2B") || Scenario.contains("Heavy")) {
				Utilities.hardWait(30);
			}

			Utilities.hardWait(120);

			// Perform GI
			String fm_loc = apiCtrl.fetchPackageInfo(waybill, Payload).cs.sl;
			apiCtrl.giApi(waybill, fm_loc, clData);
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				apiCtrl.giApi(child_waybill, fm_loc, clData);
			}

			Utilities.hardWait(60);
			
			//update weight -> on master shipment only
			clData.put("wt_rule", "SORTER");
			UpdateWeight(Scenario, waybill, clData);
			
			
			Utilities.hardWait(30);
			
			
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
