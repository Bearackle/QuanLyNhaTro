/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import model.User;
/**
 *
 * @author Admin
 */
public class RegisterPanel extends javax.swing.JPanel {

    /**
     * Creates new form RegisterPanel
     */
    public RegisterPanel() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        initComponents();
    }
    public void addPlaceHolder(JTextField txtf)
    {
        Font font = txtf.getFont();
        font = font.deriveFont(Font.ITALIC);
        txtf.setFont(font);
        txtf.setForeground(Color.gray);
    }
      public void removePlaceHolder(JTextField txtf)
    {
        Font font = txtf.getFont();
        font = font.deriveFont(Font.PLAIN|Font.BOLD);
        txtf.setFont(font);
        txtf.setForeground(Color.black);
    }
    public User getUser()
    {
        User user = new User();
        user.setName(txthoten.getText());
        user.setPhone(txtsdt.getText());
        user.setEmail(txtemail.getText());
        user.setRole(String.valueOf(jComboBox2.getSelectedItem()));
        user.setPassword(String.valueOf(new String(pwf.getPassword()))); 
        return user;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        pwf = new javax.swing.JPasswordField();
        txtsdt = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 600));
        setPreferredSize(new java.awt.Dimension(500, 650));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Register");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentX(0.5F);
        add(jLabel1, new java.awt.GridBagConstraints());

        txthoten.setForeground(new java.awt.Color(153, 153, 153));
        txthoten.setText("Nhập họ tên...");
        txthoten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txthoten.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txthotenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txthotenFocusLost(evt);
            }
        });
        txthoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthotenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 6);
        add(txthoten, gridBagConstraints);

        pwf.setForeground(new java.awt.Color(128, 128, 128));
        pwf.setText("Nhập mật khẩu...");
        pwf.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pwf.setEchoChar('\u0000');
        pwf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pwfFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 6, 0, 6);
        add(pwf, gridBagConstraints);

        txtsdt.setForeground(new java.awt.Color(153, 153, 153));
        txtsdt.setText("Nhập số điện thoại...");
        txtsdt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtsdt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtsdtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsdtFocusLost(evt);
            }
        });
        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 6);
        add(txtsdt, gridBagConstraints);

        txtemail.setForeground(new java.awt.Color(153, 153, 153));
        txtemail.setText("Email...");
        txtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtemailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtemailFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 6);
        add(txtemail, gridBagConstraints);

        jComboBox1.setBackground(new java.awt.Color(204, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giới tính", "Nam ", "Nữ", "Khác" }));
        jComboBox1.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vai trò", "Người thuê phòng", "Người cho thuê" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        add(jComboBox2, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Đăng ký");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 311;
        gridBagConstraints.ipady = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 0, 19, 6);
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txthotenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txthotenFocusGained
        if (txthoten.getText().equals("Nhập họ tên..."))
        {
            txthoten.setText(null);
            txthoten.requestFocus();
            removePlaceHolder(txthoten);
        }
    }//GEN-LAST:event_txthotenFocusGained

    private void txthotenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txthotenFocusLost
        if(txthoten.getText().length() == 0)
        {
            addPlaceHolder(txthoten);
            txthoten.setText("Nhập họ tên...");
        }
    }//GEN-LAST:event_txthotenFocusLost

    private void pwfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwfFocusGained
        if (pwf.getText().equals("Nhập mật khẩu..."))
        {
            pwf.setText(null);
            pwf.requestFocus();
            pwf.setEchoChar('*');
            removePlaceHolder(pwf);
        }
    }//GEN-LAST:event_pwfFocusGained

    private void pwfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwfFocusLost
        if(pwf.getText().length() == 0 || pwf.getText().contains("Nhập mật khẩu..."))
        {
            addPlaceHolder(pwf);
            pwf.setText("Nhập mật khẩu...");
            pwf.setEchoChar('\u0000');
        }
    }//GEN-LAST:event_pwfFocusLost

    private void txtsdtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsdtFocusGained
        if (txtsdt.getText().equals("Nhập số điện thoại...") || txtsdt.getText().equals("Số điện thoại không hợp lệ.."))
           {
               txtsdt.setText(null);
               txtsdt.requestFocus();
               removePlaceHolder(txtsdt);
           }
    }//GEN-LAST:event_txtsdtFocusGained

    private void txtsdtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsdtFocusLost
        if (txtsdt.getText().length() < 10 && txtsdt.getText().length() != 0 )
        {
            addPlaceHolder(txtsdt);
            txtsdt.setText("Số điện thoại không hợp lệ...");
        } 
        else
        if(txtsdt.getText().length() == 0)
        {
            addPlaceHolder(txtsdt);
            txtsdt.setText("Nhập số điện thoại...");
        }
    }//GEN-LAST:event_txtsdtFocusLost

    private void txtemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusGained
        if (txtemail.getText().equals("Email..."))
           {
               txtemail.setText(null);
               txtemail.requestFocus();
               removePlaceHolder(txtemail);
           }
    }//GEN-LAST:event_txtemailFocusGained

    private void txtemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtemailFocusLost
        
        if(txtemail.getText().length() == 0)
        {
            addPlaceHolder(txtemail);
            txtemail.setText("Email...");
        }else if (!txtemail.getText().contains("@"))
        {
            addPlaceHolder(txtemail);
            txtemail.setText("email không hợp lệ...");
        }
    }//GEN-LAST:event_txtemailFocusLost
    public void setActionListenerbtnSumbit(ActionListener listener)
    {
        jButton1.addActionListener(listener);
    }
    private void txthotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthotenActionPerformed

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField pwf;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
