package utils;

import kong.unirest.ContentType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import pojos.likes.IsLiked;
import pojos.photos.*;
import pojos.wall.Comment;
import pojos.wall.Post;

import java.io.File;

import static io.restassured.RestAssured.given;

public class VkApiUtils {
    private static final String apiUrl = "https://api.vk.com/method";
    private static final String versionOfApi = "5.131";
    private static final String USER_AGENT = "VKontakte/1.0";

    public static HttpResponse<Post> createPost(String message, int ownerId) {
        return Unirest.post(String.format("%s/wall.post", apiUrl))
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("message", message)
                .queryString("owner_id", ownerId)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(Post.class);
    }

    public static HttpResponse<Comment> commentPost(String commentText, int postId, int ownerId) {
        return Unirest.post(String.format("%s/wall.createComment", apiUrl))
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("post_id", postId)
                .queryString("owner_id", ownerId)
                .queryString("message", commentText)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(Comment.class);
    }

    public static HttpResponse<IsLiked> isPostLiked(int postId, int ownerId) {
        return Unirest.post(String.format("%s/likes.isLiked", apiUrl))
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("type", "post")
                .queryString("owner_id", ownerId)
                .queryString("item_id", postId)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(IsLiked.class);
    }



    private static HttpResponse<Server> getUploadServer() {
        return Unirest.get(String.format("%s/photos.getWallUploadServer", apiUrl))
                .header("User-agent", USER_AGENT)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(Server.class);
    }

    private static HttpResponse<UploadImageOnServer> uploadImageOnSever(String serverUrl, File photo) {
        return Unirest.post(serverUrl)
                .header("User-agent", USER_AGENT)
                .field("photo", photo)
                .asObject(UploadImageOnServer.class);
    }

    private static HttpResponse<Photo> saveWallPhoto(int server, String photo, String hash) {
        return Unirest.post(String.format("%s/photos.saveWallPhoto", apiUrl))
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("server", server)
                .queryString("photo", photo)
                .queryString("hash", hash)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(Photo.class);
    }

    public static String uploadImgOnServerAndGetPath(File photo) {
        String uploadUrl = VkApiUtils.getUploadServer().getBody().getResponse().getUpload_url();
        UploadImageOnServer uploadPhotoResponse = VkApiUtils.uploadImageOnSever(uploadUrl, photo).getBody();
        int serverOfUploadedPhoto = uploadPhotoResponse.getServer();
        String uploadedPhoto = uploadPhotoResponse.getPhoto();
        String hashOfUploadedPhoto = uploadPhotoResponse.getHash();
        PhotoResponse savedPhoto = VkApiUtils.saveWallPhoto(serverOfUploadedPhoto, uploadedPhoto, hashOfUploadedPhoto).getBody().getResponse().get(0);
        int photoId = savedPhoto.getId();
        int ownerId = savedPhoto.getOwner_id();
        return String.format("photo%d_%d", ownerId, photoId);
    }

    public static HttpResponse<Post> editPost(String newMessage, String imgPathOnServer, int postId, int ownerId) {
        return Unirest.post(String.format("%s/wall.edit", apiUrl))
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("message", newMessage)
                .queryString("owner_id", ownerId)
                .queryString("attachments", imgPathOnServer)
                .queryString("post_id", postId)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(Post.class);
    }

    public static HttpResponse<Post> deletePost(int postId, int ownerId) {
        return Unirest.post(String.format("%s/wall.delete", apiUrl))
                .header("User-agent", USER_AGENT)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("post_id", postId)
                .queryString("owner_id", ownerId)
                .queryString("access_token", JsonUtil.getJsonFile("testData").getValue("/token").toString())
                .queryString("v", versionOfApi)
                .asObject(Post.class);
    }

}

