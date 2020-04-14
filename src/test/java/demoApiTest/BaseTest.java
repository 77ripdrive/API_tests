package demoApiTest;

import model.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import testUtils.TestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected TestUtils testUtils;
    protected Client client = null;
    protected String sessionId = null;

    @BeforeAll
    public void setup() {
        testUtils = new TestUtils();
    }

    @AfterAll
    public void afterTest() {
        client = null;
        sessionId = null;
    }
}
