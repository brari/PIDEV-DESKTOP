/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisserie.ui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pidev.entities.CommandeProduit;
import pidev.entities.Produit;
import pidev.service.CommandeProduitService;
import pidev.service.ProduitService;
import pidev.ui.AjouterFXMLController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherProdFXMLController implements Initializable {

    @FXML
    private Button btnR;
    @FXML
    private TableView<Produit> TVP;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, Float> prix;
    @FXML
    private TableColumn<Produit, String> cat;
    @FXML
    private TableColumn<Produit, String> det;
    @FXML
    private TableColumn<Produit, String> name;
   
    @FXML
    private AnchorPane pane1;
    Produit pr;
  
    @FXML
    private ImageView pa_icone;
   String path=" ";
    @FXML
    private Text filtrer;
    @FXML
    private ComboBox<String> comboprF;
    @FXML
    private TextField prRecherche;
    @FXML
    private Button BtnFiltrer;
    @FXML
    private Button refrech;
   // private WebView webview1;
    @FXML
    private Button btncomprod;
    @FXML
    private Button btnAfficherCmdPro;
    @FXML
    private Button btnstat;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ProduitService ps = new ProduitService();
 //comboprF.getItems().addAll("nom","adresse");
comboprF.getItems().addAll("categoriepro","nompro","detailspro");
        try {
            ArrayList<Produit> produits = (ArrayList<Produit>) ps.getAllProducts();
            ObservableList obs = FXCollections.observableArrayList(produits);
            TVP.setItems(obs);
            nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nompro"));

            prix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prixpro"));
            cat.setCellValueFactory(new PropertyValueFactory<Produit,String>("categoriepro"));
            det.setCellValueFactory(new PropertyValueFactory<Produit,String>("detailspro"));
            name.setCellValueFactory(new PropertyValueFactory<Produit,String>("nompat"));   
         
        } catch (SQLException ex) {    
            Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


  

    @FXML
    private void Filtrer(ActionEvent event) throws SQLException {
        
           if(prRecherche.getText().equals(""))
                  {Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Saisissez la valeur");
                        alert.showAndWait();}
           else{
                try{
                String filtre=comboprF.getValue();
                String valeur=prRecherche.getText();
                ProduitService ps=new ProduitService();

                   
                        try {
                    ArrayList <Produit> produits=(ArrayList < Produit >) ps.rechercherProduit(filtre, valeur);
                    ObservableList obs=FXCollections.observableArrayList(produits);
                    TVP.setItems(obs);
                    nom.setCellValueFactory(new PropertyValueFactory<>("nompro"));
                    prix.setCellValueFactory(new PropertyValueFactory<>("prixpro"));
                    cat.setCellValueFactory(new PropertyValueFactory<>("categoriepro"));
                    det.setCellValueFactory(new PropertyValueFactory<>("detailspro"));
                     name.setCellValueFactory(new PropertyValueFactory<>("nompat"));
                 }catch (SQLException ex) {
                    Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }catch(NullPointerException e){
                 
                
                }
                
                
            
        
    }}

    @FXML
    private void Refrech(ActionEvent event) {
        
        
         ProduitService ps= new ProduitService();
        try {
            ArrayList <Produit> produits=(ArrayList < Produit >) ps.getAllProducts();
            ObservableList obs=FXCollections.observableArrayList(produits);
            TVP.setItems(obs);
                    nom.setCellValueFactory(new PropertyValueFactory<>("nompro"));
                    prix.setCellValueFactory(new PropertyValueFactory<>("prixpro"));
                    cat.setCellValueFactory(new PropertyValueFactory<>("categoriepro"));
                    det.setCellValueFactory(new PropertyValueFactory<>("detailspro"));
                     name.setCellValueFactory(new PropertyValueFactory<>("nompat"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void AfficherImage(MouseEvent event) {
        TVP.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        
 @Override
                
            public void handle(MouseEvent event) {
                int index=TVP.getSelectionModel().getSelectedIndex();
                if(TVP.getSelectionModel().isSelected(index)){
                    ProduitService ps= new ProduitService();
                    pr=(Produit)TVP.getSelectionModel().getSelectedItem();
                    Image image = new Image(pr.getImage());
                    pa_icone.setImage(image);
//                    try{
//                        // InputStream inputStream= new FileInputStream(pr.getImage());
//           
//                       pa_icone.setImage(ps.extraireImage(pr.getIdpro()));
//                    }catch(NullPointerException e){
//                        pa_icone.setImage(null);
//                        //System.out.println("Fichier off");
//                    } catch (SQLException ex) {
//                        Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                           }
            }
        });
    }

    
    
    public void ChangeNomEvent(TableColumn.CellEditEvent edeted){
      // TableView.TableViewSelectionModel<Produit> produitselect=affpr.getSelectionModel();
       Produit produitselect=TVP.getSelectionModel().getSelectedItem();
       produitselect.setNompat(edeted.getNewValue().toString());
    }
     public void ChangePrixEvent(TableColumn.CellEditEvent edeted){
      // TableView.TableViewSelectionModel<Produit> produitselect=affpr.getSelectionModel();
       Produit produitselect=TVP.getSelectionModel().getSelectedItem();
       produitselect.setPrixpro(Float.valueOf(edeted.getNewValue().toString()));
    }
    public void ChangeCatEvent(TableColumn.CellEditEvent edeted){
      // TableView.TableViewSelectionModel<Produit> produitselect=affpr.getSelectionModel();
       Produit produitselect=TVP.getSelectionModel().getSelectedItem();
       produitselect.setCategoriepro(edeted.getNewValue().toString());
    }
    public void ChangedetEvent(TableColumn.CellEditEvent edeted){
      // TableView.TableViewSelectionModel<Produit> produitselect=affpr.getSelectionModel();
       Produit produitselect=TVP.getSelectionModel().getSelectedItem();
       produitselect.setDetailspro(edeted.getNewValue().toString());
    }
     public void ChangeNameEvent(TableColumn.CellEditEvent edeted){
      // TableView.TableViewSelectionModel<Produit> produitselect=affpr.getSelectionModel();
       Produit produitselect=TVP.getSelectionModel().getSelectedItem();
       produitselect.setNompat(edeted.getNewValue().toString());
    }

 

    @FXML
    private void btnCommanderProd(ActionEvent event) throws SQLException {
           AnchorPane pane=new AnchorPane();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");

          Date date=new Date();
           System.out.println(sdf.format(date));
           String dat="";
           dat+=date;
         if (!TVP.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" ");
            alert.setHeaderText("Etes-vous sur que vous voulez commander le produit : "
                    + TVP.getSelectionModel().getSelectedItem().getNompro()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                
                
                
                CommandeProduitService cps=new CommandeProduitService();
                CommandeProduit cp=new CommandeProduit(2,TVP.getSelectionModel().getSelectedItem().getIdpro(),
                                 TVP.getSelectionModel().getSelectedItem().getNompro(), 
                                 TVP.getSelectionModel().getSelectedItem().getPrixpro(),
                                 TVP.getSelectionModel().getSelectedItem().getCategoriepro(),
                                 TVP.getSelectionModel().getSelectedItem().getDetailspro(),
                        TVP.getSelectionModel().getSelectedItem().getNompat(),
                                 TVP.getSelectionModel().getSelectedItem().getImage(),dat);
                cps.ajouterComdPro(cp);
                
                  
                        
                
                
                
                try {
                    
                    pane = FXMLLoader.load(getClass().getResource("AfficherProdFXML.fxml"));
                } catch (IOException ex) {
                  //  Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
               pane1.getChildren().setAll(pane);
               
                 
              
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un produit  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
        
    }

    @FXML
    private void btnAfficheCmdPro(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader ( getClass().getResource("./AfficherComdProFXML.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      btnAfficherCmdPro.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void btnstat(ActionEvent event) {
         FXMLLoader loader= new FXMLLoader ( getClass().getResource("./StatistiquesProdFXML.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      btnR.getScene().setRoot(root);
                    } catch (IOException ex) {
                   //     Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   // }//pour que le bouton ouvre une autre interface
    }
}

    @FXML
    private void btnRetour(ActionEvent event) {
    }

  
}
