
package org.loveLetter.ws.ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.loveLetter.ws.domaine.Eleve;
import org.loveLetter.ws.domaine.Eleves;
import org.loveLetter.ws.service.EleveService;
import org.loveLetter.ws.service.impl.EleveServiceImpl;

/**
 * Cette classe contient les services permettant de lire et créer des élèves
 * @author Antoine Pasquale
 *
 */
@Path("/eleves")
@Api(value = "/eleves", description = "Opérations sur les élèves")
public class ElevesRessource {
	private static Logger logger = Logger.getLogger(ElevesRessource.class);
	
	private EleveService eleveService;
	
	/**
	 * Construit un ElevesRessource
	 */
	public ElevesRessource() {
		this.eleveService = new EleveServiceImpl();
	}
	
	/**
	 * Construit un ElevesRessource à partir d'un DAO
	 * @param eleveDao Le DAO
	 */
	public ElevesRessource(EleveService eleveService) {
		this.eleveService = eleveService;
	}
	
	/**
	 * Renvoie la liste des élèves.
	 * @return La réponse HTTP
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Renvoie la liste des élèves")
	public Response obtenirListeEleves() {
		if (logger.isInfoEnabled()) {
			logger.info("obtenirListeEleves() appelée");
		}
		
		try {
			List<Eleve> listEleves = eleveService.obtenirListeEleve();
			Eleves eleves = new Eleves();
			eleves.setEleves(listEleves);
					
			if (logger.isInfoEnabled()) {
				logger.info("Elèves obtenus avec succès");
			}
			return Response.status(200).entity(eleves).build();
		} catch (Exception e) {
			
			logger.error("Erreur lors de l'obtention des élèves :"+e);
			return Response.status(500).build();
		}
	}
	
	/**
	 * Ajoute un élève à partir de données XML.
	 * @param xmlEleve Les données de l'élève au format XML.
	 * @return La réponse HTTP
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Ajoute un élève")
	public Response ajouterUnEleve(Eleve eleve) {
		if (logger.isInfoEnabled()) {
			logger.info("ajouterUnEleve() appelée");
		}
		
		try {
			eleveService.ajouterEleve(eleve);
						
			if (logger.isInfoEnabled()) {
				logger.info("Elève ajouté avec succès");
			}
			return Response.status(201).build();

		} catch (Exception e) {
			logger.error("Erreur lors de l'ajout d'un élève :"+e);
			return Response.status(500).build();
		}
		
	}
	
}
