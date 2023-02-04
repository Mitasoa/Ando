package mg.groupe26.enchere2.controller;

import mg.groupe26.enchere.util.Verif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TokenRC {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/verifToken/{token}")
    public int verifToken(@PathVariable String token) {
        return (new Verif().tokenValide(token, jdbcTemplate));
    }
    
}
