package com.delhivery.Express.dataprovider;

import org.testng.annotations.DataProvider;

public class manifestationData {

    //Data provider for Key_different_data_types
    @DataProvider(name = "Key_different_data_types", parallel = true)
    public static Object[][] Key_different_data_types() {
        return new Object[][]{
                {"Scenario:: When key value = empty string ", ""},
                {"Scenario:: When key value = null ", null},
                {"Scenario:: When key value = integer ", 1234},
                {"Scenario:: When key value = boolean true ", true},
                {"Scenario:: When key value = boolean false ", false},
                {"Scenario:: When key value = string ", "abc"},
                {"Scenario:: When key value = valid value ", ""},
                {"Scenario:: When key value = string integer ", "123456"},
                {"Scenario:: When key value = string null ", "null"},
                {"Scenario:: When key value = special char ", "@#$*()?"},
                {"Scenario:: When key value = boolean in string ", "true"},
                {"Scenario:: When key value = boolean in string ", "false"},
                {"Scenario:: When key value = float ", 123.456},
                {"Scenario:: When key value = long int - Length check ", "123456789123456781234"},
                {"Scenario:: When key value = long string - Length check ", "test string for check of lenth - testingvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"},
                {"Scenario:: When key value = containing trailing spaces ", "      abc            "}
        };
    }

    //Data provider for Different_pdt_payment_types
    //Payment types = prepaid, postpaid, cod, cash, pickup, repl
    //pdt = B2C, B2B, Heavy, DOC
    @DataProvider(name = "Different_pdt_payment_types", parallel = true)
    public static Object[][] Different_pdt_payment_types() {
        return new Object[][]{
                {"Scenario:: When pdt = B2C and payment type = pickup ", "B2C", "pickup"},
                {"Scenario:: When pdt = B2C and payment type = prepaid ", "B2C", "prepaid"},
                {"Scenario:: When pdt = B2C and payment type = postpaid ", "B2C", "postpaid"},
                {"Scenario:: When pdt = B2C and payment type = cod ", "B2C", "cod"},
                {"Scenario:: When pdt = B2C and payment type = cash ", "B2C", "cash"},
                {"Scenario:: When pdt = B2C and payment type = repl ", "B2C", "repl"},
                {"Scenario:: When pdt = B2B and payment type = prepaid ", "B2B", "prepaid"},
                {"Scenario:: When pdt = B2B and payment type = postpaid ", "B2B", "postpaid"},
                {"Scenario:: When pdt = B2B and payment type = cod ", "B2B", "cod"},
                {"Scenario:: When pdt = B2B and payment type = cash ", "B2B", "cash"},
                {"Scenario:: When pdt = B2B and payment type = pickup ", "B2B", "pickup"},
                {"Scenario:: When pdt = B2B and payment type = repl ", "B2B", "repl"},
                {"Scenario:: When pdt = Heavy and payment type = prepaid ", "Heavy", "prepaid"},
                {"Scenario:: When pdt = Heavy and payment type = postpaid ", "Heavy", "postpaid"},
                {"Scenario:: When pdt = Heavy and payment type = cod ", "Heavy", "cod"},
                {"Scenario:: When pdt = Heavy and payment type = cash ", "Heavy", "cash"},
                {"Scenario:: When pdt = Heavy and payment type = pickup ", "Heavy", "pickup"},
                {"Scenario:: When pdt = Heavy and payment type = repl ", "Heavy", "repl"},
                {"Scenario:: When pdt = DOC and payment type = prepaid ", "DOC", "prepaid"},
                {"Scenario:: When pdt = DOC and payment type = postpaid ", "DOC", "postpaid"},
                {"Scenario:: When pdt = DOC and payment type = cod ", "DOC", "cod"},
                {"Scenario:: When pdt = DOC and payment type = cash ", "DOC", "cash"},
                {"Scenario:: When pdt = DOC and payment type = pickup ", "DOC", "pickup"},
                {"Scenario:: When pdt = DOC and payment type = repl ", "DOC", "repl"}
        };
    }

    @DataProvider(name = "Different_state_type", parallel = true)
    public static Object[][] Different_state_type() {
        return new Object[][]{
                {"Scenario:: When NSL is applied to manifested package  ", "B2C", "MANIFEST"},
                {"Scenario:: When NSL is applied to In transit package", "B2C", "IN TRANSIT"},
                {"Scenario::  When NSL is applied to Pending package ", "B2C", "PENDING"},
                {"Scenario:: When NSL is applied to Delivered package", "B2C", "DELIVERED"},
                {"Scenario:: When NSL is applied to Returned package ", "B2C", "RETURNED"},
                {"Scenario:: When NSL is applied to PICKUPPENDING package ", "B2C", "PICKUPPENDING"},
                {"Scenario:: When NSL is applied to PickedUp package ", "B2C", "PICKEDUP"},
                {"Scenario:: When NSL is applied to Cancelled package ", "B2C", "CANCELLED"}
        };
    }

    @DataProvider(name = "Different_type_pkg", parallel = true)
    public static Object[][] Different_type_pkg() {
        return new Object[][]{
                {"Scenario:: When NSL is applied to B2C manifested package  ", "B2C"},
                {"Scenario:: When NSL is applied to B2C MPS package", "B2C MPS"},
                {"Scenario:: When NSL is applied to B2B package ", "B2B"},
                {"Scenario:: When NSL is applied to B2B MPS package", "B2B MPS"},
                {"Scenario:: When NSL is applied to HEAVY package ", "HEAVY"},
                {"Scenario:: When NSL is applied to HEAVY MPS package ", "HEAVY MPS"},
                {"Scenario:: When NSL is applied to B2B MPS WITH INTERNAL CHILD package ", "B2B MPS WITH INTERNAL CHILD"},
                {"Scenario:: When NSL is applied to MPS WITH MCOUNT 1 package ", "MPS WITH MCOUNT 1"},
                {"Scenario:: When NSL is applied to NO DATA package  ", "NO DATA"},
                {"Scenario::  When NSL is applied to PARTIALLY MANIFESTED package ", "PARTIALLY MANIFESTED"},
                {"Scenario:: When NSL is applied to NO DATA WITHOUT MANIFESTATION package", "NO DATA WITHOUT MANIFESTATION"}

        };
    }
    
    @DataProvider(name = "SMS_For_different_Pkg_type", parallel = false)
    public static Object[][] SMS_For_different_Pkg_type() {
        return new Object[][]{
                {"Scenario:: SMS for QC failed package", "B2C", "pickup"},
                {"Scenario:: SMS for delivered package", "B2C", "prepaid"},
                {"Scenario:: SMS for prepaid package", "B2C", "prepaid"},
                {"Scenario:: SMS for COD package", "B2C", "COD"}
        };
    }
    

    @DataProvider(name = "Different_pdt_types", parallel = true)
    public static Object[][] Different_pdt_types() {
        return new Object[][]{
                {"B2C"},
                {"B2B"},
                {"Heavy"},
                {"DOC"}
        };
    }

    @DataProvider(name = "Different_pt_types", parallel = true)
    public static Object[][] Different_pt_types() {
        return new Object[][]{
                {"prepaid"},
                {"postpaid"},
                {"pickup"},
                {"cod"},
                {"cash"},
                {"REPL"}
        };
    }

    //Data provider for different state pkg
    //state =DELIVERED, RTO, DTO, Dispatched
    @DataProvider(name = "Different_state_type_pkg_for_dd_unset", parallel = true)
    public static Object[][] differentStateTypePkgForDDUnset() {
        return new Object[][]{
                {"Scenario:: When NSL is applied to REPL RETURNED", "REPL RETURNED"},
                {"Scenario:: When NSL is applied to DTO", "DTO"},
                {"Scenario:: When NSL is applied to REPL PICKUP", "REPL PICKUP"},
        };
    }
    
    
    //Data provider for different state pkg
    //state =DELIVERED, RTO, DTO, Dispatched
    @DataProvider(name = "Different_state_type_pkg", parallel = true)
    public static Object[][] Different_state_type_pkg() {
        return new Object[][]{
                {"Scenario:: When NSL is applied to Delivered package  ", "DELIVERED", "B2C"},
                {"Scenario:: When NSL is applied to RTO package", "RTO", "B2C"},
                {"Scenario::  When NSL is applied to DTO package ", "DTO", "B2C"},
                {"Scenario:: When NSL is applied to Dispatched package", "DISPATCHED", "B2C"},
                {"Scenario:: When NSL is applied to Lost package", "LOST", "B2C"},
                {"Scenario:: When NSL is applied to Delivered package  ", "DELIVERED", "B2B"},
                {"Scenario:: When NSL is applied to RTO package", "RTO", "B2B"},
                {"Scenario::  When NSL is applied to DTO package ", "DTO", "B2B"},
                {"Scenario:: When NSL is applied to Dispatched package", "DISPATCHED", "B2B"},
                {"Scenario:: When NSL is applied to Lost package", "LOST", "B2B"},
                {"Scenario:: When NSL is applied to Delivered package  ", "DELIVERED", "Heavy"},
                {"Scenario:: When NSL is applied to RTO package", "RTO", "Heavy"},
                {"Scenario::  When NSL is applied to DTO package ", "DTO", "Heavy"},
                {"Scenario:: When NSL is applied to Lost package", "LOST", "Heavy"},
                {"Scenario:: When NSL is applied to Dispatched package", "DISPATCHED", "Heavy"},
                {"Scenario:: When NSL is applied to Delivered package  ", "DELIVERED", "DOC"},
                {"Scenario:: When NSL is applied to RTO package", "RTO", "DOC"},
                {"Scenario::  When NSL is applied to DTO package ", "DTO", "DOC"},
                {"Scenario:: When NSL is applied to Dispatched package", "DISPATCHED", "DOC"},
                {"Scenario:: When NSL is applied to Lost package", "LOST", "DOC"}
        };
    }

    @DataProvider(name = "all_different_state_type_pkg", parallel = true)
    public static Object[][] allDifferentTypePackageState() {
        return new Object[][]{
                {"Scenario:: When NSL is applied to MANIFEST state shipments", "MANIFEST"},
                {"Scenario:: When NSL is applied to IN TRANSIT state shipment", "IN TRANSIT"},
                {"Scenario:: When NSL is applied to PENDING state shipment ", "PENDING"},
                {"Scenario:: When NSL is applied to DISPATCHED state shipment", "DISPATCHED"},
                {"Scenario:: When NSL is applied to DELIVERED state shipment", "DELIVERED"},
                {"Scenario:: When NSL is applied to RETURNED state shipment", "RETURNED"},
                {"Scenario:: When NSL is applied to REPL PICKUP state shipment", "REPL PICKUP"},
                {"Scenario:: When NSL is applied to REPL RETURNED state shipment", "REPL RETURNED"},
                {"Scenario:: When NSL is applied to RTO state shipment", "RTO"},
                {"Scenario:: When NSL is applied to LOST state shipment", "LOST"},
                {"Scenario:: When NSL is applied to PICKUPPENDING state shipment", "PICKUPPENDING"},
                {"Scenario:: When NSL is applied to PICKEDUP state shipment", "PICKEDUP"},
                {"Scenario:: When NSL is applied to DTO state shipment", "DTO"},
                {"Scenario:: When NSL is applied to CANCELLED state shipment", "CANCELLED"},
        };
    }

    @DataProvider(name="different_env")
    public static Object[][] getDiffEnv(){
        return new Object[][]{
                {"Scenario:: When env is edt","edt"},
                {"Scenario:: When env is stage","staging"},
        };
    }

    @DataProvider(name = "different_type_state", parallel = true)
    public static Object[][] getDifferentState() {
        return new Object[][]{
                {"Scenario::when state is IN TRANSIT", "IN TRANSIT"},
                {"Scenario::when state is IN PENDING", "PENDING"},
                {"Scenario::when state is RETURNED", "RETURNED"},
                {"Scenario::when state is DELIVERED", "DELIVERED"},
                {"Scenario::when state is MANIFEST", "MANIFEST"}
        };
    }

    @DataProvider(name = "different_payment_type", parallel = true)
    public static Object[][] getDifferentPaymentType() {
        return new Object[][]{
                {"Scenario::when payment is COD", "cod"},
                {"Scenario::when payment is Pre-paid", "Pre-paid"},
                {"Scenario::when payment is Pickup", "Pickup"},
                {"Scenario::when payment is REPL", "REPL"}
        };
    }
    
  //Data provider for different bagging endpoints
    @DataProvider(name = "Different_Bagging_endpoints", parallel = true)
    public Object[][] Different_Bagging_endpoints() {
        return new Object[][]{
                {"Scenario:: Create bag using v2"},
                {"Scenario:: Create bag using v3"},
                {"Scenario:: Create bag using v4"},
                {"Scenario:: Create bag using instabagging"}
        };
    }

    @DataProvider(name = "Partial_Manifest_Improvements", parallel = true)
    public Object[][] Partial_Manifest_Improvements() {
        return new Object[][]{
                {"Scenario:: NO DATA package  ", "NO DATA"},
                {"Scenario:: PARTIALLY MANIFESTED package ", "PARTIALLY MANIFESTED"},
                {"Scenario:: NO DATA WITHOUT MANIFESTATION package", "NO DATA WITHOUT MANIFESTATION"}
        };
    }

}