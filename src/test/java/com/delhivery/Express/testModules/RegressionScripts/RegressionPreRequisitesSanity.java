package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response.FetchClientUuidDetailsResponsePayload;
import com.delhivery.Express.pojo.ODTat.Response.ODTatResponsePayload;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.delhivery.core.utils.Assertions.compareJSON;
import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.ServiceabilityKeysAssertions.assertNodeValue;

public class RegressionPreRequisitesSanity extends BaseTest {

    protected static Map<String, Object> clientUuid_HQAPIREGRESSION, client_WayBillAsService, client_99tshirts,
            client_AramexDomestic, client_Paytm;
    FetchClientUuidDetailsResponsePayload expectedHqApiRegClientResponse,
            actualHqApiRegClientResponse, clientWayBillAsServiceResponse, client99tshirtsResponse,
            clientAramexDomesticResponse, clientPaytmResponse= null;
    ApiController apiCtrl = new ApiController();
    File file;
    ObjectMapper objectMapper = new ObjectMapper();
    HashMap<String, String> clientData = null;
    HashMap<String, String> paramsMap = new HashMap<>();


    //constructor to fetch client uuid of HQAPIREGRESSION from yaml reader
    public RegressionPreRequisitesSanity() {
        clientUuid_HQAPIREGRESSION = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION");
        client_WayBillAsService = YamlReader.getYamlValues("Client_Details.client_WayBillAsService");
        client_99tshirts = YamlReader.getYamlValues("Client_Details.client_99tshirts");
        client_AramexDomestic = YamlReader.getYamlValues("Client_Details.client_AramexDomestic");
        client_Paytm = YamlReader.getYamlValues("Client_Details.client_Paytm");
 }


    //test case to compare expected and actual client config data from fetch api response by first converting pojo object response to String and then use inbuild compare function for two strings
    @Test()
    public void configDataClient_HQAPIREGRESSION() throws IOException {
        file = new File("src/test/resources/responsePayloads/expectedHqApiRegClientResponse.json");
        expectedHqApiRegClientResponse = objectMapper.readValue(file, FetchClientUuidDetailsResponsePayload.class);

        //convert expectedHqApiRegClientResponse json obeject into String
        actualHqApiRegClientResponse = apiCtrl.verifyFetchClientUuidDetailsApi(clientUuid_HQAPIREGRESSION.get("client_uuid").toString());
        compareJSON(Utilities.jsonObjectToString(expectedHqApiRegClientResponse), Utilities.jsonObjectToString(actualHqApiRegClientResponse));

    }

     @Test()
    public void configDataClient_WayBillAsService() throws IOException {
        //Updating client config code
//         clientData = new HashMap<>();
//         clientData.put("client_uuid", client_WayBillAsService.get("client_uuid").toString());
//         //put clientData with key value of product_type and billing_method
//         clientData.put("product_type", "");
//         clientData.put("billing_method", "Postpaid");
//         apiCtrl.createUpdateClientData(clientData);

         clientWayBillAsServiceResponse = apiCtrl.verifyFetchClientUuidDetailsApi(client_WayBillAsService.get("client_uuid").toString());
         assertKeyValue("Client product type", "", clientWayBillAsServiceResponse.data.product_type);
         assertKeyValue("Client billing method", "Postpaid", clientWayBillAsServiceResponse.data.billing_method);

    }

    @Test()
    public void configDataClient_99tshirts() throws IOException {
        client99tshirtsResponse = apiCtrl.verifyFetchClientUuidDetailsApi(client_99tshirts.get("client_uuid").toString());
        assertKeyValue("Client product type", "", client99tshirtsResponse.data.product_type);
        assertKeyValue("Client billing method", "prepaid", client99tshirtsResponse.data.billing_method);

    }

    @Test()
    public void configDataClient_AramexDomestic() throws IOException {
        clientAramexDomesticResponse = apiCtrl.verifyFetchClientUuidDetailsApi(client_AramexDomestic.get("client_uuid").toString());
        assertKeyValue("Client product type", "", clientAramexDomesticResponse.data.product_type);
        assertKeyValue("Client billing method", "Postpaid", clientAramexDomesticResponse.data.billing_method);

    }

    @Test()
    public void configDataClient_Paytm() throws IOException {
        clientPaytmResponse = apiCtrl.verifyFetchClientUuidDetailsApi(client_Paytm.get("client_uuid").toString());
        assertKeyValue("Client product type", "", clientPaytmResponse.data.product_type);
        assertKeyValue("Client billing method", "Postpaid", clientPaytmResponse.data.billing_method);

    }



    @Test(dependsOnMethods = "configDataClient_HQAPIREGRESSION")
    public void checkCmu1_ClientConfig() throws JsonProcessingException {
        assertKeyValue("Client product type", "", actualHqApiRegClientResponse.data.product_type);

    }

    //test cases for cmu-2 job specific keys assertion
//    @Test(dependsOnMethods = "configDataClient_HQAPIREGRESSION")
//    public void checkCmu2_ClientConfig() throws JsonProcessingException {
//
//    }

    @Test(dependsOnMethods = "configDataClient_HQAPIREGRESSION")
    public void checkCmu3_ClientConfig() throws JsonProcessingException {
        assertKeyValue("B2c serviceability type", "null", Utilities.jsonObjectToString(actualHqApiRegClientResponse.data.b2c_serviceability_type));
        assertKeyValue("B2b serviceability type", "null", Utilities.jsonObjectToString(actualHqApiRegClientResponse.data.b2b_serviceability_type));
        assertKeyValue("Return serviceability type", "null", Utilities.jsonObjectToString(actualHqApiRegClientResponse.data.return_serviceability_type));

    }

    @Test(dependsOnMethods = "configDataClient_HQAPIREGRESSION")
    public void checkCodTrip_ClientConfig() throws JsonProcessingException {
        assertKeyValue("Enable serviceability service for client", false, actualHqApiRegClientResponse.data.extra.enable_srv);

    }

    @Test(dependsOnMethods = "configDataClient_HQAPIREGRESSION")
    public void checkOtherThanPkgFlow_ClientConfig() throws JsonProcessingException {
        assertKeyValue("Enable serviceability service for client", false, actualHqApiRegClientResponse.data.extra.enable_srv);

    }
    
    @Test()
    public void verifyOdTatValues(){
    	paramsMap.put("origin_city", YamlReader.getYamlValues("OdTatData.City").get("Gurgaon").toString());
    	paramsMap.put("dest_city", YamlReader.getYamlValues("OdTatData.City").get("Delhi").toString());
    	ODTatResponsePayload apiResponse = apiCtrl.FetchODTatValues(paramsMap);
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.flashAirTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("flash_air_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.dtoTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("dto_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.forwardExpressTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_express_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.forwardFastB2bTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_fast_b2b_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.forwardFastB2cTat),YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_fast_b2c_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.forwardSurfaceTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_surface_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.heavyTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("heavy_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.ltlAirTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("ltl_air_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.ltlRegularTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("ltl_regular_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.nextB2cSurfaceTat),YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("next_b2c_surface_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.returnTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("return_tat").toString());
    	assertKeyValue("Tat Value of the Lane", Long.toString(apiResponse.data.rvpAirTat), YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("rvp_air_tat").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.dtoCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("dto_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.flashAirCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("flash_air_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.forwardExpressCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_express_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.forwardFastB2bCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_fast_b2b_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.forwardFastB2cCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_fast_b2c_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.forwardSurfaceCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("forward_surface_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.heavyCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("heavy_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.ltlAirCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("ltl_air_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.ltlRegularCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("ltl_regular_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.nextB2cSurfaceCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("next_b2c_surface_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.returnCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("return_cutoff").toString());
    	assertKeyValue("Tat Value of the Lane", apiResponse.data.rvpAirCutoff, YamlReader.getYamlValues("OdTatData.TatData_Gurgaon_Delhi.Data").get("rvp_air_cutoff").toString());
    }

    @DataProvider(name = "CheckPinData_B2C", parallel = true)
    public Object[][] Pin_data_Provider_B2C() {
        return new Object[][] {
                { "Scenario:: pin = 122001", "B2C", "122001", "Gurgaon (Haryana)"},
                { "Scenario:: pin = 122003", "B2C", "122003", "Del_B_RPC (Delhi)"},
                { "Scenario:: pin = 110011", "B2C", "110011", "East Delhi (Delhi)"},
                { "Scenario:: pin = 400059", "B2C", "400059", "Mumbai MIDC (Maharashtra)"}
        };
    }
    @Test(dataProvider = "CheckPinData_B2C" , enabled = true)
    public void CheckPinData_B2C(String Scenario, String type, String pin, String cn)throws JsonProcessingException {
        List<String> waybills = new ArrayList<String>();
        HashMap<String, String> clData = new HashMap<>();
        //Make payload that is essential for populating ewaybill
        clData.put("client", "regression_client");
        clData.put("client", "regression_client");
        clData.put("pin",pin);


        waybills = diffTypeShipment.DifferentTypeShipments(type, clData);
        String waybill = waybills.get(0);
        Utilities.hardWait(5);
        assertNodeValue("cn", 	cn, apiCtrl.fetchPackageInfo(waybill, clData).cn.toString());
    }

    @DataProvider(name = "CheckPinData_B2B", parallel = true)
    public Object[][] Pin_data_Provider_B2B() {
        return new Object[][] {
                { "Scenario:: pin = 122001", "B2B", "122001", "GGN_DPC (Haryana)"},
                { "Scenario:: pin = 122003", "B2B", "122003", "Gurgaon_Kadipur (Haryana)"},
                { "Scenario:: pin = 110011", "B2B", "110011", "Delhi_Paschim_DC (Delhi)"},
                { "Scenario:: pin = 400059", "B2B", "400059", "Mumbai MIDC (Maharashtra)"}
        };
    }
    @Test(dataProvider = "CheckPinData_B2B" , enabled = true)
    public void CheckPinData_B2B(String Scenario, String type, String pin, String cn)throws JsonProcessingException {
        List<String> waybills = new ArrayList<String>();
        HashMap<String, String> clData = new HashMap<>();
        //Make payload that is essential for populating ewaybill
        clData.put("client", "regression_client");
        clData.put("client", "regression_client");
        clData.put("pin",pin);


        waybills = diffTypeShipment.DifferentTypeShipments(type, clData);
        String waybill = waybills.get(0);
        Utilities.hardWait(90);
        assertNodeValue("cn", 	cn, apiCtrl.fetchPackageInfo(waybill, clData).cn.toString());
    }
    
    

}
