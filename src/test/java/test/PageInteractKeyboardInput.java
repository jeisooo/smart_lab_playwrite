package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageInteractKeyboardInput {
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
    @Story("Main page undermenu: check css style hover")
    @Description("check hover functionality on the button")
    @DisplayName("Test 'hover' on the undermenu buttons")
    void hoverButtonsUndermenuClass() {

        page = context.newPage();
        page.navigate("https://smart-lab.ru/");
        //List<ElementHandle> UndermenuArrayButton = page.querySelectorAll(".undermenu a");
        Locator locators = page.locator(".undermenu a"); //TODO hover and assert css color
        //for (locator:locators){
            //locators.hover();
            //String color = UndermenuArrayButton.get(i).getAttribute("background");
           // System.out.println(locators.textContent());
        //}

    }

    @Test
    void shouldHavePercentsOfDividends() {
        page = context.newPage();
        page.navigate("https://smart-lab.ru/dividends/");
        List<ElementHandle> data = page.querySelectorAll(".dividend_approved td");
        //List<ElementHandle> tdElements = row.$$("td");
        for (int i = 0; i <data.size(); i++){
            System.out.println(data.get(i).innerText());
        }
        assertEquals("2,7%",data.get(4).innerText());
    }

    @Test
    void shoulBeFortyOneSelected() {
        page = context.newPage();
        page.navigate("https://smart-lab.ru/dividends/");
        List<ElementHandle> data = page.querySelectorAll("#sector_id");
        String[] arr = data.get(0).innerText().split("\\n");
        assertEquals(41,arr.length);
        page.locator("p:has-text(\"Все сектора\") i").click();
        page.locator("li:has-text(\"ФИНАНСЫ\") i").click();
        page.locator("li:has-text(\"МЕТАЛЛУРГИЯ черн.\") i").click();
        page.locator("li:has-text(\"МЕТАЛЛУРГИЯ цвет.\") i").click();
        page.locator("li:has-text(\"МЕТАЛЛУРГИЯ разное\") i").click();
        page.locator("li:has-text(\"ДРАГ.МЕТАЛЛЫ\") i").click();
        List<ElementHandle> selectedUl = page.querySelectorAll("li.opt.selected");
        for (ElementHandle element:selectedUl){
            System.out.println(element.innerText());
        }
        //System.out.println("");
    }


}
