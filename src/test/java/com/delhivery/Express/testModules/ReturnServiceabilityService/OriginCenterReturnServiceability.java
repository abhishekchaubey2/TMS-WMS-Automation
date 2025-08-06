package com.delhivery.Express.testModules.ReturnServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

public class OriginCenterReturnServiceability extends BaseTest {

    private String rasegCallbackAddress = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";

    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    protected static Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION Ret Add OC");
    protected static Map<String, Object> same_center_from_cwh_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");
    protected static Map<String, Object> diff_center_from_cwh_center = YamlReader.getYamlValues("Centers.East_Delhi");


    @DataProvider(name = "Origin_Center_Return_Different_type_flow_shipment", parallel = false)
    public Object[][] Origin_Center_Return_Different_type_flow_shipment() {
        return new Object[][]{
                //data set
                {"Scenario:: Shipment manifested without pickup location", "B2C", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "null", "null"},
                {"Scenario:: Shipment manifested without pickup location FM done", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Shipment manifested with pickup location", "B2C WITH CWH", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND400093AAA", "null"},

                //rcn not setting as oc but pl incoming center
                {"Scenario:: Manifested shipment FM done on same location and pickup_location", "B2C WITH CWH", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Manifested shipment FM done on diff location and same pickup_location", "B2C WITH CWH", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: Manifested shipment FM done on same location and diff pickup_location", "B2C WITH CWH", "Del_Okhla_PC (DELHI)", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Manifested shipment FM done on diff location and pickup_location", "B2C WITH CWH", "Del_Okhla_PC (DELHI)", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Del_Okhla_PC (DELHI)", "IND110044AAB", "Delhi", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},

                //data set
                {"Scenario:: No data FM incoming shipment", "NO DATA WITHOUT MANIFESTATION", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "null", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not             received from Client", "null", "null", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: No data Manifested shipment", "NO DATA", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: No data GI shipment", "NO DATA", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
        };
    }

    @Test(dataProvider = "Origin_Center_Return_Different_type_flow_shipment", enabled = true)
    public void verifyRcnForDifferentTypeFlowShipment(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "122001");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", "122001");
        if (type.contains("NO DATA")){
            requestPayload.put("client", "return_address_origin_center_No_Data");

        }else{
            requestPayload.put("client", "return_address_origin_center");

        }
        waybills = diffTypeShipment.DifferentTypeShipments(type, requestPayload);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);

        // setting the default value of child_waybill as master waybill
        child_waybill = null;
        if (waybills.size() > 1) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }

        // Performing FM of the package
        if (Scenario.contains("FM done on same location and pickup_location") || Scenario.contains("Shipment manifested without pickup location FM done")){
            apiCtrl.fmOMSApi(waybill, "FMPICK", same_center_from_cwh_center.get("SortCode").toString(),requestPayload);

        } else if (Scenario.contains("FM done on diff location and same pickup_location")) {
            apiCtrl.fmOMSApi(waybill, "FMPICK", diff_center_from_cwh_center.get("SortCode").toString(),requestPayload);
            Utilities.hardWait(5);
            apiCtrl.QcWtApi(waybill, 0.1, "sorter",requestPayload);

        } else if (Scenario.contains("FM done on same location and diff pickup_location")) {
            requestPayload.put("clientWarehouse", clientDetails.get("cwh_uuid1").toString());
            apiCtrl.fmOMSApi(waybill, "FMPICK", same_center_from_cwh_center.get("SortCode").toString(),requestPayload);
            Utilities.hardWait(5);
            apiCtrl.QcWtApi(waybill, 0.1, "sorter",requestPayload);

        } else if (Scenario.contains("FM done on diff location and pickup_location")) {
            requestPayload.put("clientWarehouse", clientDetails.get("cwh_uuid1").toString());
            apiCtrl.fmOMSApi(waybill, "FMPICK", diff_center_from_cwh_center.get("SortCode").toString(),requestPayload);
            Utilities.hardWait(5);
            apiCtrl.QcWtApi(waybill, 0.1, "sorter",requestPayload);

        } else if (Scenario.contains("GI")) {
            Utilities.hardWait(10);
            apiCtrl.giApi(waybill, diff_center_from_cwh_center.get("Name").toString(),requestPayload);
            if (waybills.size() > 1) {
                apiCtrl.giApi(child_waybill, diff_center_from_cwh_center.get("Name").toString(),requestPayload);
            }
            
        }
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

        if (waybills.size() > 1) {
            logInfo("Checking child package keys :: "+child_waybill);
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }


    @DataProvider(name = "Origin_Center_Return_Different_type_shipment_Manifestation", parallel = false)
    public Object[][] Origin_Center_Return_Different_type_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B package", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Heavy package", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "Heavy", "N", "|dffdf448", "true", "IND400093AAA", "null"},
                {"Scenario:: B2C MPS", "B2C MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B MPS", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Heavy MPS", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "Heavy", "N", "|dffdf448", "true", "IND400093AAA", "null"},
                {"Scenario:: NO DATA shipment", "NO DATA", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Partial manifest shipment", "PARTIALLY MANIFESTED", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "null", "null", "X-PPOM", "UD", "Shipment picked up", "null", "null", "1209", "null", "IND400093AAA", "IND400612AAA"}
        };
    }

    @Test(dataProvider = "Origin_Center_Return_Different_type_shipment_Manifestation", enabled = true)
    public void verifyRCnForDifferentTypeShipmentManifestation(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "122001");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", "122001");
        if (type.contains("NO DATA") || type.contains("PARTIALLY MANIFESTED")){
            requestPayload.put("client", "return_address_origin_center_No_Data");

        }else{
            requestPayload.put("client", "return_address_origin_center");

        }
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

        //fm incoming of the shipment
        apiCtrl.fmOMSApi(waybill, "FMPICK", same_center_from_cwh_center.get("SortCode").toString(),requestPayload);

        // Performing FM of child package if present
        if (waybills.size() > 1) {
            apiCtrl.fmOMSApi(child_waybill, "FMPICK", same_center_from_cwh_center.get("SortCode").toString(),requestPayload);
        }
        Utilities.hardWait(2);

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



    @DataProvider(name = "Origin_Center_Return_Different_Payment_Mode_shipment_Manifestation", parallel = false)
    public Object[][] Origin_Center_Return_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: Prepaid shipment", "Prepaid", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: REPL shipment", "REPL", "Gurgaon (Haryana)", "Gurgaon (Haryana)", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: RVP shipment", "Pickup", "Gurgaon (Haryana)", "null", "IND122001AAB", "null", "null", "Gurgaon", "null", "X-ASP", "PP", "Pickup scheduled", "", "W", "1028", "null", "IND400093AAA", "null"},
        };
    }

    @Test(dataProvider = "Origin_Center_Return_Different_Payment_Mode_shipment_Manifestation", enabled = true)
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
        requestPayload.put("return_pin", "122001");
        requestPayload.put("client", "return_address_origin_center");
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


    @DataProvider(name = "Origin_Center_Return_weight_updated_shipment", parallel = false)
    public Object[][] Origin_Center_Return_weight_updated_shipment() {
        return new Object[][] {
                {"Scenario:: B2C package with weight less than 15 kg", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-231", "UD", "Center changed by system", "Heavy", "N", "|dffdf448", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2C package with weight more than 15 kg", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-231", "UD", "Center changed by system", "Heavy", "N", "|dffdf448", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B package with weight less than 15 kg", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B package with weight more than 15 kg", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Heavy package with weight less than 15 kg", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Heavy", "N", "|dffdf448", "true", "IND400093AAA", "null"},
                {"Scenario:: B2C MPS package with weight more than 15 kg", "B2C MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "DTUP-231", "UD", "Center changed by system", "Heavy", "N", "|dffdf448", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package with weight less than 15 kg", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package with weight less than 15 kg", "B2B MPS WITH INTERNAL CHILD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: B2B MPS with weight less than 15 kg", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "|dffdf448", "true", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Heavy MPS with weight less than 15 kg", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Heavy", "N", "|dffdf448", "true", "IND400093AAA", "null"}
        };
    }


    @Test(dataProvider = "Origin_Center_Return_weight_updated_shipment" , enabled = true)
    public void verifyRCnForWeightUpdatedShipment(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "122001");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", "122001");
        requestPayload.put("client", "return_address_origin_center");
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
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        // Performing FM of the package
        apiCtrl.fmOMSApi(waybill, "FMPICK", same_center_from_cwh_center.get("SortCode").toString(),requestPayload);
        Utilities.hardWait(2);
        apiCtrl.giApi(waybill, same_center_from_cwh_center.get("Name").toString(),requestPayload);
        Utilities.hardWait(2);

        // Performing FM of child package if present
        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            apiCtrl.fmOMSApi(child_waybill, "FMPICK", same_center_from_cwh_center.get("SortCode").toString(),requestPayload);
            Utilities.hardWait(5);
            apiCtrl.giApi(waybill, same_center_from_cwh_center.get("Name").toString(),requestPayload);
            Utilities.hardWait(2);
        }

        // Update weight -> for master only
        if (Scenario.contains("weight less than 15 kg")) {
            apiCtrl.QcWtApi(waybill, 14999.12, "sorter",requestPayload);
            Utilities.hardWait(10);
        } else if (Scenario.contains("weight more than 15 kg")) {
            apiCtrl.QcWtApi(waybill, 15000.12, "sorter",requestPayload);
            Utilities.hardWait(70);
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
