package loveLetters.service;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import loveLetters.exception.CaNauraitJamaisDuArriverException;
import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;
import loveLetters.objetsMetier.PartieTest;

public class ActionJouerTest {

    private Logger log = Logger.getLogger(ActionJouerTest.class);
    Partie p;
    Joueur j1, j2, j3;
    FabriqueActionJouer fabrique = new FabriqueActionJouer();

    public void creerPartie() throws LoveLettersException {
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

    @Test
    public void main() throws Exception {
        creerPartie();
        while (!p.isFinDePArtie()) {
            Joueur jc = p.getJoueurCourant();
            Carte carteAJouer = p.getJoueurCourant().getCarteActive();
            Joueur jCible = (p.obtenirJoueurAttaquable().size() > 1) ? p.obtenirJoueurAttaquable().get(1) : p.obtenirJoueurAttaquable().get(0);
            ActionJouer aj = fabrique.creerAction(p, jc, carteAJouer, jCible, Carte.PRETRE);
            if (aj.isComplete() && aj.isValide()) {
                log.debug(p.toString());
                log.debug(aj.toString());
                aj.jouer();
                p.debuterNouveauTour();
            }
            else {
                throw new CaNauraitJamaisDuArriverException("l'action n'est pas valide");
            }
        }
    }
}
