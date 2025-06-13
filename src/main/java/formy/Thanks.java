package formy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Thanks {
    private WebDriver driver;
    private WebDriverWait wait;
    private By alertNotif = By.cssSelector("div[role='alert']");

    public Thanks(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getThankText(){
        // Wait for the alert notification to be visible and contain text
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(alertNotif));

        // Additional wait to ensure text is loaded
        wait.until(ExpectedConditions.textToBePresentInElement(element, "successfully"));

        return element.getText();
    }

    // Alternative method if you want to wait for specific text
    public String getThankTextWithSpecificWait(String expectedText){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(alertNotif));
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
        return element.getText();
    }

    // Method to check if thank you message is displayed
    public boolean isThankMessageDisplayed(){
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(alertNotif));
            return element.isDisplayed() && !element.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}