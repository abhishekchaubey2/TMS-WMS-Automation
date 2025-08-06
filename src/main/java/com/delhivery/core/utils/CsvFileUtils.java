package com.delhivery.core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;


import static com.delhivery.core.utils.Utilities.logInfo;

public class CsvFileUtils {

    //write a util function to accept file path and multi hashmap data from user and write it to a csv file
    public static void writeDataToCSV(String filePath, String fileName, Map<String, ArrayList<String>> multiHashMap) {
        FileUtils.createFileIfNotExist(filePath);

        try (FileWriter csvWriter = new FileWriter(filePath+fileName)) {
            csvWriter.write("");
            System.out.println("CSV file data has been cleared successfully!");

            // Write headers
            writeCSVLine(csvWriter, multiHashMap.get("Headers").toArray(new String[0]));

            // Write data
            for (Map.Entry<String, ArrayList<String>> entry : multiHashMap.entrySet()) {
                String key = entry.getKey();
                if (key.equals("Headers")) continue; // skip writing header again
                writeCSVLine(csvWriter, multiHashMap.get(key).toArray(new String[0]));
            }

            System.out.println("CSV file has been created successfully!");

        } catch (IOException e) {
            throw new RuntimeException("Exception occurred while writing the data " + e);
        }
    }

    private static void writeCSVLine(FileWriter csvWriter, String... values) throws IOException {
        for (String value : values) {
            csvWriter.append(escapeSpecialCharacters(value)).append(",");
        }
        csvWriter.append("\n");
    }

    private static String escapeSpecialCharacters(String value) {
        // Escape double quotes with double double quotes
        return value.replace("\"", "\"\"");
    }

    public static File loadCSVFile(String fileName) {
        try {
            // Get the URL of the file from the resources folder
            URL resourceUrl = CsvFileUtils.class.getClassLoader().getResource(fileName);

            if (resourceUrl == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            // Convert URL to URI and then to File
            return Paths.get(resourceUrl.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error loading CSV file: " + e.getMessage(), e);
        }
    }

    public static void printCSVFileData(File csvFile) {
        try (BufferedReader reader = Files.newBufferedReader(csvFile.toPath(), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                logInfo(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while printing the data " + e);
        }
    }

    public static void createCSVFileWithUTFEncoded(String filePath, String fileName, Map<String, ArrayList<String>> reqData) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(filePath+fileName)), StandardCharsets.UTF_8))) {

            ArrayList<String> headers = reqData.get("Headers");
            writeCSVLine(writer, headers);

            reqData.remove("Headers");
            for (Map.Entry<String, ArrayList<String>> entry : reqData.entrySet()) {
                writeCSVLine(writer, entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception occurred "+e);
        }
        System.out.println("Request data written successfully.");
    }

    private static void writeCSVLine(BufferedWriter writer, ArrayList<String> values) throws IOException {
        StringBuilder line = new StringBuilder();
        boolean firstField = true;
        for (String value : values) {
            if (!firstField) {
                line.append(",");
            }
            line.append(formatCSVField(value));
            firstField = false;
        }
        writer.write(line.toString());
        writer.newLine();
    }

    private static String formatCSVField(String field) {
        if (field == null) return "";
        if (field.contains("\"")) {
            field = field.replace("\"", "\"\"");
        }
        if (field.contains(",") || field.contains("\n") || field.contains("\r")) {
            field = "\"" + field + "\"";
        }
        return field;
    }

    public static void createCSVFileFromByteStream(byte[] responseBytes, String filePath) {
        String responseData = removeBOM(new String(responseBytes));
        String[] lines = responseData.split("\n");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line.trim());
                writer.newLine();
            }
            System.out.println("CSV file written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }

    private static String removeBOM(String data) {
        if (data != null && !data.isEmpty() && (int) data.charAt(0) == 65279) {
            return data.substring(1);
        }
        return data;
    }
}
