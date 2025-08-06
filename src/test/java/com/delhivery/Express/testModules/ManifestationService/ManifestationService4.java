package com.delhivery.Express.testModules.ManifestationService;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForSecondBuilder;
import static com.delhivery.core.utils.Utilities.getUniqueString;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ManifestationService4 extends BaseTest  {
	protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void nextTrialDateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("nextTrialDate", ValidPayloadData.get("nextTrialDate").toString());
        }else {
        	manifestData.put("nextTrialDate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void movementGeographyKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("movementGeography", ValidPayloadData.get("movementGeography").toString());
        }else {
        	manifestData.put("movementGeography", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void fmModeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("fmMode", ValidPayloadData.get("fmMode").toString());
        }else {
        	manifestData.put("fmMode", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void flowTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("flowType", ValidPayloadData.get("flowType").toString());
        }else {
        	manifestData.put("flowType", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void productCategoryKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("productCategory", ValidPayloadData.get("productCategory").toString());
        }else {
        	manifestData.put("productCategory", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void speedKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("speed", ValidPayloadData.get("speed").toString());
        }else {
        	manifestData.put("speed", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void vasKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("vas", ValidPayloadData.get("vas").toString());
        }else {
        	manifestData.put("vas", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void readyToShipKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("readyToShip", ValidPayloadData.get("readyToShip").toString());
        }else {
        	manifestData.put("readyToShip", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void internalKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("internal", ValidPayloadData.get("internal").toString());
        }else {
        	manifestData.put("internal", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void primeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("prime", ValidPayloadData.get("prime").toString());
        }else {
        	manifestData.put("prime", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void deliveryInstructionsKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("deliveryInstructions", ValidPayloadData.get("deliveryInstructions").toString());
        }else {
        	manifestData.put("deliveryInstructions", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void lrnKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("lrn", ValidPayloadData.get("lrn").toString());
        }else {
        	manifestData.put("lrn", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void qcKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("qc", ValidPayloadData.get("qc").toString());
        }else {
        	manifestData.put("qc", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void consigneeGstAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("consigneeGstAmount", ValidPayloadData.get("consigneeGstAmount").toString());
        }else {
        	manifestData.put("consigneeGstAmount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void sellerGstAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerGstAmount", ValidPayloadData.get("sellerGstAmount").toString());
        }else {
        	manifestData.put("sellerGstAmount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void integratedGstAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("integratedGstAmount", ValidPayloadData.get("integratedGstAmount").toString());
        }else {
        	manifestData.put("integratedGstAmount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void gstCessAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("gstCessAmount", ValidPayloadData.get("gstCessAmount").toString());
        }else {
        	manifestData.put("gstCessAmount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void productKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("product", ValidPayloadData.get("product").toString());
        }else {
        	manifestData.put("product", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void productDescKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("productDesc", ValidPayloadData.get("productDesc").toString());
        }else {
        	manifestData.put("productDesc", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void productQuantityKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("productQuantity", ValidPayloadData.get("productQuantity").toString());
        }else {
        	manifestData.put("productQuantity", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void unitKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("unit", ValidPayloadData.get("unit").toString());
        }else {
        	manifestData.put("unit", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void consigneeGstRateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("consigneeGstRate", ValidPayloadData.get("consigneeGstRate").toString());
        }else {
        	manifestData.put("consigneeGstRate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void sellerGstRateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerGstRate", ValidPayloadData.get("sellerGstRate").toString());
        }else {
        	manifestData.put("sellerGstRate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}



}
        


