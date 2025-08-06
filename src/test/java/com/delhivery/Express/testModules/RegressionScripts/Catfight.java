package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class contains test cases for the Catfight module.
 * The test cases are related to the manifestation of packages.
 * ThreadLocal is used to make the data thread safe.
 * postProcess() method is used to remove the thread local variables.
 */

public class Catfight extends BaseTest {
    ApiController apiCtrl = new ApiController();
    RegressionTwoScriptHelper regressionTwoScriptHelper = new RegressionTwoScriptHelper();

    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<ArrayList<String>> cmuWaybills = new ThreadLocal<>();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();
    private final ThreadLocal<String> child_wbn = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgDetails = new ThreadLocal<>();
    private final ThreadLocal<String> upl = new ThreadLocal<>();

    private final String product_type, payment_mode, pin;

    public Catfight() {
        product_type = "B2C";
        payment_mode = "COD";
        pin = "400059";
    }

    @DataProvider(name = "Different Manifestation Endpoints", parallel = true)
    public Object[][] Different_Manifestation_Catfight() {
        return new Object[][]{
                {"Scenario:: Manifestation through create.json", false, 0, "S"},
                {"Scenario:: Manifestation through push.json", false, 0, "S"},
                {"Scenario:: Manifestation through v2/manifest", false, 0, "S"}
        };
    }

    @Test(dataProvider = "Different Manifestation Endpoints", enabled = true)
    public void Catfight_Manifestation(String scenario, Boolean expectedDg, Integer expectedDgScore, String expectedMot) {
        clData.set(TestModuleHelper.getClData("regression_client").get());
        data.set(TestModuleHelper.prepareManifestData("regression_client", product_type, payment_mode, pin).get());
        if (scenario.contains("create.json")) {
            waybills.set(apiCtrl.cmuManifestApi(data.get()));
            waybill.set(waybills.get().get(0));
        } else if (scenario.contains("push.json")) {
            waybills.set(apiCtrl.verifyCmuPush(data.get()));
            waybill.set(waybills.get().get(0));
        } else if (scenario.contains("v2/manifest")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinReturnAddAndReturnPin(null, null, null, pin, "test", "110001").get());
            String token = apiCtrl.fetchUserJwtTokenApi(null);
            upl.set(apiCtrl.createNoDataUplApi(token));
            Utilities.hardWait(30);
            waybills.set((ArrayList<String>) apiCtrl.getNoDataUplShipment(upl.get(),token));
            try {
                apiCtrl.cmuV2ManifestNoDataShipment(waybills.get(), data.get(), token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            waybill.set(waybills.get().get(0));
        }
        logInfo("Waybill generated " + waybill.get());
        Utilities.hardWait(60);

        pkgDetails.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("dg", expectedDg, pkgDetails.get().pseg.dg);
        assertKeyValue("dg_score", expectedDgScore, pkgDetails.get().pseg.dgScore);
        assertKeyValue("mot", expectedMot, pkgDetails.get().mot);
        postProcess();
    }

    @DataProvider(name = "Non Dangerous Manifestation", parallel = true)
    public Object[][] Non_Dangerous_Manifestation() {
        return new Object[][]{
                {"Scenario:: Manifesting a package with shipping mode = Surface", false, 0, "S"},
                {"Scenario:: Manifesting a package with shipping mode = Express", false, 0, "E"}
        };
    }

    @Test(dataProvider = "Non Dangerous Manifestation", enabled = true)
    public void Non_Dangerous_Manifestation(String scenario, Boolean expectedDg, Integer expectedDgScore, String expectedMot) {
        clData.set(TestModuleHelper.getClData("client_99labels").get());
        if (scenario.contains("Surface")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes("client_99labels", product_type, payment_mode, pin, "Surface", null).get());
        } else {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes("client_99labels", product_type, payment_mode, pin, "Express", null).get());
        }

        waybills.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());
        Utilities.hardWait(60);

        pkgDetails.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("dg", expectedDg, pkgDetails.get().pseg.dg);
        assertKeyValue("dg_score", expectedDgScore, pkgDetails.get().pseg.dgScore);
        assertKeyValue("mot", expectedMot, pkgDetails.get().mot);
        postProcess();
    }

    @DataProvider(name = "Dangerous Manifestation", parallel = true)
    public Object[][] Dangerous_Manifestation() {
        return new Object[][]{
                {"Scenario:: Manifesting a package with shipping mode = Surface", true, 1, "S", true},
                {"Scenario:: Manifesting a package with shipping mode = Express", true, 1, "S", true}
        };
    }

    @Test(dataProvider = "Dangerous Manifestation", enabled = true)
    public void Dangerous_Manifestation(String scenario, Boolean expectedDg, Integer expectedDgScore, String expectedMot, Boolean expectedAr) {
        clData.set(TestModuleHelper.getClData("client_99labels").get());

        if (scenario.contains("Surface")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes("client_99labels", product_type, payment_mode, pin, "Surface", "Revolver").get());
        } else if (scenario.contains("Express")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes("client_99labels", product_type, payment_mode, pin, "Express", "Revolver").get());
        }
        waybills.set(apiCtrl.cmuManifestApi(data.get()));
        logInfo("Waybill generated " + waybills.get());
        Utilities.hardWait(120);
        regressionTwoScriptHelper.applyDGDgScoreMotOrARAssertion(waybills, clData, expectedDg, expectedDgScore, expectedMot, expectedAr);
        postProcess();

    }

    @DataProvider(name = "Prohibited Manifestation", parallel = true)
    public Object[][] Prohibited_Manifestation() {
        return new Object[][]{
                {"Scenario:: Manifesting a package with shipping mode = Surface", true, 1, "S", true, "NSZ", null, null},
                {"Scenario:: Manifesting a package with shipping mode = Express", true, 1, "S", true, "NSZ", null, null}
        };
    }

    @Test(dataProvider = "Prohibited Manifestation", enabled = true)
    public void Prohibited_Manifestation(String scenario, Boolean expectedDg, Integer expectedDgScore, String expectedMot, Boolean expectedAr, String expectedCn
            , Object expectedCnc, Object expectedDpc) {
        clData.set(TestModuleHelper.getClData("client_99labels").get());

        if (scenario.contains("Surface")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes("client_99labels", product_type, payment_mode, pin, "Surface", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack").get());

        } else if (scenario.contains("Express")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes("client_99labels", product_type, payment_mode, pin, "Express", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack").get());
        }

        waybills.set(apiCtrl.cmuManifestApi(data.get()));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybills.get());
        Utilities.hardWait(60);

        pkgDetails.set(apiCtrl.fetchPackageInfo(waybill.get(), clData.get()));
        assertKeyValue("dg", expectedDg, pkgDetails.get().pseg.dg);
        assertKeyValue("dg_score", expectedDgScore, pkgDetails.get().pseg.dgScore);
        assertKeyValue("mot", expectedMot, pkgDetails.get().mot);
        assertKeyValue("ar", expectedAr, pkgDetails.get().ar);
        assertKeyValue("cn", expectedCn, pkgDetails.get().cn);
        Assertions.assertIfNull("dpc", pkgDetails.get().dpc);
        Assertions.assertIfNull("cnc", pkgDetails.get().cnc);
        postProcess();
    }

    @DataProvider(name = "Esntl Manifestation", parallel = true)
    public Object[][] Esntl_Manifestation() {
        return new Object[][]{
                {"Scenario:: Manfesting a package for which flags.censtl = YES", "YES", true, "S", null, "mobile cover"},
                {"Scenario:: Manfesting a MPS package for which flags.censtl = YES", "YES", true, "S", null, "mobile cover"},
                {"Scenario:: Manfesting a package for which flags.censtl = YES , dg = true , mot is sent as Express", "YES", true, "S", true, "revolver"},
                {"Scenario:: Manfesting a MPS package for which flags.censtl = YES , dg = true , mot is sent as Express", "YES", true, "S", true, "revolver"},
                {"Scenario:: Manfesting a package for which flags.censtl = No", "NO", false, "S", false, "mobile cover"},
                {"Scenario:: Manfesting a MPS package for which flags.censtl = No, dg = true , mot is sent as Express", "NO", false, "S", false, "revolver"},
                {"Scenario:: Manfesting a package for which flags.censtl = No, dg = true , mot is sent as Express", "NO", false, "S", false, "revolver"}
        };
    }

    @Test(dataProvider = "Esntl Manifestation", enabled = true)
    public void Esntl_Manifestation(String scenario, String essential_good, Boolean expectedEnstl, String expectedMot, Object expectedAr, String prd) {
        clData.set(TestModuleHelper.getClData("client_99labels").get());

        if (scenario.contains("MPS")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeProductDesAndEssentialGoods("client_99labels", product_type, payment_mode, pin,
                    scenario.contains("Express") ? "Express" : "Surface",
                    prd, essential_good).get());
        } else {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeProductDesAndEssentialGoods("client_99labels", product_type, payment_mode, pin, "Surface",
                    prd, essential_good).get());
        }

        if (scenario.contains("MPS")) {
            waybills.set(diffTypeShipment.DifferentTypeShipments("B2C MPS", data.get()));
            waybill.set(waybills.get().get(0));
            child_wbn.set(waybills.get().get(1));
            logInfo("Waybill generated " + waybills.get());
            Utilities.hardWait(60);

            logInfo("Master waybill assertions " + waybill.get());
            regressionTwoScriptHelper.applyEssentialAndMotAssertion(waybill, clData, expectedEnstl, expectedMot);
            logInfo("Child waybill assertions " + child_wbn.get());
            regressionTwoScriptHelper.applyEssentialAndMotAssertion(waybill, clData, expectedEnstl, expectedMot);
        } else {
            waybills.set(apiCtrl.cmuManifestApi(data.get()));
            waybill.set(waybills.get().get(0));
            logInfo("Waybill generated " + waybill.get());
            Utilities.hardWait(60);
            regressionTwoScriptHelper.applyEssentialAndMotAssertion(waybill, clData, expectedEnstl, expectedMot);
        }
        postProcess();
    }


    @DataProvider(name = "Client esntl Manifestation", parallel = true)
    public Object[][] Client_Esntl_Manifestation() {
        return new Object[][]{
                {"Scenario:: Manfesting a package for which flags.censtl = No and client.enstl = True", "NO", false, "mobile cover"},
                {"Scenario:: Manfesting a MPS package for which flags.censtl = No and client.enstl = True", "NO", false, "mobile cover"},
                {"Scenario:: Manfesting a package for which flags.censtl = null and client.enstl = True", "", true, "mobile cover"},
                {"Scenario:: Manfesting a MPS package for which flags.censtl = null and client.enstl = True", "", true, "mobile cover"},
                //{"Scenario:: Manfesting a package for which flags.censtl = YES & client.esntl = false and pseg.cat == uncategorized", "YES", true, "JTTESTOR Soft toy"}
                {"Scenario:: Manfesting a package for which flags.censtl = null & client.esntl = True and pseg.cat == uncategorized", "", true, "JTTESTOR Soft toy"}
                //{"Scenario:: Manfesting a package for which flags.censtl = null & client.esntl = false and pseg.cat == uncategorized", "", false, "JTTESTOR Soft toy"},
        };
    }

    @Test(dataProvider = "Client esntl Manifestation", enabled = true)
    public void Client_Esntl_Manifestation(String scenario, String essential_good, Boolean expectedEnstl, String prd) {
        clData.set(TestModuleHelper.getClData("client_99labels").get());

        if (scenario.contains("MPS")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeProductDesAndEssentialGoods("client_99labels", product_type, payment_mode, pin,
                    scenario.contains("Express") ? "Express" : "Surface",
                    prd, essential_good).get());
        } else {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeProductDesAndEssentialGoods("client_99labels", product_type, payment_mode, pin, "Surface",
                    prd, essential_good).get());
        }

        if (scenario.contains("MPS")) {
            logInfo("Thread id  for if manifestation " + Thread.currentThread().getId());
            waybills.set(diffTypeShipment.DifferentTypeShipments("B2C MPS", data.get()));
            waybill.set(waybills.get().get(0));
            child_wbn.set(waybills.get().get(1));
            logInfo("Waybill generated " + waybills.get());
            Utilities.hardWait(60);

            logInfo("Master waybill assertions " + waybill.get());
            regressionTwoScriptHelper.applyEssentialAndMotAssertion(waybill, clData, expectedEnstl, null);
            logInfo("Child waybill assertions " + child_wbn.get());
            regressionTwoScriptHelper.applyEssentialAndMotAssertion(waybill, clData, expectedEnstl, null);
        } else {
            logInfo("Thread id  in else case for manifestation " + Thread.currentThread().getId());
            waybills.set(apiCtrl.cmuManifestApi(data.get()));
            waybill.set(waybills.get().get(0));
            logInfo("Waybill generated " + waybill.get());
            Utilities.hardWait(60);
            regressionTwoScriptHelper.applyEssentialAndMotAssertion(waybill, clData, expectedEnstl, null);
        }
        postProcess();
    }

    private void postProcess() {
        upl.remove();
        waybill.remove();
        waybills.remove();
        cmuWaybills.remove();
        child_wbn.remove();
        clData.remove();
        data.remove();
        pkgDetails.remove();
    }
}



