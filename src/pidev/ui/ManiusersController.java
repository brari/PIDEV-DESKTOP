/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pidev.entities.Users;
import pidev.service.Userservice;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ManiusersController implements Initializable {

    @FXML
    private TextField First_name;
    @FXML
    private TextField Last_name;
    @FXML
    private TextField Address;
    @FXML
    private TextField Mobile_num;
    @FXML
    private TextField Email;
    @FXML
    private Button Update_password;
    @FXML
    private PasswordField NewPassword;
    @FXML
    private PasswordField OldPassword;
    @FXML
    private PasswordField Confirm_password;
    @FXML
    private Label oldpasswordlabel;
    @FXML
    private Label confirmpasswordlabel;
    
    @FXML
    private Button retourA;
    @FXML
    private Button charger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Update_password(MouseEvent event) {
        String c=First_name.getText();
        Users u=new Users(First_name.getText(),Last_name.getText(),Email.getText(),NewPassword.getText(),Integer.parseInt(Mobile_num.getText()),Address.getText());
        Userservice us = new Userservice();
        try {
            us.modifierUser(c,u);
        } catch (SQLException ex) {
            Logger.getLogger(ManiusersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void recherche(MouseEvent event) {
//        Userservice us=new Userservice();
//        Users u;
//        try {
//            u=us.cherchNom(ch.getText());
//            First_name.setText(u.getFirst_name());
//        Last_name.setText(u.getLast_name());
//        Address.setText(u.getAddress());
//        Mobile_num.setText(Integer.toString(u.getMobile_number()));
//        OldPassword.setText(u.getPassword());
//        Email.setText(u.getEmail());
//        } catch (SQLException ex) {
//            Logger.getLogger(ManiusersController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }

    @FXML
    private void saveMod(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilClientFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    retourA.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ManiusersController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void chargerI(MouseEvent event) {
        Userservice us=new Userservice();
        Users u;
        try {
            //u=us.cherchNom(ch.getText());
            u=us.cherchNom(us.getIdSession());
            First_name.setText(ConnexionFXMLController.session.getFirst_name());
            
            Address.setText(ConnexionFXMLController.session.getAddress());
            
            //First_name.setText(u.getFirst_name());
        Last_name.setText(u.getLast_name());
        //Address.setText(u.getAddress());
        Mobile_num.setText(Integer.toString(u.getMobile_number()));
        OldPassword.setText(u.getPassword());
        Email.setText(u.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(ManiusersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
