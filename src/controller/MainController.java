/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import view.Contract;
import view.Info;
import view.MainMonitor;
import view.Post_Form;

/**
 *
 * @author Admin
 */
public class MainController {
       private MainMonitor mainMonitor;
       private User user;
       public MainController(User user)
       {
           this(new MainMonitor());
           this.user = user;
           mainMonitor.setVisible(true);
       }
       public MainController(MainMonitor monitor)
       {
            this.mainMonitor = monitor;
            mainMonitor.setActionListenerForlblPostForm(new ClicklblPost());
            mainMonitor.setActionListenerForlblContract(new ClicklblContract());
            mainMonitor.setActionListenerForbtnInfo(new ClickInfo());
             mainMonitor.setVisible(true);
       }
     class ClicklblPost implements ActionListener 
      {
        @Override
        public void actionPerformed(ActionEvent e) {
            PostController postController = new PostController(new Post_Form());
            mainMonitor.setForm(postController.renderForm());
           
        }     
      }
      class ClicklblContract implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            mainMonitor.setForm(new Contract());
        }
      }
      class ClickInfo implements ActionListener
      {

        @Override
        public void actionPerformed(ActionEvent e) {
            InfoController infoController = new InfoController(new Info(),user);
            mainMonitor.setForm(infoController.Render());
        }
          
      }
}
