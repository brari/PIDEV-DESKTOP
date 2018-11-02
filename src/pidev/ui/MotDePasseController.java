/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidev.entities.Users;
import pidev.service.SendingMail;
import pidev.service.Userservice;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MotDePasseController implements Initializable {

    @FXML
    private JFXTextField mail;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(MouseEvent event) throws SQLException {
          Userservice us=new Userservice();
        Users u=us.chercherMail(mail.getText());
        SendingMail sm= new SendingMail();
        sm.envoyer(mail.getText(),u.getPassword());
    }

    @FXML
    private void retour(MouseEvent event) {
    
     try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./ConnexionFXML.fxml"));
                    Parent root;
                    root=loader.load();
                 retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MotDePasseController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
}
