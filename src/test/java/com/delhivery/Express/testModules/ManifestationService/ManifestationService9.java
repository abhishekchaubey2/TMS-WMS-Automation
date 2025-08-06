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

public class ManifestationService9 extends BaseTest {
    protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");


    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    @DataProvider(name = "Key_different_data_types", parallel = false)
    public Object[][] Key_different_data_types() {
        return new Object[][]{
                { "Scenario:: When key value = empty string ", ""},
                { "Scenario:: When key value = null ", null},
                { "Scenario:: When key value = integer ", 1234},
                { "Scenario:: When key value = boolean true ", true},
                { "Scenario:: When key value = boolean false ", false},
                { "Scenario:: When key value = string ", "abc"},
                { "Scenario:: When key value = valid value ", ""},
                { "Scenario:: When key value = string integer ", "123456"},
                { "Scenario:: When key value = string null ", "null"},
                { "Scenario:: When key value = special char ", "@#$*()?"},
                { "Scenario:: When key value = boolean in string ", "true"},
                { "Scenario:: When key value = boolean in string ", "false"},
                { "Scenario:: When key value = float ", 123.456},
                { "Scenario:: When key value = long int - Length check ", "123456789123456781234"},
                { "Scenario:: When key value = long string - Length check ", "test string for check of lenth - testingvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"},
                { "Scenario:: When key value = containing trailing spaces ", "      abc            "}



        };
    }


    @Test(dataProvider = "Key_different_data_types", enabled=true)
    public void integratedGstRateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("integratedGstRate", ValidPayloadData.get("integratedGstRate").toString());
        }else {
            manifestData.put("integratedGstRate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void cessGstRateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("cessGstRate", ValidPayloadData.get("cessGstRate").toString());
        }else {
            manifestData.put("cessGstRate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void addressTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("addressType", ValidPayloadData.get("addressType").toString());
        }else {
            manifestData.put("addressType", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void multiInvAmtKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("multiInvAmt", ValidPayloadData.get("multiInvAmt").toString());
        }else {
            manifestData.put("multiInvAmt", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void codInstructionsKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("codInstructions", ValidPayloadData.get("codInstructions").toString());
        }else {
            manifestData.put("codInstructions", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void buybackAddressKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("buybackAddress", ValidPayloadData.get("buybackAddress").toString());
        }else {
            manifestData.put("buybackAddress", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void buybackPinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("buybackPin", ValidPayloadData.get("buybackPin").toString());
        }else {
            manifestData.put("buybackPin", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void fmUcidKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("fmUcid", ValidPayloadData.get("fmUcid").toString());
        }else {
            manifestData.put("fmUcid", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void insuredKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("insured", ValidPayloadData.get("insured").toString());
        }else {
            manifestData.put("insured", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void hasDocumentKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("hasDocument", ValidPayloadData.get("hasDocument").toString());
        }else {
            manifestData.put("hasDocument", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void verifiedAddKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("verifiedAdd", ValidPayloadData.get("verifiedAdd").toString());
        }else {
            manifestData.put("verifiedAdd", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void einvQrKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("einvQr", ValidPayloadData.get("einvQr").toString());
        }else {
            manifestData.put("einvQr", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestationUserKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("manifestationUser", ValidPayloadData.get("manifestationUser").toString());
        }else {
            manifestData.put("manifestationUser", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void itemDescKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("itemDesc", ValidPayloadData.get("itemDesc").toString());
        }else {
            manifestData.put("itemDesc", value);
        }


        manifestForThirdBuilder(manifestData, Scenario);

    }



}
