package javascript;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.DropdownPage;


import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JavaScriptTests extends BaseTests {

    @Test
    public void testScrollToTable() {
        homePage.clickLargeAndDeepDOM().scrollToTable();
    }

    @Test
    public void testScrollToFifthParagraph() {
        homePage.clickInfiniteScroll().scrollToParagraph(5);
    }

    @Test
    public void testDropdownSetToMultiple() {
        DropdownPage dropdownPage = homePage.clickDropdown();
        dropdownPage.setMultiple();

        List<String> optionsToSelect = Arrays.asList("Option 1", "Option 2");
        optionsToSelect.forEach(dropdownPage::selectFromDropDown);

        List<String> options = dropdownPage.getSelectedOptions();
        assertEquals(options.size(), 2, "Incorrect number of selected options");
        assertTrue(options.containsAll(optionsToSelect), "All options were not selected");
    }
}
