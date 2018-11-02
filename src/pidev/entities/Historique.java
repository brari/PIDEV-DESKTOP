/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.io.InputStream;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author IMEN
 */
public class Historique {
    private int id;
    private String nom;
    private String description;
    private InputStream photo;
    private String url;
    private  String     etat;
    private String action;
    private Date      date;	
    
    
    public Historique() {
    }

     public Historique(int id, String nom, String description, InputStream photo, String url, String etat, String action, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.url = url;
        this.etat = etat;
        this.action = action;
        this.date = date;
    }

    
    
    
    public Historique(String nom, String description, InputStream photo, String url, String etat, String action, Date date) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.url = url;
        this.etat = etat;
        this.action = action;
        this.date = date;
    }

    public Historique(int id, String nom, String description, InputStream photo, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.date = date;
    }

    public Historique(String nom, String description, String url, Date date) {
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    public Historique(String nom, String description, String url) {
        this.nom = nom;
        this.description = description;
        this.url = url;
    }

    public Historique(int id, String nom, String description, InputStream photo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public Historique(String nom, String description, InputStream photo) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public Historique(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Historique(int id, String nom, String description, Date date,String etat, String action ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.action = action;
        this.date = date;
    }

    public Historique(int id, String nom, String description, InputStream photo, String url) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.url = url;
    }

    

    public Historique(int id, String nom, String description, String url) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.url = url;
    }
    
  
  
    
    

   

    public Historique(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public String getUrl() {
        return url;
    }

    public String getEtat() {
        return etat;
    }

    public String getAction() {
        return action;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Historique other = (Historique) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    
    
    
    
}
