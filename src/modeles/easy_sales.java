/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Faustin PADINGANYI
 */
public class easy_sales {
    public static Connection cn;
    public static PreparedStatement Pst;
    public static ResultSet rs;
    
    /**
     * La Méthode de Connexion
     */
    public static void connexionEasy(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn  = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hpp_easy_sales","root","");
            cn.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * La Méthode de déconnexion
     */
    public static void deconnexionEasy(){
        try {
            if (!cn.isClosed()) {
                cn.setAutoCommit(true);
                Pst.close();
                rs.close();
                cn.close();
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
}
