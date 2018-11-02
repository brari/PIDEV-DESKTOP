/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pidev.entities.CommentaireEvent;
import pidev.service.CommentaireRecetteServices;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CommentController implements Initializable {

    @FXML
    private TableView<CommentaireEvent> tab_commentaireEvent;
    @FXML
    private TableColumn<CommentaireEvent, Integer> id_utilisateur;
    //private TableColumn<CommentaireEvent, Integer> id_Event;
    @FXML
    private TableColumn<CommentaireEvent, String> comment;
    @FXML
    private TableColumn<CommentaireEvent, Date> date;
    @FXML
    private TableColumn<CommentaireEvent, Integer> id_comment;
    @FXML
    private Button btn_SuppComment;
    @FXML
    private Button Commenter;
    @FXML
    private Button retour;
    @FXML
    private TextArea tf_comment;
    @FXML
    private Text text;
    @FXML
    private Label code_Event;
    @FXML
    private TableColumn<CommentaireEvent, Integer> id;
    @FXML
    private Button btn_modif_comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public TableView<CommentaireEvent> getTab_commentaireEvent() {
        return tab_commentaireEvent;
    }

    public void setTab_commentaireEvent(TableView<CommentaireEvent> tab_commentaireEvent) {
        this.tab_commentaireEvent = tab_commentaireEvent;
    }

    public TableColumn<CommentaireEvent, Integer> getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(TableColumn<CommentaireEvent, Integer> id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

   

    

    public TableColumn<CommentaireEvent, String> getComment() {
        return comment;
    }

    public void setComment(TableColumn<CommentaireEvent, String> comment) {
        this.comment = comment;
    }

    public TableColumn<CommentaireEvent, Date> getDate() {
        return date;
    }

    public void setDate(TableColumn<CommentaireEvent, Date> date) {
        this.date = date;
    }

    public TableColumn<CommentaireEvent, Integer> getId_comment() {
        return id_comment;
    }

    public void setId_comment(TableColumn<CommentaireEvent, Integer> id_comment) {
        this.id_comment = id_comment;
    }

    public Button getCommenter() {
        return Commenter;
    }

    public void setCommenter(Button Commenter) {
        this.Commenter = Commenter;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Button getBtn_SuppComment() {
        return btn_SuppComment;
    }

    public void setBtn_SuppComment(Button btn_SuppComment) {
        this.btn_SuppComment = btn_SuppComment;
    }

    public Label getCode_Event() {
        return code_Event;
    }

    public void setCode_Event(Label code_Event) {
        this.code_Event = code_Event;
    }

    public TableColumn<CommentaireEvent, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<CommentaireEvent, Integer> id) {
        this.id = id;
    }
    
    
    
    
    @FXML
    private void supprimer_commentEvent(ActionEvent event) throws SQLException {
        CommentaireRecetteServices  cs=new   CommentaireRecetteServices ();
        CommentaireEvent c=tab_commentaireEvent.getSelectionModel().getSelectedItem();
        cs.supprimerCommentaire(c);
         ArrayList<CommentaireEvent> comments =(ArrayList<CommentaireEvent>) cs.getAllCommentaire(Integer.parseInt(code_Event.getText()));
              ObservableList obs =FXCollections.observableArrayList(comments);
           tab_commentaireEvent.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
           comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
               id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                id_comment.setCellValueFactory(new PropertyValueFactory<>("id_comment"));
        
        
    }

    @FXML
    private void Commenter(ActionEvent event) throws SQLException {
         CommentaireRecetteServices  cs=new   CommentaireRecetteServices ();
           CommentaireEvent e= new CommentaireEvent(Integer.parseInt(code_Event.getText()), tf_comment.getText());
    cs.ajouterCommentaire(e);
    
       ArrayList<CommentaireEvent> comments =(ArrayList<CommentaireEvent>) cs.getAllCommentaire(Integer.parseInt(code_Event.getText()));
              ObservableList obs =FXCollections.observableArrayList(comments);
           tab_commentaireEvent.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
           comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
               id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
                id_comment.setCellValueFactory(new PropertyValueFactory<>("id_comment"));
        
        
        
        
        
    }

    @FXML
    private void retourToEvent(ActionEvent event) {
        
        
        FXMLLoader loder =new FXMLLoader(getClass().getResource("./AfficheRecette.fxml"));
                    Parent root = null;
        try {
            root=loder.load();
        } catch (IOException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        retour.getScene().setRoot(root);
    }

    @FXML
    private void Modifier_CommentEvent(ActionEvent event) {
    }
    
}
