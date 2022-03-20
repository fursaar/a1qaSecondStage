package tests;

import forms.FirstCardPage;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FourthCase extends BaseTest{
    @Test
    public void fourthCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        Assert.assertTrue(mainPage.state().waitForDisplayed());
        mainPage.clickToStartLink();

        Assert.assertTrue(firstCardPage.state().waitForDisplayed());
        Assert.assertEquals(firstCardPage.getTextFromTimer(), "00:00:00");
    }
}
