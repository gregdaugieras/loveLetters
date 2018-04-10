package loveLetters.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private Logger log = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        log.warn("je suis dans getRootConfigClasses");
        return new Class[] { ContextConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        log.warn("je suis dans getServletConfigClasses");
        return new Class[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        log.warn("je suis dans getServletMappings");
        return new String[] { "/" };
    }
}
