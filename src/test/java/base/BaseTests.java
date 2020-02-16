package base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        // Closes the browser BUT NOT the session.
        //webDriver.close();
        // Closes the browser AND the session.
        //webDriver.quit();
    }

    public static void main(String args[]) {
        BaseTests baseTests = new BaseTests();
        baseTests.setup();
    }
}
