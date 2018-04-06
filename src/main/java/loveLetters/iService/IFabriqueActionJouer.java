package loveLetters.iService;

import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public interface IFabriqueActionJouer {

    public IActionJouer creerAction(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible);
}
