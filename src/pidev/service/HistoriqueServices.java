/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import pidev.entities.Historique;
import pidev.entities.Recette1;
import pidev.util.MyDB;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author IMEN
 */
public class HistoriqueServices {
    
  Connection connection;//la création d'un objet DataBase pour permettre d'encapsuler ensemble des objets nécessaires à la connexion à une base de données 
    PreparedStatement pst;
    ResultSet rs; //Un objet ResultSet permettra de récupérer les données en provenance de l'objet Statement.
    Statement stmt;//créer un objet Statement, pouvant être obtenu à partir de l'objet Connection Pour exécuter une requête SQL
//méthode Execute rendant l'exécution de requête

    Connection connexion;
    Statement stm;

    private FileInputStream fis;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;

    public HistoriqueServices() {
        connexion = MyDB.getInstance().getConnection();
    }
    
  
    
      public void ajouterRecette(Recette1 p) throws SQLException {
        String req = "INSERT INTO `patisserie`.`historique` (`id`,`nom`,`description`,`photo`,`date`,`etat`,`action`) VALUES ( '" + p.getId()+ "', '" + p.getNom() + "', '" + p.getDescription() + "' , '" + p.getPhoto()+ "', '" + p.getDate()+ "' , '" + p.getEtat()+ "' , '" + p.getAction()+ "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);

        
        
    }
    
    
      public ArrayList<Historique> getAllHis() throws SQLException{
        ArrayList<Historique> annonce = new ArrayList<>();
        String req="select * from `patisserie`.`historique` ";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while(rst.next()){
            Historique p=new Historique(rst.getInt("id"),
                    rst.getString("nom"), rst.getString("description"), rst.getDate("date"), rst.getString("etat"), rst.getString("action")
            );
            annonce.add(p);
        }
        return annonce;
    }
    
    
    public  void ajoutHis(Recette1 d) throws SQLException{
        String req="INSERT INTO historique (id,nom,description,date,etat,action)"
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement ps =connexion.prepareStatement(req);
        ps.setInt(1, d.getId());
        ps.setString(2, d.getNom());
        ps.setString(3, d.getDescription());
        
        ps.setDate(4, d.getDate());
        
        ps.setString(5,d.getEtat());
        ps.setString(6,d.getAction());
     
       ps.executeUpdate();
      
    }

    
    
    
    
    
}
