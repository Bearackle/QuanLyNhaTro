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
public class ActionPanel extends javax.swing.JPanel {
    /**
     * Creates new form ActionPanel
     */
    public ActionPanel() {
        initComponents();
    }
    public void setActionListener(ActionListener listener)
    {
        actionBtnDelete1.addActionListener(listener);
    }
    public void setImageForActionPanel(String img){
         actionBtnDelete1.setImage(img);
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

        actionBtnDelete1.setPreferredSize(new java.awt.Dimension(40, 40));
        add(actionBtnDelete1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.CustomControl.ActionBtnDelete actionBtnDelete1;
    // End of variables declaration//GEN-END:variables
}
