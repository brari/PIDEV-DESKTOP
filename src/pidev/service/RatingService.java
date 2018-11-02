package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.entities.Patisserie;
import pidev.entities.Rating;
import pidev.util.MyDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class RatingService {
    Connection connexion;

    public RatingService() {
        connexion=MyDB.getInstance().getConnection();
    }
    
    //un nouvel utilisateur va noter
    public void AjouterNote(Rating r) throws SQLException{
        //String requete="insert into patisserie (nom, adresse, raiting) values ("
        //        +p.getNom()+","+ p.getAdresse()+","+p.raiting+")";
            
            String requete="insert into rating (id_user, id_pat, note) values (?,?,?)";
            PreparedStatement ps=connexion.prepareStatement(requete);
            ps.setInt(1, r.getId_user());
            ps.setInt(2, r.getId_pat());
            ps.setDouble(3, r.getNote());
            ps.executeUpdate();
        
    }
    
    //vérifier si l'user a deja noté
    public int returnId(int id,int idp) throws SQLException{
        String requete="select id_user from rating where id_user="+id+" and id_pat="+idp;
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        if(rst.next())
            return rst.getInt("id_user");
        return 0;
    }
    
    //méthode de modification de la note
    public void updateNote(Double note, int id) throws SQLException{
        String requete="update rating set note="+note+" where id_user="+id;
        PreparedStatement ps=connexion.prepareStatement(requete);
        ps.executeUpdate();
    }
    
    //nombre notes pour une patisserie
    public int NombreNote(int id) throws SQLException{
        int c=0;
        String requete="select * from rating where id_pat="+id;
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        while(rst.next())
           c++;
        return c;
    
    }
    
    //total de notes pour une patisserie
    public Double TotalNote(int id) throws SQLException{
        Double c=0.0;
        String requete="select note from rating where id_pat="+id;
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        while(rst.next())
           c+=rst.getDouble("note");
        return c;
    
    }
    
    public Double rendNote(int id) throws SQLException{
        String requete="select note from rating where id_pat="+id;
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        while(rst.next())
           return rst.getDouble("note");
        return 0.0;
    
    }
}
