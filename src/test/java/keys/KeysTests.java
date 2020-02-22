package keys;

import base.BaseTests;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

import static org.testng.Assert.assertEquals;

public class KeysTests extends BaseTests {

    KeyPressesPage keyPressesPage;

    @BeforeClass
    public void goToKeyPressesPage() {
        keyPressesPage = homePage.clickKeyPresses();
    }

    @Test
    public void testBackSpace() {
        keyPressesPage.enterText("A" + Keys.BACK_SPACE);
        assertEquals(keyPressesPage.getResult(), "You entered: BACK_SPACE");
    }

    @Test
    public void testPi() {
        keyPressesPage.enterPi();
    }
}
