package com.delhivery.core.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFileUtil {
    private static final int headerRow = 0;
    private static final ThreadLocal<Boolean> shouldHandleSuite = new ThreadLocal<>();
    private static Map<Integer, Integer> columnMaxLengthMap = new HashMap<>();

    public static void writeExcelFile(String path, String fileName, String sheetName, Map<String, String> fileDecorators, Map<String, List<String>> data) {
        try{
            Workbook workbook = new XSSFWorkbook();
            List<String> suiteNames = shouldProcessForSuite(fileDecorators, data);
            Sheet sheet = workbook.createSheet(sheetName);
            Map<String, List<String>> dataWithoutHeader = writeHeader(sheet, data);
            writeData(sheet, dataWithoutHeader);
            postFileAction(fileDecorators, sheet, suiteNames);
            saveFile(path, fileName, workbook);
        } catch (Exception e) {
            System.out.println("Error creating Excel workbook: " + e.getMessage());
        }
    }

    private static List<String> shouldProcessForSuite(Map<String, String> decorators, Map<String, List<String>> data) {
        for (Map.Entry<String, String> entry : decorators.entrySet()) {
            String key = entry.getKey();
            if (key.contains(CoreConstants.SUITE_IDENTIFIER)) {
                shouldHandleSuite.set(true);
                List<String> suiteList = data.get(CoreConstants.SUITE_IDENTIFIER);
                data.remove(CoreConstants.SUITE_IDENTIFIER);
                return suiteList;
            }
        }
        shouldHandleSuite.set(false);
        return null;
    }

    private static Map<String, List<String>> writeHeader(Sheet sheet, Map<String, List<String>> data) {
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            Row row = sheet.createRow(headerRow);
            int columnCount = 0;
            String key = entry.getKey();
            List<String> value = entry.getValue();

            if (key.equalsIgnoreCase(CoreConstants.HEADER)) {
                for (String header : value) {
                    Cell cell = row.createCell(columnCount++);
                    cell.setCellValue(header);
                }
                data.remove(CoreConstants.HEADER);
                break;
            } else {
                System.out.println("No need to create header row");
            }
        }
        return data;
    }

    private static int getColumnIndex() {
        return shouldHandleSuite.get() ? 1 : 0;
    }

    private static void writeData(Sheet sheet, Map<String, List<String>> data) {
        int rowPointer = headerRow + 1;
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            Row row = sheet.createRow(rowPointer++);
            int columnIndex = getColumnIndex();
            List<String> value = entry.getValue();
            for (String val : value) {
                setCellLength(columnIndex, val);
                Cell cell = row.createCell(columnIndex++);
                cell.setCellValue(val);
            }
        }
    }

    private static void postFileAction(Map<String, String> decorators, Sheet sheet, List<String> data) {
        for (Map.Entry<String, String> entry : decorators.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case "should_merged_column":
                    if (value.equalsIgnoreCase("True")) {
                        mergeCells(sheet, Integer.parseInt(decorators.get("merged_cell_column")), data.get(0));
                    }
                    break;

                case "should_set_column_width":
                    if (value.equalsIgnoreCase("True")) {
                        setColumnWidth(sheet);
                    }

                default:
                    System.out.println("No post write action required");

            }
        }

    }

    private static void setCellLength(Integer columnIndex, String value) {
        int currentLength = value.length();
        int previousMaxLength = columnMaxLengthMap.getOrDefault(columnIndex, 0);

        if (currentLength > previousMaxLength) {
            columnMaxLengthMap.put(columnIndex, currentLength);
        }
    }

    private static void mergeCells(Sheet sheet, int columnIndex, String mergedValue) {
        // Create a CellRangeAddress for merging cells in the specified column
        CellRangeAddress mergedRegion = new CellRangeAddress(headerRow + 1, sheet.getLastRowNum(), columnIndex, columnIndex);

        if (isValidMergeRegion(sheet, mergedRegion)) {
            sheet.addMergedRegion(mergedRegion);
        }

        // Set a value to the merged cell
        Row firstRow = sheet.getRow(headerRow + 1);
        Cell mergedCell = firstRow.createCell(columnIndex);
        mergedCell.setCellValue(mergedValue);
        setCellLength(columnIndex, mergedValue);
    }

    private static boolean isValidMergeRegion(Sheet sheet, CellRangeAddress mergeRegion) {
        int firstRow = mergeRegion.getFirstRow();
        int lastRow = mergeRegion.getLastRow();
        int firstCol = mergeRegion.getFirstColumn();
        int lastCol = mergeRegion.getLastColumn();

        return (lastRow > firstRow || lastCol > firstCol);
    }

    private static void setColumnWidth(Sheet sheet) {
        for (Map.Entry<Integer, Integer> columnLength : columnMaxLengthMap.entrySet()) {
            Integer colNum = columnLength.getKey();
            Integer contentLength = columnLength.getValue();
            sheet.setColumnWidth(colNum, contentLength*256);
        }
    }

    private static void saveFile(String path, String fileName, Workbook workbook) {
        String filePath = path + "/" + fileName + CoreConstants.EXCEL_EXT;

        FileUtils.createFileIfNotExist(path);

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            System.out.println("Excel file written successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing Excel file: " + e.getMessage());
        }
    }
}
