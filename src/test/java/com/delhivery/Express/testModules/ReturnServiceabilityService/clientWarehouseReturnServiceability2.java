package com.delhivery.Express.testModules.ReturnServiceabilityService;

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

public class clientWarehouseReturnServiceability2 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public clientWarehouseReturnServiceability2() {
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
	
	@DataProvider(name = "weight_updated_on_diff_state_shipments", parallel = false)
	public Object[][] weight_updated_on_diff_state_shipments() {
		return new Object[][] {
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAA", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAA", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-DDD3FD", "UD", "Out for delivery", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAA", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "EOD-38", "DL", "Delivered to consignee", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "RTO", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"LOST", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "LT-100", "LT", "Shipment LOST", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_CyberHLD_D (Haryana)", "Gurgaon_CyberHLD_D (Haryana)", "IND122001AAH", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon_CyberHLD_D (Haryana)", "Gurgaon_CyberHLD_D (Haryana)", "IND122001AAH", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-111", "DL", "DTO due to poor packaging", "Gurgaon_CyberHLD_D (Haryana)", "Gurgaon_CyberHLD_D (Haryana)", "IND122001AAH", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "Gurgaon_CyberHLD_D (Haryana)", "Gurgaon_CyberHLD_D (Haryana)", "IND122001AAH", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"REPL RETURNED" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "EOD-77", "RT", "Pickup completed", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"REPL PICKUP" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"}
			};
		}
			
		@Test(dataProvider = "weight_updated_on_diff_state_shipments", enabled = true)
		public void verifyRCnWTUpdatedForDiffStateShipment(String Scenario, String type, String state, String cn,
				String cnid, String rgn, String sc, String srv, String ocid, String cnc, String dpc, String wvcid,
				String cpdt, String cn1, String dpcid, String cs_nsl, String cs_st, String cs_sr, String rcn,
				String rcn1, String rcnid, String rdpc, String rdpcid, String rcty, String rcns) {
			clData = new HashMap<String, String>();
			clData.put("client", "return_address_client_warehouse");
			clData.put("pin","122001");
			clData.put("pickup_location", "HQAPIREGRESSIONCWH");
			clData.put("cwh", "HQAPIREGRESSIONCWH");
			clData.put("cwh_sent", "");
			clData.put("return_pin", "122003");
			clData.put("return_add", "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas");
			clData.put("return_city", "Gurgaon");
			clData.put("return_state", "Haryana");
			clData.put("product_type", type);
			clData.put("return_pin", "122003");
			clData.put("return_add", "Test");
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
		
		//radd updated such that addfix suggest no new pin
		@DataProvider(name = "Return_address_updated_to_test", parallel = false)
		public Object[][] Return_address_updated_to_test() {
			return new Object[][] { 
				{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C package", "B2C WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},

				};
		}
		
		@Test(dataProvider = "Return_address_updated_to_test" , enabled = true)
		public void VerifyCWHPLServiceabilityRaddUpdatedToTest(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential
			clData.put("client", "return_address_client_warehouse");
			Payload.put("client", "return_address_client_warehouse");
			Payload.put("pin","122001");
			Payload.put("pickup_location", "HQAPIREGRESSIONCWH");
			Payload.put("cwh", "HQAPIREGRESSIONCWH");
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
			
			//Returning the package
			List<String> wbns = new ArrayList<>();
			wbns.add(waybill);
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				wbns.add(child_waybill);
			}
			apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
			
			Utilities.hardWait(10);
			
			//editing the return address
			Payload.put("return_add", "Test");
			apiCtrl.EditApi(waybill, Payload);
			Utilities.hardWait(40);
			
			
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
