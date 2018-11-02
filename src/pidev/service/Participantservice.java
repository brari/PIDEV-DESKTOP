/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entities.Participants;
import pidev.util.MyDB;

/**
 *
 * @author DELL
 */
public class Participantservice {
     Connection connexion;
     private ResultSet rs;

    public Participantservice() {
        connexion = MyDB.getInstance().getConnection();
    }

    
     public void ajouter_part(String nom_E) throws SQLException {
    
        try {
         
            String requete = "INSERT INTO participants(nom_E) VALUES (?)";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setString(1,nom_E);
           // pst.setInt(2,idC);
            //pst.setInt(3, p.getNb_p());
            pst.executeUpdate();
          } 
          catch (SQLException ex)
          {
            System.out.println("error lors de la ajout " + ex.getMessage());
          }

    }
     
     
      public List <Participants> getAllReservations(int idE) throws SQLException
    {   
        List <Participants> r= new ArrayList<>();
       String req="SELECT * FROM User e  inner join Participants i on (i.idC=e.idU) WHERE i.idP=?";
        
        PreparedStatement ps=connexion.prepareStatement(req);
        ps.setInt(1,idE);
        ResultSet rst=ps.executeQuery(req);
        while (rst.next()){
            Participants p;
            p = new Participants();
            r.add(p);
        }
        return r; }
      
      
      
      
    
    public boolean Verifier_part(String nom_E) throws SQLException 
    {
      
        String req="SELECT * FROM participants where nom_E=?";
        PreparedStatement ps=connexion.prepareStatement(req);
        try {
           // ps.setInt(1, idC);
         ps.setString(1,nom_E);
        } catch (SQLException ex) {
            Logger.getLogger(Participantservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs=ps.executeQuery();
            if (!rs.next())
            {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Participantservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    
    
     
    public void SupprimerParticipation(String nom_E) throws SQLException 
    {
          String req="DELETE FROM participants where nom_E=?";
          PreparedStatement ps=connexion.prepareStatement(req);
        try 
        {
           // ps.setInt(1, id_utilisateur);
            ps.setString(1,nom_E);
        } catch (SQLException ex) {
            Logger.getLogger(Participantservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Participantservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
      public List<Participants> afficher_Participation() throws SQLException{
        
        List<Participants> participants=new ArrayList<>();
        String requete="select * from participants ";
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        
        while(rst.next()){
            Participants p=new Participants(rst.getInt("id_participation"),rst.getString("nom_E"));
                    
            participants.add(p);
        }
        return participants;
    }
     
    /* public List<Participants> afficher_part() throws SQLException {
         
        List<Participants> participants = new ArrayList<>();
        String req = "select * from participants";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Participants p = new Participants(rst.getInt("id_participation"),rst.getInt("idE"), rst.getInt("idC"));
            participants.add(p);
        }
        return participants;
    }*/
     
     
     
    
    
}
