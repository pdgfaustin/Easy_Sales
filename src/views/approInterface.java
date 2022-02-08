/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.mysql.jdbc.PreparedStatement;
import controllers.Approvisionnements;
import controllers.PontParametres;
import controllers.Ventes;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modeles.easy_sales;

/**
 *
 * @author user
 */
public class approInterface extends javax.swing.JInternalFrame {

    /**
     * Creates new form approInterface
     */
    public approInterface() {
        initComponents();
        initialize();
    }

    void initialize(){
        setLocation(50, 40);
        lblSite.setText(PontParametres.site);
        chargerAppro();
        chargerStock();
        chargerCBAppro();
        production();
        nettoie();
    }
    void nettoie(){
        txtIdProd.setText(null);
        txtIdProd.setEditable(false);
        txtLibPro.setText(null);
        txtLibPro.setEditable(false);
        txtQTE.setText("0");
        txtQTE.requestFocus();
    }
    DefaultTableModel dt;
    void chargerStock(){
        try {
            dt = new DefaultTableModel();
            dt.addColumn("ID PRO");
            dt.addColumn("QTE PRO");
            dt.addColumn("PV Vente");
            dt.addColumn("Article");
            tblStock.setModel(dt);
            tblStock.getColumn("QTE PRO").setMaxWidth(60);
            tblStock.getColumn("PV Vente").setMaxWidth(60);
            tblStock.getColumn("Article").setMaxWidth(0);
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT desiArticle, qteStock, idArticles,pteauvente FROM articlesite WHERE idSite = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) {                
                String a,b,d,e;
                e = easy_sales.rs.getString(1);
                a = easy_sales.rs.getString(4);
                b = easy_sales.rs.getString(2);
                d = easy_sales.rs.getString(3);
                String [] c = {d,b,a,e};
                dt.addRow(c);
            }
            tblStock.setModel(dt);
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    DefaultTableModel dt1 ;
    void chargerAppro(){
        try {
            dt1 = new DefaultTableModel();
            dt1.addColumn("Article");
            dt1.addColumn("ID Pro");
            dt1.addColumn("Qualité");
            dt1.addColumn("QTE");
            dt1.addColumn("Type Appro");
            tblAppro.setModel(dt1);
            tblAppro.getColumn("QTE").setMaxWidth(60);
            tblAppro.getColumn("Article").setMaxWidth(0);
            tblAppro.getColumn("Qualité").setMaxWidth(100);
            tblAppro.getColumn("ID Pro").setMaxWidth(100);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    void chargerCBAppro(){
        try {
            cbTAppro.removeAllItems();
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idType FROM TypeAppro");
            easy_sales.rs=easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) {                
                cbTAppro.addItem(easy_sales.rs.getString(1));
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    Approvisionnements AP = null;
    DateFormatSymbols dtf=new DateFormatSymbols();
    SimpleDateFormat sdt=new SimpleDateFormat("yyyy-MM-dd",dtf);
    void production(){
        AP = new Approvisionnements();
        /**
         * Chargement des SQB
         */
        lblTotPro.setText(String.valueOf(AP.stockProduction() - AP.stockCONSProduction()));
        lblProBAG.setText(String.valueOf(AP.stockSQB("BAG") - AP.stockCONSSQB("BAG")));
        lblProCHA.setText(String.valueOf(AP.stockSQB("CHA") - AP.stockCONSSQB("CHA")));
        lblProClo.setText(String.valueOf(AP.stockSQB("CLO") - AP.stockCONSSQB("CLO")));
        lblProMat.setText(String.valueOf(AP.stockSQB("MAT") - AP.stockCONSSQB("MAT")));
        lblProTNS.setText(String.valueOf(AP.stockSQB("TNS") - AP.stockCONSSQB("TNS")));
        /**
         * Chargement des TM/LM
         */
        lblTMProBAG.setText(String.valueOf(AP.stockTM("BAG") - AP.stockCONSTM("BAG")));
        lblTMProCHA.setText(String.valueOf(AP.stockTM("CHA") - AP.stockCONSTM("CHA")));
        lblTMProClo.setText(String.valueOf(AP.stockTM("CLO") - AP.stockCONSTM("CLO")));
        lblTMProMAT.setText(String.valueOf(AP.stockTM("MAT") - AP.stockCONSTM("MAT")));
        lblTMProTNS.setText(String.valueOf(AP.stockTM("TNS") - AP.stockCONSTM("TNS")));
        /**
         * Chargement SHOE
         */
        lblProSHOE.setText(String.valueOf(AP.stockSHOE()));
        
        /**
         * Chargement Pavement des Ventes
         */
        lblPVBAG.setText(String.valueOf(AP.stocPAVEMENT("BAG")));
        lblPVCHA.setText(String.valueOf(AP.stocPAVEMENT("CHA")));
        lblPVClo.setText(String.valueOf(AP.stocPAVEMENT("CLO")));
        lblPVMAT.setText(String.valueOf(AP.stocPAVEMENT("MAT")));
        lblPVSHOE.setText(String.valueOf(AP.stocPAVEMENT("SHOE")));
        lblPVTNS.setText(String.valueOf(AP.stocPAVEMENT("TNS")));
        lblTotPvmnt.setText(String.valueOf(AP.pavementVente()));
        /**
         * Chargement des TOPs UP du Jour
         */
        try {
            String a = String.valueOf(Calendar.getInstance().getTime());
            java.util.Date dte1 = new java.util.Date();
            java.sql.Date dte2 = new Date(dte1.getTime());
            lblTopBAG.setText(String.valueOf(AP.stockCONSSQB("BAG", dte2)));
            lblTopCHA.setText(String.valueOf(AP.stockCONSSQB("CHA", dte2)));
            lblTopClo.setText(String.valueOf(AP.stockCONSSQB("CLO", dte2)));
            lblTopMAT.setText(String.valueOf(AP.stockCONSSQB("MAT", dte2)));
            lblTopTNS.setText(String.valueOf(AP.stockCONSSQB("TNS", dte2)));
            lblBAGTM.setText(String.valueOf(AP.stockCONSTM("BAG", dte2)));
            lblCHATM.setText(String.valueOf(AP.stockCONSTM("CHA", dte2)));
            lblCLOTM.setText(String.valueOf(AP.stockCONSTM("CLO", dte2)));
            lblMATTM.setText(String.valueOf(AP.stockCONSTM("MAT", dte2)));
            lblTNSTM.setText(String.valueOf(AP.stockCONSTM("TNS", dte2)));
            lblSHOETM.setText(String.valueOf(AP.stockCONSSHOE(dte2)));
            lblJrTop.setText(String.valueOf(AP.stockCONSProduction(dte2)));
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAppro = new javax.swing.JTable();
        lblSite = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        txtLibPro = new javax.swing.JTextField();
        cbTAppro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtQTE = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        CBQ = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblProClo = new javax.swing.JLabel();
        lblProBAG = new javax.swing.JLabel();
        lblProCHA = new javax.swing.JLabel();
        lblProMat = new javax.swing.JLabel();
        lblProTNS = new javax.swing.JLabel();
        lblTMProClo = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTMProCHA = new javax.swing.JLabel();
        lblTMProBAG = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblTMProMAT = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblTMProTNS = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblProSHOE = new javax.swing.JLabel();
        lblTotPro = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblTopClo = new javax.swing.JLabel();
        lblTopBAG = new javax.swing.JLabel();
        lblTopCHA = new javax.swing.JLabel();
        lblTopMAT = new javax.swing.JLabel();
        lblTopTNS = new javax.swing.JLabel();
        lblCLOTM = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        lblCHATM = new javax.swing.JLabel();
        lblBAGTM = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblMATTM = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        lblTNSTM = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        lblSHOETM = new javax.swing.JLabel();
        lblJrTop = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        lblPVCHA = new javax.swing.JLabel();
        lblPVClo = new javax.swing.JLabel();
        lblPVBAG = new javax.swing.JLabel();
        lblPVMAT = new javax.swing.JLabel();
        lblPVTNS = new javax.swing.JLabel();
        lblPVSHOE = new javax.swing.JLabel();
        lblTotPvmnt = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Les Produits en Stock"));
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStockMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblStock);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Les Approvisionnements"));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        tblAppro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblAppro);

        lblSite.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblSite.setForeground(new java.awt.Color(255, 255, 255));
        lblSite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produit :");

        txtIdProd.setText("jTextField1");

        txtLibPro.setText("jTextField1");

        cbTAppro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("QTE :");

        txtQTE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQTE.setText("jTextField1");

        jButton1.setText("Ajouter à la Liste");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Retirer de la liste");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Valider Appro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        CBQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SQB", "TM/LM", "SHOE" }));

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Qualité Article :");

        jPanel2.setBackground(new java.awt.Color(30, 55, 55));

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Production");

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SQB");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CLO");

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CHA");

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BAG");

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("MAT");

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TNS");

        lblProClo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblProClo.setForeground(new java.awt.Color(255, 255, 255));
        lblProClo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProClo.setText("0");

        lblProBAG.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblProBAG.setForeground(new java.awt.Color(255, 255, 255));
        lblProBAG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProBAG.setText("0");

        lblProCHA.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblProCHA.setForeground(new java.awt.Color(255, 255, 255));
        lblProCHA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProCHA.setText("0");

        lblProMat.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblProMat.setForeground(new java.awt.Color(255, 255, 255));
        lblProMat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProMat.setText("0");

        lblProTNS.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblProTNS.setForeground(new java.awt.Color(255, 255, 255));
        lblProTNS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProTNS.setText("0");

        lblTMProClo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTMProClo.setForeground(new java.awt.Color(255, 255, 255));
        lblTMProClo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTMProClo.setText("0");

        jLabel17.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("CLO");

        jLabel18.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("CHA");

        lblTMProCHA.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTMProCHA.setForeground(new java.awt.Color(255, 255, 255));
        lblTMProCHA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTMProCHA.setText("0");

        lblTMProBAG.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTMProBAG.setForeground(new java.awt.Color(255, 255, 255));
        lblTMProBAG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTMProBAG.setText("0");

        jLabel21.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("BAG");

        jLabel22.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("MAT");

        lblTMProMAT.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTMProMAT.setForeground(new java.awt.Color(255, 255, 255));
        lblTMProMAT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTMProMAT.setText("0");

        jLabel24.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("TNS");

        lblTMProTNS.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTMProTNS.setForeground(new java.awt.Color(255, 255, 255));
        lblTMProTNS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTMProTNS.setText("0");

        jLabel26.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("SHOE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel27.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("TM/LM");

        lblProSHOE.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblProSHOE.setForeground(new java.awt.Color(255, 255, 255));
        lblProSHOE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProSHOE.setText("0");

        lblTotPro.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTotPro.setForeground(new java.awt.Color(255, 255, 255));
        lblTotPro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotPro.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(8, 8, 8)
                                .addComponent(lblProTNS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(5, 5, 5)
                                .addComponent(lblProMat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProClo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblProCHA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblProBAG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(8, 8, 8)
                                .addComponent(lblTMProTNS, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(5, 5, 5)
                                .addComponent(lblTMProMAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addComponent(lblTotPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addGap(40, 40, 40)
                        .addComponent(lblProSHOE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel27)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTMProClo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTMProCHA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTMProBAG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTotPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblProClo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblProCHA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblProBAG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblProMat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblProTNS))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblTMProClo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblTMProCHA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblTMProBAG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lblTMProMAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblTMProTNS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblProSHOE))
                .addGap(29, 29, 29))
        );

        jPanel9.setBackground(new java.awt.Color(30, 55, 55));

        jLabel35.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("TOP DU JOUR");

        jLabel36.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("SQB");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel37.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("CLO");

        jLabel38.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("CHA");

        jLabel39.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("BAG");

        jLabel40.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("MAT");

        jLabel41.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("TNS");

        lblTopClo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTopClo.setForeground(new java.awt.Color(255, 255, 255));
        lblTopClo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTopClo.setText("0");

        lblTopBAG.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTopBAG.setForeground(new java.awt.Color(255, 255, 255));
        lblTopBAG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTopBAG.setText("0");

        lblTopCHA.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTopCHA.setForeground(new java.awt.Color(255, 255, 255));
        lblTopCHA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTopCHA.setText("0");

        lblTopMAT.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTopMAT.setForeground(new java.awt.Color(255, 255, 255));
        lblTopMAT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTopMAT.setText("0");

        lblTopTNS.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTopTNS.setForeground(new java.awt.Color(255, 255, 255));
        lblTopTNS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTopTNS.setText("0");

        lblCLOTM.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblCLOTM.setForeground(new java.awt.Color(255, 255, 255));
        lblCLOTM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCLOTM.setText("0");

        jLabel48.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("CLO");

        jLabel49.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("CHA");

        lblCHATM.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblCHATM.setForeground(new java.awt.Color(255, 255, 255));
        lblCHATM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCHATM.setText("0");

        lblBAGTM.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblBAGTM.setForeground(new java.awt.Color(255, 255, 255));
        lblBAGTM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBAGTM.setText("0");

        jLabel52.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("BAG");

        jLabel53.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("MAT");

        lblMATTM.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblMATTM.setForeground(new java.awt.Color(255, 255, 255));
        lblMATTM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMATTM.setText("0");

        jLabel55.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("TNS");

        lblTNSTM.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTNSTM.setForeground(new java.awt.Color(255, 255, 255));
        lblTNSTM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTNSTM.setText("0");

        jLabel57.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("SHOE");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel58.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("TM/LM");

        lblSHOETM.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblSHOETM.setForeground(new java.awt.Color(255, 255, 255));
        lblSHOETM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSHOETM.setText("0");

        lblJrTop.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblJrTop.setForeground(new java.awt.Color(255, 255, 255));
        lblJrTop.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblJrTop.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(8, 8, 8)
                                .addComponent(lblTopTNS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(5, 5, 5)
                                .addComponent(lblTopMAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel37))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTopClo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTopCHA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTopBAG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel58)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35)
                        .addGap(61, 61, 61)
                        .addComponent(lblJrTop, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addGap(8, 8, 8)
                                .addComponent(lblTNSTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addGap(5, 5, 5)
                                .addComponent(lblMATTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel49)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCLOTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCHATM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBAGTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addGap(40, 40, 40)
                        .addComponent(lblSHOETM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblJrTop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(lblTopClo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lblTopCHA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblTopBAG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lblTopMAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lblTopTNS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(lblCLOTM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(lblCHATM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(lblBAGTM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(lblMATTM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(lblTNSTM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(lblSHOETM))
                .addGap(12, 12, 12))
        );

        jPanel14.setBackground(new java.awt.Color(30, 55, 55));

        jLabel29.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("PAVEMENT");

        jLabel30.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("CLO");

        jLabel76.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("CHA");

        jLabel77.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("BAG");

        jLabel78.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("MAT");

        jLabel79.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("TNS");

        jLabel80.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("SHOE");

        lblPVCHA.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPVCHA.setForeground(new java.awt.Color(255, 255, 255));
        lblPVCHA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPVCHA.setText("0");

        lblPVClo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPVClo.setForeground(new java.awt.Color(255, 255, 255));
        lblPVClo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPVClo.setText("0");

        lblPVBAG.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPVBAG.setForeground(new java.awt.Color(255, 255, 255));
        lblPVBAG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPVBAG.setText("0");

        lblPVMAT.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPVMAT.setForeground(new java.awt.Color(255, 255, 255));
        lblPVMAT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPVMAT.setText("0");

        lblPVTNS.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPVTNS.setForeground(new java.awt.Color(255, 255, 255));
        lblPVTNS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPVTNS.setText("0");

        lblPVSHOE.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPVSHOE.setForeground(new java.awt.Color(255, 255, 255));
        lblPVSHOE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPVSHOE.setText("0");

        lblTotPvmnt.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblTotPvmnt.setForeground(new java.awt.Color(255, 255, 255));
        lblTotPvmnt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotPvmnt.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(lblTotPvmnt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPVCHA, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPVClo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPVSHOE, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPVBAG, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPVMAT, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPVTNS, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblTotPvmnt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lblPVClo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(lblPVCHA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(lblPVBAG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(lblPVMAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(lblPVTNS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(lblPVSHOE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtLibPro))
                        .addComponent(cbTAppro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBQ, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblSite, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSite, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTAppro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLibPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockMouseClicked
        // TODO add your handling code here:
        txtLibPro.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 3).toString());
        txtIdProd.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 0).toString());
        String a  = txtIdProd.getText().trim();
        if (a.equalsIgnoreCase("SHOE")) {
            CBQ.setSelectedItem("SHOE");
        }
        txtQTE.requestFocus();
    }//GEN-LAST:event_tblStockMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String a = PontParametres.getJrSemaine(Calendar.getInstance());
            int qte = Integer.parseInt(txtQTE.getText().toUpperCase().trim());
            if (qte<1) {
                JOptionPane.showMessageDialog(this, "La quantité doit être supérieure à 0", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String b = cbTAppro.getSelectedItem().toString().trim();
//            if (!a.equals("Lundi") && b.equals("IN STAND")) {
//                if (!a.equals("Samedi") && b.equals("IN STAND")) {
//                    if (!a.equals("Dimanche") && b.equals("IN STAND")) {
//                        JOptionPane.showMessageDialog(this, "Le IN STAND ne se fait que soit le samedi, dimanche ou le lundi ! Revoir cela SVP", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
//                        return;
//                    }
//                }
//            }
            String c = txtIdProd.getText().trim();
            if (a.isEmpty() || c.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Toutes les zones sont obligatoires SVP", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Object [] d = {txtLibPro.getText(),txtIdProd.getText(),CBQ.getSelectedItem(),qte,b};
            dt1.addRow(d);
            nettoie();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int viewIndex = tblAppro.getSelectedRow();
        if(viewIndex != -1) {
            int modelIndex = tblAppro.convertRowIndexToModel(viewIndex); // converts the row index in the view to the appropriate index in the model
            dt1 = (DefaultTableModel)tblAppro.getModel();
            dt1.removeRow(modelIndex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            int nbrLigne = tblAppro.getRowCount();
            if (nbrLigne <= 0) {
                JOptionPane.showMessageDialog(this, "La liste à Approvisionner est vide", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String cbTA = cbTAppro.getSelectedItem().toString();
            if (!cbTA.equalsIgnoreCase("PRODUCTION")) {
                int msg = JOptionPane.showConfirmDialog(this, "Avez vous déjà préalablement enregistrer le Production ?", "Easy Sales", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (msg == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            int msg = JOptionPane.showConfirmDialog(this, "Les données sont - elles correctes ?", "Easy Sales", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (msg==JOptionPane.NO_OPTION) {
                return;
            }
            Approvisionnements Ap =  null;
            Ventes vt = new Ventes();
            if (vt.rechercherCycle().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Aucun Cycle n'est créé, prière de d'abord le faire SVP", "Easy Sales", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (int i = 0; i < nbrLigne; i++) {
                String a,b,c,d;
                a = tblAppro.getValueAt(i, 2).toString();
                int e = Integer.parseInt(tblAppro.getValueAt(i, 3).toString());
                b = tblAppro.getValueAt(i, 4).toString();
                c = tblAppro.getValueAt(i, 1).toString();
                d = lblSite.getText().trim();
                Ap = new Approvisionnements(b, c, d, e,PontParametres.User,a);
                Ap.enregistrerAppro();
                Ap.ajouterStock();
            }
            JOptionPane.showMessageDialog(this, cbTAppro.getSelectedItem().toString() + " effectuée", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
            initialize();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur : "+ e.getMessage(), "Easy Sales", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBQ;
    private javax.swing.JComboBox<String> cbTAppro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBAGTM;
    private javax.swing.JLabel lblCHATM;
    private javax.swing.JLabel lblCLOTM;
    private javax.swing.JLabel lblJrTop;
    private javax.swing.JLabel lblMATTM;
    private javax.swing.JLabel lblPVBAG;
    private javax.swing.JLabel lblPVCHA;
    private javax.swing.JLabel lblPVClo;
    private javax.swing.JLabel lblPVMAT;
    private javax.swing.JLabel lblPVSHOE;
    private javax.swing.JLabel lblPVTNS;
    private javax.swing.JLabel lblProBAG;
    private javax.swing.JLabel lblProCHA;
    private javax.swing.JLabel lblProClo;
    private javax.swing.JLabel lblProMat;
    private javax.swing.JLabel lblProSHOE;
    private javax.swing.JLabel lblProTNS;
    private javax.swing.JLabel lblSHOETM;
    private javax.swing.JLabel lblSite;
    private javax.swing.JLabel lblTMProBAG;
    private javax.swing.JLabel lblTMProCHA;
    private javax.swing.JLabel lblTMProClo;
    private javax.swing.JLabel lblTMProMAT;
    private javax.swing.JLabel lblTMProTNS;
    private javax.swing.JLabel lblTNSTM;
    private javax.swing.JLabel lblTopBAG;
    private javax.swing.JLabel lblTopCHA;
    private javax.swing.JLabel lblTopClo;
    private javax.swing.JLabel lblTopMAT;
    private javax.swing.JLabel lblTopTNS;
    private javax.swing.JLabel lblTotPro;
    private javax.swing.JLabel lblTotPvmnt;
    private javax.swing.JTable tblAppro;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtLibPro;
    private javax.swing.JTextField txtQTE;
    // End of variables declaration//GEN-END:variables
}
