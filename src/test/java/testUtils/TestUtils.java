package testUtils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import response.GetClientResponse;
import response.StandardResponse;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class TestUtils {

    public String jsonUserNameBuilder(String username) {
        return String.format("{\"username\":\"%s\"}", username);
    }

    public String jsonClientBuilder(String username, String fullName) {
        return String.format("{\"fullName\":\"%s\",\"username\":\"%s\"}", username, fullName);
    }

    public GetClientResponse getClientsList() {
        return given()
                .when()
                .get("http://localhost:8080/challenge/clients")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body().as(GetClientResponse.class);
    }

    public StandardResponse addClientPostRequest(String userName, String fullName, int statusCode) {
        String client = jsonClientBuilder(userName, fullName);
        StandardResponse response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(client)
                .post("http://localhost:8080/challenge/clients")
                .then()
                .assertThat()
                .statusCode(statusCode)
                .and()
                .extract()
                .body().as(StandardResponse.class);
        return response;
    }

    public String setClient(String username, String fullName) {
        return String.format("username=%s&fullName=%s", username, fullName);
    }

    public JsonPath getJsonPath(Response response) {
        String json = response.asString();
        return new JsonPath(json);
    }

    public ArrayList getClients(JsonPath jsonPath) {
        ArrayList clientList = jsonPath.get();
        return clientList;
    }
}
