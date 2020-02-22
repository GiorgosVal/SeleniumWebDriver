package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        goHome();
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
}
