
# About
This repo was created during the [course](https://testautomationu.applitools.com/selenium-webdriver-tutorial-java/) of [Test Automation University](https://testautomationu.applitools.com/) about the Selenium Web Driver and it is used to note the very first steps for using this framework.

ChromeDriver 92.0.4515.107 was used (executable file inside the resources directory). To find out which version should be used:
1. check the current version of Google Chrome
2. download the according executable file from [ChromeDriver downloads](https://chromedriver.chromium.org/downloads).

# Table of Contents

- [About](#about)
- [Table of Contents](#table-of-contents)
- [QuickStart](#quickstart)
- [The Page Object Model design pattern](#the-page-object-model-design-pattern)
- [Instantiation of the WebDriver](#instantiation-of-the-webdriver)
- [Opening and closing the WebDriver](#opening-and-closing-the-webdriver)
- [Geting and handling the Window](#geting-and-handling-the-window)
- [Locating elements](#locating-elements)
  * [NoSuchElementException](#nosuchelementexception)
- [Getting information of elements](#getting-information-of-elements)
  * [Broken Images](#broken-images)
- [Interacting with elements](#interacting-with-elements)
  * [Basic interactions](#basic-interactions)
  * [Interacting with Dropdown elements](#interacting-with-dropdown-elements)
  * [Advanced interactions](#advanced-interactions)
  * [Writing text](#writing-text)
  * [Interacting with JavaScript alerts](#interacting-with-javascript-alerts)
  * [Interacting with FileUploads](#interacting-with-fileuploads)
  * [Interacting with Modals](#interacting-with-modals)
  * [Interacting with iframes](#interacting-with-iframes)
- [Wait Strategies](#wait-strategies)
  * [Implicit wait](#implicit-wait)
  * [Explicit Waits](#explicit-waits)
    + [With WebDriverWait class](#with-webdriverwait-class)
    + [With FluentWait class](#with-fluentwait-class)
- [Using JavaScript](#using-javascript)
- [Navigation](#navigation)
    + [Back, Forward, Refresh, Go to url](#back-forward-refresh-go-to-url)
  * [Switch to window or tab](#switch-to-window-or-tab)
- [Taking screenshots](#taking-screenshots)
  * [General](#general)
  * [After each failed test](#after-each-failed-test)
- [Event Listeners](#event-listeners)
- [Managing the browser](#managing-the-browser)
- [Cookies](#cookies)
- [Appendix A Example implementation of POM design pattern](#appendix-a-example-implementation-of-pom-design-pattern)
- [Cool Sites](#cool-sites)
- [Design patterns for Test Automation](#design-patterns-for-test-automation)
- [Troubleshooting](#Troubleshooting)
  * [Mac OS](#MacOS)
    * [Error: “chromedriver” cannot be opened because the developer cannot be verified.](#error-chromedriver-cannot-be-opened-because-the-developer-cannot-be-verified)

# QuickStart
* Download the ChromeDriver exec and unzip it inside the resourses directory.
* Add the Maven [dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver) for Selenium Chrome Driver inside the ```pom.xml```.
* Add the Maven [dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-support) for Selenium Support inside the ```pom.xml``` (Selenium Support offers classes such as ```Select``` which allows to select elements from dropdown menus, etc).
* Inside your test class use the ```System.setProperty("webdriver.chrome.driver", "resources/chromedriver");``` to set the system property for the Selenium to look for.
* Create an instance of the WebDriver interface through it's implementing classes (ChromeDriver, EdgeDriver, EventFiringWebDriver, FirefoxDriver, InternetExplorerDriver, OperaWebDriver,
         RemoteWebDriver, SafariDriver).

# The Page Object Model design pattern
In test automation projects there are typically two layers: the **framework** layer and the **test** layer. The framework layer is all the coding that's done under the covers of the application (all the interactions with the web browser). The test layer is the actual tests that are executed.

The way the Page Object Model design pattern works is that for every page of the application, a new class is created in the framework section of the project. For every element of the page that we want to interact with we create a private instance variable of type ```By``` and we instantiate it according to the ```By``` method that we want to use. To interact with the element we declare public methods. If the interaction results in browsing to a different page, then the method return type should be of the same type of the class that represents that page.

For an example implementation of the POM design pattern on a web site, please refer to the Appendix A.

# Instantiation of the WebDriver
The instantiation happens in two steps:
- Setting the system property for the driver to look at the driver exe.
- Instantiating the WebDriver interface through it's implementing classes, here ChromeDriver class (other classes are EdgeDriver, EventFiringWebDriver, FirefoxDriver, InternetExplorerDriver, OperaWebDriver, RemoteWebDriver, SafariDriver).
```java
System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
WebDriver driver = new ChromeDriver();
```
# Opening and closing the WebDriver
Opening and closing the browser is easy through the following methods.

Method | Return type | Description
------ | ----------- | -----------
```get(String url)``` | ```void``` | Opens the browser to the specified url
```close()``` | ```void``` | Closes the window **but not necessarily** the session
```quit()``` | ```void``` | Closes all windows **and** the session

# Geting and handling the Window
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


# Locating elements
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

# Getting information of elements
## Broken Images
Assuming that we want to check if an image is broken (not loaded). One convenient way is to check the
`naturalWidth` attribute of that image:
```java
private List<WebElement> images = this.driver.findElement(imageContainer).findElements(imagesLocator);
int brokenImagesCounter = 0;
for (WebElement image : images) {
  if (image.getAttribute("naturalWidth").equals("0")) {
    brokenImagesCounter++;
  }
}
```

## NoSuchElementException
While searching for an element, there is always the possibility that this element does not exist. In such case the exception ```org.openqa.selenium.NoSuchElementException``` is thrown.

# Interacting with elements
## Basic interactions
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

## Interacting with Dropdown elements
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

## Advanced interactions
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

## Writing text
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

## Interacting with JavaScript alerts
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

## Interacting with FileUploads
File upload input elements open a window to choose a file to e uploaded. This window is part of the OS, and not of the DOM, so it is impossible to interact with it. To solve this issue, we skip this step by sending directy the absolute path of the file we want to upload to the input field.
```java
driver.findElement(inputField).sendKeys("abslute/path/of/file/myfile.txt");
```

## Interacting with Modals
Unlike alerts, modals are part of the DOM, and this means that as long as a modal is visible, it is possible to locate it and interact with it.

**Notice that** when a modal is visible, it is impossible to interact with anything on the page besides the modal. Attempting to do so will throw an exception.

## Interacting with iframes
Iframes are pages inside a page, so in order to interact with an iframe the WebDriver must first switch to this page. As long as this happens, we can interact with the iframe just like any other page. To switch to an iframe and switch back to parent Selenium WebDriver offers the following methods:

Method | Return type | Description
------ | ----------- | -----------
`switchTo().frame(String id)` | `WebDriver` | Selects the iframe by id
`switchTo().frame(WebElement target)` | `WebDriver` | Select the iframe by a WebElement
`switchTo().frame(int i)` | `WebDriver` | Selects the iframe by it's index (in case of multiple iframes). It is zero-based.
`switchTo().parentFrame()` | `void` | Switches back to the parent frame.

**Good practice tip 1:** In each interaction method with an iframe make sure you switch back to the parent frame. This way each interaction will always start from the parent element, and we avoid the possibility of NoSuchElementException.

**Good practice tip 2:** Consider returning a new Page class for each nested iframe (according to the POM design pattern).

# Wait Strategies
When it is about interacting with elements that may delay to load, or load dynamically, Selenium WebDriver offers three wait strategies, a) implicit wait, b) explicit wait with `WebDriverWait` class and c) explicit wait with `FluentWait` class.

## Implicit wait
Implicit wait is applied to all the elements of the page, in every interaction. Every time the WebDriver needs to interract with any element and the element is not available, then the driver will wait for up to a specified time. If the specified time passes, then an exception is thrown. It is recommended to use when the elements are located with the time frame specified in implicit wait.

Examples:
```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
// Will wait for up to 30 seconds in each interaction
driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
// Will wait for up to 5 seconds until a page loads
driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
// Will wait for up to 5 seconds for asynchronous scripts
```

## Explicit Waits
Explicit waits offer more flexibility as they are applied to specific elements and are recommended to use when the elements are taking long time to load and we also want to verify a property of the element (e.g. it's visibility).

### With WebDriverWait class
With the WebDriverWait class it is possible to specify the wait timeout and the expected conditions that should be met in order for the WebDriver to proceed with an element. For example, to wait for 5 seconds until an element disappears (if the element disappears within 5 seconds the WebDriver proceeds, or else an exception is thrown).

Example:

```java
WebDriverWait wait = new WebDriverWait(driver, 5);
// where 5 is the timeout in seconds

wait.until(ExpectedConditions.invisibilityOf(webElement));
```
### With FluentWait class
It is even more flexible than the WebDriverWait class, allowing also to specify how ofter the WebDriver should try to poll for the element, and what exceptions should ignore during this.

Example:

```java
FluentWait wait = new FluentWait(driver)
         .withTimeout(Duration.ofSeconds(5))
         .pollingEvery(Duration.ofSeconds(1))
         .ignoring(NoSuchElementException.class);

wait.until(ExpectedConditions.invisibilityOf(webElement));
```

# Using JavaScript
Selenium WebDriver provides a way to allow the execution of JavaScript in the browser. To do this, simply cast the `WebDriver` to a `JavascriptExecutor` class and use the method `executeScript(String script, Object... objects)` or the method `executeAsyncScript(String script, Object... objects)`.

Assuming that there is an element with id 'dropdown', then the following two examples are equivalent:
```java
String script = "document.querySelector('#dropdown').setAttribute('multiple', '')";
((JavascriptExecutor)driver).executeScript(script);
```
```java
Select dropdown = new Select(this.driver.findElement(By.id("dropdown")));
String script = "arguments[0].setAttribute('multiple', '')";
((JavascriptExecutor)driver).executeScript(script, dropdown);
// With arguments[0] we refer to the 1st Object that is passed as an argument inside the executeScript() method.
```

# Navigation

### Back, Forward, Refresh, Go to url
Through `WebDriver` class we can access the `Navigation` class with `driver.navigate()` which offers the following methods for navigating back and forth or to another location:

Method | Return type | Description
------ | ----------- | -----------
`back()` | `void` |
`forward()` | `void` |
`refresh()` | `void` |
`to(String url)` | `void` |
`to(Url url)` | `void` |

**Notice that** `driver.get(String url)` and `driver.navigate().to(String url)` are different in that the `get()` method waits for the page to load while the `to()` method does not.

## Switch to window or tab
Selenium WebDriver provides the already open windows or tabs in a Set of Strings:

```java
Set<String> windows = this.driver.getWindowHandles();
```
Each window String of the above set will be like this: `CDwindow-14FF467D2FAB379F0B652FC0B522853D`, with the hash number to be **non deterministic** (in every run it will differ).

In order to switch to a specific window, the above window String must be passed to the following method:

```java
driver.switchTo().window(window);
```
But since the window string is non deterministic, how do we know in which window to switch to? Literally, we don't. The only option is that we iterate through the entire `Set<String> windows` and for every `window` we will switch the driver to this window. The `driver.getTitle()` method in every switch will return the name of the current window. Thus, the breaking condition is that the current window value must be equal with a predefined value (`tabTitle.equals(this.driver.getTitle())`).


# Taking screenshots
## General
To take screenshots Selenium offers the interface `TakeScreenshot` in which the `WebDriver` object need to be cast to. After casting, the "camera" object has only one method `getScreenshotAs(OutputType<X> outputType)`. For example:

```java
TakesScreenshot camera = (TakesScreenshot)driver;
File screenshot = camera.getScreenshotAs(OutputType.FILE);     // saves it as java.io.File
```
The newly created screenshot file wll be stored in some directory which can be found with `screenshot.getAbsolutePath()`. To move the file to another directory (e.g. a sub directory inside our resources derectory) we can use the `com.google.common.io.Files.move(File from, File to)` method. For example:

```java
Files.move(screenshot, new File("resources/screenshots/" + result.getInstanceName() + "." + result.getName() + ".png"));
```
The above line will move the file to the directory `resources/screenshots/` and also rename it to be like `package.TestClass.TestMethod.png`.

## After each failed test
To take a screenshot after each failed test, we can use the `org.testng.ITestResult` interface and pass it as a parameter in a `@AfterMethod`. With the result object we can check if the `result.getStatus()` is equal to `ITestResult.FAILURE` and if this is true, to take a screenshot. For example:

```java
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
```

# Event Listeners
Selenium offers an interface called `WebDriverEventListener`, which provides methods that will listen for Selenium events and allows us to add additional functionality when those events occur. To implement this interface simply we make a class that implements it, like:
```java
public class EventReporter implements WebDriverEventListener {
    // implementing all interface methods
}
```
This is our Listener, or else our *Observer*, so according to the Observer Design Pattern, we need to register it to some sort of *Subject*. The *Subject* in our case is the driver, not the `WebDriver` but the `EventFiringWebDriver` class.

The `EventFiringWebDriver` class is a more powerfull version of `WebDriver` which offers more capabilities as it `implements WebDriver, JavascriptExecutor, TakesScreenshot, WrapsDriver, HasInputDevices, HasTouchScreen, Interactive, HasCapabilities`.

If we look the source code of `EventFiringWebDriver` we see that it is indeed implementing the Observer Design Pattern:
```java
// Selenium WebDriver source code
private final List<WebDriverEventListener> eventListeners = new ArrayList();
...
public EventFiringWebDriver register(WebDriverEventListener eventListener) {...}
...
public EventFiringWebDriver unregister(WebDriverEventListener eventListener) {...}
```

So instead of instantiating the WebDriver like,
```java
WebDriver driver = new ChromeDriver();
```
we can do this like below, while registering a Listener:

```java
EventFiringWebDriver driver = new EventFiringWebDriver(new ChromeDriver());
driver.register(new EventReporter());
```
In this way, the `EventFiringWebDriver` will trigger events and notify (invoke methods of) the `EventReporter` class.

# Managing the browser
Selenium offers the `ChromeOptions` class that allows us to make changes to the browser instance that is launched. For example:
* We can `addArguments(String arg)` ~~such as `disable-infobars` if we want the info bar not to be visible~~ (disabled by GoogleChrome), and other [arguments](https://peter.sh/experiments/chromium-command-line-switches/)
* We can `setHeadless(Boolean boolean)` if we want our tests to run in the background without opening an instance of the browser (this offers faster execution).
* We can `setBinary(File file)` of the browser if we want to use a different binary
* and many more...

# Cookies
Selenium offers the `Cookie` class if we want to build a cookie. To send/get a cookie we can use the `Options` class (`driver.manage()`) and from `Options` class we have many options:

Method | Return type | Description
------ | ----------- | -----------
`addCookie(Cookie cookie)` | `void` |
`getCookieNamed(String name)` | `void` |
`getCookies()` | `Set<Cookie>` |
`deleteCookie(Cookie cookie)` | `void` |
`deleteCookieNamed(String name)` | `void` |
`deleteAllCookies()` | `void` |

To build a cookie it's easy:
```java
Cookie cookie new Cookie.Builder(String name, String value)
                .domain(String host)
                .expiresOn(Date expiry)
                .path(String path)
                .isSecure(boolean secure)
                .isHttpOnly(boolean httpOnly)
                .build();
```


# Appendix A Example implementation of POM design pattern

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
# Cool Sites
[Test Automation University](https://testautomationu.applitools.com/) - Free Test Automation courses

[The Internet](https://the-internet.herokuapp.com/) - A website with all possible web elements for UI testing.

[Formy](https://formy-project.herokuapp.com/) - Another websote for UI Testing with web elements especially used in forms.

# Design patterns for Test Automation
Below are listed some design patterns that are especially beneficial for test automation projects:
- Page Object Model
- Screenplay
- Fluent
- Builder
- Singleton
- Factory
Facade

# Troubleshooting
## MacOS
### Error: “chromedriver” cannot be opened because the developer cannot be verified.

1. Open terminal
2. Navigate to path where your chromedriver file is located
3. Execute any one of the below commands

**Command1:** `xattr -d com.apple.quarantine <name-of-executable>`

Example

```bash
/usr/local/Caskroom/chromedriver 
$ xattr -d com.apple.quarantine chromedriver
``` 
(or)

**Command2:** `spctl --add --label 'Approved' <name-of-executable>`

>**Note:** This will work only with the file(s) where the above command is executed. If a new 
>chromedriver is downloaded then the command has to be executed again on the newly downloaded file

Sources:
- [StackOverflow](https://stackoverflow.com/questions/60362018/macos-catalinav-10-15-3-error-chromedriver-cannot-be-opened-because-the-de)
- [Upgrading to Catalina](https://docwhat.org/upgrading-to-catalina)