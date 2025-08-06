package com.delhivery.Express.testModules.PackageFlow.fileUpload.AddNewScan;

import com.delhivery.core.utils.CsvFileUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AddNewScanOnPackageHelper {

    public static Map<String, String> createNSLMap(String status, String statusType, String nslCode, String additionalRemarks) {
        Map<String, String> nslMap = new HashMap<>();
        nslMap.put("status", status);
        nslMap.put("status_type", statusType);
        nslMap.put("nsl_code", nslCode);
        nslMap.put("additional_remarks", additionalRemarks);
        return nslMap;
    }


    public static void writeCSVWithUFTEncoded(Map<String, ArrayList<String>> manifestedWaybillAndStateData, Map<String, String> nslMap, String requestFilePath,String fileName) {
        Map<String, ArrayList<String>> reqData = new HashMap<>();
        System.out.println(reqData);
        manifestedWaybillAndStateData.forEach((state, waybill) -> {
            ArrayList<String> newEntry = new ArrayList<>(Arrays.asList(waybill.get(1), nslMap.get("status"),
                    nslMap.get("status_type"), nslMap.get("nsl_code"), nslMap.get("additional_remarks")));
            reqData.put(state, newEntry);
        });
        reqData.put("Headers", new ArrayList<>(Arrays.asList("wbn", "status", "status type", "nsl_code", "additional_remarks")));
        System.out.println(reqData);
        CsvFileUtils.createCSVFileWithUTFEncoded(requestFilePath, fileName, reqData);
        System.out.println("Request data written successfully.");
    }
}
