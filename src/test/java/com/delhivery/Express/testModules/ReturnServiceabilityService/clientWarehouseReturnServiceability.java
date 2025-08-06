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

public class clientWarehouseReturnServiceability extends BaseTest{
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();

	public clientWarehouseReturnServiceability() {
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
	
	@DataProvider(name = "return_address_set_as_cwh", parallel = false)
	public Object[][] return_address_set_as_cwh() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS WITH CWH" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND122413AAD", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not received from Client", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "null", "null"},
			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND122413AAD", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "return_address_set_as_cwh" , enabled = true)
	public void VerifyCWHServiceability(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "return_address_client_warehouse");
		Payload.put("client", "return_address_client_warehouse");
		Payload.put("pin","122001");
		Payload.put("pickup_location", "HQAPIREGRESSIONCWH");
		Payload.put("cwh", "HQAPIREGRESSIONCWH");
		Payload.put("cwh_sent", "");
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
	
	@DataProvider(name = "package_returned_while_return_address_set_as_cwh", parallel = false)
	public Object[][] package_returned_while_return_address_set_as_cwh() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "null", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS WITH CWH" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND122413AAD", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND122413AAD", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "X-UCI", "UD", "Manifest uploaded", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "null", "null"},
			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND122413AAD", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-SPM", "UD", "Shipment partially manifested", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "null", "null"}
			};
	}
	
	@Test(dataProvider = "package_returned_while_return_address_set_as_cwh" , enabled = true)
	public void VerifyCWHServiceabilityRtState(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
		clData.put("client", "return_address_client_warehouse");
		Payload.put("client", "return_address_client_warehouse");
		Payload.put("pin","122001");
		Payload.put("pickup_location", "HQAPIREGRESSIONCWH");
		Payload.put("cwh", "HQAPIREGRESSIONCWH");
		Payload.put("cwh_sent", "");
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
		
		//Returning the package
		List<String> wbns = new ArrayList<>();
		wbns.add(waybill);
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			wbns.add(child_waybill);
		}
		
		if(!type.equalsIgnoreCase("PARTIALLY MANIFESTED")) {
			//Partially Manifested shipments cannot be returned
			apiCtrl.ApplyNsl(wbns, "RT", "RT-101",clData);
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
	
	@DataProvider(name = "weight_passed_while_return_address_set_as_cwh", parallel = false)
	public Object[][] weight_passed_while_return_address_set_as_cwh() {
		return new Object[][] { 
			{ "Scenario:: B2B package ", "B2B WITH CWH" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C package", "B2C WITH CWH" ,  "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy package", "HEAVY WITH CWH" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with internal child", "B2B MPS WITH INTERNAL CHILD" , "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS with Master Package", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS with Master Package only", "B2C MPS WITH MCOUNT" ,"Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2B MPS", "B2B MPS WITH CWH" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "", "B2B", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: B2C MPS", "B2C MPS WITH CWH" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND110092AAB", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: Heavy MPS", "HEAVY MPS WITH CWH" ,  "GGN_DPC (Haryana)", "IND122001AAA", "N", "|dffdf448", "true", "IND110092AAB", "Gurgaon", "null", "null", "Heavy", "GGN_DPC (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
			{ "Scenario:: NO DATA", "NO DATA" , "Gurgaon (Haryana)", "IND122001AAB", "N", "1209", "null", "IND122413AAD", "Gurgaon", "null", "", "", "Gurgaon (Haryana)", "null", "RT-101", "RT", "Returned as per Client Instructions", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "Gurgaon", "null"},
//			{ "Scenario:: Partially Manifested package", "PARTIALLY MANIFESTED" ,"Gurgaon (Haryana)", "IND122001AAB", "null", "1209", "null", "IND110092AAB", "null", "null", "", "null", "Gurgaon (Haryana)", "null", "X-PIBM", "UD", "Shipment Received at Origin Center - Manifestation Pending", "Gurgaon_Central1_D (Haryana)", "Gurgaon_Central1_D (Haryana)", "IND122001B1A", "null", "null", "null", "null"}
			};
	}
	
	//Weight passed
	@Test(dataProvider = "weight_passed_while_return_address_set_as_cwh" , enabled = true)
	public void VerifyCWHServiceabilityWTPassed(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String dpcid,String cs_nsl,String cs_st,String cs_sr, String rcn , String rcn1, String rcnid ,String rdpc , String rdpcid, String rcty , String rcns) {
			
		List<String> waybills = new ArrayList<String>();
		HashMap<String, String> Payload = new HashMap<>();
		clData = new HashMap<>();
		//Make payload that is essential for populating ewaybill
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
		
		//Returning Package
		MarkReturnAfterGI(waybill, child_waybill, clData);
		Utilities.hardWait(10);
		
		//Updating weight
		if(Scenario.contains("B2C")|| Scenario.contains("NO DATA") || Scenario.contains("PARTIALLY MANIFESTED")) {
			apiCtrl.QcWtApi(waybill, 15000.12, "Predicted_CTLG", clData);
		}else {
			apiCtrl.QcWtApi(waybill, 14000.12, "Predicted_CTLG", clData);
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

}
