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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import pidev.entities.CellPersonalise;
import pidev.entities.Patisserie;
import pidev.entities.Produit;
import pidev.service.PatisserieService;
import pidev.service.ProduitService;
import pidev.service.RatingService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AccueilClientFXMLController implements Initializable {

    @FXML
    private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    VBox sidepane;

    @FXML
    private ListView<Patisserie> listview;

    Patisserie pat;
    @FXML
    private ComboBox<String> pa_combo;
    @FXML
    private TextField pa_recherche;
    @FXML
    private Button pa_rechercher;
    @FXML
    private Rating raiting;
    @FXML
    private Label affiche;
    @FXML
    private Button pa_eval;
    @FXML
    private Button listeProd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pa_combo.getItems().addAll("nom", "adresse");
        drawer.setOpacity(0f);

        try {
            sidepane = FXMLLoader.load(getClass().getResource("./drawerviewClientMenu.fxml"));
            drawer.setSidePane(sidepane);
        } catch (IOException ex) {
            Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //liste Patisseries
        PatisserieService ps = new PatisserieService();

        try {
            ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherPatisseries();
            ObservableList items = FXCollections.observableArrayList(patisseries);
            listview.setCellFactory((param) -> new CellPersonalise());
            listview.setItems(items);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void hidedrawer(MouseEvent event) {
        drawer.close();
        drawer.setOpacity(0f);
    }

    @FXML
    private void showdrawer(MouseEvent event) {
        drawer.setOpacity(1f);
        drawer.open();
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
                    try {
                        ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.rechercherPatisserie(filtre, valeur);
                        ObservableList items = FXCollections.observableArrayList(patisseries);
                        listview.setCellFactory((param) -> new CellPersonalise());
                        listview.setItems(items);
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
            ObservableList items = FXCollections.observableArrayList(patisseries);
            listview.setCellFactory((param) -> new CellPersonalise());
            listview.setItems(items);
            affiche.setText("");
            pa_eval.setVisible(false);
            raiting.setVisible(false);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Double nbvote;

    @FXML
    private void calculRating(ActionEvent event) {
        pa_eval.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RatingService rs= new RatingService();
                PatisserieService ps = new PatisserieService();
                int index = listview.getSelectionModel().getSelectedIndex();
                if (listview.getSelectionModel().isSelected(index)) {
                    pat = (Patisserie) listview.getSelectionModel().getSelectedItem();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Evaluation");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous allez donner la note " + raiting.getRating() + " à la patisserie"
                            + pat.getNom());

                    Optional<ButtonType> resultat = alert.showAndWait();
                    if (resultat.get() == ButtonType.OK) {

                        try {
                            try {
                        if(rs.returnId(ConnexionFXMLController.session.getUser_id(),pat.getIdp())!=0){
                            rs.updateNote(raiting.getRating(), ConnexionFXMLController.session.getUser_id());
                        }
                        else{
                            pidev.entities.Rating r=new pidev.entities.Rating (ConnexionFXMLController.session.getUser_id(),
                                    pat.getIdp(),raiting.getRating());
                            rs.AjouterNote(r);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            Double rat = (rs.TotalNote(pat.getIdp()) / rs.NombreNote(pat.getIdp()));
                            System.out.println(rs.TotalNote(pat.getIdp()));
                            System.out.println(rat);
                            ps.updateRating(rat, pat.getIdp());
                            
                            affiche.setText("");
                            
                            raiting.setRating(1);
                            
                            ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherPatisseries();
                            ObservableList items = FXCollections.observableArrayList(patisseries);
                            listview.setCellFactory((param) -> new CellPersonalise());
                            listview.setItems(items);

                        } catch (SQLException ex) {
                            Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }
            }
        });

    }

    @FXML
    private void evaluer(MouseEvent event) {
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index = listview.getSelectionModel().getSelectedIndex();
                if (listview.getSelectionModel().isSelected(index)) {
                    //System.out.println(ConnexionFXMLController.session);
                    pat = (Patisserie) listview.getSelectionModel().getSelectedItem();
                   
                    RatingService rs=new RatingService();
                    try {
                        if
                            (rs.returnId(ConnexionFXMLController.session.getUser_id(),pat.getIdp())!=0){
                            Double note=rs.rendNote(ConnexionFXMLController.session.getUser_id());
                            
                            raiting.setRating(note);
                        }
                        else
                            raiting.setRating(1);
                            
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         
                    affiche.setText(pat.getNom());

                }

            }
        });
    }
static int idPa;
    @FXML
    private void listeProd(ActionEvent event) {
        int index = listview.getSelectionModel().getSelectedIndex();
        if (listview.getSelectionModel().isSelected(index)) {
            pat = (Patisserie) listview.getSelectionModel().getSelectedItem();
           idPa=pat.getIdp();
               FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficherProdFXML.fxml"));
                Parent root; 
                try {
                    root=loader.load();
                    listeProd.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherComdProFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } 

        }
    }

    }
