package base;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.CookieManager;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    private EventFiringWebDriver driver;
    //private WebDriver driver;
    protected HomePage homePage;
    protected CookieManager cookieManager;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        //driver = new ChromeDriver();
        driver.register(new EventReporter());
        //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);   // how much time to wait for the page to load
        //driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);  // how much time to wait for asynchronous scripts
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goHome();
        setCookie();
        cookieManager = new CookieManager(driver);
        /*
        System.out.println(webDriver.getTitle());                   // Gets and prints the title of the web page.
         */
    }

    @BeforeMethod
    public void goHome() {
        driver.get("https://the-internet.herokuapp.com/");
        homePage = new HomePage(driver);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {

        if(ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("resources/screenshots/" + result.getInstanceName() + "." + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WindowManager getWindowManager() {
        return new WindowManager(this.driver);
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");     // deprecated
        chromeOptions.setHeadless(false);    // runs tests with no opening GUI of browser
        return chromeOptions;
    }

    private void setCookie() {
        Cookie cookie = new Cookie.Builder("giorgos", "123")
                .domain("the-internet.herokuapp.com")      // the domain which will save the cookie
                .build();

        driver.manage().addCookie(cookie);
    }
}
