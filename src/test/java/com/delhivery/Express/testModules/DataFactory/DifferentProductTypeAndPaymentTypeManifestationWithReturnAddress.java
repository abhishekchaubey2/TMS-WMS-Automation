package com.delhivery.Express.testModules.DataFactory;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.CoreConstants;
import com.delhivery.core.utils.CsvFileUtils;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DifferentProductTypeAndPaymentTypeManifestationWithReturnAddress extends BaseTest {
    private final Map<String, ArrayList<String>> csvData = new HashMap<>();
    private final ThreadLocal<ArrayList<String>> waybillList = new ThreadLocal<>();

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class)
    private void testDifferentProductTypeAndPaymentTypeManifestationWithReturnAddress(String scenario, String productType, String paymentType) {
        Utilities.NUM_THREADS = manifestationData.Different_pdt_payment_types().length;
        System.out.println("Length "+Utilities.NUM_THREADS);
        ApiController apiController = new ApiController();
        ThreadLocal<HashMap<String, String>> data = TestModuleHelper.prepareManifestDataWithPinAddReturnAddAndReturnPin(null, productType, paymentType, "122003", "testingDummyData", "test", "122005");
        waybillList.set(new ArrayList<>(Collections.singletonList(productType+" "+paymentType)));
        waybillList.get().addAll(apiController.cmuManifestApi(data.get()));
        csvData.put(productType+" "+paymentType, waybillList.get());
        Utilities.executeInThreadAndWaitForCompletion();
        System.out.println("CSV Data "+csvData);
    }

    @Test(dependsOnMethods = "testDifferentProductTypeAndPaymentTypeManifestationWithReturnAddress")
    private void writeDataToCSV() {
        csvData.put("Headers", new ArrayList<>(Arrays.asList("Package Type and Payment Type", "Master Waybill", "Child Waybill")));
        CsvFileUtils.writeDataToCSV(CoreConstants.REG_CHECKLIST_PATH, "testDifferentProductTypeAndPaymentTypeManifestationWithReturnAddress.csv", csvData);
    }
}
