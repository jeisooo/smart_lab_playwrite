package test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
import java.util.Arrays;
import java.util.stream.Collectors;

//import static test.config.ConfigReader.jsonObject;

@Epic("Browser Manipulation Test Epic")
@Feature("Main page tests: Authorization")
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

}
