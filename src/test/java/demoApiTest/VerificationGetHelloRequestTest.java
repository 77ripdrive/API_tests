package demoApiTest;

import io.restassured.http.ContentType;
import model.Client;
import org.junit.jupiter.api.*;
import response.HelloResponse;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VerificationGetHelloRequestTest extends BaseTest {

    private String testUserName = "Alex_One";
    private String testFullName = "Alex_One";
    private String wrongSessionId = "1";

    @Order(1)
    @DisplayName("Validation for challenge/hello Get method")
    @Test
    void getHelloPositiveTest() {
        client = new Client(testUserName, testFullName);
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(this.client)
                .post("http://localhost:8080/challenge/clients");

        sessionId = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserName))
                .post("http://localhost:8080/challenge/login")
                .then()
                .statusCode(200)
                .extract()
                .header("X-Session-Id");

        HelloResponse receivedResponseBody = given()
                .when()
                .header("X-Session-Id", this.sessionId)
                .get("http://localhost:8080/challenge/hello")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body().as(HelloResponse.class);

        String expectedMessage = String.format("Hello, %s!", this.client.getFullName());

        Assertions.assertEquals(receivedResponseBody.getMessage(), expectedMessage);
    }

    @Order(2)
    @DisplayName("Validation for challenge/hello Get method with wrong sessionId value")
    @Test
    void getHelloWithWrongSessionIdNumberNegativeTest() {
        response = given()
                .when()
                .header("X-Session-Id", wrongSessionId)
                .get("http://localhost:8080/challenge/hello");

        jsonPath = testUtils.getJsonPath(response);
        String actualResult = jsonPath.get("resultCode");

        testUtils.statusCodeVerification(response, 401);
        Assertions.assertEquals("Unauthorized", actualResult);
    }

    @Order(3)
    @DisplayName("Validation for challenge/hello Get method without Login")
    @Test
    void getHelloWithWrongHeaderNegativeTest() {
        response = given()
                .when()
                .header("WrongHeader", sessionId)
                .get("http://localhost:8080/challenge/hello");

        jsonPath = testUtils.getJsonPath(response);
        String actualResult = jsonPath.get("resultCode");
        System.out.println(actualResult);

        testUtils.statusCodeVerification(response, 401);
        Assertions.assertEquals("Unauthorized", actualResult);
    }
}
