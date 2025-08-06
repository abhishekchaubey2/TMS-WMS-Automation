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


public class PincodeReturnServiceability3 extends BaseTest {

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

    @DataProvider(name = "Pincode_Return_shipment_rpin_not_updated_from_return_addfix_callback", parallel = false)
    public Object[][] Pincode_Return_shipment_rpin_not_updated_from_return_addfix_callback() {
        return new Object[][]{
                {"Scenario:: B2C package shipment return addupdate and then return addfix callback not update pin", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "W", "1028", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B package shipment return addupdate and then return addfix callback not update pin", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy package shipment return addupdate and then return addfix callback not update pin", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "Heavy", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: B2C MPS package shipment return addupdate and then return addfix callback not update pin", "B2C MPS", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "W", "1028", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS shipment return addupdate and then return addfix callback not update pin", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy MPS shipment return addupdate and then return addfix callback not update pin", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-203", "RT", "Package details changed by shipper", "Heavy", "W", "1028", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pincode_Return_shipment_rpin_not_updated_from_return_addfix_callback" , enabled = true)
    public void verifyRcnForShipmentRpinNOTUpdatedFromReturnAddfixCallback(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
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
        Utilities.hardWait(2);
        apiCtrl.ApplyNsl(waybills, "RT", "RT-101",requestPayload);

        //editing radd such that raseg callback would not change the rpin to a new rpin
        HashMap<String, String> editRadd = new HashMap<>();
        editRadd.put("return_add", rasegCallbackReturnAddress_notUpdateRpin); // raseg not update rpin
        apiCtrl.EditApi(waybill, editRadd);

        //waiting period for retaddfix callback
        Utilities.hardWait(returnAddfixCallbackDelayTime);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
        Assertions.assertIfNotNull("raseg", pkgdetails.raseg.ud);

        if (!type.contains("B2C")) {
            Utilities.hardWait(70);
        } else {
            Utilities.hardWait(10);
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

        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }


//    DL_NWDLH_DLH_visdelhi1138	110011	B2C	IND110014AAA	Delhi_Bhogal
//    DL_NWDLH_DLH_visdelhi1138	110011	B2B	IND110049AAA	Delhi_shahpurJat

    @DataProvider(name = "Pincode_Return_shipment_rpin_updated_from_return_addfix_callback", parallel = false)
    public Object[][] Pincode_Return_shipment_rpin_updated_from_return_addfix_callback() {
        return new Object[][]{
                {"Scenario:: B2C package shipment return addupdate and then return addfix callback update pin", "B2C", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "W", "1028", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B package shipment return addupdate and then return addfix callback update pin", "B2B", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy package shipment return addupdate and then return addfix callback update pin", "Heavy", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "Heavy", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: B2C MPS package shipment return addupdate and then return addfix callback update pin", "B2C MPS", "Del_B_RPC (Delhi)", "Del_B_RPC (Delhi)", "IND110037AAB", "null", "null", "Delhi", "null", "DTUP-203", "RT", "Package details changed by shipper", "", "W", "1028", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS shipment return addupdate and then return addfix callback update pin", "B2B MPS", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "B2B", "W", "1028", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy MPS shipment return addupdate and then return addfix callback update pin", "Heavy MPS", "Gurgaon_Kadipur (Haryana)", "Gurgaon_Kadipur (Haryana)", "IND122001AAD", "null", "null", "Gurgaon", "null", "DTUP-203", "RT", "Package details changed by shipper", "Heavy", "W", "1028", "true", "IND110092AAB", "null"},
        };
    }


    @Test(dataProvider = "Pincode_Return_shipment_rpin_updated_from_return_addfix_callback" , enabled = true)
    public void verifyRcnForShipmentRpinUpdatedFromReturnAddfixCallback(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid){

        // Manifesting a package
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        List<String> waybills = new ArrayList<String>();
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
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
        Utilities.hardWait(2);
        apiCtrl.ApplyNsl(waybills, "RT", "RT-101",requestPayload);

        //editing radd such that raseg callback would change the rpin to a new rpin
        HashMap<String, String> editRadd = new HashMap<>();
        editRadd.put("return_add", rasegCallbackReturnAddress_updateRpin); // raseg update rpin
        apiCtrl.EditApi(waybill, editRadd);

        //waiting period for retaddfix callback
        Utilities.hardWait(returnAddfixCallbackDelayTime);
        pkgdetails = apiCtrl.fetchPackageInfo(waybill,requestPayload);
        Assertions.assertIfNotNull("raseg", pkgdetails.raseg.ud);

        if (!type.contains("B2C")) {
            Utilities.hardWait(70);
        } else {
            Utilities.hardWait(10);
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

        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }

    @DataProvider(name = "Pincode_Return_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", parallel = false)
    public Object[][] Pincode_Return_edt_vs_staging_package_keys_comparison() {
        return new Object[][] {
                { "Scenario:: B2C package", "B2C"},
                { "Scenario:: B2C MPS package", "B2C MPS"},
                { "Scenario:: B2B package", "B2B"},
                { "Scenario:: B2B MPS package", "B2B MPS"},
                { "Scenario:: HEAVY package", "HEAVY"},
                { "Scenario:: HEAVY MPS package", "HEAVY MPS"}
        };
    }

    @Test(dataProvider = "Pincode_Return_EDT_VS_STAGING_PACKAGE_KEYS_COMPARISON", enabled = true)
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