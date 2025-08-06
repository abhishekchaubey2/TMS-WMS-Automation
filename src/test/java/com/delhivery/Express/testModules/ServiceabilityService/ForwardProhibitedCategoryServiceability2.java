package com.delhivery.Express.testModules.ServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.Utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ForwardProhibitedCategoryServiceability2 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;
	private String waybill1 = "";

//	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();
	public LinkedHashMap<String, String> Expected_values;
	public HashMap<String, String> clData = new HashMap<>();
	public ForwardProhibitedCategoryServiceability2() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

	}
	
	
	public void FillExpectedValues(String cn, String cnid, String rgn, String sc, String srv, String ocid, 
			String cnc,String dpc, String wvcid, String cpdt, String cn1,String cs_ss,String pin,String rpin) {
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
		Expected_values.put("cs.ss", cs_ss);
		Expected_values.put("pin", pin);
		Expected_values.put("rpin", rpin);

	}
	
	@DataProvider(name = "prd_sent_as_prohibhited_in_Manifestation", parallel = false)
	public Object[][] prd_sent_as_prohibhited_in_Manifestation() {
		return new Object[][] { 
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "N","249",
				"true","IND110092AAB", null , "null" , "null" , "B2B", "NSZ","","",""},
				{ "Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD",
				"NSZ", "null", "N","249", "true","IND110092AAB", null , "null" , 
				"null" , "B2B" , "NSZ","","",""},
				{ "Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1" ,"NSZ", 
				"null", "N","249", "true","IND110092AAB", null , "null" , 
				"null" , "B2B", "NSZ","","",""},
				{ "Scenario:: B2C package", "B2C","NSZ", "null", "N","1209", "null",
				null, null , null , "" , "" , "NSZ","","",""},
				{ "Scenario:: B2B MPS", "B2B MPS","NSZ", "null", "N","249", "TRUE",
				"IND110092AAB", null , "null" , "null" , "B2B" , "NSZ","","",""},
				{ "Scenario:: Heavy package", "Heavy","NSZ", "null", "N","249", "TRUE",
				"IND110092AAB", null , "null" , "null" , "Heavy" , "NSZ" ,"","",""},
				{ "Scenario:: Heavy MPS", "Heavy MPS","NSZ", "null", "N","249", "TRUE",
				"IND110092AAB", null , "null" , "null" , "Heavy" , "NSZ","","",""},
				{ "Scenario:: B2C MPS", "B2C MPS", "NSZ", "null", "N","1209", "null","IND110092AAB", "null" 
				, "null" , "" , "" , "NSZ","","",""},
				{ "Scenario:: NO DATA ", "NO DATA", "NSZ", "null", "W","1028", "null","IND122001AAB", null 
					, "null" , "" , "" , "NSZ","","",""}};
	}

	@Test(dataProvider = "prd_sent_as_prohibhited_in_Manifestation" , enabled =true)
	public void VerifyCnAfterPrdSentAsProhibhitedInManifestation(String Scenario, String type , String cn , String cnid,
			String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1,String cs_ss,String pin,String rpin) {
		
		// Manifesting a package
		clData.put("client", "regression_client");
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

		Utilities.hardWait(25);

		pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		// Making a hashmap of Expected values to send in the assertion fn
		FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt, cn1,cs_ss,pin,rpin);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, Expected_values);

		if (!child_waybill.equalsIgnoreCase(waybill)) {
			PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, Expected_values);
		}

	}

	@DataProvider(name = "pseg_updated_to_prohibhited_and_address_updated_on_packages", parallel = false)
	public Object[][] pseg_updated_to_prohibhited_and_address_updated_on_packages() {
		return new Object[][] {
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "N","249","TRUE","IND110092AAB", null , "null" , "" , "B2B" , "NSZ","","",""},
			{ "Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B", "NSZ","","",""},
			{ "Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1" ,"NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B", "NSZ","","",""},
			{ "Scenario:: B2C package", "B2C","NSZ", "null", "N", "1209", "null", "IND110092AAB", "null", "null", "", "", "NSZ","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B" , "NSZ","","",""},
			{ "Scenario:: Heavy package", "Heavy","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "null", "Heavy" , "NSZ","","",""},
			{ "Scenario:: Heavy MPS", "Heavy MPS","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "null", "Heavy", "NSZ","","",""},
			{ "Scenario:: B2C MPS", "B2C MPS", "NSZ", "null", "N", "1209", "null", "IND110092AAB", "null", "null", "", "", "NSZ","","",""} };
	}

	@Test(dataProvider = "pseg_updated_to_prohibhited_and_address_updated_on_packages", enabled = true)
	public void VerifyCnAfterPrdUpdateAfterAddressUpdatedOnPackages(String Scenario, String type , String cn , 
			String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1,String cs_ss,String pin,String rpin) {
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

		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI",clData);
		if (type.contains("B2B") || type.contains("Heavy")) {
			Utilities.hardWait(70);
		}

		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		 ocid = pkgdetails.cs.slid;
		
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
			apiCtrl.verifyPackageFetchInfoApi(child_waybill, "UD", "In Transit", "X-PPOM", clData);
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
					assertKeyValue("prohibited", true, apiCtrl.fetchPackageInfo(child_waybill, clData)
							.pseg.prohibited);
				}
				
				Utilities.hardWait(30);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				String aseg_ud = pkgdetails.aseg.ud;
				// Making a hashmap of Expected values to send in the assertion fn
				FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cn1,cs_ss,pin,rpin);
				

				
////				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
//						Expected_values);
//				
//				if(!child_waybill.equalsIgnoreCase(waybill)) {
//					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
//					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
//							Expected_values);
//				}
				
				
				// editing add such that aseg callback would change the pin to a new pin
				HashMap<String, String> Edit_add = new HashMap<>();
				Edit_add.put("add", "Palika bazar");
				apiCtrl.EditApi(waybill, Edit_add);
				
				//asserting values
				Utilities.hardWait(30);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				
				//checking if new addfix callback came on package or not
				String new_aseg_ud = pkgdetails.aseg.ud;
				retry = 0;
				while (retry <= 2 && new_aseg_ud.equals(aseg_ud)) {
					logInfo("Callback of addfix with updated address not arrived on the package yet so retrying");
					Utilities.hardWait(15);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					new_aseg_ud = pkgdetails.aseg.ud;
					retry++;
				}
				
				//asserting srv keys
				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
						Expected_values);
				
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
							Expected_values);
				}
				
	}
	
	//add updated to test
	@DataProvider(name = "pseg_updated_to_prohibhited_and_address_updated_to_test", parallel = false)
	public Object[][] pseg_updated_to_prohibhited_and_address_updated_to_test() {
		return new Object[][] {
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "N","249","TRUE","IND110092AAB", null , "null" , "" , "B2B", "NSZ","","",""},
			{ "Scenario:: B2C package", "B2C","NSZ", "null", "N", "1209", "null", "IND110092AAB", "null", "null", "", "", "NSZ","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B" , "NSZ","","",""},
			{ "Scenario:: Heavy package", "Heavy","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "null", "Heavy" , "NSZ","","",""} };
	}

	@Test(dataProvider = "pseg_updated_to_prohibhited_and_address_updated_to_test", enabled = true)
	public void pseg_updated_to_prohibhited_and_address_updated_to_test(String Scenario, String type , String cn , 
			String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1 , String cs_ss, String pin, String rpin) {
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

		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI",clData);
		if (type.contains("B2B") || type.contains("Heavy")) {
			Utilities.hardWait(70);
		}

		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		 ocid = pkgdetails.cs.slid;
		
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
			apiCtrl.verifyPackageFetchInfoApi(child_waybill, "UD", "In Transit", "X-PPOM", clData);
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
					assertKeyValue("prohibited", true, apiCtrl.fetchPackageInfo(child_waybill, clData)
							.pseg.prohibited);
				}
				
				Utilities.hardWait(30);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				String aseg_ud = pkgdetails.aseg.ud;
				// Making a hashmap of Expected values to send in the assertion fn
				FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cn1,cs_ss,pin,rpin);
				

				
////				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
//						Expected_values);
//				
//				if(!child_waybill.equalsIgnoreCase(waybill)) {
//					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
//					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
//							Expected_values);
//				}
				
				
				// editing add such that aseg callback would change the pin to a new pin
				HashMap<String, String> Edit_add = new HashMap<>();
				Edit_add.put("add", "test");
				apiCtrl.EditApi(waybill, Edit_add);
				
				//asserting values
				Utilities.hardWait(30);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				
				//checking if new addfix callback came on package or not
				String new_aseg_ud = pkgdetails.aseg.ud;
				retry = 0;
				while (retry <= 2 && new_aseg_ud.equals(aseg_ud)) {
					logInfo("Callback of addfix with updated address not arrived on the package yet so retrying");
					Utilities.hardWait(15);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					new_aseg_ud = pkgdetails.aseg.ud;
					retry++;
				}
				
				//asserting srv keys
				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
						Expected_values);
				
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
							Expected_values);
				}
				
	}
	
	@DataProvider(name = "pseg_updated_to_prohibhited_and_pin_updated", parallel = false)
	public Object[][] pseg_updated_to_prohibhited_and_pin_updated() {
		return new Object[][] {
			{ "Scenario:: B2B package", "B2B" ,"NSZ", "null", "N","249","TRUE","IND110092AAB", null , "null" , "" , "B2B", "NSZ","","",""},
			{ "Scenario:: B2C package", "B2C","NSZ", "null", "N", "1209", "null", "IND110092AAB", "null", "null", "", "", "NSZ","","",""},
			{ "Scenario:: B2B MPS", "B2B MPS","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "", "B2B" , "NSZ","","",""},
			{ "Scenario:: Heavy package", "Heavy","NSZ", "null", "N", "249", "true", "IND110092AAB", "null", "null", "null", "Heavy" , "NSZ","","",""} };
	}

	@Test(dataProvider = "pseg_updated_to_prohibhited_and_pin_updated", enabled = true)
	public void pseg_updated_to_prohibhited_and_pin_updated(String Scenario, String type , String cn , 
			String cnid, String rgn , String sc, String srv , String ocid , String cnc , String dpc , String wvcid ,
			String cpdt, String cn1, String cs_ss, String pin, String rpin) {
		// Manifesting a package
		clData.put("client", "regression_client");
		clData.put("pin","122001");
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

		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI",clData);
		if (type.contains("B2B") || type.contains("Heavy")) {
			Utilities.hardWait(70);
		}

		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM", clData);
		PackageDetail pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
		 ocid = pkgdetails.cs.slid;
		
		//Performing FM of child package if present
		if (!child_waybill.equalsIgnoreCase(waybill)) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", clData);
			apiCtrl.verifyPackageFetchInfoApi(child_waybill, "UD", "In Transit", "X-PPOM", clData);
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
					assertKeyValue("prohibited", true, apiCtrl.fetchPackageInfo(child_waybill, clData)
							.pseg.prohibited);
				}
				
				Utilities.hardWait(30);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				String aseg_ud = pkgdetails.aseg.ud;
				// Making a hashmap of Expected values to send in the assertion fn
				FillExpectedValues(cn,cnid,rgn,sc,srv,ocid,cnc,dpc,wvcid,cpdt,cn1,cs_ss,pin,rpin);
				

				
////				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
//						Expected_values);
//				
//				if(!child_waybill.equalsIgnoreCase(waybill)) {
//					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
//					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
//							Expected_values);
//				}
				
				
				// editing add such that aseg callback would change the pin to a new pin
				HashMap<String, String> Edit_add = new HashMap<>();
				Edit_add.put("pincode", "122003");
				apiCtrl.EditApi(waybill, Edit_add);
				
				//asserting values
				Utilities.hardWait(30);

				pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
				
				//checking if new addfix callback came on package or not
				String new_aseg_ud = pkgdetails.aseg.ud;
				retry = 0;
				while (retry <= 2 && new_aseg_ud.equals(aseg_ud)) {
					logInfo("Callback of addfix with updated address not arrived on the package yet so retrying");
					Utilities.hardWait(15);
					pkgdetails = apiCtrl.fetchPackageInfo(waybill, clData);
					new_aseg_ud = pkgdetails.aseg.ud;
					retry++;
				}
				
				//asserting srv keys
				ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, 
						Expected_values);
				
				if(!child_waybill.equalsIgnoreCase(waybill)) {
					PackageDetail child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, clData);
					ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, 
							Expected_values);
				}
				
	}
}