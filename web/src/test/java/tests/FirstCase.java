package tests;

import forms.FirstCardPage;
import forms.MainPage;
import forms.SecondCardPage;
import forms.ThirdCardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

public class FirstCase extends BaseTest{
    @Test
    public void firstCaseTest() {

        MainPage mainPage = new MainPage();
        FirstCardPage firstCardPage = new FirstCardPage();
        SecondCardPage secondCardPage = new SecondCardPage();
        ThirdCardPage thirdCardPage = new ThirdCardPage();
        //step 1
        Assert.assertTrue(mainPage.state().waitForDisplayed());
        //step2
        mainPage.clickToStartLink();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed());
        //step3
        firstCardPage.enterPassword(RandomUtils.generateRandomPassword(10));
        firstCardPage.enterEmail(RandomUtils.generateRandomString(7));
        firstCardPage.enterDomain(RandomUtils.generateRandomString(5));
        firstCardPage.openDomainList();
        firstCardPage.chooseOrgDomain();
        firstCardPage.uncheckTerms();
        firstCardPage.clickToNext();
        Assert.assertTrue(secondCardPage.state().waitForDisplayed());
        //step4
        secondCardPage.unselectAllCheckboxes();
        secondCardPage.selectRandomCheckbox();
        secondCardPage.selectRandomCheckbox();
        secondCardPage.selectRandomCheckbox();
        secondCardPage.uploadFile("D:\\a1qaSecondStage\\a2.tomilov\\web\\img\\avatar.png");
        secondCardPage.clickToNext();
        Assert.assertTrue(thirdCardPage.state().waitForDisplayed());

    }
}

