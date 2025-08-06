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
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class EmbargoServiceability extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public EmbargoServiceability() {
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
	
	@DataProvider(name = "MND_Falls_Before_Embargo_Time", parallel = false)
	public Object[][] MND_Falls_Before_Embargo_Time() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "null", "Delhi", "null", "null", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "East Delhi (Delhi)", "IND110092AAB", "", "KCF/MDL", "null", "null", "Delhi", "null", "null", "", "East Delhi (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "null", "Delhi", "null", "null", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" ,  "East Delhi (Delhi)", "IND110092AAB", "", "KCF/MDL", "null", "null", "Delhi", "null", "null", "", "East Delhi (Delhi)", "null", "X-UCI", "UD", "Manifest uploaded","","",""}
			};
	}
	
	@Test(dataProvider = "MND_Falls_Before_Embargo_Time" , enabled = true)
	public void VerifyNoEmbargoServiceability(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
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
		
		Utilities.hardWait(55);
		
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
	
	@DataProvider(name = "MND_Falls_After_Embargo_Time", parallel = false)
	public Object[][] MND_Falls_After_Embargo_Time() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" ,"NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy package", "HEAVY" ,"NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "Heavy", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "Heavy", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: NO DATA", "NO DATA" , "NSZ", "null", "null", "null", "null", "null", "null", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "null", "null", "null", "null", "X-SPM", "UD", "Shipment partially manifested","","",""}
			};
	}
	
	@Test(dataProvider = "MND_Falls_After_Embargo_Time" , enabled = true)
	public void VerifyEmbargoServiceability(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("pin","122001"); 
		
		if(type.equalsIgnoreCase("NO DATA") || type.equalsIgnoreCase("PARTIALLY MANIFESTED")) {
			Payload.put("client", "custom_srv_client_2");
			clData.put("client", "custom_srv_client_2");
			Payload.put("pin","829205"); 
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
	
	
	@DataProvider(name = "MND_Falls_After_Embargo_Time_with_fail_nsz_set_to_false", parallel = false)
	public Object[][] MND_Falls_After_Embargo_Time_with_fail_nsz_set_to_false() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "null", "null", "X-UCI", "UD", "Manifest uploaded","","",""}
			};
	}
	
	@Test(dataProvider = "MND_Falls_After_Embargo_Time_with_fail_nsz_set_to_false" , enabled = true)
	public void VerifyNoEmbargoServiceabilityAsFailNSZSetAsFalse(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("pin","122003");
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
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
	
	
	@DataProvider(name = "Performing_GI_And_MND_Falls_Before_Embargo_Time", parallel = false)
	public Object[][] Performing_GI_And_MND_Falls_Before_Embargo_Time() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "null", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C package", "B2C" , "East Delhi (Delhi)", "IND110092AAB", "", "KCF/MDL", "null", "IND110092AAB", "Delhi", "null", "", "", "null", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "null", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "East Delhi (Delhi)", "IND110092AAB", "", "KCF/MDL", "null", "IND110092AAB", "Delhi", "null", "", "", "null", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","","",""}
			};
	}
	
	@Test(dataProvider = "Performing_GI_And_MND_Falls_Before_Embargo_Time" , enabled = true)
	public void VerifyNoEmbargoServiceabilityWithGI(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
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
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(70);
		}
		
		
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
	
	@DataProvider(name = "Updating_Address_of_a_Embargoed_package", parallel = false)
	public Object[][] Updating_Address_of_a_Embargoed_package() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package", "B2C" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "null", "", "NSZ", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "null", "", "NSZ", "null",  "DTUP-203", "RT", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "Updating_Address_of_a_Embargoed_package" , enabled = true)
	public void VerifyNoEmbargoServiceabilityAddUpdate(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("pin","122001");
		Payload.put("add", "Godoli");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
		
		
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
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		
		Utilities.hardWait(20);
		
		//Updating address such that aseg will suggest pin = 122003
		Payload.put("add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		apiCtrl.EditApi(waybill,Payload);
		
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
	
	//Updating add of an Embargoed package in manifested state
	@DataProvider(name = "Updating_Address_of_a_Embargoed_package_in_manifested_state", parallel = false)
	public Object[][] Updating_Address_of_a_Embargoed_package_in_manifested_state() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""}
			};
	}
	
	@Test(dataProvider = "Updating_Address_of_a_Embargoed_package_in_manifested_state" , enabled = true)
	public void VerifyNoEmbargoServiceabilityAddUpdateManifestedState(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("pin","122001");
		Payload.put("add", "Godoli");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
		
		
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
			Utilities.hardWait(30);
		}
		
		Utilities.hardWait(20);
		
		//Updating address such that aseg will suggest pin = 122003
		Payload.put("add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
		apiCtrl.EditApi(waybill,Payload);
		
		Utilities.hardWait(100);
		
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
