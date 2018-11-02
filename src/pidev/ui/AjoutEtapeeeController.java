/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pidev.entities.Etape;
import pidev.service.EtapeDAO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjoutEtapeeeController implements Initializable {

    @FXML
    private Text text;
    @FXML
    private Label code_Event;
    @FXML
    private TableView<Etape> table_ing;
    @FXML
    private TableColumn<Etape, String> nom_eta;
    @FXML
    private TableColumn<Etape, String> desceta;
    @FXML
    private TableColumn<Etape, Integer> id_utilisateur;
    @FXML
    private TableColumn<Etape, Integer> id_eta;
    @FXML
    private TableColumn<Etape, Integer> id;
    @FXML
    private TextField tf_nometa;
    @FXML
    private TextArea tf_desceta;
    Etape pat;
    @FXML
    private Button btn_eta;
    @FXML
    private Button btn_supp;
    
    @FXML
    private Button btnHome;
    private Button btn_modif;
    @FXML
    private Button pa_modifier;
    @FXML
    private Button btn_modif_eta;
    @FXML
    private Button btn_actualiser;
    @FXML
    private Button btn_imprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public Text getText() {
        return text;
    }

    public Label getCode_Event() {
        return code_Event;
    }

    public TableView<Etape> getTable_ing() {
        return table_ing;
    }

    public TableColumn<Etape, String> getNom_eta() {
        return nom_eta;
    }

    public TableColumn<Etape, String> getDesceta() {
        return desceta;
    }

    public TableColumn<Etape, Integer> getId_utilisateur() {
        return id_utilisateur;
    }

    public TableColumn<Etape, Integer> getId_eta() {
        return id_eta;
    }

    public TableColumn<Etape, Integer> getId() {
        return id;
    }

    public TextField getTf_nometa() {
        return tf_nometa;
    }

    public TextArea getTf_desceta() {
        return tf_desceta;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void setCode_Event(Label code_Event) {
        this.code_Event = code_Event;
    }

    public void setTable_ing(TableView<Etape> table_ing) {
        this.table_ing = table_ing;
    }

    public void setNom_eta(TableColumn<Etape, String> nom_eta) {
        this.nom_eta = nom_eta;
    }

    public void setDesceta(TableColumn<Etape, String> desceta) {
        this.desceta = desceta;
    }

    public void setId_utilisateur(TableColumn<Etape, Integer> id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_eta(TableColumn<Etape, Integer> id_eta) {
        this.id_eta = id_eta;
    }

    public void setId(TableColumn<Etape, Integer> id) {
        this.id = id;
    }

    public void setTf_nometa(TextField tf_nometa) {
        this.tf_nometa = tf_nometa;
    }

    public void setTf_desceta(TextArea tf_desceta) {
        this.tf_desceta = tf_desceta;
    }
    @FXML
    private void ajouterEtape(ActionEvent event)throws SQLException {
        
        EtapeDAO  cs=new   EtapeDAO ();
           Etape e= new Etape(tf_nometa.getText(),tf_desceta.getText() ,Integer.parseInt(code_Event.getText())  );
    cs.ajouterEtape(e);
    
       ArrayList<Etape> ingg =(ArrayList<Etape>) cs.getAllEtape(Integer.parseInt(code_Event.getText()));
              ObservableList obs =FXCollections.observableArrayList(ingg);
           table_ing.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
           nom_eta.setCellValueFactory(new PropertyValueFactory<>("nom_eta"));
            desceta.setCellValueFactory(new PropertyValueFactory<>("description_eta"));
               id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                id_eta.setCellValueFactory(new PropertyValueFactory<>("id_eta")); 
        
        
        
     clear();   
        
        
        
    }

    
     private void clear() {
        tf_nometa.setText("");
        tf_desceta.setText("");
       
    }
    @FXML
    private void SupprimerEtape(ActionEvent event) throws SQLException{
        
        
 
        
        
         btn_supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index=table_ing.getSelectionModel().getSelectedIndex();
                if(table_ing.getSelectionModel().isSelected(index)){
                    EtapeDAO ps= new EtapeDAO();
                    pat=(Etape)table_ing.getSelectionModel().getSelectedItem();
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Suppression");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes vous sûr de vouloir "
                            + "supprimer l'etape "+pat.getNom_eta());
                    Optional <ButtonType> resultat=alert.showAndWait();
                    if(resultat.get()==ButtonType.OK){
                        try {
                            ps.deleteEta(pat.getId());
                            try {
            ArrayList <Etape> eta=(ArrayList < Etape >) ps.getAllEtape(Integer.parseInt(code_Event.getText()));
            ObservableList obs=FXCollections.observableArrayList(eta);
                       table_ing.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
           nom_eta.setCellValueFactory(new PropertyValueFactory<>("nom_eta"));
            desceta.setCellValueFactory(new PropertyValueFactory<>("description_eta"));
               id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                id_eta.setCellValueFactory(new PropertyValueFactory<>("id_eta")); 
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void retour(ActionEvent event) {
     FXMLLoader loder =new FXMLLoader(getClass().getResource("./AfficheRecette.fxml"));
                    Parent root = null;
        try {
            root=loder.load();
        } catch (IOException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnHome.getScene().setRoot(root);  
           
        
        
    }

    @FXML
    private void ModifierEtape(ActionEvent event) {
        btn_modif_eta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(tf_nometa.getText().equals("")|| tf_desceta.getText().equals("")){
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Modifications");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez saisir le nom et la desc");
                    alert.showAndWait();
                }
            else{
            
                
               
                
                    pat.setNom_eta(tf_nometa.getText());
                    pat.setDescription_eta(tf_desceta.getText());
                    
                   
                    EtapeDAO ps=new EtapeDAO();//connexion établie
                    try {
                        ps.modifierEta(pat);
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Modifications enregistrées");
                        alert.showAndWait();
                        
                        tf_nometa.clear(); tf_desceta.clear();
                        
                        
                        
            try {
            ArrayList <Etape> eta=(ArrayList < Etape >) ps.getAllEtape(Integer.parseInt(code_Event.getText()));
            ObservableList obs=FXCollections.observableArrayList(eta);
            table_ing.setItems(obs);
          nom_eta.setCellValueFactory(new PropertyValueFactory<>("nom_eta"));
            desceta.setCellValueFactory(new PropertyValueFactory<>("description_eta"));
           
            
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEtapeeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AddRecetteController.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println("Erreur d'ajout");
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Erreur:Modifications non enregistrées");
                        alert.showAndWait();
                    }
                
                }
          
        }
            
        }); 
        
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void SetTextfield(ActionEvent event) {
     pa_modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                int index=table_ing.getSelectionModel().getSelectedIndex();
                if(table_ing.getSelectionModel().isSelected(index)){
                    pat=(Etape)table_ing.getSelectionModel().getSelectedItem();
                    tf_nometa.setText(pat.getNom_eta());
                    tf_desceta.setText(pat.getDescription_eta());
                    
                }
                
            }
        });
        
        
           
        
        
    }

    @FXML
    private void Actualiser(ActionEvent event) throws SQLException {
       
        
         EtapeDAO  cs=new   EtapeDAO ();
           Etape e= new Etape(tf_nometa.getText(),tf_desceta.getText() ,Integer.parseInt(code_Event.getText())  );
    
    
       ArrayList<Etape> ingg =(ArrayList<Etape>) cs.getAllEtape(Integer.parseInt(code_Event.getText()));
              ObservableList obs =FXCollections.observableArrayList(ingg);
           table_ing.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
           nom_eta.setCellValueFactory(new PropertyValueFactory<>("nom_eta"));
            desceta.setCellValueFactory(new PropertyValueFactory<>("description_eta"));
               id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                id_eta.setCellValueFactory(new PropertyValueFactory<>("id_eta")); 
        
        
        
        
        
          
        
        
    }
    
    
    
    private void Actua() throws SQLException {
        
          
         EtapeDAO  cs=new   EtapeDAO ();
           Etape e= new Etape(tf_nometa.getText(),tf_desceta.getText() ,Integer.parseInt(code_Event.getText())  );
    
    
       ArrayList<Etape> ingg =(ArrayList<Etape>) cs.getAllEtape(Integer.parseInt(code_Event.getText()));
              ObservableList obs =FXCollections.observableArrayList(ingg);
           table_ing.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
           nom_eta.setCellValueFactory(new PropertyValueFactory<>("nom_eta"));
            desceta.setCellValueFactory(new PropertyValueFactory<>("description_eta"));
               id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                id_eta.setCellValueFactory(new PropertyValueFactory<>("id_eta"));
        
        
        
    }

    @FXML
    private void imprime(ActionEvent event) {
    }
    
}
