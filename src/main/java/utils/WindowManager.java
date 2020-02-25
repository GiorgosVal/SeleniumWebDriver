package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowManager {

    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public WindowManager (WebDriver driver) {
        this.driver = driver;
        navigate = driver.navigate();
    }

    public void goBack() {
        navigate.back();
    }

    public void goForward() {
        navigate.forward();
    }

    public void refreshPage() {
        navigate.refresh();
    }

    public void goTo(String url) {
        navigate.to(url);
    }

    public void switchToTab(String tabTitle) {
        Set<String> windows = this.driver.getWindowHandles();

        System.out.println("Number of tabs: " + windows.size());
        System.out.println("Window handles:");
        windows.forEach(System.out::println);

        for(String window : windows) {
            System.out.println("Current window title: " + this.driver.getTitle());

            if (!tabTitle.equals(this.driver.getTitle())) {
                System.out.println("Switching to window: " + window);
                this.driver.switchTo().window(window);
            } else {
                System.out.println("Reached destination window.");
                break;
            }
        }
    }
}
