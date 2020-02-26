package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CookieManager {

    private WebDriver driver;

    public CookieManager(WebDriver driver) {
        this.driver = driver;
    }


    public Cookie getCookie(String cookie) {
        return driver.manage().getCookieNamed(cookie);
    }

    public void setCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void deleteCookie(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
    }

    public void deleteCookieByName(String name) {
        driver.manage().deleteCookieNamed(name);
    }


}
