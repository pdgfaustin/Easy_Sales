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
public class Approvisionnements {
    private String idType, idArticles,idSite,users;
    private int qteAppro;
    public Approvisionnements(){
        
    }
    public Approvisionnements(String idType, String idArticles,String idSite, int qteAppro,String users){
        this.idType = idType;
        this.idArticles = idArticles;
        this.idSite = idSite;
        this.qteAppro = qteAppro;
        this.users = users;
    }
    public void enregistrerAppro(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO Appro (idType,idArticles,"
                    + "qteAppro,jrAppro,idSite,users, dateAppro) VALUES (?,?,?,?,?,?,now())");
            easy_sales.Pst.setString(1, idType);
            easy_sales.Pst.setString(2, idArticles);
            easy_sales.Pst.setInt(3, qteAppro);
            easy_sales.Pst.setString(4, PontParametres.getJrSemaine(Calendar.getInstance()));
            easy_sales.Pst.setString(5, idSite);
            easy_sales.Pst.setString(6, users);
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
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles, desiArticle, qteStock FROM Articles WHERE idArticles =?");
            easy_sales.Pst.setString(1, idArticles);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                String ID = easy_sales.rs.getString(1);
                int qte = easy_sales.rs.getInt(3);
                qte += qteAppro;
                easy_sales.deconnexionEasy();
                easy_sales.connexionEasy();
                easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET qtestock = ? WHERE idarticles = ?");
                easy_sales.Pst.setString(2, ID);
                easy_sales.Pst.setInt(1, qte);
                easy_sales.Pst.execute();
                easy_sales.deconnexionEasy();
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
}
