/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class easy_sales {
    public static Connection cn;
    public static PreparedStatement Pst;
    public static ResultSet rs;
    public static void connexionEasy(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn  = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hpp_easy_sales","root","");
            cn.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
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
