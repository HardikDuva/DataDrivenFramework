package org.testScript;

import org.dataProvider.LoginTestDataProvider;
import org.configuration.BaseTest;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.*;

public class checkLogin extends BaseTest {

    String outputExcelResultFileName = "";
    String outputExcelResultSheetName = "";

    private LinkedHashMap<String, LinkedHashMap<String, String>> executionAllClientResultMap = new LinkedHashMap<>();

    @Test(dataProvider="GetExcelData", dataProviderClass = LoginTestDataProvider.class)
    public void Login(String project,String projectURL,String username,String password,String expectedResult)  {
        LinkedHashMap<String, String> executionEachClientResultMap = new LinkedHashMap<>();

        //If output is different from expected then store actual result
        String actualResult = "";

        //Store test case execution status - Pass / Fail / Not Executed
        String testCaseStatus = "";

        try {
            createWebdriver();

            //Write test case
        }

        catch (Exception e) {
            System.out.println("Error got " + e.getMessage());
        }

        finally {
            //Store all the parameter value for particular client
            executionEachClientResultMap.put("Project", project);
            executionEachClientResultMap.put("URL", projectURL);
            executionEachClientResultMap.put("Username", username);
            executionEachClientResultMap.put("Password", password);
            executionEachClientResultMap.put("Status", testCaseStatus);
            executionEachClientResultMap.put("ExpectedResult", expectedResult);
            executionEachClientResultMap.put("ActualResult", actualResult);
            executionAllClientResultMap.put(project, executionEachClientResultMap);
            closeBrowser();
        }
    }

    @AfterTest
    public void appendResultToExcelFile() throws IOException {
        String filePath = createExcelSheetOutputResultFile("CheckLogin", outputExcelResultFileName,outputExcelResultSheetName,outputDirPath);
        createExcelSheetHeaderRowValue(filePath,outputExcelResultSheetName,executionAllClientResultMap);
        addRowColumnValueIntoExcelFile(filePath,outputExcelResultSheetName,executionAllClientResultMap);
        sendResultFileToEmail(filePath);
    }


}

