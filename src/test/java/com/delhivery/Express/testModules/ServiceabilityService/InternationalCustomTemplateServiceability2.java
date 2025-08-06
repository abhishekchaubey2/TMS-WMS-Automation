package com.delhivery.Express.testModules.ServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

public class InternationalCustomTemplateServiceability2 extends BaseTest {
	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();

	protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
	protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


	String expectedCn, expectedCn1, expectedCnid, expectedDpc, expectedDpcid, expectedCnc, expectedCns, expectedCsNsl, expectedCsSt, expectedCsSr, expectedCpdt, expectedRgn, expectedSc, expectedSrv, expectedOcid, expectedWvcid = "";


	public InternationalCustomTemplateServiceability2() {
//		client = HQAPIREGRESSION SRV;
//		b2c_template	football
//		b2b_template	e2e
//		InternationalLightCenter	Rudrapur_UdhamNgr_L
//		InternationalHeavyCenter	Del_Okhla_PC

	}

	@DataProvider(name = "international_shipment_Manifestation_with_diff_pin_data", parallel = false)
	public Object[][] international_shipment_Manifestation_with_diff_pin_data() {
		return new Object[][]{
				{ "Scenario:: B2C package with pin having custom center as invalid", "B2C", "122100", "GGN_Gateway (Haryana)", "GGN_Gateway (Haryana)", "IND122413AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "573", "null", "null", "null"},
				{ "Scenario:: B2C package with pin having custom center as NSZ", "B2C", "122101", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "", "null", "null", "null", "null", "null"},
				{ "Scenario:: B2C package with pin having custom center as valid", "B2C", "122001", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
				{ "Scenario:: B2B package with pin having custom center as invalid", "B2B", "122100", "NSZ", "null", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "null", "null", "true", "null", "null"},
				{ "Scenario:: B2B package with pin having custom center as NSZ", "B2B", "122101", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "null", "null", "true", "null", "null"},
				{ "Scenario:: B2B package with pin having custom center as valid", "B2B", "122001", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"}
		};
	}

	@Test(dataProvider = "international_shipment_Manifestation_with_diff_pin_data", enabled = true)
	public void verifyCnForCustomInternationShipmentManifestationWithDiffPinData(String Scenario, String productType, String pin, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
			, String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

		// Manifesting a package
		List<String> waybills = new ArrayList<String>();
		String waybill, child_waybill = "";
		PackageDetail pkgdetails, child_pkgDetails = null;
		LinkedHashMap<String, String> expected_values;
		HashMap requestPayload = new HashMap<String, String>();
		requestPayload.put("client","custom_srv_client");
		requestPayload.put("international", "true");
		if (Scenario.contains("MPS")) {
			requestPayload.put("master_id", Utilities.getUniqueInt(15));
		}
		if (!Scenario.contains("ONLY MASTER")) {
			requestPayload.put("waybill", Utilities.getUniqueInt(15));
		}
		requestPayload.put("pin", pin);
		waybills = diffTypeShipment.DifferentTypeShipments(productType, requestPayload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);

		// setting the default value of child_waybill as master waybill
		child_waybill = null;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}

		if (!productType.contains("B2C")) {
			Utilities.hardWait(80);
		} else {
			Utilities.hardWait(5);
		}

		// checking prd on master package
		logInfo("Checking i11l on package");
		pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
		assertKeyValue("i11l", "true", pkgdetails.i11l);

		// checking prd of child waybill
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			logInfo("Checking i11l on child package");
			child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
			assertKeyValue("i11l", "true", child_pkgDetails.i11l);
		}

		// Making a hashmap of Expected values to send in the assertion fnc
		expected_values = new LinkedHashMap<String, String>();
		expected_values.put("cn", expectedCn);
		expected_values.put("cn1", expectedCn1);
		expected_values.put("cnid", expectedCnid);
		expected_values.put("dpc", expectedDpc);
		expected_values.put("dpcid", expectedDpcid);
		expected_values.put("cnc", expectedCnc);
		expected_values.put("cns", expectedCns);
		expected_values.put("cs.nsl", expectedCsNsl);
		expected_values.put("cs.st", expectedCsSt);
		expected_values.put("cs.sr", expectedCsSr);
		expected_values.put("cpdt", expectedCpdt);
		expected_values.put("rgn", expectedRgn);
		expected_values.put("sc", expectedSc);
		expected_values.put("srv", expectedSrv);
		expected_values.put("ocid", expectedOcid);
		expected_values.put("wvcid", expectedWvcid);

		Utilities.hardWait(keyassertPkgInfoFetchDelayTime);
		logInfo("Checking package keys :: "+waybill);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			logInfo("Checking child package keys :: "+child_waybill);
			child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
		}

	}


	@DataProvider(name = "international_custom_shipment_pin_updated_from_addfix_callback", parallel = false)
	public Object[][] international_custom_shipment_pin_updated_from_addfix_callback() {
		return new Object[][] {
				{ "Scenario:: B2C package with weight more than 10 kg and then addfix callback update pin", "B2C", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
				{ "Scenario:: B2B package with weight less than 10 kg and then addfix callback update pin", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
				{ "Scenario:: B2C MPS package with weight more than 10 kg and then addfix callback update pin", "B2C MPS SHIPMENT WITH CHILD PAYLOAD", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
				{ "Scenario:: B2B MPS with weight less than 10 kg and then addfix callback update pin", "B2B MPS SHIPMENT WITH CHILD PAYLOAD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "W", "1028", "true", "IND110092AAB", "null"}
		};
	}

	@Test(dataProvider = "international_custom_shipment_pin_updated_from_addfix_callback" , enabled = true)
	public void verifyCnForCustomInternationShipmentPackagePinUpdatedFromAddfixCallback(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
			, String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

		// Manifesting a package
		List<String> waybills = new ArrayList<String>();
		String waybill, child_waybill = "";
		PackageDetail pkgdetails, child_pkgDetails = null;
		LinkedHashMap<String, String> expected_values;
		HashMap requestPayload = new HashMap<String, String>();
		requestPayload.put("client","custom_srv_client");
		requestPayload.put("international", "true");
		if (Scenario.contains("MPS")) {
			requestPayload.put("master_id", Utilities.getUniqueInt(15));
		}
		if (!Scenario.contains("ONLY MASTER")) {
			requestPayload.put("waybill", Utilities.getUniqueInt(15));
		}
		requestPayload.put("pin", "122003");
		waybills = diffTypeShipment.DifferentTypeShipments(type, requestPayload);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);

		// setting the default value of child_waybill as master waybill
		child_waybill = null;
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			child_waybill = waybills.get(1);
			logInfo("Child Waybill generated " + child_waybill);
		}

		if (!type.contains("B2C")) {
			Utilities.hardWait(70);
		} else {
			Utilities.hardWait(5);
		}

		// Performing FM of the package
		apiCtrl.fmOMSApi(waybill, "FMPICK", origin_center.get("SortCode").toString(),requestPayload);
		Utilities.hardWait(2);
		apiCtrl.giApi(waybill, origin_center.get("Name").toString(),requestPayload);
		Utilities.hardWait(2);

		// Performing FM of child package if present
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			apiCtrl.fmOMSApi(child_waybill, "FMPICK", origin_center.get("SortCode").toString(),requestPayload);
			Utilities.hardWait(2);
			apiCtrl.giApi(child_waybill, origin_center.get("Name").toString(),requestPayload);
			Utilities.hardWait(2);
		}

		// checking prd on master package
		logInfo("Checking i11l on package");
		pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
		assertKeyValue("i11l", "true", pkgdetails.i11l);

		// checking prd of child waybill
		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			logInfo("Checking i11l on child package");
			child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
			assertKeyValue("i11l", "true", child_pkgDetails.i11l);
		}

		// Update weight -> for master only
		if (Scenario.contains("weight less than 10 kg")) {
			apiCtrl.QcWtApi(waybill, 9999.12, "sorter",requestPayload);
			Utilities.hardWait(10);
		} else if (Scenario.contains("weight more than 10 kg")) {
			apiCtrl.QcWtApi(waybill, 10000.12, "sorter",requestPayload);
			Utilities.hardWait(70);
		}

 		//editing add such that aseg callback would change the pin to a new pin
		HashMap<String, String> editAdd = new HashMap<>();
		editAdd.put("client","custom_srv_client");
		editAdd.put("add", "palika bazar"); // aseg update pin to 122003
		apiCtrl.EditApi(waybill, editAdd);

		//waiting period for addfix callback
		Utilities.hardWait(addfixCallbackDelayTime);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
		Assertions.assertIfNotNull("aseg", pkgdetails.aseg.ud);

		if (!type.contains("B2C")) {
			Utilities.hardWait(70);
		} else {
			Utilities.hardWait(5);
		}

		// Making a hashmap of Expected values to send in the assertion fnc
		expected_values = new LinkedHashMap<String, String>();
		expected_values.put("cn", expectedCn);
		expected_values.put("cn1", expectedCn1);
		expected_values.put("cnid", expectedCnid);
		expected_values.put("dpc", expectedDpc);
		expected_values.put("dpcid", expectedDpcid);
		expected_values.put("cnc", expectedCnc);
		expected_values.put("cns", expectedCns);
		expected_values.put("cs.nsl", expectedCsNsl);
		expected_values.put("cs.st", expectedCsSt);
		expected_values.put("cs.sr", expectedCsSr);
		expected_values.put("cpdt", expectedCpdt);
		expected_values.put("rgn", expectedRgn);
		expected_values.put("sc", expectedSc);
		expected_values.put("srv", expectedSrv);
		expected_values.put("ocid", expectedOcid);
		expected_values.put("wvcid", expectedWvcid);


		Utilities.hardWait(keyassertPkgInfoFetchDelayTime);

		logInfo("Checking package keys :: "+waybill);
		pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
		ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

		if (waybills.size() > 1 && Scenario.contains("MPS")) {
			logInfo("Checking child package keys :: "+child_waybill);
			expected_values.put("cs.nsl", "DTUP-203");
			expected_values.put("cs.sr", "Package details changed by shipper");
			if (Scenario.contains("Scenario:: B2C MPS")) {
				expected_values.put("rgn", "null");
			}

			child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
			ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
		}

	}

	@DataProvider(name = "international_custom_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", parallel = false)
	public Object[][] international_custom_edt_vs_staging_package_keys_comparison() {
		return new Object[][] {
				{ "Scenario:: B2C package ", "B2C"},
				{ "Scenario:: B2C MPS package ", "B2C MPS SHIPMENT WITH CHILD PAYLOAD"},
				{ "Scenario:: B2B package ", "B2B"},
				{ "Scenario:: B2B MPS package ", "B2B MPS SHIPMENT WITH CHILD PAYLOAD"},
				{ "Scenario:: HEAVY package ", "HEAVY"},
				{ "Scenario:: HEAVY MPS package ", "HEAVY MPS SHIPMENT WITH CHILD PAYLOAD"}
		};
	}

	@Test(dataProvider = "international_custom_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", enabled = false)
	public void edtVsStagingPkgKeysComparison(String Scenario, String type) {
		List<String> edt_waybills = new ArrayList<String>();
		List<String> staging_waybills = new ArrayList<String>();


         //Manifesting a package
		String waybill, child_waybill = "";
        LinkedHashMap<String, String> expected_values;

		for (int i = 0; i < 2; i++) {
			List<String> waybills = new ArrayList<String>();
			HashMap<String, String> requestPayload = new HashMap();
				requestPayload.put("client","custom_srv_client");
				requestPayload.put("international", "true");
				if (Scenario.contains("MPS")) {
				requestPayload.put("master_id", Utilities.getUniqueInt(15));
			}
				if (!Scenario.contains("ONLY MASTER")) {
				requestPayload.put("waybill", Utilities.getUniqueInt(15));
			}
				requestPayload.put("add", "sector-99");
				requestPayload.put("pin", "122001");

			// creating shipment on edt
			if (i == 0) {
				requestPayload.put("enviorment", "edt");
			} else {
				requestPayload.put("enviorment", "staging");
			}
			waybills = diffTypeShipment.DifferentTypeShipments(type, requestPayload);

			if (i == 0) {
				edt_waybills = waybills;
				waybill = waybills.get(0);
				logInfo("Waybill generated from edt env :: " + waybill);
			} else {
				staging_waybills = waybills;
				waybill = waybills.get(0);
				logInfo("Waybill generated from staging env :: " + waybill);
			}

			// setting the default value of child_waybill as master waybill
			child_waybill = waybill;
			if (waybills.size() > 1 && Scenario.contains("MPS")) {
				child_waybill = waybills.get(1);
				logInfo("Child Waybill generated " + child_waybill);
			}
			// asserting keys after manifestation
			if (!type.contains("B2C")) {
				Utilities.hardWait(70);
			} else {
				Utilities.hardWait(5);
			}

			// Performing FM of the package
			apiCtrl.fmOMSApiDiffEnv(requestPayload.get("enviorment"), waybill, "FMPICK",requestPayload);

			// Performing FM of child package if present
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				apiCtrl.fmOMSApiDiffEnv(requestPayload.get("enviorment"), child_waybill, "FMPICK",requestPayload);
			}

			// Performing GI of master
			if (!type.contains("B2C")) {
				Utilities.hardWait(10);
			} else {
				Utilities.hardWait(2);
			}

			PackageDetail pkgdetails = apiCtrl.fetchPackageInfoDiffEnv(requestPayload.get("enviorment"),waybill,requestPayload);
			apiCtrl.giApiDiffEnv(requestPayload.get("enviorment"), waybill, pkgdetails.cs.sl,requestPayload);
			// Performing GI of child if present
			if (!child_waybill.equalsIgnoreCase(waybill)) {
				apiCtrl.giApiDiffEnv(requestPayload.get("enviorment"), child_waybill, pkgdetails.cs.sl,requestPayload);
			}

		}

		Utilities.hardWait(10);
		Utilities.hardWait(keyassertPkgInfoFetchDelayTime);

		//call assertion function here
		HashMap<String, String> requestPayload1 = new HashMap();
		requestPayload1.put("client","custom_srv_client");
		PackageDetail edtMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(0),requestPayload1);

		// checking prd on master package
		logInfo("Checking i11l on package");
		assertKeyValue("i11l", "true", edtMasterShipmentDetails.i11l);


		PackageDetail stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(0),requestPayload1);
		// checking prd on master package
		logInfo("Checking i11l on package");
		assertKeyValue("i11l", "true", stagingMasterShipmentDetails.i11l);

		try {
			logInfo("Waybill generated from edt env :: " + edt_waybills.get(0));
			logInfo("Waybill generated from staging env :: " + staging_waybills.get(0));
			ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingMasterShipmentDetails, edtMasterShipmentDetails);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(Scenario.contains("MPS")) {
			PackageDetail edtChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(1),requestPayload1);

			// checking prd on child package
			logInfo("Checking i11l on package");
			assertKeyValue("i11l", "true", edtChildShipmentDetails.i11l);

			PackageDetail stagingChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(1),requestPayload1);

			// checking prd on child package
			logInfo("Checking i11l on package");
			assertKeyValue("i11l", "true", stagingChildShipmentDetails.i11l);

			try {
				logInfo("Child waybill generated from edt env :: " + edt_waybills.get(1));
				logInfo("Child waybill generated from staging env :: " + staging_waybills.get(1));
				ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingChildShipmentDetails, edtChildShipmentDetails);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}