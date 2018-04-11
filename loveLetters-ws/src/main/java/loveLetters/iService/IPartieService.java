package loveLetters.iService;

import java.util.Collection;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public interface IPartieService {

    Partie getNouvellePartie();

    Collection<Partie> getAllParties();

    Partie getPartie(Integer i);

    void ajouterJoueur(Partie p, Joueur j) throws LoveLettersException;

    void startPartie(Partie p) throws LoveLettersException;
}
