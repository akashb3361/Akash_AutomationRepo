package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverHelper;

public class ResultsPage {

    final WebDriverHelper webDriverHelper;
    private final WebDriver driver;

    @FindBy(css = "div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1'] span[class='a-size-medium a-color-base a-text-normal']")
    public WebElement firstElementInResults;

    @FindBy(css = "div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_2'] h2[class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'] span:nth-child(1)")
    public WebElement secondElementInResults;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.webDriverHelper = new WebDriverHelper(driver);
    }

    public void selectFirstElementInResults() {
        WebDriverHelper.waitUntilClickable(firstElementInResults, 30, 5);
        WebDriverHelper.click(firstElementInResults);
    }

    public void selectSecondElementInResults() {
        WebDriverHelper.waitUntilClickable(secondElementInResults, 30, 5);
        WebDriverHelper.click(secondElementInResults);
    }

}
