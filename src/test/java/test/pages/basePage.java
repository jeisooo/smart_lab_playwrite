package test.pages;
import com.microsoft.playwright.*;
import test.playwright.playwright;



public class basePage {
    String link;
    ///public playwright browser;
    public static Page page;
    public static Page openLink(String link){
        page = playwright.openNewTab("firefox", false);
        page.navigate(link);
        return page;
    }
}
