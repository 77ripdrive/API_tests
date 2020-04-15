package demoApiTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import response.ResultCode;
import response.StandardResponse;

import static io.restassured.RestAssured.given;

public class NegativeAddClientWithEmptyFieldTest extends BaseTest {

    private String testUserName = "NegativeWithEmptyFields";
    private String testFullName = "Neg";
    private String emptyUserNameField = "";
    private String emptyFullNameField = "";

    @Test
    public void postClientWithEmptyFullNameFieldTest() {

        String client =testUtils.jsonClientBuilder(testUserName,emptyFullNameField);
        StandardResponse response= given()
                .when()
                .contentType(ContentType.JSON)
                .body(client)
                .post("http://localhost:8080/challenge/clients")
                .then()
                .assertThat()
                .statusCode(500)
                .and()
                .extract()
                .body().as(StandardResponse.class);

        Assertions.assertEquals(ResultCode.UnexpectedError, response.getResultCode());
    }

    @Test
    public void postClientWithEmptyUserNameFieldTest() {

        String client =testUtils.jsonClientBuilder(emptyUserNameField,testFullName);
        StandardResponse response= given()
                .when()
                .contentType(ContentType.JSON)
                .body(client)
                .post("http://localhost:8080/challenge/clients")
                .then()
                .assertThat()
                .statusCode(500)
                .and()
                .extract()
                .body().as(StandardResponse.class);
        Assertions.assertEquals(ResultCode.UnexpectedError, response.getResultCode());
    }

    @Test
    public void postClientWithEmptyAllFieldTest() {
        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(emptyUserNameField,emptyFullNameField,500);

        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }

}
