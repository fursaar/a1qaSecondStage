package utils;

import kong.unirest.ContentType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import pojos.WallPost;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class VkApiUtils {
    private static final String baseUrl = "https://api.vk.com/method";
    private static final String versionOfApi = "5.131";
    private static final String USER_AGENT = "VKontakte/1.0";

    public static HttpResponse<WallPost> wallPost(String message) {
        return Unirest.post("https://api.vk.com/method/wall.post")
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("message", message)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(WallPost.class);
    }
}

