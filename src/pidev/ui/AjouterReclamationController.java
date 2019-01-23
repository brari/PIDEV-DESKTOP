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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
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
public class AjouterReclamationController implements Initializable {

    @FXML
    private JFXTextField tx_objet;
    @FXML
    private JFXButton btn_envoyer;
    @FXML
    private JFXTextArea tx_contenu;
    @FXML
    private JFXComboBox<String> combobox;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton btn_retour;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        combobox.getItems().addAll("produit","patissier");
           btn_envoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                   if(tx_contenu.getText().length()==0||tx_objet.getText().length()==0||combobox.getValue()==null){
          /*      Alert missingFields = new Alert(AlertType.ERROR);
                missingFields.setTitle("Missing fields error");
                missingFields.setHeaderText(null);
                missingFields.setContentText("Please ensure that you have provided all information in the available fields");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();*/
               String title = "Missing fields error";
        String message = "Please ensure that you have provided all information in the available fields";
         NotificationType notification = NotificationType.WARNING;
                TrayNotification tray = new TrayNotification();
                    tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndWait();
                   }
                   else{
                 String title = "Congratulations sir";
        String message = "You've successfully sended your reclamation";
          Reclamation r = new Reclamation(combobox.getValue(),tx_objet.getText(),tx_contenu.getText());
              ReclamationService rs = new ReclamationService();
             // r.setId_reclamant(ConnexionFXMLController.session.getUser_id());
                 NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                EnvoyerEmail test = new EnvoyerEmail();
                       Userservice us=new Userservice();
                try {
                            rs.ajouterreclamation(r);
                           Users u=us.chercherUser_id(r.getId_concerne());                          
test.envoyer("","",u.getEmail());
                               tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndWait();
          tx_objet.setText("");
          tx_contenu.setText("");
                }    catch (SQLException ex) {
                           Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
            }
        });
    }

    @FXML
    private void retour(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("./mainspace.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    btn_retour.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}

   
