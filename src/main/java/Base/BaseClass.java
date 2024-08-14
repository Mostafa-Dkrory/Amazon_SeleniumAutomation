package Base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseClass {
    protected WebDriver driver;
    private WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    protected WebElement getElement(By by){
        return driver.findElement(by);
    }
    public String getAttributeValue(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }
    protected void clickOn(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }
    protected void jsExecuter(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(element));

    }

    protected void sendKeys(By element, String text) {
        driver.findElement(element).sendKeys(text);
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    protected String getText(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element)).getText();
    }

    protected WebElement waitToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected WebElement waitToPresent(By locator) {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected Boolean isDisplayed(By locator) {
        return  driver.findElement(locator).isDisplayed();
    }

    protected void scrollTo(By element) {
        WebElement webElement = waitToPresent(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    protected String getAttribute(By element, String attribute) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element)).getAttribute(attribute);
    }

}