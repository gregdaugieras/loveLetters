package loveLetters.service;

import loveLetters.objetsMetier.Joueur;

public class JoueurService {

    public JoueurService() {
        // TODO Auto-generated constructor stub
    }

    public Joueur getJoueur(int id) {
        return new Joueur(id);
    };
}
