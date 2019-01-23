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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.entities.Produit;
import pidev.util.MyDB;
/**
 *
 * @author hp
 */
public class ProduitService {
    Connection connexion;
    public ProduitService()
    {
        connexion =MyDB.getInstance().getConnection();
   }
    
    public void ajouterProduct(Produit p) throws SQLException, FileNotFoundException
    {
        
//              
//        File img= new File(p.getImage());
//        FileInputStream istream=null;
//        
//        try {
//            istream= new FileInputStream(img);
//            String requete="insert into prods (nompro, prixpro, categoriepro, detailspro, nompat,image) values (?,?,?,?,?,?)";
//            PreparedStatement ps=connexion.prepareStatement(requete);
//            ps.setString(1, p.getNompro());
//            ps.setFloat(2, p.getPrixpro());
//            ps.setString(3, p.getCategoriepro());
//            ps.setString(4, p.getDetailspro());
//            ps.setString(5, p.getNompat());
//            ps.setBinaryStream(6, istream);
//            ps.executeUpdate();
//        } catch (FileNotFoundException ex) {
//            //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Fichier inexistant");
//String requete="insert into prods (nompro, prixpro, categoriepro, detailspro, nompat,image) values (?,?,?,?,?,?)";            PreparedStatement ps=connexion.prepareStatement(requete);
//            ps.setString(1, p.getNompro());
//            ps.setFloat(2, p.getPrixpro());
//            ps.setString(3, p.getCategoriepro());
//            ps.setString(4, p.getDetailspro());
//            ps.setString(5, p.getNompat());
//            ps.executeUpdate();
//        }   
        
     String req="INSERT INTO `prods`( `nompro`, `prixpro`, `categoriepro`, `detailspro`, `nompat`, `image`, `idpat`,`stock`) "
        + "VALUES ('"  + p.getNompro() + "','" + p.getPrixpro()+"','"+p.getCategoriepro()+"','"+p.getDetailspro()+"','"+p.getNompat()+"','"+p.getImage()+"','"+p.getIdp()+"','"+5+"')";
       Statement stm=connexion.createStatement();
        stm.executeUpdate(req);
    }
    
     public void ModifierProduct(int a,String nom,float prix,String cat, String det ,String name,String im) throws SQLException
    {
     String req="UPDATE prods SET nompro='"+nom +"', prixpro='"+prix +"', categoriepro='"+cat+"', detailspro='"+det+"', nompat='"+name+"', image='"+im+"'where idpro="+a;
      Statement s=connexion.createStatement() ;
      s.executeUpdate(req);
    }
     public void ModifierProduct(Produit p) throws SQLException
    {
     String req="UPDATE prods SET nompro='"+p.getNompro() +"', prixpro='"+p.getPrixpro()+"', categoriepro='"+p.getCategoriepro()+"', detailspro='"+p.getDetailspro()+"', nompat='"+p.getNompat()+"', image='"+p.getImage();
      Statement s=connexion.createStatement() ;
      s.executeUpdate(req);
    }
     public List<Produit> getAllProducts() throws SQLException
    {
        
        List<Produit> products = new ArrayList<Produit>();
        String req="select * from prods";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
        Produit p = new Produit(rst.getInt("idpro")
                , rst.getString("nompro")
                ,rst.getFloat("prixpro")
                ,rst.getString("categoriepro")
                ,rst.getString("detailspro")
                ,rst.getString("nompat")
                ,rst.getString("image")
        );
        products.add(p);
        
    }
        return products;
    }
      public void SupprimerProduit(int id ) throws SQLException 
 {
        String re= "DELETE FROM `prods` WHERE idpro="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(re);				
}

      
       public List<Produit> getProductsById(int id) throws SQLException
    {
        
        List<Produit> products = new ArrayList<Produit>();
        String req="select * from prods where idpat ="+id;
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
         Produit p = new Produit(rst.getInt("idpro")
                , rst.getString("nompro")
                ,rst.getFloat("prixpro")
                ,rst.getString("categoriepro")
                ,rst.getString("detailspro")
                ,rst.getString("nompat")
                ,rst.getString("image")
                ,rst.getInt("idpat"));
        products.add(p);
        
    }
        return products;
    }
       
         public List<Produit> getProductsByPrix(float prix) throws SQLException
    {
        
        List<Produit> products = new ArrayList<Produit>();
        String req="select * from prods where prixpro ="+prix;
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
         Produit p = new Produit(rst.getInt("idpro")
                , rst.getString("nompro")
                ,rst.getFloat("prixpro")
                ,rst.getString("categoriepro")
                ,rst.getString("detailspro")
                ,rst.getString("nompat")
                ,rst.getString("image"));
        products.add(p);
        
    }
        return products;
    }
         
          public ObservableList<Produit> getProductsByNom(String nom) {
     ObservableList<Produit> ListeProduit = FXCollections.observableArrayList();
            Produit produit = null;
        String req = "select * from prods where nompro =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, nom);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              produit = new Produit(resultSet.getInt(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
           ListeProduit.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeProduit;
    }
           public ObservableList<Produit> getProductsByCat(String nom) {
     ObservableList<Produit> ListeProduit = FXCollections.observableArrayList();
            Produit produit = null;
        String req = "select * from prods where categoriepro =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, nom);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              produit = new Produit(resultSet.getInt(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
           ListeProduit.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeProduit;
    }
    
              public ObservableList<Produit> getProductsByNomPat(String nom) {
     ObservableList<Produit> ListeProduit = FXCollections.observableArrayList();
            Produit produit = null;
        String req = "select * from prods where nompat =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, nom);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              produit = new Produit(resultSet.getInt(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
           ListeProduit.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListeProduit;
    }
               public List<Produit>  rechercherProduit(String filtre, String valeur) throws SQLException{
        List<Produit> produits= new ArrayList<>();
        String requete="select * from prods";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(requete);
        
        while(rst.next()){
            if(rst.getString(filtre).toLowerCase().contains(valeur.toLowerCase())){
            Produit p=new Produit(rst.getInt("idpro")
                , rst.getString("nompro")
                ,rst.getFloat("prixpro")
                ,rst.getString("categoriepro")
                ,rst.getString("detailspro")
                ,rst.getString("nompat")
                ,rst.getString("image")
                ,rst.getInt("idpat"));
            produits.add(p);
            }
        }
        return produits;               
    
    }
               
               
                public void modifierProduit(Produit p) throws SQLException{ 
     String req="UPDATE prods SET nompro='"+p.getNompro() +
        "', prixpro='"+p.getPrixpro()+"', categoriepro='"+
    p.getCategoriepro()+"', detailspro='"+p.getDetailspro()+"', nompat='"+p.getNompat()+"', image='"
             +p.getImage()+"' where idpro="+p.getIdpro();

//        String requete="update patisserie set nom='"+p.getNom()+"',adresse='"+p.getAdresse()+
//                "',contact='"+p.getContact()+"',mail='"+p.getMail()+"' where idp="+p.getIdp();
        Statement stm=connexion.createStatement();
        stm.executeUpdate(req);
        
//        if(!p.getImage().equals("") && p.getImage()!=null){
//           File img= new File(p.getImage());
//            try {
//                FileInputStream stream=new FileInputStream(img);
//                String reqe="update prods set image=? where idpro="+p.getIdpro();
//                PreparedStatement ps=connexion.prepareStatement(req);
//                ps.setBinaryStream(1, stream);
//                ps.executeUpdate();
//            } catch (FileNotFoundException ex) {
//                //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("Fichier inexistant");
//            }         
//        
//        }
        
    }
                
                
                 public Image extraireImage(int id) throws SQLException{
       
        try {
            
//            String requete="select image from prods where idpro='"+id;
String requete="select image from prods where idpro=?";
 PreparedStatement preparedStatement;
  preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setInt(1, id);
              ResultSet rst=preparedStatement.executeQuery(requete);
            
//            Statement stm=connexion.createStatement();
//            ResultSet rst=stm.executeQuery(requete);
            
//        java.sql.Blob blob=rst.getBlob("image");
//        InputStream stream=blob.getBinaryStream();



        
       // OutputStream ostream= new FileOutputStream(new File("photo.jpg"));
        
        if(rst.next()){

        Image image=new Image("file:"+rst.getString("image"));
        
        return image; }
        
      } catch (SQLException ex) {
         //  Logger.getLogger(patisserie.service.ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
        
    }  
 
           public int  Afficher_list()  {
int c = 0 ;
        try {

            String req = "select * from prods ";
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);

            while (rst.next()) {
                c++;
            }
            
               
          
            }

         
     catch (SQLException ex) {
      //  Logger.getLogger(patisserie.service.ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;

}     
           
           
             public int  Afficher_listcat()  {
int c = 0 ;
        try {
            String sal="sale";
            String req = "select * from prods where categoriepro='"+ sal;
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);

            while (rst.next()) {
                c++;
            }
            
         
            }

         
     catch (SQLException ex) {
        //Logger.getLogger(patisserie.service.ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;

}     
           
   
                          public int  Afficher_listcate()  {
int c = 0 ;
        try {
            String sal="sucre";
            String req = "select * from prods where categoriepro='"+ sal;
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);

            while (rst.next()) {
                c++;
            }
            
               
          
            }

         
     catch (SQLException ex) {
      //  Logger.getLogger(patisserie.service.ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;

} 
             
             
    
}
