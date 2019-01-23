/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.ui.ManiusersController;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class EnvoyermessageController implements Initializable {

    @FXML
    private TableView<?> table_message;
    @FXML
    private TableColumn<?, ?> cl_nom;
    @FXML
    private TableColumn<?, ?> cl_prenom;
    @FXML
    private TableColumn<?, ?> cl_message;
    @FXML
    private JFXTextField tx_message;
    @FXML
    private TableView<?> table_reclamation;
    @FXML
    private TableColumn<?, ?> cl_type;
    @FXML
    private TableColumn<?, ?> cl_objet;
    @FXML
    private TableColumn<?, ?> cl_contenu;
    @FXML
    private TableColumn<?, ?> cl_date;
    @FXML
    private ImageView btn_envoyer;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void affichermessages(MouseEvent event) {
    }

    @FXML
    private void ajoutermessages(MouseEvent event) {
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
