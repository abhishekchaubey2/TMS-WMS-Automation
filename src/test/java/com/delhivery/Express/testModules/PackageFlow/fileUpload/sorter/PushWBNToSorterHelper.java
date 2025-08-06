package com.delhivery.Express.testModules.PackageFlow.fileUpload.sorter;

import com.delhivery.core.utils.CsvFileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PushWBNToSorterHelper {
    public static void writeCSVWithUFTEncoded(Map<String, ArrayList<String>> manifestedWaybillAndStateData, String requestFilePath, String fileName) {
        Map<String, ArrayList<String>> reqData = new HashMap<>();
        System.out.println(reqData);
        manifestedWaybillAndStateData.forEach((state, waybill) -> {
            ArrayList<String> newEntry = new ArrayList<>(Arrays.asList(waybill.get(1)));
            reqData.put(state, newEntry);
        });
        reqData.put("Headers", new ArrayList<>(Arrays.asList("Waybills")));
        System.out.println(reqData);
        CsvFileUtils.createCSVFileWithUTFEncoded(requestFilePath, fileName, reqData);
        System.out.println("Request data written successfully.");
    }
}
