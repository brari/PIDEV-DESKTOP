/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DrawerAcceuilAdminController implements Initializable {

    @FXML
    private JFXButton gestionR;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton gr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherR(ActionEvent event) {
        deconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./MainreclamationAdmin.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    deconnexion.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(DrawerAcceuilAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void DeconnexionA(ActionEvent event) {
        deconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./ConnexionFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    deconnexion.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(DrawerAcceuilAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void gererr(ActionEvent event) {
    }
    
}
