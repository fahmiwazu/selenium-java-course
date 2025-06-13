package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class EventReporter implements WebDriverListener {

    @Override
    public void beforeGet(WebDriver driver, String url) {
        System.out.println("Before navigating to: " + url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("After navigating to: " + url);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Before finding element by: " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("After finding element by: " + locator);
    }

    @Override
    public void beforeClick(WebElement element) {
        try {
            System.out.println("Before clicking element: " + element.getTagName());
        } catch (StaleElementReferenceException e) {
            System.out.println("Before clicking element: [Stale Element]");
        } catch (Exception e) {
            System.out.println("Before clicking element");
        }
    }

    @Override
    public void afterClick(WebElement element) {
        try {
            System.out.println("After clicking element: " + element.getTagName());
        } catch (StaleElementReferenceException e) {
            System.out.println("After clicking element: [Element became stale after click - normal behavior]");
        } catch (Exception e) {
            System.out.println("After clicking element");
        }
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        try {
            System.out.println("Before sending keys to element: " + element.getTagName());
        } catch (StaleElementReferenceException e) {
            System.out.println("Before sending keys to element: [Stale Element]");
        } catch (Exception e) {
            System.out.println("Before sending keys to element");
        }
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        try {
            System.out.println("After sending keys to element: " + element.getTagName());
        } catch (StaleElementReferenceException e) {
            System.out.println("After sending keys to element: [Element became stale]");
        } catch (Exception e) {
            System.out.println("After sending keys to element");
        }
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        System.out.println("Before quitting driver");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        System.out.println("After quitting driver");
    }

    @Override
    public void beforeClose(WebDriver driver) {
        System.out.println("Before closing driver");
    }

    @Override
    public void afterClose(WebDriver driver) {
        System.out.println("After closing driver");
    }
}