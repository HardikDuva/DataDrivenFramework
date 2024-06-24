package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.awaitility.Awaitility;

import java.util.concurrent.TimeUnit;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * @author myname
 */
public class Learning {

    public static void main(String[] args) {
         try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().
            launch(new BrowserType.LaunchOptions().setHeadless(false));

            Page page = browser.newPage();
            page.navigate("https://www.flipkart.com/");
            assertThat(page.locator("//a[@title='Cart'][1]")).isVisible();
            page.locator("//a[@title='Cart'][1]").click();
            /*assertThat(page).hasTitle(Pattern.compile("Shopping Cart"));*/

            //page.locator("").selectOption("value");


            Awaitility.await()
                     .atMost(5, TimeUnit.SECONDS)
                     .until(() -> page.title().contains("Shopping Cart"));

            page.close();
        }

    }
}
