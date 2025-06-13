package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HorizontalSliderPage {
    private WebDriver driver;
    private By slider = By.cssSelector("input[value='0']");
    private By range = By.cssSelector("#range");

    public HorizontalSliderPage(WebDriver driver){
        this.driver = driver;
    }

    public void moveRightSlider(){
        driver.findElement(slider).sendKeys(Keys.ARROW_RIGHT);
    }

    public void moveLeftSlider(){
        driver.findElement(slider).sendKeys(Keys.ARROW_LEFT);
    }

    public String getRangeText(){
        return driver.findElement(range).getText();
    }

}
