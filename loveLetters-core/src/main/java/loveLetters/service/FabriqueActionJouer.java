package loveLetters.service;

import loveLetters.iService.IActionJouer;
import loveLetters.iService.IFabriqueActionJouer;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public class FabriqueActionJouer implements IFabriqueActionJouer {

    public FabriqueActionJouer() {
        super();
    }

    public IActionJouer creerAction(Partie partie, Joueur joueur, Carte carte, Joueur joueurCible, Carte carteCible) {
        ActionJouer aj = null;
        switch (carte) {
            case SOLDAT:
                aj = new ActionJouer1(partie, joueur, carte, joueurCible, carteCible);
                break;
            case PRETRE:
                aj = new ActionJouer2(partie, joueur, carte, joueurCible, carteCible);
                break;
            case BARON:
                aj = new ActionJouer3(partie, joueur, carte, joueurCible, carteCible);
                break;
            case SERVANTE:
                aj = new ActionJouer4(partie, joueur, carte, joueurCible, carteCible);
                break;
            case PRINCE:
                aj = new ActionJouer5(partie, joueur, carte, joueurCible, carteCible);
                break;
            case ROI:
                aj = new ActionJouer6(partie, joueur, carte, joueurCible, carteCible);
                break;
            case COMTESSE:
                aj = new ActionJouer7(partie, joueur, carte, joueurCible, carteCible);
                break;
            case PRINCESSE:
                aj = new ActionJouer8(partie, joueur, carte, joueurCible, carteCible);
                break;
        }
        return aj;
    }
}
