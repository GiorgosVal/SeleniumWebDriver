package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrokenImages {

    private WebDriver driver;
    private By imageContainer = By.className("example");
    private By imagesLocator = By.tagName("img");
    private List<WebElement> images;

    BrokenImages(WebDriver driver) {
        this.driver = driver;
    }

    private void getImages() {
        this.images = this.driver.findElement(imageContainer).findElements(imagesLocator);
    }

    public int countBrokenImages() {
        if (images == null) {
            getImages();
        }

        int brokenImagesCounter = 0;
        for (WebElement image : images) {
            if (image.getAttribute("naturalWidth").equals("0")) {
                brokenImagesCounter++;
            }
        }
        return brokenImagesCounter;
    }

}
