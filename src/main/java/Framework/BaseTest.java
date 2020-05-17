package main.java.Framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest {
    private WebDriver driver;
    protected SiteNavigator siteNavigator;
    protected Settings settings;

    @BeforeTest(alwaysRun = true)
    public void beforeClass() {
        this.settings = new Settings();
        this.driver = this.settings.getDriver();
        siteNavigator = new SiteNavigator(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun = true)
    public void afterClass() {
        this.driver.quit();
    }


}
