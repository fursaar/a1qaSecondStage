package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.JsonUtil;

public class BaseTest {
    @BeforeMethod
    public void init() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(JsonUtil.configData.getValue("/baseUrl").toString());

    }

    @AfterMethod
    public void terminate() {
        AqualityServices.getBrowser().quit();
    }
}
