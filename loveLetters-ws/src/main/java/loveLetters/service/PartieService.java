package loveLetters.service;

import org.springframework.stereotype.Service;

import loveLetters.objetsMetier.Partie;

@Service
public class PartieService {

    public PartieService() {
        // TODO Auto-generated constructor stub
    }

    public Partie getPArtie() {
        return new Partie(1);
    }
}
