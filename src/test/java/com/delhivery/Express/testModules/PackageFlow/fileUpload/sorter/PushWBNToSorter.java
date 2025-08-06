package com.delhivery.Express.testModules.PackageFlow.fileUpload.sorter;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.testModules.PackageFlow.fileUpload.util.FileUploadUtility;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushWBNToSorter extends BaseTest {
    DifferentStateShipments differentStateShipments = new DifferentStateShipments();
    ApiController apiController = new ApiController();
    List<String> file = new ArrayList<>();
    ThreadLocal<String> manifestedWaybill = new ThreadLocal<>();
    ThreadLocal<HashMap<String, String>> manifestData = new ThreadLocal<>();
    ThreadLocal<Map<String, ArrayList<String>>> manifestedWaybillAndStateData = new ThreadLocal<>();
    private static final List<String> processedScenarios = Collections.synchronizedList(new ArrayList<>());
    private final ArrayList<String> manifestedWayBillFileHeader = new ArrayList<>();

    private final String manifestedWBNFilePath = "src/test/resources/testData/sorter/request/ManifestedWbn/";
    private final String fileToBePushOnSorterPath = "src/test/resources/testData/sorter/request/WBNToBeProcessed/";

    PushWBNToSorter(){
        manifestedWayBillFileHeader.add("State");
        manifestedWayBillFileHeader.add("Waybill");
    }

    @BeforeSuite
    private void setup() {
        file.add(manifestedWBNFilePath);
        file.add(fileToBePushOnSorterPath);
        FileUploadUtility.clearFolder(file);
    }

    @Test(dataProvider = "all_different_state_type_pkg", dataProviderClass = manifestationData.class)
    private void testManifestDifferentStateShipmentsForSorter(String scenario, String state) {
        manifestData.set(TestModuleHelper.prepareManifestData(null, "B2C", null, null).get());
        FileUploadUtility.NUM_THREADS = manifestationData.allDifferentTypePackageState().length;
        if (scenario.contains("REPL RETURNED")) {
            manifestData.get().put("payment_mode", "Prepaid");
        }
        manifestData.get().put("cwh_sent","true");
        manifestData.get().put("pickup_location","Vishal");
        manifestData.get().put("location_id","IND110044AAB");
        manifestData.get().put("center","Del_Okhla_PC (DELHI)");
        manifestData.get().put("cwh_uuid","delhivery::clientwarehouse::40abab94-c4e2-431a-bd68-9181c6c5d42c");
        manifestedWaybill.set(differentStateShipments.DifferentStateShipments(state, manifestData.get()));
        HashMap<String, ArrayList<String>> waybillMap = new HashMap<>();
        waybillMap.put(manifestedWaybill.get(), new ArrayList<>(Arrays.asList(state, manifestedWaybill.get())));
        manifestedWaybillAndStateData.set(waybillMap);
        System.out.println("Map Data " + manifestedWaybillAndStateData.get());

        FileUploadUtility.wbnList.add(new HashMap<>(manifestedWaybillAndStateData.get()));
        processedScenarios.add(scenario);
        FileUploadUtility.executeInThreadAndWaitForCompletion();
        System.out.println("Data " + FileUploadUtility.wbnList);
    }

    private synchronized void setWbnDataForBulkUpdate() {
        if (!FileUploadUtility.wbnList.isEmpty()) {
            Map<String, ArrayList<String>> manifestedWayBillFileData = FileUploadUtility.wbnList.stream().limit(manifestationData.allDifferentTypePackageState().length)
                    .collect(HashMap::new, Map::putAll, Map::putAll);
            manifestedWaybillAndStateData.set(manifestedWayBillFileData);
            System.out.println("Data to be executed " + FileUploadUtility.wbnList);
            FileUploadUtility.wbnList.subList(0, manifestationData.allDifferentTypePackageState().length).clear();
            System.out.println("Data after processing " + FileUploadUtility.wbnList);
        }
    }

    @Test(dependsOnMethods = "testManifestDifferentStateShipmentsForSorter")
    public void testPushToSorter() {
        String wayBillFileName = "waybill_" + System.currentTimeMillis() + ".csv";
        String requestFileName = "upload_to_sorter_" + System.currentTimeMillis() + ".csv";
        setWbnDataForBulkUpdate();
        FileUploadUtility.decorateFileDataAndWriteToCSVFile(manifestedWayBillFileHeader,manifestedWBNFilePath, wayBillFileName,manifestedWaybillAndStateData.get());
        PushWBNToSorterHelper.writeCSVWithUFTEncoded(manifestedWaybillAndStateData.get(), fileToBePushOnSorterPath, requestFileName);

        Map<String, String> data = new HashMap<>();
        data.put("file_param_name", "csv_file");
        data.put("file_path", fileToBePushOnSorterPath + requestFileName);
        data.put("param_name", "sorter");
        data.put("param_value", "46");

        String jwt = apiController.fetchInternalUserJwtTokenApi(null);
        data.put("jwt_token", jwt);
        data.put("token_type", "client_token");
        System.out.println(data);
        apiController.pushWBNToSorter(data);
    }
}
