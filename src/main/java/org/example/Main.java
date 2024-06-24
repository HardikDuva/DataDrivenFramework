package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * @author myname
 */
public class Main {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();
            page.navigate("https://www.amazon.in/");
            // Expect a title "to contain" a substring.
            assertThat(page).hasTitle(Pattern.compile("Amazon"));

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
        }
    }
}