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
public class InternalWeightServiceability5 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability5() {
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
	

	//Restricted Mapper Case
	@DataProvider(name = "Restricted_Mapper_Case", parallel = false)
	public Object[][] Restricted_Mapper_Case() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"East Delhi (Delhi)", "IND110092AAB", "", "KCF/MDL", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_Okhla_PC (DELHI)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "null", "IND110092AAB", "Delhi", "null", "", "Heavy", "Delhi_Paschim_DC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" , "East Delhi (Delhi)", "IND110092AAB", "", "KCF/MDL", "true", "IND110092AAB", "Delhi", "null", "null", "B2C", "Del_Okhla_PC (DELHI)", "null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}
			};
	}
	
	@Test(dataProvider = "Restricted_Mapper_Case" , enabled = true)
	public void Restricted_Mapper_Case(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		Payload.put("client", "restrict_mapper_callback_client");
		Payload.put("pin","110011");
		Payload.put("add", "J649 26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001");
		Payload.put("city", "Delhi");
		Payload.put("state", "Delhi");
		clData.put("client", "restrict_mapper_callback_client");
		
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

		Utilities.hardWait(40);

		// Perform GI
		String fm_loc = apiCtrl.fetchPackageInfo(waybill, Payload).cs.sl;
		if(type.equalsIgnoreCase("B2C")) {
			fm_loc = "HQ (Haryana)"; // Otherwise B2C shipment gets into pending state
		}
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
	
	//Mapper not restricted
	//Restricted Mapper Case
	//Heavy mapper cn - > Delhi_shahpurJat
	//Light Mapper cn -> Delhi_Bhogal
	@DataProvider(name = "Non_Restricted_Mapper_Case", parallel = false)
	public Object[][] Non_Restricted_Mapper_Case() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Delhi_Bhogal (Delhi)", "IND110014AAA", "", "KCF/MDL", "true", "IND110092AAB", "Delhi", "null", "","B2C", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "","Heavy", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" , "Delhi_Bhogal (Delhi)", "IND110014AAA", "", "KCF/MDL", "true", "IND110092AAB", "Delhi", "null", "","B2C", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Delhi_shahpurJat (Delhi)", "IND110049AAA", "", "null", "true", "IND110092AAB", "Delhi", "null", "","B2B", "","null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}
			};
	}
	
	@Test(dataProvider = "Non_Restricted_Mapper_Case" , enabled = true)
	public void Non_Restricted_Mapper_Case(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		Payload.put("client", "regression_client");
		Payload.put("pin","110011");
		Payload.put("add", "J649 26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001");
		Payload.put("city", "Delhi");
		Payload.put("state", "Delhi");
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
	
	//Mapper gives same cn
	@DataProvider(name = "Mapper_gives_Same_cn", parallel = false)
	public Object[][] Mapper_gives_Same_cn() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Patna_Gaighat (Bihar)", "IND800007AAA", "E", "338", "true", "IND110092AAB", "Patna", "null", "","B2C", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "null", "IND110092AAB", "Gurgaon", "null", "","Heavy", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" , "Patna_Gaighat (Bihar)", "IND800007AAA", "E", "338", "true", "IND110092AAB", "Patna", "null", "","B2C", "","null", "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "","B2B", "","null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}
			};
	}
	
	@Test(dataProvider = "Mapper_gives_Same_cn" , enabled = true)
	public void Mapper_gives_Same_cn(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		Payload.put("client", "regression_client");
		Payload.put("pin","800005");
		Payload.put("add", "Gulbi ghat");
		Payload.put("city", "Patna");
		Payload.put("state", "Bihar");
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
