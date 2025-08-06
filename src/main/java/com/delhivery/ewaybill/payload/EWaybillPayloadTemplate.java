package com.delhivery.ewaybill.payload;

import com.delhivery.core.utils.DateTimeUtility;
import com.delhivery.core.utils.Utilities;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class is used to generate the payload for the E-Waybill API.
 * The payload is then encrypted and sent to the API.
 * @author Manmohan
 * @version 1.0
 * @since 2024-09-20
 */
public class EWaybillPayloadTemplate {
    public static Map<String, Object> samplePayload = new HashMap<>();
    public static final String nonEncryptedAppKey = getAppKey();

    public static String getAppKey() {
        Random random = new Random();
        StringBuilder appKey = new StringBuilder();

        for (int x = 0; x < 32; x++) {
            char c = (char) ('a' + random.nextInt(26));
            appKey.append(c);
        }

        String result = appKey.toString();
        System.out.println("App Key: " + result);
        return result;
    }

    public static Map<String, Object> getSamplePayload() {
        System.out.println("Sample App Key: " + nonEncryptedAppKey);

        try {
            samplePayload.put("App_key", new String(Base64.getEncoder().encode(nonEncryptedAppKey.getBytes("UTF-8")), "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        samplePayload.put("password", "Delhivery123!@#");
        samplePayload.put("action", "ACCESSTOKEN");
        samplePayload.put("username", "DelhiveryHR");

        System.out.println("Sample Payload in initialisation : " + samplePayload);
        return samplePayload;
    }

    //will make this enum
    private static Map<String, Integer> getStateCodeMap() {
        Map<String, Integer> stateCodeMap = new HashMap<>();
        stateCodeMap.put("JAMMU AND KASHMIR", 1);
        stateCodeMap.put("HIMACHAL PRADESH", 2);
        stateCodeMap.put("PUNJAB", 3);
        stateCodeMap.put("CHANDIGARH", 4);
        stateCodeMap.put("UTTARAKHAND", 5);
        stateCodeMap.put("HARYANA", 6);
        stateCodeMap.put("DELHI", 7);
        stateCodeMap.put("RAJASTHAN", 8);
        stateCodeMap.put("UTTAR PRADESH", 9);
        stateCodeMap.put("BIHAR", 10);
        stateCodeMap.put("SIKKIM", 11);
        stateCodeMap.put("ARUNACHAL PRADESH", 12);
        stateCodeMap.put("NAGALAND", 13);
        stateCodeMap.put("MANIPUR", 14);
        stateCodeMap.put("MIZORAM", 15);
        stateCodeMap.put("TRIPURA", 16);
        stateCodeMap.put("MEGHALAYA", 17);
        stateCodeMap.put("ASSAM", 18);
        stateCodeMap.put("WEST BENGAL", 19);
        stateCodeMap.put("JHARKHAND", 20);
        stateCodeMap.put("ORISSA", 21);
        stateCodeMap.put("CHHATTISGARH", 22);
        stateCodeMap.put("MADHYA PRADESH", 23);
        stateCodeMap.put("GUJARAT", 24);
        stateCodeMap.put("DAMAN AND DIU", 25);
        stateCodeMap.put("DADRA AND NAGAR HAVELI", 26);
        stateCodeMap.put("MAHARASHTRA", 27);
        stateCodeMap.put("KARNATAKA", 29);
        stateCodeMap.put("GOA", 30);
        stateCodeMap.put("LAKSHADWEEP", 31);
        stateCodeMap.put("KERALA", 32);
        stateCodeMap.put("TAMIL NADU", 33);
        stateCodeMap.put("PONDICHERRY", 34);
        stateCodeMap.put("ANDAMAN AND NICOBAR", 35);
        stateCodeMap.put("TELANGANA", 36);
        stateCodeMap.put("ANDHRA PRADESH", 37);
        stateCodeMap.put("OTHER TERRITORY", 97);
        stateCodeMap.put("OTHER COUNTRY", 99);
        stateCodeMap.put("LADAKH", 38);
        return stateCodeMap;
    }

    public static Map<String, Object> getGovtEWbnPayload(Map<String, Object> data) {
        Map<String, Object> newPayload = new HashMap<>();
        Map<String, Object> itemDetail = new HashMap<>();

        if (data.containsKey("tripItems")) {
            var tripItems = (Object[]) data.get("tripItems");
            if (tripItems.length > 0) {
                itemDetail = (Map<String, Object>) tripItems[0];
            }
        }

        String subSupplyType;
        String docType;
        String subSupplyDesc;

        if ("SALES".equals(itemDetail.get("transactionType"))) {
            subSupplyType = "1";
            docType = "INV";
            subSupplyDesc = "Forward";
            System.out.println("[govt_ewbn_payload] Generating Forward EWBN");
        } else {
            subSupplyType = "8";
            subSupplyDesc = "Return";
            docType = "CHL";
            System.out.println("[govt_ewbn_payload] Generating Return EWBN");
        }

        newPayload.put("actFromStateCode", getStateCodeMap().getOrDefault(itemDetail.get("billToState"), 27));
        newPayload.put("actFromStateCode", getStateCodeMap().getOrDefault(itemDetail.get("billToState"), 27));

        newPayload.put("actToStateCode", getStateCodeMap().getOrDefault(itemDetail.get("shipToState"), 27));
        newPayload.put("cessNonAdvolValue", 0);
        newPayload.put("cessValue", 0);
        newPayload.put("cgstValue", 0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date invoiceDate = dateFormat.parse((String) itemDetail.get("invoiceDt"));
//            newPayload.put("docDate", dateFormat.format(invoiceDate));
            newPayload.put("docDate", DateTimeUtility.getDateMonthYearFormatDate(0L));
        } catch (Exception e) {
            e.printStackTrace(); // Handle date parsing exception
        }

        newPayload.put("docNo", DateTimeUtility.getModularCurrentTimeStamp());
        newPayload.put("docType", docType);
        newPayload.put("fromAddr1", itemDetail.get("consignorName"));
        newPayload.put("fromAddr2", itemDetail.get("consignorAddr"));
        newPayload.put("fromGstin", "06AAPCS9575E1ZR");
        newPayload.put("fromPincode", itemDetail.get("consignorPin"));
        newPayload.put("fromPlace", itemDetail.get("consignorAddr"));
        newPayload.put("fromStateCode", getStateCodeMap().getOrDefault(itemDetail.get("billToState"), 27));
        newPayload.put("fromTrdName", itemDetail.get("consignorName"));
        newPayload.put("igstValue", 0);

        // Create item list
        Map<String, Object> item1 = createItemMap(itemDetail);
        Map<String, Object> item2 = createItemMap(itemDetail); // Assuming two items with same details

        newPayload.put("itemList", new Map[]{item1, item2});
        newPayload.put("otherValue", 0);
        newPayload.put("sgstValue", 0);
        newPayload.put("subSupplyType", subSupplyType);
        newPayload.put("subSupplyDesc", subSupplyDesc);
        newPayload.put("supplyType", "O");
        newPayload.put("toAddr1", itemDetail.get("consigneeName"));
        newPayload.put("toAddr2", itemDetail.get("consigneeAddr"));
        newPayload.put("toGstin", itemDetail.get("consigneeGst"));
        newPayload.put("toPincode", itemDetail.get("consigneePin"));
        newPayload.put("toPlace", itemDetail.get("consigneeAddr"));
        newPayload.put("toStateCode", getStateCodeMap().getOrDefault(itemDetail.get("shipToState"), 27));
        newPayload.put("toTrdName", itemDetail.get("consigneeName"));
        newPayload.put("totInvValue", itemDetail.get("invoiceVal"));
        newPayload.put("totalValue", 0);
        newPayload.put("transDistance", itemDetail.get("distance"));

        newPayload.put("transDocDate", DateTimeUtility.getDateMonthYearFormatDate(0L));
        newPayload.put("transDocNo", null);
        newPayload.put("transMode", null);
        newPayload.put("transactionType", 1);
        newPayload.put("transporterId", "06AAPCS9575E1ZR");
        newPayload.put("transporterName", "DELHIVERY PRIVATE LIMITED");
        newPayload.put("vehicleNo", null);
        newPayload.put("vehicleType", "R");

        return newPayload;
    }

    private static Map<String, Object> createItemMap(Map<String, Object> itemDetail) {
        Map<String, Object> item = new HashMap<>();
        item.put("cessNonadvol", 0);
        item.put("cessRate", 0);
        item.put("cgstRate", 0);
        item.put("hsnCode", itemDetail.get("hsnCode"));
        item.put("igstRate", 0);
        item.put("productDesc", "This is the test product description");
        item.put("productName", "");
        item.put("qtyUnit", itemDetail.get("unit"));
        item.put("quantity", itemDetail.get("qty"));
        item.put("sgstRate", 0);
        item.put("taxableAmount", 0);
        return item;
    }

    public static Map<String, Object> getStaticPayload() {
        Map<String, Object> data = new HashMap<>();
        data.put("consolidate", false);

        // Create tripItems list
        Map<String, Object> tripItem = new HashMap<>();
        tripItem.put("billToState", "MAHARASHTRA");
        tripItem.put("distance", 5);
        tripItem.put("hsnCode", "9105");
        tripItem.put("consigneeName", "ABSAR SK");
        tripItem.put("invoiceVal", 60000);
        tripItem.put("ewaybill", "");
        tripItem.put("consignorPin", "400060");
        tripItem.put("consignorAddr", "Shop Num 19 Second Floor Silwar Sqauar Bhagwan Das Road C Scheme");
        tripItem.put("invoiceNo", DateTimeUtility.getModularCurrentTimeStamp());
        tripItem.put("unit", "NOS");
        tripItem.put("consigneeAddr", "sahebrampur jalangi ,sahebrampur jalangi murshidabad Domkol");
        tripItem.put("consignorGst", "URP");
        tripItem.put("consignorName", "Shop.with.Style");
        tripItem.put("consigneeGst", "URP");
        tripItem.put("invoiceDt", "2023-09-20T14:25:14");
        tripItem.put("qty", 1);
        tripItem.put("awb", "5551392283492");
        tripItem.put("consigneePin", "400059");
        tripItem.put("shipToState", "MAHARASHTRA");
        tripItem.put("transactionType", "SALES");
        System.out.println("Static Trip Item: " + tripItem);

        // Add tripItem to a list (Array)
        data.put("tripItems", new Map[]{tripItem});
        data.put("modeOfTransport", "ROAD");
        data.put("transhipmentLocation", "Mumbai");
        data.put("state", "MH");
        data.put("vehicleNo", "");

        System.out.println("Static Payload: " + data);
        return getGovtEWbnPayload(data);
    }
}
