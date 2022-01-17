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
 * @author user
 */
public class UsersBD extends Controller {

    String ID, nomComplet, contact, pwd;
    public UsersBD(){
        
    }
    public UsersBD(String ID, String nomComplet, String contact, String pwd){
        this.ID = ID;
        this.nomComplet = nomComplet;
        this.contact = contact;
        this.pwd = pwd;
    }
    @Override
    public void enregistrer() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("INSERT INTO UsersBD (idUsers,nomComplet,contact,pwd) "
                    + " VALUES (?,?,?,?)");
            easy_sales.Pst.setString(1, ID);
            easy_sales.Pst.setString(2, nomComplet);
            easy_sales.Pst.setString(3, contact);
            easy_sales.Pst.setString(4, pwd);
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
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("UPDATE UsersBD SET nomComplet=?,contact=? "
                    + " WHERE idUsers=?");
            easy_sales.Pst.setString(3, ID);
            easy_sales.Pst.setString(1, nomComplet);
            easy_sales.Pst.setString(2, contact);
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
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("DELETE FROM UsersBD  WHERE idUsers=?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.err.println("Suppression effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public boolean rechercherUser(){
        boolean trouve=false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("SELECT idUsers FROM UsersBD WHERE idUsers = ?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
}
