package loveLetters.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import loveLetters.annotation.NeedCarte;
import loveLetters.annotation.NeedJoueur;
import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.EtatJoueur;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public abstract class ActionJouer {

    Logger log = LoggerFactory.getLogger(ActionJouer.class);
    Partie partie;
    Joueur joueur;
    Carte carte;
    Joueur joueurCible;
    Carte carteCible;

    public ActionJouer(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        super();
        this.partie = partie;
        this.joueur = joueur;
        this.carte = carte;
        this.joueurCible = joueurCible;
        this.carteCible = carteCible;
    }

    public abstract Carte jouer() throws LoveLettersException;

    /**
     * verifie que le coup est valide :
     * joueur=joueurCourant,
     * carte bien presente dans la main du joueur,
     * cible valide
     * @return
     */
    public boolean isValide() {
        // les information obligatoire sont renseignées
        if (partie == null || joueur == null || carte == null) {
            log.error("la partie ou le joueur ou la carte est null");
            return false;
        }
        // le joueur est bien le joueur courant de la partie
        if (partie.getJoueurCourant() != joueur || partie.getJoueurCourant().getEtat() == EtatJoueur.MORT) {
            log.error("le joueur n'est pas le joueur courant de la partie ou il est mort");
            return false;
        }
        // la joeur possede bien la carte a jouer
        if (joueur.getCarteActive() != carte && joueur.getCartePiochee() != carte) {
            log.error("le joueur ne possede pas cette carte");
            return false;
        }
        // le joueur cible est bien ciblable
        if (!partie.obtenirJoueurAttaquable().contains(joueurCible)) {
            log.error("la cible n'est pas un joueur attaquable");
            return false;
        }
        return true;
    }

    /**
     * verifie que toutes les informations requises sont completes pour la carte jouée grace au annotation sur l'enum Carte
     * @return true si tous les champs requis sont renseigné, false sinon.
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public boolean isComplete() throws NoSuchFieldException, SecurityException {
        if (Carte.class.getField(carte.name()).isAnnotationPresent(NeedJoueur.class) && joueurCible == null) {
            return false;
        }
        else if (Carte.class.getField(carte.name()).isAnnotationPresent(NeedCarte.class) && carteCible == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ActionJouer [joueur=" + joueur + ", carte=" + carte + ", joueurCible=" + joueurCible + ", carteCible=" + carteCible + "]";
    }
}
