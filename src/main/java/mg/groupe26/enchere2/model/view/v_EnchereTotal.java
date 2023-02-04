package mg.groupe26.enchere2.model.view;

import org.springframework.jdbc.core.JdbcTemplate;

public class v_EnchereTotal {

    Integer total;

    public v_EnchereTotal() {
    }

    public v_EnchereTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public v_EnchereTotal selectAll(JdbcTemplate jt) {
        return (jt.query("select * from v_EnchereTotal", (rs, row) -> new v_EnchereTotal(
                rs.getInt("total")
        )).get(0));
    }

}
