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


public class PdtBasedForwardServiceability3 extends BaseTest {

    private String asegCallbackAddress_GetNoMapperCenter = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";
    private String asegCallbackAddress_GetMapperCenter = "J649+26G, Janpath Rd, Janpath Road Area, Aurangzeb Road, New Delhi, Delhi 110001";

    //	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
    public String scenario;
    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    ArrayList<String> lightPdts = new ArrayList<String>(Arrays.asList("B2C", "NEXT_B2C_SURFACE", "FLASH_B2C_SURFACE", "FLASH_B2C_AIR", "HLD", "C2C-Lite", "DOC", "DOC_FLASH", "KYC"));
    ArrayList<String> heavyPdts = new ArrayList<String>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));

    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


    @DataProvider(name = "Pdt_Forward_b2c_diff_state_shipments_aseg_update_pin", parallel = false)
    public Object[][] Pdt_Forward_b2c_diff_state_shipments_aseg_update_pin() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB", true},
                { "Scenario:: Returned state shipment", "Returned", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "N", "1209", "null", "IND110092AAB", "null", true},
                { "Scenario:: PickupPending state shipment", "PickupPending", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "PP", "Pincode updated by Addfix", "", "N", "1209", "null", "null", "null", true},
                { "Scenario:: PickedUp state shipment", "PickedUp", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-203", "PU", "Package details changed by shipper", "", "N", "1209", "null", "null", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                { "Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "Pdt_Forward_b2c_diff_state_shipments_aseg_update_pin", enabled = true)
    public void verifyCnForB2CDiffStateShipmentAsegUpdatePin(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "testingDummyData");
            requestPayload.put("pin", "122001");
            requestPayload.put("product_type", "B2C");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(10);

            //editing add
            HashMap<String, String> editAdd = new HashMap<>();
            editAdd.put("add", asegCallbackAddress_GetNoMapperCenter); // aseg update pin to 122003
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


    @DataProvider(name = "Pdt_Forward_b2b_diff_state_shipments_aseg_update_pin", parallel = false)
    public Object[][] Pdt_Forward_b2b_diff_state_shipments_aseg_update_pin() {
        return new Object[][] {
                { "Scenario:: Manifest state shipment", "Manifest", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "null", "null", true},
                { "Scenario:: In Transit state shipment", "In Transit", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND110092AAB", "null", true},
                { "Scenario:: Pending state shipment", "Pending", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND122001AAA", "null", true},
                { "Scenario:: Returned state shipment", "Returned", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "N", "|dffdf448", "true", "IND110092AAB", "null", true},
                { "Scenario:: PickupPending state shipment", "PickupPending", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "PP", "Pincode updated by Addfix", "B2B", "N", "249", "true", "null", "null", true},
                { "Scenario:: PickedUp state shipment", "PickedUp", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "DTUP-203", "PU", "Package details changed by shipper", "B2B", "N", "|dffdf448", "true", "null", "null", true},

                //NR edit api won't update address
                { "Scenario:: Dispatched state shipment", "Dispatched", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "X-DDD3FD", "UD", "Out for delivery", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: Delivered state shipment", "Delivered", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "EOD-38", "DL", "Delivered to consignee", "", "N", "null", "false", "IND400093AAA", "IND400612AAA", false},
                { "Scenario:: RTO state shipment", "RTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-110", "DL", "RTO due to poor packaging", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: LOST state shipment", "LOST", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "LT-100", "LT", "Shipment LOST", "", "N", "null", "false", "IND110092AAB", "null", false},
                { "Scenario:: DTO state shipment", "DTO", "Rudrapur_UdhamNgr_L (Uttarakhand)", "Rudrapur_UdhamNgr_L (Uttarakhand)", "IND263153AAC", "null", "null", "Rudrapur", "null", "RT-111", "DL", "DTO due to poor packaging", "", "N", "null", "false", "null", "null", false},
                { "Scenario:: Cancelled state shipment", "Cancelled", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-205", "CN", "Package details changed by Delhivery", "B2B", "W", "1028", "true", "null", "null", false}

        };
    }

    @Test(dataProvider = "Pdt_Forward_b2b_diff_state_shipments_aseg_update_pin", enabled = true)
    public void verifyCnForB2BDiffStateShipmentAsegUpdatePin(String Scenario, String state, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid, Boolean enable) {

        if (enable) {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("add", "testingDummyData");
            requestPayload.put("pin", "122001");
            requestPayload.put("product_type", "B2B");
            waybill = diffStShpt.DifferentStateShipments(state, requestPayload);
            logInfo("Waybill " + waybill);
            Utilities.hardWait(60);

            //editing add
            HashMap<String, String> editAdd = new HashMap<>();
            editAdd.put("add", asegCallbackAddress_GetNoMapperCenter); // aseg update pin to 122003
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