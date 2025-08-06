package com.delhivery.Express.dataprovider;

import org.testng.annotations.DataProvider;

public class EWaybillDataProvider {
    @DataProvider(name = "eWaybillDataProvider", parallel = true)
    public Object[][] eWaybillDataProvider() {
        return new Object[][]{
                {"Scenario:: Prev amt < 50K ", "12345678", "", "1234", "External"},
                {"Scenario:: Prev amt < 50K ", "12345678", "", "80000", "External"},
                {"Scenario:: Prev amt > 50K ", "12345678", "", "1234", "External"},
                {"Scenario:: Prev amt > 50K ", "12345678", "", "80000", "External"},
        };
    }

    @DataProvider(name = "eWaybillRestInternalDataProvider", parallel = true)
    public Object[][] eWaybillRestInternalDataProvider() {
        return new Object[][]{
                {"Scenario:: Prev amt < 50K ", "12345678", "", "1234", "Internal"},
                {"Scenario:: Prev amt < 50K ", "12345678", "", "80000", "Internal"},
                {"Scenario:: Prev amt > 50K ", "12345678", "", "1234", "Internal"},
                {"Scenario:: Prev amt > 50K ", "12345678", "", "80000", "Internal"}
        };
    }


    @DataProvider(name = "eWaybillEditAPIDataProvider", parallel = true)
    public Object[][] eWaybillEditAPIDataProvider() {
        return new Object[][]{
                {"Scenario:: Prev amt < 50K ", "12345678", "", "1234"},
                {"Scenario:: Prev amt > 50K ", "12345678", "", "1234"},
                {"Scenario:: Prev amt < 50K ", "12345678", "", "80000"},
                {"Scenario:: Prev amt > 50K ", "12345678", "", "80000"},
        };
    }
}