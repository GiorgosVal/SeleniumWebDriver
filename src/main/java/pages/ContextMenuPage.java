package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ContextMenuPage {

    private WebDriver driver;
    private By box = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void rightClickOnBox() {
        Actions actions = new Actions(this.driver);
        actions.contextClick(this.driver.findElement(box)).perform();
    }

    public void alert_clickAccept() {
        this.driver.switchTo().alert().accept();
    }

    public String alert_getText() {
       return this.driver.switchTo().alert().getText();
    }


}
