# Selenium Web Driver
This repo was created during the [course](https://testautomationu.applitools.com/selenium-webdriver-tutorial-java/) of [Test Automation University](https://testautomationu.applitools.com/) about the Selenium Web Driver and it is used to note the very first steps for using this framework.

ChromeDriver 80.0.3987.106 was used (executable file inside the resources directory). To find out which version should be used a) check the current version of Google Chrome b) download the according executable file from [ChromeDriver downloads](https://chromedriver.chromium.org/downloads).

## QuickStart
* Download the ChromeDriver exec and unzip it inside the resourses directory.
* Add the Maven [dependency](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver) for Selenium Chrome Driver inside the ```pom.xml```.
* Inside your test class use the ```System.setProperty("webdriver.chrome.driver", "resources/chromedriver");``` to set the system property for the Selenium to look for.
* Create an instance of the WebDriver interface through it's implementing classes (ChromeDriver, EdgeDriver, EventFiringWebDriver, FirefoxDriver, InternetExplorerDriver, OperaWebDriver,
         RemoteWebDriver, SafariDriver).

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

### NoSuchElementException
While searching for an element, there is always the possibility that this element does not exist. In such case the exception ```org.openqa.selenium.NoSuchElementException``` is thrown.

## Interacting with elements
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


## The Page Object Model design pattern
In test automation projects there are typically two layers: the **framework** layer and the **test** layer. The framework layer is all the coding that's done under the covers of the application (all the interactions with the web browser). The test layer is the actual tests that are executed.

The way the Page Object Model design pattern works is that for every page of the application, a new class is created in the framework section of the project. For every element of the page that we want to interact with we create a private instance variable of type ```By``` and we instantiate it according to the ```By``` method that we want to use. To interact with the element we declare public methods. If the interaction results in browsing to a different page, then the method return type should be of the same type of the class that represents that page.

example structure

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
