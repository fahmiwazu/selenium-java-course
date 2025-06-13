package base;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseTests implements WebDriverListener {

    private WebDriver driver;
    protected pages.HomePage theInternetHomePage;
    protected formy.HomePage formyHomePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        // Create the base driver
        WebDriver baseDriver = new ChromeDriver(getChromeOptions());

        // Create event reporter instance
        EventReporter eventReporter = new EventReporter();

        // Wrap the driver with EventFiringDecorator
        driver = new EventFiringDecorator((WebDriverListener) eventReporter).decorate(baseDriver);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        goHome();
        setCookie();

    }

    @BeforeMethod
    public void goHome(){
        // Initialize based on which site you're testing
        driver.get("https://the-internet.herokuapp.com/");
        theInternetHomePage = new pages.HomePage(driver);

        // Or for Formy:
        // driver.get("https://formy-project.herokuapp.com/");
        // formyHomePage = new formy.HomePage(driver);
    }


    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;

            try {
                File screenshot = camera.getScreenshotAs(OutputType.FILE);
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
                System.out.println("Screenshot taken: " + screenshot.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();

        // Essential options to disable info bars
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-save-password-bubble");

        // Enable headless mode
//        options.addArguments("--headless");

        // Disable automation indicators (removes "Chrome is being controlled by automated test software")
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

    private void setCookie(){
        Cookie cookie = new Cookie.Builder("tau", "123")
                .domain("the-internet.herokuapp.com")
                .build();
        driver.manage().addCookie(cookie);
    }
}