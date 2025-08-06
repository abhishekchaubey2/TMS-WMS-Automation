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

import static com.delhivery.core.utils.Utilities.logInfo;


public class PdtBasedForwardServiceability5 extends BaseTest {

    private String asegCallbackAddress_GetNSZCenterPin = "Theka road";
    private String asegCallbackAddress_NoMapperCenter = "Theka road";

    public String scenario;
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    ArrayList<String> lightPdts = new ArrayList<String>(Arrays.asList("B2C", "NEXT_B2C_SURFACE", "FLASH_B2C_SURFACE", "FLASH_B2C_AIR", "HLD", "C2C-Lite", "DOC", "DOC_FLASH", "KYC"));
    ArrayList<String> heavyPdts = new ArrayList<String>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));

    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


    @DataProvider(name = "Pdt_Forward_b2c_diff_state_shipments_aseg_update_pin_NSZ_center", parallel = false)
    public Object[][] Pdt_Forward_b2c_diff_state_shipments_aseg_update_pin_NSZ_center() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "", "1053", "null", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "", "1053", "null", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "", "1053", "null", "IND110096AAA", "null", true},
                { "Scenario:: Returned state shipment", "Returned", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "", "1053", "null", "IND110092AAB", "null", true},
                { "Scenario:: PickupPending state shipment", "PickupPending", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "PP", "Package details changed by shipper", "", "", "1053", "null", "null", "null", true},
                { "Scenario:: PickedUp state shipment", "PickedUp", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "", "1053", "null", "null", "null", true},
                { "Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "", "1053", "null", "IND110096AAA", "null", true},
                { "Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Delhi_East_RPC (Delhi)", "null", "IND110096AAA", "null", "null", "Delhi", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "", "1053", "null", "IND110096AAA", "", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                { "Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "Pdt_Forward_b2c_diff_state_shipments_aseg_update_pin_NSZ_center", enabled = true)
    public void verifyCnForB2CDiffStateShipmentAsegUpdatePinNSZCenter(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "testingDummyData");
            requestPayload.put("pin", "110031");
            requestPayload.put("product_type", "B2C");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(10);

            //editing add
            HashMap<String, String> editAdd = new HashMap<>();
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


    @DataProvider(name = "Pdt_Forward_b2b_diff_state_shipments_aseg_update_pin_NSZ_center", parallel = false)
    public Object[][] Pdt_Forward_b2b_diff_state_shipments_aseg_update_pin_NSZ_center() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "null", "275", "true", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "null", "275", "true", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "NSZ", "null", "IND110086AAA", "null", "null", "null", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "275", "true", "IND110086AAA", "null", true},
                { "Scenario:: Returned state shipment", "Returned", "Delhi_Rohini_RP (DELHI)", "null", "IND110086AAA", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "", "275", "true", "IND110092AAB", "null", true},
                { "Scenario:: PickupPending state shipment", "PickupPending", "NSZ", "null", "null", "null", "null", "null", "null", "X-NSZ", "PP", "Non-serviceable location", "B2B", "null", "275", "true", "null", "null", true},
                { "Scenario:: PickedUp state shipment", "PickedUp", "Delhi_Rohini_RP (DELHI)", "null", "IND110086AAA", "null", "null", "Delhi", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "", "275", "true", "null", "null", true},
                { "Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Delhi_Rohini_RP (DELHI)", "null", "IND110086AAA", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "", "275", "true", "IND110086AAA", "null", true},
                { "Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Delhi_Rohini_RP (DELHI)", "null", "IND110086AAA", "null", "null", "Delhi", "null", "EOD-77", "PU", "Package details changed by shipper", "B2B", "", "275", "true", "null", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                { "Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "Pdt_Forward_b2b_diff_state_shipments_aseg_update_pin_NSZ_center", enabled = true)
    public void verifyCnForB2BDiffStateShipmentAsegUpdatePinNSZCenter(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "testingDummyData");
            requestPayload.put("pin", "110031");
            requestPayload.put("product_type", "B2B");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(60);

            //editing add
            HashMap<String, String> editAdd = new HashMap<>();
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

    @DataProvider(name = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_same_center_mapper_callback", parallel = false)
    public Object[][] Pdt_Forward_shipment_pin_updated_from_addfix_callback_same_center_mapper_callback() {
        return new Object[][]{
                { "Scenario:: B2C package shipment add update and then addfix callback update pin | center set from HQ", "B2C", "Delhi_Bhogal (Delhi)", "Delhi_Bhogal (Delhi)", "IND110014AAA", "null", "null", "Delhi", "Mapper0", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B package shipment add update and then addfix callback update pin | center set from HQ", "B2B", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "null", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy package shipment add update and then addfix callback update pin | center set from HQ", "Heavy", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "", "null", "true", "IND110092AAB", "null"},
                { "Scenario:: B2C MPS package shipment add update and then addfix callback update pin | center set from HQ", "B2C MPS", "Delhi_Bhogal (Delhi)", "Delhi_Bhogal (Delhi)", "IND110014AAA", "null", "null", "Delhi", "Mapper0", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B MPS shipment add update and then addfix callback update pin | center set from HQ", "B2B MPS", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper0", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "null", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy MPS shipment add update and then addfix callback update pin | center set from HQ", "Heavy MPS", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "", "null", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_same_center_mapper_callback" , enabled = true)
    public void verifyCnForShipmentPinUpdatedFromAddfixCallbackSameCenterMapperCallback(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "800005");
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
            Utilities.hardWait(10);
        }

        pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
        Assertions.assertIfNotNull("aseg", pkgdetails.aseg.ud);

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
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }



}