package com.delhivery.Express.testModules.RegressionScripts;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.delhivery.Express.testModules.util.TestModuleHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;

public class Mapper1 extends BaseTest {
    ApiController apiCtrl = new ApiController();

    private final ThreadLocal<PackageDetail> pkgdetails = new ThreadLocal<>();
    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> requestPayload = new ThreadLocal<>();

    @DataProvider(name = "Pincode_Return_Different_type_flow_shipment", parallel = true)
    public Object[][] Pincode_Return_Different_type_flow_shipment() {
        return new Object[][]{
                //data set
                {"Scenario:: Shipment manifested with rpin", "B2C WITH CWH"},
                {"Scenario:: Shipment manifested COD shipment without rpin but with pickup location", "B2C WITH CWH"},
                {"Scenario:: Shipment manifested RVP shipment without rpin but with pickup location", "B2C WITH CWH"},
                {"Scenario:: Shipment manifested COD shipment without rpin and pickup location", "B2C"},
                {"Scenario:: Shipment manifested RVP shipment without rpin and pickup location", "B2C"},
        };
    }

    //FT 224074 cases
    @Test(dataProvider = "Pincode_Return_Different_type_flow_shipment", enabled = true)
    public void UpdateRcnForDifferentTypeFlowPkg(String Scenario, String type) {
        requestPayload.set(TestModuleHelper.prepareManifestData(null, null, null, "122003").get());

        waybills.set(diffTypeShipment.DifferentTypeShipments(type, requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());

        Utilities.hardWait(10);
        apiCtrl.centerUpdateRTApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");

        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("rcn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().rcn);
        postProcess();
    }

    @DataProvider(name = "Pincode_Return_Different_type_shipment_Manifestation", parallel = true)
    public Object[][] Pincode_Return_Different_type_shipment_Manifestation() {
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
    @Test(dataProvider = "Pincode_Return_Different_type_shipment_Manifestation", enabled = true)
    public void UpdateRcnForDifferentTypePkg(String Scenario, String type) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin(null, null, null, "122003", "test", "122005").get());
        waybills.set(diffTypeShipment.DifferentTypeShipments(type, requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());

        if (!type.contains("B2C")) {
            Utilities.hardWait(120);
        } else {
            Utilities.hardWait(5);
        }

        apiCtrl.centerUpdateRTApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");

        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("rcn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().rcn);
        postProcess();
    }

    @DataProvider(name = "Return_Different_Product_type_shipment_Manifestation", parallel = true)
    public Object[][] Pincode_Return_Different_Product_type_shipment_Manifestation() {
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
    @Test(dataProvider = "Return_Different_Product_type_shipment_Manifestation", enabled = true)
    public void UpdateRcnForDifferentPdtPkg(String Scenario, String productType) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin(null, productType, null,
                "122003", "test", "122005").get());
        ArrayList<String> heavyPdts = new ArrayList<String>(Arrays.asList("B2B", "Heavy", "Freight", "Flash_Heavy"));
        waybills.set(diffTypeShipment.DifferentTypeShipments("", requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());

        if (heavyPdts.contains(productType)) {
            Utilities.hardWait(80);
        } else {
            Utilities.hardWait(5);
        }

        apiCtrl.centerUpdateRTApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");

        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("rcn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().rcn);
        postProcess();
    }

    @DataProvider(name = "Return_Different_Payment_Mode_shipment_Manifestation", parallel = true)
    public Object[][] Pincode_Return_Different_Payment_Mode_shipment_Manifestation() {
        return new Object[][]{
                {"Scenario:: COD shipment", "COD"},
                {"Scenario:: Prepaid shipment", "Prepaid"},
                {"Scenario:: REPL shipment", "REPL"},
                {"Scenario:: RVP shipment", "Pickup"},
        };
    }

    //FT 224074 cases
    @Test(dataProvider = "Return_Different_Payment_Mode_shipment_Manifestation", enabled = true)
    public void UpdateRcnForDifferentPtPkg(String Scenario, String paymentMode) {
        requestPayload.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin(null, null, paymentMode,
                "122003", "test", "122005").get());

        waybills.set(diffTypeShipment.DifferentTypeShipments("B2C", requestPayload.get()));
        logInfo("Waybill generated " + waybills.get());
        Utilities.hardWait(5);

        apiCtrl.centerUpdateRTApi(waybills.get().get(0), "Mumbai MIDC (Maharashtra)");

        pkgdetails.set(apiCtrl.fetchPackageInfo(waybills.get().get(0), requestPayload.get()));
        assertKeyValue("rcn", "Mumbai MIDC (Maharashtra)", pkgdetails.get().rcn);
        postProcess();
    }

    private void postProcess() {
        requestPayload.remove();
        waybills.remove();
        pkgdetails.get();
    }
}
