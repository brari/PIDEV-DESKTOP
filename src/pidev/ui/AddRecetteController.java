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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import pidev.entities.Recette;
import pidev.entities.Recette1;
import pidev.service.Recette1Services;
import pidev.service.RecetteDAO;
import pidev.util.ImageUtils;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddRecetteController implements Initializable {
private RecetteDAO sv=new RecetteDAO();
    private Recette v=new Recette();
        private String s;
        
        String chemin="";
        String remotePath="/Upload";
    File file1;
    @FXML
    private Button btnAjoutrecette;
    @FXML
    private TextField tfNomrecette;
    @FXML
    private TextArea tfDescrecette;
    private Button btnAjoutPhoto;
    @FXML
    private ImageView imgp;
    @FXML
    private Button btn_retour;
    @FXML
    private Button pa_fichier;
    @FXML
    private Button pa_creer;
    @FXML
    private ImageView pa_image;
    @FXML
    private Label pa_erreur;
    @FXML
    private TextField pa_choix;

    
    public RecetteDAO getSv() {
        return sv;
    }

    public Recette getV() {
        return v;
    }

    public String getS() {
        return s;
    }

    public Button getBtnAjoutrecette() {
        return btnAjoutrecette;
    }

    public TextField getTfNomrecette() {
        return tfNomrecette;
    }

    public TextArea getTfDescrecette() {
        return tfDescrecette;
    }

    public Button getBtnAjoutPhoto() {
        return btnAjoutPhoto;
    }

    public ImageView getImgp() {
        return imgp;
    }

    public void setSv(RecetteDAO sv) {
        this.sv = sv;
    }

    public void setV(Recette v) {
        this.v = v;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setBtnAjoutrecette(Button btnAjoutrecette) {
        this.btnAjoutrecette = btnAjoutrecette;
    }

    public void setTfNomrecette(TextField tfNomrecette) {
        this.tfNomrecette = tfNomrecette;
    }

    public void setTfDescrecette(TextArea tfDescrecette) {
        this.tfDescrecette = tfDescrecette;
    }

    public void setBtnAjoutPhoto(Button btnAjoutPhoto) {
        this.btnAjoutPhoto = btnAjoutPhoto;
    }

    public void setImgp(ImageView imgp) {
        this.imgp = imgp;
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
     private void clear() {
        tfNomrecette.setText("");
        tfDescrecette.setText("");
        
        
      //  btnAjoutrecette.setVisible(false);
       // valider.setVisible(true);// bech t5abi el boutton 
    } 
    
     
     private void browse(ActionEvent event) {
        byte[] imageRecette = null;
         JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        File file = f.getAbsoluteFile();
        ImageUtils imageUtils = new ImageUtils();
        try {
            imageRecette = imageUtils.extractBytes(file.toString());
            Image image;
            image = new Image(file.toURI().toString(), 100, 150, true, true);
            imgp.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(AddRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
    @FXML
    private void Add(ActionEvent event) throws FileNotFoundException {
        
        {
          
           
               
                Recette  p=new Recette(tfNomrecette.getText(),tfDescrecette.getText()) ;  
                RecetteDAO ps=new RecetteDAO();
                try {
                    ps.ajouterRecette(p);
                   // FXMLLoader loader =new FXMLLoader(getClass().getResource("./AddRecette.fxml"));
                   // Parent root;
//                    try {
//                        root=loader.load();
//                       // btnAjoutrecette.getScene().setRoot(root);
//                    } catch (IOException ex) {
//                        Logger.getLogger(AddRecetteController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AddRecetteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
        
        
        
        
        
      clear();
        }
        
        
        
    }
private void ajout_avec_image(ActionEvent event) throws IOException {
               InputStream img = new FileInputStream(new File(s));

        v=new Recette(tfNomrecette.getText(), tfDescrecette.getText());
      
        try {
            sv.ajouterRecette(v,img);
            //actualiser();
//              clear();
          
        } catch (SQLException ex) {
            Logger.getLogger(AddRecetteController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Parent root = FXMLLoader.load(getClass().getResource("VoitureCovoiturageModif.fxml"));
//        Scene scene = new Scene(root);
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
//        app_stage.setScene(scene);

//        app_stage.show();
        clear();
        
        
        
    }
    
    
    
    @FXML
    private void retour(ActionEvent event)throws IOException {
        
        FXMLLoader loader =new FXMLLoader (getClass().getResource("./DrawerViewRecetteFXML.fxml"));
        Parent root=null;
        root=loader.load();
        btn_retour.getScene().setRoot(root);
        
        
    }

    @FXML
    private void choisirFichier1(ActionEvent event) {
      pa_fichier.setOnAction(new EventHandler<ActionEvent>() {
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
                final File fichier=dialog.showOpenDialog(pa_fichier.getScene().getWindow());
               
                chemin=fichier.getAbsolutePath();
                
                pa_choix.setText(fichier.getName());
                
                //récuperer l'image choisie
                file1 = new File(chemin);
                Image image = new Image(file1.toURI().toString());
                pa_image.setImage(image);
            }
        });
        
          
        
        
    }

    @FXML
    private void Creer(ActionEvent event) {
         pa_creer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            pa_erreur.setText("");    
            if(tfNomrecette.getText().equals("")|| tfDescrecette.getText().equals(""))
            pa_erreur.setText("Veuillez saisir le nom et la desc");
            else{
            
               
                
                
                
                    pa_erreur.setText("");
                    Recette1 p=new Recette1(tfNomrecette.getText(),tfDescrecette.getText()
                            );
                 //   p.setUrl(remotePath);//remotepath////ajouté ftp
                    p.setUrl(chemin);//remotepath
                    Recette1Services ps=new Recette1Services();//connexion établie
                    try {
                        ps.ajouterRecetteeeee(p);
                        pa_erreur.setText("Ajout réussi");
                    } catch (SQLException ex) {
                        
                        System.out.println("Erreur d'ajout");
                        pa_erreur.setText("Erreur d'ajout");
                    }
                }               
           
            }
           
        });    
        
      // clear(); 
        
        
    }
    
}
