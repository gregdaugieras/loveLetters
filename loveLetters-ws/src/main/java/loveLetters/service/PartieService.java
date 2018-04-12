package loveLetters.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import loveLetters.exception.LoveLettersException;
import loveLetters.iService.IPartieService;
import loveLetters.objetsMetier.EtatPartie;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

@Service("IPartieService")
public class PartieService implements IPartieService {

    private static Integer numeroPartie = new Integer(0);
    private static Map<Integer, Partie> partieMap;

    public PartieService() {
        if (partieMap == null) {
            partieMap = new HashMap<>();
        }
    }

    /*
     * (non-Javadoc)
     * @see loveLetters.service.IPartieService#getNouvellePartie()
     */
    @Override
    public Partie getNouvellePartie() {
        numeroPartie = numeroPartie + 1;
        Partie p = new Partie(numeroPartie);
        partieMap.put(numeroPartie, p);
        return new Partie(numeroPartie);
    }

    /*
     * (non-Javadoc)
     * @see loveLetters.service.IPartieService#getAllParties()
     */
    @Override
    public Collection<Partie> getAllParties() {
        Collection<Partie> parties = new ArrayList<>();
        parties = partieMap.values();
        return parties;
    }

    /*
     * (non-Javadoc)
     * @see loveLetters.service.IPartieService#getPartie(java.lang.Integer)
     */
    @Override
    public Partie getPartie(Integer i) {
        return partieMap.get(i);
    }

    /**
     * ajoute un joueur a la partie
     * @param j
     * @throws LoveLettersException si le joueur est null ou le joueur existe deja ou la partie est deja commencée
     */
    @Override
    public void ajouterJoueur(Partie p, Joueur j) throws LoveLettersException {
        if (j == null || p.getJoueurs().contains(j)) {
            throw new LoveLettersException("tentative d'ajout d'un joueur null ou deja existant");
        }
        if (p.getEtatPartie().ordinal() >= EtatPartie.COMMENCE.ordinal()) {
            throw new LoveLettersException("il n'est pas possible d'ajouter un joueur a ce moment de la partie [" + p.getEtatPartie() + "]");
        }
        p.getJoueurs().add(j);
    }

    @Override
    public void startPartie(Partie p) throws LoveLettersException {
        p.start();
    }

    @Override
    public Joueur getJoueur(Partie p, int id) {
        return p.getJoueurs().stream().filter(j -> j.getId() == id).collect(Collectors.toList()).get(0);
    }
}
