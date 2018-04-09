
package org.loveLetter.ws.ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.loveLetter.ws.domaine.Eleve;
import org.loveLetter.ws.service.EleveService;
import org.loveLetter.ws.service.impl.EleveServiceImpl;

/**
 * Cette classe contient les services permettant de lire, créer, modifier et supprimer
 * des données sur les élèves.
 * @author Antoine Pasquale
 *
 */
@Path("/eleve")
@Api(value = "/eleve", description = "Opérations sur un élève")
public class EleveRessource {
	private static Logger logger = Logger.getLogger(EleveRessource.class);

	private EleveService eleveService;
	
	/**
	 * Construit un EleveRessource
	 */
	public EleveRessource() {
		this.eleveService = new EleveServiceImpl();
	}
	
	/**
	 * Construit un EleveRessource avec un certain DAO
	 * @param eleveDao Le DAO
	 */
	public EleveRessource(EleveService eleveService) {
		this.eleveService = eleveService;
	}	
	
	/**
	 * Renvoie les données d'un élève
	 * @param strId L'identifiant de l'élève
	 * @return La réponse HTTP
	 */
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Renvoie les données sur un élève")
	public Response obtenirUnEleve(@PathParam("id") int id) {
		if (logger.isInfoEnabled()) {
			logger.info("obtenirUnEleve() appelée");
		}
		
		try {
			Eleve eleve = null;
			List<Eleve> listEleve = eleveService.obtenirListeEleve();
			for (Eleve e : listEleve) {
				if (e.getId()==id) {
					eleve = e;
				}
			}			
			
			if (logger.isInfoEnabled()) {
				logger.info("Elève obtenu avec succès");
			}
			
			return Response.status(200).entity(eleve).build();
		} catch (Exception e) {
			logger.error("Echec de l'obtention d'un élève :"+e);
			return Response.status(500).build();
		}
	}
	
	
	/**
	 * Crée ou modifie un élève à partir de données XML.
	 * Si l'élève associé à l'identifiant existe, effectue sa modification.
	 * Sinon, crée un élève avec pour identifiant le prochain identifiant
	 * de la séquence (et non pas celui du paramètre strId).
	 * @param xmlEleve Les données XML.
	 * @param strId L'identifiant
	 * @return La réponse HTTP
	 */
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Crée ou modifie un élève")
	public Response creeOuModifieEleve(Eleve eleveEntree, @PathParam("id") int id) {
		if (logger.isInfoEnabled()) {
			logger.info("creeOuModifieEleve() appelée");
		}		
		try {
			if (logger.isInfoEnabled()) {
				logger.info("Recherche de l'élève dont l'identifiant est passé en paramètre");
			}
			Eleve eleve = null;
			List<Eleve> eleves = eleveService.obtenirListeEleve();
			for (Eleve e : eleves) {
				if (e.getId() == id) {
					eleve = e;
				}
			}
			
			//S'il n'existe pas d'élève de cet identifiant, on crée un élève
			if (eleve==null) {
				eleve = new Eleve();
			}
			
			eleve.setNom(eleveEntree.getNom());
			eleve.setPrenom(eleveEntree.getPrenom());
									
			if (logger.isInfoEnabled()) {
				logger.info("Création ou mise à jour de l'élève");
			}
			
			eleveService.ajouterEleve(eleve);

			if (logger.isInfoEnabled()) {
				logger.info("Elève créé ou modifié avec succès");
			}
			return Response.status(201).build();

		} catch (Exception e) {

			logger.error("Echec lors de la création ou de la modification d'un élève :"+e);
			return Response.status(500).build();
		}
	}
	
	/**
	 * Supprime un élève selon son identifiant.
	 * @param strId L'identifiant
	 * @return La réponse HTTP
	 */
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Supprime un élève")
	public Response supprimeEleve(@PathParam("id") int id) {	
		if (logger.isInfoEnabled()) {
			logger.info("supprimeEleve() appelée");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Recherche de l'élève dont l'identifiant est le même que celui passé en paramètre");
		}
		Eleve eleve = null;
		List<Eleve> eleves = eleveService.obtenirListeEleve();
		for (Eleve e : eleves) {
			if (e.getId() == id) {
				eleve = e;
			}
		}
		
		//Si l'élève existe, on le supprime.
		if (eleve!=null) {
			eleveService.supprimerEleve(id);
			if (logger.isInfoEnabled()) {
				logger.info("Elève supprimé avec succès");
			}
			return Response.status(200).build();
		} else {
			logger.error("Il n'existe pas d'élève portant cet identifiant");
			return Response.status(500).build();
		}
	}
	
	
}
