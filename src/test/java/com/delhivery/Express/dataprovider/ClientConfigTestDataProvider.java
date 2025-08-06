package com.delhivery.Express.dataprovider;

import org.testng.annotations.DataProvider;

public class ClientConfigTestDataProvider {
    @DataProvider(name = "createUpdateClientData", parallel = true)
    public static Object[][] createUpdateClientData(){
        return new Object[][] {
                {"Scenario:: B2B Product Type", "B2B"},
                {"Scenario:: null Product Type", null},
                {"Scenario:: Heavy Product Type", "Heavy"},
                {"Scenario:: KYC Product Type", "KYC"},
                {"Scenario:: DOC Product Type", "DOC"},
                {"Scenario:: C2C-Lite Product Type", "C2C-Lite"}};
    }
}
