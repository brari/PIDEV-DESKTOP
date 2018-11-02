/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entities.Participants;
import pidev.service.Participantservice;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Separticiper_evenController implements Initializable {

    @FXML
    private TableColumn<Participants, Integer> E_col_idp;
    @FXML
    private TableColumn<Participants, String> E_col_nomE;
    @FXML
    private TableView<Participants> E_afficher_parti;
    @FXML
    private Label label1;
    @FXML
    private Button E_part_buuton;
    @FXML
    private Button E_retour_partip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void E_aff_participants(ActionEvent event) {
        Participantservice ps= new Participantservice ();
        ArrayList<Participants> participant;
        try {
            participant = (ArrayList<Participants>)ps.afficher_Participation();
            ObservableList obs = FXCollections.observableArrayList(participant);
            E_afficher_parti.setItems(obs);
            E_col_idp.setCellValueFactory(new PropertyValueFactory<>("id_participation"));
            E_col_nomE.setCellValueFactory(new PropertyValueFactory<>("nom_E"));
           
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(Separticiper_evenController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void retour_partip(ActionEvent event) {
          E_retour_partip.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)        
            {
                    
                
                  
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficheMesEvenementFXML.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                      E_retour_partip.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                
           }
    });    
    }
    
}
