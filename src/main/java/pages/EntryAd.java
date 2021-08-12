package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class EntryAd {

    private WebDriver driver;
    private By modalBox = By.id("modal");

    public EntryAd(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isModalOpen() {

        FluentWait wait = new FluentWait(this.driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))        // how ofter to check for the element.
                .ignoring(NoSuchElementException.class);    // if we try to find the element before it is there, ignore the exception.

        wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(modalBox)));
        String visibility = this.driver.findElement(modalBox).getCssValue("display");
        return visibility.equals("block");
    }

    public boolean isModalClosed() {

        String visibility = this.driver.findElement(modalBox).getCssValue("display");
        return visibility.equals("none");
    }

    public Modal getModal() {

        return new Modal(this.driver.findElement(modalBox));
    }

    public class Modal {

        private WebElement modalBox;
        private By modalFooter = By.xpath("//div[contains(@class, 'modal-footer')]");

        public Modal(WebElement modalBox) {

            this.modalBox = modalBox;
        }

        public void modal_clickFooterToClose() {

            this.modalBox.findElement(modalFooter).click();
        }
    }
}
