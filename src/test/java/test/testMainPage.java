package test;
import com.microsoft.playwright.ElementHandle;
import io.qameta.allure.*;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.config.ConfigReader;
import test.pages.mainPage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static test.config.ConfigReader.jsonObject;

@Epic("Browser Manipulation Test Epic")
@Feature("Main page tests")
public class testMainPage {
   public static mainPage firstPage;

   @BeforeAll
   static void getPage(){
       firstPage = new mainPage();
       ConfigReader reader = new ConfigReader();
       //String [] jsonArray = reader.getProperty("browsers");
      // List<browsers> browser = Arrays.stream(arr).map(browsers::valueOf).collect(Collectors.toList());
   }

    @Test
    @Story("Main page: authorize")
    @Description("Authorize with firefox")
   // @ParameterizedTest
    ///@EnumSource(arr)
    void testAuthorization(){
        firstPage.authorize();
    }

    @Test
    @Story("Main page: search field functional")
    @Description("search for a text inside financial vocabulary")
        // @ParameterizedTest
        ///@EnumSource(arr)
    void test_search_in_vocabulary(){
       firstPage.find_by_text_vocab("гэп");
    }
    @Test
    @Story("Main page: search field functional")
    @Description("search for a text inside book shield")
        // @ParameterizedTest
        ///@EnumSource(arr)
    void test_search_in_books(){
        firstPage.find_by_text_vocab("Манифест Инвестора");
    }
    @Test
    @Story("Main page: search field functional")
    @Description("search for blog")
        // @ParameterizedTest
        ///@EnumSource(arr)
    void test_search_with_options(){
            firstPage.search_with_options("jeisooo","book");
    }
    @Test
    @Story("Main page: main logo functional")
    @Description("Click to main logo")
    void test_main_logo_click(){
       firstPage.get_main_logo().click();
    }

    @Test
    @Story("Main page: BBR frames functional")
    @Description("Useful records: Check stars and counter")
    void test_bbr_record_frame(){
        List<ElementHandle> tabs = new ArrayList<>();
        List<ElementHandle> stars = new ArrayList<>();
        tabs = firstPage.get_bbrs_elements(".tab-frame .trt");
        stars = firstPage.get_bbrs_elements(".r");

        System.out.println(stars.get(0).innerText());
        assertEquals("★",stars.get(0).innerText().substring(0,1));
        Integer val = Integer.parseInt(stars.get(0).innerText().substring(1));
        assertEquals(true,val!=null?true:false );
    }

    @Test
    @Story("Main page: BBR frames functional")
    @Description("Top24 tab: Check counter and ")
    public void lambda_worker_test(){

    }
    @Test
    @Story("Main page: BBR frames functional")
    @Description("Useful records: Check stars and counter")
    public void ternarn_operation_test(){
      // firstPage.get_bbrs_elements();

    }



}
