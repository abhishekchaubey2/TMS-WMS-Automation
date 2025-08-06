package com.delhivery.Express.testModules.ManifestationService;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;
import static com.delhivery.core.utils.Utilities.getUniqueString;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ManifestationService2 extends BaseTest  {
	protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void EssentialGoodKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("essentialGood", ValidPayloadData.get("essentialGood").toString());
        }else {
        	manifestData.put("essentialGood", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void TaxationTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("taxationType", ValidPayloadData.get("taxationType").toString());
        }else {
        	manifestData.put("taxationType", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void EmailKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("email", ValidPayloadData.get("email").toString());
        }else {
        	manifestData.put("email", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void WeightKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("weight", ValidPayloadData.get("weight").toString());
        }else {
        	manifestData.put("weight", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void InvoiceUrlKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("invoiceUrl", ValidPayloadData.get("invoiceUrl").toString());
        }else {
        	manifestData.put("invoiceUrl", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void QuantityKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("quantity", ValidPayloadData.get("quantity"));
        }else {
        	manifestData.put("quantity", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerNameKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerName",ValidPayloadData.get("sellerName").toString());
        }else {
        	manifestData.put("sellerName", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerAddKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerAdd", ValidPayloadData.get("sellerAdd").toString());
        }else {
        	manifestData.put("sellerAdd", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerCityKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerCity", ValidPayloadData.get("sellerCity").toString());
        }else {
        	manifestData.put("sellerCity", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerStateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerState", ValidPayloadData.get("sellerState").toString());
        }else {
        	manifestData.put("sellerState", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerCstKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerCst", ValidPayloadData.get("sellerCst").toString());
        }else {
        	manifestData.put("sellerCst", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerTinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerTin", ValidPayloadData.get("sellerTin").toString());
        }else {
        	manifestData.put("sellerTin", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerInvKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerInv", ValidPayloadData.get("sellerInv").toString());
        }else {
        	manifestData.put("sellerInv", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerInvDateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerInvDate", ValidPayloadData.get("sellerInvDate").toString());
        }else {
        	manifestData.put("sellerInvDate", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ShipmentLengthKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("shipmentLength", ValidPayloadData.get("shipmentLength"));
        }else {
        	manifestData.put("shipmentLength", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ShipmentWidthKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("shipmentWidth", ValidPayloadData.get("shipmentWidth"));
        }else {
        	manifestData.put("shipmentWidth", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ShipmentHeightKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("shipmentHeight", ValidPayloadData.get("shipmentHeight"));
        }else {
        	manifestData.put("shipmentHeight", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ConsigneeTinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("consigneeTin", ValidPayloadData.get("consigneeTin").toString());
        }else {
        	manifestData.put("consigneeTin", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void CommodityValueKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("commodityValue", ValidPayloadData.get("commodityValue").toString());
        }else {
        	manifestData.put("commodityValue", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void CategoryOfGoodsKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("categoryOfGoods", ValidPayloadData.get("categoryOfGoods").toString());
        }else {
        	manifestData.put("categoryOfGoods", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void DangerousGoodsKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dangerousGoods", ValidPayloadData.get("dangerousGoods").toString());
        }else {
        	manifestData.put("dangerousGoods", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ShipmentTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("shipmentType", ValidPayloadData.get("shipmentType").toString());
        }else {
        	manifestData.put("shipmentType", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void SellerGstTinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("sellerGstTin", ValidPayloadData.get("sellerGstTin").toString());
        }else {
        	manifestData.put("sellerGstTin", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}




	
	
	
}
        


