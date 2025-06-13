package javascript;

import base.BaseTests;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

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