/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import pidev.entities.Evenements;
import pidev.service.Evenementservice;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterMesEvenementFXMLController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextField E_nom_entry;
    @FXML
    private TextField E_description_entry;
    @FXML
    private TextField E_adresse_entry;
    @FXML
    private TextField E_type_entry;
    @FXML
    private DatePicker E_date_entry;
    @FXML
    private TextField E_image_entry;
    @FXML
    private Button E_image_button;
    @FXML
    private Button E_ajout_button;
    @FXML
    private Button E_afficher_pdf;
    @FXML
    private ImageView E_im_ajout_view;
    @FXML
    private Label E_ajoutmsg_label;
    String image;
    String lien_image;
    private FileInputStream fis;
    @FXML
    private TextField E_interesses_entry;
    @FXML
    private TextField E_capacite_entry;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisir_image(ActionEvent event) throws MalformedURLException {
        String imageFile;
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();
            Image image1 = new Image(imageFile);

            E_im_ajout_view.setImage(image1);
            image = imageFile;
            
            String path =imageFile;
            path = path.replace("\\","/");
            path = path.replace("%20", " ");
            path = path.replace("file:/", "");

        } else {
            System.out.println("file doesn't exist");
    }
    }
    
    

    @FXML
    private void ajouter_evenement(ActionEvent event) {
        E_ajout_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
               E_ajoutmsg_label.setText("");   
                
                      
                      
                 if (E_nom_entry.getText().equals(""))
                 {
                     
                         TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Evènements patisserie");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir le nom");
				tr.showAndDismiss(Duration.seconds(3));
                 
                 }     
                  else if (E_description_entry.getText().equals(""))
                 {
                     
                         TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Evènements patisserie");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir la description");
				tr.showAndDismiss(Duration.seconds(3));
                 
                 }
                  
                    else if (E_adresse_entry.getText().equals(""))
                 {
                     
                         TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Evènements patisserie");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir l'adresse");
				tr.showAndDismiss(Duration.seconds(3));
                 
                 }
                 else if(E_date_entry.getValue()==null)
                {
                        TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Evènements patisserie");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez choisir une date");
				tr.showAndDismiss(Duration.seconds(3));

                }
                 else if (LocalDate.now().toEpochDay() >= E_date_entry.getValue().toEpochDay())
                 {
                        TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Evènements patisserie");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("la date de début de l'évenement doit etre superieur à la date courante !");
				tr.showAndDismiss(Duration.seconds(3));

                } 
      
                else{ 
                
               
                
                Evenementservice es=new Evenementservice();  //instanciation
       
                try{
                    String path =  E_im_ajout_view.getImage().impl_getUrl();
                    path = path.replace("\\","/");
                    path = path.replace("%20", " ");
                    path = path.replace("file:/", "");
        
                    System.out.println("ath: "+path);
                    Evenements e=new Evenements (E_nom_entry.getText(),E_description_entry.getText(),E_adresse_entry.getText(),E_type_entry.getText(),java.sql.Date.valueOf(E_date_entry.getValue()),path,Integer.parseInt(E_interesses_entry.getText()),Integer.parseInt(E_capacite_entry.getText()));
                    es.ajouter_even(e);
                    TrayNotification tr = new TrayNotification();
			tr.setAnimationType(AnimationType.POPUP);
			tr.setTitle("Evenements patisserie");
			tr.setNotificationType(NotificationType.INFORMATION);
			tr.setMessage("l'évènement a été bien ajouté");
			tr.showAndDismiss(Duration.seconds(2));
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficheMesEvenementFXML.fxml"));
                    Parent root;
                    try {
                        root=loader.load();
                       E_ajout_button.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterMesEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterMesEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Erreur d'ajout");
                     E_ajoutmsg_label.setText("Erreur d'ajout");
                }
                    
                }
                
            }
        });
        
    }

    @FXML
    private void afficher_pdf(ActionEvent event) {
         E_afficher_pdf.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)        
            {
               File file = new File("C:\\Users\\DELL\\Desktop\\even.pdf");
       try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(AjouterMesEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            
             });  
    }
    
}
