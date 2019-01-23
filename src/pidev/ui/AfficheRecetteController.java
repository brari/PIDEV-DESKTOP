/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.itextpdf.text.BaseColor;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.w3c.dom.Document;
import pidev.entities.CommentaireEvent;
import pidev.entities.Etape;
import pidev.entities.Ingredient;
import pidev.entities.Recette1;
import pidev.service.CommentaireRecetteServices;
import pidev.service.EtapeDAO;
import pidev.service.IngredientDAO;
import pidev.service.Recette1Services;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficheRecetteController implements Initializable {

    private Recette1Services servicerec = new Recette1Services();
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @FXML
    private TableView<Recette1> table;
    @FXML
    private TableColumn<Recette1, String> nomRece;
    @FXML
    private TableColumn<Recette1, String> desc;
    @FXML
    private TableColumn<Recette1, ?> phot;
    @FXML
    private TextArea tfDescrecette;
    @FXML
    private TextField tfNomrecette;
    private ImageView imageView;
    private Image image;
    private FileInputStream fis;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private AnchorPane anchorPane;
    private final Desktop deskTop = Desktop.getDesktop();
    @FXML
    private Button btn_ajout;
    private Button btn_add;
    @FXML
    private Button btn_che;
    @FXML
    private TextField txt_cherch;
    @FXML
    private Button Btn_blog;
    //private TableColumn<Recette, Integer> id_rece;
    @FXML
    private TableColumn<Recette1, Integer> id;
    @FXML
    private ImageView pa_icone;
    @FXML
    private Button pa_browse;
    @FXML
    private TextField pa_fichierchoisi;
    
     String path="";
     Recette1 pat;
    @FXML
    private Button pa_modifier;
    @FXML
    private Button pa_supprimer;
    @FXML
    private Button pa_saveChanged;
    @FXML
    private Button btn_ing;
    private Button btn_etape;
    @FXML
    private Button btn_eta;
    @FXML
    private Button btnHome;
    @FXML
    private Button btn_imprimer;
    private Button ret;
    @FXML
    private Button btn_clear;
    @FXML
    private Button btn_delete;
    @FXML
    private Button n_valid;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Recette1Services ps =new Recette1Services();
        try {
            ArrayList<Recette1>recettes=(ArrayList<Recette1>) ps.getAllRecettesaaa();
            ObservableList obs=FXCollections.observableArrayList(recettes);
            table.setItems(obs); //pour définir tout les element des tableau
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomRece.setCellValueFactory(new PropertyValueFactory<>("nom"));
            desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            
           // phot.setCellValueFactory(new PropertyValueFactory<>("photo"));
           //id_rece.setCellValueFactory(new PropertyValueFactory<>("id"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setCellValueFromTableToTextField();
        
        
        
       

//Recette1Services ds = new Recette1Services();
//        ArrayList<Recette1> declarations = null;
//       ObservableList obs = null;
//        try {
//            obs = ds.getAllRecetteForUtilisateur();
//        } catch (SQLException ex) {
//            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        table.setItems(obs); //pour définir tout les element des tableau
//           id.setCellValueFactory(new PropertyValueFactory<>("id"));
//           nomRece.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            desc.setCellValueFactory(new PropertyValueFactory<>("description"));
      

   
    }    

        private void setCellValueFromTableToTextField(){
        table.setOnMouseClicked(e -> {
            Recette1 pl = table.getItems().get(table.getSelectionModel().getSelectedIndex());
            //Recette pl = (Recette) table.getItems().get(table.getSelectionModel().getSelectedIndex());
            tfNomrecette.setText(pl.getNom());
            tfDescrecette.setText(pl.getDescription());
            
            
            
            //howProductImage(pl.getBarcode());
          
        });
        
        
    }
     private void clearTextField(){
        tfNomrecette.clear();
        tfDescrecette.clear();
        
       
    }
    
      private void setCellTable(){
        nomRece.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
            
        
    }
     private void handleBrowser(ActionEvent event) {
        
         stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        /*try {
            deskTop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        if(file != null){
            System.out.println(""+file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(), imageView.getFitHeight(), true, true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
        }
        
        
    }

    
    
    @FXML
    private void AfficherImage(MouseEvent event) {
        
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index=table.getSelectionModel().getSelectedIndex();
                if(table.getSelectionModel().isSelected(index)){
                    Recette1Services ps= new Recette1Services();
                   // Recette1 selectedCommentaire = (Recette1) table.getSelectionModel().getSelectedItem();//ajouté
                    pat=(Recette1)table.getSelectionModel().getSelectedItem();
                    try{
                        pa_icone.setImage(ps.extraireImage(pat.getId()));
                        tfNomrecette.setText(pat.getNom());
                    tfDescrecette.setText(pat.getDescription());
                        
                    }catch(NullPointerException e){
                        pa_icone.setImage(null);
                    }
        }
            }
        });  
        
        
        
    }

    @FXML
    private void handleClear(ActionEvent event) {
        
         clearTextField();
    }

    @FXML
    private void insert(ActionEvent event) throws IOException {
        
         FXMLLoader loader =new FXMLLoader(getClass().getResource("./AddRecette.fxml"));
      Parent root = null;
         root = loader.load(); 
         btn_ajout.getScene().setRoot(root);
    }
 private void update(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("./modifierRecette.fxml"));
      Parent root = null;
         root = loader.load();
         
           
        table.setOnMouseClicked(e -> {
            Recette1 pl = table.getItems().get(table.getSelectionModel().getSelectedIndex());
            //Recette pl = (Recette) table.getItems().get(table.getSelectionModel().getSelectedIndex());
            tfNomrecette.setText(pl.getNom());
            tfDescrecette.setText(pl.getDescription());
            
            
            
            //howProductImage(pl.getBarcode());
          
        });
         
//    Recette x=(Recette) table.getSelectionModel().getSelectedItem();
//  RecetteDAO ps=new RecetteDAO();
//  ModifierRecetteController s=loader.getController();
//  btn_update.getScene().setRoot(root);
//  
//  s.getTfNomrecette().setText(x.getNom());
//
//  s.getTfDescrecette().setText(x.getDescription());
  
     
        
    }
    
   
    
    
    
    
    @FXML
    private void delete(ActionEvent event) throws SQLException  {///it works :)
        
//        Recette p=(Recette)table.getSelectionModel().getSelectedItems();
//        RecetteDAO ps= new RecetteDAO();
//        ps.deleteRecette(p);
        
        
Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("confirmation");
            alerte.setHeaderText("voulez vous vraiment supprimer?");
            Optional<ButtonType> result = alerte.showAndWait();
            if (result.get() == ButtonType.OK) {
                
                servicerec.supprimer(table.getSelectionModel().getSelectedItem().getNom());}
desc.setCellValueFactory(new PropertyValueFactory<Recette1, String>("description"));
 //nomRece.setCellValueFactory(new PropertyValueFactory<Recette, String>("nom"));           
    
          
         

            List<Recette1> list = servicerec.getAllRecettesaaa();
           ObservableList<Recette1> items = FXCollections.observableArrayList(list);

          

            table.setItems(items);


        
        
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
        
    if (tfDescrecette.getText().equals("")) {
        Alert alert =new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Alert de controle");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez selectionner une recette!");
    alert.showAndWait();
    } else {
        
            Alert alert =new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Confirmation");
    alert.setHeaderText("confirmation");
    alert.setContentText("Voulez vous modifier cette recette?");
    
    Optional<ButtonType>result=alert.showAndWait();
    if( result.get()==ButtonType.OK)
    { String dc=tfDescrecette.getText();
    String des=table.getSelectionModel().getSelectedItem().getNom();
    Recette1Services rec=new Recette1Services();
    rec.modifierReccc(dc, des);
    

    
    
    }}
            
         desc.setCellValueFactory(new PropertyValueFactory<Recette1, String>("description"));
 nomRece.setCellValueFactory(new PropertyValueFactory<Recette1, String>("nom"));

         //   List<Recette> list = servicerec.getAllRecettes();
         //  ObservableList<Recette> items = FXCollections.observableArrayList(list);

          

           // table.setItems(items);
   
            
          desc.setCellValueFactory(new PropertyValueFactory<Recette1, String>("description"));
 //nomRece.setCellValueFactory(new PropertyValueFactory<Recette, String>("nom"));           
    
          
         

            List<Recette1> list = servicerec.getAllRecettesaaa();
           ObservableList<Recette1> items = FXCollections.observableArrayList(list);

          

            table.setItems(items);  
            
            
    
        
        
        
    }

    @FXML
    private void find(ActionEvent event) throws SQLException {
        String a=txt_cherch.getText();
        if("".equals(a)){
             Recette1Services ds=new Recette1Services();
                             ArrayList<Recette1> recettes = null;
        
            try {
                recettes = (ArrayList<Recette1>) ds.getAllRecettes();
            } catch (SQLException ex) {
                Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            ObservableList obs =FXCollections.observableArrayList(recettes);
            
            table.setItems(obs); 
            desc.setCellValueFactory(new PropertyValueFactory<Recette1, String>("description"));
 //nomRece.setCellValueFactory(new PropertyValueFactory<Recette, String>("nom"));
            
        }
            
        else{
        Recette1Services ds=new Recette1Services();
                             ArrayList<Recette1> recettes = null;
       
            ObservableList obs =ds.getRecetteByName(a);
            
           table.setItems(obs); 
                      desc.setCellValueFactory(new PropertyValueFactory<Recette1, String>("description"));
 nomRece.setCellValueFactory(new PropertyValueFactory<Recette1, String>("nom"));
              
        
        
        
        
        
    }
    
    
        
    }

    @FXML
    private void AllerComment(ActionEvent event)  throws SQLException {
        
        Recette1 e= table.getSelectionModel().getSelectedItem();
    
           
         FXMLLoader loder =new FXMLLoader(getClass().getResource("./Comment_1.fxml"));
                    Parent root = null;
        try {
            root = loder.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                      Btn_blog.getScene().setRoot(root);
          
     
        
         CommentaireRecetteServices cs=new CommentaireRecetteServices();
         ArrayList<CommentaireEvent> rectt =(ArrayList<CommentaireEvent>) cs.getAllCommentaire(e.getId());
         ObservableList obs =FXCollections.observableArrayList(rectt);
         CommentController s=loder.getController();
          s.getText().setText(e.getNom());
         s.getCode_Event().setText(Integer.toString(e.getId()));
         s.getTab_commentaireEvent().setItems(obs);
       s .getId().setCellValueFactory(new PropertyValueFactory<>("id"));
      s.getComment().setCellValueFactory(new PropertyValueFactory<>("comment"));
           s.getId_utilisateur().setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
              s.getDate().setCellValueFactory(new PropertyValueFactory<>("date"));
              s.getId_comment().setCellValueFactory(new PropertyValueFactory<>("id_comment"));
        
        
        
        
        
        
        
    }

    @FXML
    private void FileSelected(ActionEvent event) {
        
        
        pa_browse.setOnAction(new EventHandler<ActionEvent>() {
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
                final File fichier=dialog.showOpenDialog(pa_browse.getScene().getWindow());
               
                path=fichier.getAbsolutePath();
                
                pa_fichierchoisi.setText(fichier.getName());
                
                //récuperer l'image choisie
                File file = new File(path);
                Image image = new Image(file.toURI().toString());
                pa_icone.setImage(image);
            }
        });
        
        
    }
    
@FXML
    private void Modifierrecccc(ActionEvent event) {
     pa_modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                int index=table.getSelectionModel().getSelectedIndex();
                if(table.getSelectionModel().isSelected(index)){
                    pat=(Recette1)table.getSelectionModel().getSelectedItem();
                    tfNomrecette.setText(pat.getNom());
                    tfDescrecette.setText(pat.getDescription());
                    
                }
                
            }
        }); 
        
            
        
        
    }
  @FXML
    private void SupprimerReccc(ActionEvent event) {
      pa_supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int index=table.getSelectionModel().getSelectedIndex();
                if(table.getSelectionModel().isSelected(index)){
                    Recette1Services ps= new Recette1Services();
                    pat=(Recette1)table.getSelectionModel().getSelectedItem();
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Suppression");
                    alert.setHeaderText(null);
                    alert.setContentText("Etes vous sûr de vouloir "
                            + "supprimer la recette "+pat.getNom());
                    Optional <ButtonType> resultat=alert.showAndWait();
                    if(resultat.get()==ButtonType.OK){
                        try {
                            ps.deleteRecette(pat.getId());
                            try {
            ArrayList <Recette1> recc=(ArrayList < Recette1 >) ps.getAllRecettesaaa();
            ObservableList obs=FXCollections.observableArrayList(recc);
            table.setItems(obs);
            nomRece.setCellValueFactory(new PropertyValueFactory<>("nom"));
            desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            
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
    private void modif_avectof(ActionEvent event) {
         pa_saveChanged.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(tfNomrecette.getText().equals("")|| tfDescrecette.getText().equals("")){
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Modifications");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez saisir le nom et le desc");
                    alert.showAndWait();
                }
            else{
            
                
               
                
                    pat.setNom(tfNomrecette.getText());
                    pat.setDescription(tfDescrecette.getText());
                    
                    pat.setUrl(path);
                    Recette1Services ps=new Recette1Services();//connexion établie
                    try {
                        ps.modifierRecetteAvecIMage(pat);
                        Alert alert= new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Modifications enregistrées");
                        alert.showAndWait();
                        
                        tfNomrecette.clear(); tfNomrecette.clear();
                        
                        pa_fichierchoisi.clear(); pa_icone.setImage(null);
                        
            try {
            ArrayList <Recette1> patisseries=(ArrayList < Recette1 >) ps.getAllRecettesaaa();
            ObservableList obs=FXCollections.observableArrayList(patisseries);
            table.setItems(obs);
            nomRece.setCellValueFactory(new PropertyValueFactory<>("nom"));
            desc.setCellValueFactory(new PropertyValueFactory<>("description"));
           
            
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
    private void Allering(ActionEvent event)  throws SQLException {
        
        Recette1 e= table.getSelectionModel().getSelectedItem();
    
           
         FXMLLoader loder =new FXMLLoader(getClass().getResource("./AjoutIngredient.fxml"));
                    Parent root = null;
        
        try {
            root = loder.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
                      btn_ing.getScene().setRoot(root);
          
     
        
         IngredientDAO cs=new IngredientDAO();
         ArrayList<Ingredient> ingg =(ArrayList<Ingredient>) cs.getAllIngredient(e.getId());
         ObservableList obs =FXCollections.observableArrayList(ingg);
         AjoutIngredientController s=loder.getController();
          s.getText().setText(e.getNom());
         s.getCode_Event().setText(Integer.toString(e.getId()));
         s.getTable_ing().setItems(obs);
       s .getId().setCellValueFactory(new PropertyValueFactory<>("id"));
      s.getNom_ing().setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
           s.getId_utilisateur().setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
              s.getQ().setCellValueFactory(new PropertyValueFactory<>("quantite"));
              s.getId_ing().setCellValueFactory(new PropertyValueFactory<>("id_ing"));
        
        
        
        
        
        
        
    }

    @FXML
    private void AllerEt(ActionEvent event)  throws SQLException {
        
         Recette1 e= table.getSelectionModel().getSelectedItem();
    
           
         FXMLLoader loder =new FXMLLoader(getClass().getResource("./AjoutEtapeee.fxml"));
                    Parent root = null;
        try {
            root = loder.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                      btn_eta.getScene().setRoot(root);
        
        
        
                      
                      EtapeDAO cs=new EtapeDAO();
         ArrayList<Etape> etap =(ArrayList<Etape>) cs.getAllEtape(e.getId());
         ObservableList obs =FXCollections.observableArrayList(etap);
         AjoutEtapeeeController s=loder.getController();
          s.getText().setText(e.getNom());
         s.getCode_Event().setText(Integer.toString(e.getId()));
         s.getTable_ing().setItems(obs);
       s .getId().setCellValueFactory(new PropertyValueFactory<>("id"));
      s.getNom_eta().setCellValueFactory(new PropertyValueFactory<>("nom_eta"));
           s.getId_utilisateur().setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
              s.getDesceta().setCellValueFactory(new PropertyValueFactory<>("description_eta"));
              s.getId_eta().setCellValueFactory(new PropertyValueFactory<>("id_eta"));
        
        
        
    }

//    @FXML
//    private void retour(ActionEvent event) {
//       FXMLLoader loder = new FXMLLoader(getClass().getResource("./DrawerViewRecetteFXML.fxml"));
//        Parent root = null;
//        try {
//            root = loder.load();
//        } catch (IOException ex) {
//            Logger.getLogger(AjoutIngredientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        btnHome.getScene().setRoot(root);  
//        
//        
//    }

    @FXML
    private void imprime(ActionEvent event) {
         //C:\Users\hp\Desktop\pdf\ad.pdf
           
          JOptionPane jop = new JOptionPane();
    String ad = jop.showInputDialog(null, "Veuillez saisir l'emplacement du ficher !", JOptionPane.QUESTION_MESSAGE);
               // C:\Users\IMEN\Desktop\pdf\ad.pdf";
                com.itextpdf.text.Document doc=new com.itextpdf.text.Document();
               try {
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream(ad));
                    } catch (DocumentException ex) {
                        Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
                        
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
                   Paragraph p=new Paragraph("CoinPatissier\n",f);
                   p.setAlignment(Element.ALIGN_CENTER);
                   doc.add(p);
Font f1=new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.UNDERLINE, BaseColor.RED);
                   Paragraph p1=new Paragraph("Rapport des recettes ",f1);
                   p1.setAlignment(Element.ALIGN_CENTER);
                   doc.add(p1);
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
String d=   dtf.format(now);
 Font f2=new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE, BaseColor.BLACK);
 Paragraph p2=new Paragraph("\nDate: "+d,f2);
   doc.add(p2);
      doc.add(new Paragraph(" : " ,f2));
      doc.add(new Paragraph(" "));
                   PdfPTable table = new PdfPTable(6);
                   table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
                   Font f3=new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDEFINED, BaseColor.BLUE);
                   PdfPCell c1=new PdfPCell(new Phrase("Nom",f3));
                   c1.setPadding(4f);
                   table.addCell(c1);
                     c1=new PdfPCell(new Phrase("desription ",f3));
                     c1.setPadding(4f);     
                   table.addCell(c1);
                   //  c1=new PdfPCell(new Phrase("Domaine ",f3));
                   //  c1.setPadding(4f);
                  // table.addCell(c1);
                   //  c1=new PdfPCell(new Phrase("Salle ",f3));
                   //  c1.setPadding(4f);
                  // table.addCell(c1);
                    // c1=new PdfPCell(new Phrase("Date d'ajout",f3));
                   //  c1.setPadding(4f);
                  // table.addCell(c1);
                    
                  // table.setHeaderRows(0);
                  Recette1Services s=new Recette1Services();
                    ArrayList<Recette1> e =(ArrayList<Recette1>)s.getAllRecettesaaa();
                    for(int i=0;i<e.size();i++)
                    {
                        String n=e.get(i).getNom();
                        Font f4=new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.UNDEFINED, BaseColor.BLACK);
                        c1=new PdfPCell(new Phrase(n,f4));
                        table.addCell(c1);
                        String r=e.get(i).getDescription();
                        c1=new PdfPCell(new Phrase(r,f4));
                        table.addCell(c1);
//                        String dom=e.get(i).getDomaine();
//                        c1=new PdfPCell(new Phrase(dom,f4));
//                        table.addCell(c1);
//                        String sa=e.get(i).getSalle();
//                        c1=new PdfPCell(new Phrase(sa,f4));
//                        table.addCell(c1);
//                        String da=e.get(i).getDate();
//                        c1=new PdfPCell(new Phrase(da,f4));
//                        table.addCell(c1);
//                        String et=e.get(i).getEtat();
//                        c1=new PdfPCell(new Phrase(et,f4));
//                        table.addCell(c1);
                    }
                   doc.add(table);
               } catch (DocumentException ex) {
                   Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
               
        
    
     
        
        
        
        
    
    
    
        
        
        
        
    }

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
                        Logger.getLogger(AfficheRecetteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void retourToRecette(ActionEvent event) {
        
       FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilClientFXML.fxml"));
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
    
    
}
