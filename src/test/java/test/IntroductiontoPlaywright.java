package test;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class IntroductiontoPlaywright {
    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void takeScreenOfMainPage() {
        page.navigate("https://smart-lab.ru/mobile");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./screenshots/screenshot.png"))
                .setFullPage(false));
    }

    @Test
    void NavigateFormAndSubmit() {
        page.navigate("https://smart-lab.ru/mobile");
        // Click img[alt="Гость"]
        page.click("img[alt=\"Гость\"]");
        // Click text=Вход на сайт
        page.click("text=Вход на сайт");
        // assert page.url().equals("https://smart-lab.ru/mobile/login/");
        // Click [placeholder="Логин или Email"]
        page.click("[placeholder=\"Логин или Email\"]");
        // Fill [placeholder="Логин или Email"]
        page.fill("[placeholder=\"Логин или Email\"]", "jeisooo");
        // Click [placeholder="Пароль"]
        //page.click("[placeholder=\"Пароль\"]");
        // Fill [placeholder="Пароль"]
        page.fill("[placeholder=\"Пароль\"]", "jdbgkjrjc");
        // Click text=Войти
        page.click("text=Войти");
        // assert page.url().equals("https://smart-lab.ru/mobile/");
    }

    @Test
    void extractTextAndAssertValue() {
        page.navigate("https://smart-lab.ru/mobile");
        String locator = page.locator("span:has-text(\"Новости\")").textContent();
        System.out.println(locator);
        assertEquals (locator,"Новости");
    }

}
