package mg.groupe26.enchere2.model.view;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class v_Solde {

    String utilisateurId;
    Double solde;

    public v_Solde() {
    }

    public v_Solde(String utilisateurId, Double solde) {
        this.utilisateurId = utilisateurId;
        this.solde = solde;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public List<v_Solde> selectById(JdbcTemplate jt) {
        String query = String.format("select * from v_Solde where utilisateurId = '%s' ", getUtilisateurId());

        return jt.query(query, (rs, row) -> new v_Solde(
                rs.getString("utilisateurId"),
                rs.getDouble("solde")
        ));
    }

}
