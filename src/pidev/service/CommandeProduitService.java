/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entities.CommandeProduit;
import pidev.entities.Produit;
import pidev.util.MyDB;

/**
 *
 * @author Insaf-Nefzi
 */
public class CommandeProduitService {
      Connection connexion;
    public CommandeProduitService()
    {
        connexion =MyDB.getInstance().getConnection();
   }
    public void ajouterComdPro (CommandeProduit cp) throws SQLException
    {
        String req="INSERT INTO `cmdprod`( `idu`, `idpro`, `nompro`, `prixpro`, `categoriepro`, `detailspro`, `nompat`, `image`, `date`)"
                + " VALUES ('"  + cp.getIdu() + "','"  + cp.getIdpro() + "','"  + cp.getNompro()+ 
                "','"  + cp.getPrixpro()+ "','"  + cp.getCategoriepro()+ "','"  + cp.getDetailspro()+ 
                "','"  + cp.getNompat()+ "','"  + cp.getImage()+ "','"  + cp.getDate()+ "')";
               Statement stm=connexion.createStatement();
        stm.executeUpdate(req);
    }
      public void ModifierComdPro(CommandeProduit cp , int id) throws SQLException
    {
        String req="UPDATE `cmdprod` SET `idu`='"  + cp.getIdu() + "',`idpro`='"  + cp.getIdpro() +
            "',`nompro`='"  + cp.getNompro()+ "',`prixpro`='"  + cp.getPrixpro()+ "',`categoriepro`='"  
                + cp.getCategoriepro()+ "',`detailspro`='"  + cp.getDetailspro()+ "',`nompat`='"  
                + cp.getNompat()+ "',`image`='"  + cp.getImage()+ "',`date`='"  + cp.getDate()+"'where idcp="+id ;
      Statement s=connexion.createStatement() ;
      s.executeUpdate(req);
    }
         public List<CommandeProduit> getAllComdPro() throws SQLException
    {
        
        List<CommandeProduit> cmdpros = new ArrayList<CommandeProduit>();
        String req="select * from cmdprod";
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
        CommandeProduit cp = new CommandeProduit(rst.getInt("idcp")
                ,rst.getInt("idu")
                ,rst.getInt("idpro")
                , rst.getString("nompro")
                ,rst.getFloat("prixpro")
                ,rst.getString("categoriepro")
                ,rst.getString("detailspro")
                ,rst.getString("nompat")
                ,rst.getString("image")
                ,rst.getString("date")
        );
        cmdpros.add(cp);
        
    }
        return cmdpros;
    }
         
          public void SupprimerComdPro(int id ) throws SQLException 
 {
        String re= "DELETE FROM `cmdprod` WHERE idcp="+id;
        Statement stm=connexion.createStatement();
        stm.executeUpdate(re);				
}
        public void SupprimerComdPro(CommandeProduit cp) throws SQLException 
 {
        String re= "DELETE FROM `cmdprod` ";
        Statement stm=connexion.createStatement();
        stm.executeUpdate(re);				
}
           public List<CommandeProduit> getAllComdProById(int id) throws SQLException
    {
        
        List<CommandeProduit> cmdpros = new ArrayList<CommandeProduit>();
        String req="select * from cmdprod where idpro="+id;
        Statement stm=connexion.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
        CommandeProduit cp = new CommandeProduit(rst.getInt("idcp")
                ,rst.getInt("idu")
                ,rst.getInt("idpro")
                , rst.getString("nompro")
                ,rst.getFloat("prixpro")
                ,rst.getString("categoriepro")
                ,rst.getString("detailspro")
                ,rst.getString("nompat")
                ,rst.getString("image")
                ,rst.getString("date")
        );
        cmdpros.add(cp);
        
    }
        return cmdpros;
    }
                      public int  Afficher_listCom()  {
int c = 0 ;
        try {

            String req = "select * from cmdprod";
            Statement stm=connexion.createStatement();

              ResultSet rst=stm.executeQuery(req);

            while (rst.next()) {
                c++;
            }
            
                c++;
          
            }

         
     catch (SQLException ex) {
        Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;

}
}
