package loveLetters.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loveLetters.exception.LoveLettersException;
import loveLetters.formulaire.CoupPoste;
import loveLetters.iService.IActionJouer;
import loveLetters.iService.IFabriqueActionJouer;
import loveLetters.iService.IPartieService;
import loveLetters.objetsMetier.Carte;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;
import loveLetters.service.FabriqueActionJouer;
import loveLetters.service.JoueurService;

@RestController
@RequestMapping(value = "/partie")
public class PartieController {

    Logger log = LoggerFactory.getLogger(PartieController.class);
    @Autowired
    private IPartieService partieService;
    @Autowired
    private JoueurService joueurService;
    private IFabriqueActionJouer fabriqueActionJouer = new FabriqueActionJouer();

    public PartieController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping(value = "/new")
    public Partie getNouvellePartie() {
        return partieService.getNouvellePartie();
    }

    @GetMapping(value = "/new1")
    public Partie getNouvellePartie1() {
        return partieService.initPartie1();
    }

    @GetMapping(value = "/")
    public Collection<Partie> getAllParties() {
        return partieService.getAllParties();
    }

    @GetMapping(value = "/{idPartie}")
    public Partie getPartie(@PathVariable("idPartie") Partie p) {
        return p;
    }

    @GetMapping(value = "/{idPartie}/rejoindre")
    public Partie AjouterJoueur(@PathVariable("idPartie") Partie p) throws LoveLettersException {
        partieService.ajouterJoueur(p, joueurService.getNouveauJoueur());
        return p;
    }

    @GetMapping(value = "/{idPartie}/start")
    public Partie startPartie(@PathVariable("idPartie") Partie p) throws LoveLettersException {
        partieService.startPartie(p);
        return p;
    }

    @PostMapping(value = "/{idPartie}/jouer")
    public Carte jouer(@PathVariable("idPartie") Partie p, @RequestBody CoupPoste coup) throws LoveLettersException {
        IActionJouer aj = fabriqueActionJouer.creerAction(p, partieService.getJoueur(p, coup.getNumJoueurCourant()), coup.getCarteJoue(),
                partieService.getJoueur(p, coup.getNumJoueurCible()), coup.getCarteCible());
        Carte resultat = aj.jouer();
        log.info(aj.toString());
        p.debuterNouveauTour();
        return resultat;
    }

    @GetMapping(value = "/{idPartie}/joueur/{idJoueur}")
    public List<Carte> getCartes(@PathVariable("idPartie") Partie p, @PathVariable("idJoueur") int j) throws LoveLettersException {
        return partieService.getMains(p, partieService.getJoueur(p, j));
    }

    @GetMapping(value = "/{idPartie}/joueurCourant")
    public Joueur getJoueurCourant(@PathVariable("idPartie") Partie p) throws LoveLettersException {
        return p.getJoueurCourant();
    }
}
