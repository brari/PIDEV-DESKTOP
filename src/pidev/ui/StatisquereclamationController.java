/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import pidev.service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author brari
 */
public class StatisquereclamationController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton btn_stat;
    @FXML
    private JFXButton btn_stat1;
    @FXML
    private JFXTextField tx_id_patissier;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    btn_stat.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               ReclamationService rs=new ReclamationService();
               int a=0;int b=0;int c=0;
                 try {
              a=rs.getNombre("traitee");
               b=  rs.getNombre(" en cours ");
       //       c=  rs.getNombre("En maintenance");
                 } catch (SQLException ex) {
                     Logger.getLogger(StatisquereclamationController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
                         dataset.setValue(a, "statut", "traitee");
                         dataset.setValue(b, "statut", " en cours");
                  //      dataset.setValue(c, "equipements", "en maintenance");
                 JFreeChart chart=ChartFactory.createBarChart3D("statut de reclamation ", "etat", "nombres", dataset);
                 chart.setBackgroundPaint(Color.YELLOW);
                 chart.getTitle().setPaint(Color.red);
                 CategoryPlot p=chart.getCategoryPlot();
                 p.setRangeGridlinePaint(Color.BLUE);
                 ChartFrame frame=new ChartFrame("bar de reclamation",chart);
                 frame.setVisible(true);
                 frame.setSize(450, 350);
                         System.out.println(a);
                              System.out.println(b);

             }
    });
  btn_stat1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
               ReclamationService rs=new ReclamationService();
               int a=0;int b=0;int c =0;
                 try {
                     
              a=rs.Afficher_listcat(Integer.parseInt(tx_id_patissier.getText()));
              b=rs.NombreRec();
              c=a/b;
       //       c=  rs.getNombre("En maintenance");tx_id_patissier.getText()
                 } catch (SQLException ex) {
                     Logger.getLogger(StatisquereclamationController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
                         dataset.setValue(a, "taux de  reclamation", "Patissier n°"+tx_id_patissier.getText());
                       
                  //      dataset.setValue(c, "equipements", "en maintenance");
                 JFreeChart chart=ChartFactory.createBarChart3D("Statistique de patissier", "patissier", "nombres", dataset);
                 chart.setBackgroundPaint(Color.YELLOW);
                 chart.getTitle().setPaint(Color.red);
                 CategoryPlot p=chart.getCategoryPlot();
                 p.setRangeGridlinePaint(Color.BLUE);
                 ChartFrame frame=new ChartFrame("bar de reclamation",chart);
                 frame.setVisible(true);
                 frame.setSize(450, 350);
                         System.out.println(a);
                            
             }
    });

    }

    @FXML
    private void retour(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("./MainreclamationAdmin.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    retour_btn.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
 }
    

