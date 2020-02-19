package hover;

import base.BaseTests;
import pages.HoversPage.FigureCaption;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class HoverTests extends BaseTests {

    @Test
    public void testHoverUser1() {
        FigureCaption caption = homePage.clickHovers().hoverOverFigure(1);
        assertTrue(caption.isCaptionDisplayed(), "Caption is not displayed.");
        assertEquals(caption.getTitle(), "name: user1", "Caption title incorrect.");
        assertEquals(caption.getLinkText(), "View profile", "Caption link text incorrect.");
        assertTrue(caption.getLink().endsWith("/users/1"), "Caption link incorrect.");
    }
}
