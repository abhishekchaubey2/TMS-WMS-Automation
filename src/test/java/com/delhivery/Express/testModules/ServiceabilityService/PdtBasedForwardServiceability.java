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


public class PdtBasedForwardServiceability extends BaseTest {

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


    @DataProvider(name = "Pdt_Forward_Different_type_shipment_Manifestation", parallel = false)
    public Object[][] Pdt_Forward_Different_type_shipment_Manifestation() {
        return new Object[][]{
                { "Scenario:: B2C package", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: B2B package", "B2B", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: Heavy package", "Heavy", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: B2C MPS", "B2C MPS", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD" , "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: B2B MPS", "B2B MPS", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: Heavy MPS", "Heavy MPS", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: NO DATA shipment", "NO DATA", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND122001AAB", "IND122001AAB"},
                { "Scenario:: Partial manifest shipment", "PARTIALLY MANIFESTED", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "null", "null", "X-SPM", "UD", "Shipment partially manifested", "null", "null", "1209", "null", "IND122001AAB", "IND122001AAB"}
        };
    }

    @Test(dataProvider = "Pdt_Forward_Different_type_shipment_Manifestation", enabled = true)
    public void verifyCnForDifferentTypeShipmentManifestation(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("add", "sector-99");
        requestPayload.put("pin", "122001");
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

        if (waybills.size() > 1) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }

    @DataProvider(name = "Pdt_Forward_Different_Payment_Mode_shipment_Manifestation", parallel = false)
    public Object[][] Pdt_Forward_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                { "Scenario:: COD shipment", "COD", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: Prepaid shipment", "Prepaid", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: REPL shipment", "REPL", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: RVP shipment", "Pickup", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-ASP", "PP", "Pickup scheduled", "", "N", "1209", "null", "IND122001AAB", "null"},
        };
    }

    @Test(dataProvider = "Pdt_Forward_Different_Payment_Mode_shipment_Manifestation", enabled = true)
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
        logInfo("Checking package keys :: "+waybill);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails, expected_values);

    }

    @DataProvider(name = "Pdt_Forward_Different_Product_type_shipment_Manifestation", parallel = false)
    public Object[][] Pdt_Forward_Different_Product_type_shipment_Manifestation() {
        return new Object[][]{
                { "Scenario:: B2C package", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: B2B package", "B2B", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: Freight package", "Freight", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "Freight", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: Heavy package", "Heavy", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "N", "|dffdf448", "true", "null", "null"},
                { "Scenario:: NEXT_B2C_SURFACE package", "NEXT_B2C_SURFACE", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: FLASH_B2C_SURFACE package", "FLASH_B2C_SURFACE", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: FLASH_B2C_AIR package", "FLASH_B2C_AIR", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: HLD package", "HLD", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "HLD", "N", "1209", "null", "null", "null"},
                { "Scenario:: DOC package", "DOC", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "DOC", "N", "1209", "null", "null", "null"},
                { "Scenario:: DOC_FLASH package", "DOC_FLASH", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: KYC package", "KYC", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                { "Scenario:: Flash_Heavy package", "Flash_Heavy", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"}
        };
    }

    @Test(dataProvider = "Pdt_Forward_Different_Product_type_shipment_Manifestation", enabled = true)
    public void verifyCnForDiffernetProductTypeShipmentManifestation(String Scenario, String productType, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("product_type", productType);
        requestPayload.put("add", "sector-99");
        requestPayload.put("pin", "122001");
        waybills = diffTypeShipment.DifferentTypeShipments("", requestPayload);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);

        if (heavyPdts.contains(productType)) {
            Utilities.hardWait(80);
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

    }

    @DataProvider(name = "Pdt_Forward_shipment_manifestation_with_diff_pin_data", parallel = false)
    public Object[][] Pdt_Forward_shipment_manifestation_with_diff_pin_data() {
        return new Object[][]{
                { "Scenario:: B2C package with pin having no data", "B2C", "133100", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "", "null", "null", "null", "null", "null"},
                { "Scenario:: B2C package with pin having center as invalid", "B2C", "133101", "test50_facility (Haryana)", "test50_facility (Haryana)", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "", "null", "null", "null", "null", "null"},
                { "Scenario:: B2C package with pin having center as NSZ", "B2C", "133102", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "", "null", "null", "null", "null", "null"},
                { "Scenario:: B2C package with pin having center as valid", "B2C", "133001", "East Delhi (Delhi)", "East Delhi (Delhi)", "IND110092AAB", "null", "null", "Delhi", "null", "X-UCI", "UD", "Manifest uploaded", "", "", "KCF/MDL", "null", "null", "null"},
                { "Scenario:: B2B package with pin having no data", "B2B", "133100", "", "NSZ", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "null", "null", "true", "null", "null"},
                { "Scenario:: B2B package with pin having center as invalid", "B2B", "133101", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-NSZ", "UD", "Non-serviceable location", "B2B", "null", "null", "true", "null", "null"},
                { "Scenario:: B2B package with pin having center as NSZ", "B2B", "133102", "NSZ", "NSZ", "null", "null", "null", "null", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "null", "null", "true", "null", "null"},
                { "Scenario:: B2B package with pin having center as valid", "B2B", "133001", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "N", "|dffdf448", "true", "null", "null"}
        };
    }

    @Test(dataProvider = "Pdt_Forward_shipment_manifestation_with_diff_pin_data", enabled = true)
    public void verifyCnForDiffPinDataShipmentManifestation(String Scenario, String productType, String pin, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        try {
            String waybill, child_waybill = "";
            PackageDetail pkgdetails, child_pkgDetails;
            LinkedHashMap<String, String> expected_values;
            List<String> waybills = new ArrayList<String>();
            HashMap requestPayload = new HashMap<String, String>();
            requestPayload.put("pin", pin);
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
        } catch (Exception e) {
            if (productType.equalsIgnoreCase("b2b") && e.toString().contains(pin+" is not serviceable")) {
                logInfo("error :: "+e);
            } else {
                throw new RuntimeException(e);
            }
        }

    }


    @DataProvider(name = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_HQ_CN_no_mapper_callback", parallel = false)
    public Object[][] Pdt_Forward_shipment_pin_updated_from_addfix_callback_HQ_CN_no_mapper_callback() {
        return new Object[][]{
                { "Scenario:: B2C package shipment add update and then addfix callback update pin | center set from HQ", "B2C", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B package shipment add update and then addfix callback update pin | center set from HQ", "B2B", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy package shipment add update and then addfix callback update pin | center set from HQ", "Heavy", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "N", "249", "true", "IND110092AAB", "null"},
                { "Scenario:: B2C MPS package shipment add update and then addfix callback update pin | center set from HQ", "B2C MPS", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "", "N", "1209", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B MPS shipment add update and then addfix callback update pin | center set from HQ", "B2B MPS", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "N", "249", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy MPS shipment add update and then addfix callback update pin | center set from HQ", "Heavy MPS", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "N", "249", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_HQ_CN_no_mapper_callback" , enabled = true)
    public void verifyCnForShipmentPinUpdatedFromAddfixCallbackHQCnNoMapperCallback(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
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

        //editing add such that aseg callback would change the pin to a new pin
        HashMap<String, String> editAdd = new HashMap<>();
        editAdd.put("add", asegCallbackAddress_GetNoMapperCenter); // aseg update pin to 122003
        apiCtrl.EditApi(waybill, editAdd);

        //waiting period for addfix callback
        Utilities.hardWait(addfixCallbackDelayTime);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
        Assertions.assertIfNotNull("aseg", pkgdetails.aseg.ud);

        if (!type.contains("B2C")) {
            Utilities.hardWait(70);
        } else {
            Utilities.hardWait(10);
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
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }


}