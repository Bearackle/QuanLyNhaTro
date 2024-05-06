/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Customer;
import model.User;
import view.BillPanel;
import view.Contract;
import view.Info;
import view.MainMonitor;
import view.Post_Form;
import view.ResidentRegistration;
import view.RoomServiceView;
import view.SearchMatch;
import view.Welcompage;
import view.loginAndRegister;

/**
 *
 * @author Admin
 */
public class MainController {
       private MainMonitor mainMonitor;
       private User user;
       private Customer customer;
       private CustomerDAO customerDAO;
       public MainController(User user)
       {
           this(new MainMonitor());
           this.user = user;
           this.customerDAO = new CustomerDAO();
           this.customer = customerDAO.getCustomer(user.getPhone());
           mainMonitor.setVisible(true);
       }
       public MainController(MainMonitor monitor)
       {
            this.mainMonitor = monitor;
            mainMonitor.setForm(new Welcompage());
            mainMonitor.setActionListenerForlblPostForm(new ClicklblPost());
            mainMonitor.setActionListenerForlblContract(new ClicklblContract());
            mainMonitor.setActionListenerForbtnInfo(new ClickInfo());
            mainMonitor.setActionListenerForbtnLogout(new ClickLogout());
            mainMonitor.setActionListenerForbtnSearchMatch(new ClickSearchMatch());
            mainMonitor.setActionListenerForbtnRegistration(new ClickResidentRegistration());
            mainMonitor.setActionListenerforBtnBill(new ClickBtnBill());
            mainMonitor.setActionListenerforBtnService(new ClickService());
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
            ContractController contractController = new ContractController(new Contract(), user);
            mainMonitor.setForm(contractController.RenderForm());
         //   mainMonitor.setForm();
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
      class ClickLogout implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = JOptionPane.showConfirmDialog(mainMonitor, "Bạn có chắc muốn đăng xuất?","Đăng xuất", JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.YES_OPTION)
            {
            mainMonitor.DisposeFrame();
            new LoginController(new loginAndRegister());
            }
      }
    }  
    class ClickResidentRegistration implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           RegistrationResidentController controller = new RegistrationResidentController(new ResidentRegistration(),customer);
           mainMonitor.setForm(controller.Render());
        }
        
    }
    class ClickSearchMatch implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            SearchMatchController searchMatchController = new SearchMatchController(new SearchMatch(),user,customer);
            mainMonitor.setForm(searchMatchController.Renderer());
        }
    }
    class ClickBtnBill implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BillController billController = new BillController(new BillPanel(), customer);
            mainMonitor.setForm(billController.Render());
        }
    }
    class ClickService implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ServicesController serviceController = new ServicesController(new RoomServiceView(),customer);
            mainMonitor.setForm(serviceController.Render());
        }
        
    }
}
