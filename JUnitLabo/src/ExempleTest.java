import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExempleTest {
    private Exemple exemple = new Exemple();

    @Test
    public void testFactoriel() {
        exemple.factoriel(4);
        assertEquals(24, exemple.getResult());
    }
}
