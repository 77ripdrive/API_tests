package demoApiTest;

import io.restassured.http.ContentType;
import model.Client;
import org.junit.jupiter.api.*;
import response.GetClientResponse;
import response.HelloResponse;
import response.ResultCode;
import response.StandardResponse;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class End2EndClientTest extends BaseTest {

    private String testUserName = "Boris1";
    private String testFullName = "First";

    @Order(1)
    @Test
    public void postClientTest() {
        client = new Client(testUserName, testFullName);
        StandardResponse receivedResponseBody = given()
                .when()
                .contentType(ContentType.JSON)
                .body(this.client)
                .post("http://localhost:8080/challenge/clients")
                .then()
                .statusCode(200)
                .extract()
                .body().as(StandardResponse.class);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBody.getResultCode());
    }

    @Order(2)
    @Test
    void getClientsTest() {
        GetClientResponse receivedResponseBody = given()
                .when()
                .get("http://localhost:8080/challenge/clients")
                .then()
                .statusCode(200)
                .extract()
                .body().as(GetClientResponse.class);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBody.getResultCode());
        Assertions.assertTrue(receivedResponseBody.getClients().contains(this.client.getUsername()));
    }

    @Order(3)
    @Test
    public void loginTest() {

        sessionId = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserName))
                .post("http://localhost:8080/challenge/login")
                .then()
                .statusCode(200)
                .extract()
                .header("X-Session-Id");

        Assertions.assertNotNull(sessionId);
    }

    @Order(4)
    @Test
    public void helloTest() {
        HelloResponse receivedResponseBody = given()
                .when()
                .header("X-Session-Id", this.sessionId)
                .get("http://localhost:8080/challenge/hello")
                .then()
                .statusCode(200)
                .extract()
                .body().as(HelloResponse.class);

        String expectedHelloMessage = String.format("Hello, %s!", this.client.getFullName());

        Assertions.assertEquals(receivedResponseBody.getMessage(), expectedHelloMessage);
    }

    @Order(5)
    @Test
    public void LogOutTest() {
        StandardResponse receivedResponseBody = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserName))
                .post("http://localhost:8080/challenge/logout")
                .then()
                .statusCode(200)
                .extract()
                .body().as(StandardResponse.class);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBody.getResultCode());
    }
}
