package com.delhivery.Express.testModules.PackageFlow.UnsetReturnDDId;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class UnsetReturnDdIdUtility {
    private final static ThreadLocal<PackageDetail> packageDetail = new ThreadLocal<>();
    ApiController apiController = new ApiController();

    public void postVerifyPackages(ThreadLocal<ArrayList<String>> waybillList, ThreadLocal<HashMap<String, String>> manifestData) {
        Utilities.hardWait(5);
        if (waybillList.get().size() < 12) {
            waybillList.get().forEach(waybill -> {
                packageDetail.set(apiController.fetchPackageInfo(waybill, manifestData.get()));
                if (packageDetail.get().dd.getId() == null) {
                    Utilities.logInfo("Dispatch ID is null after unsetting dd id for waybill : " + waybill);
                    System.out.println("Dispatch ID is null after unsetting dd id for waybill : " + waybill);

                } else {
                    System.out.println("Dispatch id id is not null for waybill: " + waybill);
                    Utilities.logInfo("Dispatch id id is not null for waybill: " + waybill);
                    Assertions.assertKeyValue("Dispatch id is not null ", 1, 0);
                }
            });
        } else {
            packageDetail.set(apiController.fetchPackageInfo(waybillList.get().get(0), manifestData.get()));
            if (packageDetail.get().dd.getId() == null) {
                Utilities.logInfo("Dispatch ID is null after unsetting dd id for waybill : " + waybillList.get());
                System.out.println("Dispatch ID is null after unsetting dd id for waybill : " + waybillList.get());

            } else {
                System.out.println("Dispatch id is not id null for waybill: " + waybillList.get());
                Utilities.logInfo("Dispatch id is not null for waybill: " + waybillList.get());
                Assertions.assertKeyValue("Dispatch id is not null ", 1, 0);
            }
        }
        postProcess();
    }

    public boolean shouldProcess(String scenario, String factoryScenario) {
        return !scenario.contains("REPL") || !factoryScenario.contains("MPS");
    }

    private void postProcess() {
        packageDetail.remove();
    }
}
