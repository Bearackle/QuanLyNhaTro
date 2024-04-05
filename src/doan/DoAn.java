/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package doan;

import java.awt.Frame;
import javax.swing.JFrame;
import view.MainMonitor;

/**
 *
 * @author Admin
 */
public class DoAn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMonitor().setVisible(true);
            }
        });
    }
}
