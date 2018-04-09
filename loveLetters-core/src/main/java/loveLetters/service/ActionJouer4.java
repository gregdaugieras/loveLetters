package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.EtatJoueur;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer4 extends ActionJouer {

    public ActionJouer4(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        if (!this.isValide()) {
            throw new LoveLettersException("l'action n'est pas valide");
        }
        joueur.setEtat(EtatJoueur.PROTEGE);
        joueur.defausserCarte(carte);
        return null;
    }

    @Override
    public String toString() {
        return joueur.getPseudo() + " se protege avec sa " + carte;
    }
}
