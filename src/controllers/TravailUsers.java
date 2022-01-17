/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import modeles.easy_sales;

/**
 *
 * @author Faustin PADINGANYI
 */
public class TravailUsers {
    String idUsers, idSite;
    public TravailUsers(){
        
    }
    public TravailUsers(String idUsers, String idSite){
        this.idUsers = idUsers;
        this.idSite = idSite;
    }
    public void enregistrerTravail(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst =  (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO travailUsers (idUsers,idSite,dateDebut,actif) "
                    + " VALUES(?,?,NOW(),?)");
            easy_sales.Pst.setString(1, idUsers);
            easy_sales.Pst.setString(2, idSite);
            easy_sales.Pst.setString(3, "A");
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public boolean rechercherEnCours(){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst=(ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idUsers FROM travailUsers WHERE "
                    + " idUsers = ? AND datefin = null");
            easy_sales.Pst.setString(1, idUsers);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    public void finTravail(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE travailUsers SET "
                    + " dateFin = now(), actif = ? WHERE idUsers = ?");
            easy_sales.Pst.setString(2, idUsers);
            easy_sales.Pst.setString(1, "B");
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Modification effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
}
