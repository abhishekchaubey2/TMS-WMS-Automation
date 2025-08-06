package com.delhivery.Express.testModules.ManifestationService;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
    //Data provider for Key_different_data_types
    @DataProvider(name = "Key_different_data_types", parallel = false)
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
    @DataProvider(name = "Different_pdt_payment_types", parallel = false)
    public static Object[][] Different_pdt_payment_types() {
        return new Object[][]{
                {"Scenario:: When pdt = B2C and payment type = prepaid ", "B2C", "prepaid"},
                {"Scenario:: When pdt = B2C and payment type = postpaid ", "B2C", "postpaid"},
                {"Scenario:: When pdt = B2C and payment type = cod ", "B2C", "cod"},
                {"Scenario:: When pdt = B2C and payment type = cash ", "B2C", "cash"},
                {"Scenario:: When pdt = B2C and payment type = pickup ", "B2C", "pickup"},
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


    //Data provider for Different_dates
    //dates = today, tomorrow, yesterday, future date, past date, null date, invalid
    @DataProvider(name = "date_related_cases", parallel = false)
    public static Object[][] Different_dates() {
        return new Object[][]{
                //function to fetch date in yyyy-MM-dd'T'HH:mm:ss.SSS format
                {"Scenario:: When date = today ", Utilities.getDateTime(0)},
                {"Scenario:: When date = tomorrow ", Utilities.getDateTime(0)},
                {"Scenario:: When date = yesterday ", Utilities.getDateTime(-1)},
                {"Scenario:: When date = null date ", null},
                {"Scenario:: When date = invalid ", "abc"},
                {"Scenario:: When date = past date ", "1900-10-31T16:01:18.116"},
                {"Scenario:: When date = future date ", "2100-10-31T16:01:18.116"}
        };

    }

    //Data provider for ewbn_related_cases

    @DataProvider(name = "ewbn_related_cases", parallel = false)
    public static Object[][] EWBN() {
        return new Object[][]{
                {"Scenario:: When ewbn is not required for the shipment.", "40000", "ewbnRequired:: no"},
                {"Scenario:: When ewbn is not required and valid ewbn is passed in payload", "40000", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is not required and already used ewbn is passed in payload", "40000", "ewbnRequired:: no"},
                {"Scenario:: When ewbn is not required for multi-item and valid ewbn is passed in both item", "40000", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is not required for multi-item and already used ewbn is passed in both item", "40000", "ewbnRequired:: no"},
                {"Scenario:: When ewbn is not required for multi-item and valid ewbn is passed in one item", "40000", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is not required for multi-item and already used ewbn is passed in one item", "40000", "ewbnRequired:: no"},
                {"Scenario:: When ewbn is not required and is not passed in payload", "40000", "ewbnRequired:: no"},
                {"Scenario:: When ewbn is required for the shipment and ewbn is not passed in the payload. ", "50001", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is required for the shipment and ewbn is passed is valid in the payload. ", "50001", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is required for the shipment and ewbn is passed is already used in the payload. ", "50001", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is required for the multi-item shipment and is passed in only one item in the payload", "50001", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is required for the multi-item shipment and is passed in both items in the payload", "50001", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is required for the multi-item shipment and is passed in both items in the payload and one of the ewbn is already used", "50001", "ewbnRequired:: yes"},
                {"Scenario:: When ewbn is required for the multi-item shipment and is passed in both items in the payload and one of the ewbn is not passed", "50001", "ewbnRequired:: yes"}


        };
    }

    //Data provider for addfix callback cases
    //cases = valid, invalid, fraud address
    //address = secondary address, return address, fm address, Address, seller address, billing address, buybackAddress, chequeaddress
    @DataProvider(name = "addfix_callback_cases", parallel = false)
    public static Object[][] addfix_callback_cases() {
        return new Object[][]{
                {"Scenario:: When Address is passed as valid address", "Test address"},
                {"Scenario:: When Address is passed as invalid address", "abc"},
                {"Scenario:: When Address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When secondary address is passed as valid address", "Test address"},
                {"Scenario:: When secondary address is passed as invalid address", "abc"},
                {"Scenario:: When secondary address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When return address is passed as valid address", "Test address"},
                {"Scenario:: When return address is passed as invalid address", "abc"},
                {"Scenario:: When return address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When fm address is passed as valid address", "Test address"},
                {"Scenario:: When fm address is passed as invalid address", "abc"},
                {"Scenario:: When fm address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When seller address is passed as valid address", "Test address"},
                {"Scenario:: When seller address is passed as invalid address", "abc"},
                {"Scenario:: When seller address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When billing address is passed as valid address", "Test address"},
                {"Scenario:: When billing address is passed as invalid address", "abc"},
                {"Scenario:: When billing address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When buyback address is passed as valid address", "Test address"},
                {"Scenario:: When buyback address is passed as invalid address", "abc"},
                {"Scenario:: When buyback address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When cheque address is passed as valid address", "Test address"},
                {"Scenario:: When cheque address is passed as invalid address", "abc"},
                {"Scenario:: When cheque address is passed as fraud address", "vivekanand colony"},
                {"Scenario:: When secondary address is passed as valid address", "Test address"},
                {"Scenario:: When secondary address is passed as invalid address", "abc"},
                {"Scenario:: When secondary address is passed as fraud address", "Test address"},
                {"Scenario:: When return address is passed as valid address", "Test address"},
                {"Scenario:: When return address is passed as invalid address", "abc"},
                {"Scenario:: When return address is passed as fraud address", "Test address"},
                {"Scenario:: When fm address is passed as valid address", "Test address"},
                {"Scenario:: When fm address is passed as invalid address", "abc"},
                {"Scenario:: When fm address is passed as fraud address", "Test address"},
                {"Scenario:: When seller address is passed as valid address", "Test address"},
                {"Scenario:: When seller address is passed as invalid address", "abc"},
                {"Scenario:: When seller address is passed as fraud address", "Test address"},
                {"Scenario:: When billing address is passed as valid address", "Test address"},
                {"Scenario:: When billing address is passed as invalid address", "abc"},
                {"Scenario:: When billing address is passed as fraud address", "Test address"},
                {"Scenario:: When buyback address is passed as valid address", "Test address"},
                {"Scenario:: When buyback address is passed as invalid address", "abc"},
                {"Scenario:: When buyback address is passed as fraud address", "Test address"},
                {"Scenario:: When cheque address is passed as valid address", "Test address"},
                {"Scenario:: When cheque address is passed as invalid address", "abc"},
                {"Scenario:: When cheque address is passed as fraud address", "Test address"}
        };
    }

    //Data provider for catfight callback cases
    //cases = valid, invalid, fraud product description
    @DataProvider(name = "catfight_callback_cases", parallel = false)
    public static Object[][] catfight_callback_cases() {
        return new Object[][]{
                {"Scenario:: When product description is passed as valid description", "Test product description"},
                {"Scenario:: When product description is passed as invalid description", "abc"},
                {"Scenario:: When product description is passed as fraud description", "Godoli"}
        };
    }

    //Data provider for ucid callback cases
    //cases = valid, invalid, fraud phone number
    //phone number = Phone, fm phone, return phone
    @DataProvider(name = "ucid_callback_cases", parallel = false)
    public static Object[][] ucid_callback_cases() {
        return new Object[][]{
                {"Scenario:: When Phone number is passed as valid phone number", "9617113651"},
                {"Scenario:: When Phone number is passed as invalid phone number", "abc"},
                {"Scenario:: When Phone number is passed as fraud phone number", "9425113008"},
                {"Scenario:: When fm phone number is passed as valid phone number", "9617113651"},
                {"Scenario:: When fm phone number is passed as invalid phone number", "abc"},
                {"Scenario:: When fm phone number is passed as fraud phone number", "9425113008"},
                {"Scenario:: When return phone number is passed as valid phone number", "9617113651"},
                {"Scenario:: When return phone number is passed as invalid phone number", "abc"},
                {"Scenario:: When return phone number is passed as fraud phone number", "9425113008"}
        };
    }

    //Data provider for uaid callback cases
    //cases = valid, invalid, fraud address
    //address = secondary address, return address, fm address, Address
    @DataProvider(name = "uaid_callback_cases", parallel = false)
    public static Object[][] uaid_callback_cases() {
        return new Object[][]{
                {"Scenario:: When Address is passed as valid address", "Test address"},
                {"Scenario:: When Address is passed as invalid address", "abc"},
                {"Scenario:: When Address is passed as fraud address", "Test address"},
                {"Scenario:: When secondary address is passed as valid address", "Test address"},
                {"Scenario:: When secondary address is passed as invalid address", "abc"},
                {"Scenario:: When secondary address is passed as fraud address", "Test address"},
                {"Scenario:: When return address is passed as valid address", "Test address"},
                {"Scenario:: When return address is passed as invalid address", "abc"},
                {"Scenario:: When return address is passed as fraud address", "Test address"},
                {"Scenario:: When fm address is passed as valid address", "Test address"},
                {"Scenario:: When fm address is passed as invalid address", "abc"},
                {"Scenario:: When fm address is passed as fraud address", "Test address"}

        };
    }

    //Data provider for Od Tat callback cases
    //pincode = 474011, 400059, 110030
    @DataProvider(name = "Od_Tat_callback_cases", parallel = false)
    public static Object[][] Od_Tat_callback_cases() {
        return new Object[][]{
                {"Scenario:: When pincode is passed as 474011", "474011"},
                {"Scenario:: When pincode is passed as 400059", "400059"},
                {"Scenario:: When pincode is passed as 110030", "110030"}
        };
    }

    //Data provider for client config cases
    //Service related cases = prepaid, cod, cash, pickup, repl, No Data
    //pdt = B2C, B2B, Heavy
    //payment type = prepaid, postpaid, cod, cash, pickup, repl
    @DataProvider(name = "client_config_cases_services", parallel = false)
    public static Object[][] client_config_cases() {
        return new Object[][]{
                {"Scenario:: When services = cod ", "COD", "B2C", "prepaid"},
                {"Scenario:: When services = cod ", "COD", "B2B", "cod"},
                {"Scenario:: When services = cod ", "COD", "heavy", "REPL"},
                {"Scenario:: When services = cod ", "COD", "B2C", "pickup"},
                {"Scenario:: When services = cod ", "COD", "B2B", "No Data"},
                {"Scenario:: When services = prepaid ", "prepaid", "B2C", "prepaid"},
                {"Scenario:: When services = prepaid ", "prepaid", "B2B", "cod"},
                {"Scenario:: When services = prepaid ", "prepaid", "heavy", "REPL"},
                {"Scenario:: When services = prepaid ", "prepaid", "B2C", "pickup"},
                {"Scenario:: When services = prepaid ", "prepaid", "B2B", "No Data"},
                {"Scenario:: When services = cash ", "Cash", "B2C", "prepaid"},
                {"Scenario:: When services = cash ", "Cash", "B2B", "cod"},
                {"Scenario:: When services = cash ", "Cash", "heavy", "REPL"},
                {"Scenario:: When services = cash ", "Cash", "B2C", "pickup"},
                {"Scenario:: When services = cash ", "Cash", "B2B", "No Data"},
                {"Scenario:: When services = pickup ", "Pickup", "B2C", "prepaid"},
                {"Scenario:: When services = pickup ", "Pickup", "B2B", "cod"},
                {"Scenario:: When services = pickup ", "Pickup", "heavy", "REPL"},
                {"Scenario:: When services = pickup ", "Pickup", "B2C", "pickup"},
                {"Scenario:: When services = pickup ", "Pickup", "B2B", "No Data"},
                {"Scenario:: When services = repl ", "REPL", "B2C", "prepaid"},
                {"Scenario:: When services = repl ", "REPL", "B2B", "cod"},
                {"Scenario:: When services = repl ", "REPL", "heavy", "REPL"},
                {"Scenario:: When services = repl ", "REPL", "B2C", "pickup"},
                {"Scenario:: When services = repl ", "REPL", "B2B", "No Data"},
                {"Scenario:: When services = No Data ", "No Data", "B2C", "prepaid"},
                {"Scenario:: When services = No Data ", "No Data", "B2B", "cod"},
                {"Scenario:: When services = No Data ", "No Data", "heavy", "REPL"},
                {"Scenario:: When services = No Data ", "No Data", "B2C", "pickup"},
                {"Scenario:: When services = No Data ", "No Data", "B2B", "No Data"}


        };
    }

    //Data provider for client config cases
    //Billing Method related cases = prepaid, postpaid, cod_netoff
    //pdt = B2C, B2B, Heavy
    //payment type = prepaid, cod, cash, pickup, repl
    @DataProvider(name = "client_config_billing_method_cases", parallel = false)
    public static Object[][] client_config_billing_method_cases() {
        return new Object[][]{
                {"Scenario:: When billing method = prepaid ", "prepaid", "B2C", "prepaid"},
                {"Scenario:: When billing method = prepaid ", "prepaid", "B2B", "cod"},
                {"Scenario:: When billing method = prepaid ", "prepaid", "heavy", "REPL"},
                {"Scenario:: When billing method = prepaid ", "prepaid", "B2C", "pickup"},
                {"Scenario:: When billing method = prepaid ", "prepaid", "B2B", "No Data"},
                {"Scenario:: When billing method = postpaid ", "postpaid", "B2C", "prepaid"},
                {"Scenario:: When billing method = postpaid ", "postpaid", "B2B", "cod"},
                {"Scenario:: When billing method = postpaid ", "postpaid", "heavy", "REPL"},
                {"Scenario:: When billing method = postpaid ", "postpaid", "B2C", "pickup"},
                {"Scenario:: When billing method = postpaid ", "postpaid", "B2B", "No Data"},
                {"Scenario:: When billing method = cod_netoff ", "cod_netoff", "B2C", "prepaid"},
                {"Scenario:: When billing method = cod_netoff ", "cod_netoff", "B2B", "cod"},
                {"Scenario:: When billing method = cod_netoff ", "cod_netoff", "heavy", "REPL"},
                {"Scenario:: When billing method = cod_netoff ", "cod_netoff", "B2C", "pickup"},
                {"Scenario:: When billing method = cod_netoff ", "cod_netoff", "B2B", "No Data"}
        };
    }

    //Data provider for client config cases
    //Billing Mode related cases = ES, SE, E, S, null
    //pdt = B2C
    //mot = Express, Surface
    @DataProvider(name = "client_config_billing_mode_cases", parallel = false)
    public static Object[][] client_config_billing_mode_cases() {
        return new Object[][]{
                {"Scenario:: When billing mode = ES ", "ES", "B2C", "Express"},
                {"Scenario:: When billing mode = ES ", "ES", "B2C", "Surface"},
                {"Scenario:: When billing mode = SE ", "SE", "B2C", "Express"},
                {"Scenario:: When billing mode = SE ", "SE", "B2C", "Surface"},
                {"Scenario:: When billing mode = E ", "E", "B2C", "Express"},
                {"Scenario:: When billing mode = E ", "E", "B2C", "Surface"},
                {"Scenario:: When billing mode = S ", "S", "B2C", "Express"},
                {"Scenario:: When billing mode = S ", "S", "B2C", "Surface"},
                {"Scenario:: When billing mode = null ", null, "B2C", "Express"},
                {"Scenario:: When billing mode = null ", null, "B2C", "Surface"}
        };
    }

    //Data provider for client config cases
    //Product Type related cases = null, B2B, Heavy, C2C-Lite, DOC, KYC
    //pdt = B2C, B2B, Heavy, DOC, KYC, C2C-Lite
    //payment type = prepaid
    @DataProvider(name = "client_config_product_type_cases", parallel = false)
    public static Object[][] client_config_product_type_cases() {
        return new Object[][]{
                {"Scenario:: When product type = null ", null, "B2C", "prepaid"},
                {"Scenario:: When product type = null ", null, "B2B", "prepaid"},
                {"Scenario:: When product type = null ", null, "heavy", "prepaid"},
                {"Scenario:: When product type = null ", null, "DOC", "prepaid"},
                {"Scenario:: When product type = null ", null, "KYC", "prepaid"},
                {"Scenario:: When product type = null ", null, "C2C-Lite", "prepaid"},
                {"Scenario:: When product type = B2B ", "B2B", "B2C", "prepaid"},
                {"Scenario:: When product type = B2B ", "B2B", "B2B", "prepaid"},
                {"Scenario:: When product type = B2B ", "B2B", "heavy", "prepaid"},
                {"Scenario:: When product type = B2B ", "B2B", "DOC", "prepaid"},
                {"Scenario:: When product type = B2B ", "B2B", "KYC", "prepaid"},
                {"Scenario:: When product type = B2B ", "B2B", "C2C-Lite", "prepaid"},
                {"Scenario:: When product type = Heavy ", "heavy", "B2C", "prepaid"},
                {"Scenario:: When product type = Heavy ", "heavy", "B2B", "prepaid"},
                {"Scenario:: When product type = Heavy ", "heavy", "heavy", "prepaid"},
                {"Scenario:: When product type = Heavy ", "heavy", "DOC", "prepaid"},
                {"Scenario:: When product type = Heavy ", "heavy", "KYC", "prepaid"},
                {"Scenario:: When product type = Heavy ", "heavy", "C2C-Lite", "prepaid"},
                {"Scenario:: When product type = DOC ", "DOC", "B2C", "prepaid"},
                {"Scenario:: When product type = DOC ", "DOC", "B2B", "prepaid"},
                {"Scenario:: When product type = DOC ", "DOC", "heavy", "prepaid"},
                {"Scenario:: When product type = DOC ", "DOC", "DOC", "prepaid"},
                {"Scenario:: When product type = DOC ", "DOC", "KYC", "prepaid"},
                {"Scenario:: When product type = DOC ", "DOC", "C2C-Lite", "prepaid"},
                {"Scenario:: When product type = KYC ", "KYC", "B2C", "prepaid"},
                {"Scenario:: When product type = KYC ", "KYC", "B2B", "prepaid"},
                {"Scenario:: When product type = KYC ", "KYC", "heavy", "prepaid"},
                {"Scenario:: When product type = KYC ", "KYC", "DOC", "prepaid"},
                {"Scenario:: When product type = KYC ", "KYC", "KYC", "prepaid"},
                {"Scenario:: When product type = KYC ", "KYC", "C2C-Lite", "prepaid"},
                {"Scenario:: When product type = C2C-Lite ", "C2C-Lite", "B2C", "prepaid"},
                {"Scenario:: When product type = C2C-Lite ", "C2C-Lite", "B2B", "prepaid"},
                {"Scenario:: When product type = C2C-Lite ", "C2C-Lite", "heavy", "prepaid"},
                {"Scenario:: When product type = C2C-Lite ", "C2C-Lite", "DOC", "prepaid"},
                {"Scenario:: When product type = C2C-Lite ", "C2C-Lite", "KYC", "prepaid"},
                {"Scenario:: When product type = C2C-Lite ", "C2C-Lite", "C2C-Lite", "prepaid"}
        };
    }

    //Data provider for client config cases
    //Client type related cases = other_customers, long_tail_clients, e_tail, SMB, international, BFSI, co_loader, brands_enterprise, Aggregators, C2C, Franchise, SAAS, direct_client
    //pdt = B2C
    //payment type = prepaid
    @DataProvider(name = "client_config_client_type_cases", parallel = false)
    public static Object[][] client_config_client_type_cases() {
        return new Object[][]{
                {"Scenario:: When client type = other_customers ", "other_customers", "B2C", "prepaid"},
                {"Scenario:: When client type = long_tail_clients ", "long_tail_clients", "B2C", "prepaid"},
                {"Scenario:: When client type = e_tail ", "e_tail", "B2C", "prepaid"},
                {"Scenario:: When client type = SMB ", "SMB", "B2C", "prepaid"},
                {"Scenario:: When client type = international ", "international", "B2C", "prepaid"},
                {"Scenario:: When client type = BFSI ", "BFSI", "B2C", "prepaid"},
                {"Scenario:: When client type = co_loader ", "co_loader", "B2C", "prepaid"},
                {"Scenario:: When client type = brands_enterprise ", "brands_enterprise", "B2C", "prepaid"},
                {"Scenario:: When client type = Aggregators ", "Aggregators", "B2C", "prepaid"},
                {"Scenario:: When client type = C2C ", "C2C", "B2C", "prepaid"},
                {"Scenario:: When client type = Franchise ", "Franchise", "B2C", "prepaid"},
                {"Scenario:: When client type = SAAS ", "SAAS", "B2C", "prepaid"},
                {"Scenario:: When client type = direct_client ", "direct_client", "B2C", "prepaid"}
        };
    }

    //Data provider for client config cases
    //Nodes Tenant related cases = delhivery, edesh, grameenphone, advantis
    //pdt = B2C
    //payment type = prepaid
    @DataProvider(name = "client_config_nodes_tenant_cases", parallel = false)
    public static Object[][] client_config_nodes_tenant_cases() {
        return new Object[][]{
                {"Scenario:: When nodes tenant = delhivery ", "delhivery", "B2C", "prepaid"},
                {"Scenario:: When nodes tenant = edesh ", "edesh", "B2C", "prepaid"},
                {"Scenario:: When nodes tenant = grameenphone ", "grameenphone", "B2C", "prepaid"},
                {"Scenario:: When nodes tenant = advantis ", "advantis", "B2C", "prepaid"}
        };
    }

    //Data provider for client config cases
    //Check related cases = lock, enable_manifest_failure, weight_capture_enable, fragile_shipment, verified_add, capture_client_otp, mps_service, enable_ivr, auto_pickup, on_demand_fail, fail_manifest
    //pdt = B2C, B2B
    //payment type = prepaid, pickup
    @DataProvider(name = "client_config_check_cases", parallel = false)
    public static Object[][] client_config_check_cases() {
        return new Object[][]{
                {"Scenario:: When check = lock ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = lock ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = lock ", "false", "B2C", "pickup"},
                {"Scenario:: When check = lock ", "false", "B2B", "pickup"},
                {"Scenario:: When check = enable_manifest_failure ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = enable_manifest_failure ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = enable_manifest_failure ", "false", "B2C", "pickup"},
                {"Scenario:: When check = enable_manifest_failure ", "false", "B2B", "pickup"},
                {"Scenario:: When check = weight_capture_enable ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = weight_capture_enable ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = weight_capture_enable ", "false", "B2C", "pickup"},
                {"Scenario:: When check = weight_capture_enable ", "false", "B2B", "pickup"},
                {"Scenario:: When check = fragile_shipment ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = fragile_shipment ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = fragile_shipment ", "false", "B2C", "pickup"},
                {"Scenario:: When check = fragile_shipment ", "false", "B2B", "pickup"},
                {"Scenario:: When check = verified_add ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = verified_add ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = verified_add ", "false", "B2C", "pickup"},
                {"Scenario:: When check = verified_add ", "false", "B2B", "pickup"},
                {"Scenario:: When check = capture_client_otp ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = capture_client_otp ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = capture_client_otp ", "false", "B2C", "pickup"},
                {"Scenario:: When check = capture_client_otp ", "false", "B2B", "pickup"},
                {"Scenario:: When check = mps_service ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = mps_service ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = mps_service ", "false", "B2C", "pickup"},
                {"Scenario:: When check = mps_service ", "false", "B2B", "pickup"},
                {"Scenario:: When check = enable_ivr ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = enable_ivr ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = enable_ivr ", "false", "B2C", "pickup"},
                {"Scenario:: When check = enable_ivr ", "false", "B2B", "pickup"},
                {"Scenario:: When check = auto_pickup ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = auto_pickup ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = auto_pickup ", "false", "B2C", "pickup"},
                {"Scenario:: When check = auto_pickup ", "false", "B2B", "pickup"},
                {"Scenario:: When check = on_demand_fail ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = on_demand_fail ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = on_demand_fail ", "false", "B2C", "pickup"},
                {"Scenario:: When check = on_demand_fail ", "false", "B2B", "pickup"},
                {"Scenario:: When check = fail_manifest ", "true", "B2C", "prepaid"},
                {"Scenario:: When check = fail_manifest ", "true", "B2B", "prepaid"},
                {"Scenario:: When check = fail_manifest ", "false", "B2C", "pickup"},
                {"Scenario:: When check = fail_manifest ", "false", "B2B", "pickup"}


        };
    }


}
