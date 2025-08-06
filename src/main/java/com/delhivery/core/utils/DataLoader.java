package com.delhivery.core.utils;

import com.delhivery.project1.pojo.dataprovider.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataLoader {
    private static DataLoader dataLoader;
    private final TestData testData;

    public DataLoader() throws IOException {
        String testEnv = System.getProperty("env");
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/" + testEnv + ".json");
        testData = objectMapper.readValue(file, TestData.class);
    }

    public static DataLoader getInstance() throws IOException {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public TestData getTestData() {
        return testData;
    }
}

