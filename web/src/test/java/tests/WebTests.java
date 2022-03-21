package tests;

import aquality.selenium.browser.AqualityServices;
import forms.FirstCardPage;
import forms.MainPage;
import forms.SecondCardPage;
import forms.ThirdCardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EmulateKeysUtils;
import utils.JsonUtil;
import utils.RandomUtils;

public class WebTests extends BaseTest{
    @Test
    public void firstCase() {

        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();
        SecondCardPage secondCardPage = new SecondCardPage();
        ThirdCardPage thirdCardPage = new ThirdCardPage();

        AqualityServices.getLogger().info("STEP 1");
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page page should be opened");

        AqualityServices.getLogger().info("STEP 2");
        mainPage.clickToStartLink();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");

        AqualityServices.getLogger().info("STEP 3");
        firstCardPage.enterPassword(RandomUtils.generateRandomPassword(10));
        firstCardPage.enterEmail(RandomUtils.generateRandomString(7));
        firstCardPage.enterDomain(RandomUtils.generateRandomString(5));
        firstCardPage.openDomainList();
        firstCardPage.chooseDomain(".org");
        firstCardPage.uncheckTerms();
        firstCardPage.clickToNext();
        Assert.assertTrue(secondCardPage.state().waitForDisplayed(), "Second card page should be opened");

        AqualityServices.getLogger().info("STEP 4");
        secondCardPage.unselectAllCheckboxes();
        secondCardPage.selectCheckboxByIndex(RandomUtils.generateRandomNumberInRangeExcept(1, 21, 18, 21));
        secondCardPage.selectCheckboxByIndex(RandomUtils.generateRandomNumberInRangeExcept(1, 21, 18, 21));
        secondCardPage.selectCheckboxByIndex(RandomUtils.generateRandomNumberInRangeExcept(1, 21, 18, 21));
        secondCardPage.clickUploadButton();
        EmulateKeysUtils.setClipboardData(JsonUtil.testData.getValue("/avatarFilePath").toString());
        EmulateKeysUtils.pasteClipboardData();
        secondCardPage.clickToNext();
        Assert.assertTrue(thirdCardPage.state().waitForDisplayed(), "Third card page should be opened");

    }

    @Test
    public void secondCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        AqualityServices.getLogger().info("STEP 1");
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page should be opened");
        mainPage.clickToStartLink();

        AqualityServices.getLogger().info("STEP 2");
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");
        firstCardPage.hideHelpForm();
        Assert.assertTrue(firstCardPage.waitForHelpFormClosing(), "Help form should be closed");

    }

    @Test
    public void thirdCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        AqualityServices.getLogger().info("STEP 1");
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page should be opened");
        mainPage.clickToStartLink();

        AqualityServices.getLogger().info("STEP 2");
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");
        firstCardPage.acceptCookies();
        Assert.assertTrue(firstCardPage.isCookiesFormClosed(), "Cookies form should be closed");
    }

    @Test
    public void fourthCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        AqualityServices.getLogger().info("STEP 1");
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page should be opened");
        mainPage.clickToStartLink();

        AqualityServices.getLogger().info("STEP 2");
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");
        Assert.assertEquals(firstCardPage.getTextFromTimer(), JsonUtil.testData.getValue("/timerStartValue"));
    }
}

