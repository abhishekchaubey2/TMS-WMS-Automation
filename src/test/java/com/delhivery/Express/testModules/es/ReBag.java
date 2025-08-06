package com.delhivery.Express.testModules.es;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReBag extends BaseTest {
    @Test(dataProvider = "different_env",dataProviderClass = manifestationData.class)
    public void testReBag(String scenario, String env){
        List<Map<String,Object>> dataList = new ArrayList<>();
        Map<String,Object> data = new HashMap<>();
        data.put("action","accepted");
        data.put("bag_barcode","DV73L"+ Utilities.getUniqueInt(12));
        data.put("sl","Gurgaon (Haryana)");
        data.put("slid","IND122001AAB");
        dataList.add(data);
        ApiController apiController = new ApiController();
        apiController.reBag(dataList,env);
    }
}
