/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXDrawer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.input.MouseEvent;
import javafx.animation.FadeTransition;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author brari
 */
public class MainspaceController implements Initializable {
   VBox sidePane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label panel;
     AnchorPane ajouterReclamation, afficherReclamation, afficherReponse,envoyermessage;
    @FXML
    private AnchorPane anchorPane;
    
   /*@FXML
   public void showDrawer(MouseEvent me) {
        if (!drawer.isOpening()) {
            drawer.open();
        }
        drawer.isOpening();
    }
    public void hideDrawer(MouseEvent me) {
        drawer.close();
    }*/
   public void setNode(Node node) {
        /*AudioClip notifyMe = new AudioClip((getClass().getResource("Notify.wav")).toString());
        notifyMe.play();
         */
 /*
        tray = new TrayNotification();
        tray.setTitle("Congratulations");
        tray.setMessage("You have opened the form");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1500));
         */
   //     anchorPane.getChildren().clear();
       // anchorPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          panel.setText("");
        
        try {
            sidePane = FXMLLoader.load(getClass().getResource("./drawerview.fxml"));
                 drawer.setSidePane(sidePane);
         
        
            }
                 catch (IOException ex) {
           Logger.getLogger(MainspaceController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        
                            }

  

    @FXML
    private void hideDrawer(MouseEvent event) {
          drawer.close();
          
       
    }

    @FXML
    private void showDrawer(MouseEvent event) {
    
        if (!drawer.isShown()) {
            drawer.open();
        }
        drawer.isShown();
    }
    
    
}
