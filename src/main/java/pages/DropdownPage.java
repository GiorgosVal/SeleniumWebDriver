package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownPage {

    private WebDriver driver;
    private By dropdown = By.id("dropdown");

    DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFromDropDown(String option) {
        findDropdown().selectByVisibleText(option);
    }

    public List<String> getSelectedOptions() {
        List<WebElement> selectedWebElements = findDropdown().getAllSelectedOptions();
        return selectedWebElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private Select findDropdown() {
        return new Select(this.driver.findElement(dropdown));
    }

    public void setMultiple() {
        String script = "arguments[0].setAttribute('multiple', '')";
        ((JavascriptExecutor)driver).executeScript(script, findDropdown());
    }
}
