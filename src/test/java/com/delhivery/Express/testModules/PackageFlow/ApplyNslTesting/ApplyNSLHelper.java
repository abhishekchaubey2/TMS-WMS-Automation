package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;

import java.util.HashMap;

public class ApplyNSLHelper {
    public static ThreadLocal<HashMap<String, String>> getManifestDataWithOrWithoutEINVQR(String product_type, String payment_mode, String pin, String einvqr) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("client", "regression_client");
            map.put("product_type", product_type);
            if (payment_mode != null) {
                map.put("payment_mode", payment_mode);
            }

            if (einvqr != null) {
                map.put("einv_qr", einvqr);
            }
            if (pin != null) {
                map.put("pin", pin);
            }
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> getClData(String client) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("client", client);
            return map;
        });
    }
}
