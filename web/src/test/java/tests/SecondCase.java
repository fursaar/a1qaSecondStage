package tests;

import forms.FirstCardPage;
import forms.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondCase extends BaseTest{
    @Test
    public void secondCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        Assert.assertTrue(mainPage.state().waitForDisplayed());
        mainPage.clickToStartLink();

        Assert.assertTrue(firstCardPage.state().waitForDisplayed());
        firstCardPage.hideHelpForm();
        Assert.assertTrue(firstCardPage.waitForHelpFormClosing());

    }
}
