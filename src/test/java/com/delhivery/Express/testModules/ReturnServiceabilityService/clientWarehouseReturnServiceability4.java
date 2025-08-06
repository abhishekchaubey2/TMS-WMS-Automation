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
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

public class clientWarehouseReturnServiceability4 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public clientWarehouseReturnServiceability4() {
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
		
		
		//Different payment mode 
		@DataProvider(name = "Different_payment_mode", parallel = false)
		public Object[][] Different_payment_mode() {
			return new Object[][] { 
				{ "Scenario:: B2B package ", "B2B" ,"COD", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"Pre-paid", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"Pickup", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAA", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-ASP", "PP", "Pickup scheduled", "Gurgaon_CyberHLD_D (Haryana)", "Gurgaon_CyberHLD_D (Haryana)", "IND122001AAH", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"Cash", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "PP", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				{ "Scenario:: B2B package ", "B2B" ,"REPL", "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
				};
		}
		
		@Test(dataProvider = "Different_payment_mode" , enabled = true)
		public void VerifyCWHPLServiceabilityDIffPayment(String Scenario, String type, String payment_mode,String cn , String cnid,
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
			Payload.put("cwh_sent", "true");
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

		@Test(dataProvider = "EDT_VS_STAGING", enabled = true)
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
				
				Payload.put("client", "return_address_client_warehouse");
				Payload.put("pin", "122001");
				Payload.put("pickup_location", "HQAPIREGRESSION");
				Payload.put("cwh", "HQAPIREGRESSION");
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
			Payload1.put("client", "return_address_client_warehouse");
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
