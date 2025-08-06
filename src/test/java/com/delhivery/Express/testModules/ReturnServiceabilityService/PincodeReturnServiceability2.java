package com.delhivery.Express.testModules.ReturnServiceabilityService;

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

import static com.delhivery.core.utils.Utilities.logInfo;


public class PincodeReturnServiceability2 extends BaseTest {

    private String returnPin = "122001";
    private String updatedReturnPin = "122003";
    private String rasegCallbackReturnAddress_notUpdateRpin = "testingServiceability12345";
    private String rasegCallbackReturnAddress_updateRpin = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";

    public String scenario;
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    ArrayList<String> lightPdts = new ArrayList<String>(Arrays.asList("B2C", "NEXT_B2C_SURFACE", "FLASH_B2C_SURFACE", "FLASH_B2C_AIR", "HLD", "C2C-Lite", "DOC", "DOC_FLASH", "KYC"));
    ArrayList<String> heavyPdts = new ArrayList<String>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));

    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


    @DataProvider(name = "Pincode_Return_Different_Payment_Mode_shipment_Manifestation", parallel = false)
    public Object[][] Pincode_Return_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: Prepaid shipment", "Prepaid", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: REPL shipment", "REPL", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: RVP shipment", "Pickup", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-ASP", "PP", "Pickup scheduled", "", "W", "1028", "null", "IND400093AAA", "null"},
        };
    }

    @Test(dataProvider = "Pincode_Return_Different_Payment_Mode_shipment_Manifestation", enabled = true)
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


    @DataProvider(name = "Pincode_Return_b2c_diff_state_shipments_raseg_update_rpin", parallel = false)
    public Object[][] Pincode_Return_b2c_diff_state_shipments_raseg_update_rpin() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                { "Scenario:: In Transit state shipment", "In Transit", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND110092AAB", "null", false},
                { "Scenario:: Pending state shipment", "Pending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Returned state shipment", "Returned", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "W", "1028", "null", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "W", "1028", "true", "null", "null", false},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "W", "1028", "null", "null", "null", true},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "W", "1028", "null", "IND400093AAA", "IND400612AAA", true},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "W", "1028", "null", "IND400093AAA", "IND400612AAA", true},



                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "Pincode_Return_b2c_diff_state_shipments_raseg_update_rpin", enabled = true)
    public void verifyRcnForB2CDiffStateShipmentRasegUpdateRpin(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "testingDummyData");
            requestPayload.put("pin", "400059");
            requestPayload.put("product_type", "B2C");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", returnPin);
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(10);

            //editing add
            HashMap<String, String> editRadd = new HashMap<>();
            editRadd.put("return_add", rasegCallbackReturnAddress_updateRpin); // raseg update rpin
            apiCtrl.EditApi(waybill, editRadd);

            //waiting period for return pincode update
            Utilities.hardWait(returnAddfixCallbackDelayTime);
            pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
            Assertions.assertIfNotNull("raseg", pkgdetails.raseg.ud);
            Utilities.hardWait(10);


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

        } else {
            logInfo("This method not required");
        }

    }

    @DataProvider(name = "Pincode_Return_b2b_diff_state_shipments_raseg_update_rpin", parallel = false)
    public Object[][] Pincode_Return_b2b_diff_state_shipments_raseg_update_rpin() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "null", "null", false},
                { "Scenario:: In Transit state shipment", "In Transit", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND110092AAB", "null", false},
                { "Scenario:: Pending state shipment", "Pending", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Returned state shipment", "Returned", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "PP", "Pincode updated by Addfix", "B2B", "N", "249", "true", "null", "null", false},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "W", "1028", "true", "null", "null", true},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND400093AAA", "IND400612AAA", true},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND400093AAA", "IND400612AAA", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "Pincode_Return_b2b_diff_state_shipments_raseg_update_rpin", enabled = true)
    public void verifyRcnForB2BDiffStateShipmentRasegUpdateRpin(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "testingDummyData");
            requestPayload.put("pin", "400059");
            requestPayload.put("product_type", "B2B");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", returnPin);
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(70);

            //editing radd
            HashMap<String, String> editRadd = new HashMap<>();
            editRadd.put("return_add", rasegCallbackReturnAddress_updateRpin); // raseg update rpin
            apiCtrl.EditApi(waybill, editRadd);

            //waiting period for return pincode update
            Utilities.hardWait(returnAddfixCallbackDelayTime);
            pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
            Assertions.assertIfNotNull("raseg", pkgdetails.raseg.ud);
            Utilities.hardWait(70);


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

        } else {
            logInfo("This method not required");
        }

    }

    @DataProvider(name = "Pincode_Return_shipment_manifestation_with_diff_rpin_data", parallel = false)
    public Object[][] Pincode_Return_shipment_manifestation_with_diff_rpin_data() {
        return new Object[][]{
                {"Scenario:: B2C package with pin having no data", "B2C", "133100", "null", "null", "null", "null", "null", "", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2C package with pin having center as invalid", "B2C", "133101", "test5_facility (Haryana)", "null", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2C package with pin having center as NSZ", "B2C", "133102", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2C package with pin having center as valid", "B2C", "133001", "East Delhi (Delhi)", "East Delhi (Delhi)", "IND110092AAB", "null", "null", "Delhi", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B package with pin having no data", "B2B", "133100", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B package with pin having center as invalid", "B2B", "133101", "testqwerty12345", "testqwerty12345", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B package with pin having center as NSZ", "B2B", "133102", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B package with pin having center as valid", "B2B", "133001", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"}
        };
    }

    @Test(dataProvider = "Pincode_Return_shipment_manifestation_with_diff_rpin_data", enabled = true)
    public void verifyRcnForDiffRpinDataShipmentManifestation(String Scenario, String productType, String rpin, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        try {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            List<String> waybills = new ArrayList<String>();
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("pin", "400059");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", rpin);
            waybills = diffTypeShipment.DifferentTypeShipments(productType, requestPayload);
            waybill = waybills.get(0);
            logInfo("Waybill generated " + waybill);

            if (!productType.contains("B2C")) {
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
        } catch (Exception e) {
            if (productType.equalsIgnoreCase("b2b") && e.toString().contains(rpin+" is not serviceable")) {
                logInfo("error :: "+e);
            } else {
                throw new RuntimeException(e);
            }
        }

    }


}