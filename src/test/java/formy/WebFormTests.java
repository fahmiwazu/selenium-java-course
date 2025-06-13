package formy;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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

        Thanks thanks =  webForm.clickSubmitButton();

        assertEquals(thanks.getThankText(),
                "The form was successfully submitted!",
                "Complete Web Form");


    }

}
