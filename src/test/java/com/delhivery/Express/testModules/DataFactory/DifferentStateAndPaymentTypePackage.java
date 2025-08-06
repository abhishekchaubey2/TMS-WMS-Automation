package com.delhivery.Express.testModules.DataFactory;

import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.testModules.PackageFlow.fileUpload.util.FileUploadUtility;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.CsvFileUtils;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class DifferentStateAndPaymentTypePackage extends BaseTest {
    private final String state;
    Map<String, ArrayList<String>> waybillData=new HashMap<>();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();

    public DifferentStateAndPaymentTypePackage(String state) {
        this.state = state;
    }



    @Factory(dataProvider = "different_type_state",dataProviderClass = manifestationData.class)
    public static Object[] getState(String scenario, String state) {
        return new Object[]{new
                DifferentStateAndPaymentTypePackage(state)};
    }

    @Test(dataProvider = "different_payment_type",dataProviderClass = manifestationData.class)
    private void testDifferentPaymentAndStateTypePackage(String scenario, String paymentType) {
        FileUploadUtility.NUM_THREADS = manifestationData.getDifferentPaymentType().length;
        DifferentStateShipments differentStateShipments = new DifferentStateShipments();
        HashMap<String, String> data = new HashMap<>();
        data.put("payment_mode", paymentType);
        waybill.set(differentStateShipments.DifferentStateShipments(state, data));
        waybillData.put(paymentType, new ArrayList<>(Arrays.asList(paymentType,waybill.get())));
        Utilities.executeInThreadAndWaitForCompletion();
    }
    @Test(dependsOnMethods = "testDifferentPaymentAndStateTypePackage")
    private void writeDataToCSV() {
        waybillData.put("Headers", new ArrayList<>(Arrays.asList("payment_mode", "waybill")));
        CsvFileUtils.writeDataToCSV("src/test/resources/testData/different_payment_type/response/", state+".csv", waybillData);
    }
}
