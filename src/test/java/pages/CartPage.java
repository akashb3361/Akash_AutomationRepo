package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WebDriverHelper;

public class CartPage {

    final WebDriverHelper webDriverHelper;
    private final WebDriver driver;

    @FindBy(xpath = "//div[@id='nav-cart-count-container']")
    public WebElement cartMenu;
    @FindBy(css = ".a-size-medium.a-color-base.sc-price.sc-white-space-nowrap.sc-product-price.a-text-bold")
    public WebElement priceOfItemFromCartPage;
    @FindBy(xpath = "//form[@id='activeCartViewForm']/div[2]/child::div[3]/child::div[4]/descendant::span[8]")
    public WebElement priceOfFirstItemFromCartPage;
        @FindBy(xpath = "//form[@id='activeCartViewForm']/div[2]/child::div[4]/child::div[4]/descendant::span[11]")
//    @FindBy(xpath = "//form[@id='activeCartViewForm']/div[2]/child::div[4]/child::div[4]/descendant::span[12]")
    public WebElement priceOfSecondItemFromCartPage;
    @FindBy(css = "span[id='sc-subtotal-amount-buybox'] span[class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
    public WebElement subTotalFromCartPage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.webDriverHelper = new WebDriverHelper(driver);
    }

    public void clickOnCartMenu() {
        WebDriverHelper.waitUntilClickable(cartMenu, 30, 5);
        WebDriverHelper.click(cartMenu);
    }

    public String getSelectedItemPriceFromCartPage() {
        WebDriverHelper.waitUntilVisible(priceOfItemFromCartPage, 30, 5);
        return WebDriverHelper.getText(priceOfItemFromCartPage);
    }

    public String getFirstSelectedItemPriceFromCartPage() {
        WebDriverHelper.waitUntilVisible(priceOfFirstItemFromCartPage, 30, 5);
        return WebDriverHelper.getText(priceOfFirstItemFromCartPage);
    }

    public String getSecondSelectedItemPriceFromCartPage() {
        WebDriverHelper.waitUntilVisible(priceOfSecondItemFromCartPage, 30, 5);
        return WebDriverHelper.getText(priceOfSecondItemFromCartPage);
    }

    public String getSelectedItemSubTotalFromCartPage() {
        WebDriverHelper.waitUntilVisible(subTotalFromCartPage, 30, 5);
        return WebDriverHelper.getText(subTotalFromCartPage);
    }

}
