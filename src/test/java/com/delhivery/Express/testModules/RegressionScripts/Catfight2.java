package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;

import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.HashMap;
import java.util.List;

public class Catfight2 extends BaseTest {
    ApiController apiCtrl = new ApiController();
    RegressionTwoScriptHelper regressionTwoScriptHelper = new RegressionTwoScriptHelper();
    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();
    private final ThreadLocal<String> child_wbn = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> pkgDetails = new ThreadLocal<>();
    private final String payment_mode = "COD";
    private final String client = "client_99labels";
    private final String pin = "400059";

    @DataProvider(name = "Frgl Manifestation", parallel = true)
    public Object[][] Frgl_Manifestation() {
        return new Object[][]{
                {"Scenario:: pdt = B2B , mot = Express and prd = non- dangerous good", "B2B", "", "mobile cover", null},
                {"Scenario:: MPS pdt = B2B , mot = Express and prd = non- dangerous good", "B2B", "", "mobile cover", null},
                {"Scenario:: pdt = B2B , mot = Express and prd = dangerous good", "B2B", "", "Revolver", null},
                {"Scenario:: MPS pdt = B2B , mot = Express and prd = dangerous good", "B2B", "", "Revolver", null},
                {"Scenario:: pdt = B2B , mot = Express and prd = prohibited good", "B2B", "", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack", null},
                {"Scenario:: MPS pdt = B2B , mot = Express and prd = prohibited good", "B2B", "", "Wesol Hydrogen Peroxide 3 Food Grade 500 ML Pack", null},
                {"Scenario:: Pdt = B2B , pseg.frgl = true and flags.frgl = true initially", "B2B", "true", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", null},
                {"Scenario:: MPS Pdt = B2B , pseg.frgl = true and flags.frgl = true initially", "B2B", "true", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", null},
                {"Scenario:: Pdt = B2B , pseg.frgl = true and flags.frgl = false initially", "B2B", "false", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", null},
                {"Scenario:: MPS Pdt = B2B , pseg.frgl = true and flags.frgl = false initially", "B2B", "false", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", null},
                {"Scenario:: Pdt = B2C, pseg.frgl = true and flags.frgl = false initially", "B2C", "false", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", true},
                {"Scenario:: MPS Pdt = B2C, pseg.frgl = true and flags.frgl = false initially", "B2C", "false", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", true},
                {"Scenario:: Pdt = Heavy, pseg.frgl = true and flags.frgl = false initially", "Heavy", "false", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", true},
                {"Scenario:: MPS Pdt = Heavy, pseg.frgl = true and flags.frgl = false initially", "Heavy", "false", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", true},
                {"Scenario:: Pdt = B2C, pseg.frgl = false and flags.frgl = null initially", "B2C", "", "Revolver", null},
                {"Scenario:: MPS Pdt = B2C, pseg.frgl = false and flags.frgl = null initially", "B2C", "", "Revolver", null},
                {"Scenario:: Pdt = B2C, pseg.frgl = true and flags.frgl = null initially", "B2C", "", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", true},
                {"Scenario:: MPS Pdt = B2C, pseg.frgl = true and flags.frgl = null initially", "B2C", "", "Nuerma EI Science Rosemary Essential Oil 100 Pure Therapeutic Grade Rosemary Oil 15m", true},
        };
    }

    @Test(dataProvider = "Frgl Manifestation", enabled = true)
    public void Frgl_Manifestation(String scenario, String pdt, String frgl, String prd, Object expectedFrgl) {
        clData.set(TestModuleHelper.getClData(client).get());

        if (scenario.contains("MPS")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeProductDesEssentialGoodsAndFragileShp(client, pdt, payment_mode, pin,
                    scenario.contains("Express") ? "Express" : "Surface", prd, null, frgl).get());
        } else {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeProductDesEssentialGoodsAndFragileShp(client, pdt, payment_mode, pin,
                    scenario.contains("Express") ? "Express" : "Surface", prd, null, frgl).get());
        }

        if (scenario.contains("MPS")) {
            if (scenario.contains("B2B")) {
                waybills.set(diffTypeShipment.DifferentTypeShipments("B2B MPS", data.get()));
            } else if (scenario.contains("B2C")) {
                waybills.set(diffTypeShipment.DifferentTypeShipments("B2C MPS", data.get()));
            } else if (scenario.contains("Heavy")) {
                waybills.set(diffTypeShipment.DifferentTypeShipments("Heavy MPS", data.get()));
            }
        } else {
            waybills.set(apiCtrl.cmuManifestApi(data.get()));
        }

        logInfo("Waybill generated " + waybills.get());
        regressionTwoScriptHelper.applyFRGLAssertion(waybills, clData, scenario, pdt, expectedFrgl);
        postProcess();
    }


    @DataProvider(name = "Catfight MPS Manifestation", parallel = true)
    public Object[][] Catfight_MPS_Manifestation() {
        return new Object[][]{
                {"Scenario:: mot = Express, non dg good B2B", "B2B MPS", "mobile cover", "E", false},
                {"Scenario:: mot = Express, dg good B2B", "B2B MPS", "Revolver", "S", true},
                {"Scenario:: mot = Surface, dg good B2B", "B2B MPS", "Revolver", "S", true},
                {"Scenario:: mot = Express, non dg good B2C", "B2C MPS", "mobile cover", "E", false},
                {"Scenario:: mot = Express, dg good B2C", "B2C MPS", "Revolver", "S", true},
                {"Scenario:: mot = Surface, dg good B2C", "B2C MPS", "Revolver", "S", true},
                {"Scenario:: mot = Express, non dg good Heavy", "Heavy MPS", "mobile cover", "E", false},
                {"Scenario:: mot = Express, dg good Heavy", "Heavy MPS", "Revolver", "S", true},
                {"Scenario:: mot = Surface, dg good Heavy", "Heavy MPS", "Revolver", "S", true}
        };
    }

    @Test(dataProvider = "Catfight MPS Manifestation", enabled = true)
    public void Catfight_MPS_Manifestation(String scenario, String type, String prd, String expectedMot, Boolean expectedAr) {
        clData.set(TestModuleHelper.getClData(client).get());

        if (scenario.contains("Express")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes(client, null,
                    payment_mode, pin, "Express", prd).get());
        } else if (scenario.contains("Surface")) {
            data.set(TestModuleHelper.prepareManifestDataWithPinShippingModeAndProductDes(client, null,
                    payment_mode, pin, "Surface", prd).get());
        }
        waybills.set(diffTypeShipment.DifferentTypeShipments(type, data.get()));
        logInfo("Waybill generated " + waybills.get());
        Utilities.hardWait(60);

        logInfo("Master Waybill assertions " + waybills.get().get(0));
        regressionTwoScriptHelper.applyDGDgScoreMotOrARAssertion(waybills, clData, null, null, expectedMot, expectedAr);
        postProcess();
    }

    //Remove the thread local variables
    private void postProcess() {
        waybill.remove();
        waybills.remove();
        child_wbn.remove();
        clData.remove();
        data.remove();
        pkgDetails.remove();
    }
}
