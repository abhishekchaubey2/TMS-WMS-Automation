package com.delhivery.Express.testModules.ewaybill.util;

import com.delhivery.core.utils.ConfigLoader;

public class EWayBillTestHelper {
    public static final Integer eWaybillThreshold = 50000;

    public static String pinCodeHandler(String productType) {
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
