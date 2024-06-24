package org.example.demotestng.pages;

import com.microsoft.playwright.*;
import org.example.testrail.APIException;
import org.example.testrail.TestRailManager;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class BasePage {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected  Page page;
    protected String testCaseId;

    @BeforeTest
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterTest
    public void closeBrowser() {
        playwright.close();
    }

    @AfterMethod
    public void addResultToTestRail(ITestResult iTestResult) throws APIException, IOException {
        if(iTestResult.getStatus() == ITestResult.SUCCESS) {
            TestRailManager.addResultForTestCase
                    (testCaseId,TestRailManager.TEST_RAIL_PASS);
        }

        else if(iTestResult.getStatus() == ITestResult.FAILURE) {
            TestRailManager.addResultForTestCase
                    (testCaseId, TestRailManager.TEST_RAIL_FAIL);
        }
        context.close();
    }

}
