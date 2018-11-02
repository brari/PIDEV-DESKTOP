/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.sql.Date;

/**
 *
 * @author brari
 */
public class Reclamation {
     private int id;
    private String type;
    private String objet;
        private String contenu;
     private Date date;
     private String reponse;
     private String statut="en cours";
     private String reclamant;
     private String decision;
     private int id_reclamant;
       private int id_concerne;

    public int getId_concerne() {
        return id_concerne;
    }

    public int getId_reclamant() {
        return id_reclamant;
    }

    public String getDecision() {
        return decision;
    }

    public String getReclamant() {
        return reclamant;
    }
       public Reclamation(int id_reclamant, int id ,String type, String contenu, Date date,String reponse, String statut,String objet,String decision) {
        this.id = id;
        this.type = type;
        this.contenu = contenu;
          this.date=date;
        this.reponse = reponse;
        this.statut = statut;
        this.objet=objet;
        this.decision=decision;
        this.id_reclamant=id_reclamant;
        
    }
         public Reclamation(  String type, String objet,String contenu) {
        this.type = type;
        this.contenu = contenu;
        this.objet=objet;
    }

    public String getObjet() {
        return objet;
    }
       
       public Reclamation(){ }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date date) {
        this.date= date;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return date;
    }

    public String getReponse() {
        return reponse;
    }

    public String getStatut() {
        return statut;
    }
    

    public void setReclamant(String reclamant) {
        this.reclamant = reclamant;
    }
       
    
}
