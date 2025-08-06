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
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class ReturnInternalWeightServiceability4 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ReturnInternalWeightServiceability4() {
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
		data.put("cwh_uuid", "");
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

		
		//Ewaybill case
		@DataProvider(name = "Ewaybill_Case", parallel = false)
		public Object[][] Ewaybill_Case() {
			return new Object[][] { 
				{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null"},
				{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//				{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null"},
				{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Gurgaon", "null"},
//				{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "null", "null"}
				};
		}
		
		@Test(dataProvider = "Ewaybill_Case" , enabled = false)
		public void Ewaybill_Case(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential for populating ewaybill
			clData.put("client", "regression_client");
			Payload.put("client", "regression_client");
			Payload.put("pin","122001"); 
			Payload.put("total_amount", "55000");
			Payload.put("return_pin", "122001");
			Payload.put("return_add", "Sec 44 gurgaon");
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
			
			//Marking the package as return
			MarkReturnAfterGI(waybill, child_waybill, clData);
			
			Utilities.hardWait(10);
			
			if(Scenario.contains("B2C")){
				apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
			}else {
				apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
			}
			
			Utilities.hardWait(30);
			
//			This will change the return pin to delhi
			Payload.put("return_add", "Karol Bagh");
			Payload.put("return_city", "Delhi");
			Payload.put("return_state", "Haryana");
			apiCtrl.EditApi(waybill, Payload);
			
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
		
		//Restricted Mapper case
		@DataProvider(name = "Restricted_Mapper_case", parallel = false)
		public Object[][] Restricted_Mapper_case() {
			return new Object[][] { 
				{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null"},
				{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null"},
				{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null"},
				{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Gurgaon", "null"}
				};
		}
		
		@Test(dataProvider = "Restricted_Mapper_case" , enabled = false)
		public void Restricted_Mapper_case(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential for populating ewaybill
			clData.put("client", "restrict_mapper_callback_client");
			Payload.put("client", "restrict_mapper_callback_client");
			Payload.put("pin","122001"); 
			Payload.put("return_pin", "122001");
			Payload.put("return_add", "Sec 44 gurgaon");
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
			
			//Marking the package as return
			MarkReturnAfterGI(waybill, child_waybill, clData);
			
			Utilities.hardWait(10);
			
			if(Scenario.contains("B2C")){
				apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
			}else {
				apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
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
		
		//Different payment mode case
		@DataProvider(name = "Different_payment_mode", parallel = false)
		public Object[][] Different_payment_mode() {
			return new Object[][] { 
				{ "Scenario:: B2B package ", "B2B" ,"COD", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null",  "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"Pre-paid", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"Pickup", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"Cash", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "PP", "Manifest uploaded", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"REPL", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
				};
		}
		
		@Test(dataProvider = "Different_payment_mode" , enabled = true)
		public void VerifyReturnInternalWeightServiceabilityDIffPayment(String Scenario, String type, String payment_mode,String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential
			clData.put("client", "regression_client");
			Payload.put("client", "regression_client");
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
			
			if(Scenario.contains("B2B")|| Scenario.contains("Heavy")) {
				clData.put("B2B", "");
				clData.put("Heavy", "");
			}
			
			Utilities.hardWait(10);
			
			//Return the package
			clData.put("cwh_uuid", "");
			if(!payment_mode.equalsIgnoreCase("Pickup") && !payment_mode.equalsIgnoreCase("Cash")) {
				MarkReturnAfterGI(waybill, child_waybill, clData);
			}
			
			Utilities.hardWait(10);
			
			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
			
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
		
		//addfix return no new pin
		@DataProvider(name = "Radd_Updated_to_test", parallel = false)
		public Object[][] Radd_Updated_to_test() {
			return new Object[][] { 
				{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
				{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null"},
				{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"}
				};
		}
		
		@Test(dataProvider = "Radd_Updated_to_test" , enabled = true)
		public void Radd_Updated_to_test(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential for populating ewaybill
			clData.put("client", "regression_client");
			Payload.put("client", "regression_client");
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
			
			//Marking the package as return
			MarkReturnAfterGI(waybill, child_waybill, clData);
			
			Utilities.hardWait(10);
			
			if(Scenario.contains("B2C")){
				apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
			}else {
				apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
			}
			
			Utilities.hardWait(30);
			
//			Payload.put("return_add", "Palika Bazar");
			Payload.put("return_add", "Test");
			apiCtrl.EditApi(waybill, Payload);
			
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
		
		//Diff state nsz -> covered in RTSRV5
		@DataProvider(name = "aseg_updates_nsz_cn_diff_state_shipments", parallel = false)
		public Object[][] aseg_updayes_nsz_cn_diff_state_shipments() {
			return new Object[][] {
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-PIOM", "UD", "Shipment Recieved at Origin Center","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-DDD3FD", "UD", "Out for delivery","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-38", "DL", "Delivered to consignee","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null" },
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST" ,"Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-ASP", "PP", "Pickup scheduled","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-77", "PU", "Pickup completed","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "RT-111", "DL", "DTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-108", "CN", "QC failed at pickup. Product count mismatch","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Pending", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Dispatched", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-DDD3FD", "UD", "Out for delivery","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Delivered", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "EOD-38", "DL", "Delivered to consignee","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Returned" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-101", "RT", "Returned as per Client Instructions","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2C Package with weight more than 10 kg", "B2C","RTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-110", "DL", "RTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" , "LOST", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "LT-100", "LT", "Shipment LOST","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"PickupPending", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "X-ASP", "PP", "Pickup scheduled","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "PickedUp", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-77", "PU", "Pickup completed","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "DTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "RT-111", "DL", "DTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Cancelled" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"}
			};
		}
		
		@Test(dataProvider = "aseg_updates_nsz_cn_diff_state_shipments", enabled = false)
		public void verifyCnaddUpdatedtoNSZForDiffStateShipment(String Scenario, String type,String state,
				String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			clData = new HashMap<String,String>();
			clData.put("client", "regression_client");
			clData.put("product_type", type);
			clData.put("return_add", "Ghondli");
			clData.put("return_city", "Delhi");
			clData.put("return_state", "Delhi");
			clData.put("return_pin", "110031");
			waybill = diffStShpt.DifferentStateShipments(state,clData);
			logInfo("Waybill " + waybill);
			Utilities.hardWait(20);
			
			//Updating weight
			clData.put("wt_rule", "Predicted_CTLG");
			if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
				clData.put("SecondPojo", "true");
			}
			
			Utilities.hardWait(10);
			
			//Returning the package
			
			MarkReturnAfterGI(waybill, waybill, clData);
			
			Utilities.hardWait(10);
			
			if(Scenario.contains("B2C")){
				apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
			}else {
				apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
			}
			
			Utilities.hardWait(30);
			
			//Editing radd
			clData.put("return_add", "Theka road");
			apiCtrl.EditApi(waybill, clData);
			
			Utilities.hardWait(60);
			//Asserting values on master package
			FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt,cn1,dpcid,cs_nsl,cs_sr,cs_st,rcn,rcn1,rcnid,rdpc,rdpcid,rcty,rcns);
			if(state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO") || state.equalsIgnoreCase("Cancelled")) {
				PackageDetail2 pkgDetails = apiCtrl.fetchPackageInfo2(waybill, clData);
				ServiceabilityKeysAssertions.assertServiceabilityKeys2(pkgDetails, Expected_values);
			}else {
				PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
			}

		}
		
		//cn = NSZ, invalid,No data exist
		
		//SRV TRIGGER FOR IN-TRANSIT SHIPMENTS , WT RULE -> "Predicted_CTLG"--> DONE
		@DataProvider(name = "Wrong_cn_cases", parallel = false)
		public Object[][] Wrong_cn_cases() {
			return new Object[][] { 
				{ "Scenario:: B2B package with no data exist", "B2B" ,"Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "RT", "Center changed by system","KN_RPC (DELHI)", "KN_RPC (DELHI)", "IND110064AAB", "null", "null", "Delhi", "null"},
				{ "Scenario:: B2B package with invalid cn", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "RT", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{ "Scenario:: B2B package with NSZ cn", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "RT", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{ "Scenario:: B2C package with no data exist", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "RT", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{ "Scenario:: B2C package with invalid cn", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "RT", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{ "Scenario:: B2C package with NSZ cn", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "N", "1209", "null", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "RT", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				};
		}
		
		@Test(dataProvider = "Wrong_cn_cases" , enabled = true)
		public void VerifyServiceabilityTriggerWrongCN(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			clData.put("client", "regression_client");
			
			if(Scenario.contains("NSZ cn")) {
				clData.put("return_pin", "110033");
				clData.put("return_add", "Burari");
			}else if(Scenario.contains("no data exist")) {
				clData.put("return_pin", "312456");
				clData.put("return_add", "test");
			}else {
				//invalid cn
				clData.put("return_pin", "133101");
				clData.put("return_add", "Saha");
			}
			Payload = clData;
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
			
			
			//Returning the package
			MarkReturnAfterGI(waybill, waybill, clData);
			
			Utilities.hardWait(10);
			
			if(Scenario.contains("B2C")){
				apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
			}else {
				apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
			}
			
			
			
			
			Utilities.hardWait(35);
			
			
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