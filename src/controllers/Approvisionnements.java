/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import java.util.Calendar;
import modeles.easy_sales;

/**
 *
 * @author Faustin PADINGANYI
 */
public class Approvisionnements {
    private String idType, idArticles, jrAppro,idSite;
    private int qteAppro;
    public Approvisionnements(){
        
    }
    public Approvisionnements(String idType, String idArticles,String idSite, int qteAppro){
        this.idType = idType;
        this.idArticles = idArticles;
        this.idSite = idSite;
        this.qteAppro = qteAppro;
    }

    public String getJrAppro() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int JJ = day-1;
        switch (JJ){
            case 1: jrAppro = "Lundi";
                    break;
            case 2: jrAppro="Mardi";
                    break;
            case 3: jrAppro = "Mercredi";
                    break;
            case 4: jrAppro = "Jeudi";
                    break;
            case 5: jrAppro = "Vendredi";
                    break;
            case 6: jrAppro = "Samedi";
                    break;
            case 0: jrAppro = "Dimanche";
                    break;
        }
        return jrAppro;
    }
    public void enregistrerAppro(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO Appro (idType,idArticles,"
                    + "qteAppro,jrAppro,idSite, dateAppro) VALUES (?,?,?,?,?,now())");
            easy_sales.Pst.setString(1, idType);
            easy_sales.Pst.setString(2, idArticles);
            easy_sales.Pst.setInt(3, qteAppro);
            easy_sales.Pst.setString(4, getJrAppro());
            easy_sales.Pst.setString(5, idSite);
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
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles, desiArticle, qteStock FROM Articles WHERE idArticles =?");
            easy_sales.Pst.setString(1, idArticles);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                String ID = easy_sales.rs.getString(1);
                int qte = easy_sales.rs.getInt(3);
                qte += qteAppro;
                easy_sales.deconnexionEasy();
                easy_sales.connexionEasy();
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET qtestock = ? WHERE idarticles = ?");
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
