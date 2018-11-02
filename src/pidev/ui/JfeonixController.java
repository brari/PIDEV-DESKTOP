/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXDrawer;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Insaf-Nefzi
 */
public class JfeonixController implements Initializable {

    private MediaView mediaplayer;
private static final String Media_url="vid.mp4"; 
    @FXML
    private MediaView media;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button btnretvid;
    @FXML
    private Button btnwebv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            mediaPlayer= new MediaPlayer( new Media (this.getClass().getResource(Media_url).toExternalForm()));
       mediaPlayer.setAutoPlay(true);
       media.setMediaPlayer(mediaPlayer);
      
    }    

    @FXML
    private void videoplayer(MouseEvent event) {
        
         mediaPlayer= new MediaPlayer( new Media (this.getClass().getResource(Media_url).toExternalForm()));
       mediaPlayer.setAutoPlay(false);
       media.setMediaPlayer(mediaPlayer);
       
        
    }

    @FXML
    private void btnretvid(ActionEvent event) {
       
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjouterFXML.fxml"));
                Parent root; 
                try {
                    root=loader.load();
                    btnretvid.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherComdProFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } 
 
    }

    @FXML
    private void btnweb(ActionEvent event) {
                    
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./webViewFXML.fxml"));
                Parent root; 
                try {
                    root=loader.load();
                    btnretvid.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherComdProFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    
    
}
