package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void init() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo("https://userinyerface.com/");

    }

    @AfterMethod
    public void terminate() {
        AqualityServices.getBrowser().quit();
    }
}
