package main.java.POM;


import main.java.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class BasePage {
    public WebDriver driver;


    public static <T extends BasePage> T initPage(Class<T> pageClass, WebDriver driver) {
        T page = PageFactory.initElements(driver, pageClass);
        page.driver = driver;
        return page;
    }


    public String takeSreanshot(String name) {
        TakesScreenshot screenshotTakingDriver = (TakesScreenshot) this.driver;
        String path = "target\\screenshots";
        try {
            File localScreenshots = new File(new File("target"), "screenshots");
            if (!localScreenshots.exists() || !localScreenshots.isDirectory()) {
                localScreenshots.mkdirs();
            }
            String fileName = new StringBuilder().append(name).append(".png").toString();
            File screenshot = new File(localScreenshots, fileName);
            FileHandler.copy(screenshotTakingDriver.getScreenshotAs(OutputType.FILE), screenshot);
            return new StringBuilder().append(path).append("\\").append(name).append(".png").toString();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return null;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }

    }


}
