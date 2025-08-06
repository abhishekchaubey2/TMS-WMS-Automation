package com.delhivery.Express.testModules.ReturnServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.delhivery.core.utils.Utilities.logInfo;


public class PincodeReturnServiceability5 extends BaseTest {

    private String returnPin = "122001";
    private String updatedReturnPin = "122003";
    private String rasegCallbackAddress_GetNSZReturnCenterPin = "Theka road";

    public String scenario;
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    ArrayList<String> lightPdts = new ArrayList<String>(Arrays.asList("B2C", "NEXT_B2C_SURFACE", "FLASH_B2C_SURFACE", "FLASH_B2C_AIR", "HLD", "C2C-Lite", "DOC", "DOC_FLASH", "KYC"));
    ArrayList<String> heavyPdts = new ArrayList<String>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));

    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


    @DataProvider(name = "AMAZON_Client_Pincode_Return_Different_Payment_Mode_shipment_Manifestation", parallel = false)
    public Object[][] AMAZON_Client_Pincode_Return_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: Prepaid shipment", "Prepaid", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: REPL shipment", "REPL", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: RVP shipment", "Pickup", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "PP", "Yet to Pickup", "", "W", "1028", "null", "null", "null"},
        };
    }

    @Test(dataProvider = "AMAZON_Client_Pincode_Return_Different_Payment_Mode_shipment_Manifestation", enabled = true)
    public void verifyRcnForDiffernetPaymentModeShipmentManifestation(String Scenario, String paymentMode, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("payment_mode", paymentMode);
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
        waybills = diffTypeShipment.DifferentTypeShipments("B2C", requestPayload);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);
        Utilities.hardWait(5);

        // Making a hashmap of Expected values to send in the assertion fnc
        expected_values = new LinkedHashMap<String, String>();
        expected_values.put("rcn", expectedRCn);
        expected_values.put("rcn1", expectedRCn1);
        expected_values.put("rcnid", expectedRCnid);
        expected_values.put("rdpc", expectedRDpc);
        expected_values.put("rdpcid", expectedRDpcid);
        expected_values.put("rcty", expectedRCty);
        expected_values.put("rcns", expectedRCns);
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

    }

    @DataProvider(name = "AMAZON_Client_Pincode_Return_Different_type_shipment_Manifestation_With_Rpin_Radd", parallel = false)
    public Object[][] AMAZON_Client_Pincode_Return_Different_type_shipment_Manifestation_With_Rpin_Radd() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B package", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy package", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2C MPS", "B2C MPS", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "null", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B MPS", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy MPS", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"},
                {"Scenario:: NO DATA shipment", "NO DATA", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "null", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not received from Client", "null", "null", "1028", "null", "IND122001AAB", ""},
                {"Scenario:: Partial manifest shipment", "PARTIALLY MANIFESTED", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "null", "null", "X-SPM", "UD", "Shipment partially manifested", "null", "null", "1209", "null", "IND122001AAB", ""}
        };
    }

    @Test(dataProvider = "AMAZON_Client_Pincode_Return_Different_type_shipment_Manifestation_With_Rpin_Radd", enabled = true)
    public void verifyRcnForAMAZONClientDifferentTypeShipmentManifestationWithRpinRadd(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
        waybills = diffTypeShipment.DifferentTypeShipments(type, requestPayload);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);

        // setting the default value of child_waybill as master waybill
        child_waybill = null;
        if (waybills.size() > 1) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }

        if (!type.contains("B2C")) {
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        // Making a hashmap of Expected values to send in the assertion fnc
        expected_values = new LinkedHashMap<String, String>();
        expected_values.put("rcn", expectedRCn);
        expected_values.put("rcn1", expectedRCn1);
        expected_values.put("rcnid", expectedRCnid);
        expected_values.put("rdpc", expectedRDpc);
        expected_values.put("rdpcid", expectedRDpcid);
        expected_values.put("rcty", expectedRCty);
        expected_values.put("rcns", expectedRCns);
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

        if (waybills.size() > 1) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }

    @DataProvider(name = "AMAZON_Client_Pincode_Return_Different_type_shipment_Manifestation_Without_Rpin_Radd", parallel = false)
    public Object[][] AMAZON_Client_Pincode_Return_Different_type_shipment_Manifestation_Without_Rpin_Radd() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B package", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy package", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2C MPS", "B2C MPS", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "null", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B MPS", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy MPS", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"}
        };
    }

    @Test(dataProvider = "AMAZON_Client_Pincode_Return_Different_type_shipment_Manifestation_Without_Rpin_Radd", enabled = true)
    public void verifyRcnForAMAZONClientDifferentTypeShipmentManifestationWithOutRpinRadd(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
        waybills = diffTypeShipment.DifferentTypeShipments(type, requestPayload);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);

        // setting the default value of child_waybill as master waybill
        child_waybill = null;
        if (waybills.size() > 1) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }

        if (!type.contains("B2C")) {
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        // Making a hashmap of Expected values to send in the assertion fnc
        expected_values = new LinkedHashMap<String, String>();
        expected_values.put("rcn", expectedRCn);
        expected_values.put("rcn1", expectedRCn1);
        expected_values.put("rcnid", expectedRCnid);
        expected_values.put("rdpc", expectedRDpc);
        expected_values.put("rdpcid", expectedRDpcid);
        expected_values.put("rcty", expectedRCty);
        expected_values.put("rcns", expectedRCns);
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

        if (waybills.size() > 1) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }


}