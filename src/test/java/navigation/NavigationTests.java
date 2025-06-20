package navigation;

import base.BaseTests;
import org.junit.jupiter.api.Test;


public class NavigationTests extends BaseTests {

    @Test
    public void testNavigator(){
        theInternetHomePage.clickDynamicLoading().clickExample1();
        getWindowManager().goBack();
        getWindowManager().refreshPage();
        getWindowManager().goForward();
        getWindowManager().goTo("https://fahmiwazu.github.io");
    }

    @Test
    public void testSwitchTab(){
        theInternetHomePage.clickMultipleWindows().clickHere();
        getWindowManager().switchToTab("New Window");
    }
}