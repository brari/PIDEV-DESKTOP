/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;
  import com.mysql.jdbc.Blob;
import java.util.Objects;
import java.util.Objects;

/**
 *
 * @author hp
 */
public class Produit {
  

    private int idpro;
    private String nompro;
    private float prixpro;
    private String categoriepro;
    private String detailspro;
    private String nompat;
    private String image;
    private int idp;

    public Produit() {
    }

    public Produit(String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image, int idp) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
        this.idp = idp;
    }

    public Produit(int idpro, String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image, int idp) {
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
        this.idp = idp;
    }

    public Produit(int idpro, String nompro, float prixpro, String categoriepro, String detailspro, String nompat) {
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
    }

    public Produit(String nompro, float prixpro, String categoriepro, String detailspro, String nompat) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
    }

    public Produit(String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image) {
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
    }

    public Produit(int idpro, String nompro, float prixpro, String categoriepro, String detailspro, String nompat, String image) {
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.categoriepro = categoriepro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.image = image;
    }
    

    public Produit(String nompro, String categoriepro) {
        this.nompro = nompro;
        this.categoriepro = categoriepro;
    }

    public Produit(int idpro, String nompro, float prixpro, String detailspro, String nompat, int idp) {
        this.idpro = idpro;
        this.nompro = nompro;
        this.prixpro = prixpro;
        this.detailspro = detailspro;
        this.nompat = nompat;
        this.idp = idp;
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

    public int getIdp() {
        return idp;
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

    public void setIdp(int idp) {
        this.idp = idp;
    }

    @Override
    public String toString() {
        return "Produit{" + "idpro=" + idpro + ", nompro=" + nompro + ", prixpro=" + prixpro + ", categoriepro=" + categoriepro + ", detailspro=" + detailspro + ", nompat=" + nompat + ", image=" + image + '}';
    }
    

    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.categoriepro, other.categoriepro)) {
            return false;
        }
        return true;
    }

 

   
    
    
    
}
