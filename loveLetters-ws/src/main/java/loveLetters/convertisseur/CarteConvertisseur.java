package loveLetters.convertisseur;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import loveLetters.objetsMetier.Carte;

@Component
public class CarteConvertisseur implements Converter<Integer, Carte> {

    public CarteConvertisseur() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Carte convert(Integer source) {
        return Carte.recupererCarteByNumero(source);
    }
}
