/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

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
               Font font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(18f).deriveFont(Font.BOLD);
               setUIFont(new javax.swing.plaf.FontUIResource(font));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get(key);
          if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put(key, f);
          }
        } 
    }
