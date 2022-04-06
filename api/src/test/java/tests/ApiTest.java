package tests;

import apirequests.BaseApiRequest;
import aquality.selenium.browser.AqualityServices;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.PostPojo;
import pojos.UserPojo;
import utils.ListUtils;
import utils.JsonUtil;
import utils.RandomUtils;

import java.util.List;


public class ApiTest {

    @Test
    public void Test() {
        BaseApiRequest<PostPojo> postsRequest = new BaseApiRequest<>(JsonUtil.configData.getValue("/baseUri").toString(), "/posts", PostPojo.class);
        BaseApiRequest<UserPojo> usersRequest = new BaseApiRequest<>(JsonUtil.configData.getValue("/baseUri").toString(), "/users", UserPojo.class);
        int nullForInt = 0;
        int post99expectedId = 99;
        int post99expectedUserId = 10;

        AqualityServices.getLogger().info("STEP1");
        List<PostPojo> posts = postsRequest.getAllFieldsAsList(200);
        Assert.assertTrue(ListUtils.checkAscendingSorting(posts));

        AqualityServices.getLogger().info("STEP2");
        PostPojo post99 = postsRequest.getFieldByPath("/99", 200);
        Assert.assertEquals(post99.getUserId(), post99expectedUserId);
        Assert.assertEquals(post99.getId(), post99expectedId);
        Assert.assertNotNull(post99.getBody());
        Assert.assertNotNull(post99.getTitle());

        AqualityServices.getLogger().info("STEP3");
        PostPojo post150 = postsRequest.getFieldByPath("/150", 404);
        Assert.assertEquals(post150.getUserId(), nullForInt);
        Assert.assertEquals(post150.getId(), nullForInt);
        Assert.assertNull(post150.getBody());
        Assert.assertNull(post150.getTitle());

        AqualityServices.getLogger().info("STEP4");
        PostPojo postForInsert = new PostPojo();
        postForInsert.setUserId(1);
        postForInsert.setBody(RandomUtils.generateRandomString(10));
        postForInsert.setTitle(RandomUtils.generateRandomString(10));
        PostPojo addedPost = postsRequest.createField(201, postForInsert);
        Assert.assertEquals(postForInsert.getUserId(), addedPost.getUserId());
        Assert.assertNotEquals(addedPost.getId(), nullForInt);
        Assert.assertEquals(postForInsert.getBody(), addedPost.getBody());
        Assert.assertEquals(postForInsert.getTitle(), addedPost.getTitle());

        AqualityServices.getLogger().info("STEP5");
        JsonPath allUsers = usersRequest.getAllFieldsAsJsonPath(200);
        UserPojo userToCompare = usersRequest.getFieldByPath("/5", 200);
        Assert.assertEquals(allUsers.getObject("find{it.id==5}", UserPojo.class), userToCompare);


        AqualityServices.getLogger().info("STEP6");
        UserPojo user5 = usersRequest.getFieldByPath("/5", 200);
        Assert.assertEquals(user5, (allUsers.getObject("find{it.id==5}", UserPojo.class)));
    }
}
