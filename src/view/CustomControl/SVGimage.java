/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class SVGimage extends JLabel{
    private FlatSVGIcon svgIcon;
    public void setSVGImage(String image, int width,int height)
    {
        svgIcon = new FlatSVGIcon(image,width,height);
        setIcon(svgIcon);
    }
}
