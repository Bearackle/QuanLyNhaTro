/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import controller.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.Login.loginAndRegister;
import view.admin.AdminMonitor;
import view.admin.ContractCustomer;

/**
 *
 * @author Admin
 */
public class AdminController {
      private AdminMonitor view;
      public AdminController(){
          view = new AdminMonitor();
          view.setbtnContractCustomer(new ClickContractCustomer());
          view.setbtnRoom(new ClickRoom());
          view.setbtnContractLandlord(new ClickContractLandlord());
          view.setbtnBill(new ClickBill());
          view.setbtnPay(new ClickPay());
          view.setbtnStatistic(new ClickStatistic());
          view.setbtnlogout(new Logout());
          view.setVisible(true);
      }
    class ClickContractCustomer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminContractController controller = new AdminContractController(new ContractCustomer());
            view.setForm(controller.render());
        }
    }
    class ClickRoom implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent e) {
               }
    }
    class ClickContractLandlord implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent e) {
               }
    }
    class ClickBill implements ActionListener {
         @Override
        public void actionPerformed(ActionEvent e) {
               }
    }
    class ClickPay implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent e) {
               }
    }
    class ClickStatistic implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
               }
    }
       class Logout implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn đăng xuất?","Đăng xuất", JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.YES_OPTION)
            {
            view.DisposeFrame();
            new LoginController(new loginAndRegister());
            }
      }
    }  
}
