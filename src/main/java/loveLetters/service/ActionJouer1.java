package loveLetters.service;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class ActionJouer1 extends ActionJouer {

    public ActionJouer1(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super(partie, joueur, carte, joueurCible, carteCible);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte jouer() throws LoveLettersException {
        boolean invalideSoldat = false;
        if (carteCible == Carte.SOLDAT) {
            log.error("on ne peut pas assassiner un soldat");
            invalideSoldat = true;
        }
        if (!this.isValide() || invalideSoldat) {
            throw new LoveLettersException("l'action n'est pas valide");
        }
        if (joueurCible.getCarteActive() == carteCible) {
            partie.eliminerJoueur(joueurCible);
            joueur.defausserCarte(carte);
        }
        return null;
    }

    @Override
    public String toString() {
        return joueur.getPseudo() + " accuse " + joueurCible.getPseudo() + " d'etre la carte " + carteCible;
    }
}
