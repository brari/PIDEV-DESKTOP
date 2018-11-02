/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.service.Userservice;
import pidev.service.Usersession;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.entities.Users;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ConnexionFXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label user;
    @FXML
    private Label pass;
    @FXML
    private Button signup_btn;
    @FXML
    private Button signin_btn;
    @FXML
    private ImageView fb_btn;
    @FXML
    private JFXTextField username_login;
    @FXML
    private JFXPasswordField password_login;
    @FXML
    private Button forgot_pw;
    @FXML
    private Label login_errorlabel;
    @FXML
    private Text forgot_pw1;
    
    static Users session=new Users();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscri(MouseEvent event) {
         try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./inscription.fxml"));
                    Parent root;
                    root=loader.load();
                 signup_btn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void sign_in(MouseEvent event) throws SQLException, IOException {
         Userservice us=new Userservice();
        Usersession usa=new Usersession();
        Users user1= new Users();
   
             if("".equals(username_login.getText()))
             {  user.setTextFill(Color.RED);
                 user.setText("veuiller remplir");
             }
             else if("".equals(password_login.getText()))
             {  pass.setTextFill(Color.RED);
                 pass.setText("veuiller remplir");
             }else if ((username_login.getText().equals("admin"))&&(password_login.getText().equals("admin")))
                         { FXMLLoader loader =new FXMLLoader(getClass().getResource("./admin.fxml"));
                               Parent root;
                               root=loader.load();
                               signin_btn.getScene().setRoot(root);} 
          else   {     
              
                     Users u=us.chercherUser(username_login.getText());
                
                
                   if(us.chercheruser(username_login.getText(),password_login.getText()))
                    {if(u.getEnabled()==1){
                        if("Patissier".equals(us.avoirRole(username_login.getText(),password_login.getText())))
                           {
                               session=us.cherchNom(username_login.getText());
                               TrayNotification tr3 = new TrayNotification();
				tr3.setAnimationType(AnimationType.POPUP);
				tr3.setTitle("Authentification");
				tr3.setNotificationType(NotificationType.SUCCESS);
				tr3.setMessage("Bienvenue a l'espace des patissiers  ");
				tr3.showAndDismiss(Duration.seconds(3));
                              
                               FXMLLoader loader =new FXMLLoader(getClass().getResource("./AccueilPatissierFXML.fxml"));
                               Parent root;
                               root=loader.load();
                               signin_btn.getScene().setRoot(root); 
                               usa.add_session(username_login.getText());  
                                
                           }
                         else if("Client".equals(us.avoirRole(username_login.getText(),password_login.getText())))
                            {   
                                session=us.cherchNom(username_login.getText());
                                
                                TrayNotification tr1 = new TrayNotification();
				tr1.setAnimationType(AnimationType.POPUP);
				tr1.setTitle("Authentification");
				tr1.setNotificationType(NotificationType.SUCCESS);
				tr1.setMessage("Bienvenue a l'espace des clients");
				tr1.showAndDismiss(Duration.seconds(3));
                               FXMLLoader loader =new FXMLLoader(getClass().getResource("./AccueilClientFXML.fxml"));
                               Parent root;
                               root=loader.load();
                               signin_btn.getScene().setRoot(root);
                               usa.add_session(username_login.getText());
                               
                            
                            }
                              else 
                                {
                                  TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Authentification");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Authentification incorrect /n role introuvable ");
				tr.showAndDismiss(Duration.seconds(3));
                                }
                 
                    
                }
                   else {
                        TrayNotification tr2 = new TrayNotification();
				tr2.setAnimationType(AnimationType.POPUP);
				tr2.setTitle("Erreur Authentification");
				tr2.setNotificationType(NotificationType.ERROR);
				tr2.setMessage("En attente d'activation de votre compte");
				tr2.showAndDismiss(Duration.seconds(3));
                   }
               
           
                }
                
             
              else  {
             
         /*Image img = new Image("/Image/moins.png");
            Notifications notificationBuilder2 = Notifications.create()
                                    .title("erreur d'authentification")
                                    .text("en attente d'activation de votre compte  ")
                                    .graphic(new ImageView(img))
                                    .hideAfter(Duration.seconds(10))
                                    .position(Pos.TOP_RIGHT);
                            notificationBuilder2.show();   */
                             
                  login_errorlabel.setTextFill(Color.RED);
                    login_errorlabel.setText("(*) User name ou Password incorrect");
                                  System.out.println("errror");
             
         }
                   
    }
    }
    @FXML
    private void forgot_pwd(MouseEvent event) {
         try{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("./MotDePasse.fxml"));
                    Parent root;
                    root=loader.load();
                 forgot_pw.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ConnexionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goFb(MouseEvent event) {
    }
    
}

    