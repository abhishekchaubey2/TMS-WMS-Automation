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

public class BuisnessFlows2 extends BaseTest {
    private static String UPL;
    static ApiController apiCtrl = new ApiController();
    private String waybill, bagId, tripId, dispatchId;
    private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String,String> manifestData = new HashMap<String,String>();
    public Map<String, Object> pkgFlowData;

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_sellerInvDate(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("sellerInvDate", value);
        manifestForFirstBuilder(manifestData, Scenario);
    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_pickupStartTime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pickupStartTime", value);
        manifestForFirstBuilder(manifestData, Scenario);
    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_pickupEndTime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pickupEndTime", value);
        manifestForFirstBuilder(manifestData, Scenario);
    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_pickupSlotCode(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pickupSlotCode", value);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_dropEndTime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("dropEndTime", value);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_dropSlotCode(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("dropSlotCode", value);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_dropStartTime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("dropStartTime", value);
        manifestForFirstBuilder(manifestData, Scenario);
    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_pickupendtime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pickupendtime", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_pickupslotcode(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pickupslotcode", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_dropstarttime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("dropstarttime", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_dropendtime(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("dropendtime", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_dropslotcode(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("dropslotcode", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_deliveryDate(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("deliveryDate", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "date_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_date_related_cases_for_estimatedArrivalDate(String Scenario, Object value) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("estimatedArrivalDate", value);
        manifestForSecondBuilder(manifestData, Scenario);

    }

    //Manifestation for different shippingMode and shippingMethod for all pdt and payment types
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_manifestation_for_surface_shipments(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        manifestData.put("shippingMode", "surface");
        manifestData.put("shippingMethod", "surface");
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Manifestation for different shippingMode and shippingMethod for all pdt and payment types
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_manifestation_for_air_shipments(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        manifestData.put("shippingMode", "express");
        manifestData.put("shippingMethod", "express");
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //verify otp and secured shipments cases
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verify_secured_otp_manifestation(String Scenario, Object pdt, Object paymentMode) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload
        manifestData.put("otp", "1234");
        manifestData.put("isSecure", true);
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentMode);
        manifestForSecondBuilder(manifestData, Scenario);

    }


}
