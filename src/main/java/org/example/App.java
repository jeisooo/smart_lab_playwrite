package org.example;

import com.microsoft.playwright.*;
import java.util.*;
import com.microsoft.playwright.Locator;
import java.util.regex.Pattern;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            // Open new page
            Page page = context.newPage();

            page.navigate("https://www.google.com/");
            // Click [aria-label="Գտնել"]
            page.click("[aria-label=\"Գտնել\"]");
            // Fill [aria-label="Գտնել"]
            page.fill("[aria-label=\"Գտնել\"]", "Hello");
            // Press Enter
            // page.waitForNavigation(new Page.WaitForNavigationOptions().setUrl("https://www.google.com/search?q=test&source=hp&ei=fAqsZPmUPJnAxc8Pt6i8iAk&iflsig=AD69kcEAAAAAZKwYjTr8pH8hw44jMluszyBLo6NMa7I8&ved=0ahUKEwj5wqaYooSAAxUZYPEDHTcUD5EQ4dUDCAk&uact=5&oq=test&gs_lcp=Cgdnd3Mtd2l6EAMyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgsIABCABBCxAxCDAToLCAAQigUQsQMQgwE6CAgAEIAEELEDUKgEWKMHYOoKaAFwAHgAgAF3iAHKA5IBAzAuNJgBAKABAbABAA&sclient=gws-wiz"), () ->
            page.waitForNavigation(() -> {
                page.press("[aria-label=\"Գտնել\"]", "Enter");
            });
            //assert page.url().equals("dddd");
        }
    }
}