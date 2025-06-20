package cucumber;  // Make sure this matches your directory structure

import base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

// JUnit 5 assertions
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Steps {

    // ThreadLocal to ensure each thread gets its own WebDriver instance
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BaseUtil> baseUtilThreadLocal = new ThreadLocal<>();

    public Steps() {
        // Initialize BaseUtil for this thread if not already done
        if (baseUtilThreadLocal.get() == null) {
            baseUtilThreadLocal.set(new BaseUtil());
        }
    }

    @Before
    public void setup() {
        // Use WebDriverManager to automatically manage ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options for better parallel execution
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        // Uncomment the next line if you want to run in headless mode for faster execution
        // options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        driverThreadLocal.set(driver);
    }

    @Given("I am in the login page")
    @Given("I am in the login page of the Para Bank Application")
    public void i_am_in_the_login_page_of_the_Para_Bank_Application() {
        getDriver().get("http://parabank.parasoft.com/parabank/index.htm");
    }

    @When("I enter valid {string} and {string} with {string}")
    public void i_enter_valid_credentials(String username, String password, String userFullName) {
        getBaseUtil().userFullName = userFullName;

        getDriver().findElement(By.name("username")).sendKeys(username);
        getDriver().findElement(By.name("password")).sendKeys(password);
        getDriver().findElement(By.name("username")).submit();
    }

    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_Overview_page() throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rightPanel']/div/div/h1")));

        String actualUserFullName = getDriver().findElement(By.className("smallText")).getText();
        assertTrue(actualUserFullName.contains(getBaseUtil().userFullName), "User full name validation failed");
        assertTrue(getDriver().findElement(By.xpath("//*[@id='rightPanel']/div/div/h1")).isDisplayed(), "Overview page not displayed");
        getDriver().findElement(By.linkText("Log Out")).click();
    }

    @Then("Invalid Credential Warnings")
    public void invalidCredentialWarningPage() throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error")));

        String errorMessage = getDriver().findElement(By.cssSelector(".error")).getText();

        assertTrue(errorMessage.contains("The username and password could not be verified."), "Failed to Verify Condition");
    }

    @After
    public void quitBrowser() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
        // Clean up BaseUtil for this thread
        baseUtilThreadLocal.remove();
    }

    // Helper methods to get thread-local instances
    private WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    private BaseUtil getBaseUtil() {
        return baseUtilThreadLocal.get();
    }
}