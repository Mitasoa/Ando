package mg.groupe26.enchere2.model;

import java.util.List;
import mg.groupe26.enchere2.model.view.v_Solde;
import org.springframework.jdbc.core.JdbcTemplate;

public class Utilisateur extends Personne {

    String id;
    String nom;
    String pseudo;

    public Utilisateur() {
    }

    public Utilisateur(String id, String nom, String pseudo, String email, String mdp) {
        super(email, mdp);
        this.id = id;
        this.nom = nom;
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Utilisateur> select(String query, JdbcTemplate jt) {
        return jt.query(query, (rs, row) -> new Utilisateur(
                rs.getString("id"),
                rs.getString("nom"),
                rs.getString("pseudo"),
                rs.getString("email"),
                rs.getString("mdp")
        ));
    }
    
    public Utilisateur selectById(JdbcTemplate jt) {
        String query = String.format("select * from Utilisateur where id = '%s' ", getId());
        return (select(query, jt).get(0));
    }
    
    public Utilisateur login(JdbcTemplate jt) {
        Utilisateur result = null;
        String query = String.format("select * from Utilisateur where email = '%s' and mdp = '%s' ", getEmail(), getMdp());
        List<Utilisateur> listUtilisateur = select(query, jt);
        
        if (!listUtilisateur.isEmpty()) {
            result = listUtilisateur.get(0);
            result.generateToken();
        }
        
        return result;
    }

    public void insert(JdbcTemplate jt) {
        String query = String.format("insert into Utilisateur values (concat('Utilisateur', nextval('seq_utilisateur')), '%s', '%s', '%s', '%s')", getNom(), getPseudo(), getEmail(), getMdp());
        jt.update(query);
    }

    public void update(JdbcTemplate j) {
        String query = String.format("update utilisateur set nom = '%s', pseudo = '%s', email = '%s', mdp = '%s' where id = '%s'", getNom(), getPseudo(), getEmail(), getMdp(), getId());
        j.update(query);
    }
    
    public double getSolde(JdbcTemplate jt) {
        List<v_Solde> solde = new v_Solde(getId(), null).selectById(jt);
        if (solde.isEmpty()) {
            return 0;
        } else {
            return solde.get(0).getSolde();
        }
    }
    
}
