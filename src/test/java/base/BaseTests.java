package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BaseTests {

    private WebDriver webDriver;

    public void setup(){
        /*
         Sets a system property for the chromedriver. The key string must be exactly 'webdriver.chrome.driver' because
         this is the property Selenium will search for.
         */
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        /*
         The instantiation of WebDriver interface is made through the implementing classes. Known implementing classes
         are: ChromeDriver, EdgeDriver, EventFiringWebDriver, FirefoxDriver, InternetExplorerDriver, OperaWebDriver,
         RemoteWebDriver, SafariDriver.
         */
        webDriver = new ChromeDriver();

        // Launch the browser to the specified page.
        webDriver.get("https://the-internet.herokuapp.com/");

        // Gets and prints the title of the web page.
        System.out.println(webDriver.getTitle());

        WebDriver.Window window = webDriver.manage().window();      // Gets the window element of the web page.
        //window.maximize();                                          // Maximizes the window.
        //window.fullscreen();                                        // ...
        window.setSize(new Dimension(375, 812));       // iPhone X dimensions.

        // The findElement method returns a single WebElement object. To locate the element we use the By class.
        // If more than one such elements exist, the very first element inside the DOM it is returned.
        WebElement inputsLink = webDriver.findElement(By.linkText("Inputs"));
        inputsLink.click(); // since we have the element we can interact with it.

        // The findElements method returns a List of WebElement objects.
        List<WebElement> links = webDriver.findElements(By.tagName("a"));    // finds all elements which have links <a>
        System.out.println(links.size());

        // Seaarching for an element which does not exist throws a org.openqa.selenium.NoSuchElementException
        String notExistingElement = "aNonExistingElement";
        try {
            webDriver.findElement(By.linkText(notExistingElement));
        } catch (NoSuchElementException e) {
            System.out.println("No element with text " + notExistingElement + " was found.");
        }



        // Closes the browser BUT NOT the session.
        //webDriver.close();
        // Closes the browser AND the session.
        //webDriver.quit();
    }

    public static void main(String args[]) {
        Exercise1 exercise1 = new Exercise1();
        exercise1.setup();
        exercise1.getShiftingContent();
    }
}
