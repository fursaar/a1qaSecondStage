package apirequests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseApiRequest<T>{

    private String baseUri;
    private String basePath;
    private Class<T> basePojo;

    public BaseApiRequest(String baseUri, String basePath, Class<T> basePojo) {
        this.baseUri = baseUri;
        this.basePath = basePath;
        this.basePojo = basePojo;
    }

    public List<T> getAllFieldsAsList(int expectedStatusCode) {
        return given()
                .baseUri(baseUri)
                .basePath(basePath)
                .when().get()
                .then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getList("", basePojo);
    }

    public JsonPath getAllFieldsAsJsonPath(int expectedStatusCode) {
        return given()
                .baseUri(baseUri)
                .basePath(basePath)
                .when().get()
                .then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
    }

    public T getFieldByPath(String path, int expectedStatusCode) {
        return given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath(String.format("%s%s", basePath, path))
                .when().get()
                .then()
                .statusCode(expectedStatusCode)
                .contentType(ContentType.JSON)
                .extract().body().as(basePojo);
    }

    public T createField(int expectedStatusCode, Object pojoToInsert) {
        return given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .body(pojoToInsert)
                .when().post()
                .then()
                .statusCode(expectedStatusCode)
                .extract().body().as(basePojo);
    }
}
