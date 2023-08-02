package test.pages;
import com.microsoft.playwright.ElementHandle;
import test.config.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class mainPage extends basePage{
    private static ElementHandle element;
    private static String logoSelector = ".h1";
    private static String underMenuSelector = ".undermenu_more_main";
    private static String bbrFrameSelector = ".bbr";

    private static final String SEARCH_FIELD_SELECTOR = ".commandline";
    public mainPage(){
        mainPage.openLink("https://smart-lab.ru/");
    }
    public void authorize(){
        ConfigReader reader = new ConfigReader();
        mainPage.openLink("https://smart-lab.ru/mobile");
        page.getByAltText("Гость").click();
        page.click("text=Вход на сайт");
        page.click("[placeholder=\"Логин или Email\"]");
        page.fill("[placeholder=\"Логин или Email\"]", reader.getProperty("login"));
        page.click("[placeholder=\"Пароль\"]");
        page.fill("[placeholder=\"Пароль\"]", reader.getProperty("password"));
        page.click("text=Войти");
    }

    public com.microsoft.playwright.ElementHandle get_search_field(){
        mainPage.openLink("https://smart-lab.ru/");
        return page.querySelector(".commandline input[type='text']");
    }
    public void find_by_text_vocab(String text){
        //mainPage.openLink("https://smart-lab.ru/");
        get_search_field();
        page.querySelector("[unique=FIND F]").click();
        page.querySelector(".commandline input[type='text']").fill("FIND F " + text);
        page.querySelector(".commandline input[type='text']").press("Enter");
    }
    public void find_by_text_book(String text){
        get_search_field();
        //page.querySelector(".hilited").click();
        page.querySelector(".commandline input[type='text']").fill("FIND B " + text);
        page.querySelector(".commandline input[type='text']").press("Enter");
    }
    public void find_by_text_people(String text){

    }
    public void find_by_text_blogs(String text){

    }
    public void find_by_text_people_writes(String text){

    }
    public void search_with_options(String text, String options){
        get_search_field();
        element = page.querySelector(".commandline input[type='text']");
        switch (options){
            case "text vocab":
                element.fill("FIND F " + text);
                element.press("Enter");
            case "text book":
                element.fill("FIND B " + text);
                element.press("Enter");
            case "login":
                element.fill("FIND @ " + text);
                element.press("Enter");
            case "text_login":
                element.fill(text+"@"+"jeisooo");
                element.press("Enter");
            case "blog":
                element.fill("BLOG @"+ text);
                element.press("Enter");
        }
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
}
