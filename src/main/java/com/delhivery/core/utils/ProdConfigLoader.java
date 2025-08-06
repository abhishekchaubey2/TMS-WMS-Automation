package com.delhivery.core.utils;

import java.util.Properties;

public class ProdConfigLoader {
    private final Properties properties;
    private static ProdConfigLoader configLoader;
    private static String testEnv;

    private ProdConfigLoader() {
        testEnv = "prod";
        properties = PropertyUtils.propertyLoader("src/main/resources/" + testEnv + ".properties");
    }

    public static ProdConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ProdConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        else throw new RuntimeException("baseUrl property not found in properties file");
    }

    public String getClient() {
        String prop = properties.getProperty("dummy_client");
        if (prop != null) return prop;
        else throw new RuntimeException("dummy_client property not found in properties file");
    }

    public String getPin() {
        String prop = properties.getProperty("dummy_pin");
        if (prop != null) return prop;
        else throw new RuntimeException("dummy_pin property not found in properties file");
    }

    public String getProductType() {
        String prop = properties.getProperty("product_type");
        if (prop != null) return prop;
        else throw new RuntimeException("product_type property not found in properties file");
    }

    public String getPaymentType() {
        String prop = properties.getProperty("payment_mode");
        if (prop != null) return prop;
        else throw new RuntimeException("payment_mode property not found in properties file");
    }

    public String getOriginCenter() {
        String prop = properties.getProperty("dummy_originCenter");
        if (prop != null) return prop;
        else throw new RuntimeException("dummy_originCenter property not found in properties file");
    }

    public String getDestinationCenter() {
        String prop = properties.getProperty("dummy_destinationCenter");
        if (prop != null) return prop;
        else throw new RuntimeException("dummy_destinationCenter property not found in properties file");
    }

    public String getInstaBagStationId() {
        String prop = properties.getProperty("dummy_instaBagStationId");
        if (prop != null) return prop;
        else throw new RuntimeException("dummy_instaBagStationId property not found in properties file");
    }

    public String getInstaBagStationName() {
        String prop = properties.getProperty("dummy_instaBagStationName");
        if (prop != null) return prop;
        else throw new RuntimeException("dummy_instaBagStationName property not found in properties file");
    }

}