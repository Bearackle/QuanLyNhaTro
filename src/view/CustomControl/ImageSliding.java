/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class ImageSliding extends  JPanel{
    private ArrayList<BufferedImage> listImg;
    private double location;
    public ImageSliding(ArrayList<BufferedImage> listImg)
    {
        this.listImg = listImg;
        location = 0.0;
    }
    public void setLocation(double location)
    {
        this.location = Math.min(1.0, Math.max(0.0, location));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int dx = (int) (100 * location);
        g.drawImage(listImg.get(0), -dx, 0, null);
        g.drawImage(listImg.get(1), -dx + listImg.get(1).getWidth(), 0, this);
    }
    
}
