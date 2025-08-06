package com.delhivery.Express.testModules.ServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ForwardProhibitedCategoryServiceability extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();
	public ForwardProhibitedCategoryServiceability() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

	}
	
	
	public void FillExpectedValues(String cn, String cnid, String rgn, String sc, String srv, String ocid, 
			String cnc,String dpc, String wvcid, String cpdt,String cs_ss,String pin,String rpin) {
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
		Expected_values.put("cs.ss", cs_ss);
		Expected_values.put("pin", pin);
		Expected_values.put("rpin", rpin);
	}

	@DataProvider(name = "prd_updated_on_package_from_false_to_true", parallel = false)
	public Object[][] prd_updated_on_package_from_false_to_true() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" , "NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B","","",""},
			{ "Scenario:: B2C package", "B2C" ,"NSZ", "null", "N", "1209", "null", "IND110092AAB", "null", "null", "", "","","",""} };
	}

	@Test(dataProvider = "prd_updated_on_package_from_false_to_true", enabled = true)
	public void VerifyCnAfterPrdUpdateFromFalseToTrue(String Scenario, String pdt , String cn , String cnid,
	String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
	String cpdt,String cs_ss,String pin,String rpin) {

		// Manifesting a package
		clData.put("client", "regression_client");
		HashMap<String, String> manifest_data = new HashMap<String, String>();
		manifest_data.put("product_type", pdt);

		List<String> waybills = new ArrayList<String>();
		waybills = apiCtrl.cmuManifestApi(manifest_data);
		waybill = waybills.get(0);

		logInfo("Waybill generated " + waybill);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI", clData);
		if (pdt.equals("B2B")) {
			Utilities.hardWait(70);
		}

		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK",clData);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ocid = pkgdetails.cs.slid;

		Utilities.hardWait(15);

		// checking if pseg key is present on the package or not
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		int i = 0;
		while (i <= 2 && pkgdetails.pseg == null) {
			logInfo("Pseg missing on package, retrying");
			Utilities.hardWait(20);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			i++;
		}

		// Stopping the test if pseg callback still mot arrived on package
		Assertions.assertNull("pseg", pkgdetails.pseg);

		// editing product_details of package
		HashMap<String, String> data = new HashMap<>();
		data.put("product_details", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");

		String Pseg_ud = pkgdetails.pseg.ud;
		logInfo("Pseg.ud is -> " + Pseg_ud);
		apiCtrl.EditApi(waybill, data);
		Utilities.hardWait(30);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack", pkgdetails.prd);

		// checking if new pseg callback came on the package or not
		String Pseg_ud_after_update = pkgdetails.pseg.ud;

		int retry = 0;
		while (retry <= 2 && Pseg_ud_after_update.equals(Pseg_ud)) {
			logInfo("Callback of updated prd not arrived on the package yet so retrying");
			Utilities.hardWait(20);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			Pseg_ud_after_update = pkgdetails.pseg.ud;
			retry++;
		}

		logInfo("Updated pseg ud is " + Pseg_ud_after_update);

		// Stopping the test if updated pseg callback did not get updated on the package
		Assertions.assertIfUpdated("ud", Pseg_ud, Pseg_ud_after_update);

		// checking if prohibited key is true or not in the new pseg callback
		assertKeyValue("prohibited", true, pkgdetails.pseg.prohibited);
		Utilities.hardWait(30);

		// asserting keys and thier values
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);

		// Making a hashmap of Expected values to send in the assertion fn
		FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cs_ss,pin,rpin);
	
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, Expected_values);

	}

	@DataProvider(name = "prd_updated_on_package_from_true_to_false", parallel = false)
	public Object[][] prd_updated_on_package_from_true_to_false() {
		return new Object[][] { { "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B","","",""},
				{ "Scenario:: B2C package with weight more than 10 kg", "B2C", "NSZ", "null", "N", "1209", "null", "IND110092AAB", "null", "null", "", "","","",""} };
	}

	@Test(dataProvider = "prd_updated_on_package_from_true_to_false", enabled = true)
	public void VerifyCnAfterPrdUpdateFromTrueToFalse(String Scenario, String pdt , String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt,String cs_ss,String pin,String rpin) {
		// Manifesting a package
		clData.put("client", "regression_client");
		HashMap<String, String> manifest_data = new HashMap<String, String>();
		manifest_data.put("product_type", pdt);

		List<String> waybills = new ArrayList<String>();
		waybills = apiCtrl.cmuManifestApi(manifest_data);
		waybill = waybills.get(0);

		logInfo("Waybill generated " + waybill);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI",clData);
		if (pdt.equals("B2B")) {
			Utilities.hardWait(70);
		}
		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
//		String ocid = pkgdetails.cs.slid;

		Utilities.hardWait(15);

		// checking if pseg key is present on the package or not
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		int i = 0;
		while (i <= 2 && pkgdetails.pseg == null) {
			logInfo("Pseg missing on package retrying");
			Utilities.hardWait(15);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			i++;
		}

		// Stopping the test if pseg callback still not arrived on package
		Assertions.assertNull("pseg", pkgdetails.pseg);


		// editing product_details of package with prohibhited product
		HashMap<String, String> data = new HashMap<>();
		data.put("product_details", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");

		String Pseg_ud = pkgdetails.pseg.ud;
		apiCtrl.EditApi(waybill, data);
		Utilities.hardWait(30);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack", pkgdetails.prd);

		// checking if new pseg callback came on the package or not
		String Pseg_ud_after_update = pkgdetails.pseg.ud;

		int retry = 0;
		while (retry <= 2 && Pseg_ud_after_update.equals(Pseg_ud)) {
			logInfo("Callback of updated prd not arrived on the package yet so retrying");
			Utilities.hardWait(20);
			retry++;
		}

		// Stopping the test if updated pseg callback did not get updated on the package
		Assertions.assertIfUpdated("ud", Pseg_ud, Pseg_ud_after_update);

		// checking if prohibited key is true or not in the new pseg callback
		assertKeyValue("prohibited", true, pkgdetails.pseg.prohibited);
		Utilities.hardWait(30);

		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		// Making a hashmap of Expected values to send in the assertion fn
		FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cs_ss,pin,rpin);
		
		
//		ServiceabilityKeysAssertions.AssertProhibhitedCategoryServiceabilityKeys(pkgdetails, Expected_values);
		// Now updating prd to a non prohibhited item
		Pseg_ud = pkgdetails.pseg.ud;
		data = new HashMap<>();
		data.put("product_details", "Mobile Cover");
		apiCtrl.EditApi(waybill, data);
		Utilities.hardWait(30);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		assertKeyValue("prd", "Mobile Cover", pkgdetails.prd);

		// checking if new pseg callback came on the package or not
		Pseg_ud_after_update = pkgdetails.pseg.ud;

		retry = 0;
		while (retry <= 2 && Pseg_ud_after_update.equals(Pseg_ud)) {
			logInfo("Callback of updated prd not arrived on the package yet so retrying");
			Utilities.hardWait(20);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			Pseg_ud_after_update = pkgdetails.pseg.ud;
			retry++;
		}

		// Stopping the test if updated pseg callback did not get updated on the package
		Assertions.assertIfUpdated("ud", Pseg_ud, Pseg_ud_after_update);

		// checking if prohibited key is false or not in the new pseg callback
		assertKeyValue("prohibited", false, pkgdetails.pseg.prohibited);

		Utilities.hardWait(30);

		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);

		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, Expected_values);

	}

	@DataProvider(name = "prd_updated_on_diff_state_shipments", parallel = false)
	public Object[][] prd_updated_on_diff_state_shipments() {
		return new Object[][] {
				{ "Scenario:: Manifest shipment prohibited gets true", "Manifest", "NSZ" , "null" , null , null , null , null, "DTUP-203", "UD", "Package details changed by shipper","","",""},
				{ "Scenario:: In Transit shipment prohibited gets true", "In Transit", "NSZ" , "null" , null , null , null , null, "S-MAR", "UD", "Air-restricted product. Move by surface","","",""},
				{ "Scenario:: Pending shipment prohibited gets true", "Pending", "NSZ" , "null" , null , null , null , null, "S-TAT2", "UD", "Moved via surface","","",""},
//				{ "Scenario:: Dispatched shipment prohibited gets true", "Dispatched", "Mumbai MIDC (Maharashtra)" , "IND400093AAA" , "null" , "null" , "Mumbai" , "null"},
//				{"Scenario:: Delivered shipment prohibited gets true", "Delivered", "Mumbai MIDC (Maharashtra)" , "IND400093AAA" , "null" , "null" , "Mumbai" , "null"},
				{"Scenario:: Returned shipment prohibited gets true", "Returned" , "NSZ" , "null" , "null" , "null" , "null" , "null", "S-MAR", "RT", "Air-restricted product. Move by surface","","",""},
//				{"Scenario:: RTO shipment prohibited gets true", "RTO", "Mumbai MIDC (Maharashtra)" , "IND400093AAA" , "null" , "null" , "Mumbai" , "null"},
//				{"Scenario:: LOST shipment prohibited gets true", "LOST", "Mumbai MIDC (Maharashtra)" , "IND400093AAA" , "null" , "null" , "Mumbai" , "null"},
				{"Scenario:: PickupPending shipment prohibited gets true", "PickupPending", "NSZ" , "null" , "null" , "null" , "null" , "null", "DTUP-203", "PP", "Package details changed by shipper","","",""},
//				{"Scenario:: PickedUp shipment prohibited gets true", "PickedUp", "Del_B_RPC (Delhi)" , "IND110037AAB" , "null" , "null" , "Delhi" , "null"},
//				{"Scenario:: DTO shipment prohibited gets true", "DTO", "Del_B_RPC (Delhi)" , "IND110037AAB" , "null" , "null" , "Delhi" , "null"},
//				{"Scenario:: Cancelled shipment prohibited gets true", "Cancelled" , "Del_B_RPC (Delhi)" , "IND110037AAB" , "null" , "null" , "Delhi" , "null"}
		};
	}

	@Test(dataProvider = "prd_updated_on_diff_state_shipments", enabled = true)
	public void verifyCnMarkedProhibitedForDiffStateShipment(String Scenario, String state,
		String expectedCn, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr ,String cs_ss,String pin,String rpin) {
		clData.put("client", "regression_client");
		waybill = diffStShpt.DifferentStateShipments(state,clData);
		logInfo("Waybill " + waybill);
		Utilities.hardWait(60);

		// checking if pseg key is present on the package or not
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);

		// Stopping the test if pseg callback still not arrived on package
		Assertions.assertNull("pseg", pkgdetails.pseg);

		// editing product_details of package with prohibhited product
		HashMap<String, String> data = new HashMap<>();
		data.put("product_details", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");

		String Pseg_ud = pkgdetails.pseg.ud;
		apiCtrl.EditApi(waybill, data);
		Utilities.hardWait(30);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack", pkgdetails.prd);

		// checking if new pseg callback came on the package or not
		String Pseg_ud_after_update = pkgdetails.pseg.ud;

		int retry = 0;
		while (retry <= 2 && pkgdetails.pseg.ud.equals(Pseg_ud)) {
			logInfo("Callback of updated prd not arrived on the package yet so retrying");
			Utilities.hardWait(15);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			retry++;
		}

		// Stopping the test if updated pseg callback did not get updated on the package
		Assertions.assertIfUpdated("ud", Pseg_ud, Pseg_ud_after_update);

		// checking if prohibited key is true or not in the new pseg callback
		assertKeyValue("prohibited", true, pkgdetails.pseg.prohibited);

		// Making a hashmap of Expected values to send in the assertion fn
		LinkedHashMap<String, String> Expected_values = new LinkedHashMap<>();
		Expected_values.put("cn", expectedCn);
		Expected_values.put("cnid", expectedCnid);
		Expected_values.put("dpc", expectedDpc);
		Expected_values.put("dpcid", expectedDpcid);
		Expected_values.put("cnc", expectedCnc);
		Expected_values.put("cns", expectedCns);
		Expected_values.put("cs.nsl", expectedCsNsl);
		Expected_values.put("cs.st", expectedCsSt);
		Expected_values.put("cs.sr", expectedCsSr);
		Expected_values.put("cs.ss", cs_ss);
		Expected_values.put("pin", pin);
		Expected_values.put("rpin", rpin);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, Expected_values);

	}

	@DataProvider(name = "pseg_updated_to_prohibhited_and_weight_sent_on_packages", parallel = false)
	public Object[][] pseg_updated_to_prohibhited_and_weight_sent_on_packages() {
		return new Object[][] {
			{ "Scenario:: B2B package with weight less than 10 kg", "B2B" ,"NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B","","",""},
			{ "Scenario:: B2C package with weight more than 10 kg", "B2C" ,"NSZ", "null", "N", "249", "null", "IND110092AAB", "null", "null", "", "Heavy","","",""},
			{ "Scenario:: B2B MPS WITH INTERNAL CHILD package with weight less than 10 kg","B2B MPS WITH INTERNAL CHILD" , "NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B","","",""},
			{ "Scenario:: B2B MPS WITH ONLY MASTER package with weight less than 10 kg", "MPS WITH MCOUNT 1", "NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B","","",""},
			{ "Scenario:: B2B MPS package with weight less than 10 kg", "B2B MPS","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B","","",""},
			{ "Scenario:: Heavy package with weight less than 10 kg", "Heavy", "NSZ", "null", "null", "249", "true", "IND110092AAB", "null", "null", "null", "Heavy","","",""},
			{ "Scenario:: Heavy MPS package with weight less than 10 kg", "Heavy MPS", "NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "null", "Heavy","","",""},
			{ "Scenario:: B2C MPS package with weight more than 10 kg", "B2C MPS", "NSZ", "null", "N", "249", "null", "IND110092AAB", "null", "null", "", "","","",""},
			{ "Scenario:: NO DATA with weight less than 10 kg", "NO DATA", "NSZ", "null", "W", "1028", "null", "IND122001AAB", "null", "null", "", "","","",""}
			};
	}

	@Test(dataProvider = "pseg_updated_to_prohibhited_and_weight_sent_on_packages" , enabled = true)
	public void VerifyCnAfterPrdUpdateWhileWeightUpdatedOnPackages(String Scenario, String type, String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt,String cs_ss,String pin,String rpin) {
		// Manifesting a package
		clData.put("client", "regression_client");
		System.out.println("Type of shipment -> "+ type);
		List<String> waybills = new ArrayList<String>();
		HashMap<String,String> Payload = new HashMap<>();
		waybills = diffTypeShipment.DifferentTypeShipments(type,Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);

		// setting the default value of child_waybill as master waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}

		if (type.contains("B2B") || type.contains("Heavy")) {
			Utilities.hardWait(30);
		}

		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		ocid = pkgdetails.cs.slid;
		
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
		}

		Utilities.hardWait(15);
		
		// checking if pseg key is present on the package or not
				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				int i = 0;
				while (i <= 2 && pkgdetails.pseg == null) {
					logInfo("Pseg missing on package retrying");
					Utilities.hardWait(15);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					i++;
				}

				// Stopping the test if pseg callback still mot arrived on package
				Assertions.assertNull("pseg", pkgdetails.pseg);
				
				//checking if child package has pseg or not
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					Assertions.assertNull("pseg", apiCtrl.
							fetchPackageInfo(child_waybill, clData).pseg);
				}
				
				
				// Update weight -> for master only
				if (Scenario.contains("weight less than 10 kg")) {
					apiCtrl.QcWtApi(waybill, 9999.12, "sorter", clData);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					double iwt = pkgdetails.intWt.wt;
					assertKeyValue("int_wt", 9999.12, iwt);
					Utilities.hardWait(40);
				} else if (Scenario.contains("weight more than 10 kg")) {
					apiCtrl.QcWtApi(waybill, 15000.12, "sorter", clData);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					double iwt = pkgdetails.intWt.wt;
					assertKeyValue("int_wt", 15000.12, iwt);
					Utilities.hardWait(40);
//					assertKeyValue("cpdt", "Heavy", pkgdetails.cpdt);
				}
				
				
				
				// editing product_details of package with prohibhited product
				HashMap<String, String> Edit_data = new HashMap<>();
				Edit_data.put("product_details", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");

				String Pseg_ud = pkgdetails.pseg.ud;
				apiCtrl.EditApi(waybill, Edit_data);
				Utilities.hardWait(30);
				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				
				//checking prd on master package
				logInfo("Checking prd on master package");
				assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack", 
						pkgdetails.prd);
				
				//checking prd of child waybill
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					logInfo("Checking prd on child package");
					assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack", 
							apiCtrl.fetchPackageInfo(child_waybill, clData).prd);
				}
				
				// checking if new pseg callback came on the package or not
				String Pseg_ud_after_update = pkgdetails.pseg.ud;

				int retry = 0;
				while (retry <= 2 && Pseg_ud_after_update.equals(Pseg_ud)) {
					logInfo("Callback of updated prd not arrived on the package yet so retrying");
					Utilities.hardWait(15);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					Pseg_ud_after_update = pkgdetails.pseg.ud;
					retry++;
				}
				
				// Stopping the test if updated pseg callback did not get updated on the package
				Assertions.assertIfUpdated("ud", Pseg_ud, Pseg_ud_after_update);
				
				// checking if prohibited key is true or not in the new pseg callback on master
				assertKeyValue("prohibited", true, pkgdetails.pseg.prohibited);
				
				//checking if prohibited key is true or not in the new pseg callback on chiild
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					assertKeyValue("prohibited", true, apiCtrl.fetchPackageInfo(child_waybill,clData)
							.pseg.prohibited);
				}
				
				Utilities.hardWait(10);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				// Making a hashmap of Expected values to send in the assertion fn
				FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cs_ss,pin,rpin);
				

				
				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
						Expected_values);
				
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
							Expected_values);
				}
				
	}
				
						
	
	@DataProvider(name = "EDT_VS_STAGING", parallel = false)
	public Object[][] edt_vs_staging() {
		return new Object[][] { 
			{ "Scenario:: B2C package ", "B2C"},
			{ "Scenario:: B2C MPS package ", "B2C MPS"},
			{ "Scenario:: B2B package ", "B2B"},
			{ "Scenario:: B2B MPS package ", "B2B MPS"},
			{ "Scenario:: HEAVY package ", "HEAVY"},
			{ "Scenario:: HEAVY MPS package ", "HEAVY MPS"}};
	}

	@Test(dataProvider = "EDT_VS_STAGING", enabled = false)
	public void EdtVsStaging(String Scenario, String type) {
		List<String> edt_waybills = new ArrayList<String>();
		List<String> staging_waybills = new ArrayList<String>();
		
		for (int i = 0; i < 2; i++) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> Payload = new HashMap<>();
			Payload.put("client", "regression_client");
			// creating shipment on edt
			if (i == 0) {
				Payload.put("enviorment", "edt");
			} else {
				Payload.put("enviorment", "staging");
			}
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
			apiCtrl.fmOMSApiDiffEnv(Payload.get("enviorment"), waybill, "FMPICK",Payload);

			// Performing FM of child package if present
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				apiCtrl.fmOMSApiDiffEnv(Payload.get("enviorment"), child_waybill, "FMPICK",Payload);
			}

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
			
			//Editing prd
			Payload.put("product_details", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");
			apiCtrl.EditApiDiffEnv(Payload.get("enviorment"), waybill, Payload);
			
			Utilities.hardWait(20);
			//checking value of prd on master
			assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack", 
					apiCtrl.fetchPackageInfoDiffEnv(Payload.get("enviorment"),waybill,Payload).prd);
			//checking value of prd on child
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				assertKeyValue("prd", "Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack",
						apiCtrl.fetchPackageInfoDiffEnv(Payload.get("enviorment"), child_waybill,Payload).prd);
			}
			
			
			if (i == 0) {
				edt_waybills = waybills;
			} else {
				staging_waybills = waybills;
			}
		}
		
		Utilities.hardWait(60);
		
		//call assertion function here
		HashMap<String,String> SPayload = new HashMap<>();
		SPayload.put("Client", "regression_client");
		PackageDetail edtMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(0),SPayload);
		PackageDetail stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(0),SPayload);
		
		try {
			ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingMasterShipmentDetails, edtMasterShipmentDetails);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(Scenario.contains("MPS")) {
			
			PackageDetail edtChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(1),SPayload);
			PackageDetail stagingChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(1),SPayload);
			try {
				ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingChildShipmentDetails, edtChildShipmentDetails);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Restrict Mapper Case
	//This case is not required in this template
	@DataProvider(name = "Restricted_Mapper_Client", parallel = false)
	public Object[][] Restricted_Mapper_Client() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "NSZ", "N","249",
				"true","IND110092AAB", null , "Gurgaon" , "null" , "B2B","","",""},
				{ "Scenario:: B2C package", "B2C","NSZ", "NSZ", "","1209", "null",
				null, null , null , "" , "","","",""},
				{ "Scenario:: B2B MPS", "B2B MPS","NSZ", "NSZ", "N","249", "TRUE",
				"IND110092AAB", null , "null" , "null" , "B2B"   },
				{ "Scenario:: Heavy package", "Heavy","NSZ", "NSZ", "N","249", "TRUE",
				"IND110092AAB", null , "null" , "null" , "Heavy","","",""}};
	}

	@Test(dataProvider = "Restricted_Mapper_Client" , enabled =false)
	public void Restricted_Mapper_Client(String Scenario, String type , String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt,String cs_ss,String pin,String rpin) {
		
		// Manifesting a package
		clData.put("client", "restrict_mapper_callback_client");
		List<String> waybills = new ArrayList<String>();
		HashMap<String,String> Payload = new HashMap<>();
		Payload.put("products_desc","Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);

		// setting the default value of child_waybill as master waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}

		if (type.contains("B2B") || type.contains("Heavy")) {
			Utilities.hardWait(20);
		}
		
		Utilities.hardWait(10);
//		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		 ocid = pkgdetails.cs.slid;

//		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK" , clData);
		}
//
		Utilities.hardWait(10);

		// checking if pseg key is present on the package or not
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		int i = 0;
		while (i <= 2 && pkgdetails.pseg == null) {
			logInfo("Pseg missing on package retrying");
			Utilities.hardWait(10);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			i++;
		}

		// Stopping the test if pseg callback still not arrived on package
		Assertions.assertNull("pseg", pkgdetails.pseg);

		// checking if child package has pseg or not
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			Assertions.assertNull("pseg", apiCtrl.fetchPackageInfo(child_waybill, clData).pseg);
		}


		// checking prd on master package
		logInfo("Checking prd on master package");
		assertKeyValue("prd", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack", pkgdetails.prd);

		// checking prd of child waybill
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			logInfo("Checking prd on child package");
			assertKeyValue("prd", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack",
					apiCtrl.fetchPackageInfo(child_waybill, clData).prd);
		}

		// checking if prohibited key is true or not in the new pseg callback on master
		assertKeyValue("prohibited", true, pkgdetails.pseg.prohibited);

		// checking if prohibited key is true or not in the new pseg callback on child
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			assertKeyValue("prohibited", true, apiCtrl.fetchPackageInfo(child_waybill, clData).pseg.prohibited);
		}

		Utilities.hardWait(20);

		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		// Making a hashmap of Expected values to send in the assertion fn
		FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cs_ss,pin,rpin);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, Expected_values);

		if (!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, Expected_values);
		}

	}
	

	
	//Different payment mode
	@DataProvider(name = "Diff_payment_mode", parallel = false)
	public Object[][] Diff_payment_mode() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"COD","NSZ", "null", "N","249","true","IND122001AAD", null , "null" , "null" , "B2B","","",""},
			{ "Scenario:: B2B package", "B2B" ,"Pre-paid","NSZ", "null", "N","249","true","IND122001AAD", null , "null" , "null" , "B2B","","",""},
			{ "Scenario:: B2B package", "B2B" ,"Pickup","NSZ", "null", "N","249","true","null", null , "null" , "null" , "B2B","","",""},
			{ "Scenario:: B2B package", "B2B" ,"Cash","NSZ", "null", "N","249","true","null", null , "null" , "null" , "B2B","","",""},
			{ "Scenario:: B2B package", "B2B" ,"REPL","NSZ", "null", "N","249","true","IND122001AAD", null , "null" , "null" , "B2B","","",""}
			};
	}

	@Test(dataProvider = "Diff_payment_mode" , enabled =false)
	public void Diff_payment_mode(String Scenario, String type , String payment_mode ,String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt,String cs_ss,String pin,String rpin) {
		
		// Manifesting a package
		clData.put("client", "regression_client");
		List<String> waybills = new ArrayList<String>();
		HashMap<String,String> Payload = new HashMap<>();
		Payload.put("products_desc","Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack");
		Payload.put("payment_mode", payment_mode);
		waybills = diffTypeShipment.DifferentTypeShipments(type, Payload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);

		// setting the default value of child_waybill as master waybill
		String child_waybill = waybill;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}

		if (type.contains("B2B") || type.contains("Heavy")) {
			Utilities.hardWait(20);
		}
		
		Utilities.hardWait(10);
//		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		 ocid = pkgdetails.cs.slid;

//		// Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK" , clData);
		}
//
		Utilities.hardWait(10);

		// checking if pseg key is present on the package or not
		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		int i = 0;
		while (i <= 2 && pkgdetails.pseg == null) {
			logInfo("Pseg missing on package retrying");
			Utilities.hardWait(10);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
			i++;
		}

		// Stopping the test if pseg callback still not arrived on package
		Assertions.assertNull("pseg", pkgdetails.pseg);

		// checking if child package has pseg or not
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			Assertions.assertNull("pseg", apiCtrl.fetchPackageInfo(child_waybill, clData).pseg);
		}


		// checking prd on master package
		logInfo("Checking prd on master package");
		assertKeyValue("prd", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack", pkgdetails.prd);

		// checking prd of child waybill
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			logInfo("Checking prd on child package");
			assertKeyValue("prd", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack",
					apiCtrl.fetchPackageInfo(child_waybill, clData).prd);
		}

		// checking if prohibited key is true or not in the new pseg callback on master
		assertKeyValue("prohibited", true, pkgdetails.pseg.prohibited);

		// checking if prohibited key is true or not in the new pseg callback on child
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			assertKeyValue("prohibited", true, apiCtrl.fetchPackageInfo(child_waybill, clData).pseg.prohibited);
		}

		Utilities.hardWait(20);

		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		// Making a hashmap of Expected values to send in the assertion fn
		FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cs_ss,pin,rpin);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, Expected_values);

		if (!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, Expected_values);
		}

	}

}