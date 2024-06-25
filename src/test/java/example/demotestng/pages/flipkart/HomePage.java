package example.demotestng.pages.flipkart;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * @author myname
 */
public class HomePage {
    private final Page page;
    private final Locator cartButton;

    public HomePage(Page page) {
        this.page = page;
        this.cartButton = page.locator("//a[@title='Cart'][1]");
    }

    public void navigate() {
        page.navigate("https://www.flipkart.com/");
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

}


