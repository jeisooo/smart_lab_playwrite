package test.pages;
import test.config.ConfigReader;

public class mainPage extends basePage{
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
}
