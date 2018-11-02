/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author hp
 */
public class Users {
   
    
      private int user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int mobile_number;
     private String address;
     private String gender;
     private String photo;
     private int enabled;
     private String pdf ;

    public Users(String first_name, String last_name, String email, int mobile_number, String address, int enabled) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.address = address;
        this.enabled = enabled;
    }

    public Users(int user_id, String first_name, String last_name, String email, String password, int mobile_number, String address) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
    }

    public Users( String first_name, String last_name, String email, String password, int mobile_number, String address, String gender, String photo, int enabled, String pdf) {
       
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
        this.photo = photo;
        this.enabled = enabled;
        this.pdf = pdf;
    }

    public Users(int user_id, String first_name, String last_name, String email, String password, int mobile_number, String address, String gender, String photo) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
        this.photo = photo;
    }

    public Users(String first_name, String last_name, String email, String password, int mobile_number, String address, String gender, int enabled) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
        this.enabled = enabled;
    }

    

//    public Users(String first_name, String last_name, String email, String password, int mobile_number, String address) {
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.email = email;
//        this.password = password;
//        this.mobile_number = mobile_number;
//        this.address = address;
//    }

    public Users(int user_id, String first_name, String last_name, String email, String password, int mobile_number, String address, String gender, int enabled) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
        this.enabled = enabled;
    }

    public Users(String first_name, String last_name, String email, String password, int mobile_number, String address, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
    }

    public Users() {
    }

    public Users(String first_name, String last_name, String email, String password, int mobile_number, String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
    }

    public Users(int user_id, String first_name, String last_name, String email, String password, int mobile_number, String address, String gender) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

   

  

   
      
 

   

  

    public int getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public String getAddress() {
        return address;
    }

  

  

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPseudo(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getLocked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setConnected(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public Users(String first_name, String last_name, String email, String password, int mobile_number, String address, String gender, String photo, int enabled) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.mobile_number = mobile_number;
        this.address = address;
        this.gender = gender;
        this.photo = photo;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Users{" + "user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", password=" + password + ", mobile_number=" + mobile_number + ", address=" + address + ", gender=" + gender + ", photo=" + photo + ", enabled=" + enabled + ", pdf=" + pdf + '}';
    }

    
    
}



    

