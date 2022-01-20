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
    private String idClie, idArticles, idSite, idTvente, users;
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
            easy_sales.Pst.setString(7, PontParametres.getJrSemaine(Calendar.getInstance()));
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
            String jr = PontParametres.getJrSemaine(Calendar.getInstance());
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
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Prix Modifié");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public String paramVentes(){
        String paramS = "";
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT nomParam,dateOuverture FROM parametreventes WHERE etatParam = ?");
            easy_sales.Pst.setString(1, "A");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                paramS = easy_sales.rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return paramS;
    }
    public void creerParam(String a){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO parametreVentes "
                    + "(nomParam,dateOuverture,etatParam,users) VALUES (?,now(),?,?)");
            easy_sales.Pst.setString(1, a);
            easy_sales.Pst.setString(2, "A");
            easy_sales.Pst.setString(3, PontParametres.User);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public void tuerParam(String a){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (ClientPreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE parametreVentes "
                    + "SET dateFermeture = now(), etatParam = ? WHERE nomParam = ?");
            easy_sales.Pst.setString(1, "B");
            easy_sales.Pst.setString(2, a);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
}
//                Calendar cl = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                String dte = easy_sales.rs.getString(2);
//                Date dt2 = sdf.parse(dte);
//                cl.setTime(dt2);
