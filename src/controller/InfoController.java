/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.CustomerDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contract;
import model.Customer;
import model.Room;
import model.User;
import view.Info;
import view.NewCustomer;

/**
 *
 * @author Admin
 */
public class InfoController {
    private Info info;
    private final ContractDAO contractDAO;
    private final RoomDAO roomDAO;
    private final CustomerDAO customerDAO;
    private Customer customer;
    private User user;
    private NewCustomer newCustomer;
    public InfoController(Info info,User user)
    {
        customerDAO = new CustomerDAO();
        contractDAO = new ContractDAO();
        roomDAO = new RoomDAO();
        this.info = info;
        this.user = user;
        RenderInformation();
        info.setUpdateBtn(new clickUpdateBtn());
        info.setBecomeCustomerBtn(new clickNewCustomer());
        
    }
    public void RenderInformation()
    {
        customer = customerDAO.getCustomer(user.getPhone());
        if (customer!=null)
        {
        info.setCustomerInfo(customer);
        RenderContractList();
        }
        else 
        {
           info.setUserInfo(user);
        }
    }
    public void RenderContractList()
    {
        List<Contract> contracts;
        contracts = contractDAO.getAllContractByCustomer(customer.getCCCD());
        info.initContract(contracts);
    }
    public Info Render()
    {
        return info;
    }
    public Room getRoomByContract(int id)
    {
        return roomDAO.getRoomInfoWithId(id);  
    }
    class clickUpdateBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    class clickNewCustomer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            newCustomer = new NewCustomer(user);
            newCustomer.setDefaultCloseOperation(NewCustomer.DISPOSE_ON_CLOSE);
            newCustomer.setVisible(true);
            newCustomer.setActionListenerForBtn(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                     customerDAO.CreeateNewCustomer(newCustomer.CreateCustomer()); 
                     newCustomer.dispose();
                     JOptionPane.showMessageDialog(info,"Đã Đăng ký khách hàng thành công!!");
                }
        });
        }     
    }
}
