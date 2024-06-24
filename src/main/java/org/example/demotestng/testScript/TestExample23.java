package org.example.demotestng.testScript;

import com.microsoft.playwright.*;
import org.example.demotestng.pages.amazon.SearchPage;
import org.testng.annotations.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestExample23 {
    // Shared between all tests in this class.
    Playwright playwright;
    Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    }

    @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    void closeContext() {
        context.close();
    }

    @Test
    void shouldClickButton() {
        SearchPage searchPage = new SearchPage(page);
        searchPage.navigate();
        searchPage.clickOnCartButton();
        assertThat(page).hasTitle(Pattern.compile("Shopping Cart"));

    }


}
