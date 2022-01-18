/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Faustin PADINGANYI
 */
public class PontParametres {
    public static String  User, statut, site, admin, connecter;
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

    public String getSite() {
        return site;
    }
    
}
