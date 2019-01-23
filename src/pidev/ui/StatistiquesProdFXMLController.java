/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;


import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import pidev.entities.Produit;
import pidev.service.CommandeProduitService;
import pidev.service.ProduitService;

/**
 * FXML Controller class
 *
 * @author Insaf-Nefzi
 */
public class StatistiquesProdFXMLController implements Initializable {

    
    
    public ProduitService ps=new ProduitService();
    public CommandeProduitService cps=new CommandeProduitService();


    private Object PieChart;
    @FXML
    private Button stat;
    @FXML
    private Button stat1;
 
   
   /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
      
            
             
              stat.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               ProduitService s=new ProduitService();
               CommandeProduitService cs=new CommandeProduitService();
               int a=0;int b=0;int c=0;
               a=s.Afficher_list();
               // b=  s.getNombre("Non Fonctionnel");
               c=  cs.Afficher_listCom();
               b=c/a;
                 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
                         dataset.setValue(a, "Produits", "existants");
                         //
                         dataset.setValue(c, "Produits", "Commandés");
                         dataset.setValue(b, "Produit ", " Commande par Produit");
                 JFreeChart chart=ChartFactory.createBarChart3D("etat d'equipements ", "produit", "commande", dataset);
                 //chart.setBackgroundPaint(Color.YELLOW);
                 //chart.getTitle().setPaint(Color.red);
                 CategoryPlot p=chart.getCategoryPlot();
                 //p.setRangeGridlinePaint(Color.BLUE);
                 ChartFrame frame=new ChartFrame("bar des produits",chart);
                 frame.setVisible(true);
                 frame.setSize(450, 350);
                         

             }
    });
              
              
              
     
                         

             }

    @FXML
    private void REtourStat(ActionEvent event) {
        
        
        
        
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficherProduitPatissierFXML.fxml"));
                Parent root; 
                try {
                    root=loader.load();
                    stat1.getScene().setRoot(root);
                } catch (IOException ex) {
                   // Logger.getLogger(AfficherComdProFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    

    
    }
       
        
    

