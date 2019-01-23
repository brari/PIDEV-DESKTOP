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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.BCrypt;
public class Userservice {
    
 public static Userservice getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     Connection connection;
    private Users user;

    public Userservice() {
                this.connection= MyDB.getInstance().getConnection();}
                
                 public void add_user(Users u)  throws SQLException {
                    String Salt= BCrypt.gensalt(13);
                    String hash= BCrypt.hashpw(u.getPassword(), Salt);
        String requete="INSERT INTO `user`(`nom`, `prenom`, `email`, `password`, `telephone`, `adresse`, `roles`,`enabled`, `photo`, `pdf`,`username`,`username_canonical`,`email_canonical`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
         PreparedStatement ps= connection.prepareStatement(requete);
         
                ps.setString(1, u.getFirst_name());
         ps.setString(2, u.getLast_name());
         ps.setString(3, u.getEmail());
         ps.setString(4, hash);
         ps.setInt(5, u.getMobile_number());
         ps.setString(6, u.getAddress());
ps.setString(7, u.getGender());
        ps.setInt(8,u.getEnabled());
     ps.setString(9, u.getPhoto());
     ps.setString(10, u.getPdf());
         ps.setString(11, u.getLast_name());
          ps.setString(12, u.getLast_name());
          ps.setString(13,  u.getEmail());
         ps.executeUpdate();
        
    }
      public boolean chercheruser(String u, String p) throws SQLException
      {
          boolean existe=false;
          String req="Select * from user";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req);
          while(rs.next())
          {
          
              boolean vrai=BCrypt.checkpw(p, rs.getString("password"));
              if((u.equals(rs.getString("nom")))&&vrai)
                      {
                          existe=true;
                      }
          }
          return existe;
      }

    
public String avoirRole(String u,String p) throws SQLException
{ 
    String c="";
    String req="Select * from user";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req);
          while(rs.next())
          {
              boolean vrai=BCrypt.checkpw(p, rs.getString("password"));
              if((u.equals(rs.getString("nom")))&&vrai)
                      {
                          c=rs.getString("roles");
                      }
          }
          return c;
}
public Users cherchNom(String n) throws SQLException
{ 
    
    String req="Select * from user WHERE nom="+"'"+n+"';";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req);
          
          if(rs.next())
          {
              user=new Users(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("password"),rs.getInt("telephone"),rs.getString("adresse"),rs.getString("roles"));
}return user;             
                      
          
         
          
}
public void modifierUser(String n, Users u) throws SQLException
{
    String query="Select * from user where nom="+"'"+n+"';";
    String req="UPDATE `user` SET `nom`=?,`prenom`=?,`email`=?,`password`=?,`telephone`=?,`adresse`=? WHERE nom=?";
    PreparedStatement ps=connection.prepareStatement(req);
    Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(query);
         if(rs.next()) {
              ps.setString(1,u.getFirst_name());
              ps.setString(2,u.getLast_name());
              ps.setString(3,u.getEmail());
              ps.setString(4,u.getPassword());
              ps.setInt(5,u.getMobile_number());
              ps.setString(6,u.getAddress());
              ps.setString(7,n);
              ps.executeUpdate();
              System.out.println("modifié");
          }
          else
          {
                  System.out.println("non");
                  }
    
}
    public ResultSet getUser_infoByEmail(String email) {
        try {

            String request="SELECT * FROM `user` WHERE `email`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }
       
    public String set_confirmation_token(String email) throws NoSuchAlgorithmException {
        try {
            String token= MD5Hashing(email);
            String request="UPDATE `user` SET `confirmation_token`=? WHERE `email`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, token);
            statement.setString(2, email);
            statement.executeUpdate();
            return token;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
      public String MD5Hashing(String password) throws NoSuchAlgorithmException {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
             StringBuilder hexString = new StringBuilder();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);

            }
            return hexString.toString();

    }
public boolean set_Requested_password(String email) {
        try {
            String request="UPDATE `users` SET `password_requested_at`=CURRENT_TIMESTAMP WHERE `email`=?";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, email);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean check_email(String email) {

        try {
            String request="SELECT DISTINCT `email` FROM `user` WHERE `email`=?";
            PreparedStatement statement= connection.prepareStatement(request);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                if(result.getString("email").equals(email)){
                    return true;
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return false;
    }

    public Users get(Users m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Users m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  public List<Users> getAllUsers() throws SQLException
    {
        
        List<Users> user = new ArrayList<Users>();
        String req="select *  from user";
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
        Users p = new Users(rst.getInt("id")
                ,rst.getString("nom")
                , rst.getString("prenom")
                ,rst.getString("email")
                  ,rst.getString("password")

                ,rst.getInt("telephone")
                ,rst.getString("adresse")
                ,rst.getString("roles")

                ,rst.getInt("enabled")
                
        );
//First_name`=?,`Last_name`=?,`email`=?,`password`=?,`Mobile`=?,`Address`=
//ps.setString(1,u.getFirst_name());
//              ps.setString(2,u.getLast_name());
//              ps.setString(3,u.getEmail());
//              ps.setString(4,u.getPassword());
//              ps.setInt(5,u.getMobile_number());
//              ps.setString(6,u.getAddress());
//              ps.setString(7,n);
//              ps.executeUpdate();
        user.add(p);
        
    }
        return user;
 
    }
         public void SupprimerUser(int id) throws SQLException 
 {
        String re= "DELETE FROM `user` where id = "+id;
        Statement stm=connection.createStatement();
        stm.executeUpdate(re);				
}
           public boolean enable_user(int id) {
               boolean bool=true;
        try {
            
            String request="UPDATE `users` SET `enable`= ? WHERE `id`="+id;
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setBoolean(1,bool);
           
            statement.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, e);
            
        }
        return true;
           }
           public boolean disable_user(int id) {
                boolean bool=false;
        try {
            String request="UPDATE `user` SET `enable`= ? WHERE `id`="+id;
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setBoolean(1,bool);
        
            statement.executeUpdate();
            
        } catch (SQLException e) {
            Logger.getLogger(Userservice.class.getName()).log(Level.SEVERE, null, e);
            
        }
return false;
    }
           public void change(int id,int c) throws SQLException
           {
               String request="UPDATE `user` SET `enable`= ? WHERE `id`="+id;
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setInt(1,c);
            statement.executeUpdate();
           }
          
        /*  String req="select *  from users where First_name='"+s+"';";
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery(req);*/
           public Users chercherUser(String s) throws SQLException{
        Users u=null;
        Userservice us = new Userservice();
        for(Users u2 :us.getAllUsers())
        {
            if(u2.getFirst_name().equals(s))
                u=u2;
        }
        return u;
     }
             public Users chercherMail(String s) throws SQLException{
        Users u=null;
        Userservice us = new Userservice();
        for(Users u2 :us.getAllUsers())
        {
            if(u2.getEmail().equals(s))
                u=u2;
        }
        return u;
     }
             
             
             
             public String getIdSession() throws SQLException{
        String re= "select u.nom from user u inner join session s where u.id=s.id";;
        Statement stm= connection.createStatement();
        ResultSet rst=stm.executeQuery(re);
        
        while(rst.next()){
            return rst.getString("nom");
            
        }
	
         return "";
       }
             
               public Users chercherUser_id(int i) throws SQLException{
        Users u=null;
        Userservice us = new Userservice();
        for(Users u2 :us.getAllUsers())
        {
            if(u2.getUser_id()==i)
                u=u2;
        }
        return u;
          }
}
