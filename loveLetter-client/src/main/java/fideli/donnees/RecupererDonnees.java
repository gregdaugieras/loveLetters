package fideli.donnees;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RecupererDonnees {

    private static Logger log = LoggerFactory.getLogger(RecupererDonnees.class);

    public RecupererDonnees() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        PipedReader pr;
        RestTemplate restTemplate = new RestTemplate();
        String message = restTemplate.getForObject("http://localhost:8080/fichier/", String.class);
        log.info("message recu : " + message);
        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
        acceptableMediaTypes.add(MediaType.ALL);
        acceptableMediaTypes.add(MediaType.valueOf("application/octet-stream"));
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        // Send the request as GET
        ResponseEntity<PipedReader> result = restTemplate.exchange("http://localhost:8080/fichier/download", HttpMethod.GET, entity,
                PipedReader.class);
        pr = result.getBody();
        log.info("message recu : " + message);
    }
}
