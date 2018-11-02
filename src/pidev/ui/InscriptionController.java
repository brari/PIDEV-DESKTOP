/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.entities.Users;
import pidev.service.Userservice;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField adresse;
    @FXML
    private Button importer;
    @FXML
    private JFXTextField nom1;
    @FXML
    private Label firstname_er_label;
    @FXML
    private Label lastname_er_label;
    @FXML
    private Label email_er_label;
    @FXML
    private Label password_er_label;
    @FXML
    private Label lab;
    @FXML
    private Label tel_er_label;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField Confirmer;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Label nom2;
    @FXML
    private ImageView img;
    @FXML
    private Button pdf;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll("Patissier","Client");
       Confirmer.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Confirmer.getText().equals(password.getText())){
                lab.setTextFill(Color.GREEN);
               lab.setText("Mot de passe correspond");
            }else {
                lab.setTextFill(Color.RED);
                lab.setText("Mot de passe ne correspond pas");
            }
        });
    }    

    @FXML
    private void add(MouseEvent event) throws SQLException {
        String gender=role.getValue();
         if((validatefirstname())&&(validatepassword())&&(validatelastname())&&(validateNumTel())&&(validateEmail()))
         {Users u =new Users(nom.getText(),
                 prenom.getText(),
                 mail.getText(),password.getText(),
                 Integer.parseInt(mobile.getText()),
                 adresse.getText(),gender,nom1.getText(),0,nom2.getText());
        Userservice us= new Userservice();
        
             u.setFirst_name(nom.getText());
        us.add_user(u);
        TrayNotification tr2 = new TrayNotification();
				tr2.setAnimationType(AnimationType.POPUP);
				tr2.setTitle("Inscription");
				tr2.setNotificationType(NotificationType.SUCCESS);
				tr2.setMessage("Inscription effectue.\n En attente de confirmation de l'admin");
				tr2.showAndDismiss(Duration.seconds(3));
                                nom.clear();prenom.clear();mail.clear();
                                mobile.clear();adresse.clear();nom1.clear();
//         Image img = new Image("/Image/confirm.png");
//            Notifications notificationBuilder = Notifications.create()
//                                    .title("Inscription ")
//                                    .text("inscription effectuee.\n  En attente de confirmation de l'administrateur ")
//                                    .graphic(new ImageView(img))
//                                    .hideAfter(Duration.seconds(10))
//                                    .position(Pos.TOP_RIGHT);
//                            notificationBuilder.show();
         }else {
             TrayNotification tr1 = new TrayNotification();
				tr1.setAnimationType(AnimationType.POPUP);
				tr1.setTitle("Erreur d'inscription");
				tr1.setNotificationType(NotificationType.ERROR);
				tr1.setMessage("Inscription non effectuee.\n veuillez completer les champs");
				tr1.showAndDismiss(Duration.seconds(3));
//          Image img = new Image("/Image/moins.png");
//            Notifications notificationBuilder2 = Notifications.create()
//                                    .title("erreur d'inscription")
//                                    .text("inscription non effectuee.\n  veuillez completer les champs  ")
//                                    .graphic(new ImageView(img))
//                                    .hideAfter(Duration.seconds(10))
//                                    .position(Pos.TOP_RIGHT);
//                            notificationBuilder2.show();   
//             
         }
    }

    @FXML
    private void annuler(MouseEvent event) {
          try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./ConnexionFXML.fxml"));
                    Parent root;
                    root=loader.load();
                 retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }

    @FXML
    private void parcourir(ActionEvent event) throws MalformedURLException {
         String imageFile;
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            //    getImageUrl = selectedFile.getAbsolutePath();
            // System.out.println(getImageUrl);
            // Image value = new Image(getImageUrl);
            //  img.setImage(value);
            imageFile = selectedFile.toURI().toURL().toString();
            System.out.println(imageFile);

            Image image1 = new Image(imageFile);

            img.setImage(image1);
            //////a changer static

            //////////
            nom1.setText(imageFile);

            /////
        } else {
            System.out.println("file doesn't exist");
        }
        
        
        
        
        
    }
String chemin="";
    @FXML
    private void addpdf(MouseEvent event) {
           pdf.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle (ActionEvent event )
            {
                final FileChooser dialog= new FileChooser();
                
                FileChooser.ExtensionFilter extensionFichier;
                extensionFichier= new FileChooser.ExtensionFilter("Fichiers pdf", "*.pdf");
                dialog.getExtensionFilters().setAll(extensionFichier);
                File fichier=dialog.showOpenDialog(pdf.getScene().getWindow());
                chemin=fichier.getAbsolutePath();
                File file1;
                file1=new File(chemin);
                nom2.setText(chemin);
                
                
            }
        });
        
        
        
        
    }
     private boolean validatefirstname(){
        firstname_er_label.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z]+");
        Matcher matcher=pattern.matcher(prenom.getText());
        if (prenom.getText().isEmpty()){
            firstname_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(prenom.getText())){
            return true;
        }else {
            firstname_er_label.setText("(*)Enter des lettres");
            return false;
        }
    
}
     private boolean validatelastname(){
        lastname_er_label.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z]+");
        Matcher matcher=pattern.matcher(nom.getText());
        if (nom.getText().isEmpty()){
            lastname_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(nom.getText())){
            return true;
        }else {
            lastname_er_label.setText("(*)Enter des lettres");
            return false;
        }
    }
 private boolean validatepassword(){
        password_er_label.setText("");
        if (password.getText().isEmpty()){
            password_er_label.setText("(*)champ requis");
            return false;
        }else {
            return true;
        }

    }
 private boolean validateNumTel(){
        tel_er_label.setText("");
        Pattern pattern=Pattern.compile("[0-9]+");
        Matcher matcher=pattern.matcher(mobile.getText());
        if (mobile.getText().isEmpty()){
            tel_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(mobile.getText())){
            return true;
        }else {
            tel_er_label.setText("(*)Enter des chiffres");
            return false;
        }}
  private boolean validateEmail(){
        email_er_label.setText("");
        Pattern pattern=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9.]*@[a-zA-Z0-9.]+([.][a-zA-Z]+)+");
        Matcher matcher=pattern.matcher(mail.getText());
        if (mail.getText().isEmpty()){
            email_er_label.setText("(*)champ requis");
            return false;
        }
        else if (matcher.find() && matcher.group().equals(mail.getText())){
            return true;
        }else {
            email_er_label.setText("(*)Enter une adresse mail valide");
            return false;
        }
    }
}
