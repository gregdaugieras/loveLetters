package loveLetters.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loveLetters.objetsMetier.Partie;
import loveLetters.service.PartieService;

@RestController
@RequestMapping(value = "/partie")
public class PartieController {

    private Logger log = LoggerFactory.getLogger(PartieController.class);
    @Autowired
    private PartieService partieService;

    public PartieController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping(value = "/new")
    public Partie getPartie() {
        log.debug("j'entre dans mon get/partie/new");
        return partieService.getPArtie();
    }
}
