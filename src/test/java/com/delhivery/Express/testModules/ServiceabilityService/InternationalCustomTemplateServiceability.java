package com.delhivery.Express.testModules.ServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

public class InternationalCustomTemplateServiceability extends BaseTest {
    public String scenario;
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


    String expectedCn, expectedCn1, expectedCnid, expectedDpc, expectedDpcid, expectedCnc, expectedCns, expectedCsNsl, expectedCsSt, expectedCsSr, expectedCpdt, expectedRgn, expectedSc, expectedSrv, expectedOcid, expectedWvcid = "";


    public InternationalCustomTemplateServiceability() {
//		client = HQAPIREGRESSION SRV;
//		b2c_template	football
//		b2b_template	e2e
//		InternationalLightCenter	Rudrapur_UdhamNgr_L
//		InternationalHeavyCenter	Del_Okhla_PC

    }


    @DataProvider(name = "international_shipment_Manifestation", parallel = false)
    public Object[][] international_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
                {"Scenario:: B2B package", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy package", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2C MPS", "B2C MPS SHIPMENT WITH CHILD PAYLOAD", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B MPS", "B2B MPS SHIPMENT WITH CHILD PAYLOAD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy MPS", "Heavy MPS SHIPMENT WITH CHILD PAYLOAD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"}
        };
    }

    @Test(dataProvider = "international_shipment_Manifestation", enabled = true)
    public void verifyCnForCustomInternationShipmentManifestation(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails = null;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("client", "custom_srv_client");
        requestPayload.put("international", "true");
        if (Scenario.contains("MPS")) {
            requestPayload.put("master_id", Utilities.getUniqueInt(15));
        }
        if (!Scenario.contains("ONLY MASTER")) {
            requestPayload.put("waybill", Utilities.getUniqueInt(15));
        }
        requestPayload.put("add", "sector-99");
        requestPayload.put("pin", "122001");
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
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        // checking prd on master package
        logInfo("Checking i11l on package");
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        assertKeyValue("i11l", "true", pkgdetails.i11l);

        // checking prd of child waybill
        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking i11l on child package");
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, requestPayload);
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

//		serviceabilityUtils.verifyScenarioShipmentData(waybills, requestPayload, expected_values);

        Utilities.hardWait(keyassertPkgInfoFetchDelayTime);

        logInfo("Checking package keys :: " + waybill);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking child package keys :: " + child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }

    @DataProvider(name = "international_Different_Payment_Mode_shipment_Manifestation", parallel = false)
    public Object[][] international_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
                {"Scenario:: Prepaid shipment", "Prepaid", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
                {"Scenario:: REPL shipment", "REPL", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
                {"Scenario:: RVP shipment", "Pickup", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "PP", "Yet to Pickup", "", "N", "null", "null", "null", "null"},
        };
    }

    @Test(dataProvider = "international_Different_Payment_Mode_shipment_Manifestation", enabled = true)
    public void verifyCnForDiffernetPaymentModeShipmentManifestation(String Scenario, String paymentMode, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("payment_mode", paymentMode);
        requestPayload.put("add", "sector-99");
        requestPayload.put("pin", "122001");
        requestPayload.put("client", "custom_srv_client");
        requestPayload.put("international", "true");
        requestPayload.put("waybill", Utilities.getUniqueInt(15));
        waybills = diffTypeShipment.DifferentTypeShipments("B2C", requestPayload);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);
        Utilities.hardWait(5);

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
        logInfo("Checking package keys :: " + waybill);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

    }


    @DataProvider(name = "international_custom_shipment_Manifestation_and_weight_sent_on_packages", parallel = false)
    public Object[][] international_custom_shipment_Manifestation_and_weight_sent_on_packages() {
        return new Object[][]{
                {"Scenario:: B2C package with weight less than 10 kg", "B2C", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "null", "null", "IND110092AAB", "null"},
                {"Scenario:: B2C package with weight more than 10 kg", "B2C", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-231", "UD", "Center changed by system", "Heavy", "W", "1028", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B package with weight less than 10 kg", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: B2B package with weight more than 10 kg", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy package with weight more than 10 kg", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Heavy", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: B2C MPS package with weight more than 10 kg", "B2C MPS SHIPMENT WITH CHILD PAYLOAD", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-231", "UD", "Center changed by system", "Heavy", "W", "1028", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package with weight less than 10 kg", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package with weight less than 10 kg", "B2B MPS WITH INTERNAL CHILD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS with weight less than 10 kg", "B2B MPS SHIPMENT WITH CHILD PAYLOAD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "W", "1028", "true", "IND110092AAB", "null"}
        };
    }


    @Test(dataProvider = "international_custom_shipment_Manifestation_and_weight_sent_on_packages", enabled = true)
    public void verifyCnForCustomInternationShipmentManifestationWithWeightUpdatedOnPackages(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails = null;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("client", "custom_srv_client");
        requestPayload.put("international", "true");
        if (Scenario.contains("MPS")) {
            requestPayload.put("master_id", Utilities.getUniqueInt(15));
        }
        if (!Scenario.contains("ONLY MASTER")) {
            requestPayload.put("waybill", Utilities.getUniqueInt(15));
        }
        requestPayload.put("add", "sector-99");
        requestPayload.put("pin", "122001");
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
        apiCtrl.fmOMSApi(waybill, "FMPICK", origin_center.get("SortCode").toString(), requestPayload);
        Utilities.hardWait(10);
        apiCtrl.giApi(waybill, origin_center.get("Name").toString(), requestPayload);
        Utilities.hardWait(2);

        // Performing FM of child package if present
        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            apiCtrl.fmOMSApi(child_waybill, "FMPICK", origin_center.get("SortCode").toString(), requestPayload);
            Utilities.hardWait(5);
            apiCtrl.giApi(child_waybill, origin_center.get("Name").toString(), requestPayload);
            Utilities.hardWait(2);
        }

        // checking prd on master package
        logInfo("Checking i11l on package");
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        assertKeyValue("i11l", "true", pkgdetails.i11l);

        // checking prd of child waybill
        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking i11l on child package");
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, requestPayload);
            assertKeyValue("i11l", "true", child_pkgDetails.i11l);
        }

        // Update weight -> for master only
        if (Scenario.contains("weight less than 10 kg")) {
            apiCtrl.QcWtApi(waybill, 9999.12, "sorter", requestPayload);
            Utilities.hardWait(10);
        } else if (Scenario.contains("weight more than 10 kg")) {
            apiCtrl.QcWtApi(waybill, 10000.12, "sorter", requestPayload);
            Utilities.hardWait(70);
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
        logInfo("Checking package keys :: " + waybill);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking child package keys :: " + child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill, requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }

    @DataProvider(name = "international_custom_diff_state_shipments", parallel = false)
    public Object[][] international_custom_diff_state_shipments() {
        return new Object[][]{
                {"Scenario:: Manifest state international shipment", "Manifest", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "null", "null", "null", "null"},
                {"Scenario:: In Transit state international shipment", "In Transit", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "null", "null", "IND110092AAB", "null"},
                {"Scenario:: Pending state international shipment", "Pending", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "null", "null", "IND263153AAC", ""},
                {"Scenario:: Dispatched state international shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "null", "IND263153AAC", ""},
                {"Scenario:: Delivered state international shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "null", "IND263153AAC", ""},
                {"Scenario:: Returned state international shipment", "Returned", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-101", "RT", "Returned as per Client Instructions", "", "N", "null", "null", "IND110092AAB", "null"},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-50", "RT", "Returned as per client instructions", "", "N", "null", "null", "IND263153AAC", ""},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-77", "PU", "Pickup completed", "", "N", "null", "null", "IND263153AAC", ""},
                {"Scenario:: RTO state international shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "null", "IND110092AAB", "null"},
                {"Scenario:: LOST state international shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "null", "IND110092AAB", "null"},
                {"Scenario:: PickupPending state international shipment", "PickupPending", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-ASP", "PP", "Pickup scheduled", "", "N", "null", "null", "null", "null"},
                {"Scenario:: PickedUp state international shipment", "PickedUp", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-77", "PU", "Pickup completed", "", "N", "null", "null", "null", "null"},
                {"Scenario:: DTO state international shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "null", "null", "null"},
                {"Scenario:: Cancelled state international shipment", "Cancelled", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "", "N", "null", "null", "null", "null"}
        };
    }

    @Test(dataProvider = "international_custom_diff_state_shipments", enabled = true)
    public void verifyCnInternationalCustomForDiffStateShipment(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails = null;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("client", "custom_srv_client");
        requestPayload.put("waybill", Utilities.getUniqueInt(15));
        requestPayload.put("international", "true");
        requestPayload.put("add", "sector-99");
        requestPayload.put("pin", "122001");
        requestPayload.put("cn", "Rudrapur_UdhamNgr_L");
        waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
        logInfo("Waybill " + waybill);
        Utilities.hardWait(2);

        // checking prd on package
        logInfo("Checking i11l on package");
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        assertKeyValue("i11l", "true", pkgdetails.i11l);


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
        logInfo("Checking package keys :: " + waybill);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill, requestPayload);
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

    }
}