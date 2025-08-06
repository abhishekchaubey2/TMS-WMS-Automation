package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Mapper extends BaseTest {
    ApiController apiCtrl = new ApiController();
    private final ThreadLocal<PackageDetail> pkgdetails = new ThreadLocal<>();
    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestPayload = new ThreadLocal<>();
    private final String pin = "122001";
    private final String add = "sector-99";


    @DataProvider(name = "Different_type_shipment_Manifestation", parallel = true)
    public Object[][] Pdt_Forward_Different_type_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C"},
                {"Scenario:: B2B package", "B2B"},
                {"Scenario:: Heavy package", "Heavy"},
                {"Scenario:: B2C MPS", "B2C MPS"},
                {"Scenario:: B2B MPS WITH ONLY MASTER package", "MPS WITH MCOUNT 1"},
                {"Scenario:: B2B MPS WITH INTERNAL CHILD package", "B2B MPS WITH INTERNAL CHILD"},
                {"Scenario:: B2B MPS", "B2B MPS"},
                {"Scenario:: Heavy MPS", "Heavy MPS"},
                {"Scenario:: NO DATA shipment", "NO DATA"},
                {"Scenario:: Partial manifest shipment", "PARTIALLY MANIFESTED"}
        };
    }

    //FT 224074 cases
    @Test(dataProvider = "Different_type_shipment_Manifestation", enabled = true)
    public void UpdateCnForDifferentTypePkg(String Scenario, String type) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinAndAdd(null, null, null, pin, add).get());
        waybills.set(diffTypeShipment.DifferentTypeShipments(type, requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());

        if (!type.contains("B2C")) {
            Utilities.hardWait(120);
        } else {
            Utilities.hardWait(5);
        }

        apiCtrl.centerUpdateApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("cn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().cn);
        postProcess();
    }


    @DataProvider(name = "Different_Payment_Mode_shipment_Manifestation", parallel = true)
    public Object[][] Pdt_Forward_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD"},
                {"Scenario:: Prepaid shipment", "Prepaid"},
                {"Scenario:: REPL shipment", "REPL"},
                {"Scenario:: RVP shipment", "Pickup"},
        };
    }

    //FT 224074 cases
    @Test(dataProvider = "Different_Payment_Mode_shipment_Manifestation", enabled = true)
    public void UpdateCnForDifferentPTPkg(String Scenario, String paymentMode) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinAndAdd(null, null, paymentMode, pin, add).get());

        waybills.set(diffTypeShipment.DifferentTypeShipments("B2C", requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());
        Utilities.hardWait(5);
        apiCtrl.centerUpdateApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");

        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("cn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().cn);
        postProcess();
    }


    @DataProvider(name = "Different_Product_type_shipment_Manifestation", parallel = true)
    public Object[][] Pdt_Forward_Different_Product_type_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: B2C package", "B2C"},
                {"Scenario:: B2B package", "B2B"},
                {"Scenario:: Freight package", "Freight"},
                {"Scenario:: Heavy package", "Heavy"},
                {"Scenario:: NEXT_B2C_SURFACE package", "NEXT_B2C_SURFACE"},
                {"Scenario:: FLASH_B2C_SURFACE package", "FLASH_B2C_SURFACE"},
                {"Scenario:: FLASH_B2C_AIR package", "FLASH_B2C_AIR"},
                {"Scenario:: HLD package", "HLD"},
                {"Scenario:: DOC package", "DOC"},
                {"Scenario:: DOC_FLASH package", "DOC_FLASH"},
                {"Scenario:: KYC package", "KYC"},
                {"Scenario:: Flash_Heavy package", "Flash_Heavy"}
        };
    }

    //FT 224074 cases
    @Test(dataProvider = "Different_Product_type_shipment_Manifestation", enabled = true)
    public void UpdateCnForDifferentPDTPkg(String Scenario, String productType) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinAndAdd(null, productType, null, pin, add).get());
        ArrayList<String> heavyPdts = new ArrayList<>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));
        waybills.set(diffTypeShipment.DifferentTypeShipments("", requestPayload.get()));
        logInfo("Waybills generated " + waybills.get());

        if (heavyPdts.contains(productType)) {
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        apiCtrl.centerUpdateApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");
        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("cn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().cn);
        postProcess();
    }

    private void postProcess() {
        requestPayload.remove();
        waybills.remove();
        pkgdetails.get();
    }
}
