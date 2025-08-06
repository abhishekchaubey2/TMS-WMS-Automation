package com.delhivery.Express.testModules.ReturnServiceabilityService.RtMapper;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.delhivery.core.utils.Utilities.logInfo;

public class RT_Mapper3 extends BaseTest {

    private String rasegCallbackAddress = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";

    ApiController apiCtrl = new ApiController();
    private static Map<String, Object> same_center_from_cwh_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");

    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestPayload = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgdetails = new ThreadLocal<>();
    private final ThreadLocal<LinkedHashMap<String, String>> expected_values = new ThreadLocal<>();


    @DataProvider(name = "RT_Mapper_weight_updated_shipment_recIDNull", parallel = true)
    public Object[][] RT_Mapper_weight_updated_shipment_recIDNull() {
        return new Object[][]{
//                {"Scenario:: B2C package with weight less than 15 kg", "B2C", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2C package with weight 15kg", "B2C", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2B package with weight less than 15 kg", "B2B", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2B package with weight 15kg", "B2B", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: Heavy package with weight less than 15 kg", "Heavy", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2C MPS package with weight less than 15 kg", "B2C MPS", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2C MPS package with weight 15kg", "B2C MPS", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package with weight less than 15 kg", "MPS WITH MCOUNT 1", "Suratgarh_MandiDPP_D (Rajasthan)", "IND462023AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package with weight less than 15 kg", "B2B MPS WITH INTERNAL CHILD", "Suratgarh_MandiDPP_D (Rajasthan)", "IND462023AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2B MPS with weight less than 15 kg", "B2B MPS", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: B2B MPS with weight 15kg", "B2B MPS", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: Heavy MPS with weight less than 15 kg", "Heavy MPS", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: NO DATA MPS shipment manifest with weight less than 15 kg", "NO DATA", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: NO DATA MPS shipment manifest with weight 15kg", "NO DATA", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
                {"Scenario:: Partial NO DATA MPS shipment manifest with weight less than 15 kg", "PARTIALLY MANIFESTED", "Surat_Brpc (Gujarat)", "IND395010AAB", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"},
//                {"Scenario:: Partial NO DATA MPS shipment manifest with weight 15kg", "PARTIALLY MANIFESTED", "Suratgarh_MandiDPP_D (Rajasthan)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper"}
        };
    }


    @Test(dataProvider = "RT_Mapper_weight_updated_shipment_recIDNull", enabled = true)
    public void verifyRCnForWeightUpdatedShipmentRecIdNUll(String Scenario, String type, String expectedRCn, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin(null, null, null, "122001", "test", "395006").get());

        waybills.set(diffTypeShipment.DifferentTypeShipments(type, requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());

        if (!type.contains("B2C")) {
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        // Performing FM of the package
        requestPayload.get().put("clientWarehouse", "");
        apiCtrl.fmOMSApi(waybills.get().get(0), "FMPICK", same_center_from_cwh_center.get("SortCode").toString(), requestPayload.get());
        Utilities.hardWait(2);
        apiCtrl.giApi(waybills.get().get(0), same_center_from_cwh_center.get("Name").toString(), requestPayload.get());
        Utilities.hardWait(2);

        // Performing FM of child package if present
        if (waybills.get().size() > 1) {
            apiCtrl.fmOMSApi(waybills.get().get(1), "FMPICK", same_center_from_cwh_center.get("SortCode").toString(), requestPayload.get());
            Utilities.hardWait(5);
            apiCtrl.giApi(waybills.get().get(1), same_center_from_cwh_center.get("Name").toString(), requestPayload.get());
            Utilities.hardWait(2);
        }

        // Update weight -> for master only
        if (Scenario.contains("weight less than 15 kg")) {
            apiCtrl.QcWtApi(waybills.get().get(0), 14999.99, "sorter", requestPayload.get());
            Utilities.hardWait(10);
        } else if (Scenario.contains("weight 15kg")) {
            apiCtrl.QcWtApi(waybills.get().get(0), 15000.00, "sorter", requestPayload.get());
            Utilities.hardWait(70);
        }

        Utilities.hardWait(10);
        apiCtrl.ApplyNsl(waybills.get(), "RT", "RT-101", requestPayload.get());

        Utilities.hardWait(10);
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        logInfo("After marking shipment RT; rcn value :: " + pkgdetails.get().rcn);
        logInfo("After marking shipment RT; rcns value :: " + pkgdetails.get().rcns);

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
