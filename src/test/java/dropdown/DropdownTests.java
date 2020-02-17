package dropdown;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.DropdownPage;

import java.util.List;

import static org.testng.Assert.*;

public class DropdownTests extends BaseTests {

    private static final String OPTION_ONE = "Option 1";

    @Test
    public void testSelectedOption() {
        DropdownPage dropdownPage = homePage.clickDropdown();
        dropdownPage.selectFromDropDown(OPTION_ONE);
        List<String> selectedOptions = dropdownPage.getSelectedOptions();
        assertEquals(selectedOptions.size(), 1, "Incorrect number of selected options.");
        assertTrue(selectedOptions.contains(OPTION_ONE), OPTION_ONE + " not selected.");
    }
}
