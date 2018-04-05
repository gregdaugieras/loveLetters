package loveLetters.objetsMetier;

import loveLetters.annotation.NeedCarte;
import loveLetters.annotation.NeedJoueur;

public enum Carte {
    @NeedJoueur @NeedCarte SOLDAT(1), @NeedJoueur PRETRE(2), @NeedJoueur BARON(3), SERVANTE(4), @NeedJoueur PRINCE(5), @NeedJoueur ROI(6), COMTESSE(
            7), PRINCESSE(8);

    private int numero;

    private Carte(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    protected void setNumero(int numero) {
        this.numero = numero;
    }
}
