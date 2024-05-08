/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class ActionBtnDelete extends JButton{
    private FlatSVGIcon svgIcon;
    private void setSVGImage(String image, int width,int height)
    {
        svgIcon = new FlatSVGIcon(image,width,height);
        this.setIcon(svgIcon);
    }
    public ActionBtnDelete()
    {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3,3,3,3));
    }
    public void setImage(String image){
         setSVGImage(image,20, 20);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width,height);
        int x = (width - size) /2;
        int y = (height - size) /2;
        g2.setColor(new Color(255,255,255));
        g2.fill(new Ellipse2D.Double(x,y,size,size));
        g2.dispose();
        super.paintComponent(g);
    }
    
}
