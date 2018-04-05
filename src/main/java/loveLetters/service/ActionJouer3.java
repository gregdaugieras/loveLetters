package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer3 extends ActionJouer {

    public ActionJouer3(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        if (!this.isValide()) {
            throw new LoveLettersException("l'action n'est pas valide");
        }
        Carte carte2 = joueur.obtenirSecondeCarte(carte);
        if (carte2.getNumero() > joueurCible.getCarteActive().getNumero()) {
            partie.eliminerJoueur(joueurCible);
        }
        else if (carte2.getNumero() < joueurCible.getCarteActive().getNumero()) {
            partie.eliminerJoueur(joueur);
        }
        return joueurCible.getCarteActive();
    }

    @Override
    public String toString() {
        return joueur.getPseudo() + " provoque en duel " + joueurCible.getPseudo() + " avec son " + carte;
    }
}
