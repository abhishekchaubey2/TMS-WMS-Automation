package com.delhivery.Express.testModules.ManifestationService;
import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;
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

public class ManifestationService3 extends BaseTest  {
	protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ClearanceModeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("clearanceMode", ValidPayloadData.get("clearanceMode").toString());
        }else {
        	manifestData.put("clearanceMode", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void FmAddressKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("fmAddress", ValidPayloadData.get("fmAddress").toString());
        }else {
        	manifestData.put("fmAddress", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void FmPhoneKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("fmPhone", ValidPayloadData.get("fmPhone").toString());
        }else {
        	manifestData.put("fmPhone", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void FmPinKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("fmPin", ValidPayloadData.get("fmPin").toString());
        }else {
        	manifestData.put("fmPin", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void TransportSpeedKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("transportSpeed", ValidPayloadData.get("transportSpeed").toString());
        }else {
        	manifestData.put("transportSpeed", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void MasteridKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("masterId", ValidPayloadData.get("masterId"));
        }else {
        	manifestData.put("masterId", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void PickupStartTimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("pickupStartTime", ValidPayloadData.get("pickupStartTime").toString());
        }else {
        	manifestData.put("pickupStartTime", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void PickupEndTimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("pickupEndTime", ValidPayloadData.get("pickupEndTime").toString());
        }else {
        	manifestData.put("pickupEndTime", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void PickupSlotCodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("pickupSlotCode", ValidPayloadData.get("pickupSlotCode").toString());
        }else {
        	manifestData.put("pickupSlotCode", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void DropEndTimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dropEndTime", ValidPayloadData.get("dropEndTime").toString());
        }else {
        	manifestData.put("dropEndTime", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void DropSlotCodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dropSlotCode",ValidPayloadData.get("dropSlotCode").toString());
        }else {
        	manifestData.put("dropSlotCode", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void DropStartTimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dropStartTime", ValidPayloadData.get("dropStartTime").toString());
        }else {
        	manifestData.put("dropStartTime", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ServiceTypeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("serviceType", ValidPayloadData.get("serviceType").toString());
        }else {
        	manifestData.put("serviceType", value);
        }
        
       
        manifestForFirstBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void salesTaxFormAckNoKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("salesTaxFormAckNo", ValidPayloadData.get("salesTaxFormAckNo").toString());
        }else {
        	manifestData.put("salesTaxFormAckNo", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void mpsChildrenKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("mpsChildren", ValidPayloadData.get("mpsChildren").toString());
        }else {
        	manifestData.put("mpsChildren", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void mpsWeightKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("mpsWeight", ValidPayloadData.get("mpsWeight").toString());
        }else {
        	manifestData.put("mpsWeight", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void mpsVweightKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("mpsVweight", ValidPayloadData.get("mpsVweight").toString());
        }else {
        	manifestData.put("mpsVweight", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void masteridKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("masterid", ValidPayloadData.get("masterid").toString());
        }else {
        	manifestData.put("masterid", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void pickupStartTimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
		//Payload
		if (Scenario.contains("valid value")){
			manifestData.put("masterid", ValidPayloadData.get("masterid").toString());
		}else {
			manifestData.put("masterid", value);
		}


		manifestForSecondBuilder(manifestData, Scenario);

	}


	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void pickupendtimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("pickupendtime", ValidPayloadData.get("pickupendtime").toString());
        }else {
        	manifestData.put("pickupendtime", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void pickupslotcodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("pickupslotcode", ValidPayloadData.get("pickupslotcode").toString());
        }else {
        	manifestData.put("pickupslotcode", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void dropstarttimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dropstarttime", ValidPayloadData.get("dropstarttime").toString());
        }else {
        	manifestData.put("dropstarttime", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void dropendtimeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dropendtime", ValidPayloadData.get("dropendtime").toString());
        }else {
        	manifestData.put("dropendtime", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void dropslotcodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("dropslotcode", ValidPayloadData.get("dropslotcode").toString());
        }else {
        	manifestData.put("dropslotcode", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}



}

