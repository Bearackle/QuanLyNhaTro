/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
           if (accountDAO.Authentication(logAndRegister.getDataInput()) != null)
           {
               new MainController(accountDAO.Authentication(logAndRegister.getDataInput()));
               logAndRegister.setVisible(false);
           }
           else 
               JOptionPane.showMessageDialog(logAndRegister, "Thông tin đăng nhập sai");
       }
       class btnLogin implements ActionListener
       {
        @Override
        public void actionPerformed(ActionEvent e) {
             getDataLogin();
        }
       }
}
