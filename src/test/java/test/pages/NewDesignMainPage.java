package test.pages;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import test.config.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class NewDesignMainPage extends basePage{
    private static String pageUrl;
    private static String newPageUrl;
    private static ElementHandle element;
    private static String logoSelector = ".h1";
    private static String underMenuSelector = ".undermenu_more_main";
    private static String bbrFrameSelector = ".bbr";
    private static String mainMenuSelector = ".header__menu";
    //private static String url = mainPage.page.url();

    private static final String SEARCH_FIELD_SELECTOR = ".commandline";
    public NewDesignMainPage(){
        newPageUrl = this.getUrl("newPageUrl");
        pageUrl = this.getUrl("oldPageUrl");
        NewDesignMainPage.openLink(newPageUrl);
    }
    public void authorize(){
        ConfigReader reader = new ConfigReader();

        NewDesignMainPage.openLink(this.getUrl("oldPageUrl"));
        page.getByAltText("Гость").click();
        page.click("text=Вход на сайт");
        page.click("[placeholder=\"Логин или Email\"]");
        page.fill("[placeholder=\"Логин или Email\"]", reader.getProperty("login"));
        page.click("[placeholder=\"Пароль\"]");
        page.fill("[placeholder=\"Пароль\"]", reader.getProperty("password"));
        page.click("text=Войти");
    }

    public ElementHandle get_search_field(){
        NewDesignMainPage.openLink("https://smart-lab.ru/");
        return page.querySelector(".commandline input[type='text']");
    }

    public ElementHandle get_main_logo(){
        //mainPage.openLink("https://smart-lab.ru/");
        ElementHandle logo = page.querySelector(logoSelector);
        return logo;
    }
    public ElementHandle get_under_menu(){
        ElementHandle menu = page.querySelector(underMenuSelector);
        return menu;
    }
    public void goBack(){
        page.goBack();
    };
    public void navigate(String string){
        page.navigate(string);
    } //TODO deprecate this method. Jeisooo

    public ElementHandle get_bbr_frames(){
        ElementHandle bbr_frame = page.querySelector(bbrFrameSelector);
        return bbr_frame;
    }
    public List<ElementHandle> get_bbrs_elements(String selector){
        List<ElementHandle> elements = new ArrayList<>();
        ElementHandle bbr_element = get_bbr_frames();
        elements = bbr_element.querySelectorAll(selector);
        return elements;
    }
    public ElementHandle get_main_menu(){
        ElementHandle menu = page.querySelector(mainMenuSelector);
        return menu;
    }

    public String getUrl(String type){
        ConfigReader reader = new ConfigReader();
        return reader.getProperty(type);
    }
    public Locator getLocator(String string){
        return page.locator(string);
    }
    /*public String getCode(){
        return response.statusText();
    };
    public void setResponse(Response answer){
        response = answer;
    }*/
    public void contextClose(){
        basePage.PageClose();
    }

    public String url(){
        return page.url();
    }

    public void pageClose(){page.close(); }

}
