/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;



import controllers.PontParametres;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class MenuGen extends javax.swing.JFrame {

    /**
     * Creates new form MenuGen
     */
    public MenuGen() {
        initComponents();
        setLocationRelativeTo(this);
        setTitle("Easy Sales : For a secure sale. Vous êtes connecté en tant que : "+PontParametres.User);
        MInterVisa.setCollapsed(true);
        paraMe();
        try{
            this.setIconImage(new ImageIcon(getClass().getResource("/LesImages/HPP_Congo.jpg")).getImage());//donne l'icon au formulaire
        }catch  (Exception ex){
            JOptionPane.showMessageDialog(this, "Erreur : "+ex.getMessage(), "Easy Sales", JOptionPane.OK_OPTION);
            return;
        }
    }

    void paraMe(){
        String stat = PontParametres.statut;
        if (stat.equals("Caissier")) {
            MBaseVisa.setVisible(false);
            MBaseVisa1.setVisible(false);
            MInterVisa.setVisible(false);
        }else if(stat.equals("Gérant")){
            MBaseVisa.setVisible(false);
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

        Mvisa = new org.jdesktop.swingx.JXTaskPane();
        MBaseVisa = new org.jdesktop.swingx.JXTaskPane();
        jXHyperlink15 = new org.jdesktop.swingx.JXHyperlink();
        jXHyperlink16 = new org.jdesktop.swingx.JXHyperlink();
        MBaseVisa1 = new org.jdesktop.swingx.JXTaskPane();
        jXHyperlink9 = new org.jdesktop.swingx.JXHyperlink();
        MBaseVisa2 = new org.jdesktop.swingx.JXTaskPane();
        jXHyperlink10 = new org.jdesktop.swingx.JXHyperlink();
        MBaseVisa3 = new org.jdesktop.swingx.JXTaskPane();
        jXHyperlink17 = new org.jdesktop.swingx.JXHyperlink();
        MInterVisa = new org.jdesktop.swingx.JXTaskPane();
        jXHyperlink7 = new org.jdesktop.swingx.JXHyperlink();
        jXHyperlink11 = new org.jdesktop.swingx.JXHyperlink();
        DsKtp = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FRAIS SCOLAIRE : POUR UNE GESTION SAINE");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Mvisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/HPP_Congo.jpg"))); // NOI18N
        Mvisa.setTitle("Easy Sales :");
        Mvisa.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        Mvisa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                MvisaMouseDragged(evt);
            }
        });

        MBaseVisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/exec.png"))); // NOI18N
        MBaseVisa.setTitle("Parameters :");
        MBaseVisa.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N

        jXHyperlink15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/b_usradd.png"))); // NOI18N
        jXHyperlink15.setText("Users & Affectations");
        jXHyperlink15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXHyperlink15MouseClicked(evt);
            }
        });
        jXHyperlink15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink15ActionPerformed(evt);
            }
        });
        MBaseVisa.getContentPane().add(jXHyperlink15);

        jXHyperlink16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/b_usradd.png"))); // NOI18N
        jXHyperlink16.setText("Ouverture Cycle");
        jXHyperlink16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXHyperlink16MouseClicked(evt);
            }
        });
        jXHyperlink16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink16ActionPerformed(evt);
            }
        });
        MBaseVisa.getContentPane().add(jXHyperlink16);

        Mvisa.getContentPane().add(MBaseVisa);

        MBaseVisa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/iClear.png"))); // NOI18N
        MBaseVisa1.setTitle("Arrivals :");
        MBaseVisa1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N

        jXHyperlink9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/b_usrcheck.png"))); // NOI18N
        jXHyperlink9.setText("In Stand / Top Up");
        jXHyperlink9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXHyperlink9MouseClicked(evt);
            }
        });
        jXHyperlink9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink9ActionPerformed(evt);
            }
        });
        MBaseVisa1.getContentPane().add(jXHyperlink9);

        Mvisa.getContentPane().add(MBaseVisa1);

        MBaseVisa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/iClear.png"))); // NOI18N
        MBaseVisa2.setTitle("Easy Sales :");
        MBaseVisa2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N

        jXHyperlink10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/b_usrcheck.png"))); // NOI18N
        jXHyperlink10.setText("Pavement des Ventes");
        jXHyperlink10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXHyperlink10MouseClicked(evt);
            }
        });
        jXHyperlink10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink10ActionPerformed(evt);
            }
        });
        MBaseVisa2.getContentPane().add(jXHyperlink10);

        Mvisa.getContentPane().add(MBaseVisa2);

        MBaseVisa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/exec.png"))); // NOI18N
        MBaseVisa3.setTitle("Personal Parameters :");
        MBaseVisa3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N

        jXHyperlink17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/b_usradd.png"))); // NOI18N
        jXHyperlink17.setText("Changer Mot de Passe");
        jXHyperlink17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXHyperlink17MouseClicked(evt);
            }
        });
        jXHyperlink17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink17ActionPerformed(evt);
            }
        });
        MBaseVisa3.getContentPane().add(jXHyperlink17);

        Mvisa.getContentPane().add(MBaseVisa3);

        MInterVisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/Print.png"))); // NOI18N
        MInterVisa.setTitle("Some Lists :");
        MInterVisa.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N

        jXHyperlink7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/Copy.png"))); // NOI18N
        jXHyperlink7.setText("Les Inputs de la Semaine");
        jXHyperlink7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXHyperlink7ActionPerformed(evt);
            }
        });
        MInterVisa.getContentPane().add(jXHyperlink7);

        Mvisa.getContentPane().add(MInterVisa);

        jXHyperlink11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LesImages/Help.png"))); // NOI18N
        jXHyperlink11.setText("About Us");
        Mvisa.getContentPane().add(jXHyperlink11);

        javax.swing.GroupLayout DsKtpLayout = new javax.swing.GroupLayout(DsKtp);
        DsKtp.setLayout(DsKtpLayout);
        DsKtpLayout.setHorizontalGroup(
            DsKtpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
        );
        DsKtpLayout.setVerticalGroup(
            DsKtpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Mvisa, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DsKtp))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DsKtp)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Mvisa, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MvisaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MvisaMouseDragged
        // TODO add your handling code here:
        this.Mvisa.setCollapsed(true);
    }//GEN-LAST:event_MvisaMouseDragged

    private void jXHyperlink15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXHyperlink15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jXHyperlink15MouseClicked

    private void jXHyperlink15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink15ActionPerformed
        // TODO add your handling code here:
        usersInterface IntUser  =  new usersInterface();
        DsKtp.add(IntUser);
        IntUser.setVisible(true);
    }//GEN-LAST:event_jXHyperlink15ActionPerformed

    private void jXHyperlink9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXHyperlink9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jXHyperlink9MouseClicked

    private void jXHyperlink9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink9ActionPerformed
        // TODO add your handling code here:
//        frmOccuper_Chambre frmOC = new frmOccuper_Chambre();
//        DsKtp.add(frmOC);
//        frmOC.setVisible(true);
        approInterface API = new  approInterface();
        DsKtp.add(API);
        API.setVisible(true);
    }//GEN-LAST:event_jXHyperlink9ActionPerformed

    private void jXHyperlink10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXHyperlink10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jXHyperlink10MouseClicked

    private void jXHyperlink10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink10ActionPerformed
        // TODO add your handling code here:
        venteInterface VI = new venteInterface();
        DsKtp.add(VI);
        VI.setVisible(true);
    }//GEN-LAST:event_jXHyperlink10ActionPerformed

    private void jXHyperlink17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXHyperlink17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jXHyperlink17MouseClicked

    private void jXHyperlink17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink17ActionPerformed
        // TODO add your handling code here:
        new pwdInterface().setVisible(true);
    }//GEN-LAST:event_jXHyperlink17ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int msg = JOptionPane.showConfirmDialog(this, "Voulez - vous mettre fin à votre seession ?", "Easy Sales", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (msg == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jXHyperlink7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink7ActionPerformed
        // TODO add your handling code here:
        approIImpression apII = new approIImpression();
        DsKtp.add(apII);
        apII.setVisible(true);
    }//GEN-LAST:event_jXHyperlink7ActionPerformed

    private void jXHyperlink16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXHyperlink16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jXHyperlink16MouseClicked

    private void jXHyperlink16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXHyperlink16ActionPerformed
        // TODO add your handling code here:
        cycleInterface cI = new cycleInterface();
        DsKtp.add(cI);
        cI.setVisible(true);
    }//GEN-LAST:event_jXHyperlink16ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuGen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DsKtp;
    private org.jdesktop.swingx.JXTaskPane MBaseVisa;
    private org.jdesktop.swingx.JXTaskPane MBaseVisa1;
    private org.jdesktop.swingx.JXTaskPane MBaseVisa2;
    private org.jdesktop.swingx.JXTaskPane MBaseVisa3;
    private org.jdesktop.swingx.JXTaskPane MInterVisa;
    private org.jdesktop.swingx.JXTaskPane Mvisa;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink10;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink11;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink15;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink16;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink17;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink7;
    private org.jdesktop.swingx.JXHyperlink jXHyperlink9;
    // End of variables declaration//GEN-END:variables
}
