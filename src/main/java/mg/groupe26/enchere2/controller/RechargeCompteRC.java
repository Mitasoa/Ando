package mg.groupe26.enchere2.controller;

import java.util.List;
import mg.groupe26.enchere2.model.RechargeCompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RechargeCompteRC {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/rechargeComptes")
    public List<RechargeCompte> getRechargeComptes() {
        return (new RechargeCompte().select("select * from v_rechargecompte", jdbcTemplate));
    }

    @GetMapping("/addRechargeCompte/{utilisateurId}")
    public void addRechargeCompte(
            @RequestParam double montant,
            @PathVariable String utilisateurId) {
        new RechargeCompte(null, utilisateurId, montant, null, null).insert(jdbcTemplate);
    }

    @GetMapping("/acceptRechargeCompte/{id}")
    public void acceptRechargeCompte(@PathVariable String id) {
        new RechargeCompte(id, null, null, 1, null).update(jdbcTemplate);
    }

    @GetMapping("/declineRechargeCompte/{id}")
    public void declineRechargeCompte(@PathVariable String id) {
        new RechargeCompte(id, null, null, -1, null).update(jdbcTemplate);
    }
    
    
}
