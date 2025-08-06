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

public class ClientWarehouseReturnAddressReturnServiceability3 extends BaseTest {

    private String returnPin = "110031";
    private String rasegCallbackAddress_GetNSZReturnCenterPin = "Theka road";

    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    protected static Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION Ret Add OC");
    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");

    @DataProvider(name = "ClientWarehouse_RtAdd_Return_b2c_diff_state_shipments_raseg_update_pin_NSZ_center", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Return_b2c_diff_state_shipments_raseg_update_pin_NSZ_center() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                { "Scenario:: In Transit state shipment", "In Transit", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND110092AAB", "null", false},
                { "Scenario:: Pending state shipment", "Pending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Returned state shipment", "Returned", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "RT-101", "RT", "Returned as per Client Instructions", "", "N", "1209", "null", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "PP", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "EOD-77", "PU", "Pickup completed", "", "N", "1209", "null", "null", "null", true},
                { "Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},
                { "Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Return_b2c_diff_state_shipments_raseg_update_pin_NSZ_center", enabled = true)
    public void verifyRcnB2CDiffStateShipmentRasegUpdatePinNSZCenter(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
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
            requestPayload.put("product_type", "B2C");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(10);

            //editing add
            HashMap<String, String> editRadd = new HashMap<>();
            editRadd.put("client", "return_address_return_client_warehouse");
            editRadd.put("return_add", rasegCallbackAddress_GetNSZReturnCenterPin); // aseg update pin to 122003
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



    @DataProvider(name = "ClientWarehouse_RtAdd_Return_b2b_diff_state_shipments_raseg_update_pin_NSZ_center", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Return_b2b_diff_state_shipments_raseg_update_pin_NSZ_center() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                { "Scenario:: In Transit state shipment", "In Transit", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND110092AAB", "null", false},
                { "Scenario:: Pending state shipment", "Pending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Returned state shipment", "Returned", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "RT-101", "RT", "Returned as per Client Instructions", "", "N", "1209", "null", "IND110092AAB", "null", true},
                {"Scenario:: PickupPending state shipment", "PickupPending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "PP", "Pincode updated by Addfix", "", "N", "1209", "false", "null", "null", false},
                {"Scenario:: PickedUp state shipment", "PickedUp", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "EOD-77", "PU", "Pickup completed", "", "N", "1209", "null", "null", "null", true},
                { "Scenario:: REPL RETURNED state shipment", "REPL RETURNED", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},
                { "Scenario:: REPL PICKUP state shipment", "REPL PICKUP", "Rudrapur_UdhamNgr_L (Uttarakhand)", "null", "IND263153AAC", "null", "null", "Rudrapur", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "N", "null", "null", "IND263153AAC", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                {"Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                {"Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                {"Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Return_b2b_diff_state_shipments_raseg_update_pin_NSZ_center", enabled = true)
    public void verifyRcnB2BDiffStateShipmentRasegUpdatePinNSZCenter(String Scenario, String state, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
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
            requestPayload.put("product_type", "B2B");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(60);

            //editing add
            HashMap<String, String> editRadd = new HashMap<>();
            editRadd.put("client", "return_address_return_client_warehouse");
            editRadd.put("return_add", rasegCallbackAddress_GetNSZReturnCenterPin); // aseg update pin to 122003
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




}
