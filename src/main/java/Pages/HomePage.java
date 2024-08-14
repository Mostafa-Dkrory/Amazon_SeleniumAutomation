package Pages;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseClass {
    final private By searchBar = By.id("twotabsearchtextbox");
    final private By shoppingCart = By.id("nav-cart");
    final private By todayDeals = By.linkText("Today's Deals");
    final private By addToCartButton = By.id("add-to-cart-button");
    final private By firstSearchItem = By.cssSelector("div.s-result-item:not(.AdHolder) div[data-cy='title-recipe']");

    // Product Page Locators
    final private By productTitle = By.id("productTitle");

    //Today's Deals Locators
    final private By departmentFilter = By.xpath("//div[@data-a-input-name='departments']//span[contains(text(),'%s')]");
    final private By discountFilter = By.xpath("//div[@data-a-input-name='percentOff']//span[contains(text(),'%s')]");
    final private By seeMoreDepartment = By.xpath("//button[@aria-labelledby='see-more-departments-label']");


    public HomePage(WebDriver driver){
        super(driver);
    }

    public void dismissLocationPopUpMSG() {
        clickOn(By.cssSelector("[data-action-type='DISMISS']"));
    }

    public String getProductData_asinInProcuctPage(){
        return getAttribute(By.cssSelector("[data-asin]"),"data-asin");}

    public String getProductData_asinInCartPage(){
        return getAttribute(By.cssSelector("div[data-asin]"),"data-asin");
    }

    public void SearchForItem(String item) {
        sendKeys(searchBar,item);

    }

    public void selectFirstSearchResult() {
        jsExecuter(firstSearchItem);
    }
    public void selectFirstProduct() {
        jsExecuter(By.cssSelector("div[data-testid='product-card']"));
    }

    public void addItemToCart() {
        clickOn(addToCartButton);
    }
    public void addToCart() {
        clickOn(By.xpath("//button[contains(text(), 'Add to Cart')]"));
    }

    public void gotoCart() {
        clickOn(shoppingCart);
    }
    public void selectFilters(String filter) {
        clickOn(By.xpath(String.format("//div[@data-a-input-name='departments']//span[contains(text(),'%s')]",filter)));
        }

    public void openTodaysDeals() {
        clickOn(todayDeals);
    }

    public void selectDiscount(String discount) {
        jsExecuter(By.xpath(String.format("//div[@data-csa-c-element-id='filter-percentOff-%s']/*/input",discount)));
    }


    public void seeMoreDep() {
        clickOn(seeMoreDepartment);
    }
}


