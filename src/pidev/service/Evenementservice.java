/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entities.Evenements;
import pidev.util.MyDB;

/**
 *
 * @author hp
 */
public class Evenementservice {
    Connection connexion;
      private PreparedStatement ps=null;
    private ResultSet rs=null;

    public Evenementservice() {
        connexion = MyDB.getInstance().getConnection();
    }

    public void ajouter_even(Evenements e) throws SQLException {
        
           String requete = "INSERT INTO evenements(nom_E,description_E,adresse_E,type_E,date_E,image_E) VALUES (?,?,?,?,?,?)";
          PreparedStatement pst=connexion.prepareStatement(requete);
          // pst.setInt(1, e.getIdE());
            pst.setString(1, e.getNom_E());
            pst.setString(2, e.getDescription_E());
            pst.setString(3, e.getAdresse_E());
            pst.setString(4, e.getType_E());
            pst.setDate(5, (Date)e.getDate_E());
            pst.setString(6, e.getImage_E());
            pst.executeUpdate();
            
           
        

    }

    public ObservableList<String> afficher_even() throws SQLException 
    {
            ObservableList<String> evenement=FXCollections.observableArrayList();
            String req="SELECT * FROM evenements";
            ps=connexion.prepareStatement(req);
           // ps.setInt(1, idUtilisateur);
           //ps.setString(1,nom_E);
       
            rs=ps.executeQuery();
            while(rs.next())
            {
                Evenements p=new Evenements(rs.getString("nom_E"),
                        rs.getString("description_E"),
                        rs.getString("adresse_E"),
                        rs.getString("type_E"),
                        rs.getDate("date_E"));
                        //rs.getString("image_E"));
                        
                evenement.add(p.getNom_E());
            }
            
       
    return evenement; 
    }
    
     public List<Evenements> afficher_evene() throws SQLException 
    {
            List<Evenements> evenement=new ArrayList<Evenements>();
            String req="SELECT * FROM evenements";
            ps=connexion.prepareStatement(req);
           // ps.setInt(1, idUtilisateur);
           //ps.setString(1,nom_E);
       
            rs=ps.executeQuery();
            while(rs.next())
            {
                Evenements p=new Evenements(rs.getString("nom_E"),
                        rs.getString("description_E"),
                        rs.getString("adresse_E"),
                        rs.getString("type_E"),
                        rs.getDate("date_E"),
                        rs.getString("image_E"));
                        
                evenement.add(p);
            }
            
       
    return evenement; 
    }

  public ObservableList<Evenements> get_even_nom(String nom_E)throws SQLException 
    {
            ObservableList<Evenements> evenement=FXCollections.observableArrayList();
            String req="SELECT * FROM evenements WHERE nom_E LIKE ?";
         
       
            ps=connexion.prepareStatement(req);
           // ps.setInt(1, idUtilisateur);
          ps.setString(1, "%" + nom_E + "%");
       
            rs=ps.executeQuery();
            while(rs.next())
            {
                Evenements e = new Evenements();
                e.setNom_E(rs.getString("nom_E"));
                e.setDescription_E(rs.getString("description_E"));
                e.setAdresse_E(rs.getString("adresse_E"));
                e.setType_E(rs.getString("type_E"));
               e.setDate_E(rs.getDate("date_E"));
               e.setImage_E(rs.getString("image_E"));
               evenement.add(e);
             
            }
            
       
    return evenement; 
    }

    

    public Evenements rechercher_even(Evenements e) throws SQLException {
        String req = "SELECT * FROM `patisserie`.`evenements` WHERE idE=" + e.getIdE();
        Statement stm = connexion.createStatement();

        Evenements ev = null;
        try {
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                ev = new Evenements();
                ev.setIdE(rst.getInt("idE"));
                ev.setNom_E(rst.getString("nom_E"));
                ev.setDescription_E(rst.getString("description_E"));
                ev.setAdresse_E(rst.getString("adresse_E"));
                ev.setType_E(rst.getString("type_E"));
                ev.setDate_E(rst.getDate("date_E"));
               ev.setImage_E(rst.getString("image_E"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ev;

    }
    
  
    public void modifier_even(String nom_E,String description_E,String adresse_E,String type_E,Date date_E,String image_E) throws SQLException {
        String req = "UPDATE evenements SET description_E='"+description_E+"',adresse_E='"+adresse_E+"',type_E='"+type_E+"',date_E='"+date_E+"' ,image_E='"+image_E+"' WHERE  nom_E='"+nom_E+"'  ";
       
        try {
                 
            PreparedStatement pst = connexion.prepareStatement(req);

            
            pst.executeUpdate();
            System.out.println("Evenement modifier");
        } catch (SQLException ex) {
            System.out.println("error lors de la modif " + ex.getMessage());
        }

    }
        
    
    
    
    
    
    
  public void supprimer_even(String nom_E) throws SQLException {
        try { 
      
          String req3= "delete from evenements where nom_E=? ";
           PreparedStatement p=connexion.prepareStatement(req3);
            p.setString(1,nom_E);
            p.executeUpdate();
             System.out.println("Suppression effectuée avec succès");
          }
            
        catch (SQLException ex) {
            System.out.println("error lors de la supression " + ex.getMessage());
          
        }
   
   
    }  
   
 
    
    
    public  ObservableList<String> rechercher_even_by_type(String type_E) throws SQLException {
        
       ObservableList<String> evenement=FXCollections.observableArrayList();
        String req="SELECT * FROM evenements WHERE type_E LIKE ?";

        PreparedStatement ps = connexion.prepareStatement(req);
       
            ps.setString(1, "%" + type_E + "%");
            ResultSet rs = ps.executeQuery();

            //rs.getInt("idUser")
            while (rs.next()) {
               Evenements p=new Evenements(rs.getString("nom_E"),
                        rs.getString("description_E"),
                        rs.getString("adresse_E"),
                        rs.getString("type_E"),
                        rs.getDate("date_E"),
                       rs.getString("image_E"));
                       
                evenement.add(p.getNom_E());
       
            }
            return evenement;

    }
    
    
  public  ObservableList<String> rechercher_even_by_nom(String nom_E) throws SQLException {
        
       ObservableList<String> evenement=FXCollections.observableArrayList();
        String req="SELECT * FROM evenements WHERE nom_E LIKE ?";

        PreparedStatement ps = connexion.prepareStatement(req);
       
            ps.setString(1, "%" + nom_E + "%");
            ResultSet rs = ps.executeQuery();

            //rs.getInt("idUser")
            while (rs.next()) {
               Evenements p=new Evenements(rs.getString("nom_E"),
                        rs.getString("description_E"),
                        rs.getString("adresse_E"),
                        rs.getString("type_E"),
                        rs.getDate("date_E"),
                       rs.getString("image_E"));
                       
                evenement.add(p.getNom_E());
       
            }
            return evenement;

    }
     
    
    
    
    public List<Evenements> findByNom(String nom) throws SQLException {
       
          String req ="SELECT * FROM evenements WHERE nom_E LIKE ?";
           List<Evenements> liste=new ArrayList<>();
          PreparedStatement ps = connexion.prepareStatement(req);
        try {
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
                Evenements e = new Evenements();
                e.setNom_E(rs.getString("nom_E"));
                e.setDescription_E(rs.getString("description_E"));
                e.setAdresse_E(rs.getString("adresse_E"));
                e.setType_E(rs.getString("type_E"));
                e.setDate_E(rs.getDate("date_E"));
              e.setImage_E(rs.getString("image_E"));
                liste.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       return liste; 
    }
    
    
 
    /*public void IncrementNombreInteresses(String nom_E) 
    {   
        
        String req="SELECT nombre_interesses FROM evenements WHERE nom_E=?";
        String reqq="UPDATE evenements SET nombre_interesses=? WHERE nom_E=?";
        PreparedStatement ps1=null;
        try {
            
          PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1,nom_E);
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
            ps1 =connexion.prepareStatement(reqq);
            while(rs.next())
            {
            ps1.setInt(1,(rs.getInt(1))+1);
            ps1.setString(2,nom_E);   
            }
            ps1.executeUpdate();
        } catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
        
    }*/

    
    
 /*  public void DecrementNombreInteresses(String nom_E) 
    {
        String req="SELECT nombre_interesses FROM evenements WHERE nom_E=?";
        String reqq="UPDATE evenements SET nombre_interesses=? where nom_E=?";
        PreparedStatement ps1;
        try {
            
           PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1,nom_E);
        } catch (SQLException ex)
        {
             System.out.println(Arrays.toString(ex.getStackTrace()));   
        }
        try {
            rs=ps.executeQuery();
            ps1 = connexion.prepareStatement(reqq);
            while(rs.next())
            {
            ps1.setInt(1,(rs.getInt(1))-1 );
            ps1.setString(2, nom_E);   
            }
            ps1.executeUpdate();
        } catch (SQLException ex) 
        {
           ex.printStackTrace();
        }
    }*/
    

    

    
    
    
    
    
}
