package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WysizygEditorPage {

    private WebDriver driver;
    private String editorIframeId = "mce_0_ifr";
    private By textArea = By.id("tinymce");
    private By descreaseIndentButton = By.cssSelector("#mceu_12 button");


    public WysizygEditorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clearTextArea() {
        switchToEditArea();
        this.driver.findElement(textArea).clear();
        switchToTheMainArea();
    }

    public void setTextArea(String text) {
        switchToEditArea();
        this.driver.findElement(textArea).sendKeys(text);
        switchToTheMainArea();
    }

    public String getTextFromEditor() {
        switchToEditArea();
        String text = this.driver.findElement(textArea).getText();
        switchToTheMainArea();
        return text;
    }

    public void decreaseIndentation() {
        this.driver.findElement(descreaseIndentButton).click();
    }

    private void switchToEditArea() {
        this.driver.switchTo().frame(editorIframeId);
    }

    private void switchToTheMainArea() {
        this.driver.switchTo().parentFrame();
    }
}



