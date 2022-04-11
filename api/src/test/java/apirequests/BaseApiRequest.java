package apirequests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import utils.JsonUtil;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseApiRequest{
    public static  <T> List<T> getFieldsAsListByPath(String path, int expectedStatusCode, Class<T> pojoClass) {
        return given()
                .baseUri(JsonUtil.getJsonFile("configData").getValue("/apiUri").toString())
                .basePath(path)
                .when().get()
                .then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getList("", pojoClass);
    }

    public static JsonPath getFieldsAsJsonPathByPath(String path, int expectedStatusCode) {
        return given()
                .baseUri(JsonUtil.getJsonFile("configData").getValue("/apiUri").toString())
                .basePath(path)
                .when().get()
                .then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
    }

    public static  <T> T getFieldByPath(String path, int expectedStatusCode, Class<T> pojoClass) {
        return given()
                .baseUri(JsonUtil.getJsonFile("configData").getValue("/apiUri").toString())
                .basePath(path)
                .when().get()
                .then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON)
                .extract().body().as(pojoClass);
    }

    public static  <T> T createFieldByPath(String path, int expectedStatusCode, Object pojoToInsert, Class<T> pojoClass) {
        return given()
                .baseUri(JsonUtil.getJsonFile("configData").getValue("/apiUri").toString())
                .basePath(path)
                .contentType(ContentType.JSON)
                .body(pojoToInsert)
                .when().post()
                .then()
                .statusCode(expectedStatusCode)
                .extract().body().as(pojoClass);
    }

    public enum StatusCode {
        OK_GET(200),
        OK_POST(201),
        NOT_FOUND(404);

        private final int statusCode;

        StatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}
