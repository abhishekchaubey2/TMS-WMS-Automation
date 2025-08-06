package com.delhivery.Express.testModules.ReturnServiceabilityService;
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
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class TemplateReturnServiceability extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public TemplateReturnServiceability() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

	}
	
	public void FillExpectedValues(String cn, String cnid, String rgn, String sc, String srv, String ocid, 
			String cnc,String dpc, String wvcid, String cpdt,String cn1, String dpcid,String cs_nsl,String cs_sr,String cs_st,String rcn ,String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
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
		//For return shipments
		Expected_values.put("rcn", rcn);
		Expected_values.put("rcn1", rcn1);
		Expected_values.put("rcnid", rcnid);
		Expected_values.put("rdpc", rdpc);
		Expected_values.put("rdpcid", rdpcid);
		Expected_values.put("rcty", rcty);
		Expected_values.put("rcns", rcns);
		

	}
	
	
	public void MarkReturnAfterGI(String Waybill , String CWaybill , HashMap<String,String> data) {
		//Perform FM
		apiCtrl.fmOMSApi(Waybill, "FMPICK", data);
		//Performing FM of child package if present
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			apiCtrl.fmOMSApi(CWaybill, "FMPICK", data);
		}
		
		if(data.containsKey("B2B")|| data.containsKey("Heavy")) {
			Utilities.hardWait(60);
		}
		
		
		//Perform GI
		String fm_loc  = apiCtrl.fetchPackageInfo(Waybill, data).cs.sl;
		apiCtrl.giApi(Waybill, fm_loc, data);
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			apiCtrl.giApi(CWaybill, fm_loc, data);
		}
		
		Utilities.hardWait(20);
		
		//Mark return
		HashMap<String,String> edit_payload = new HashMap<>();
		List<String> wbns = new ArrayList<>();
		wbns.add(Waybill);
		if (!CWaybill.equalsIgnoreCase(Waybill)) {
			wbns.add(CWaybill);
		}
		apiCtrl.ApplyNsl(wbns, "RT", "RT-101",data);
	}
	
	//Cannot return no data and partially manifested package
	@DataProvider(name = "Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not received from Client", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "null", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceability(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client_2");
		Payload.put("client", "custom_srv_client_2");
		Payload.put("pin","122001"); 
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
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		Utilities.hardWait(60);
		
		//Returning the package
		List<String> wbns = new ArrayList<>();
		wbns.add(waybill);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			wbns.add(child_waybill);
		}
		apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
		
		Utilities.hardWait(10);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid,	 rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Weight_Updated_in_FWD_Flow_While_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Weight_Updated_in_FWD_Flow_While_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PPOM", "UD", "Shipment picked up", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Sonipat", "null"},
			{ "Scenario:: B2C package", "B2C" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "null", "IND110092AAB", "Mumbai", "null", "", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-231", "UD", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "true", "IND110092AAB", "Gurgaon", "null", "null", "B2C", "Gurgaon (Haryana)", "null", "DTUP-231", "UD", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "B2B", "Gurgaon (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Sonipat", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "B2B", "Gurgaon (Haryana)", "null", "X-PPOM", "UD", "Shipment picked up", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Sonipat", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "null", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-231", "UD", "Center changed by system", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-PPOM", "UD", "Shipment picked up", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Sonipat", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "null", "IND110092AAB", "Mumbai", "null", "", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-231", "UD", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-PPOM", "UD", "Shipment picked up", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Sonipat", "null"}
			};
	}
	
	@Test(dataProvider = "Weight_Updated_in_FWD_Flow_While_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityWeightUpdate(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client_2");
		Payload.put("client", "custom_srv_client_2");
		Payload.put("pin","122001"); 
		Payload.put("return_pin","122003"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
			Utilities.hardWait(60);
		}
		
		Utilities.hardWait(20);
		
		//Perform FM
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}
		
		
		//updating weight of package
		if(Scenario.contains("Heavy") || Scenario.contains("B2B")) {
			apiCtrl.QcWtApi(waybill, 9000.12 ,"Predicted_CTLG", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 15000.12 ,"Predicted_CTLG", clData);
		}
		
		Utilities.hardWait(30);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	
	@DataProvider(name = "Rcn_Set_As_NSZ_For_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Rcn_Set_As_NSZ_For_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "NSZ", "NSZ", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2C package", "B2C" , "KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "Delhi", "null", "null", "", "KN_RPC (DELHI)", "null", "X-UCI", "UD", "Manifest uploaded", "NSZ", "NSZ", "null", "null", "null", "null", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "Heavy", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "NSZ", "NSZ", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "\"Del_Okhla_PC (DELHI)\"", "null", "B2B", "Del_Okhla_PC (DELHI)", "\"IND110044AAB\"", "X-UCI", "UD", "Manifest uploaded", "NSZ", "null", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "\"Del_Okhla_PC (DELHI)\"", "null", "B2B", "Del_Okhla_PC (DELHI)", "\"IND110044AAB\"", "X-UCI", "UD", "Manifest uploaded", "NSZ", "null", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "ro", "null", "null", "", "KN_RPC (DELHI)", "null", "X-UCI", "UD", "Manifest uploaded", "NSZ", "null", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "NSZ", "NSZ", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "Delhi", "null", "null", "", "KN_RPC (DELHI)", "null", "X-UCI", "UD", "Manifest uploaded", "NSZ", "NSZ", "null", "null", "null", "null", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" , "Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "Heavy", "Del_Okhla_PC (DELHI)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "NSZ", "NSZ", "null", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Rcn_Set_As_NSZ_For_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityRcnAsNSZ(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client_2");
		Payload.put("client", "custom_srv_client_2");
		Payload.put("return_add", "Test");
		Payload.put("return_city", "Delhi");
		Payload.put("return_state", "Delhi");
		Payload.put("return_pin","110086"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
			Utilities.hardWait(60);
		}
		
		Utilities.hardWait(60);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Rcn_Set_As_Empty_For_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Rcn_Set_As_Empty_For_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "Patna_1 (Bihar)", "Patna_1 (Bihar)", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2C package", "B2C" , "KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "Delhi", "null", "null", "", "KN_RPC (DELHI)", "null", "X-UCI", "UD", "Manifest uploaded", "KN_RPC (Delhi)", "KN_RPC (Delhi)", "IND110064AAB", "null", "null", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "Heavy", "Del_Okhla_PC (Delhi)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "Patna_1 (Bihar)", "Patna_1 (Bihar)", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "\"Del_Okhla_PC (DELHI)\"", "null", "B2B", "Del_Okhla_PC (DELHI)", "\"IND110044AAB\"", "X-UCI", "UD", "Manifest uploaded", "Patna_1 (Bihar)", "null", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "\"Del_Okhla_PC (DELHI)\"", "null", "B2B", "Del_Okhla_PC (DELHI)", "\"IND110044AAB\"", "X-UCI", "UD", "Manifest uploaded", "Patna_1 (Bihar)", "null", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "ro", "null", "null", "", "KN_RPC (DELHI)", "null", "X-UCI", "UD", "Manifest uploaded", "KN_RPC (Delhi)", "null", "IND110064AAB", "null", "null", "ro", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Del_Okhla_PC (DELHI)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "Del_Okhla_PC (DELHI)", "null", "B2B", "Del_Okhla_PC (DELHI)", "IND110044AAB", "X-UCI", "UD", "Manifest uploaded", "Patna_1 (Bihar)", "Patna_1 (Bihar)", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2C MPS", "B2C MPS" ,"KN_RPC (DELHI)", "IND110064AAB", "", "null", "null", "null", "ro", "null", "null", "", "KN_RPC (DELHI)", "null", "X-UCI", "UD", "Manifest uploaded", "KN_RPC (Delhi)", "null", "IND110064AAB", "null", "null", "ro", "null"},
//			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Del_Okhla_PC (Delhi)", "IND110044AAB", "", "hdghdj", "true", "null", "Delhi", "\"Del_Okhla_PC (DELHI)\"", "null", "Heavy", "Del_Okhla_PC (Delhi)", "\"IND110044AAB\"", "X-UCI", "UD", "Manifest uploaded", "Patna_1 (Bihar)", "null", "null", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Rcn_Set_As_Empty_For_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityRcnAsEmpty(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client_2");
		Payload.put("client", "custom_srv_client_2");
		Payload.put("return_add", "Test");
		Payload.put("return_city", "Delhi");
		Payload.put("return_state", "Delhi");
		Payload.put("return_pin","110085"); 
		
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		Utilities.hardWait(60);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Weight_Updated_In_RT_State_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Weight_Updated_In_RT_State_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C package", "B2C" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110037AAI", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Delhi_Rohini_RP (Delhi)", "Delhi_Rohini_RP (Delhi)", "IND110086AAA", "null", "null", "Delhi", "null"},
			};
	}
	
	@Test(dataProvider = "Weight_Updated_In_RT_State_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityWeightUpdateInRtFlow(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client_2");
		Payload.put("client", "custom_srv_client_2");
		Payload.put("pin","122001"); 
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
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		//updating weight of package
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
		Utilities.hardWait(10);
		
		//updating weight of package
		if(Scenario.contains("Heavy") || Scenario.contains("B2B")) {
			apiCtrl.QcWtApi(waybill, 14000.12 ,"Predicted_CTLG", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 15000.12 ,"Predicted_CTLG", clData);
		}
		
		Utilities.hardWait(50);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
	
	@DataProvider(name = "Old_Pin_Has_No_Data_While_Custom_Data_Set_In_Client_Config", parallel = false)
	public Object[][] Old_Pin_Has_No_Data_While_Custom_Data_Set_In_Client_Config() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "domesticTemplateReturnHeavy"},
			{ "Scenario:: B2C package", "B2C" ,  "Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "IND110092AAB", "Barauli", "null", "", "", "Barauli_SiwanRd_D (Bihar)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "domesticTemplateReturnHeavy"},
			{ "Scenario:: Heavy package", "HEAVY" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "domesticTemplateReturnHeavy"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "IND110092AAB", "Mumbai", "null", "", "B2B", "Mumbai MIDC (Maharashtra)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
			};
	}
	
	@Test(dataProvider = "Old_Pin_Has_No_Data_While_Custom_Data_Set_In_Client_Config" , enabled = true)
	public void VerifyCustomServiceabilityRaddUpdateAndOldRpinHasNoData(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "custom_srv_client_2");
		Payload.put("client", "custom_srv_client_2");
		Payload.put("pin","122001"); 
		Payload.put("return_pin", "110020");
		Payload.put("return_add", "Lotus Temple Rd, Bahapur, Shambhu Dayal Bagh, Kalkaji, New Delhi, Delhi 110019");
		Payload.put("return_city", "Delhi");
		Payload.put("return_state", "Delhi");
		
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
		
		//Getting child waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("Partially Manifested"))) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}
		
		if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
			clData.put("B2B", "");
			clData.put("Heavy", "");
		}
		
		//Returning package
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
		Utilities.hardWait(15);
		
		//updating return add of package
		Payload.put("return_add", "5FR+W6J, Chatri Wala Kuan, Lado Sarai, New Delhi, Delhi 110030");
		apiCtrl.EditApi(waybill,Payload);
		
		Utilities.hardWait(90);
		
		//Asserting values on master package
		FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
		PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
		
		//Asserting values on child waybill if present
		if(!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail cpkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(cpkgDetails, Expected_values);
		}
		
	}
}
