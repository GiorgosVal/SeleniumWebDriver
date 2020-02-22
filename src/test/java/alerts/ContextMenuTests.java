package alerts;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.ContextMenuPage;

import static org.testng.Assert.assertEquals;

public class ContextMenuTests extends BaseTests {

    @Test
    public void testContextMenyAlert() {
        ContextMenuPage contextMenuPage = homePage.clickContextMenu();
        contextMenuPage.rightClickOnBox();
        String alertText = contextMenuPage.alert_getText();
        contextMenuPage.alert_clickAccept();
        assertEquals(alertText, "You selected a context menu", "Incorrect alert text");
    }
}
