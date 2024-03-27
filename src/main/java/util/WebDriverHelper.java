package util;

import base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WebDriverHelper extends BaseClass {

    public static Wait<WebDriver> wait;
    private static WebDriver driver;
    private static final int MAX_STALE_ELEMENT_RETRIES = 2;

    public WebDriverHelper(WebDriver driver) {
        WebDriverHelper.driver = driver;
    }

    /**
     * Using this method we can pass value to the web element
     *
     * @param element
     * @param value
     */
    public static void enterValue(WebElement element, String value) {
        try {
            WebDriverHelper.waitUntilVisible(element, 20, 1);

            if (element.isDisplayed()) {
                if (element.isEnabled()) {
                    element.click();
                    element.clear();
                    element.sendKeys(value);
                } else {
                    log.info("Element is not enabled");
                }
            } else {
                log.info("Element is not displayed");
            }
        } catch (Exception ex) {
            log.info("Exception occurred. " + ex.getMessage());
            ex.getStackTrace();
        }
    }

    /**
     * This method returns the text of a web element
     *
     * @param element
     * @return
     */
    public static String getText(WebElement element) {
        String str = "";
        try {
            if (element.isDisplayed()) {
                str = element.getText();
                str = str.trim();
                log.info("Element is displayed for getText");
            } else {
                log.info("Element is not displayed for getText");
            }
        } catch (Exception ex) {
            log.info("Exception occurred" + ex.getMessage());
            ex.getStackTrace();
        }
        return str;
    }

    /**
     * This method is used to click on provided web element
     *
     * @param element
     */
    public static void click(WebElement element) {
        int retries = 0;
        while (retries <= MAX_STALE_ELEMENT_RETRIES) {
            try {
                WebDriverHelper.waitUntilVisible(element, 20, 1);
                if (element.isDisplayed()) {
                    log.info("Element is displayed");
                    if (element.isEnabled()) {
                        log.info("Element is enabled");
                        element.click();
                        log.info("Element is clicked on ");
                    } else {
                        log.info("Element is not enabled");
                    }
                } else {
                    log.info("Element is not displayed");
                }
                break;
            } catch (StaleElementReferenceException ex) {
                retries++;
            } catch (TimeoutException ex) {
                if (ex.getMessage().contains("Timed out receiving message from renderer")) {
                    log.info("Timed Out Due to Bad Inspector Message Exception was successfully handled");
                    throw ex;
                } else {
                    log.info("Unknown Timed Out Exception. Please Investigate and Handle");
                    throw ex;
                }
            } catch (WebDriverException ex) {
                if (ex.getMessage().contains("cannot determine loading status") || (ex.getMessage().contains("bad inspector message"))) {
                    log.info("Bad Inspector Message Exception was successfully handled");
                    throw ex;
                } else {
                    log.info(ex.getMessage());
                    log.info("Unknown WebDriver Exception. Please Investigate and Handle");
                    throw ex;
                }
            } catch (Exception ex) {
                log.info("Unknown Exception occurred while clicking on the element. Please Investigate and Handle" + ex.getMessage());
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    /**
     * This method returns all windows/tabs handles launched/opened by same driver instance including all parent and child windows
     *
     * @return
     */
    public static Set<String> getAllWindowHandles() {
        Set<String> allWindowHandles = null;
        try {
            allWindowHandles = driver.getWindowHandles();
        } catch (Exception e) {
            log.info("Exception occurred. " + e.getMessage());
            e.getStackTrace();
        }
        return allWindowHandles;
    }

    /**
     * This method helps to switch between the windows
     *
     * @param window
     */
    public static void switchToWindow(String window) {
        driver.switchTo().window(window);
    }

    /**
     * This method causes the current thread to suspend execution for a specified period
     *
     * @param sleepTimeInMilliSec
     */
    public static void threadSleep(int sleepTimeInMilliSec) {
        try {
            Thread.sleep(sleepTimeInMilliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param element
     * @param timeout
     * @param pollingTime
     */
    public static void waitUntilVisible(WebElement element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * @param element
     * @param timeout
     * @param pollingTime
     */
    public static void waitUntilClickable(WebElement element, int timeout, int pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * wait until the element is present
     *
     * @param element
     * @param timeout
     */
    public static void waitUntilVisibilityOfElement(WebElement element, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method checks for the element if the element is displayed or not
     *
     * @param element - web element
     */
    public static Boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            waitUntilVisibilityOfElement(element, 10);
            if (element.isDisplayed()) {
                flag = true;
            }
        } catch (NoSuchElementException ex) {
            log.info("Exception occurred while checking for the element presence. " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            log.info("Exception occurred. " + ex.getMessage());
            ex.printStackTrace();
        }
        return flag;
    }

}