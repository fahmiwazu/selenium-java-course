package alerts;

import base.BaseTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContexMenuTests extends BaseTests {

    @Test
    public void contextTesting() {
        var contextest = theInternetHomePage.clickContexMenu();

        // Right-click on context area (this will automatically handle the alert)
        contextest.rightClickOnContextArea();

        // Test passes if no exception is thrown
        System.out.println("Context menu test completed successfully!");
    }

    @Test
    public void contextTestingWithAlertVerification() {
        var contextest = theInternetHomePage.clickContexMenu();

        // Right-click on context area and get alert text in one operation
        String alertText = contextest.rightClickOnContextAreaAndGetAlertText();

        // Verify alert text
        assertEquals("You selected a context menu", alertText, "Alert text verification failed");

        System.out.println("Context menu test with alert verification completed!");
    }
}
