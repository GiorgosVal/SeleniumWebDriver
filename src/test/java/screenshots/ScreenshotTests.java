package screenshots;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ScreenshotTests extends BaseTests {

    @Test
    public void testScreenshot_onFAIL() {
        assertTrue(false);
    }
}
