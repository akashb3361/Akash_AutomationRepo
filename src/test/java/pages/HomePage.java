package pages;

import dataProvider.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverHelper;

public class HomePage {

    final WebDriverHelper webDriverHelper;
    private final WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchTextBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement searchSubmitButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.webDriverHelper = new WebDriverHelper(driver);
    }

    public void launchHomePage() {
        driver.get(PropertiesReader.homePageUrl);
    }

    public void enterItemInSearchBox(String item) {
        WebDriverHelper.waitUntilClickable(searchTextBox, 30, 5);
        WebDriverHelper.enterValue(searchTextBox, item);
    }

    public void clickOnSearchSubmitButton() {
        WebDriverHelper.waitUntilClickable(searchSubmitButton, 30, 5);
        WebDriverHelper.click(searchSubmitButton);
    }

}