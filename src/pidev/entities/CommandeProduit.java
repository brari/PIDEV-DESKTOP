/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author Insaf-Nefzi
 */
public class CommandeProduit {
    private int idcp;
    private int idu;
    private int idpro;
    private String nompro;
    private float prixpro;
    private String categoriepro;
    private String detailspro;
    private String nompat;
    private String image;
    private String date;

    public CommandeProduit(int idcp, int idu, int idpro, String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image, String date) {
        this.idcp = idcp;
        this.idu = idu;
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
        this.date = date;
    }

    public CommandeProduit(String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image, String date) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
        this.date = date;
    }

    public CommandeProduit(String nompro, float prixpro, String categoriepro, String nompat, String date) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.nompat = nompat;
        this.date = date;
    }

    public CommandeProduit(int idu, int idpro, String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image, String date) {
        this.idu = idu;
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
        this.date = date;
    }

    public CommandeProduit(int idu, int idpro, String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String date) {
        this.idu = idu;
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.date = date;
    }

    public CommandeProduit() {
    }

    public int getIdcp() {
        return idcp;
    }

    public int getIdu() {
        return idu;
    }

    public int getIdpro() {
        return idpro;
    }

    public String getNompro() {
        return nompro;
    }

    public float getPrixpro() {
        return prixpro;
    }

    public String getCategoriepro() {
        return categoriepro;
    }

    public String getDetailspro() {
        return detailspro;
    }

    public String getNompat() {
        return nompat;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public void setIdcp(int idcp) {
        this.idcp = idcp;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public void setPrixpro(float prixpro) {
        this.prixpro = prixpro;
    }

    public void setCategoriepro(String categoriepro) {
        this.categoriepro = categoriepro;
    }

    public void setDetailspro(String detailspro) {
        this.detailspro = detailspro;
    }

    public void setNompat(String nompat) {
        this.nompat = nompat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.idcp;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CommandeProduit other = (CommandeProduit) obj;
        if (this.idcp != other.idcp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommandeProduit{" + "idcp=" + idcp + ", idu=" + idu + 
                ", idpro=" + idpro + ", nompro=" + nompro + ", prixpro=" +
                prixpro + ", categoriepro=" + categoriepro + ", detailspro=" + 
                detailspro + ", nompat=" + nompat + ", image=" + image + ", date=" + date + '}';
    }
    

}
