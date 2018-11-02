/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import pidev.entities.Reclamation;
import pidev.service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tb_reclamation;
    private TableColumn<Reclamation,Integer> cl_id;
    @FXML
    private TableColumn<Reclamation, String> cl_type;
    @FXML
    private TableColumn<Reclamation, String> cl_contenu;
    @FXML
    private TableColumn<Reclamation, Date> cl_date;
    private TableColumn<Reclamation, String> cl_statut;
    private TableColumn<Reclamation, String> cl_reclamant;
    @FXML
    private JFXButton btn_afficher;
    @FXML
    private JFXButton btn_supprimer;
    
    private JFXTextField lb_supp;
    private JFXButton btn_valsupp;
    @FXML
    private TableColumn<Reclamation, String> cl_objet;
    @FXML
    private AnchorPane pane;
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
        Reclamation  r  = rs.chercherReclamation(ConnexionFXMLController.session.getUser_id());
            reclamations = (ArrayList<Reclamation>)rs.afficherhistorique(r.getId_reclamant());
               ObservableList obs = FXCollections.observableArrayList(reclamations);
               tb_reclamation.setItems(obs);            
             cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
                cl_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
                   cl_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                 cl_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
            
        });
           
    }    

    @FXML
    private void delete(ActionEvent event) throws SQLException{
            AnchorPane p=new AnchorPane();
        
         if (!tb_reclamation.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" ");
            alert.setHeaderText("Etes-vous sur");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ReclamationService ps=new ReclamationService();
                ps.supprimerreclamation(tb_reclamation.getSelectionModel().getSelectedItem().getId());
                
                try {      
                    p = FXMLLoader.load(getClass().getResource("afficherReclamation.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
               pane.getChildren().setAll(p);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un produit  ");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./mainspace.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    btn_retour.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }

    }
    
  
   

