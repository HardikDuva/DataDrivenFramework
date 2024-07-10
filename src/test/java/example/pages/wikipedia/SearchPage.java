package example.pages.wikipedia;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.*;

/**
 * @author myname
 */
public class SearchPage {
    private final Page page;
    private final Locator searchTermInput;

    public SearchPage(Page page) {
        this.page = page;
        this.searchTermInput = page.locator("#searchInput");
    }
    
    public void navigate() {
        
        page.navigate("https://www.wikipedia.org/");
    }

    public void search(String text) {
        searchTermInput.click();
        searchTermInput.fill(text);
        searchTermInput.press("Enter");
    }

}


