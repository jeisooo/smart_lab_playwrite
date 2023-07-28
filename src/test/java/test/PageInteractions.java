package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageInteractions {
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
    void IteractWithFormsAndElements() {

        page = context.newPage();
        page.navigate("https://smart-lab.ru/dividends/");
        page.locator("p:has-text(\"Все сектора\") i").click();
        page.locator("li:has-text(\"НЕФТЕГАЗ\") i").click();
        page.locator("#dividends_filter li:has-text(\"БАНКИ\") i").click();
        page.locator("li:has-text(\"ФИНАНСЫ\") i").click();
        page.locator("li:has-text(\"МЕТАЛЛУРГИЯ черн.\") i").click();
        page.locator("li:has-text(\"МЕТАЛЛУРГИЯ цвет.\") i").click();
        page.locator("li:has-text(\"МЕТАЛЛУРГИЯ разное\") i").click();
        page.locator("li:has-text(\"ДРАГ.МЕТАЛЛЫ\") i").click();
        page.locator("li:has-text(\"ГОРНОДОБЫВАЮЩИЕ\") span").click();
        page.locator("li:has-text(\"ХИМИЯ удобрения\") i").click();
        page.locator("li:has-text(\"ХИМИЯ разное\") i").click();
        page.locator("li:has-text(\"Э/ГЕНЕРАЦИЯ\") i").click();
        page.locator("li:has-text(\"ЭЛЕКТРОСЕТИ\") i").click();
        page.locator("li:has-text(\"ЭНЕРГОСБЫТ\") i").click();
        page.locator("p:has-text(\"13 Selected\") i").click();
        page.getByText("13 Selected").click();
        page.getByPlaceholder("Год").click();
        page.getByText("2027").click();
        page.getByPlaceholder("Год").click();
        page.getByText("утвержденные дивиденды").click();
        page.getByText("убрать пустые даты").click();
        page.locator("select[name=\"quarter\"]").selectOption("Y");
        page.locator("select[name=\"quarter\"]").selectOption("");
        page.locator("select[name=\"quarter\"]").selectOption("1");
        page.locator("select[name=\"quarter\"]").selectOption("Y");
        page.getByRole(AriaRole.valueOf("BUTTON"), new Page.GetByRoleOptions().setName("Выбрать")).click();
        //page.
        assertEquals(page.url(),"https://smart-lab.ru/dividends/?sector_id%5B%5D=1&sector_id%5B%5D=2&sector_id%5B%5D=14&sector_id%5B%5D=3&sector_id%5B%5D=21&sector_id%5B%5D=22&sector_id%5B%5D=23&sector_id%5B%5D=18&sector_id%5B%5D=17&sector_id%5B%5D=24&sector_id%5B%5D=4&sector_id%5B%5D=19&sector_id%5B%5D=20&is_approved=1&year=2027&quarter=Y&with_dates=1");
        //page.getByRole(AriaRole.valueOf("cell"), new Page.GetByRoleOptions().setName("МТС-ао")).click();
        //page.getByRole("cell", new Page.GetByRoleOptions().setName("345,95")).click();

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
