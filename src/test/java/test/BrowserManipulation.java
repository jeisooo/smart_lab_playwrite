package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import org.junit.jupiter.api.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Browser Manipulation Test Epic")
@Feature("Cross-browser tests: Open main page")
public class BrowserManipulation {
    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {

    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    @Story("Main page: open with all browsers")
    @Description("Open mane page with all browsers")
    void openCloseDiffBrowser() {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://smart-lab.ru/mobile");
        browser.close();

        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://smart-lab.ru/mobile");
        browser.close();

        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://smart-lab.ru/mobile");
        browser.close();
    }

    @Test
    @Story("Get browser Prompts")
    @Description("Test playwright function for handle pop up dialogs")
    void handleBrowserPromptsAndConfirmations() {
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("http://autopract.com/selenium/alert5/");

        page.onceDialog(dialog -> {
            dialog.accept();
        });
        page.locator("#alert-button").click();
        //page.onDialog

        page.onceDialog(dialog -> {
            dialog.dismiss();
        });
        page.locator("#confirm-button").click();

        page.onceDialog(dialog -> {
            dialog.accept("20");
        });
        page.locator("#prompt-button").click();
    }

    @Test
    @Story("Main page: get and set cookies")
    @Description("get cookies from new version of site and then clear them")
    void cookiesSetClearTest() {
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://smart-lab.ru/mobile");

        List<Cookie> cookies;
        cookies = page.context().cookies();

        for (Cookie cookie:cookies){
            System.out.println(cookie.name + ":" + cookie.value);
            //System.out.println(cookie.value);
        }
        context.clearCookies();
        assertTrue(page.context().cookies().isEmpty());
    }


}
