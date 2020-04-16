package demoApiTest;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VerificationGetRequestClientsTest extends BaseTest {

    private String testUserNameValue1 = "Alex_Two";
    private String testFullNameValue1 = "Alex_Two";

    @Order(1)
    @DisplayName("Validation for challenge/clients Get method")
    @Test
    void getClientsTest() {
        response = given()
                .when()
                .get("http://localhost:8080/challenge/clients");

        testUtils.statusCodeVerification(response, 200);
    }

    @Order(2)
    @DisplayName("Validation for challenge/clients Get method")
    @Test
    public void addOneClientAndCheckWithGetClientsRequestTest() {

        testUtils
                .addClientPostRequest(testUserNameValue1, testFullNameValue1, 200);

        Assertions.assertEquals(1, testUtils.getClientsList().getClients().size());
    }
}
