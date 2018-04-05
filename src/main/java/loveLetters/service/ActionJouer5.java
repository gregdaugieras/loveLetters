package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.EtatJoueur;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer5 extends ActionJouer {

    public ActionJouer5(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        Carte carteDefausse;
        // le joueur decide de se defausser lui meme
        if (joueur == joueurCible) {
            carteDefausse = joueur.obtenirSecondeCarte(carte);
        }
        else {
            carteDefausse = joueurCible.getCarteActive();
        }
        joueurCible.defausserCarte(carteDefausse);
        // le joueur pioche une nouvelle carte
        if (joueurCible.getEtat() != EtatJoueur.MORT) {
            joueurCible.piocher(partie.getPioche());
        }
        return carteDefausse;
    }

    @Override
    public String toString() {
        return joueur.getPseudo() + " fait defausser " + joueurCible.getPseudo() + " avec son " + carte;
    }
}
