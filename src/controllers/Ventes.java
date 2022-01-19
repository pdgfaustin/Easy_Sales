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
public class Ventes {
    private String idClie, idArticles, idSite, idTvente,jrVente, users;
    private int qteVente,prixVente;
    public Ventes(){
        
    }
    public Ventes(String idClie,String idArticles,String idSite,String idTvente,String users,int qteVente, int prixVente){
        this.idClie = idClie;
        this.idArticles = idArticles;
        this.idSite = idSite;
        this.idTvente = idTvente;
        this.users = users;
        this.qteVente = qteVente;
        this.prixVente = prixVente;
    }
    public String getJrVente() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int JJ = day-1;
        switch (JJ){
            case 1: jrVente = "Lundi";
                    break;
            case 2: jrVente="Mardi";
                    break;
            case 3: jrVente = "Mercredi";
                    break;
            case 4: jrVente = "Jeudi";
                    break;
            case 5: jrVente = "Vendredi";
                    break;
            case 6: jrVente = "Samedi";
                    break;
            case 0: jrVente = "Dimanche";
                    break;
        }
        return jrVente;
    }
    public void enregistrerVente(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO ventes "
                    + "(idClie, idArticles, idSite, idTVentes, qteVente, prixVente, jrVente, "
                    + "users, dateVente VALUES (?,?,?,?,?,?,?,?,now())");
            easy_sales.Pst.setString(1, idClie);
            easy_sales.Pst.setString(2, idArticles);
            easy_sales.Pst.setString(3, idSite);
            easy_sales.Pst.setString(4, idTvente);
            easy_sales.Pst.setInt(5, qteVente);
            easy_sales.Pst.setInt(6, prixVente);
            easy_sales.Pst.setString(7, getJrVente());
            easy_sales.Pst.setString(8, users);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public static void fixerPrix(String ID, int prixUnitaire){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles FROM Articles WHERE idArticles = ?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                easy_sales.deconnexionEasy();
                easy_sales.connexionEasy();
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = ? WHERE idArticles = ?");
                easy_sales.Pst.setInt(1, prixUnitaire);
                easy_sales.Pst.setString(2, ID);
                easy_sales.Pst.execute();
                easy_sales.deconnexionEasy();
                System.out.println("Prix Fixé");
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public void fixerNouveauPrix(){
        try {
            easy_sales.connexionEasy();
            String jr = getJrVente();
            if (jr.equals("Mardi")) {
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 8000 WHERE prixUnitaire > 8000");
            }else if(jr.equals("Mercredi")){
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 7000 WHERE prixUnitaire > 7000");
            }else if(jr.equals("Jeudi")){
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 6000 WHERE prixUnitaire > 6000");
            }else if(jr.equals("Vendredi")){
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 5000 WHERE prixUnitaire > 5000");
            }else if(jr.equals("Samedi")){
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 4000 WHERE prixUnitaire > 4000");
            }else if(jr.equals("Dimanche")){
                easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 3000 WHERE prixUnitaire > 3000");
            }
            easy_sales.deconnexionEasy();
            System.out.println("Prix Modifié");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
}
