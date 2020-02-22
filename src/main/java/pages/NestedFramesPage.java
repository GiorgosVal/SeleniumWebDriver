package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage {

    private WebDriver driver;
    private By body = By.tagName("body");

    public NestedFramesPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextFromUpperLeftFrame() {
        switchToFrame(1);
        switchToFrame(1);
        String text = getFrameText();
        switchToParent();
        switchToParent();
        return text;
    }

    public String getTextFromBottomFrame() {
        switchToFrame(2);
        String text = getFrameText();
        switchToParent();
        return text;
    }

    /**
     * Switches to frame based on frame's index position.
     * @param i Starts at 1.
     */
    private void switchToFrame(int i) {
        this.driver.switchTo().frame(i - 1);
    }

    private void switchToParent() {
        this.driver.switchTo().parentFrame();
    }

    private String getFrameText() {
        return this.driver.findElement(body).getText();
    }
}
