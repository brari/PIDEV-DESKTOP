/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import pidev.entities.Etape;
import pidev.entities.Ingredient;
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
public class IngredientDAO {
    
    Connection connexion;
    Statement stm;
    PreparedStatement pst;
    
    
    public IngredientDAO() {
        //connection = (DataSource.getInstance()).getConnection();
        connexion=MyDB.getInstance().getConnection();
    } 
    
    public  void ajouterIngredient(Ingredient e) throws SQLException{

    String req="INSERT INTO `patisserie`.`ingredient` (`id`,`nom_ing`,`quantite`,`id_utilisateur`) VALUES ( '"+e.getId()+"', '"+e.getNom_ing()+"' ,'"+e.getQuantite()+"','"+e.getId_utilisateur()+"')";
    
    Statement stm=connexion.createStatement();
    stm.executeUpdate(req);
    
}
      
  

 public List <Ingredient> getAllIngredient( int a) throws SQLException{
     List<Ingredient> c= new ArrayList<>();
     String req="Select * from ingredient where id=?";
      pst =connexion.prepareStatement(req);
     pst.setInt(1,a);

     ResultSet rst=pst.executeQuery();
       while (rst.next()){
           Ingredient ce=new Ingredient(rst.getInt("id"),rst.getString("nom_ing"),rst.getString("quantite"), rst.getInt("id_ing"));
          c.add(ce);
       }
        return c;
    
} 
      
   
 
 
  public void supprimerIngredient(Ingredient c) throws SQLException{
              
        
            String requete="DELETE FROM ingredient where id_ing=?";
             pst=connexion.prepareStatement(requete);
            pst.setInt(1,c.getId_ing());
            pst.executeUpdate();
                 System.out.println("ingrédient  supprimé");

    }  
    
  
  /////fonctionnel
      public void deleteingg(int id )throws SQLException {
         
           
            
              String requete="delete from ingredient where id_ing="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
    
    
    }
  
  //utilisé en suppression
      
     public List<Ingredient> getAllinggggg() throws SQLException {
            List<Ingredient> ingg=new ArrayList<>();
            String req= "select * from ingredient";
            Statement stm=connexion.createStatement();
            ResultSet rst=stm.executeQuery(req);
            
            while (rst.next()) {
    
           Ingredient p=new Ingredient(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(1));
            ingg.add(p);
            
            }
           return ingg; 
            
            
    }
      
  
     public void modifierIngredient(String d,String a) {
 
String req="UPDATE ingredient SET quantite='"+d+"' WHERE  nom_ing='"+a+"'  ";
         try {
            pst = connexion.prepareStatement(req);
           
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    }
      
     
     
     
     public void modifierIng(Ingredient p) throws SQLException{ 
        String requete="update ingredient set nom_ing='"+p.getNom_ing()+"',quantite='"+p.getQuantite()+
                "' where id_ing="+p.getId();
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
        
      
        
    }
   
   
     
  
}
