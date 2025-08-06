package com.delhivery.Express.testModules.DataFactory;

import com.delhivery.Express.controllers.api.DifferentStateShipmentListManifestation;
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

public class DifferentStateTypeManifestation extends BaseTest {
    private final Map<String, ArrayList<String>> csvData = new HashMap<>();
    private final ThreadLocal<ArrayList<String>> waybillList = new ThreadLocal<>();

    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class)
    private void testDifferentProductTypeAndPaymentTypeManifestation(String scenario, String productType, String state) {
        Utilities.NUM_THREADS = manifestationData.Different_state_type().length;
        System.out.println("Length " + Utilities.NUM_THREADS);
        DifferentStateShipmentListManifestation differentStateShipments = new DifferentStateShipmentListManifestation();
        ThreadLocal<HashMap<String, String>> data = TestModuleHelper.prepareManifestData(null, productType, null, null);
        waybillList.set(new ArrayList<>(Collections.singletonList(productType + " " + state)));
        waybillList.get().addAll(differentStateShipments.getDifferentStateShipmentAsList(state, data.get()));
        csvData.put(productType + " " + state, waybillList.get());
        Utilities.executeInThreadAndWaitForCompletion();
        System.out.println("CSV Data " + csvData);
    }

    @Test(dependsOnMethods = "testDifferentProductTypeAndPaymentTypeManifestation")
    private void writeDataToCSV() {
        csvData.put("Headers", new ArrayList<>(Arrays.asList("Package Type and State Type", "Master Waybill", "Child Waybill")));
        CsvFileUtils.writeDataToCSV(CoreConstants.REG_CHECKLIST_PATH, "DifferentProductTypeAndPaymentTypeManifestation.csv", csvData);
    }
}
