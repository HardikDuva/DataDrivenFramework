package org.example.demotestng.testScript;

import org.example.demotestng.pages.BasePage;
import org.example.demotestng.pages.flipkart.HomePage;
import org.testng.annotations.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class cartButtonClick extends BasePage {

    @Test
    void shouldClickButton() {
        testCaseId = "4";
        HomePage homePage = new HomePage(page);
        homePage.navigate();
        homePage.clickOnCartButton();
        assertThat(page).hasTitle(Pattern.compile("Shopping Cart"));
    }


}
