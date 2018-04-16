package loveLetters.formulaire;

import org.springframework.stereotype.Service;

import loveLetters.objetsMetier.Carte;

@Service
public class CoupPoste {

    private int numJoueurCourant;
    private int carteJoue;
    private int numJoueurCible;
    private int carteCible;

    public CoupPoste(int numJoueurCourant, int idCarteJoue, int numJoueurCible, int idCarteCible) {
        super();
        this.numJoueurCourant = numJoueurCourant;
        this.carteJoue = idCarteJoue;
        this.numJoueurCible = numJoueurCible;
        this.carteCible = idCarteCible;
        ;
    }

    public CoupPoste() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getNumJoueurCourant() {
        return numJoueurCourant;
    }

    public Carte getCarteJoue() {
        return Carte.recupererCarteByNumero(carteJoue);
    }

    public int getNumJoueurCible() {
        return numJoueurCible;
    }

    public Carte getCarteCible() {
        return Carte.recupererCarteByNumero(carteCible);
    }
}
