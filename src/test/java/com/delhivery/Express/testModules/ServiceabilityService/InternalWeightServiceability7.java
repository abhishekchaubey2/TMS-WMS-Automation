package com.delhivery.Express.testModules.ServiceabilityService;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

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

public class InternalWeightServiceability7 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability7() {
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
	
	
	public void setDataForNoData(HashMap<String,String> payloadData) {
		payloadData.put("product_type", "B2B");
		payloadData.put("pin", "122001");
		payloadData.put("add", "Palika bazar");
		payloadData.put("city", "Gurgaon");
	}
	
	
	//RESTRICT_SRV_FLAG_ENABLED --> DONE
	@DataProvider(name = "RESTRICT_SRV_FLAG_ENABLED_IN_CLIENT_CONFIG", parallel = false)
	public Object[][] RESTRICT_SRV_FLAG_ENABLED_IN_CLIENT_CONFIG() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Gurgaon_Kadipur (Haryana)", 
				"IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", 
				null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", 
				"null", null, "Delhi", null, null, "", "Del_B_RPC (Delhi)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", 
				"249", "true", null, "Gurgaon", null, null, "Heavy", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", 
				"Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,
				"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, 
				"B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", 
				"249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", 
				"Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", 
				null, "Delhi", null, null, "", "Del_B_RPC (Delhi)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", 
				"true", null, "Gurgaon", null, null, "Heavy", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""}};
	}
	
	@Test(dataProvider = "RESTRICT_SRV_FLAG_ENABLED_IN_CLIENT_CONFIG" , enabled = true)
	public void VerifyServiceabilityRestriction(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		
		//Manifesting for client -> Restrict_Srv_Flag_Enabled_client
		Payload.put("client", "Restrict_Srv_Flag_Enabled_client");
		Payload.put("weight_verification", "true");
		//HQREGRESSION SRV12
		
		//Sending weight in payload
		if(Scenario.contains("weight less than 10 kg")) {
			Payload.put("weight", "9999.2");
		}else if(Scenario.contains("weight more than 10 kg")) {
			Payload.put("weight", "15000.12");
		}else {
			Payload.put("weight", "10000");
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
		
		Utilities.hardWait(70);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill,Payload);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill,Payload);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
	}
	
		
		//103-107
		@DataProvider(name = "EDT_VS_STAGING", parallel = false)
		public Object[][] edt_vs_staging() {
			return new Object[][] { 
				{ "Scenario:: B2C package weight more than 10 kg", "B2C"},
				{ "Scenario:: B2C MPS package weight more than 10 kg", "B2C MPS"},
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B"},
				{ "Scenario:: B2B MPS package with weight less than 10 kg", "B2B MPS"},
				{ "Scenario:: HEAVY package with weight less than 10 kg", "HEAVY"},
				{ "Scenario:: HEAVY MPS package with less more than 10 kg", "HEAVY MPS"}};
		}

		@Test(dataProvider = "EDT_VS_STAGING", enabled = false)
		public void EdtVsStaging(String Scenario, String type) {
			List<String> edt_waybills = new ArrayList<String>();
			List<String> staging_waybills = new ArrayList<String>();
			
			for (int i = 0; i < 2; i++) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				Payload.put("client", "regression_client");
				// creating shipment on edt
				if (i == 0) {
					Payload.put("enviorment", "edt");
				} else {
					Payload.put("enviorment", "staging");
				}
				waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);

				waybill = waybills.get(0);
				logInfo("Waybill generated " + waybill);

				// setting the default value of child_waybill as master waybill
				String child_waybill = waybill;
				if (waybills.size() > 1 && Scenario.contains("MPS")) {
					child_waybill = waybills.get(1);
					logInfo("Child Waybill generated " + child_waybill);
				}
				// asserting keys after manifestation
				if (type.contains("B2B") || type.contains("HEAVY")) {
					Utilities.hardWait(40);
				}

				// Performing FM of the package
				apiCtrl.fmOMSApiDiffEnv(Payload.get("enviorment"), waybill, "FMPICK",Payload);

				// Performing FM of child package if present
				if (!child_waybill.equalsIgnoreCase(waybill)) {
					apiCtrl.fmOMSApiDiffEnv(Payload.get("enviorment"), child_waybill, "FMPICK",Payload);
				}

				// Performing GI of master
				if(Scenario.contains("HEAVY") || Scenario.contains("B2B")) {
					Utilities.hardWait(40);
				}
				PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(Payload.get("enviorment"),waybill,Payload);
				apiCtrl.giApiDiffEnv(Payload.get("enviorment"), waybill, pkgdetails.cs.sl,Payload);
				// Performing GI of child if present
				if (!child_waybill.equalsIgnoreCase(waybill)) {
					apiCtrl.giApiDiffEnv(Payload.get("enviorment"), child_waybill, pkgdetails.cs.sl,Payload);
				}
				
				//updating weight
				UpdateWeightDiffEnv(Payload.get("enviorment"), Scenario, waybill, Payload);
				
				Utilities.hardWait(50);
				
				
				if (i == 0) {
					edt_waybills = waybills;
				} else {
					staging_waybills = waybills;
				}
			}
			
			Utilities.hardWait(60);
			HashMap<String,String> SPayload = new HashMap<>();
			//call assertion function here
			PackageDetail edtMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(0),SPayload);
			PackageDetail stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(0),SPayload);
			
			try {
				ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingMasterShipmentDetails, edtMasterShipmentDetails);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(Scenario.contains("MPS")) {
				
				PackageDetail edtChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(1),SPayload);
				PackageDetail stagingChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(1),SPayload);
				try {
					ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingChildShipmentDetails, edtChildShipmentDetails);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		//88-92 CUSTOM_SRV_IS_SET_ON_CLIENT_CONFIG , Not switching for heavy and b2b --> covered in custom template
//		@DataProvider(name = "CUSTOM_SRV_IS_SET_ON_CLIENT_CONFIG", parallel = false)
//		public Object[][] CUSTOM_SRV_IS_SET_ON_CLIENT_CONFIG() {
//			return new Object[][] { 
//				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-PIOM", "UD", "Shipment Recieved at Origin Center" },
//				{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "null", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "Heavy", "Del_Okhla_PC (Delhi)", "IND110044AAB", "DTUP-231", "UD", "Center changed by system","","",""},
//				{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "Heavy", "Del_Okhla_PC (Delhi)", "IND110044AAB", "DTUP-231", "UD", "Center changed by system" },
//				{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" , "Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
//				{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
//				{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS","Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "null", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "Heavy", "Del_Okhla_PC (DELHI)", "IND110044AAB", "DTUP-231", "UD", "Center changed by system","","",""}};
//		}
//		
//		@Test(dataProvider = "CUSTOM_SRV_IS_SET_ON_CLIENT_CONFIG" , enabled = false)
//		public void VerifyServiceabilityTriggerForCustomSRV(String Scenario, String type, String cn , String cnid,
//				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
//				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
//			List<String> waybills = new ArrayList<String>();
//			HashMap<String, String> Payload = new HashMap<>();
//			clData = new HashMap<>();
//			clData.put("client", "custom_srv_client");
//			
//				Payload.put("pin", "122003");
//				Payload.put("client", "custom_srv_client");
//			
//			waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
//			waybill = waybills.get(0);
//			logInfo("Waybill generated " + waybill);
//			
//			//Getting child waybill
//			String child_waybill = waybill;
//			if (waybills.size() > 1 && Scenario.contains("MPS")) {
//				child_waybill = waybills.get(1);
//				logInfo("Child Waybill generated " + child_waybill);
//			}
//			
//			if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
//				Utilities.hardWait(50);
//			}
//			
//			
//			//Perform FM
//			apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
//			//Performing FM of child package if present
//			if (!child_waybill.equalsIgnoreCase(waybill)) {
//				apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
//			}
//			
//			if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
//				Utilities.hardWait(30);
//			}
//			
//			
//			//Perform GI
//			String fm_loc = "East_Delhi (DELHI)";
//			if(apiCtrl.fetchPackageInfo(waybill, clData) != null) {
//				fm_loc  = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
//			}
//			apiCtrl.giApi(waybill, fm_loc, clData);
//			if (!child_waybill.equalsIgnoreCase(waybill)) {
//				apiCtrl.giApi(child_waybill, fm_loc, clData);
//			}
//			
//			Utilities.hardWait(10);
//			
//			//update weight -> on master shipment only
//			clData.put("wt_rule", "Predicted_CTLG");
//			UpdateWeight(Scenario, waybill, clData);
//			
//			
//			Utilities.hardWait(60);
//			
//			//Asserting values on master package
//			FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
//			PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
//			ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
//			
//			//Asserting values on child waybill if present
//			if(!child_waybill.equalsIgnoreCase(waybill)) {
//				PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
//				ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
//			}
//		}
		
		
		
		//Different Payment Mode
		@DataProvider(name = "Diff_payment_mode", parallel = true)
		public Object[][] Diff_payment_mode() {
			return new Object[][] { 
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"COD","Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Pre-paid","Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Pickup","Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled","","",""},
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Cash","Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-UCI", "PP", "Manifest uploaded","","",""},
				{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"REPL","Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""}
				};
		}
		
		@Test(dataProvider = "Diff_payment_mode" , enabled = false)
		public void Diff_payment_mode(String Scenario, String type,String payment_mode,String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			clData.put("client", "regression_client");
			Payload.put("payment_mode", payment_mode);
			
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
			
			
			// Perform FM
			apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
			// Performing FM of child package if present
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
			}
			
			if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
				Utilities.hardWait(60);
			}
			// Perform GI
			if(!payment_mode.equalsIgnoreCase("Pickup") && !payment_mode.equalsIgnoreCase("Cash")) {
				String fm_loc = apiCtrl.fetchPackageInfo(waybill, clData).cs.sl;
				if(fm_loc.equalsIgnoreCase("NSZ")) {
					fm_loc = "HQ (Haryana)";
				}
				apiCtrl.giApi(waybill, fm_loc, clData);
				if (!child_waybill.equalsIgnoreCase(waybill)) {
					apiCtrl.giApi(child_waybill, fm_loc, clData);
				}
			}

			Utilities.hardWait(10);
			
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
