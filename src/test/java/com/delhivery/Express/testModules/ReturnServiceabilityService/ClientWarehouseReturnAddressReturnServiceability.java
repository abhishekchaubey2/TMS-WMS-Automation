package com.delhivery.Express.testModules.ReturnServiceabilityService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.delhivery.core.utils.Utilities.logInfo;

public class ClientWarehouseReturnAddressReturnServiceability extends BaseTest {

    private String returnPin = "122001";
    private String rasegCallbackReturnAddress_updateRpin = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";

    ApiController apiCtrl = new ApiController();
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    protected static Map<String, Object> clientDetails = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION Ret Add Rt Cwh");
    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");


    @DataProvider(name = "ClientWarehouse_RtAdd_Return_Different_type_flow_shipment", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Return_Different_type_flow_shipment() {
        return new Object[][]{
                //data set
                {"Scenario:: COD Shipment manifested without pickup location", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: RVP Shipment manifested without pickup location", "B2C", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "X-ASP", "PP", "Pickup scheduled", "", "N", "1209", "null", "IND122001AAB", "null"},
                {"Scenario:: COD Shipment manifested with same pickup location as ret_add", "B2C WITH CWH", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: RVP Shipment manifested with same pickup location as ret_add", "B2C WITH CWH", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "X-ASP", "PP", "Pickup scheduled", "", "N", "1209", "null", "IND122001AAB", "null"},
                {"Scenario:: COD Shipment manifested with diff pickup location as ret_add", "B2C WITH CWH", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: RVP Shipment manifested with diff pickup location as ret_add", "B2C WITH CWH", "Delhi_Mayapuri_PC (Delhi)", "Delhi_Mayapuri_PC (Delhi)", "IND110064AAA", "null", "null", "Delhi", "null", "X-ASP", "PP", "Pickup scheduled", "", "N", "1209", "null", "IND122001AAB", "null"},
                {"Scenario:: Shipment FM done on same pickup location as ret_add", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: Shipment FM done on same different location as ret_add", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PPOM", "UD", "Shipment picked up", "", "N", "1209", "null", "IND110092AAB", "null"},

                {"Scenario:: No data Shipment FM done on same pickup location as ret_add", "NO DATA WITHOUT MANIFESTATION", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "null", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not             received from Client", "null", "null", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: No data Shipment FM done on diff pickup location as ret_add", "NO DATA WITHOUT MANIFESTATION", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "null", "null", "X-PPONM", "UD", "Shipment Physically Picked but data not             received from Client", "null", "null", "1209", "null", "IND400093AAA", "IND400612AAA"},

//                For this case no api will throw error of insufficient params, So case not valid
                {"Scenario:: No data Shipment manifestation done without pickup location", "NO DATA", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},

                {"Scenario:: No data Shipment manifestation done on same pickup location as ret_add", "NO DATA", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: No data Shipment manifestation done on diff pickup location as ret_add", "NO DATA", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "N", "1209", "null", "IND400093AAA", ""},
        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Return_Different_type_flow_shipment", enabled = true)
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
        requestPayload.put("return_pin", returnPin);
        requestPayload.put("client", "return_address_return_client_warehouse");

        if (Scenario.contains("COD")){
            requestPayload.put("payment_mode", "COD");
        }

        if (Scenario.contains("RVP")){
            requestPayload.put("payment_mode", "Pickup");
        }

//        if (Scenario.contains("manifested with same pickup location as ret_add")){
//            requestPayload.put("pickup_location", "rt_add_cwh");
//        }

        if (Scenario.contains("manifested with diff pickup location as ret_add")){
            requestPayload.put("pickup_location", clientDetails.get("client_warehouse1").toString());
        }

        if (Scenario.contains("No data")) {
//            if (Scenario.contains("same pickup location")){
//                requestPayload.put("cwh", "rt_add_cwh");
//
//            }
            if (Scenario.contains("diff pickup location")){
                requestPayload.put("cwh", clientDetails.get("client_warehouse1").toString());

            }
            if (Scenario.contains("without pickup location")){
                requestPayload.put("cwh", "");

            }

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
        if (Scenario.contains("Shipment FM done on diff pickup location as ret_add")){
            requestPayload.put("clientWarehouse", "other_cwh_id");

        }
        apiCtrl.fmOMSApi(waybill, "FMPICK", origin_center.get("SortCode").toString(),requestPayload);
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


    @DataProvider(name = "ClientWarehouse_RtAdd_Different_type_shipment_Manifestation", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Different_type_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B package", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy package", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2C MPS", "B2C MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD"	, "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: B2B MPS", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "B2B", "W", "1028", "true", "null", "null"},
                {"Scenario:: Heavy MPS", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "Heavy", "W", "1028", "true", "null", "null"},
                {"Scenario:: NO DATA shipment", "NO DATA", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "IND400093AAA", "IND400612AAA"},
                {"Scenario:: Partial manifest shipment", "PARTIALLY MANIFESTED", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "null", "null", "X-SPM", "UD", "Shipment partially manifested", "null", "null", "1209", "null", "IND400093AAA", "IND400612AAA"}
        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Different_type_shipment_Manifestation", enabled = true)
    public void verifyRcnForDifferentTypeShipmentManifestation(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "400059");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
        requestPayload.put("client", "return_address_return_client_warehouse");
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
            if (Scenario.contains("Scenario:: B2C MPS")){
                expected_values.put("rcnid", "null");
            }
            child_pkgDetails = apiCtrl.fetchPackageInfo(child_waybill,requestPayload);
            ServiceabilityKeysAssertions.assertServiceabilityKeys(child_pkgDetails, expected_values);
        }

    }


    @DataProvider(name = "ClientWarehouse_RtAdd_Different_Payment_Mode_shipment_Manifestation", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: Prepaid shipment", "Prepaid", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: REPL shipment", "REPL", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-UCI", "UD", "Manifest uploaded", "", "W", "1028", "null", "null", "null"},
                {"Scenario:: RVP shipment", "Pickup", "Delhi_Mayapuri_PC (Delhi)", "null", "IND110064AAA", "null", "null", "Delhi", "null", "X-ASP", "PP", "Pickup scheduled", "", "W", "1028", "null", "IND400093AAA", "null"},
        };
    }

    @Test(dataProvider = "ClientWarehouse_RtAdd_Different_Payment_Mode_shipment_Manifestation", enabled = true)
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
        requestPayload.put("client", "return_address_return_client_warehouse");
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

    @DataProvider(name = "ClientWarehouse_RtAdd_Return_weight_updated_shipment", parallel = false)
    public Object[][] ClientWarehouse_RtAdd_Return_weight_updated_shipment() {
        return new Object[][] {
                {"Scenario:: B2C package with weight less than 15 kg", "B2C", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: B2C package with weight more than 15 kg", "B2C", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B package with weight less than 15 kg", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "1209", "true", "IND110092AAB", "null"},
                {"Scenario:: B2B package with weight more than 15 kg", "B2B", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "1209", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy package with weight less than 15 kg", "Heavy", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Heavy", "N", "1209", "true", "IND110092AAB", "null"},
                {"Scenario:: B2C MPS package with weight more than 15 kg", "B2C MPS", "Mumbai MIDC (Maharashtra)", "null", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "", "N", "1209", "null", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package with weight less than 15 kg", "MPS WITH MCOUNT 1", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "1209", "true", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package with weight less than 15 kg", "B2B MPS WITH INTERNAL CHILD", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "1209", "true", "IND110092AAB", "null"},
                {"Scenario:: B2B MPS with weight less than 15 kg", "B2B MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "B2B", "N", "1209", "true", "IND110092AAB", "null"},
                {"Scenario:: Heavy MPS with weight less than 15 kg", "Heavy MPS", "Mumbai MIDC (Maharashtra)", "Mumbai MIDC (Maharashtra)", "IND400093AAA", "null", "null", "Mumbai", "null", "X-PIOM", "UD", "Shipment Recieved at Origin Center", "Heavy", "N", "1209", "true", "IND110092AAB", "null"}
        };
    }


    @Test(dataProvider = "ClientWarehouse_RtAdd_Return_weight_updated_shipment" , enabled = true)
    public void verifyRCnForWeightUpdatedShipment(String Scenario, String type, String expectedRCn, String expectedRCn1, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedCsNsl, String expectedCsSt, String expectedCsSr
            , String expectedCpdt, String expectedRgn, String expectedSc, String expectedSrv, String expectedOcid, String expectedWvcid) {

        // Manifesting a package
        List<String> waybills = new ArrayList<String>();
        String waybill, child_waybill = "";
        PackageDetail pkgdetails, child_pkgDetails;
        LinkedHashMap<String, String> expected_values;
        HashMap requestPayload = new HashMap<String, String>();
        requestPayload.put("pin", "122051");
        requestPayload.put("return_add", "test");
        requestPayload.put("return_pin", returnPin);
        requestPayload.put("client", "return_address_return_client_warehouse");
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
