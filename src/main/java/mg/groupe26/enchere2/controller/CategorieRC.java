package mg.groupe26.enchere2.controller;

import java.util.List;
import mg.groupe26.enchere2.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategorieRC {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @GetMapping("/categories")
    public List<Categorie> getCategories() {
        return (new Categorie().select("select * from categorie", jdbcTemplate));
    }
    
}
