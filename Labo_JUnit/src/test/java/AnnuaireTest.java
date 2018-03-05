import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnnuaireTest {
    private static int numero = 0;

    private Annuaire annuaire = new Annuaire();

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
    void addPersonne() {
        annuaire.addPersonne("Julius", "Henri", "h.julius@gmail.com", 1978);
        assertTrue(annuaire.Exist("h.julius@gmail.com"));
    }

    @Test
    void delPersonne() {
        annuaire.delPersonne("s.radouls@gmail.com");
        assertFalse(annuaire.Exist("s.radouls@gmail.com"));
    }

    @Test
    void changeNomPersonne() {
        annuaire.changeNomPersonne("richard.albert@free.fr", "De Franco");
        assertEquals("De Franco", annuaire.getNomPersonne("richard.albert@free.fr"));
    }
}