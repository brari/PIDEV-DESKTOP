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
import pidev.ui.AccueilPatissierFXMLController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminDrawerviewController1 implements Initializable {

    @FXML
    private JFXButton Traitementbtn;
    @FXML
    private JFXButton Historiquebtn;
    @FXML
    private JFXButton Statistiquebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afichetrai(ActionEvent event) {
        Traitementbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./traiterReclamation.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    Traitementbtn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AdminDrawerviewController1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void afichehisto(ActionEvent event) {
        Historiquebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./historiqueReclamation.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    Historiquebtn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AdminDrawerviewController1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void aficheStat(ActionEvent event) {
//        Statistiquebtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AfficherPatisserieFXML.fxml"));
//                Parent root;
//                try {
//                    root = loader.load();
//                    Statistiquebtn.getScene().setRoot(root);
//                } catch (IOException ex) {
//                    Logger.getLogger(AdminDrawerviewController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
    }
    
}
