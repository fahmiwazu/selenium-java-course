package seleniumAdvance;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RelativeLocators {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testRelativeLocatorForCredentials() {
        // Method 1: Using the login panel as reference point
        WebElement loginPanel = driver.findElement(By.tagName("h5"));

        // Find credentials above the login panel
        List<WebElement> credentialElements = driver.findElements(
                RelativeLocator.with(By.className("oxd-text"))
                        .above(loginPanel)
        );


        System.out.println("=== Method 1: All credentials found above login panel ===");
        System.out.println(credentialElements);

        for (WebElement element : credentialElements) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                System.out.println("Credential: " + text);
            }
        }
    }

    @Test
    public void testRelativeLocatorSpecificCredentials() {
        // Method 2: More specific approach using the credentials container
        WebElement loginPanel = driver.findElement(By.tagName("h5"));

        // Find the specific credential elements
        try {
            // Look for elements with specific text patterns
            WebElement usernameElement = driver.findElement(
                    RelativeLocator.with(By.xpath("//p[contains(text(), 'Username') or contains(text(), 'Admin')]"))
                            .above(loginPanel)
            );
            System.out.println("Username credential: " + usernameElement.getText());

            WebElement passwordElement = driver.findElement(
                    RelativeLocator.with(By.xpath("//p[contains(text(), 'Password') or contains(text(), 'admin123')]"))
                            .above(loginPanel)
            );
            System.out.println("Password credential: " + passwordElement.getText());


        } catch (Exception e) {
            System.out.println("Could not find specific credentials, trying alternative approach...");
            alternativeCredentialLocation();
        }
    }

    @Test
    public void testRelativeLocatorWithLogo() {
        // Method 3: Using logo as reference point
        try {
            WebElement logo = driver.findElement(By.cssSelector("img[alt*='orange']"));

            // Find credentials below the logo
            List<WebElement> credentialElements = driver.findElements(
                    RelativeLocator.with(By.className("oxd-text"))
                            .below(logo)
                            .above(driver.findElement(By.tagName("h5")))
            );

            System.out.println("=== Method 3: Credentials found between logo and login panel ===");
            for (WebElement element : credentialElements) {
                String text = element.getText().trim();
                if (!text.isEmpty() && (text.contains("Admin") || text.contains("admin123") ||
                        text.contains("Username") || text.contains("Password"))) {
                    System.out.println("Credential: " + text);
                }
            }
        } catch (Exception e) {
            System.out.println("Logo-based approach failed: " + e.getMessage());
        }
    }

    @Test
    public void testRelativeLocatorAdvanced() {
        // Method 4: Advanced relative locator usage
        WebElement loginForm = driver.findElement(By.cssSelector("form"));

        // Find elements above the form that contain credential info
        List<WebElement> credentialContainer = driver.findElements(
                RelativeLocator.with(By.tagName("div"))
                        .above(loginForm)
        );

        System.out.println("=== Method 4: Advanced search for credentials ===");
        for (WebElement container : credentialContainer) {
            String text = container.getText().trim();
            if (text.contains("Username") || text.contains("Password") ||
                    text.contains("Admin") || text.contains("admin123")) {
                System.out.println("Found credential container: " + text);

                // Find specific credential elements within this container
                List<WebElement> credentials = container.findElements(By.tagName("p"));
                for (WebElement cred : credentials) {
                    String credText = cred.getText().trim();
                    if (!credText.isEmpty()) {
                        System.out.println("  -> " + credText);
                    }
                }
                break;
            }
        }
    }

    @Test
    public void testPracticalCredentialExtraction() {
        // Method 5: Practical approach for actual test automation
        System.out.println("=== Practical Credential Extraction ===");

        try {
            // Wait a bit for page to fully load
            Thread.sleep(2000);

            // Look for the credentials panel/container
            WebElement credentialsContainer = driver.findElement(
                    RelativeLocator.with(By.className("orangehrm-login-slot"))
                            .above(driver.findElement(By.className("orangehrm-login-form")))
            );

            System.out.println("Credentials container found: " + credentialsContainer.getText());

            // Extract username and password from the container
            String containerText = credentialsContainer.getText();
            String[] lines = containerText.split("\n");

            String username = "";
            String password = "";

            for (String line : lines) {
                if (line.contains("Username") && line.contains(":")) {
                    username = line.split(":")[1].trim();
                } else if (line.contains("Password") && line.contains(":")) {
                    password = line.split(":")[1].trim();
                }
            }

            System.out.println("Extracted Username: " + username);
            System.out.println("Extracted Password: " + password);

            // Now use these credentials to login
            if (!username.isEmpty() && !password.isEmpty()) {
                performLogin(username, password);
            }

        } catch (Exception e) {
            System.out.println("Practical extraction failed: " + e.getMessage());
        }
    }

    // Helper method for alternative credential location
    private void alternativeCredentialLocation() {
        try {
            // Try to find all paragraph elements that might contain credentials
            List<WebElement> allParagraphs = driver.findElements(By.tagName("p"));

            System.out.println("=== Alternative Method: Searching all paragraphs ===");
            for (WebElement p : allParagraphs) {
                String text = p.getText().trim();
                if (text.contains("Username") || text.contains("Password") ||
                        text.contains("Admin") || text.contains("admin123")) {
                    System.out.println("Found credential: " + text);
                }


            }

            System.out.println(allParagraphs);


        } catch (Exception e) {
            System.out.println("Alternative method also failed: " + e.getMessage());
        }
    }

    // Helper method to perform login with extracted credentials
    private void performLogin(String username, String password) {
        try {
            WebElement usernameField = driver.findElement(By.cssSelector("input[placeholder='Username']"));
            WebElement passwordField = driver.findElement(By.cssSelector("input[placeholder='Password']"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            loginButton.click();

            System.out.println("Login attempted with extracted credentials");
            Thread.sleep(3000); // Wait to see the result

        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}