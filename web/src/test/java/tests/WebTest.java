package tests;

import aquality.selenium.browser.AqualityServices;
import forms.FirstCardPage;
import forms.MainPage;
import forms.SecondCardPage;
import forms.ThirdCardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EmulateKeysUtils;
import utils.FileUtils;
import utils.JsonUtil;
import utils.RandomUtils;

public class WebTest extends BaseTest{
    @Test
    public void firstCase() {

        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();
        SecondCardPage secondCardPage = new SecondCardPage();
        ThirdCardPage thirdCardPage = new ThirdCardPage();

        AqualityServices.getLogger().info("STEP 1");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page page should be opened");

        AqualityServices.getLogger().info("STEP 2");
        AqualityServices.getBrowser().getScreenshot();
        mainPage.clickToStartLink();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");

        AqualityServices.getLogger().info("STEP 3");
        AqualityServices.getBrowser().getScreenshot();
        firstCardPage.enterPassword(RandomUtils.generateRandomPassword((Integer) JsonUtil.testData.getValue("/lengthOfPassword")));
        firstCardPage.enterEmail(RandomUtils.generateRandomString((Integer) JsonUtil.testData.getValue("/lengthOfEmail")));
        firstCardPage.enterDomain(RandomUtils.generateRandomString((Integer) JsonUtil.testData.getValue("/lengthOfDomain")));
        firstCardPage.openDomainList();
        firstCardPage.chooseDomain(FirstCardPage.Domain.GOV);
        firstCardPage.uncheckTerms();
        firstCardPage.clickToNext();
        Assert.assertTrue(secondCardPage.state().waitForDisplayed(), "Second card page should be opened");

        AqualityServices.getLogger().info("STEP 4");
        AqualityServices.getBrowser().getScreenshot();
        secondCardPage.unselectAllCheckboxes();
        secondCardPage.selectCheckboxByIndex(RandomUtils.generateRandomNumberInRangeExcept(1, (Integer) JsonUtil.testData.getValue("/numberOfCheckboxes"), SecondCardPage.Checkboxes.SELECT_ALL.getIndexOfCheckbox(), SecondCardPage.Checkboxes.USELECT_ALL.getIndexOfCheckbox()));
        secondCardPage.selectCheckboxByIndex(RandomUtils.generateRandomNumberInRangeExcept(1, (Integer) JsonUtil.testData.getValue("/numberOfCheckboxes"), SecondCardPage.Checkboxes.SELECT_ALL.getIndexOfCheckbox(), SecondCardPage.Checkboxes.USELECT_ALL.getIndexOfCheckbox()));
        secondCardPage.selectCheckboxByIndex(RandomUtils.generateRandomNumberInRangeExcept(1, (Integer) JsonUtil.testData.getValue("/numberOfCheckboxes"), SecondCardPage.Checkboxes.SELECT_ALL.getIndexOfCheckbox(), SecondCardPage.Checkboxes.USELECT_ALL.getIndexOfCheckbox()));
        secondCardPage.clickUploadButton();
        EmulateKeysUtils.setClipboardData(FileUtils.getFilePath(JsonUtil.testData.getValue("/avatarFilePath").toString()));
        EmulateKeysUtils.pasteClipboardData();
        secondCardPage.clickToNext();
        Assert.assertTrue(thirdCardPage.state().waitForDisplayed(), "Third card page should be opened");

    }

    @Test
    public void secondCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        AqualityServices.getLogger().info("STEP 1");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page should be opened");
        mainPage.clickToStartLink();

        AqualityServices.getLogger().info("STEP 2");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");
        firstCardPage.hideHelpForm();
        Assert.assertTrue(firstCardPage.waitForHelpFormClosing(), "Help form should be closed");

    }

    @Test
    public void thirdCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        AqualityServices.getLogger().info("STEP 1");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page should be opened");
        mainPage.clickToStartLink();

        AqualityServices.getLogger().info("STEP 2");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");
        firstCardPage.acceptCookies();
        Assert.assertTrue(firstCardPage.isCookiesFormClosed(), "Cookies form should be closed");
    }

    @Test
    public void fourthCase() {
        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();

        AqualityServices.getLogger().info("STEP 1");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page should be opened");
        mainPage.clickToStartLink();

        AqualityServices.getLogger().info("STEP 2");
        AqualityServices.getBrowser().getScreenshot();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page should be opened");
        Assert.assertEquals(firstCardPage.getTextFromTimer(), JsonUtil.testData.getValue("/timerStartValue"));
    }
}
