package tests;

import forms.FirstCardPage;
import forms.MainPage;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThirdCase extends BaseTest{
    @Test
    public void thirdCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        Assert.assertTrue(mainPage.state().waitForDisplayed());
        mainPage.clickToStartLink();

        Assert.assertTrue(firstCardPage.state().waitForDisplayed());
        firstCardPage.acceptCookies();
        Assert.assertTrue(firstCardPage.isCookiesFormClosed());
    }
}
