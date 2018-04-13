package loveLetters.controller;

import java.util.Collection;

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
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;
import loveLetters.service.ActionJouer;
import loveLetters.service.FabriqueActionJouer;

@RestController
@RequestMapping(value = "/partie")
public class PartieController {

    @Autowired
    private IPartieService partieService;
    private IFabriqueActionJouer fabriqueActionJouer = new FabriqueActionJouer();

    public PartieController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping(value = "/new")
    public Partie getNouvellePartie() {
        return partieService.getNouvellePartie();
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
        int random = (int) (Math.round(Math.random() * 1000));
        partieService.ajouterJoueur(p, new Joueur(random, "testJoueur" + random));
        return p;
    }

    @GetMapping(value = "/{idPartie}/start")
    public Partie startPartie(@PathVariable("idPartie") Partie p) throws LoveLettersException {
        partieService.startPartie(p);
        return p;
    }

    @PostMapping(value = "/{idPartie}/jouer")
    public Partie jouer(@PathVariable("idPartie") Partie p, @RequestBody CoupPoste coup) throws LoveLettersException {
        IActionJouer aj = fabriqueActionJouer.creerAction(p, partieService.getJoueur(p, coup.getNumJoueurCourant()), coup.getCarteJoue(),
                partieService.getJoueur(p, coup.getNumJoueurCible()), coup.getCarteCible());
        aj.jouer();
        return p;
    }
}
