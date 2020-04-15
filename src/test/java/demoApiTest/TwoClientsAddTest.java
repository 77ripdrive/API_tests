package demoApiTest;

import org.junit.jupiter.api.*;
import response.GetClientResponse;
import response.ResultCode;
import response.StandardResponse;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TwoClientsAddTest extends BaseTest {

    private String testUserNameValue1 = "Alex_Two";
    private String testFullNameValue1 = "Alex_Two";
    private String testUserNameValue2 = "Boris_Two";
    private String testFullNameValue2 = "Boris_Two";
    private int expectedQuantityClients = 2;
    private GetClientResponse actualClientsList;

    @Order(1)
    @Test
    public void addTwoClientsWithPostTest() {

        actualClientsList = testUtils.getClientsList();
        StandardResponse receivedResponseBodyFirst = testUtils
                .addClientPostRequest(testUserNameValue1, testFullNameValue1, 200);

        StandardResponse receivedResponseBodySecond = testUtils
                .addClientPostRequest(testUserNameValue2, testFullNameValue2, 200);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBodyFirst.getResultCode());
        Assertions.assertEquals(ResultCode.Ok, receivedResponseBodySecond.getResultCode());
    }

    @Order(2)
    @Test
    void twoClientsGetTest() {
        GetClientResponse actualResponseBody = testUtils.getClientsList();
        int actualClientsNumberAdded = actualResponseBody.getClients().size()
                - actualClientsList.getClients().size();

        Assertions.assertEquals(expectedQuantityClients, actualClientsNumberAdded);
    }
}
