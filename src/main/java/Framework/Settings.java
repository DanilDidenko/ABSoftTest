package main.java.Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static final String TEST_BROWSER = "test.browser";
    private static final String TEST_PROPERTIES = "test.properties";
    private static final String TEST_EMAIL = "test.email";
    private static final String TEST_PASSWORD = "test.password";


    private BrowserType browser;
    private Properties properties = new Properties();
    private String email;
    private String password;

    public Settings() {
        loadSettings();
    }


    private void loadSettings() {
        properties = loadPropertiesFile();
        browser = BrowserType.Browser(getPropertyOrThrowException(TEST_BROWSER));
        password = getPropertyOrThrowException(TEST_PASSWORD);
        email = getPropertyOrThrowException(TEST_EMAIL);
    }

    private Properties loadPropertiesFile() {
        try {
            String filename = getPropertyOrNull(TEST_PROPERTIES);
            if (filename == null) {
                filename = TEST_PROPERTIES;
            }
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);

            if (stream == null) {
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    public String getPropertyOrThrowException(String name) {
        return getProperty(name, true);
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case GC:
                System.setProperty("webdriver.chrome.driver", ".//target//test-classes//chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                return driver;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", ".//target//test-classes//geckodriver.exe");
                return new FirefoxDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }


}
