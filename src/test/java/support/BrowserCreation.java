package support;

import dataProvider.PropertiesReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class BrowserCreation {

    final static ChromeOptions chromeOptions = new ChromeOptions();
    public static WebDriver driver;

    @Before("@browser")
    public static void setUp() throws IOException {
        PropertiesReader.loadProperties();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors"); //handle alerts by default
        chromeOptions.addArguments("--silent");
        chromeOptions.addArguments("--log-level=3");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        new Globals().driverClass();
    }

    @After("@browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

}