package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer2 extends ActionJouer {

    public ActionJouer2(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        if (!this.isValide()) {
            throw new LoveLettersException("l'action n'est pas valide");
        }
        joueur.defausserCarte(carte);
        return joueurCible.getCarteActive();
    }
}
