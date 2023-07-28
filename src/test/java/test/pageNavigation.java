package test;

import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import io.qameta.allure.Step;
import org.junit.rules.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.runners.Parameterized.*;

@Epic("Navigation Test Epic")
@Feature("Main page navigation")
public class pageNavigation {
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
    @Story("Main menu: functionality")
    @Description("Try to move through all items in the main menu")
    void shouldNavigateThoughAllMenu() {
        ArrayList<String> address = new ArrayList<String>();
        page = context.newPage();
        page.navigate("https://smart-lab.ru/");
        List<ElementHandle> selectedA = page.querySelectorAll("div .menu_column ul li");
        for (int i = 0; i < 3; i++) {
            String[] add = selectedA.get(i).innerHTML().substring(9).split("\"");
            address.add(add[0]);
        }

        for (String element:address){
            //address.add(element.innerHTML().substring(9).split("\""));
            //APIResponse response = page.request().get("https://smart-lab.ru" + address[0]);
            try {
                System.out.println(element);
                page.navigate("https://smart-lab.ru" + element);
                page.goBack();
                //assertEquals(response.statusText(), "OK");
            }
            catch (Exception e){
                System.out.println("Ошибка: " + element);
                //continue;
            }
            //System.out.println(address[0]);
            //element.click();
        }
    }

    @Test
    @Story("Graphs: functionality")
    @Description("Wait sec for highcharts container")
    @DisplayName("Parameterized Test from Resource")
    @ParameterizedTest(name = "{index} Test 'Wait for some seconds for graph'")
    @ValueSource(ints = {10, 50, 500, 1000})
     void shouldWaitForGraph(int number) {
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'Wait for " + number +" seconds for graph"));
        page = context.newPage();
        page.navigate("https://smart-lab.ru/q/shares_fundamental/");
        page.setDefaultTimeout(number);
        //try {
            page.waitForSelector(".highcharts-container");
            page.querySelector(".highcharts-credits").click();
        //}
        //catch(Exception e){
        //    System.out.println("Element with selector 'highcharts-container' is not found.");
        //}
        }

    @Test
    @Story("Navigation: functionality")
    @Description("Negative: Catch errors with navigating")
    void shouldHandleNavigationErros (){
        page = context.newPage();
        try {
            page.navigate("https://smart-lub.ru/q/bitus/");
        }
        catch (Exception e){
            io.qameta.allure.Allure.step("Test упал с ошибкой навигации до страницы" + page.url());
            //System.out.println(e);
            io.qameta.allure.Allure.step("Полный текст ошибки: \r\n" + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("./screenshots/navigation_error.png"))
                    .setFullPage(false));
        }


    }

}
