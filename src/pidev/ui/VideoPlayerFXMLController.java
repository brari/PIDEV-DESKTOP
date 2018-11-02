/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class VideoPlayerFXMLController implements Initializable {

    @FXML
    private MediaView E_media_view;
    @FXML
    private Button E_play_video;
    @FXML
    private Button E_stop_video;
    MediaPlayer mediaplayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String VUrl="file:/C:/Users/DELL/Desktop/video.mp4";
        Media media=new Media(VUrl);
        mediaplayer=new MediaPlayer(media);
        E_media_view.setFitHeight(1000);
        E_media_view.setFitWidth(1200);
        E_media_view.setMediaPlayer(mediaplayer);
    }    

    @FXML
    private void play_video(ActionEvent event) {
        if(mediaplayer.getStatus()==PLAYING)
        {
            mediaplayer.stop(); 
            mediaplayer.play();
        }
        else 
        {
            mediaplayer.play();
        }
        
    }

    @FXML
    private void stop_video(ActionEvent event) {
         mediaplayer.stop();
    }
    
}
