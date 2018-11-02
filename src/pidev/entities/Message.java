/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.util.logging.Logger;

/**
 *
 * @author brari
 */
public class Message {
    private int id;
    private String nom;
    private String prenom;
        private String contenu ;
        private int id_rec;

    public Message(int id, String nom, String prenom, String contenu) {
       this.id=id;
    this.prenom=prenom;
    this.nom=nom;
    this.contenu=contenu;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }


    public int getId_rec() {
        return id_rec;
    }

public Message(){}
    public Message( String nom, String prenom, String contenu) {
       // this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
}
    
   
    

    
            
    

