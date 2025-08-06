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

public class ReturnInternalWeightServiceability9 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ReturnInternalWeightServiceability9() {
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
		
		if(data.containsKey("B2C")|| data.containsKey("Heavy")) {
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
	@DataProvider(name = "weight_updated_on_diff_state_shipments_B2C", parallel = false)
	public Object[][] weight_updated_on_diff_state_shipments_B2C() {
		return new Object[][] {
//			{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"Pending", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2C", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "X-DDD3FD", "UD", "Out for delivery", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "EOD-38", "DL", "Delivered to consignee", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null" },
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "RT-111", "DL", "DTO due to poor packaging", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
			{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"}
			};
		}
			
		@Test(dataProvider = "weight_updated_on_diff_state_shipments_B2C", enabled = true)
		public void verifyRCnWTUpdatedForDiffStateShipmentB2C(String Scenario, String type, String state, String cn,
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

			apiCtrl.QcWtApi(waybill, 16000.12, "Predicted_CTLG", clData);

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
		
		
		//Diff state -> DONE
		@DataProvider(name = "weight_updated_on_diff_state_shipments_B2C_No_Srv_Trigger", parallel = false)
		public Object[][] weight_updated_on_diff_state_shipments_B2C_No_Srv_Trigger() {
			return new Object[][] {
//				{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"Pending", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2C", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "X-DDD3FD", "UD", "Out for delivery", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "EOD-38", "DL", "Delivered to consignee", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//				{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null" },
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "RT-111", "DL", "DTO due to poor packaging", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
				{"Scenario:: B2C Package with weight less than 10 kg", "B2C" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2C", "Gurgaon_Kadipur (Haryana)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"}
				};
			}
				
			@Test(dataProvider = "weight_updated_on_diff_state_shipments_B2C_No_Srv_Trigger", enabled = true)
			public void weight_updated_on_diff_state_shipments_B2B_No_Srv_Trigger(String Scenario, String type, String state, String cn,
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
//				//Mark return
//				HashMap<String,String> edit_payload = new HashMap<>();
//				List<String> wbns = new ArrayList<>();
//				wbns.add(waybill);
//				apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
				

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
			//Diff state -> DONE
			@DataProvider(name = "weight_updated_on_diff_state_shipments_No_Srv_trigger", parallel = false)
			public Object[][] weight_updated_on_diff_state_shipments_No_Srv_trigger() {
				return new Object[][] {
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "W", "1028", "true", "null", "Mumbai", "null", "null", "B2B", "Mumbai MIDC (Maharashtra)", "null", "X-UCI", "UD", "Manifest uploaded", "Del_Okhla_PC (Delhi)", "null", "IND110044AAB", "\"Del_Okhla_PC (DELHI)\"", "\"IND110044AAB\"", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-DDD3FD", "UD", "Out for delivery", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND122001AAD", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-38", "DL", "Delivered to consignee", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "DTUP-231", "RT", "Center changed by system", "Del_B_RPC (Delhi)", "null", "IND110037AAB", "null", "null", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null" },
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-77", "PU", "Pickup completed", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-111", "DL", "DTO due to poor packaging", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "null", "Gurgaon", "null", "null", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null"}
					};
				}
					
				@Test(dataProvider = "weight_updated_on_diff_state_shipments_No_Srv_trigger", enabled = true)
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
//					//Mark return
//					HashMap<String,String> edit_payload = new HashMap<>();
//					List<String> wbns = new ArrayList<>();
//					wbns.add(waybill);
//					apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
					

					// Update weight
					Utilities.hardWait(15);

					apiCtrl.QcWtApi(waybill, 16000.12, "Predicted_CTLG", clData);

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
}