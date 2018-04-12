package loveLetters.convertisseur;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import loveLetters.objetsMetier.Joueur;

@Component
public class JoueurConvertisseur implements Converter<String, Joueur> {

    public JoueurConvertisseur() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Joueur convert(String source) {
        // TODO Auto-generated method stub
        return null;
    }
}
