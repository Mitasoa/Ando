package mg.groupe26.enchere.util;

import java.util.List;
import mg.groupe26.enchere2.model.view.v_EnchereCategorie;
import mg.groupe26.enchere2.model.view.v_EnchereTotal;
import org.springframework.jdbc.core.JdbcTemplate;

public class Statistiques {

    public List<v_EnchereCategorie> getStat(JdbcTemplate jt) {
        List<v_EnchereCategorie> listEnchere = new v_EnchereCategorie().selectAll(jt);
        
        v_EnchereTotal enchereTotal = new v_EnchereTotal().selectAll(jt);
        
        for (v_EnchereCategorie ec : listEnchere) {
            ec.setPourcentage( new Integer(ec.getNb()).doubleValue() / enchereTotal.getTotal());
        }
        
        return listEnchere;
    }
    
}
