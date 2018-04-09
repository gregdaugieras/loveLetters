package loveLetters.iService;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;

public interface IActionJouer {

    /**
     * applique les effets de la carte jouée puis la defausse.
     * @return
     * @throws LoveLettersException
     */
    public Carte jouer() throws LoveLettersException;

    /**
     * verifie que le coup est valide :
     * joueur=joueurCourant,
     * carte bien presente dans la main du joueur,
     * cible valide
     * @return
     */
    public boolean isValide();

    /**
     * verifie que toutes les informations requises sont completes pour la carte jouée grace au annotation sur l'enum Carte
     * @return true si tous les champs requis sont renseigné, false sinon.
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public boolean isComplete() throws NoSuchFieldException, SecurityException;
}
