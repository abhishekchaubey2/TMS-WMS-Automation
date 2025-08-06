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
import com.fasterxml.jackson.core.JsonProcessingException;
public class ReturnInternalWeightServiceability3 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ReturnInternalWeightServiceability3() {
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

	
	//Diff state -> DONE
	@DataProvider(name = "weight_updated_on_diff_state_shipments", parallel = false)
	public Object[][] weight_updated_on_diff_state_shipments() {
		return new Object[][] {
//			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-DDD3FD", "UD", "Out for delivery", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-38", "DL", "Delivered to consignee", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null" },
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-111", "DL", "DTO due to poor packaging", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"}
			};
		}
			
		@Test(dataProvider = "weight_updated_on_diff_state_shipments", enabled = true)
		public void verifyRCnWTUpdatedForDiffStateShipment(String Scenario, String type, String state, String cn,
				String cnid, String rgn, String sc, String srv, String ocid, String cnc, String dpc, String wvcid,
				String cpdt, String cn1, String dpcid, String cs_nsl, String cs_st, String cs_sr, String rcn,
				String rcn1, String rcnid, String rdpc, String rdpcid, String rcty, String rcns) {
			clData = new HashMap<String, String>();
			clData.put("client", "regression_client");
			clData.put("product_type", type);
			clData.put("return_pin", "122003");
			clData.put("return_add", "Test");
			waybill = diffStShpt.DifferentStateShipments(state, clData);
			logInfo("Waybill " + waybill);
			Utilities.hardWait(20);

			// Updating weight
			clData.put("wt_rule", "Predicted_CTLG");
			if (state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO")
					|| state.equalsIgnoreCase("Cancelled")) {
				clData.put("SecondPojo", "true");
			}

			// Return the shipment
//			//Mark return
//			HashMap<String,String> edit_payload = new HashMap<>();
//			List<String> wbns = new ArrayList<>();
//			wbns.add(waybill);
//			apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
			

			// Update weight
			Utilities.hardWait(15);

			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);

			Utilities.hardWait(60);
			// Asserting values on master package
			FillExpectedValues(cn, cnid, rgn, sc, srv, ocid, cnc, dpc, wvcid, cpdt, cn1, dpcid, cs_nsl, cs_sr, cs_st,
					rcn, rcn1, rcnid, rdpc, rdpcid, rcty, rcns);
			if (state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO")
					|| state.equalsIgnoreCase("Cancelled")) {
				PackageDetail2 pkgDetails = apiCtrl.fetchPackageInfo2(waybill, clData);
				ServiceabilityKeysAssertions.assertServiceabilityKeys2(pkgDetails, Expected_values);
			} else {
				PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgDetails, Expected_values);
			}

		}
			
		@DataProvider(name = "Radd_Updated__In_RT_Transit_State", parallel = false)
		public Object[][] Radd_Updated__In_RT_Transit_State() {
			return new Object[][] { 
				{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null"},
				{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//				{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "pinCodeReturn"},
				{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
//				{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "null", "null"}
				};
		}
		
		@Test(dataProvider = "Radd_Updated__In_RT_Transit_State" , enabled = true)
		public void Radd_Updated__In_RT_Transit_State(String Scenario, String type, String cn , String cnid,
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
			Payload.put("return_add", "Godoli");
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
		
		@DataProvider(name = "Updated__Radd_Return_NSZ_Rcn_In_RT_Transit_State", parallel = false)
		public Object[][] Updated__Radd_Return_NSZ_Rcn_In_RT_Transit_State() {
			return new Object[][] { 
				{ "Scenario:: B2B package", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Delhi_East_RPC (Delhi)", "Delhi_East_RPC (Delhi)", "IND110096AAA", "null", "null", "Delhi", "null"},
				{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy package", "HEAVY" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Delhi_East_RPC (Delhi)", "Delhi_East_RPC (Delhi)", "IND110096AAA", "null", "null", "Delhi", "null"},
//				{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT 1" ,"Barauli_SiwanRd_D (Bihar)", "IND841405AAA", "E", "null", "null", "null", "Barauli", "null", "null", "", "Barauli_SiwanRd_D (Bihar)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//				{ "Scenario:: NO DATA", "NO DATA" , "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "Heavy", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
//				{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110037AAI", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "null", "null"}
				};
		}
		
		@Test(dataProvider = "Updated__Radd_Return_NSZ_Rcn_In_RT_Transit_State" , enabled = true)
		public void Updated__Radd_Return_NSZ_Rcn_In_RT_Transit_State(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential for populating ewaybill
			clData.put("client", "regression_client");
			Payload.put("client", "regression_client");
			Payload.put("pin","122001"); 
			Payload.put("return_pin", "110031");
			Payload.put("return_add", "Ghondli");
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
			
			//Marking the package as return
			MarkReturnAfterGI(waybill, child_waybill, clData);
			
			Utilities.hardWait(10);
			
			if(Scenario.contains("B2C")){
				apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
			}else {
				apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
			}
			
			Utilities.hardWait(30);
			
			//Payload.put("return_add", "3J6Q+H6R, Sardhana Bypass Road Dabathwa, Karnal - Meerut Rd, Meerut, Uttar Pradesh 250341");
			//Gandhi Smarak Inter College, Dabathwa
			Payload.put("return_add", "Theka Road");
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
//		if(Scenario.contains("B2C")) {
//			Payload.put("pin", "122012");
//		}else {
//			Payload.put("pin", "110032");
//		}
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

		@Test(dataProvider = "EDT_VS_STAGING", enabled = false)
		public void EdtVsStaging(String Scenario, String type) {
			List<String> edt_waybills = new ArrayList<String>();
			List<String> staging_waybills = new ArrayList<String>();
			
			for (int i = 0; i < 2; i++) {
				List<String> waybills = new ArrayList<String>();
				HashMap<String, String> Payload = new HashMap<>();
				
				// creating shipment on edt
				if (i == 0) {
					Payload.put("enviorment", "staging");
				} else {
					Payload.put("enviorment", "edt");
				}
				
				Payload.put("client", "regression_client");
				Payload.put("pin", "122001");
				Payload.put("return_pin", "122003");
				Payload.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
				Payload.put("return_city", "Gurgaon");
				Payload.put("return_state", "Haryana");
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
				Payload.put("cwh_uuid", "");
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
				
				Utilities.hardWait(15);
				
				//Returning Shipment 
				List<String> wbns = new ArrayList<>();
				wbns.add(waybill);
				if (!child_waybill.equalsIgnoreCase(waybill)) {
					wbns.add(child_waybill);
				}
				apiCtrl.ApplyNslDiffEnv(Payload.get("enviorment"), wbns,"RT", "RT-101",Payload);
				
				
				//Updating weight on shipments
				if(Scenario.contains("B2C")){
					apiCtrl.QcWtApiDiffEnv(Payload.get("enviorment"),waybill, 15000.12, "Predicted_CTLG", Payload);
				}else {
					apiCtrl.QcWtApiDiffEnv(Payload.get("enviorment"),waybill, 14000.12, "Predicted_CTLG", Payload);
				}
				
				
				Utilities.hardWait(10);
				
				
				if (i == 0) {
					staging_waybills = waybills;
				} else {
					edt_waybills = waybills;
				}
			}
			
			Utilities.hardWait(60);
			
			//call assertion function here
			HashMap<String,String> Payload1 = new HashMap<>();
			Payload1.put("client", "regression_client");
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
}