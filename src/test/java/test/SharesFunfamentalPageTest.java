package test;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.TimeoutError;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.pages.SharesFunfamentalPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Epic("Develop fundamental analise page Epic")
@Feature(" fundamental analise page tests: Filter")
public class SharesFunfamentalPageTest {
    public static SharesFunfamentalPage FundamentalPage;

    //@BeforeAll


    @BeforeEach
    public void createPage(){
        FundamentalPage = new SharesFunfamentalPage();
    }
    @AfterEach
    public void closePage(){
        FundamentalPage.pageClose();
    }

    @Test
    @Story("test open filter")
    @Description("Open filter to filter companies")
    public void ShouldOpenFilter(){
        try {
            FundamentalPage.OpenFilterShares();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Story("test Graphs: functionality")
    @Description("Wait 1 sec for highcharts container")
    //@DisplayName("Parameterized Test from Resource")
    //@ParameterizedTest(name = "{index} Test 'Wait for some seconds for graph'")
    //@ValueSource(ints = {10, 50, 500, 1000})
    void shouldWaitForGraph1000ms() {
        Integer number = 1000;
        FundamentalPage.SetDefaulTimeout(number);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'Wait for " + number +" seconds for graph"));
        //page.SetDefaulTimeout(number);
        ElementHandle graph = FundamentalPage.GetGraphs(number).get(0);
        //Locator link = graph.getByAltText("smart-lab.ru");
        //page.HighchartsCredits().evaluate("()=> $document.")
        ElementHandle link = FundamentalPage.getHighchartsCredits(graph).get(0);
        link.click();
        //Response response = FundamentalPage.waitForResponce();
        assertEquals("https://smart-lab.ru/",FundamentalPage.url());
        }
    @Test
    @Story("test Graphs: functionality")
    @Description("Wait 0,5 for highcharts container")
        //@DisplayName("Parameterized Test from Resource")
        //@ParameterizedTest(name = "{index} Test 'Wait for some seconds for graph'")
        //@ValueSource(ints = {10, 50, 500, 1000})
    void cantWaitForGraph200msNegativeTest() {
        Integer number = 200;
        FundamentalPage.SetDefaulTimeout(number);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'Wait for " + number +" seconds for graph"));
        //page.SetDefaulTimeout(number);
        //ElementHandle graph = FundamentalPage.GetGraphs(number).get(0);
        //Locator link = graph.getByAltText("smart-lab.ru");
        //page.HighchartsCredits().evaluate("()=> $document.")
       // ElementHandle link = FundamentalPage.getHighchartsCredits(graph).get(0);
        //link.click();
        //Response response = FundamentalPage.waitForResponce();

        TimeoutError trying =  assertThrows(TimeoutError.class, () -> {ElementHandle graph = FundamentalPage.GetGraphs(number).get(0);});


        //assertEquals("https://smart-lab.ru/",FundamentalPage.url());
    }

    @Test
    @Story("test Graphs: functionality")
    @Description("Wait 0,1 for highcharts container")
        //@DisplayName("Parameterized Test from Resource")
        //@ParameterizedTest(name = "{index} Test 'Wait for some seconds for graph'")
        //@ValueSource(ints = {10, 50, 500, 1000})
    void cantWaitForGraph100msNegativeTest() {
        Integer number = 100;
        FundamentalPage.SetDefaulTimeout(number);
        io.qameta.allure.Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Test 'Wait for " + number +" seconds for graph"));
        //page.SetDefaulTimeout(number);
        //ElementHandle graph = FundamentalPage.GetGraphs(number).get(0);
        //Locator link = graph.getByAltText("smart-lab.ru");
        //page.HighchartsCredits().evaluate("()=> $document.")
        //ElementHandle link = FundamentalPage.getHighchartsCredits(graph).get(0);
        //link.click();

        assertThrows(TimeoutError.class, () -> {ElementHandle graph = FundamentalPage.GetGraphs(number).get(0);});
        //Response response = FundamentalPage.waitForResponce();
        //assertEquals("https://smart-lab.ru/",FundamentalPage.url());
    }
}
