package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.delhivery.core.utils.Assertions.assertKeyValue;

public class RegressionTwoScriptHelper {
    private final ThreadLocal<PackageDetail> pkgDetails = new ThreadLocal<>();
    ApiController apiController = new ApiController();

    public void applyEssentialAndMotAssertion(ThreadLocal<String> waybill, ThreadLocal<HashMap<String, String>> clData, Boolean expectedEnstl, String expectedMot) {
        pkgDetails.set(apiController.fetchPackageInfo(waybill.get(), clData.get()));
        Optional.ofNullable(expectedEnstl).ifPresent(v -> assertKeyValue("esntl", v, pkgDetails.get().flags.esntl));
        Optional.ofNullable(expectedMot).ifPresent(v -> assertKeyValue("mot", v, pkgDetails.get().mot));
        postProcess();
    }

    public void applyFRGLAssertion(ThreadLocal<List<String>> waybills, ThreadLocal<HashMap<String, String>> clData, String scenario, String pdt, Object expectedFrgl) {
        Utilities.hardWait(90);
        waybills.get().forEach(waybill -> {
            pkgDetails.set(apiController.fetchPackageInfo(waybill, clData.get()));
            if (pdt.contains("B2B") || scenario.contains("pseg.frgl = false")) {
                Assertions.assertIfNull("frgl", pkgDetails.get().flags.frgl);
            } else {
                Optional.ofNullable(expectedFrgl).ifPresent(v -> assertKeyValue("frgl", expectedFrgl.toString(), pkgDetails.get().flags.frgl.toString()));
            }
        });
        postProcess();
    }

    public void applyDGDgScoreMotOrARAssertion(ThreadLocal<List<String>> waybills, ThreadLocal<HashMap<String, String>> clData, Boolean expectedDg,
                                               Integer expectedDgScore, String expectedMot, Boolean expectedAr) {
        waybills.get().forEach(waybill -> {
            pkgDetails.set(apiController.fetchPackageInfo(waybill, clData.get()));
            Optional.ofNullable(expectedDg).ifPresent(v -> assertKeyValue("dg", v, pkgDetails.get().pseg.dg));
            Optional.ofNullable(expectedDgScore).ifPresent(v -> assertKeyValue("dg_score", v, pkgDetails.get().pseg.dgScore));
            Optional.ofNullable(expectedMot).ifPresent(v -> assertKeyValue("mot", v, pkgDetails.get().mot));
            Optional.ofNullable(expectedAr).ifPresent(v -> assertKeyValue("ar", v, pkgDetails.get().ar));
        });

    }

    private void postProcess() {
        pkgDetails.remove();
    }
}
