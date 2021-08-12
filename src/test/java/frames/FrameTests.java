package frames;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.WysizygEditorPage;

import static org.testng.Assert.assertEquals;

public class FrameTests extends BaseTests {

    @Test
    public void testWysiwyg() {
        WysizygEditorPage wysizygEditorPage = homePage.clickWysizygEditor();
        wysizygEditorPage.clearTextArea();

        String text1 = "hello ";
        String text2 = "world";

        wysizygEditorPage.setTextArea(text1);
        wysizygEditorPage.increaseIndentation();
        wysizygEditorPage.setTextArea(text2);

        assertEquals(wysizygEditorPage.getTextFromEditor(), text1 + text2, "Text from editor is incorrect");
    }
}
