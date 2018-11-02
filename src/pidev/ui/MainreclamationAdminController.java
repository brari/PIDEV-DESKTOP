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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class MainreclamationAdminController implements Initializable {
VBox Sidepane;
    @FXML
    private JFXDrawer drawer;

   
    private AnchorPane traiterreclamation;
    private AnchorPane historiquereclamation,statistiquereclamation;
    @FXML
    private Label label;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             label.setText("");
                    
        
    try {  
        Sidepane = FXMLLoader.load(getClass().getResource("./drawerview.fxml"));
    } catch (IOException ex) {
        Logger.getLogger(MainreclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
            drawer.setSidePane(Sidepane);
  
 
     
        
                
    }    
    @FXML
    private void hidedrawer(MouseEvent event) {
          drawer.close();
        
    }

    @FXML
    private void showdrawer(MouseEvent event) {

          if (!drawer.isShown()) {
            drawer.open();
        }
        drawer.isShown();
    }
    
}
