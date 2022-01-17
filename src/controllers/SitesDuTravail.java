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
public class SitesDuTravail extends Controller {

    private String idSite, libSite, adreSite;
    public SitesDuTravail(){
        
    }
    public SitesDuTravail(String idSite, String libSite, String adreSite){
        this.idSite = idSite;
        this.libSite = libSite;
        this.adreSite = adreSite;
    }
    @Override
    public void enregistrer() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("INSERT INTO Sites (idSite,libSite,adresse) "
                    + " (?,?,?)");
            easy_sales.Pst.setString(1, idSite);
            easy_sales.Pst.setString(2, libSite);
            easy_sales.Pst.setString(3, adreSite);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }

    @Override
    public void modifier() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("UPDATE Sites SET libSite=?,adresse=? "
                    + " WHERE idSite = ?");
            easy_sales.Pst.setString(3, idSite);
            easy_sales.Pst.setString(1, libSite);
            easy_sales.Pst.setString(2, adreSite);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Modification effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }

    @Override
    public void supprimer() {
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("DELETE FROM Sites WHERE idSite = ?");
            easy_sales.Pst.setString(1, idSite);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Suppression effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public boolean rechercherSite(){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.prepareStatement("SELECT idSite FROM Sites WHERE idSite = ?");
            easy_sales.Pst.setString(1, idSite);
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
