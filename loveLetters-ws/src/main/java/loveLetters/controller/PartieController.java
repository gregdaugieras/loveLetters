package loveLetters.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import loveLetters.objetsMetier.Partie;

@RestController
@RequestMapping(value = "/partie")
public class PartieController {

    public PartieController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Partie getPartie() {
        return new Partie(1);
    }
}
