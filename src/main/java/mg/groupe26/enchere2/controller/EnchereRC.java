package mg.groupe26.enchere2.controller;

import java.util.ArrayList;
import java.util.List;
import mg.groupe26.enchere2.model.Categorie;
import mg.groupe26.enchere2.model.Enchere;
import mg.groupe26.enchere2.model.Historique;
import mg.groupe26.enchere2.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EnchereRC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/encheres")
    public List<Enchere> getEncheres() {
        return (new Enchere().select("select * from v_Enchere", jdbcTemplate));
    }

    @GetMapping("/encheres/{id}")
    public Object[] getEnchereById(@PathVariable String id) {
        Object[] result = new Object[1];
        Enchere enchere = null;

        enchere = new Enchere().select(String.format("select * from v_Enchere where id = '%s'", id), jdbcTemplate).get(0);
        enchere.setProprietaire(new Utilisateur(enchere.getProprietaireId(), null, null, null, null).selectById(jdbcTemplate));
        enchere.setCategorie(new Categorie(enchere.getCategorieId(), null).selectById(jdbcTemplate));

        if (enchere.getGagnantId() != null) {
            enchere.setGagnant(new Utilisateur(enchere.getGagnantId(), null, null, null, null).selectById(jdbcTemplate));

        }
        result[0] = enchere;

        return result;
    }

    @GetMapping("/encheresByUtilisateur/{utilisateurId}")
    public List<Enchere> getEncheresByUtilisateur(@PathVariable String utilisateurId) {
        String query = String.format("select * from v_Enchere where proprietaireId = '%s' ", utilisateurId);

        return (new Enchere(null, utilisateurId, null, null, null, null, null, null, null, null, null, null).select(query, jdbcTemplate));
    }

    @PostMapping("/addEnchere")
    public void addEnchere(@RequestBody Enchere enchere) {
        enchere.insert(jdbcTemplate);
    }

    @GetMapping("/updateEnchere")
    public void updateEnchere(
            @RequestParam String id,
            @RequestParam Double duree,
            @RequestParam Double prixmin,
            @RequestParam String descri,
            @RequestParam String nomProduit,
            @RequestParam String categorieId) {
        new Enchere(id, null, null, duree, null, null, null, nomProduit, descri, prixmin, categorieId).update(jdbcTemplate);
    }

    @GetMapping("/deleteEnchere/{id}")
    public void deleteEnchere(@PathVariable String id) {
        new Enchere(id).delete(jdbcTemplate);
    }

    @GetMapping("/checkEncheresFinis")
    public List<Enchere> getEncheresFinis() {
        List<Enchere> result = new ArrayList<>();

        String query = String.format("select * from v_Enchere where (status = 0) and (now() < datefin)");

        result = new Enchere().select(query, jdbcTemplate);

        for (Enchere e : result) {
            List<Historique> historique = new Historique(e.getId()).getLastByEnchere(jdbcTemplate);
            if (!historique.isEmpty()) {
                e.updateFinish(historique.get(0).getUtilisateurId(), historique.get(0).getPrix(), jdbcTemplate);
//                resaka vola
            } else {
                e.updateFinish(jdbcTemplate);
            }
        }

        return result;
    }

    @GetMapping("/encheres2")
    public List<Enchere> getEncheres2(
            @RequestParam String categorieId,
            @RequestParam String nomProduit,
            @RequestParam String dateDebut,
            @RequestParam String proprietaireId,
            @RequestParam String status
    ) {
        String query = "select * from v_Enchere where 1 = 1";

        if (!categorieId.equals("") && !categorieId.equals("undefined")) {
            query += String.format(" and categorieId = '%s'", categorieId);
        }

        if (!nomProduit.equals("") && !nomProduit.equals("undefined")) {
            query += " and nomProduit like '%" + nomProduit + "%'";
        }

        if (!dateDebut.equals("") && !dateDebut.equals("undefined")) {
            query += String.format(" and dateDebut >= '%s' ", dateDebut);
        }

        if (!proprietaireId.equals("") && !proprietaireId.equals("undefined")) {
            query += String.format(" and proprietaireId = '%s' ", proprietaireId);
        }

        if (!status.equals("") && !status.equals("undefined")) {
            query += String.format(" and status = %s", status);
        }

        System.out.println(query);

        return (new Enchere().select(query, jdbcTemplate));
    }

    @GetMapping("/prixMin/{enchereId}")
    public double getPriMin(@PathVariable String enchereId) {
        List<Historique> lastHistorique = new Historique(enchereId).getLastByEnchere(jdbcTemplate);
        if (!lastHistorique.isEmpty()) {
            return lastHistorique.get(0).getPrix() + 1;
        } else {
            String query = String.format("select * from v_Enchere where id = '%s' ", enchereId);
            Enchere enchere = new Enchere(enchereId).select(query, jdbcTemplate).get(0);
            return enchere.getPrixMin() + 1;
        }
    }

}
