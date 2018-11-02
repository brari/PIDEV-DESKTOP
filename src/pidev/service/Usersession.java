/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

import pidev.util.MyDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import pidev.entities.Users;
import pidev.entities.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usersession {
     Connection connection;
    private Session user;
    
      public Usersession() {
                this.connection= MyDB.getInstance().getConnection();}
                
                 public void add_session(String s)throws SQLException {
        
          String req="Select * from users where First_name='"+s+"';";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req); 
        String requete="INSERT INTO `session`(`first_name`, `last_name`,`user_roles`,`email`,`id`) VALUES (?,?,?,?,?);";
         PreparedStatement ps= connection.prepareStatement(requete);
                    
                ps.setString(1,s);
                while(rs.next()){
         ps.setString(2, rs.getString("Last_name"));
         ps.setString(3, rs.getString("gender"));
         ps.setString(4, rs.getString("email"));
         ps.setInt(5, rs.getInt("idU"));
                }
        
        
     
         ps.executeUpdate();
        
    }
               public void SupprimerSession() throws SQLException 
 {
        String re= "DELETE FROM `session` ";
        Statement stm=connection.createStatement();
        stm.executeUpdate(re);				
}
      public String getIdSession() throws SQLException{
        String re= "select u.First_name from users u inner join session s where u.idU=s.id";;
        Statement stm= connection.createStatement();
        ResultSet rst=stm.executeQuery(re);
        
        while(rst.next()){
            return rst.getString("First_name");
            
        }
	
         return "";
       }
                 
}
