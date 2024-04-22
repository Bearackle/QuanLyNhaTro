/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class BufferedImageCreator {
    public static BufferedImage getBufferedImage(String path) 
    {
        try {
        BufferedImage input = ImageIO.read(new File(path));
        BufferedImage image = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        return image;
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
}
