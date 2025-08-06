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

public class EmbargoServiceability2 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public EmbargoServiceability2() {
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
	
	@DataProvider(name = "Custom_Config_Set", parallel = false)
	public Object[][] Custom_Config_Set() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "null", "null", "true", "IND110092AAB", "null", "null", "null", "B2B", "NSZ", "null", "X-NSZ", "UD", "Non-serviceable location","","",""},
			{ "Scenario:: B2C package", "B2C" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "X-PPOM", "RT", "Shipment picked up","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "NSZ", "null", "null", "null", "true", "IND110092AAB", "null", "null", "null", "B2B", "NSZ", "null", "X-NSZ", "UD", "Non-serviceable location","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "", "NSZ", "null", "X-PPOM", "RT", "Shipment picked up","","",""}
			};
	}
	
	@Test(dataProvider = "Custom_Config_Set" , enabled = true)
	public void VerifyEmbargoServiceabilityCustomConfigSet(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload
		clData.put("client", "custom_srv_client");
		Payload.put("client", "custom_srv_client");
		Payload.put("pin","110039");
		
		
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
	
	@DataProvider(name = "Updating_Weight_of_a_Embargoed_package", parallel = false)
	public Object[][] Updating_Weight_of_a_Embargoed_package() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "null", "B2B", "NSZ", "null", "X-PPOM", "UD", "Shipment picked up","","",""},
			{ "Scenario:: B2C package", "B2C" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "null", "", "NSZ", "null", "X-PPOM", "RT", "Shipment picked up","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "", "B2B", "NSZ", "null", "X-PPOM", "UD", "Shipment picked up","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "NSZ", "null", "null", "null", "null", "IND110092AAB", "null", "null", "null", "", "NSZ", "null", "X-PPOM", "RT", "Shipment picked up","","",""}
			};
	}
	
	@Test(dataProvider = "Updating_Weight_of_a_Embargoed_package" , enabled = true)
	public void VerifyEmbargoServiceabilityWeightUpdate(String Scenario, String type, String cn , String cnid,
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
		
		Utilities.hardWait(30);
		
		//Updating address such that aseg will suggest pin = 122003
		if(Scenario.contains("B2C")) {
			apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 9999.12, "Predicted_CTLG", clData);
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
	
	@DataProvider(name = "Time_Key_is_Missing", parallel = false)
	public Object[][] Time_Key_is_Missing() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-PPOM", "UD", "Shipment picked up","","",""},
			{ "Scenario:: B2C package", "B2C" , "Delhi_mukherjeenagar (Delhi)", "IND110033AAA", "", "533", "null", "IND110092AAB", "Delhi", "null", "null", "", "Delhi_mukherjeenagar (Delhi)", "null", "X-PPOM", "UD", "Shipment picked up","","",""},
			{ "Scenario:: B2B MPS package", "B2B MPS" , "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "", "1395", "true", "IND110092AAB", "Delhi", "null", "null", "B2B", "Delhi_Paschim_DC (Delhi)", "null", "X-PPOM", "UD", "Shipment picked up","","",""},
			{ "Scenario:: B2C MPS package", "B2C MPS" , "Delhi_mukherjeenagar (Delhi)", "IND110033AAA", "", "591", "false", "IND110092AAB", "Delhi", "null", "null", "", "Delhi_mukherjeenagar (Delhi)", "null", "X-PPOM", "UD", "Shipment picked up","","",""}
			};
	}
	
	@Test(dataProvider = "Time_Key_is_Missing" , enabled = true)
	public void VerifyEmbargoServiceabilityTimeKeyMissing(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr,String cs_ss,String pin,String rpin) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "embargo_client");
		Payload.put("client", "embargo_client");
		Payload.put("payment_mode", "Pre-paid");
		Payload.put("mps_amount", "0");
		Payload.put("pin","110088");
		
		
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
		
		
		Utilities.hardWait(80);
		
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
