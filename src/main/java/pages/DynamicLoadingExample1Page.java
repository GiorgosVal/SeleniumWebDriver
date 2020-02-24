package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingExample1Page {

    private WebDriver driver;
    private By startButton = By.cssSelector("#start button");
    private By loadingIndicator = By.id("loading");
    private By loadedText = By.id("finish");

    public DynamicLoadingExample1Page(WebDriver driver) {
        this.driver = driver;
    }

    public void clickStart() {
        this.driver.findElement(startButton).click();
        /*
        Explicit wait example with WebDriverWait. The WebDriverWait instance takes as an argument the driver and the time to wait.
         */
        //WebDriverWait wait = new WebDriverWait(driver, 5);  // this is just a setup statement, it does not do anything unless we provide the .until

        /*
        Explicit wait with FluentWait wait example. Fluent wait is more flexible than WebDriverWait class.
        Each FluentWait instance defines the maximum amount of time to wait for a condition, as well as the frequency with which to check the condition. Furthermore, the user may configure the wait to ignore specific types of exceptions whilst waiting, such as NoSuchElementExceptions when searching for an element on the page.
         */
        FluentWait wait = new FluentWait(this.driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))        // how ofter to check for the element.
                .ignoring(NoSuchElementException.class);    // if we try to find the element before it is there, ignore the exception.

        wait.until(ExpectedConditions.invisibilityOf(this.driver.findElement(loadingIndicator)));   // sets the conditions to be fulfilled in order to continue.
        // The driver will wait for the timeout specified, for the loadingIndicator to disappear. If the loadingIndicator disappears withing 5 sec
        // the program will continue, or else it will throw an exception.


    }

    public String getLoadedText() {
        return this.driver.findElement(loadedText).getText();
    }
}
