package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
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
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
