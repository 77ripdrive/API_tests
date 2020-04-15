package demoApiTest;

import model.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import testUtils.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected TestUtils testUtils;
    protected Client client = null;
    protected String sessionId = null;
    protected Process process;

    @BeforeAll
    public void setup() {
        testUtils = new TestUtils();
        try {
           process = Runtime.getRuntime ().exec ("java -jar src/test/resources/hello-world-challenge-runner.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public void afterTest() {
        client = null;
        sessionId = null;
        process.destroy ();
    }
}
