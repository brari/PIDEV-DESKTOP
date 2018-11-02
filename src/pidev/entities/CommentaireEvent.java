/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.sql.Date;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Asus
 */
public class CommentaireEvent {
    private int id;
    private String comment;
    private int id_utilisateur;
    private Date date;
    private int id_comment;

    public CommentaireEvent(int id, String comment, int id_utilisateur, int id_comment) {
        this.id = id;
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
        this.id_comment = id_comment;
    }

    public CommentaireEvent(int id, String comment, int id_utilisateur) {
        this.id = id;
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
    }

    

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public CommentaireEvent(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public CommentaireEvent(int id, String comment, Date date, int id_comment) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.id_comment = id_comment;
    }

    public CommentaireEvent(int id, String comment, int id_utilisateur, Date date, int id_comment) {
        this.id = id;
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
        this.date = date;
        this.id_comment = id_comment;
    }
    

    public CommentaireEvent(int id, String comment, int id_utilisateur, Date date) {
        this.id = id;
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
        this.date = date;
    }

    public CommentaireEvent(String comment, int id_utilisateur) {
        this.comment = comment;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    


  

    

    @Override
    public String toString() {
        return "CommentaireEvent{" + "id=" + id + ", comment=" + comment + ", id_utilisateur=" + id_utilisateur + ", date=" + date + ", id_comment=" + id_comment + '}';
    }

   
    

    

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
}
