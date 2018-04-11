package loveLetters.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loveLetters.exception.LoveLettersException;
import loveLetters.iService.IPartieService;
import loveLetters.objetsMetier.Joueur;
import loveLetters.objetsMetier.Partie;

@RestController
@RequestMapping(value = "/partie")
public class PartieController {

    @Autowired
    private IPartieService partieService;

    public PartieController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping(value = "/new")
    public Partie getNouvellePartie() {
        return partieService.getNouvellePartie();
    }

    @GetMapping(value = "/toutes")
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
}
