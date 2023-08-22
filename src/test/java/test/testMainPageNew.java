package test;
import com.microsoft.playwright.ElementHandle;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.config.ConfigReader;
import test.pages.NewDesignMainPage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Epic("Develop new design main page Epic")
@Feature("New main page tests: Main menu")
public class testMainPageNew {
    public static NewDesignMainPage firstPage;
    private static String menucount;
    private static Stream<Integer> generateRangeFeeds() {return IntStream.range(0, 8).boxed();
    }
    private static Stream<Integer> generateRangeForums() { return IntStream.range(0, 9).boxed();}
    private static Stream<Integer> generateRangeRates() {
        return IntStream.range(0, 8).boxed();
    }
    private static Stream<Integer> generateRangeShares() {
        return IntStream.range(0, 5).boxed();
    }


    @BeforeAll
    static void getPage(){
        firstPage = new NewDesignMainPage();
        ConfigReader reader = new ConfigReader();
        //String [] jsonArray = reader.getProperty("browsers");
        // List<browsers> browser = Arrays.stream(arr).map(browsers::valueOf).collect(Collectors.toList());
    }
    @AfterAll
    static void closePage(){
        firstPage.pageClose();
    }
   /* @BeforeEach
    void newContext(){
        firstPage.newContext();
    }
*/

/*
    @Test
    @Story("Main page: open main menu with button")
    @Description("Open new menu")

    void shouldOpenMainMenu(){
        ElementHandle menu = firstPage.get_main_menu();
        ElementHandle button = menu.querySelector("#main-menu-btn");
        button.click();
    }*/
    /*@AfterEach
    public void closeOnePage(){
        firstPage.contextClose();
    }*/


    /*@Test
    @Story("Main page: go through main menu: all blogs")
    @Description("go through main menu to all blogs")
    void shouldGoToAllBlogsFeed(){
        List<ElementHandle> premium_feed_link = new ArrayList<>();
        ElementHandle menu = firstPage.get_main_menu();
        premium_feed_link = menu.querySelectorAll("li.menu__item.menu__item--feeds .menu__sub-item");
        String[] href = premium_feed_link.get(0).innerHTML().substring(9).split("\"");
        firstPage.navigate(firstPage.getUrl("newPageUrl")+href[0].substring(7));
        //assertEquals("200",firstPage.getCode());
        firstPage.goBack();
    }*/

    @Story("Main page: go through main menu: all blogs")
    @Description("go through main menu to all blogs\feeds")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("generateRangeFeeds")
    void shouldGoToFeeds(Integer value){
        ElementHandle menu = firstPage.get_main_menu();
        String expression = "() => document.querySelectorAll('.menu__item--feeds a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = (String)menu.evaluate(expression);
        firstPage.Navigate(firstPage.getUrl("newPageUrl")+href.substring(7));
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href.substring(7) +" page"));
        firstPage.goBack();
    }


    @Story("Main page: go through main menu: all forums")
    @Description("go through main menu to all forums")
    @ParameterizedTest()
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9})
    void shouldGoToForums(Integer value){
        firstPage.Navigate(firstPage.getUrl("newPageUrl"));
        ElementHandle menu = firstPage.get_main_menu();
        //String expression = "() => document.querySelectorAll('.menu__item--forum a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = menu.querySelectorAll(".menu__item--forum a").get(value).getAttribute("href"); //(String)menu.evaluate(expression);
        String fullUrl = firstPage.getUrl("oldPageUrl")+href;
        firstPage.Navigate(fullUrl);
        //firstPage.waitForTimeout(4000);
        //firstPage.waitForURL(fullUrl);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href +" page"));
        System.out.println(href);
        //firstPage.Navigate(firstPage.getUrl("newPageUrl"));
    }


    @Story("Main page: go through main menu: all rates")
    @Description("go through main menu to all rates")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("generateRangeRates")
    void shouldGoToRates(Integer value){
        List<ElementHandle> premium_feed_link = new ArrayList<>();
        ElementHandle menu = firstPage.get_main_menu();
        //String expression = "() => document.querySelectorAll('.menu__item--quotes a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = menu.querySelectorAll(".menu__item--quotes a").get(value).getAttribute("href");
        String fullUrl = firstPage.getUrl("oldPageUrl")+href;
        firstPage.Navigate(fullUrl);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href +" page"));
        System.out.println(href);
        //assertEquals(fullUrl,firstPage.url());
        firstPage.goBack();
    }


    @Story("Main page: go through main menu: all rates")
    @Description("go through main menu to all rates")
    @ParameterizedTest(name = "{index} - {0}")
    @ValueSource(ints = {0,1,2,3,4})
    void shouldGoToStocks(Integer value){
        ElementHandle menu = firstPage.get_main_menu();
        //String expression = "() => document.querySelectorAll('.menu__item--stocks a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = menu.querySelectorAll(".menu__item--stocks a").get(value).getAttribute("href");
        String fullUrl = firstPage.getUrl("oldPageUrl")+href;
        firstPage.Navigate(fullUrl);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href +" page"));
        System.out.println(href);
        firstPage.goBack();
    }

    @Story("Main page: go through main menu: combine")
    @Description("go through main menu to combine")
    @ParameterizedTest(name = "{index} - {0}")
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void shouldGoToUsers(Integer value){
        //List<ElementHandle> premium_feed_link = new ArrayList<>();
        ElementHandle menu = firstPage.get_main_menu();
        //String expression = "() => document.querySelectorAll('.menu__item--comb a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = menu.querySelectorAll(".menu__item--comb a").get(value).getAttribute("href");
        String fullUrl = firstPage.getUrl("oldPageUrl")+href;
        firstPage.Navigate(fullUrl);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href +" page"));
        System.out.println(href);
        //assertEquals(fullUrl,firstPage.url());
        firstPage.goBack();
    }



    /*@Test
    void testCode(){
        firstPage.navigate("https://ya.ru");
        System.out.println(firstPage.getCode());
        firstPage.getCode();

    }*/
    @Test
    @Story("Main page: check hovering")
    @Description("Check .css hovering")
    void shouldHoverMenuButons(){
        ElementHandle menu = firstPage.get_main_menu();
        menu.click();
        ElementHandle Button = menu.querySelectorAll(".menu__item--feeds a").get(1);
        Button.hover();
        String expression = "(element) => window.getComputedStyle(element).getPropertyValue('background-color')";
        Object color = Button.evaluate(expression);
        assertEquals(color,"rgb(245, 147, 0)");
        //System.out.println(color);
    }
}
