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

public class EmbargoServiceability3 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public EmbargoServiceability3() {
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
	
	@DataProvider(name = "Updating_Address_of_a_Non_Embargoed_package", parallel = false)
	public Object[][] Updating_Address_of_a_Non_Embargoed_package() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "NSZ", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS","Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "NSZ", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "Updating_Address_of_a_Non_Embargoed_package" , enabled = true)
	public void VerifyEmbargoServiceabilityAddUpdate(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload 
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
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		
		Utilities.hardWait(10);
		
		//Updating address such that aseg will suggest pin = 122001
		Payload.put("add", "Godoli");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
		apiCtrl.EditApi(waybill,Payload);
		
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
	
	//Updating add of an Embargoed package to a non Embargoed pin
	@DataProvider(name = "Updating_Address_of_a_Embargoed_package", parallel = false)
	public Object[][] Updating_Address_of_a_Embargoed_package() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-219", "UD", "Pincode updated by Addfix","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS","Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "Updating_Address_of_a_Embargoed_package" , enabled = true)
	public void VerifyEmbargoServiceabilityAddUpdateToNonEmbargoPin(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload 
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("add", "Godoli");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
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
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		
		Utilities.hardWait(10);
		
		//Updating address such that aseg will suggest pin = 122001
		Payload.put("add", "Baliwas");
		Payload.put("city", "Gurgaon");
		Payload.put("state", "Haryana");
		apiCtrl.EditApi(waybill,Payload);
		
		Utilities.hardWait(160);
		
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
	
	
	@DataProvider(name = "Updating_Pincode_of_a_Embargoed_package", parallel = false)
	public Object[][] Updating_Pincode_of_a_Embargoed_package() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
			{ "Scenario:: B2C package", "B2C" , "Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-205", "RT", "Package details changed by Delhivery","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-205", "UD", "Package details changed by Delhivery","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS","Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "DTUP-205", "RT", "Package details changed by Delhivery","","",""}
			};
	}
	
	@Test(dataProvider = "Updating_Pincode_of_a_Embargoed_package" , enabled = true)
	public void VerifyEmbargoServiceabilityPincodeUpdateToNonEmbargoPin(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload 
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("city", "");
		Payload.put("state", "");
		Payload.put("add", "test");
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
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		
		Utilities.hardWait(10);
		
		//Updating address such that aseg will suggest pin = 122001
		Payload.put("pincode", "122003");
		apiCtrl.EditApi(waybill,Payload);
		
		Utilities.hardWait(160);
		
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
	
	@DataProvider(name = "Addfix_does_not_Suggests_New_Pin", parallel = false)
	public Object[][] Addfix_does_not_Suggests_New_Pin() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C package", "B2C" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "DTUP-203", "UD", "Package details changed by shipper","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "DTUP-203", "RT", "Package details changed by shipper","","",""}
			};
	}
	
	@Test(dataProvider = "Addfix_does_not_Suggests_New_Pin" , enabled = true)
	public void VerifyEmbargoServiceabilityNoNewPinSuggestedByAddfix(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
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
		
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			Utilities.hardWait(30);
		}
		
		Utilities.hardWait(10);
		
		//Updating address such that aseg will suggest pin = 122001
		Payload.put("add", "Test123");
		apiCtrl.EditApi(waybill,Payload);
		
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
