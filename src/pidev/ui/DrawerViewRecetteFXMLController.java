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
 * @author hp
 */
public class DrawerViewRecetteFXMLController implements Initializable {

    @FXML
    private JFXButton btn_affiche_recette;
    @FXML
    private JFXButton btn_ajout_recette;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Afficherrecette(ActionEvent event) {
     FXMLLoader loder =new FXMLLoader(getClass().getResource("./AfficheRecette.fxml"));
                    Parent root = null;
        try {
            root=loder.load();
        } catch (IOException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn_affiche_recette.getScene().setRoot(root);    
        
        
        
    }

    @FXML
    private void AjouterRecette(ActionEvent event) {
       FXMLLoader loder =new FXMLLoader(getClass().getResource("./AddRecette.fxml"));
                    Parent root = null;
        try {
            root=loder.load();
        } catch (IOException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn_ajout_recette.getScene().setRoot(root);  
        
        
    }
    
}
