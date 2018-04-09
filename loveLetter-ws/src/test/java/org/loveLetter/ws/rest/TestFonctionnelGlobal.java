
package org.loveLetter.ws.rest;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import org.loveLetter.ws.domaine.Eleve;
import org.loveLetter.ws.domaine.Eleves;
import fr.insee.config.InseeConfig;

/**
 * Cette classe contient un test fonctionnel global de l'application
 * @author vywdlv
 *
 */
public class TestFonctionnelGlobal {

	private static Logger logger = Logger.getLogger(TestFonctionnelGlobal.class);
	
	/**
	 * Effectue un test fonctionnel global de l'application
	 */
	@Test
	public void testFonctionnelGlobal() {
		//TODO
	}
	
	
}
