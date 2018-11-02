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
import java.util.ArrayList;
import java.util.List;
import pidev.entities.Message;
import pidev.entities.Reclamation;
import pidev.util.MyDB;

/**
 *
 * @author brari
 */
public class MessageService {
    Connection connexion;
    public MessageService(){
     connexion=MyDB.getInstance().getConnection();}

public void ajouterMessage(String s,int i) throws SQLException{
 String req="INSERT INTO `message` (`id`,`nom`, `prenom`,`contenu`,`id_rec`,`id_reclamant`,`id_concerne`) VALUES (3,'5oune','5oune','"+s+"',"+i+",45,54)";
        //String req1="INSERT INTO 'Product' ('libelle' ,'nom')" +" values (p.getLib(),p.getDesc();";
                Statement stm= connexion.createStatement();
                stm.executeUpdate(req);
}
 public List<Message> afficher(int d)throws SQLException
    {
List <Message> messages= new ArrayList<>();
          PreparedStatement pst = connexion.prepareStatement("SELECT * FROM message where id_rec=?");
          pst.setInt(1,d);
        ResultSet rst=pst.executeQuery();
        while (rst.next()){
            Message m= new Message(rst.getInt("id"),rst.getString("nom"),rst.getString("prenom"),rst.getString("contenu"));
            messages.add(m);
        }
        return messages;
    }
}