package example.demotestng.testScript;

import example.demotestng.pages.BasePage;
import example.demotestng.pages.wikipedia.SearchPage;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class wikipediaSearchText9 extends BasePage {

    @Test
    void shouldSearchResult() {
        SearchPage searchPage = new SearchPage(page);
        searchPage.navigate();
        searchPage.search("api");
        assertThat(page).hasTitle(Pattern.compile("API"));
    }
}
