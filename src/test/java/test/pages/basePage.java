package test.pages;
import com.microsoft.playwright.*;
import test.playwright.playwright;

import javax.swing.text.AbstractDocument;

import static test.playwright.playwright.InitialContext;
import static test.playwright.playwright.initializePlaywright;


public class basePage {
    String link;

    ///public playwright browser;
    public static Page page;
    public static Browser browser;
    private static BrowserContext context;
    //private static Response response;
    public static Page openLink(String link){
        browser = initializePlaywright("firefox", false);
        context = InitialContext(browser);
        page = context.newPage();
        //page = playwright.openNewTab("firefox", false);
        page.navigate(link);
        //Response response = page.waitForResponse(link,(response) -> {response.status() == 200});
        return page;
    }

    //public static void closeTab(Page page){
        //playwright.closeTab(page);
    //}

    public void Navigate(String url){page.navigate(url);}
    public static void PageClose(){page.close();}
    public static void waitForTimeout(Integer time){page.waitForTimeout(time);}
    public static void newContext(){context.newPage();}


}
