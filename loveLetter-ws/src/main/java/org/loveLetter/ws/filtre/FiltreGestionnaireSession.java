
package org.loveLetter.ws.filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import org.loveLetter.ws.transversal.HibernateUtil;
import fr.insee.config.InseeConfig;

public class FiltreGestionnaireSession implements Filter {

	private static Logger log = Logger
			.getLogger(FiltreGestionnaireSession.class);

	private SessionFactory sf;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (log.isInfoEnabled()) {
			log.info("FiltreGestionnaireSession init()");
		}
		HibernateUtil.init();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (log.isInfoEnabled()) {
			log.info("lancement de l'interceptor, connection à la base de données : "
					+ InseeConfig.getConfig().getString(
							"fr.insee.database.h2Main.url"));
		}

		try {
			sf = HibernateUtil.getSessionFactory();

			if (!sf.getCurrentSession().isOpen()) {
				HibernateUtil.init();
			}
			sf.getCurrentSession().beginTransaction();

			chain.doFilter(request, response);

			if (sf.getCurrentSession().getTransaction().isActive()) {
				// Cas standard, on met le if pour les cas de "chainage"
				// d'action (sans cela, le programme tenterai de commiter 2 fois
				// la transaction, et déclencherait une erreur au 2eme commit,
				// vu que la transaction n'est plus active)
				sf.getCurrentSession().getTransaction().commit();
			}

			if (log.isInfoEnabled()) {
				log.info("Fermeture du filtre");
			}
		} catch (StaleObjectStateException e) {
			log.error("Ce filtre n'implémente pas la gestion optimiste de la concurrence");
			throw e;
		} catch (Exception e) {
			log.error("Le filtre reçoit une exception", e);
			if (sf.getCurrentSession().getTransaction().isActive()) {
				try {
					if (log.isInfoEnabled()) {
						log.info("Tentative de Rollback");
					}
					sf.getCurrentSession().getTransaction().rollback();
					if (log.isInfoEnabled()) {
						log.info("rollback réussi");
					}
				} catch (Exception rollbacke) {
					log.error("rollback échoué");
				}
			}
			//Pour faire disparaître l'erreur "Unhandled exception type Exception" :
			//aller dans File/Properties/Project Facets puis passer le project facet "Java" à "1.7".
			throw e;

		}
	}

	@Override
	public void destroy() {
		if (log.isInfoEnabled()) {
			log.info("Fermeture de FitreGestionnaireSession.");
		}
		HibernateUtil.shutdown();
	}

}
