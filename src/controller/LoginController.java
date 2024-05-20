/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import controller.ADMINCONTROLLER.AdminController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import model.User;
import view.Login.RegisterForm;
import view.Login.loginAndRegister;
import view.admin.AdminMonitor;

/**
 *
 * @author Admin
 */
public class LoginController {
       private loginAndRegister logAndRegister;
       private AccountDAO accountDAO;
       private User authenticatedUser;
       public LoginController(loginAndRegister logAndRegister)
       {
           this.logAndRegister = logAndRegister;
           accountDAO = new AccountDAO();
           logAndRegister.setVisible(true);
           logAndRegister.setActionBtnLogin(new btnLogin());
           logAndRegister.setKeyListenerForthis(new EnterLogin());
           logAndRegister.setActionListenerforRegisterBtn(new btnNewRegister());
       }
       public void getDataLogin() 
       {
           Thread authen = new Thread(() -> {  authenticatedUser = accountDAO.Authentication(logAndRegister.getDataInput());});
           authen.start();
           try{
               authen.join();
           } catch(InterruptedException e){
               e.printStackTrace();
           }
           if (authenticatedUser == null)
               JOptionPane.showMessageDialog(logAndRegister, "Thông tin đăng nhập sai");
           else {
               if ("ADMIN".equals(authenticatedUser.getRole())){
                   var Admin = new  AdminController();
               } else{
                   var mainMonitor = new MainController(authenticatedUser);
               }
               logAndRegister.setVisible(false);
           }
       }
       class btnLogin implements ActionListener
       {
        @Override
        public void actionPerformed(ActionEvent e) {
             getDataLogin();
        }
       }
        class EnterLogin implements KeyListener{
               @Override
               public void keyTyped(KeyEvent e) {       
               }
               @Override
               public void keyPressed(KeyEvent e) {
                   if ( e.getKeyCode() == KeyEvent.VK_ENTER)
                   {
                       getDataLogin();
                   }
               }
               @Override
               public void keyReleased(KeyEvent e) {
               }
        }
        class btnNewRegister implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             logAndRegister.setVisible(false);
             RegisterController register = new RegisterController(new RegisterForm());
             
        }
        }
}
