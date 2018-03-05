import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExempleTest {
    private static int numero = 0;

    private Exemple exemple = new Exemple();

    private static boolean erreur;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        erreur = true;
    }

    @BeforeEach
    public void setUp() throws Exception {
        numero++;
        System.out.println("Le test numéro " + numero + " a commencé");
    }

    @AfterEach
    public void tearDown()throws Exception {
        System.out.println("Le test numéro " + numero + " est terminé");
        System.out.println("Etat de la valeur: " + exemple.getVal());
        exemple.incVal();
    }

    @Test
    public void testFactoriel() {
        exemple.factoriel(4);
        assertEquals(24, exemple.getResult());
    }

    @Test
    public void testMin() {
        exemple.min(5, 6);
        assert(exemple.getResult() == 5);
    }

    @Test
    public void testPlusGrand() {
        exemple.plusGrand();
        assertTrue(exemple.getNom().equals("Toto"));
    }

    @Test
    public void testPlusPetit() {
        exemple.plusPetit();
        assertFalse(exemple.getNom().equals("Toto"));
    }

    @Test
    public void testRenvoiNull() {
        assertNull(exemple.renvoiNull());
    }

    @Test
    public void testRenvoiNotNull() {
        assertNotNull(exemple.getObject1());
    }

    @Test
    public void testMemeObject() {
        assertSame(exemple.getObject1(), exemple.getObject1());
    }

    @Test
    public void testObjectDifferent() {
        assertNotSame(exemple.getObject1(), exemple.getObject2());
    }

    @Test
    public void testEchec() {
         if (erreur) fail("Echec");
    }
}