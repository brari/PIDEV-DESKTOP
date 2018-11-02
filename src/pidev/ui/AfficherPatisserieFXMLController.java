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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import pidev.entities.CellPersonalise;
import pidev.entities.Patisserie;
import pidev.service.PatisserieService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherPatisserieFXMLController implements Initializable {

    @FXML
    private ComboBox<String> pa_combo;
    @FXML
    private TextField pa_recherche;
    @FXML
    private Button pa_rechercher;
    @FXML
    private Button pa_retourpat;
    @FXML
    private ListView<Patisserie> listview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PatisserieService ps = new PatisserieService();
        pa_combo.getItems().addAll("nom", "adresse");
        try {
            ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherPatisseries();
            ObservableList items = FXCollections.observableArrayList(patisseries);
            listview.setCellFactory((param) -> new CellPersonalise());
            listview.setItems(items);

        } catch (SQLException ex) {
            Logger.getLogger(AfficherPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Rechercher(ActionEvent event) {
         pa_rechercher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String filtre = pa_combo.getValue();
                    String valeur = pa_recherche.getText();
                    PatisserieService ps = new PatisserieService();
                    int val=ConnexionFXMLController.session.getUser_id();
                    try {
                        ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.rechercherMesPatisserie(filtre, valeur,val);
                        ObservableList items = FXCollections.observableArrayList(patisseries);
                        listview.setCellFactory((param) -> new CellPersonalise());
                        listview.setItems(items);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficherPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NullPointerException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Choisissez un filtre");
                    alert.showAndWait();

                }

            }
        });

    }

    @FXML
    private void refresh(ActionEvent event) {
        PatisserieService ps = new PatisserieService();
        try {
            ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherPatisseries();
            ObservableList items=FXCollections.observableArrayList(patisseries);
            listview.setCellFactory((param) -> new CellPersonalise());
            listview.setItems(items);

        } catch (SQLException ex) {
            Logger.getLogger(AfficherPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void RetourAffiche(ActionEvent event) {
        pa_retourpat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilPatissierFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    pa_retourpat.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    
}
