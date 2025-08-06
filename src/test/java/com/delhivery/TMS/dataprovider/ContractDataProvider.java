package com.delhivery.TMS.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Data Provider for TMS Contract Tests
 */
public class ContractDataProvider {
    
    @DataProvider(name = "contractCreationData")
    public static Object[][] getContractCreationData() {
        return new Object[][] {
            {
                "SIN9393",
                "singh_transport_contract2",
                1753122600000L,
                1785436200000L,
                "LTL",
                "PER_TRIP",
                "LONG_TERM",
                "JNP123",
                "ISPAT_GGN",
                700.0,
                "Flat",
                12,
                "hours",
                "singh transporter pvt ltd",
                389,
                38,
                38,
                false
            },
            {
                "SIN9394",
                "singh_transport_contract3",
                1753122600000L,
                1785436200000L,
                "FTL",
                "PER_TRIP",
                "LONG_TERM",
                "MUM001",
                "DEL001",
                800.0,
                "Flat",
                24,
                "hours",
                "singh transporter pvt ltd",
                400,
                50,
                50,
                true
            },
            {
                "SIN9395",
                "singh_transport_contract4",
                1753122600000L,
                1785436200000L,
                "PTL",
                "PER_KM",
                "SHORT_TERM",
                "BLR001",
                "CHN001",
                5.0,
                "Per KM",
                48,
                "hours",
                "singh transporter pvt ltd",
                350,
                25,
                25,
                false
            }
        };
    }
    
    @DataProvider(name = "contractServiceTypes")
    public static Object[][] getContractServiceTypes() {
        return new Object[][] {
            {"LTL"},
            {"FTL"},
            {"PTL"}
        };
    }
    
    @DataProvider(name = "contractTypes")
    public static Object[][] getContractTypes() {
        return new Object[][] {
            {"PER_TRIP"},
            {"PER_KM"},
            {"PER_TON"}
        };
    }
    
    @DataProvider(name = "contractRequestTypes")
    public static Object[][] getContractRequestTypes() {
        return new Object[][] {
            {"LONG_TERM"},
            {"SHORT_TERM"}
        };
    }
    
    @DataProvider(name = "contractRateTypes")
    public static Object[][] getContractRateTypes() {
        return new Object[][] {
            {"Flat"},
            {"Per KM"},
            {"Per Ton"}
        };
    }
    
    @DataProvider(name = "contractTatUnits")
    public static Object[][] getContractTatUnits() {
        return new Object[][] {
            {"hours"},
            {"days"}
        };
    }
    
    @DataProvider(name = "contractOrigins")
    public static Object[][] getContractOrigins() {
        return new Object[][] {
            {"JNP123"},
            {"MUM001"},
            {"BLR001"},
            {"DEL001"}
        };
    }
    
    @DataProvider(name = "contractDestinations")
    public static Object[][] getContractDestinations() {
        return new Object[][] {
            {"ISPAT_GGN"},
            {"DEL001"},
            {"CHN001"},
            {"MUM001"}
        };
    }
    
    @DataProvider(name = "contractVendors")
    public static Object[][] getContractVendors() {
        return new Object[][] {
            {"SIN9393", "singh transporter pvt ltd"},
            {"SIN9394", "test transporter pvt ltd"},
            {"SIN9395", "sample transporter pvt ltd"}
        };
    }
} 