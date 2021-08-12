package brokenimages;

import static org.testng.Assert.assertEquals;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.BrokenImages;

public class BrokenImagesTests extends BaseTests {

    @Test
    public void testImagesAreDisplayed() {

        BrokenImages brokenImages = homePage.clickBrokenImages();
        int brokenImagesCount = brokenImages.countBrokenImages();
        assertEquals(brokenImagesCount, 0, String.format("Found %s broken images.", brokenImagesCount));
    }
}
