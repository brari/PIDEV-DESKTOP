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
import pidev.entities.Ingredient;
import pidev.service.IngredientDAO;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjoutIngredientController implements Initializable {

  @FXML
    private Label code_Event;
    @FXML
    private Text text;
    @FXML
    private TextArea tf_quantite;
    @FXML
    private Button Commenter;
    @FXML
    private TextField tf_noming;
    @FXML
    private TableView<Ingredient> table_ing;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<Ingredient, String> nom_ing;
    @FXML
    private TableColumn<Ingredient, String> q;
    @FXML
    private TableColumn<Ingredient, Integer> id_utilisateur;
    @FXML
    private TableColumn<Ingredient, Integer> id_ing;
    @FXML
    private TableColumn<Ingredient, Integer> id;
    @FXML
    private Button btn_supp;

    Ingredient pat;
    @FXML
    private Button btnHome;
    @FXML
    private Button btn_modif_ing;
    @FXML
    private Button pa_modifier;
    @FXML
    private Button btn_actualiser;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public Label getCode_Event() {
        return code_Event;
    }

    public Text getText() {
        return text;
    }

    public TextArea getTf_quantite() {
        return tf_quantite;
    }

    public Button getCommenter() {
        return Commenter;
    }

    public TextField getTf_noming() {
        return tf_noming;
    }

    public TableView<Ingredient> getTable_ing() {
        return table_ing;
    }

    public Button getRetour() {
        return retour;
    }

    public TableColumn<Ingredient, String> getNom_ing() {
        return nom_ing;
    }

    public TableColumn<Ingredient, String> getQ() {
        return q;
    }

    public TableColumn<Ingredient, Integer> getId_utilisateur() {
        return id_utilisateur;
    }

    public TableColumn<Ingredient, Integer> getId_ing() {
        return id_ing;
    }

    public TableColumn<Ingredient, Integer> getId() {
        return id;
    }

    public void setCode_Event(Label code_Event) {
        this.code_Event = code_Event;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void setTf_quantite(TextArea tf_quantite) {
        this.tf_quantite = tf_quantite;
    }

    public void setCommenter(Button Commenter) {
        this.Commenter = Commenter;
    }

    public void setTf_noming(TextField tf_noming) {
        this.tf_noming = tf_noming;
    }

    public void setTable_ing(TableView<Ingredient> table_ing) {
        this.table_ing = table_ing;
    }

    public void setRetour(Button retour) {
        this.retour = retour;
    }

    public void setNom_ing(TableColumn<Ingredient, String> nom_ing) {
        this.nom_ing = nom_ing;
    }

    public void setQ(TableColumn<Ingredient, String> q) {
        this.q = q;
    }

    public void setId_utilisateur(TableColumn<Ingredient, Integer> id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_ing(TableColumn<Ingredient, Integer> id_ing) {
        this.id_ing = id_ing;
    }

    public void setId(TableColumn<Ingredient, Integer> id) {
        this.id = id;
    }

    public Button getBtn_supp() {
        return btn_supp;
    }

    public void setBtn_supp(Button btn_supp) {
        this.btn_supp = btn_supp;
    }

    
    
    
    @FXML
    private void Ajout_ing(ActionEvent event) throws SQLException {
        IngredientDAO cs = new IngredientDAO();
        Ingredient e = new Ingredient(Integer.parseInt(code_Event.getText()), tf_noming.getText(), tf_quantite.getText());
        cs.ajouterIngredient(e);

        ArrayList<Ingredient> ingg = (ArrayList<Ingredient>) cs.getAllIngredient(Integer.parseInt(code_Event.getText()));
        ObservableList obs = FXCollections.observableArrayList(ingg);
        table_ing.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_ing.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        q.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        id_ing.setCellValueFactory(new PropertyValueFactory<>("id_ing"));

        clear();
        
    }

    
    
        
    private void clear() {
        tf_noming.setText("");
        tf_quantite.setText("");
       
    }
    @FXML
    private void retourToRecette(ActionEvent event) {
        
       FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficheRecette.fxml"));
                    Parent root;
                    try 
                    {
                      root=loader.load();
                     retour.getScene().setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        Logger.getLogger(DrawerviewClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }   
        
        
    }

    @FXML
    private void SupprimerIngrédient(ActionEvent event) {
       btn_supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index = table_ing.getSelectionModel().getSelectedIndex();
                if (table_ing.getSelectionModel().isSelected(index)) {
                    IngredientDAO ps = new IngredientDAO();
                    pat = (Ingredient) table_ing.getSelectionModel().getSelectedItem();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Suppression");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes vous sûr de vouloir "
                            + "supprimer l'ing " + pat.getNom_ing());
                    Optional<ButtonType> resultat = alert.showAndWait();
                    if (resultat.get() == ButtonType.OK) {
                        try {
                            ps.deleteingg(pat.getId());
                            try {
                                ArrayList<Ingredient> ingg = (ArrayList< Ingredient>) ps.getAllIngredient(Integer.parseInt(code_Event.getText()));
                                ObservableList obs = FXCollections.observableArrayList(ingg);
                                table_ing.setItems(obs);
                                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                                nom_ing.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
                                q.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                                id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                                id_ing.setCellValueFactory(new PropertyValueFactory<>("id_ing"));

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
//setCellValueFromTableToTextField(); 
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
        
        FXMLLoader loder = new FXMLLoader(getClass().getResource("./AfficheRecette.fxml"));
        Parent root = null;
        try {
            root = loder.load();
        } catch (IOException ex) {
            Logger.getLogger(AjoutIngredientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnHome.getScene().setRoot(root);
    }

    
    private void setCellValueFromTableToTextField(){
        table_ing.setOnMouseClicked(e -> {
            Ingredient pl = table_ing.getItems().get(table_ing.getSelectionModel().getSelectedIndex());
            //Recette pl = (Recette) table.getItems().get(table.getSelectionModel().getSelectedIndex());
            tf_noming.setText(pl.getNom_ing());
            tf_quantite.setText(pl.getQuantite());
            
            
            
            //howProductImage(pl.getBarcode());
          
        });
        
        
    }
    
    
    
    
    @FXML
    private void ModifierIngedient(ActionEvent event) {
      btn_modif_ing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(tf_noming.getText().equals("")|| tf_quantite.getText().equals("")){
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Modifications");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez saisir le nom et la quantite");
                    alert.showAndWait();
                }
            else{
            
                
               
                
                    pat.setNom_ing(tf_noming.getText());
                    pat.setQuantite(tf_quantite.getText());
                    
                   // pat.setUrl(path);
                    IngredientDAO ps=new IngredientDAO();//connexion établie
                    try {
                        ps.modifierIng(pat);
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Modifications enregistrées");
                        alert.showAndWait();
                        
                        tf_noming.clear(); tf_quantite.clear();
                        
                        
                        
            try {
                
                
            ArrayList <Ingredient> patisseries=(ArrayList < Ingredient >) ps.getAllIngredient(pat.getId());
            ObservableList obs=FXCollections.observableArrayList(patisseries);
            table_ing.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_ing.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        q.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        id_ing.setCellValueFactory(new PropertyValueFactory<>("id_ing"));
           
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
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
                    pat=(Ingredient)table_ing.getSelectionModel().getSelectedItem();
                    tf_noming.setText(pat.getNom_ing());
                    tf_quantite.setText(pat.getQuantite());
                    
                }
                
            }
        }); 
        
        
        
        
    }

    @FXML
    private void Actualiser(ActionEvent event) throws SQLException {
     IngredientDAO cs = new IngredientDAO();
        Ingredient e = new Ingredient(Integer.parseInt(code_Event.getText()), tf_noming.getText(), tf_quantite.getText());
        

        ArrayList<Ingredient> ingg = (ArrayList<Ingredient>) cs.getAllIngredient(Integer.parseInt(code_Event.getText()));
        ObservableList obs = FXCollections.observableArrayList(ingg);
        table_ing.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_ing.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        q.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        id_ing.setCellValueFactory(new PropertyValueFactory<>("id_ing"));
        
        
           
        
        
    }
    
}
