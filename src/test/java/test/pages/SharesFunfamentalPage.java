package test.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.List;

public class SharesFunfamentalPage extends basePage {
    private static String pageUrl = "https://smart-lab.ru/q/shares_fundamental/";
    private static String newPageUrl;
    private static String openFilterSelector = ".title_block__filter--btn";
    private static String underMenuSelector = ".undermenu_more_main";
    private static String bbrFrameSelector = ".bbr";
    private static String mainMenuSelector = ".header__menu";
    private static Response responce;
    //private static String url = mainPage.page.url();
    public SharesFunfamentalPage(){
        SharesFunfamentalPage.openLink(pageUrl);
    }

    public void OpenFilterShares(){
        page.querySelector(openFilterSelector).click();
    }

    public void SetDefaulTimeout(Integer value){
        page.setDefaultTimeout(value);
    }
    public ElementHandle WaitForSelector(String selector){
        ElementHandle element =  page.waitForSelector(selector);
        return element;
    }

    public List<ElementHandle> GetGraphs(Integer number){
        page.setDefaultTimeout(number);
        page.waitForSelector(".highcharts-container");
        List<ElementHandle> graph = page.querySelectorAll(".highcharts-container ");
        return graph;
    }
    public List<ElementHandle> getHighchartsCredits(ElementHandle graph){
        List<ElementHandle> credit = graph.querySelectorAll("text.highcharts-credits");
        return credit;
    }
    public void Navigate(String link){
        page.navigate(link);
    }

    public String url(){return page.url();}
    public void pageClose(){page.close();}
}
