package com.delhivery.Express.testModules.ServiceabilityService;

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


public class PdtBasedForwardServiceability4 extends BaseTest {
    private String asegCallbackAddress_notUpdatePin = "testingServiceability12345";
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


    @DataProvider(name = "Pdt_Forward_shipment_pin_not_updated_from_addfix_callback", parallel = false)
    public Object[][] Pdt_Forward_shipment_pin_not_updated_from_addfix_callback() {
        return new Object[][]{
                { "Scenario:: B2C package shipment add update and then addfix callback not update pin | center set from HQ", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "N", "1209", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B package shipment add update and then addfix callback not update pin | center set from HQ", "B2B", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "DTUP-203", "UD", "Package details changed by shipper", "B2B", "N", "|dffdf448", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy package shipment add update and then addfix callback not update pin | center set from HQ", "Heavy", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "DTUP-203", "UD", "Package details changed by shipper", "Heavy", "N", "|dffdf448", "true", "IND110092AAB", "null"},
                { "Scenario:: B2C MPS package shipment add update and then addfix callback not update pin | center set from HQ", "B2C MPS", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-203", "UD", "Package details changed by shipper", "", "N", "1209", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B MPS shipment add update and then addfix callback not update pin | center set from HQ", "B2B MPS", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "DTUP-203", "UD", "Package details changed by shipper", "B2B", "N", "|dffdf448", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy MPS shipment add update and then addfix callback not update pin | center set from HQ", "Heavy MPS", "GGN_DPC (Haryana)", "GGN_DPC (Haryana)", "IND122001AAA", "null", "null", "Gurgaon", "null", "DTUP-203", "UD", "Package details changed by shipper", "Heavy", "N", "|dffdf448", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pdt_Forward_shipment_pin_not_updated_from_addfix_callback" , enabled = true)
    public void verifyCnForShipmentPinNOTUpdatedFromAddfixCallback(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
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
        editAdd.put("add", asegCallbackAddress_notUpdatePin); // aseg update pin to 110011
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
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }


    //    DL_NWDLH_DLH_visdelhi1138	110011	B2C	IND110014AAA	Delhi_Bhogal
//    DL_NWDLH_DLH_visdelhi1138	110011	B2B	IND110049AAA	Delhi_shahpurJat
    @DataProvider(name = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_mapper_callback_cn", parallel = false)
    public Object[][] Pdt_Forward_shipment_pin_updated_from_addfix_callback_mapper_callback_cn() {
        return new Object[][]{
                { "Scenario:: B2C package shipment add update and then addfix callback update pin | center set from HQ", "B2C", "Delhi_Bhogal (Delhi)", "Delhi_Bhogal (Delhi)", "IND110014AAA", "null", "null", "Delhi", "Mapper0", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B package shipment add update and then addfix callback update pin | center set from HQ", "B2B", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "null", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy package shipment add update and then addfix callback update pin | center set from HQ", "Heavy", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "", "null", "true", "IND110092AAB", "null"},
                { "Scenario:: B2C MPS package shipment add update and then addfix callback update pin | center set from HQ", "B2C MPS", "Delhi_Bhogal (Delhi)", "Delhi_Bhogal (Delhi)", "IND110014AAA", "null", "null", "Delhi", "Mapper0", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B MPS shipment add update and then addfix callback update pin | center set from HQ", "B2B MPS", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper0", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "null", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy MPS shipment add update and then addfix callback update pin | center set from HQ", "Heavy MPS", "Delhi_shahpurJat (Delhi)", "Delhi_shahpurJat (Delhi)", "IND110049AAA", "null", "null", "Delhi", "Mapper", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "", "null", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_mapper_callback_cn" , enabled = true)
    public void verifyCnForShipmentPinUpdatedFromAddfixCallbackMapperCallbackCenter(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "110012");
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
        editAdd.put("add", asegCallbackAddress_GetMapperCenter); // aseg update pin to 110011
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



    @DataProvider(name = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_restrict_mapper_callback_HQ_cn", parallel = false)
    public Object[][] Pdt_Forward_shipment_pin_updated_from_addfix_callback_restrict_mapper_callback_HQ_cn() {
        return new Object[][]{
                { "Scenario:: B2C package shipment add update and then addfix callback update pin | center set from HQ", "B2C", "East Delhi (Delhi)", "East Delhi (Delhi)", "IND110092AAB", "null", "null", "Delhi", "", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B package shipment add update and then addfix callback update pin | center set from HQ", "B2B", "Delhi_Paschim_DC (Delhi)", "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "null", "null", "Delhi", "", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "1395", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy package shipment add update and then addfix callback update pin | center set from HQ", "Heavy", "Delhi_Paschim_DC (Delhi)", "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "null", "null", "Delhi", "", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "", "1395", "true", "IND110092AAB", "null"},
                { "Scenario:: B2C MPS package shipment add update and then addfix callback update pin | center set from HQ", "B2C MPS", "East Delhi (Delhi)", "East Delhi (Delhi)", "IND110092AAB", "null", "null", "Delhi", "", "DTUP-219", "UD", "Pincode updated by Addfix", "", "", "KCF/MDL", "null", "IND110092AAB", "null"},
                { "Scenario:: B2B MPS shipment add update and then addfix callback update pin | center set from HQ", "B2B MPS", "Delhi_Paschim_DC (Delhi)", "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "null", "null", "Delhi", "", "DTUP-219", "UD", "Pincode updated by Addfix", "B2B", "", "1395", "true", "IND110092AAB", "null"},
                { "Scenario:: Heavy MPS shipment add update and then addfix callback update pin | center set from HQ", "Heavy MPS", "Delhi_Paschim_DC (Delhi)", "Delhi_Paschim_DC (Delhi)", "IND110087AAA", "null", "null", "Delhi", "", "DTUP-219", "UD", "Pincode updated by Addfix", "Heavy", "", "1395", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pdt_Forward_shipment_pin_updated_from_addfix_callback_restrict_mapper_callback_HQ_cn" , enabled = true)
    public void verifyCnForShipmentPinUpdatedFromAddfixCallbackRestrictMapperCallbackHQCenter(String Scenario, String type, String expectedCn, String expectedCn1, String expectedCnid, String expectedDpc, String expectedDpcid, String expectedCnc, String expectedCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("client", "restrict_mapper_callback_client");
        requestPayload.put("pin", "110012");
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
        editAdd.put("client", "restrict_mapper_callback_client");
        editAdd.put("add", asegCallbackAddress_GetMapperCenter); // aseg update pin to 110011
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

    @DataProvider(name = "Pdt_Forward_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", parallel = false)
    public Object[][] Pdt_Forward_edt_vs_staging_package_keys_comparison() {
        return new Object[][] {
                { "Scenario:: B2C package", "B2C"},
                { "Scenario:: B2C MPS package", "B2C MPS"},
                { "Scenario:: B2B package", "B2B"},
                { "Scenario:: B2B MPS package", "B2B MPS"},
                { "Scenario:: HEAVY package", "HEAVY"},
                { "Scenario:: HEAVY MPS package", "HEAVY MPS"}
        };
    }

    @Test(dataProvider = "Pdt_Forward_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", enabled = false)
    public void edtVsStagingPkgKeysComparison(String Scenario, String type) {
        List<String> edt_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();


        // Manifesting a package
        String waybill, child_waybill = "";
        LinkedHashMap<String, String> expected_values;

        for (int i = 0; i < 2; i++) {
            List<String> waybills = new ArrayList<String>();
            HashMap<String, String> requestPayload = new HashMap();
            requestPayload.put("add", "sector-99");
            requestPayload.put("pin", "122001");

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