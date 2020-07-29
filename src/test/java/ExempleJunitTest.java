import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExempleJunitTest {
    @Test
    public void testNumber() {
        assertEquals(5, Exemple1.getNumber());
    }
    @Test
    public void testMeaningfulText () {
        assertEquals("Hello World", Exemple1.getMeaningfulText ());
    }
}

