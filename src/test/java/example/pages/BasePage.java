package example.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.ITestContext;
import org.testng.annotations.*;
import example.utilities.FrameworkConfig;

import static example.utilities.TestConstants.RUN_HEADLESS;

public class BasePage {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected  Page page;

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    @Parameters({"Browser"})
    public void launchBrowser(String brows) {
        FrameworkConfig.init(System.getProperty("user.dir")
                + "/dependencies/fw_config/fw_config.properties");
        playwright = Playwright.create();
        setBrowser(brows);
        context = browser.newContext();
        page = context.newPage();
    }

    @BeforeTest
    public void startReport() {
        // Initialize ExtentReports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Testing");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Hardik Duva");
    }

    @BeforeMethod
    public void setUpMethod(ITestContext iTestContext) {
        // This will start a new test in the report
        test = extent.createTest(iTestContext.getName());
    }

    @AfterMethod
    public void tearDownMethod() {
        // End the test
        extent.flush();
    }

    @AfterClass
    public void closeBrowser() {
        context.close();
        playwright.close();
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
        } else if (browserStr.toLowerCase().contains("safari")) {
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
