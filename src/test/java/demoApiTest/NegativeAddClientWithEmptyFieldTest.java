package demoApiTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import response.ResultCode;
import response.StandardResponse;

public class NegativeAddClientWithEmptyFieldTest extends BaseTest {

    private String testUserName = "NegativeWithEmptyFields";
    private String testFullName = "Neg";
    private String emptyUserNameField = "";
    private String emptyFullNameField = "";

    @Test
    public void postClientWithEmptyFullNameFieldTest() {

        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(testUserName, emptyFullNameField, 500);

        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }

    @Test
    public void postClientWithEmptyUserNameFieldTest() {

        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(emptyUserNameField, testFullName, 500);

        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }

    @Test
    public void postClientWithEmptyAllFieldTest() {

        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(emptyUserNameField, emptyFullNameField, 500);

        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }

}
