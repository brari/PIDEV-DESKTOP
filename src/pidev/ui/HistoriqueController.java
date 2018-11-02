/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.entities.Historique;
import pidev.service.HistoriqueServices;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class HistoriqueController implements Initializable {

    @FXML
    private TableView<Historique> tab_declaration;
    @FXML
    private TableColumn<Historique, Integer> id;
    @FXML
    private TableColumn<Historique, String> nom;
    @FXML
    private TableColumn<Historique, String> description;
    @FXML
    private TableColumn<Historique, String> etat;
    @FXML
    private TableColumn<Historique, String> action;
    @FXML
    private TableColumn<Historique, String> date;
    @FXML
    private Button btnHome;

    public TableView<Historique> getTab_declaration() {
        return tab_declaration;
    }

    public TableColumn<Historique, Integer> getId() {
        return id;
    }

    public TableColumn<Historique, String> getNom() {
        return nom;
    }

    public TableColumn<Historique, String> getDescription() {
        return description;
    }

    public TableColumn<Historique, String> getEtat() {
        return etat;
    }

    public TableColumn<Historique, String> getAction() {
        return action;
    }

    public TableColumn<Historique, String> getDate() {
        return date;
    }

    public void setTab_declaration(TableView<Historique> tab_declaration) {
        this.tab_declaration = tab_declaration;
    }

    public void setId(TableColumn<Historique, Integer> id) {
        this.id = id;
    }

    public void setNom(TableColumn<Historique, String> nom) {
        this.nom = nom;
    }

    public void setDescription(TableColumn<Historique, String> description) {
        this.description = description;
    }

    public void setEtat(TableColumn<Historique, String> etat) {
        this.etat = etat;
    }

    public void setAction(TableColumn<Historique, String> action) {
        this.action = action;
    }

    public void setDate(TableColumn<Historique, String> date) {
        this.date = date;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         HistoriqueServices ds=new HistoriqueServices();
        ArrayList<Historique> declarations = null;
        try {
            declarations = (ArrayList<Historique>) ds.getAllHis();
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
        //action.setCellValueFactory(new PropertyValueFactory<>("action"));
    }    

    @FXML
    private void retour(ActionEvent event) {
    }
    
}
