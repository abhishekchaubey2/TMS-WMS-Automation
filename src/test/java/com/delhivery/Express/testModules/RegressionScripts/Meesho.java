package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;


import static com.delhivery.core.utils.Utilities.logInfo;

public class Meesho extends BaseTest {
    private final String product_type, payment_mode, pin, embargoPin, invalid_wbn, duplicate_orderId, caplimitPin, NonCapLimitPin;
    ApiController apiCtrl = new ApiController();
    private final ThreadLocal<HashMap<String, String>> manifestData = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestData = new ThreadLocal<>();
    protected ThreadLocal<String> UPL = new ThreadLocal<>();

    public Meesho() {
        this.product_type = "B2C";
        this.payment_mode = "Prepaid";
        this.pin = "400059";
        this.duplicate_orderId = "1234567890";
        this.invalid_wbn = "1234567891234";
        this.embargoPin = "900001";
        this.caplimitPin = "474011";
        this.NonCapLimitPin = "474012";
    }

    @DataProvider(name = "Meesho_Success_manifestation_cases", parallel = true)
    public Object[][] Meesho_Success_manifestation_cases() {
        return new Object[][]{
                {"Scenario:: Single shipment manifestation"},
                {"Scenario:: Single shipment capping limit manifestation"},
                {"Scenario:: MPS manifestation"},
                {"Scenario:: MPS manifestation for capping limit"},
        };
    }


    @Test(dataProvider = "Meesho_Success_manifestation_cases", enabled = true)
    public void verifyMeeshoSuccessManifestation(String scenario) throws IOException {
        manifestData.set(getManifestDataForSuccessManifestation(scenario).get());
        UPL.set(apiCtrl.meeshoSuccessManifestApi(manifestData.get()));
        System.out.println("UPL: " + UPL.get());
        logInfo("UPL generated " + UPL.get());
        Utilities.hardWait(30);
        if (scenario.contains("capping limit")) {
            requestData.set(getRequestDataForSuccessManifestation(UPL.get()).get());
            apiCtrl.getManifestationSuccessUplData(requestData.get());

        } else {
            apiCtrl.getManifestationUplData(UPL.get());
        }
        postProcess();
    }

    private ThreadLocal<HashMap<String, String>> getRequestDataForSuccessManifestation(String UPL) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> reqData = new HashMap<>();
            reqData.put("client", "meesho_embargo_client");
            reqData.put("upl", UPL);
            return reqData;
        });
    }

    private ThreadLocal<HashMap<String, String>> getManifestDataForSuccessManifestation(String scenario) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> manifestData = new HashMap<>();
            manifestData.put("product_type", product_type);
            manifestData.put("payment_mode", payment_mode);
            manifestData.put("pin", pin);
            if (scenario.contains("MPS")) {
                manifestData.put("package_count", "2");
            }
            if (scenario.contains("MPS manifestation for capping limit")) {
                manifestData.put("package_count", "2");
                manifestData.put("pin", caplimitPin);
                manifestData.put("client", "meesho_embargo_client");
            }
            if (scenario.contains("Single shipment capping limit manifestation")) {
                manifestData.put("pin", NonCapLimitPin);
                manifestData.put("client", "meesho_embargo_client");
            }
            return manifestData;
        });
    }


    @DataProvider(name = "Meesho_Error_manifestation_cases", parallel = true)
    public Object[][] Meesho_Error_manifestation_cases() {
        return new Object[][]{
                {"Scenario:: ClientWarehouse not passed", "ClientWarehouse matching query does not exist.", "ClientWarehouse matching query does not exist."},
                {"Scenario:: Invalid client token passed", "Invalid client!", "Invalid client!"}
        };
    }


    @Test(dataProvider = "Meesho_Error_manifestation_cases", enabled = true)
    public void verifyMeeshoErrorManifestation(String scenario, String manifestApiErrorRemark, String uplDataApiErrorRemark) throws IOException {
        //Meesho manifest API logic
        HashMap<String, String> manifestData, reqData;
        manifestData = new HashMap<String, String>();
        manifestData.put("product_type", product_type);
        manifestData.put("payment_mode", payment_mode);
        manifestData.put("pin", pin);
        if (scenario.contains("ClientWarehouse")) {
            manifestData.put("pickup_location", "");
        }

        if (scenario.contains("Invalid client")) {
            manifestData.put("client", "invalid_regression_client_token");
        }

        String UPL = apiCtrl.meeshoErrorManifestApi(manifestData, manifestApiErrorRemark);
        logInfo("UPL generated " + UPL);
        Utilities.hardWait(30);

        //UPL API logic
        reqData = new HashMap<String, String>();
        if (scenario.contains("Invalid client")) {
            reqData.put("upl", UPL);
            reqData.put("scenario", manifestApiErrorRemark);
        }
        if (scenario.contains("ClientWarehouse")) {
            reqData.put("upl", UPL);
            reqData.put("remark", uplDataApiErrorRemark);
        }

        if (!reqData.isEmpty()) {
            apiCtrl.getManifestationErrorUplData(reqData);

        } else {
            apiCtrl.getManifestationErrorUplData(UPL);

        }
        postProcess();
    }

    @DataProvider(name = "Meesho_Failure_manifestation_cases", parallel = true)
    public Object[][] Meesho_Failure_manifestation_cases() {
        return new Object[][]{
                {"Scenario:: Pincode not passed", "Crashing while saving package due to exception Consignee pin code is invalid. Package might have been partially saved."},
                {"Scenario:: Duplicate order id passed", "Crashing while saving package due to exception Duplicate Order Id : duplicate_orderId. Package might have been partially saved."},
                {"Scenario:: Invalid waybill passed", "Crashing while saving package due to exception wbn: invalid_wbn is not available. Package might have been partially saved."},
                {"Scenario:: Consignee name not passed", "Crashing while saving package due to exception consignee name not provided. Package might have been partially saved."},
                {"Scenario:: Phone not passed", "Crashing while saving package due to exception No phone number provided.. Package might have been partially saved."},
                {"Scenario:: Payment mode not passed", "Crashing while saving package due to exception Payment mode \"\" could not be standardized, check you have specified payment mode for packages. Package might have been partially saved."},
                {"Scenario:: Address not passed", "Crashing while saving package due to exception add is required.Check if there is any requiredfield missing against package for data. Package might have been partially saved."},
                {"Scenario:: Embargo pin passed", "Crashing while saving package due to exception embargoPin is non serviceable pincode. Package might have been partially saved."},
                {"Scenario:: Capping Limit pin passed", "Crashing while saving package due to exception Manifestation failed as the total shipment volume has exceeded the available capacity of this pincode. Package might have been partially saved."}
        };
    }


    @Test(dataProvider = "Meesho_Failure_manifestation_cases", enabled = true)
    public void verifyMeeshoFailureManifestation(String scenario, String manifestApiErrorRemark) throws IOException {
        //Meesho manifest API logic
        manifestData.set(getManifestDataForFailureManifestation(scenario, manifestApiErrorRemark).get());
        UPL.set(apiCtrl.meeshoFailureManifestApi(manifestData.get(), manifestData.get().get("manifestApiErrorRemark")));
        logInfo("UPL generated " + UPL.get());
        Utilities.hardWait(30);

        //UPL API logic
        ThreadLocal<HashMap<String, String>> requestData = getRequestDataForFailureManifestation(scenario, UPL.get());


        if (!requestData.get().isEmpty()) {
            apiCtrl.getManifestationFailureUplData(requestData.get());

        } else {
            apiCtrl.getManifestationFailureUplData(UPL.get());
        }
        postProcess();
    }

    private ThreadLocal<HashMap<String, String>> getRequestDataForFailureManifestation(String scenario, String uplid) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> reqData = new HashMap<>();
            if (scenario.contains("Embargo")) {
                reqData.put("client", "meesho_embargo_client");
                reqData.put("upl", uplid);
            }
            if (scenario.contains("Payment mode")) {
                reqData.put("scenario", "Payment mode");
                reqData.put("upl", uplid);
            }
            if (scenario.contains("Capping Limit")) {
                reqData.put("client", "meesho_embargo_client");
                reqData.put("upl", uplid);
            }
            return reqData;
        });
    }

    private ThreadLocal<HashMap<String, String>> getManifestDataForFailureManifestation(String scenario, String manifestApiErrorRemark) {
        final String finalManifestApiErrorRemark = manifestApiErrorRemark;
        return ThreadLocal.withInitial(() -> {
            HashMap<String, String> manifestData;
            manifestData = new HashMap<>();
            manifestData.put("product_type", product_type);
            manifestData.put("payment_mode", payment_mode);
            manifestData.put("pin", pin);
            String remark = null;
            if (scenario.contains("Pincode")) {
                manifestData.put("pin", "");
            }
            if (scenario.contains("order id")) {
                manifestData.put("order", duplicate_orderId);
                remark = finalManifestApiErrorRemark.replace("duplicate_orderId", duplicate_orderId);
            }
            if (scenario.contains("Invalid waybill")) {
                manifestData.put("waybill", invalid_wbn);
                remark = finalManifestApiErrorRemark.replace("invalid_wbn", invalid_wbn);
            }
            if (scenario.contains("Consignee name")) {
                manifestData.put("name", "");
            }
            if (scenario.contains("Phone")) {
                manifestData.put("phone", "");
            }
            if (scenario.contains("Payment mode")) {
                manifestData.put("payment_mode", "");
            }
            if (scenario.contains("Address")) {
                manifestData.put("add", "");
            }
            if (scenario.contains("Embargo")) {
                manifestData.put("client", "meesho_embargo_client");
                manifestData.put("pin", embargoPin);
                remark = finalManifestApiErrorRemark.replace("embargoPin", embargoPin);
            }
            if (scenario.contains("Capping Limit")) {
                manifestData.put("client", "meesho_embargo_client");
                manifestData.put("pin", caplimitPin);
                remark = finalManifestApiErrorRemark.replace("caplimitPin", caplimitPin);
            }
            manifestData.put("manifestApiErrorRemark", remark == null ? finalManifestApiErrorRemark : remark);

            return manifestData;
        });
    }

    private void postProcess() {
        manifestData.remove();
        requestData.remove();
        UPL.remove();
    }
}
