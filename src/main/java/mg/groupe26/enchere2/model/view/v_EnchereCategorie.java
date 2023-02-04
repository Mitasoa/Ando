package mg.groupe26.enchere2.model.view;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class v_EnchereCategorie {

    String categorieId;
    Integer nb;
    String intitule;

    Double pourcentage;

    public v_EnchereCategorie() {
    }

    public v_EnchereCategorie(String categorieId, Integer nb, String intitule) {
        this.categorieId = categorieId;
        this.nb = nb;
        this.intitule = intitule;
    }

    public String getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = categorieId;
    }

    public Integer getNb() {
        return nb;
    }

    public void setNb(Integer nb) {
        this.nb = nb;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<v_EnchereCategorie> selectAll(JdbcTemplate jt) {
        return (jt.query("select * from v_EnchereCategorie", (rs, row) -> new v_EnchereCategorie(
                rs.getString("categorieId"),
                rs.getInt("nb"),
                rs.getString("intitule")
        )));
    }

}
