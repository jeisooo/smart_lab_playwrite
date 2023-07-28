package test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import test.pages.mainPage;



//import static test.config.ConfigReader.jsonObject;

@Epic("Browser Manipulation Test Epic")
@Feature("Main page tests: Authorization")
public class testMainPage {
    public static mainPage firstPage;

   @BeforeAll
   static void getPage(){
       firstPage = new mainPage();
       //ConfigReader reader = new ConfigReader();
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
