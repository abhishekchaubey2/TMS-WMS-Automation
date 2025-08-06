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
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class DomesticTemplateForwardServiceability2 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public DomesticTemplateForwardServiceability2() {
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
			apiCtrl.QcWtApiDiffEnv(Data.get("enviorment"), waybill, 10000.12, "Predicted_CTLG",Data);
			
			PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(Data.get("enviorment"),waybill,Data);
			double iwt = pkgdetails.intWt.wt;
			assertKeyValue("int_wt", 10000.12, iwt);
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
	
	@DataProvider(name = "Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: NO DATA", "NO DATA" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110037AAI", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "null", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested","","",""}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceability(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("pin","122001"); 
		
		if(Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested")) {
			Payload.put("client", "custom_srv_client_2");
			clData.put("client", "custom_srv_client_2");
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
		
		Utilities.hardWait(150);
		
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
	
	@DataProvider(name = "diff_state_shipments", parallel = false)
	public Object[][] diff_state_shipments() {
		return new Object[][] {
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110044AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110044AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-DDD3FD", "UD", "Out for delivery","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110044AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "EOD-38", "DL", "Delivered to consignee","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "RT-101", "RT", "Returned as per Client Instructions","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "RT-110", "DL", "RTO due to poor packaging","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "IND110092AAB", "Delhi", "Del_Okhla_PC (DELHI)", "", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "LT-100", "LT", "Shipment LOST","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-ASP", "PP", "Pickup scheduled","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "EOD-77", "PU", "Pickup completed","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "RT-111", "DL", "DTO due to poor packaging","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "EOD-108", "CN", "QC failed at pickup. Product count mismatch","","",""}
		};
	}
	
	@Test(dataProvider = "diff_state_shipments", enabled = true)
	public void VerifyCustomServiceabilityForDiffStateShipment(String Scenario, String type,String state,
			String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		clData = new HashMap<String,String>();
		clData.put("client", "custom_srv_client");
		clData.put("product_type", type);
		waybill = diffStShpt.DifferentStateShipments(state,clData);
		logInfo("Waybill " + waybill);
		
		
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
