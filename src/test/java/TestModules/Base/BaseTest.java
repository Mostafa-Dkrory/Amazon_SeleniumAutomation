package TestModules.Base;

import Pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest implements ITestListener{
    protected WebDriver driver;

    protected HomePage homePage;
    private static final Logger logger = LogManager.getLogger();


    @BeforeClass
    @Parameters({"URL","BrowserType"})
    public void setUp(String url, String browserType, ITestContext context) {
        if (browserType.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }
        else if (browserType.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        }
        else {
            driver = new ChromeDriver();
        }
        driver.manage().window().setSize(new Dimension(1024, 768));
        homePage = new HomePage(driver);
        driver.get(url);
    }

    public void goToHomePage(){
        driver.get("https://www.amazon.com/");
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("https://www.amazon.eg/-/en");
    }

    @AfterMethod
    public void tearMethod(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getMethod().getMethodName());
            logger.error("Error in " + result.getMethod().getMethodName() + "Test Method...");
        }
    }
    @AfterClass
    public void tearDown(){
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                System.out.println("***** CAUGHT EXCEPTION IN DRIVER TEARDOWN *****");
                throw e;
            }
        }
    }

    private void captureScreenshot(String methodName) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String screenshotDirectory = "Screenshots";
            Path directoryPath = Paths.get(screenshotDirectory);
            Files.createDirectories(directoryPath);

            // Save the screenshot to the specified directory
            Path screenshotPath = directoryPath.resolve(methodName + formattedDateTime + ".png");

            // Use FileOutputStream to write the screenshot bytes
            try (FileOutputStream fos = new FileOutputStream(screenshotPath.toFile())) {
                fos.write(Files.readAllBytes(screenshotFile.toPath()));
            }

            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

