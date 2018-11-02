/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author hp
 */
public class Rating {
    private int id_user;
    private int id_pat;
    private Double note;

    public Rating() {
    }

    public Rating(int id_user, int id_pat, Double note) {
        this.id_user = id_user;
        this.id_pat = id_pat;
        this.note = note;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_pat() {
        return id_pat;
    }

    public Double getNote() {
        return note;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_pat(int id_pat) {
        this.id_pat = id_pat;
    }

    public void setNote(Double note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Rating{" + "id_user=" + id_user + ", id_pat=" + id_pat + ", note=" + note + '}';
    }

   
    
    
    
    
}
