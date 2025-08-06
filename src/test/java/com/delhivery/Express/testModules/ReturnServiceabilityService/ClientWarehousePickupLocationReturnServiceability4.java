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
import com.fasterxml.jackson.core.JsonProcessingException;

public class ClientWarehousePickupLocationReturnServiceability4 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ClientWarehousePickupLocationReturnServiceability4() {
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
			Utilities.hardWait(120);
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
		
		//radd updated such that addfix suggest no new pin
		//radd update
		@DataProvider(name = "Return_address_updated_to_test", parallel = false)
		public Object[][] Return_address_updated_to_test() {
			return new Object[][] { 
				{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "IND122001AAB", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C package", "B2C WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "IND122001AAB", "B2B", "GGN_DPC (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "IND122001AAB", "", "Gurgaon (Haryana)", "null", "DTUP-203", "RT", "Package details changed by shipper", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},

				};
		}
		
		@Test(dataProvider = "Return_address_updated_to_test" , enabled = true)
		public void VerifyCWHPLServiceabilityRaddUpdatedToTest(String Scenario, String type, String cn , String cnid,
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
				Utilities.hardWait(30);
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
			edit_payload.put("return_add", "Test");
			apiCtrl.EditApi(waybill, edit_payload);
			
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
		
		//Mapper restricted client
		//This case is not required for this template
		@DataProvider(name = "Mapper_Restricted_Client", parallel = false)
		public Object[][] Mapper_Restricted_Client() {
			return new Object[][] { 
				{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C package", "B2C WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},

				};
		}
		
		@Test(dataProvider = "Mapper_Restricted_Client" , enabled = false)
		public void VerifyCWHPLServiceabilityMapperRestrict(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
				
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			//Make payload that is essential for populating ewaybill
			clData.put("client", "restrict_mapper_callback_client");
			Payload.put("client", "restrict_mapper_callback_client");
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
			List<String> wbns = new ArrayList<>();
			wbns.add(waybill);
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				wbns.add(child_waybill);
			}
			apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);

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
		
		//Edt vs staging
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
				Payload.put("pickup_location", "HQAPIREGRESSION");
				Payload.put("cwh", "HQAPIREGRESSION");
				Payload.put("return_pin", "");
				Payload.put("return_add", "");
				Payload.put("return_city", "");
				Payload.put("return_state", "");
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
		
		//Diff state nsz
		//As this Template requires us to send no rpin and radd so raseg won't give the desired pin even if we update the return address
		@DataProvider(name = "raseg_updates_nsz_rcn_diff_state_shipments", parallel = false)
		public Object[][] raseg_updates_nsz_rcn_diff_state_shipments() {
			return new Object[][] {
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Pending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-PIOM", "UD", "Shipment Recieved at Origin Center","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Dispatched", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-DDD3FD", "UD", "Out for delivery","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Delivered", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND400093AAA", "Gurgaon", null, "", "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-38", "DL", "Delivered to consignee","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "Returned" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B","RTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "RT-110", "DL", "RTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null" },
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" , "LOST", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "Gurgaon_Kadipur (Haryana)", "null", "LT-100", "LT", "Shipment LOST" ,"Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"PickupPending", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "X-ASP", "PP", "Pickup scheduled","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "PickedUp", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-77", "PU", "Pickup completed","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B", "DTO", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "RT-111", "DL", "DTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2B Package with weight less than 10 kg", "B2B" ,"Cancelled" , "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "N", "249", "true", null, "Gurgaon", null, null, "B2B", "Gurgaon_Kadipur (Haryana)", null, "EOD-108", "CN", "QC failed at pickup. Product count mismatch","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Pending", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Dispatched", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "X-DDD3FD", "UD", "Out for delivery","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Delivered", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND400093AAA", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "EOD-38", "DL", "Delivered to consignee","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "Returned" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-101", "RT", "Returned as per Client Instructions","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C","RTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "RT-110", "DL", "RTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" , "LOST", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "IND110092AAB", "Delhi", "null", "", "", "Del_B_RPC (Delhi)", "null", "LT-100", "LT", "Shipment LOST","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"PickupPending", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "X-ASP", "PP", "Pickup scheduled","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "PickedUp", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-77", "PU", "Pickup completed","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C", "DTO", "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "RT-111", "DL", "DTO due to poor packaging","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
					{"Scenario:: B2C Package with weight more than 10 kg", "B2C" ,"Cancelled" , "Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "null", "null", "Delhi", "null", "null", "", "Del_B_RPC (Delhi)", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"}
			};
		}
		
		@Test(dataProvider = "raseg_updates_nsz_rcn_diff_state_shipments", enabled = false)
		public void verifyRcnaddUpdatedtoNSZForDiffStateShipment(String Scenario, String type,String state,
				String cn , String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			clData = new HashMap<String,String>();
			clData.put("client", "regression_client");
			clData.put("pickup_location", "HQAPIREGRESSION");
			clData.put("cwh", "HQAPIREGRESSION");
			clData.put("cwh_sent", "true");
			clData.put("product_type", type);
			clData.put("return_add", "");
			clData.put("return_city", "");
			clData.put("return_state", "");
			clData.put("return_pin", "");
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
			clData.put("return_add", "Jyoti Kunj");//This radd is supposed to return rpin = 122011 for which rcn = NSZ
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
		
		//We cannot make a cwh with RTO and DTO set as NSZ or with invalid cn/rcn so NSZ rcn cases are commented
		//Also we annot make no data cwh as manifestation API gives error "ClientWarehouse matching query does not exist."
		@DataProvider(name = "Wrong_rcn_cases", parallel = false)
		public Object[][] Wrong_rcn_cases() {
			return new Object[][] { 
				{ "Scenario:: B2B package with no data exist", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{ "Scenario:: B2B package with invalid cn", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{ "Scenario:: B2B package with NSZ cn", "B2B" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				{ "Scenario:: B2C package with no data exist", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{ "Scenario:: B2C package with invalid cn", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
//				{ "Scenario:: B2C package with NSZ cn", "B2C" ,"Del_B_RPC (Delhi)", "IND110037AAB", "", "1209", "true", "IND110092AAB", "Delhi", "null", "", "B2C", "Del_B_RPC (Delhi)", "null", "DTUP-231", "UD", "Center changed by system","Del_Okhla_PC (Delhi)", "Del_Okhla_PC (Delhi)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null"},
				};
		}
		
		@Test(dataProvider = "Wrong_rcn_cases" , enabled = false)
		public void VerifyServiceabilityTriggerWrongCN(String Scenario, String type, String cn , String cnid,
				String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
				String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			clData = new HashMap<>();
			clData.put("client", "regression_client");
			clData.put("pickup_location", "HQAPIREGRESSION");
			clData.put("cwh", "HQAPIREGRESSION");
			clData.put("cwh_sent", "true");
			
			if(Scenario.contains("NSZ cn")) {
//				clData.put("pin", "110033");
			}else if(Scenario.contains("no data exist")) {
				clData.put("pickup_location", "THISWAREHOUSEDONOTEXIST");
				clData.put("cwh", "THISWAREHOUSEDONOTEXIST");
			}else {
				//invalid cn
//				clData.put("pin", "133101");
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
			
			
			
			
			Utilities.hardWait(20);
			
			
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
