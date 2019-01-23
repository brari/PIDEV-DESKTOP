/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidev.service.MessageService;
import pidev.service.ReclamationService;
import pidev.entities.Message;
import pidev.entities.Reclamation;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class HistoriqueReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> table_rec;
    @FXML
    private TableColumn<Reclamation, Integer> cl_id;
    @FXML
    private TableColumn<Reclamation, Date> cl_date;
    @FXML
    private TableColumn<Reclamation, String> cl_type;
    @FXML
    private TableColumn<Reclamation, String> cl_objet;
    @FXML
    private TableColumn<Reclamation, String> cl_statut;
    @FXML
    private TableColumn<Reclamation, String> cl_decision;
    @FXML
    private TableColumn<Reclamation, String> cl_reponse;
    @FXML
    private TableColumn<Reclamation, Integer> cl_id_reclamant;
    @FXML
    private TableView<Message> tacle_message;
    @FXML
    private TableColumn<Message, String> cl_nom;
    @FXML
    private TableColumn<Message, String> cl_prenom;
    @FXML
    private TableColumn<Message, String> cl_contenu;
    @FXML
    private AnchorPane panefiche;
    @FXML
    private Label lb_id;
    @FXML
    private Label lb_id1;
    @FXML
    private Label lb_type;
    @FXML
    private Label lb_id_reclamant;
    @FXML
    private Label lb_id_concerne;
    @FXML
    private Label lb_objet;
    @FXML
    private Label lb_contenu;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
               panefiche.setVisible(false);
        ArrayList<Reclamation> reclamations ;
        try {
            reclamations = (ArrayList<Reclamation>)rs.getAllReclamation();
               ObservableList obs = FXCollections.observableArrayList(reclamations);
              table_rec.setItems(obs);
              cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
               cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 cl_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
              cl_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
                      cl_statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
                      cl_id_reclamant.setCellValueFactory(new PropertyValueFactory<>("if_reclamant"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void affichemessages(MouseEvent event) {
                MessageService ms = new MessageService();
        ArrayList<Message> messages ;
        try {
            messages = (ArrayList<Message>)ms.afficher(table_rec.getSelectionModel().getSelectedItem().getId());
               ObservableList obs = FXCollections.observableArrayList(messages);
              tacle_message.setItems(obs);
              cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
               cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                 cl_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherfiche(MouseEvent event) {
        panefiche.setVisible(true);
         lb_id.setText(Integer.toString(table_rec.getSelectionModel().getSelectedItem().getId()));
         lb_id_reclamant.setText(table_rec.getSelectionModel().getSelectedItem().getReclamant());
         lb_type.setText(table_rec.getSelectionModel().getSelectedItem().getType());
         lb_objet.setText(table_rec.getSelectionModel().getSelectedItem().getObjet());
         lb_contenu.setText(table_rec.getSelectionModel().getSelectedItem().getContenu());
        
    }

    @FXML
    private void hideFiche(MouseEvent event) {
            panefiche.setVisible(false);
    }

    @FXML
    private void Retour(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("./MainreclamationAdmin.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    retour_btn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
