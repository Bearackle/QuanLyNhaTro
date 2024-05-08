/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class itemUpload extends JPanel{
    private JLabel lblContent;
    private ActionBtnDelete btnDelete;
    private String value;
     public itemUpload(String content){
         value = content;
         this.setLayout(new FlowLayout());
         lblContent = new JLabel(value);
         btnDelete = new ActionBtnDelete();
         btnDelete.setSVGImage("icon/deleteThis.svg", 15,15);
         this.add(lblContent);
         this.add(btnDelete);
         this.repaint();
         this.revalidate();
     }
     public void setActionListenerDelete(ActionListener listener){
         btnDelete.addActionListener(listener);
     }
}
