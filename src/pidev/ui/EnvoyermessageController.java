/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.service.MessageService;
import pidev.service.ReclamationService;
import pidev.entities.Message;
import pidev.entities.Reclamation;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class EnvoyermessageController implements Initializable {

    @FXML
    private TableView<Message> table_message;
    @FXML
    private JFXTextField tx_message;
    @FXML
    private TableColumn<Message, String> cl_nom;
    @FXML
    private TableColumn<Message, String> cl_prenom;
    @FXML
    private TableColumn<Message, String> cl_message;
    @FXML
    private ImageView btn_envoyer;
    @FXML
    private TableView<Reclamation> table_reclamation;
    @FXML
    private TableColumn<Reclamation, String> cl_type;
    @FXML
    private TableColumn<Reclamation, String> cl_objet;
    @FXML
    private TableColumn<Reclamation, String> cl_contenu;
    @FXML
    private TableColumn<Reclamation, Date> cl_date;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.cl_type
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ReclamationService rs = new ReclamationService();
           
        ArrayList<Reclamation> reclamations ;
        try {
            reclamations = (ArrayList<Reclamation>)rs.afficherhistorique(1);
               ObservableList obs = FXCollections.observableArrayList(reclamations);
              table_reclamation.setItems(obs);
              cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
               cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 cl_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
                cl_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void affichermessages(MouseEvent event) {
           MessageService ms = new MessageService();
        ArrayList<Message> messages ;
        try {
            messages = (ArrayList<Message>)ms.afficher(table_reclamation.getSelectionModel().getSelectedItem().getId());
               ObservableList obs = FXCollections.observableArrayList(messages);
              table_message.setItems(obs);
              cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
               cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                 cl_message.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
            
}
    @FXML
     public void ajoutermessages(MouseEvent event) {
           MessageService ms = new MessageService();
              ArrayList<Message> messages ;
         try {
                    ms.ajouterMessage(tx_message.getText(), table_reclamation.getSelectionModel().getSelectedItem().getId());
                     messages = (ArrayList<Message>)ms.afficher(table_reclamation.getSelectionModel().getSelectedItem().getId());
                     tx_message.setText("");
               ObservableList obs = FXCollections.observableArrayList(messages);
              table_message.setItems(obs);
              cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
               cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                 cl_message.setCellValueFactory(new PropertyValueFactory<>("contenu"));
                } catch (SQLException ex) {
                    Logger.getLogger(EnvoyermessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void retour(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("./mainspace.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    retour_btn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(EnvoyermessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
                }

   
