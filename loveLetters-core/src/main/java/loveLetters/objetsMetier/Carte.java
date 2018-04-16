package loveLetters.objetsMetier;

import com.fasterxml.jackson.annotation.JsonIgnore;

import loveLetters.annotation.NeedCarte;
import loveLetters.annotation.NeedJoueur;

public enum Carte {
    @NeedJoueur @NeedCarte SOLDAT(1, 5), @NeedJoueur PRETRE(2, 2), @NeedJoueur BARON(3, 2), SERVANTE(4, 2), @NeedJoueur PRINCE(5,
            2), @NeedJoueur ROI(6, 1), COMTESSE(7, 1), PRINCESSE(8, 1);

    private int numero;
    @JsonIgnore
    private int nbExemplaire;

    private Carte(int numero) {
        this.numero = numero;
    }

    private Carte(int numero, int nbExemplaire) {
        this.numero = numero;
        this.nbExemplaire = nbExemplaire;
    }

    public int getNumero() {
        return numero;
    }

    protected void setNumero(int numero) {
        this.numero = numero;
    }

    protected int getNbExemplaire() {
        return nbExemplaire;
    }

    public static Carte recupererCarteByNumero(int num) {
        for (Carte carte : Carte.values()) {
            if (num == carte.numero) {
                return carte;
            }
        }
        return null;
    }
}
