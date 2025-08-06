package com.delhivery.Express.testModules.PackageFlow.fileUpload.util;

import com.delhivery.core.utils.CsvFileUtils;
import com.delhivery.core.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class FileUploadUtility {
    public static final List<Map<String, ArrayList<String>>> wbnList = new ArrayList<>();
    public static int NUM_THREADS;
    private static final CountDownLatch latch = new CountDownLatch(NUM_THREADS);

    public static void executeInThreadAndWaitForCompletion() {
        new Thread(() -> {
            try {
                latch.countDown();
                if (latch.getCount() == 0) {
                    synchronized (latch) {
                        latch.notify();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Exception raised in latch " + e);
            }
        }).start();
        waitForAllThreads();
    }

    // Method to wait for all threads to complete
    private static void waitForAllThreads() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted while waiting for threads to complete.");
        }
    }

    public static void clearFolder( List<String> file) {
        FileUtils.removeFile(file);
    }

    public static void decorateFileDataAndWriteToCSVFile(ArrayList<String> header,String filePath, String fileName, Map<String, ArrayList<String>> multiHashMap){
        multiHashMap.put("Headers",header);
        CsvFileUtils.writeDataToCSV(filePath, fileName,multiHashMap);
    }
}
