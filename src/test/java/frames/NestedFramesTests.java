package frames;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.NestedFramesPage;

import static org.testng.Assert.assertEquals;

public class NestedFramesTests extends BaseTests {

    @Test
    public void testNestedFrames() {
        NestedFramesPage nestedFramesPage = homePage.clickNestedFrames();
        assertEquals(nestedFramesPage.getTextFromUpperLeftFrame(), "LEFT", "Incorrect text of upper-left iframe");
        assertEquals(nestedFramesPage.getTextFromBottomFrame(), "BOTTOM", "Incorrect text of bottom iframe");
    }
}
