package loveLetters.objetsMetier;

import java.util.concurrent.TransferQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import loveLetters.exception.CaNauraitJamaisDuArriverException;

@JsonPropertyOrder({ "id", "pseudo", "etat" })
public class Joueur {

    private int id;
    private String pseudo;
    // @JsonIgnore
    private Carte carteActive;
    // @JsonIgnore
    private Carte cartePiochee;
    private EtatJoueur etat = EtatJoueur.NORMAL;

    public Joueur(int id) {
        super();
        this.id = id;
    }

    public EtatJoueur getEtat() {
        return etat;
    }

    public void setEtat(EtatJoueur etat) {
        this.etat = etat;
    }

    public Joueur(int id, String pseudo) {
        super();
        this.id = id;
        this.pseudo = pseudo;
    }

    /**
     * supprime la carte de la main du joueur. si c'est la carte active et que la carte Pioché est non null,
     * transfere la carte pioché vers la carte active.
     * Si le joueur defause la princesse, il est mort.
     * @param j
     * @param carte
     */
    public void defausserCarte(Carte carte) {
        if (carte == cartePiochee) {
            cartePiochee = null;
            etat = (carte == Carte.PRINCESSE) ? EtatJoueur.MORT : etat;
        }
        else if (carte == carteActive) {
            carteActive = cartePiochee;
            cartePiochee = null;
            etat = (carte == Carte.PRINCESSE) ? EtatJoueur.MORT : etat;
        }
    }

    public void piocher(TransferQueue<Carte> pioche) throws CaNauraitJamaisDuArriverException {
        if (cartePiochee != null) {
            throw new CaNauraitJamaisDuArriverException("on essaye de piocher alors que le joueur a deja une carte 'pioché'");
        }
        cartePiochee = pioche.poll();
    }

    public Carte obtenirSecondeCarte(Carte carte) {
        return (carte == carteActive) ? cartePiochee : carteActive;
    }

    public Carte getCarteActive() {
        return carteActive;
    }

    public void setCarteActive(Carte carteActive) {
        this.carteActive = carteActive;
    }

    public Carte getCartePiochee() {
        return cartePiochee;
    }

    protected void setCartePiochee(Carte cartePiochee) {
        this.cartePiochee = cartePiochee;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    protected void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Joueur other = (Joueur) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return afficherMain();
    }

    public String afficherMain() {
        StringBuilder sb = new StringBuilder("[" + pseudo + "(" + etat + ") ");
        sb.append((carteActive != null) ? " carte1 = " + carteActive : "");
        sb.append((cartePiochee != null) ? " carte2 =  " + cartePiochee : "");
        sb.append("]");
        return sb.toString();
    }
}
