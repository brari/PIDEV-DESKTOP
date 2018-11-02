/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javax.activation.DataSource;
import pidev.entities.Reclamation;
import pidev.util.MyDB;

/**
 *
 * @author brari
 */
public class ReclamationService {
    Connection connexion;
   public  ReclamationService(){
     connexion=MyDB.getInstance().getConnection();}
    public void  ajouterreclamation(Reclamation r) throws SQLException
    {
          DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
LocalDate date = LocalDate.now();
      String req="INSERT INTO `reclamation` (`type`, `contenu`,`date`,`statut`,`reclamant`,`objet`) VALUES ('" +r.getType()+"', '"+r.getContenu()+"','"+date+"',' en cours ','anouir hmidi','"+r.getObjet()+"')";
        //String req1="INSERT INTO 'Product' ('libelle' ,'nom')" +" values (p.getLib(),p.getDesc();";
                Statement stm= connexion.createStatement();
                stm.executeUpdate(req);
    }
      public void supprimerreclamation(int id) throws SQLException {
            PreparedStatement pst = connexion.prepareStatement("DELETE FROM reclamation WHERE id=?");
            pst.setInt(1, id);
            pst.executeUpdate();   
        } 
    public void traiterreclamation(int id,String s ,String k ) throws SQLException{
         PreparedStatement ps = connexion.prepareStatement("update  reclamation   set statut = 'traitee',decision=?,reponse=? where id = ?");
       ps.setString(1, s);
         ps.setString(2, k);
         ps.setInt(3,id);
              ps.executeUpdate();
    }
    public void getReclamationNonTraite()throws SQLException
    {
         String req="SELECT * FROM reclamation where statut='traitee'";
          Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            System.out.println("les reclamation non traités : "+"   "+rst.getInt("id")+"   "+rst.getString("type")+"    "+rst.getString("contenu")+"    "+rst.getString("date")+"    "+rst.getString("reponse")+"   "+rst.getString("statut"));
        }
        
    }
    
    public List<Reclamation> getAllReclamation() throws SQLException
    {   
        List <Reclamation> reclamations= new ArrayList<>();
        String req="SELECT * FROM reclamation";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            Reclamation r= new Reclamation(rst.getInt("id_reclamant"),rst.getInt("id"),rst.getString("type"),rst.getString("contenu"),rst.getDate("date"),rst.getString("reponse"),rst.getString("statut"),rst.getString("objet"),rst.getString("decision"));
            reclamations.add(r);
        }
        return reclamations;
    }   
    public List<Reclamation> afficherhistorique(int i)throws SQLException
    {
List <Reclamation> reclamations= new ArrayList<>();
          PreparedStatement pst = connexion.prepareStatement("SELECT * FROM reclamation where id_reclamant=?");
          pst.setInt(1,i);
        ResultSet rst=pst.executeQuery();
        while (rst.next()){
            Reclamation r= new Reclamation(rst.getInt("id_reclamant"),rst.getInt("id"),rst.getString("type"),rst.getString("contenu"),rst.getDate("date"),rst.getString("reponse"),rst.getString("statut"),rst.getString("objet"),rst.getString("decision"));
            reclamations.add(r);
        }
        return reclamations;
    }
 public Reclamation chercherReclamation(int id)throws SQLException
    {
        Reclamation r = null ;
      ReclamationService ReclamationService = new ReclamationService();  
      for (Reclamation r2 : ReclamationService.afficherhistorique(1)) {
       if(r2.getId()==id) 
           r=r2;
      }
        return r;
    }    
     public String chercher(String s)throws SQLException
    {
      
      ReclamationService ReclamationService = new ReclamationService();  
        String k = null;
      for (Reclamation r2 : ReclamationService.afficherhistorique(1)) {
       if(r2.getContenu()==s) 
           k=r2.getObjet();
      }
        return k;
    }    

/*public ObservableList<PieChart.Data> statistique() throws SQLException {
       
        Connection connection = MyDB.getInstance().getConnection();
        ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();
        Statement stm = connection.createStatement();
        ResultSet res = stm.executeQuery("select statut,count(statut) from `reclamation` group by statut");
            while (res.next()) {
                PieChart.Data e = new PieChart.Data(res.getString(1),res.getInt(2)); 
                pie.add(e);               
            }
        return pie;
    }*/
       public int  getNombre(String s) throws SQLException  {
int c = 0 ;
            String req = "select count(*) from `reclamation` group by statut HAVING statut='"+s+"' ";
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);
      while(rst.next()){        
   c= rst.getInt("count(*)");
      }
         return c ;
            }
         public int  Afficher_listcat(int i ) throws SQLException  {
int c = 0 ;
     
         
            String req = "select * from reclamation where id_concerne='"+i+"' and  type='patissier';";
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);

            while (rst.next()) {
                c++;
            }
            
           return c;
         }
               public int  NombreRec() throws SQLException  {
int c = 0 ;
     
         
            String req = "select * from reclamation" ;
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);

            while (rst.next()) {
                c++;
            }
            
           return c;
            }
  

} 




         
 
         
 
 
