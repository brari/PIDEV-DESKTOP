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
public class Userservice {
    
 public static Userservice getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     Connection connection;
    private Users user;

    public Userservice() {
                this.connection= MyDB.getInstance().getConnection();}
                
                 public void add_user(Users u)throws SQLException {
       
        String requete="INSERT INTO `users`(`First_name`, `Last_name`, `email`, `password`, `Mobile`, `Address`, `gender`,`enable`, `photo`, `pdf`) VALUES (?,?,?,?,?,?,?,?,?,?);";
         PreparedStatement ps= connection.prepareStatement(requete);
         
                ps.setString(1, u.getFirst_name());
         ps.setString(2, u.getLast_name());
         ps.setString(3, u.getEmail());
         ps.setString(4, u.getPassword());
         ps.setInt(5, u.getMobile_number());
         ps.setString(6, u.getAddress());
ps.setString(7, u.getGender());
        ps.setInt(8,u.getEnabled());
     ps.setString(9, u.getPhoto());
     ps.setString(10, u.getPdf());
         ps.executeUpdate();
        
    }
      public boolean chercheruser(String u, String p) throws SQLException
      {
          boolean existe=false;
          String req="Select * from users";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req);
          while(rs.next())
          {
              if((u.equals(rs.getString("first_name")))&&(p.equals(rs.getString("password"))))
                      {
                          existe=true;
                      }
          }
          return existe;
      }

    
public String avoirRole(String u,String p) throws SQLException
{ 
    String c="";
    String req="Select * from users";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req);
          while(rs.next())
          {
              if((u.equals(rs.getString("first_name")))&&(p.equals(rs.getString("password"))))
                      {
                          c=rs.getString("gender");
                      }
          }
          return c;
}
public Users cherchNom(String n) throws SQLException
{ 
    
    String req="Select * from users WHERE First_name="+"'"+n+"';";
          Statement stm=connection.createStatement();
          ResultSet rs=stm.executeQuery(req);
          
          if(rs.next())
          {
              user=new Users(rs.getInt("idU"),rs.getString("First_name"),rs.getString("Last_name"),rs.getString("email"),rs.getString("password"),rs.getInt("Mobile"),rs.getString("Address"),rs.getString("gender"));
}return user;             
                      
          
         
          
}
public void modifierUser(String n, Users u) throws SQLException
{
    String query="Select * from users where First_name="+"'"+n+"';";
    String req="UPDATE `users` SET `First_name`=?,`Last_name`=?,`email`=?,`password`=?,`Mobile`=?,`Address`=? WHERE First_name=?";
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

            String request="SELECT * FROM `users` WHERE `email`=?";
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
            String request="UPDATE `users` SET `confirmation_token`=? WHERE `email`=?";
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
            String request="SELECT DISTINCT `email` FROM `users` WHERE `email`=?";
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
        String req="select *  from users";
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while(rst.next()){
        Users p = new Users(rst.getInt("IdU")
                ,rst.getString("First_name")
                , rst.getString("Last_name")
                ,rst.getString("email")
                  ,rst.getString("password")

                ,rst.getInt("Mobile")
                ,rst.getString("Address")
                ,rst.getString("gender")

                ,rst.getInt("enable")
                
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
        String re= "DELETE FROM `users` where idU = "+id;
        Statement stm=connection.createStatement();
        stm.executeUpdate(re);				
}
           public boolean enable_user(int id) {
               boolean bool=true;
        try {
            
            String request="UPDATE `users` SET `enable`= ? WHERE `idU`="+id;
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
            String request="UPDATE `users` SET `enable`= ? WHERE `idU`="+id;
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
               String request="UPDATE `users` SET `enable`= ? WHERE `idU`="+id;
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
        String re= "select u.First_name from users u inner join session s where u.idU=s.id";;
        Statement stm= connection.createStatement();
        ResultSet rst=stm.executeQuery(re);
        
        while(rst.next()){
            return rst.getString("First_name");
            
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
