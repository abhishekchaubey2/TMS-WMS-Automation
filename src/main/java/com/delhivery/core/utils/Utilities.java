package com.delhivery.core.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static java.util.UUID.randomUUID;

public class Utilities {
    public static int NUM_THREADS;
    private static final CountDownLatch latch = new CountDownLatch(NUM_THREADS);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String generateUniqueEntity(String entity) {
        long time = System.currentTimeMillis();
        String result;
        switch (entity.toLowerCase()) {
            case "bag":
                result = "BAG" + getUniqueInt(12);
                System.out.println("BAG Generated : " + result);
                logInfo("Bag id using : " + result);
                break;
            case "trip":
                result = "trip-" + getUniqueInt(20);
                System.out.println("Trip Generated : " + result);
                logInfo("Trip id using : " + result);
                break;
            case "dispatch":
                result = getUniqueInt(6);
                System.out.println("Dispatch Id Generated : " + result);
                logInfo("Dispatch Id using : " + result);
                break;
            case "agn":
                result = "AGN" + time;
                System.out.println("AGN Generated : " + result);
                logInfo("AGN : " + result);
                break;
            case "order":
                result = "OD" + getUniqueInt(15);
                System.out.println("Order Generated : " + result);
                logInfo("Order : " + result);
                break;
            case "shipment":
                result = "SH" + time;
                System.out.println("Shipment Generated : " + result);
                logInfo("Shipment : " + result);
                break;
            case "invoice":
                result = "IN" + time;
                System.out.println("Invoice Generated : " + result);
                logInfo("Invoice : " + result);
                break;
            case "cyclecountwave":
                result = "WAVE" + time;
                System.out.println("CycleCount wave Generated : " + result);
                logInfo("CycleCount wave created: " + result);
                break;
            case "container":
                result = "CONT" + time;
                System.out.println("Container ID Generated : " + result);
                logInfo("Container ID created: " + result);
                break;
            case "requestid":
                result = generateRequestId();
                System.out.println("RequestId Generated : " + result);
                logInfo("RequestId created: " + result);
                break;
            case "waybill":
                result = "WB" + time;
                System.out.println("Waybill Generated : " + result);
                logInfo("Waybill : " + result);
                break;
            default:
                result = String.valueOf(time);
                break;
        }
        return result;
    }


    public static void logInfo(String info) {
        ExtentITestListenerAdapter.getTest().info(info);
    }

    public static void logInfo(ExtentTest test, String info) {
        test.info(info);
    }

    public static void logCodeBlock(String response) {
        ExtentITestListenerAdapter.getTest().info(MarkupHelper.createCodeBlock(response));
    }

    public static void logCodeBlock(ExtentTest test, String response) {
        test.info(MarkupHelper.createCodeBlock(response));
    }

    public static String htmlResponseParser(Response response) {
        String responseString = response.asPrettyString();
        XmlPath xmlPath = new XmlPath(XmlPath.CompatibilityMode.HTML, responseString);
        return xmlPath.getString("html.body");
    }

    public static String getUniqueString() {
        return RandomStringUtils.random(16, true, true);
    }

    public static String getUniqueInt(int length) {
        return RandomStringUtils.random(length, false, true);
    }

    private static String generateRequestId() {
        UUID uuid = randomUUID();
        String uuidStr = String.valueOf(uuid);
        return uuidStr;
    }

    //todo migrate this method to a getJsonString method
    public static String jsonObjectToString(Object body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(body);
    }

    public static String getJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(object);
            System.out.println("Json String : " + jsonString);
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException("Error while converting object to string", e);
        }
    }

    public static String jsonObjectToStringIncludeNullValue(Object body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //write code so that objectMapper inclues null values in the String json body
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.ALWAYS);
        String json = objectMapper.writeValueAsString(body);

        return json;
    }

    public static void hardWait(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void logSuccess(String successMessage) {
        ExtentITestListenerAdapter.getTest().pass(successMessage);
    }

    public static void logFailure(String failureMessage) {
        ExtentITestListenerAdapter.getTest().fail(failureMessage);
    }

    public static boolean hasMatchingSubstring(String str, List<String> substrings) {
        return StringUtils.indexOfAny(str, substrings.toArray(new String[0])) != -1;
    }


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

    public static String getDateTime(long days) {
        Instant instant = Instant.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Instant requestedDateTimeInUTC = instant.plus(days, ChronoUnit.DAYS);
        Date myDate = Date.from(requestedDateTimeInUTC);
        return dateFormat.format(myDate);
    }
}