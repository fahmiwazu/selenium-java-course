package formy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WebForm {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.cssSelector("#first-name");
    private By lastName = By.id("last-name");
    private By jobTitle = By.id("job-title");
    private By highSchool = By.xpath("//div[normalize-space()='High School']");
    private By college = By.xpath("//div[normalize-space()='College']");
    private By gradSchool = By.xpath("//div[normalize-space()='Grad School']");
    private By genderMale = By.cssSelector("#checkbox-1");
    private By genderFemale = By.cssSelector("#checkbox-2");
    private By genderNotToSay = By.cssSelector("#checkbox-3");
    private By yearsOfExperience = By.cssSelector("#select-menu");
    private By datePicker = By.cssSelector("#datepicker");
    private By submitButton = By.cssSelector("a[role='button']");

    public WebForm(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setFirstName(String firstname){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(firstName));
        element.sendKeys(firstname);
    }

    public void setLastName(String lastname){
        driver.findElement(lastName).sendKeys(lastname);
    }

    public void setJobTitle(String jobtitle){
        driver.findElement(jobTitle).sendKeys(jobtitle);
    }

    public void clickhighSchool(){
        driver.findElement(highSchool).click();
    }

    public void clickcollege(){
        driver.findElement(college).click();
    }

    public void clickgradSchool(){
        driver.findElement(gradSchool).click();
    }

    public void clickgenderMale(){
        driver.findElement(genderMale).click();
    }

    public void clickgenderFemale(){
        driver.findElement(genderFemale).click();
    }

    public void clickgenderNotToSay(){
        driver.findElement(genderNotToSay).click();
    }

    // Refactored Select methods - similar to DropdownPage
    public void selectExperienceByText(String experience){
        findExperienceDropDownElement().selectByVisibleText(experience);
    }

    public void selectExperienceByValue(String value){
        findExperienceDropDownElement().selectByValue(value);
    }

    public void selectExperienceByIndex(int index){
        findExperienceDropDownElement().selectByIndex(index);
    }

    public List<String> getSelectedExperienceOptions(){
        List<WebElement> selectedElements =
                findExperienceDropDownElement().getAllSelectedOptions();
        return selectedElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> getAllExperienceOptions(){
        List<WebElement> allOptions =
                findExperienceDropDownElement().getOptions();
        return allOptions.stream()
                .map(WebElement::getText)
                .collect(Collectors.toUnmodifiableList());
    }

    private Select findExperienceDropDownElement(){
        return new Select(driver.findElement(yearsOfExperience));
    }

    // Convenience methods for specific experience selections
    public void selectZeroToOneExp(){
        selectExperienceByText("0-1");
    }

    public void selectTwoToFourExp(){
        selectExperienceByText("2-4");
    }

    public void selectFiveToNineExp(){
        selectExperienceByText("5-9");
    }

    public void selectTenPlusExp(){
        selectExperienceByText("10+");
    }

    public void setDatePicker(String datepicker){
        driver.findElement(datePicker).sendKeys(datepicker);
    }

    public Thanks clickSubmitButton(){
        driver.findElement(submitButton).click();
        return new Thanks(driver);
    }
}