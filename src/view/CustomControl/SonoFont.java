/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class SonoFont {
    public static void setSonoFontForAllComponent()
    {
         try {
            File file = new File("src/resource/Sono-Regular.ttf");
           Font font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(44f).deriveFont(Font.BOLD);
            UIManager.put("Label.font", font);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
    }
}
