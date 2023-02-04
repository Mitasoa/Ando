package mg.groupe26.enchere2.model;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class Admin extends Personne {

    String id;

    public Admin() {
    }

    public Admin(String id, String email, String mdp) {
        super(email, mdp);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Admin> select(String query, JdbcTemplate jt) {
        return jt.query(query, (rs, row) -> new Admin(
                rs.getString("id"),
                rs.getString("email"),
                rs.getString("mdp")
        ));
    }
    
    public Admin login(JdbcTemplate jt) {
        Admin result = null;
        String query = String.format("select * from Admin where email = '%s' and mdp = '%s' ", getEmail(), getMdp());
        List<Admin> listAdmin = select(query, jt);
        
        if (!listAdmin.isEmpty()) {
            result = listAdmin.get(0);
            result.generateToken();
        }
        
        return result;
    }

    public void insert(JdbcTemplate jt) {
        String query = String.format("insert into Admin values (concat('Admin',nextval('seq_admin')), '%s', '%s')", getEmail(), getMdp());
        jt.update(query);
    }
}
