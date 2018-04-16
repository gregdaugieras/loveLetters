package loveLetters.iService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import loveLetters.exception.LoveLettersException;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

public interface IPartieService {

    Partie getNouvellePartie();

    Collection<Partie> getAllParties();

    Partie getPartie(Integer i);

    void ajouterJoueur(Partie p, Joueur j) throws LoveLettersException;

    void startPartie(Partie p) throws LoveLettersException;

    Joueur getJoueur(Partie p, int id);

    List<Carte> getMains(Partie p, Joueur j);

    Partie initPartie1();
}
