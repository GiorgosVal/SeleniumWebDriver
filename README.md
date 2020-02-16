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
