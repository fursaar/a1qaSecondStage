package tests;

import apirequests.BaseApiRequest;
import aquality.selenium.browser.AqualityServices;
import io.opentelemetry.api.trace.StatusCode;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Post;
import pojos.User;
import utils.ListUtils;
import utils.JsonUtil;
import utils.RandomUtils;

import java.util.List;


public class ApiTest {

    @Test
    public void Test() {
        BaseApiRequest<Post> postsRequest = new BaseApiRequest<>(JsonUtil.getJsonFile("configData").getValue("/baseUri").toString(), "/posts", Post.class);
        BaseApiRequest<User> usersRequest = new BaseApiRequest<>(JsonUtil.getJsonFile("configData").getValue("/baseUri").toString(), "/users", User.class);
        JsonUtil<User> userParser = new JsonUtil<>();


        AqualityServices.getLogger().info("STEP1");
        JsonPath posts = postsRequest.getAllFieldsAsJsonPath(BaseApiRequest.StatusCode.OK_GET.getStatusCode());
        List<Integer> idsOfPosts = posts.getList("id");
        List<Integer> sortedIdsOfPosts = ListUtils.sortList(idsOfPosts);
        Assert.assertEquals(idsOfPosts, sortedIdsOfPosts);

        AqualityServices.getLogger().info("STEP2");
        Post post99 = postsRequest.getFieldByPath(JsonUtil.getJsonFile("testData").getValue("/post99path").toString(), BaseApiRequest.StatusCode.OK_GET.getStatusCode());
        Assert.assertEquals(post99.getUserId(), JsonUtil.getJsonFile("testData").getValue("/post99expectedUserId"));
        Assert.assertEquals(post99.getId(), JsonUtil.getJsonFile("testData").getValue("/post99expectedId"));
        Assert.assertNotNull(post99.getBody());
        Assert.assertNotNull(post99.getTitle());

        AqualityServices.getLogger().info("STEP3");
        Post post150 = postsRequest.getFieldByPath(JsonUtil.getJsonFile("testData").getValue("/post150path").toString(), BaseApiRequest.StatusCode.NOT_FOUND.getStatusCode());
        Assert.assertEquals(post150.getUserId(), 0);
        Assert.assertEquals(post150.getId(), 0);
        Assert.assertNull(post150.getBody());
        Assert.assertNull(post150.getTitle());

        AqualityServices.getLogger().info("STEP4");
        Post postForInsert = new Post();
        postForInsert.setUserId(1);
        postForInsert.setBody(RandomUtils.generateRandomString(10));
        postForInsert.setTitle(RandomUtils.generateRandomString(10));
        Post addedPost = postsRequest.createField(BaseApiRequest.StatusCode.OK_POST.getStatusCode(), postForInsert);
        Assert.assertEquals(postForInsert.getUserId(), addedPost.getUserId());
        Assert.assertNotEquals(addedPost.getId(), 0);
        Assert.assertEquals(postForInsert.getBody(), addedPost.getBody());
        Assert.assertEquals(postForInsert.getTitle(), addedPost.getTitle());

        AqualityServices.getLogger().info("STEP5");
        JsonPath users = usersRequest.getAllFieldsAsJsonPath(BaseApiRequest.StatusCode.OK_GET.getStatusCode());
        User userToCompare =userParser.castJsonStringToPojo(JsonUtil.getJsonFile("testData").getValue("/user5").toString(), User.class);
        Assert.assertEquals(users.getObject("find{it.id==5}", User.class), userToCompare);



        AqualityServices.getLogger().info("STEP6");
        User user5 = usersRequest.getFieldByPath("/5", BaseApiRequest.StatusCode.OK_GET.getStatusCode());
        Assert.assertEquals(user5, (users.getObject("find{it.id==5}", User.class)));
    }
}
