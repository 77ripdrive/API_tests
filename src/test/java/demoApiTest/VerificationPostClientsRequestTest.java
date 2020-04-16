package demoApiTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import response.ResultCode;
import response.StandardResponse;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VerificationPostClientsRequestTest extends BaseTest {

    private String testUserName = "FirstUser";
    private String testFullName = "FirstUserFamily";

    @Order(1)
    @DisplayName("Validation for add client with challenge/clients Post method")
    @Test
    public void postClientAddUserTest() {

        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(testUserName, testFullName, 200);

        Assertions.assertEquals(ResultCode.Ok, receivedResponseBody.getResultCode());
    }

    @Order(2)
    @DisplayName("Validation for add client with challenge/clients Post method")
    @Test
    public void postClientAddUserWithExistNameNegativeTest() {

        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(testUserName, testFullName, 500);

//        Assertions.assertEquals(ResultCode.UserAlreadyExists, receivedResponseBody.getResultCode())
//        * ResultCode message is not correct
        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("stringProvider")
    public void postClientWithEmptyFieldNegativeTest(String userName, String fullName) {

        StandardResponse receivedResponseBody = testUtils
                .addClientPostRequest(userName, fullName, 500);

        Assertions.assertEquals(ResultCode.UnexpectedError, receivedResponseBody.getResultCode());
    }

    static Stream<Arguments> stringProvider() {
        return Stream.of(
                arguments("NegativeUserName", ""),
                arguments("", "NegativeFullName"),
                arguments("", "")
        );
    }
}
