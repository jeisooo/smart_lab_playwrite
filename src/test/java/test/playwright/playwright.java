package test.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
//import com.microsoft.playwright.;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class playwright {
    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    public static Browser initializePlaywright(String browserType, boolean headless){
        playwright = Playwright.create();
        switch (browserType){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                return browser;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                return browser;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                return browser;
        }
        return browser;
    }
    public static BrowserContext InitialContext(Browser browser){
        BrowserContext context = browser.newContext();
        return context;
    }
    public static Page openNewTab(String browserType, boolean headless){
        Browser browser = initializePlaywright(browserType,headless);
        BrowserContext context = InitialContext(browser);
        Page page = context.newPage();
        return page;
    }
}