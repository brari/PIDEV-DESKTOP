/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pidev.entities.Patisserie;
import pidev.service.PatisserieService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjoutPatisserieFXMLController implements Initializable {

    @FXML
    private TextField pa_nom;
    @FXML
    private TextField pa_adresse;
    @FXML
    private TextField pa_contact;
    @FXML
    private Button pa_fichier;
    @FXML
    private Button pa_creer;
    @FXML
    private ImageView pa_image;
    @FXML
    private TextField pa_choix;
    @FXML
    private Label pa_erreur;
    @FXML
    private TextField pa_mail;
    @FXML
    private Button pa_retourpat;

    /**
     * Initializes the controller class.
     */
    
    String chemin="";
    File file1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisirFichier(ActionEvent event) {
        pa_fichier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                //ouvrir une boite de dialogue pour choisir le fichier
                final FileChooser dialog= new FileChooser();
                //préciser l'extension du fichier à choisir, ici les images
                FileChooser.ExtensionFilter extensionFichier;
                extensionFichier = new FileChooser.ExtensionFilter("Fichiers Image", "*.jpg", "*.jpeg","*.png");
                //appliquer le filtre choisi a la boite de dialogue
                dialog.getExtensionFilters().setAll(extensionFichier);
                //affiche la boite de dialogue
                final File fichier=dialog.showOpenDialog(pa_fichier.getScene().getWindow());
               
                chemin=fichier.getAbsolutePath();
                
                pa_choix.setText(fichier.getName());
                
                //récuperer l'image choisie
                file1 = new File(chemin);
                Image image = new Image(file1.toURI().toString());
                pa_image.setImage(image);
            }
        });
    }

    @FXML
    private void Creer(ActionEvent event) {
        
        pa_creer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            pa_erreur.setText("");    
            if(pa_nom.getText().equals("")|| pa_contact.getText().equals("")){
                pa_erreur.setText("Veuillez saisir le nom et le contact");}
            else{
            try{
                int tel=Integer.parseInt(pa_contact.getText());
                
                if(pa_contact.getText().length()!=8)
                    pa_erreur.setText("Le contact est à 8 chifres");
                else{
                    pa_erreur.setText("");
                    int val=ConnexionFXMLController.session.getUser_id();
                    Patisserie p=new Patisserie(pa_nom.getText(),pa_adresse.getText(),
                            tel,pa_mail.getText(),val);
                    p.setUrl(chemin);
                    PatisserieService ps=new PatisserieService();//connexion établie
                    try {
                        ps.ajouterPatisserie(p);
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ajout à la base");
                        alert.setHeaderText(null);
                        alert.setContentText("Patisserie ajoutée avec succès");
                        alert.showAndWait();
                        
                        pa_nom.clear(); pa_adresse.clear();
                        pa_contact.clear(); pa_mail.clear();
                        pa_choix.clear(); pa_image.setImage(null);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println("Erreur d'ajout");
                        pa_erreur.setText("Erreur d'ajout");
                    }
                }               
            }catch(NumberFormatException e){
                pa_erreur.setText("Le contact est un nombre");
            }
            }   
            }
        });
    }

    @FXML
    private void RetourAffiche(ActionEvent event) {
        
         pa_retourpat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilPatissierFXML.fxml"));
                Parent root; 
                
                try {
                    root=loader.load();
                    pa_retourpat.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutPatisserieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
