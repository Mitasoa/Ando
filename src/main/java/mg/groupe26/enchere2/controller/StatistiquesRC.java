package mg.groupe26.enchere2.controller;

import java.util.List;
import mg.groupe26.enchere2.model.view.v_EnchereCategorie;
import mg.groupe26.enchere.util.Statistiques;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StatistiquesRC {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @GetMapping("/stat")
    public List<v_EnchereCategorie> getStat() {
        return (new Statistiques().getStat(jdbcTemplate));
    }
    
}
