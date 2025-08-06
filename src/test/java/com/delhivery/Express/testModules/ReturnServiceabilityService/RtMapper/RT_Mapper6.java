package com.delhivery.Express.testModules.ReturnServiceabilityService.RtMapper;

import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.delhivery.Express.testModules.util.TestModuleHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;

public class RT_Mapper6 extends BaseTest {
    private String rasegCallbackAddress = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";
    ApiController apiCtrl = new ApiController();

    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestPayload = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgdetails = new ThreadLocal<>();
    private final ThreadLocal<LinkedHashMap<String, String>> expected_values = new ThreadLocal<>();
    private final ThreadLocal<String> dispatchId = new ThreadLocal<>();

    @DataProvider(name = "RT_Mapper_rvp_cases_weight_updated_recIDNull", parallel = true)
    public Object[][] RT_Mapper_rvp_cases_weight_updated_recIDNull() {
        return new Object[][]{
                {"Scenario:: B2C package with weight less than 15 kg", "B2C", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2C package with weight 15kg", "B2C", "Bhopal_Kasturba_M (Madhya Pradesh)", "IND462023AAB", "null", "null", "Bhopal", "null"},
                {"Scenario:: B2B package with weight less than 15 kg", "B2B", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2B package with weight 15kg", "B2B", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2C MPS package with weight less than 15 kg", "B2C MPS", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2C MPS package with weight 15kg", "B2C MPS", "Bhopal_Kasturba_M (Madhya Pradesh)", "IND462023AAB", "null", "null", "Bhopal", "null"},
                {"Scenario:: B2B MPS with weight less than 15 kg", "B2B MPS", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2B MPS with weight 15kg", "B2B MPS", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
        };
    }


    @Test(dataProvider = "RT_Mapper_rvp_cases_weight_updated_recIDNull", enabled = true)
    public void verifyRCnForRVPWeightUpdatedRecIdNUll(String Scenario, String type, String expectedRCn, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin(null, null, "Pickup", "400059", "test", "395006").get());
        if (!Scenario.contains("NO DATA")) {
            requestPayload.get().put("client", "flipkart");
        }
        waybills.set(diffTypeShipment.DifferentTypeShipments(type, requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());

        if (!type.contains("B2C")) {
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        apiCtrl.ApplyNsl(waybills.get(), "PP", "X-ASP", requestPayload.get());

        // Update weight -> for master only
        if (Scenario.contains("weight less than 15 kg")) {
            apiCtrl.QcWtApi(waybills.get().get(0), 14999.99, "sorter", requestPayload.get());
            Utilities.hardWait(10);
        } else if (Scenario.contains("weight 15kg")) {
            apiCtrl.QcWtApi(waybills.get().get(0), 15000.00, "sorter", requestPayload.get());
            Utilities.hardWait(70);
        }

        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        logInfo("In PP state; rcn value :: " + pkgdetails.get().rcn);
        logInfo("In PP state; rcns value :: " + pkgdetails.get().rcns);
        requestPayload.get().put("shipment_destination_center", pkgdetails.get().cn);

        Utilities.hardWait(2);
        dispatchId.set(apiCtrl.markRerveseShipmentDispatchApi(waybills.get(), requestPayload.get()));
        Utilities.hardWait(10);

        apiCtrl.lmUpdateHQShipmentApi(waybills.get().get(0), "PickedUp", requestPayload.get());
        Utilities.hardWait(2);
        apiCtrl.unsetShipmentDispatchIdApi(waybills.get().get(0), dispatchId.get(), requestPayload.get());
        if (waybills.get().size() > 1) {
            apiCtrl.lmUpdateHQShipmentApi(waybills.get().get(1), "PickedUp", requestPayload.get());
            Utilities.hardWait(2);
            apiCtrl.unsetShipmentDispatchIdApi(waybills.get().get(1), dispatchId.get(), requestPayload.get());
        }

        //waiting period for return pincode update
        Utilities.hardWait(returnAddfixCallbackDelayTime);
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        Assertions.assertIfNotNull("raseg", pkgdetails.get().raseg.ud);


        expected_values.set(TestModuleHelper.fillExpectedValuesForRCNIdRpcIdRctyRcnsNslStSr(expectedRCn, expectedRCnid, expectedRDpc, expectedRDpcid, expectedRCty,
                expectedRCns, null, null, null).get());

        Utilities.hardWait(keyassertPkgInfoFetchDelayTime);

        logInfo("Checking package keys :: " + waybills.get().get(0));
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails.get(), expected_values.get());

        if (waybills.get().size() > 1) {
            logInfo("Checking child package keys :: " + waybills.get().get(1));
            pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(1), requestPayload.get()));
            ServiceabilityKeysAssertions.assertServiceabilityKeys(pkgdetails.get(), expected_values.get());
        }
        postProcess();
    }

    private void postProcess() {
        waybills.remove();
        requestPayload.remove();
        pkgdetails.remove();
        expected_values.remove();
    }
}
