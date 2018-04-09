
package org.loveLetter.ws.ressources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.loveLetter.ws.dao.impl.InitialisationImpl;

/**
 * Cette classe contient le service permettant l'initialisation de
 * la base de données
 * @author Antoine Pasquale
 *
 */
@Path("/init")
@Api(value = "/init", description = "Initialisation de la base de données")
public class InitRessource {
	private static Logger logger = Logger.getLogger(InitRessource.class);
	
	/**
	 * Construit un InitRessource
	 */
	public InitRessource() {
	}
	
	/**
	 * initialise la base de données
	 * @return La réponse HTTP
	 */
	@GET
	@ApiOperation(value="Initialise la base de données")
	public Response initialiser() {
		if (logger.isInfoEnabled()) {
			logger.info("initialiser() appelée");
		}
		try {
			InitialisationImpl.getInstance().initBaseDeDonnees();
			if (logger.isInfoEnabled()) {
				logger.info("Réinitialisation effectuée avec succès");
			}
			return Response.status(200).build();
		} catch (Exception e) {
			logger.error("Erreur lors de la réinitialisation :"+e);
			return Response.status(500).build();
		}
	}
}
