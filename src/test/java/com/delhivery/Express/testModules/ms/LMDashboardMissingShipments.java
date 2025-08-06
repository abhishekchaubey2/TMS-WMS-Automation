package com.delhivery.Express.testModules.ms;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.ms.response.LMDashboardMissingShipmentsRes;
import com.delhivery.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;

public class LMDashboardMissingShipments extends BaseTest {
    @DataProvider(name = "lm_dashboard_missing_Shipment",parallel = true)
    private Object[][] getLmDashboardMissingShipmentData(){
        return new Object[][]{
                {"Scenario:: When valid client-token, IND400093AAA Center provided","regression_client","IND400093AAA"},
                {"Scenario:: When valid client-token, IND110044AAB Center provided","regression_client","IND110044AAB"},
        };
    }

    @Test(dataProvider = "lm_dashboard_missing_Shipment")
    private void getLMDashboardMissingShipments(String scenario,String client, String center){
        HashMap<String,String> data = new HashMap<>();
        data.put("client",client);
        ApiController apiController= new ApiController();
        data.put("cwh_sent","true");
        data.put("pickup_location","Vishal");
        System.out.println("Data  is "+data);
        String wbn = apiController.cmuManifestApi(data).get(0);
        LMDashboardMissingShipmentsRes lmDashboardMissingShipmentsRes =  apiController.getLMDashboardMissingShipments(data,"cn_code",center);
        apiController.ApplyNsl(Collections.singletonList(wbn),"UD","L-PMA",data);
        lmDashboardMissingShipmentsRes =  apiController.getLMDashboardMissingShipments(data,"cn_code",center);
    }
}
