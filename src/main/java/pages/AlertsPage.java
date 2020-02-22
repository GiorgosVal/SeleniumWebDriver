package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage {

    private WebDriver driver;
    private By triggerAlertButton = By.xpath(".//button[text() = 'Click for JS Alert']");
    private By triggerConfirmButton = By.xpath(".//button[text() = 'Click for JS Confirm']");
    private By triggerPromptButton = By.xpath(".//button[text() = 'Click for JS Prompt']");

    private By results = By.id("result");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void triggerAlert() {
        this.driver.findElement(triggerAlertButton).click();
    }

    public void triggerConfirm() {
        this.driver.findElement(triggerConfirmButton).click();
    }

    public void triggerPrompt() {
        this.driver.findElement(triggerPromptButton).click();
    }

    public void alert_clickToAccept() {
        this.driver.switchTo().alert().accept();
    }

    public void alert_clickToDismiss() {
        this.driver.switchTo().alert().dismiss();
    }

    public String alert_getText() {
        return this.driver.switchTo().alert().getText();
    }

    public void alert_setInput(String text) {
        this.driver.switchTo().alert().sendKeys(text);
    }

    public String getResult() {
        return this.driver.findElement(results).getText();
    }
}