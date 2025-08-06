package com.delhivery.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is used to handle the data from YML file
 * It will check the data type and return the data in the requested format
 *
 * @author Manmohan Pandey
 * @version 1.0
 * @since 2024-05-21
 */

public class YMLDataHandler {
    public static void main(String[] args) {
        Map<String,Object> itemData =  YamlReader.getYamlValues("Item."+("item"));
        YMLDataHandler.fetchListOfMapByKey(itemData,"list");
    }
    //Return the Map if Data is instance of Map
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fetchMapByKey(Map<String, Object> data, String key) {
        Object mapNode = data.get(key);
        if (mapNode instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) mapNode;
            System.out.println("Data as map: " + dataMap);
            return dataMap;
        }
        throw new RuntimeException("Abort !!!  Error while fetching Map from requested data");
    }

    //Return the list if Data is instance of list
    public static List<String> fetchListOfStringByKey(Map<String, Object> data, String key) {
        Object listNode = data.get(key);
        if (listNode instanceof List) {
            List<String> dataList = new ArrayList<>();
            List<?> listSubNode = (List<?>) listNode;
            listSubNode.forEach(
                    dataLeaf -> {
                        dataList.add(dataLeaf.toString());
                    });
            System.out.println("Data as list: " + dataList);
            return dataList;
        }
        throw new RuntimeException("Abort !!!  Error while fetching List from requested data");
    }

    //Return the list if Data is instance of List of integer
    public static List<Integer> fetchListOfIntegerByKey(Map<String, Object> data, String key) {
        Object listNode = data.get(key);
        if (listNode instanceof List) {
            List<Integer> dataList = new ArrayList<>();
            List<?> listSubNode = (List<?>) listNode;
            listSubNode.forEach(
                    dataLeaf -> {
                        dataList.add((Integer) dataLeaf);
                    });
            System.out.println("Data as list: " + dataList);
            return dataList;
        }
        //Returning null for invalid data handle a/c in caller method, dont change here
        return null;
    }


    //Return the list of Map if Data is instance of list
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> fetchListOfMapByKey(Map<String, Object> data, String key) {
        Object listNode = data.get(key);
        if (listNode instanceof List) {
            List<Map<String, Object>> dataList = new ArrayList<>();
            List<?> listSubNode = (List<?>) listNode;
            listSubNode.forEach(
                    dataLeaf -> {
                        dataList.add((Map<String,Object>) dataLeaf);
                    });
            System.out.println("Data as list of map : " + dataList);
            return dataList;
        }
        throw new RuntimeException("Abort !!! Error while fetching List of Map from requested data");
    }

    public static String ObjectToStringOrNull(Object name) {
        return (name != null) ? name.toString() : null;
    }
}