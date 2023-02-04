package mg.groupe26.enchere2.controller;

import java.util.List;
import mg.groupe26.enchere2.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UtilisateurRC {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getUtilisateurs() {
        return (new Utilisateur().select("select * from Utilisateur", jdbcTemplate));
    }
    
    @GetMapping("/utilisateurs/{id}")
    public Utilisateur getUtilisateurById(@PathVariable String id) {
        String query = String.format("select * from Utilisateur where id = '%s' ", id);
        return (new Utilisateur(id, null, null, null, null).select(query, jdbcTemplate).get(0));
    }

    @GetMapping("/loginUtilisateur")
    public Utilisateur loginUtilisateur(@RequestParam String email,
            @RequestParam String mdp) {
        return (new Utilisateur(null, null, null, email, mdp).login(jdbcTemplate));
    }

    @GetMapping("/inscription")
    public void addUtilisateur(
            @RequestParam String nom,
            @RequestParam String pseudo,
            @RequestParam String email,
            @RequestParam String mdp) {
        new Utilisateur(null, nom, pseudo, email, mdp).insert(jdbcTemplate);
    }
    
    @GetMapping("/solde/{utilisateurId}")
    public double getSolde(@PathVariable String utilisateurId) {
        return (new Utilisateur(utilisateurId, null, null, null, null).getSolde(jdbcTemplate));
    }

}
