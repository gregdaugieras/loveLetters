package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer7 extends ActionJouer {

    public ActionJouer7(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        boolean invalideComtesse = false;
        Carte carte2 = joueur.obtenirSecondeCarte(carte);
        if (carte2 == Carte.PRINCE || carte2 == Carte.ROI || carte2 == Carte.PRINCESSE) {
            invalideComtesse = true;
        }
        if (!this.isValide() || invalideComtesse) {
            throw new LoveLettersException("l'action n'est pas valide");
        }
        joueur.defausserCarte(carte);
        return null;
    }

    @Override
    public String toString() {
        return joueur.getPseudo() + " defausse sa " + carte;
    }
}
