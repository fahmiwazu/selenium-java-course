package seleniumAdvance;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowManagement {
    WebDriver driver;



    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().minimize();
        driver.get("https://formy-project.herokuapp.com");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        driver.manage().window().minimize();
        newWindow.get("https://formy-project.herokuapp.com");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindowTabs(){
        // Automatically Open & Switch To The New Window Or Tab
        driver.manage().window().minimize();
        driver.switchTo().newWindow(WindowType.TAB)
                .get("https://formy-project.herokuapp.com/form");
        System.out.println("Title: " + driver.getTitle());

        // Work In The New Window Or Tab
        driver.findElement(By.cssSelector("#first-name")).sendKeys("Fahmi");
        driver.findElement(By.cssSelector("a[role='button']")).click();

        // Get The Window ID Handles
        Set<String> allWindowTabss= driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabss.iterator();
        String mainFirstWindow = iterate.next();

        // Switch & Work In The Main Window Or Tab
        driver.switchTo().window(mainFirstWindow);
        driver.manage().window().minimize();
        driver.findElement(By.partialLinkText("Web Form")).click();
        System.out.println("Title: " + driver.getTitle());
    }
}