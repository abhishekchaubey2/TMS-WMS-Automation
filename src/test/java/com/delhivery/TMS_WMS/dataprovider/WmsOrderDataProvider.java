package com.delhivery.TMS_WMS.dataprovider;

import org.testng.annotations.DataProvider;

import java.util.HashMap;

/**
 * Data Provider for WMS Order Tests
 * Follows Express pattern - uses HashMap for flexibility
 */
public class WmsOrderDataProvider {
    
    /**
     * Minimal data provider - all defaults come from qa.properties
     * Only baseOrderNumber is required
     */
    @DataProvider(name = "orderCreationData")
    public static Object[][] getOrderCreationData() {
        return new Object[][] {
            {
                "wmstmsintltl151",           // baseOrderNumber
                new HashMap<String, String>() // empty HashMap - all defaults from qa.properties
            }
        };
    }
    
    /**
     * Data provider with custom overrides
     * HashMap can override any default value
     */
    @DataProvider(name = "orderCreationDataWithOverrides")
    public static Object[][] getOrderCreationDataWithOverrides() {
        HashMap<String, String> customData = new HashMap<>();
        customData.put("payment_mode", "COD");
        customData.put("priority", "P1");
        customData.put("city", "Mumbai");
        
        return new Object[][] {
            {
                "wmstmsintltl151",           // baseOrderNumber
                customData                   // custom overrides
            }
        };
    }
}
