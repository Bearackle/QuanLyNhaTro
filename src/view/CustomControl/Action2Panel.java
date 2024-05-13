/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.CustomControl;

import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class Action2Panel extends javax.swing.JPanel {
    private String[] itemImages;
    /**
     * Creates new form Action2Panel
     */
    public Action2Panel(String items) {
        initComponents();
        itemImages = items.split(" ");
        actionBtnDelete1.setImage(itemImages[0]);
        actionBtnDelete2.setImage(itemImages[1]);
        btnDetail.setImage(itemImages[2]);
//        actionBtnDelete1.setImage("icon/delete.svg");
//        actionBtnDelete2.setImage("icon/extend.svg");
//        btnDetail.setImage("icon/detail3.svg");
    }
    public void setActionlistenerForbtnDelete(ActionListener listener){
         actionBtnDelete1.addActionListener(listener);
    }
    public void setActionListenerForbtnExtend(ActionListener listener){
        actionBtnDelete2.addActionListener(listener);
    }
    public void setActionListenerForbtnDetail(ActionListener listener){
        btnDetail.addActionListener(listener);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionBtnDelete1 = new view.CustomControl.ActionBtnDelete();
        actionBtnDelete2 = new view.CustomControl.ActionBtnDelete();
        btnDetail = new view.CustomControl.ActionBtnDelete();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(actionBtnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionBtnDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(actionBtnDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(actionBtnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.CustomControl.ActionBtnDelete actionBtnDelete1;
    private view.CustomControl.ActionBtnDelete actionBtnDelete2;
    private view.CustomControl.ActionBtnDelete btnDetail;
    // End of variables declaration//GEN-END:variables
}
