/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;
import view.loginAndRegister;

/**
 *
 * @author Admin
 */
public class LoginController {
       private loginAndRegister logAndRegister;
       private AccountDAO accountDAO;
       public LoginController(loginAndRegister logAndRegister)
       {
           this.logAndRegister = logAndRegister;
           logAndRegister.setVisible(true);
           logAndRegister.setActionBtnLogin(new btnLogin());
       }
       public void getDataLogin()
       {
           accountDAO = new AccountDAO();
           User authenticatedUser = logAndRegister.getDataInput();
           authenticatedUser = accountDAO.Authentication(authenticatedUser);
           if (authenticatedUser == null)
               JOptionPane.showMessageDialog(logAndRegister, "Thông tin đăng nhập sai");
           else {
               new MainController(authenticatedUser);
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
}
