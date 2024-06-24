package org.example.junit;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author myname
 */
public class Test2 extends TestFixtures{

    @Test
    void shouldReturnInnerHTML() {
        page.setContent("<div>hello</div>");
        assertEquals("hello", page.innerHTML("css=div"));
    }

    @Test
    void shouldClickButton() {
        Page popup = page.waitForPopup(() -> {
            page.evaluate("window.open('about:blank');");
        });
        assertEquals("about:blank", popup.url());
    }
}
