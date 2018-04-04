package loveLetters.service;

import static org.junit.Assert.*;

import org.junit.Test;

import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouerTest {

    Partie p;
    Joueur j1, j2, j3;

    public void creerPartie() throws Exception {
        p = new Partie();
        j1 = new Joueur(1, "j1");
        j2 = new Joueur(2, "j2");
        j3 = new Joueur(3, "j3");
        p.ajouterJoueur(j1);
        p.ajouterJoueur(j2);
        p.ajouterJoueur(j3);
        p.start();
    }

    @Test
    public void isCompleteTestNeedJoueur() throws Exception {
        creerPartie();
        Joueur jc = p.getJoueurCourant();
        // Carte carteAJouer = p.getJoueurCourant().getCarteActive();
        Joueur jCible = p.obtenirJoueurAttaquable().get(0);
        ActionJouer aj = new ActionJouer1(p, jc, Carte.PRETRE, null, Carte.PRETRE);
        assertFalse(aj.isComplete());
        aj = new ActionJouer1(p, jc, Carte.SOLDAT, jCible, null);
        assertFalse(aj.isComplete());
        aj = new ActionJouer1(p, jc, Carte.SOLDAT, jCible, Carte.PRETRE);
        assertTrue(aj.isComplete());
    }

    @Test
    public void isValid() throws Exception {
        creerPartie();
        Joueur jc = p.getJoueurCourant();
        Carte carteAJouer = p.getJoueurCourant().getCarteActive();
        Joueur jCible = p.obtenirJoueurAttaquable().get(0);
        ActionJouer aj = new ActionJouer1(p, jc, carteAJouer, jCible, Carte.PRETRE);
        assertTrue(aj.isValide());
    }
}
