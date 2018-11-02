/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Objects;

/**
 *
 * @author IMEN
 */
public class Etape {
    
    private int id_eta;
   private String nom_eta;
   private String description_eta;
   private int id;
private int id_utilisateur;
   
    public Etape(){}
   
    public Etape(int id_eta, String nom_eta, String description_eta, int id) {
        this.id_eta = id_eta;
        this.nom_eta = nom_eta;
        this.description_eta = description_eta;
        this.id = id;
    }

    public Etape(String nom_eta, String description_eta, int id) {
        this.nom_eta = nom_eta;
        this.description_eta = description_eta;
        this.id = id;
    }

    public Etape(String nom_eta, String description_eta) {
        this.nom_eta = nom_eta;
        this.description_eta = description_eta;
    }

    public Etape(int id_eta, String nom_eta, String description_eta, int id, int id_utilisateur) {
        this.id_eta = id_eta;
        this.nom_eta = nom_eta;
        this.description_eta = description_eta;
        this.id = id;
        this.id_utilisateur = id_utilisateur;
    }

    public Etape(String nom_eta, String description_eta, int id, int id_utilisateur) {
        this.nom_eta = nom_eta;
        this.description_eta = description_eta;
        this.id = id;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_eta() {
        return id_eta;
    }

    public String getNom_eta() {
        return nom_eta;
    }

    public String getDescription_eta() {
        return description_eta;
    }

    public int getId() {
        return id;
    }

    public void setId_eta(int id_eta) {
        this.id_eta = id_eta;
    }

    public void setNom_eta(String nom_eta) {
        this.nom_eta = nom_eta;
    }

    public void setDescription_eta(String description_eta) {
        this.description_eta = description_eta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Etape other = (Etape) obj;
        if (this.id_eta != other.id_eta) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (!Objects.equals(this.nom_eta, other.nom_eta)) {
            return false;
        }
        if (!Objects.equals(this.description_eta, other.description_eta)) {
            return false;
        }
        return true;
    }

   

   
    
    
    
    
    
    
    
}
