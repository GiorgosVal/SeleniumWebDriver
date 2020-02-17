package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {

    private WebDriver driver;
    private By emailInput = By.id("email");
    private By formSubmit = By.id("form_submit");


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeEmail() {
        findWebElement(emailInput).sendKeys("example@email.com");
    }

    public EmailSentPage clickRetrieveButton() {
        findWebElement(formSubmit).click();
        return new EmailSentPage(this.driver);
    }

    private WebElement findWebElement(By by) {
        return this.driver.findElement(by);
    }


}
