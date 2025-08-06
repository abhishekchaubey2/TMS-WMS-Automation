package com.delhivery.Express.testModules.PackageFlow;

import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;

import io.restassured.response.Response;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;



public class QcModule extends BaseTest {
	
	private String waybill;
    private String bagId;
    private String tripId;
    private String dispatchId;

    private String wbn;
    private String tripId1;
    private String bagId1;
	private ArrayList<String> waybills;
	private ArrayList<String> cmuWaybills;
	
	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public Map<String, Object> pkgFlowData, bagFlowData;
    ApiController apiCtrl = new ApiController();

    public QcModule() {
        DataProviderClass.fileName = "CmuRegressionData";
        DataProviderClass.sheetName = "Pkg_flows";
        product_type = "B2C";
        payment_mode = "pickup";
    }

    public void fillHashMap(String product_type,String payment_mode, String pin) {
    	clData.put("client", "Essential_Disabled_client");
    	clData.put("product_type",product_type);
    	clData.put("payment_mode", payment_mode);
    	clData.put("pin", "400059");
    }
    @DataProvider(name = "QC_VALUE", parallel = true)
    public Object[][] qc_value() {
        return new Object[][]{
                
                {"Scenario:: Qc_Value_Less_Than_Two_pickup_B2C ", "1","pickup","B2C"},
                {"Scenario:: Qc_Value_Equals_Two_pickup_B2C ", "2","pickup","B2C"},
                {"Scenario:: Qc_Value_More_Than_Two_pickup_B2C ", "3","pickup","B2C"},
                {"Scenario:: Qc_Value_More_Than_Two_pickup_B2B ", "3","pickup","B2B"},
                {"Scenario:: Qc_Value_More_Than_Two_pickup_Heavy ", "3","pickup","Heavy"},
               
               {"Scenario:: Qc_Value_Less_Than_Two_repl_B2C ", "1","repl","B2C"},
               {"Scenario:: Qc_Value_Equals_Two_repl_B2C ", "2","repl","B2C"},
               {"Scenario:: Qc_Value_More_Than_Two_repl_B2C ", "3","repl","B2C"},
               {"Scenario:: Qc_Value_More_Than_Two_repl_B2B ", "3","repl","B2B"},
               {"Scenario:: Qc_Value_More_Than_Two_repl_Heavy ", "3","repl","Heavy"},
                
                {"Scenario:: Qc_Value_More_Than_Two_cod_B2C ", "3","cod","B2C"},
                {"Scenario:: Qc_Value_More_Than_Two_prepaid_B2C ", "3","prepaid","B2C"},
                {"Scenario:: Qc_Value_More_Than_Two_cod_B2B ", "3","cod","B2B"},
                {"Scenario:: Qc_Value_More_Than_Two_prepaid_B2B ", "3","prepaid","B2B"},
                {"Scenario:: Qc_Value_More_Than_Two_cod_Heavy ", "3","cod","Heavy"},
                {"Scenario:: Qc_Value_More_Than_Two_prepaid_Heavy ", "3","prepaid","Heavy"},
                
        };
    }
    
     @Test(dataProvider = "QC_VALUE")
     public void Qc_fail_when_qty_more_than_two(String Scenario, String qty, String payment_mode, String product_type) throws InterruptedException{
    	 clData.put("client", "ManifestClient");
			HashMap<String,String> data = new HashMap<>();
			data.put("product_type",product_type);
			data.put("payment_mode", payment_mode);
			data.put("pin", pinCodeHandler(product_type));
			data.put("client", "ManifestClient");
			data.put("quantity",qty);
			List<HashMap<String,String>> dataList = new ArrayList<>();
			dataList.add(data);
			apiCtrl.rvpManifestApi(dataList);
     }
    //new question added in meesho 2.0 case
	 @Test()
	 
	    public void QC_Check() throws InterruptedException {
		 
		 //2.0 is enabled for this client only for regression		    
	    	clData.put("client", "ManifestClient");
			HashMap<String,String> data = new HashMap<String,String>();
			data.put("product_type",product_type);
			data.put("payment_mode", payment_mode);
			data.put("pin", pinCodeHandler(product_type));
			data.put("quality_check", "true");
			data.put("client", "ManifestClient");
			
			waybills = apiCtrl.cmuManifestApi(data);
			waybill = waybills.get(0);

			logInfo("Waybill generated " + waybill);
			apiCtrl.verifyPackageFetchInfoApi(waybill, "PP", "Open", "X-UCI", clData);
			if (product_type.equals("B2B")) {
				Utilities.hardWait(70);
			}
			
			String json = null;
			PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
			assertKeyValue("qc", true, pkgs.qc);
			
			//fetching QC answers from DB
			Utilities.hardWait(50);
			Response response = apiCtrl.verifyQCAnswerResponse(waybill);
	        assertKeyValue("question_id", "DLV 52", response.jsonPath().get("qc_data."+ waybill +".item_1[7].question_id"));
	        assertKeyValue("question_id", "DLV 53", response.jsonPath().get("qc_data."+ waybill +".item_1[8].question_id"));
		
		}
	 
	 private String pinCodeHandler(String productType) {
	        String pinCode;
	        if (productType.contains("B2B")) {
	            pinCode = ConfigLoader.getInstance().getB2BPinCode();
	        } else if (productType.contains("B2C")) {
	            pinCode = ConfigLoader.getInstance().getB2CPinCode();
	        } else {
	            pinCode = ConfigLoader.getInstance().getHeavyPinCode();
	        }

	        return pinCode;
	    }
	 
	 
	
}
