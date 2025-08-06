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
import com.fasterxml.jackson.core.JsonProcessingException;

public class DomesticTemplateForwardServiceability3 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public DomesticTemplateForwardServiceability3() {
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
	
	
	@DataProvider(name = "EDT_VS_STAGING", parallel = false)
	public Object[][] edt_vs_staging() {
		return new Object[][] { 
			{ "Scenario:: B2C package weight less than 10 kg", "B2C"},
			{ "Scenario:: B2C MPS package weight less than 10 kg", "B2C MPS"},
			{ "Scenario:: B2B package with weight more than 10 kg", "B2B"},
			{ "Scenario:: B2B MPS package with weight more than 10 kg", "B2B MPS"},
			{ "Scenario:: HEAVY package with weight more than 10 kg", "HEAVY"},
			{ "Scenario:: HEAVY MPS package with weight more than 10 kg", "HEAVY MPS"}
			};
	}

	@Test(dataProvider = "EDT_VS_STAGING", enabled = true)
	public void EdtVsStaging(String Scenario, String type) {
		List<String> edt_waybills = new ArrayList<String>();
		List<String> staging_waybills = new ArrayList<String>();
		
		for (int i = 0; i < 2; i++) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			
			// creating shipment on edt
			if (i == 0) {
				Payload.put("enviorment", "edt");
			} else {
				Payload.put("enviorment", "staging");
			}
			
			Payload.put("client", "custom_srv_client");
			Payload.put("pin", "122001");
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
			
			Utilities.hardWait(30);
			
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
			
			Utilities.hardWait(10);
			
			
			if (i == 0) {
				edt_waybills = waybills;
			} else {
				staging_waybills = waybills;
			}
		}
		
		Utilities.hardWait(60);
		
		//call assertion function here
		HashMap<String,String> Payload1 = new HashMap<>();
		Payload1.put("client", "custom_srv_client");
		PackageDetail edtMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(0),Payload1);
		PackageDetail stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(0),Payload1);
		
		try {
			ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingMasterShipmentDetails, edtMasterShipmentDetails);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(Scenario.contains("MPS")) {
			
			PackageDetail edtChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(1),Payload1);
			PackageDetail stagingChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(1),Payload1);
			try {
				ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingChildShipmentDetails, edtChildShipmentDetails);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Mapper restricted client
	//disabled as this template requires custom template to be set on client config
	@DataProvider(name = "Mapper_Restricted_client", parallel = false)
	public Object[][] Mapper_Restricted_client() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "null", "Gurgaon", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon_Bilaspur_HB (Haryana)", "IND000000ACB", "N", "hdghdj", "null", "null", "Gurgaon", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "null", "Gurgaon", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "null", "Gurgaon", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			};
	}
	
	@Test(dataProvider = "Mapper_Restricted_client" , enabled = false)
	public void Mapper_Restricted_client(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "restrict_mapper_callback_client");
		Payload.put("client", "restrict_mapper_callback_client");
		Payload.put("add", "J649 26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001");
		Payload.put("city", "Delhi");
		Payload.put("state", "Delhi");
		Payload.put("pin","110011"); 
		
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
	
	@DataProvider(name = "Mapper_NonRestricted_client", parallel = false)
	public Object[][] Mapper_NonRestricted_client() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon_Bilaspur_HB (Haryana)", "IND000000ACB", "N", "hdghdj", "null", "null", "Gurgaon", "Del_Okhla_PC (DELHI)", "null", "", "Gurgaon_Bilaspur_HB (Haryana)", " IND110044AAB", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "null", "Gurgaon", "null", "null", "Heavy", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			};
	}
	
	@Test(dataProvider = "Mapper_NonRestricted_client" , enabled = false)
	public void Mapper_NonRestricted_client(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("add", "J649 26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001");
		Payload.put("city", "Delhi");
		Payload.put("state", "Delhi");
		Payload.put("pin","110011"); 
		
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
	
	//Different payment mode
	@DataProvider(name = "Different_payment_mode", parallel = false)
	public Object[][] Different_payment_mode() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B" ,"COD", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"Pre-paid", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"Pickup", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND400093AAA", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-ASP", "PP", "Pickup scheduled","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"Cash", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "PP", "Manifest uploaded","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"REPL", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			};
	}
	
	@Test(dataProvider = "Different_payment_mode" , enabled = true)
	public void Different_payment_mode(String Scenario, String type, String payment_mode,String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("pin","122001");
		Payload.put("payment_mode", payment_mode);
		Payload.put("return_pin", "122003");
		Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		Payload.put("return_city", "Gurgaon");
		Payload.put("return_state", "Haryana");
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
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
	
	//add updated to test
	@DataProvider(name = "Add_Updated_to_test", parallel = false)
	public Object[][] Add_Updated_to_test() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "Add_Updated_to_test" , enabled = true)
	public void Add_Updated_to_test(String Scenario, String type, String cn , String cnid,
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
		Payload.put("add", "test");
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
	
	//different state shipments and aseg updates the cn = NSZ
	//Diff state -> DONE
	@DataProvider(name = "aseg_updates_nsz_cn_diff_state_shipments", parallel = false)
	public Object[][] aseg_updates_nsz_cn_diff_state_shipments() {
		return new Object[][] {
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "NSZ", "IND400093AAA", "W", "1028", "true", "IND400093AAA", "null", null, "IND400612AAA", "B2B", "NSZ", null, "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-DDD3FD", "UD", "Out for delivery"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-38", "DL", "Delivered to consignee"},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging" },
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST" },
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "NSZ", "null", "W", "1028", "true", "null", "null", null, null, "B2B", "NSZ", null, "X-NSZ", "PP", "Non-serviceable location","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", null, null, "B2B", "Mumbai MIDC (Maharashtra)", null, "DTUP-203", "PU", "Package details changed by shipper","","",""},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "RT-111", "DL", "DTO due to poor packaging"},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-108", "CN", "QC failed at pickup. Product count mismatch"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Pending", "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND841405AAA", "Barauli", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Dispatched", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-DDD3FD", "UD", "Out for delivery"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Delivered", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "EOD-38", "DL", "Delivered to consignee"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Returned" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C","RTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-110", "DL", "RTO due to poor packaging"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" , "LOST", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "LT-100", "LT", "Shipment LOST"},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"PickupPending", "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "PP", "Package details changed by shipper","","",""},
				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "PickedUp", "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "PU", "Package details changed by shipper","","",""},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "DTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "RT-111", "DL", "DTO due to poor packaging"},
//				{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Cancelled" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch"}
		};
	}
	
	@Test(dataProvider = "aseg_updates_nsz_cn_diff_state_shipments", enabled = true)
	public void aseg_updayes_nsz_cn_diff_state_shipments(String Scenario, String type,String state,
			String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		clData = new HashMap<String,String>();
		clData.put("client", "custom_srv_client");
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
	@DataProvider(name = "Wrong_cn_cases", parallel = false)
	public Object[][] Wrong_cn_cases() {
		return new Object[][] { 
//			{ "Scenario:: B2B package with no data exist", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system"},
			{ "Scenario:: B2B package with invalid cn", "B2B" ,"NSZ", "null", "null", "null", "true", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "X-PIOM", "RT", "Shipment Recieved at Origin Center","","",""},
//			{ "Scenario:: B2B package with NSZ cn", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system"},
			{ "Scenario:: B2C package with no data exist", "B2C" ,"NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "X-NSZ", "RT", "Non-serviceable location","","",""},
			{ "Scenario:: B2C package with invalid cn", "B2C" ,"test50_facility (Haryana)", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "test50_facility (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C package with NSZ cn", "B2C" ,"NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "X-NSZ", "RT", "Non-serviceable location","","",""},
			};
	}
	
	@Test(dataProvider = "Wrong_cn_cases" , enabled = true)
	public void VerifyServiceabilityTriggerWrongCN(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		
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
