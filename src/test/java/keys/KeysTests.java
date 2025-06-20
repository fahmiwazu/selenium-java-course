package keys;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KeysTests extends BaseTests {

    @Test
    public void testBackspace(){
        var keyPage = theInternetHomePage.clickKeyPress();
        keyPage.enterText("A" + Keys.BACK_SPACE);

        assertEquals(keyPage.getResult(),"You entered: BACK_SPACE");
    }
}
