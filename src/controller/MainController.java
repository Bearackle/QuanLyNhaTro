/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.LandLordDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Customer;
import model.LandLord;
import model.User;
import view.Bill.BillPanel;
import view.Contract.Contract;
import view.CustomControl.NoFunctionFound;
import view.User.Info;
import view.User.LandLordView;
import view.MainMonitor;
import view.Room.Post_Form;
import view.User.ResidentRegistration;
import view.Room.RoomServiceView;
import view.User.SearchMatch;
import view.Login.Welcompage;
import view.Login.loginAndRegister;

/**
 *
 * @author Admin
 */
public class MainController {
       private MainMonitor mainMonitor;
       private User user;
       private Customer customer;
       private CustomerDAO customerDAO;
       private LandLordDAO landLordDAO;
       private LandLord landLord;
       public MainController(User user)
       {
           this(new MainMonitor());
           this.user = user;
           this.customerDAO = new CustomerDAO();
           this.landLordDAO = new LandLordDAO();
           this.customer = customerDAO.getCustomer(user.getPhone());
           this.landLord = landLordDAO.getLandLord(user.getPhone());
           if(landLord == null) mainMonitor.setLandlordBtn(false);
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
            monitor.setActionListenerforLandlordbtn(new clickShowLandLord());
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
             if(customer == null){
                mainMonitor.setForm(new NoFunctionFound());
            }else{
            ContractController contractController = new ContractController(new Contract(), user);
            mainMonitor.setForm(contractController.RenderForm());
             }
            
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
             if(customer == null){
                mainMonitor.setForm(new NoFunctionFound());
            }
            else {
            RegistrationResidentController controller = new RegistrationResidentController(new ResidentRegistration(),customer);
            mainMonitor.setForm(controller.Render());
            }
          
        }
        
    }
    class ClickSearchMatch implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             if(customer == null){
                mainMonitor.setForm(new NoFunctionFound());
            }else 
             {
            SearchMatchController searchMatchController = new SearchMatchController(new SearchMatch(),user,customer);
            mainMonitor.setForm(searchMatchController.Renderer());
             }
        }
    }
    class ClickBtnBill implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (customer == null) {
                mainMonitor.setForm(new NoFunctionFound());
                return;
            }
            BillController billController = new BillController(new BillPanel(),customer);
            mainMonitor.setForm(billController.Render());
        }
    }
    class ClickService implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(customer == null){
                mainMonitor.setForm(new NoFunctionFound());
            }
            else {
            ServicesController serviceController = new ServicesController(new RoomServiceView(),customer);
            mainMonitor.setForm(serviceController.Render());
            }
        }
    }
    class clickShowLandLord implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            LandlordController landlordController = new LandlordController(new LandLordView(), landLord);
            mainMonitor.setForm(landlordController.render());
        }
        
    }
}
