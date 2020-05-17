package main.java.Framework;


import main.java.POM.BasePage;
import main.java.POM.GetNadaPage;
import org.openqa.selenium.WebDriver;

public class SiteNavigator {
    private WebDriver driver;

    public SiteNavigator(WebDriver driver) {
        this.driver = driver;
    }

    public GetNadaPage goToGetnadaPage() {
        this.driver.get("https://getnada.com/");
        GetNadaPage getnada = BasePage.initPage(GetNadaPage.class, driver);
        return getnada;
    }

    public BasePage goToUrl(String url) {
        this.driver.get(url);
        return BasePage.initPage(BasePage.class, this.driver);
    }

}
