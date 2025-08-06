package com.delhivery.Express.testModules.DataFactory;

import com.delhivery.Express.controllers.api.DifferentTypeShipments;
import com.delhivery.Express.dataprovider.manifestationData;
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

public class DifferentTypeShipmentManifestation extends BaseTest {
    private final ThreadLocal<ArrayList<String>> waybillList = new ThreadLocal<>();
    private final Map<String,ArrayList<String>> csvData=new HashMap<>();

    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class)
    private void testDifferentTypeShipmentManifestation(String scenario, String type) {
        Utilities.NUM_THREADS = manifestationData.Different_type_pkg().length;
        DifferentTypeShipments differentTypeShipments = new DifferentTypeShipments();
        HashMap<String, String> data = new HashMap<>();
        waybillList.set(new ArrayList<>(Collections.singletonList(type)));
        waybillList.get().addAll(differentTypeShipments.DifferentTypeShipments(type, data));
        System.out.println("Waybill List: " + waybillList.get());
        csvData.put(type, waybillList.get());
        Utilities.executeInThreadAndWaitForCompletion();
    }

    @Test(dependsOnMethods = "testDifferentTypeShipmentManifestation")
    private void writeDataToCSV() {
        csvData.put("Headers", new ArrayList<>(Arrays.asList("Package Type", "Master Waybill","Child Waybill")));
        CsvFileUtils.writeDataToCSV(CoreConstants.REG_CHECKLIST_PATH, "DifferentTypeShipmentManifestation.csv", csvData);
    }
}
