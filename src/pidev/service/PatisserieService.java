/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import com.mysql.jdbc.Blob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import pidev.entities.Patisserie;
import pidev.util.MyDB;

/**
 *
 * @author hp
 */
public class PatisserieService {
    Connection connexion;

    public PatisserieService() {
        connexion=MyDB.getInstance().getConnection();
        
    }
    
    // methode d'ajout avec prepared statement
    
    public void ajouterPatisserie(Patisserie p) throws SQLException{
        //String requete="insert into patisserie (nom, adresse, raiting) values ("
        //        +p.getNom()+","+ p.getAdresse()+","+p.raiting+")";
            
            String requete="insert into patisserie (nom, adresse, contact, mail, url, rating,idprop) values (?,?,?,?,?,?,?)";
            PreparedStatement ps=connexion.prepareStatement(requete);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getAdresse());
            ps.setInt(3, p.getContact());
            ps.setString(4, p.getMail());
            ps.setString(5, p.getUrl());
            ps.setDouble(6,0.0);
            ps.setDouble(7,p.getIdprop());
            ps.executeUpdate();
        
    }
    
    // methode de modification avec preparedstatement
    public void modifierPatisserie(Patisserie p) throws SQLException{ 
        String requete="update patisserie set nom='"+p.getNom()+"',adresse='"+p.getAdresse()+
                "',contact="+p.getContact()+",mail='"+p.getMail()+"' where idp="+p.getIdp();
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
        
        if(!p.getUrl().equals("") && p.getUrl()!=null){
                String req="update patisserie set url=? where idp="+p.getIdp();
                PreparedStatement ps=connexion.prepareStatement(req);
                ps.setString(1, p.getUrl());
                ps.executeUpdate();
        }
        
    }
    
    // methode de modification et supression avec statement
    public void suprimerPatisserie(int id) throws SQLException{ 
        //String requete="delete from patisserie where idp="+p.getIdp();
        String requete="delete from patisserie where idp="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
    }
    
        
    public List<Patisserie> afficherPatisseries() throws SQLException{
        
        List<Patisserie> patisseries=new ArrayList<>();
        String requete="select * from patisserie order by nom";
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        
        while(rst.next()){
            Patisserie p=new Patisserie(rst.getInt("idp"),rst.getString("nom"),
                    rst.getString("adresse"),rst.getInt("contact"),
                    rst.getString("mail"),rst.getString("url"),rst.getInt("idprop"));
            p.setRating(rst.getDouble("rating"));
            patisseries.add(p);
        }
        return patisseries;
    }
    
    public List<Patisserie> afficherMesPatisseries(int id) throws SQLException{
        
        List<Patisserie> patisseries=new ArrayList<>();
        String requete="select * from patisserie where idprop="+id+" order by nom";
        Statement stm= connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        
        while(rst.next()){
            Patisserie p=new Patisserie(rst.getInt("idp"),rst.getString("nom"),
                    rst.getString("adresse"),rst.getInt("contact"),
                    rst.getString("mail"),rst.getString("url"),rst.getInt("idprop"));
            p.setRating(rst.getDouble("rating"));
            patisseries.add(p);
        }
        return patisseries;
    }
    
    
    public Image extraireImage(int id){
       
        try {
            
            String requete="select url from patisserie where idp="+id;
            Statement stm=connexion.createStatement();
            ResultSet rst=stm.executeQuery(requete);
            
//        java.sql.Blob blob=rst.getBlob("image");
//        InputStream stream=blob.getBinaryStream();
        
        //OutputStream ostream= new FileOutputStream(new File("C:\\wamp64\\www\\Images\\photo.jpg"));
        
        if(rst.next()){
            
//        InputStream istream=rst.getBinaryStream("image");
//        byte[] contenant = new byte[1024];
//        int longueur = 0; 
//        while((longueur = istream.read(contenant)) != -1)
//        {
//            ostream.write(contenant, 0, longueur);
//        }
//        }
//        ostream.close();
//        //istream.close();
        Image image=new Image("file:"+rst.getString("url"));
        
        return image; }
        
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }  
    
    public List<Patisserie>  rechercherPatisserie(String filtre, String valeur) throws SQLException{
        List<Patisserie> patisseries= new ArrayList<>();
        String requete="select * from patisserie";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        
        while(rst.next()){
            if(rst.getString(filtre).toLowerCase().contains(valeur.toLowerCase())){
            Patisserie p=new Patisserie(rst.getInt("idp"),rst.getString("nom"),
                    rst.getString("adresse"),rst.getInt("contact"),
                    rst.getString("mail"));
            p.setRating(rst.getDouble("rating"));
            p.setIdprop(rst.getInt("idprop"));
            patisseries.add(p);
            }
        }
        return patisseries;               
    
    }
    
    
    public List<Patisserie>  rechercherMesPatisserie(String filtre, String valeur,int id) throws SQLException{
        List<Patisserie> patisseries= new ArrayList<>();
        String requete="select * from patisserie where idprop="+id;
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        
        while(rst.next()){
            if(rst.getString(filtre).toLowerCase().contains(valeur.toLowerCase())){
            Patisserie p=new Patisserie(rst.getInt("idp"),rst.getString("nom"),
                    rst.getString("adresse"),rst.getInt("contact"),
                    rst.getString("mail"));
            p.setIdprop(id);
            p.setRating(rst.getDouble("rating"));p.setIdprop(rst.getInt("idprop"));
            patisseries.add(p);
            }
        }
        return patisseries;               
    
    }
    
    public void updateRating(Double rating, int id) throws SQLException{
        String requete="update patisserie set rating ="+rating+" where idp="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
    
    
    }
    
}

