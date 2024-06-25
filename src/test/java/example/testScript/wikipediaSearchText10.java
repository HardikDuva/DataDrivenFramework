package example.testScript;

import example.pages.BasePage;
import example.pages.wikipedia.SearchPage;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class wikipediaSearchText10 extends BasePage {

    @Test
    void shouldSearchResult() {
        SearchPage searchPage = new SearchPage(page);
        searchPage.navigate();
        searchPage.search("php");
        assertThat(page).hasTitle(Pattern.compile("PHP"));
    }
}
