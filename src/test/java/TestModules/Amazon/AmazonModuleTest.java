package TestModules.Amazon;

import Pages.HomePage;
import TestModules.Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonModuleTest extends BaseTest {

    @Test
    public void Scenario1() {
        String data_asin;
        HomePage homePage = new HomePage(driver);
        homePage.SearchForItem("car accessories");
        homePage.selectFirstSearchResult();
        data_asin =homePage.getProductData_asinInProcuctPage();
        homePage.addItemToCart();
        homePage.gotoCart();
        Assert.assertEquals(homePage.getProductData_asinInCartPage(), data_asin, "The added item is not the same item in the cart.");
    }

    @Test
    public void Scenario2() {
        String filter = "Grocery";
        String data_asin;

        HomePage homePage = new HomePage(driver);
        //homePage.dismissLocationPopUpMSG();
        homePage.openTodaysDeals();
        homePage.seeMoreDep();
        homePage.selectFilters(filter);
        homePage.selectDiscount("10% off or more");
        data_asin =homePage.getProductData_asinInCartPage();
        homePage.selectFirstProduct();
        homePage.addItemToCart();
        homePage.gotoCart();
        Assert.assertEquals(homePage.getProductData_asinInCartPage(), data_asin, "The added item is not the same item in the cart.");
    }

}
