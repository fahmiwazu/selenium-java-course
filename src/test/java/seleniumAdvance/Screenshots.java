package seleniumAdvance;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;

public class Screenshots {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Solution 1: Use Chrome instead of Firefox (Recommended)
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.addArguments("--disable-extensions");
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);

        // Solution 2: If you must use Firefox, uncomment the code below and comment out Chrome code above
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // Option 2a: Set Firefox binary path explicitly (update path as needed)
        // firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        // Option 2b: Use headless mode if Firefox GUI is not needed
        // firefoxOptions.addArguments("--headless");

//        driver = new FirefoxDriver(firefoxOptions);


        driver.manage().window().maximize();
        driver.get("https://applitools.com/");
    }

    @Test
    public void takeWebElementScreenshot() throws IOException {
        WebElement nextGenerationPlatform = driver.findElement
                (By.cssSelector("#post-8 h1"));
        File source = nextGenerationPlatform.getScreenshotAs(OutputType.FILE);
        File destination = new File("D:\\Repository\\Automation\\IntelliJ Automation Test\\New folder\\" +
                "selenium-java-waz\\resources\\screenshots\\Next Generation Platform.png");
        FileHandler.copy(source, destination);
        System.out.println("Screenshot saved successfully!");
    }

    @Test
    public void takeWebElementPageSectionScreenshot() throws IOException {
        WebElement applitoolsPageSection = driver.findElement
                (By.cssSelector("#post-8"));
        File source = applitoolsPageSection.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("D:\\Repository\\Automation\\IntelliJ Automation Test\\" +
                "New folder\\selenium-java-waz\\resources\\screenshots\\Applitools Page Section.png"));

    }

    @Test
    public void takeFullPageScreenshot() throws IOException {
        File source = ((ChromeDriver)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File
                ("D:\\Repository\\Automation\\IntelliJ Automation Test\\New folder" +
                        "\\selenium-java-waz\\resources\\screenshots\\Applitools Full Page Screenshot.png"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}