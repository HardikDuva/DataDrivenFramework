package org.dataProvider;

import org.utilities.ExcelConnector;
import org.testng.annotations.DataProvider;

/**
 * @author myname
 */
public class LoginTestDataProvider {

    @DataProvider(name="GetExcelData")
    public static Object[][] readExcelData() {
        ExcelConnector excelOperation = new ExcelConnector();

        //Data Read Excel sheet file path
        String excelFilePath = "";

        //How Many column have data
        int totalColumn = 5;

        //Data Read Excel sheet name
        String sheetName = "";

        return excelOperation.readFromExcel(excelFilePath,sheetName,totalColumn);

    }

}
