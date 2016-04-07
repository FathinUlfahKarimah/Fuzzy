/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

/**
 *
 * @author UPEFATHIN
 */
public class Fuzzy1 extends javax.swing.JFrame {
    public double[] output_rule_kel_2 = new double[6];

public double[] u_output_kel_2 = new double[6];
public double z_kelompok_2;
public double min_result_kelompok;
public double u_aset_SM;
public double u_aset_SKM;
public double u_jaminan_SM;
public double u_jaminan_CM;
public double u_jaminan_SKM;
public double kondisi_aset;
public double kondisi_jaminan;

    /**
     * Creates new form Fuzzy1
     */
    public Fuzzy1() {
        initComponents();
    }

    class Jaminan {
        public double data_jaminan;
        // Himpunan Sangat Kurang Mendukung
        private double SangatKurangMendukung() {
            if (data_jaminan == 0) {
                return data_jaminan = 1;
            } 
            else if ((data_jaminan >= 1) && (data_jaminan < 7.5)) {
                return (7.5 - data_jaminan) / (7.5 - 1);
            } else {
                return 0;
            }
        }
        // Himpunan Cukup Mendukung
        private double CukupMendukung() {
            if ((data_jaminan >= 5) && (data_jaminan < 7.5)) {
                return (data_jaminan - 5) / (7.5 - 5);
            } else if (data_jaminan == 7.5) {
                return data_jaminan = 1;
            } else if ((data_jaminan > 7.5) && (data_jaminan <= 10)) {
                return (10 - data_jaminan) / (10 - 7.5);
            } else {
                return 0;
            }
        } // end of cukup mendukung
        
        private double SangatMendukung() {
            if ((data_jaminan >= 7.5) && (data_jaminan <= 15)) {
                return (data_jaminan - 7.5) / (15 - 7.5);
            } else {
                return 0;
            }
        } // end sangat mendukung
    } //end class Jaminan


class Aset {
    public double data_aset;
    // Himpunan Sangat Kurang Mendukung
    private double SangatKurangMendukung() {
        if (data_aset == 0) {
            return data_aset = 1;
        } else if ((data_aset > 0) && (data_aset <= 300000000)) {
            return (300000000 - data_aset) / (300000000 - 0);
        } else {
            return 0;
        }
    }
    private double SangatMendukung() {
        if (data_aset == 500000000) {
            return data_aset = 1;
        } else if ((data_aset >= 200000000) && (data_aset < 500000000)) {
            return (data_aset - 200000000) / (500000000 - 200000000);
        } else {
            return 0;
        }
    } // end sangat mendukung
} //end class Aset


class KelayakanKelompok2 extends Fuzzy1 {
    public double[] nilai_kelayakan_kelompok2;
    KelayakanKelompok2() {
        nilai_kelayakan_kelompok2 = new double[6];
        nilai_kelayakan_kelompok2[0] = (104 + 123) / 2;   // sangat layak dengan resiko rendah
        nilai_kelayakan_kelompok2[1] = (80 + 104) / 2;    // layak dengan resiko tinggi
        nilai_kelayakan_kelompok2[2] = (80 + 104) / 2;    // layak
        nilai_kelayakan_kelompok2[3] = (44 + 80) / 2;    //  cukup layak
        nilai_kelayakan_kelompok2[4] = (2 + 20) / 2;     //  kurang layak
        nilai_kelayakan_kelompok2[5] = (2 + 20) / 2;      // sangat kurang layak
    }
}

private void Compute_Membership() {
    Jaminan jaminan = new Jaminan();
    Aset aset = new Aset();
    jaminan.data_jaminan = kondisi_jaminan;
    aset.data_aset = kondisi_aset;
    u_jaminan_SM = jaminan.SangatMendukung();
    u_jaminan_CM = jaminan.CukupMendukung();
    u_jaminan_SKM = jaminan.SangatKurangMendukung();
    u_aset_SM = aset.SangatMendukung();
    u_aset_SKM = aset.SangatKurangMendukung();
}


private double Cari_Min2(double a, double b) {
    if (a < b) {
        min_result_kelompok = a;
        return min_result_kelompok;
    } else {
        min_result_kelompok = b;
        return min_result_kelompok;
    }
}


private void applyRule_Kelompok_2() {
    KelayakanKelompok2 kelompok2 = new KelayakanKelompok2();
    output_rule_kel_2[0] = Cari_Min2(u_jaminan_SM, u_aset_SM);
    u_output_kel_2[0] = kelompok2.nilai_kelayakan_kelompok2[0];
    
    output_rule_kel_2[1] = Cari_Min2(u_jaminan_SM, u_aset_SKM);
    u_output_kel_2[1] = kelompok2.nilai_kelayakan_kelompok2[1];
    
    output_rule_kel_2[2] = Cari_Min2(u_jaminan_CM, u_aset_SM);
    u_output_kel_2[2] = kelompok2.nilai_kelayakan_kelompok2[2];
    
    output_rule_kel_2[3] = Cari_Min2(u_jaminan_CM, u_aset_SKM);
    u_output_kel_2[3] = kelompok2.nilai_kelayakan_kelompok2[3];
    
    output_rule_kel_2[4] = Cari_Min2(u_jaminan_SKM, u_aset_SM);
    u_output_kel_2[4] = kelompok2.nilai_kelayakan_kelompok2[4];
    
    output_rule_kel_2[5] = Cari_Min2(u_jaminan_SKM, u_aset_SKM);
    u_output_kel_2[5] = kelompok2.nilai_kelayakan_kelompok2[5];
}


private void Compute_Output_Kelompok_2() {
    double temp_1 = 0;
    double temp_2 = 0;
    for (int i = 0; i < 6; i++) {
        temp_1 += output_rule_kel_2[i] * u_output_kel_2[i];
        temp_2 += output_rule_kel_2[i];
    }
    z_kelompok_2 = temp_1 / temp_2;
    
    System.out.print("\n kelompok");
    System.out.print("OutPut Kel-2 " + z_kelompok_2);
    txt_evaluasi.setText(String.valueOf(z_kelompok_2));
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_evaluasi = new javax.swing.JTextField();
        jProses = new javax.swing.JButton();
        txt_aset = new javax.swing.JFormattedTextField();
        txt_jaminan = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Jaminan");

        jLabel2.setText("Aset");

        jLabel3.setText("Skor Evaluasi");

        txt_evaluasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_evaluasiActionPerformed(evt);
            }
        });

        jProses.setText("Proses");
        jProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProsesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProses)
                            .addComponent(txt_evaluasi, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_jaminan, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(txt_aset))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_jaminan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_aset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_evaluasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jProses)
                .addContainerGap(44, Short.MAX_VALUE))
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

    private void txt_evaluasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_evaluasiActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_evaluasiActionPerformed

    private void jProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProsesActionPerformed
        // TODO add your handling code here:
        kondisi_jaminan=Double.parseDouble(txt_jaminan.getText());
        kondisi_aset=Double.parseDouble(txt_aset.getText());
        Compute_Membership();
        applyRule_Kelompok_2();
        Compute_Output_Kelompok_2();
    }//GEN-LAST:event_jProsesActionPerformed

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
            java.util.logging.Logger.getLogger(Fuzzy1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fuzzy1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fuzzy1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fuzzy1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fuzzy1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jProses;
    private javax.swing.JFormattedTextField txt_aset;
    private javax.swing.JTextField txt_evaluasi;
    private javax.swing.JFormattedTextField txt_jaminan;
    // End of variables declaration//GEN-END:variables
}
