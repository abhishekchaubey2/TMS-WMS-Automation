package com.delhivery.Express.testModules.PackageFlow;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.YamlReader;

public class BillingMethodCases extends BaseTest {

    private String dispatchId;
    private ArrayList<Object> waybills, bagIds, tripIds;
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    public HashMap<String, String> reqData;

    private String product_type, payment_mode, packageStatus, client, client_wallet, client_uuid, pin, package_count, UPL;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<String, String>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    String rmk = "Crashing while saving package due to exception 'Prepaid client manifest charge API failed due to insufficient balance'. Package might have been partially saved.";
    ApiController apiCtrl = new ApiController();

    public BillingMethodCases() {
        this.product_type = "B2C";
        this.payment_mode = "Prepaid";
        this.pin = "400059";
        this.package_count = "3";
        client = YamlReader.getYamlValues("Client_Details.client_ManifestClient").get("name").toString();
        client_wallet = YamlReader.getYamlValues("Client_Details.client_ManifestClient").get("wallet_id").toString();
        client_uuid = YamlReader.getYamlValues("Client_Details.client_ManifestClient").get("client_uuid").toString();


    }
	
	
	/*@Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class, enabled = true)
    public void testBillingMethodCasesWhenEinvQrKeyIsTrue(String scenario, String product_type, String payment_mode) {
    	clData.put("client", "regression_client");
		HashMap<String,String> data = new HashMap<String,String>();
		data.put("product_type",product_type);
		data.put("payment_mode", payment_mode);
		data.put("pin", "400059");
		data.put("client", client);
		//data.put("einv_qr", "true");
		
		waybills = apiCtrl.cmuManifestApiWithResponse(data);
		
		String waybill = waybills.get(0).toString();
		if(product_type == "B2B" ) {
			assertKeyValue("status", "Success", waybills.get(2).toString());
		}else {
			assertKeyValue("status", "Fail", waybills.get(2).toString());
			assertKeyValue("remarks", rmk, waybills.get(3).toString());
		}
		UPL = waybills.get(1).toString();
		
		logInfo("UPL generated: " + UPL);
		HashMap<String,String> UPLdata = new HashMap<String,String>();
		UPLdata.put("upl", UPL);
		UPLdata.put("client", client);
		GetManifestUplDataResponsePayload UPLResponse = apiCtrl.getUplData(UPLdata);
		if(product_type == "B2B") {
			assertKeyValue("s", "Success", UPLResponse.s);
			
		}else {
			assertKeyValue("s", "Failure", UPLResponse.s);
		}
			
		
	
	}
	*/


}
