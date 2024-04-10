/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Image;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.tree.DefaultTreeCellEditor;
import model.ContractDetail;
import model.Room;
import view.CustomControl.CustomScrollBarUI;
/**
 *
 * @author Admin
 */
public class Contract extends javax.swing.JPanel {

    /**
     * Creates new form Contract
     */
    public Contract() {
        initComponents();
    }
    public void LoadContract(ContractDetail contract)
    {        
       String ContractHtml = """
        <!DOCTYPE html>
        <html lang="vi">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hợp đồng thuê phòng trọ</title>
        <style>
            p{margin:20px;
             font-size: 14;}                       
        </head>
        <body>
        <h1 style="text-align: center;">HỢP ĐỒNG THUÊ PHÒNG TRỌ</h1>

        <p style="text-align: center;">Hôm nay ngày %d tháng %d năm %d; tại địa chỉ: %s</p>

        <p style="text-align: center;">………………………………………………………………………………</p>

        <p style="padding:20px;"><strong>Chúng tôi gồm:</strong></p>

        <p><strong>1. Đại diện bên cho thuê phòng trọ (Bên A):</strong></p>
        <p>Ông/bà: Cty Quản Lý Nhà Trọ  NDH</p>
        <p>Số điện thoại: 086 270 3740</p>

        <p><strong>2. Bên thuê phòng trọ (Bên B):</strong></p>

        <p>Ông/bà: %s Sinh ngày:%d-%d-%d</p>

        <p>Nơi đăng ký HK thường trú: %s </p>

        <p>Số CCCD: %s </p>

        <p>Số điện thoại: %s  </p>

        <p>Sau khi bàn bạc trên tinh thần dân chủ, hai bên cùng có lợi, cùng thống nhất như sau:</p>

        <p>Bên A đồng ý cho bên B thuê 01 phòng ở tại địa chỉ: %s </p>

        <p>………………………………………………………………………………………</p>

        <p>Giá thuê: %d vnd/tháng</p>

        <p>Hình thức thanh toán: Chuyển Khoản qua ngân hàng VietComBank</p>

        <p>………………………………………………………………………………………</p>

        <p>Tiền điện %d vnd/kwh tính theo chỉ số công tơ, thanh toán vào cuối các tháng.</p>

        <p>Tiền nước: %d vnd/người thanh toán vào đầu các tháng.</p>
        <p>Hợp đồng có giá trị %d tháng kể từ ngày ký</p>                
        <p>Tiền đặt cọc: %d (vnd)<p>
        </body>
        </html>""".formatted(model.Contract.getCalendar(contract.getSign_date()).get(Calendar.DAY_OF_MONTH),
                model.Contract.getCalendar(contract.getSign_date()).get(Calendar.MONTH),
                model.Contract.getCalendar(contract.getSign_date()).get(Calendar.YEAR),
                contract.getLocation(), 
                contract.getCustomer_Name(),
                model.Contract.getCalendar(contract.getBirthDay()).get(Calendar.DAY_OF_MONTH),
                model.Contract.getCalendar(contract.getBirthDay()).get(Calendar.MONTH),
                model.Contract.getCalendar(contract.getBirthDay()).get(Calendar.YEAR),
                contract.getPermanent_resident(), String.valueOf(contract.getCustomer_ID()),contract.getPhone(),contract.getLocation(),
                contract.getPrice(),contract.getElectricPrice(),
                contract.getWaterPrice(), contract.getDuration(),
                contract.getDeposit());
        jTextPane1.setText(ContractHtml);
        jTextPane1.setEditable(false);
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setPreferredSize(new java.awt.Dimension(700, 600));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        jTextPane1.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(jTextPane1);
        jTextPane1.getAccessibleContext().setAccessibleDescription("text/html\n");

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
