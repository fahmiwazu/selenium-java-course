package alerts;

import base.BaseTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertTests extends BaseTests {

    @Test
    public void testAcceptAlert() {
        var alertsPage = theInternetHomePage.clickJavaScriptAlerts();
        alertsPage.triggerAlert();
        alertsPage.acceptAlert();

        assertEquals("You successfully clicked an alert", alertsPage.getResult(), "Results text incorrect");
    }

    @Test
    public void testGetTextFromAlert() {
        var alertsPage = theInternetHomePage.clickJavaScriptAlerts();
        alertsPage.triggerConfirm();

        String text = alertsPage.alert_getText();
        alertsPage.alert_clickToDismiss();

        assertEquals("I am a JS Confirm", text, "Alert text incorrect");
    }

    @Test
    public void testSetInputInAlert() {
        var alertsPage = theInternetHomePage.clickJavaScriptAlerts();
        alertsPage.triggerPrompt();

        String text = "TAU rocks!";
        alertsPage.alert_setInput(text);
        alertsPage.alert_clickToAccept();

        assertEquals("You entered: " + text, alertsPage.getResult(), "Results text incorrect");
    }
}
