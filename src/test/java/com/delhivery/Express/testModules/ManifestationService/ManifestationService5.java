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

public class ManifestationService5 extends BaseTest  {
	protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void buybackDescriptionKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("buybackDescription", ValidPayloadData.get("buybackDescription").toString());
        }else {
        	manifestData.put("buybackDescription", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void shipOptionKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("shipOption", ValidPayloadData.get("shipOption").toString());
        }else {
        	manifestData.put("shipOption", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void estimatedArrivalDateKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("estimatedArrivalDate", ValidPayloadData.get("estimatedArrivalDate").toString());
        }else {
        	manifestData.put("estimatedArrivalDate", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void shipMethodKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("shipMethod", ValidPayloadData.get("shipMethod").toString());
        }else {
        	manifestData.put("shipMethod", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void buybackAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("buybackAmount", ValidPayloadData.get("buybackAmount").toString());
        }else {
        	manifestData.put("buybackAmount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void isSecureKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("isSecure", ValidPayloadData.get("isSecure").toString());
        }else {
        	manifestData.put("isSecure", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void personSpecificKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("personSpecific", ValidPayloadData.get("personSpecific").toString());
        }else {
        	manifestData.put("personSpecific", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void addressSpecificKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("addressSpecific", ValidPayloadData.get("addressSpecific").toString());
        }else {
        	manifestData.put("addressSpecific", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void checkOneSecureKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("checkOneSecure", ValidPayloadData.get("checkOneSecure").toString());
        }else {
        	manifestData.put("checkOneSecure", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void otpKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("otp", ValidPayloadData.get("otp").toString());
        }else {
        	manifestData.put("otp", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void shipmentCodeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("shipmentCode", ValidPayloadData.get("shipmentCode").toString());
        }else {
        	manifestData.put("shipmentCode", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void ccKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload 
        if (Scenario.contains("valid value")){
        	manifestData.put("cc", ValidPayloadData.get("cc").toString());
        }else {
        	manifestData.put("cc", value);
        }
        
       
        manifestForSecondBuilder(manifestData, Scenario);
        
	}
	
	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void codKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("cod", ValidPayloadData.get("cod").toString());
        }else {
        	manifestData.put("cod", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void customsKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("customs", ValidPayloadData.get("customs").toString());
        }else {
        	manifestData.put("customs", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void waiverKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("waiver", ValidPayloadData.get("waiver").toString());
        }else {
        	manifestData.put("waiver", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void returnStoreLocationKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("returnStoreLocation", ValidPayloadData.get("returnStoreLocation").toString());
        }else {
        	manifestData.put("returnStoreLocation", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void pickupPaymentModeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("pickupPaymentMode", ValidPayloadData.get("pickupPaymentMode").toString());
        }else {
        	manifestData.put("pickupPaymentMode", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void copAmountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("copAmount", ValidPayloadData.get("copAmount").toString());
        }else {
        	manifestData.put("copAmount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void preferredDaysKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("preferredDays", ValidPayloadData.get("preferredDays").toString());
        }else {
        	manifestData.put("preferredDays", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void expectedPcountKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("expectedPcount", ValidPayloadData.get("expectedPcount").toString());
        }else {
        	manifestData.put("expectedPcount", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void isOtpVerifiedKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("isOtpVerified", ValidPayloadData.get("isOtpVerified").toString());
        }else {
        	manifestData.put("isOtpVerified", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}

	@Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
	public void freightModeKeyValidations( String Scenario, Object value) throws JsonProcessingException {
		HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
        	manifestData.put("freightMode", ValidPayloadData.get("freightMode").toString());
        }else {
        	manifestData.put("freightMode", value);
        }


        manifestForSecondBuilder(manifestData, Scenario);

	}



}

        


