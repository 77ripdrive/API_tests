package demoApiTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import response.ResultCode;
import response.StandardResponse;

import static io.restassured.RestAssured.given;

public class VerificationPostLogOutRequestTest extends BaseTest {

    private String testUserName = "User";

    @Test
    public void LogOutTest() {
        StandardResponse receivedResponseBody = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserName))
                .post("http://localhost:8080/challenge/logout")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .body().as(StandardResponse.class);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBody.getResultCode());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    public void LogOutWithEmptyUserValueNegativeTest(String testUserName) {

        response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserName))
                .post("http://localhost:8080/challenge/logout");

        testUtils.statusCodeVerification(response, 200);
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "\n"})
    public void LogOutWithNotCorrectUserValueNegativeTest(String testUserName) {

        response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(testUtils.jsonUserNameBuilder(testUserName))
                .post("http://localhost:8080/challenge/logout");

        testUtils.statusCodeVerification(response, 400);
    }
}
