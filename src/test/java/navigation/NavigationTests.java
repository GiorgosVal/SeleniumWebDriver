package navigation;

import base.BaseTests;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTests {

    @Test
    public void testNavigator() {
        homePage.clickDynamicLoad().clickExample1();
        getWindowManager().goBack();
        getWindowManager().refreshPage();
        getWindowManager().goForward();
        getWindowManager().goTo("https:/google.com");
    }

    @Test
    public void testSwitchWindow() {
        homePage.clickMultipleWindows().clickHere();
        getWindowManager().switchToTab("New Window");
    }


}
