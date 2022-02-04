/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import java.util.Calendar;
import modeles.easy_sales;

/**
 *
 * @author Faustin PADINGANYI
 */
public class PontParametres {
    public static String  User,userAdm, statut, site,  connecter,jrSemaine;

    public static boolean admin;
    public PontParametres(){
        
    }
    public PontParametres(String User, String statut, String site){
        this.User = User;
        this.statut = statut;
        this.site = site;
    }

    public static String getNumeroFacture() {
        String fact = "";
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT DISTINCT numFact FROM ventes ORDER BY numFact DESC LIMIT 1");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                fact = easy_sales.rs.getString(1);
            }else{
                fact = "HPP1";
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return fact;
    }
    
    public  String getUser() {
        return User;
    }

    public static String getUserAdm() {
        return userAdm;
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void setUserAdm(String userAdm) {
        PontParametres.userAdm = userAdm;
    }

    public static void setAdmin(boolean admin) {
        PontParametres.admin = admin;
    }

    public String getStatut() {
        return statut;
    }

    public  String getSite() {
        return site;
    }
    public static Integer getSemaineYear(Calendar cal){
        int week = 0;
        try {
            week = cal.get(Calendar.WEEK_OF_YEAR);
            System.out.println("La semaine : "+week);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return week;
    }
    public static String getJrSemaine(Calendar cal) {
        
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int JJ = day-1;
        switch (JJ){
            case 1: jrSemaine = "Lundi";
                    break;
            case 2: jrSemaine="Mardi";
                    break;
            case 3: jrSemaine = "Mercredi";
                    break;
            case 4: jrSemaine = "Jeudi";
                    break;
            case 5: jrSemaine = "Vendredi";
                    break;
            case 6: jrSemaine = "Samedi";
                    break;
            case 0: jrSemaine = "Dimanche";
                    break;
        }
        return jrSemaine;
    }
    public static int prixParJour(){
        int prix = 0;
        getJrSemaine(Calendar.getInstance());
        switch (jrSemaine){
            case "Mardi" : prix = 8000;
                break;
            case "Mercredi" : prix = 7000;
                break;
            case "Jeudi": prix = 6000;
                break;
            case "Vendredi": prix = 5000;
                break;
            case "Samedi" : prix = 4000;
                break;
            case "Dimanche" : prix = 3000;
                break;
        }
        return prix;
    }
}
