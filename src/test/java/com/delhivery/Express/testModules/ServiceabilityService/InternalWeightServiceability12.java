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
public class InternalWeightServiceability12 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability12() {
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
			
	//different state shipments and aseg updates the cn = NSZ
			//Diff state -> DONE
			@DataProvider(name = "aseg_updates_nsz_cn_diff_state_shipments", parallel = false)
			public Object[][] aseg_updates_nsz_cn_diff_state_shipments() {
				return new Object[][] {
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "NSZ", "IND110086AAA", "null", "275", "true", "IND110086AAA", "null", null, "", "B2B", "NSZ", null, "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-DDD3FD", "UD", "Out for delivery"},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-38", "DL", "Delivered to consignee"},
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Delhi_Rohini_RP (Delhi)", "IND110086AAA", "", "275", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Rohini_RP (Delhi)", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging" },
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST" },
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "NSZ", "null", "null", "275", "true", "null", "null", null, null, "B2B", "NSZ", null, "X-NSZ", "PP", "Non-serviceable location","","",""},
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Delhi_Rohini_RP (Delhi)", "IND110086AAA", "", "275", "true", "null", "Delhi", null, null, "B2B", "Delhi_Rohini_RP (Delhi)", null, "DTUP-203", "PU", "Package details changed by shipper","","",""},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "RT-111", "DL", "DTO due to poor packaging"},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-108", "CN", "QC failed at pickup. Product count mismatch"},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Pending", "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "null", "IND110096AAA", "Delhi", "null", "", "", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Dispatched", "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "null", "IND110037AAB", "Delhi", "null", "", "", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper"},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Delivered", "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "null", "IND110037AAB", "Delhi", "null", "", "", "Delhi_East_RPC (Delhi)", "null", "EOD-38", "DL", "Delivered to consignee"},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Returned" , "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "null", "IND110092AAB", "Delhi", "null", "", "", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C","RTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-110", "DL", "RTO due to poor packaging"},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" , "LOST", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "LT-100", "LT", "Shipment LOST"},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"PickupPending", "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "null", "null", "Delhi", "null", "null", "", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "PP", "Package details changed by shipper","","",""},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "PickedUp", "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-203","PU", "Package details changed by shipper","","",""},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "DTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "RT-111", "DL", "DTO due to poor packaging"},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Cancelled" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch"}
				};
			}
			
			@Test(dataProvider = "aseg_updates_nsz_cn_diff_state_shipments", enabled = true)
			public void verifyCnaddUpdatedtoNSZForDiffStateShipment(String Scenario, String type,String state,
					String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				clData = new HashMap<String,String>();
				clData.put("client", "regression_client");
				clData.put("product_type", type);
				clData.put("add", "Ghondli");
				clData.put("city", "Delhi");
				clData.put("state", "Delhi");
				clData.put("pin", "110031");
				waybill = diffStShpt.DifferentStateShipments(state,clData);
				logInfo("Waybill " + waybill);
				Utilities.hardWait(20);
				
				//Updating weight
				clData.put("wt_rule", "Predicted_CTLG");
				if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
					clData.put("SecondPojo", "true");
				}
				
				Utilities.hardWait(10);
				
				//updating weight
				if(Scenario.contains("B2B")) {
					apiCtrl.QcWtApi(waybill, 9999.12, "Predicted_CTLG", clData);
				}else {
					apiCtrl.QcWtApi(waybill, 15999.12, "Predicted_CTLG", clData);
				}
				
				Utilities.hardWait(30);
				
				//Editing add
				clData.put("add", "Theka road");
				apiCtrl.EditApi(waybill, clData);
				
				Utilities.hardWait(60);
				//Asserting values on master package
				FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
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
//					{ "Scenario:: B2B package with no data exist with weight less than 10 kg", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2B package with invalid cn with weight less than 10 kg", "B2B" ,"NSZ", "null", "null", "null", "true", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "X-PIOM", "RT", "Shipment Recieved at Origin Center","","",""},
//					{ "Scenario:: B2B package with NSZ cn with weight less than 10 kg", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
					{ "Scenario:: B2C package with no data exist with weight more than 10 kg", "B2C" ,"NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "X-NSZ", "RT", "Non-serviceable location","","",""},
					{ "Scenario:: B2C package with invalid cn with weight more than 10 kg", "B2C" ,"test50_facility (Haryana)", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "test50_facility (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
					{ "Scenario:: B2C package with NSZ cn with weight more than 10 kg", "B2C" ,"NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "X-NSZ", "RT", "Non-serviceable location","","",""},
					};
			}
			
			@Test(dataProvider = "Wrong_cn_cases" , enabled = true)
			public void VerifyServiceabilityTriggerWrongCN(String Scenario, String type, String cn , String cnid,
					String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				clData = new HashMap<>();
				clData.put("client", "regression_client");
				Payload.put("client", "regression_client");
				
				if(Scenario.contains("NSZ cn")) {
					Payload.put("pin", "110033");
				}else if(Scenario.contains("no data exist")) {
					Payload.put("pin", "312456");
				}else {
					//invalid cn
					Payload.put("pin", "133101");
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
				
				if(Scenario.contains("B2B")|| Scenario.contains("HEAVY")) {
					Utilities.hardWait(50);
				}
				
				
				//Perform FM
				apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
				//Performing FM of child package if present
				if (!child_waybill.equalsIgnoreCase(waybill)) {
					apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
				}
				
				if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
					Utilities.hardWait(60);
				}
				Utilities.hardWait(60);
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

	
			//83-87 addfix callback after pin update
			@DataProvider(name = "Pin_updated_on_a_package", parallel = false)
			public Object[][] Pin_updated_on_a_package() {
				return new Object[][] { 
					{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", "null", "", "B2C", "Gurgaon (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
					{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
					{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", "null", "null", "B2C", "Gurgaon (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
					{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", "null", "", "B2C", "Gurgaon (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
					{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", "null", "null", "B2C", "Gurgaon (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
					{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS WITH MCOUNT 1","GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""}
					};
			}
			
			@Test(dataProvider = "Pin_updated_on_a_package" , enabled = true)
			public void VerifyServiceabilityTriggerAfterAddfixCallbackDueToPinChange(String Scenario, String type, String cn , String cnid,
					String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				clData = new HashMap<>();
				clData.put("client", "regression_client");
				clData.put("city", "");
				clData.put("state", "");
				clData.put("add", "Test");
				clData.put("pin", "122003");
				
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
				
				Utilities.hardWait(10);
				
				//update weight -> on master shipment only
				clData.put("wt_rule", "Predicted_CTLG");
				UpdateWeight(Scenario, waybill, clData);
				
				
				Utilities.hardWait(60);
				
				//Updating add
				clData.put("pincode", "122001");
				apiCtrl.EditApi(waybill,clData);
				
				Utilities.hardWait(90);
				
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
	
	//aseg update after weigh trigger in different state shipment
			@DataProvider(name = "aseg_update_after_weight_trigger", parallel = false)
			public Object[][] aseg_update_after_weight_trigger() {
				return new Object[][] {
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAD", "Gurgaon", null, "", "B2B", "GGN_DPC (Haryana)", null, "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-DDD3FD", "UD", "Out for delivery"},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-38", "DL", "Delivered to consignee"},
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging" },
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST" },
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", null, null, "B2B", "GGN_DPC (Haryana)", null, "DTUP-219", "PP", "Pincode updated by Addfix","","",""},
						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp",  "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "DTUP-203", "PU", "Package details changed by shipper","","",""},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "RT-111", "DL", "DTO due to poor packaging"},
//						{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-108", "CN", "QC failed at pickup. Product count mismatch"},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Pending", "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110037AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Dispatched", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110037AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-DDD3FD", "UD", "Out for delivery"},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Delivered", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "EOD-38", "DL", "Delivered to consignee"},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Returned" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C","RTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-110", "DL", "RTO due to poor packaging"},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" , "LOST", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "LT-100", "LT", "Shipment LOST"},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"PickupPending", "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "null", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "DTUP-219", "PP", "Pincode updated by Addfix","","",""},
						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "PickedUp", "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Gurgaon (Haryana)", "null", "DTUP-203", "PU", "Package details changed by shipper","","",""},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "DTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "RT-111", "DL", "DTO due to poor packaging"},
//						{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Cancelled" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch"}
				};
			}
			
			@Test(dataProvider = "aseg_update_after_weight_trigger", enabled = true)
			public void verifyCnaddUpdateForDiffStateShipment(String Scenario, String type,String state,
					String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
					String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
				clData = new HashMap<String,String>();
				clData.put("client", "regression_client");
				clData.put("product_type", type);
				clData.put("add", "Baliawas");
				clData.put("city", "Delhi");
				clData.put("state", "Delhi");
				clData.put("pin", "122003");
				waybill = diffStShpt.DifferentStateShipments(state,clData);
				logInfo("Waybill " + waybill);
				Utilities.hardWait(20);
				
				//Updating weight
				clData.put("wt_rule", "Predicted_CTLG");
				if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
					clData.put("SecondPojo", "true");
				}
				
				Utilities.hardWait(10);
				
				//updating weight
				if(Scenario.contains("B2B")) {
					apiCtrl.QcWtApi(waybill, 14999.12, "Predicted_CTLG", clData);
				}else {
					apiCtrl.QcWtApi(waybill, 15999.12, "Predicted_CTLG", clData);
				}
				
				Utilities.hardWait(30);
				
				//Editing add
				clData.put("add", "Palika Bazar");
				apiCtrl.EditApi(waybill, clData);
				
				Utilities.hardWait(60);
				//Asserting values on master package
				FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,pin,rpin);
				if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
					PackageDetail2 pkgDetails = apiCtrl.fetchPackageInfo2(waybill, clData);
					ServiceabilityKeysAssertions.assertServiceabilityKeys2(pkgDetails, Expected_values);
				}else {
					PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
					ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
				}

			}
}
	

