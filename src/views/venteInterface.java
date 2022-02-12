/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.mysql.jdbc.PreparedStatement;
import controllers.PontParametres;
import controllers.Ventes;
import java.io.File;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modeles.easy_sales;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class venteInterface extends javax.swing.JInternalFrame {

    /**
     * Creates new form venteInterface
     */
    public venteInterface() {
        initComponents();
        setLocation(100, 50);
        chargerVente();
        numeroFacture();
        nettoie();
    }

    void nettoie(){
        txtIdProd.setText(null);
        txtLibPro.setText(null);
        lblSite.setText(PontParametres.site);
        txtPU.setText("0");
        txtQTE.setText("0");
        txtIdProd.setEditable(false);
        txtLibPro.setEditable(false);
        txtNomClient.setText(null);
        chargerStock();
//        chargerVente();
        chargerCBVente();
        chargerTVentes();
        txtIdClient.requestFocus();
    }
    void numeroFacture(){
        ventes = new Ventes();
        txtNFact.setText(ventes.numeroFacture());
        txtIdClient.setText("00243");
    }
    void chargerCBVente(){
        try {
            cbTVente.removeAllItems();
            ventes = new Ventes();
            boolean hj = ventes.heureDeJoie();
            if (hj) {
                cbTVente.addItem("HEURE DE JOIE");
            }else{
                cbTVente.addItem(ventes.paramVentes());
            }
            easy_sales.deconnexionEasy();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    void chargerTVentes(){
        try {
            ventes = new Ventes();
            String param = ventes.paramVentes();
            if (param.equals("")) {
                ventes.creerParam("NORMAL");
                param = ventes.paramVentes();
            }
                cbTVente.setSelectedItem(param);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    DefaultTableModel dt1;
    void chargerVente(){
        try {
            dt1 = new DefaultTableModel();
            dt1.addColumn("ID Pro");
            dt1.addColumn("QTE");
            dt1.addColumn("PU");
            dt1.addColumn("Type Vente");
            dt1.addColumn("Article");
            tblVente.setModel(dt1);
            tblVente.getColumn("QTE").setMaxWidth(60);
            tblVente.getColumn("PU").setMaxWidth(60);
            tblVente.getColumn("Type Vente").setMaxWidth(0);
            tblVente.getColumn("ID Pro").setMaxWidth(100);
            tblVente.getColumn("Article").setMaxWidth(0);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    DefaultTableModel dt;
    Ventes ventes = null;
    void chargerStock(){
        try {
            dt = new DefaultTableModel();
            dt.addColumn("ID PRO");
            dt.addColumn("PV Vente");
            dt.addColumn("DesiPro");
            tblStock.setModel(dt);
            tblStock.getColumn("PV Vente").setMaxWidth(60);
            tblStock.getColumn("DesiPro").setMaxWidth(0);
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT desiArticle, qteStock, idArticles,pteauvente FROM articlesite WHERE idSite = ?");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) {                
                String a,b,d;
                a = easy_sales.rs.getString(4);
                b = easy_sales.rs.getString(1);
                d = easy_sales.rs.getString(3);
                String [] c = {d,a,b};
                dt.addRow(c);
            }
            tblStock.setModel(dt);
            easy_sales.deconnexionEasy();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVente = new javax.swing.JTable();
        lblSite = new javax.swing.JLabel();
        cbTVente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        txtLibPro = new javax.swing.JTextField();
        txtPU = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQTE = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtNFact = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdClient = new javax.swing.JTextField();
        txtNomClient = new javax.swing.JTextField();
        lblPrixTot = new javax.swing.JLabel();
        lblPrixTot1 = new javax.swing.JLabel();
        lblPrixTot2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Les Produits en Stock"));

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
        jScrollPane2.setViewportView(tblStock);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Les Commandes"));

        tblVente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblVente);

        lblSite.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        lblSite.setForeground(new java.awt.Color(255, 255, 255));
        lblSite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cbTVente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTVente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTVenteItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Produit :");

        txtIdProd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIdProd.setText("jTextField1");

        txtLibPro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLibPro.setText("jTextField1");

        txtPU.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPU.setText("jTextField1");

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CDF");

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantité :");

        txtQTE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQTE.setText("jTextField1");

        jButton1.setText("Ajouter à la liste");
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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/b_edit.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Valider Facture");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtNFact.setEditable(false);
        txtNFact.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Client :");

        txtIdClient.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIdClient.setText("jTextField1");
        txtIdClient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdClientFocusLost(evt);
            }
        });

        txtNomClient.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNomClient.setText("jTextField1");

        lblPrixTot.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPrixTot.setForeground(new java.awt.Color(255, 255, 255));
        lblPrixTot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrixTot.setText("0");

        lblPrixTot1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPrixTot1.setForeground(new java.awt.Color(255, 255, 255));
        lblPrixTot1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrixTot1.setText("CDF");

        lblPrixTot2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblPrixTot2.setForeground(new java.awt.Color(255, 255, 255));
        lblPrixTot2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrixTot2.setText("Total :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLibPro, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNomClient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNFact, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTVente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblPrixTot2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPrixTot, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPrixTot1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSite, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbTVente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLibPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPrixTot, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPrixTot1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPrixTot2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    Integer ajoutFacture(){
        int prix = Integer.parseInt(lblPrixTot.getText().trim());
        try {
            if (Integer.parseInt(txtPU.getText().trim())>0 && Integer.parseInt(txtQTE.getText().trim())>0) {
                prix += Integer.parseInt(txtPU.getText().trim()) * Integer.parseInt(txtQTE.getText().trim());
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return prix;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ventes = new Ventes();
        try {
            String e  =  txtLibPro.getText().trim();
            int b = Integer.parseInt(txtQTE.getText().trim());
            int c = Integer.parseInt(txtPU.getText().trim());
            String d = cbTVente.getSelectedItem().toString().trim();
            String a = txtIdProd.getText().trim();
            String site = lblSite.getText();
            if (e.equals("") || b<1 || c<1) {
                JOptionPane.showMessageDialog(this, "Toutes les zones sont obligatoires", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (ventes.qteProduit(a,site) < b) {
                JOptionPane.showMessageDialog(this, "La quantité en Stock est de : "+ventes.qteProduit(a,site)+", vous ne pouvez vendre qu'une quantité inférieure ou égale", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Object [] f = {a,b,c,d,e};
//            DefaultTableModel model = (DefaultTableModel) tblVente.getModel();
            dt1.addRow(f);
            lblPrixTot.setText(ajoutFacture().toString());
            nettoie();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockMouseClicked
        // TODO add your handling code here:
        txtLibPro.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 2).toString());
        txtIdProd.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 0).toString());
        txtPU.requestFocus();
        
    }//GEN-LAST:event_tblStockMouseClicked

    Integer retirerFacture(){
        int prix = Integer.parseInt(lblPrixTot.getText().trim());
        try {
            prix -= Integer.parseInt(tblVente.getValueAt(tblVente.getSelectedRow(), 1).toString()) * Integer.parseInt(tblVente.getValueAt(tblVente.getSelectedRow(), 2).toString());
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
        return prix;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int viewIndex = tblVente.getSelectedRow();
        if(viewIndex != -1) {
            int modelIndex = tblVente.convertRowIndexToModel(viewIndex); // converts the row index in the view to the appropriate index in the model
            dt1 = (DefaultTableModel)tblVente.getModel();
            lblPrixTot.setText(retirerFacture().toString());
            dt1.removeRow(modelIndex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbTVenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTVenteItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTVenteItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
        new adminConnexion().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    DateFormatSymbols dtf=new DateFormatSymbols();
    SimpleDateFormat sdt=new SimpleDateFormat("yyyy-MM-dd",dtf);
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            int nbrLigne = tblVente.getRowCount();
            String numClient = txtIdClient.getText();
            if (numClient.length() < 10) {
                int msg = JOptionPane.showConfirmDialog(this, "Vous n'avez pas renseigner le numéro du Client, voulez vous le renseigner ?", "Easy Sales", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (msg == JOptionPane.YES_OPTION) {
                    return;
                }
            }
            int msg = JOptionPane.showConfirmDialog(this, "Les données sont - elles correctes ?", "Easy Sales", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (msg == JOptionPane.NO_OPTION) {
                return;
            }
            if (nbrLigne <= 0) {
                int msg1 = JOptionPane.showConfirmDialog(this, "Find de la Journée ?", "Easy Sales", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (msg1 == JOptionPane.YES_OPTION) {
                    easy_sales.connexionEasy();
                    Calendar cal = Calendar.getInstance();
                    java.util.Date dateJ = new java.util.Date(cal.getTimeInMillis());
                    easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles,sommeQTEVente,prixTotal,dateVente,jrVente,idSite,semaineVente FROM totalVenteJournalier WHERE dateVente = ?");
                    easy_sales.Pst.setObject(1,new java.sql.Date(cal.getTimeInMillis()));
                    easy_sales.rs = easy_sales.Pst.executeQuery();
                    if (easy_sales.rs.next()) {
                        Map para = new HashMap();
                        para.put("dateJ", dateJ);
                        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("etats/venteDJour.jasper"),para,easy_sales.cn);
                        JasperViewer.viewReport(jp, false);
                        return;
                    }
                }
            }else{
                String site = lblSite.getText().trim();
                String client = txtIdClient.getText().trim();
                String user = PontParametres.User;
                String facture = txtNFact.getText().trim();
                for (int i = 0; i < nbrLigne; i++) {
                    String idArt = tblVente.getValueAt(i, 0).toString();
                    String idTVente = tblVente.getValueAt(i, 3).toString();
                    int qteVente = Integer.parseInt(tblVente.getValueAt(i, 1).toString());
                    int pUnit = Integer.parseInt(tblVente.getValueAt(i, 2).toString());
                    String nomClient = txtNomClient.getText();
                    ventes = new Ventes(client, idArt, site, idTVente, user, qteVente, pUnit, facture,nomClient);
                    if (idTVente.equals("HEURE DE JOIE")) {
                        boolean tr = ventes.heureDeJoieActif();
                        if (!tr) {
                            ventes.heureDeJoieActif();
                        }
                    }
                    boolean trClie = ventes.rechercherClient();
                    if (!trClie) {
                        ventes.enregistrerClient();
                    }
                    boolean tvT = ventes.trTypeVente();
                    if (!tvT) {
                        ventes.creerTVente();
                    }
                    ventes.enregistrerVente();
                    ventes.updateQtePro();
                }
                JOptionPane.showMessageDialog(this, "Vente effectuée", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
            }
            ventes = new Ventes();
                int numeroD = ventes.numeroDHJetJ100("Journal100");
                if (numeroD == 0) {
                    ventes.debut1001ERArticles();
                }else{
                    int qte100 = ventes.qteDes100();
                    if (qte100>=100) {
                        ventes.ajoutQTE100ARticles();
                        JOptionPane.showMessageDialog(this, "Vous avez atteint "+qte100+" Articles Vendus", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                        /**
                         * Impression Liste des 100
                         */
                        impression100();
                        /**
                         * Fin Impression
                         */
                        ventes.fermerJournal100();
                        ventes.debut1001ERArticles();
                    }else{
                        qte100 += ventes.produitParFacture(PontParametres.getNumeroFacture());
                        if (qte100>=100) {
                            ventes.ajoutQTE100ARticles();
                            ventes.fermerJournal100();
                            JOptionPane.showMessageDialog(this, "Vous avez atteint "+qte100+" Articles Vendus", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                            /**
                             * Impression Liste des 100
                             */
                            impression100();
                            /**
                             * Fin Impression
                             */
                        }else{
                            ventes.ajoutQTE100ARticles();
                        }
                    }
                }
            boolean HJ = ventes.heureDeJoieActif();
            if (!HJ) {
                ventes.listArticleHJ0();
            }
            numeroFacture();
            chargerVente();
            nettoie();
            lblPrixTot.setText("0");
            /**
             * Début de l'impression
             */
            imprimerFacture(PontParametres.getNumeroFacture());
            /**
             * Fin de l'Impression
             */
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur : "+e.getMessage(), "Easy Sales", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    void impression100(){
        int numD = ventes.numeroDHJetJ100("Journal100", "numeroD");
        int numF = ventes.numeroDHJetJ100("Journal100", "numeroF");
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idArticles, sum(QTEVENTE) FROM Ventes WHERE idSite = ? AND idVentes BETWEEN ? AND ? "
                    + " GROUP BY idArticles");
            easy_sales.Pst.setString(1, PontParametres.site);
            easy_sales.Pst.setInt(2, numD);
            easy_sales.Pst.setInt(3, numF);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                Map para = new HashMap();
                para.put("numeroD", numD);
                para.put("numeroF", numF);
                para.put("site", PontParametres.site);
                JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("etats/liste100.jasper"),para,easy_sales.cn);
//                JasperPrintManager.printReport(jp, true);
                JasperViewer.viewReport(jp, false);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    private void txtIdClientFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdClientFocusLost
        // TODO add your handling code here:
        rechercherClient(txtIdClient.getText());
    }//GEN-LAST:event_txtIdClientFocusLost

    void rechercherClient(String ID){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idClie,nomClient FROM Clients WHERE idClie = ?");
            easy_sales.Pst.setString(1, ID);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                txtNomClient.setText(easy_sales.rs.getString(2));
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+ e.getMessage());
        }
    }
    void imprimerFacture(String numFact){
        try {
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT idSite,numFact, "
                    + "desiArticle, qteVente, prixVente, dateVente FROM les_ventes WHERE numFact = ?");
            easy_sales.Pst.setString(1, numFact);
            easy_sales.rs = easy_sales.Pst.executeQuery();
            if (easy_sales.rs.next()) {
                Map para = new HashMap();
                para.put("hFacture", numFact);
                para.put("site", PontParametres.site);
                JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("etats/facture1.jasper"),para,easy_sales.cn);
//                JasperPrintManager.printReport(jp, true);
                JasperViewer.viewReport(jp, false);
            }
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTVente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblPrixTot;
    private javax.swing.JLabel lblPrixTot1;
    private javax.swing.JLabel lblPrixTot2;
    private javax.swing.JLabel lblSite;
    private javax.swing.JTable tblStock;
    private javax.swing.JTable tblVente;
    private javax.swing.JTextField txtIdClient;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtLibPro;
    private javax.swing.JTextField txtNFact;
    private javax.swing.JTextField txtNomClient;
    private javax.swing.JTextField txtPU;
    private javax.swing.JTextField txtQTE;
    // End of variables declaration//GEN-END:variables
}
