package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.delhivery.Express.testModules.ManifestationService.manifestation.*;
import static com.delhivery.Express.testModules.PackageFlow.TestScript1.clData;
import static com.delhivery.core.utils.Utilities.getUniqueInt;
import static com.delhivery.core.utils.Utilities.logInfo;

public class BuisnessFlows extends BaseTest {
    private static String UPL;
    static ApiController apiCtrl = new ApiController();
    private String waybill, bagId, tripId, dispatchId;
    private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<String, String>();
    public Map<String, Object> pkgFlowData;

    //Different Manifestation types = B2C, B2B, heavy, DOC

    //Manifestation for all payment * pdt types
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_pdt_pt(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload


        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);


        manifestForFirstBuilder(manifestData, Scenario);

    }

    //manifestation of MPS package when packageCount=2 for all payment types
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void MPS_manifestation_pdt_pt(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        if (Scenario.contains("COD")) {
            manifestData.put("mpsAmount", 10);

        } else if (Scenario.contains("Prepaid")) {
            manifestData.put("mpsAmount", 0);
        }
        manifestData.put("pdt", pdt);
        manifestData.put("packageCount", 2);
        manifestData.put("paymentMode", paymentType);
        manifestForFirstBuilder(manifestData, Scenario+"MPS");

    }

    //manifestation for mandatory keys only for all payment types
    //manifestation = MandatoryKeys
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void MandatoryKeysManifestation(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        manifestUsingMandatoryKeys(manifestData, Scenario);

    }

    //Manifestation for MPS containg both master and child payload
    //manifestation = MPS
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void MPS_master_child_payload(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        MPSManifestationMasterChildPayload(manifestData, Scenario);

    }


    //Verify error message response on qc as true and payment Type is prepaid
    @Test
    public void verifyQCShipments() throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
            manifestData.put("qualitycheck", true);
            manifestData.put("paymentMode", "prepaid");
            manifestForSecondBuilder(manifestData, "");
    }

    //Verify error message response on timeBound as true and payment Type is prepaid
    @Test
    public void verifyTimeBound() throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
            manifestData.put("timeBound", true);
            manifestData.put("paymentMode", "prepaid");
            manifestForFirstBuilder(manifestData, "");

    }

    //Verify response on qc as false and payment Type is prepaid
    @Test
    public void verifyNonQCShipment() throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload

            manifestData.put("qualitycheck", false);
            manifestData.put("paymentMode", "prepaid");
            manifestForSecondBuilder(manifestData, "");

    }

    //Verify response on timeBound as false and payment Type is prepaid
    @Test
    public void verifyNonTimeBoundShipments() throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload

            manifestData.put("timeBound", false);
            manifestData.put("paymentMode", "prepaid");
            manifestForFirstBuilder(manifestData, "");

    }

    //Verify response on qc as true and paymentMode is pickup
    @Test
    public void verifyQCPickup() throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
            manifestData.put("qualitycheck", true);
            manifestData.put("paymentMode", "pickup");
            manifestForSecondBuilder(manifestData, "");

    }

    //Verify response on timeBound as true and paymentMode is pickup
    @Test
    public void verifyTimeBoundPickup() throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload

            manifestData.put("timeBound", true);
            manifestData.put("paymentMode", "pickup");
            manifestForFirstBuilder(manifestData, "");

    }

    //Manifestation of all endpoints
    //cmu/push
    @Test
    public void cmu_push_manifest() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("product_type", "B2C");
        data.put("payment_mode", "Prepaid");
        data.put("pin", "400059");

        ArrayList<String> cmuWaybills = apiCtrl.verifyCmuPush(data);
    }


    //v2.json (meesho manifestation)
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void v2_json_manifestation(String scenario, String pdt, String payment_mode) throws IOException {
        HashMap<String, String>manifestData = new HashMap<String, String>();
        manifestData.put("product_type", pdt);
        manifestData.put("payment_mode", payment_mode);
        manifestData.put("pin", "474011");
        UPL = apiCtrl.meeshoSuccessManifestApi(manifestData);
        logInfo("UPL generated " + UPL);
        Utilities.hardWait(30);
        apiCtrl.getManifestationUplData(UPL);

    }

    //ewbn related cases
    @Test(dataProvider = "ewbn_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void ewbn_related_cases(String scenario, String value, String ewbnRequired) throws JsonProcessingException {
        HashMap<String, Object> manifestDataNewService = new HashMap<String, Object>();
        HashMap<String, Object> manifestDataStaging = new HashMap<String, Object>();
        //Payload
        if (ewbnRequired.contains("yes")) {
            //New Service manifestData
            String createEWBN_new_service = apiCtrl.CreateEwbn("ewbnCreate", getUniqueInt(13));
            Utilities.hardWait(3);
            String fetchEWBN_new_service = apiCtrl.FetchEwbn("ewbnFetch", createEWBN_new_service);
            logInfo("Ewbn used in new service " + fetchEWBN_new_service);
            manifestDataNewService.put("pdt", "B2C");
            manifestDataNewService.put("ewbn", fetchEWBN_new_service);
            manifestDataNewService.put("paymentMode", "prepaid");
            manifestDataNewService.put("totalAmount", value);

            //Staging manifestData
            String createEWBN_staging = apiCtrl.CreateEwbn("ewbnCreate", getUniqueInt(13));
            Utilities.hardWait(3);
            String fetchEWBN_staging = apiCtrl.FetchEwbn("ewbnFetch", createEWBN_staging);
            logInfo("Ewbn used in staging " + fetchEWBN_staging);
            manifestDataStaging.put("pdt", "B2C");
            manifestDataStaging.put("ewbn", fetchEWBN_staging);
            manifestDataStaging.put("paymentMode", "prepaid");
            manifestDataStaging.put("totalAmount", value);

            manifestForEwbn(manifestDataNewService, manifestDataStaging, scenario);
        } else if (ewbnRequired.contains("no") && scenario.contains("already used ewbn")) {
            manifestDataStaging.put("pdt", "B2C");
            manifestDataStaging.put("paymentMode", "prepaid");
            manifestDataStaging.put("totalAmount", value);
            manifestDataStaging.put("ewbn", "321009238244");
            manifestForEwbn(manifestDataStaging, manifestDataStaging, scenario);
        } else {
            manifestDataStaging.put("pdt", "B2C");
            manifestDataStaging.put("paymentMode", "prepaid");
            manifestDataStaging.put("totalAmount", value);
            manifestForEwbn(manifestDataStaging, manifestDataStaging, scenario);

        }
    }



    //addfix response cases and address keys cases
    //Verify response on sending valid, invalid, fraud add
    //addresses= Address, return address, fm address, secondary address, seller address, billingAdd, chequeaddress, buybackAddress
    @Test(dataProvider = "addfix_callback_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_addfix_responses(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        if (Scenario.contains("Address")) {
            manifestData.put("add", value);
            manifestData.put("city", "Gurgaon");
            manifestData.put("state", "Haryana");
            manifestData.put("pin", "122001");
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("return address")) {
            manifestData.put("returnAdd", value);
            manifestData.put("returnCity", "Gurgaon");
            manifestData.put("returnState", "Haryana");
            manifestData.put("returnPin", "122001");
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("secondary address")) {
            manifestData.put("secondaryAdd", value);
            manifestData.put("secondaryCity", "Gurgaon");
            manifestData.put("secondaryState", "Haryana");
            manifestData.put("secondaryPin", "122001");
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("seller address")) {
            manifestData.put("sellerAdd", value);
            manifestData.put("sellerCity", "Gurgaon");
            manifestData.put("sellerState", "Haryana");
            manifestData.put("sellerPin", "122001");
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("fm address")) {
            manifestData.put("fmAddress", value);
            manifestData.put("fmCity", "Gurgaon");
            manifestData.put("fmState", "Haryana");
            manifestData.put("fmPin", "122001");
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("billingAdd")) {
            manifestData.put("billingAdd", value);
            manifestData.put("billingCity", "Gurgaon");
            manifestData.put("billingState", "Haryana");
            manifestData.put("billingPin", "122001");
            manifestForThirdBuilder(manifestData, Scenario);
        } else if (Scenario.contains("chequeaddress")) {
            manifestData.put("chequeaddress", value);
            manifestData.put("chequecity", "Gurgaon");
            manifestData.put("chequestate", "Haryana");
            manifestData.put("chequepin", "122001");
            manifestForThirdBuilder(manifestData, Scenario);
        } else if (Scenario.contains("buybackAddress")) {
            manifestData.put("buybackAddress", value);
            manifestData.put("buybackCity", "Gurgaon");
            manifestData.put("buybackState", "Haryana");
            manifestData.put("buybackPin", "122001");
            manifestForSecondBuilder(manifestData, Scenario);
        }
    }


    //ucid callback responses
    //Verify response on sending valid, invalid, fraud phone number
    //Verify for phone, return phone, fm phone
    @Test(dataProvider = "ucid_callback_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_ucid_callback_responses(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        if (Scenario.contains("Phone")) {
            manifestData.put("phone", value);
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("return phone")) {
            manifestData.put("returnPhone", value);
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("fm phone")) {
            manifestData.put("fmPhone", value);
            manifestForFirstBuilder(manifestData, Scenario);
        }
    }

    //uaid callback responses
    //Verify response on sending valid, invalid, fraud address
    //Verify for address, return address, fm address, secondary address
    @Test(dataProvider = "uaid_callback_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_uaid_callback_responses(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        if (Scenario.contains("Address")) {
            manifestData.put("address", value);
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("return address")) {
            manifestData.put("returnAddress", value);
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("fm address")) {
            manifestData.put("fmAddress", value);
            manifestForFirstBuilder(manifestData, Scenario);
        } else if (Scenario.contains("secondary address")) {
            manifestData.put("secondaryAddress", value);
            manifestForFirstBuilder(manifestData, Scenario);
        }
    }

    //catfight callback response
    //Verify response on sending valid, invalid, fraud prd
    @Test(dataProvider = "catfight_callback_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_catfight_callback_responses(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("productsDesc", value);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Od Tat callback cases
    //Verify response on manifesting for multiple pincodes
    @Test(dataProvider = "Od_Tat_callback_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_od_tat_callback_responses(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pin", value);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Verify response on passing different date related keys
    //keys in manifestForFirstBuilder = orderDate, sellerInvDate, pickupStartTime, pickupEndTime, pickupSlotCode, dropEndTime, dropSlotCode, dropStartTime
    //keys in manifestForSecondBuilder = pickupendtime, pickupslotcode, dropstarttime, dropendtime, dropslotcode, deliveryDate, estimatedArrivalDate
    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_orderDate(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("orderDate", value);
        manifestForFirstBuilder(manifestData, Scenario);
    }





}
















