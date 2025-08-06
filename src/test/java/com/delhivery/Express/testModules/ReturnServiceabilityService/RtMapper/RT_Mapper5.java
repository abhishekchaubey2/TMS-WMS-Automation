package com.delhivery.Express.testModules.ReturnServiceabilityService.RtMapper;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.delhivery.Express.testModules.util.TestModuleHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RT_Mapper5 extends BaseTest {
    private String rasegCallbackAddress = "186, BALIYAWAS MANDIR, behind golden Tulip hotel, Baliawas";

    ApiController apiCtrl = new ApiController();

    private static final Map<String, Object> same_center_from_cwh_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");

    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestPayload = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgdetails = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgdetails1 = new ThreadLocal<>();
    private final ThreadLocal<LinkedHashMap<String, String>> expected_values = new ThreadLocal<>();

    @DataProvider(name = "RT_Mapper_return_address_update", parallel = true)
    public Object[][] RT_Mapper_return_address_update() {
        return new Object[][]{
                {"Scenario:: B2C package with weight less than 15 kg", "B2C", "Delhi_Skynet_INT (Delhi)", "IND110037AAH", "null", "null", "Delhi", "rt_polymapper", "Delhi_FedExVRTL_D (Haryana)"},
                {"Scenario:: B2B package with weight less than 15 kg", "B2B", "Delhi_Skynet_INT (Delhi)", "IND110037AAH", "null", "null", "Delhi", "rt_polymapper", "Delhi_FedExVRTL_D (Haryana)"},
                {"Scenario:: B2C MPS package with weight less than 15 kg", "B2C MPS", "Delhi_Skynet_INT (Delhi)", "IND110037AAH", "null", "null", "Delhi", "rt_polymapper", "Delhi_FedExVRTL_D (Haryana)"},
                {"Scenario:: B2B MPS with weight less than 15 kg", "B2B MPS", "Delhi_NorthHLD_D (Delhi)", "IND335804AAA", "Goa_Hub (Goa)", "IND403726AAA", "Surat", "rt_polymapper", "Guwahati_Kaikchi_GW (Assam)"},
        };
    }

    @Test(dataProvider = "RT_Mapper_return_address_update", enabled = true)
    public void verifyRCnForRtAddressUpdate(String Scenario, String type, String expectedRCn, String expectedRCnid, String expectedRDpc, String expectedRDpcid, String expectedRCty, String expectedRCns, String expectedFirstRCn) throws JsonProcessingException {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin("flipkart", null, null, "122001", "FUTURE ENERGY II 2C/19, Second Floor, New Rohtak Road Delhi", "110015").get());

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

        //waiting period for return pincode update
        Utilities.hardWait(returnAddfixCallbackDelayTime);
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        Assertions.assertIfNotNull("raseg", pkgdetails.get().raseg.ud);

        Utilities.hardWait(30);
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("rcn", expectedFirstRCn, pkgdetails.get().rcn);
        logInfo("In RT state before weight update; rcn value :: " + pkgdetails.get().rcn);
        logInfo("In RT state before weight update; rcns value :: " + pkgdetails.get().rcns);
        logInfo("In RT state before weight update; radd value :: " + pkgdetails.get().radd);
        logInfo("In RT state before weight update; rpin value :: " + pkgdetails.get().rpin);
        logInfo("In RT state before weight update; raseg value :: " + Utilities.jsonObjectToString(pkgdetails.get().raseg));

        //editing radd such that raseg callback would not change the rpin to a new rpin
        HashMap<String, String> editRadd = new HashMap<>();
        editRadd.put("client", "flipkart");
        editRadd.put("return_add", "4612-15/G3 shahtara street Aj,meri Gate,Central Delhi");
        apiCtrl.EditApi(waybills.get().get(0), editRadd);

        //waiting period for return pincode update
        Utilities.hardWait(returnAddfixCallbackDelayTime);
        pkgdetails1.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        Assertions.assertIfUpdated("raseg", pkgdetails.get().raseg.ud, pkgdetails1.get().raseg.ud);

        Utilities.hardWait(30);

        // Making a hashmap of Expected values to send in the assertion fnc
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
