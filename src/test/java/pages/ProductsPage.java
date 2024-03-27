package pages;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverHelper;

import static support.Globals.productsPage;

public class ProductsPage extends BaseClass {

    final WebDriverHelper webDriverHelper;
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")
    public WebElement addToCartButton;
    @FindBy(css = "span[class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay'] span[class='a-price-whole']")
    public WebElement selectedProductPrice;
    @FindBy(xpath = "//body/div[@id='a-page']/div[@id='sw-full-view-container']/div[@id='sw-full-view']/div[@id='sw-atc-confirmation']/div[@id='sw-atc-actions']/div[@id='sw-atc-fst-buybox']/div[@id='sw-atc-bb']/div[@id='sw-atc-actions-buy-box-sign-in']/div[@id='sw-atc-buy-box']/div[@id='sw-subtotal']/span/span[@class='a-price sw-subtotal-amount']/span[2]")
    public WebElement subTotalFromProductPage;
    @FindBy(xpath = "//span[@class='a-price sw-subtotal-amount']//span[@class='a-price-whole']")
    public WebElement subTotalFromProductPageOfSecondSearchItem;
    @FindBy(css = "#attach-accessory-cart-subtotal")
    public WebElement subTotalFromProductPage1;
    @FindBy(xpath = "//input[@aria-labelledby='attachSiNoCoverage-announce']")
    public WebElement skipBtn;
    @FindBy(xpath = "//input[@aria-labelledby='attachSiNoCoverage-announce']")
    public WebElement skipBtnAfterAddToCart;
    @FindBy(css = "#attach-close_sideSheet-link")
    public WebElement cancelBtn;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickOnAddToCart() {
        boolean isDisplayed;
        WebDriverHelper.waitUntilClickable(addToCartButton, 30, 5);
        WebDriverHelper.click(addToCartButton);
        try {
            isDisplayed = WebDriverHelper.isElementPresent(skipBtnAfterAddToCart);
            log.info("Element displayed is :" + isDisplayed);
        } catch (Exception e) {
            isDisplayed = false;
            log.info("Element displayed is :" + isDisplayed);
        }

        if (isDisplayed) {
            WebDriverHelper.waitUntilClickable(skipBtnAfterAddToCart, 30, 5);
            WebDriverHelper.click(skipBtnAfterAddToCart);
        } else {
            log.info("Skip the skipButton step");
        }
    }

    public String getSelectedItemPriceFromProductPage() {
        WebDriverHelper.waitUntilVisible(selectedProductPrice, 30, 5);
        return WebDriverHelper.getText(selectedProductPrice);
    }

    public String getSelectedItemSubTotalFromProductPage() {
        boolean isDisplayed;
        String str;
        try {
            isDisplayed = WebDriverHelper.isElementPresent(subTotalFromProductPage);
            log.info("Element displayed is :" + isDisplayed);
        } catch (Exception e) {
            isDisplayed = false;
            log.info("Element displayed is :" + isDisplayed);
        }

        if (isDisplayed) {
            WebDriverHelper.waitUntilVisible(subTotalFromProductPageOfSecondSearchItem, 30, 5);
            str = WebDriverHelper.getText(subTotalFromProductPageOfSecondSearchItem).concat(".00").trim();
            return str;
        } else {
            WebDriverHelper.waitUntilVisible(subTotalFromProductPage1, 30, 5);
            str = WebDriverHelper.getText(subTotalFromProductPage1).substring(1).trim();
            productsPage.clickOnCancelButton();
            return str;
        }
    }

    public void clickOnCancelButton() {
        WebDriverHelper.waitUntilClickable(cancelBtn, 30, 5);
        WebDriverHelper.click(cancelBtn);
    }

    public String getSecondSelectedItemSubTotalFromProductPage() {
        WebDriverHelper.waitUntilVisible(subTotalFromProductPageOfSecondSearchItem, 30, 5);
        return WebDriverHelper.getText(subTotalFromProductPageOfSecondSearchItem);
    }

}
