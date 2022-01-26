/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.mysql.jdbc.PreparedStatement;
import controllers.Approvisionnements;
import controllers.PontParametres;
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
        setLocation(50, 100);
        lblSite.setText(PontParametres.site);
        chargerAppro();
        chargerStock();
        chargerCBAppro();
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
            dt.addColumn("Produit");
            dt.addColumn("QTE");
            dt.addColumn("ID PRO");
            tblStock.setModel(dt);
            tblStock.getColumn("QTE").setMaxWidth(50);
            tblStock.getColumn("ID PRO").setMaxWidth(0);
            easy_sales.connexionEasy();
            easy_sales.Pst = (PreparedStatement) easy_sales.cn.clientPrepareStatement("SELECT desiArticle, qteStock, idArticles FROM articles");
            easy_sales.rs = easy_sales.Pst.executeQuery();
            while (easy_sales.rs.next()) {                
                String a,b,d;
                a = easy_sales.rs.getString(1);
                b = easy_sales.rs.getString(2);
                d = easy_sales.rs.getString(3);
                String [] c = {a,b,d};
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
            dt1.addColumn("QTE");
            dt1.addColumn("Type Appro");
            dt1.addColumn("ID Pro");
            tblAppro.setModel(dt1);
            tblAppro.getColumn("QTE").setMaxWidth(60);
            tblAppro.getColumn("Type Appro").setMaxWidth(0);
            tblAppro.getColumn("ID Pro").setMaxWidth(0);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLibPro))
                    .addComponent(cbTAppro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSite, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                            .addComponent(txtQTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(27, 27, 27)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3)))
                .addGap(0, 22, Short.MAX_VALUE))
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
        txtLibPro.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 0).toString());
        txtIdProd.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 2).toString());
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
            if (!a.equals("Lundi") && b.equals("BALANCE DEBUT")) {
                JOptionPane.showMessageDialog(this, "La Balance du début ne se fait que le lundi ! Revoir cela SVP", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String c = txtIdProd.getText().trim();
            if (a.isEmpty() || c.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Toutes les zones sont obligatoires SVP", "Easy Sales", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Object [] d = {a,qte,b,c};
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
            Approvisionnements Ap =  null;
            for (int i = 0; i < nbrLigne; i++) {
                String a,b,c,d;
                a = tblAppro.getValueAt(i, 0).toString();
                int e = Integer.parseInt(tblAppro.getValueAt(i, 1).toString());
                b = tblAppro.getValueAt(i, 2).toString();
                c = tblAppro.getValueAt(i, 3).toString();
                d = lblSite.getText().trim();
                Ap = new Approvisionnements(b, c, d, e,PontParametres.User);
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
    private javax.swing.JComboBox<String> cbTAppro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblSite;
    private javax.swing.JTable tblAppro;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtLibPro;
    private javax.swing.JTextField txtQTE;
    // End of variables declaration//GEN-END:variables
}
