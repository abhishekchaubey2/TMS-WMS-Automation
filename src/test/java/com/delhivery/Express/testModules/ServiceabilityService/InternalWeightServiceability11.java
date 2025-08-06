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
public class InternalWeightServiceability11 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability11() {
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
	

			
	//Switch to Secondary add cases
			@DataProvider(name = "Switch_To_Secondary_Address", parallel = false)
			public Object[][] Switch_To_Secondary_Address() {
				return new Object[][] { 
					{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
					{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "","Heavy", "GGN_DPC (Haryana)","null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
					{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
					{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
					{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
					{ "Scenario:: B2C MPS with weight more than 10 kg ","B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""},
					{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-PDASS", "UD", "Deliver to secondary address","","",""}
					};
			}
			
			//108-114 Triggering_SRV_Twice
			@Test(dataProvider = "Switch_To_Secondary_Address" , enabled = true)
			public void Switch_To_Secondary_Address(String Scenario, String type, String cn , String cnid,
					String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				clData = new HashMap<>();
				clData.put("client", "regression_client");
				Payload.put("add", "Baliawas");
				Payload.put("secondary_add", "Godoli");
				Payload.put("secondary_city", "Gurgaon");
				Payload.put("secondary_state", "Haryana");
				Payload.put("secondary_pin", "122001");
				Payload.put("secondary_name", "Test");
				
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
					Utilities.hardWait(10);
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
				
				Utilities.hardWait(10);
				
				//update weight -> on master shipment only
				clData.put("wt_rule", "Predicted_CTLG");
				UpdateWeight(Scenario, waybill, clData);
				
				
				Utilities.hardWait(40);
				
				
				//Perform GI on cn
				String gi_cn  = apiCtrl.fetchPackageInfo(waybill, clData).cn;
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
				
				Utilities.hardWait(50);
				
				
				
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
			
		//Switch to Secondary address and address updated after addfix result
			//Switch to Secondary add cases 
			@DataProvider(name = "Switch_To_Secondary_Address_and_Update_Address", parallel = false)
			public Object[][] Switch_To_Secondary_Address_and_Update_Address() {
				return new Object[][] { 
					{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
					{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "null", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
					{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
					{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
					{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""},
					{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "null", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-232", "UD", "Pincode updated by addfix for secondary address","","",""}
					};
			}
			
			//108-114 Triggering_SRV_Twice
			@Test(dataProvider = "Switch_To_Secondary_Address_and_Update_Address" , enabled = true)
			public void Switch_To_Secondary_Address_and_Update_Address(String Scenario, String type, String cn , String cnid,
					String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				clData = new HashMap<>();
				clData.put("client", "regression_client");
				Payload.put("client", "regression_client");
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
					Utilities.hardWait(10);
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
				
				Utilities.hardWait(40);
				
				//update weight -> on master shipment only
				clData.put("wt_rule", "Predicted_CTLG");
				UpdateWeight(Scenario, waybill, clData);
				
				
				Utilities.hardWait(40);
				
				
				//Perform GI on cn
				String gi_cn  = apiCtrl.fetchPackageInfo(waybill, clData).cn;
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
				
				
//				//Update add
//				clData.put("add", "Baliawas");
//				apiCtrl.EditApi(waybill,clData);
				
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
			@DataProvider(name = "Switch_To_Secondary_Address_And_Mapper_Updates_cn", parallel = false)
			public Object[][] Switch_To_Secondary_Address_And_Mapper_Updates_cn() {
				return new Object[][] { 
					{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "","Heavy", "GGN_DPC (Haryana)","null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS","Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Gurgaon (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" , "Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""}
					};
			}
			
			//108-114 Triggering_SRV_Twice
			@Test(dataProvider = "Switch_To_Secondary_Address_And_Mapper_Updates_cn" , enabled = true)
			public void Switch_To_Secondary_Address_And_Mapper_Updates_cn(String Scenario, String type, String cn , String cnid,
					String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				clData = new HashMap<>();
				clData.put("client", "regression_client");
				Payload.put("add", "Baliawas");
				Payload.put("secondary_add", "J649 26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001");
				Payload.put("secondary_city", "Delhi");
				Payload.put("secondary_state", "Delhi");
				Payload.put("secondary_pin", "110011");
				Payload.put("secondary_name", "Test");
				
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
					Utilities.hardWait(10);
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
				
				Utilities.hardWait(10);
				
				//update weight -> on master shipment only
				clData.put("wt_rule", "Predicted_CTLG");
				UpdateWeight(Scenario, waybill, clData);
				
				
				Utilities.hardWait(40);
				
				
				//Perform GI on cn
				String gi_cn  = apiCtrl.fetchPackageInfo(waybill, clData).cn;
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
}
	

