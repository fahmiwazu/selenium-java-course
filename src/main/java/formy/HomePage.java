package formy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By completeWebForm = By.xpath(
            "(//a[@class='btn btn-lg'][normalize-space()='Complete Web Form'])[1]");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public WebForm clickcompleteWebForm(){
        driver.findElement(completeWebForm).click();
        return new WebForm(driver);
    }

}
