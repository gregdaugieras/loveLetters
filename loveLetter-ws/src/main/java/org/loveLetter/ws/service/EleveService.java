
package org.loveLetter.ws.service;

import java.util.List;

import org.loveLetter.ws.domaine.Eleve;

public interface EleveService {

	List<Eleve> obtenirListeEleve();

	void ajouterEleve(Eleve eleve);

	void supprimerEleve(int id);

}
