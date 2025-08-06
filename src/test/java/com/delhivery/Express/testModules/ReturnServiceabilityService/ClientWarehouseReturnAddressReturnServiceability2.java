package com.delhivery.Express.testModules.ReturnServiceabilityService;

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

import static com.delhivery.core.utils.Utilities.logInfo;

public class ClientWarehouseReturnAddressReturnServiceability2 extends BaseTest {

    private String returnPin = "122001";
    private String rasegCallbackReturnAddress_updateRpin = "Juhu Supreme Shopping Centre, Opp Criti Care Hospital, Gulmohar Road 9, Vile Parle West";

    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    protected static Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION Ret Add OC");
    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");

    @DataProvider(name = "ClientWarehouse_RtAdd_Return_diff_state_shipments", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Return_diff_state_shipments() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "1209", "null", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB", true},
                {"Scenario:: Returned state shipment", "Returned", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "RT-101", "RT", "Returned as per Client Instructions", "", "N", "1209", "null", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "X-ASP", "PP", "Pickup scheduled", "", "N", "1209", "null", "null", "null", true},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "EOD-77", "PU", "Pickup completed", "", "N", "1209", "null", "null", "null", true},
                {"Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "EOD-50", "RT", "Returned as per client instructions", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB", true},
                {"Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "EOD-77", "PU", "Pickup completed", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB", true},
                { "Scenario:: Dispatched state shipment", "Dispatched", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB", true},
                {"Scenario:: Delivered state shipment", "Delivered", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB", true},
                {"Scenario:: RTO state shipment", "RTO", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "1209", "null", "IND110092AAB", "null", true},
                {"Scenario:: LOST state shipment", "LOST", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "LT-100", "LT", "Shipment LOST", "", "N", "1209", "null", "IND110092AAB", "null", true},
                {"Scenario:: DTO state shipment", "DTO", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "1209", "null", "null", "null", true},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "EOD-108", "CN", "QC failed at pickup. Product count mismatch", "", "N", "1209", "null", "null", "null", true}

        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Return_diff_state_shipments", enabled = true)
    public void verifyRcnForDiffStateShipmentn(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            // Manifesting a package
            List<String> waybills = new ArrayList<String>();
            String waybill, child_waybill = "";
            PackageDetail pkgdetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("pin", "122001");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", returnPin);
            requestPayload.put("client", "return_address_return_client_warehouse");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
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

    @DataProvider(name = "ClientWarehouse_RtAdd_Return_diff_state_shipments_raseg_update_pin", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Return_diff_state_shipments_raseg_update_pin() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                { "Scenario:: In Transit state shipment", "In Transit", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND110092AAB", "null", false},
                { "Scenario:: Pending state shipment", "Pending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Returned state shipment", "Returned", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "N", "1209", "null", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "PP", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "N", "1209", "null", "null", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Return_diff_state_shipments_raseg_update_pin", enabled = true)
    public void verifyRcnForDiffStateShipmentRasegUpdatePin(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            // Manifesting a package
            List<String> waybills = new ArrayList<String>();
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("pin", "122001");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", returnPin);
            requestPayload.put("client", "return_address_return_client_warehouse");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(10);

            //editing add
            HashMap<String, String> editRadd = new HashMap<>();
            editRadd.put("client", "return_address_return_client_warehouse");
            editRadd.put("return_add", rasegCallbackReturnAddress_updateRpin); // aseg update pin to 122003
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

    @DataProvider(name = "ClientWarehouse_RtAdd_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_edt_vs_staging_package_keys_comparison() {
        return new Object[][] {
                { "Scenario:: B2C package", "B2C"},
                { "Scenario:: B2C MPS package", "B2C MPS"},
                { "Scenario:: B2B package", "B2B"},
                { "Scenario:: B2B MPS package", "B2B MPS"},
                { "Scenario:: HEAVY package", "HEAVY"},
                { "Scenario:: HEAVY MPS package", "HEAVY MPS"}
        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", enabled = true)
    public void edtVsStagingPkgKeysComparison(String Scenario, String type) {
        List<String> edt_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();


        // Manifesting a package
        String waybill, child_waybill = "";
        LinkedHashMap<String, String> expected_values;

        for (int i = 0; i < 2; i++) {
            List<String> waybills = new ArrayList<String>();
            HashMap<String, String> requestPayload = new HashMap();
            requestPayload.put("pin", "400059");
            requestPayload.put("return_add", "test");
            requestPayload.put("return_pin", returnPin);
            requestPayload.put("client", "return_address_return_client_warehouse");

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

        HashMap<String, String> requestPayload = new HashMap();
        requestPayload.put("client", "return_address_return_client_warehouse");

        //call assertion function here
        PackageDetail edtShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(0),requestPayload);
        PackageDetail stagingShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(0),requestPayload);

        try {
            logInfo("Waybill generated from edt env :: " + edt_waybills.get(0));
            logInfo("Waybill generated from staging env :: " + staging_waybills.get(0));
            ServiceabilityKeysAssertions.compareEdtStagingShipment(stagingShipmentDetails, edtShipmentDetails);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(Scenario.contains("MPS")) {
            PackageDetail edtChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("edt",edt_waybills.get(1),requestPayload);

            PackageDetail stagingChildShipmentDetails = apiCtrl.fetchPackageInfoDiffEnv("staging",staging_waybills.get(1),requestPayload);

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
