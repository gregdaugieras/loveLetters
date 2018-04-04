package loveLetters.objetsMetier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import org.slf4j.LoggerFactory;

import loveLetters.exception.CaNauraitJamaisDuArriverException;
import loveLetters.exception.LoveLettersException;
import loveLetters.exception.TricheException;

public class Partie {

    private org.slf4j.Logger log = LoggerFactory.getLogger(Partie.class);
    private LinkedList<Joueur> joueurs = new LinkedList<>();

    protected TransferQueue<Carte> getPioche() {
        return pioche;
    }

    private LinkedTransferQueue<Carte> pioche = new LinkedTransferQueue<>();
    private Joueur JoueurCourant;

    public Partie() {
        super();
        initPartie();
    }

    public List<Joueur> obtenirJoueurAttaquable() {
        List<Joueur> res = new ArrayList<>();
        for (Joueur joueur : joueurs) {
            if (joueur.getEtat() == EtatJoueur.NORMAL) {
                res.add(joueur);
            }
        }
        return res;
    }

    /**
     * definit le 1er joueur, donne une carte a tous les joueurs et une 2eme a celui qui commence
     * @throws Exception
     */
    public void start() throws Exception {
        choisir1erJoueur();
        for (Joueur joueur : joueurs) {
            joueur.setCarteActive(pioche.poll());
        }
        JoueurCourant.piocher(pioche);
        ;
    }

    private void choisir1erJoueur() {
        int nbJoueur = joueurs.size();
        int choix = (int) Math.round(Math.random() * (nbJoueur - 1));
        for (int i = 0; i < choix; i++) {
            obtenirJoueurSuivant();
        }
        JoueurCourant = obtenirJoueurSuivant();
    }

    protected boolean isFinDePArtie() {
        if (joueurs.size() == 1 || pioche.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * change le joueur courant et lui donne la 1ere carte de la pioche si elle existe.
     * @throws Exception
     */
    protected void debuterNouveauTour() throws CaNauraitJamaisDuArriverException {
        if (isFinDePArtie()) {
        }
        else {
            JoueurCourant = obtenirJoueurSuivant();
            JoueurCourant.piocher(pioche);
        }
    }

    private Joueur obtenirJoueurSuivant() {
        // on ajoute le premier element a la fin de la list puis on supprime le premier element.
        Joueur j = joueurs.getFirst();
        joueurs.offerLast(j);
        joueurs.removeFirst();
        return joueurs.getFirst();
    }

    public void jouer(Joueur j, Carte carte) throws LoveLettersException {
        j.defausserCarte(carte);
        debuterNouveauTour();
    }

    private void initPartie() {
        for (Carte c : Carte.values()) {
            pioche.add(c);
        }
    }

    public void ajouterJoueur(Joueur j) throws LoveLettersException {
        if (j == null || joueurs.contains(j)) {
            throw new LoveLettersException("tentative d'ajout d'un joueur null ou deja existant");
        }
        joueurs.add(j);
    }

    public void eliminerJoueur(Joueur j) {
        j.setEtat(EtatJoueur.MORT);
    }

    protected Collection<Joueur> getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueurCourant() {
        return JoueurCourant;
    }

    @Override
    public String toString() {
        StringBuilder sbJoueur = new StringBuilder();
        for (Joueur j : joueurs) {
            sbJoueur.append("joueur " + j + ",");
        }
        StringBuilder sbPioche = new StringBuilder();
        for (Carte c : pioche) {
            sbPioche.append("[" + c.name() + "],");
        }
        return "Partie : JoueurCourant : [" + JoueurCourant.afficherMain() + ", pioche=" + pioche + ", joueurs=" + joueurs + "]";
    }
}
