package com.delhivery.Express.testModules.ReturnServiceabilityService;

import com.delhivery.core.BaseTest;

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
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class ClientWarehousePickupLocationReturnServiceability2 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ClientWarehousePickupLocationReturnServiceability2() {
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
		if(fm_loc.equalsIgnoreCase("NSZ")) {
			fm_loc = "HQ (Haryana)";
		}
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
	
	
	//Different payment mode
	@DataProvider(name = "Different_payment_mode", parallel = false)
	public Object[][] Different_payment_mode() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B" ,"COD", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2B package ", "B2B" ,"Pre-paid", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2B package ", "B2B" ,"Pickup", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "PP", "Yet to Pickup", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B package ", "B2B" ,"Cash", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "PP", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B package ", "B2B" ,"REPL", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			};
	}
	
	@Test(dataProvider = "Different_payment_mode" , enabled = true)
	public void VerifyCWHPLServiceabilityDIffPayment(String Scenario, String type, String payment_mode,String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		Payload.put("cwh_sent","");
		Payload.put("payment_mode", payment_mode);
		Payload.put("pin","122001");
		Payload.put("pickup_location", "HQAPIREGRESSION");
		Payload.put("cwh", "HQAPIREGRESSION");
		Payload.put("return_pin", "");
		Payload.put("return_add", "");
		Payload.put("return_city", "");
		Payload.put("return_state", "");
		
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
		clData.put("cwh_uuid", "delhivery::clientwarehouse::0463fc90-d7ca-4233-8ac4-be9d28d1aad2");
		if(!payment_mode.equalsIgnoreCase("Pickup") && !payment_mode.equalsIgnoreCase("Cash")) {
			MarkReturnAfterGI(waybill, child_waybill, clData);
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
	
	//different states
	@DataProvider(name = "diff_state_shipments", parallel = false)
	public Object[][] diff_state_shipments() {
		return new Object[][] {
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-DDD3FD", "UD", "Out for delivery", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-38", "DL", "Delivered to consignee", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null" },
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-111", "DL", "DTO due to poor packaging", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"REPL RETURNED" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "RT", "Pickup completed", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"REPL PICKUP" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"}
			};
		}
			
		@Test(dataProvider = "diff_state_shipments", enabled = true)
		public void VerifyCWHPLServiceabilityForDiffStateShipment(String Scenario, String type, String state, String cn,
				String cnid, String rgn, String sc, String srv, String ocid, String cnc, String dpc, String wvcid,
				String cpdt, String cn1, String dpcid, String cs_nsl, String cs_st, String cs_sr, String rcn,
				String rcn1, String rcnid, String rdpc, String rdpcid, String rcty, String rcns) {
			clData = new HashMap<String, String>();
			clData.put("client", "regression_client");
			clData.put("product_type", type);
			clData.put("pickup_location", "HQAPIREGRESSION");
			clData.put("cwh", "HQAPIREGRESSION");
			clData.put("cwh_sent","");
			clData.put("return_pin", "");
			clData.put("return_add", "");
			clData.put("return_city", "");
			clData.put("return_state", "");
			waybill = diffStShpt.DifferentStateShipments(state, clData);
			logInfo("Waybill " + waybill);
			Utilities.hardWait(20);

			// Updating weight
//			clData.put("wt_rule", "Predicted_CTLG");
//			if (state.equalsIgnoreCase("PickedUp") || state.equalsIgnoreCase("DTO")
//					|| state.equalsIgnoreCase("Cancelled")) {
//				clData.put("SecondPojo", "true");
//			}
//
//			// Return the shipment
////			//Mark return
//			HashMap<String,String> edit_payload = new HashMap<>();
//			List<String> wbns = new ArrayList<>();
//			wbns.add(waybill);
//			apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
//			
//
//			// Update weight
//			Utilities.hardWait(15);
//
//			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
//
//			Utilities.hardWait(60);
//			// Asserting values on master package
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
		
		
		//radd update
		@DataProvider(name = "Return_address_updated", parallel = false)
		public Object[][] Return_address_updated() {
			return new Object[][] { 
				{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C package", "B2C WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},

				};
		}
		
		@Test(dataProvider = "Return_address_updated" , enabled = true)
		public void VerifyCWHPLServiceabilityRaddUpdated(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential for populating ewaybill
			clData.put("client", "regression_client");
			Payload.put("client", "regression_client");
			Payload.put("pin","122001");
			Payload.put("pickup_location", "HQAPIREGRESSION");
			Payload.put("cwh", "HQAPIREGRESSION");
			Payload.put("return_pin", "");
			Payload.put("return_add", "");
			Payload.put("return_city", "");
			Payload.put("return_state", "");
			
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
			
			//Returning the package
			HashMap<String,String> edit_payload = new HashMap<>();
			List<String> wbns = new ArrayList<>();
			wbns.add(waybill);
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				wbns.add(child_waybill);
			}
			apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
			
			Utilities.hardWait(10);
			
			//editing the return address
			edit_payload.put("return_add", "Palika bazar");
			
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
}
