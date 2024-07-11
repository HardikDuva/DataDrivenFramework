package testScript;

import com.configuration.BaseTest;
import com.dataProvider.LoginTestDataProvider;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.*;

public class checkLogin extends BaseTest {

    String outputExcelResultFileName = "SauceLabResult";
    String outputExcelResultSheetName = "LoginResult";
    String functionality = "LoginCheck";

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

            ObLogInPage().goToApp(projectURL);
            ObLogInPage().enterUsername(username)
                    .enterPassword(password)
                    .clickOnLoginInButton();

            boolean isPass = false;
            if(!ObLogInPage().isUserLoggedIn()) {
                if(ObLogInPage().isErrorMessageDisplayed()) {
                    String errMsg = ObLogInPage().getDisplayedErrorMessage();
                    if(errMsg.contains(expectedResult)) {
                        testCaseStatus = "Pass";
                        isPass = true;
                    }

                    else {
                        actualResult = errMsg;
                    }
                }
                else {
                    actualResult = "Error Message is not displayed";
                }

                if (!isPass) {
                    testCaseStatus = "Failed";
                    ObLogInPage().captureFailedScreenShot("LoginFailed");
                    Assert.fail(actualResult);
                }
            }

            else {
                testCaseStatus = "Pass";
            }

            closeBrowser();
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
            executionAllClientResultMap.put(project + username, executionEachClientResultMap);
            closeBrowser();
        }
    }

    @AfterTest
    public void appendResultToExcelFile() throws IOException {
        String filePath = createExcelSheetOutputResultFile(functionality,
                outputExcelResultFileName,outputExcelResultSheetName,outputDirPath);
        createExcelSheetHeaderRowValue(filePath,outputExcelResultSheetName,executionAllClientResultMap);
        addRowColumnValueIntoExcelFile(filePath,outputExcelResultSheetName,executionAllClientResultMap);
        //sendResultFileToEmail(filePath);
    }


}

