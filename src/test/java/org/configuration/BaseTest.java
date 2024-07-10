package org.configuration;

import org.pages.LogInPage;
import org.utilities.EmailConnector;
import org.utilities.ExcelConnector;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.configuration.Utilities.getTimeStamp;

/**
 * @author myname
 */
public class BaseTest {

    public BaseDriver baseDriver = null;
    public String browser = null;
    private String serverURL = "";
    private final int limit = 60;
    protected String outputDirPath;

    @BeforeClass
    @Parameters({"Browser"})
    public void setBrowser(String brw) {
        this.browser = brw;

        this.outputDirPath = "." + File.separator + "TestResult"
                + File.separator + this.browser + File.separator + getTimeStamp();
    }

    /**
     * Create Web driver
     */
    protected void createWebdriver() {
        try {
            baseDriver = new BaseDriver(serverURL, browser,limit,outputDirPath);
        }

        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    /**
     * Close the browser
     */
    public void closeBrowser() {
        baseDriver.driver.quit();
    }

    LogInPage obLogInPage = null;
    ExcelConnector excelConnector = null;
    EmailConnector emailConnector = null;

    public ExcelConnector objExcelConnector() {
        try {
            if (excelConnector == null) {
                excelConnector = new ExcelConnector();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Assert.assertNotNull(excelConnector, "Excel Connector Page is not initialized!");

        return excelConnector;
    }

    public EmailConnector objEmailConnector() {
        try {
            if (emailConnector == null) {
                emailConnector = new EmailConnector();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNotNull(emailConnector, "Email Connector Page is not initialized!");

        return emailConnector;
    }

    public LogInPage ObLogInPage() {
        obLogInPage = new LogInPage(baseDriver);
        return obLogInPage;
    }

    /**
     * Create Excel file for output result
     */
    public String createExcelSheetOutputResultFile(String functionality,String resultFileName,
                                                   String resultSheetName,String outputDir) {
        return objExcelConnector()
                .createExcelFile(functionality,resultFileName,resultSheetName,outputDir);
    }

    /**
     * Added Header to Excel file
     */
    protected void createExcelSheetHeaderRowValue(String filePath,String sheetName,LinkedHashMap<String,
            LinkedHashMap<String, String>> headerMap) {
        int rowIndex = 0;
        for (Map.Entry<String, LinkedHashMap<String, String>> outerEntry : headerMap.entrySet()) {
            LinkedHashMap<String, String> innerMap = outerEntry.getValue();
            int columnIndex = 0;

            for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
                objExcelConnector().writeIntoExcel(filePath,sheetName, innerEntry.getKey(),
                        rowIndex,columnIndex);
                columnIndex +=1;
            }
            break;
        }
    }

    /**
     * Added output result to Excel file
     */
    protected void addRowColumnValueIntoExcelFile(String filePath,String sheetName,LinkedHashMap<String,
            LinkedHashMap<String, String>> resultMap) {
        int rowIndex = 1;
        for (Map.Entry<String, LinkedHashMap<String, String>> outerEntry : resultMap.entrySet()) {
            LinkedHashMap<String, String> innerMap = outerEntry.getValue();
            int columnIndex = 0;

            for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
                objExcelConnector().writeIntoExcel(filePath,sheetName, innerEntry.getValue(),
                        rowIndex,columnIndex);
                columnIndex +=1;
            }
            rowIndex += 1;
        }
    }

    /**
     * Send Execution Report Excel file to Email
     */
    protected void sendResultFileToEmail(String filePath) throws IOException {
        String to = "";
        String subject = "";
        String bodyContent = "";

        //Send file path
        File tempFile = new File(filePath);

        objEmailConnector().sendEmailWithAttachment(to,subject,bodyContent,tempFile);
    }
}
