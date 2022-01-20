/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Calendar;

/**
 *
 * @author Faustin PADINGANYI
 */
public class PontParametres {
    public static String  User, statut, site, admin, connecter,jrSemaine;
    public PontParametres(){
        
    }
    public PontParametres(String User, String statut, String site){
        this.User = User;
        this.statut = statut;
        this.site = site;
    }

    public  String getUser() {
        return User;
    }

    public String getStatut() {
        return statut;
    }

    public  String getSite() {
        return site;
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
}
