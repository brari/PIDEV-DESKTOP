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
import javafx.event.Event;
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
public class DrawerviewPatissierController implements Initializable {

    @FXML
    private JFXButton listePat;
    @FXML
    private JFXButton cmpt;
    @FXML
    private JFXButton btn;
    @FXML
    private JFXButton ListEven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherPat(ActionEvent event) {
        listePat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AfficherPatisserieFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    listePat.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void mntreC(ActionEvent event) {
        try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./maniusers.fxml"));
                    Parent root;
                    root=loader.load();
                 cmpt.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewPatissierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void quit(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ConnexionFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    btn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AfficherEven(ActionEvent event) {
        ListEven.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AfficheEvenementPatissierFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    ListEven.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
}
