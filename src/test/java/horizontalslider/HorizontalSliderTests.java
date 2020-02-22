package horizontalslider;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.HorizontalSliderPage;

import static org.testng.Assert.assertEquals;

public class HorizontalSliderTests extends BaseTests {

    @Test
    public void moveSliderRightWithArrows() {
        HorizontalSliderPage horizontalSliderPage = homePage.clickHorizontalSliderPage();
        horizontalSliderPage.moveSliderWithArrows(4);
        assertEquals(horizontalSliderPage.getSliderValue(), horizontalSliderPage.getSliderSpanValue());
    }

    @Test
    public void moveSliderLeftWithArrows() {
        HorizontalSliderPage horizontalSliderPage = homePage.clickHorizontalSliderPage();
        horizontalSliderPage.moveSliderWithArrows(0);
        assertEquals(horizontalSliderPage.getSliderValue(), horizontalSliderPage.getSliderSpanValue(), "Slider value is incorrect.");
    }

}
