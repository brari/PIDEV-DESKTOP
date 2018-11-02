/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;


import pidev.entities.Recette;
//import com.esprit.entite.User;
import com.esprit.interfac.IRecetteDAO;
//import com.esprit.utils.DataSource;
import pidev.util.MyDB;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author IMEN
 */
public class RecetteDAO implements IRecetteDAO <Recette>{
    
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
    
    public RecetteDAO() {
        //connection = (DataSource.getInstance()).getConnection();
        connexion=MyDB.getInstance().getConnection();
    } 

    
   private static IRecetteDAO irecetteDAO;

    public static IRecetteDAO getInstance() {
        if (irecetteDAO == null) {
            irecetteDAO = new RecetteDAO();
        }
        return irecetteDAO;
    } 
    
    
    
    
    
    public void add(Recette t,InputStream img) {
      try {
            String req="insert into recette (nom,description,photo) values (?,?,?)";
            pst=connection.prepareStatement(req);
            
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setBinaryStream(3, img);
            
            
            pst.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());        }  
        
        
        
        
        
        
    }

    @Override
    public void update(Recette t, String nom) {
         try {
            String req2= "update recette set description=?,photo=? where nom=?";
            pst=connection.prepareStatement(req2);
            
            pst.setString(1, t.getDescription());
            pst.setBlob(2, t.getPhoto());
            
            
            
            
            pst.executeUpdate();
             System.out.println("Mise à jour effectuée avec succès");
            
        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        
    }
    }

    @Override
    public void delete(Recette t) {
         try {
            String req3= "delete from recette where nom=?";
            pst=connexion.prepareStatement(req3);
            pst.setString(1,t.getNom());
            pst.executeUpdate();
             System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public List<Recette> displayAll() {
         String req="select * from recette";
        List<Recette> rect=new ArrayList<>();
        
        try {
            pst=connexion.prepareStatement(req);
            rs=pst.executeQuery();
            
            while(rs.next()){
                Recette p=new Recette(rs.getInt(1),rs.getString(2),rs.getString(3));
                rect.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rect; 
    }

    @Override
    public List<Recette> findByNom(Recette t) {
        List<Recette> rect=new ArrayList<>();
//          String req5 ="SELECT * FROM `recette` WHERE nom=?";
//        try {
//           pst=connection.prepareStatement(req5);
//           pst.setString(1,t.getNom());
//           rs = pst.executeQuery();
//           
//           rs.next();
//           try{
//           Recette p =   new Recette(rs.getString(2),rs.getString(3),rs.getBinaryStream(4));
//           rect.add(p);}catch(Exception e)
//           {
//               System.out.println(e);
//           }
//           } catch (SQLException ex) {
//            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return rect;
    }

    @Override
    public void add(Recette t) {
         try {
            String req="insert into recette (nom,description) values (?,?)";
            pst=connection.prepareStatement(req);
            
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            //pst.setBytes(3, t.getPhoto());
            
            
            pst.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());   
    }

         
    
         }
    
    //***************************************************************************
    
    
     public void ajouterRecette(Recette p) throws SQLException{
    String req="INSERT INTO `pat`.`recette` (`nom`,`description`) VALUES ( '"+p.getNom()+"', '"+p.getDescription()+"')";
    
    Statement stm=connexion.createStatement();
    stm.executeUpdate(req);
    
    } 
    
    
     public List<Recette> getAllRecettes() throws SQLException {
            List<Recette> recettes=new ArrayList<>();
            String req= "select * from recette order by nom";
            Statement stm=connexion.createStatement();
            ResultSet rst=stm.executeQuery(req);
            
            while (rst.next()) {
    
           Recette p=new Recette(rst.getInt(1),rst.getString(2),rst.getString(3));
            recettes.add(p);
            
            }
           return recettes; 
            
            
    }
    
    /////utilisé avec bouton supprimer dans afficheRecette
    public void deleteRecette(int id )throws SQLException {
         
           
            
              String requete="delete from recette where id="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
    
    
    }
    ////////it works yess
     public void supprimer(String nom) throws SQLException{
        String req = "DELETE FROM `recette` WHERE `recette`.`nom` = ?";
        
        
          
            
            pst=connexion.prepareStatement(req);
           
            pst.setString(1,nom);
            
             System.out.println(pst);
   
    
    pst.executeUpdate();
     System.out.println("Suppression effectuée avec succès");
        }
   
    
    
    
    
    
//       public void supprimer(String nom) {
//        String req = "DELETE FROM `recette` WHERE `recette`.`nom` = ?";
//        
//        try {
//            PreparedStatement pre=connexion.prepareStatement(req);
//            pre.setString(1, nom);
//            pre.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

    
    public void ModifierRecette(Recette x) throws SQLException{
              
        
           String requete="UPDATE recette SET nom=? description=? where id=?";
             pst=connexion.prepareStatement(requete);
            pst.setString(1, x.getNom());
            
            pst.setString(2, x.getDescription());
            pst.setInt(3, x.getId());
            
            pst.executeUpdate();
         System.out.println("Recette modifié"); 

    }
    
     public void ajouterRecette(Recette v,InputStream img) throws SQLException{
        String req="INSERT INTO `recette`(`nom`, `description`, `photo`) VALUES (?,?,?)";      
        PreparedStatement pst=connexion.prepareStatement(req);
        pst.setString(1, v.getNom());
        pst.setString(2, v.getDescription());
        
        pst.setBlob(5, img);
        pst.executeUpdate();

    }

      
    //////works 
    public void modifierReccc(String d,String a) {
 
String req="UPDATE recette SET description='"+d+"' WHERE  nom='"+a+"'  ";
         try {
            pst = connexion.prepareStatement(req);
           
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    }
    
    
    
    
    
   public ObservableList<Recette> getRecetteByName(String nom) throws SQLException{
   ObservableList<Recette> recettes = FXCollections.observableArrayList();
   String req;
          req ="select * from recette where nom=?";
   pst=connexion.prepareStatement(req);
   pst.setString(1, nom);
   ResultSet rst=pst.executeQuery();
    while(rst.next()){
         Recette p =new Recette(rst.getString("nom"),rst.getString("description"));
           recettes.add(p);
        }
        return recettes;
    }  
    
   
   
   
   
   
   
  // *************************************************************************
   
   
  public void ajouterRecetteeeee(Recette p) throws SQLException{
      
        File img= new File(p.getUrl());
        FileInputStream istream=null;
        
        try {
            istream= new FileInputStream(img);
            String requete="insert into recette (nom, description, photo) values (?,?,?)";
             pst=connexion.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());
            
            pst.setBinaryStream(3, istream);
            pst.executeUpdate();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichier inexistant");
            String requete="insert into recette (nom, description) values (?,?)";
             pst=connexion.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());
            
            pst.executeUpdate();
        }   
    
        
    }
     
   public Image extraireImage(int id){
        try {
            String requete="select photo from recette where id="+id;
            Statement stm=connexion.createStatement();
            ResultSet rst=stm.executeQuery(requete);
        OutputStream ostream= new FileOutputStream(new File("C:\\Users\\IMEN\\Desktop\\imagee\\tof.jpg")); 
        if(rst.next()){
        InputStream istream=rst.getBinaryStream("photo");
        byte[] contenant = new byte[1024];
        int longueur = 0; 
        while((longueur = istream.read(contenant)) != -1)
        {
            ostream.write(contenant, 0, longueur);
        }
        }
        ostream.close();
        //istream.close();
        Image image=new Image("file:C:\\Users\\IMEN\\Desktop\\imagee\\tof.jpg");
        return image; 
        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }  
    
   
   
   
   public void modifierRecetteAvecIMage(Recette p) throws SQLException{ 
        String requete="update recette set nom='"+p.getNom()+"',description='"+p.getDescription()+
                "' where id="+p.getId();
        Statement stm=connexion.createStatement();
        stm.executeUpdate(requete);
        
        if(!p.getUrl().equals("") && p.getUrl()!=null){
           File img= new File(p.getUrl());
            try {
                FileInputStream stream=new FileInputStream(img);
                String req="update recette set photo=? where id="+p.getId();
                PreparedStatement ps=connexion.prepareStatement(req);
                ps.setBinaryStream(1, stream);
                ps.executeUpdate();
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Fichier inexistant");
            }         
        
        }
        
    }
   
   
   
   
   
   
   
}