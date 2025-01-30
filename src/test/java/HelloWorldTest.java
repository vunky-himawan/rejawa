import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {
    String greeting = "Hello World";

    @Test
    void testHelloWorld() {
        assertEquals("Hello World", greeting);
    }
}
