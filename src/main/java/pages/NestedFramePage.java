package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NestedFramePage {
    WebDriver driver;

    // Frame names for switching
    private static final String TOP_FRAME_NAME = "frame-top";
    private static final String LEFT_FRAME_NAME = "frame-left";
    private static final String MIDDLE_FRAME_NAME = "frame-middle";
    private static final String RIGHT_FRAME_NAME = "frame-right";
    private static final String BOTTOM_FRAME_NAME = "frame-bottom";

    private By leftFrameBody = By.tagName("body");
    private By bottomFrameBody = By.tagName("body");

    public NestedFramePage(WebDriver driver){
        this.driver = driver;
    }

    public void switchToTopFrame(){
        driver.switchTo().frame(TOP_FRAME_NAME);
    }

    public void switchToLeftFrame(){
        // First switch to top frame, then to left frame
        driver.switchTo().frame(TOP_FRAME_NAME);
        driver.switchTo().frame(LEFT_FRAME_NAME);
    }

    public void switchToBottomFrame(){
        // Switch directly to bottom frame (it's not nested in top frame)
        driver.switchTo().frame(BOTTOM_FRAME_NAME);
    }

    public String getLeftFrameText(){
        switchToLeftFrame();
        WebElement bodyElement = driver.findElement(leftFrameBody);
        String text = bodyElement.getText();
        driver.switchTo().defaultContent(); // Switch back to main content
        return text;
    }

    public String getBottomFrameText(){
        switchToBottomFrame();
        WebElement bodyElement = driver.findElement(bottomFrameBody);
        String text = bodyElement.getText();
        driver.switchTo().defaultContent(); // Switch back to main content
        return text;
    }

    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }
}