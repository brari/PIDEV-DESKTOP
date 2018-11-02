/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.Image;

/**
 *
 * @author IMEN
 */


 

public class Recette {
    private int id;
    private String nom;
    private String description;
    private InputStream photo;
    private String url;
    
    
    

    
  public Recette() {
    }  

    public Recette(int id, String nom, String description, InputStream photo, String url) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.url = url;
    }

    public Recette(String nom, String description, String url) {
        this.nom = nom;
        this.description = description;
        this.url = url;
    }

    public Recette(int id, String nom, String description, String url) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.url = url;
    }
    
  
  
    
    public Recette(int idrecette, String nom, String description, InputStream photo) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public Recette(String nom, String description, InputStream photo) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
    }

    public Recette(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Recette(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        final Recette other = (Recette) obj;
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
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return true;
    }

  

    
    
    
    
    
    
    
    
    
    @Override
    public String toString() {
        return "Recette{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", photo=" + photo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
