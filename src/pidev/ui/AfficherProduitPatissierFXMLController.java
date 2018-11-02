/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

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
import com.jfoenix.controls.JFXDrawer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pidev.entities.Produit;
import pidev.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherProduitPatissierFXMLController implements Initializable {

    @FXML
    private Button modif;
    private Button ajouter;
    @FXML
    private Button supp;
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
    @FXML
    private ImageView pa_icone;
 Produit pr;
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
    @FXML
    private TextField modnom;
    @FXML
    private TextField modprix;
    @FXML
    private TextField modcat;
    @FXML
    private TextField moddet;
    @FXML
    private TextField modname;
    @FXML
    private TextField modimg;
    @FXML
    private Button ParcModPr;
    @FXML
    private Button SauvgarderMod;
    @FXML
    private Button btnpdf;
    String path="";
     VBox sidepane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         drawer.setOpacity(0f);
//        
//        try {
//            sidepane = FXMLLoader.load(getClass().getResource("./drawerviewClientMenu.fxml"));
//            drawer.setSidePane(sidepane);
//        } catch (IOException ex) {
//            Logger.getLogger(AccueilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
           // Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void modif(ActionEvent event) {
         int index=TVP.getSelectionModel().getSelectedIndex();
                 if (!TVP.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" ");
            alert.setHeaderText("Etes-vous sur que vous voulez Modifier le produit : "
                    + TVP.getSelectionModel().getSelectedItem().getNompro()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if(TVP.getSelectionModel().isSelected(index)){
                    pr=(Produit)TVP.getSelectionModel().getSelectedItem();
                    modnom.setText(pr.getNompro());
                    
                    String prix=Float.toString(pr.getPrixpro());
                     modprix.setText(prix);
                    modcat.setText(pr.getCategoriepro());
                   
                    moddet.setText(pr.getDetailspro());
                    modname.setText(pr.getNompat());
                    modimg.setText(pr.getImage());
                    
                }}
      
        
    }
                 else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un produit  ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    private void ajouter(ActionEvent event) {
            FXMLLoader loader= new FXMLLoader ( getClass().getResource("./AjouterProduitFXML.fxml"));
                    Parent root ;
                    try {
                       root= loader.load();
                      ajouter.getScene().setRoot(root);
                    } catch (IOException ex) {
                      //  Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                   // }//pour que le bouton ouvre une autre interface
    }
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
         AnchorPane pane=new AnchorPane();
        
         if (!TVP.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" ");
            alert.setHeaderText("Etes-vous sur que vous voulez supprimer le produit : "
                    + TVP.getSelectionModel().getSelectedItem().getNompro()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ProduitService ps=new ProduitService();
                ps.SupprimerProduit(TVP.getSelectionModel().getSelectedItem().getIdpro());
                
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

    @FXML
    private void Filtrer(ActionEvent event) {
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
                  //  Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }catch(NullPointerException e){
                 
                
                }
                
                
            
        
    }
    }

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
            //Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void FileSelected(ActionEvent event) {
          ParcModPr.setOnAction(new EventHandler<ActionEvent>() {
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
                final File fichier=dialog.showOpenDialog(ParcModPr.getScene().getWindow());
               
                path=fichier.getAbsolutePath();
                
                modimg.setText(fichier.getName());
                
                //récuperer l'image choisie
                File file = new File(path);
                Image image = new Image(file.toURI().toString());
                pa_icone.setImage(image);
            }
        });
    }

    @FXML
    private void Sauvegarder(ActionEvent event) {
         if(modnom.getText().equals("")||modprix.getText().equals("")|| modcat.getText().equals("")||moddet.getText().equals("")||modname.getText().equals("")||modimg.getText().equals("")){
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Modifications");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez saisir les donnees convenables");
                    alert.showAndWait();
                }
            else{
            try{
                float pri=Float.parseFloat(modprix.getText());
                
                if(pri<=0){
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Modifications");
                    alert.setHeaderText(null);
                    alert.setContentText("Le prix ne peut pa etre negatif ou egal a zero");
                    alert.showAndWait();
                }
                   else if(modnom.getText().length()<4){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Le Nom du Produit que vous voulez ajouter n'est pas valide");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
                  else if(modcat.getText().length()<4){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("La categorie du Produit que vous voulez ajouter n'est pas valide");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
                               else if(moddet.getText().length()<4){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Les Details du Produit que vous voulez ajouter n'est pas valide");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
                                            else if(modname.getText().length()<4){
         
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Ajout refusé ");
                                            alert.setHeaderText("Le nom de patisserie que vous voulez ajouter ne sont pas valide");
                                            Optional<ButtonType> result = alert.showAndWait(); 
         
     }
    else if(modnom.getText().matches("[a-zA-Z]+")&&modcat.getText().matches("[a-zA-Z]+")
            &&moddet.getText().matches("[a-zA-Z]+")&&modname.getText().matches("[a-zA-Z]+")){
                  pr.setNompro(modnom.getText());
                    pr.setPrixpro(pri);
                    pr.setCategoriepro(modcat.getText());
                    
                    pr.setDetailspro(moddet.getText());
                    pr.setNompat(modname.getText());
                    pr.setImage(path);
                    ProduitService ps=new ProduitService();//connexion établie
                    try {
                        ps.modifierProduit(pr);
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Modifications enregistrées");
                        alert.showAndWait();
                        
                        modnom.clear(); modprix.clear();
                        modcat.clear(); moddet.clear();
                        modimg.clear(); pa_icone.setImage(null);
                        
            try {
            ArrayList <Produit> produits=(ArrayList < Produit >) ps.getAllProducts();
            ObservableList obs=FXCollections.observableArrayList(produits);
            TVP.setItems(obs);
            nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nompro"));

            prix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prixpro"));
            cat.setCellValueFactory(new PropertyValueFactory<Produit,String>("categoriepro"));
            det.setCellValueFactory(new PropertyValueFactory<Produit,String>("detailspro"));
            name.setCellValueFactory(new PropertyValueFactory<Produit,String>("nompat")); 
            
            
        } catch (SQLException ex) {
           // Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println("Erreur d'ajout");
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Erreur:Modifications non enregistrées");
                        alert.showAndWait();
                    }
                
                }
               
            }catch(NumberFormatException e){
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Modification");
                alert.setHeaderText(null);
                alert.setContentText("Le contact est un nombre");
                alert.showAndWait();
            }
        }
         
               
    }

    @FXML
    private void btnpdf(ActionEvent event) {
                 btnpdf.setOnAction(new EventHandler<ActionEvent>() {
           @Override
            public void handle(ActionEvent event) {
//          JOptionPane jop = new JOptionPane();
//    String ad = jop.showInputDialog(null, "Veuillez saisir l'emplacement du ficher !", JOptionPane.QUESTION_MESSAGE);
               // "C:\\Users\\pc-dell\\Desktop\\ad.pdf";
               
 String ad="C:\\Users\\Insaf-Nefzi\\Desktop\\Patisserie.pdf";

                Document doc=new Document();
               try {
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream(ad));
                    } catch (DocumentException ex) {
                      //  Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
               } catch (FileNotFoundException ex) {
                 Alert alerta = new Alert(Alert.AlertType.ERROR);
                         
                         alerta.setTitle("Notification");
                         alerta.setContentText("veuillez saisir un chemin VALIDE ! ");
                         alerta.showAndWait();
               }
               doc.open();
               try {
                   
                   Font f=new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.UNDERLINE, BaseColor.RED);
                   Paragraph p=new Paragraph("Annuaire De Patisserie\n",f);
                   p.setAlignment(Element.ALIGN_CENTER);
                   doc.add(p);
Font f1=new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.UNDERLINE, BaseColor.RED);
                   Paragraph p1=new Paragraph("Rapports des Produits",f1);
                   p1.setAlignment(Element.ALIGN_CENTER);
                   doc.add(p1);
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
String d=   dtf.format(now);
 Font f2=new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE, BaseColor.BLACK);
 Paragraph p2=new Paragraph("\nDate: "+d,f2);
   doc.add(p2);
      doc.add(new Paragraph("Gerant responsable : " ,f2));
      doc.add(new Paragraph(" "));
                   PdfPTable table = new PdfPTable(5);
                   table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        
                   Font f3=new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDEFINED, BaseColor.BLUE);
                   PdfPCell c1=new PdfPCell(new Phrase("Nom du Produit",f3));
                   c1.setPadding(4f);
                   table.addCell(c1);
                   
                     c1=new PdfPCell(new Phrase("Prix du Produit ",f3));
                     c1.setPadding(4f);     
                   table.addCell(c1);
                   
                     c1=new PdfPCell(new Phrase("Categorie du Produit ",f3));
                     c1.setPadding(4f);
                   table.addCell(c1);
                   
                     c1=new PdfPCell(new Phrase("Details du Produit ",f3));
                     c1.setPadding(4f);
                   table.addCell(c1);
                   
                     c1=new PdfPCell(new Phrase("Nom de la Patisserie",f3));
                     c1.setPadding(4f);
                   table.addCell(c1);
                   
                  // table.setHeaderRows(0);
                  ProduitService s=new ProduitService();
                    ArrayList<Produit> e =(ArrayList<Produit>)s.getAllProducts();
                    for(int i=0;i<e.size();i++)
                    {
                      String n=e.get(i).getNompro();
                     Font f4=new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.UNDEFINED, BaseColor.BLACK);
                       c1=new PdfPCell(new Phrase(n,f4));
                       table.addCell(c1);
                        
                        float r1=e.get(i).getPrixpro();
                        String r2="";
                        r2+=r1;
                        c1=new PdfPCell(new Phrase(r2,f4));
                        table.addCell(c1);
                        
                        String r=e.get(i).getCategoriepro();
                        c1=new PdfPCell(new Phrase(r,f4));
                        table.addCell(c1);
                        
                        String dom=e.get(i).getDetailspro();
                        c1=new PdfPCell(new Phrase(dom,f4));
                        table.addCell(c1);
                        
                        String sa=e.get(i).getNompat();
                        c1=new PdfPCell(new Phrase(sa,f4));
                        table.addCell(c1);
                      
                    }
                   doc.add(table);
               } catch (DocumentException ex) {
                //   Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                  // Logger.getLogger(AfficherProdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
                }
        }) ;
    }
    
    
}
