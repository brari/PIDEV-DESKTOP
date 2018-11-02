/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import pidev.service.Usersession;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DrawerviewClientMenuController implements Initializable {

    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton compte;
    @FXML
    private JFXButton idcom;
    @FXML
    private JFXButton btnrec;
    @FXML
    private JFXButton btneve;
    @FXML
    private JFXButton reclamation_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deconecter(MouseEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" ");
            alert.setHeaderText("Etes-vous sur que vous voulez se deconnecter  : " );
             Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Usersession ps=new Usersession();
                ps.SupprimerSession();
                //ps.SupprimerSession();
        
            
            try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./ConnexionFXML.fxml"));
                    Parent root;
                    root=loader.load();
                 deconnexion.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./maniusers.fxml"));
                    Parent root;
                    root=loader.load();
                 compte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void listcomd(ActionEvent event) {
           try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./AfficherComdProFXML.fxml"));
                    Parent root;
                    root=loader.load();
                 idcom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   

    @FXML
    private void listrec(ActionEvent event) {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficheRecette_1.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                     btnrec.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void listeve(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilClientEvenementFXML.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                     btneve.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
    }

    @FXML
    private void reclamation(ActionEvent event) {
        try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./mainspace.fxml"));
                    Parent root;
                    root=loader.load();
                 reclamation_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
