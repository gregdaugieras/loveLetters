package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer6 extends ActionJouer {

    public ActionJouer6(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        Carte carteADonnee = (carte == joueur.getCarteActive()) ? joueur.getCartePiochee() : joueur.getCarteActive();
        Carte carteARecevoir = joueurCible.getCarteActive();
        joueur.defausserCarte(carte);
        joueur.setCarteActive(carteARecevoir);
        joueurCible.setCarteActive(carteADonnee);
        joueur.defausserCarte(carte);
        return null;
    }
    

    @Override
    public String toString() {
        return joueur.getPseudo() + " echange sa carte avec " + joueurCible.getPseudo() + " avec son " + carte;
    }
}
