package mg.groupe26.enchere2.model;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class Categorie {

    String id;
    String intitule;

    public Categorie() {
    }

    public Categorie(String id, String intitule) {
        this.id = id;
        this.intitule = intitule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<Categorie> select(String query, JdbcTemplate jt) {
        return jt.query(query, (rs, row) -> new Categorie(
                rs.getString("id"),
                rs.getString("intitule")
        ));
    }
    
    public Categorie selectById(JdbcTemplate jt) {
        String query = String.format("select * from Categorie where id = '%s' ", getId());
        return (select(query, jt).get(0));
    }

    public void insert(JdbcTemplate jt) {
        String query = String.format("insert into admin values (concat('Categorie', nextval('seq_categorie')), '%s')", getIntitule());
        jt.update(query);
    }

    public void update(JdbcTemplate j) {
        String query = String.format("update categorie set intitule = %s where id = %s", getIntitule(), getId());
        j.update(query);
    }
}
