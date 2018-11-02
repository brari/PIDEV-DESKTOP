/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pidev.entities.Evenements;
import pidev.service.Evenementservice;
import pidev.service.Participantservice;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AccueilClientEvenementFXMLController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private ImageView logo;
    @FXML
    private ComboBox<String> E_combo_client;
    @FXML
    private Button E_filtreclient_button;
    @FXML
    private Text filtrer;
    @FXML
    private ListView<String> E_list_client;
    @FXML
    private Button E_video_button;
    @FXML
    private Button E_interet_button;
    @FXML
    private Label label_nom;
    @FXML
    private Label label_description;
    @FXML
    private Label label_adresse;
    @FXML
    private Label label_type;
    @FXML
    private Label label_image;
    public Participantservice spe=new Participantservice();
    
    public Evenementservice se=new Evenementservice();
    @FXML
    private Button ret;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Evenementservice ese=new Evenementservice();
        E_combo_client.getItems().addAll("degustation","coatching","reception");
         ObservableList obs=null;
         label_nom.setVisible(false);
         label_description.setVisible(false);
         label_adresse.setVisible(false);
         label_type.setVisible(false);
         //label_date.setVisible(false);
         //participant_label.setVisible(false);
        //ArrayList<Evenements> ds=null;
        try {
            obs =ese.afficher_even();
        } catch (SQLException ex) {
            Logger.getLogger(AccueilClientEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         E_list_client.setItems(obs);
    }    

    @FXML
    private void filtrer_client(ActionEvent event) {
         E_filtreclient_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Evenementservice ese=new Evenementservice();
                try{
                String fil= E_combo_client.getValue();
                 ObservableList obs=null;
                  
                    try {
                        obs =ese.rechercher_even_by_type(fil);
                    } catch (SQLException ex) {
                        Logger.getLogger(AccueilClientEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  E_list_client.setItems(obs);
                
                  
               //String a=(String)E_list_view.getSelectionModel().getSelectedItem();
        
               //Evenements e=(Evenements) obs.get(0);
               //E_nom_text.setText(e.getNom_E());
               //E_description_text.setText(e.getDescription_E());
               //E_adresse_text.setText(e.getAdresse_E());
                //E_type_text.setText(e.getType_E());
                }
                catch(NullPointerException e){
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Choisissez un filtre!!");
                        alert.showAndWait();
                }
                
                
                
                
            }
        });
    }

    @FXML
    private void visualiser_even(MouseEvent event) throws SQLException {
         Evenementservice ese=new Evenementservice();
        String a=(String)E_list_client.getSelectionModel().getSelectedItem();
         label_nom.setVisible(true);
         label_description.setVisible(true);
         label_adresse.setVisible(true);
         label_type.setVisible(true);
         //label_date.setVisible(true);
        // participant_label.setVisible(true);
        //System.out.println(a);
        ObservableList li=null;
        li=ese.get_even_nom(a);
        Evenements e=(Evenements) li.get(0);
        label_nom.setText("Nom évènement: "+e.getNom_E());
        label_description.setText("Description évènement: "+e.getDescription_E());
        label_adresse.setText("Adresse évènement: "+e.getAdresse_E());
        label_type.setText("Type évènement: "+e.getType_E());
        //label_date.setPromptText(e.getDate_E());
        //label_date.setText((e.getDate_E()).getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"Date évènement: ");
        //label_date.setText("Date évènement: "+e.getDate_E().getPromptText());
       // participant_label.setText("Participants: "+e.getNombre_interesses());
        
    }

    @FXML
    private void regarder_video(ActionEvent event) {
         E_video_button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)        
            {
                    
                
                     
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("./VideoPlayerFXML.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                     E_video_button.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                
           }
    });
    }

    @FXML
    private void participe(ActionEvent event) throws SQLException {
         Evenementservice ese=new Evenementservice();
        String a=(String)E_list_client.getSelectionModel().getSelectedItem();
        ObservableList li=null;
        li=ese.get_even_nom(a);
        Evenements e=(Evenements) li.get(0);
        //E_interet_button.setText("Participer");
        
        if (spe.Verifier_part(a)==false)
        {   
          E_interet_button.setText("Participer");  
         spe.ajouter_part(a);
         //se.IncrementNombreInteresses(e.getNom_E());
          //e.setNombre_interesses(e.getNombre_interesses()+1);
          //participant_label.setText("Participants: "+Integer.toString(e.getNombre_interesses()));
          E_interet_button.setText("Annuler");
        }
        else 
        {
            spe.SupprimerParticipation(e.getNom_E());
            //se.DecrementNombreInteresses(evenement.getNom_E());
            //e.setNombre_interesses(e.getNombre_interesses());
            // participant_label.setText("Participants: "+Integer.toString(e.getNombre_interesses()));
            E_interet_button.setText("Participer");
        }
        
    }

    @FXML
    private void retourne(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilClientFXML.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                     ret.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(AccueilClientEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
}
