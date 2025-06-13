package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FileUploadPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By inputField = By.id("file-upload");
    // Try multiple possible selectors for the upload button
    private By uploadButton = By.cssSelector("#file-submit");
    private By uploadButtonAlt1 = By.cssSelector("input[type='submit']");
    private By uploadButtonAlt2 = By.cssSelector("button[type='submit']");
    private By uploadButtonAlt3 = By.xpath("//input[@value='Upload']");
    private By uploadButtonAlt4 = By.xpath("//button[contains(text(),'Upload')]");
    private By uploadedFiles = By.cssSelector("#uploaded-files");

    public FileUploadPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickUploadButton(){
        WebElement uploadBtn = findUploadButton();
        if (uploadBtn != null) {
            uploadBtn.click();
        } else {
            throw new RuntimeException("Upload button not found with any of the provided selectors");
        }
    }

    /**
     * Finds the upload button using multiple possible selectors
     * @return WebElement of the upload button or null if not found
     */
    private WebElement findUploadButton() {
        By[] selectors = {uploadButton, uploadButtonAlt1, uploadButtonAlt2, uploadButtonAlt3, uploadButtonAlt4};

        for (By selector : selectors) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selector));
                System.out.println("Upload button found with selector: " + selector.toString());
                return element;
            } catch (Exception e) {
                System.out.println("Upload button not found with selector: " + selector.toString());
                // Continue to next selector
            }
        }
        return null;
    }

    /**
     * Only uploads the file without clicking the upload button
     * @param absolutePathOfFile The complete path of the file to upload
     */
    public void selectFile(String absolutePathOfFile){
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(inputField));
        fileInput.sendKeys(absolutePathOfFile);
        System.out.println("File selected: " + absolutePathOfFile);
    }

    /**
     * Provides path of file to the form then clicks Upload button
     * @param absolutePathOfFile The complete path of the file to upload
     */
    public void uploadFile(String absolutePathOfFile){
        selectFile(absolutePathOfFile);
        clickUploadButton();
    }

    public String getUploadedFiles(){
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFiles));
            return element.getText();
        } catch (Exception e) {
            System.out.println("Could not find uploaded files element. Checking page source...");
            // Print page source for debugging
            System.out.println("Current page title: " + driver.getTitle());
            return "Error: Could not retrieve uploaded files";
        }
    }

    /**
     * Method to help debug what elements are available on the page
     */
    public void debugPageElements() {
        System.out.println("=== DEBUG: Available elements on the page ===");
        System.out.println("Page title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Try to find common upload-related elements
        try {
            driver.findElement(By.id("file-upload"));
            System.out.println("✓ File input field found: #file-upload");
        } catch (Exception e) {
            System.out.println("✗ File input field not found: #file-upload");
        }

        try {
            driver.findElement(By.cssSelector("#file-submit"));
            System.out.println("✓ Submit button found: #file-submit");
        } catch (Exception e) {
            System.out.println("✗ Submit button not found: #file-submit");
        }

        // Look for any submit buttons or inputs
        System.out.println("All input elements:");
        driver.findElements(By.tagName("input")).forEach(element -> {
            String type = element.getAttribute("type");
            String id = element.getAttribute("id");
            String value = element.getAttribute("value");
            System.out.println("  Input - Type: " + type + ", ID: " + id + ", Value: " + value);
        });

        System.out.println("All button elements:");
        driver.findElements(By.tagName("button")).forEach(element -> {
            String type = element.getAttribute("type");
            String id = element.getAttribute("id");
            String text = element.getText();
            System.out.println("  Button - Type: " + type + ", ID: " + id + ", Text: " + text);
        });

        System.out.println("=== END DEBUG ===");
    }
}