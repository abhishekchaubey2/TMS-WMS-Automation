package com.delhivery.Express.testModules.ReturnServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.delhivery.core.utils.Utilities.logInfo;

public class EpodReturnServiceability2 extends BaseTest {

    private String asegCallbackAddress_GetNoMapperCenter = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";
    private String asegCallbackAddress_GetMapperCenter = "J649+26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001";
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();


    @DataProvider(name = "EPOD_Return_diff_state_shipments_pin_update", parallel = false)
    public Object[][] EPOD_Return_diff_state_shipments_pin_update() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-205", "UD", "Package details changed by Delhivery", "", "N", "1209", "false", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-205", "UD", "Package details changed by Delhivery", "", "N", "1209", "false", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-205", "UD", "Package details changed by Delhivery", "", "N", "1209", "false", "IND400093AAA", "IND400612AAA", true},
                {"Scenario:: Returned state shipment", "Returned", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-205", "RT", "Package details changed by Delhivery", "", "N", "1209", "false", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-205", "PP", "Package details changed by Delhivery", "", "N", "1209", "false", "null", "null", true},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-205", "PU", "Package details changed by Delhivery", "", "N", "1209", "false", "null", "null", true},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "", "N", "1209", "false", "null", "null", true},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-205", "RT", "Package details changed by Delhivery", "", "N", "1209", "false", "IND110092AAB", "null", true},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-205", "PU", "Package details changed by Delhivery", "", "N", "1209", "false", "null", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "EPOD_Return_diff_state_shipments_pin_update", enabled = true)
    public void verifyRcnForDiffStateShipmentPinUpdate(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "812 Niti Khand-1");
            requestPayload.put("pin", "400059");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", "122001");
            requestPayload.put("client", "epod_client");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(10);

            //editing pin
            HashMap<String, String> editPin = new HashMap<>();
            editPin.put("pincode", "122001"); // aseg update pin to 122003
            apiCtrl.EditApi(waybill, editPin);

            //waiting period for pincode update
            Utilities.hardWait(30);


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

            logInfo("Checking package keys :: "+waybill);
            pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

        } else {
            logInfo("This method not required");
        }

    }


    @DataProvider(name = "EPOD_Return_shipment_rpin_updated_from_return_addfix", parallel = false)
    public Object[][] EPOD_Return_shipment_rpin_updated_from_return_addfix() {
        return new Object[][]{
                {"Scenario:: B2C package shipment return addfix callback update rpin", "B2C", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B package shipment return addfix callback update rpin", "B2B", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy package shipment return addfix callback update rpin", "Heavy", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "N", "249", "true", "IND110092AAB", "null"},
                {"Scenario:: B2C MPS package shipment return addfix callback update rpin", "B2C MPS", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS shipment return addfix callback update rpin", "B2B MPS", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy MPS shipment return addfix callback update rpin", "Heavy MPS", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "N", "249", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "EPOD_Return_shipment_rpin_updated_from_return_addfix" , enabled = true)
    public void verifyRcnForShipmentRpinUpdatedFromReturnAddfixCallback(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("add", "812 Niti Khand-1");
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", asegCallbackAddress_GetNoMapperCenter);
        requestPayload.put("return_pin", "122001");
        requestPayload.put("client", "epod_client");
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

        logInfo("Checking package keys :: "+waybill);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }

    @DataProvider(name = "EPOD_Return_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", parallel = false)
    public Object[][] EPOD_Return_edt_vs_staging_package_keys_comparison() {
        return new Object[][] {
                { "Scenario:: B2C package", "B2C"},
//                { "Scenario:: B2C MPS package", "B2C MPS"},
//                { "Scenario:: B2B package", "B2B"},
//                { "Scenario:: B2B MPS package", "B2B MPS"},
//                { "Scenario:: HEAVY package", "HEAVY"},
//                { "Scenario:: HEAVY MPS package", "HEAVY MPS"}
        };
    }

    @Test(dataProvider = "EPOD_Return_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", enabled = false)
    public void edtVsStagingPkgKeysComparison(String Scenario, String type) {
        List<String> edt_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        HashMap<String, String> requestPayload = new HashMap();
        requestPayload.put("add", "812 Niti Khand-1");
        requestPayload.put("pin", "122001");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", "122001");
        requestPayload.put("client", "epod_client");

        // Manifesting a package
        String waybill, child_waybill = "";
        LinkedHashMap<String, String> expected_values;

        for (int i = 0; i < 2; i++) {
            List<String> waybills = new ArrayList<String>();

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
        }

        //call assertion function here
        PackageDetail edtShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt", edt_waybills.get(0),requestPayload);
        PackageDetail stagingShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging", staging_waybills.get(0),requestPayload);

        try {
            logInfo("Waybill generated from edt env :: " + edt_waybills.get(0));
            logInfo("Waybill generated from staging env :: " + staging_waybills.get(0));
            ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingShipmentDetails, edtShipmentDetails);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (Scenario.contains("MPS")) {
            PackageDetail edtChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt", edt_waybills.get(1),requestPayload);
            PackageDetail stagingChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging", staging_waybills.get(1),requestPayload);

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
