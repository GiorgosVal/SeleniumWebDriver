package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickFormAuthentication() {
        clickLink("Form Authentication");
        return new LoginPage(this.driver);
    }

    public DropdownPage clickDropdown(){
        clickLink("Dropdown");
        return new DropdownPage(this.driver);
    }
    public ForgotPasswordPage clickForgotPassword() {
        clickLink("Forgot Password");
        return new ForgotPasswordPage(this.driver);
    }

    public HoversPage clickHovers() {
        clickLink("Hovers");
        return new HoversPage(this.driver);
    }

    public KeyPressesPage clickKeyPresses() {
        clickLink("Key Presses");
        return new KeyPressesPage(this.driver);
    }

    public HorizontalSliderPage clickHorizontalSliderPage() {
        clickLink("Horizontal Slider");
        return new HorizontalSliderPage(this.driver);
    }

    public AlertsPage clickJavaScriptAlerts() {
        clickLink("JavaScript Alerts");
        return new AlertsPage(this.driver);
    }

    public FileUploadPage clickFileUpload() {
        clickLink("File Upload");
        return new FileUploadPage(this.driver);
    }

    public ContextMenuPage clickContextMenu() {
        clickLink("Context Menu");
        return new ContextMenuPage(this.driver);
    }

    public WysizygEditorPage clickWysizygEditor() {
        clickLink("WYSIWYG Editor");
        return new WysizygEditorPage(this.driver);
    }

    public NestedFramesPage clickNestedFrames() {
        clickLink("Nested Frames");
        return new NestedFramesPage(this.driver);
    }

    public DynamicLoadPage clickDynamicLoad() {
        clickLink("Dynamic Loading");
        return new DynamicLoadPage(this.driver);
    }

    public LargeAndDeepDOMPage clickLargeAndDeepDOM() {
        clickLink("Large & Deep DOM");
        return new LargeAndDeepDOMPage(this.driver);
    }

    public InfiniteScrollPage clickInfiniteScroll() {
        clickLink("Infinite Scroll");
        return new InfiniteScrollPage(this.driver);
    }

    public MultipleWindowsPage clickMultipleWindows() {
        clickLink("Multiple Windows");
        return new MultipleWindowsPage(this.driver);
    }

    public BrokenImages clickBrokenImages() {
        clickLink("Broken Images");
        return new BrokenImages(this.driver);
    }

    private void clickLink(String linkText) {
        this.driver.findElement(By.linkText(linkText)).click();
    }
}
