package tests;

import aquality.selenium.browser.AqualityServices;
import forms.FeedPage;
import forms.MainPage;
import forms.ProfilePage;
import forms.SignInForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.WallPost;
import utils.JsonUtil;
import utils.RandomUtils;
import utils.VkApiUtils;

public class FirstTest extends BaseTest{
    @Test
    public void firstTest() {
        MainPage mainPage = new MainPage();
        SignInForm signInForm = new SignInForm();
        FeedPage feedPage = new FeedPage();
        ProfilePage profilePage = new ProfilePage();

        AqualityServices.getLogger().info("STEP1");
        Assert.assertTrue(mainPage.state().waitForDisplayed());
        mainPage.clickToSignIn();

        AqualityServices.getLogger().info("STEP2");
        Assert.assertTrue(signInForm.state().waitForDisplayed());
        signInForm.enterLogin(JsonUtil.getJsonFile("testData").getValue("/login").toString());
        signInForm.enterPassword(JsonUtil.getJsonFile("testData").getValue("/password").toString());

        AqualityServices.getLogger().info("STEP3");
        Assert.assertTrue(feedPage.state().waitForDisplayed());
        feedPage.clickToProfile();

        AqualityServices.getLogger().info("STEP4");
        Assert.assertTrue(profilePage.state().waitForDisplayed());
        String randomWallPostText = RandomUtils.generateRandomString(10);
        WallPost randomWallPost = VkApiUtils.wallPost(randomWallPostText).getBody();
        int randomWallPostId = randomWallPost.getResponse().getPost_id();

        AqualityServices.getLogger().info("STEP5");
        Assert.assertEquals(profilePage.getPostTextByPostId(randomWallPostId), randomWallPostText);
        Assert.assertEquals(profilePage.getNameOfPostAuthorByPostId(randomWallPostId), "Иван Иванов");



    }
}
