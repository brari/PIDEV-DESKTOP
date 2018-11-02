/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.service.EnvoyerEmail;
import pidev.service.ReclamationService;
import pidev.entities.Reclamation;
import pidev.entities.Users;
import pidev.service.Userservice;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class TraiterReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> table_rec;
    @FXML
    private TableColumn<Reclamation, Integer> cl_id_rec;
    @FXML
    private TableColumn<Reclamation, Date> cl_date;
    @FXML
    private TableColumn<Reclamation, String> cl_contenu;
    @FXML
    private TableColumn<Reclamation, String > cl_statut;
    @FXML
    private TableColumn<Reclamation, String> cl_objet;
    @FXML
    private TableColumn<Reclamation, String> cl_decision;
    @FXML
    private TableColumn<Reclamation, String> cl_id_reclamant;
    @FXML
    private TableColumn<Reclamation, String> cl_type;
    @FXML
    private JFXTextArea tx_reponse;
    private JFXTextField tx_decision;
    @FXML
    private ImageView btn_envoie;
    @FXML
    private JFXComboBox<String> cb_decision;
   private String st[]={"favorable","défavorable","partiellement favorable"};
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ReclamationService rs = new ReclamationService();
           cb_decision.getItems().addAll(st);
        ArrayList<Reclamation> reclamations ;
        try {
            reclamations = (ArrayList<Reclamation>)rs.getAllReclamation();
               ObservableList obs = FXCollections.observableArrayList(reclamations);
              table_rec.setItems(obs);
              cl_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
              cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
               cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 cl_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
                cl_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
                cl_statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
                cl_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
                cl_id_reclamant.setCellValueFactory(new PropertyValueFactory<>("id_reclamant"));
                
        } catch (SQLException ex) {
            Logger.getLogger(TraiterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
        // TODO
        // TODO
        // TODO
    }    

    @FXML
    private void envoyerEmail(MouseEvent event) throws SQLException {
        ReclamationService rs = new ReclamationService();
        EnvoyerEmail mail = new EnvoyerEmail();
        rs.traiterreclamation(table_rec.getSelectionModel().getSelectedItem().getId(), st[cb_decision.getSelectionModel().getSelectedIndex()],tx_reponse.getText() );
        Userservice us=new Userservice();
        Users u=us.chercherUser_id(table_rec.getSelectionModel().getSelectedItem().getId_reclamant());
            Users v=us.chercherUser_id(table_rec.getSelectionModel().getSelectedItem().getId_concerne());
        mail.envoyer(st[cb_decision.getSelectionModel().getSelectedIndex()], tx_reponse.getText(),u.getEmail());
         mail.envoyer(st[cb_decision.getSelectionModel().getSelectedIndex()], tx_reponse.getText(),v.getEmail());
        tx_reponse.setText("");
           ArrayList<Reclamation> reclamations ;
        try {
            reclamations = (ArrayList<Reclamation>)rs.getAllReclamation();
               ObservableList obs = FXCollections.observableArrayList(reclamations);
              table_rec.setItems(obs);
              cl_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
              cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
               cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 cl_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
                cl_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
                cl_statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
                cl_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
                cl_id_reclamant.setCellValueFactory(new PropertyValueFactory<>("reclamant"));
                
        } catch (SQLException ex) {
            Logger.getLogger(TraiterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
