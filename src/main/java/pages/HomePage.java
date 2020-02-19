package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickFormAuthentication() {
        clickLink("Form Authentication");
        return new LoginPage(this.driver);
    }

    public DropdownPage clickDropdown(){
        clickLink("Dropdown");
        return new DropdownPage(this.driver);
    }
    public ForgotPasswordPage clickForgotPassword() {
        clickLink("Forgot Password");
        return new ForgotPasswordPage(this.driver);
    }

    public HoversPage clickHovers() {
        clickLink("Hovers");
        return new HoversPage(this.driver);
    }

    private void clickLink(String linkText) {
        this.driver.findElement(By.linkText(linkText)).click();
    }
}
