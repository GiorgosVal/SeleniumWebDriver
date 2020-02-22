package horizontalslider;

import base.BaseTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HorizontalSliderPage;

import static org.testng.Assert.assertEquals;

public class HorizontalSliderTests extends BaseTests {

    HorizontalSliderPage horizontalSliderPage;

    @BeforeClass
    public void gotoSliderPage() {
        horizontalSliderPage = homePage.clickHorizontalSliderPage();
    }

    @Test
    public void moveSliderRightWithArrows() {
        horizontalSliderPage.moveSliderWithArrows(4);
        assertEquals(horizontalSliderPage.getSliderValue(), horizontalSliderPage.getSliderSpanValue());
    }

    @Test
    public void moveSliderLeftWithArrows() {
        horizontalSliderPage.moveSliderWithArrows(0);
        assertEquals(horizontalSliderPage.getSliderValue(), horizontalSliderPage.getSliderSpanValue(), "Slider value is incorrect.");
    }

}
