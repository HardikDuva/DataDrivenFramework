package com.configuration;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelConnector {

    /**
     * Create Excel file
     */
    public String createExcelFile(String functionality,String resultFileName,String resultSheetName,String outputDir) {
        String filePath = null;

        try {
            // Generate a dynamic filename using the current timestamp
            String directoryPath = outputDir + File.separator + "OutputResultSheet" +
                    File.separator + functionality;

            Utilities.createDir(directoryPath);

            if(!resultFileName.contains(".xlsx")) {
                resultFileName = resultFileName + ".xlsx";
            }

            // Complete file path
            filePath = directoryPath + File.separator + resultFileName;

            Workbook workbook = new XSSFWorkbook();
            workbook.createSheet(resultSheetName);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            // Write the workbook to the file
            workbook.write(fileOut);
            System.out.println("Excel file created successfully at " + filePath);
        }

        catch (Exception e) {
            Assert.fail("Error "+ e.getMessage() +
                    "found while creating file " + resultFileName);
        }

        return filePath;
    }

    /**
     * Read Data from Excel file
     */
    public Object[][] readFromExcel(String excelFilePath, String sheetName,int totalColumn) {

        try {
            DataFormatter formatter= new DataFormatter();
            FileInputStream file = new FileInputStream(excelFilePath);
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int row = sheet.getLastRowNum();

            Object[][] Data = new Object[row][totalColumn];
            for(int rowCount = 0 ; rowCount < row ; rowCount++) {
                for (int columnCount = 0; columnCount < totalColumn; columnCount++) {
                    String value = formatter.formatCellValue(sheet.getRow(rowCount+1).getCell(columnCount));
                    if(value.equalsIgnoreCase("null") || StringUtils.isBlank(value)
                            || value.equalsIgnoreCase("")) {
                        break;
                    }
                    else {
                        Data[rowCount][columnCount] = value.trim();
                    }
                }
            }

            file.close();
            return Data;
        }

        catch(Exception e) {
            Assert.fail("Error "+ e.getMessage() +
                    "found while fetching Data from the file " + excelFilePath);
            return null;
        }
    }

    /**
     * Write Data into Excel file
     */
    public void writeIntoExcel(String excelFilePath,String sheetName,String cellValue,int rowIndex,int columnIndex) {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            // Get the sheet at index 0 (first sheet)
            Sheet sheet = workbook.getSheet(sheetName);

            // Access the specific row (create if it doesn't exist)
            Row row = sheet.getRow(rowIndex);

            if (row == null) {
                row = sheet.createRow(rowIndex);
            }

            // Access the specific cell (create if it doesn't exist)
            Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            // Update the cell value
            cell.setCellValue(cellValue);

            // Write the output to the file
            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            Assert.fail("Error "+ e.getMessage() +
                    "found while writing Data into the file " + excelFilePath);
        }
    }
}
