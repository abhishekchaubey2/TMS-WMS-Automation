package com.delhivery.Express.testModules.util;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Helper class for test modules.
 * Contains methods to prepare manifest data with different combinations of parameters.
 * Also contains methods to prepare expected values for RCN, RPC, RCTY, RCNS, NSL, ST, SR.
 * ThreadLocal is used to make the data thread safe.
 */

public class TestModuleHelper {

    public static ThreadLocal<HashMap<String, String>> getClData(String client) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = new HashMap<>();
            if (client != null) {
                map.put("client", client);
            }
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestData(String client, String product_type, String payment_mode, String pin) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = new HashMap<>();
            Optional.ofNullable(client).ifPresent(v -> map.put("client", v));
            Optional.ofNullable(product_type).ifPresent(v -> map.put("product_type", v));
            Optional.ofNullable(payment_mode).ifPresent(v -> map.put("payment_mode", v));
            Optional.ofNullable(pin).ifPresent(v -> map.put("pin", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestDataWithPinAndAdd(String client, String product_type, String payment_mode, String pin, String add) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestData(client, product_type, payment_mode, pin).get();
            Optional.ofNullable(add).ifPresent(v -> map.put("add", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestDataWithPinReturnAddAndReturnPin(String client, String product_type, String payment_mode, String pin, String return_add, String return_pin) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestData(client, product_type, payment_mode, pin).get();
            Optional.ofNullable(return_add).ifPresent(v -> map.put("return_add", v));
            Optional.ofNullable(return_pin).ifPresent(v -> map.put("return_pin", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestDataWithPinAddReturnAddAndReturnPin(String client, String product_type, String payment_mode, String pin, String add, String return_add, String return_pin) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestDataWithPinReturnAddAndReturnPin(client, product_type, payment_mode, pin, return_add, return_pin).get();
            Optional.ofNullable(add).ifPresent(v -> map.put("add", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestDataWithPinShippingModeAndProductDes(String client, String product_type, String payment_mode, String pin, String shipping_mode, String products_desc) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestData(client, product_type, payment_mode, pin).get();
            Optional.ofNullable(shipping_mode).ifPresent(v -> map.put("shipping_mode", v));
            Optional.ofNullable(products_desc).ifPresent(v -> map.put("products_desc", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestDataWithPinShippingModeProductDesAndEssentialGoods(String client, String product_type, String payment_mode, String pin, String shipping_mode, String products_desc, String essential_good) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestDataWithPinShippingModeAndProductDes(client, product_type, payment_mode, pin, shipping_mode, products_desc).get();
            Optional.ofNullable(essential_good).ifPresent(v -> map.put("essential_good", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String, String>> prepareManifestDataWithPinShippingModeProductDesEssentialGoodsAndFragileShp(String client, String product_type, String payment_mode, String pin, String shipping_mode, String products_desc, String essential_good, String fragile_shipment) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestDataWithPinShippingModeProductDesAndEssentialGoods(client, product_type, payment_mode, pin, shipping_mode, products_desc, essential_good).get();
            Optional.ofNullable(fragile_shipment).ifPresent(v -> map.put("fragile_shipment", v));
            return map;
        });
    }

    public static ThreadLocal<HashMap<String,String>> prepareManifestDataWithPinReturnAddAndReplPinAdd(String client, String product_type, String payment_mode, String pin, String add, String return_add, String return_pin,String buyback_add,String buyback_pin, String buyback_city,String buyback_state){
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> map = prepareManifestDataWithPinReturnAddAndReturnPin(client, product_type, payment_mode, pin, return_add, return_pin).get();
            Optional.ofNullable(add).ifPresent(v -> map.put("add", v));
            Optional.ofNullable(buyback_add).ifPresent(v -> map.put("buyback_address", v));
            Optional.ofNullable(buyback_pin).ifPresent(v -> map.put("buyback_pin", v));
            Optional.ofNullable(buyback_city).ifPresent(v -> map.put("buyback_city", v));
            Optional.ofNullable(buyback_state).ifPresent(v -> map.put("buyback_state", v));
            return map;
        });
    }

    public static ThreadLocal<LinkedHashMap<String, String>> fillExpectedValuesForRCNIdRpcIdRctyRcnsNslStSr(String expectedRCn, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String csNsl, String csSt, String csSr) {
        return ThreadLocal.withInitial(() -> {
            LinkedHashMap<String, String> expected_values = new LinkedHashMap<>();
            Optional.ofNullable(expectedRCn).ifPresent(v -> expected_values.put("rcn", v));
            Optional.ofNullable(expectedRCnid).ifPresent(v -> expected_values.put("rcnid", v));
            Optional.ofNullable(expectedRDpc).ifPresent(v -> expected_values.put("rdpc", v));
            Optional.ofNullable(expectedRDpcid).ifPresent(v -> expected_values.put("rdpcid", v));
            Optional.ofNullable(expectedRCty).ifPresent(v -> expected_values.put("rcty", v));
            Optional.ofNullable(expectedRCns).ifPresent(v -> expected_values.put("rcns", v));
            Optional.ofNullable(csNsl).ifPresent(v -> expected_values.put("cs.nsl", v));
            Optional.ofNullable(csSt).ifPresent(v -> expected_values.put("cs.st", v));
            Optional.ofNullable(csSr).ifPresent(v -> expected_values.put("cs.sr", v));
            return expected_values;
        });
    }
}