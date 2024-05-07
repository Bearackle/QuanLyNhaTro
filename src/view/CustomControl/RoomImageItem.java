/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class RoomImageItem extends JPanel{
    private final JLabel lblName;
    private final JButton btnDelete;
    public RoomImageItem(String Name){
        lblName = new JLabel(Name);
        btnDelete = new JButton();
        ImageIcon icon = new ImageIcon("icon/remove.png");
        btnDelete.setIcon(icon);
        this.setLayout(new FlowLayout());
        this.add(lblName);
        this.add(btnDelete);
        this.setSize(30,30);
        this.repaint();
        this.revalidate();
    }
}
