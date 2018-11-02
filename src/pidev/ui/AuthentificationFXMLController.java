/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AuthentificationFXMLController implements Initializable {

    @FXML
    private Button btn_decouvrir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void afficherConnexion(MouseEvent event) {
          try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./ConnexionFXML.fxml"));
                    Parent root;
                    root=loader.load();
                 btn_decouvrir.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
