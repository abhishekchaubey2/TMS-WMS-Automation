package com.delhivery.Express.testModules.ManifestationService;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForThirdBuilder;
import static com.delhivery.core.utils.Utilities.getUniqueString;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ManifestationService7 extends BaseTest  {
	protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void invoiceValueKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("invoiceValue", ValidPayloadData.get("invoiceValue").toString());
        }else {
        	manifestData.put("invoiceValue", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void supplySubTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("supplySubType", ValidPayloadData.get("supplySubType").toString());
        }else {
        	manifestData.put("supplySubType", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void documentTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("documentType", ValidPayloadData.get("documentType").toString());
        }else {
        	manifestData.put("documentType", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void documentDateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("documentDate", ValidPayloadData.get("documentDate").toString());
        }else {
        	manifestData.put("documentDate", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void billingAddKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("billingAdd", ValidPayloadData.get("billingAdd").toString());
        }else {
        	manifestData.put("billingAdd", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void billingPinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("billingPin", ValidPayloadData.get("billingPin").toString());
        }else {
        	manifestData.put("billingPin", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void supplytypesKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("supplytypes", ValidPayloadData.get("supplytype").toString());
        }else {
        	manifestData.put("supplytypes", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void subSupplyTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("subSupplyType", ValidPayloadData.get("subSupplyType").toString());
        }else {
        	manifestData.put("subSupplyType", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void othervaluesKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("othervalues", ValidPayloadData.get("othervalue").toString());
        }else {
        	manifestData.put("othervalues", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void cessnonadvolvaluesKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("cessnonadvolvalues", ValidPayloadData.get("cessnonadvolvalues").toString());
        }else {
        	manifestData.put("cessnonadvolvalues", value);
        }
        
       
        manifestForThirdBuilder(manifestData, Scenario);
        
	}
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void shippingModeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("shippingMode", ValidPayloadData.get("shippingMode").toString());
        }else {
        	manifestData.put("shippingMode", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SecondaryAddKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("secondaryAdd", ValidPayloadData.get("secondaryAdd").toString());
        }else {
        	manifestData.put("secondaryAdd", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SecondaryCityKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("secondaryCity", ValidPayloadData.get("secondaryCity").toString());
        }else {
        	manifestData.put("secondaryCity", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SecondaryStateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("secondaryState", ValidPayloadData.get("secondaryState").toString());
        }else {
        	manifestData.put("secondaryState", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void WeightVerificationKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("weightVerification", ValidPayloadData.get("weightVerification"));
        }else {
        	manifestData.put("weightVerification", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}


	
}
        


