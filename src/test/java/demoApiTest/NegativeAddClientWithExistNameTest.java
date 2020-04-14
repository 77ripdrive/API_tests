package demoApiTest;

import org.junit.jupiter.api.*;
import response.ResultCode;
import response.StandardResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NegativeAddClientWithExistNameTest extends BaseTest {

    private String testUserName = "FirstUser";
    private String testFullName = "FirstUserFamily";

    @Order(1)
    @Test
    public void postClientAddUserTest() {

        StandardResponse receivedResponseBody =testUtils
                .addClientPostRequest(testUserName,testFullName,200);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBody.getResultCode());
    }

    @Order(2)
    @Test
    public void postClientAddUserWithExistNameTest() {

        StandardResponse receivedResponseBody =testUtils
                .addClientPostRequest(testUserName,testFullName,500);

        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }
}
