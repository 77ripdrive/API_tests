package demoApiTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import testUtils.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected TestUtils testUtils;
    protected Client client = null;
    protected String sessionId = null;
    protected Process process;
    protected Response response ;
    protected JsonPath jsonPath ;

    @BeforeAll
    public void setup() {
        testUtils = new TestUtils();
        try {
            process = Runtime.getRuntime()
                    .exec("java -jar src/test/resources/hello-world-challenge-runner.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public void afterTest() {
        process.destroy();
    }
}
