
package org.loveLetter.ws.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import org.loveLetter.ws.domaine.Eleve;
import org.loveLetter.ws.domaine.Eleves;
import org.loveLetter.ws.service.EleveService;

/**
 * Tests associés à la classe ElevesRessource
 * @author vywdlv
 *
 */
public class ElevesRessourceTest {
	
	private ElevesRessource elevesRessource;
	
	@Mock
	private EleveService eleveServiceMock;
		
	
	/**
	 * S'exécute avant chaque test
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		elevesRessource = new ElevesRessource(eleveServiceMock);
	}
	
	/**
	 * Teste l'obtention de la liste des élèves
	 */
	@Test
	public void testObtenirListeEleves() {
		Eleve eleve;
		List<Eleve> listEleve = new ArrayList<Eleve>();
		eleve = new Eleve();
		eleve.setId(1);
		eleve.setNom("NOM");
		eleve.setPrenom("Prénom");
		listEleve.add(eleve);		
		when(eleveServiceMock.obtenirListeEleve()).thenReturn(listEleve);
		
		Response response = elevesRessource.obtenirListeEleves();	
		Eleve eleve2 = ((Eleves) response.getEntity()).getEleves().get(0);
		
		assertEquals(200,response.getStatus());
		assertTrue(eleve2.getId()==1 && eleve2.getNom().equals("NOM") && eleve2.getPrenom().equals("Prénom"));
		
	}

	/**
	 * Teste l'ajout d'un élève
	 */
	@Test
	public void testAjouterUnEleve() {
		doNothing().when(eleveServiceMock).ajouterEleve((Eleve)anyObject());
		
		Eleve eleve = new Eleve();
		eleve.setNom("MACHIN");
		eleve.setPrenom("TRUC");
		
		Response responseAjouter = elevesRessource.ajouterUnEleve(eleve);
		Response responseObtenir = elevesRessource.obtenirListeEleves();

		assertEquals(201,responseAjouter.getStatus());
		assertEquals(200,responseObtenir.getStatus());
	}

}
