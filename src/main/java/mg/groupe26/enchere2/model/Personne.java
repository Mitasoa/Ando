package mg.groupe26.enchere2.model;

import java.time.LocalDateTime;
import mg.groupe26.enchere.util.Utilities;

public class Personne {

    String email;
    String mdp;

    String token;

    public Personne() {
    }

    public Personne(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void generateToken() {
        LocalDateTime dateTime = LocalDateTime.now();
        setToken(Utilities.generateHash(dateTime + getEmail()));
    }
}
