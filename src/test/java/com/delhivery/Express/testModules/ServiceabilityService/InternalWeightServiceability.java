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

public class InternalWeightServiceability extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public InternalWeightServiceability() {
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
			assertKeyValue("int_wt", 10000.12, iwt);
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
	
	@DataProvider(name = "Weight_sent_on_packages_while_Manifestation", parallel = false)
	public Object[][] Weight_sent_on_packages_while_Manifestation() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", null, null, "B2C", "Del_B_RPC (Delhi)", null, "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B package with weight more than 10 kg", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "null", null, "Gurgaon", null, null, "Heavy", "Gurgaon_Kadipur (Haryana)", null, "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C package with weight less than 10 kg", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", null, null, "B2C", "Del_B_RPC (Delhi)", null, "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: Heavy package with weight more than 10 kg", "HEAVY" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "Heavy", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", null, null, "B2C", "Del_B_RPC (Delhi)", null, "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2B MPS with Master Package only with weight less than 10 kg ", "MPS WITH MCOUNT 1" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "true", null, "Delhi", null, null, "B2C", "Del_B_RPC (Delhi)", null, "DTUP-231", "UD", "Center changed by system","","",""},
			{ "Scenario:: B2C MPS with Master Package only with weight more than 10 kg ", "B2C MPS WITH MCOUNT 1" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", null, "Delhi", null, null, "", "Del_B_RPC (Delhi)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "Heavy", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: NO DATA with weight less than 10 kg", "NO DATA" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAB", "Gurgaon", null, "IND122001AAB", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""}};
	}
	
	@Test(dataProvider = "Weight_sent_on_packages_while_Manifestation" , enabled = true)
	public void VerifyServiceabilityTriggerAfterManifestation(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
//		Payload.put("client", "regression_client");
		clData.put("client", "regression_client");
		clData.put("weight_verification", "true");
		Payload.put("weight_verification", "true");
		//Sending weight in payload
		if(Scenario.contains("weight less than 10 kg")) {
			Payload.put("weight", "9999.2");
		}else if(Scenario.contains("weight more than 10 kg")) {
			Payload.put("weight", "10000.12");
		}else {
			Payload.put("weight", "10000");
		}
		
		if(Scenario.contains("NO DATA")){
			Payload.put("product_type", "B2B");
			Payload.put("pin", "122003");
			Payload.put("add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
			Payload.put("city", "Gurgaon");
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
		
		Utilities.hardWait(140);
		
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
	
	//Weight_verification_false --> DONE (100%)
	@DataProvider(name = "Weight_Verification_key_is_disabled_in_client_config", parallel = false)
	public Object[][] Weight_Verification_key_is_disabled_in_client_config() {
		return new Object[][] { 
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null,null,"B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "HEAVY" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null,"Heavy", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with internal child with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD" ,	"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", 	null, null,"B2B", "Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with weight less than 10 kg ", "B2B MPS" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null,"B2B", 	"Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS with weight more than 10 kg ", "B2C MPS" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy MPS with weight less than 10 kg ", "HEAVY MPS" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null,"Heavy", 	"Gurgaon_Kadipur (Haryana)", null, "X-UCI", "UD", "Manifest uploaded","","",""}};
	}
	
	@Test(dataProvider = "Weight_Verification_key_is_disabled_in_client_config" , enabled = true)
	public void VerifyNoServiceabilityTriggerAfterManifestation(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		
		//Manifesting for client -> Weight_verification_false_client
		Payload.put("client", "Weight_verification_false_client");
		
		//Sending weight in payload
		if(Scenario.contains("weight less than 10 kg")) {
			Payload.put("weight", "9999.2");
		}else if(Scenario.contains("weight more than 10 kg")) {
			Payload.put("weight", "10000.12");
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
	
}
