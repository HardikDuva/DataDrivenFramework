package example.pages;

import com.microsoft.playwright.*;

import org.testng.annotations.*;
import example.utilities.FrameworkConfig;

import static example.utilities.TestConstants.RUN_HEADLESS;

public class BasePage {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected  Page page;

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
