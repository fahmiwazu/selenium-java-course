package frames;

import base.BaseTests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class FrameTests extends BaseTests {

    @Test

    public void testWysiwyg(){

        var editorPage =  theInternetHomePage.clickWysiwygEditor();
//        editorPage.clearTextArea();
//
        String text1 = "Your content goes here.";
        String text2 = "";

//        editorPage.setTextArea(text1);
//        editorPage.decreaseIndention();
//        editorPage.setTextArea(text2);

        assertEquals(editorPage.getTextFromEditor(), text1+text2, "Text from editor is incorrect");
    }
}