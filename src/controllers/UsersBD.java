/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import modeles.easy_sales;

/**
 *
 * @author Faustin PADINGANYI
 */
public class UsersBD extends Controller {

    String ID, nomComplet,statuts, contact = "OK", pwd;
    public UsersBD(){
        
    }
    public UsersBD(String ID, String nomComplet, String statuts, String pwd){
        this.ID = ID;
        this.nomComplet = nomComplet;
        this.statuts = statuts;
        this.pwd = pwd;
    }
    @Override
    public void enregistrer() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("INSERT INTO UsersBD (idUsers,nomComplet,contact,statuts,pwd) "
                    + " VALUES (?,?,?,?,?)");
            easy_sales.Pst.setString(1, ID);
            easy_sales.Pst.setString(2, nomComplet);
            easy_sales.Pst.setString(3, contact);
            easy_sales.Pst.setString(4, statuts);
            easy_sales.Pst.setString(5, pwd);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.err.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }

    @Override
    public void modifier() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement)  easy_sales.cn.prepareStatement("UPDATE UsersBD SET nomComplet=?,contact=?, statuts = ? "
                    + " WHERE idUsers=?");
            easy_sales.Pst.setString(4, ID);
            easy_sales.Pst.setString(1, nomComplet);
            easy_sales.Pst.setString(2, contact);
            easy_sales.Pst.setString(3, statuts);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.err.println("Modification effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }

    @Override
    public void supprimer() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement)  easy_sales.cn.prepareStatement("DELETE FROM UsersBD  WHERE idUsers=?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.err.println("Suppression effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public boolean rechercherUser(String id){
        boolean trouve=false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement)  easy_sales.cn.prepareStatement("SELECT idUsers FROM UsersBD WHERE idUsers = ?");
            easy_sales.Pst.setString(1, id);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    public boolean trouverUser(String User, String pwd){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement)  easy_sales.cn.prepareStatement("SELECT idUsers,statuts,pwd,idSite,actif FROM travailusersview WHERE idUsers = ?");
            easy_sales.Pst.setString(1, User);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) {   
                String dd = easy_sales.rs.getString(5);
                if (dd.equals("A")) {
                    String a = easy_sales.rs.getString(3);
                    if (pwd.equals(a)) {
                        PontParametres PP = new PontParametres(easy_sales.rs.getString(1), easy_sales.rs.getString(2), easy_sales.rs.getString(4));
                        trouve = true;
                    }
                }
            }
        } catch (Exception e) {
             System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    public boolean trouverAdmin(String User,String pwd){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement)  easy_sales.cn.prepareStatement("SELECT idUsers,statuts,pwd,idSite,actif FROM travailusersview WHERE idUsers = ?");
            easy_sales.Pst.setString(1, User);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) {   
                String dd = easy_sales.rs.getString(5);
                if (dd.equals("A")) {
                    String a = easy_sales.rs.getString(3);
                    if (pwd.equals(a)) {
                        String b = easy_sales.rs.getString(2);
                        if (b.equals("Administrateur") || b.equals("Gérant")) {
                            trouve = true;
                            PontParametres.setAdmin(true);
                            PontParametres.setUserAdm(easy_sales.rs.getString(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    public void modifierPWD(String userID, String userPWD){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement)  easy_sales.cn.prepareStatement("UPDATE UsersBD SET pwd=? WHERE idUsers=?");
            easy_sales.Pst.setString(2, userID);
            easy_sales.Pst.setString(1, userPWD);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.err.println("Mot de Passe Modifié");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
}
