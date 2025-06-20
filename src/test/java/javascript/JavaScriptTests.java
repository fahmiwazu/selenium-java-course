package javascript;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;


public class JavaScriptTests extends BaseTests {

    @Test
    public void testScrollToTable(){
        theInternetHomePage.clickLargeAndDeepDom().scrollToTable();
    }

    @Test
    public void testScrollToFifthParagraph(){
        theInternetHomePage.clickInfiniteScroll().scrollToParagraph(5);
    }
}