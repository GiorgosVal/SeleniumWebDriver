# Selenium Web Driver
This repo was created during the [course](https://testautomationu.applitools.com/selenium-webdriver-tutorial-java/) of [Test Automation University](https://testautomationu.applitools.com/) about the Selenium Web Driver and it is used to note the very first steps for using this framework.

ChromeDriver 80.0.3987.106 was used (executable file inside the resources directory). To find out which version should be used a) check the current version of Google Chrome b) download the according executable file from [ChromeDriver downloads](https://chromedriver.chromium.org/downloads).

## QuickStart
* Download the ChromeDriver exec and unzip it inside the resourses directory.
* Add the Maven [dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver) for Selenium Chrome Driver inside the ```pom.xml```.
* Add the Maven [dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-support) for Selenium Support inside the ```pom.xml``` (Selenium Support offers classes such as ```Select``` which allows to select elements from dropdown menus, etc).
* Inside your test class use the ```System.setProperty("webdriver.chrome.driver", "resources/chromedriver");``` to set the system property for the Selenium to look for.
* Create an instance of the WebDriver interface through it's implementing classes (ChromeDriver, EdgeDriver, EventFiringWebDriver, FirefoxDriver, InternetExplorerDriver, OperaWebDriver,
         RemoteWebDriver, SafariDriver).

## The Page Object Model design pattern
In test automation projects there are typically two layers: the **framework** layer and the **test** layer. The framework layer is all the coding that's done under the covers of the application (all the interactions with the web browser). The test layer is the actual tests that are executed.

The way the Page Object Model design pattern works is that for every page of the application, a new class is created in the framework section of the project. For every element of the page that we want to interact with we create a private instance variable of type ```By``` and we instantiate it according to the ```By``` method that we want to use. To interact with the element we declare public methods. If the interaction results in browsing to a different page, then the method return type should be of the same type of the class that represents that page.

For an example implementation of the POM design pattern on a web site, please refer to the Appendix A.

## Instantiation of the WebDriver
The instantiation happens in two steps:
- Setting the system property for the driver to look at the driver exe.
- Instantiating the WebDriver interface through it's implementing classes, here ChromeDriver class (other classes are EdgeDriver, EventFiringWebDriver, FirefoxDriver, InternetExplorerDriver, OperaWebDriver, RemoteWebDriver, SafariDriver).
```java
System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
WebDriver driver = new ChromeDriver();
```
## Opening and closing the WebDriver
Opening and closing the browser is easy through the following methods.

Method | Return type | Description
------ | ----------- | -----------
```get(String url)``` | ```void``` | Opens the browser to the specified url
```close()``` | ```void``` | Closes the browser **but not*** the session
```quit()``` | ```void``` | Closes the browser **and** the session

## Geting and handling the Window
Since we open the browser, we can get info about the window, or set it's dimensions:

```java
Window window  = driver.manage().window();
```
The Window object has the following methods:

Method | Return type | Description
------ | ----------- | -----------
```maximize()``` | ```void``` | Maximizes the window.
```fullscreen()``` | ```void```| Sets the window to full screen.
```setSize(Dimension dimension)``` | ```void``` | Sets the dimensions of the window. It accepts a Dimension object which represents the with and hight in pixels. e.g. ```window.setSize(new Dimension(375, 812))``` (iPhone X dimensions)
```getSize()``` | ```Dimension``` | Gets the dimensions of the window
```setPosition(Point point)``` | ```void``` | Sets the position of the window relatively to the upper left corner of the browser. Accepts a Point object which holds the x, y coordinates. e.g. ```window().setPosition(new Point(100, 400))```
```getPosition()``` | ```Point``` | Gets the position of the window


## Locating elements
Since the WebDriver is instantiated, there are two methods for finding elements inside the web page:

Method | Return type
------ | -----------
```findElement(By by)``` | ```WebElement```
```findElements(By by)``` | ```List<WebElement>```

The ```By``` class offers various ways to search for the element such as:
* ```By.linkText(String linkText)```
* ```By.tagName(String tagName)```
* ```By.cssSelector(String cssSelector)```
* ```By.id(String id)```
* ```By.name(String name)```
* ```By.xpath(String xpathExpression)```
* ```By.partialLinkText(String partialLinkText)```

**Notice that** the above two methods return the child element(s) of the parent element. So if the parent element is the driver, the methods will search all the DOM. If the parent element is another element, the methods will search only inside this element.

### NoSuchElementException
While searching for an element, there is always the possibility that this element does not exist. In such case the exception ```org.openqa.selenium.NoSuchElementException``` is thrown.

## Interacting with elements
### Basic interactions
Since we've got a WebElement, there are various methods to interact with it:

Method | Return type | Description
------ | ----------- | -----------
```click()``` | ```void``` | Clicks the selected element (e.g. a submit button, a link, a checkbox, a radio button, etc).
```sendKeys(CharSequence ... charSequence)``` | ```void``` | Writes a text inside an input field.
```getTagName()``` | ```String``` | Gets the tag name of the element (e.g. for a ```<a>``` element it will return ```a```.
```getText()``` | ```String``` | Returns the text that the element contains. e.g. In a ```<div>some text</div>``` it will return "some text".
```getAttribute(String s)``` | ```String``` | Gets the value of the attribute described.
```getCssValue(String s)``` | ```String``` | Gets the value of the css property described.
```getSize()``` | ```Dimension``` | Returns a ```Dimension``` object that represents the dimensions of the element.
```getLocation()``` | ```Point``` | Returns a ```Point``` object which represents the position of the element.
```getRect()``` | ```Rectangle``` | Returns a ```Rectangle``` object which represents the dimensions of the element and also the (x,y) coordinates that the element starts in the browser.
```isDisplayed()``` | ```boolean``` | Returns ```true``` if the element is displayed.
```isEnabled()``` | ```boolean``` | Returns ```true``` if the element is enabled.
```isSelected()``` | ```boolean``` | Returns ```true``` if the element is selected.

### Interacting with Dropdown elements
Selenium has a special class ```Select``` that makes it posible to interact with dropdowns. This class is included inside the package ```org.openqa.selenium.support.ui``` (Selenium Support dependency).

As long as the WebElement representing a dropdown is found, we can create a ```Select``` object that will actually hold this dropdown element:
```java
Select dropdown = new Select(WebElement webElement);
```
The newly created object offers plenty of new methods to interact with it:

Method | Return type | Description
------ | ----------- | -----------
```getAllSelectedOptions()``` | ```List<WebElement>``` |
```getFirstSelectedOption()``` | ```WebElement``` |
```getOptions()``` | ```List<WebElement>``` |
```getWrappedElement()``` | ```WebElement``` |
 | |
```selectByIndex()``` | ```void``` |
```selectByValue()``` | ```void``` |
```selectByVisibleText()``` | ```void``` |
 | |
```deselectByIndex()``` | ```void``` |
```deselectByValue()``` | ```void``` |
```deselectByVisibleText()``` | ```void``` |
```deselectAll()``` | ```void``` |
 | |
```isMultiple()``` | ```boolean``` |

### Advanced interactions
To interact with elements with advanced interactions such as hovering, drag and drop, double clicking, click and wait, etc, Selenium provides the **```Actions```** class. The Actions class is instantiated by passing the WebDriver.
```java
Actions actions = new Actions(driver);
```
An Actions object offers plenty of methods for advanced interactions with the web elements.

**Notice that** the Actions follows the builder pattern, meaning that **method chaining is possible**. When all chaining is completed, for the action to take effect we last chain the **```.perform()```** method (or else the .build() method which returns an Action object, and on it we call .perform()). e.g.:
```java
actions.moveToElement(figure).perform();
```
More methods

Method | Return type | Description
------ | ----------- | -----------
```moveToElement(WebElement target)``` | ```Actions``` |
```moveToElement(WebElement target, int xOffset, int yOffset)``` | ```Actions``` |
```moveByOffset(int xOffset, int yOffset)``` | ```Actions``` |
```dragAndDrop(WebElement source, WebElement target)``` | ```Actions``` |
```dragAndDropBy(WebElement source, int xOffset, int yOffset)``` | ```Actions``` |
 | |
```click()``` | ```Actions``` |
```click(WebElement target)``` | ```Actions``` |
```doubleClick()``` | ```Actions``` |
```doubleClick(WebElement target)``` | ```Actions``` |
```contextClick()``` | ```Actions``` |
```contextClick(WebElement target)``` | ```Actions``` |
```clickAndHold()``` | ```Actions``` |
```clickAndHold(WebElement target)``` | ```Actions``` |
```release()``` | ```Actions``` |
```release(WebElement target)``` | ```Actions``` |
```pause(long pause)``` | ```Actions``` |
```pause(Duration duration)``` | ```Actions``` |
```tick(Action action)``` | ```Actions``` |
```tick(Interaction... actions)``` | ```Actions``` |
 | | 
```keyDown(CharSequence key)``` | ```Actions``` |
```keyDown(WebElement target, CharSequence key)``` | ```Actions``` |
```keyUp(CharSequence key)``` | ```Actions``` |
```keyUp(WebElement target, CharSequence key)``` | ```Actions``` |
```sendKeys(CharSequence... key)``` | ```Actions``` |
```sendKeys(WebElement target, CharSequence... key)``` | ```Actions``` |
 | |
```perform()``` | ```void``` |
```build()``` | ```Action``` |

### Writing text
Both `WebElement` class and `Actions` class offer the `sendKeys(CharSequence ... charSequence)` method for writing text in input fields. This method ofcourse can accept any `String`, but Selenium WebDriver comes with a special [`enum Keys`](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/Keys.html) which offers some out-of-the-box functionality for pressing keys such as ALT, BACK_SPACE, SHIFT, F1, NUMPAD0, etc. The `Keys enum` implements `java.lang.CharSequence` so it can be passed as an argument in the `sendKeys()` method.

A very usefull feature of `Keys enum` is the method `chord(CharSequence ... charSequence)` with a return type of `String` which allows to combine keys, like SHIFT+p, etc. Some valid examples:

`sendKeys("a string");`

The above will type 'a string'.

`sendKeys("some other string", Keys.BACK_SPACE);`

The above will type 'a string' and press the back space button which will erase the 'g'.

`sendKeys(Keys.chord(Keys.SHIFT, "p"));`

The above will type combine the keys SHIFT and 'p' to write an 'P'.

`sendKeys(Keys.chord(Keys.SHIFT, "p") + " some string");`

The above will type 'P some string'.

`sendKeys(Keys.chord(Keys.SHIFT, "p"), " some string", Keys.BACK_SPACE, Keys.chord(Keys.SHIFT, "g"));`

The above will type 'P some strinG'.

### Interacting with JavaScript alerts
JavaScipt alerts are not part of the DOM, and this means that they cannot be located directly using the `By` class. To locate and interact with JS alerts we use the `switchTo()` method of the `WebDriver` which returns a `TargetLocator`. On the `TargetLocator` object we use the `alert()` method which returns an `Alert` object.
```java
Alert alert = this.driver.switchTo().alert();
```
The `Alert` class offers the following methods to interact with the alert object:

Method | Return type | Description
------ | ----------- | -----------
`accept()` | `void` | Clicks on the OK button of the alert.
`dismiss()`| `void` | Clicks the Cancel button of the alert.
`sendKeys(String s)` | `void` | Writes text to the input field of the alert.
`getText()` | `String` | Returns the text of the alert.

### Interacting with FileUploads
File upload input elements open a window to choose a file to e uploaded. This window is part of the OS, and not of the DOM, so it is impossible to interact with it. To solve this issue, we skip this step by sending directy the absolute path of the file we want to upload to the input field.
```java
driver.findElement(inputField).sendKeys("abslute/path/of/file/myfile.txt");
```

### Interacting with Modals
Unlike alerts, modals are part of the DOM, and this means that as long as a modal is visible, it is possible to locate it and interact with it.

**Notice that** when a modal is visible, it is impossible to interact with anything on the page besides the modal. Attempting to do so will throw an exception.

## Appendix A Example implementation of POM design pattern

The HomePage class

```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private By formAuthentication = By.linkText("Form Authentication");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPage clickFormAuthentication() {
        this.driver.findElement(formAuthentication).click();
        return new LoginPage(this.driver);
    }
}
```

The LoginPage
```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("#login button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        this.driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        this.driver.findElement(passwordField).sendKeys(password);
    }

    public SecureAreaPage clickLoginButton() {
        this.driver.findElement(loginButton).click();
        return new SecureAreaPage(this.driver);
    }
}
```
The SecureAreaPage class
```java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {

    private WebDriver driver;
    private By statusAlert = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    private String getAlertText(){
        return this.driver.findElement(statusAlert).getText();
    }
}
```
## Cool Sites
[Test Automation University](https://testautomationu.applitools.com/) - Free Test Automation courses

[The Internet](https://the-internet.herokuapp.com/) - A website with all possible web elements for UI testing.

[Formy](https://formy-project.herokuapp.com/) - Another websote for UI Testing with web elements especially used in forms.

## Design patterns
Below are listed some design patterns that are especially beneficial for test automation projects:
- Page Object Model
- Screenplay
- Fluent
- Builder
- Singleton
- Factory
Facade
