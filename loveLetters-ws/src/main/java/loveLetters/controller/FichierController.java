package loveLetters.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fichier")
public class FichierController {

    public FichierController() {
        // TODO Auto-generated constructor stub
    }

    @GetMapping("/")
    @ResponseBody
    public String helloWord() {
        return "hello World";
    }

    @GetMapping("/fichier")
    public void getFichier(HttpServletResponse response) throws IOException {
        File f = new File("D:\\nat2015.txt");
        response.addHeader("Content-Disposition", "attachment;filename=myfilename.csv");
        FileInputStream fis = new FileInputStream(f);
        IOUtils.copy(fis, response.getOutputStream());
        response.flushBuffer();
    }

    @GetMapping(value = "/download", headers = "Accept=application/octet-stream")
    public void download(HttpServletResponse response) throws IOException, ClassNotFoundException {
        String requete = "copy (select dirindik,dirindic,dir,csdep from s_accueil_2015.t_pote where CSDEP='24') TO STDOUT with delimiter ';'";
        Class.forName("org.postgresql.Driver");
        response.addHeader("Content-Disposition", "attachment;filename=dl.csv");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://qffidelildb02.ad.insee.intra:1983/ri_pg_fideli_qf02", "vxuxi5",
                "F0uin0u!!")) {
            // try (Connection connection = DriverManager.getConnection("jdbc:postgresql://dvfidelildb02.ad.insee.intra:1983/di_pg_fideli_dv02",
            // "user_fideli_loc", "6543fidelI")) {
            CopyManager cm = new CopyManager((BaseConnection) connection);
            cm.copyOut(requete, response.getOutputStream());
            response.flushBuffer();
            // BufferedReader br = new BufferedReader(new FileInputStream(new File("test.txt")));
            // File file = new File("D:\\nat2015.txt");
            // Path path = Paths.get(file.getAbsolutePath());
            // ByteArrayResource resource = new ByteArrayResource;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
