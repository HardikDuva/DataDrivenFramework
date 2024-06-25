package example.demotestng.pages;

import com.microsoft.playwright.*;
import example.testrail.APIException;
import example.testrail.TestRailManager;
import org.testng.ITestResult;
import org.testng.annotations.*;
import example.utilities.FrameworkConfig;
import java.io.IOException;

import static example.utilities.TestConstants.RUN_HEADLESS;
import static example.utilities.TestConstants.TEST_RESULT_ADD_TESTRAIL;

public class BasePage {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected  Page page;
    protected String testCaseId;

    @BeforeTest
    @Parameters({"Browser"})
    public void launchBrowser(String brows) {
        FrameworkConfig.init(System.getProperty("user.dir")
                + "/dependencies/fw_config/fw_config.properties");
        playwright = Playwright.create();
        setBrowser(brows);
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterTest
    public void closeBrowser() {
        playwright.close();
    }

    @AfterMethod
    public void addResultToTestRail(ITestResult iTestResult) throws APIException, IOException {
        if(!TEST_RESULT_ADD_TESTRAIL) {
            if(iTestResult.getStatus() == ITestResult.SUCCESS) {
                TestRailManager.addResultForTestCase
                        (testCaseId,TestRailManager.TEST_RAIL_PASS);
            }

            else if(iTestResult.getStatus() == ITestResult.FAILURE) {
                TestRailManager.addResultForTestCase
                        (testCaseId, TestRailManager.TEST_RAIL_FAIL);
            }
        }

        context.close();
    }

    public void setBrowser(String browserStr) {
        if (browserStr.toLowerCase().contains("chrome")) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless
                    (RUN_HEADLESS));
        } else if (browserStr.toLowerCase().contains("firefox")) {
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions().setHeadless
                    (RUN_HEADLESS));
        } else if (browserStr.contains("safari")) {
            browser = playwright.webkit().launch(
                    new BrowserType.LaunchOptions().setHeadless
                    (RUN_HEADLESS));
        }
        else {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless
                    (RUN_HEADLESS));
        }

    }
}
