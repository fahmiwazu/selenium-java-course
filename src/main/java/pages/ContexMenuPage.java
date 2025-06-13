package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ContexMenuPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By contextMenuArea = By.id("hot-spot");
    private By contextMenu = By.id("menu");

    public ContexMenuPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void rightClickOnContextArea() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(contextMenuArea));
        actions.contextClick(element).perform();

        // Handle the alert that appears after right-clicking
        handleAlert();
    }

    public String rightClickOnContextAreaAndGetAlertText() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(contextMenuArea));
        actions.contextClick(element).perform();

        // Handle the alert and return the text
        return getAlertTextAndAccept();
    }

    public void handleAlert() {
        try {
            // Wait for alert to be present
            wait.until(ExpectedConditions.alertIsPresent());

            // Switch to alert and accept it
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            alert.accept(); // Click OK button

        } catch (Exception e) {
            System.out.println("No alert found or error handling alert: " + e.getMessage());
        }
    }

    // Alternative method if you want to handle alert separately in your test
    public String getAlertTextAndAccept() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            System.out.println("No alert found: " + e.getMessage());
            return null;
        }
    }
}