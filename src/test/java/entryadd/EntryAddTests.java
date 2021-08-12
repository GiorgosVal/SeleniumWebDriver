package entryadd;

import static org.testng.Assert.assertTrue;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.EntryAd;

public class EntryAddTests extends BaseTests {

    @Test
    public void testModalWillClose() {

        EntryAd entryAd = homePage.clickEntryAdd();
        assertTrue(entryAd.isModalOpen(), "Modal is not open.");
        entryAd.getModal().modal_clickFooterToClose();
        assertTrue(entryAd.isModalClosed(), "Modal is open.");
    }
}
