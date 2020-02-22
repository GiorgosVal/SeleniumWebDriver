package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class HorizontalSliderPage {

    private WebDriver driver;

    private By sliderInput = By.tagName("input");
    private By sliderValue = By.id("range");

    public HorizontalSliderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void moveSliderWithArrows(float valueToReach) {
        WebElement slider = this.driver.findElement(sliderInput);
        slider.click();
        float sliderCurrentValue = Float.parseFloat(getSliderValue());
        int direction = inferDirection(sliderCurrentValue, valueToReach);

        if(direction != 0) {
            Keys key = (direction == 1) ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

            while (Float.parseFloat(getSliderValue()) != valueToReach) {
                slider.sendKeys(key);
            }
        }
    }

    public String getSliderValue() {
        return this.driver.findElement(sliderInput).getAttribute("value");
    }

    public String getSliderSpanValue() {
        return this.driver.findElement(sliderValue).getText();
    }

    private int inferDirection(float currentValue, float valueToReach) {
        int direction = 0;
        if (valueToReach > currentValue) direction = 1;
        if (valueToReach < currentValue) direction = -1;
        return direction;
    }
}
