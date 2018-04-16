package loveLetters.convertisseur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import loveLetters.exception.LoveLettersException;
import loveLetters.iService.IPartieService;
import loveLetters.objetsMetier.Partie;
import loveLetters.service.PartieService;

@Component
public class PartieConvertisseur implements Converter<String, Partie> {

    // TODO comprendre pourquoi l'autowired ne fonctionne pas
    @Autowired
    private IPartieService partieService = new PartieService();

    public PartieConvertisseur() {
    }

    @Override
    public Partie convert(String id) {
        return partieService.getPartie(Integer.valueOf(id));
    }
}
