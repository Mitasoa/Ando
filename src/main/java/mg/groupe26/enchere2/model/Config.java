package mg.groupe26.enchere2.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class Config {

    Double tauxCommission;

    public Config() {
    }

    public Config(Double tauxCommission) {
        this.tauxCommission = tauxCommission;
    }

    public Double getTauxCommission() {
        return tauxCommission;
    }

    public void setTauxCommission(Double tauxCommission) {
        this.tauxCommission = tauxCommission;
    }
    
    public Config select(JdbcTemplate jt) {
        String query = "select * from config";
        return jt.query(query, (rs, row) -> new Config(
                rs.getDouble("tauxCommission")
        )).get(0);
    }

}
