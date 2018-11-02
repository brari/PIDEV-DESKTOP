/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

//import com.esprit.entite.Recette;
import pidev.entities.Recette1;
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
public class Recette1Services {

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

    public Recette1Services() {
        connexion = MyDB.getInstance().getConnection();
    }

    public int getilham(int c) throws SQLException {
        String req = "SELECT id from recette where id=?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, c);
        ResultSet rst = ps.executeQuery();

        return rst.getInt(req);
    }

    public List<Recette1> getAllRecette1() throws SQLException {
        List<Recette1> annonce = new ArrayList<>();
        String req = "select * from recette";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Recette1 p = new Recette1(rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("description"), rst.getDate("date"), rst.getString("etat"), rst.getString("action"));
            annonce.add(p);
        }
        return annonce;
    }

    public List<Recette1> getAllRecette5() throws SQLException {
        List<Recette1> annonce = new ArrayList<>();
        String req = "select * from recette";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Recette1 p = new Recette1(rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("description"), rst.getDate("date"), rst.getString("etat"), rst.getString("action"));
            annonce.add(p);
        }
        return annonce;
    }

    public ObservableList<String> getAllRecetteForUtilisateur() throws SQLException {
        ObservableList<String> annonce = FXCollections.observableArrayList();
        String req;
        req = "select * from recette where etat=?";
        pst = connexion.prepareStatement(req);
        pst.setString(1, "Accepter");
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            Recette1 p = new Recette1(rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("description"), rst.getDate("date"), rst.getString("etat"), rst.getString("action"));
            annonce.add(p.getNom());
        }
        return annonce;
    }

   
    
    
    public List<Recette1> getAllRecettesaaa() throws SQLException {
        List<Recette1> recettes = new ArrayList<>();
        String req = "SELECT * FROM `recette` WHERE etat LIKE 'a%' ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            Recette1 p = new Recette1(rst.getInt(1), rst.getString(2), rst.getString(3));
            recettes.add(p);

        }
        return recettes;

    }


    public void ModifierEtat(Recette1 d) throws SQLException {

        String requete = "UPDATE recette SET etat=? where id=?";
        PreparedStatement pst = connexion.prepareStatement(requete);
        pst.setString(1, "Accepter");
        pst.setInt(2, d.getId());
        pst.executeUpdate();
        System.out.println("Modifiée");

    }

    public void ModifierTraiter(Recette1 d) throws SQLException {

        String requete = "UPDATE recette SET action=? where id=?";
        PreparedStatement pst = connexion.prepareStatement(requete);
        pst.setString(1, "Traiter");
        pst.setInt(2, d.getId());
        pst.executeUpdate();
        System.out.println("Modifiée");

    }

    public List<Recette1> getRecetteNonTraite() throws SQLException {
        List<Recette1> annonce = new ArrayList<>();

        String req = "SELECT * from recette where action=? AND CURRENT_TIMESTAMP-date>=7";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, "non traité");
        ResultSet rst = ps.executeQuery();
        while (rst.next()) {
            Recette1 p = new Recette1(rst.getInt("id"),
                    rst.getString("nom"), rst.getString("description"), rst.getDate("date"), rst.getString("etat"), rst.getString("action")
            );
            annonce.add(p);
        }
        return annonce;
    }

    //////Recette////////////
    ////////////////////////////**************************////////////////////////////
    public void add(Recette1 t, InputStream img) {
        try {
            String req = "insert into recette (nom,description,photo) values (?,?,?)";
            pst = connection.prepareStatement(req);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setBinaryStream(3, img);

            pst.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }

    }

    public void update(Recette1 t, String nom) {
        try {
            String req2 = "update recette set description=?,photo=? where nom=?";
            pst = connection.prepareStatement(req2);

            pst.setString(1, t.getDescription());
            pst.setBlob(2, t.getPhoto());

            pst.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");

        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println("erreur lors de la mise à jour " + ex.getMessage());

        }
    }

    public void delete(Recette1 t) {
        try {
            String req3 = "delete from recette where nom=?";
            pst = connexion.prepareStatement(req3);
            pst.setString(1, t.getNom());
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public List<Recette1> displayAll() {
        String req = "select * from recette";
        List<Recette1> rect = new ArrayList<>();

        try {
            pst = connexion.prepareStatement(req);
            rs = pst.executeQuery();

            while (rs.next()) {
                Recette1 p = new Recette1(rs.getInt(1), rs.getString(2), rs.getString(3));
                rect.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecetteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rect;
    }

    public List<Recette1> findByNom(Recette1 t) {
        List<Recette1> rect = new ArrayList<>();
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

    public void add(Recette1 t) {
        try {
            String req = "insert into recette (nom,description) values (?,?)";
            pst = connection.prepareStatement(req);

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
    public void ajouterRecette(Recette1 p) throws SQLException {
        String req = "INSERT INTO `patisserie`.`recette` (`nom`,`description`) VALUES ( '" + p.getNom() + "', '" + p.getDescription() + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public List<Recette1> getAllRecettes() throws SQLException {
        List<Recette1> recettes = new ArrayList<>();
        String req = "select * from recette order by nom";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            Recette1 p = new Recette1(rst.getInt(1), rst.getString(2), rst.getString(3));
            recettes.add(p);

        }
        return recettes;

    }

    /////utilisé avec bouton supprimer dans afficheRecette
    public void deleteRecette(int id) throws SQLException {

        String requete = "delete from recette where id=" + id;
        Statement stm = connexion.createStatement();
        stm.executeUpdate(requete);

    }

    ////////it works yess
    public void supprimer(String nom) throws SQLException {
        String req = "DELETE FROM `recette` WHERE `recette`.`nom` = ?";

        pst = connexion.prepareStatement(req);

        pst.setString(1, nom);

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
    public void ModifierRecette(Recette1 x) throws SQLException {

        String requete = "UPDATE recette SET nom=? description=? where id=?";
        pst = connexion.prepareStatement(requete);
        pst.setString(1, x.getNom());

        pst.setString(2, x.getDescription());
        pst.setInt(3, x.getId());

        pst.executeUpdate();
        System.out.println("Recette modifié");

    }

    public void ajouterRecette(Recette1 v, InputStream img) throws SQLException {
        String req = "INSERT INTO `recette`(`nom`, `description`, `photo`) VALUES (?,?,?)";
        PreparedStatement pst = connexion.prepareStatement(req);
        pst.setString(1, v.getNom());
        pst.setString(2, v.getDescription());

        pst.setBlob(5, img);
        pst.executeUpdate();

    }

    //////works 
    public void modifierReccc(String d, String a) {

        String req = "UPDATE recette SET description='" + d + "' WHERE  nom='" + a + "'  ";
        try {
            pst = connexion.prepareStatement(req);

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Recette1> getRecetteByName(String nom) throws SQLException {
        ObservableList<Recette1> recettes = FXCollections.observableArrayList();
        String req;
        req = "select * from recette where nom=?";
        pst = connexion.prepareStatement(req);
        pst.setString(1, nom);
        ResultSet rst = pst.executeQuery();
        while (rst.next()) {
            Recette1 p = new Recette1(rst.getString("nom"), rst.getString("description"));
            recettes.add(p);
        }
        return recettes;
    }

    // *************************************************************************
    public void ajouterRecetteeeee(Recette1 p) throws SQLException {

        File img = new File(p.getUrl());
        FileInputStream istream = null;

        try {
            istream = new FileInputStream(img);
            String requete = "insert into recette (nom, description, photo) values (?,?,?)";
            pst = connexion.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());

            pst.setBinaryStream(3, istream);
            pst.executeUpdate();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fichier inexistant");
            String requete = "insert into recette (nom, description) values (?,?)";
            pst = connexion.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDescription());

            pst.executeUpdate();
        }

    }

    public Image extraireImage(int id) {
        try {
            String requete = "select photo from recette where id=" + id;
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            OutputStream ostream = new FileOutputStream(new File("C:\\Users\\hp\\Desktop\\projet ilhem\\imagee\\tof.jpg"));
            if (rst.next()) {
                InputStream istream = rst.getBinaryStream("photo");
                byte[] contenant = new byte[1024];
                int longueur = 0;
                while ((longueur = istream.read(contenant)) != -1) {
                    ostream.write(contenant, 0, longueur);
                }
            }
            ostream.close();
            //istream.close();
            Image image = new Image("file:C:\\Users\\hp\\Desktop\\projet ilhem\\imagee\\tof.jpg");
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

    public void modifierRecetteAvecIMage(Recette1 p) throws SQLException {
        String requete = "update recette set nom='" + p.getNom() + "',description='" + p.getDescription()
                + "' where id=" + p.getId();
        Statement stm = connexion.createStatement();
        stm.executeUpdate(requete);

        if (!p.getUrl().equals("") && p.getUrl() != null) {
            File img = new File(p.getUrl());
            try {
                FileInputStream stream = new FileInputStream(img);
                String req = "update recette set photo=? where id=" + p.getId();
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setBinaryStream(1, stream);
                ps.executeUpdate();
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(PatisserieService.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Fichier inexistant");
            }

        }

    }
              //nbreprojetparcat
    public int nombreCommentParRecette(int id) {
        int i = 0;
        String requete = "select id_comment from commentaire_event where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                i++;
            }
            return i;
        } catch (SQLException ex) {
            System.out.println("erreur lors du calcul du nombre de comment " + ex.getMessage());
            //System.out.println(categorie.getIdCategorie());
            return 0;
        }

    }
    
    
    
    
    public int nombreTotalDeComment() {
        int i = 0;
        String requete = "select * from commentaire_event where id!=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, 0);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                i++;
            }
            return i;
        } catch (SQLException ex) {
            System.out.println("erreur lors du calcul du nombre de commet " + ex.getMessage());
            //System.out.println(categorie.getIdCategorie());
            return 0;
        }

    }
    
      public int getNombre(int s) throws SQLException{
        int x=0;
        String req="SELECT * FROM commentaire_event WHERE id='"+s+"'";
        Statement st=connection.createStatement();
        ResultSet rst=st.executeQuery(req);
         while (rst.next()){
            x++;
        }
         return x;
    }
    
    
    
    
    
}
