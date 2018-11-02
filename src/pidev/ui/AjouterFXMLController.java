package pidev.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import pidev.entities.Produit;
import pidev.service.ProduitService;

//import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Insaf-Nefzi
 */
public class AjouterFXMLController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnafficher;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> cat;
    ObservableList<String> comboListcate = FXCollections.observableArrayList("Sucre","Sale");
    @FXML
    private TextField det;
    @FXML
    private TextField name;
    @FXML
    private TextField nom1;
    @FXML
    private Button btnparc;
    @FXML
    private ImageView img;
     AudioClip note = new AudioClip(this.getClass().getResource("audio.mp3").toString());
    @FXML
    private ImageView photo;
    @FXML
    private Button btncontinuer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                        ProduitService m=new ProduitService();
                         cat.setValue("Categorie");
        cat.setItems(comboListcate);


 
    }    

    @FXML
    private void afficherprod(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader ( getClass().getResource("./AfficherProduitPatissierFXML.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      btnafficher.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   // }//pour que le bouton ouvre une autre interface
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

//    String chemin ="";
//     @FXML
//    private void parcourir(ActionEvent event) {
//    
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                
//                //ouvrir une boite de dialogue pour choisir le fichier
//                final FileChooser dialog= new FileChooser();
//                //préciser l'extension du fichier à choisir, ici les images
//                FileChooser.ExtensionFilter extensionFichier;
//                extensionFichier = new FileChooser.ExtensionFilter("Fichiers Image", "*.jpg", "*.jpeg","*.png");
//                //appliquer le filtre choisi a la boite de dialogue
//                dialog.getExtensionFilters().setAll(extensionFichier);
//                //affiche la boite de dialogue
//                final File fichier=dialog.showOpenDialog(btnparc.getScene().getWindow());
//               
//                chemin=fichier.getAbsolutePath();
//                
//                nom1.setText(fichier.getName());
//                 File file1;
//                //récuperer l'image choisie
//                file1 = new File(chemin);
//                Image image = new Image(file1.toURI().toString());
//                img.setImage(image);
//
//    }

    
    @FXML
    private void ajouterprod(ActionEvent event) throws SQLException, FileNotFoundException  {
        
        
        
             
                  if(nom.getText().equals("")||prix.getText().equals("")||cat.getValue().equals("")||det.getText().equals("")||name.getText().equals("")||nom1.getText().equals("")){
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Veuillez remplir tous les champs");
                                            Optional<ButtonType> result = alert.showAndWait();   
     }   
//                    else  if(nom.getText().matches("[a-zA-Z]+")&&det.getText().matches("[a-zA-Z]+")&&name.getText().matches("[a-zA-Z]+")){
//       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                                            alert.setTitle("Ajout refusé ");
//                                            alert.setHeaderText("Veuillez saisir des donnees valides");
//                                            Optional<ButtonType> result = alert.showAndWait(); }
                  
                  else if(Float.parseFloat(prix.getText())<=0){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Le Prix du produit ne peut pas etre negatif ");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
                  else if(nom.getText().length()<4){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Le Nom du Produit que vous voulez ajouter n'est pas valide");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
                  else if(det.getText().length()<4){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Les Details du Produit que vous voulez ajouter ne sont pas valide");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
                
                  else  {  
                      
                         Produit p=new Produit(nom.getText(), Float.parseFloat(prix.getText()),cat.getValue(),det.getText(),name.getText(),nom1.getText(),ConnexionFXMLController.session.getUser_id());
                ProduitService m=new ProduitService();
                m.ajouterProduct(p);
                      
                      
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Succés d'ajout ");
                                            alert.setContentText(p.getNompro());
                                            alert.setHeaderText("Produit ajouté avec succé");
                                            Optional<ButtonType> result = alert.showAndWait();}
//                  else {
//                         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                                            alert.setTitle("Probleme d'ajout ");
//                                           
//                                            alert.setHeaderText("Veuillez saisir des données valides");
//                                            Optional<ButtonType> result = alert.showAndWait();}
                  }



                                           
               
            

    @FXML
    private void audioplayer(MouseEvent event) {
       note.isPlaying();
       if (note.isPlaying() == true) {
            note.stop();
        } else {
           note.play();
           
        }
    }

    @FXML
    private void continuer(ActionEvent event) {
        
          FXMLLoader loader= new FXMLLoader ( getClass().getResource("./jfeonix.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      btnafficher.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   // }//pour que le bouton ouvre une autre interface
    }

    }
        
    
}
