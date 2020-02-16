package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

public class BaseTests {

    private WebDriver driver;
    private HomePage homePage;

    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");

        homePage = new HomePage(driver);

        /*
        System.out.println(webDriver.getTitle());                   // Gets and prints the title of the web page.
        WebDriver.Window window = webDriver.manage().window();      // Gets the window element of the web page.
        window.maximize();                                          // Maximizes the window.
        window.fullscreen();                                        // ...
        window.setSize(new Dimension(375, 812));                    // iPhone X dimensions.
        webDriver.close();                                          // Closes the browser BUT NOT the session.
        webDriver.quit();                                           // Closes the browser AND the session.
         */

        driver.quit();
    }

    public static void main(String args[]) {
        BaseTests baseTests = new BaseTests();
        baseTests.setup();
    }
}


/*
The Page Object Model Pattern
In test automation projects there are typically two layers: the framework layer and the test layer.
The framework layer is all the coding that's done under the covers of the application (all the interactions with the web browser).
The test layer is the actual tests that are executed.

The way the Page Object Model design pattern works is that for every page of the application, a new class is created in the framework section of the project.
For every element of the page that we want to interact with we create a private instance variable of type By and we instantiate it according to the By method that we want to use.
To interact with the element we declare public methods. If the interaction results in browsing to a different page, then the method return type should be of the same type of the class that represents that page.






 */
