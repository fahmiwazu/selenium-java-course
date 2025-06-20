package hover;

import base.BaseTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HoverTests extends BaseTests {

    @Test
    public void testHoverUsser1(){
        var hoversPage = theInternetHomePage.clickHovers();

        var caption = hoversPage.hoverOverFigure(1);

        assertTrue(caption.isCaptionDisplayed(),"Caption not displayed");

        assertEquals(caption.getTitle(),"name: user1","Caption title incorrect");

        assertEquals(caption.getLinkText(),"View profile","Caption link text ");

        assertTrue(caption.getLink().endsWith("/users/1"),"Link incorrect");
    }
}
