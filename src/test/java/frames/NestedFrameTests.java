package frames;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.NestedFramePage;
import static org.testng.Assert.*;

public class NestedFrameTests extends BaseTests {

    @Test
    public void NestedFrameTest(){
        // Navigate to the nested frames page
        var nestedFramePage = theInternetHomePage.clickNestedFrame();

        // Get text from LEFT frame
        String leftFrameText = nestedFramePage.getLeftFrameText();
        System.out.println("Left Frame Text: " + leftFrameText);

        // Get text from BOTTOM frame
        String bottomFrameText = nestedFramePage.getBottomFrameText();
        System.out.println("Bottom Frame Text: " + bottomFrameText);

        // Assertions to verify the text content
        assertEquals(leftFrameText.trim(), "LEFT", "Left frame should contain 'LEFT' text");
        assertEquals(bottomFrameText.trim(), "BOTTOM", "Bottom frame should contain 'BOTTOM' text");

        // Optional: Print both texts together
        System.out.println("Retrieved texts - Left: '" + leftFrameText + "', Bottom: '" + bottomFrameText + "'");
    }
}