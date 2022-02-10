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
public class Ventes {
    private String nFacture,idClie, idArticles, idSite, idTvente, users,nomClient,idCycle;
    private int qteVente,prixVente,semaineD, semaineF;
    public Ventes(){
        
    }
    public Ventes(int semaineD,int semaineF){
        this.semaineD = semaineD;
        this.semaineF = semaineF;
        idCycle = PontParametres.site + Calendar.getInstance().getWeekYear() + semaineD;
    }
    public Ventes(String idClie,String idArticles,String idSite,String idTvente,String users,int qteVente, int prixVente,String nFacture,String nomClient){
        this.idClie = idClie;
        this.idArticles = idArticles;
        this.idSite = idSite;
        this.idTvente = idTvente;
        this.users = users;
        this.qteVente = qteVente;
        this.prixVente = prixVente;
        this.nFacture = nFacture;
        this.nomClient = nomClient;
    }
    
    /**
     * Début des Opérations sur le Cycle de Vente
     */
    
    public void enregistrerCycle(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO cycleV (id,semaineD,semaineF,etat,site) "
                    + " VALUES (?,?,?,?,?)");
            easy_sales.Pst.setString(1, idCycle);
            easy_sales.Pst.setInt(2, semaineD);
            easy_sales.Pst.setInt(3, semaineF);
            easy_sales.Pst.setString(4, "A");
            easy_sales.Pst.setString(5, PontParametres.site);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Cycle Créer");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public String rechercherCycle(){
        String cycleV = "";
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT id FROM cycleV WHERE site = ? AND etat = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.setString(2, "A");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                cycleV = easy_sales.rs.getString(1);
                easy_sales.connexionEasy();
                PontParametres.setIdCycle(cycleV);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return cycleV;
    }
    public void fermerCycleDesArticles(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE ArticleSite SET qteStock = 0, pteauVente = 0 WHERE idSite = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE cycleV set dateFerm = now(), etat = ? WHERE id = ?");
            easy_sales.Pst.setString(1, "B");
            easy_sales.Pst.setString(2, PontParametres.getIdCycle());
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    
    /**
     * Fin des Opérations sur le Cycle
     */
    
    /**
     * Méthode sur le Numéro Facture
     * @return String Numéro Facture
     */
    
    public String numeroFacture(){
        String fact = "";
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT DISTINCT numFact FROM ventes WHERE idSite = ? ORDER BY idVentes DESC LIMIT 1");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                String a = easy_sales.rs.getString(1);
                int b = Integer.parseInt(a.substring(3, a.length()));
                b++;
                fact = "HPP" + b;
            }else{
                fact = "HPP1";
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return fact;
    }
    
    /**
     * Enregistrement du Client
     */
    
    public void enregistrerClient(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO clients (idClie,nomClient) VALUES (?,?)");
            easy_sales.Pst.setString(1, idClie);
            easy_sales.Pst.setString(2, nomClient);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Client Créé");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    
    /**
     * Recherche du Client
     * @return Vraie ou Faux, si le client existe ou pas
     */
    
    public boolean rechercherClient(){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idClie FROM Clients WHERE idClie = ?");
            easy_sales.Pst.setString(1, idClie);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    
    /**
     * Enregistrement des opérations sur la vente
     */
    
    public void enregistrerVente(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO ventes "
                    + "(idClie, idArticles, idSite, idTVentes, qteVente, prixVente, jrVente,"
                    + "users,numFact, semaineVente,idCycle, dateVente) VALUES (?,?,?,?,?,?,?,?,?,?,?,now())");
            easy_sales.Pst.setString(1, idClie);
            easy_sales.Pst.setString(2, idArticles);
            easy_sales.Pst.setString(3, idSite);
            easy_sales.Pst.setString(4, idTvente);
            easy_sales.Pst.setInt(5, qteVente);
            easy_sales.Pst.setInt(6, prixVente);
            easy_sales.Pst.setString(7, PontParametres.getJrSemaine(Calendar.getInstance()));
            easy_sales.Pst.setString(8, users);
            easy_sales.Pst.setString(9, nFacture);
            easy_sales.Pst.setInt(10, PontParametres.getSemaineYear(Calendar.getInstance()));
            easy_sales.Pst.setString(11, PontParametres.getIdCycle());
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Enregistrement effectué");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public void updateQtePro(){
        try {
            int nvlQTE = qteProduit(idArticles,idSite) - qteVente;
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articlesite SET pteauvente = ? WHERE idArticles = ? AND idsite = ?");
            easy_sales.Pst.setInt(1, nvlQTE);
            easy_sales.Pst.setString(2, idArticles);
            easy_sales.Pst.setString(3, idSite);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public Integer qteProduit(String ID, String IDSite){
        int QTE = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT pteauVente FROM articlesite WHERE idArticles = ? AND idsite = ?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.Pst.setString(2, IDSite);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                QTE = easy_sales.rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return QTE;
    }
    
    public static void fixerPrix(String ID, int prixUnitaire){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles FROM Articles WHERE idArticles = ?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                easy_sales.deconnexionEasy();
                easy_sales.connexionEasy();
                easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = ? WHERE idArticles = ?");
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
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 8000 WHERE prixUnitaire > 8000");
                }else if(jr.equals("Mercredi")){
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 7000 WHERE prixUnitaire > 7000");
                }else if(jr.equals("Jeudi")){
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 6000 WHERE prixUnitaire > 6000");
                }else if(jr.equals("Vendredi")){
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 5000 WHERE prixUnitaire > 5000");
                }else if(jr.equals("Samedi")){
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 4000 WHERE prixUnitaire > 4000");
                }else if(jr.equals("Dimanche")){
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Articles SET prixUnitaire = 3000 WHERE prixUnitaire > 3000");
                }
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Prix Modifié");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public boolean heureDeJoie(){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT nomParam,dateOuverture FROM parametreventes WHERE etatParam = ? AND nomParam = ? AND idSite = ?");
            easy_sales.Pst.setString(1, "A");
            easy_sales.Pst.setString(2, "HEURE DE JOIE");
            easy_sales.Pst.setString(3, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    /**
     * 
     * @return "Le Type de Vente activé"
     */
    public String paramVentes(){
        String paramS = "";
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT nomParam,dateOuverture FROM parametreventes WHERE etatParam = ? AND idSite = ?");
            easy_sales.Pst.setString(1, "A");
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                paramS = easy_sales.rs.getString(1);
            }else{
                easy_sales.deconnexionEasy();
                easy_sales.connexionEasy();
                easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO parametreventes (nomParam,dateOuverture,etatParam,idSite) VALUES(?,now(),?,?)");
                easy_sales.Pst.setString(1, "NORMAL");
                easy_sales.Pst.setString(2, "A");
                easy_sales.Pst.setString(3, PontParametres.site);
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return paramS;
    }
    public void creerParam(String a){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO parametreVentes "
                    + "(nomParam,dateOuverture,etatParam,users,idSite) VALUES (?,now(),?,?,?)");
            easy_sales.Pst.setString(1, a);
            easy_sales.Pst.setString(2, "A");
            easy_sales.Pst.setString(3, PontParametres.User);
            easy_sales.Pst.setString(4, PontParametres.site);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * 
     * @param a "Y Spécifier la valeur du Type de Vente"
     * @return  "Met fin au paramettre en cours spécifié"
     */
    public void tuerParam(String a){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE parametreVentes "
                    + "SET dateFermeture = now(), etatParam = ? WHERE nomParam = ? AND idSite = ?");
            easy_sales.Pst.setString(1, "B");
            easy_sales.Pst.setString(2, a);
            easy_sales.Pst.setString(3, PontParametres.site);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    public void creerTVente(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO TypeVentes (idTVentes) VALUES (?)");
            easy_sales.Pst.setString(1, idTvente);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());;
        }
    }
    public boolean trTypeVente(){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idTVentes FROM typeVentes WHERE idTventes = ?");
            easy_sales.Pst.setString(1, idTvente);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    
    /**
     ***** Taitement sur l'Heure de Joie et le Journal 100 ******
     ************************************************************/
    /**
     **** LE DERNIER NUMERO DE VENTE ****
     * **********************************
     * @return Integer
     */
    public Integer dernierNumVente(){
        int numero = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idVentes FROM Ventes WHERE "
                    + " idSite = ? ORDER BY idVentes DESC LIMIT 1");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                numero = easy_sales.rs.getInt(1);
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return numero;
    }
    
    /**
     ****HEURE DE JOIE ACTIF****
     * *************************
     * @return BOOLEAN
     */
    public boolean heureDeJoieActif(){
        boolean trouve = false;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT id FROM heureDeJoie WHERE numeroF = ? AND idSite = ?");
            easy_sales.Pst.setInt(1, 0);
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                trouve = true;
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return trouve;
    }
    /**
     ****CREATION DE LA LISTE DES PRODUITS HEURE DE JOIE****
     * *****************************************************
     */
    public void listArticleHJ0(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO heureDeJoie "
                    + "(numeroD,dateJ,JourAngo,idSIte) VALUES (?,now(),?,?)");
            easy_sales.Pst.setInt(1, dernierNumVente());
            easy_sales.Pst.setString(2, PontParametres.getJrSemaine(Calendar.getInstance()));
            easy_sales.Pst.setString(3, PontParametres.site);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Debut Liste Enregistré");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * ***NOMBRE DES PRODUITS DE LA FACTURE*** *
     * *************************************** *
     */
    public Integer produitParFacture(String numeroFact){
        int qte = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT SUM(qteVente) FROM Ventes WHERE numFact = ? AND idSite = ?");
            easy_sales.Pst.setString(1, numeroFact);
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                qte = easy_sales.rs.getInt(1);
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return qte;
    }
    /**
     ****CREATION LA LISTE DES 100 1ERS ARTICLES VENDUS****
     * ************************************************** *
     */
    public void debut1001ERArticles(){
        try {
            int a = produitParFacture(PontParametres.getNumeroFacture());
            int b = dernierNumVente();
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("INSERT INTO journal100 "
                    + " (numeroD,nbrArticle,idSite) VALUES (?,?,?)");
            easy_sales.Pst.setInt(1, b);
            easy_sales.Pst.setInt(2, a);
            easy_sales.Pst.setString(3, PontParametres.site);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Debut 100 Articles Créé");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * ***AJOUT QTE ARTICLE POUR ATEINDRE 100*** *
     * ***************************************** *
     */
    public void ajoutQTE100ARticles(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT nbrArticle FROM journal100 WHERE idSite = ? AND numeroF = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.setInt(2, 0);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                int a = easy_sales.rs.getInt(1);
                a += produitParFacture(PontParametres.getNumeroFacture());
                easy_sales.deconnexionEasy();
                easy_sales.connexionEasy();
                easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE journal100 SET nbrArticle = ? WHERE idSite = ? AND numeroF = ?");
                easy_sales.Pst.setInt(1, a);
                easy_sales.Pst.setString(2, PontParametres.site);
                easy_sales.Pst.setInt(3, 0);
                easy_sales.Pst.execute();
                easy_sales.deconnexionEasy();
                System.out.println("Qte des 100 Ajouté");
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * ***CATCH DE LA QTE DES 100 PREMIERS ARTICLES*** *
     * *********************************************** *
     */
    public Integer qteDes100(){
        int qte = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT nbrArticle FROM journal100 WHERE numeroF = ? AND idSite = ?");
            easy_sales.Pst.setInt(1, 0);
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                qte = easy_sales.rs.getInt(1);
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return qte;
    }
    /**
     * ***FERMER LE JOURNAL 100*** *
     * *************************** *
     */
    public void fermerJournal100(){
        try {
            int a = dernierNumVente();
            int b = numeroDHJetJ100("Journal100");
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE Journal100 SET numeroF = ? WHERE idSite = ? AND numeroD = ?");
            easy_sales.Pst.setInt(1, a);
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.Pst.setInt(3, b);
            easy_sales.Pst.execute();
            easy_sales.deconnexionEasy();
            System.out.println("Fermeture effectuée");
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * **NUMERO DU DEBUT HEURE DE JOIE ET JOURNAL 100 ** *
     * ************************************************* *
     */
    public Integer numeroDHJetJ100(String table){
        int numero = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT numeroD FROM " + table + " WHERE idSite = ? AND numeroF = ? ORDER BY id DESC LIMIT 1");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.setInt(2, 0);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                numero = easy_sales.rs.getInt(1);
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return numero;
    }
    public Integer numeroDHJetJ100(String table,String Rubrique){
        int numero = 0;
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT "+ Rubrique +" FROM " + table + " WHERE idSite = ? AND numeroF <> ? ORDER BY id DESC LIMIT 1");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.setInt(2, 0);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                numero = easy_sales.rs.getInt(1);
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return numero;
    }
    /**
     * **FERMER HEURE DE JOIE** *
     * ************************ *
     */
    public void fermerListeHJ(){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("UPDATE heureDeJoie SET numeroF = ? WHERE idSite = ? AND numeroD = ?");
            easy_sales.Pst.setInt(1, dernierNumVente());
            easy_sales.Pst.setString(2, PontParametres.site);
            easy_sales.Pst.setInt(3, numeroDHJetJ100("Journal100"));
            easy_sales.Pst.execute();
            System.out.println("Fermeture effectuée");
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
