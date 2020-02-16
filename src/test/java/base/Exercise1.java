package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Exercise1 {

    public static final String url = "https://the-internet.herokuapp.com/";
    WebDriver driver;

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.get(url);
    }

    public void getShiftingContent() {
        WebElement element = this.driver.findElement(By.linkText("Shifting Content"));
        element.click();
        element = this.driver.findElement(By.linkText("Example 1: Menu Element"));
        element.click();
        List<WebElement> elementList = this.driver.findElements(By.cssSelector("ul li"));
        System.out.println(elementList.size());

    }
}
