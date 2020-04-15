package demoApiTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class NegativeLoginTest extends BaseTest {

    private String testUserNameValueWithOutRegistration = "Unregistered";

    @Test
    public void loginWithOutRegisteredUserNameTest() {

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
