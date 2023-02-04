package mg.groupe26.enchere2.controller;

import java.util.List;
import mg.groupe26.enchere2.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminRC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/admins")
    public List<Admin> getAdmins() {
        return (new Admin().select("select * from Admin", jdbcTemplate));
    }

    @GetMapping("/loginAdmin")
    public Admin loginAdmin(@RequestParam String email,
            @RequestParam String mdp) {
        return (new Admin(null, email, mdp).login(jdbcTemplate));
    }

    @GetMapping("/addAdmin")
    public void addAdmin(@RequestParam String email,
            @RequestParam String mdp) {
        new Admin(null, email, mdp).insert(jdbcTemplate);
    }

}
