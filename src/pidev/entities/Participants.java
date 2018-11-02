/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author DELL
 */
public class Participants {
     private int id_participation;
    private String nom_E;
    private int idC;
   // private int nb_p;

    public Participants() {
    }

    public Participants(int id_participation, int idE, int idC) {
        this.id_participation = id_participation;
        this.nom_E = nom_E;
        this.idC = idC;
    }
    
    

   /* public Participants(int id_participation, int idE, int idC, int nb_p) {
        this.id_participation = id_participation;
        this.idE = idE;
        this.idC = idC;
        this.nb_p = nb_p;
    }*/

    public Participants(int id_participation, String nom_E) {
        this.id_participation = id_participation;
        this.nom_E = nom_E;
    }
    
    

    public int getId_participation() {
        return id_participation;
    }

    public String getNom_E() {
        return nom_E;
    }

   

    public int getIdC() {
        return idC;
    }

   /* public int getNb_p() {
        return nb_p;
    }*/
    
    

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public void setNom_E(String nom_E) {
        this.nom_E = nom_E;
    }

   

    public void setIdC(int idC) {
        this.idC = idC;
    }

 /*   public void setNb_p(int nb_p) {
        this.nb_p = nb_p;
    }*/

    @Override
    public String toString() {
        return "Participants{" + "id_participation=" + id_participation + ", nom_E=" + nom_E + ", idC=" + idC + '}';
    }

   
    
    
}
