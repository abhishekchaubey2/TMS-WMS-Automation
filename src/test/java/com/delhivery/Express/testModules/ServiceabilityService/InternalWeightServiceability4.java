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
public class InternalWeightServiceability4 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability4() {
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
	
	//83-87 addfix callback after srv -> DONE , Issue in 1st amd 4th
	@DataProvider(name = "Address_updated_on_a_package", parallel = false)
	public Object[][] Address_updated_on_a_package() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "Gurgaon (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "null", "B2B", "Gurgaon (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS WITH MCOUNT 1","GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "UD", "Center changed by system","","",""}
			};
	}
	
	@Test(dataProvider = "Address_updated_on_a_package" , enabled = true)
	public void VerifyServiceabilityTriggerAfterAddfixCallback(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "regression_client");
		clData.put("city", "Gurgaon");
		clData.put("state", "Haryana");
		
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
		clData.put("add", "Godoli");
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
	
	//93-97 CN IS NSZ FOR THE NEW CPDT
	@DataProvider(name = "CN_IS_SET_AS_NSZ_FOR_NEW_CPDT", parallel = false)
	public Object[][] CN_IS_SET_AS_NSZ_FOR_NEW_CPDT() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Govindpura (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "GGN_SC (Haryana)", "INHRABDK", "N", "IND345678qqq", "null", "IND110092AAB", "Gurgaon", "null", "", "", "GGN_SC (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "null", "Heavy", "Delhi_Govindpura (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" , "Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Govindpura (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Delhi_Govindpura (Delhi)", "IND110051AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Govindpura (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS WITH MCOUNT 1","GGN_SC (Haryana)", "INHRABDK", "N", "IND345678qqq", "null", "IND110092AAB", "Gurgaon", "null", "", "", "GGN_SC (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}};
	}
	
	@Test(dataProvider = "CN_IS_SET_AS_NSZ_FOR_NEW_CPDT" , enabled = true)
	public void VerifyNoServiceabilityTriggerAsCNIsSetAsNSZ(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "regression_client");
		
		if(Scenario.contains("B2C")) {
			Payload.put("pin", "122012"); //This pin has cn only for B2C
		}else {
			Payload.put("pin", "110032"); //This pin has cn only for B2B
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
	
	@DataProvider(name = "NEW_PIN_HAS_NO_DATA", parallel = false)
	public Object[][] NEW_PIN_HAS_NO_DATA() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Delhi_Rohini_RP (Delhi)", "IND110086AAA", "", "275", "null", "IND110092AAB", "Delhi", "null", "", "Heavy", "Delhi_Rohini_RP (DELHI)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" , "Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Delhi_East_RPC (Delhi)", "IND110096AAA", "", "1053", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Delhi_East_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS WITH MCOUNT 1", "Delhi_Rohini_RP (Delhi)", "IND110086AAA", "", "275", "null", "IND110092AAB", "Delhi", "null", "", "Heavy", "Delhi_Rohini_RP (DELHI)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "NEW_PIN_HAS_NO_DATA" , enabled = true)
	public void VerifyServiceabilityTriggerAddUpdatedToNSZCN(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String expected_pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "regression_client");
		Payload.put("state", "Delhi");
		Payload.put("city", "Delhi");
		Payload.put("add", "Ghondli");
		Payload.put("pin","110031");
		
		
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
		
		//waiting so that srv is triggered
		Utilities.hardWait(60);
		
		//edit add such that the new pin has no data for the current cpdt
		Payload.put("add", "Lusa Tower Commercial Complex Azadpur Delhi North West DL IN");
		Payload.put("add", "Theka Road,110033");
		apiCtrl.EditApi(child_waybill, Payload);
		
		//waiting to make sure pin and cn does not changes
		Utilities.hardWait(60);
		
		//asserting pin
		Long cpin = apiCtrl.fetchPackageInfo(waybill, clData).cpin;
		Long pin = apiCtrl.fetchPackageInfo(waybill, clData).pin;
		if(!type.equalsIgnoreCase("B2C")) {
			assertKeyValue("pin",cpin,pin);
		}
		
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,cs_ss,expected_pin,rpin);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
	}
}
