package test;
import com.microsoft.playwright.ElementHandle;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.config.ConfigReader;
import test.pages.NewDesignMainPage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Epic("Develop new design main page Epic")
@Feature("New main page tests: Main menu")
public class testMainPageNew {
    public static NewDesignMainPage firstPage;
    private static Stream<Integer> generateRange() {
        return IntStream.range(0, 10).boxed();
    }
    @BeforeAll
    static void getPage(){
        firstPage = new NewDesignMainPage();
        ConfigReader reader = new ConfigReader();
        //String [] jsonArray = reader.getProperty("browsers");
        // List<browsers> browser = Arrays.stream(arr).map(browsers::valueOf).collect(Collectors.toList());
    }
    @Test
    @Story("Main page: open main menu with button")
    @Description("Open new menu")

    void shouldOpenMainMenu(){
        ElementHandle menu = firstPage.get_main_menu();
        ElementHandle button = menu.querySelector("#main-menu-btn");
        button.click();
    }
    @Test
    @Story("Main page: go through main menu: all blogs")
    @Description("go through main menu to all blogs")
    void shouldGoToAllBlogsFeed(){
        List<ElementHandle> premium_feed_link = new ArrayList<>();
        ElementHandle menu = firstPage.get_main_menu();
        premium_feed_link = menu.querySelectorAll("li.menu__item.menu__item--feeds .menu__sub-item");
        String[] href = premium_feed_link.get(0).innerHTML().substring(9).split("\"");
        firstPage.navigate(firstPage.getUrl("newPageUrl")+href[0].substring(7));
        //assertEquals("200",firstPage.getCode());
    }
    @Test
    @Story("Main page: go through main menu: all blogs")
    @Description("go through main menu to all blogs")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("generateRange")
    void shouldGoToFeeds(Integer value){
        List<ElementHandle> premium_feed_link = new ArrayList<>();
        ElementHandle menu = firstPage.get_main_menu();
        String expression = "() => document.querySelectorAll('.menu__item--feeds a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = (String)menu.evaluate(expression);
        //premium_feed_link = menu.querySelectorAll("li.menu__item.menu__item--feeds .menu__sub-item");
        //String[] href = premium_feed_link.get(0).innerHTML().substring(9).split("\"");
        firstPage.navigate(firstPage.getUrl("newPageUrl")+href.substring(7));
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href.substring(7) +" page"));
        //System.out.println(href.substring(7));
        //assertEquals("200",firstPage.getCode());
    }

    @Test
    @Story("Main page: go through main menu: all forums")
    @Description("go through main menu to all forums")
    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("generateRange")
    void shouldGoToForums(Integer value){
        List<ElementHandle> premium_feed_link = new ArrayList<>();
        ElementHandle menu = firstPage.get_main_menu();
        String expression = "() => document.querySelectorAll('.menu__item--forum a')["+Integer.toString(value)+"].getAttribute('href')";
        String href = (String)menu.evaluate(expression);
        //premium_feed_link = menu.querySelectorAll("li.menu__item.menu__item--feeds .menu__sub-item");
        //String[] href = premium_feed_link.get(0).innerHTML().substring(9).split("\"");
        firstPage.navigate(firstPage.getUrl("oldPageUrl")+href);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'go to " + href +" page"));
        System.out.println(href.substring(7));
        //assertEquals("200",firstPage.getCode());
    }
}
