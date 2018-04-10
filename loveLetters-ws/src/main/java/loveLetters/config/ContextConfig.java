package loveLetters.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = { "loveLetters" })
public class ContextConfig {

    public ContextConfig() {
        // TODO Auto-generated constructor stub
    }
}
