package com.delhivery.Express.testModules.ServiceabilityService;

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

public class EmbargoServiceability4 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public EmbargoServiceability4() {
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
	
	//As package is embargoed diff state shipments is not possible as cn  = NSZ
	@DataProvider(name = "diff_state_shipments", parallel = false)
	public Object[][] diff_state_shipments() {
		return new Object[][] {
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "X-DDD3FD", "UD", "Out for delivery","","",""},
//				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "EOD-38", "DL", "Delivered to consignee","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "", "B2B", "NSZ", "null", "RT-101", "RT", "Returned as per Client Instructions","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "NSZ", "null", "null", "null", "null", "null", "null", "null", "", "B2B", "NSZ", "null", "RT-110", "DL", "RTO due to poor packaging","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "NSZ", "null", "null", "null", "null", "null", "null", "null", "", "B2B", "NSZ", "null", "LT-100", "LT", "Shipment LOST","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "NSZ", "null", "null", "null", "null", "null", "null", "null", "", "B2B", "NSZ", "null", "X-ASP", "PP", "Pickup scheduled","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "NSZ", "null", "EOD-77", "PU", "Pickup completed","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "NSZ", "null", "RT-111", "DL", "DTO due to poor packaging","","",""},
				{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "", "B2B", "NSZ", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch","","",""}
		};
	}
	
	@Test(dataProvider = "diff_state_shipments", enabled = false)
	public void VerifyEmbargoServiceabilityForDiffStateShipment(String Scenario, String type,String state,
			String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
		clData = new HashMap<String,String>();
		clData.put("client", "embargo_client");
		clData.put("product_type", type);
		clData.put("pin","122001"); 
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
	
	@DataProvider(name = "EDT_VS_STAGING", parallel = false)
	public Object[][] edt_vs_staging() {
		return new Object[][] { 
			{ "Scenario:: B2C package", "B2C"},
			{ "Scenario:: B2C MPS package", "B2C MPS"},
			{ "Scenario:: B2B package", "B2B"},
			{ "Scenario:: B2B MPS package", "B2B MPS"},
			{ "Scenario:: HEAVY package", "HEAVY"},
			{ "Scenario:: HEAVY MPS", "HEAVY MPS"}};
	}

	@Test(dataProvider = "EDT_VS_STAGING", enabled = false)
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
			Payload.put("client", "embargo_client");
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
			
			
			Utilities.hardWait(90);
			
			
			if (i == 0) {
				edt_waybills = waybills;
			} else {
				staging_waybills = waybills;
			}
		}
		
		Utilities.hardWait(60);
		
		//call assertion function here
		HashMap<String,String> Payload1 = new HashMap<>();
		Payload1.put("client", "embargo_client");
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
	
	//restrict mapper case
	//This case is not required for this template
	@DataProvider(name = "Restrict_Mapper_Case", parallel = false)
	public Object[][] Restrict_Mapper_Case() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "Restrict_Mapper_Case" , enabled = false)
	public void Restrict_Mapper_Case(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "restrict_mapper_callback_client");
		Payload.put("client", "restrict_mapper_callback_client");
		Payload.put("pin","110012");
		Payload.put("add", "Mayapuri");
		Payload.put("city", "Delhi");
		Payload.put("state", "Delhi");
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
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
	
	//Different payment mode
	@DataProvider(name = "Different_payment_mode", parallel = false)
	public Object[][] Different_payment_mode() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B" ,"COD", "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"Pre-paid","NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"Pickup","NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "PP", "Yet to Pickup","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"Cash", "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "PP", "Manifest uploaded","","",""},
			{ "Scenario:: B2B package ", "B2B" ,"REPL", "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""}
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
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("pin","122001");
		Payload.put("payment_mode", payment_mode);
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
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
