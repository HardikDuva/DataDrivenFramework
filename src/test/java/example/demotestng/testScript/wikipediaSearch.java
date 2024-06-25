package example.demotestng.testScript;

import example.demotestng.pages.BasePage;
import example.demotestng.pages.wikipedia.SearchPage;
import org.testng.annotations.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class wikipediaSearch extends BasePage {

    @Test
    void shouldSearchResult() {
        testCaseId = "3";
        SearchPage searchPage = new SearchPage(page);
        searchPage.navigate();
        searchPage.search("testng");
        assertThat(page).hasURL(Pattern.compile("https://en.wikipedia.org/wiki/Testng"));

    }
}
