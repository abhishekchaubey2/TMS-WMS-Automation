package com.delhivery.Express.testModules.PackageFlow.UnsetReturnDDId;

import com.delhivery.Express.controllers.api.DifferentStateShipmentListManifestation;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.UnsetReturnDispatchId.response.UnsetReturnDispatchIdResPayload;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class UnsetReturnDdIdForHeavy extends BaseTest {
    private final Integer packageCount;
    private final String factoryScenario;

    private final ThreadLocal<ArrayList<String>> waybillList = new ThreadLocal<>();
    private final ThreadLocal<HashMap<String, String>> manifestData = new ThreadLocal<>();
    private final ThreadLocal<UnsetReturnDispatchIdResPayload> unsetReturnDdIdRes = new ThreadLocal<>();
    private final ThreadLocal<PackageDetail> packageDetail = new ThreadLocal<>();
    UnsetReturnDdIdUtility unsetReturnDdIdUtility = new UnsetReturnDdIdUtility();
    DifferentStateShipmentListManifestation differentStateShipmentListManifestation = new DifferentStateShipmentListManifestation();

    public UnsetReturnDdIdForHeavy(String factoryScenario, Integer packageCount) {
        this.packageCount = packageCount;
        this.factoryScenario = factoryScenario;
    }

    @DataProvider(name = "Different_type_pkg_and_package_count", parallel = true)
    public static Object[][] Different_type_pkg() {
        return new Object[][]{
                {"Scenario::When product type is Heavy and package count 0", "Heavy", 0},
                {"Scenario::When product type is Heavy MPS and package count 4", "Heavy MPS", 4},
                {"Scenario::When product type is Heavy MPS and package count 10", "Heavy MPS", 10},
                {"Scenario::When product type is Heavy MPS and package count 11", "Heavy MPS", 11},
        };
    }

    @Factory(dataProvider = "Different_type_pkg_and_package_count")
    private static Object[] getDifferentTypePkgAndPackageCount(String scenario, String pdt, Integer packageCount) {
        return new Object[]{new UnsetReturnDdIdForHeavy(scenario, packageCount)};
    }

    @Test(dataProvider = "Different_state_type_pkg_for_dd_unset", dataProviderClass = manifestationData.class)
    private void testUnsetReturnDdIdForHeavy(String scenario, String state) {
        if (unsetReturnDdIdUtility.shouldProcess(scenario,factoryScenario)) {

            manifestData.set(TestModuleHelper.prepareManifestData(null, "Heavy", null, null).get());
            manifestData.get().put("rt_odx", "");

            if (packageCount > 1) {
                manifestData.get().put("package_count", packageCount.toString());
                manifestData.get().put("mps_amount", "10");
            }

            if (scenario.contains("REPL RETURNED")) {
                manifestData.get().put("payment_mode", "Prepaid");
            }

            waybillList.set(differentStateShipmentListManifestation.getDifferentStateShipmentAsList(state, manifestData.get()));
            unsetReturnDdIdUtility.postVerifyPackages(waybillList, manifestData);

            postProcess();
        }
    }

    private void postProcess() {
        waybillList.remove();
        manifestData.remove();
        unsetReturnDdIdRes.remove();
        packageDetail.remove();
    }
}