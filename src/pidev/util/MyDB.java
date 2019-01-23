/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class MyDB {
          /// Attributs
        final String url="jdbc:mysql://localhost/patisserie2";
        final String login="root";
        final String mdp="";
        Connection connexion;
        private static MyDB instance;
        
        
        /// Private Constructor
        private MyDB() {
             try {
            connexion = DriverManager.getConnection(url, login,mdp);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion a la base de donnée");
        }
        }


        ///getters

    /**
     *
     * @return
     */
        static public MyDB getInstance() {
            if (instance==null) 
                instance= new MyDB();
            return instance;
        }
        
        
        public Connection getConnection() {
            return connexion;
        }
        
}
