/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXDrawer;
import java.io.File;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import pidev.entities.CellPersonalise;
import pidev.entities.Patisserie;
import pidev.service.PatisserieService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AccueilPatissierFXMLController implements Initializable {
VBox sidepane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ListView<Patisserie> listview;
    @FXML
    private TextField pa_nommodifie;
    @FXML
    private TextField pa_adressemodifie;
    @FXML
    private TextField pa_contactmodifie;
    @FXML
    private TextField pa_mailmodifie;
    @FXML
    private TextField pa_fichierchoisi;
    @FXML
    private Button pa_browse;
    @FXML
    private ImageView pa_icone;
    @FXML
    private ComboBox<String> pa_combo;
    @FXML
    private TextField pa_recherche;
    @FXML
    private Button pa_rechercher;
    @FXML
    private Button pa_saveChanged;
    @FXML
    private Button pa_aficheajout;
    @FXML
    private Button pa_supprimer;
    @FXML
    private Button pa_modifier;
    
String path = "";   Patisserie pat; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pa_combo.getItems().addAll("nom", "adresse");
         drawer.setOpacity(0f);
        try {
        sidepane=FXMLLoader.load(getClass().getResource("./drawerviewPatissier.fxml"));
        drawer.setSidePane(sidepane);
    } catch (IOException ex) {
        Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        PatisserieService ps = new PatisserieService();
        
        try {
            ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherMesPatisseries(ConnexionFXMLController.session.getUser_id());
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
        if(!drawer.isShown())
        drawer.open();
        else 
            drawer.open();
    }

    @FXML
    private void FileSelected(ActionEvent event) {
        pa_browse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                //ouvrir une boite de dialogue pour choisir le fichier
                final FileChooser dialog = new FileChooser();
                //préciser l'extension du fichier à choisir, ici les images
                FileChooser.ExtensionFilter extensionFichier;
                extensionFichier = new FileChooser.ExtensionFilter("Fichiers Image", "*.jpg", "*.jpeg", "*.png");
                //appliquer le filtre choisi a la boite de dialogue
                dialog.getExtensionFilters().setAll(extensionFichier);
                //affiche la boite de dialogue
                final File fichier = dialog.showOpenDialog(pa_browse.getScene().getWindow());

                path = fichier.getAbsolutePath();

                pa_fichierchoisi.setText(fichier.getName());

                //récuperer l'image choisie
                File file = new File(path);
                Image image = new Image(file.toURI().toString());
                pa_icone.setImage(image);
            }
        });
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
                        ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.rechercherMesPatisserie(filtre, valeur,ConnexionFXMLController.session.getUser_id());
                        ObservableList items = FXCollections.observableArrayList(patisseries);
                        listview.setCellFactory((param) -> new CellPersonalise());
                        listview.setItems(items);
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
            ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherMesPatisseries(ConnexionFXMLController.session.getUser_id());
            ObservableList items=FXCollections.observableArrayList(patisseries);
            listview.setCellFactory((param) -> new CellPersonalise());
            listview.setItems(items);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Sauvegarder(ActionEvent event) {
        pa_saveChanged.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (pa_nommodifie.getText().equals("") || pa_contactmodifie.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Modifications");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez saisir le nom et le contact");
                    alert.showAndWait();
                } else {
                    try {
                        int tel = Integer.parseInt(pa_contactmodifie.getText());

                        if (pa_contactmodifie.getText().length() != 8) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Modifications");
                            alert.setHeaderText(null);
                            alert.setContentText("Le contact est à 8 chifres");
                            alert.showAndWait();
                        } else {
                            pat.setNom(pa_nommodifie.getText());
                            pat.setAdresse(pa_adressemodifie.getText());
                            pat.setContact(tel);
                            pat.setMail(pa_mailmodifie.getText());
                            pat.setUrl(path);
                            PatisserieService ps = new PatisserieService();//connexion établie
                            try {
                                ps.modifierPatisserie(pat);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Modification");
                                alert.setHeaderText(null);
                                alert.setContentText("Modifications enregistrées");
                                alert.showAndWait();

                                pa_nommodifie.clear();
                                pa_adressemodifie.clear();
                                pa_contactmodifie.clear();
                                pa_mailmodifie.clear();
                                pa_fichierchoisi.clear();
                                pa_icone.setImage(null);

                                try {
                                    ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherPatisseries();
                                    ObservableList items = FXCollections.observableArrayList(patisseries);
                                    listview.setCellFactory((param) -> new CellPersonalise());
                                    listview.setItems(items);

                                } catch (SQLException ex) {
                                    Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                //System.out.println("Erreur d'ajout");
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Modification");
                                alert.setHeaderText(null);
                                alert.setContentText("Erreur:Modifications non enregistrées");
                                alert.showAndWait();
                            }

                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Le contact est un nombre");
                        alert.showAndWait();
                    }
                }
            }
        });
    }

    @FXML
    private void afficherAjout(ActionEvent event) {
        pa_aficheajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AjoutPatisserieFXML.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    pa_aficheajout.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }

    @FXML
    private void SupprimerPat(ActionEvent event) {
        pa_supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index = listview.getSelectionModel().getSelectedIndex();
                if (listview.getSelectionModel().isSelected(index)) {
                    PatisserieService ps = new PatisserieService();
                    pat = (Patisserie) listview.getSelectionModel().getSelectedItem();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Suppression");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes vous sûr de vouloir "
                            + "supprimer la Patisserie " + pat.getNom());
                    Optional<ButtonType> resultat = alert.showAndWait();
                    if (resultat.get() == ButtonType.OK) {
                        try {
                            ps.suprimerPatisserie(pat.getIdp());
                            try {
                                ArrayList<Patisserie> patisseries = (ArrayList< Patisserie>) ps.afficherPatisseries();
                                ObservableList items = FXCollections.observableArrayList(patisseries);
                                listview.setCellFactory((param) -> new CellPersonalise());
                                listview.setItems(items);

                            } catch (SQLException ex) {
                                Logger.getLogger(AccueilPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (SQLException ex) {
                            //Logger.getLogger(AfficherPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Erreur Supression");
                        }

                    }

                }

            }
        });

    }

    @FXML
    private void ModifierPatisserie(ActionEvent event) {
        pa_modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index = listview.getSelectionModel().getSelectedIndex();
                if (listview.getSelectionModel().isSelected(index)) {
                    pat = (Patisserie) listview.getSelectionModel().getSelectedItem();
                    pa_nommodifie.setText(pat.getNom());
                    pa_adressemodifie.setText(pat.getAdresse());
                    String tel = Integer.toString(pat.getContact());
                    pa_contactmodifie.setText(tel);
                    pa_mailmodifie.setText(pat.getMail());
                }

            }
        });
    }
    
}
