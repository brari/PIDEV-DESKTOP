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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
/**
 * FXML Controller class
 *
 * @author brari
 */
public class DrawerviewController implements Initializable {

    @FXML
    private JFXButton reclamer_btn;
    @FXML
    private JFXButton historique_btn;
    @FXML
    private JFXButton reponse_btn;
    private JFXButton boite_btn;
    @FXML
    private JFXButton boite_btn1;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }

    @FXML
    private void reclamer(ActionEvent event) {
           try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./ajouterReclamation.fxml"));
                    Parent root;
                    root=loader.load();
                 reclamer_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void historique(ActionEvent event) {
            try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./afficherReclamation.fxml"));
                    Parent root;
                    root=loader.load();
                 historique_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reponses(ActionEvent event) {
           try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./afficherreponses.fxml"));
                    Parent root;
                    root=loader.load();
                 reponse_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void boite(ActionEvent event) {
           try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./envoyermessage.fxml"));
                    Parent root;
                    root=loader.load();
                 boite_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void retour(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilClientFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    retour_btn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ManiusersController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

}
