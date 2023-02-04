package mg.groupe26.enchere2.model;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class Enchere {

    String id;
    String proprietaireId;
    String dateDebut;
    Double duree;
    String gagnantId;
    Double prixVente;
    Integer status;
    String nomProduit;
    String descri;
    Double prixMin;
    String categorieId;
    String dateFin;
    String img;

    Utilisateur proprietaire;
    Utilisateur gagnant;
    Categorie categorie;

    public Enchere() {
    }

    public Enchere(String id, String proprietaireId, String dateDebut, Double duree, String gagnantId, Double prixVente, Integer status, String nomProduit, String descri, Double prixMin, String categorieId) {
        this.id = id;
        this.proprietaireId = proprietaireId;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.gagnantId = gagnantId;
        this.prixVente = prixVente;
        this.status = status;
        this.nomProduit = nomProduit;
        this.descri = descri;
        this.prixMin = prixMin;
        this.categorieId = categorieId;
    }

    public Enchere(String id, String proprietaireId, String dateDebut, Double duree, String gagnantId, Double prixVente, Integer status, String nomProduit, String descri, Double prixMin, String categorieId, String dateFin) {
        this.id = id;
        this.proprietaireId = proprietaireId;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.gagnantId = gagnantId;
        this.prixVente = prixVente;
        this.status = status;
        this.nomProduit = nomProduit;
        this.descri = descri;
        this.prixMin = prixMin;
        this.categorieId = categorieId;
        this.dateFin = dateFin;
    }

    public Enchere(String id, String proprietaireId, String dateDebut, Double duree, String gagnantId, Double prixVente, Integer status, String nomProduit, String descri, Double prixMin, String categorieId, String dateFin, String img) {
        this.id = id;
        this.proprietaireId = proprietaireId;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.gagnantId = gagnantId;
        this.prixVente = prixVente;
        this.status = status;
        this.nomProduit = nomProduit;
        this.descri = descri;
        this.prixMin = prixMin;
        this.categorieId = categorieId;
        this.dateFin = dateFin;
        this.img = img;
    }
    
    

    public Enchere(String id) {
        this.id = id;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProprietaireId() {
        return proprietaireId;
    }

    public void setProprietaireId(String proprietaireId) {
        this.proprietaireId = proprietaireId;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public String getGagnantId() {
        return gagnantId;
    }

    public void setGagnantId(String gagnantId) {
        this.gagnantId = gagnantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(Double prixMin) {
        this.prixMin = prixMin;
    }

    public String getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = categorieId;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Utilisateur getGagnant() {
        return gagnant;
    }

    public void setGagnant(Utilisateur gagnant) {
        this.gagnant = gagnant;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

    public List<Enchere> select(String query, JdbcTemplate jt) {
        return jt.query(query, (rs, row) -> new Enchere(
                rs.getString("id"),
                rs.getString("proprietaireid"),
                rs.getString("datedebut"),
                rs.getDouble("duree"),
                rs.getString("gagnantid"),
                rs.getDouble("prixvente"),
                rs.getInt("status"),
                rs.getString("nomproduit"),
                rs.getString("descri"),
                rs.getDouble("prixmin"),
                rs.getString("categorieid"),
                rs.getString("datefin"),
                rs.getString("img")
        ));
    }

    public List<Enchere> selectAll(JdbcTemplate jt) {
        String query = "select * from v_Enchere";
        return (select(query, jt));
    }

    public void insert(JdbcTemplate jt) {
        String query = String.format("insert into Enchere values (concat('Enchere',nextval('seq_enchere')), '%s', default, %s, default, default, default, '%s', '%s', %s, '%s', '%s')", getProprietaireId(), getDuree(), getNomProduit(), getDescri(), getPrixMin(), getCategorieId(), getImg());
        jt.update(query);
    }

    public void update(JdbcTemplate j) {
        String query = String.format("update Enchere set duree = '%s', prixMin = %s, status = %s, nomProduit = '%s', descriProduit = '%s' where id = '%s' ", getDuree(), getPrixMin(), getStatus(), getNomProduit(), getDescri(), getId());
        j.update(query);
    }

    public void updateFinish(String proprietaireId, double prixVente, JdbcTemplate j) {
        String query = String.format("update Enchere set proprietaireid = '%s', prixVente = %s, status = 1 where id = '%s' ", proprietaireId, prixVente, getId());
        j.update(query);
    }

    public void updateFinish(JdbcTemplate j) {
        String query = String.format("update Enchere set status = 1 where id = '%s' ", getId());
        j.update(query);
    }

    public void delete(JdbcTemplate j) {
        String query = String.format("delete from Enchere where id = '%s'", getId());
        j.update(query);
    }

}
