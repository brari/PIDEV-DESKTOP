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
public class Ingredient {
    
   private int id_ing;
   private String nom_ing;
   private String quantite;
   private int id;
   private int id_utilisateur;

   
   
    public Ingredient(){}
   
    public Ingredient(int id_ing, String nom_ing, String quantite, int id) {
        this.id_ing = id_ing;
        this.nom_ing = nom_ing;
        this.quantite = quantite;
        this.id = id;
    }

    public Ingredient(String nom_ing, String quantite, int id, int id_utilisateur) {
        this.nom_ing = nom_ing;
        this.quantite = quantite;
        this.id = id;
        this.id_utilisateur = id_utilisateur;
    }

    public Ingredient(int id_ing, String nom_ing, String quantite, int id, int id_utilisateur) {
        this.id_ing = id_ing;
        this.nom_ing = nom_ing;
        this.quantite = quantite;
        this.id = id;
        this.id_utilisateur = id_utilisateur;
    }

    public Ingredient(String nom_ing, String quantite) {
        this.nom_ing = nom_ing;
        this.quantite = quantite;
    }

    public Ingredient( int id,String nom_ing, String quantite) {
        this.nom_ing = nom_ing;
        this.quantite = quantite;
        this.id = id;
    }

    public int getId_ing() {
        return id_ing;
    }

    public String getNom_ing() {
        return nom_ing;
    }

    public String getQuantite() {
        return quantite;
    }

    public int getId() {
        return id;
    }

    public void setId_ing(int id_ing) {
        this.id_ing = id_ing;
    }

    public void setNom_ing(String nom_ing) {
        this.nom_ing = nom_ing;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
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
        final Ingredient other = (Ingredient) obj;
        if (this.id_ing != other.id_ing) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (!Objects.equals(this.nom_ing, other.nom_ing)) {
            return false;
        }
        if (!Objects.equals(this.quantite, other.quantite)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
