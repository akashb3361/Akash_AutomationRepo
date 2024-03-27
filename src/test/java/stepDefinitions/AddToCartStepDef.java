package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import util.WebDriverHelper;

import java.util.ArrayList;
import java.util.Set;

import static support.BrowserCreation.driver;
import static support.Globals.*;

public class AddToCartStepDef extends BaseClass {

    String priceOfSelectedItemFromProductPage;
    String subTotalOfSelectedItemFromProductPage;
    String priceOfSelectedItemFromCartPage;
    String subTotalOfSelectedItemFromCartPage;
    String priceOfSelectedHeadphoneFromProductPage;
    String subTotalOfSelectedHeadphoneFromProductPage;
    String priceOfSelectedKeyboardFromProductPage;
    String subTotalOfSelectedKeyboardFromProductPage;
    String priceOfSelectedHeadphoneFromCartPage;
    String priceOfSelectedKeyboardFromCartPage;

    @Given("user open Amazon homePage")
    public void user_open_amazon_home_page() {
        homePage.launchHomePage();
    }

    @When("user enters {string} in the searchBox")
    public void user_enters_in_the_search_box(String item) {
        homePage.enterItemInSearchBox(item);
    }

    @When("clicks on search submit button")
    public void clicks_on_search_submit_button() {
        homePage.clickOnSearchSubmitButton();
    }

    @Given("selects the {string} item in the result list")
    public void select_the_1st_item_in_the_result_list(String itemNum) {
        if (itemNum.equalsIgnoreCase("1st") || itemNum.equalsIgnoreCase("1st-keyboard")) {
            resultsPage.selectFirstElementInResults();
        } else if (itemNum.equalsIgnoreCase("2nd")) {
            resultsPage.selectSecondElementInResults();
        } else {
            log.info("Neither 1st/1st-keyboard Item nor 2nd Item is selected");
        }

        Set<String> s = WebDriverHelper.getAllWindowHandles(); //handles the new window which gets opened when clicked on the 1st item of search results
        ArrayList<String> arr = new ArrayList<>(s);
        log.info("size of arr: " + arr.size());
        if (itemNum.equalsIgnoreCase("1st-keyboard")) {
            WebDriverHelper.switchToWindow(arr.get(2));
        } else {
            WebDriverHelper.switchToWindow(arr.get(1));
        }
    }

    @Given("adds selected {string} into cart")
    public void add_selected_item_into_cart(String item) {
        if (item.equalsIgnoreCase("Headphones")) {
            priceOfSelectedHeadphoneFromProductPage = productsPage.getSelectedItemPriceFromProductPage();
            priceOfSelectedHeadphoneFromProductPage = priceOfSelectedHeadphoneFromProductPage.concat(".00").trim();
            log.info("Price of selected Headphone from Product Page: " + priceOfSelectedHeadphoneFromProductPage);

            productsPage.clickOnAddToCart();

            subTotalOfSelectedHeadphoneFromProductPage = productsPage.getSelectedItemSubTotalFromProductPage();
            subTotalOfSelectedHeadphoneFromProductPage = subTotalOfSelectedHeadphoneFromProductPage.substring(1).trim();
            log.info("Sub total selected Headphone from Product Page: " + subTotalOfSelectedHeadphoneFromProductPage);
            WebDriverHelper.threadSleep(4_000); //waiting 4 sec here to clear the searchBox and enter new searchValue in it.
        } else if (item.equalsIgnoreCase("Keyboard")) {
            priceOfSelectedKeyboardFromProductPage = productsPage.getSelectedItemPriceFromProductPage();
            priceOfSelectedKeyboardFromProductPage = priceOfSelectedKeyboardFromProductPage.concat(".00");
            log.info("Price of selected Keyboard from Product Page: " + priceOfSelectedKeyboardFromProductPage);

            productsPage.clickOnAddToCart();

            subTotalOfSelectedKeyboardFromProductPage = productsPage.getSelectedItemSubTotalFromProductPage();
            subTotalOfSelectedKeyboardFromProductPage = subTotalOfSelectedKeyboardFromProductPage.substring(1).trim();
            log.info("Sub total of selected Keyboard from Product Page: " + subTotalOfSelectedKeyboardFromProductPage);
        } else {
            priceOfSelectedItemFromProductPage = productsPage.getSelectedItemPriceFromProductPage();
            priceOfSelectedItemFromProductPage = priceOfSelectedItemFromProductPage.concat(".00");
            log.info("Price of Selected Item From Product Page: " + priceOfSelectedItemFromProductPage);

            productsPage.clickOnAddToCart();

            if (item.equalsIgnoreCase("Laptop")) {
                subTotalOfSelectedItemFromProductPage = productsPage.getSecondSelectedItemSubTotalFromProductPage();
                subTotalOfSelectedItemFromProductPage = subTotalOfSelectedItemFromProductPage.trim().concat(".00");
            } else if (item.equalsIgnoreCase("Monitor")) {
                subTotalOfSelectedItemFromProductPage = productsPage.getSelectedItemSubTotalFromProductPage();
            } else {
                log.info("Neither Laptop nor Monitor is added into cart.");
            }
            log.info("Sub total of selected Item from Product Page: " + subTotalOfSelectedItemFromProductPage);
        }
    }

    @Given("user opens cart menu")
    public void user_opens_cart_menu() {
        driver.navigate().refresh();
        cartPage.clickOnCartMenu();
        priceOfSelectedItemFromCartPage = cartPage.getSelectedItemPriceFromCartPage();
        log.info("Price of selected Item from Cart Page: " + priceOfSelectedItemFromCartPage);

        subTotalOfSelectedItemFromCartPage = cartPage.getSelectedItemSubTotalFromCartPage();
        log.info("Sub total of selected Item from Cart Page:  " + subTotalOfSelectedItemFromCartPage);
    }

    @Then("verify that item's productPage price should match with cartPage price")
    public void verify_that_item_s_product_page_price_should_match_with_cart_page_price() {
        log.info("Price of selected Item from Product Page: " + priceOfSelectedItemFromProductPage);
        log.info("Price of selected Item from Cart Page: " + priceOfSelectedItemFromCartPage);
        Assert.assertEquals("Price is not matching", priceOfSelectedItemFromProductPage, priceOfSelectedItemFromCartPage);
    }

    @Then("verify that product page subtotal should match with cart page subtotal")
    public void verify_that_product_page_subtotal_should_match_with_cart_page_subtotal() {
        log.info("Sub total of selected Item from Product Page: " + subTotalOfSelectedItemFromProductPage);
        log.info("Sub total of selected Item from Cart Page: " + subTotalOfSelectedItemFromCartPage);
        Assert.assertEquals("SubTotal is not matching", subTotalOfSelectedItemFromProductPage, subTotalOfSelectedItemFromCartPage);
    }

    @Given("user opens cart menu for multiple items added")
    public void user_opens_cart_menu_for_multiple_items_added() {
        driver.navigate().refresh();
        cartPage.clickOnCartMenu();

        priceOfSelectedHeadphoneFromCartPage = cartPage.getSecondSelectedItemPriceFromCartPage();
        log.info("Price of selected Headphone from Cart Page: " + priceOfSelectedHeadphoneFromCartPage);

        priceOfSelectedKeyboardFromCartPage = cartPage.getFirstSelectedItemPriceFromCartPage();
        log.info("Price of selected Keyboard from Cart Page: " + priceOfSelectedKeyboardFromCartPage);

        subTotalOfSelectedItemFromCartPage = cartPage.getSelectedItemSubTotalFromCartPage();
        log.info("Sub total of selected Items from Cart Page:  " + subTotalOfSelectedItemFromCartPage);
    }

    @Then("verify that for each item productPage price should match with cartPage price")
    public void verify_that_for_each_item_product_page_price_should_match_with_cart_page_price() {
        log.info("Price of selected Headphone from Product Page: " + priceOfSelectedHeadphoneFromProductPage);
        log.info("Price of selected Headphone from Cart Page: " + priceOfSelectedHeadphoneFromCartPage);
        log.info("Price of selected Keyboard from Product Page: " + priceOfSelectedKeyboardFromProductPage);
        log.info("Price of selected Keyboard from Cart Page: " + priceOfSelectedKeyboardFromCartPage);
        Assert.assertEquals("Price is not matching", priceOfSelectedHeadphoneFromProductPage, priceOfSelectedHeadphoneFromCartPage);
        Assert.assertEquals("Price is not matching", priceOfSelectedKeyboardFromProductPage, priceOfSelectedKeyboardFromCartPage);
    }

    @Then("verify that for each item product page subtotal should match with cart page subtotal")
    public void verify_that_for_each_item_product_page_subtotal_should_match_with_cart_page_subtotal() {
        priceOfSelectedHeadphoneFromProductPage = priceOfSelectedHeadphoneFromProductPage.replaceAll(",", "");
        priceOfSelectedKeyboardFromProductPage = priceOfSelectedKeyboardFromProductPage.replaceAll(",", "");
        Double item1 = Double.parseDouble(priceOfSelectedHeadphoneFromProductPage);
        Double item2 = Double.parseDouble(priceOfSelectedKeyboardFromProductPage);
        String sumOfItems = String.valueOf(item1 + item2).concat("0");
        log.info("Sum of both items SubTotal: " + sumOfItems);
        subTotalOfSelectedItemFromCartPage = subTotalOfSelectedItemFromCartPage.replaceAll(",", "");
        log.info("Sub total of selected Items from Cart Page:  " + subTotalOfSelectedItemFromCartPage);
        Assert.assertEquals("SubTotal is not matching", sumOfItems, subTotalOfSelectedItemFromCartPage);
    }

}