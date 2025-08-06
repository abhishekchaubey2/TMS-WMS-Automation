package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForSecondBuilder;

public class ManifestationService8 extends BaseTest {
    protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void BillableWeightKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("billableWeight", ValidPayloadData.get("billableWeight").toString());
        }else {
            manifestData.put("billableWeight", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void ShippingMethodKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("shippingMethod", ValidPayloadData.get("shippingMethod").toString());
        }else {
            manifestData.put("shippingMethod", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void TaxValueKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("taxValue", ValidPayloadData.get("taxValue").toString());
        }else {
            manifestData.put("taxValue", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void FragileShipmentKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("fragileShipment", ValidPayloadData.get("fragileShipment").toString());
        }else {
            manifestData.put("fragileShipment", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void DocumentNumberKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("documentNumber", ValidPayloadData.get("documentNumber").toString());
        }else {
            manifestData.put("documentNumber", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void EwbnKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("ewbn", ValidPayloadData.get("ewbn").toString());
        }else {
            manifestData.put("ewbn", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void SourceKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("source", ValidPayloadData.get("source").toString());
        }else {
            manifestData.put("source", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void ClientGstTinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("clientGstTin", ValidPayloadData.get("clientGstTin").toString());
        }else {
            manifestData.put("clientGstTin", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void ConsigneeGstTinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("consigneeGstTin", ValidPayloadData.get("consigneeGstTin").toString());
        }else {
            manifestData.put("consigneeGstTin", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void HsnCodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("hsnCode", ValidPayloadData.get("hsncode").toString());
        }else {
            manifestData.put("hsnCode", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void InvoiceReferenceKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("invoiceReference", ValidPayloadData.get("invoiceReference").toString());
        }else {
            manifestData.put("invoiceReference", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void OdDistanceKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("odDistance", ValidPayloadData.get("odDistance"));
        }else {
            manifestData.put("odDistance", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void BillingNameKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("billingName", ValidPayloadData.get("billingName").toString());
        }else {
            manifestData.put("billingName", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void CountryCodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("countryCode", ValidPayloadData.get("countryCode").toString());
        }else {
            manifestData.put("countryCode", value);
        }


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void producttypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("producttype", ValidPayloadData.get("producttype").toString());
        }else {
            manifestData.put("producttype", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void servicetypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("servicetype", ValidPayloadData.get("servicetype").toString());
        }else {
            manifestData.put("servicetype", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void timeboundKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("timebound", ValidPayloadData.get("timebound").toString());
        }else {
            manifestData.put("timebound", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void qualitycheckKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("qualitycheck", ValidPayloadData.get("qualitycheck").toString());
        }else {
            manifestData.put("qualitycheck", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void taxationtypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("taxationtype", ValidPayloadData.get("taxationtype").toString());
        }else {
            manifestData.put("taxationtype", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void fodKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("fod", ValidPayloadData.get("fod").toString());
        }else {
            manifestData.put("fod", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void deliveryDateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("deliveryDate", ValidPayloadData.get("deliveryDate").toString());
        }else {
            manifestData.put("deliveryDate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

    }




}
