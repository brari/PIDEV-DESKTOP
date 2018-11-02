/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.awt.Image;

/**
 *
 * @author hp
 */
public class Patisserie {
   private int idp;
   private String nom;
   private String adresse;
   private int contact;
   private String mail;
   private String url;
   private Double rating;
   private int idprop;

    public Patisserie() {
        rating=0.0;
        idprop=0;
    }

    public Patisserie(int idp, String nom, String adresse, int contact, String mail, String url,int idprop) {
        super();
        this.idp = idp;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.mail = mail;
        this.url = url;
        this.idprop = idprop;
    }

    public Patisserie(int idp, String nom, String adresse, int contact, String mail)
    {
        super();
        this.idp = idp;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.mail = mail;
    }

    public Patisserie(String nom, String adresse, int contact, String mail, String url) {
        super();
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.mail = mail;
        this.url = url;
    }
    
   
    public Patisserie(String nom, String adresse, int contact, String mail,int idprop) {
        super();
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
        this.mail = mail;
        this.idprop=idprop;
    }
   
    public int getIdp() {
        return idp;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getContact() {
        return contact;
    }

    public String getUrl() {
        return url;
    }

    public Double getRating() {
        return rating;
    }

    public int getIdprop() {
        return idprop;
    }

    public void setIdprop(int idprop) {
        this.idprop = idprop;
    }

    
    

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
     
    
    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Patisserie{" + "idp=" + idp + ", nom=" + nom + ", adresse=" + adresse + ", contact=" + contact + ", mail=" + mail+", rating=" + rating + '}';
    }

    
   

    

    

   

    
   
   
    
   
}
