/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
public class AfficheEvenementPatissierFXMLController implements Initializable {

    @FXML
    private ListView<String> E_list_view;
    @FXML
    private Button E_modifier_button;
    @FXML
    private Button E_aj_aff_button;
    @FXML
    private Button E_supp_button;
    @FXML
    private Button E_liste_p;
    @FXML
    private ComboBox<String> filtre;
    @FXML
    private Button filtrer_button;
    @FXML
    private Text filtrer;
    @FXML
    private Button aff_tout;
    @FXML
    private Button E_choisir_img;
    @FXML
    private TextField E_nom_text;
    @FXML
    private TextField E_description_text;
    @FXML
    private TextField E_adresse_text;
    @FXML
    private DatePicker E_date_text;
    @FXML
    private TextField E_type_text;
    @FXML
    private TextField E_image_text;
    @FXML
    private ImageView E_image_view_modif;
    @FXML
    private Button E_visualiser_pdf;
    @FXML
    private Button search_butt;
    @FXML
    private TextField E_trouver_text;
    String image;
    String lien_image;
    private FileInputStream fis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Evenementservice ese = new Evenementservice();
        filtre.getItems().addAll("degustation", "coatching", "reception");
        ObservableList obs = null;
        E_nom_text.setVisible(false);
        E_description_text.setVisible(false);
        E_adresse_text.setVisible(false);
        E_type_text.setVisible(false);
        E_date_text.setVisible(false);
        E_image_text.setVisible(false);
        //ArrayList<Evenements> ds=null;
        try {
            obs =ese.afficher_even();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        E_list_view.setItems(obs);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Evenementservice ese = new Evenementservice();
        Evenements e = new Evenements();
        if (E_nom_text.getText().equals("")) {

            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Evènements patisserie");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez remplir le nom");
            tr.showAndDismiss(Duration.seconds(3));

        } else if (E_description_text.getText().equals("")) {

            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Evènements patisserie");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez remplir la description");
            tr.showAndDismiss(Duration.seconds(3));

        } else if (E_adresse_text.getText().equals("")) {

            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Evènements patisserie");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez remplir l'adresse");
            tr.showAndDismiss(Duration.seconds(3));

        } else if (E_date_text.getValue() == null) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Evènements patisserie");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez choisir une date");
            tr.showAndDismiss(Duration.seconds(3));

        } else if (LocalDate.now().toEpochDay() >= E_date_text.getValue().toEpochDay()) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Evènements patisserie");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("la date de début de l'évenement doit etre superieur à la date courante !");
            tr.showAndDismiss(Duration.seconds(3));

        } else {
            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("confirmation");
            alerte.setHeaderText("voulez vous vraiment modifier votre évènement?");
            Optional<ButtonType> result = alerte.showAndWait();
            if (result.get() == ButtonType.OK) {

                //Evenements e=(Evenements) E_affiche_table.getSelectionModel().getSelectedItem();
                String path = E_image_view_modif.getImage().impl_getUrl();
                path = path.replace("\\", "/");
                path = path.replace("%20", " ");
                path = path.replace("file:/", "");

                //System.out.println("ath: "+path);
                ese.modifier_even(E_nom_text.getText(), E_description_text.getText(), E_adresse_text.getText(), E_type_text.getText(),
                        java.sql.Date.valueOf(E_date_text.getValue()), path);

            }
            ObservableList obs = null;

            obs = ese.afficher_even();

//        try 
//        {
//           
//            obs =ese.afficher_even();
//        } catch (SQLException ex) {
//            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
            E_list_view.setItems(obs);

            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Evenements patisserie");
            tr.setNotificationType(NotificationType.INFORMATION);
            tr.setMessage("La modification a été bien effectuée");
            tr.showAndDismiss(Duration.seconds(2));

        }

    }

    @FXML
    private void ajouter_aff(ActionEvent event) {
         E_aj_aff_button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)        
            {
                    
                
                     
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjoutMesEvenementFXML.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                     E_aj_aff_button.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger( AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                
           }
    });
    }

    @FXML
    private void supprimer_evenement_aff(ActionEvent event) throws SQLException {
        if(E_list_view.getSelectionModel().getSelectedItem()!=null)
        {
            Evenementservice ese=new Evenementservice(); 
            String a=E_list_view.getSelectionModel().getSelectedItem();
           
            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("confirmation");
            alerte.setHeaderText("voulez vous vraiment supprimer votre évènement?");
            Optional<ButtonType> result = alerte.showAndWait();
            if (result.get() == ButtonType.OK) {
                
                ese.supprimer_even(a);
                ObservableList obs=null;
        
         try {
            obs =ese.afficher_even();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         E_list_view.setItems(obs);
               
            
            }
            triggernotif("La suppression a été bien effectuée");
            
            
        
        
        }  
       
       
  
    }
    
     public void triggernotif(String champ){
        TrayNotification tr = new TrayNotification();
        tr.setAnimationType(AnimationType.POPUP);
        tr.setTitle("notif");
        tr.setNotificationType(NotificationType.ERROR);
        tr.setMessage( champ);
        tr.showAndDismiss(Duration.seconds(2));

    }

    @FXML
    private void E_voir_particip(ActionEvent event) {
        E_liste_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("./separticiper_even.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    E_liste_p.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(Separticiper_evenController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @FXML
    private void chercher_filtre(ActionEvent event) {
        filtrer_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Evenementservice ese = new Evenementservice();
                try {
                    String fil = filtre.getValue();
                    ObservableList obs = null;

                    try {
                        obs = ese.rechercher_even_by_type(fil);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    E_list_view.setItems(obs);

                    //String a=(String)E_list_view.getSelectionModel().getSelectedItem();
                    //Evenements e=(Evenements) obs.get(0);
                    //E_nom_text.setText(e.getNom_E());
                    //E_description_text.setText(e.getDescription_E());
                    //E_adresse_text.setText(e.getAdresse_E());
                    //E_type_text.setText(e.getType_E());
                } catch (NullPointerException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Choisissez un filtre!!");
                    alert.showAndWait();
                }

            }
        });

    }

    @FXML
    private void afficher_tout(ActionEvent event) {
        Evenementservice ese = new Evenementservice();

        ObservableList obs = null;
        //ArrayList<Evenements> ds=null;
//        try {
//
//            obs = ese.afficher_even();
//        } catch (SQLException ex) {
//            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            obs= ese.afficher_even();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        E_list_view.setItems(obs);
    }

    @FXML
    private void choisir_image_modif(ActionEvent event) throws MalformedURLException {
        String imageFile;
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();
            Image image1 = new Image(imageFile);

            E_image_view_modif.setImage(image1);
            image = imageFile;

            String path = imageFile;
            path = path.replace("\\", "/");
            path = path.replace("%20", " ");
            path = path.replace("file:/", "");

        } else {
            System.out.println("file doesn't exist");
        }
    }

    @FXML
    private void visualiser_pdf(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
        search_butt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Evenementservice ese = new Evenementservice();
                try {
                    String fil = E_trouver_text.getText();
                    ObservableList obs = null;

                    try {
                        obs = ese.rechercher_even_by_nom(fil);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEvenementPatissierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    E_list_view.setItems(obs);

                } catch (NullPointerException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Choisissez un filtre!!");
                    alert.showAndWait();
                }
            }

        });
    }

    @FXML
    private void listeclicked(MouseEvent event) throws SQLException {
        Evenementservice ese = new Evenementservice();
        String a = (String) E_list_view.getSelectionModel().getSelectedItem();
        E_nom_text.setVisible(true);
        E_description_text.setVisible(true);
        E_adresse_text.setVisible(true);
        E_type_text.setVisible(true);
        E_date_text.setVisible(true);
        //E_image_text.setVisible(true);
        //System.out.println(a);
        ObservableList li = null;
        li = ese.get_even_nom(a);
        Evenements e = (Evenements) li.get(0);
        String id = Integer.toString(e.getIdE());

        E_nom_text.setText(e.getNom_E());
        E_description_text.setText(e.getDescription_E());
        E_adresse_text.setText(e.getAdresse_E());
        E_type_text.setText(e.getType_E());
        //E_image_text.setText(e.getType_E());

        //E_date_text.setPromptText(e.getDate_E());
        //dte.setText(toString(e.getDate_E()));
        //java.sql.Date.valueOf(E_date_text.getValue())
    }

}
