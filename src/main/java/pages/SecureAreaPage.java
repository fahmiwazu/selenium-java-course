package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecureAreaPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By statusAlert = By.id("flash");

    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getAlertText(){
        // Wait for the alert notification to be visible and contain text
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(statusAlert));

        return element.getText();
    }


}
