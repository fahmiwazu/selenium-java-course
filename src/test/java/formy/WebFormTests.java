package formy;

import base.BaseTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormTests extends BaseTests {

    @Test
    public void testCompleteWebForm() {
        WebForm webForm = formyHomePage.clickcompleteWebForm();

        webForm.setFirstName("Fahmi");
        webForm.setLastName("Wiradika");
        webForm.setJobTitle("QA Engineer");
        webForm.clickcollege();
        webForm.clickgenderMale();
        webForm.selectFiveToNineExp();
        webForm.setDatePicker("10/06/2025");

        Thanks thanks = webForm.clickSubmitButton();

        assertEquals("The form was successfully submitted!",
                thanks.getThankText(),
                "Complete Web Form");
    }
}
