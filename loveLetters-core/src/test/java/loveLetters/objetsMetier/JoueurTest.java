package loveLetters.objetsMetier;

import static org.junit.Assert.*;

import org.junit.Test;

public class JoueurTest {

    @Test
    public void testDefausserCarte() {
        Joueur j1 = new Joueur(1, "j1");
        j1.setCarteActive(Carte.SOLDAT);
        j1.setCartePiochee(Carte.BARON);
        // WHEN
        j1.defausserCarte(Carte.SOLDAT);
        assertEquals(Carte.BARON, j1.getCarteActive());
        assertEquals(null, j1.getCartePiochee());
        j1.defausserCarte(Carte.SOLDAT);
        assertEquals(Carte.BARON, j1.getCarteActive());
        assertEquals(null, j1.getCartePiochee());
        j1.defausserCarte(Carte.BARON);
        assertEquals(null, j1.getCarteActive());
        assertEquals(null, j1.getCartePiochee());
    }
}
