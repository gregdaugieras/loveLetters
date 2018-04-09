
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
import static org.mockito.Matchers.*;
import org.loveLetter.ws.domaine.Eleve;
import org.loveLetter.ws.service.EleveService;

/**
 * Tests associés à la classe EleveRessource
 * @author Antoine Pasquale
 *
 */
public class EleveRessourceTest {
	
	private EleveRessource eleveRessource;
	
	@Mock
	private EleveService eleveServiceMock;

	/**
	 * S'exécute avant chaque test
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		eleveRessource = new EleveRessource(eleveServiceMock);
	}
	
	/**
	 * Teste l'obtention d'un élève
	 */
	@Test
	public void testObtenirUnEleve() {
		Eleve eleve = new Eleve();
		eleve.setId(1);
		eleve.setNom("NOM");
		eleve.setPrenom("Prénom");
		List<Eleve> listEleve = new ArrayList<Eleve>();
		listEleve.add(eleve);
		when(eleveServiceMock.obtenirListeEleve()).thenReturn(listEleve);
		
		Response response = eleveRessource.obtenirUnEleve(1);
		Eleve eleve2 = (Eleve) response.getEntity();
		
		assertEquals(200,response.getStatus());
		assertTrue(eleve2.getId()==1 && eleve2.getNom().equals("NOM") && eleve2.getPrenom().equals("Prénom"));
	}

	/**
	 * Teste la création et la modification d'un élève
	 */
	@Test
	public void testCreeOuModifieEleve() {
		Eleve eleve;
		List<Eleve> listEleve = new ArrayList<Eleve>();
		eleve = new Eleve();
		eleve.setId(1);
		eleve.setNom("NOM");
		eleve.setPrenom("Prénom");
		listEleve.add(eleve);		
		when(eleveServiceMock.obtenirListeEleve()).thenReturn(listEleve);
		doNothing().when(eleveServiceMock).ajouterEleve((Eleve)anyObject());
				
		Response responseCreer = eleveRessource.creeOuModifieEleve(eleve,1);
		
		eleve.setNom("TRUC");
		Response responseModifier = eleveRessource.creeOuModifieEleve(eleve,1);
		
		assertEquals(201, responseCreer.getStatus());
		assertEquals(201, responseModifier.getStatus());	
	}

	/**
	 * Teste la suppression d'un élève
	 */
	@Test
	public void testSupprimeEleve() {
		Eleve eleve;
		List<Eleve> listEleve = new ArrayList<Eleve>();
		eleve = new Eleve();
		eleve.setId(1);
		eleve.setNom("NOM");
		eleve.setPrenom("Prénom");
		listEleve.add(eleve);		
		when(eleveServiceMock.obtenirListeEleve()).thenReturn(listEleve);
		doNothing().when(eleveServiceMock).supprimerEleve(anyInt());
				
		Response response = eleveRessource.supprimeEleve(1);
		
		assertEquals(200, response.getStatus());
	}

}
