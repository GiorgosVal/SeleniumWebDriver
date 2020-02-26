package cookies;

import base.BaseTests;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import utils.CookieManager;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class CookiesTests extends BaseTests {

    @Test
    public void testExistingCookie() {
        Set<Cookie> cookies = cookieManager.getAllCookies();
        Set<Cookie> expCookies = new HashSet<>();
        expCookies.add(new Cookie.Builder("giorgos", "123").build());

        assertTrue(cookies.containsAll(expCookies));

    }
}
