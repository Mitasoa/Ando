package mg.groupe26.enchere2.model;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class Historique {

    String id;
    String dateEnchere;
    Double prix;
    String utilisateurId;
    String enchereId;

    Utilisateur u;

    public Historique() {
    }

    public Historique(String id, String dateEnchere, Double prix, String utilisateurId, String enchereId) {
        this.id = id;
        this.dateEnchere = dateEnchere;
        this.prix = prix;
        this.utilisateurId = utilisateurId;
        this.enchereId = enchereId;
    }

    public Historique(String enchereId) {
        this.enchereId = enchereId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(String dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getEnchereId() {
        return enchereId;
    }

    public void setEnchereId(String enchereId) {
        this.enchereId = enchereId;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public List<Historique> select(String query, JdbcTemplate jt) {
        return jt.query(query, (rs, row) -> new Historique(
                rs.getString("id"),
                rs.getString("dateEnchere"),
                rs.getDouble("prix"),
                rs.getString("utilisateurId"),
                rs.getString("enchereId")
        ));
    }

    public List<Historique> getLastByEnchere(JdbcTemplate jt) {
        String query = String.format("select * from Historique where enchereId = '%s' order by dateEnchere desc limit 1", getEnchereId());
        return (select(query, jt));
    }

    public void insert(JdbcTemplate jt) {
        String query = String.format("insert into Historique values (concat('Historique', nextval('seq_historique')), default, %s, '%s', '%s')", getPrix(), getUtilisateurId(), getEnchereId());
        jt.update(query);
    }

}
