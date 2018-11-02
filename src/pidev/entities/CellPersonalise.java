/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Rating;
import pidev.service.PatisserieService;

/**
 *
 * @author hp
 */
public class CellPersonalise extends ListCell<Patisserie>{
    HBox hbox= new HBox();
    Pane pane=new Pane();
    Rating rating= new Rating();
    ImageView img= new ImageView();
    PatisserieService ps=new PatisserieService();
    
    //Image profil=new Image("file:E:\\4InfoB\\PIDEV\\JavaPIDEV\\src\\pidev\\images\\profil.png");

        public CellPersonalise() {
            super();
            img.setFitHeight(128); img.setFitWidth(128); 
            rating.setPartialRating(true);
            pane.getChildren().add(rating);
            hbox.getChildren().addAll(img,pane);
            //hbox.seth
        }
                
            @Override
            protected void updateItem(Patisserie p,boolean bol){
                        super.updateItem(p, bol);
                        setGraphic(null); 
                        setText(null);
                        if(p!=null && !bol){
                            setText("Nom:\t" +p.getNom()+"\nAdresse:\t"+p.getAdresse()
                            +"\nContact:\t"+p.getContact()+"\nMail: \t"+p.getMail());
                            img.setImage(ps.extraireImage(p.getIdp()));
                            rating.setRating(p.getRating());
                            rating.setDisable(true);
                            setGraphic(hbox);
                        } 
            }
}
