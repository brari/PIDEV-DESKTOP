/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import pidev.entities.CellPersonalise;
import pidev.entities.CommandeProduit;
import pidev.entities.Produit;
import pidev.service.CommandeProduitService;
import pidev.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author Insaf-Nefzi
 */
public class AfficherComdProFXMLController implements Initializable {

    @FXML
    private Button Retourcmd;
         List<String> liste = new ArrayList<>();
    @FXML
    private ListView<CommandeProduit> LVC;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView img;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
CommandeProduitService cps = new CommandeProduitService();
// LVC.setCellFactory((param) -> new CellPersonalise());
         try {
          
            affiche ();
            } catch (SQLException ex) {
             // Logger.getLogger(AfficherListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

      
//        try {
//            ArrayList<CommandeProduit> cmdpro = (ArrayList<CommandeProduit>) cps.getAllComdPro();
//            ObservableList obs = FXCollections.observableArrayList(cmdpro );
//            TVC.setItems(obs);
//            nompro.setCellValueFactory(new PropertyValueFactory<CommandeProduit,String>("nompro"));
//
//            prixpro.setCellValueFactory(new PropertyValueFactory<CommandeProduit,Float>("prixpro"));
//            catpro.setCellValueFactory(new PropertyValueFactory<CommandeProduit,String>("categoriepro"));
//            detpro.setCellValueFactory(new PropertyValueFactory<CommandeProduit,String>("detailspro"));
//            name.setCellValueFactory(new PropertyValueFactory<CommandeProduit,String>("nompat"));
//         datepro.setCellValueFactory(new PropertyValueFactory<CommandeProduit,String>("date"));   
//
//            
//        } catch (SQLException ex) {    
//            Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }   
    }    

    @FXML
    private void Retourcmd(ActionEvent event) {
         
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilClientFXML.fxml"));
                Parent root; 
                try {
                    root=loader.load();
                    Retourcmd.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherComdProFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    

                  
    
    
    
    public void affiche () throws SQLException {

        CommandeProduitService ps =new CommandeProduitService();
	    
                  ObservableList<CommandeProduit> items = FXCollections.observableArrayList(ps.getAllComdPro());
                  
                 // LVC.setCellFactory((param) -> new CellP());

        LVC.setCellFactory((ListView<CommandeProduit> arg0) -> {
            ListCell<CommandeProduit> cell = new ListCell<CommandeProduit>() {
                @Override
                protected void updateItem(CommandeProduit e, boolean btl) {
                    super.updateItem(e, btl);

                    if (e != null) {
            
                        File file = new File("src\\images\\images (24).jpg");
                        file.getParentFile().mkdirs();
                        Image IMAGE_RUBY = new Image(file.toURI().toString());
                         //Image IMAGE_RUBY = new Image(ps.findById(e.getPassager().getIdpro()).getAvatar());

                        ImageView imgview = new ImageView(IMAGE_RUBY);

                        setGraphic(imgview);

                        imgview.setFitHeight(50);
                        imgview.setFitWidth(50);
                        Rectangle clip = new Rectangle(
                                imgview.getFitWidth(), imgview.getFitHeight()
                        );

                        clip.setArcWidth(20);
                        clip.setArcHeight(20);
                        imgview.setClip(clip);

                        // snapshot the rounded image.
                        SnapshotParameters parameters = new SnapshotParameters();
                        parameters.setFill(Color.TRANSPARENT);
                        WritableImage image = imgview.snapshot(parameters, null);

                        // remove the rounding clip so that our effect can show through.
                        imgview.setClip(null);

                        // apply a shadow effect.
                        imgview.setEffect(new DropShadow(20, Color.BLACK));

                        // store the rounded image in the imageView.
                        imgview.setImage(image);
//                        
//     ListProd.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                            
//
//          @Override
//  public void handle(MouseEvent event) {
//                           
//    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
//                                  
//               try {
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile_veterinaire.fxml"));
//                                              
//        Parent root =loader.load();
//        Profile_veterinaireController pc = loader.getController();
//
// Veterinaire v=  (list_utilisateur.getSelectionModel().getSelectedItem());
//       
//         pc.veteo(v.getAdresse_cabinet());
//         pc.veteo_all(v);
//           System.out.println(v.getAdresse_cabinet());
//           Stage stage=(Stage) profile.getScene().getWindow();
//        stage.close();
//        
//        Stage s = new Stage ();
//    s.setScene(new Scene (root));    
//    s.show();
//    
//    
//    } catch (IOException ex) {
//        System.out.println(ex.getMessage());
//    }
//                            
//                                    
//
//
//           }
//    }
//   });
                                   // System.out.println(e.getId_utilisateur());
  setText("Nom:    "+e.getNompro()+ "\n" + "Prix      :" + e.getPrixpro() +"DT"+
          "\n" + "categorie    :" + e.getCategoriepro()+ "\n" + "Details    :" 
          + e.getDetailspro()+ "\n" +"Nom Patisserie    :" + e.getNompat()+ "\n" +"date de la commande    :" + e.getDate());
 setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                        // setAlignment(Pos.CENTER);
                    }
                    
                    
                    

                        
                }

            };
            return cell;
        });
        LVC.setItems(items);
        
  

       
        // TODO
    }    
    
    
    
    
}

