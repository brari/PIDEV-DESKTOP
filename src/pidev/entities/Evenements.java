/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.Date;

/**
 *
 * @author hp
 */
public class Evenements {
    private int idE;
    private String nom_E;
    private String description_E;
    private String adresse_E;
    private String type_E;
    private Date date_E;
    //private int nombre_interesses;
    private String image_E;
    private int interesses;
    private int capacite;

    public Evenements() {
    }

    public Evenements(String text, String text0, String text1, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getInteresses() {
        return interesses;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setInteresses(int interesses) {
        this.interesses = interesses;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    
    public int getIdE() {
        return idE;
    }

    public String getNom_E() {
        return nom_E;
    }

    public String getDescription_E() {
        return description_E;
    }

    public String getAdresse_E() {
        return adresse_E;
    }

    public Date getDate_E() {
        return date_E;
    }

    public String getType_E() {
        return type_E;
    }

  public String getImage_E() {
        return image_E;
    }

    /*public int getNombre_interesses() {
        return nombre_interesses;
    }*/

    /*public String getImage_E() {
        return image_E;
    }*/

    

    
    
    

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public void setNom_E(String nom_E) {
        this.nom_E = nom_E;
    }

    public void setDescription_E(String description_E) {
        this.description_E = description_E;
    }

    public void setAdresse_E(String adresse_E) {
        this.adresse_E = adresse_E;
    }

    public void setDate_E(Date date_E) {
        this.date_E = date_E;
    }

    public void setType_E(String type_E) {
        this.type_E = type_E;
    }

    public void setImage_E(String image_E) {
        this.image_E = image_E;
    }

   /* public void setNombre_interesses(int nombre_interesses) {
        this.nombre_interesses = nombre_interesses;
    }*/
    
    

   

   

    
    public Evenements(int idE, String nom_E, String description_E, String adresse_E, String type_E, Date date_E) {
        this.idE = idE;
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
    }

    public Evenements(String nom_E, String description_E, String adresse_E, String type_E, Date date_E) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
    }

   /* public Evenements(int idE, String nom_E, String description_E, String adresse_E, String type_E, Date date_E, String image_E) {
        this.idE = idE;
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
        this.image_E = image_E;
    }*/

 /*   public Evenements(String nom_E, String description_E, String adresse_E, String type_E, Date date_E, String image_E) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
        this.image_E = image_E;
    }*/

   /* public Evenements(String nom_E, String description_E, String adresse_E, String type_E, Date date_E, int nombre_interesses, String image_E) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
        this.nombre_interesses = nombre_interesses;
        this.image_E = image_E;
    }*/

   public Evenements(String nom_E, String description_E, String adresse_E, String type_E, Date date_E, String image_E) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
        this.image_E = image_E;
    }

  /*  public Evenements(String nom_E, String description_E, String adresse_E, String type_E, Date date_E, int nombre_interesses) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
        this.nombre_interesses = nombre_interesses;
    }*/

    public Evenements(String nom_E, String description_E, String adresse_E, String type_E, Date date_E, String image_E, int interesses, int capacite) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.type_E = type_E;
        this.date_E = date_E;
        this.image_E = image_E;
        this.interesses = interesses;
        this.capacite = capacite;
    }
    
    

    

    
    
    
    
    
    

   

    public Evenements(String nom_E, String description_E, String adresse_E, Date date_E) {
        this.nom_E = nom_E;
        this.description_E = description_E;
        this.adresse_E = adresse_E;
        this.date_E = date_E;
    }

    @Override
    public String toString() {
        return "Evenements{" + "idE=" + idE + ", nom_E=" + nom_E + ", description_E=" + description_E + ", adresse_E=" + adresse_E + ", type_E=" + type_E + ", date_E=" + date_E + ", image_E=" + image_E + ", interesses=" + interesses + ", capacite=" + capacite + '}';
    }

    

    

    

   
   

    

    
    
    
    
    
    

    
    
    

    

   
    
    
    
    
}
