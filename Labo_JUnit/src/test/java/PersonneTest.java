import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {
    private static int numero = 0;

    private Personne personne = new Personne("Lefaucault", "Remi", "r.lefaucault@gmail.com", 1990);

    @BeforeEach
    public void setUp() throws Exception {
        numero++;
        System.out.println("Le test numéro " + numero + " a commencé");
    }

    @AfterEach
    public void tearDown()throws Exception {
        System.out.println("Le test numéro " + numero + " est terminé");
    }

    @Test
    void getNom() {
        assertEquals("Lefaucault", personne.getNom());
    }

    @Test
    void setNom() {
        personne.setNom("Ultimo");
        assertEquals("Ultimo", personne.getNom());
    }
}