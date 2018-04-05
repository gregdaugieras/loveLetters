package loveLetters.objetsMetier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

import loveLetters.exception.CaNauraitJamaisDuArriverException;
import loveLetters.exception.LoveLettersException;

public class Partie {

    private org.slf4j.Logger log = LoggerFactory.getLogger(Partie.class);
    private LinkedList<Joueur> joueurs = new LinkedList<>();
    private EtatPartie etatPartie;

    public TransferQueue<Carte> getPioche() {
        return pioche;
    }

    private LinkedTransferQueue<Carte> pioche = new LinkedTransferQueue<>();
    private Joueur JoueurCourant;

    public Partie() {
        super();
        initPartie();
        etatPartie = EtatPartie.INITIALISE;
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
    public void start() throws LoveLettersException {
        choisir1erJoueur();
        for (Joueur joueur : joueurs) {
            joueur.setCarteActive(pioche.poll());
        }
        JoueurCourant.piocher(pioche);
        etatPartie = EtatPartie.COMMENCE;
    }

    private void choisir1erJoueur() {
        int nbJoueur = joueurs.size();
        int choix = (int) Math.round(Math.random() * (nbJoueur - 1));
        for (int i = 0; i < choix; i++) {
            obtenirJoueurVivantSuivant();
        }
        JoueurCourant = obtenirJoueurVivantSuivant();
    }

    /**
     * verfie si la partie est terminé car il ne reste plus qu'un joueur ou que la pioche est vide
     * @return true si la pioche est vide ou s'il ne reste qu'un joueur, flase sinon
     */
    public boolean isFinDePArtie() {
        if (joueurs.size() == 1 || pioche.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * change le joueur courant et lui donne la 1ere carte de la pioche si elle existe.
     * @throws Exception
     */
    public void debuterNouveauTour() throws CaNauraitJamaisDuArriverException {
        if (isFinDePArtie()) {
            etatPartie = EtatPartie.FINI;
        }
        else {
            JoueurCourant = obtenirJoueurVivantSuivant();
            JoueurCourant.piocher(pioche);
        }
    }

    /**
     * permet de recuperer le prochain joueur non mort
     * @return
     */
    private Joueur obtenirJoueurVivantSuivant() {
        // on ajoute le premier element a la fin de la list puis on supprime le premier element.
        Joueur j = obtenirJoueurSuivant1();
        while (j.getEtat() == EtatJoueur.MORT) {
            j = obtenirJoueurSuivant1();
        }
        return j;
    }

    /**
     * recupere le prochain joueur en decalant la liste chainé de joueur d'une position
     * @return
     */
    private Joueur obtenirJoueurSuivant1() {
        Joueur j = joueurs.getFirst();
        joueurs.offerLast(j);
        joueurs.removeFirst();
        return joueurs.getFirst();
    }

    public void jouer(Joueur j, Carte carte) throws LoveLettersException {
        j.defausserCarte(carte);
        debuterNouveauTour();
    }

    /**
     * rempli la pioche de carte
     */
    private void initPartie() {
        for (Carte c : Carte.values()) {
            pioche.add(c);
        }
    }

    /**
     * ajoute un joueur a la partie
     * @param j
     * @throws LoveLettersException si le joueur est null ou le joueur existe deja ou la partie est deja commencée
     */
    public void ajouterJoueur(Joueur j) throws LoveLettersException {
        if (j == null || joueurs.contains(j)) {
            throw new LoveLettersException("tentative d'ajout d'un joueur null ou deja existant");
        }
        if (etatPartie.ordinal() >= EtatPartie.COMMENCE.ordinal()) {
            throw new LoveLettersException("il n'est pas possible d'ajouter un joueur a ce moment de la partie [" + etatPartie + "]");
        }
        joueurs.add(j);
    }

    /**
     * passe l'etat d'un joueur a MORT
     * @param j
     */
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
