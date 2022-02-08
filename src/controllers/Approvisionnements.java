/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.util.Calendar;
import modeles.easy_sales;

/**
 *
 * @author Faustin PADINGANYI
 */
public class Approvisionnements {
    private String idType, idArticles,idSite,users, qlteArticle,idCycle;
    private int qteAppro;
    public Approvisionnements(){
        
    }
    public Approvisionnements(String idType, String idArticles,String idSite, int qteAppro,String users, String qlteArticle){
        this.idType = idType;
        this.idArticles = idArticles;
        this.idSite = idSite;
        this.qteAppro = qteAppro;
        this.users = users;
        this.qlteArticle = qlteArticle;
        idCycle = PontParametres.getIdCycle();
    }
    public void enregistrerAppro(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO Appro (idType,idArticles,"
                    + "qteAppro,jrAppro,idSite,users,qlteArticle,semaineApp,idCycle, dateAppro) VALUES (?,?,?,?,?,?,?,?,?,now())");
            easy_sales.Pst.setString(1, idType);
            easy_sales.Pst.setString(2, idArticles);
            easy_sales.Pst.setInt(3, qteAppro);
            easy_sales.Pst.setString(4, PontParametres.getJrSemaine(Calendar.getInstance()));
            easy_sales.Pst.setString(5, idSite);
            easy_sales.Pst.setString(6, users);
            easy_sales.Pst.setString(7, qlteArticle);
            easy_sales.Pst.setInt(8, PontParametres.getSemaineYear(Calendar.getInstance()));
            easy_sales.Pst.setString(9, PontParametres.getIdCycle());
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public void ajouterStock(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles, desiArticle, qteStock, idSite, pteauVente FROM Articlesite WHERE idArticles =? AND idSite = ?");
            easy_sales.Pst.setString(1, idArticles);
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                if (idType.equals("PRODUCTION")) {
                    String ID = easy_sales.rs.getString(1);
                    int qte = easy_sales.rs.getInt(3);
                    qte += qteAppro;
                    easy_sales.deconnexionEasy();
                    easy_sales.connexionEasy();
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articlesite SET qtestock = ? WHERE idSite = ? AND idarticles = ?");
                    easy_sales.Pst.setString(3, ID);
                    easy_sales.Pst.setString(2, PontParametres.site);
                    easy_sales.Pst.setInt(1, qte);
                    easy_sales.Pst.execute();
                    easy_sales.deconnexionEasy();
                }else{
                    String ID = easy_sales.rs.getString(1);
                    int qte = easy_sales.rs.getInt(5);
                    qte += qteAppro;
                    easy_sales.deconnexionEasy();
                    easy_sales.connexionEasy();
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articlesite SET pteauVente = ? WHERE idarticles = ? AND idSite = ? AND qteStock > 0");
                    easy_sales.Pst.setString(2, ID);
                    easy_sales.Pst.setString(3, PontParametres.site);
                    easy_sales.Pst.setInt(1, qte);
                    easy_sales.Pst.execute();
                    easy_sales.deconnexionEasy();
                }
                
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * Remplissage de la zone de la situation du Stock dans le Cycle
     */
    
    /**
     * Début STOCK INITIAL DU CYCLE
     * @return 
     */
    public Integer stockProduction(){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType = ? AND idCycle = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockSQB(String Article){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType = ? AND idCycle = ? AND qlteArticle = ? AND idArticles = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "SQB");
            easy_sales.Pst.setString(4, Article);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockTM(String Article){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType = ? AND idCycle = ? AND qlteArticle = ? AND idArticles = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "TM/LM");
            easy_sales.Pst.setString(4, Article);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockSHOE(){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType = ? AND idCycle = ? AND qlteArticle = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "SHOE");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    /**
     * FIN STOCK INITIAL
     */
    
    /**
     * Début CONSOMMATION DU STOCK
     */
    public Integer stockCONSProduction(){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockCONSSQB(String Article){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND qlteArticle = ? AND idArticles = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "SQB");
            easy_sales.Pst.setString(4, Article);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockCONSTM(String Article){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND qlteArticle = ? AND idArticles = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "TM/LM");
            easy_sales.Pst.setString(4, Article);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockCONSSHOE(){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND qlteArticle = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "SHOE");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    /**
     * Fin Consommation du STock Initial
     */
    /**
     * Début du Pavement de vente
     */
    public Integer stocPAVEMENT(String Article){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT pteauVente FROM ArticleSite WHERE idSite = ? AND idArticles = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.setString(2, Article);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer pavementVente(){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(pteauVente) FROM ArticleSite WHERE idSite = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    /**
     * Les TOPs de la Journée (Consommation Journalière du STOCK)
     * (Début)
     */
    public Integer stockCONSProduction(Date dateJ){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND dateAppro = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setDate(3, dateJ);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockCONSSQB(String Article,Date dateJ){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND qlteArticle = ? AND idArticles = ? AND dateAppro = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "SQB");
            easy_sales.Pst.setString(4, Article);
            easy_sales.Pst.setDate(5, dateJ);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockCONSTM(String Article,Date dateJ){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND qlteArticle = ? AND idArticles = ? AND dateAppro = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "TM/LM");
            easy_sales.Pst.setString(4, Article);
            easy_sales.Pst.setDate(5, dateJ);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
    public Integer stockCONSSHOE(Date dateJ){
        int stock = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteAppro) FROM Appro WHERE idType <> ? AND idCycle = ? AND qlteArticle = ? AND dateAppro = ?");
            easy_sales.Pst.setString(1, "PRODUCTION");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.setString(3, "SHOE");
            easy_sales.Pst.setDate(4, dateJ);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                stock = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return stock;
    }
}
