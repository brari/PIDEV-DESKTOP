package pidev.service;





import pidev.entities.CommentaireEvent;
import pidev.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class CommentaireRecetteServices {
      Connection connexion ;
      PreparedStatement pst;

    public CommentaireRecetteServices() {
         // connexion =MyDb.getInstance().getConnection();
         connexion=MyDB.getInstance().getConnection();
    }
    
      public  void ajouterCommentaire(CommentaireEvent c) throws SQLException{
        String req="INSERT INTO commentaire_event (id,comment,id_utilisateur) "
                + "VALUES(?,?,?)";
         pst =connexion.prepareStatement(req);
        pst.setInt(1, c.getId());
        pst.setString(2, c.getComment());
        pst.setInt(3,c.getId_utilisateur());
       
       pst.executeUpdate();
}
       public void supprimerCommentaire(CommentaireEvent c) throws SQLException{
              
        
            String requete="DELETE FROM commentaire_event where id_comment=?";
             pst=connexion.prepareStatement(requete);
            pst.setInt(1,c.getId_comment());
            pst.executeUpdate();
                 System.out.println("commentaire supprimé");

    }
              public void ModifierCommentaire(CommentaireEvent c) throws SQLException{
              
        
           String requete="UPDATE commentaire_event SET comment=? where id_comment=?";
             pst=connexion.prepareStatement(requete);
            pst.setString(1, c.getComment());
           
            pst.setInt(2, c.getId_comment());
            
            pst.executeUpdate();
            System.out.println("commentaire Modifié");

    }
              public List <CommentaireEvent> getAllCommentaire( int a) throws SQLException{
     List<CommentaireEvent> c= new ArrayList<>();
     String req="Select * from commentaire_event where id=?";
      pst =connexion.prepareStatement(req);
     pst.setInt(1,a);

     ResultSet rst=pst.executeQuery();
       while (rst.next()){
           CommentaireEvent ce=new CommentaireEvent(rst.getInt("id"),rst.getString("comment"),rst.getInt("id_utilisateur"),rst.getDate("date"), rst.getInt("id_comment"));
          c.add(ce);
       }
        return c;
    }
              
      public void deleteComment(int id) throws SQLException {

        String requete = "delete from commentaire_event where id=" + id;
        Statement stm = connexion.createStatement();
        stm.executeUpdate(requete);

    }         
              
              
}
     