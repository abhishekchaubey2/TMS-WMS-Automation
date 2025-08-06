package com.delhivery.Express.testModules.PackageFlow.fileUpload.AddNewScan;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.testModules.PackageFlow.fileUpload.util.FileUploadUtility;
import com.delhivery.Express.testModules.util.TestModuleHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.CsvFileUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewScanOnPackage extends BaseTest {
    DifferentStateShipments differentStateShipments = new DifferentStateShipments();
    ApiController apiController = new ApiController();
    ArrayList<String> manifestedWaybillFileHeader = new ArrayList<>();
    ArrayList<String> requestFileHeader = new ArrayList<>();
    ThreadLocal<String> manifestedWaybill = new ThreadLocal<>();
    ThreadLocal<HashMap<String, String>> manifestData = new ThreadLocal<>();
    ThreadLocal<Map<String, ArrayList<String>>> manifestedWaybillAndStateData = new ThreadLocal<>();
    private static final List<String> processedScenarios = Collections.synchronizedList(new ArrayList<>());

    private final String status;
    private final String statusType;
    private final String nslCode;
    private final String additionalRemarks;
    private final String waybillFilePath = "src/test/resources/testData/BulkUpdate/request/ManifestedWbn/";
    private final  String requestFilePath = "src/test/resources/testData/BulkUpdate/request/WBNToBeProcessed/";
    private final String outputFilePath ="src/test/resources/testData/BulkUpdate/response/";

    public AddNewScanOnPackage(String status, String statusType, String nslCode, String additionalRemarks) {
        this.status = status;
        this.statusType = statusType;
        this.nslCode = nslCode;
        this.additionalRemarks = additionalRemarks;
        manifestedWaybillFileHeader.add("Status");
        manifestedWaybillFileHeader.add("Waybill");
        requestFileHeader.add("wbn");
        requestFileHeader.add("status");
        requestFileHeader.add("status type");
        requestFileHeader.add("nsl_code");
        requestFileHeader.add("additional_remarks");
    }

    @DataProvider(name = "all_different_type_scan", parallel = true)
    public static Object[][] allDifferentTypePackageState() {
        return new Object[][]{
                {"Scenario:: Add scan status: Pending, status type: UD, nsl_code: DTUP-213 and additional remark : rmk", "Pending", "UD", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: Delivered, status type: DL, nsl_code: EOD-38 and additional remark : rmk", "Delivered", "DL", "EOD-38", "rmk"},
                {"Scenario:: Add scan status: Scheduled, status type: PP, nsl_code: DTUP-213 and additional remark : rmk", "Scheduled", "PP", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: RTO , status type: DL, nsl_code: DTUP-213 and additional remark : rmk", "RTO ", "DL", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: DTO , status type: DL, nsl_code: DTUP-213 and additional remark : rmk", "DTO ", "DL", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: In Transit , status type: RT, nsl_code: DTUP-213 and additional remark : rmk", "In Transit ", "RT", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: Pending , status type: RT, nsl_code: DTUP-213 and additional remark : rmk", "Pending ", "RT", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: Pending , status type: PU, nsl_code: DTUP-213 and additional remark : rmk", "Pending ", "PU", "DTUP-213", "rmk"},
                {"Scenario:: Add scan status: In Transit , status type: PU, nsl_code: DTUP-213 and additional remark : rmk", "In Transit ", "PU", "DTUP-213", "rmk"},
        };
    }

    @Factory(dataProvider = "all_different_type_scan")
    private static Object[] getAllDifferentTypePackageStateFactory(String scenario, String status, String statusType, String nslCode, String additionalRemarks) {
        return new Object[]{new AddNewScanOnPackage(status, statusType, nslCode, additionalRemarks)};
    }

    @BeforeSuite
    private void setup() {
        List<String> file = new ArrayList<>();
        file.add(waybillFilePath);
        file.add(requestFilePath);
        file.add(outputFilePath);
        FileUploadUtility.clearFolder(file);
    }

    @Test(dataProvider = "all_different_state_type_pkg", dataProviderClass = manifestationData.class)
    private void testManifestDifferentStateShipments(String scenario, String state) {
        manifestData.set(TestModuleHelper.prepareManifestData(null, "B2C", null, null).get());
        FileUploadUtility.NUM_THREADS = manifestationData.allDifferentTypePackageState().length;
        if (scenario.contains("REPL RETURNED")) {
            manifestData.get().put("payment_mode", "Prepaid");
        }
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

            Map<String, ArrayList<String>> firstTenMaps = FileUploadUtility.wbnList.stream().limit(manifestationData.allDifferentTypePackageState().length)
                    .collect(HashMap::new, Map::putAll, Map::putAll);
            manifestedWaybillAndStateData.set(firstTenMaps);
            System.out.println("Data to be executed " + FileUploadUtility.wbnList);
            FileUploadUtility.wbnList.subList(0, manifestationData.allDifferentTypePackageState().length).clear();
            System.out.println("Data after processing " + FileUploadUtility.wbnList);
        }
    }

    @Test(dependsOnMethods = "testManifestDifferentStateShipments", threadPoolSize = 10)
    public void testAddNewScanOnPackage() throws InterruptedException {
        String wayBillFileName = "waybill_" + status.trim() + "_" + System.currentTimeMillis() + ".csv";
        String requestFileName = "add_new_nsl_on_package_" + status.trim() + "_" + System.currentTimeMillis() + ".csv";

        setWbnDataForBulkUpdate();
        FileUploadUtility.decorateFileDataAndWriteToCSVFile(manifestedWaybillFileHeader,waybillFilePath, wayBillFileName,manifestedWaybillAndStateData.get());
        Map<String, String> nslMap = AddNewScanOnPackageHelper.createNSLMap(status, statusType, nslCode, additionalRemarks);
        AddNewScanOnPackageHelper.writeCSVWithUFTEncoded(manifestedWaybillAndStateData.get(), nslMap,requestFilePath, requestFileName);

        Map<String, String> data = new HashMap<>();
        data.put("file_param_name", "wbn_file");
        data.put("file_path", requestFilePath + requestFileName);
        data.put("param_name", "action");
        data.put("param_value", "ADD");

        String jwt = apiController.fetchInternalUserJwtTokenApi(null);
        jwt = "staging_ums_token=" + "csrftoken=LdcT7VEaXL2vVrK4fJX7zUHduNByT5L1; __utmc=47649330; __utmz=47649330.1712893598.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); staging_ums_token=" + jwt + "; center_name=\"Mumbai MIDC\"; center_code=IND400093AAA; _ga=GA1.2.1772887149.1712893598; _gid=GA1.2.1788968007.1712893631; __utma=47649330.1772887149.1712893598.1712893598.1712896547.2; __utmt=1; __utmb=47649330.3.10.1712896547; sessionid=t0wdtt1ftpyhani4r753lacxb8ojuw32";
        data.put("jwt_token", jwt);
        data.put("token_type", "cookie_token");
        byte[] res = apiController.updateStatusByFile(data);

        String outputPath = outputFilePath+"response_" + status.trim() + "_" + System.currentTimeMillis() + ".csv";
        CsvFileUtils.createCSVFileFromByteStream(res, outputPath);
    }
}
