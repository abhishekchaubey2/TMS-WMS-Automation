package common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class ExpressUtilities {
    public static String jsonObjectToString(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(object);
            System.out.println("Json String : " + jsonString);
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException("Error while converting object to string", e);
        }
    }


    public static String generateUniqueEntity(String entity) {
        long time = System.currentTimeMillis();
        String result;
        switch (entity.toLowerCase()) {
            case "bag":
                result = "BAG" + getUniqueInt(12);
                System.out.println("BAG Generated : " + result);

                break;
            case "trip":
                result = "trip-" + getUniqueInt(20);
                System.out.println("Trip Generated : " + result);
                break;
            case "dispatch":
                result = getUniqueInt(6);
                System.out.println("Dispatch Id Generated : " + result);
                break;
            case "agn":
                result = "AGN" + time;
                System.out.println("AGN Generated : " + result);
                break;
            case "order":
                result = "OD" + getUniqueInt(15);
                System.out.println("Order Generated : " + result);
                break;
            case "shipment":
                result = "SH" + time;
                System.out.println("Shipment Generated : " + result);

                break;
            case "invoice":
                result = "IN" + time;
                System.out.println("Invoice Generated : " + result);
                break;
            case "cyclecountwave":
                result = "WAVE" + time;
                System.out.println("CycleCount wave Generated : " + result);
                break;
            case "container":
                result = "CONT" + time;
                System.out.println("Container ID Generated : " + result);
                break;
            case "requestid":
                result = generateRequestId();
                System.out.println("RequestId Generated : " + result);
                break;
            case "waybill":
                result = "WB" + time;
                System.out.println("Waybill Generated : " + result);
                break;
            default:
                result = String.valueOf(time);
                break;
        }
        return result;
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

    public static void hardWait(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error while waiting");
        }
    }
}
