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
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class ClientWarehousePickupLocationReturnServiceability3 extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public ClientWarehousePickupLocationReturnServiceability3() {
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
	
	//provide rpin
	//as radd is empty rpin will be null in these cases
	@DataProvider(name = "Rpin_passed", parallel = false)
	public Object[][] Rpin_passed() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "null", "B2B", "NSZ", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "null", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
			{ "Scenario:: Heavy package", "HEAVY" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
//			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "null", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122001AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "null", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "null", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "null", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "null", "null", "null", "null", "null", "null", "null"},
//			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "", "", "null", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" , "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND122001AAB", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Rpin_passed" , enabled = true)
	public void VerifyCWHPLServiceabilityWithRpinPassed(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "regression_client");
		Payload.put("client", "regression_client");
		Payload.put("pin","122001");
//		Payload.put("pickup_location", "HQAPIREGRESSION");
//		Payload.put("cwh", "HQAPIREGRESSION");
		Payload.put("return_pin", "122003");
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
		HashMap<String,String> edit_payload = new HashMap<>();
		List<String> wbns = new ArrayList<>();
		wbns.add(waybill);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			wbns.add(child_waybill);
		}
		apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
		
		Utilities.hardWait(70);
		
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
	
	//change cwh while FM
	//provide rpin
	@DataProvider(name = "Cwh_changed_while_FM", parallel = false)
	public Object[][] Cwh_changed_while_FM() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2C package", "B2C" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: Heavy package", "HEAVY" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS" ,  "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "Gurggaon", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122001AAB", "Gurgaon", "null", "", "", "Mumbai MIDC (Maharashtra)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,  "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110092AAB", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PIBM", "UD", "Shipment Received at Origin Center - Manifestation Pending", "OK_RPC (Delhi)", "OK_RPC (Delhi)", "IND110020AAB", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "Cwh_changed_while_FM" , enabled = true)
	public void VerifyCWHPLServiceabilityWithCWHChanged(String Scenario, String type, String cn , String cnid,
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
		clData.put("cwh_sent", "true");
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
		MarkReturnAfterGI(waybill, child_waybill, clData);
		
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
