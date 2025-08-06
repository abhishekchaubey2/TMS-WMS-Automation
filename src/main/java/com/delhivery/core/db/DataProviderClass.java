package com.delhivery.core.db;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderClass {
    public static String Path = System.getProperty("user.dir");
//    public static String sheetName;
//    public static String fileName;
    public static String sheetName = "Pkg_flows";
    public static String fileName = "CmuRegressionData";
    public static String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/";

    @DataProvider(name = "dataprovider")
    public static Object[][] dataprovider() throws IOException, EncryptedDocumentException, InvalidFormatException {
        String TestDataFilePath = filePath + fileName + ".xls";
        File file = new File(TestDataFilePath);
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fis);
        int row = workbook.getSheet(sheetName).getPhysicalNumberOfRows() - 1;
        int col = workbook.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[row][col];
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < col; j++) {
                data[i - 1][j] = workbook.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();
                System.out.print(data[i - 1][j] + " , ");
            }
            System.out.println("\n");
        }

        return data;
    }


}
