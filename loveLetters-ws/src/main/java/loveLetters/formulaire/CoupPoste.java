package loveLetters.formulaire;

import org.springframework.stereotype.Service;

import loveLetters.objetsMetier.Carte;

@Service
public class CoupPoste {

    private int numJoueurCourant;
    private Carte carteJoue;
    private int numJoueurCible;
    private Carte carteCible;

    public CoupPoste(int numJoueurCourant, Carte carteJoue, int numJoueurCible, Carte carteCible) {
        super();
        this.numJoueurCourant = numJoueurCourant;
        this.carteJoue = carteJoue;
        this.numJoueurCible = numJoueurCible;
        this.carteCible = carteCible;
    }

    public CoupPoste() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getNumJoueurCourant() {
        return numJoueurCourant;
    }

    public Carte getCarteJoue() {
        return carteJoue;
    }

    public int getNumJoueurCible() {
        return numJoueurCible;
    }

    public Carte getCarteCible() {
        return carteCible;
    }
}
