//package loveLetters.objetsMetier;
//
//import static org.junit.Assert.*;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//
//public class PartieTest {
//
//    private Logger log = Logger.getLogger(PartieTest.class);
//
//    @Test
//    public void initTest() throws Exception {
//        Partie p = new Partie(1);
//        Joueur j1 = new Joueur(1, "j1");
//        Joueur j2 = new Joueur(2, "j2");
//        assertTrue(p.getPioche().contains(Carte.SOLDAT));
//        assertTrue(p.getJoueurs().size() == 0);
//        p.ajouterJoueur(j1);
//        assertTrue(p.getJoueurs().size() == 1);
//        p.ajouterJoueur(j2);
//        assertTrue(p.getJoueurs().size() == 2);
//        p.start();
//        log.debug(p.toString());
//        while (!p.isFinDePArtie()) {
//            p.jouer(p.getJoueurCourant(), p.getJoueurCourant().getCartePiochee());
//            log.debug(p.toString());
//        }
//    }
//
//    @Test(expected = Exception.class)
//    public void ajouterJoueurDoubleTest() throws Exception {
//        Partie p = new Partie(1);
//        Joueur j1 = new Joueur(1);
//        p.ajouterJoueur(j1);
//        assertTrue(p.getJoueurs().size() == 1);
//        p.ajouterJoueur(j1);
//        assertTrue(p.getJoueurs().size() == 1);
//    }
//
//    @Test(expected = Exception.class)
//    public void ajouterJoueurNull() throws Exception {
//        Partie p = new Partie(1);
//        p.ajouterJoueur(null);
//        assertTrue(false);
//    }
//
//    @Test
//    public void debuterNouveauTourTestchangementJoueur() throws Exception {
//        Partie p = initTest2joueurs();
//        p.start();
//        Joueur j1 = p.getJoueurCourant();
//        p.debuterNouveauTour();
//        assertFalse("premierJoueur = " + j1.getPseudo() + " joueurSuivant = " + p.getJoueurCourant().getPseudo(), j1.equals(p.getJoueurCourant()));
//    }
//
//    @Test
//    public void isFinDePartieTest() throws Exception {
//        Partie p = initTest2joueurs();
//        p.start();
//        assertFalse(p.isFinDePArtie());
//        for (Joueur j : p.getJoueurs()) {
//            j.setEtat(EtatJoueur.MORT);
//        }
//        p.getJoueurCourant().setEtat(EtatJoueur.PROTEGE);
//        assertTrue(p.isFinDePArtie());
//        p.getJoueurSuivant().setEtat(EtatJoueur.NORMAL);
//        assertFalse(p.isFinDePArtie());
//    }
//
//    /**
//     * on verifie que les cartes sont bien repartie lors de l'initialisation :
//     * 1 carte par joueur et 2 pour le joueur courant
//     * @throws Exception
//     */
//    @Test
//    public void demarrerPArtieTest() throws Exception {
//        Partie p = initTest2joueurs();
//        int taillePiocheDebut = p.getPioche().size();
//        p.start();
//        int nbCarteJoueurs = (int) p.getJoueurs().stream()
//                .mapToInt((j) -> ((j.getCarteActive() == null ? 0 : 1) + (j.getCartePiochee() == null ? 0 : 1))).sum();
//        assertEquals(3, nbCarteJoueurs);
//        assertEquals(taillePiocheDebut - 3, p.getPioche().size());
//    }
//
//    private Partie initTest2joueurs() throws Exception {
//        Partie p = new Partie(1);
//        Joueur j1 = new Joueur(1, "j1");
//        Joueur j2 = new Joueur(2, "j2");
//        p.ajouterJoueur(j1);
//        p.ajouterJoueur(j2);
//        return p;
//    }
//}
