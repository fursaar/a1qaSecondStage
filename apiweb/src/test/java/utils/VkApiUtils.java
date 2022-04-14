package utils;

import aquality.selenium.browser.AqualityServices;
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

    public static HttpResponse<Post> createPost(String message, int ownerId) {
        return Unirest.post(VkApiMethods.WALL_POST.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("message", message)
                .queryString("owner_id", ownerId)
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(Post.class);
    }

    public static HttpResponse<Comment> commentPost(String commentText, int postId, int ownerId) {
        return Unirest.post(VkApiMethods.WALL_CREATE_COMMENT.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("post_id", postId)
                .queryString("owner_id", ownerId)
                .queryString("message", commentText)
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(Comment.class);
    }

    public static HttpResponse<IsLiked> isPostLiked(int postId, int ownerId) {
        return Unirest.post(VkApiMethods.LIKES_IS_LIKED.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("type", "post")
                .queryString("owner_id", ownerId)
                .queryString("item_id", postId)
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(IsLiked.class);
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
        return Unirest.post(VkApiMethods.WALL_EDIT.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("message", newMessage)
                .queryString("owner_id", ownerId)
                .queryString("attachments", imgPathOnServer)
                .queryString("post_id", postId)
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(Post.class);
    }

    public static HttpResponse<Post> deletePost(int postId, int ownerId) {
        return Unirest.post(VkApiMethods.WALL_DELETE.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("post_id", postId)
                .queryString("owner_id", ownerId)
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(Post.class);
    }

    private static HttpResponse<Server> getUploadServer() {
        return Unirest.get(VkApiMethods.PHOTOS_GET_WALL_UPLOAD_SERVER.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(Server.class);
    }

    private static HttpResponse<UploadImageOnServer> uploadImageOnSever(String serverUrl, File photo) {
        return Unirest.post(serverUrl)
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .field("photo", photo)
                .asObject(UploadImageOnServer.class);
    }

    private static HttpResponse<Photo> saveWallPhoto(int server, String photo, String hash) {
        return Unirest.post(VkApiMethods.PHOTOS_SAVE_WALL_PHOTO.getUrl())
                .header("User-agent", JsonUtil.getJsonFile("configData").getValue("/userAgent").toString())
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .queryString("server", server)
                .queryString("photo", photo)
                .queryString("hash", hash)
                .queryString("access_token", JsonUtil.getJsonFile("configData").getValue("/token").toString())
                .queryString("v", JsonUtil.getJsonFile("configData").getValue("/versionOfApi").toString())
                .asObject(Photo.class);
    }

    public enum VkApiMethods {
        WALL_POST ("https://api.vk.com/method/wall.post"),
        WALL_CREATE_COMMENT ("https://api.vk.com/method/wall.createComment"),
        WALL_EDIT ("https://api.vk.com/method/wall.edit"),
        WALL_DELETE ("https://api.vk.com/method/wall.delete"),
        LIKES_IS_LIKED ("https://api.vk.com/method/likes.isLiked"),
        PHOTOS_GET_WALL_UPLOAD_SERVER ("https://api.vk.com/method/photos.getWallUploadServer"),
        PHOTOS_SAVE_WALL_PHOTO ("https://api.vk.com/method/photos.saveWallPhoto");

        private final String url;

        VkApiMethods(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }
}

