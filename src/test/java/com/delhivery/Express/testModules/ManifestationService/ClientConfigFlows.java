package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;

public class ClientConfigFlows extends BaseTest {
    private static String UPL;
    static ApiController apiCtrl = new ApiController();
    public static String NewEnv = "NewManifest";
    public static String StagingEnv = "staging";
    private String waybill, bagId, tripId, dispatchId;
    private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<String, String>();
    public Map<String, Object> pkgFlowData;

    @Test(dataProvider = "client_config_cases_services", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_pdt_pt(String Scenario, Object serviceType, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        if (serviceType.equals("COD")) {
            ClientData.put("cod", "COD");
        } else if (serviceType.equals("Pre-paid")) {
            ClientData.put("prePaid", "Pre-paid");
        } else if (serviceType.equals("Cash")) {
            ClientData.put("Cash", "Cash");
        } else if (serviceType.equals("REPL")) {
            ClientData.put("repl", "REPL");
        } else if (serviceType.equals("Pickup")) {
            ClientData.put("pickup", "Pickup");
        } else if (serviceType.equals("No Data")) {
            ClientData.put("NoData", "No Data");
        }

        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Test case for billingMethod
    //Data Provider = client_config_billing_method_cases
    @Test(dataProvider = "client_config_billing_method_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_billing_method(String Scenario, Object billingMethod, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        ClientData.put("billingMethod", billingMethod);
        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Test case for billingMode
    //Data Provider = client_config_billing_mode_cases
    @Test(dataProvider = "client_config_billing_mode_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_billing_mode(String Scenario, Object billingMode, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        ClientData.put("billingMode", billingMode);
        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);


    }

    //Test case for productType cases
    //Data Provider = client_config_product_type_cases
    @Test(dataProvider = "client_config_product_type_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_product_type(String Scenario, Object productType, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        ClientData.put("productType", productType);
        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Test case for clientType cases
    //Data Provider = client_config_client_type_cases
    @Test(dataProvider = "client_config_client_type_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_client_type(String Scenario, Object clientType, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        ClientData.put("clientType", clientType);
        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Test case for nodesTenant cases
    //Data Provider = client_config_nodes_tenant_cases
    @Test(dataProvider = "client_config_nodes_tenant_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_nodes_tenant(String Scenario, Object nodesTenant, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        ClientData.put("nodesTenant", nodesTenant);
        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);

    }

    //Test case for check related cases
    //Data Provider = client_config_check_related_cases
    //Check related cases = lock, enable_manifest_failure, weight_capture_enable, fragile_shipment, verified_add, capture_client_otp, mps_service, enable_ivr, auto_pickup, on_demand_fail, fail_manifest
    @Test(dataProvider = "client_config_check_related_cases", dataProviderClass = DataProviderClass.class, enabled = true)
    public void manifestation_check_related(String Scenario, Object checkRelated, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        //Payload for Manifestation
        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        //Payload for Client Update
        HashMap<String, Object> ClientData = new HashMap<String, Object>();
        if (Scenario.contains("lock")) {
            ClientData.put("lock", checkRelated);
        } else if (Scenario.contains("enable_manifest_failure")) {
            ClientData.put("enableManifestFailure", checkRelated);
        } else if (Scenario.contains("weight_capture_enable")) {
            ClientData.put("weightCaptureEnable", checkRelated);
        } else if (Scenario.contains("fragile_shipment")) {
            ClientData.put("fragileShipment", checkRelated);
        } else if (Scenario.contains("verified_add")) {
            ClientData.put("verifiedAdd", checkRelated);
        } else if (Scenario.contains("capture_client_otp")) {
            ClientData.put("captureClientOtp", checkRelated);
        } else if (Scenario.contains("mps_service")) {
            ClientData.put("mpsService", checkRelated);
        } else if (Scenario.contains("enable_ivr")) {
            ClientData.put("enableIvr", checkRelated);
        } else if (Scenario.contains("auto_pickup")) {
            ClientData.put("autoPickup", checkRelated);
        } else if (Scenario.contains("on_demand_fail")) {
            ClientData.put("onDemandFail", checkRelated);
        } else if (Scenario.contains("fail_manifest")) {
            ClientData.put("failManifest", checkRelated);
        }
        apiCtrl.ClientUpdateNew(StagingEnv, ClientData);
        Utilities.hardWait(20);
        manifestForFirstBuilder(manifestData, Scenario);
    }
}
