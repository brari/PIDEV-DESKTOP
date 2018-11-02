/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.interfac;

import pidev.entities.Recette;
//import com.esprit.entite.User;
import java.util.List;

/**
 *
 * @author IMEN
 */
public interface IRecetteDAO <T> {
    
    void add(T t);
    void update(T t, String titre);
    void delete (T t);
    List<T> displayAll();
   List<T>findByNom(T t);
    
    
    
    
    
    
}
