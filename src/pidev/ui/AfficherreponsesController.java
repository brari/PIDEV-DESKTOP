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
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.service.ReclamationService;
import pidev.entities.Reclamation;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class AfficherreponsesController implements Initializable {

    @FXML
    private TableView<Reclamation> table_reponses;

    @FXML
    private JFXButton btn_afficher;
    
    @FXML
    private TableColumn<Reclamation,Date> cl_date;
    @FXML
    private TextField tx_reponse;
    @FXML
    private JFXButton btn_afficher1;
    @FXML
    private TableColumn<Reclamation,String> cl_objet;
    @FXML
      private TableColumn<Reclamation,String> cl_decision;
    @FXML
    private JFXButton btn_retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           btn_afficher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            ReclamationService rs = new ReclamationService();
        ArrayList<Reclamation> reclamations ;
        try {
            reclamations = (ArrayList<Reclamation>)rs.afficherhistorique(1);
               ObservableList obs = FXCollections.observableArrayList(reclamations);
              table_reponses.setItems(obs);
               cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 cl_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
                cl_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
        });
    }    

    @FXML
    private void afficherdetail(ActionEvent event) throws SQLException {
            
         if (!table_reponses.getSelectionModel().isEmpty()) {
            
              int  id=table_reponses.getSelectionModel().getSelectedItem().getId();
              ReclamationService rs=new ReclamationService();
                Reclamation r=rs.chercherReclamation(id);
             tx_reponse.setText(r.getReponse());
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un produit  ");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("./mainspace.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    btn_retour.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherreponsesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
