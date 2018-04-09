
package org.loveLetter.ws.servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.loveLetter.ws.service.InitialisationService;
import org.loveLetter.ws.service.impl.InitialisationServiceImpl;

/**
 * Classe de configuration du servlet Jersey
 * @author Antoine Pasquale
 *
 */
public class JerseyServletConfig extends ResourceConfig {
	public JerseyServletConfig() {
		packages("com.wordnik.swagger.jaxrs.json;org.loveLetter.ws.domaine.Eleve;org.loveLetter.ws.ressources");
		register(com.wordnik.swagger.jersey.listing.ApiListingResourceJSON.class);
		register(com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider.class);
		register(com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider.class);
		property(ServerProperties.WADL_FEATURE_DISABLE, true);
		
		initialiserBaseDeDonnees();
	}

	private void initialiserBaseDeDonnees() {
		InitialisationService initService = new InitialisationServiceImpl();
		try {
			initService.initBaseDeDonnees();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
