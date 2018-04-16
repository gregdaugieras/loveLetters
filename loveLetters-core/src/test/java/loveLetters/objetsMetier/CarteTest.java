package loveLetters.objetsMetier;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CarteTest {

    @Test
    public void recupererNumeroTest() {
        assertTrue(Carte.SOLDAT == Carte.recupererCarteByNumero(1));
        assertTrue(Carte.PRETRE == Carte.recupererCarteByNumero(2));
        assertTrue(Carte.BARON == Carte.recupererCarteByNumero(3));
        assertTrue(Carte.SERVANTE == Carte.recupererCarteByNumero(4));
        assertTrue(Carte.PRINCE == Carte.recupererCarteByNumero(5));
        assertTrue(Carte.ROI == Carte.recupererCarteByNumero(6));
        assertTrue(Carte.COMTESSE == Carte.recupererCarteByNumero(7));
        assertTrue(Carte.PRINCESSE == Carte.recupererCarteByNumero(8));
    }
}
