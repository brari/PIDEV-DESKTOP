/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import pidev.entities.CommentaireEvent;
import pidev.entities.Etape;
import pidev.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IMEN
 */
public class EtapeDAO {
    
    
    Connection connexion;
    Statement stm;
    
      PreparedStatement pst;
    
    public EtapeDAO() {
        //connection = (DataSource.getInstance()).getConnection();
        connexion=MyDB.getInstance().getConnection();
    }
    
      public  void ajouterEtape(Etape e) throws SQLException{
//        String req="INSERT INTO etape (id,description_etat,nom_etat,id_utilisateur) "
//                + "VALUES(?,?,?)";
//         pst =connexion.prepareStatement(req);
//        pst.setInt(1, e.getId());
//        pst.setString(2, e.getDescription_eta());
//        pst.setString(3, e.getNom_eta());
//        pst.setInt(4,e.getId_utilisateur());
//       
//       pst.executeUpdate();

String req="INSERT INTO `patisserie`.`etape` (`id`,`nom_eta`,`description_eta`,`id_utilisateur`) VALUES ( '"+e.getId()+"', '"+e.getNom_eta()+"' ,'"+e.getDescription_eta()+"','"+e.getId_utilisateur()+"')";
    
    Statement stm=connexion.createStatement();
    stm.executeUpdate(req);
    
    
}
      
     public List <Etape> getAllEtape( int a) throws SQLException{
     List<Etape> c= new ArrayList<>();
     String req="Select * from etape where id=?";
      pst =connexion.prepareStatement(req);
     pst.setInt(1,a);

     ResultSet rst=pst.executeQuery();
       while (rst.next()){
           Etape ce=new Etape(rst.getInt("id"),rst.getString("nom_eta"),rst.getString("description_eta"), rst.getInt("id_eta"));
          c.add(ce);
       }
        return c;
    
} 
      
      
     public void supprimerEtape(Etape c) throws SQLException{
              
        
            String requete="DELETE FROM etape where id_eta=?";
             pst=connexion.prepareStatement(requete);
            pst.setInt(1,c.getId_eta());
            pst.executeUpdate();
                 System.out.println("etape supprimé");

    }   
      
      
     public List<Etape> getAllEta() throws SQLException {
            List<Etape> eta=new ArrayList<>();
            String req= "select * from etape";
            Statement stm=connexion.createStatement();
            ResultSet rst=stm.executeQuery(req);
            
            while (rst.next()) {
    
           Etape p=new Etape(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(1));
            eta.add(p);
            
            }
           return eta; 
            
            
    }
       
      public void deleteEta(int id )throws SQLException {
         
           
            
              String requete="delete from etape where id_eta="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
    
    
    }
     
     
      
          public void modifierEtape(String d,String a) {
 
String req="UPDATE etat SET description_eta='"+d+"' WHERE  nom_eta='"+a+"'  ";
         try {
            pst = connexion.prepareStatement(req);
           
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    }
     
          
          
      public void modifierEta(Etape p) throws SQLException{ 
        String requete="update etape set nom_eta='"+p.getNom_eta()+"',description_eta='"+p.getDescription_eta()+
                "' where id_eta="+p.getId();
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
        
      
        
    }      
          
     
}