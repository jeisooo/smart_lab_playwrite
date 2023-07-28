package test.auth;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.List;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Authentication Epic")
@Feature("Main page authentication")
public class authClass {
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
        browser.close();
    }

    @Test
    @Story("Main menu: enter to account page")
    @Description("Enter to the account with logina/pass authentication ")
    void testAuth() {
        page.navigate("https://smart-lab.ru/mobile");
        page.getByAltText("Гость").click();
        page.click("text=Вход на сайт");
        // assert page.url().equals("http://smart-lab.ru/mobile/login/");
        // Click [placeholder="Логин или Email"]
        page.click("[placeholder=\"Логин или Email\"]");
        // Fill [placeholder="Логин или Email"]
        page.fill("[placeholder=\"Логин или Email\"]", "jeisooo");
        // Click [placeholder="Пароль"]
        page.click("[placeholder=\"Пароль\"]");
        // Click [placeholder="Пароль"]
        page.click("[placeholder=\"Пароль\"]");
        // Fill [placeholder="Пароль"]
        page.fill("[placeholder=\"Пароль\"]", "jdbgkjrjc");
        // Click text=Войти
        page.click("text=Войти");
        // assert page.url().equals("http://smart-lab.ru/mobile/");
        // Click img[alt="Sergey"]
        page.click("img[alt=\"Sergey\"]");
        // Click :nth-match(:text("Мой портфель"), 2)
        page.click(":nth-match(:text(\"Мой портфель\"), 2)");
        // assert page.url().equals("https://smart-lab.ru/q/portfolio/jeisooo/");
        List<Cookie> cookies;
        cookies = page.context().cookies();

        for (Cookie cookie:cookies){
            System.out.println(cookie.name + ":" + cookie.value);
            //System.out.println(cookie.value);
        }
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./screenshots/portfolio.png"))
                .setFullPage(false));
    }


}
