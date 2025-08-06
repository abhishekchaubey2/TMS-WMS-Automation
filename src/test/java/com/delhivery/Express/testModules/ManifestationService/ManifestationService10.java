package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForSecondBuilder;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForThirdBuilder;

public class ManifestationService10 extends BaseTest {
    protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void freightChargeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("freightCharge", ValidPayloadData.get("freightCharge").toString());
        }else {
            manifestData.put("freightCharge", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void landMarkKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("landMark", ValidPayloadData.get("landMark").toString());
        }else {
            manifestData.put("landMark", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void addressIdKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("addressId", ValidPayloadData.get("addressId").toString());
        }else {
            manifestData.put("addressId", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void customerIdKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("customerId", ValidPayloadData.get("customerId").toString());
        }else {
            manifestData.put("customerId", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void internationalKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("international", ValidPayloadData.get("international").toString());
        }else {
            manifestData.put("international", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void e2eIdKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("e2eId", ValidPayloadData.get("e2eId").toString());
        }else {
            manifestData.put("e2eId", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void chequeAddKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("chequeAdd", ValidPayloadData.get("chequeAdd").toString());
        }else {
            manifestData.put("chequeAdd", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void chequePhoneKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("chequePhone", ValidPayloadData.get("chequePhone").toString());
        }else {
            manifestData.put("chequePhone", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }


}
