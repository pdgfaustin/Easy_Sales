/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.mysql.jdbc.PreparedStatement;
import controllers.PontParametres;
import controllers.Ventes;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modeles.easy_sales;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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
        nettoie();
        numeroFacture();
    }

    void nettoie(){
        txtIdProd.setText(null);
        txtLibPro.setText(null);
        lblSite.setText(PontParametres.site);
        txtPU.setText("0");
        txtQTE.setText("0");
        txtIdProd.setEditable(false);
        txtLibPro.setEditable(false);
        txtPU.setEditable(false);
        chargerStock();
//        chargerVente();
        chargerCBVente();
        chargerTVentes();
        txtQTE.requestFocus();
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
            dt1.addColumn("Article");
            dt1.addColumn("QTE");
            dt1.addColumn("PU");
            dt1.addColumn("Type Vente");
            dt1.addColumn("ID Pro");
            tblVente.setModel(dt1);
            tblVente.getColumn("QTE").setMaxWidth(60);
            tblVente.getColumn("PU").setMaxWidth(60);
            tblVente.getColumn("Type Vente").setMaxWidth(0);
            tblVente.getColumn("ID Pro").setMaxWidth(0);
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }
    DefaultTableModel dt;
    Ventes ventes = null;
    void chargerStock(){
        try {
            dt = new DefaultTableModel();
            dt.addColumn("Produit");
            dt.addColumn("QTE");
            dt.addColumn("ID PRO");
            dt.addColumn("PU");
            tblStock.setModel(dt);
            tblStock.getColumn("QTE").setMaxWidth(50);
            tblStock.getColumn("ID PRO").setMaxWidth(0);
            tblStock.getColumn("PU").setMaxWidth(0); 
            ventes = new Ventes();
            ventes.fixerNouveauPrix();
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT desiArticle, qteStock, idArticles,prixUnitaire FROM articles");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) { 
                String a,b,d,e;
                a = easy_sales.rs.getString(1);
                b = easy_sales.rs.getString(2);
                d = easy_sales.rs.getString(3);
                e = easy_sales.rs.getString(4);
                String [] c = {a,b,d,e};
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblSite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtIdClient))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton4)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton2))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cbTVente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtLibPro, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNFact, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
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
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addGap(0, 26, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ventes = new Ventes();
        try {
            String a  =  txtLibPro.getText().trim();
            int b = Integer.parseInt(txtQTE.getText().trim());
            int c = Integer.parseInt(txtPU.getText().trim());
            String d = cbTVente.getSelectedItem().toString().trim();
            String e = txtIdProd.getText().trim();
            if (e.equals("") || b<1) {
                JOptionPane.showMessageDialog(this, "Toutes les zones sont obligatoires", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (ventes.qteProduit(e) < b) {
                JOptionPane.showMessageDialog(this, "La quantité en Stock est de : "+ventes.qteProduit(e)+", vous ne pouvez vendre qu'une quantité inférieure ou égale", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Object [] f = {a,b,c,d,e};
//            DefaultTableModel model = (DefaultTableModel) tblVente.getModel();
            dt1.addRow(f);
            nettoie();
        } catch (Exception e) {
            System.err.println("Erreur : "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockMouseClicked
        // TODO add your handling code here:
        String a = tblStock.getValueAt(tblStock.getSelectedRow(), 3).toString();
        int b = Integer.parseInt(a);
        if (b>0) {
            txtLibPro.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 0).toString());
            txtIdProd.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 2).toString());
            String al = cbTVente.getSelectedItem().toString();
            if (al.equals("NORMAL")) {
                txtPU.setText(a);
            }else if(al.equals("SOLDE")){
                txtPU.setEditable(true);
            }else if(al.equals("HEURE DE JOIE")){
                int prix = Integer.parseInt(a);
                if (prix >= PontParametres.prixParJour()) {
                   String nvPrix = String.valueOf(PontParametres.prixParJour() - 1000); 
                   txtPU.setText(nvPrix);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Ne peut être vendu qu'un produit ayant un prix", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);  
            
        }
    }//GEN-LAST:event_tblStockMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int viewIndex = tblVente.getSelectedRow();
        if(viewIndex != -1) {
            int modelIndex = tblVente.convertRowIndexToModel(viewIndex); // converts the row index in the view to the appropriate index in the model
            dt1 = (DefaultTableModel)tblVente.getModel();
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            int nbrLigne = tblVente.getRowCount();
            if (nbrLigne <= 0) {
                JOptionPane.showMessageDialog(this, "Aucun produit n'existe dans la liste à facturer", "Easy Sales", JOptionPane.ERROR_MESSAGE);
            }else{
                String site = lblSite.getText().trim();
                String client = txtIdClient.getText().trim();
                String user = PontParametres.User;
                String facture = txtNFact.getText().trim();
                for (int i = 0; i < nbrLigne; i++) {
                    String idArt = tblVente.getValueAt(i, 4).toString();
                    String idTVente = tblVente.getValueAt(i, 3).toString();
                    int qteVente = Integer.parseInt(tblVente.getValueAt(i, 1).toString());
                    int pUnit = Integer.parseInt(tblVente.getValueAt(i, 2).toString());
                    ventes = new Ventes(client, idArt, site, idTVente, user, qteVente, pUnit, facture);
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
            /**
             * Début de l'impression
             */
            imprimerFacture(txtNFact.getText().trim());
            /**
             * Fin de l'Impression
             */
            nettoie();
            numeroFacture();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur : "+e.getMessage(), "Easy Sales", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
                JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("etats/facture1.jasper"),para,easy_sales.cn);
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
    private javax.swing.JLabel lblSite;
    private javax.swing.JTable tblStock;
    private javax.swing.JTable tblVente;
    private javax.swing.JTextField txtIdClient;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtLibPro;
    private javax.swing.JTextField txtNFact;
    private javax.swing.JTextField txtPU;
    private javax.swing.JTextField txtQTE;
    // End of variables declaration//GEN-END:variables
}
