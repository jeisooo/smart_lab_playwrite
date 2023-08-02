package test.Stories;

import com.microsoft.playwright.ElementHandle;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.config.ConfigReader;
import test.pages.mainPage;

import java.util.ArrayList;
import java.util.List;

public class GoThroughMainMenuStorie {
    public static mainPage firstPage;

    @BeforeAll
    static void getPage(){
        firstPage = new mainPage();
        ConfigReader reader = new ConfigReader();
        //String [] jsonArray = reader.getProperty("browsers");
        // List<browsers> browser = Arrays.stream(arr).map(browsers::valueOf).collect(Collectors.toList());
    }

    @Test
    @Story("Main page: main menu functional")
    @Description("Navigate through main menu")
    void should_work_all_elements_in_undermenu() {
        ArrayList<String> address = new ArrayList<String>();
        ElementHandle menu = firstPage.get_under_menu();
        List<ElementHandle> selectedA = menu.querySelectorAll("ul li");
        for (int i = 0; i < selectedA.size(); i++) {
            String[] add = selectedA.get(i).innerHTML().substring(9).split("\"");
            address.add(add[0]);
        }
        for (String element : address) {
            //address.add(element.innerHTML().substring(9).split("\""));
            //APIResponse response = page.request().get("https://smart-lab.ru" + address[0]);
            try {
                firstPage.navigate("https://smart-lab.ru" + element);
                firstPage.goBack();
                //assertEquals(response.statusText(), "OK");
            } catch (Exception e) {
                System.out.println("Ошибка: " + element);
                //continue;
            }
            //System.out.println(address[0]);
            //element.click();
        }
    }
}
