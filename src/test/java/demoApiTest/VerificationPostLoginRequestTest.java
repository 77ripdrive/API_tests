package demoApiTest;

import io.restassured.http.ContentType;
import model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class VerificationPostLoginRequestTest extends BaseTest {

    private String testUserName = "Alex_One";
    private String testFullName = "Alex_One";
    private String testUserNameValueWithOutRegistration = "Unregistered";

    @DisplayName("Validation for challenge/login Post method")
    @Test
    void postLoginWithRegisteredUserNamePositiveTest() {
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

        Assertions.assertNotNull(sessionId);
    }

    @DisplayName("Validation for challenge/login Post method")
    @Test
    public void postLoginWithEmptyUserNameFieldNegativeTest() {

        response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(""))
                .post("http://localhost:8080/challenge/login");

        jsonPath = testUtils.getJsonPath(response);

        testUtils.statusCodeVerification(response, 500);
        Assertions.assertEquals("IncorrectParameter", jsonPath.get("resultCode"));
        Assertions.assertEquals("Username or Password are not correct", jsonPath.get("errorMessage"));
    }

    @DisplayName("Validation for challenge/login Post method")
    @Test
    public void postLoginWithOutRegisteredUserNameNegativeTest() {

        sessionId = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserNameValueWithOutRegistration))
                .post("http://localhost:8080/challenge/login")
                .then()
                .assertThat()
                .statusCode(500)
                .and()
                .extract()
                .header("X-Session-Id");

        Assertions.assertNull(sessionId);
    }
}