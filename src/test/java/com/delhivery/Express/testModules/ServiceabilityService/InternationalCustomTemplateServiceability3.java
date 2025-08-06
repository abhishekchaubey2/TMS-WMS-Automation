package com.delhivery.Express.testModules.ServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

public class InternationalCustomTemplateServiceability3 extends BaseTest {

	private String asegCallbackAddress_GetNSZCenterPin = "Theka road";

	public String scenario;
	ApiController apiCtrl = new ApiController();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();

	ArrayList<String> lightPdts = new ArrayList<String>(Arrays.asList("B2C", "NEXT_B2C_SURFACE", "FLASH_B2C_SURFACE", "FLASH_B2C_AIR", "HLD", "C2C-Lite", "DOC", "DOC_FLASH", "KYC"));
	ArrayList<String> heavyPdts = new ArrayList<String>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));

	protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
	protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


	@DataProvider(name = "international_custom_b2c_diff_state_shipments_aseg_update_pin_NSZ_center", parallel = false)
	public Object[][] international_custom_b2c_diff_state_shipments_aseg_update_pin_NSZ_center() {
		return new Object[][] {
				{ "Scenario:: Manifest state shipment", "Manifest", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "N", "null", "null", "null", "null", true},
				{ "Scenario:: In Transit state shipment", "In Transit", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "N", "null", "null", "IND110092AAB", "null", true},
				{ "Scenario:: Pending state shipment", "Pending"	, "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},
				{ "Scenario:: Returned state shipment", "Returned", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "N", "null", "null", "IND110092AAB", "null", true},
				{ "Scenario:: PickupPending state shipment", "PickupPending", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "PP", "Package details changed by shipper", "", "N", "null", "null", "null", "null", true},
				{ "Scenario:: PickedUp state shipment", "PickedUp", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "N", "null", "null", "null", "null", true},
				{ "Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},
				{ "Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},

				//NR edit api won't update address
				{ "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
				{ "Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
				{ "Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
				{ "Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
				{ "Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
				{ "Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

		};
	}

	@Test(dataProvider = "international_custom_b2c_diff_state_shipments_aseg_update_pin_NSZ_center", enabled = true)
	public void verifyCnForB2CDiffStateShipmentAsegUpdatePinNSZCenter(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
			, String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

		if (enable) {
			String waybill, child_waybill = "";
			PackageDetail pkgdetails, child_pkgDetails;
			LinkedHashMap<String, String> expected_values;
			HashMap requestPayload = new HashMap<String, String>();
			requestPayload.put("client","custom_srv_client");
			requestPayload.put("waybill", Utilities.getUniqueInt(15));
			requestPayload.put("international", "true");
			requestPayload.put("add", "testingDummyData");
			requestPayload.put("pin", "110031");
			requestPayload.put("product_type", "B2C");
			waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
			logInfo("Waybill " + waybill);
			Utilities.hardWait(10);

			// checking prd on package
			logInfo("Checking i11l on package");
			pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
			assertKeyValue("i11l", "true", pkgdetails.i11l);

			//editing add
			HashMap<String, String> editAdd = new HashMap<>();
			editAdd.put("client","custom_srv_client");
			editAdd.put("add", asegCallbackAddress_GetNSZCenterPin); // aseg update pin to 122003
			apiCtrl.EditApi(waybill, editAdd);

			//waiting period for pincode update
			Utilities.hardWait(addfixCallbackDelayTime);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
			Assertions.assertIfNotNull("aseg", pkgdetails.aseg.ud);
			Utilities.hardWait(10);


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

		} else {
			logInfo("This method not required");
		}

	}


	@DataProvider(name = "international_custom_b2b_diff_state_shipments_aseg_update_pin_NSZ_center", parallel = false)
	public Object[][] international_custom_b2b_diff_state_shipments_aseg_update_pin_NSZ_center() {
		return new Object[][] {
				{ "Scenario:: Manifest state shipment", "Manifest", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "W", "1028", "true", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "W", "1028", "true", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "W", "1028", "true", "IND400093AAA", "null", true},
                { "Scenario:: Returned state shipment", "Returned", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND110092AAB", "null", true},
                { "Scenario:: PickupPending state shipment", "PickupPending", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "PP", "Non-serviceable location", "B2B", "W", "1028", "true", "null", "null", true},
                { "Scenario:: PickedUp state shipment", "PickedUp", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "W", "1028", "true", "null", "null", true},
                { "Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND400093AAA", "null", true},
                { "Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND400093AAA", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                { "Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

		};
	}

	@Test(dataProvider = "international_custom_b2b_diff_state_shipments_aseg_update_pin_NSZ_center", enabled = true)
	public void verifyCnForB2BDiffStateShipmentAsegUpdatePinNSZCenter(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
			, String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

		if (enable) {
			String waybill, child_waybill = "";
			PackageDetail pkgdetails, child_pkgDetails;
			LinkedHashMap<String, String> expected_values;
			HashMap requestPayload = new HashMap<String, String>();
			requestPayload.put("client","custom_srv_client");
			requestPayload.put("waybill", Utilities.getUniqueInt(15));
			requestPayload.put("international", "true");
			requestPayload.put("add", "testingDummyData");
			requestPayload.put("pin", "110031");
			requestPayload.put("product_type", "B2B");
			waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
			logInfo("Waybill " + waybill);
			Utilities.hardWait(60);

			// checking prd on package
			logInfo("Checking i11l on package");
			pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
			assertKeyValue("i11l", "true", pkgdetails.i11l);

			//editing add
			HashMap<String, String> editAdd = new HashMap<>();
			editAdd.put("client","custom_srv_client");
			editAdd.put("add", asegCallbackAddress_GetNSZCenterPin); // aseg update pin to 122003
			apiCtrl.EditApi(waybill, editAdd);

			//waiting period for pincode update
			Utilities.hardWait(addfixCallbackDelayTime);
			pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
			Assertions.assertIfNotNull("aseg", pkgdetails.aseg.ud);
			Utilities.hardWait(70);


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

		} else {
			logInfo("This method not required");
		}

	}



}