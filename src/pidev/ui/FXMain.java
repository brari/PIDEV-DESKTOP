/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Parent root=FXMLLoader.load(getClass().getResource("./AfficheEvenementPatissierFXML.fxml"));
        //Parent root=FXMLLoader.load(getClass().getResource("./AccueilPatissierFXML.fxml"));
        //Parent root=FXMLLoader.load(getClass().getResource("./MainReclamationFXML.fxml"));
        //Parent root=FXMLLoader.load(getClass().getResource("./AccueilClientFXML.fxml"));
        //Parent root=FXMLLoader.load(getClass().getResource("./AccueilClientEvenementFXML.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("./AuthentificationFXML.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Annuaire Patisserie"); //titre de notre page
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
