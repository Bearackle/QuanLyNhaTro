/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.admin;


import java.awt.event.ActionListener;
import javax.swing.JComponent;

/**
 *
 * @author Admin
 */
public class AdminMonitor extends javax.swing.JFrame {
    /**
     * Creates new form MainMonitor
     */
    public AdminMonitor() {
        initComponents();
    }
    public void setForm (JComponent com)
    {
           jPanel1.removeAll();
           jPanel1.add(com);
           jPanel1.repaint();
           jPanel1.revalidate();
    }
    public void setbtnRoom(ActionListener listener)
    {
         btnRoom.addActionListener(listener);
    }
    public void setbtnContractCustomer (ActionListener listener)
    {
        btnContractCustomer.addActionListener(listener);
    }
    public void setbtnlogout(ActionListener listener)
    {
        btnlogout.addActionListener(listener);
    }
    public void setbtnContractLandlord(ActionListener listener)
    {
        btnlandlordContract.addActionListener(listener);
    }
    public void setbtnPay(ActionListener listener){
        btnPay.addActionListener(listener);  
    }
    public void setbtnBill(ActionListener listener){
        btnBill.addActionListener(listener);
    }
//    public void setbtnStatistic(ActionListener listener){
//        btnStatistic.addActionListener(listener);
//    }
    public void setbtnReport(ActionListener listener){
        btnReport.addActionListener(listener);
    }
    public void DisposeFrame()
    {
        this.setVisible(false);
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu1 = new view.Menu();
        jLabel1 = new javax.swing.JLabel();
        btnRoom = new javax.swing.JButton();
        btnContractCustomer = new javax.swing.JButton();
        btnBill = new javax.swing.JButton();
        btnlandlordContract = new javax.swing.JButton();
        btnPay = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(900, 600));

        menu1.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel1.setFont(new java.awt.Font("Cascadia Code", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        jLabel1.setText("Nhà Trọ");

        btnRoom.setBackground(new java.awt.Color(51, 153, 255));
        btnRoom.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnRoom.setText("Quản lý phòng ở");
        btnRoom.setBorderPainted(false);
        btnRoom.setContentAreaFilled(false);
        btnRoom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRoom.setPreferredSize(new java.awt.Dimension(100, 30));

        btnContractCustomer.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnContractCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnContractCustomer.setText("Hợp đồng khách");
        btnContractCustomer.setBorderPainted(false);
        btnContractCustomer.setContentAreaFilled(false);
        btnContractCustomer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnContractCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnContractCustomer.setPreferredSize(new java.awt.Dimension(100, 30));

        btnBill.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnBill.setForeground(new java.awt.Color(255, 255, 255));
        btnBill.setText("Hóa Đơn");
        btnBill.setBorderPainted(false);
        btnBill.setContentAreaFilled(false);
        btnBill.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBill.setPreferredSize(new java.awt.Dimension(100, 30));

        btnlandlordContract.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnlandlordContract.setForeground(new java.awt.Color(255, 255, 255));
        btnlandlordContract.setText("Hợp đồng chủ");
        btnlandlordContract.setBorderPainted(false);
        btnlandlordContract.setContentAreaFilled(false);
        btnlandlordContract.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnPay.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnPay.setForeground(new java.awt.Color(255, 255, 255));
        btnPay.setText("Thanh toán");
        btnPay.setBorderPainted(false);
        btnPay.setContentAreaFilled(false);
        btnPay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnlogout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnlogout.setForeground(new java.awt.Color(255, 255, 255));
        btnlogout.setText("Đăng xuất");
        btnlogout.setBorder(null);
        btnlogout.setBorderPainted(false);
        btnlogout.setContentAreaFilled(false);

        btnReport.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnReport.setForeground(new java.awt.Color(255, 255, 255));
        btnReport.setText("Báo cáo");
        btnReport.setBorderPainted(false);
        btnReport.setContentAreaFilled(false);
        btnReport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContractCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlandlordContract, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPay)
                    .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnContractCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnlandlordContract, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(menu1, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AdminMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>  
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMonitor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBill;
    private javax.swing.JButton btnContractCustomer;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnRoom;
    private javax.swing.JButton btnlandlordContract;
    private javax.swing.JButton btnlogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private view.Menu menu1;
    // End of variables declaration//GEN-END:variables
}
