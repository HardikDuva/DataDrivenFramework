package org.example.demotestng.testScript;

import com.microsoft.playwright.*;
import org.example.demotestng.pages.wikipedia.SearchPage;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class TestExample {
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
        searchPage.search("testng");
        assertEquals(page.url(),"https://en.wikipedia.org/wiki/Testng");
    }

    @Test
    void shouldCheckTheBox() {
        SearchPage searchPage = new SearchPage(page);
        searchPage.navigate();
        searchPage.search("playwright");
        assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
    }

}
