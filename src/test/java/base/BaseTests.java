package base;

import com.google.common.io.Files;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTests implements WebDriverListener {

    private static WebDriver driver;
    protected static pages.HomePage theInternetHomePage;
    protected static formy.HomePage formyHomePage;

    @BeforeAll
    public static void setUp() {
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

    @BeforeEach
    public static void goHome() {
        // Choose one based on the app you're testing
        // driver.get("https://the-internet.herokuapp.com/");
        // theInternetHomePage = new pages.HomePage(driver);

        driver.get("https://formy-project.herokuapp.com/");
        formyHomePage = new formy.HomePage(driver);
    }

    @AfterEach
    public void recordFailure(org.junit.jupiter.api.TestInfo testInfo) {
        // Screenshot handling requires integration with a test execution framework that captures test results.
        // This method needs customization depending on the test runner or listener support (e.g., Maven Surefire + listener).
        // Placeholder logic below for context:
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.move(screenshot, new File("resources/screenshots/" + testInfo.getDisplayName() + ".png"));
            System.out.println("Screenshot taken: " + screenshot.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-save-password-bubble");

        // Uncomment for headless mode
        // options.addArguments("--headless");

        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

    private static void setCookie() {
        Cookie cookie = new Cookie.Builder("tau", "123")
                .domain("the-internet.herokuapp.com")
                .build();
        driver.manage().addCookie(cookie);
    }
}
