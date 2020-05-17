package main.java.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Utils {


    public static WebElement waitForElementPresent(WebElement webElement, WebDriver driver, Integer delay) {

        WebDriverWait wait = new WebDriverWait(driver, delay);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
        return element;
    }

    public static WebElement waitForElementPresent(By locator, WebDriver driver, Integer delay) {
        WebDriverWait wait = new WebDriverWait(driver, delay);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }


    public static boolean isElementPresent(By by, WebDriver driver) {
        return driver.findElements(by).size() > 0;
    }

    public static Boolean waitUntilElementsCountChanges(By locator, WebDriver driver, Integer delay) {
        final Boolean[] res = {false};
        int count = driver.findElements(locator).size();
        WebDriverWait wait = new WebDriverWait(driver, delay);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                int elementCount = driver.findElements(locator).size();
                if (elementCount > count) {
                    res[0] = true;
                    return true;
                } else
                    return false;
            }
        });
        return res[0];
    }

    public static boolean checkIfFileExist(String filePath) {
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }


}

