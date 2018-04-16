package loveLetters.service;

import org.springframework.stereotype.Service;

import loveLetters.objetsMetier.Joueur;

@Service
public class JoueurService {

    private int ident = 0;

    public JoueurService() {
        // TODO Auto-generated constructor stub
    }

    public Joueur getNouveauJoueur() {
        ident++;
        return new Joueur(ident, "toto" + ident);
    }
}
