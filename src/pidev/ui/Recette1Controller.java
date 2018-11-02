/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entities.Recette1;
import pidev.service.CommentaireRecetteServices;
import pidev.service.HistoriqueServices;
import pidev.service.Recette1Services;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Recette1Controller implements Initializable {

    @FXML
    private TableView<Recette1> tab_declaration;
    @FXML
    private TableColumn<Recette1, Integer> id;
    @FXML
    private TableColumn<Recette1, String> nom;
    @FXML
    private TableColumn<Recette1, String> description;
    @FXML
    private TableColumn<Recette1, String> etat;
    @FXML
    private TableColumn<Recette1, String> action;
    @FXML
    private TableColumn<Recette1, String> date;
    @FXML
    private Button btn_accepter;
    @FXML
    private Button btn_traiter;
    @FXML
    private Button notif;
    @FXML
    private Button Historique;
    @FXML
    private Label nb_notif;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btnHome;
    @FXML
    private Button btn_chart;
    @FXML
    private TextField id_txt;
    @FXML
    private TextField id_nom_rec;
    @FXML
    private Button btn_stat;

    public TableView<Recette1> getTab_declaration() {
        return tab_declaration;
    }

    public TableColumn<Recette1, Integer> getId() {
        return id;
    }

    public TableColumn<Recette1, String> getNom() {
        return nom;
    }

    public TableColumn<Recette1, String> getDescription() {
        return description;
    }

    public TableColumn<Recette1, String> getEtat() {
        return etat;
    }

    public TableColumn<Recette1, String> getAction() {
        return action;
    }

    public TableColumn<Recette1, String> getDate() {
        return date;
    }

    public Button getBtn_accepter() {
        return btn_accepter;
    }

    public Button getBtn_traiter() {
        return btn_traiter;
    }

    public Button getNotif() {
        return notif;
    }

    public Button getHistorique() {
        return Historique;
    }

    public Label getNb_notif() {
        return nb_notif;
    }

    public void setTab_declaration(TableView<Recette1> tab_declaration) {
        this.tab_declaration = tab_declaration;
    }

    public void setId(TableColumn<Recette1, Integer> id) {
        this.id = id;
    }

    public void setNom(TableColumn<Recette1, String> nom) {
        this.nom = nom;
    }

    public void setDescription(TableColumn<Recette1, String> description) {
        this.description = description;
    }

    public void setEtat(TableColumn<Recette1, String> etat) {
        this.etat = etat;
    }

    public void setAction(TableColumn<Recette1, String> action) {
        this.action = action;
    }

    public void setDate(TableColumn<Recette1, String> date) {
        this.date = date;
    }

    public void setBtn_accepter(Button btn_accepter) {
        this.btn_accepter = btn_accepter;
    }

    public void setBtn_traiter(Button btn_traiter) {
        this.btn_traiter = btn_traiter;
    }

    public void setNotif(Button notif) {
        this.notif = notif;
    }

    public void setHistorique(Button Historique) {
        this.Historique = Historique;
    }

    public void setNb_notif(Label nb_notif) {
        this.nb_notif = nb_notif;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Recette1Services ds = new Recette1Services();
        ArrayList<Recette1> recette1 = null;
        try {
            recette1 = (ArrayList<Recette1>) ds.getAllRecette1();
        
        } catch (SQLException ex) {
            Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(recette1);
        tab_declaration.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        
          
   LocalDateTime now = LocalDateTime.now();  
   
   Recette1 selectedDeclaration = (Recette1) tab_declaration.getSelectionModel().getSelectedItem();
   int c = 0;
   for(Recette1 d : recette1){
       if(d.getAction().equalsIgnoreCase("non traité")){
         LocalDate da= d.getDate().toLocalDate();
          
       
       if(compare(now, da)){
           c= Integer.parseInt(nb_notif.getText())+1;
          
               }
   }
   }
   nb_notif.setText(Integer.toString(c));
   ///////////////////////////////
   
    btn_stat.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               Recette1Services s=new Recette1Services();
               Recette1 r=new Recette1();
               int a=0;int b=0;int c=0;
                 try {
              a=s.getNombre(r.getId());
               
              
                 } catch (SQLException ex) {
                     Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                         

             }
    });
   
   
   
    }    

    @FXML
    private void accepter(ActionEvent event)  throws SQLException {
       try{ Recette1Services d = new Recette1Services();
        Recette1 selectedDeclaration = (Recette1) tab_declaration.getSelectionModel().getSelectedItem();
        d.ModifierEtat(selectedDeclaration);
        Recette1Services ds = new Recette1Services();
        ArrayList<Recette1> declarations = null;
        try {
            declarations = (ArrayList<Recette1>) ds.getAllRecette1();
        } catch (SQLException ex) {
            Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(declarations);
       tab_declaration.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
       
       }
       catch(NullPointerException ex){
           ex.getMessage();
       }

        
    }
  public   boolean compare (LocalDateTime d2, LocalDate d1){
 if (d1.getYear()>d2.getYear())
     return false;
 if (d1.getYear()==d2.getYear()){
     if (d1.getMonthValue()>d2.getMonthValue()){
         return false;
     }
 }
  if (d1.getYear()==d2.getYear()){
     if (d1.getMonthValue()>d2.getMonthValue()){
        if(d1.getDayOfMonth()-d2.getDayOfMonth()>=7)
        {
         return false;
     }
 }
 
 }
        return true;
        
} 
    
    
    
    @FXML
    private void traiter(ActionEvent event)  throws SQLException {
      try{  Recette1Services d = new Recette1Services();
        Recette1 selectedDeclaration = (Recette1) tab_declaration.getSelectionModel().getSelectedItem();
        //
           int c = 0;
        LocalDateTime now = LocalDateTime.now();  
        LocalDate da= selectedDeclaration.getDate().toLocalDate();
          
          if((selectedDeclaration.getAction()=="non traité")&&(compare(now, da))){
              c= Integer.parseInt(nb_notif.getText())-1;
          }
          nb_notif.setText(Integer.toString(c));
          
          
           d.ModifierTraiter(selectedDeclaration);
        Recette1Services ds = new Recette1Services();
        ArrayList<Recette1> declarations = null;
        try {
            declarations = (ArrayList<Recette1>) ds.getAllRecette1();
        } catch (SQLException ex) {
            Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(declarations);

         tab_declaration.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
      }
      catch(NullPointerException ex){
          ex.getMessage();
      }
        
        
        
        
        
        
    }

    @FXML
    private void Surpprimer(ActionEvent event)  throws SQLException, IOException {
       try{ Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    Recette1Services s=new Recette1Services();
    HistoriqueServices h=new HistoriqueServices();
    CommentaireRecetteServices y=new CommentaireRecetteServices();
    Recette1 selectedDeclaration = (Recette1) tab_declaration.getSelectionModel().getSelectedItem();
    h.ajouterRecette(selectedDeclaration);
    s.deleteRecette(selectedDeclaration.getId());
    y.deleteComment(selectedDeclaration.getId());
     Recette1Services ds = new Recette1Services();
        ArrayList<Recette1> recette1 = null;
        try {
            recette1 = (ArrayList<Recette1>) ds.getAllRecette1();
        //    ID_1.setText(Integer.toString(ds.getilham(15)));
        } catch (SQLException ex) {
            Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(recette1);
        tab_declaration.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        
} else {
    // ... user chose CANCEL or closed the dialog
}
}catch(NullPointerException ex){
        ex.getMessage();
        }

        
        
        
        
        
        
    }

    @FXML
    private void get_notif(ActionEvent event) {
        
        
        Recette1Services ds = new Recette1Services();

        ArrayList<Recette1> declarations = null;
        try {
            declarations = (ArrayList<Recette1>) ds.getRecetteNonTraite();
        //    ID_1.setText(Integer.toString(ds.getilham(15)));
        } catch (SQLException ex) {
            Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(declarations);
       tab_declaration.setItems(obs);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        
        
        
        
    }

    @FXML
    private void Consulter_Historique(ActionEvent event) {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("./Historique.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Recette1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Historique.getScene().setRoot(root);   
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void stat(ActionEvent event) {
    }
    
}
