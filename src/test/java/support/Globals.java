package support;

import pages.*;

import static support.BrowserCreation.driver;

public class Globals {
    public static HomePage homePage;
    public static ResultsPage resultsPage;
    public static ProductsPage productsPage;
    public static CartPage cartPage;

    public void driverClass() {
        Globals.homePage = new HomePage(driver);
        Globals.resultsPage = new ResultsPage(driver);
        Globals.productsPage = new ProductsPage(driver);
        Globals.cartPage = new CartPage(driver);
    }

}