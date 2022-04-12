package tests;

import aquality.selenium.browser.AqualityServices;
import forms.FeedPage;
import forms.MainPage;
import forms.ProfilePage;
import forms.SignInForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.wall.Comment;
import pojos.wall.Post;
import utils.JsonUtil;
import utils.RandomUtils;
import utils.VkApiUtils;

import java.io.File;

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
        String randomPostText = RandomUtils.generateRandomString((Integer) JsonUtil.getJsonFile("configData").getValue("/baseLengthOfRandomString"));
        Post post = VkApiUtils.createPost(randomPostText, (Integer) JsonUtil.getJsonFile("testData").getValue("/userId")).getBody();
        int postId = post.getResponse().getPost_id();

        AqualityServices.getLogger().info("STEP5");
        Assert.assertEquals(profilePage.getPostTextByPostId(postId), randomPostText);
        Assert.assertEquals(profilePage.getNameOfPostAuthorByPostId(postId), JsonUtil.getJsonFile("testData").getValue("/userName").toString());

        AqualityServices.getLogger().info("STEP6");
        File photo = new File("./img/meme.jpg");
        String photoPath = VkApiUtils.uploadImgOnServerAndGetPath(photo);
        VkApiUtils.editPost(String.format("%s - edited", randomPostText), photoPath, postId, (Integer) JsonUtil.getJsonFile("testData").getValue("/userId"));

        AqualityServices.getLogger().info("STEP7");
        Assert.assertNotEquals(profilePage.getPostTextByPostId(postId), randomPostText);
        Assert.assertEquals(profilePage.getPostPhotoHrefByPostId(postId), String.format("https://vk.com/%s", photoPath));

        AqualityServices.getLogger().info("STEP8");
        String randomCommentText = RandomUtils.generateRandomString((Integer) JsonUtil.getJsonFile("configData").getValue("/baseLengthOfRandomString"));
        Comment comment = VkApiUtils.commentPost(randomCommentText, postId, (Integer) JsonUtil.getJsonFile("testData").getValue("/userId")).getBody();
        int commentId = comment.getResponse().getComment_id();

        AqualityServices.getLogger().info("STEP9");
        profilePage.clickToShowNextCommentInPostUnderId(postId);
        Assert.assertEquals(profilePage.getCommentTextByCommentId(commentId), randomCommentText);
        Assert.assertEquals(profilePage.getAuthorNameByCommentId(commentId), "Иван Иванов");

        AqualityServices.getLogger().info("STEP10");
        profilePage.clickToPostLikeButtonByPostId(postId);
        int isPostLiked = VkApiUtils.isPostLiked(postId, (Integer) JsonUtil.getJsonFile("testData").getValue("/userId")).getBody().getResponse().getLiked();

        AqualityServices.getLogger().info("STEP11");
        Assert.assertEquals(isPostLiked, JsonUtil.getJsonFile("configData").getValue("/postLiked"));


        AqualityServices.getLogger().info("STEP12");
        VkApiUtils.deletePost(postId, (Integer) JsonUtil.getJsonFile("testData").getValue("/userId"));

        AqualityServices.getLogger().info("STEP12");
        Assert.assertFalse(profilePage.isPostExist(postId));

    }
}
