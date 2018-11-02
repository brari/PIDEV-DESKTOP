/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.util;

import pidev.entities.Recette;
import pidev.service.RecetteDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author IMEN
 */
public class AfficherRecette extends AbstractTableModel {

    String [] col={"nom","description","photo"};
    List<Recette> arm;
    
    
    public AfficherRecette() {
        
        RecetteDAO pmdao=new RecetteDAO();
       
         try{
           arm=pmdao.displayAll();
            // arm=pmdao.displayAllMessageEnvoyeAdmin(Session.getUser().getIdUser());

         }
    catch(Exception ex)
    {
         System.out.println(ex);
     }  
     
    }
    
    
    @Override
    public int getRowCount() {
        return arm.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }
    
    //@Override
    public String getColumnName(int column) {
        return col[column]; //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
        
       case 0: return arm.get(rowIndex).getNom();
          
         case 1 : return arm.get(rowIndex).getDescription();
          case 2 : return arm.get(rowIndex).getPhoto();
           
            
                   
    }
    return null; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
