/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXDrawer;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pidev.entities.Users;
import pidev.service.Userservice;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private TableView<?> TableUsers;
    @FXML
    private TableColumn<?, ?> first_name;
    @FXML
    private TableColumn<?, ?> last_name;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> address;
    @FXML
    private TableColumn<?, ?> enabled;
    @FXML
    private Button Activer;
    @FXML
    private Button Desactiver;
    @FXML
    private Button delete;
    @FXML
    private JFXDrawer drawer;
VBox sidepane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
        sidepane=FXMLLoader.load(getClass().getResource("./drawerAcceuilAdmin.fxml"));
        drawer.setSidePane(sidepane);
    } catch (IOException ex) {
        Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        // TODO
        Userservice ps = new Userservice();
    
        try {
            ArrayList<Users> users = (ArrayList<Users>) ps.getAllUsers();
            ObservableList obs = FXCollections.observableArrayList(users);
            TableUsers.setItems(obs);
            first_name.setCellValueFactory(new PropertyValueFactory<>("First_name"));

            last_name.setCellValueFactory(new PropertyValueFactory<>("Last_name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
          //  mobile_num.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
            address.setCellValueFactory(new PropertyValueFactory<>("Address")); 
           enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));   

        } catch (SQLException ex) {    
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Activer(MouseEvent event) throws SQLException {
    Users  use = new Users ();
         
                  Userservice ps = new Userservice();
use= (Users) TableUsers.getSelectionModel().getSelectedItem();

                      ArrayList<Users> users = (ArrayList<Users>) ps.getAllUsers();

                      ObservableList obs = FXCollections.observableArrayList(users);

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Confirmation");
                alert1.setHeaderText("Confirmer modification");
                alert1.setContentText("Vous êtes sûre ?");
                Optional<ButtonType> result1 =alert1.showAndWait();
                if (result1.get()==ButtonType.OK) {
                   ps.change(use.getUser_id(),1);
                    TableUsers.setItems(obs);
                    //System.out.println(value);
        
         TableUsers.setItems(obs);
            first_name.setCellValueFactory(new PropertyValueFactory<>("First_name"));

            last_name.setCellValueFactory(new PropertyValueFactory<>("Last_name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
          //  mobile_num.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
            address.setCellValueFactory(new PropertyValueFactory<>("Address")); 
           enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));   
    }

 
    
    
}

    @FXML
    private void Desactiver(MouseEvent event) throws SQLException {
                    Users  use = new Users ();
         
                  Userservice ps = new Userservice();
use=                (Users) TableUsers.getSelectionModel().getSelectedItem();

                      ArrayList<Users> users = (ArrayList<Users>) ps.getAllUsers();

                      ObservableList obs = FXCollections.observableArrayList(users);
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmer modification");
                alert.setContentText("Vous êtes sûre ?");
                Optional<ButtonType> result =alert.showAndWait();
                if (result.get()==ButtonType.OK) {
                  ps.change(use.getUser_id(),0);
                    TableUsers.setItems(obs);
       //System.out.println(value);
        
  TableUsers.setItems(obs);
            first_name.setCellValueFactory(new PropertyValueFactory<>("First_name"));

            last_name.setCellValueFactory(new PropertyValueFactory<>("Last_name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
          //  mobile_num.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
            address.setCellValueFactory(new PropertyValueFactory<>("Address")); 
           enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));   
    }
}

    @FXML
    private void delete(MouseEvent event) throws SQLException, IOException {
        
        
        
//          AnchorPane pane=new AnchorPane();
//        
//         if (!TableUsers.getSelectionModel().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle(" ");
//            alert.setHeaderText("Etes-vous sur que vous voulez supprimer  : "
//                    + TableUsers.getSelectionModel().getSelectedItem().getFirst_name()+ "?");
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                Userservice ps=new Userservice();
//                ps.SupprimerUser(TableUsers.getSelectionModel().getSelectedItem().getUser_id());
//                
//                try {
//                    
//                    pane = FXMLLoader.load(getClass().getResource("admin.fxml"));
//                } catch (IOException ex) {
//                    Logger.getLogger(AdminController_1.class.getName()).log(Level.SEVERE, null, ex);
//                }
//               pane1.getChildren().setAll(pane);
//               
//                 
//              
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Erreur de selection");
//            alert.setHeaderText("Vous etes obligé de selectioner un produit  ");
//
//            Optional<ButtonType> result = alert.showAndWait();
//        }
//    }
}

    @FXML
    private void hide(MouseEvent event) {
        drawer.close();
        drawer.setOpacity(0f);
    }

    @FXML
    private void show(MouseEvent event) {
         drawer.setOpacity(1f);
        if(!drawer.isShown())
        drawer.open();
        else 
            drawer.open();
    }
    
}
