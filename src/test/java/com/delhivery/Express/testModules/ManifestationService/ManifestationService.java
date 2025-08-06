package com.delhivery.Express.testModules.ManifestationService;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;
import static com.delhivery.core.utils.Utilities.getUniqueString;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ManifestationService extends BaseTest  {
	protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");
    
   

	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void NameKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("name", ValidPayloadData.get("name").toString());
        }else {
        	manifestData.put("name", value);
        }
		manifestForFirstBuilder(manifestData, Scenario);
        
	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void WaybillKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("waybill", "");
        }else {
        	manifestData.put("waybill", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void OrderKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("order", getUniqueString());
        }else {
        	manifestData.put("order", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ProdDescKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("productsDesc", ValidPayloadData.get("productDesc").toString());
        }else {
        	manifestData.put("productsDesc", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void OrderDateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("orderDate", ValidPayloadData.get("orderDate").toString());
        }else {
        	manifestData.put("orderDate", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void PaymentModeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("paymentMode", ValidPayloadData.get("paymentMode").toString());
        }else {
        	manifestData.put("paymentMode", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void CodAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("codAmount", ValidPayloadData.get("codAmount").toString());
        }else {
        	manifestData.put("codAmount", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ProductTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("productType",ValidPayloadData.get("productType").toString());
        }else {
        	manifestData.put("productType", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void TotalAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("totalAmount", ValidPayloadData.get("totalAmount").toString());
        }else {
        	manifestData.put("totalAmount", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void AddressKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("add", ValidPayloadData.get("add").toString());
        }else {
        	manifestData.put("add", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void CityKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("city", ValidPayloadData.get("city").toString());
        }else {
        	manifestData.put("city", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void StateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("state", ValidPayloadData.get("state").toString());
        }else {
        	manifestData.put("state", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void CountryKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("country", ValidPayloadData.get("country").toString());
        }else {
        	manifestData.put("country", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void PhoneKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("phone", ValidPayloadData.get("phone").toString());
        }else {
        	manifestData.put("phone", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void PinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("pin", ValidPayloadData.get("pin").toString());
        }else {
        	manifestData.put("pin", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnAddressKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnAdd", ValidPayloadData.get("returnAdd").toString());
        }else {
        	manifestData.put("returnAdd", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnCityKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnCity", ValidPayloadData.get("returnCity").toString());
        }else {
        	manifestData.put("returnCity", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnCountryKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnCountry", ValidPayloadData.get("returnCountry").toString());
        }else {
        	manifestData.put("returnCountry", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	//@Test//(dataProvider = "dataprovider", dataProviderClass = DataProviderClass.class)
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnNameKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnName", ValidPayloadData.get("returnName").toString());
        }else {
        	manifestData.put("returnName", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnPhoneKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnPhone", ValidPayloadData.get("returnPhone").toString());
        }else {
        	manifestData.put("returnPhone", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnPinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnPin", ValidPayloadData.get("returnPin").toString());
        }else {
        	manifestData.put("returnPin", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ReturnStateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("returnState", ValidPayloadData.get("returnState").toString());
        }else {
        	manifestData.put("returnState", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SupplierKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("supplier", ValidPayloadData.get("supplier").toString());
        }else {
        	manifestData.put("supplier", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}





}
        


