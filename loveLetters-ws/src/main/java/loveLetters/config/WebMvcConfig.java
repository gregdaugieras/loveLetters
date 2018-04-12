package loveLetters.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import loveLetters.convertisseur.CarteConvertisseur;
import loveLetters.convertisseur.PartieConvertisseur;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "loveLetters" })
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PartieConvertisseur());
        registry.addConverter(new CarteConvertisseur());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
