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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Insaf-Nefzi
 */
public class WebViewFXMLController implements Initializable {

    @FXML
    private WebView webvi;
    @FXML
    private Button retouvid;
    @FXML
    private Button webAffiche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine engine=webvi.getEngine();
            
            engine.load("https://www.youtube.com/watch?v=PX0M7-01C_Y");
    }    

    @FXML
    private void webvi(MouseEvent event) {
    }

    @FXML
    private void retouvid(ActionEvent event) {
         FXMLLoader loader= new FXMLLoader ( getClass().getResource("./jfeonix.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      retouvid.getScene().setRoot(root);
                    } catch (IOException ex) {
                      //  Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   // }//pour que le bouton ouvre une autre interface
    }
    }

    @FXML
    private void webAffiche(ActionEvent event) {
          FXMLLoader loader= new FXMLLoader ( getClass().getResource("./AfficherProdFXML.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      webAffiche.getScene().setRoot(root);
                    } catch (IOException ex) {
                      //  Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   // }//pour que le bouton ouvre une autre interface
    }
    }
    
}
