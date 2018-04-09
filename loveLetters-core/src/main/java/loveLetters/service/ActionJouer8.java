package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer8 extends ActionJouer {

    public ActionJouer8(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        joueur.defausserCarte(carte);
        return null;
    }

    @Override
    public String toString() {
        return joueur.getPseudo() + " defausse sa " + carte + " : LOOSER !!!";
    }
}
