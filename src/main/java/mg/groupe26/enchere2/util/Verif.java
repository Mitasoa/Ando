package mg.groupe26.enchere.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class Verif {

    Integer response;

    public Verif() {
    }

    public Verif(Integer response) {
        this.response = response;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public Verif select(String query, JdbcTemplate jt) {
        return jt.query(query, (rs, row) -> new Verif(
                rs.getInt("response")
        )).get(0);
    }

    public int tokenValide(String contenu, JdbcTemplate jt) {
        String query = String.format("select 1 as response from Token where contenu = '%s' and dateExpiration > now()", contenu);
        Verif verif = select(query, jt);

        if (verif.getResponse() == null) {
            return 0;
        } else {
            return 1;
        }
    }

}
